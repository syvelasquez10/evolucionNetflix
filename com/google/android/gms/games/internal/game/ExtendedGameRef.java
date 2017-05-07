// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.game;

import android.os.Parcel;
import com.google.android.gms.games.snapshot.SnapshotMetadata;
import java.util.ArrayList;
import com.google.android.gms.games.Game;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.snapshot.SnapshotMetadataRef;
import com.google.android.gms.games.GameRef;
import com.google.android.gms.common.data.d;

public class ExtendedGameRef extends d implements ExtendedGame
{
    private final GameRef aam;
    private final SnapshotMetadataRef aay;
    private final int aaz;
    
    ExtendedGameRef(final DataHolder dataHolder, final int n, final int aaz) {
        super(dataHolder, n);
        this.aam = new GameRef(dataHolder, n);
        this.aaz = aaz;
        if (this.aQ("external_snapshot_id") && !this.aS("external_snapshot_id")) {
            this.aay = new SnapshotMetadataRef(dataHolder, n);
            return;
        }
        this.aay = null;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return ExtendedGameEntity.a(this, o);
    }
    
    @Override
    public Game getGame() {
        return this.aam;
    }
    
    @Override
    public int hashCode() {
        return ExtendedGameEntity.a(this);
    }
    
    @Override
    public ArrayList<GameBadge> kO() {
        int i = 0;
        if (this.IC.c("badge_title", this.JQ, this.IC.ar(this.JQ)) == null) {
            return new ArrayList<GameBadge>(0);
        }
        final ArrayList<GameBadgeRef> list = (ArrayList<GameBadgeRef>)new ArrayList<GameBadge>(this.aaz);
        while (i < this.aaz) {
            list.add(new GameBadgeRef(this.IC, this.JQ + i));
            ++i;
        }
        return (ArrayList<GameBadge>)list;
    }
    
    @Override
    public int kP() {
        return this.getInteger("availability");
    }
    
    @Override
    public boolean kQ() {
        return this.getBoolean("owned");
    }
    
    @Override
    public int kR() {
        return this.getInteger("achievement_unlocked_count");
    }
    
    @Override
    public long kS() {
        return this.getLong("last_played_server_time");
    }
    
    @Override
    public long kT() {
        return this.getLong("price_micros");
    }
    
    @Override
    public String kU() {
        return this.getString("formatted_price");
    }
    
    @Override
    public long kV() {
        return this.getLong("full_price_micros");
    }
    
    @Override
    public String kW() {
        return this.getString("formatted_full_price");
    }
    
    @Override
    public SnapshotMetadata kX() {
        return this.aay;
    }
    
    public ExtendedGame kZ() {
        return new ExtendedGameEntity(this);
    }
    
    @Override
    public String toString() {
        return ExtendedGameEntity.b(this);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        ((ExtendedGameEntity)this.kZ()).writeToParcel(parcel, n);
    }
}
