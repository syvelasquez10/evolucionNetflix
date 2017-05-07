// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.leaderboard;

import java.util.ArrayList;
import android.net.Uri;
import android.database.CharArrayBuffer;
import com.google.android.gms.games.GameRef;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.Game;
import com.google.android.gms.common.data.b;

public final class LeaderboardRef extends b implements Leaderboard
{
    private final int LE;
    private final Game LS;
    
    LeaderboardRef(final DataHolder dataHolder, final int n, final int le) {
        super(dataHolder, n);
        this.LE = le;
        this.LS = new GameRef(dataHolder, n);
    }
    
    @Override
    public boolean equals(final Object o) {
        return LeaderboardEntity.a(this, o);
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
        return this.LS;
    }
    
    @Override
    public Uri getIconImageUri() {
        return this.ah("board_icon_image_uri");
    }
    
    @Override
    public String getIconImageUrl() {
        return this.getString("board_icon_image_url");
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
        final ArrayList<LeaderboardVariantRef> list = (ArrayList<LeaderboardVariantRef>)new ArrayList<LeaderboardVariant>(this.LE);
        for (int i = 0; i < this.LE; ++i) {
            list.add(new LeaderboardVariantRef(this.BB, this.BD + i));
        }
        return (ArrayList<LeaderboardVariant>)list;
    }
    
    public Leaderboard hC() {
        return new LeaderboardEntity(this);
    }
    
    @Override
    public int hashCode() {
        return LeaderboardEntity.a(this);
    }
    
    @Override
    public String toString() {
        return LeaderboardEntity.b(this);
    }
}
