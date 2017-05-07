// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games;

import android.os.Parcel;
import com.google.android.gms.internal.fc;
import android.database.CharArrayBuffer;
import com.google.android.gms.internal.dv;
import com.google.android.gms.internal.ee;
import com.google.android.gms.internal.ds;
import android.net.Uri;
import android.os.Parcelable$Creator;
import com.google.android.gms.internal.fm;

public final class PlayerEntity extends fm implements Player
{
    public static final Parcelable$Creator<PlayerEntity> CREATOR;
    private final int kg;
    private final String qa;
    private final Uri sL;
    private final Uri sM;
    private final String tC;
    private final long tD;
    private final int tE;
    private final long tF;
    
    static {
        CREATOR = (Parcelable$Creator)new a();
    }
    
    PlayerEntity(final int kg, final String tc, final String qa, final Uri sl, final Uri sm, final long td, final int te, final long tf) {
        this.kg = kg;
        this.tC = tc;
        this.qa = qa;
        this.sL = sl;
        this.sM = sm;
        this.tD = td;
        this.tE = te;
        this.tF = tf;
    }
    
    public PlayerEntity(final Player player) {
        this.kg = 3;
        this.tC = player.getPlayerId();
        this.qa = player.getDisplayName();
        this.sL = player.getIconImageUri();
        this.sM = player.getHiResImageUri();
        this.tD = player.getRetrievedTimestamp();
        this.tE = player.db();
        this.tF = player.getLastPlayedWithTimestamp();
        ds.d(this.tC);
        ds.d(this.qa);
        ds.p(this.tD > 0L);
    }
    
    static int a(final Player player) {
        return ee.hashCode(player.getPlayerId(), player.getDisplayName(), player.getIconImageUri(), player.getHiResImageUri(), player.getRetrievedTimestamp());
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
                if (ee.equal(player2.getPlayerId(), player.getPlayerId()) && ee.equal(player2.getDisplayName(), player.getDisplayName()) && ee.equal(player2.getIconImageUri(), player.getIconImageUri()) && ee.equal(player2.getHiResImageUri(), player.getHiResImageUri())) {
                    b2 = b;
                    if (ee.equal(player2.getRetrievedTimestamp(), player.getRetrievedTimestamp())) {
                        return b2;
                    }
                }
                return false;
            }
        }
        return b2;
    }
    
    static String b(final Player player) {
        return ee.e(player).a("PlayerId", player.getPlayerId()).a("DisplayName", player.getDisplayName()).a("IconImageUri", player.getIconImageUri()).a("HiResImageUri", player.getHiResImageUri()).a("RetrievedTimestamp", player.getRetrievedTimestamp()).toString();
    }
    
    @Override
    public int db() {
        return this.tE;
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
        return this.qa;
    }
    
    @Override
    public void getDisplayName(final CharArrayBuffer charArrayBuffer) {
        fc.b(this.qa, charArrayBuffer);
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
    public long getLastPlayedWithTimestamp() {
        return this.tF;
    }
    
    @Override
    public String getPlayerId() {
        return this.tC;
    }
    
    @Override
    public long getRetrievedTimestamp() {
        return this.tD;
    }
    
    public int getVersionCode() {
        return this.kg;
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
        if (!this.bN()) {
            c.a(this, parcel, n);
            return;
        }
        parcel.writeString(this.tC);
        parcel.writeString(this.qa);
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
            string2 = s;
        }
        else {
            string2 = this.sM.toString();
        }
        parcel.writeString(string2);
        parcel.writeLong(this.tD);
    }
    
    static final class a extends c
    {
        @Override
        public PlayerEntity Z(final Parcel parcel) {
            Uri parse = null;
            if (fm.c(dv.bM()) || dv.P(PlayerEntity.class.getCanonicalName())) {
                return super.Z(parcel);
            }
            final String string = parcel.readString();
            final String string2 = parcel.readString();
            final String string3 = parcel.readString();
            final String string4 = parcel.readString();
            Uri parse2;
            if (string3 == null) {
                parse2 = null;
            }
            else {
                parse2 = Uri.parse(string3);
            }
            if (string4 != null) {
                parse = Uri.parse(string4);
            }
            return new PlayerEntity(3, string, string2, parse2, parse, parcel.readLong(), -1, -1L);
        }
    }
}
