// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.leaderboard;

import java.util.Collection;
import com.google.android.gms.internal.jv;
import android.database.CharArrayBuffer;
import com.google.android.gms.common.internal.m;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.Game;
import java.util.ArrayList;
import android.net.Uri;

public final class LeaderboardEntity implements Leaderboard
{
    private final String Nz;
    private final Uri UW;
    private final String Vh;
    private final String abj;
    private final int abk;
    private final ArrayList<LeaderboardVariantEntity> abl;
    private final Game abm;
    
    public LeaderboardEntity(final Leaderboard leaderboard) {
        this.abj = leaderboard.getLeaderboardId();
        this.Nz = leaderboard.getDisplayName();
        this.UW = leaderboard.getIconImageUri();
        this.Vh = leaderboard.getIconImageUrl();
        this.abk = leaderboard.getScoreOrder();
        final Game game = leaderboard.getGame();
        Game abm;
        if (game == null) {
            abm = null;
        }
        else {
            abm = new GameEntity(game);
        }
        this.abm = abm;
        final ArrayList<LeaderboardVariant> variants = leaderboard.getVariants();
        final int size = variants.size();
        this.abl = new ArrayList<LeaderboardVariantEntity>(size);
        for (int i = 0; i < size; ++i) {
            this.abl.add((LeaderboardVariantEntity)variants.get(i).freeze());
        }
    }
    
    static int a(final Leaderboard leaderboard) {
        return m.hashCode(leaderboard.getLeaderboardId(), leaderboard.getDisplayName(), leaderboard.getIconImageUri(), leaderboard.getScoreOrder(), leaderboard.getVariants());
    }
    
    static boolean a(final Leaderboard leaderboard, final Object o) {
        final boolean b = true;
        boolean b2;
        if (!(o instanceof Leaderboard)) {
            b2 = false;
        }
        else {
            b2 = b;
            if (leaderboard != o) {
                final Leaderboard leaderboard2 = (Leaderboard)o;
                if (m.equal(leaderboard2.getLeaderboardId(), leaderboard.getLeaderboardId()) && m.equal(leaderboard2.getDisplayName(), leaderboard.getDisplayName()) && m.equal(leaderboard2.getIconImageUri(), leaderboard.getIconImageUri()) && m.equal(leaderboard2.getScoreOrder(), leaderboard.getScoreOrder())) {
                    b2 = b;
                    if (m.equal(leaderboard2.getVariants(), leaderboard.getVariants())) {
                        return b2;
                    }
                }
                return false;
            }
        }
        return b2;
    }
    
    static String b(final Leaderboard leaderboard) {
        return m.h(leaderboard).a("LeaderboardId", leaderboard.getLeaderboardId()).a("DisplayName", leaderboard.getDisplayName()).a("IconImageUri", leaderboard.getIconImageUri()).a("IconImageUrl", leaderboard.getIconImageUrl()).a("ScoreOrder", leaderboard.getScoreOrder()).a("Variants", leaderboard.getVariants()).toString();
    }
    
    @Override
    public boolean equals(final Object o) {
        return a(this, o);
    }
    
    @Override
    public String getDisplayName() {
        return this.Nz;
    }
    
    @Override
    public void getDisplayName(final CharArrayBuffer charArrayBuffer) {
        jv.b(this.Nz, charArrayBuffer);
    }
    
    @Override
    public Game getGame() {
        return this.abm;
    }
    
    @Override
    public Uri getIconImageUri() {
        return this.UW;
    }
    
    @Override
    public String getIconImageUrl() {
        return this.Vh;
    }
    
    @Override
    public String getLeaderboardId() {
        return this.abj;
    }
    
    @Override
    public int getScoreOrder() {
        return this.abk;
    }
    
    @Override
    public ArrayList<LeaderboardVariant> getVariants() {
        return new ArrayList<LeaderboardVariant>(this.abl);
    }
    
    @Override
    public int hashCode() {
        return a(this);
    }
    
    @Override
    public boolean isDataValid() {
        return true;
    }
    
    public Leaderboard lx() {
        return this;
    }
    
    @Override
    public String toString() {
        return b(this);
    }
}
