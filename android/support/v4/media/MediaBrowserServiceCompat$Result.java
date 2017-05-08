// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media;

public class MediaBrowserServiceCompat$Result<T>
{
    private Object mDebug;
    private boolean mDetachCalled;
    private int mFlags;
    private boolean mSendResultCalled;
    
    MediaBrowserServiceCompat$Result(final Object mDebug) {
        this.mDebug = mDebug;
    }
    
    public void detach() {
        if (this.mDetachCalled) {
            throw new IllegalStateException("detach() called when detach() had already been called for: " + this.mDebug);
        }
        if (this.mSendResultCalled) {
            throw new IllegalStateException("detach() called when sendResult() had already been called for: " + this.mDebug);
        }
        this.mDetachCalled = true;
    }
    
    boolean isDone() {
        return this.mDetachCalled || this.mSendResultCalled;
    }
    
    void onResultSent(final T t, final int n) {
    }
    
    public void sendResult(final T t) {
        if (this.mSendResultCalled) {
            throw new IllegalStateException("sendResult() called twice for: " + this.mDebug);
        }
        this.mSendResultCalled = true;
        this.onResultSent(t, this.mFlags);
    }
    
    void setFlags(final int mFlags) {
        this.mFlags = mFlags;
    }
}
