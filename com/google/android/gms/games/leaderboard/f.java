// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.leaderboard;

import java.io.Serializable;
import com.google.android.gms.internal.gc;
import com.google.android.gms.internal.ge;
import com.google.android.gms.internal.ee;

public final class f implements LeaderboardVariant
{
    private final int vV;
    private final int vW;
    private final boolean vX;
    private final long vY;
    private final String vZ;
    private final long wa;
    private final String wb;
    private final String wc;
    private final long wd;
    private final String we;
    private final String wf;
    private final String wg;
    
    public f(final LeaderboardVariant leaderboardVariant) {
        this.vV = leaderboardVariant.getTimeSpan();
        this.vW = leaderboardVariant.getCollection();
        this.vX = leaderboardVariant.hasPlayerInfo();
        this.vY = leaderboardVariant.getRawPlayerScore();
        this.vZ = leaderboardVariant.getDisplayPlayerScore();
        this.wa = leaderboardVariant.getPlayerRank();
        this.wb = leaderboardVariant.getDisplayPlayerRank();
        this.wc = leaderboardVariant.getPlayerScoreTag();
        this.wd = leaderboardVariant.getNumScores();
        this.we = leaderboardVariant.dt();
        this.wf = leaderboardVariant.du();
        this.wg = leaderboardVariant.dv();
    }
    
    static int a(final LeaderboardVariant leaderboardVariant) {
        return ee.hashCode(leaderboardVariant.getTimeSpan(), leaderboardVariant.getCollection(), leaderboardVariant.hasPlayerInfo(), leaderboardVariant.getRawPlayerScore(), leaderboardVariant.getDisplayPlayerScore(), leaderboardVariant.getPlayerRank(), leaderboardVariant.getDisplayPlayerRank(), leaderboardVariant.getNumScores(), leaderboardVariant.dt(), leaderboardVariant.dv(), leaderboardVariant.du());
    }
    
    static boolean a(final LeaderboardVariant leaderboardVariant, final Object o) {
        final boolean b = true;
        boolean b2;
        if (!(o instanceof LeaderboardVariant)) {
            b2 = false;
        }
        else {
            b2 = b;
            if (leaderboardVariant != o) {
                final LeaderboardVariant leaderboardVariant2 = (LeaderboardVariant)o;
                if (ee.equal(leaderboardVariant2.getTimeSpan(), leaderboardVariant.getTimeSpan()) && ee.equal(leaderboardVariant2.getCollection(), leaderboardVariant.getCollection()) && ee.equal(leaderboardVariant2.hasPlayerInfo(), leaderboardVariant.hasPlayerInfo()) && ee.equal(leaderboardVariant2.getRawPlayerScore(), leaderboardVariant.getRawPlayerScore()) && ee.equal(leaderboardVariant2.getDisplayPlayerScore(), leaderboardVariant.getDisplayPlayerScore()) && ee.equal(leaderboardVariant2.getPlayerRank(), leaderboardVariant.getPlayerRank()) && ee.equal(leaderboardVariant2.getDisplayPlayerRank(), leaderboardVariant.getDisplayPlayerRank()) && ee.equal(leaderboardVariant2.getNumScores(), leaderboardVariant.getNumScores()) && ee.equal(leaderboardVariant2.dt(), leaderboardVariant.dt()) && ee.equal(leaderboardVariant2.dv(), leaderboardVariant.dv())) {
                    b2 = b;
                    if (ee.equal(leaderboardVariant2.du(), leaderboardVariant.du())) {
                        return b2;
                    }
                }
                return false;
            }
        }
        return b2;
    }
    
    static String b(final LeaderboardVariant leaderboardVariant) {
        final ee.a a = ee.e(leaderboardVariant).a("TimeSpan", ge.aG(leaderboardVariant.getTimeSpan())).a("Collection", gc.aG(leaderboardVariant.getCollection()));
        Serializable value;
        if (leaderboardVariant.hasPlayerInfo()) {
            value = leaderboardVariant.getRawPlayerScore();
        }
        else {
            value = "none";
        }
        final ee.a a2 = a.a("RawPlayerScore", value);
        String displayPlayerScore;
        if (leaderboardVariant.hasPlayerInfo()) {
            displayPlayerScore = leaderboardVariant.getDisplayPlayerScore();
        }
        else {
            displayPlayerScore = "none";
        }
        final ee.a a3 = a2.a("DisplayPlayerScore", displayPlayerScore);
        Serializable value2;
        if (leaderboardVariant.hasPlayerInfo()) {
            value2 = leaderboardVariant.getPlayerRank();
        }
        else {
            value2 = "none";
        }
        final ee.a a4 = a3.a("PlayerRank", value2);
        String displayPlayerRank;
        if (leaderboardVariant.hasPlayerInfo()) {
            displayPlayerRank = leaderboardVariant.getDisplayPlayerRank();
        }
        else {
            displayPlayerRank = "none";
        }
        return a4.a("DisplayPlayerRank", displayPlayerRank).a("NumScores", leaderboardVariant.getNumScores()).a("TopPageNextToken", leaderboardVariant.dt()).a("WindowPageNextToken", leaderboardVariant.dv()).a("WindowPagePrevToken", leaderboardVariant.du()).toString();
    }
    
    @Override
    public String dt() {
        return this.we;
    }
    
    @Override
    public String du() {
        return this.wf;
    }
    
    @Override
    public String dv() {
        return this.wg;
    }
    
    public LeaderboardVariant dw() {
        return this;
    }
    
    @Override
    public boolean equals(final Object o) {
        return a(this, o);
    }
    
    @Override
    public int getCollection() {
        return this.vW;
    }
    
    @Override
    public String getDisplayPlayerRank() {
        return this.wb;
    }
    
    @Override
    public String getDisplayPlayerScore() {
        return this.vZ;
    }
    
    @Override
    public long getNumScores() {
        return this.wd;
    }
    
    @Override
    public long getPlayerRank() {
        return this.wa;
    }
    
    @Override
    public String getPlayerScoreTag() {
        return this.wc;
    }
    
    @Override
    public long getRawPlayerScore() {
        return this.vY;
    }
    
    @Override
    public int getTimeSpan() {
        return this.vV;
    }
    
    @Override
    public boolean hasPlayerInfo() {
        return this.vX;
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
