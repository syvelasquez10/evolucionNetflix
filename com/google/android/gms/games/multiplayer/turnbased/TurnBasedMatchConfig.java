// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer.turnbased;

import java.util.Collection;
import com.google.android.gms.internal.eg;
import java.util.ArrayList;
import android.os.Bundle;

public final class TurnBasedMatchConfig
{
    private final String[] wG;
    private final Bundle wH;
    private final int wT;
    private final int wo;
    
    private TurnBasedMatchConfig(final Builder builder) {
        this.wo = builder.wo;
        this.wT = builder.wT;
        this.wH = builder.wH;
        this.wG = builder.wK.toArray(new String[builder.wK.size()]);
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
        return this.wH;
    }
    
    public String[] getInvitedPlayerIds() {
        return this.wG;
    }
    
    public int getMinPlayers() {
        return this.wT;
    }
    
    public int getVariant() {
        return this.wo;
    }
    
    public static final class Builder
    {
        Bundle wH;
        ArrayList<String> wK;
        int wT;
        int wo;
        
        private Builder() {
            this.wo = -1;
            this.wK = new ArrayList<String>();
            this.wH = null;
            this.wT = 2;
        }
        
        public Builder addInvitedPlayer(final String s) {
            eg.f(s);
            this.wK.add(s);
            return this;
        }
        
        public Builder addInvitedPlayers(final ArrayList<String> list) {
            eg.f(list);
            this.wK.addAll(list);
            return this;
        }
        
        public TurnBasedMatchConfig build() {
            return new TurnBasedMatchConfig(this, null);
        }
        
        public Builder setAutoMatchCriteria(final Bundle wh) {
            this.wH = wh;
            return this;
        }
        
        public Builder setMinPlayers(final int wt) {
            this.wT = wt;
            return this;
        }
        
        public Builder setVariant(final int wo) {
            eg.b(wo == -1 || wo > 0, "Variant must be a positive integer or TurnBasedMatch.MATCH_VARIANT_ANY");
            this.wo = wo;
            return this;
        }
    }
}
