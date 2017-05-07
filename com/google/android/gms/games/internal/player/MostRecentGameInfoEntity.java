// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.player;

import android.os.Parcel;
import com.google.android.gms.common.internal.m;
import android.net.Uri;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class MostRecentGameInfoEntity implements SafeParcelable, MostRecentGameInfo
{
    public static final MostRecentGameInfoEntityCreator CREATOR;
    private final int BR;
    private final String aaB;
    private final String aaC;
    private final long aaD;
    private final Uri aaE;
    private final Uri aaF;
    private final Uri aaG;
    
    static {
        CREATOR = new MostRecentGameInfoEntityCreator();
    }
    
    MostRecentGameInfoEntity(final int br, final String aaB, final String aaC, final long aaD, final Uri aaE, final Uri aaF, final Uri aaG) {
        this.BR = br;
        this.aaB = aaB;
        this.aaC = aaC;
        this.aaD = aaD;
        this.aaE = aaE;
        this.aaF = aaF;
        this.aaG = aaG;
    }
    
    public MostRecentGameInfoEntity(final MostRecentGameInfo mostRecentGameInfo) {
        this.BR = 2;
        this.aaB = mostRecentGameInfo.ln();
        this.aaC = mostRecentGameInfo.lo();
        this.aaD = mostRecentGameInfo.lp();
        this.aaE = mostRecentGameInfo.lq();
        this.aaF = mostRecentGameInfo.lr();
        this.aaG = mostRecentGameInfo.ls();
    }
    
    static int a(final MostRecentGameInfo mostRecentGameInfo) {
        return m.hashCode(mostRecentGameInfo.ln(), mostRecentGameInfo.lo(), mostRecentGameInfo.lp(), mostRecentGameInfo.lq(), mostRecentGameInfo.lr(), mostRecentGameInfo.ls());
    }
    
    static boolean a(final MostRecentGameInfo mostRecentGameInfo, final Object o) {
        final boolean b = true;
        boolean b2;
        if (!(o instanceof MostRecentGameInfo)) {
            b2 = false;
        }
        else {
            b2 = b;
            if (mostRecentGameInfo != o) {
                final MostRecentGameInfo mostRecentGameInfo2 = (MostRecentGameInfo)o;
                if (m.equal(mostRecentGameInfo2.ln(), mostRecentGameInfo.ln()) && m.equal(mostRecentGameInfo2.lo(), mostRecentGameInfo.lo()) && m.equal(mostRecentGameInfo2.lp(), mostRecentGameInfo.lp()) && m.equal(mostRecentGameInfo2.lq(), mostRecentGameInfo.lq()) && m.equal(mostRecentGameInfo2.lr(), mostRecentGameInfo.lr())) {
                    b2 = b;
                    if (m.equal(mostRecentGameInfo2.ls(), mostRecentGameInfo.ls())) {
                        return b2;
                    }
                }
                return false;
            }
        }
        return b2;
    }
    
    static String b(final MostRecentGameInfo mostRecentGameInfo) {
        return m.h(mostRecentGameInfo).a("GameId", mostRecentGameInfo.ln()).a("GameName", mostRecentGameInfo.lo()).a("ActivityTimestampMillis", mostRecentGameInfo.lp()).a("GameIconUri", mostRecentGameInfo.lq()).a("GameHiResUri", mostRecentGameInfo.lr()).a("GameFeaturedUri", mostRecentGameInfo.ls()).toString();
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return a(this, o);
    }
    
    public int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public int hashCode() {
        return a(this);
    }
    
    public boolean isDataValid() {
        return true;
    }
    
    @Override
    public String ln() {
        return this.aaB;
    }
    
    @Override
    public String lo() {
        return this.aaC;
    }
    
    @Override
    public long lp() {
        return this.aaD;
    }
    
    @Override
    public Uri lq() {
        return this.aaE;
    }
    
    @Override
    public Uri lr() {
        return this.aaF;
    }
    
    @Override
    public Uri ls() {
        return this.aaG;
    }
    
    public MostRecentGameInfo lt() {
        return this;
    }
    
    @Override
    public String toString() {
        return b(this);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        MostRecentGameInfoEntityCreator.a(this, parcel, n);
    }
}
