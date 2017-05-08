// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import android.os.Handler;
import android.content.ComponentName;
import android.content.Context;

public abstract class MediaRouteProvider
{
    private MediaRouteProvider$Callback mCallback;
    private final Context mContext;
    private MediaRouteProviderDescriptor mDescriptor;
    private MediaRouteDiscoveryRequest mDiscoveryRequest;
    private final MediaRouteProvider$ProviderHandler mHandler;
    private final MediaRouteProvider$ProviderMetadata mMetadata;
    private boolean mPendingDescriptorChange;
    private boolean mPendingDiscoveryRequestChange;
    
    MediaRouteProvider(final Context mContext, final MediaRouteProvider$ProviderMetadata mMetadata) {
        this.mHandler = new MediaRouteProvider$ProviderHandler(this);
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
    
    void deliverDescriptorChanged() {
        this.mPendingDescriptorChange = false;
        if (this.mCallback != null) {
            this.mCallback.onDescriptorChanged(this, this.mDescriptor);
        }
    }
    
    void deliverDiscoveryRequestChanged() {
        this.mPendingDiscoveryRequestChange = false;
        this.onDiscoveryRequestChanged(this.mDiscoveryRequest);
    }
    
    public final Context getContext() {
        return this.mContext;
    }
    
    public final MediaRouteProviderDescriptor getDescriptor() {
        return this.mDescriptor;
    }
    
    public final MediaRouteDiscoveryRequest getDiscoveryRequest() {
        return this.mDiscoveryRequest;
    }
    
    public final Handler getHandler() {
        return this.mHandler;
    }
    
    public final MediaRouteProvider$ProviderMetadata getMetadata() {
        return this.mMetadata;
    }
    
    public MediaRouteProvider$RouteController onCreateRouteController(final String s) {
        if (s == null) {
            throw new IllegalArgumentException("routeId cannot be null");
        }
        return null;
    }
    
    public MediaRouteProvider$RouteController onCreateRouteController(final String s, final String s2) {
        if (s == null) {
            throw new IllegalArgumentException("routeId cannot be null");
        }
        if (s2 == null) {
            throw new IllegalArgumentException("routeGroupId cannot be null");
        }
        return this.onCreateRouteController(s);
    }
    
    public void onDiscoveryRequestChanged(final MediaRouteDiscoveryRequest mediaRouteDiscoveryRequest) {
    }
    
    public final void setCallback(final MediaRouteProvider$Callback mCallback) {
        MediaRouter.checkCallingThread();
        this.mCallback = mCallback;
    }
    
    public final void setDescriptor(final MediaRouteProviderDescriptor mDescriptor) {
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
