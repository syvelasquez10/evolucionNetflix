// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.game;

import android.os.Parcel;
import android.net.Uri;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.b;

public final class GameBadgeRef extends b implements GameBadge
{
    GameBadgeRef(final DataHolder dataHolder, final int n) {
        super(dataHolder, n);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return GameBadgeEntity.a(this, o);
    }
    
    @Override
    public String getDescription() {
        return this.getString("badge_description");
    }
    
    @Override
    public Uri getIconImageUri() {
        return this.ah("badge_icon_image_uri");
    }
    
    @Override
    public String getTitle() {
        return this.getString("badge_title");
    }
    
    @Override
    public int getType() {
        return this.getInteger("badge_type");
    }
    
    @Override
    public int hashCode() {
        return GameBadgeEntity.a(this);
    }
    
    public GameBadge hh() {
        return new GameBadgeEntity(this);
    }
    
    @Override
    public String toString() {
        return GameBadgeEntity.b(this);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        ((GameBadgeEntity)this.hh()).writeToParcel(parcel, n);
    }
}
