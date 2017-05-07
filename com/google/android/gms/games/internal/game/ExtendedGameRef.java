// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.game;

import android.os.Parcel;
import com.google.android.gms.games.Game;
import java.util.ArrayList;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.GameRef;
import com.google.android.gms.common.data.b;

public class ExtendedGameRef extends b implements ExtendedGame
{
    private final GameRef LD;
    private final int LE;
    
    ExtendedGameRef(final DataHolder dataHolder, final int n, final int le) {
        super(dataHolder, n);
        this.LD = new GameRef(dataHolder, n);
        this.LE = le;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return ExtendedGameEntity.a(this, o);
    }
    
    @Override
    public ArrayList<GameBadge> gW() {
        int i = 0;
        if (this.BB.getString("badge_title", this.BD, this.BB.G(this.BD)) == null) {
            return new ArrayList<GameBadge>(0);
        }
        final ArrayList<GameBadgeRef> list = (ArrayList<GameBadgeRef>)new ArrayList<GameBadge>(this.LE);
        while (i < this.LE) {
            list.add(new GameBadgeRef(this.BB, this.BD + i));
            ++i;
        }
        return (ArrayList<GameBadge>)list;
    }
    
    @Override
    public int gX() {
        return this.getInteger("availability");
    }
    
    @Override
    public boolean gY() {
        return this.getBoolean("owned");
    }
    
    @Override
    public int gZ() {
        return this.getInteger("achievement_unlocked_count");
    }
    
    @Override
    public Game getGame() {
        return this.LD;
    }
    
    @Override
    public long ha() {
        return this.getLong("last_played_server_time");
    }
    
    @Override
    public int hashCode() {
        return ExtendedGameEntity.a(this);
    }
    
    @Override
    public long hb() {
        return this.getLong("price_micros");
    }
    
    @Override
    public String hc() {
        return this.getString("formatted_price");
    }
    
    @Override
    public long hd() {
        return this.getLong("full_price_micros");
    }
    
    @Override
    public String he() {
        return this.getString("formatted_full_price");
    }
    
    public ExtendedGame hg() {
        return new ExtendedGameEntity(this);
    }
    
    @Override
    public String toString() {
        return ExtendedGameEntity.b(this);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        ((ExtendedGameEntity)this.hg()).writeToParcel(parcel, n);
    }
}
