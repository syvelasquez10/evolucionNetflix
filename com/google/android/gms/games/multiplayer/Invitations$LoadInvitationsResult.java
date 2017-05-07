// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Releasable;

public interface Invitations$LoadInvitationsResult extends Releasable, Result
{
    InvitationBuffer getInvitations();
}
