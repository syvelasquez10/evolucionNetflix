// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

class MediaRouter$GlobalMediaRouter$MediaSessionRecord$1$2 implements Runnable
{
    final /* synthetic */ MediaRouter$GlobalMediaRouter$MediaSessionRecord$1 this$2;
    final /* synthetic */ int val$direction;
    
    MediaRouter$GlobalMediaRouter$MediaSessionRecord$1$2(final MediaRouter$GlobalMediaRouter$MediaSessionRecord$1 this$2, final int val$direction) {
        this.this$2 = this$2;
        this.val$direction = val$direction;
    }
    
    @Override
    public void run() {
        if (this.this$2.this$1.this$0.mSelectedRoute != null) {
            this.this$2.this$1.this$0.mSelectedRoute.requestUpdateVolume(this.val$direction);
        }
    }
}
