// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles.image.v2;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public final class ImageDecryptorFactory
{
    protected static final String TAG = "nf_subtitles_imv2";
    private static Map<SegmentEncryptionInfo$EncryptionMode, ImageDecryptor> sDecryptors;
    
    static {
        (ImageDecryptorFactory.sDecryptors = new ConcurrentHashMap<SegmentEncryptionInfo$EncryptionMode, ImageDecryptor>()).put(SegmentEncryptionInfo$EncryptionMode.NONE, new PassthroughDecryptor());
        ImageDecryptorFactory.sDecryptors.put(SegmentEncryptionInfo$EncryptionMode.AES_CBC, new AesCbcImageDecryptor());
        ImageDecryptorFactory.sDecryptors.put(SegmentEncryptionInfo$EncryptionMode.AES_CTR, new AesCtrImageDecryptor());
    }
    
    public static ImageDecryptor getDecryptor(final SegmentEncryptionInfo$ImageEncryptionInfo segmentEncryptionInfo$ImageEncryptionInfo) {
        ImageDecryptor imageDecryptor;
        if (segmentEncryptionInfo$ImageEncryptionInfo == null) {
            imageDecryptor = ImageDecryptorFactory.sDecryptors.get(SegmentEncryptionInfo$EncryptionMode.NONE);
        }
        else {
            if (segmentEncryptionInfo$ImageEncryptionInfo.getEncryptionMode() == null) {
                throw new IllegalArgumentException("Missing enc mode!");
            }
            if ((imageDecryptor = ImageDecryptorFactory.sDecryptors.get(segmentEncryptionInfo$ImageEncryptionInfo.getEncryptionMode())) == null) {
                throw new IllegalArgumentException("Encryption mode " + segmentEncryptionInfo$ImageEncryptionInfo.getEncryptionMode() + " not supported!");
            }
        }
        return imageDecryptor;
    }
}
