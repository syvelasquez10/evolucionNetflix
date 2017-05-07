// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx.cast;

import com.google.android.gms.common.api.Result;
import java.io.IOException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.ConnectionResult;
import android.os.Bundle;
import com.google.android.gms.cast.ApplicationMetadata;
import com.netflix.mediaclient.Log;
import com.google.android.gms.common.api.ResultCallback;
import com.netflix.mediaclient.util.StringUtils;
import com.google.android.gms.cast.CastDevice;
import android.content.Context;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.cast.Cast;

public class MdxCastApplication extends Listener implements OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks, MessageReceivedCallback
{
    private static final String MESSAGE_NAMESPACE = "urn:x-cast:mdx-netflix-com:service:target:2";
    private static final String TAG;
    private GoogleApiClient mApiClient;
    private final String mApplicationId;
    private MdxCastApplicaCallback mCallback;
    private boolean mForceLaunch;
    
    static {
        TAG = MdxCastApplication.class.getSimpleName();
    }
    
    MdxCastApplication(final Context context, final String mApplicationId, final CastDevice castDevice, final MdxCastApplicaCallback mCallback, final boolean mForceLaunch) {
        this.mApplicationId = mApplicationId;
        this.mCallback = mCallback;
        this.mForceLaunch = mForceLaunch;
        (this.mApiClient = new GoogleApiClient.Builder(context).addApi(Cast.API, Cast.CastOptions.builder(castDevice, this).build()).addConnectionCallbacks(this).addOnConnectionFailedListener(this).build()).connect();
    }
    
    private boolean isNetflixRunning() {
        final String applicationStatus = Cast.CastApi.getApplicationStatus(this.mApiClient);
        return StringUtils.isEmpty(applicationStatus) || applicationStatus.indexOf("Netflix") != -1 || applicationStatus.equalsIgnoreCase("null");
    }
    
    private boolean isOtherAppRunning() {
        final String applicationStatus = Cast.CastApi.getApplicationStatus(this.mApiClient);
        return StringUtils.isNotEmpty(applicationStatus) && applicationStatus.indexOf("Chromecast Home Screen") == -1;
    }
    
    private void joinApp() {
        Cast.CastApi.joinApplication(this.mApiClient, this.mApplicationId).setResultCallback(new LaunchResultCallback(this));
    }
    
    private void launchApp() {
        Cast.CastApi.launchApplication(this.mApiClient, this.mApplicationId).setResultCallback(new LaunchResultCallback(this));
    }
    
    private void logAppStatus() {
        try {
            final ApplicationMetadata applicationMetadata = Cast.CastApi.getApplicationMetadata(this.mApiClient);
            Log.d(MdxCastApplication.TAG, "getApplicationMetadata: " + applicationMetadata);
            if (applicationMetadata != null) {
                Log.d(MdxCastApplication.TAG, "getApplicationId :" + applicationMetadata.getApplicationId());
                Log.d(MdxCastApplication.TAG, "getName: " + applicationMetadata.getName());
                Log.d(MdxCastApplication.TAG, "getSenderAppIdentifier: " + applicationMetadata.getSenderAppIdentifier());
            }
            Log.d(MdxCastApplication.TAG, "getApplicationStatus: " + Cast.CastApi.getApplicationStatus(this.mApiClient));
        }
        catch (IllegalStateException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void onApplicationDisconnected(final int n) {
        Log.d(MdxCastApplication.TAG, "Cast.Listener disconnected with statusCode" + n);
        this.mCallback.onApplicationStopped();
        this.logAppStatus();
    }
    
    @Override
    public void onApplicationStatusChanged() {
        Log.d(MdxCastApplication.TAG, "Cast.Listener onApplicationStatusChanged");
        this.logAppStatus();
    }
    
    @Override
    public void onConnected(final Bundle bundle) {
        Log.d(MdxCastApplication.TAG, "GoogleApiClient connect(), success arg:" + bundle);
        try {
            if (this.mForceLaunch) {
                Log.d(MdxCastApplication.TAG, "forced, GoogleApiClient launchApp()");
                this.launchApp();
                return;
            }
            if (!this.isOtherAppRunning()) {
                Log.d(MdxCastApplication.TAG, "not forced, no app is runnning");
                this.joinApp();
                return;
            }
        }
        catch (IllegalStateException ex) {
            ex.printStackTrace();
            return;
        }
        if (this.isNetflixRunning()) {
            Log.d(MdxCastApplication.TAG, "GoogleApiClient joinApp()");
            this.joinApp();
        }
    }
    
    @Override
    public void onConnectionFailed(final ConnectionResult connectionResult) {
        Log.d(MdxCastApplication.TAG, "GoogleApiClient connect(), failure" + connectionResult);
        this.mCallback.onFailToConnect();
    }
    
    @Override
    public void onConnectionSuspended(final int n) {
        Log.d(MdxCastApplication.TAG, "GoogleApiClient connect(), suspended" + n);
        this.mCallback.onFailToConnect();
    }
    
    @Override
    public void onMessageReceived(final CastDevice castDevice, final String s, final String s2) {
        Log.d(MdxCastApplication.TAG, "onMessageReceived :" + s + ": " + s2);
        this.mCallback.onMessageReceived(s2);
    }
    
    @Override
    public void onVolumeChanged() {
    }
    
    public void sendMessage(final String s) {
        Cast.CastApi.sendMessage(this.mApiClient, "urn:x-cast:mdx-netflix-com:service:target:2", s).setResultCallback(new SendMessageResultCallback());
    }
    
    public void stop() {
        while (true) {
            try {
                Cast.CastApi.removeMessageReceivedCallbacks(this.mApiClient, "urn:x-cast:mdx-netflix-com:service:target:2");
                this.mApiClient.disconnect();
            }
            catch (IllegalStateException ex) {
                ex.printStackTrace();
                continue;
            }
            catch (IOException ex2) {
                ex2.printStackTrace();
                continue;
            }
            break;
        }
    }
    
    private final class LaunchResultCallback implements ResultCallback<ApplicationConnectionResult>
    {
        final MessageReceivedCallback mMessageReceivedCallback;
        
        LaunchResultCallback(final MessageReceivedCallback mMessageReceivedCallback) {
            this.mMessageReceivedCallback = mMessageReceivedCallback;
        }
        
        @Override
        public void onResult(final ApplicationConnectionResult applicationConnectionResult) {
            if (applicationConnectionResult.getStatus().isSuccess()) {
                Log.d(MdxCastApplication.TAG, "launchApplication(), success");
                try {
                    Cast.CastApi.setMessageReceivedCallbacks(MdxCastApplication.this.mApiClient, "urn:x-cast:mdx-netflix-com:service:target:2", this.mMessageReceivedCallback);
                    MdxCastApplication.this.mForceLaunch = false;
                    MdxCastApplication.this.mCallback.onLaunched();
                    return;
                }
                catch (IllegalStateException ex) {
                    ex.printStackTrace();
                    MdxCastApplication.this.mCallback.onFailToLaunch();
                    return;
                }
                catch (IOException ex2) {
                    MdxCastApplication.this.mCallback.onFailToLaunch();
                    ex2.printStackTrace();
                    return;
                }
                catch (Exception ex3) {
                    MdxCastApplication.this.mCallback.onFailToLaunch();
                    ex3.printStackTrace();
                    return;
                }
            }
            if (applicationConnectionResult.getStatus().getStatusCode() == 2002) {
                Log.d(MdxCastApplication.TAG, "launchApplication(), cancelled & ignored");
                return;
            }
            if (applicationConnectionResult.getStatus().getStatusCode() == 15) {
                Log.d(MdxCastApplication.TAG, "launchApplication(), timeout, wait - JUST A WORKAROUND");
                MdxCastApplication.this.mCallback.onFailToLaunch();
                return;
            }
            Log.d(MdxCastApplication.TAG, "launchApplication(), failure, result: " + applicationConnectionResult.getStatus().getStatusCode());
            MdxCastApplication.this.mCallback.onFailToLaunch();
        }
    }
    
    public interface MdxCastApplicaCallback
    {
        void onApplicationStopped();
        
        void onFailToConnect();
        
        void onFailToLaunch();
        
        void onFailToSendMessage();
        
        void onLaunched();
        
        void onMessageReceived(final String p0);
        
        void onMessageSent();
    }
    
    private final class SendMessageResultCallback implements ResultCallback<Status>
    {
        @Override
        public void onResult(final Status status) {
            if (status.isSuccess()) {
                Log.d(MdxCastApplication.TAG, "SendMessage(), success");
                MdxCastApplication.this.mCallback.onMessageSent();
                return;
            }
            Log.d(MdxCastApplication.TAG, "SendMessage(), failure");
            MdxCastApplication.this.mCallback.onFailToSendMessage();
        }
    }
}
