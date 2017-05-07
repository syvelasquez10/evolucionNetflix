// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.leaderboard;

import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.internal.fc;
import android.database.CharArrayBuffer;
import com.google.android.gms.internal.ee;
import com.google.android.gms.games.Player;
import com.google.android.gms.internal.eg;
import com.google.android.gms.games.PlayerEntity;
import android.net.Uri;

public final class d implements LeaderboardScore
{
    private final long vK;
    private final String vL;
    private final String vM;
    private final long vN;
    private final long vO;
    private final String vP;
    private final Uri vQ;
    private final Uri vR;
    private final PlayerEntity vS;
    private final String vT;
    
    public d(final LeaderboardScore leaderboardScore) {
        this.vK = leaderboardScore.getRank();
        this.vL = eg.f(leaderboardScore.getDisplayRank());
        this.vM = eg.f(leaderboardScore.getDisplayScore());
        this.vN = leaderboardScore.getRawScore();
        this.vO = leaderboardScore.getTimestampMillis();
        this.vP = leaderboardScore.getScoreHolderDisplayName();
        this.vQ = leaderboardScore.getScoreHolderIconImageUri();
        this.vR = leaderboardScore.getScoreHolderHiResImageUri();
        final Player scoreHolder = leaderboardScore.getScoreHolder();
        PlayerEntity vs;
        if (scoreHolder == null) {
            vs = null;
        }
        else {
            vs = ((Freezable<PlayerEntity>)scoreHolder).freeze();
        }
        this.vS = vs;
        this.vT = leaderboardScore.getScoreTag();
    }
    
    static int a(final LeaderboardScore leaderboardScore) {
        return ee.hashCode(leaderboardScore.getRank(), leaderboardScore.getDisplayRank(), leaderboardScore.getRawScore(), leaderboardScore.getDisplayScore(), leaderboardScore.getTimestampMillis(), leaderboardScore.getScoreHolderDisplayName(), leaderboardScore.getScoreHolderIconImageUri(), leaderboardScore.getScoreHolderHiResImageUri(), leaderboardScore.getScoreHolder());
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
                if (ee.equal(leaderboardScore2.getRank(), leaderboardScore.getRank()) && ee.equal(leaderboardScore2.getDisplayRank(), leaderboardScore.getDisplayRank()) && ee.equal(leaderboardScore2.getRawScore(), leaderboardScore.getRawScore()) && ee.equal(leaderboardScore2.getDisplayScore(), leaderboardScore.getDisplayScore()) && ee.equal(leaderboardScore2.getTimestampMillis(), leaderboardScore.getTimestampMillis()) && ee.equal(leaderboardScore2.getScoreHolderDisplayName(), leaderboardScore.getScoreHolderDisplayName()) && ee.equal(leaderboardScore2.getScoreHolderIconImageUri(), leaderboardScore.getScoreHolderIconImageUri()) && ee.equal(leaderboardScore2.getScoreHolderHiResImageUri(), leaderboardScore.getScoreHolderHiResImageUri()) && ee.equal(leaderboardScore2.getScoreHolder(), leaderboardScore.getScoreHolder())) {
                    b2 = b;
                    if (ee.equal(leaderboardScore2.getScoreTag(), leaderboardScore.getScoreTag())) {
                        return b2;
                    }
                }
                return false;
            }
        }
        return b2;
    }
    
    static String b(final LeaderboardScore leaderboardScore) {
        final ee.a a = ee.e(leaderboardScore).a("Rank", leaderboardScore.getRank()).a("DisplayRank", leaderboardScore.getDisplayRank()).a("Score", leaderboardScore.getRawScore()).a("DisplayScore", leaderboardScore.getDisplayScore()).a("Timestamp", leaderboardScore.getTimestampMillis()).a("DisplayName", leaderboardScore.getScoreHolderDisplayName()).a("IconImageUri", leaderboardScore.getScoreHolderIconImageUri()).a("HiResImageUri", leaderboardScore.getScoreHolderHiResImageUri());
        Object scoreHolder;
        if (leaderboardScore.getScoreHolder() == null) {
            scoreHolder = null;
        }
        else {
            scoreHolder = leaderboardScore.getScoreHolder();
        }
        return a.a("Player", scoreHolder).a("ScoreTag", leaderboardScore.getScoreTag()).toString();
    }
    
    public LeaderboardScore ds() {
        return this;
    }
    
    @Override
    public boolean equals(final Object o) {
        return a(this, o);
    }
    
    @Override
    public String getDisplayRank() {
        return this.vL;
    }
    
    @Override
    public void getDisplayRank(final CharArrayBuffer charArrayBuffer) {
        fc.b(this.vL, charArrayBuffer);
    }
    
    @Override
    public String getDisplayScore() {
        return this.vM;
    }
    
    @Override
    public void getDisplayScore(final CharArrayBuffer charArrayBuffer) {
        fc.b(this.vM, charArrayBuffer);
    }
    
    @Override
    public long getRank() {
        return this.vK;
    }
    
    @Override
    public long getRawScore() {
        return this.vN;
    }
    
    @Override
    public Player getScoreHolder() {
        return this.vS;
    }
    
    @Override
    public String getScoreHolderDisplayName() {
        if (this.vS == null) {
            return this.vP;
        }
        return this.vS.getDisplayName();
    }
    
    @Override
    public void getScoreHolderDisplayName(final CharArrayBuffer charArrayBuffer) {
        if (this.vS == null) {
            fc.b(this.vP, charArrayBuffer);
            return;
        }
        this.vS.getDisplayName(charArrayBuffer);
    }
    
    @Override
    public Uri getScoreHolderHiResImageUri() {
        if (this.vS == null) {
            return this.vR;
        }
        return this.vS.getHiResImageUri();
    }
    
    @Override
    public Uri getScoreHolderIconImageUri() {
        if (this.vS == null) {
            return this.vQ;
        }
        return this.vS.getIconImageUri();
    }
    
    @Override
    public String getScoreTag() {
        return this.vT;
    }
    
    @Override
    public long getTimestampMillis() {
        return this.vO;
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
