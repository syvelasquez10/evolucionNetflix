// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.leaderboard;

import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.internal.gm;
import android.database.CharArrayBuffer;
import com.google.android.gms.internal.fo;
import com.google.android.gms.games.Player;
import com.google.android.gms.internal.fq;
import com.google.android.gms.games.PlayerEntity;
import android.net.Uri;

public final class LeaderboardScoreEntity implements LeaderboardScore
{
    private final long LU;
    private final String LV;
    private final String LW;
    private final long LX;
    private final long LY;
    private final String LZ;
    private final Uri Ma;
    private final Uri Mb;
    private final PlayerEntity Mc;
    private final String Md;
    private final String Me;
    private final String Mf;
    
    public LeaderboardScoreEntity(final LeaderboardScore leaderboardScore) {
        this.LU = leaderboardScore.getRank();
        this.LV = fq.f(leaderboardScore.getDisplayRank());
        this.LW = fq.f(leaderboardScore.getDisplayScore());
        this.LX = leaderboardScore.getRawScore();
        this.LY = leaderboardScore.getTimestampMillis();
        this.LZ = leaderboardScore.getScoreHolderDisplayName();
        this.Ma = leaderboardScore.getScoreHolderIconImageUri();
        this.Mb = leaderboardScore.getScoreHolderHiResImageUri();
        final Player scoreHolder = leaderboardScore.getScoreHolder();
        PlayerEntity mc;
        if (scoreHolder == null) {
            mc = null;
        }
        else {
            mc = ((Freezable<PlayerEntity>)scoreHolder).freeze();
        }
        this.Mc = mc;
        this.Md = leaderboardScore.getScoreTag();
        this.Me = leaderboardScore.getScoreHolderIconImageUrl();
        this.Mf = leaderboardScore.getScoreHolderHiResImageUrl();
    }
    
    static int a(final LeaderboardScore leaderboardScore) {
        return fo.hashCode(leaderboardScore.getRank(), leaderboardScore.getDisplayRank(), leaderboardScore.getRawScore(), leaderboardScore.getDisplayScore(), leaderboardScore.getTimestampMillis(), leaderboardScore.getScoreHolderDisplayName(), leaderboardScore.getScoreHolderIconImageUri(), leaderboardScore.getScoreHolderHiResImageUri(), leaderboardScore.getScoreHolder());
    }
    
    static boolean a(final LeaderboardScore leaderboardScore, final Object o) {
        final boolean b = true;
        boolean b2;
        if (!(o instanceof LeaderboardScore)) {
            b2 = false;
        }
        else {
            b2 = b;
            if (leaderboardScore != o) {
                final LeaderboardScore leaderboardScore2 = (LeaderboardScore)o;
                if (fo.equal(leaderboardScore2.getRank(), leaderboardScore.getRank()) && fo.equal(leaderboardScore2.getDisplayRank(), leaderboardScore.getDisplayRank()) && fo.equal(leaderboardScore2.getRawScore(), leaderboardScore.getRawScore()) && fo.equal(leaderboardScore2.getDisplayScore(), leaderboardScore.getDisplayScore()) && fo.equal(leaderboardScore2.getTimestampMillis(), leaderboardScore.getTimestampMillis()) && fo.equal(leaderboardScore2.getScoreHolderDisplayName(), leaderboardScore.getScoreHolderDisplayName()) && fo.equal(leaderboardScore2.getScoreHolderIconImageUri(), leaderboardScore.getScoreHolderIconImageUri()) && fo.equal(leaderboardScore2.getScoreHolderHiResImageUri(), leaderboardScore.getScoreHolderHiResImageUri()) && fo.equal(leaderboardScore2.getScoreHolder(), leaderboardScore.getScoreHolder())) {
                    b2 = b;
                    if (fo.equal(leaderboardScore2.getScoreTag(), leaderboardScore.getScoreTag())) {
                        return b2;
                    }
                }
                return false;
            }
        }
        return b2;
    }
    
    static String b(final LeaderboardScore leaderboardScore) {
        final fo.a a = fo.e(leaderboardScore).a("Rank", leaderboardScore.getRank()).a("DisplayRank", leaderboardScore.getDisplayRank()).a("Score", leaderboardScore.getRawScore()).a("DisplayScore", leaderboardScore.getDisplayScore()).a("Timestamp", leaderboardScore.getTimestampMillis()).a("DisplayName", leaderboardScore.getScoreHolderDisplayName()).a("IconImageUri", leaderboardScore.getScoreHolderIconImageUri()).a("IconImageUrl", leaderboardScore.getScoreHolderIconImageUrl()).a("HiResImageUri", leaderboardScore.getScoreHolderHiResImageUri()).a("HiResImageUrl", leaderboardScore.getScoreHolderHiResImageUrl());
        Object scoreHolder;
        if (leaderboardScore.getScoreHolder() == null) {
            scoreHolder = null;
        }
        else {
            scoreHolder = leaderboardScore.getScoreHolder();
        }
        return a.a("Player", scoreHolder).a("ScoreTag", leaderboardScore.getScoreTag()).toString();
    }
    
    @Override
    public boolean equals(final Object o) {
        return a(this, o);
    }
    
    @Override
    public String getDisplayRank() {
        return this.LV;
    }
    
    @Override
    public void getDisplayRank(final CharArrayBuffer charArrayBuffer) {
        gm.b(this.LV, charArrayBuffer);
    }
    
    @Override
    public String getDisplayScore() {
        return this.LW;
    }
    
    @Override
    public void getDisplayScore(final CharArrayBuffer charArrayBuffer) {
        gm.b(this.LW, charArrayBuffer);
    }
    
    @Override
    public long getRank() {
        return this.LU;
    }
    
    @Override
    public long getRawScore() {
        return this.LX;
    }
    
    @Override
    public Player getScoreHolder() {
        return this.Mc;
    }
    
    @Override
    public String getScoreHolderDisplayName() {
        if (this.Mc == null) {
            return this.LZ;
        }
        return this.Mc.getDisplayName();
    }
    
    @Override
    public void getScoreHolderDisplayName(final CharArrayBuffer charArrayBuffer) {
        if (this.Mc == null) {
            gm.b(this.LZ, charArrayBuffer);
            return;
        }
        this.Mc.getDisplayName(charArrayBuffer);
    }
    
    @Override
    public Uri getScoreHolderHiResImageUri() {
        if (this.Mc == null) {
            return this.Mb;
        }
        return this.Mc.getHiResImageUri();
    }
    
    @Override
    public String getScoreHolderHiResImageUrl() {
        if (this.Mc == null) {
            return this.Mf;
        }
        return this.Mc.getHiResImageUrl();
    }
    
    @Override
    public Uri getScoreHolderIconImageUri() {
        if (this.Mc == null) {
            return this.Ma;
        }
        return this.Mc.getIconImageUri();
    }
    
    @Override
    public String getScoreHolderIconImageUrl() {
        if (this.Mc == null) {
            return this.Me;
        }
        return this.Mc.getIconImageUrl();
    }
    
    @Override
    public String getScoreTag() {
        return this.Md;
    }
    
    @Override
    public long getTimestampMillis() {
        return this.LY;
    }
    
    public LeaderboardScore hF() {
        return this;
    }
    
    @Override
    public int hashCode() {
        return a(this);
    }
    
    @Override
    public boolean isDataValid() {
        return true;
    }
    
    @Override
    public String toString() {
        return b(this);
    }
}
