// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import com.google.android.gms.common.api.Status;
import android.os.RemoteException;
import java.io.IOException;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.internal.ij;
import com.google.android.gms.common.api.GoogleApiClient;

public final class Cast$CastApi$a implements Cast$CastApi
{
    @Override
    public ApplicationMetadata getApplicationMetadata(final GoogleApiClient googleApiClient) {
        return googleApiClient.a(Cast.CU).getApplicationMetadata();
    }
    
    @Override
    public String getApplicationStatus(final GoogleApiClient googleApiClient) {
        return googleApiClient.a(Cast.CU).getApplicationStatus();
    }
    
    @Override
    public PendingResult<Cast$ApplicationConnectionResult> joinApplication(final GoogleApiClient googleApiClient, final String s) {
        return googleApiClient.b((PendingResult<Cast$ApplicationConnectionResult>)new Cast$CastApi$a$5(this, s));
    }
    
    @Override
    public PendingResult<Cast$ApplicationConnectionResult> launchApplication(final GoogleApiClient googleApiClient, final String s) {
        return googleApiClient.b((PendingResult<Cast$ApplicationConnectionResult>)new Cast$CastApi$a$2(this, s));
    }
    
    @Override
    public void removeMessageReceivedCallbacks(final GoogleApiClient googleApiClient, final String s) {
        try {
            googleApiClient.a(Cast.CU).aE(s);
        }
        catch (RemoteException ex) {
            throw new IOException("service error");
        }
    }
    
    @Override
    public PendingResult<Status> sendMessage(final GoogleApiClient googleApiClient, final String s, final String s2) {
        return googleApiClient.b((PendingResult<Status>)new Cast$CastApi$a$1(this, s, s2));
    }
    
    @Override
    public void setMessageReceivedCallbacks(final GoogleApiClient googleApiClient, final String s, final Cast$MessageReceivedCallback cast$MessageReceivedCallback) {
        try {
            googleApiClient.a(Cast.CU).a(s, cast$MessageReceivedCallback);
        }
        catch (RemoteException ex) {
            throw new IOException("service error");
        }
    }
}
