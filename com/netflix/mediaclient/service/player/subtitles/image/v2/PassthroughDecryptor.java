// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles.image.v2;

import com.netflix.mediaclient.Log;

public final class PassthroughDecryptor implements ImageDecryptor
{
    protected static final String TAG = "nf_subtitles_imv2";
    
    @Override
    public byte[] decrypt(final byte[] array, final SegmentEncryptionInfo$ImageEncryptionInfo segmentEncryptionInfo$ImageEncryptionInfo, final String s, final int n) {
        Log.d("nf_subtitles_imv2", "PassthroughDecryptor::decrypt: returning passed image");
        return array;
    }
}
