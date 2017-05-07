// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer.turnbased;

import com.google.android.gms.internal.gf;
import com.google.android.gms.common.data.DataHolder;
import android.os.Bundle;
import com.google.android.gms.games.multiplayer.InvitationBuffer;

public final class LoadMatchesResponse
{
    private final InvitationBuffer wO;
    private final TurnBasedMatchBuffer wP;
    private final TurnBasedMatchBuffer wQ;
    private final TurnBasedMatchBuffer wR;
    
    public LoadMatchesResponse(final Bundle bundle) {
        final DataHolder a = a(bundle, 0);
        if (a != null) {
            this.wO = new InvitationBuffer(a);
        }
        else {
            this.wO = null;
        }
        final DataHolder a2 = a(bundle, 1);
        if (a2 != null) {
            this.wP = new TurnBasedMatchBuffer(a2);
        }
        else {
            this.wP = null;
        }
        final DataHolder a3 = a(bundle, 2);
        if (a3 != null) {
            this.wQ = new TurnBasedMatchBuffer(a3);
        }
        else {
            this.wQ = null;
        }
        final DataHolder a4 = a(bundle, 3);
        if (a4 != null) {
            this.wR = new TurnBasedMatchBuffer(a4);
            return;
        }
        this.wR = null;
    }
    
    private static DataHolder a(final Bundle bundle, final int n) {
        final String ag = gf.aG(n);
        if (!bundle.containsKey(ag)) {
            return null;
        }
        return (DataHolder)bundle.getParcelable(ag);
    }
    
    public void close() {
        if (this.wO != null) {
            this.wO.close();
        }
        if (this.wP != null) {
            this.wP.close();
        }
        if (this.wQ != null) {
            this.wQ.close();
        }
        if (this.wR != null) {
            this.wR.close();
        }
    }
    
    public TurnBasedMatchBuffer getCompletedMatches() {
        return this.wR;
    }
    
    public InvitationBuffer getInvitations() {
        return this.wO;
    }
    
    public TurnBasedMatchBuffer getMyTurnMatches() {
        return this.wP;
    }
    
    public TurnBasedMatchBuffer getTheirTurnMatches() {
        return this.wQ;
    }
}
