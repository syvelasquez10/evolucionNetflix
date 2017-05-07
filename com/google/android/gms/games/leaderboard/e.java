// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.leaderboard;

import android.net.Uri;
import com.google.android.gms.games.Player;
import android.database.CharArrayBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.d;
import com.google.android.gms.common.data.b;

public final class e extends b implements LeaderboardScore
{
    private final d vU;
    
    e(final DataHolder dataHolder, final int n) {
        super(dataHolder, n);
        this.vU = new d(dataHolder, n);
    }
    
    public LeaderboardScore ds() {
        return new com.google.android.gms.games.leaderboard.d(this);
    }
    
    @Override
    public boolean equals(final Object o) {
        return com.google.android.gms.games.leaderboard.d.a(this, o);
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
        if (this.M("external_player_id")) {
            return null;
        }
        return this.vU;
    }
    
    @Override
    public String getScoreHolderDisplayName() {
        if (this.M("external_player_id")) {
            return this.getString("default_display_name");
        }
        return this.vU.getDisplayName();
    }
    
    @Override
    public void getScoreHolderDisplayName(final CharArrayBuffer charArrayBuffer) {
        if (this.M("external_player_id")) {
            this.a("default_display_name", charArrayBuffer);
            return;
        }
        this.vU.getDisplayName(charArrayBuffer);
    }
    
    @Override
    public Uri getScoreHolderHiResImageUri() {
        if (this.M("external_player_id")) {
            return null;
        }
        return this.vU.getHiResImageUri();
    }
    
    @Override
    public Uri getScoreHolderIconImageUri() {
        if (this.M("external_player_id")) {
            return this.L("default_display_image_uri");
        }
        return this.vU.getIconImageUri();
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
        return com.google.android.gms.games.leaderboard.d.a(this);
    }
    
    @Override
    public String toString() {
        return com.google.android.gms.games.leaderboard.d.b(this);
    }
}
