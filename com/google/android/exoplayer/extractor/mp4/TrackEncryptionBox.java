// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.extractor.mp4;

public final class TrackEncryptionBox
{
    public final int initializationVectorSize;
    public final boolean isEncrypted;
    public final byte[] keyId;
    
    public TrackEncryptionBox(final boolean isEncrypted, final int initializationVectorSize, final byte[] keyId) {
        this.isEncrypted = isEncrypted;
        this.initializationVectorSize = initializationVectorSize;
        this.keyId = keyId;
    }
}
