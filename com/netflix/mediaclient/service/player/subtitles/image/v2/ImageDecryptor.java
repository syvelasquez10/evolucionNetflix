// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles.image.v2;

public interface ImageDecryptor
{
    byte[] decrypt(final byte[] p0, final SegmentEncryptionInfo$ImageEncryptionInfo p1, final String p2, final int p3);
}
