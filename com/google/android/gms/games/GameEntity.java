// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games;

import android.os.Parcel;
import com.google.android.gms.internal.gm;
import android.database.CharArrayBuffer;
import com.google.android.gms.internal.fe;
import com.google.android.gms.internal.fo;
import android.net.Uri;
import android.os.Parcelable$Creator;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;

public final class GameEntity extends GamesDowngradeableSafeParcel implements Game
{
    public static final Parcelable$Creator<GameEntity> CREATOR;
    private final String HA;
    private final String HB;
    private final String HC;
    private final String HD;
    private final String HE;
    private final Uri HF;
    private final Uri HG;
    private final Uri HH;
    private final boolean HI;
    private final boolean HJ;
    private final String HK;
    private final int HL;
    private final int HM;
    private final int HN;
    private final boolean HO;
    private final boolean HP;
    private final String HQ;
    private final String HR;
    private final String HS;
    private final boolean HT;
    private final boolean HU;
    private final int xH;
    private final String xI;
    
    static {
        CREATOR = (Parcelable$Creator)new GameEntityCreatorCompat();
    }
    
    GameEntity(final int xh, final String xi, final String ha, final String hb, final String hc, final String hd, final String he, final Uri hf, final Uri hg, final Uri hh, final boolean hi, final boolean hj, final String hk, final int hl, final int hm, final int hn, final boolean ho, final boolean hp, final String hq, final String hr, final String hs, final boolean ht, final boolean hu) {
        this.xH = xh;
        this.xI = xi;
        this.HA = ha;
        this.HB = hb;
        this.HC = hc;
        this.HD = hd;
        this.HE = he;
        this.HF = hf;
        this.HQ = hq;
        this.HG = hg;
        this.HR = hr;
        this.HH = hh;
        this.HS = hs;
        this.HI = hi;
        this.HJ = hj;
        this.HK = hk;
        this.HL = hl;
        this.HM = hm;
        this.HN = hn;
        this.HO = ho;
        this.HP = hp;
        this.HT = ht;
        this.HU = hu;
    }
    
    public GameEntity(final Game game) {
        this.xH = 3;
        this.xI = game.getApplicationId();
        this.HB = game.getPrimaryCategory();
        this.HC = game.getSecondaryCategory();
        this.HD = game.getDescription();
        this.HE = game.getDeveloperName();
        this.HA = game.getDisplayName();
        this.HF = game.getIconImageUri();
        this.HQ = game.getIconImageUrl();
        this.HG = game.getHiResImageUri();
        this.HR = game.getHiResImageUrl();
        this.HH = game.getFeaturedImageUri();
        this.HS = game.getFeaturedImageUrl();
        this.HI = game.gb();
        this.HJ = game.gd();
        this.HK = game.ge();
        this.HL = game.gf();
        this.HM = game.getAchievementTotalCount();
        this.HN = game.getLeaderboardCount();
        this.HO = game.isRealTimeMultiplayerEnabled();
        this.HP = game.isTurnBasedMultiplayerEnabled();
        this.HT = game.isMuted();
        this.HU = game.gc();
    }
    
    static int a(final Game game) {
        return fo.hashCode(game.getApplicationId(), game.getDisplayName(), game.getPrimaryCategory(), game.getSecondaryCategory(), game.getDescription(), game.getDeveloperName(), game.getIconImageUri(), game.getHiResImageUri(), game.getFeaturedImageUri(), game.gb(), game.gd(), game.ge(), game.gf(), game.getAchievementTotalCount(), game.getLeaderboardCount(), game.isRealTimeMultiplayerEnabled(), game.isTurnBasedMultiplayerEnabled(), game.isMuted(), game.gc());
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
                if (fo.equal(game2.getApplicationId(), game.getApplicationId()) && fo.equal(game2.getDisplayName(), game.getDisplayName()) && fo.equal(game2.getPrimaryCategory(), game.getPrimaryCategory()) && fo.equal(game2.getSecondaryCategory(), game.getSecondaryCategory()) && fo.equal(game2.getDescription(), game.getDescription()) && fo.equal(game2.getDeveloperName(), game.getDeveloperName()) && fo.equal(game2.getIconImageUri(), game.getIconImageUri()) && fo.equal(game2.getHiResImageUri(), game.getHiResImageUri()) && fo.equal(game2.getFeaturedImageUri(), game.getFeaturedImageUri()) && fo.equal(game2.gb(), game.gb()) && fo.equal(game2.gd(), game.gd()) && fo.equal(game2.ge(), game.ge()) && fo.equal(game2.gf(), game.gf()) && fo.equal(game2.getAchievementTotalCount(), game.getAchievementTotalCount()) && fo.equal(game2.getLeaderboardCount(), game.getLeaderboardCount()) && fo.equal(game2.isRealTimeMultiplayerEnabled(), game.isRealTimeMultiplayerEnabled())) {
                    final boolean turnBasedMultiplayerEnabled = game2.isTurnBasedMultiplayerEnabled();
                    final boolean b3 = game.isTurnBasedMultiplayerEnabled() && fo.equal(game2.isMuted(), game.isMuted()) && fo.equal(game2.gc(), game.gc());
                    b2 = b;
                    if (fo.equal(turnBasedMultiplayerEnabled, b3)) {
                        return b2;
                    }
                }
                return false;
            }
        }
        return b2;
    }
    
    static String b(final Game game) {
        return fo.e(game).a("ApplicationId", game.getApplicationId()).a("DisplayName", game.getDisplayName()).a("PrimaryCategory", game.getPrimaryCategory()).a("SecondaryCategory", game.getSecondaryCategory()).a("Description", game.getDescription()).a("DeveloperName", game.getDeveloperName()).a("IconImageUri", game.getIconImageUri()).a("IconImageUrl", game.getIconImageUrl()).a("HiResImageUri", game.getHiResImageUri()).a("HiResImageUrl", game.getHiResImageUrl()).a("FeaturedImageUri", game.getFeaturedImageUri()).a("FeaturedImageUrl", game.getFeaturedImageUrl()).a("PlayEnabledGame", game.gb()).a("InstanceInstalled", game.gd()).a("InstancePackageName", game.ge()).a("AchievementTotalCount", game.getAchievementTotalCount()).a("LeaderboardCount", game.getLeaderboardCount()).a("RealTimeMultiplayerEnabled", game.isRealTimeMultiplayerEnabled()).a("TurnBasedMultiplayerEnabled", game.isTurnBasedMultiplayerEnabled()).toString();
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
    public boolean gb() {
        return this.HI;
    }
    
    @Override
    public boolean gc() {
        return this.HU;
    }
    
    @Override
    public boolean gd() {
        return this.HJ;
    }
    
    @Override
    public String ge() {
        return this.HK;
    }
    
    @Override
    public int getAchievementTotalCount() {
        return this.HM;
    }
    
    @Override
    public String getApplicationId() {
        return this.xI;
    }
    
    @Override
    public String getDescription() {
        return this.HD;
    }
    
    @Override
    public void getDescription(final CharArrayBuffer charArrayBuffer) {
        gm.b(this.HD, charArrayBuffer);
    }
    
    @Override
    public String getDeveloperName() {
        return this.HE;
    }
    
    @Override
    public void getDeveloperName(final CharArrayBuffer charArrayBuffer) {
        gm.b(this.HE, charArrayBuffer);
    }
    
    @Override
    public String getDisplayName() {
        return this.HA;
    }
    
    @Override
    public void getDisplayName(final CharArrayBuffer charArrayBuffer) {
        gm.b(this.HA, charArrayBuffer);
    }
    
    @Override
    public Uri getFeaturedImageUri() {
        return this.HH;
    }
    
    @Override
    public String getFeaturedImageUrl() {
        return this.HS;
    }
    
    @Override
    public Uri getHiResImageUri() {
        return this.HG;
    }
    
    @Override
    public String getHiResImageUrl() {
        return this.HR;
    }
    
    @Override
    public Uri getIconImageUri() {
        return this.HF;
    }
    
    @Override
    public String getIconImageUrl() {
        return this.HQ;
    }
    
    @Override
    public int getLeaderboardCount() {
        return this.HN;
    }
    
    @Override
    public String getPrimaryCategory() {
        return this.HB;
    }
    
    @Override
    public String getSecondaryCategory() {
        return this.HC;
    }
    
    public int getVersionCode() {
        return this.xH;
    }
    
    @Override
    public int gf() {
        return this.HL;
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
        return this.HT;
    }
    
    @Override
    public boolean isRealTimeMultiplayerEnabled() {
        return this.HO;
    }
    
    @Override
    public boolean isTurnBasedMultiplayerEnabled() {
        return this.HP;
    }
    
    @Override
    public String toString() {
        return b(this);
    }
    
    public void writeToParcel(final Parcel parcel, int n) {
        final int n2 = 1;
        final String s = null;
        if (!this.eK()) {
            GameEntityCreator.a(this, parcel, n);
            return;
        }
        parcel.writeString(this.xI);
        parcel.writeString(this.HA);
        parcel.writeString(this.HB);
        parcel.writeString(this.HC);
        parcel.writeString(this.HD);
        parcel.writeString(this.HE);
        String string;
        if (this.HF == null) {
            string = null;
        }
        else {
            string = this.HF.toString();
        }
        parcel.writeString(string);
        String string2;
        if (this.HG == null) {
            string2 = null;
        }
        else {
            string2 = this.HG.toString();
        }
        parcel.writeString(string2);
        String string3;
        if (this.HH == null) {
            string3 = s;
        }
        else {
            string3 = this.HH.toString();
        }
        parcel.writeString(string3);
        if (this.HI) {
            n = 1;
        }
        else {
            n = 0;
        }
        parcel.writeInt(n);
        if (this.HJ) {
            n = n2;
        }
        else {
            n = 0;
        }
        parcel.writeInt(n);
        parcel.writeString(this.HK);
        parcel.writeInt(this.HL);
        parcel.writeInt(this.HM);
        parcel.writeInt(this.HN);
    }
    
    static final class GameEntityCreatorCompat extends GameEntityCreator
    {
        @Override
        public GameEntity an(final Parcel parcel) {
            if (GamesDowngradeableSafeParcel.c(fe.eJ()) || fe.al(GameEntity.class.getCanonicalName())) {
                return super.an(parcel);
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
            return new GameEntity(3, string, string2, string3, string4, string5, string6, parse, parse2, parse3, parcel.readInt() > 0, parcel.readInt() > 0, parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), false, false, null, null, null, false, false);
        }
    }
}
