// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import com.google.android.gms.wearable.MessageApi$SendMessageResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import android.content.IntentFilter;
import com.google.android.gms.wearable.MessageApi$MessageListener;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.MessageApi;

public final class ag implements MessageApi
{
    private PendingResult<Status> a(final GoogleApiClient googleApiClient, final MessageApi$MessageListener messageApi$MessageListener, final IntentFilter[] array) {
        return googleApiClient.a((PendingResult<Status>)new ag$2(this, messageApi$MessageListener, array));
    }
    
    @Override
    public PendingResult<Status> addListener(final GoogleApiClient googleApiClient, final MessageApi$MessageListener messageApi$MessageListener) {
        return this.a(googleApiClient, messageApi$MessageListener, null);
    }
    
    @Override
    public PendingResult<Status> removeListener(final GoogleApiClient googleApiClient, final MessageApi$MessageListener messageApi$MessageListener) {
        return googleApiClient.a((PendingResult<Status>)new ag$3(this, messageApi$MessageListener));
    }
    
    @Override
    public PendingResult<MessageApi$SendMessageResult> sendMessage(final GoogleApiClient googleApiClient, final String s, final String s2, final byte[] array) {
        return googleApiClient.a((PendingResult<MessageApi$SendMessageResult>)new ag$1(this, s, s2, array));
    }
}
