// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer.turnbased;

import java.util.Collection;
import com.google.android.gms.common.internal.n;
import java.util.ArrayList;
import android.os.Bundle;

public final class TurnBasedMatchConfig$Builder
{
    int abS;
    Bundle ach;
    ArrayList<String> ack;
    int acs;
    
    private TurnBasedMatchConfig$Builder() {
        this.abS = -1;
        this.ack = new ArrayList<String>();
        this.ach = null;
        this.acs = 2;
    }
    
    public TurnBasedMatchConfig$Builder addInvitedPlayer(final String s) {
        n.i(s);
        this.ack.add(s);
        return this;
    }
    
    public TurnBasedMatchConfig$Builder addInvitedPlayers(final ArrayList<String> list) {
        n.i(list);
        this.ack.addAll(list);
        return this;
    }
    
    public TurnBasedMatchConfig build() {
        return new TurnBasedMatchConfig(this, null);
    }
    
    public TurnBasedMatchConfig$Builder setAutoMatchCriteria(final Bundle ach) {
        this.ach = ach;
        return this;
    }
    
    public TurnBasedMatchConfig$Builder setVariant(final int abS) {
        n.b(abS == -1 || abS > 0, (Object)"Variant must be a positive integer or TurnBasedMatch.MATCH_VARIANT_ANY");
        this.abS = abS;
        return this;
    }
}
