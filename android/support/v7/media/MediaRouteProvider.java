// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import android.os.Handler;
import android.support.annotation.Nullable;
import android.content.ComponentName;
import android.support.annotation.NonNull;
import android.content.Context;

public abstract class MediaRouteProvider
{
    private static final int MSG_DELIVER_DESCRIPTOR_CHANGED = 1;
    private static final int MSG_DELIVER_DISCOVERY_REQUEST_CHANGED = 2;
    private MediaRouteProvider$Callback mCallback;
    private final Context mContext;
    private MediaRouteProviderDescriptor mDescriptor;
    private MediaRouteDiscoveryRequest mDiscoveryRequest;
    private final MediaRouteProvider$ProviderHandler mHandler;
    private final MediaRouteProvider$ProviderMetadata mMetadata;
    private boolean mPendingDescriptorChange;
    private boolean mPendingDiscoveryRequestChange;
    
    public MediaRouteProvider(@NonNull final Context context) {
        this(context, null);
    }
    
    MediaRouteProvider(final Context mContext, final MediaRouteProvider$ProviderMetadata mMetadata) {
        this.mHandler = new MediaRouteProvider$ProviderHandler(this, null);
        if (mContext == null) {
            throw new IllegalArgumentException("context must not be null");
        }
        this.mContext = mContext;
        if (mMetadata == null) {
            this.mMetadata = new MediaRouteProvider$ProviderMetadata(new ComponentName(mContext, (Class)this.getClass()));
            return;
        }
        this.mMetadata = mMetadata;
    }
    
    private void deliverDescriptorChanged() {
        this.mPendingDescriptorChange = false;
        if (this.mCallback != null) {
            this.mCallback.onDescriptorChanged(this, this.mDescriptor);
        }
    }
    
    private void deliverDiscoveryRequestChanged() {
        this.mPendingDiscoveryRequestChange = false;
        this.onDiscoveryRequestChanged(this.mDiscoveryRequest);
    }
    
    public final Context getContext() {
        return this.mContext;
    }
    
    @Nullable
    public final MediaRouteProviderDescriptor getDescriptor() {
        return this.mDescriptor;
    }
    
    @Nullable
    public final MediaRouteDiscoveryRequest getDiscoveryRequest() {
        return this.mDiscoveryRequest;
    }
    
    public final Handler getHandler() {
        return this.mHandler;
    }
    
    public final MediaRouteProvider$ProviderMetadata getMetadata() {
        return this.mMetadata;
    }
    
    @Nullable
    public MediaRouteProvider$RouteController onCreateRouteController(final String s) {
        return null;
    }
    
    public void onDiscoveryRequestChanged(@Nullable final MediaRouteDiscoveryRequest mediaRouteDiscoveryRequest) {
    }
    
    public final void setCallback(@Nullable final MediaRouteProvider$Callback mCallback) {
        MediaRouter.checkCallingThread();
        this.mCallback = mCallback;
    }
    
    public final void setDescriptor(@Nullable final MediaRouteProviderDescriptor mDescriptor) {
        MediaRouter.checkCallingThread();
        if (this.mDescriptor != mDescriptor) {
            this.mDescriptor = mDescriptor;
            if (!this.mPendingDescriptorChange) {
                this.mPendingDescriptorChange = true;
                this.mHandler.sendEmptyMessage(1);
            }
        }
    }
    
    public final void setDiscoveryRequest(final MediaRouteDiscoveryRequest mDiscoveryRequest) {
        MediaRouter.checkCallingThread();
        if (this.mDiscoveryRequest != mDiscoveryRequest && (this.mDiscoveryRequest == null || !this.mDiscoveryRequest.equals(mDiscoveryRequest))) {
            this.mDiscoveryRequest = mDiscoveryRequest;
            if (!this.mPendingDiscoveryRequestChange) {
                this.mPendingDiscoveryRequestChange = true;
                this.mHandler.sendEmptyMessage(2);
            }
        }
    }
}
