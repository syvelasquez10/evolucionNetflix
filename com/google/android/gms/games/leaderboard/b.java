// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.leaderboard;

import java.util.ArrayList;
import android.net.Uri;
import android.database.CharArrayBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.Game;

public final class b extends com.google.android.gms.common.data.b implements Leaderboard
{
    private final Game vG;
    private final int vH;
    
    b(final DataHolder dataHolder, final int n, final int vh) {
        super(dataHolder, n);
        this.vH = vh;
        this.vG = new com.google.android.gms.games.b(dataHolder, n);
    }
    
    public Leaderboard dp() {
        return new a(this);
    }
    
    @Override
    public boolean equals(final Object o) {
        return a.a(this, o);
    }
    
    @Override
    public String getDisplayName() {
        return this.getString("name");
    }
    
    @Override
    public void getDisplayName(final CharArrayBuffer charArrayBuffer) {
        this.a("name", charArrayBuffer);
    }
    
    @Override
    public Game getGame() {
        return this.vG;
    }
    
    @Override
    public Uri getIconImageUri() {
        return this.L("board_icon_image_uri");
    }
    
    @Override
    public String getLeaderboardId() {
        return this.getString("external_leaderboard_id");
    }
    
    @Override
    public int getScoreOrder() {
        return this.getInteger("score_order");
    }
    
    @Override
    public ArrayList<LeaderboardVariant> getVariants() {
        final ArrayList<g> list = (ArrayList<g>)new ArrayList<LeaderboardVariant>(this.vH);
        for (int i = 0; i < this.vH; ++i) {
            list.add(new g(this.nE, this.nG + i));
        }
        return (ArrayList<LeaderboardVariant>)list;
    }
    
    @Override
    public int hashCode() {
        return a.a(this);
    }
    
    @Override
    public String toString() {
        return a.b(this);
    }
}
