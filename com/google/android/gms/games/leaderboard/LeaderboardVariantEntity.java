// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.leaderboard;

import java.io.Serializable;
import com.google.android.gms.games.internal.constants.LeaderboardCollection;
import com.google.android.gms.games.internal.constants.TimeSpan;
import com.google.android.gms.internal.fo;

public final class LeaderboardVariantEntity implements LeaderboardVariant
{
    private final int Mh;
    private final int Mi;
    private final boolean Mj;
    private final long Mk;
    private final String Ml;
    private final long Mm;
    private final String Mn;
    private final String Mo;
    private final long Mp;
    private final String Mq;
    private final String Mr;
    private final String Ms;
    
    public LeaderboardVariantEntity(final LeaderboardVariant leaderboardVariant) {
        this.Mh = leaderboardVariant.getTimeSpan();
        this.Mi = leaderboardVariant.getCollection();
        this.Mj = leaderboardVariant.hasPlayerInfo();
        this.Mk = leaderboardVariant.getRawPlayerScore();
        this.Ml = leaderboardVariant.getDisplayPlayerScore();
        this.Mm = leaderboardVariant.getPlayerRank();
        this.Mn = leaderboardVariant.getDisplayPlayerRank();
        this.Mo = leaderboardVariant.getPlayerScoreTag();
        this.Mp = leaderboardVariant.getNumScores();
        this.Mq = leaderboardVariant.hG();
        this.Mr = leaderboardVariant.hH();
        this.Ms = leaderboardVariant.hI();
    }
    
    static int a(final LeaderboardVariant leaderboardVariant) {
        return fo.hashCode(leaderboardVariant.getTimeSpan(), leaderboardVariant.getCollection(), leaderboardVariant.hasPlayerInfo(), leaderboardVariant.getRawPlayerScore(), leaderboardVariant.getDisplayPlayerScore(), leaderboardVariant.getPlayerRank(), leaderboardVariant.getDisplayPlayerRank(), leaderboardVariant.getNumScores(), leaderboardVariant.hG(), leaderboardVariant.hI(), leaderboardVariant.hH());
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
                if (fo.equal(leaderboardVariant2.getTimeSpan(), leaderboardVariant.getTimeSpan()) && fo.equal(leaderboardVariant2.getCollection(), leaderboardVariant.getCollection()) && fo.equal(leaderboardVariant2.hasPlayerInfo(), leaderboardVariant.hasPlayerInfo()) && fo.equal(leaderboardVariant2.getRawPlayerScore(), leaderboardVariant.getRawPlayerScore()) && fo.equal(leaderboardVariant2.getDisplayPlayerScore(), leaderboardVariant.getDisplayPlayerScore()) && fo.equal(leaderboardVariant2.getPlayerRank(), leaderboardVariant.getPlayerRank()) && fo.equal(leaderboardVariant2.getDisplayPlayerRank(), leaderboardVariant.getDisplayPlayerRank()) && fo.equal(leaderboardVariant2.getNumScores(), leaderboardVariant.getNumScores()) && fo.equal(leaderboardVariant2.hG(), leaderboardVariant.hG()) && fo.equal(leaderboardVariant2.hI(), leaderboardVariant.hI())) {
                    b2 = b;
                    if (fo.equal(leaderboardVariant2.hH(), leaderboardVariant.hH())) {
                        return b2;
                    }
                }
                return false;
            }
        }
        return b2;
    }
    
    static String b(final LeaderboardVariant leaderboardVariant) {
        final fo.a a = fo.e(leaderboardVariant).a("TimeSpan", TimeSpan.bd(leaderboardVariant.getTimeSpan())).a("Collection", LeaderboardCollection.bd(leaderboardVariant.getCollection()));
        Serializable value;
        if (leaderboardVariant.hasPlayerInfo()) {
            value = leaderboardVariant.getRawPlayerScore();
        }
        else {
            value = "none";
        }
        final fo.a a2 = a.a("RawPlayerScore", value);
        String displayPlayerScore;
        if (leaderboardVariant.hasPlayerInfo()) {
            displayPlayerScore = leaderboardVariant.getDisplayPlayerScore();
        }
        else {
            displayPlayerScore = "none";
        }
        final fo.a a3 = a2.a("DisplayPlayerScore", displayPlayerScore);
        Serializable value2;
        if (leaderboardVariant.hasPlayerInfo()) {
            value2 = leaderboardVariant.getPlayerRank();
        }
        else {
            value2 = "none";
        }
        final fo.a a4 = a3.a("PlayerRank", value2);
        String displayPlayerRank;
        if (leaderboardVariant.hasPlayerInfo()) {
            displayPlayerRank = leaderboardVariant.getDisplayPlayerRank();
        }
        else {
            displayPlayerRank = "none";
        }
        return a4.a("DisplayPlayerRank", displayPlayerRank).a("NumScores", leaderboardVariant.getNumScores()).a("TopPageNextToken", leaderboardVariant.hG()).a("WindowPageNextToken", leaderboardVariant.hI()).a("WindowPagePrevToken", leaderboardVariant.hH()).toString();
    }
    
    @Override
    public boolean equals(final Object o) {
        return a(this, o);
    }
    
    @Override
    public int getCollection() {
        return this.Mi;
    }
    
    @Override
    public String getDisplayPlayerRank() {
        return this.Mn;
    }
    
    @Override
    public String getDisplayPlayerScore() {
        return this.Ml;
    }
    
    @Override
    public long getNumScores() {
        return this.Mp;
    }
    
    @Override
    public long getPlayerRank() {
        return this.Mm;
    }
    
    @Override
    public String getPlayerScoreTag() {
        return this.Mo;
    }
    
    @Override
    public long getRawPlayerScore() {
        return this.Mk;
    }
    
    @Override
    public int getTimeSpan() {
        return this.Mh;
    }
    
    @Override
    public String hG() {
        return this.Mq;
    }
    
    @Override
    public String hH() {
        return this.Mr;
    }
    
    @Override
    public String hI() {
        return this.Ms;
    }
    
    public LeaderboardVariant hJ() {
        return this;
    }
    
    @Override
    public boolean hasPlayerInfo() {
        return this.Mj;
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
