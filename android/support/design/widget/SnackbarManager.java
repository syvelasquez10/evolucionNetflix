// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import java.lang.ref.WeakReference;
import android.os.Message;
import android.os.Handler$Callback;
import android.os.Looper;
import android.os.Handler;

class SnackbarManager
{
    private static SnackbarManager sSnackbarManager;
    private SnackbarManager$SnackbarRecord mCurrentSnackbar;
    private final Handler mHandler;
    private final Object mLock;
    private SnackbarManager$SnackbarRecord mNextSnackbar;
    
    private SnackbarManager() {
        this.mLock = new Object();
        this.mHandler = new Handler(Looper.getMainLooper(), (Handler$Callback)new SnackbarManager$1(this));
    }
    
    private boolean cancelSnackbarLocked(final SnackbarManager$SnackbarRecord snackbarManager$SnackbarRecord) {
        final SnackbarManager$Callback snackbarManager$Callback = (SnackbarManager$Callback)snackbarManager$SnackbarRecord.callback.get();
        if (snackbarManager$Callback != null) {
            snackbarManager$Callback.dismiss();
            return true;
        }
        return false;
    }
    
    static SnackbarManager getInstance() {
        if (SnackbarManager.sSnackbarManager == null) {
            SnackbarManager.sSnackbarManager = new SnackbarManager();
        }
        return SnackbarManager.sSnackbarManager;
    }
    
    private void handleTimeout(final SnackbarManager$SnackbarRecord snackbarManager$SnackbarRecord) {
        synchronized (this.mLock) {
            if (this.mCurrentSnackbar == snackbarManager$SnackbarRecord || this.mNextSnackbar == snackbarManager$SnackbarRecord) {
                this.cancelSnackbarLocked(snackbarManager$SnackbarRecord);
            }
        }
    }
    
    private boolean isCurrentSnackbar(final SnackbarManager$Callback snackbarManager$Callback) {
        return this.mCurrentSnackbar != null && this.mCurrentSnackbar.isSnackbar(snackbarManager$Callback);
    }
    
    private boolean isNextSnackbar(final SnackbarManager$Callback snackbarManager$Callback) {
        return this.mNextSnackbar != null && this.mNextSnackbar.isSnackbar(snackbarManager$Callback);
    }
    
    private void scheduleTimeoutLocked(final SnackbarManager$SnackbarRecord snackbarManager$SnackbarRecord) {
        if (snackbarManager$SnackbarRecord.duration == -2) {
            return;
        }
        int access$100 = 2750;
        if (snackbarManager$SnackbarRecord.duration > 0) {
            access$100 = snackbarManager$SnackbarRecord.duration;
        }
        else if (snackbarManager$SnackbarRecord.duration == -1) {
            access$100 = 1500;
        }
        this.mHandler.removeCallbacksAndMessages((Object)snackbarManager$SnackbarRecord);
        this.mHandler.sendMessageDelayed(Message.obtain(this.mHandler, 0, (Object)snackbarManager$SnackbarRecord), (long)access$100);
    }
    
    private void showNextSnackbarLocked() {
        if (this.mNextSnackbar != null) {
            this.mCurrentSnackbar = this.mNextSnackbar;
            this.mNextSnackbar = null;
            final SnackbarManager$Callback snackbarManager$Callback = (SnackbarManager$Callback)this.mCurrentSnackbar.callback.get();
            if (snackbarManager$Callback == null) {
                this.mCurrentSnackbar = null;
                return;
            }
            snackbarManager$Callback.show();
        }
    }
    
    public void cancelTimeout(final SnackbarManager$Callback snackbarManager$Callback) {
        synchronized (this.mLock) {
            if (this.isCurrentSnackbar(snackbarManager$Callback)) {
                this.mHandler.removeCallbacksAndMessages((Object)this.mCurrentSnackbar);
            }
        }
    }
    
    public void dismiss(final SnackbarManager$Callback snackbarManager$Callback) {
        synchronized (this.mLock) {
            if (this.isCurrentSnackbar(snackbarManager$Callback)) {
                this.cancelSnackbarLocked(this.mCurrentSnackbar);
            }
            if (this.isNextSnackbar(snackbarManager$Callback)) {
                this.cancelSnackbarLocked(this.mNextSnackbar);
            }
        }
    }
    
    public void onDismissed(final SnackbarManager$Callback snackbarManager$Callback) {
        synchronized (this.mLock) {
            if (this.isCurrentSnackbar(snackbarManager$Callback)) {
                this.mCurrentSnackbar = null;
                if (this.mNextSnackbar != null) {
                    this.showNextSnackbarLocked();
                }
            }
        }
    }
    
    public void onShown(final SnackbarManager$Callback snackbarManager$Callback) {
        synchronized (this.mLock) {
            if (this.isCurrentSnackbar(snackbarManager$Callback)) {
                this.scheduleTimeoutLocked(this.mCurrentSnackbar);
            }
        }
    }
    
    public void restoreTimeout(final SnackbarManager$Callback snackbarManager$Callback) {
        synchronized (this.mLock) {
            if (this.isCurrentSnackbar(snackbarManager$Callback)) {
                this.scheduleTimeoutLocked(this.mCurrentSnackbar);
            }
        }
    }
    
    public void show(final int n, final SnackbarManager$Callback snackbarManager$Callback) {
        while (true) {
            while (true) {
                synchronized (this.mLock) {
                    if (this.isCurrentSnackbar(snackbarManager$Callback)) {
                        this.mCurrentSnackbar.duration = n;
                        this.mHandler.removeCallbacksAndMessages((Object)this.mCurrentSnackbar);
                        this.scheduleTimeoutLocked(this.mCurrentSnackbar);
                        return;
                    }
                    if (this.isNextSnackbar(snackbarManager$Callback)) {
                        this.mNextSnackbar.duration = n;
                        if (this.mCurrentSnackbar != null && this.cancelSnackbarLocked(this.mCurrentSnackbar)) {
                            return;
                        }
                        break;
                    }
                }
                final SnackbarManager$Callback snackbarManager$Callback2;
                this.mNextSnackbar = new SnackbarManager$SnackbarRecord(n, snackbarManager$Callback2);
                continue;
            }
        }
        this.mCurrentSnackbar = null;
        this.showNextSnackbarLocked();
    }
    // monitorexit(o)
}
