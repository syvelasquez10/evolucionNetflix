// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.leaderboard;

import java.util.Collection;
import com.google.android.gms.internal.fc;
import android.database.CharArrayBuffer;
import com.google.android.gms.internal.ee;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.Game;
import java.util.ArrayList;
import android.net.Uri;

public final class a implements Leaderboard
{
    private final String qa;
    private final Uri sL;
    private final String vD;
    private final int vE;
    private final ArrayList<f> vF;
    private final Game vG;
    
    public a(final Leaderboard leaderboard) {
        this.vD = leaderboard.getLeaderboardId();
        this.qa = leaderboard.getDisplayName();
        this.sL = leaderboard.getIconImageUri();
        this.vE = leaderboard.getScoreOrder();
        final Game game = leaderboard.getGame();
        Game vg;
        if (game == null) {
            vg = null;
        }
        else {
            vg = new GameEntity(game);
        }
        this.vG = vg;
        final ArrayList<LeaderboardVariant> variants = leaderboard.getVariants();
        final int size = variants.size();
        this.vF = new ArrayList<f>(size);
        for (int i = 0; i < size; ++i) {
            this.vF.add((f)variants.get(i).freeze());
        }
    }
    
    static int a(final Leaderboard leaderboard) {
        return ee.hashCode(leaderboard.getLeaderboardId(), leaderboard.getDisplayName(), leaderboard.getIconImageUri(), leaderboard.getScoreOrder(), leaderboard.getVariants());
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
                if (ee.equal(leaderboard2.getLeaderboardId(), leaderboard.getLeaderboardId()) && ee.equal(leaderboard2.getDisplayName(), leaderboard.getDisplayName()) && ee.equal(leaderboard2.getIconImageUri(), leaderboard.getIconImageUri()) && ee.equal(leaderboard2.getScoreOrder(), leaderboard.getScoreOrder())) {
                    b2 = b;
                    if (ee.equal(leaderboard2.getVariants(), leaderboard.getVariants())) {
                        return b2;
                    }
                }
                return false;
            }
        }
        return b2;
    }
    
    static String b(final Leaderboard leaderboard) {
        return ee.e(leaderboard).a("LeaderboardId", leaderboard.getLeaderboardId()).a("DisplayName", leaderboard.getDisplayName()).a("IconImageUri", leaderboard.getIconImageUri()).a("ScoreOrder", leaderboard.getScoreOrder()).a("Variants", leaderboard.getVariants()).toString();
    }
    
    public Leaderboard dp() {
        return this;
    }
    
    @Override
    public boolean equals(final Object o) {
        return a(this, o);
    }
    
    @Override
    public String getDisplayName() {
        return this.qa;
    }
    
    @Override
    public void getDisplayName(final CharArrayBuffer charArrayBuffer) {
        fc.b(this.qa, charArrayBuffer);
    }
    
    @Override
    public Game getGame() {
        return this.vG;
    }
    
    @Override
    public Uri getIconImageUri() {
        return this.sL;
    }
    
    @Override
    public String getLeaderboardId() {
        return this.vD;
    }
    
    @Override
    public int getScoreOrder() {
        return this.vE;
    }
    
    @Override
    public ArrayList<LeaderboardVariant> getVariants() {
        return new ArrayList<LeaderboardVariant>(this.vF);
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
