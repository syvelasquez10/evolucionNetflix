// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx.cast;

import com.google.android.gms.common.api.Result;
import java.io.IOException;
import com.google.android.gms.common.api.Status;
import java.util.concurrent.TimeUnit;
import com.google.android.gms.common.ConnectionResult;
import android.os.Bundle;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.common.api.ResultCallback;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import com.google.android.gms.cast.CastDevice;
import android.content.Context;
import java.util.concurrent.atomic.AtomicBoolean;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.cast.Cast;

public class MdxCastApplication extends Listener implements OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks, MessageReceivedCallback
{
    private static final long CAST_SEND_MEESAGE_TIMEOUT_MS = 5000L;
    private static final String MESSAGE_NAMESPACE = "urn:x-cast:mdx-netflix-com:service:target:2";
    private static final String TAG;
    private GoogleApiClient mApiClient;
    private final String mApplicationId;
    private MdxCastApplicaCallback mCallback;
    private AtomicBoolean mConnected;
    private AtomicBoolean mConnectionSuspended;
    private AtomicBoolean mForceLaunch;
    
    static {
        TAG = MdxCastApplication.class.getSimpleName();
    }
    
    MdxCastApplication(final Context context, final String mApplicationId, final CastDevice castDevice, final MdxCastApplicaCallback mCallback, final boolean b) {
        this.mForceLaunch = new AtomicBoolean(false);
        this.mConnected = new AtomicBoolean(false);
        this.mConnectionSuspended = new AtomicBoolean(false);
        this.mApplicationId = mApplicationId;
        this.mCallback = mCallback;
        this.mForceLaunch.set(b);
        final Cast.CastOptions.Builder builder = Cast.CastOptions.builder(castDevice, this);
        if (Log.isLoggable(MdxCastApplication.TAG, 3)) {
            Log.d(MdxCastApplication.TAG, "CastOptions.Builder setVerboseLoggingEnabled(true)");
            builder.setVerboseLoggingEnabled(true);
        }
        (this.mApiClient = new GoogleApiClient.Builder(context).addApi(Cast.API, builder.build()).addConnectionCallbacks(this).addOnConnectionFailedListener(this).build()).connect();
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
        this.mCallback.onApplicationStopped("onApplicationDisconnected: " + n);
    }
    
    @Override
    public void onApplicationStatusChanged() {
        Log.d(MdxCastApplication.TAG, "Cast.Listener onApplicationStatusChanged");
    }
    
    @Override
    public void onConnected(final Bundle bundle) {
        while (true) {
            Log.d(MdxCastApplication.TAG, "GoogleApiClient connect(), success arg:" + bundle);
            this.mConnected.set(true);
            this.mConnectionSuspended.set(false);
            while (true) {
                Label_0176: {
                    try {
                        if (this.mForceLaunch.get()) {
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
                        final MdxCastApplicaCallback mCallback = this.mCallback;
                        final StringBuilder append = new StringBuilder().append("onConnected ");
                        if (this.mForceLaunch.get()) {
                            final String s = "launch";
                            mCallback.onFailToConnect(append.append(s).append(" has IllegalStateException: ").append(ex.getMessage()).toString());
                            return;
                        }
                        break Label_0176;
                    }
                    break;
                }
                final String s = "join";
                continue;
            }
        }
        if (this.isNetflixRunning()) {
            Log.d(MdxCastApplication.TAG, "GoogleApiClient joinApp()");
            this.joinApp();
        }
    }
    
    @Override
    public void onConnectionFailed(final ConnectionResult connectionResult) {
        Log.d(MdxCastApplication.TAG, "GoogleApiClient connect(), failure" + connectionResult);
        this.mConnected.set(false);
        this.mConnectionSuspended.set(false);
        this.mCallback.onFailToConnect("ConnectionResult ErrorCode: " + connectionResult.getErrorCode());
    }
    
    @Override
    public void onConnectionSuspended(final int n) {
        Log.d(MdxCastApplication.TAG, "GoogleApiClient connect(), suspended" + n);
        this.mConnectionSuspended.set(true);
        this.mCallback.onFailToConnect("onConnectionSuspended: " + n);
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
        if (!this.mConnected.get()) {
            Log.d(MdxCastApplication.TAG, "SendMessage(), disconnected, message can't be delivered");
            return;
        }
        if (!this.mConnectionSuspended.get()) {
            Log.d(MdxCastApplication.TAG, "SendMessage(), message delivered to cast");
            Cast.CastApi.sendMessage(this.mApiClient, "urn:x-cast:mdx-netflix-com:service:target:2", s).setResultCallback(new SendMessageResultCallback(), 5000L, TimeUnit.MILLISECONDS);
            return;
        }
        Log.d(MdxCastApplication.TAG, "SendMessage(), connection suspended, message can't be delivered");
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
                    MdxCastApplication.this.mForceLaunch.set(false);
                    MdxCastApplication.this.mCallback.onLaunched();
                    return;
                }
                catch (IllegalStateException ex) {
                    MdxCastApplication.this.mCallback.onFailToLaunch("IllegalStateException: " + ex.getMessage());
                    return;
                }
                catch (IOException ex2) {
                    MdxCastApplication.this.mCallback.onFailToLaunch("IOException: " + ex2.getMessage());
                    return;
                }
                catch (Exception ex3) {
                    MdxCastApplication.this.mCallback.onFailToLaunch("Exception: " + ex3.getMessage());
                    return;
                }
            }
            if (applicationConnectionResult.getStatus().getStatusCode() == 2002) {
                Log.d(MdxCastApplication.TAG, "launchApplication(), cancelled & ignored");
                return;
            }
            if (applicationConnectionResult.getStatus().getStatusCode() == 15) {
                Log.d(MdxCastApplication.TAG, "launchApplication(), timeout, wait - JUST A WORKAROUND");
                MdxCastApplication.this.mCallback.onFailToLaunch("StatusCodes: TIMEOUT");
                return;
            }
            Log.d(MdxCastApplication.TAG, "launchApplication(), failure, result: " + applicationConnectionResult.getStatus().getStatusCode());
            MdxCastApplication.this.mCallback.onFailToLaunch("getStatusCode: " + applicationConnectionResult.getStatus().getStatusCode());
        }
    }
    
    public interface MdxCastApplicaCallback
    {
        void onApplicationStopped(final String p0);
        
        void onFailToConnect(final String p0);
        
        void onFailToLaunch(final String p0);
        
        void onFailToSendMessage(final String p0);
        
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
            if (Log.isLoggable(MdxCastApplication.TAG, 3)) {
                Log.d(MdxCastApplication.TAG, "SendMessage(), failure with result " + status);
            }
            if (status.getStatus().getStatusCode() == 15) {
                Log.d(MdxCastApplication.TAG, "SendMessage(), has timed out");
                MdxCastApplication.this.mCallback.onFailToSendMessage("StatusCodes: TIMEOUT");
                return;
            }
            MdxCastApplication.this.mCallback.onFailToSendMessage("getStatusCode: " + status.getStatus().getStatusCode());
        }
    }
}
