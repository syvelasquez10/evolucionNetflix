// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games;

import android.os.Parcel;
import com.google.android.gms.internal.gm;
import android.database.CharArrayBuffer;
import com.google.android.gms.internal.fe;
import com.google.android.gms.internal.fo;
import com.google.android.gms.internal.fb;
import android.net.Uri;
import android.os.Parcelable$Creator;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;

public final class PlayerEntity extends GamesDowngradeableSafeParcel implements Player
{
    public static final Parcelable$Creator<PlayerEntity> CREATOR;
    private final String HA;
    private final Uri HF;
    private final Uri HG;
    private final String HQ;
    private final String HR;
    private final String Ie;
    private final long If;
    private final int Ig;
    private final long Ih;
    private final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new PlayerEntityCreatorCompat();
    }
    
    PlayerEntity(final int xh, final String ie, final String ha, final Uri hf, final Uri hg, final long if1, final int ig, final long ih, final String hq, final String hr) {
        this.xH = xh;
        this.Ie = ie;
        this.HA = ha;
        this.HF = hf;
        this.HQ = hq;
        this.HG = hg;
        this.HR = hr;
        this.If = if1;
        this.Ig = ig;
        this.Ih = ih;
    }
    
    public PlayerEntity(final Player player) {
        this.xH = 4;
        this.Ie = player.getPlayerId();
        this.HA = player.getDisplayName();
        this.HF = player.getIconImageUri();
        this.HQ = player.getIconImageUrl();
        this.HG = player.getHiResImageUri();
        this.HR = player.getHiResImageUrl();
        this.If = player.getRetrievedTimestamp();
        this.Ig = player.gh();
        this.Ih = player.getLastPlayedWithTimestamp();
        fb.d(this.Ie);
        fb.d(this.HA);
        fb.x(this.If > 0L);
    }
    
    static int a(final Player player) {
        return fo.hashCode(player.getPlayerId(), player.getDisplayName(), player.getIconImageUri(), player.getHiResImageUri(), player.getRetrievedTimestamp());
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
                if (fo.equal(player2.getPlayerId(), player.getPlayerId()) && fo.equal(player2.getDisplayName(), player.getDisplayName()) && fo.equal(player2.getIconImageUri(), player.getIconImageUri()) && fo.equal(player2.getHiResImageUri(), player.getHiResImageUri())) {
                    b2 = b;
                    if (fo.equal(player2.getRetrievedTimestamp(), player.getRetrievedTimestamp())) {
                        return b2;
                    }
                }
                return false;
            }
        }
        return b2;
    }
    
    static String b(final Player player) {
        return fo.e(player).a("PlayerId", player.getPlayerId()).a("DisplayName", player.getDisplayName()).a("IconImageUri", player.getIconImageUri()).a("IconImageUrl", player.getIconImageUrl()).a("HiResImageUri", player.getHiResImageUri()).a("HiResImageUrl", player.getHiResImageUrl()).a("RetrievedTimestamp", player.getRetrievedTimestamp()).toString();
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
        return this.HA;
    }
    
    @Override
    public void getDisplayName(final CharArrayBuffer charArrayBuffer) {
        gm.b(this.HA, charArrayBuffer);
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
    public long getLastPlayedWithTimestamp() {
        return this.Ih;
    }
    
    @Override
    public String getPlayerId() {
        return this.Ie;
    }
    
    @Override
    public long getRetrievedTimestamp() {
        return this.If;
    }
    
    public int getVersionCode() {
        return this.xH;
    }
    
    @Override
    public int gh() {
        return this.Ig;
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
        return a(this);
    }
    
    public boolean isDataValid() {
        return true;
    }
    
    @Override
    public String toString() {
        return b(this);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final String s = null;
        if (!this.eK()) {
            PlayerEntityCreator.a(this, parcel, n);
            return;
        }
        parcel.writeString(this.Ie);
        parcel.writeString(this.HA);
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
            string2 = s;
        }
        else {
            string2 = this.HG.toString();
        }
        parcel.writeString(string2);
        parcel.writeLong(this.If);
    }
    
    static final class PlayerEntityCreatorCompat extends PlayerEntityCreator
    {
        @Override
        public PlayerEntity ao(final Parcel parcel) {
            if (GamesDowngradeableSafeParcel.c(fe.eJ()) || fe.al(PlayerEntity.class.getCanonicalName())) {
                return super.ao(parcel);
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
            return new PlayerEntity(4, string, string2, parse, parse2, parcel.readLong(), -1, -1L, null, null);
        }
    }
}
