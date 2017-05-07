// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.leaderboard;

import java.util.Collection;
import com.google.android.gms.internal.gm;
import android.database.CharArrayBuffer;
import com.google.android.gms.internal.fo;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.Game;
import java.util.ArrayList;
import android.net.Uri;

public final class LeaderboardEntity implements Leaderboard
{
    private final String HA;
    private final Uri HF;
    private final String HQ;
    private final String LP;
    private final int LQ;
    private final ArrayList<LeaderboardVariantEntity> LR;
    private final Game LS;
    
    public LeaderboardEntity(final Leaderboard leaderboard) {
        this.LP = leaderboard.getLeaderboardId();
        this.HA = leaderboard.getDisplayName();
        this.HF = leaderboard.getIconImageUri();
        this.HQ = leaderboard.getIconImageUrl();
        this.LQ = leaderboard.getScoreOrder();
        final Game game = leaderboard.getGame();
        Game ls;
        if (game == null) {
            ls = null;
        }
        else {
            ls = new GameEntity(game);
        }
        this.LS = ls;
        final ArrayList<LeaderboardVariant> variants = leaderboard.getVariants();
        final int size = variants.size();
        this.LR = new ArrayList<LeaderboardVariantEntity>(size);
        for (int i = 0; i < size; ++i) {
            this.LR.add((LeaderboardVariantEntity)variants.get(i).freeze());
        }
    }
    
    static int a(final Leaderboard leaderboard) {
        return fo.hashCode(leaderboard.getLeaderboardId(), leaderboard.getDisplayName(), leaderboard.getIconImageUri(), leaderboard.getScoreOrder(), leaderboard.getVariants());
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
                if (fo.equal(leaderboard2.getLeaderboardId(), leaderboard.getLeaderboardId()) && fo.equal(leaderboard2.getDisplayName(), leaderboard.getDisplayName()) && fo.equal(leaderboard2.getIconImageUri(), leaderboard.getIconImageUri()) && fo.equal(leaderboard2.getScoreOrder(), leaderboard.getScoreOrder())) {
                    b2 = b;
                    if (fo.equal(leaderboard2.getVariants(), leaderboard.getVariants())) {
                        return b2;
                    }
                }
                return false;
            }
        }
        return b2;
    }
    
    static String b(final Leaderboard leaderboard) {
        return fo.e(leaderboard).a("LeaderboardId", leaderboard.getLeaderboardId()).a("DisplayName", leaderboard.getDisplayName()).a("IconImageUri", leaderboard.getIconImageUri()).a("IconImageUrl", leaderboard.getIconImageUrl()).a("ScoreOrder", leaderboard.getScoreOrder()).a("Variants", leaderboard.getVariants()).toString();
    }
    
    @Override
    public boolean equals(final Object o) {
        return a(this, o);
    }
    
    @Override
    public String getDisplayName() {
        return this.HA;
    }
    
    @Override
    public void getDisplayName(final CharArrayBuffer charArrayBuffer) {
        gm.b(this.HA, charArrayBuffer);
    }
    
    @Override
    public Game getGame() {
        return this.LS;
    }
    
    @Override
    public Uri getIconImageUri() {
        return this.HF;
    }
    
    @Override
    public String getIconImageUrl() {
        return this.HQ;
    }
    
    @Override
    public String getLeaderboardId() {
        return this.LP;
    }
    
    @Override
    public int getScoreOrder() {
        return this.LQ;
    }
    
    @Override
    public ArrayList<LeaderboardVariant> getVariants() {
        return new ArrayList<LeaderboardVariant>(this.LR);
    }
    
    public Leaderboard hC() {
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
