// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;

public interface MessageApi
{
    public static final int UNKNOWN_REQUEST_ID = -1;
    
    PendingResult<Status> addListener(final GoogleApiClient p0, final MessageApi$MessageListener p1);
    
    PendingResult<Status> removeListener(final GoogleApiClient p0, final MessageApi$MessageListener p1);
    
    PendingResult<MessageApi$SendMessageResult> sendMessage(final GoogleApiClient p0, final String p1, final String p2, final byte[] p3);
}
