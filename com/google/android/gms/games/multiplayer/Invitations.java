// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer;

import com.google.android.gms.common.api.PendingResult;
import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;

public interface Invitations
{
    Intent getInvitationInboxIntent(final GoogleApiClient p0);
    
    PendingResult<Invitations$LoadInvitationsResult> loadInvitations(final GoogleApiClient p0);
    
    PendingResult<Invitations$LoadInvitationsResult> loadInvitations(final GoogleApiClient p0, final int p1);
    
    void registerInvitationListener(final GoogleApiClient p0, final OnInvitationReceivedListener p1);
    
    void unregisterInvitationListener(final GoogleApiClient p0);
}
