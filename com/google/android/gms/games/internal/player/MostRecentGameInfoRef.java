// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.player;

import android.os.Parcel;
import android.net.Uri;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;

public final class MostRecentGameInfoRef extends d implements MostRecentGameInfo
{
    private final PlayerColumnNames VN;
    
    public MostRecentGameInfoRef(final DataHolder dataHolder, final int n, final PlayerColumnNames vn) {
        super(dataHolder, n);
        this.VN = vn;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return MostRecentGameInfoEntity.a(this, o);
    }
    
    @Override
    public int hashCode() {
        return MostRecentGameInfoEntity.a(this);
    }
    
    @Override
    public String ln() {
        return this.getString(this.VN.aba);
    }
    
    @Override
    public String lo() {
        return this.getString(this.VN.abb);
    }
    
    @Override
    public long lp() {
        return this.getLong(this.VN.abc);
    }
    
    @Override
    public Uri lq() {
        return this.aR(this.VN.abd);
    }
    
    @Override
    public Uri lr() {
        return this.aR(this.VN.abe);
    }
    
    @Override
    public Uri ls() {
        return this.aR(this.VN.abf);
    }
    
    public MostRecentGameInfo lt() {
        return new MostRecentGameInfoEntity(this);
    }
    
    @Override
    public String toString() {
        return MostRecentGameInfoEntity.b(this);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        ((MostRecentGameInfoEntity)this.lt()).writeToParcel(parcel, n);
    }
}
