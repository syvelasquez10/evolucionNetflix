// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.snapshot;

import android.os.Parcel;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.Game;
import com.google.android.gms.internal.jv;
import android.database.CharArrayBuffer;
import com.google.android.gms.common.internal.m;
import com.google.android.gms.games.PlayerEntity;
import android.net.Uri;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class SnapshotMetadataEntity implements SafeParcelable, SnapshotMetadata
{
    public static final SnapshotMetadataEntityCreator CREATOR;
    private final int BR;
    private final String No;
    private final String Tg;
    private final String Wx;
    private final GameEntity aan;
    private final Uri acZ;
    private final PlayerEntity add;
    private final String ade;
    private final long adf;
    private final long adg;
    private final float adh;
    private final String adi;
    
    static {
        CREATOR = new SnapshotMetadataEntityCreator();
    }
    
    SnapshotMetadataEntity(final int br, final GameEntity aan, final PlayerEntity add, final String wx, final Uri acZ, final String ade, final String no, final String tg, final long adf, final long adg, final float adh, final String adi) {
        this.BR = br;
        this.aan = aan;
        this.add = add;
        this.Wx = wx;
        this.acZ = acZ;
        this.ade = ade;
        this.adh = adh;
        this.No = no;
        this.Tg = tg;
        this.adf = adf;
        this.adg = adg;
        this.adi = adi;
    }
    
    public SnapshotMetadataEntity(final SnapshotMetadata snapshotMetadata) {
        this.BR = 3;
        this.aan = new GameEntity(snapshotMetadata.getGame());
        this.add = new PlayerEntity(snapshotMetadata.getOwner());
        this.Wx = snapshotMetadata.getSnapshotId();
        this.acZ = snapshotMetadata.getCoverImageUri();
        this.ade = snapshotMetadata.getCoverImageUrl();
        this.adh = snapshotMetadata.getCoverImageAspectRatio();
        this.No = snapshotMetadata.getTitle();
        this.Tg = snapshotMetadata.getDescription();
        this.adf = snapshotMetadata.getLastModifiedTimestamp();
        this.adg = snapshotMetadata.getPlayedTime();
        this.adi = snapshotMetadata.getUniqueName();
    }
    
    static int a(final SnapshotMetadata snapshotMetadata) {
        return m.hashCode(snapshotMetadata.getGame(), snapshotMetadata.getOwner(), snapshotMetadata.getSnapshotId(), snapshotMetadata.getCoverImageUri(), snapshotMetadata.getCoverImageAspectRatio(), snapshotMetadata.getTitle(), snapshotMetadata.getDescription(), snapshotMetadata.getLastModifiedTimestamp(), snapshotMetadata.getPlayedTime(), snapshotMetadata.getUniqueName());
    }
    
    static boolean a(final SnapshotMetadata snapshotMetadata, final Object o) {
        final boolean b = true;
        boolean b2;
        if (!(o instanceof SnapshotMetadata)) {
            b2 = false;
        }
        else {
            b2 = b;
            if (snapshotMetadata != o) {
                final SnapshotMetadata snapshotMetadata2 = (SnapshotMetadata)o;
                if (m.equal(snapshotMetadata2.getGame(), snapshotMetadata.getGame()) && m.equal(snapshotMetadata2.getOwner(), snapshotMetadata.getOwner()) && m.equal(snapshotMetadata2.getSnapshotId(), snapshotMetadata.getSnapshotId()) && m.equal(snapshotMetadata2.getCoverImageUri(), snapshotMetadata.getCoverImageUri()) && m.equal(snapshotMetadata2.getCoverImageAspectRatio(), snapshotMetadata.getCoverImageAspectRatio()) && m.equal(snapshotMetadata2.getTitle(), snapshotMetadata.getTitle()) && m.equal(snapshotMetadata2.getDescription(), snapshotMetadata.getDescription()) && m.equal(snapshotMetadata2.getLastModifiedTimestamp(), snapshotMetadata.getLastModifiedTimestamp()) && m.equal(snapshotMetadata2.getPlayedTime(), snapshotMetadata.getPlayedTime())) {
                    b2 = b;
                    if (m.equal(snapshotMetadata2.getUniqueName(), snapshotMetadata.getUniqueName())) {
                        return b2;
                    }
                }
                return false;
            }
        }
        return b2;
    }
    
    static String b(final SnapshotMetadata snapshotMetadata) {
        return m.h(snapshotMetadata).a("Game", snapshotMetadata.getGame()).a("Owner", snapshotMetadata.getOwner()).a("SnapshotId", snapshotMetadata.getSnapshotId()).a("CoverImageUri", snapshotMetadata.getCoverImageUri()).a("CoverImageUrl", snapshotMetadata.getCoverImageUrl()).a("CoverImageAspectRatio", snapshotMetadata.getCoverImageAspectRatio()).a("Description", snapshotMetadata.getDescription()).a("LastModifiedTimestamp", snapshotMetadata.getLastModifiedTimestamp()).a("PlayedTime", snapshotMetadata.getPlayedTime()).a("UniqueName", snapshotMetadata.getUniqueName()).toString();
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return a(this, o);
    }
    
    public SnapshotMetadata freeze() {
        return this;
    }
    
    @Override
    public float getCoverImageAspectRatio() {
        return this.adh;
    }
    
    @Override
    public Uri getCoverImageUri() {
        return this.acZ;
    }
    
    @Override
    public String getCoverImageUrl() {
        return this.ade;
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
    public Game getGame() {
        return this.aan;
    }
    
    @Override
    public long getLastModifiedTimestamp() {
        return this.adf;
    }
    
    @Override
    public Player getOwner() {
        return this.add;
    }
    
    @Override
    public long getPlayedTime() {
        return this.adg;
    }
    
    @Override
    public String getSnapshotId() {
        return this.Wx;
    }
    
    @Override
    public String getTitle() {
        return this.No;
    }
    
    @Override
    public String getUniqueName() {
        return this.adi;
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
    public String toString() {
        return b(this);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        SnapshotMetadataEntityCreator.a(this, parcel, n);
    }
}
