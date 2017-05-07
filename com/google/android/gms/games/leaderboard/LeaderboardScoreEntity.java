// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.leaderboard;

import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.internal.jv;
import android.database.CharArrayBuffer;
import com.google.android.gms.common.internal.m;
import com.google.android.gms.games.Player;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.games.PlayerEntity;
import android.net.Uri;

public final class LeaderboardScoreEntity implements LeaderboardScore
{
    private final long abo;
    private final String abp;
    private final String abq;
    private final long abr;
    private final long abs;
    private final String abt;
    private final Uri abu;
    private final Uri abv;
    private final PlayerEntity abw;
    private final String abx;
    private final String aby;
    private final String abz;
    
    public LeaderboardScoreEntity(final LeaderboardScore leaderboardScore) {
        this.abo = leaderboardScore.getRank();
        this.abp = n.i(leaderboardScore.getDisplayRank());
        this.abq = n.i(leaderboardScore.getDisplayScore());
        this.abr = leaderboardScore.getRawScore();
        this.abs = leaderboardScore.getTimestampMillis();
        this.abt = leaderboardScore.getScoreHolderDisplayName();
        this.abu = leaderboardScore.getScoreHolderIconImageUri();
        this.abv = leaderboardScore.getScoreHolderHiResImageUri();
        final Player scoreHolder = leaderboardScore.getScoreHolder();
        PlayerEntity abw;
        if (scoreHolder == null) {
            abw = null;
        }
        else {
            abw = ((Freezable<PlayerEntity>)scoreHolder).freeze();
        }
        this.abw = abw;
        this.abx = leaderboardScore.getScoreTag();
        this.aby = leaderboardScore.getScoreHolderIconImageUrl();
        this.abz = leaderboardScore.getScoreHolderHiResImageUrl();
    }
    
    static int a(final LeaderboardScore leaderboardScore) {
        return m.hashCode(leaderboardScore.getRank(), leaderboardScore.getDisplayRank(), leaderboardScore.getRawScore(), leaderboardScore.getDisplayScore(), leaderboardScore.getTimestampMillis(), leaderboardScore.getScoreHolderDisplayName(), leaderboardScore.getScoreHolderIconImageUri(), leaderboardScore.getScoreHolderHiResImageUri(), leaderboardScore.getScoreHolder());
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
                if (m.equal(leaderboardScore2.getRank(), leaderboardScore.getRank()) && m.equal(leaderboardScore2.getDisplayRank(), leaderboardScore.getDisplayRank()) && m.equal(leaderboardScore2.getRawScore(), leaderboardScore.getRawScore()) && m.equal(leaderboardScore2.getDisplayScore(), leaderboardScore.getDisplayScore()) && m.equal(leaderboardScore2.getTimestampMillis(), leaderboardScore.getTimestampMillis()) && m.equal(leaderboardScore2.getScoreHolderDisplayName(), leaderboardScore.getScoreHolderDisplayName()) && m.equal(leaderboardScore2.getScoreHolderIconImageUri(), leaderboardScore.getScoreHolderIconImageUri()) && m.equal(leaderboardScore2.getScoreHolderHiResImageUri(), leaderboardScore.getScoreHolderHiResImageUri()) && m.equal(leaderboardScore2.getScoreHolder(), leaderboardScore.getScoreHolder())) {
                    b2 = b;
                    if (m.equal(leaderboardScore2.getScoreTag(), leaderboardScore.getScoreTag())) {
                        return b2;
                    }
                }
                return false;
            }
        }
        return b2;
    }
    
    static String b(final LeaderboardScore leaderboardScore) {
        final m.a a = m.h(leaderboardScore).a("Rank", leaderboardScore.getRank()).a("DisplayRank", leaderboardScore.getDisplayRank()).a("Score", leaderboardScore.getRawScore()).a("DisplayScore", leaderboardScore.getDisplayScore()).a("Timestamp", leaderboardScore.getTimestampMillis()).a("DisplayName", leaderboardScore.getScoreHolderDisplayName()).a("IconImageUri", leaderboardScore.getScoreHolderIconImageUri()).a("IconImageUrl", leaderboardScore.getScoreHolderIconImageUrl()).a("HiResImageUri", leaderboardScore.getScoreHolderHiResImageUri()).a("HiResImageUrl", leaderboardScore.getScoreHolderHiResImageUrl());
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
        return this.abp;
    }
    
    @Override
    public void getDisplayRank(final CharArrayBuffer charArrayBuffer) {
        jv.b(this.abp, charArrayBuffer);
    }
    
    @Override
    public String getDisplayScore() {
        return this.abq;
    }
    
    @Override
    public void getDisplayScore(final CharArrayBuffer charArrayBuffer) {
        jv.b(this.abq, charArrayBuffer);
    }
    
    @Override
    public long getRank() {
        return this.abo;
    }
    
    @Override
    public long getRawScore() {
        return this.abr;
    }
    
    @Override
    public Player getScoreHolder() {
        return this.abw;
    }
    
    @Override
    public String getScoreHolderDisplayName() {
        if (this.abw == null) {
            return this.abt;
        }
        return this.abw.getDisplayName();
    }
    
    @Override
    public void getScoreHolderDisplayName(final CharArrayBuffer charArrayBuffer) {
        if (this.abw == null) {
            jv.b(this.abt, charArrayBuffer);
            return;
        }
        this.abw.getDisplayName(charArrayBuffer);
    }
    
    @Override
    public Uri getScoreHolderHiResImageUri() {
        if (this.abw == null) {
            return this.abv;
        }
        return this.abw.getHiResImageUri();
    }
    
    @Override
    public String getScoreHolderHiResImageUrl() {
        if (this.abw == null) {
            return this.abz;
        }
        return this.abw.getHiResImageUrl();
    }
    
    @Override
    public Uri getScoreHolderIconImageUri() {
        if (this.abw == null) {
            return this.abu;
        }
        return this.abw.getIconImageUri();
    }
    
    @Override
    public String getScoreHolderIconImageUrl() {
        if (this.abw == null) {
            return this.aby;
        }
        return this.abw.getIconImageUrl();
    }
    
    @Override
    public String getScoreTag() {
        return this.abx;
    }
    
    @Override
    public long getTimestampMillis() {
        return this.abs;
    }
    
    @Override
    public int hashCode() {
        return a(this);
    }
    
    @Override
    public boolean isDataValid() {
        return true;
    }
    
    public LeaderboardScore lA() {
        return this;
    }
    
    @Override
    public String toString() {
        return b(this);
    }
}
