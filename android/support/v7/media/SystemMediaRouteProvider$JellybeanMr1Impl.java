// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import android.view.Display;
import android.content.Context;

class SystemMediaRouteProvider$JellybeanMr1Impl extends SystemMediaRouteProvider$JellybeanImpl implements MediaRouterJellybeanMr1$Callback
{
    private MediaRouterJellybeanMr1$ActiveScanWorkaround mActiveScanWorkaround;
    private MediaRouterJellybeanMr1$IsConnectingWorkaround mIsConnectingWorkaround;
    
    public SystemMediaRouteProvider$JellybeanMr1Impl(final Context context, final SystemMediaRouteProvider$SyncCallback systemMediaRouteProvider$SyncCallback) {
        super(context, systemMediaRouteProvider$SyncCallback);
    }
    
    @Override
    protected Object createCallbackObj() {
        return MediaRouterJellybeanMr1.createCallback(this);
    }
    
    protected boolean isConnecting(final SystemMediaRouteProvider$JellybeanImpl$SystemRouteRecord systemMediaRouteProvider$JellybeanImpl$SystemRouteRecord) {
        if (this.mIsConnectingWorkaround == null) {
            this.mIsConnectingWorkaround = new MediaRouterJellybeanMr1$IsConnectingWorkaround();
        }
        return this.mIsConnectingWorkaround.isConnecting(systemMediaRouteProvider$JellybeanImpl$SystemRouteRecord.mRouteObj);
    }
    
    @Override
    protected void onBuildSystemRouteDescriptor(final SystemMediaRouteProvider$JellybeanImpl$SystemRouteRecord systemMediaRouteProvider$JellybeanImpl$SystemRouteRecord, final MediaRouteDescriptor$Builder mediaRouteDescriptor$Builder) {
        super.onBuildSystemRouteDescriptor(systemMediaRouteProvider$JellybeanImpl$SystemRouteRecord, mediaRouteDescriptor$Builder);
        if (!MediaRouterJellybeanMr1$RouteInfo.isEnabled(systemMediaRouteProvider$JellybeanImpl$SystemRouteRecord.mRouteObj)) {
            mediaRouteDescriptor$Builder.setEnabled(false);
        }
        if (this.isConnecting(systemMediaRouteProvider$JellybeanImpl$SystemRouteRecord)) {
            mediaRouteDescriptor$Builder.setConnecting(true);
        }
        final Display presentationDisplay = MediaRouterJellybeanMr1$RouteInfo.getPresentationDisplay(systemMediaRouteProvider$JellybeanImpl$SystemRouteRecord.mRouteObj);
        if (presentationDisplay != null) {
            mediaRouteDescriptor$Builder.setPresentationDisplayId(presentationDisplay.getDisplayId());
        }
    }
    
    @Override
    public void onRoutePresentationDisplayChanged(final Object o) {
        final int systemRouteRecord = this.findSystemRouteRecord(o);
        if (systemRouteRecord >= 0) {
            final SystemMediaRouteProvider$JellybeanImpl$SystemRouteRecord systemMediaRouteProvider$JellybeanImpl$SystemRouteRecord = this.mSystemRouteRecords.get(systemRouteRecord);
            final Display presentationDisplay = MediaRouterJellybeanMr1$RouteInfo.getPresentationDisplay(o);
            int displayId;
            if (presentationDisplay != null) {
                displayId = presentationDisplay.getDisplayId();
            }
            else {
                displayId = -1;
            }
            if (displayId != systemMediaRouteProvider$JellybeanImpl$SystemRouteRecord.mRouteDescriptor.getPresentationDisplayId()) {
                systemMediaRouteProvider$JellybeanImpl$SystemRouteRecord.mRouteDescriptor = new MediaRouteDescriptor$Builder(systemMediaRouteProvider$JellybeanImpl$SystemRouteRecord.mRouteDescriptor).setPresentationDisplayId(displayId).build();
                this.publishRoutes();
            }
        }
    }
    
    @Override
    protected void updateCallback() {
        super.updateCallback();
        if (this.mActiveScanWorkaround == null) {
            this.mActiveScanWorkaround = new MediaRouterJellybeanMr1$ActiveScanWorkaround(this.getContext(), this.getHandler());
        }
        final MediaRouterJellybeanMr1$ActiveScanWorkaround mActiveScanWorkaround = this.mActiveScanWorkaround;
        int mRouteTypes;
        if (this.mActiveScan) {
            mRouteTypes = this.mRouteTypes;
        }
        else {
            mRouteTypes = 0;
        }
        mActiveScanWorkaround.setActiveScanRouteTypes(mRouteTypes);
    }
}
