// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import android.content.Intent;
import android.os.Message;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.content.ComponentName;
import android.support.annotation.NonNull;
import android.content.Context;

public abstract class MediaRouteProvider
{
    private static final int MSG_DELIVER_DESCRIPTOR_CHANGED = 1;
    private static final int MSG_DELIVER_DISCOVERY_REQUEST_CHANGED = 2;
    private Callback mCallback;
    private final Context mContext;
    private MediaRouteProviderDescriptor mDescriptor;
    private MediaRouteDiscoveryRequest mDiscoveryRequest;
    private final ProviderHandler mHandler;
    private final ProviderMetadata mMetadata;
    private boolean mPendingDescriptorChange;
    private boolean mPendingDiscoveryRequestChange;
    
    public MediaRouteProvider(@NonNull final Context context) {
        this(context, null);
    }
    
    MediaRouteProvider(final Context mContext, final ProviderMetadata mMetadata) {
        this.mHandler = new ProviderHandler();
        if (mContext == null) {
            throw new IllegalArgumentException("context must not be null");
        }
        this.mContext = mContext;
        if (mMetadata == null) {
            this.mMetadata = new ProviderMetadata(new ComponentName(mContext, (Class)this.getClass()));
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
    
    public final ProviderMetadata getMetadata() {
        return this.mMetadata;
    }
    
    @Nullable
    public RouteController onCreateRouteController(final String s) {
        return null;
    }
    
    public void onDiscoveryRequestChanged(@Nullable final MediaRouteDiscoveryRequest mediaRouteDiscoveryRequest) {
    }
    
    public final void setCallback(@Nullable final Callback mCallback) {
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
    
    public abstract static class Callback
    {
        public void onDescriptorChanged(@NonNull final MediaRouteProvider mediaRouteProvider, @Nullable final MediaRouteProviderDescriptor mediaRouteProviderDescriptor) {
        }
    }
    
    private final class ProviderHandler extends Handler
    {
        public void handleMessage(final Message message) {
            switch (message.what) {
                default: {}
                case 1: {
                    MediaRouteProvider.this.deliverDescriptorChanged();
                }
                case 2: {
                    MediaRouteProvider.this.deliverDiscoveryRequestChanged();
                }
            }
        }
    }
    
    public static final class ProviderMetadata
    {
        private final ComponentName mComponentName;
        
        ProviderMetadata(final ComponentName mComponentName) {
            if (mComponentName == null) {
                throw new IllegalArgumentException("componentName must not be null");
            }
            this.mComponentName = mComponentName;
        }
        
        public ComponentName getComponentName() {
            return this.mComponentName;
        }
        
        public String getPackageName() {
            return this.mComponentName.getPackageName();
        }
        
        @Override
        public String toString() {
            return "ProviderMetadata{ componentName=" + this.mComponentName.flattenToShortString() + " }";
        }
    }
    
    public abstract static class RouteController
    {
        public boolean onControlRequest(final Intent intent, @Nullable final MediaRouter.ControlRequestCallback controlRequestCallback) {
            return false;
        }
        
        public void onRelease() {
        }
        
        public void onSelect() {
        }
        
        public void onSetVolume(final int n) {
        }
        
        public void onUnselect() {
        }
        
        public void onUpdateVolume(final int n) {
        }
    }
}
