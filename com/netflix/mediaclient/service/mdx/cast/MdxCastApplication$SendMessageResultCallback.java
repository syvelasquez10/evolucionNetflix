// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx.cast;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import com.google.android.gms.common.ConnectionResult;
import android.os.Bundle;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.cast.Cast$ApplicationConnectionResult;
import com.netflix.mediaclient.util.StringUtils;
import com.google.android.gms.cast.Cast$CastOptions$Builder;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.common.api.GoogleApiClient$Builder;
import com.google.android.gms.cast.Cast$CastOptions;
import com.google.android.gms.cast.CastDevice;
import android.content.Context;
import java.util.concurrent.atomic.AtomicBoolean;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.cast.Cast$MessageReceivedCallback;
import com.google.android.gms.cast.Cast$Listener;
import com.netflix.mediaclient.Log;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.ResultCallback;

final class MdxCastApplication$SendMessageResultCallback implements ResultCallback<Status>
{
    final /* synthetic */ MdxCastApplication this$0;
    
    private MdxCastApplication$SendMessageResultCallback(final MdxCastApplication this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onResult(final Status status) {
        if (status.isSuccess()) {
            Log.d(MdxCastApplication.TAG, "SendMessage(), success");
            this.this$0.mCallback.onMessageSent();
            return;
        }
        if (Log.isLoggable(MdxCastApplication.TAG, 3)) {
            Log.d(MdxCastApplication.TAG, "SendMessage(), failure with result " + status);
        }
        if (status.getStatus().getStatusCode() == 15) {
            Log.d(MdxCastApplication.TAG, "SendMessage(), has timed out");
            this.this$0.mCallback.onFailToSendMessage("StatusCodes: TIMEOUT");
            return;
        }
        this.this$0.mCallback.onFailToSendMessage("getStatusCode: " + status.getStatus().getStatusCode());
    }
}
