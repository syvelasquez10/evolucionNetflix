// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

class MediaRouter$GlobalMediaRouter$MediaSessionRecord$1$1 implements Runnable
{
    final /* synthetic */ MediaRouter$GlobalMediaRouter$MediaSessionRecord$1 this$2;
    final /* synthetic */ int val$volume;
    
    MediaRouter$GlobalMediaRouter$MediaSessionRecord$1$1(final MediaRouter$GlobalMediaRouter$MediaSessionRecord$1 this$2, final int val$volume) {
        this.this$2 = this$2;
        this.val$volume = val$volume;
    }
    
    @Override
    public void run() {
        if (this.this$2.this$1.this$0.mSelectedRoute != null) {
            this.this$2.this$1.this$0.mSelectedRoute.requestSetVolume(this.val$volume);
        }
    }
}
