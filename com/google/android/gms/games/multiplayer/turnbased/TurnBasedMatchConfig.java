// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer.turnbased;

import java.util.Collection;
import com.google.android.gms.common.internal.n;
import java.util.ArrayList;
import android.os.Bundle;

public final class TurnBasedMatchConfig
{
    private final int abS;
    private final String[] acg;
    private final Bundle ach;
    private final int acs;
    
    private TurnBasedMatchConfig(final Builder builder) {
        this.abS = builder.abS;
        this.acs = builder.acs;
        this.ach = builder.ach;
        this.acg = builder.ack.toArray(new String[builder.ack.size()]);
    }
    
    public static Builder builder() {
        return new Builder();
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
    
    public static final class Builder
    {
        int abS;
        Bundle ach;
        ArrayList<String> ack;
        int acs;
        
        private Builder() {
            this.abS = -1;
            this.ack = new ArrayList<String>();
            this.ach = null;
            this.acs = 2;
        }
        
        public Builder addInvitedPlayer(final String s) {
            n.i(s);
            this.ack.add(s);
            return this;
        }
        
        public Builder addInvitedPlayers(final ArrayList<String> list) {
            n.i(list);
            this.ack.addAll(list);
            return this;
        }
        
        public TurnBasedMatchConfig build() {
            return new TurnBasedMatchConfig(this, null);
        }
        
        public Builder setAutoMatchCriteria(final Bundle ach) {
            this.ach = ach;
            return this;
        }
        
        public Builder setVariant(final int abS) {
            n.b(abS == -1 || abS > 0, (Object)"Variant must be a positive integer or TurnBasedMatch.MATCH_VARIANT_ANY");
            this.abS = abS;
            return this;
        }
    }
}
