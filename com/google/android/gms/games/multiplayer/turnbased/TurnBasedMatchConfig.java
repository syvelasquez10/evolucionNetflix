// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer.turnbased;

import android.os.Bundle;

public final class TurnBasedMatchConfig
{
    private final int abS;
    private final String[] acg;
    private final Bundle ach;
    private final int acs;
    
    private TurnBasedMatchConfig(final TurnBasedMatchConfig$Builder turnBasedMatchConfig$Builder) {
        this.abS = turnBasedMatchConfig$Builder.abS;
        this.acs = turnBasedMatchConfig$Builder.acs;
        this.ach = turnBasedMatchConfig$Builder.ach;
        this.acg = turnBasedMatchConfig$Builder.ack.toArray(new String[turnBasedMatchConfig$Builder.ack.size()]);
    }
    
    public static TurnBasedMatchConfig$Builder builder() {
        return new TurnBasedMatchConfig$Builder(null);
    }
    
    public static Bundle createAutoMatchCriteria(final int n, final int n2, final long n3) {
        final Bundle bundle = new Bundle();
        bundle.putInt("min_automatch_players", n);
        bundle.putInt("max_automatch_players", n2);
        bundle.putLong("exclusive_bit_mask", n3);
        return bundle;
    }
    
    public Bundle getAutoMatchCriteria() {
        return this.ach;
    }
    
    public String[] getInvitedPlayerIds() {
        return this.acg;
    }
    
    public int getVariant() {
        return this.abS;
    }
    
    public int lF() {
        return this.acs;
    }
}
