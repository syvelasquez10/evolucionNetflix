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

public final class d extends b implements Player
{
    private final a tG;
    
    public d(final DataHolder dataHolder, final int n) {
        this(dataHolder, n, null);
    }
    
    public d(final DataHolder dataHolder, final int n, final String s) {
        super(dataHolder, n);
        this.tG = new a(s);
    }
    
    @Override
    public int db() {
        return this.getInteger(this.tG.tM);
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
        return this.getString(this.tG.tI);
    }
    
    @Override
    public void getDisplayName(final CharArrayBuffer charArrayBuffer) {
        this.a(this.tG.tI, charArrayBuffer);
    }
    
    @Override
    public Uri getHiResImageUri() {
        return this.L(this.tG.tK);
    }
    
    @Override
    public Uri getIconImageUri() {
        return this.L(this.tG.tJ);
    }
    
    @Override
    public long getLastPlayedWithTimestamp() {
        if (!this.hasColumn(this.tG.tN)) {
            return -1L;
        }
        return this.getLong(this.tG.tN);
    }
    
    @Override
    public String getPlayerId() {
        return this.getString(this.tG.tH);
    }
    
    @Override
    public long getRetrievedTimestamp() {
        return this.getLong(this.tG.tL);
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
    
    private static final class a
    {
        public final String tH;
        public final String tI;
        public final String tJ;
        public final String tK;
        public final String tL;
        public final String tM;
        public final String tN;
        
        public a(final String s) {
            if (TextUtils.isEmpty((CharSequence)s)) {
                this.tH = "external_player_id";
                this.tI = "profile_name";
                this.tJ = "profile_icon_image_uri";
                this.tK = "profile_hi_res_image_uri";
                this.tL = "last_updated";
                this.tM = "is_in_circles";
                this.tN = "played_with_timestamp";
                return;
            }
            this.tH = s + "external_player_id";
            this.tI = s + "profile_name";
            this.tJ = s + "profile_icon_image_uri";
            this.tK = s + "profile_hi_res_image_uri";
            this.tL = s + "last_updated";
            this.tM = s + "is_in_circles";
            this.tN = s + "played_with_timestamp";
        }
    }
}
