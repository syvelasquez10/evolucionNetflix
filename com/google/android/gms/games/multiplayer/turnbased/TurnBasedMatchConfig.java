// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer.turnbased;

import java.util.Collection;
import com.google.android.gms.internal.fq;
import java.util.ArrayList;
import android.os.Bundle;

public final class TurnBasedMatchConfig
{
    private final String[] MN;
    private final Bundle MO;
    private final int MZ;
    private final int My;
    
    private TurnBasedMatchConfig(final Builder builder) {
        this.My = builder.My;
        this.MZ = builder.MZ;
        this.MO = builder.MO;
        this.MN = builder.MR.toArray(new String[builder.MR.size()]);
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
        return this.MO;
    }
    
    public String[] getInvitedPlayerIds() {
        return this.MN;
    }
    
    public int getMinPlayers() {
        return this.MZ;
    }
    
    public int getVariant() {
        return this.My;
    }
    
    public static final class Builder
    {
        Bundle MO;
        ArrayList<String> MR;
        int MZ;
        int My;
        
        private Builder() {
            this.My = -1;
            this.MR = new ArrayList<String>();
            this.MO = null;
            this.MZ = 2;
        }
        
        public Builder addInvitedPlayer(final String s) {
            fq.f(s);
            this.MR.add(s);
            return this;
        }
        
        public Builder addInvitedPlayers(final ArrayList<String> list) {
            fq.f(list);
            this.MR.addAll(list);
            return this;
        }
        
        public TurnBasedMatchConfig build() {
            return new TurnBasedMatchConfig(this, null);
        }
        
        public Builder setAutoMatchCriteria(final Bundle mo) {
            this.MO = mo;
            return this;
        }
        
        public Builder setMinPlayers(final int mz) {
            this.MZ = mz;
            return this;
        }
        
        public Builder setVariant(final int my) {
            fq.b(my == -1 || my > 0, "Variant must be a positive integer or TurnBasedMatch.MATCH_VARIANT_ANY");
            this.My = my;
            return this;
        }
    }
}
