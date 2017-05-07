// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games;

import android.os.Parcel;
import com.google.android.gms.internal.jv;
import android.database.CharArrayBuffer;
import com.google.android.gms.common.internal.c;
import com.google.android.gms.common.internal.m;
import android.net.Uri;
import android.os.Parcelable$Creator;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;

public final class GameEntity extends GamesDowngradeableSafeParcel implements Game
{
    public static final Parcelable$Creator<GameEntity> CREATOR;
    private final int BR;
    private final String Ez;
    private final String Nz;
    private final String Tg;
    private final String UT;
    private final String UU;
    private final String UV;
    private final Uri UW;
    private final Uri UX;
    private final Uri UY;
    private final boolean UZ;
    private final boolean Va;
    private final String Vb;
    private final int Vc;
    private final int Vd;
    private final int Ve;
    private final boolean Vf;
    private final boolean Vg;
    private final String Vh;
    private final String Vi;
    private final String Vj;
    private final boolean Vk;
    private final boolean Vl;
    private final boolean Vm;
    private final String Vn;
    
    static {
        CREATOR = (Parcelable$Creator)new GameEntityCreatorCompat();
    }
    
    GameEntity(final int br, final String ez, final String nz, final String ut, final String uu, final String tg, final String uv, final Uri uw, final Uri ux, final Uri uy, final boolean uz, final boolean va, final String vb, final int vc, final int vd, final int ve, final boolean vf, final boolean vg, final String vh, final String vi, final String vj, final boolean vk, final boolean vl, final boolean vm, final String vn) {
        this.BR = br;
        this.Ez = ez;
        this.Nz = nz;
        this.UT = ut;
        this.UU = uu;
        this.Tg = tg;
        this.UV = uv;
        this.UW = uw;
        this.Vh = vh;
        this.UX = ux;
        this.Vi = vi;
        this.UY = uy;
        this.Vj = vj;
        this.UZ = uz;
        this.Va = va;
        this.Vb = vb;
        this.Vc = vc;
        this.Vd = vd;
        this.Ve = ve;
        this.Vf = vf;
        this.Vg = vg;
        this.Vk = vk;
        this.Vl = vl;
        this.Vm = vm;
        this.Vn = vn;
    }
    
    public GameEntity(final Game game) {
        this.BR = 5;
        this.Ez = game.getApplicationId();
        this.UT = game.getPrimaryCategory();
        this.UU = game.getSecondaryCategory();
        this.Tg = game.getDescription();
        this.UV = game.getDeveloperName();
        this.Nz = game.getDisplayName();
        this.UW = game.getIconImageUri();
        this.Vh = game.getIconImageUrl();
        this.UX = game.getHiResImageUri();
        this.Vi = game.getHiResImageUrl();
        this.UY = game.getFeaturedImageUri();
        this.Vj = game.getFeaturedImageUrl();
        this.UZ = game.jL();
        this.Va = game.jN();
        this.Vb = game.jO();
        this.Vc = game.jP();
        this.Vd = game.getAchievementTotalCount();
        this.Ve = game.getLeaderboardCount();
        this.Vf = game.isRealTimeMultiplayerEnabled();
        this.Vg = game.isTurnBasedMultiplayerEnabled();
        this.Vk = game.isMuted();
        this.Vl = game.jM();
        this.Vm = game.areSnapshotsEnabled();
        this.Vn = game.getThemeColor();
    }
    
    static int a(final Game game) {
        return m.hashCode(game.getApplicationId(), game.getDisplayName(), game.getPrimaryCategory(), game.getSecondaryCategory(), game.getDescription(), game.getDeveloperName(), game.getIconImageUri(), game.getHiResImageUri(), game.getFeaturedImageUri(), game.jL(), game.jN(), game.jO(), game.jP(), game.getAchievementTotalCount(), game.getLeaderboardCount(), game.isRealTimeMultiplayerEnabled(), game.isTurnBasedMultiplayerEnabled(), game.isMuted(), game.jM(), game.areSnapshotsEnabled(), game.getThemeColor());
    }
    
    static boolean a(final Game game, final Object o) {
        final boolean b = true;
        boolean b2;
        if (!(o instanceof Game)) {
            b2 = false;
        }
        else {
            b2 = b;
            if (game != o) {
                final Game game2 = (Game)o;
                if (m.equal(game2.getApplicationId(), game.getApplicationId()) && m.equal(game2.getDisplayName(), game.getDisplayName()) && m.equal(game2.getPrimaryCategory(), game.getPrimaryCategory()) && m.equal(game2.getSecondaryCategory(), game.getSecondaryCategory()) && m.equal(game2.getDescription(), game.getDescription()) && m.equal(game2.getDeveloperName(), game.getDeveloperName()) && m.equal(game2.getIconImageUri(), game.getIconImageUri()) && m.equal(game2.getHiResImageUri(), game.getHiResImageUri()) && m.equal(game2.getFeaturedImageUri(), game.getFeaturedImageUri()) && m.equal(game2.jL(), game.jL()) && m.equal(game2.jN(), game.jN()) && m.equal(game2.jO(), game.jO()) && m.equal(game2.jP(), game.jP()) && m.equal(game2.getAchievementTotalCount(), game.getAchievementTotalCount()) && m.equal(game2.getLeaderboardCount(), game.getLeaderboardCount()) && m.equal(game2.isRealTimeMultiplayerEnabled(), game.isRealTimeMultiplayerEnabled()) && m.equal(game2.isTurnBasedMultiplayerEnabled(), game.isTurnBasedMultiplayerEnabled() && m.equal(game2.isMuted(), game.isMuted()) && m.equal(game2.jM(), game.jM())) && m.equal(game2.areSnapshotsEnabled(), game.areSnapshotsEnabled())) {
                    b2 = b;
                    if (m.equal(game2.getThemeColor(), game.getThemeColor())) {
                        return b2;
                    }
                }
                return false;
            }
        }
        return b2;
    }
    
    static String b(final Game game) {
        return m.h(game).a("ApplicationId", game.getApplicationId()).a("DisplayName", game.getDisplayName()).a("PrimaryCategory", game.getPrimaryCategory()).a("SecondaryCategory", game.getSecondaryCategory()).a("Description", game.getDescription()).a("DeveloperName", game.getDeveloperName()).a("IconImageUri", game.getIconImageUri()).a("IconImageUrl", game.getIconImageUrl()).a("HiResImageUri", game.getHiResImageUri()).a("HiResImageUrl", game.getHiResImageUrl()).a("FeaturedImageUri", game.getFeaturedImageUri()).a("FeaturedImageUrl", game.getFeaturedImageUrl()).a("PlayEnabledGame", game.jL()).a("InstanceInstalled", game.jN()).a("InstancePackageName", game.jO()).a("AchievementTotalCount", game.getAchievementTotalCount()).a("LeaderboardCount", game.getLeaderboardCount()).a("RealTimeMultiplayerEnabled", game.isRealTimeMultiplayerEnabled()).a("TurnBasedMultiplayerEnabled", game.isTurnBasedMultiplayerEnabled()).a("AreSnapshotsEnabled", game.areSnapshotsEnabled()).a("ThemeColor", game.getThemeColor()).toString();
    }
    
    @Override
    public boolean areSnapshotsEnabled() {
        return this.Vm;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return a(this, o);
    }
    
    public Game freeze() {
        return this;
    }
    
    @Override
    public int getAchievementTotalCount() {
        return this.Vd;
    }
    
    @Override
    public String getApplicationId() {
        return this.Ez;
    }
    
    @Override
    public String getDescription() {
        return this.Tg;
    }
    
    @Override
    public void getDescription(final CharArrayBuffer charArrayBuffer) {
        jv.b(this.Tg, charArrayBuffer);
    }
    
    @Override
    public String getDeveloperName() {
        return this.UV;
    }
    
    @Override
    public void getDeveloperName(final CharArrayBuffer charArrayBuffer) {
        jv.b(this.UV, charArrayBuffer);
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
    public Uri getFeaturedImageUri() {
        return this.UY;
    }
    
    @Override
    public String getFeaturedImageUrl() {
        return this.Vj;
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
    public int getLeaderboardCount() {
        return this.Ve;
    }
    
    @Override
    public String getPrimaryCategory() {
        return this.UT;
    }
    
    @Override
    public String getSecondaryCategory() {
        return this.UU;
    }
    
    @Override
    public String getThemeColor() {
        return this.Vn;
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
    public boolean isMuted() {
        return this.Vk;
    }
    
    @Override
    public boolean isRealTimeMultiplayerEnabled() {
        return this.Vf;
    }
    
    @Override
    public boolean isTurnBasedMultiplayerEnabled() {
        return this.Vg;
    }
    
    @Override
    public boolean jL() {
        return this.UZ;
    }
    
    @Override
    public boolean jM() {
        return this.Vl;
    }
    
    @Override
    public boolean jN() {
        return this.Va;
    }
    
    @Override
    public String jO() {
        return this.Vb;
    }
    
    @Override
    public int jP() {
        return this.Vc;
    }
    
    @Override
    public String toString() {
        return b(this);
    }
    
    public void writeToParcel(final Parcel parcel, int n) {
        final int n2 = 1;
        final String s = null;
        if (!this.gQ()) {
            GameEntityCreator.a(this, parcel, n);
            return;
        }
        parcel.writeString(this.Ez);
        parcel.writeString(this.Nz);
        parcel.writeString(this.UT);
        parcel.writeString(this.UU);
        parcel.writeString(this.Tg);
        parcel.writeString(this.UV);
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
            string2 = null;
        }
        else {
            string2 = this.UX.toString();
        }
        parcel.writeString(string2);
        String string3;
        if (this.UY == null) {
            string3 = s;
        }
        else {
            string3 = this.UY.toString();
        }
        parcel.writeString(string3);
        if (this.UZ) {
            n = 1;
        }
        else {
            n = 0;
        }
        parcel.writeInt(n);
        if (this.Va) {
            n = n2;
        }
        else {
            n = 0;
        }
        parcel.writeInt(n);
        parcel.writeString(this.Vb);
        parcel.writeInt(this.Vc);
        parcel.writeInt(this.Vd);
        parcel.writeInt(this.Ve);
    }
    
    static final class GameEntityCreatorCompat extends GameEntityCreator
    {
        @Override
        public GameEntity cd(final Parcel parcel) {
            if (GamesDowngradeableSafeParcel.c(c.gP()) || c.aV(GameEntity.class.getCanonicalName())) {
                return super.cd(parcel);
            }
            final String string = parcel.readString();
            final String string2 = parcel.readString();
            final String string3 = parcel.readString();
            final String string4 = parcel.readString();
            final String string5 = parcel.readString();
            final String string6 = parcel.readString();
            final String string7 = parcel.readString();
            Uri parse;
            if (string7 == null) {
                parse = null;
            }
            else {
                parse = Uri.parse(string7);
            }
            final String string8 = parcel.readString();
            Uri parse2;
            if (string8 == null) {
                parse2 = null;
            }
            else {
                parse2 = Uri.parse(string8);
            }
            final String string9 = parcel.readString();
            Uri parse3;
            if (string9 == null) {
                parse3 = null;
            }
            else {
                parse3 = Uri.parse(string9);
            }
            return new GameEntity(5, string, string2, string3, string4, string5, string6, parse, parse2, parse3, parcel.readInt() > 0, parcel.readInt() > 0, parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), false, false, null, null, null, false, false, false, null);
        }
    }
}
