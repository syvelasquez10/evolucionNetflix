// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.multiplayer.OnInvitationReceivedListener;
import com.google.android.gms.games.multiplayer.Invitations$LoadInvitationsResult;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.Games;
import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.multiplayer.Invitations;

public final class InvitationsImpl implements Invitations
{
    @Override
    public Intent getInvitationInboxIntent(final GoogleApiClient googleApiClient) {
        return Games.c(googleApiClient).ke();
    }
    
    @Override
    public PendingResult<Invitations$LoadInvitationsResult> loadInvitations(final GoogleApiClient googleApiClient) {
        return this.loadInvitations(googleApiClient, 0);
    }
    
    @Override
    public PendingResult<Invitations$LoadInvitationsResult> loadInvitations(final GoogleApiClient googleApiClient, final int n) {
        return googleApiClient.a((PendingResult<Invitations$LoadInvitationsResult>)new InvitationsImpl$1(this, n));
    }
    
    @Override
    public void registerInvitationListener(final GoogleApiClient googleApiClient, final OnInvitationReceivedListener onInvitationReceivedListener) {
        Games.c(googleApiClient).a(onInvitationReceivedListener);
    }
    
    @Override
    public void unregisterInvitationListener(final GoogleApiClient googleApiClient) {
        Games.c(googleApiClient).kf();
    }
}
