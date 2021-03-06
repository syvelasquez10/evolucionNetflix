// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import com.google.android.gms.common.api.Status;
import android.os.RemoteException;
import java.io.IOException;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.cast.internal.zzk;
import com.google.android.gms.cast.internal.zze;
import com.google.android.gms.common.api.GoogleApiClient;

public final class Cast$CastApi$zza implements Cast$CastApi
{
    @Override
    public ApplicationMetadata getApplicationMetadata(final GoogleApiClient googleApiClient) {
        return googleApiClient.zza(zzk.zzRk).getApplicationMetadata();
    }
    
    @Override
    public String getApplicationStatus(final GoogleApiClient googleApiClient) {
        return googleApiClient.zza(zzk.zzRk).getApplicationStatus();
    }
    
    @Override
    public PendingResult<Cast$ApplicationConnectionResult> joinApplication(final GoogleApiClient googleApiClient, final String s) {
        return this.zza(googleApiClient, s, null, null);
    }
    
    @Override
    public PendingResult<Cast$ApplicationConnectionResult> launchApplication(final GoogleApiClient googleApiClient, final String s) {
        return googleApiClient.zzb((PendingResult<Cast$ApplicationConnectionResult>)new Cast$CastApi$zza$2(this, googleApiClient, s));
    }
    
    @Override
    public void removeMessageReceivedCallbacks(final GoogleApiClient googleApiClient, final String s) {
        try {
            googleApiClient.zza(zzk.zzRk).zzbL(s);
        }
        catch (RemoteException ex) {
            throw new IOException("service error");
        }
    }
    
    @Override
    public PendingResult<Status> sendMessage(final GoogleApiClient googleApiClient, final String s, final String s2) {
        return googleApiClient.zzb((PendingResult<Status>)new Cast$CastApi$zza$1(this, googleApiClient, s, s2));
    }
    
    @Override
    public void setMessageReceivedCallbacks(final GoogleApiClient googleApiClient, final String s, final Cast$MessageReceivedCallback cast$MessageReceivedCallback) {
        try {
            googleApiClient.zza(zzk.zzRk).zza(s, cast$MessageReceivedCallback);
        }
        catch (RemoteException ex) {
            throw new IOException("service error");
        }
    }
    
    @Override
    public PendingResult<Status> stopApplication(final GoogleApiClient googleApiClient) {
        return googleApiClient.zzb((PendingResult<Status>)new Cast$CastApi$zza$6(this, googleApiClient));
    }
    
    public PendingResult<Cast$ApplicationConnectionResult> zza(final GoogleApiClient googleApiClient, final String s, final String s2, final JoinOptions joinOptions) {
        return googleApiClient.zzb((PendingResult<Cast$ApplicationConnectionResult>)new Cast$CastApi$zza$4(this, googleApiClient, s, s2, joinOptions));
    }
}
