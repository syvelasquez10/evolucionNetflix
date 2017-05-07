// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games;

import android.os.Parcel;
import com.google.android.gms.common.internal.m;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class PlayerLevelInfo implements SafeParcelable
{
    public static final PlayerLevelInfoCreator CREATOR;
    private final int BR;
    private final long VJ;
    private final long VK;
    private final PlayerLevel VL;
    private final PlayerLevel VM;
    
    static {
        CREATOR = new PlayerLevelInfoCreator();
    }
    
    PlayerLevelInfo(final int br, final long vj, final long vk, final PlayerLevel vl, final PlayerLevel vm) {
        n.I(vj != -1L);
        n.i(vl);
        n.i(vm);
        this.BR = br;
        this.VJ = vj;
        this.VK = vk;
        this.VL = vl;
        this.VM = vm;
    }
    
    public PlayerLevelInfo(final long n, final long n2, final PlayerLevel playerLevel, final PlayerLevel playerLevel2) {
        this(1, n, n2, playerLevel, playerLevel2);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        final boolean b = true;
        boolean b2;
        if (!(o instanceof PlayerLevelInfo)) {
            b2 = false;
        }
        else {
            b2 = b;
            if (o != this) {
                final PlayerLevelInfo playerLevelInfo = (PlayerLevelInfo)o;
                if (m.equal(this.VJ, playerLevelInfo.VJ) && m.equal(this.VK, playerLevelInfo.VK) && m.equal(this.VL, playerLevelInfo.VL)) {
                    b2 = b;
                    if (m.equal(this.VM, playerLevelInfo.VM)) {
                        return b2;
                    }
                }
                return false;
            }
        }
        return b2;
    }
    
    public PlayerLevel getCurrentLevel() {
        return this.VL;
    }
    
    public long getCurrentXpTotal() {
        return this.VJ;
    }
    
    public long getLastLevelUpTimestamp() {
        return this.VK;
    }
    
    public PlayerLevel getNextLevel() {
        return this.VM;
    }
    
    public int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.VJ, this.VK, this.VL, this.VM);
    }
    
    public boolean isMaxLevel() {
        return this.VL.equals(this.VM);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        PlayerLevelInfoCreator.a(this, parcel, n);
    }
}
