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
import com.google.android.gms.common.data.d;

public final class LeaderboardRef extends d implements Leaderboard
{
    private final int aaz;
    private final Game abm;
    
    LeaderboardRef(final DataHolder dataHolder, final int n, final int aaz) {
        super(dataHolder, n);
        this.aaz = aaz;
        this.abm = new GameRef(dataHolder, n);
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
        return this.abm;
    }
    
    @Override
    public Uri getIconImageUri() {
        return this.aR("board_icon_image_uri");
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
        final ArrayList<LeaderboardVariantRef> list = (ArrayList<LeaderboardVariantRef>)new ArrayList<LeaderboardVariant>(this.aaz);
        for (int i = 0; i < this.aaz; ++i) {
            list.add(new LeaderboardVariantRef(this.IC, this.JQ + i));
        }
        return (ArrayList<LeaderboardVariant>)list;
    }
    
    @Override
    public int hashCode() {
        return LeaderboardEntity.a(this);
    }
    
    public Leaderboard lx() {
        return new LeaderboardEntity(this);
    }
    
    @Override
    public String toString() {
        return LeaderboardEntity.b(this);
    }
}
