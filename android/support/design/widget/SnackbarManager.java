// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.os.Message;
import android.os.Handler$Callback;
import android.os.Looper;
import android.os.Handler;

class SnackbarManager
{
    private static final int LONG_DURATION_MS = 2750;
    static final int MSG_TIMEOUT = 0;
    private static final int SHORT_DURATION_MS = 1500;
    private static SnackbarManager sSnackbarManager;
    private SnackbarManager$SnackbarRecord mCurrentSnackbar;
    private final Handler mHandler;
    private final Object mLock;
    private SnackbarManager$SnackbarRecord mNextSnackbar;
    
    private SnackbarManager() {
        this.mLock = new Object();
        this.mHandler = new Handler(Looper.getMainLooper(), (Handler$Callback)new SnackbarManager$1(this));
    }
    
    private boolean cancelSnackbarLocked(final SnackbarManager$SnackbarRecord snackbarManager$SnackbarRecord, final int n) {
        final SnackbarManager$Callback snackbarManager$Callback = snackbarManager$SnackbarRecord.callback.get();
        if (snackbarManager$Callback != null) {
            this.mHandler.removeCallbacksAndMessages((Object)snackbarManager$SnackbarRecord);
            snackbarManager$Callback.dismiss(n);
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
    
    private boolean isCurrentSnackbarLocked(final SnackbarManager$Callback snackbarManager$Callback) {
        return this.mCurrentSnackbar != null && this.mCurrentSnackbar.isSnackbar(snackbarManager$Callback);
    }
    
    private boolean isNextSnackbarLocked(final SnackbarManager$Callback snackbarManager$Callback) {
        return this.mNextSnackbar != null && this.mNextSnackbar.isSnackbar(snackbarManager$Callback);
    }
    
    private void scheduleTimeoutLocked(final SnackbarManager$SnackbarRecord snackbarManager$SnackbarRecord) {
        if (snackbarManager$SnackbarRecord.duration == -2) {
            return;
        }
        int duration = 2750;
        if (snackbarManager$SnackbarRecord.duration > 0) {
            duration = snackbarManager$SnackbarRecord.duration;
        }
        else if (snackbarManager$SnackbarRecord.duration == -1) {
            duration = 1500;
        }
        this.mHandler.removeCallbacksAndMessages((Object)snackbarManager$SnackbarRecord);
        this.mHandler.sendMessageDelayed(Message.obtain(this.mHandler, 0, (Object)snackbarManager$SnackbarRecord), (long)duration);
    }
    
    private void showNextSnackbarLocked() {
        if (this.mNextSnackbar != null) {
            this.mCurrentSnackbar = this.mNextSnackbar;
            this.mNextSnackbar = null;
            final SnackbarManager$Callback snackbarManager$Callback = this.mCurrentSnackbar.callback.get();
            if (snackbarManager$Callback == null) {
                this.mCurrentSnackbar = null;
                return;
            }
            snackbarManager$Callback.show();
        }
    }
    
    public void cancelTimeout(final SnackbarManager$Callback snackbarManager$Callback) {
        synchronized (this.mLock) {
            if (this.isCurrentSnackbarLocked(snackbarManager$Callback)) {
                this.mHandler.removeCallbacksAndMessages((Object)this.mCurrentSnackbar);
            }
        }
    }
    
    public void dismiss(final SnackbarManager$Callback snackbarManager$Callback, final int n) {
        synchronized (this.mLock) {
            if (this.isCurrentSnackbarLocked(snackbarManager$Callback)) {
                this.cancelSnackbarLocked(this.mCurrentSnackbar, n);
            }
            else if (this.isNextSnackbarLocked(snackbarManager$Callback)) {
                this.cancelSnackbarLocked(this.mNextSnackbar, n);
            }
        }
    }
    
    void handleTimeout(final SnackbarManager$SnackbarRecord snackbarManager$SnackbarRecord) {
        synchronized (this.mLock) {
            if (this.mCurrentSnackbar == snackbarManager$SnackbarRecord || this.mNextSnackbar == snackbarManager$SnackbarRecord) {
                this.cancelSnackbarLocked(snackbarManager$SnackbarRecord, 2);
            }
        }
    }
    
    public boolean isCurrent(final SnackbarManager$Callback snackbarManager$Callback) {
        synchronized (this.mLock) {
            return this.isCurrentSnackbarLocked(snackbarManager$Callback);
        }
    }
    
    public boolean isCurrentOrNext(final SnackbarManager$Callback snackbarManager$Callback) {
        while (true) {
            synchronized (this.mLock) {
                if (this.isCurrentSnackbarLocked(snackbarManager$Callback)) {
                    return true;
                }
                if (this.isNextSnackbarLocked(snackbarManager$Callback)) {
                    return true;
                }
                return false;
            }
            return true;
            b = false;
            return b;
        }
    }
    
    public void onDismissed(final SnackbarManager$Callback snackbarManager$Callback) {
        synchronized (this.mLock) {
            if (this.isCurrentSnackbarLocked(snackbarManager$Callback)) {
                this.mCurrentSnackbar = null;
                if (this.mNextSnackbar != null) {
                    this.showNextSnackbarLocked();
                }
            }
        }
    }
    
    public void onShown(final SnackbarManager$Callback snackbarManager$Callback) {
        synchronized (this.mLock) {
            if (this.isCurrentSnackbarLocked(snackbarManager$Callback)) {
                this.scheduleTimeoutLocked(this.mCurrentSnackbar);
            }
        }
    }
    
    public void restoreTimeout(final SnackbarManager$Callback snackbarManager$Callback) {
        synchronized (this.mLock) {
            if (this.isCurrentSnackbarLocked(snackbarManager$Callback)) {
                this.scheduleTimeoutLocked(this.mCurrentSnackbar);
            }
        }
    }
    
    public void show(final int n, final SnackbarManager$Callback snackbarManager$Callback) {
        while (true) {
            while (true) {
                synchronized (this.mLock) {
                    if (this.isCurrentSnackbarLocked(snackbarManager$Callback)) {
                        this.mCurrentSnackbar.duration = n;
                        this.mHandler.removeCallbacksAndMessages((Object)this.mCurrentSnackbar);
                        this.scheduleTimeoutLocked(this.mCurrentSnackbar);
                        return;
                    }
                    if (this.isNextSnackbarLocked(snackbarManager$Callback)) {
                        this.mNextSnackbar.duration = n;
                        if (this.mCurrentSnackbar != null && this.cancelSnackbarLocked(this.mCurrentSnackbar, 4)) {
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
