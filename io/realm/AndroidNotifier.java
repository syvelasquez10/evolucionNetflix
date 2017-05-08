// 
// Decompiled by Procyon v0.5.30
// 

package io.realm;

import io.realm.log.RealmLog;
import android.os.Message;
import io.realm.internal.async.QueryUpdateTask$Result;
import android.os.Looper;
import android.os.Handler$Callback;
import android.os.Handler;
import io.realm.internal.RealmNotifier;

class AndroidNotifier implements RealmNotifier
{
    private Handler handler;
    
    public AndroidNotifier(final HandlerController handlerController) {
        if (isAutoRefreshAvailable()) {
            this.handler = new Handler((Handler$Callback)handlerController);
        }
    }
    
    private static boolean isAutoRefreshAvailable() {
        return Looper.myLooper() != null && !isIntentServiceThread();
    }
    
    private static boolean isIntentServiceThread() {
        final String name = Thread.currentThread().getName();
        return name != null && name.startsWith("IntentService[");
    }
    
    public void close() {
        if (this.handler != null) {
            this.handler.removeCallbacksAndMessages((Object)null);
            this.handler = null;
        }
    }
    
    public void completeAsyncObject(final QueryUpdateTask$Result queryUpdateTask$Result) {
        if (this.handler.getLooper().getThread().isAlive()) {
            this.handler.obtainMessage(63245986, (Object)queryUpdateTask$Result).sendToTarget();
        }
    }
    
    public void completeAsyncResults(final QueryUpdateTask$Result queryUpdateTask$Result) {
        if (this.handler.getLooper().getThread().isAlive()) {
            this.handler.obtainMessage(39088169, (Object)queryUpdateTask$Result).sendToTarget();
        }
    }
    
    public void completeUpdateAsyncQueries(final QueryUpdateTask$Result queryUpdateTask$Result) {
        if (this.handler.getLooper().getThread().isAlive()) {
            this.handler.obtainMessage(24157817, (Object)queryUpdateTask$Result).sendToTarget();
        }
    }
    
    public boolean isValid() {
        return this.handler != null;
    }
    
    public void notifyCommitByLocalThread() {
        if (this.handler != null) {
            final Message obtain = Message.obtain();
            obtain.what = 165580141;
            if (!this.handler.hasMessages(165580141)) {
                this.handler.removeMessages(14930352);
                this.handler.sendMessageAtFrontOfQueue(obtain);
            }
        }
    }
    
    public void notifyCommitByOtherThread() {
        if (this.handler != null) {
            boolean sendEmptyMessage = true;
            if (!this.handler.hasMessages(14930352)) {
                sendEmptyMessage = sendEmptyMessage;
                if (!this.handler.hasMessages(165580141)) {
                    sendEmptyMessage = this.handler.sendEmptyMessage(14930352);
                }
            }
            if (!sendEmptyMessage) {
                RealmLog.warn("Cannot update Looper threads when the Looper has quit. Use realm.setAutoRefresh(false) to prevent this.", new Object[0]);
            }
        }
    }
    
    public void post(final Runnable runnable) {
        if (this.handler.getLooper().getThread().isAlive()) {
            this.handler.post(runnable);
        }
    }
    
    public void throwBackgroundException(final Throwable t) {
        if (this.handler.getLooper().getThread().isAlive()) {
            this.handler.obtainMessage(102334155, (Object)new Error(t)).sendToTarget();
        }
    }
}
