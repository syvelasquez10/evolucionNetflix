// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

final class MediaRouter$GlobalMediaRouter$ProviderCallback extends MediaRouteProvider$Callback
{
    final /* synthetic */ MediaRouter$GlobalMediaRouter this$0;
    
    MediaRouter$GlobalMediaRouter$ProviderCallback(final MediaRouter$GlobalMediaRouter this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onDescriptorChanged(final MediaRouteProvider mediaRouteProvider, final MediaRouteProviderDescriptor mediaRouteProviderDescriptor) {
        this.this$0.updateProviderDescriptor(mediaRouteProvider, mediaRouteProviderDescriptor);
    }
}
