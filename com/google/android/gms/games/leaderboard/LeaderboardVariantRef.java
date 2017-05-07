// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.leaderboard;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;

public final class LeaderboardVariantRef extends d implements LeaderboardVariant
{
    LeaderboardVariantRef(final DataHolder dataHolder, final int n) {
        super(dataHolder, n);
    }
    
    @Override
    public boolean equals(final Object o) {
        return LeaderboardVariantEntity.a(this, o);
    }
    
    @Override
    public int getCollection() {
        return this.getInteger("collection");
    }
    
    @Override
    public String getDisplayPlayerRank() {
        return this.getString("player_display_rank");
    }
    
    @Override
    public String getDisplayPlayerScore() {
        return this.getString("player_display_score");
    }
    
    @Override
    public long getNumScores() {
        if (this.aS("total_scores")) {
            return -1L;
        }
        return this.getLong("total_scores");
    }
    
    @Override
    public long getPlayerRank() {
        if (this.aS("player_rank")) {
            return -1L;
        }
        return this.getLong("player_rank");
    }
    
    @Override
    public String getPlayerScoreTag() {
        return this.getString("player_score_tag");
    }
    
    @Override
    public long getRawPlayerScore() {
        if (this.aS("player_raw_score")) {
            return -1L;
        }
        return this.getLong("player_raw_score");
    }
    
    @Override
    public int getTimeSpan() {
        return this.getInteger("timespan");
    }
    
    @Override
    public boolean hasPlayerInfo() {
        return !this.aS("player_raw_score");
    }
    
    @Override
    public int hashCode() {
        return LeaderboardVariantEntity.a(this);
    }
    
    @Override
    public String lB() {
        return this.getString("top_page_token_next");
    }
    
    @Override
    public String lC() {
        return this.getString("window_page_token_prev");
    }
    
    @Override
    public String lD() {
        return this.getString("window_page_token_next");
    }
    
    public LeaderboardVariant lE() {
        return new LeaderboardVariantEntity(this);
    }
    
    @Override
    public String toString() {
        return LeaderboardVariantEntity.b(this);
    }
}
