// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.leaderboard;

import android.net.Uri;
import com.google.android.gms.games.Player;
import android.database.CharArrayBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.PlayerRef;
import com.google.android.gms.common.data.d;

public final class LeaderboardScoreRef extends d implements LeaderboardScore
{
    private final PlayerRef abA;
    
    LeaderboardScoreRef(final DataHolder dataHolder, final int n) {
        super(dataHolder, n);
        this.abA = new PlayerRef(dataHolder, n);
    }
    
    @Override
    public boolean equals(final Object o) {
        return LeaderboardScoreEntity.a(this, o);
    }
    
    @Override
    public String getDisplayRank() {
        return this.getString("display_rank");
    }
    
    @Override
    public void getDisplayRank(final CharArrayBuffer charArrayBuffer) {
        this.a("display_rank", charArrayBuffer);
    }
    
    @Override
    public String getDisplayScore() {
        return this.getString("display_score");
    }
    
    @Override
    public void getDisplayScore(final CharArrayBuffer charArrayBuffer) {
        this.a("display_score", charArrayBuffer);
    }
    
    @Override
    public long getRank() {
        return this.getLong("rank");
    }
    
    @Override
    public long getRawScore() {
        return this.getLong("raw_score");
    }
    
    @Override
    public Player getScoreHolder() {
        if (this.aS("external_player_id")) {
            return null;
        }
        return this.abA;
    }
    
    @Override
    public String getScoreHolderDisplayName() {
        if (this.aS("external_player_id")) {
            return this.getString("default_display_name");
        }
        return this.abA.getDisplayName();
    }
    
    @Override
    public void getScoreHolderDisplayName(final CharArrayBuffer charArrayBuffer) {
        if (this.aS("external_player_id")) {
            this.a("default_display_name", charArrayBuffer);
            return;
        }
        this.abA.getDisplayName(charArrayBuffer);
    }
    
    @Override
    public Uri getScoreHolderHiResImageUri() {
        if (this.aS("external_player_id")) {
            return null;
        }
        return this.abA.getHiResImageUri();
    }
    
    @Override
    public String getScoreHolderHiResImageUrl() {
        if (this.aS("external_player_id")) {
            return null;
        }
        return this.abA.getHiResImageUrl();
    }
    
    @Override
    public Uri getScoreHolderIconImageUri() {
        if (this.aS("external_player_id")) {
            return this.aR("default_display_image_uri");
        }
        return this.abA.getIconImageUri();
    }
    
    @Override
    public String getScoreHolderIconImageUrl() {
        if (this.aS("external_player_id")) {
            return this.getString("default_display_image_url");
        }
        return this.abA.getIconImageUrl();
    }
    
    @Override
    public String getScoreTag() {
        return this.getString("score_tag");
    }
    
    @Override
    public long getTimestampMillis() {
        return this.getLong("achieved_timestamp");
    }
    
    @Override
    public int hashCode() {
        return LeaderboardScoreEntity.a(this);
    }
    
    public LeaderboardScore lA() {
        return new LeaderboardScoreEntity(this);
    }
    
    @Override
    public String toString() {
        return LeaderboardScoreEntity.b(this);
    }
}
