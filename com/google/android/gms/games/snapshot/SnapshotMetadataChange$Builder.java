// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.snapshot;

import android.graphics.Bitmap;
import com.google.android.gms.common.data.a;
import android.net.Uri;

public final class SnapshotMetadataChange$Builder
{
    private String Tg;
    private Uri acZ;
    private Long adb;
    private a adc;
    
    public SnapshotMetadataChange build() {
        return new SnapshotMetadataChange(this.Tg, this.adb, this.adc, this.acZ);
    }
    
    public SnapshotMetadataChange$Builder fromMetadata(final SnapshotMetadata snapshotMetadata) {
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
    
    public SnapshotMetadataChange$Builder setCoverImage(final Bitmap bitmap) {
        this.adc = new a(bitmap);
        this.acZ = null;
        return this;
    }
    
    public SnapshotMetadataChange$Builder setDescription(final String tg) {
        this.Tg = tg;
        return this;
    }
    
    public SnapshotMetadataChange$Builder setPlayedTimeMillis(final long n) {
        this.adb = n;
        return this;
    }
}
