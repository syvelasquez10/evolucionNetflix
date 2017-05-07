// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.leaderboard;

import java.io.Serializable;
import com.google.android.gms.common.internal.m$a;
import com.google.android.gms.games.internal.constants.LeaderboardCollection;
import com.google.android.gms.games.internal.constants.TimeSpan;
import com.google.android.gms.common.internal.m;

public final class LeaderboardVariantEntity implements LeaderboardVariant
{
    private final int abB;
    private final int abC;
    private final boolean abD;
    private final long abE;
    private final String abF;
    private final long abG;
    private final String abH;
    private final String abI;
    private final long abJ;
    private final String abK;
    private final String abL;
    private final String abM;
    
    public LeaderboardVariantEntity(final LeaderboardVariant leaderboardVariant) {
        this.abB = leaderboardVariant.getTimeSpan();
        this.abC = leaderboardVariant.getCollection();
        this.abD = leaderboardVariant.hasPlayerInfo();
        this.abE = leaderboardVariant.getRawPlayerScore();
        this.abF = leaderboardVariant.getDisplayPlayerScore();
        this.abG = leaderboardVariant.getPlayerRank();
        this.abH = leaderboardVariant.getDisplayPlayerRank();
        this.abI = leaderboardVariant.getPlayerScoreTag();
        this.abJ = leaderboardVariant.getNumScores();
        this.abK = leaderboardVariant.lB();
        this.abL = leaderboardVariant.lC();
        this.abM = leaderboardVariant.lD();
    }
    
    static int a(final LeaderboardVariant leaderboardVariant) {
        return m.hashCode(leaderboardVariant.getTimeSpan(), leaderboardVariant.getCollection(), leaderboardVariant.hasPlayerInfo(), leaderboardVariant.getRawPlayerScore(), leaderboardVariant.getDisplayPlayerScore(), leaderboardVariant.getPlayerRank(), leaderboardVariant.getDisplayPlayerRank(), leaderboardVariant.getNumScores(), leaderboardVariant.lB(), leaderboardVariant.lD(), leaderboardVariant.lC());
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
                if (m.equal(leaderboardVariant2.getTimeSpan(), leaderboardVariant.getTimeSpan()) && m.equal(leaderboardVariant2.getCollection(), leaderboardVariant.getCollection()) && m.equal(leaderboardVariant2.hasPlayerInfo(), leaderboardVariant.hasPlayerInfo()) && m.equal(leaderboardVariant2.getRawPlayerScore(), leaderboardVariant.getRawPlayerScore()) && m.equal(leaderboardVariant2.getDisplayPlayerScore(), leaderboardVariant.getDisplayPlayerScore()) && m.equal(leaderboardVariant2.getPlayerRank(), leaderboardVariant.getPlayerRank()) && m.equal(leaderboardVariant2.getDisplayPlayerRank(), leaderboardVariant.getDisplayPlayerRank()) && m.equal(leaderboardVariant2.getNumScores(), leaderboardVariant.getNumScores()) && m.equal(leaderboardVariant2.lB(), leaderboardVariant.lB()) && m.equal(leaderboardVariant2.lD(), leaderboardVariant.lD())) {
                    b2 = b;
                    if (m.equal(leaderboardVariant2.lC(), leaderboardVariant.lC())) {
                        return b2;
                    }
                }
                return false;
            }
        }
        return b2;
    }
    
    static String b(final LeaderboardVariant leaderboardVariant) {
        final m$a a = m.h(leaderboardVariant).a("TimeSpan", TimeSpan.dH(leaderboardVariant.getTimeSpan())).a("Collection", LeaderboardCollection.dH(leaderboardVariant.getCollection()));
        Serializable value;
        if (leaderboardVariant.hasPlayerInfo()) {
            value = leaderboardVariant.getRawPlayerScore();
        }
        else {
            value = "none";
        }
        final m$a a2 = a.a("RawPlayerScore", value);
        String displayPlayerScore;
        if (leaderboardVariant.hasPlayerInfo()) {
            displayPlayerScore = leaderboardVariant.getDisplayPlayerScore();
        }
        else {
            displayPlayerScore = "none";
        }
        final m$a a3 = a2.a("DisplayPlayerScore", displayPlayerScore);
        Serializable value2;
        if (leaderboardVariant.hasPlayerInfo()) {
            value2 = leaderboardVariant.getPlayerRank();
        }
        else {
            value2 = "none";
        }
        final m$a a4 = a3.a("PlayerRank", value2);
        String displayPlayerRank;
        if (leaderboardVariant.hasPlayerInfo()) {
            displayPlayerRank = leaderboardVariant.getDisplayPlayerRank();
        }
        else {
            displayPlayerRank = "none";
        }
        return a4.a("DisplayPlayerRank", displayPlayerRank).a("NumScores", leaderboardVariant.getNumScores()).a("TopPageNextToken", leaderboardVariant.lB()).a("WindowPageNextToken", leaderboardVariant.lD()).a("WindowPagePrevToken", leaderboardVariant.lC()).toString();
    }
    
    @Override
    public boolean equals(final Object o) {
        return a(this, o);
    }
    
    @Override
    public int getCollection() {
        return this.abC;
    }
    
    @Override
    public String getDisplayPlayerRank() {
        return this.abH;
    }
    
    @Override
    public String getDisplayPlayerScore() {
        return this.abF;
    }
    
    @Override
    public long getNumScores() {
        return this.abJ;
    }
    
    @Override
    public long getPlayerRank() {
        return this.abG;
    }
    
    @Override
    public String getPlayerScoreTag() {
        return this.abI;
    }
    
    @Override
    public long getRawPlayerScore() {
        return this.abE;
    }
    
    @Override
    public int getTimeSpan() {
        return this.abB;
    }
    
    @Override
    public boolean hasPlayerInfo() {
        return this.abD;
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
    public String lB() {
        return this.abK;
    }
    
    @Override
    public String lC() {
        return this.abL;
    }
    
    @Override
    public String lD() {
        return this.abM;
    }
    
    public LeaderboardVariant lE() {
        return this;
    }
    
    @Override
    public String toString() {
        return b(this);
    }
}
