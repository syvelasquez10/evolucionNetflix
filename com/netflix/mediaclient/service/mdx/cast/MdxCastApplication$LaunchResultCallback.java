// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx.cast;

import com.google.android.gms.common.api.Status;
import java.util.concurrent.TimeUnit;
import com.google.android.gms.common.ConnectionResult;
import android.os.Bundle;
import com.google.android.gms.cast.ApplicationMetadata;
import com.netflix.mediaclient.util.StringUtils;
import com.google.android.gms.cast.Cast$CastOptions$Builder;
import com.google.android.gms.common.api.GoogleApiClient$Builder;
import com.google.android.gms.cast.Cast$CastOptions;
import com.google.android.gms.cast.CastDevice;
import android.content.Context;
import java.util.concurrent.atomic.AtomicBoolean;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.cast.Cast$Listener;
import com.google.android.gms.common.api.Result;
import java.io.IOException;
import com.google.android.gms.cast.Cast;
import com.netflix.mediaclient.Log;
import com.google.android.gms.cast.Cast$MessageReceivedCallback;
import com.google.android.gms.cast.Cast$ApplicationConnectionResult;
import com.google.android.gms.common.api.ResultCallback;

final class MdxCastApplication$LaunchResultCallback implements ResultCallback<Cast$ApplicationConnectionResult>
{
    final Cast$MessageReceivedCallback mMessageReceivedCallback;
    final /* synthetic */ MdxCastApplication this$0;
    
    MdxCastApplication$LaunchResultCallback(final MdxCastApplication this$0, final Cast$MessageReceivedCallback mMessageReceivedCallback) {
        this.this$0 = this$0;
        this.mMessageReceivedCallback = mMessageReceivedCallback;
    }
    
    @Override
    public void onResult(final Cast$ApplicationConnectionResult cast$ApplicationConnectionResult) {
        if (cast$ApplicationConnectionResult.getStatus().isSuccess()) {
            Log.d(MdxCastApplication.TAG, "launchApplication(), success");
            try {
                Cast.CastApi.setMessageReceivedCallbacks(this.this$0.mApiClient, "urn:x-cast:mdx-netflix-com:service:target:2", this.mMessageReceivedCallback);
                this.this$0.mForceLaunch.set(false);
                this.this$0.mCallback.onLaunched();
                return;
            }
            catch (IllegalStateException ex) {
                this.this$0.mCallback.onFailToLaunch("IllegalStateException: " + ex.getMessage());
                return;
            }
            catch (IOException ex2) {
                this.this$0.mCallback.onFailToLaunch("IOException: " + ex2.getMessage());
                return;
            }
            catch (Exception ex3) {
                this.this$0.mCallback.onFailToLaunch("Exception: " + ex3.getMessage());
                return;
            }
        }
        if (cast$ApplicationConnectionResult.getStatus().getStatusCode() == 2002) {
            Log.d(MdxCastApplication.TAG, "launchApplication(), cancelled & ignored");
            return;
        }
        if (cast$ApplicationConnectionResult.getStatus().getStatusCode() == 15) {
            Log.d(MdxCastApplication.TAG, "launchApplication(), timeout, wait - JUST A WORKAROUND");
            this.this$0.mCallback.onFailToLaunch("StatusCodes: TIMEOUT");
            return;
        }
        Log.d(MdxCastApplication.TAG, "launchApplication(), failure, result: " + cast$ApplicationConnectionResult.getStatus().getStatusCode());
        this.this$0.mCallback.onFailToLaunch("getStatusCode: " + cast$ApplicationConnectionResult.getStatus().getStatusCode());
    }
}
