// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer.turnbased;

import com.google.android.gms.games.internal.constants.TurnBasedMatchTurnStatus;
import com.google.android.gms.common.data.DataHolder;
import android.os.Bundle;
import com.google.android.gms.games.multiplayer.InvitationBuffer;

public final class LoadMatchesResponse
{
    private final InvitationBuffer aco;
    private final TurnBasedMatchBuffer acp;
    private final TurnBasedMatchBuffer acq;
    private final TurnBasedMatchBuffer acr;
    
    public LoadMatchesResponse(final Bundle bundle) {
        final DataHolder a = a(bundle, 0);
        if (a != null) {
            this.aco = new InvitationBuffer(a);
        }
        else {
            this.aco = null;
        }
        final DataHolder a2 = a(bundle, 1);
        if (a2 != null) {
            this.acp = new TurnBasedMatchBuffer(a2);
        }
        else {
            this.acp = null;
        }
        final DataHolder a3 = a(bundle, 2);
        if (a3 != null) {
            this.acq = new TurnBasedMatchBuffer(a3);
        }
        else {
            this.acq = null;
        }
        final DataHolder a4 = a(bundle, 3);
        if (a4 != null) {
            this.acr = new TurnBasedMatchBuffer(a4);
            return;
        }
        this.acr = null;
    }
    
    private static DataHolder a(final Bundle bundle, final int n) {
        final String dh = TurnBasedMatchTurnStatus.dH(n);
        if (!bundle.containsKey(dh)) {
            return null;
        }
        return (DataHolder)bundle.getParcelable(dh);
    }
    
    public void close() {
        if (this.aco != null) {
            this.aco.close();
        }
        if (this.acp != null) {
            this.acp.close();
        }
        if (this.acq != null) {
            this.acq.close();
        }
        if (this.acr != null) {
            this.acr.close();
        }
    }
    
    public TurnBasedMatchBuffer getCompletedMatches() {
        return this.acr;
    }
    
    public InvitationBuffer getInvitations() {
        return this.aco;
    }
    
    public TurnBasedMatchBuffer getMyTurnMatches() {
        return this.acp;
    }
    
    public TurnBasedMatchBuffer getTheirTurnMatches() {
        return this.acq;
    }
    
    public boolean hasData() {
        return (this.aco != null && this.aco.getCount() > 0) || (this.acp != null && this.acp.getCount() > 0) || (this.acq != null && this.acq.getCount() > 0) || (this.acr != null && this.acr.getCount() > 0);
    }
}
