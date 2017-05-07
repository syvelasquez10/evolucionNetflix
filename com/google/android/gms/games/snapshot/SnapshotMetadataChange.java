// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.snapshot;

import android.os.Parcel;
import android.graphics.Bitmap;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.data.a;
import android.net.Uri;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class SnapshotMetadataChange implements SafeParcelable
{
    public static final SnapshotMetadataChangeCreator CREATOR;
    public static final SnapshotMetadataChange EMPTY_CHANGE;
    private final int BR;
    private final String Tg;
    private final Long acY;
    private final Uri acZ;
    private a ada;
    
    static {
        CREATOR = new SnapshotMetadataChangeCreator();
        EMPTY_CHANGE = new SnapshotMetadataChange();
    }
    
    SnapshotMetadataChange() {
        this(4, null, null, null, null);
    }
    
    SnapshotMetadataChange(final int br, final String tg, final Long acY, final a ada, final Uri acZ) {
        final boolean b = true;
        boolean b2 = true;
        this.BR = br;
        this.Tg = tg;
        this.acY = acY;
        this.ada = ada;
        this.acZ = acZ;
        if (this.ada != null) {
            if (this.acZ != null) {
                b2 = false;
            }
            n.a(b2, (Object)"Cannot set both a URI and an image");
        }
        else if (this.acZ != null) {
            n.a(this.ada == null && b, (Object)"Cannot set both a URI and an image");
        }
    }
    
    SnapshotMetadataChange(final String s, final Long n, final a a, final Uri uri) {
        this(4, s, n, a, uri);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public Bitmap getCoverImage() {
        if (this.ada == null) {
            return null;
        }
        return this.ada.gx();
    }
    
    public Uri getCoverImageUri() {
        return this.acZ;
    }
    
    public String getDescription() {
        return this.Tg;
    }
    
    public Long getPlayedTimeMillis() {
        return this.acY;
    }
    
    public int getVersionCode() {
        return this.BR;
    }
    
    public a lK() {
        return this.ada;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        SnapshotMetadataChangeCreator.a(this, parcel, n);
    }
    
    public static final class Builder
    {
        private String Tg;
        private Uri acZ;
        private Long adb;
        private a adc;
        
        public SnapshotMetadataChange build() {
            return new SnapshotMetadataChange(this.Tg, this.adb, this.adc, this.acZ);
        }
        
        public Builder fromMetadata(final SnapshotMetadata snapshotMetadata) {
            this.Tg = snapshotMetadata.getDescription();
            this.adb = snapshotMetadata.getPlayedTime();
            if (this.adb == -1L) {
                this.adb = null;
            }
            this.acZ = snapshotMetadata.getCoverImageUri();
            if (this.acZ != null) {
                this.adc = null;
            }
            return this;
        }
        
        public Builder setCoverImage(final Bitmap bitmap) {
            this.adc = new a(bitmap);
            this.acZ = null;
            return this;
        }
        
        public Builder setDescription(final String tg) {
            this.Tg = tg;
            return this;
        }
        
        public Builder setPlayedTimeMillis(final long n) {
            this.adb = n;
            return this;
        }
    }
}
