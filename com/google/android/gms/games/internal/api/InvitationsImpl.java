// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.multiplayer.InvitationBuffer;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.multiplayer.OnInvitationReceivedListener;
import com.google.android.gms.common.api.a;
import android.os.RemoteException;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.Games;
import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.multiplayer.Invitations;

public final class InvitationsImpl implements Invitations
{
    @Override
    public Intent getInvitationInboxIntent(final GoogleApiClient googleApiClient) {
        return Games.c(googleApiClient).gs();
    }
    
    @Override
    public PendingResult<LoadInvitationsResult> loadInvitations(final GoogleApiClient googleApiClient) {
        return this.loadInvitations(googleApiClient, 0);
    }
    
    @Override
    public PendingResult<LoadInvitationsResult> loadInvitations(final GoogleApiClient googleApiClient, final int n) {
        return googleApiClient.a((PendingResult<LoadInvitationsResult>)new LoadInvitationsImpl() {
            protected void a(final GamesClientImpl gamesClientImpl) {
                gamesClientImpl.c((d<LoadInvitationsResult>)this, n);
            }
        });
    }
    
    @Override
    public void registerInvitationListener(final GoogleApiClient googleApiClient, final OnInvitationReceivedListener onInvitationReceivedListener) {
        Games.c(googleApiClient).a(onInvitationReceivedListener);
    }
    
    @Override
    public void unregisterInvitationListener(final GoogleApiClient googleApiClient) {
        Games.c(googleApiClient).gt();
    }
    
    private abstract static class LoadInvitationsImpl extends BaseGamesApiMethodImpl<LoadInvitationsResult>
    {
        public LoadInvitationsResult C(final Status status) {
            return new LoadInvitationsResult() {
                @Override
                public InvitationBuffer getInvitations() {
                    return new InvitationBuffer(DataHolder.empty(14));
                }
                
                @Override
                public Status getStatus() {
                    return status;
                }
                
                @Override
                public void release() {
                }
            };
        }
    }
}
