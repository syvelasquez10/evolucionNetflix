// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.widgetry.buffet;

import android.os.Message;
import android.os.Handler$Callback;
import android.os.Looper;
import android.os.Handler;

class BuffetManager
{
    private static final int LONG_DURATION_MS = 2750;
    static final int MSG_TIMEOUT = 0;
    private static final int SHORT_DURATION_MS = 1500;
    private static BuffetManager sBuffetManager;
    private BuffetManager$SnackbarRecord mCurrentSnackbar;
    private final Handler mHandler;
    private final Object mLock;
    private BuffetManager$SnackbarRecord mNextSnackbar;
    
    private BuffetManager() {
        this.mLock = new Object();
        this.mHandler = new Handler(Looper.getMainLooper(), (Handler$Callback)new BuffetManager$1(this));
    }
    
    private boolean cancelSnackbarLocked(final BuffetManager$SnackbarRecord buffetManager$SnackbarRecord, final int n) {
        final BuffetManager$Callback buffetManager$Callback = buffetManager$SnackbarRecord.callback.get();
        if (buffetManager$Callback != null) {
            this.mHandler.removeCallbacksAndMessages((Object)buffetManager$SnackbarRecord);
            buffetManager$Callback.dismiss(n);
            return true;
        }
        return false;
    }
    
    static BuffetManager getInstance() {
        if (BuffetManager.sBuffetManager == null) {
            BuffetManager.sBuffetManager = new BuffetManager();
        }
        return BuffetManager.sBuffetManager;
    }
    
    private boolean isCurrentSnackbarLocked(final BuffetManager$Callback buffetManager$Callback) {
        return this.mCurrentSnackbar != null && this.mCurrentSnackbar.isSnackbar(buffetManager$Callback);
    }
    
    private boolean isNextSnackbarLocked(final BuffetManager$Callback buffetManager$Callback) {
        return this.mNextSnackbar != null && this.mNextSnackbar.isSnackbar(buffetManager$Callback);
    }
    
    private void scheduleTimeoutLocked(final BuffetManager$SnackbarRecord buffetManager$SnackbarRecord) {
        if (buffetManager$SnackbarRecord.duration == -2) {
            return;
        }
        int duration = 2750;
        if (buffetManager$SnackbarRecord.duration > 0) {
            duration = buffetManager$SnackbarRecord.duration;
        }
        else if (buffetManager$SnackbarRecord.duration == -1) {
            duration = 1500;
        }
        this.mHandler.removeCallbacksAndMessages((Object)buffetManager$SnackbarRecord);
        this.mHandler.sendMessageDelayed(Message.obtain(this.mHandler, 0, (Object)buffetManager$SnackbarRecord), (long)duration);
    }
    
    private void showNextSnackbarLocked(final boolean b) {
        if (this.mNextSnackbar != null) {
            this.mCurrentSnackbar = this.mNextSnackbar;
            this.mNextSnackbar = null;
            final BuffetManager$Callback buffetManager$Callback = this.mCurrentSnackbar.callback.get();
            if (buffetManager$Callback == null) {
                this.mCurrentSnackbar = null;
                return;
            }
            buffetManager$Callback.show(b);
        }
    }
    
    public void cancelTimeout(final BuffetManager$Callback buffetManager$Callback) {
        synchronized (this.mLock) {
            if (this.isCurrentSnackbarLocked(buffetManager$Callback)) {
                this.mHandler.removeCallbacksAndMessages((Object)this.mCurrentSnackbar);
            }
        }
    }
    
    public void dismiss(final BuffetManager$Callback buffetManager$Callback, final int n) {
        synchronized (this.mLock) {
            if (this.isCurrentSnackbarLocked(buffetManager$Callback)) {
                this.cancelSnackbarLocked(this.mCurrentSnackbar, n);
            }
            else if (this.isNextSnackbarLocked(buffetManager$Callback)) {
                this.cancelSnackbarLocked(this.mNextSnackbar, n);
            }
        }
    }
    
    void handleTimeout(final BuffetManager$SnackbarRecord buffetManager$SnackbarRecord) {
        synchronized (this.mLock) {
            if (this.mCurrentSnackbar == buffetManager$SnackbarRecord || this.mNextSnackbar == buffetManager$SnackbarRecord) {
                this.cancelSnackbarLocked(buffetManager$SnackbarRecord, 2);
            }
        }
    }
    
    public boolean isCurrent(final BuffetManager$Callback buffetManager$Callback) {
        synchronized (this.mLock) {
            return this.isCurrentSnackbarLocked(buffetManager$Callback);
        }
    }
    
    public boolean isCurrentOrNext(final BuffetManager$Callback buffetManager$Callback) {
        while (true) {
            synchronized (this.mLock) {
                if (this.isCurrentSnackbarLocked(buffetManager$Callback)) {
                    return true;
                }
                if (this.isNextSnackbarLocked(buffetManager$Callback)) {
                    return true;
                }
                return false;
            }
            return true;
            b = false;
            return b;
        }
    }
    
    public void onDismissed(final BuffetManager$Callback buffetManager$Callback) {
        synchronized (this.mLock) {
            if (this.isCurrentSnackbarLocked(buffetManager$Callback)) {
                this.mCurrentSnackbar = null;
                if (this.mNextSnackbar != null) {
                    this.showNextSnackbarLocked(this.mNextSnackbar.animated);
                }
            }
        }
    }
    
    public void onShown(final BuffetManager$Callback buffetManager$Callback) {
        synchronized (this.mLock) {
            if (this.isCurrentSnackbarLocked(buffetManager$Callback)) {
                this.scheduleTimeoutLocked(this.mCurrentSnackbar);
            }
        }
    }
    
    public void restoreTimeout(final BuffetManager$Callback buffetManager$Callback) {
        synchronized (this.mLock) {
            if (this.isCurrentSnackbarLocked(buffetManager$Callback)) {
                this.scheduleTimeoutLocked(this.mCurrentSnackbar);
            }
        }
    }
    
    public void show(final int n, final BuffetManager$Callback buffetManager$Callback, final boolean b) {
        while (true) {
            while (true) {
                synchronized (this.mLock) {
                    if (this.isCurrentSnackbarLocked(buffetManager$Callback)) {
                        this.mCurrentSnackbar.duration = n;
                        this.mHandler.removeCallbacksAndMessages((Object)this.mCurrentSnackbar);
                        this.scheduleTimeoutLocked(this.mCurrentSnackbar);
                        return;
                    }
                    if (this.isNextSnackbarLocked(buffetManager$Callback)) {
                        this.mNextSnackbar.duration = n;
                        if (this.mCurrentSnackbar != null && this.cancelSnackbarLocked(this.mCurrentSnackbar, 4)) {
                            return;
                        }
                        break;
                    }
                }
                final BuffetManager$Callback buffetManager$Callback2;
                this.mNextSnackbar = new BuffetManager$SnackbarRecord(n, b, buffetManager$Callback2);
                continue;
            }
        }
        this.mCurrentSnackbar = null;
        this.showNextSnackbarLocked(b);
    }
    // monitorexit(o)
}
