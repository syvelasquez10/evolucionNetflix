// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games;

import android.text.TextUtils;
import android.os.Parcel;
import android.net.Uri;
import android.database.CharArrayBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.b;

public final class PlayerRef extends b implements Player
{
    private final PlayerColumnNames Ii;
    
    public PlayerRef(final DataHolder dataHolder, final int n) {
        this(dataHolder, n, null);
    }
    
    public PlayerRef(final DataHolder dataHolder, final int n, final String s) {
        super(dataHolder, n);
        this.Ii = new PlayerColumnNames(s);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return PlayerEntity.a(this, o);
    }
    
    public Player freeze() {
        return new PlayerEntity(this);
    }
    
    @Override
    public String getDisplayName() {
        return this.getString(this.Ii.Ik);
    }
    
    @Override
    public void getDisplayName(final CharArrayBuffer charArrayBuffer) {
        this.a(this.Ii.Ik, charArrayBuffer);
    }
    
    @Override
    public Uri getHiResImageUri() {
        return this.ah(this.Ii.In);
    }
    
    @Override
    public String getHiResImageUrl() {
        return this.getString(this.Ii.Io);
    }
    
    @Override
    public Uri getIconImageUri() {
        return this.ah(this.Ii.Il);
    }
    
    @Override
    public String getIconImageUrl() {
        return this.getString(this.Ii.Im);
    }
    
    @Override
    public long getLastPlayedWithTimestamp() {
        if (!this.hasColumn(this.Ii.Ir)) {
            return -1L;
        }
        return this.getLong(this.Ii.Ir);
    }
    
    @Override
    public String getPlayerId() {
        return this.getString(this.Ii.Ij);
    }
    
    @Override
    public long getRetrievedTimestamp() {
        return this.getLong(this.Ii.Ip);
    }
    
    @Override
    public int gh() {
        return this.getInteger(this.Ii.Iq);
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
        return PlayerEntity.a(this);
    }
    
    @Override
    public String toString() {
        return PlayerEntity.b(this);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        ((PlayerEntity)this.freeze()).writeToParcel(parcel, n);
    }
    
    private static final class PlayerColumnNames
    {
        public final String Ij;
        public final String Ik;
        public final String Il;
        public final String Im;
        public final String In;
        public final String Io;
        public final String Ip;
        public final String Iq;
        public final String Ir;
        
        public PlayerColumnNames(final String s) {
            if (TextUtils.isEmpty((CharSequence)s)) {
                this.Ij = "external_player_id";
                this.Ik = "profile_name";
                this.Il = "profile_icon_image_uri";
                this.Im = "profile_icon_image_url";
                this.In = "profile_hi_res_image_uri";
                this.Io = "profile_hi_res_image_url";
                this.Ip = "last_updated";
                this.Iq = "is_in_circles";
                this.Ir = "played_with_timestamp";
                return;
            }
            this.Ij = s + "external_player_id";
            this.Ik = s + "profile_name";
            this.Il = s + "profile_icon_image_uri";
            this.Im = s + "profile_icon_image_url";
            this.In = s + "profile_hi_res_image_uri";
            this.Io = s + "profile_hi_res_image_url";
            this.Ip = s + "last_updated";
            this.Iq = s + "is_in_circles";
            this.Ir = s + "played_with_timestamp";
        }
    }
}
