// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles.image.v2;

import com.netflix.mediaclient.util.Base64;
import com.netflix.mediaclient.Log;
import java.security.spec.AlgorithmParameterSpec;
import java.security.Key;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import com.netflix.mediaclient.util.CryptoUtils;

public class AesCbcImageDecryptor implements ImageDecryptor
{
    protected static final String TAG = "nf_subtitles_imv2";
    
    private static byte[] decrypt(final byte[] array, byte[] padPerPKCS5Padding, final byte[] array2) {
        padPerPKCS5Padding = CryptoUtils.padPerPKCS5Padding(padPerPKCS5Padding, 16);
        final SecretKeySpec secretKeySpec = new SecretKeySpec(array, "AES");
        final Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        instance.init(2, secretKeySpec, new IvParameterSpec(array2));
        return instance.doFinal(padPerPKCS5Padding);
    }
    
    @Override
    public byte[] decrypt(final byte[] array, final SegmentEncryptionInfo$ImageEncryptionInfo segmentEncryptionInfo$ImageEncryptionInfo, final String s, final int n) {
        Log.d("nf_subtitles_imv2", "AesCbcImageDecryptor::decrypt: starts...");
        final byte[] decode = Base64.decode(s);
        final byte[] iv = segmentEncryptionInfo$ImageEncryptionInfo.getIV();
        if (Log.isLoggable()) {
            Log.d("nf_subtitles_imv2", "TO REMOVE: decryption key: " + s);
            Log.d("nf_subtitles_imv2", "TO REMOVE: IV: size " + iv.length + ", value: " + CryptoUtils.toHex(iv));
        }
        return decrypt(decode, array, iv);
    }
}
