// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games;

import android.os.Parcel;
import com.google.android.gms.internal.fc;
import android.database.CharArrayBuffer;
import com.google.android.gms.internal.dv;
import com.google.android.gms.internal.ee;
import android.net.Uri;
import android.os.Parcelable$Creator;
import com.google.android.gms.internal.fm;

public final class GameEntity extends fm implements Game
{
    public static final Parcelable$Creator<GameEntity> CREATOR;
    private final int kg;
    private final String kh;
    private final String qa;
    private final String sH;
    private final String sI;
    private final String sJ;
    private final String sK;
    private final Uri sL;
    private final Uri sM;
    private final Uri sN;
    private final boolean sO;
    private final boolean sP;
    private final String sQ;
    private final int sR;
    private final int sS;
    private final int sT;
    private final boolean sU;
    private final boolean sV;
    
    static {
        CREATOR = (Parcelable$Creator)new a();
    }
    
    GameEntity(final int kg, final String kh, final String qa, final String sh, final String si, final String sj, final String sk, final Uri sl, final Uri sm, final Uri sn, final boolean so, final boolean sp, final String sq, final int sr, final int ss, final int st, final boolean su, final boolean sv) {
        this.kg = kg;
        this.kh = kh;
        this.qa = qa;
        this.sH = sh;
        this.sI = si;
        this.sJ = sj;
        this.sK = sk;
        this.sL = sl;
        this.sM = sm;
        this.sN = sn;
        this.sO = so;
        this.sP = sp;
        this.sQ = sq;
        this.sR = sr;
        this.sS = ss;
        this.sT = st;
        this.sU = su;
        this.sV = sv;
    }
    
    public GameEntity(final Game game) {
        this.kg = 2;
        this.kh = game.getApplicationId();
        this.sH = game.getPrimaryCategory();
        this.sI = game.getSecondaryCategory();
        this.sJ = game.getDescription();
        this.sK = game.getDeveloperName();
        this.qa = game.getDisplayName();
        this.sL = game.getIconImageUri();
        this.sM = game.getHiResImageUri();
        this.sN = game.getFeaturedImageUri();
        this.sO = game.isPlayEnabledGame();
        this.sP = game.isInstanceInstalled();
        this.sQ = game.getInstancePackageName();
        this.sR = game.getGameplayAclStatus();
        this.sS = game.getAchievementTotalCount();
        this.sT = game.getLeaderboardCount();
        this.sU = game.isRealTimeMultiplayerEnabled();
        this.sV = game.isTurnBasedMultiplayerEnabled();
    }
    
    static int a(final Game game) {
        return ee.hashCode(game.getApplicationId(), game.getDisplayName(), game.getPrimaryCategory(), game.getSecondaryCategory(), game.getDescription(), game.getDeveloperName(), game.getIconImageUri(), game.getHiResImageUri(), game.getFeaturedImageUri(), game.isPlayEnabledGame(), game.isInstanceInstalled(), game.getInstancePackageName(), game.getGameplayAclStatus(), game.getAchievementTotalCount(), game.getLeaderboardCount(), game.isRealTimeMultiplayerEnabled(), game.isTurnBasedMultiplayerEnabled());
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
                if (ee.equal(game2.getApplicationId(), game.getApplicationId()) && ee.equal(game2.getDisplayName(), game.getDisplayName()) && ee.equal(game2.getPrimaryCategory(), game.getPrimaryCategory()) && ee.equal(game2.getSecondaryCategory(), game.getSecondaryCategory()) && ee.equal(game2.getDescription(), game.getDescription()) && ee.equal(game2.getDeveloperName(), game.getDeveloperName()) && ee.equal(game2.getIconImageUri(), game.getIconImageUri()) && ee.equal(game2.getHiResImageUri(), game.getHiResImageUri()) && ee.equal(game2.getFeaturedImageUri(), game.getFeaturedImageUri()) && ee.equal(game2.isPlayEnabledGame(), game.isPlayEnabledGame()) && ee.equal(game2.isInstanceInstalled(), game.isInstanceInstalled()) && ee.equal(game2.getInstancePackageName(), game.getInstancePackageName()) && ee.equal(game2.getGameplayAclStatus(), game.getGameplayAclStatus()) && ee.equal(game2.getAchievementTotalCount(), game.getAchievementTotalCount()) && ee.equal(game2.getLeaderboardCount(), game.getLeaderboardCount()) && ee.equal(game2.isRealTimeMultiplayerEnabled(), game.isRealTimeMultiplayerEnabled())) {
                    b2 = b;
                    if (ee.equal(game2.isTurnBasedMultiplayerEnabled(), game.isTurnBasedMultiplayerEnabled())) {
                        return b2;
                    }
                }
                return false;
            }
        }
        return b2;
    }
    
    static String b(final Game game) {
        return ee.e(game).a("ApplicationId", game.getApplicationId()).a("DisplayName", game.getDisplayName()).a("PrimaryCategory", game.getPrimaryCategory()).a("SecondaryCategory", game.getSecondaryCategory()).a("Description", game.getDescription()).a("DeveloperName", game.getDeveloperName()).a("IconImageUri", game.getIconImageUri()).a("HiResImageUri", game.getHiResImageUri()).a("FeaturedImageUri", game.getFeaturedImageUri()).a("PlayEnabledGame", game.isPlayEnabledGame()).a("InstanceInstalled", game.isInstanceInstalled()).a("InstancePackageName", game.getInstancePackageName()).a("GameplayAclStatus", game.getGameplayAclStatus()).a("AchievementTotalCount", game.getAchievementTotalCount()).a("LeaderboardCount", game.getLeaderboardCount()).a("RealTimeMultiplayerEnabled", game.isRealTimeMultiplayerEnabled()).a("TurnBasedMultiplayerEnabled", game.isTurnBasedMultiplayerEnabled()).toString();
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
        return this.sS;
    }
    
    @Override
    public String getApplicationId() {
        return this.kh;
    }
    
    @Override
    public String getDescription() {
        return this.sJ;
    }
    
    @Override
    public void getDescription(final CharArrayBuffer charArrayBuffer) {
        fc.b(this.sJ, charArrayBuffer);
    }
    
    @Override
    public String getDeveloperName() {
        return this.sK;
    }
    
    @Override
    public void getDeveloperName(final CharArrayBuffer charArrayBuffer) {
        fc.b(this.sK, charArrayBuffer);
    }
    
    @Override
    public String getDisplayName() {
        return this.qa;
    }
    
    @Override
    public void getDisplayName(final CharArrayBuffer charArrayBuffer) {
        fc.b(this.qa, charArrayBuffer);
    }
    
    @Override
    public Uri getFeaturedImageUri() {
        return this.sN;
    }
    
    @Override
    public int getGameplayAclStatus() {
        return this.sR;
    }
    
    @Override
    public Uri getHiResImageUri() {
        return this.sM;
    }
    
    @Override
    public Uri getIconImageUri() {
        return this.sL;
    }
    
    @Override
    public String getInstancePackageName() {
        return this.sQ;
    }
    
    @Override
    public int getLeaderboardCount() {
        return this.sT;
    }
    
    @Override
    public String getPrimaryCategory() {
        return this.sH;
    }
    
    @Override
    public String getSecondaryCategory() {
        return this.sI;
    }
    
    public int getVersionCode() {
        return this.kg;
    }
    
    @Override
    public int hashCode() {
        return a(this);
    }
    
    public boolean isDataValid() {
        return true;
    }
    
    @Override
    public boolean isInstanceInstalled() {
        return this.sP;
    }
    
    @Override
    public boolean isPlayEnabledGame() {
        return this.sO;
    }
    
    @Override
    public boolean isRealTimeMultiplayerEnabled() {
        return this.sU;
    }
    
    @Override
    public boolean isTurnBasedMultiplayerEnabled() {
        return this.sV;
    }
    
    @Override
    public String toString() {
        return b(this);
    }
    
    public void writeToParcel(final Parcel parcel, int n) {
        final int n2 = 1;
        final String s = null;
        if (!this.bN()) {
            com.google.android.gms.games.a.a(this, parcel, n);
            return;
        }
        parcel.writeString(this.kh);
        parcel.writeString(this.qa);
        parcel.writeString(this.sH);
        parcel.writeString(this.sI);
        parcel.writeString(this.sJ);
        parcel.writeString(this.sK);
        String string;
        if (this.sL == null) {
            string = null;
        }
        else {
            string = this.sL.toString();
        }
        parcel.writeString(string);
        String string2;
        if (this.sM == null) {
            string2 = null;
        }
        else {
            string2 = this.sM.toString();
        }
        parcel.writeString(string2);
        String string3;
        if (this.sN == null) {
            string3 = s;
        }
        else {
            string3 = this.sN.toString();
        }
        parcel.writeString(string3);
        if (this.sO) {
            n = 1;
        }
        else {
            n = 0;
        }
        parcel.writeInt(n);
        if (this.sP) {
            n = n2;
        }
        else {
            n = 0;
        }
        parcel.writeInt(n);
        parcel.writeString(this.sQ);
        parcel.writeInt(this.sR);
        parcel.writeInt(this.sS);
        parcel.writeInt(this.sT);
    }
    
    static final class a extends com.google.android.gms.games.a
    {
        @Override
        public GameEntity Y(final Parcel parcel) {
            if (fm.c(dv.bM()) || dv.P(GameEntity.class.getCanonicalName())) {
                return super.Y(parcel);
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
            return new GameEntity(2, string, string2, string3, string4, string5, string6, parse, parse2, parse3, parcel.readInt() > 0, parcel.readInt() > 0, parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), false, false);
        }
    }
}
