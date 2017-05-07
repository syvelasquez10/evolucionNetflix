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
    private final InvitationBuffer MV;
    private final TurnBasedMatchBuffer MW;
    private final TurnBasedMatchBuffer MX;
    private final TurnBasedMatchBuffer MY;
    
    public LoadMatchesResponse(final Bundle bundle) {
        final DataHolder a = a(bundle, 0);
        if (a != null) {
            this.MV = new InvitationBuffer(a);
        }
        else {
            this.MV = null;
        }
        final DataHolder a2 = a(bundle, 1);
        if (a2 != null) {
            this.MW = new TurnBasedMatchBuffer(a2);
        }
        else {
            this.MW = null;
        }
        final DataHolder a3 = a(bundle, 2);
        if (a3 != null) {
            this.MX = new TurnBasedMatchBuffer(a3);
        }
        else {
            this.MX = null;
        }
        final DataHolder a4 = a(bundle, 3);
        if (a4 != null) {
            this.MY = new TurnBasedMatchBuffer(a4);
            return;
        }
        this.MY = null;
    }
    
    private static DataHolder a(final Bundle bundle, final int n) {
        final String bd = TurnBasedMatchTurnStatus.bd(n);
        if (!bundle.containsKey(bd)) {
            return null;
        }
        return (DataHolder)bundle.getParcelable(bd);
    }
    
    public void close() {
        if (this.MV != null) {
            this.MV.close();
        }
        if (this.MW != null) {
            this.MW.close();
        }
        if (this.MX != null) {
            this.MX.close();
        }
        if (this.MY != null) {
            this.MY.close();
        }
    }
    
    public TurnBasedMatchBuffer getCompletedMatches() {
        return this.MY;
    }
    
    public InvitationBuffer getInvitations() {
        return this.MV;
    }
    
    public TurnBasedMatchBuffer getMyTurnMatches() {
        return this.MW;
    }
    
    public TurnBasedMatchBuffer getTheirTurnMatches() {
        return this.MX;
    }
    
    public boolean hasData() {
        return (this.MV != null && this.MV.getCount() > 0) || (this.MW != null && this.MW.getCount() > 0) || (this.MX != null && this.MX.getCount() > 0) || (this.MY != null && this.MY.getCount() > 0);
    }
}
