// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games;

import android.os.Parcel;
import com.google.android.gms.internal.jv;
import android.database.CharArrayBuffer;
import com.google.android.gms.common.internal.c;
import com.google.android.gms.common.internal.m;
import com.google.android.gms.games.internal.player.MostRecentGameInfo;
import com.google.android.gms.common.internal.a;
import com.google.android.gms.games.internal.player.MostRecentGameInfoEntity;
import android.net.Uri;
import android.os.Parcelable$Creator;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;

public final class PlayerEntity extends GamesDowngradeableSafeParcel implements Player
{
    public static final Parcelable$Creator<PlayerEntity> CREATOR;
    private final int BR;
    private final String No;
    private final String Nz;
    private final Uri UW;
    private final Uri UX;
    private final long VA;
    private final int VB;
    private final long VC;
    private final MostRecentGameInfoEntity VD;
    private final PlayerLevelInfo VE;
    private final boolean VF;
    private final String Vh;
    private final String Vi;
    private final String Vz;
    
    static {
        CREATOR = (Parcelable$Creator)new PlayerEntityCreatorCompat();
    }
    
    PlayerEntity(final int br, final String vz, final String nz, final Uri uw, final Uri ux, final long va, final int vb, final long vc, final String vh, final String vi, final String no, final MostRecentGameInfoEntity vd, final PlayerLevelInfo ve, final boolean vf) {
        this.BR = br;
        this.Vz = vz;
        this.Nz = nz;
        this.UW = uw;
        this.Vh = vh;
        this.UX = ux;
        this.Vi = vi;
        this.VA = va;
        this.VB = vb;
        this.VC = vc;
        this.No = no;
        this.VF = vf;
        this.VD = vd;
        this.VE = ve;
    }
    
    public PlayerEntity(final Player player) {
        this.BR = 11;
        this.Vz = player.getPlayerId();
        this.Nz = player.getDisplayName();
        this.UW = player.getIconImageUri();
        this.Vh = player.getIconImageUrl();
        this.UX = player.getHiResImageUri();
        this.Vi = player.getHiResImageUrl();
        this.VA = player.getRetrievedTimestamp();
        this.VB = player.jR();
        this.VC = player.getLastPlayedWithTimestamp();
        this.No = player.getTitle();
        this.VF = player.isProfileVisible();
        final MostRecentGameInfo js = player.jS();
        MostRecentGameInfoEntity vd;
        if (js == null) {
            vd = null;
        }
        else {
            vd = new MostRecentGameInfoEntity(js);
        }
        this.VD = vd;
        this.VE = player.getLevelInfo();
        a.f(this.Vz);
        a.f(this.Nz);
        a.I(this.VA > 0L);
    }
    
    static boolean a(final Player player, final Object o) {
        final boolean b = true;
        boolean b2;
        if (!(o instanceof Player)) {
            b2 = false;
        }
        else {
            b2 = b;
            if (player != o) {
                final Player player2 = (Player)o;
                if (m.equal(player2.getPlayerId(), player.getPlayerId()) && m.equal(player2.getDisplayName(), player.getDisplayName()) && m.equal(player2.getIconImageUri(), player.getIconImageUri()) && m.equal(player2.getHiResImageUri(), player.getHiResImageUri()) && m.equal(player2.getRetrievedTimestamp(), player.getRetrievedTimestamp()) && m.equal(player2.getTitle(), player.getTitle())) {
                    b2 = b;
                    if (m.equal(player2.getLevelInfo(), player.getLevelInfo())) {
                        return b2;
                    }
                }
                return false;
            }
        }
        return b2;
    }
    
    static int b(final Player player) {
        return m.hashCode(player.getPlayerId(), player.getDisplayName(), player.getIconImageUri(), player.getHiResImageUri(), player.getRetrievedTimestamp(), player.getTitle(), player.getLevelInfo());
    }
    
    static String c(final Player player) {
        return m.h(player).a("PlayerId", player.getPlayerId()).a("DisplayName", player.getDisplayName()).a("IconImageUri", player.getIconImageUri()).a("IconImageUrl", player.getIconImageUrl()).a("HiResImageUri", player.getHiResImageUri()).a("HiResImageUrl", player.getHiResImageUrl()).a("RetrievedTimestamp", player.getRetrievedTimestamp()).a("Title", player.getTitle()).a("LevelInfo", player.getLevelInfo()).toString();
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return a(this, o);
    }
    
    public Player freeze() {
        return this;
    }
    
    @Override
    public String getDisplayName() {
        return this.Nz;
    }
    
    @Override
    public void getDisplayName(final CharArrayBuffer charArrayBuffer) {
        jv.b(this.Nz, charArrayBuffer);
    }
    
    @Override
    public Uri getHiResImageUri() {
        return this.UX;
    }
    
    @Override
    public String getHiResImageUrl() {
        return this.Vi;
    }
    
    @Override
    public Uri getIconImageUri() {
        return this.UW;
    }
    
    @Override
    public String getIconImageUrl() {
        return this.Vh;
    }
    
    @Override
    public long getLastPlayedWithTimestamp() {
        return this.VC;
    }
    
    @Override
    public PlayerLevelInfo getLevelInfo() {
        return this.VE;
    }
    
    @Override
    public String getPlayerId() {
        return this.Vz;
    }
    
    @Override
    public long getRetrievedTimestamp() {
        return this.VA;
    }
    
    @Override
    public String getTitle() {
        return this.No;
    }
    
    @Override
    public void getTitle(final CharArrayBuffer charArrayBuffer) {
        jv.b(this.No, charArrayBuffer);
    }
    
    public int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public boolean hasHiResImage() {
        return this.getHiResImageUri() != null;
    }
    
    @Override
    public boolean hasIconImage() {
        return this.getIconImageUri() != null;
    }
    
    @Override
    public int hashCode() {
        return b(this);
    }
    
    public boolean isDataValid() {
        return true;
    }
    
    @Override
    public boolean isProfileVisible() {
        return this.VF;
    }
    
    @Override
    public int jR() {
        return this.VB;
    }
    
    @Override
    public MostRecentGameInfo jS() {
        return this.VD;
    }
    
    @Override
    public String toString() {
        return c(this);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final String s = null;
        if (!this.gQ()) {
            PlayerEntityCreator.a(this, parcel, n);
            return;
        }
        parcel.writeString(this.Vz);
        parcel.writeString(this.Nz);
        String string;
        if (this.UW == null) {
            string = null;
        }
        else {
            string = this.UW.toString();
        }
        parcel.writeString(string);
        String string2;
        if (this.UX == null) {
            string2 = s;
        }
        else {
            string2 = this.UX.toString();
        }
        parcel.writeString(string2);
        parcel.writeLong(this.VA);
    }
    
    static final class PlayerEntityCreatorCompat extends PlayerEntityCreator
    {
        @Override
        public PlayerEntity ce(final Parcel parcel) {
            if (GamesDowngradeableSafeParcel.c(c.gP()) || c.aV(PlayerEntity.class.getCanonicalName())) {
                return super.ce(parcel);
            }
            final String string = parcel.readString();
            final String string2 = parcel.readString();
            final String string3 = parcel.readString();
            final String string4 = parcel.readString();
            Uri parse;
            if (string3 == null) {
                parse = null;
            }
            else {
                parse = Uri.parse(string3);
            }
            Uri parse2;
            if (string4 == null) {
                parse2 = null;
            }
            else {
                parse2 = Uri.parse(string4);
            }
            return new PlayerEntity(11, string, string2, parse, parse2, parcel.readLong(), -1, -1L, null, null, null, null, null, true);
        }
    }
}
