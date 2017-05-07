// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.snapshot;

import android.os.Parcel;
import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.games.PlayerRef;
import com.google.android.gms.games.GameRef;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.Game;
import com.google.android.gms.common.data.d;

public final class SnapshotMetadataRef extends d implements SnapshotMetadata
{
    private final Game abm;
    private final Player adj;
    
    public SnapshotMetadataRef(final DataHolder dataHolder, final int n) {
        super(dataHolder, n);
        this.abm = new GameRef(dataHolder, n);
        this.adj = new PlayerRef(dataHolder, n);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return SnapshotMetadataEntity.a(this, o);
    }
    
    public SnapshotMetadata freeze() {
        return new SnapshotMetadataEntity(this);
    }
    
    @Override
    public float getCoverImageAspectRatio() {
        final float float1 = this.getFloat("cover_icon_image_height");
        final float float2 = this.getFloat("cover_icon_image_width");
        if (float1 == 0.0f) {
            return 0.0f;
        }
        return float2 / float1;
    }
    
    @Override
    public Uri getCoverImageUri() {
        return this.aR("cover_icon_image_uri");
    }
    
    @Override
    public String getCoverImageUrl() {
        return this.getString("cover_icon_image_url");
    }
    
    @Override
    public String getDescription() {
        return this.getString("description");
    }
    
    @Override
    public void getDescription(final CharArrayBuffer charArrayBuffer) {
        this.a("description", charArrayBuffer);
    }
    
    @Override
    public Game getGame() {
        return this.abm;
    }
    
    @Override
    public long getLastModifiedTimestamp() {
        return this.getLong("last_modified_timestamp");
    }
    
    @Override
    public Player getOwner() {
        return this.adj;
    }
    
    @Override
    public long getPlayedTime() {
        return this.getLong("duration");
    }
    
    @Override
    public String getSnapshotId() {
        return this.getString("external_snapshot_id");
    }
    
    @Override
    public String getTitle() {
        return this.getString("title");
    }
    
    @Override
    public String getUniqueName() {
        return this.getString("unique_name");
    }
    
    @Override
    public int hashCode() {
        return SnapshotMetadataEntity.a(this);
    }
    
    @Override
    public String toString() {
        return SnapshotMetadataEntity.b(this);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        ((SnapshotMetadataEntity)this.freeze()).writeToParcel(parcel, n);
    }
}
