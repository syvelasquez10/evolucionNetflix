// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games;

import android.os.Parcel;
import com.google.android.gms.common.internal.m;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class PlayerLevel implements SafeParcelable
{
    public static final PlayerLevelCreator CREATOR;
    private final int BR;
    private final int VG;
    private final long VH;
    private final long VI;
    
    static {
        CREATOR = new PlayerLevelCreator();
    }
    
    PlayerLevel(final int br, final int vg, final long vh, final long vi) {
        final boolean b = true;
        n.a(vh >= 0L, (Object)"Min XP must be positive!");
        n.a(vi > vh && b, (Object)"Max XP must be more than min XP!");
        this.BR = br;
        this.VG = vg;
        this.VH = vh;
        this.VI = vi;
    }
    
    public PlayerLevel(final int n, final long n2, final long n3) {
        this(1, n, n2, n3);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        final boolean b = true;
        boolean b2;
        if (!(o instanceof PlayerLevel)) {
            b2 = false;
        }
        else {
            b2 = b;
            if (this != o) {
                final PlayerLevel playerLevel = (PlayerLevel)o;
                if (m.equal(playerLevel.getLevelNumber(), this.getLevelNumber()) && m.equal(playerLevel.getMinXp(), this.getMinXp())) {
                    b2 = b;
                    if (m.equal(playerLevel.getMaxXp(), this.getMaxXp())) {
                        return b2;
                    }
                }
                return false;
            }
        }
        return b2;
    }
    
    public int getLevelNumber() {
        return this.VG;
    }
    
    public long getMaxXp() {
        return this.VI;
    }
    
    public long getMinXp() {
        return this.VH;
    }
    
    public int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.VG, this.VH, this.VI);
    }
    
    @Override
    public String toString() {
        return m.h(this).a("LevelNumber", this.getLevelNumber()).a("MinXp", this.getMinXp()).a("MaxXp", this.getMaxXp()).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        PlayerLevelCreator.a(this, parcel, n);
    }
}
