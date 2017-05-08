// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles.image.v2;

import com.netflix.mediaclient.util.Base64;
import com.netflix.mediaclient.Log;
import java.security.spec.AlgorithmParameterSpec;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AesCtrImageDecryptor implements ImageDecryptor
{
    protected static final String TAG = "nf_subtitles_imv2";
    
    private static byte[] decrypt(final byte[] array, final byte[] array2, final byte[] array3) {
        final SecretKeySpec secretKeySpec = new SecretKeySpec(array, "AES");
        final IvParameterSpec ivParameterSpec = new IvParameterSpec(array3);
        final Cipher instance = Cipher.getInstance("AES/CTR/NoPadding", "BC");
        instance.init(2, secretKeySpec, ivParameterSpec);
        return instance.doFinal(array2);
    }
    
    @Override
    public byte[] decrypt(final byte[] array, final SegmentEncryptionInfo$ImageEncryptionInfo segmentEncryptionInfo$ImageEncryptionInfo, final String s, int i) {
        Log.d("nf_subtitles_imv2", "AesCtrImageDecryptor::decrypt: starts...");
        final byte[] decode = Base64.decode(s);
        final byte[] iv = segmentEncryptionInfo$ImageEncryptionInfo.getIV();
        byte[] array3;
        if (iv.length == 8) {
            if (Log.isLoggable()) {
                Log.d("nf_subtitles_imv2", "AesCtrImageDecryptor:: IV is 8 bytes long, we need 16 bytes.");
            }
            final byte[] array2 = new byte[16];
            for (i = 0; i < segmentEncryptionInfo$ImageEncryptionInfo.getIV().length; ++i) {
                array2[i] = segmentEncryptionInfo$ImageEncryptionInfo.getIV()[i];
                array2[i + 8] = 0;
            }
            array3 = array2;
        }
        else {
            array3 = iv;
            if (Log.isLoggable()) {
                Log.d("nf_subtitles_imv2", "AesCtrImageDecryptor:: IV is " + iv.length + " bytes long, we need 16 bytes");
                array3 = iv;
            }
        }
        return decrypt(decode, array, array3);
    }
}
