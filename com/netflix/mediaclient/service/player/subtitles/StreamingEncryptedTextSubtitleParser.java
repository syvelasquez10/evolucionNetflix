// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles;

import java.util.Arrays;
import java.security.spec.AlgorithmParameterSpec;
import java.security.Key;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import com.netflix.mediaclient.util.Base64;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.player.subtitles.text.TextStyle;
import com.netflix.mediaclient.media.SubtitleUrl;
import com.netflix.mediaclient.servicemgr.IPlayer;

public class StreamingEncryptedTextSubtitleParser extends StreamingTextSubtitleParser
{
    public StreamingEncryptedTextSubtitleParser(final IPlayer player, final SubtitleUrl subtitleUrl, final TextStyle textStyle, final TextStyle textStyle2, final float n, final SubtitleParser$DownloadFailedCallback subtitleParser$DownloadFailedCallback, final long n2) {
        super(player, subtitleUrl, textStyle, textStyle2, n, subtitleParser$DownloadFailedCallback, n2);
        Log.d("nf_subtitles", "Create encrypted text based subtitle parser");
    }
    
    private String decrypt(final byte[] array) {
        final String decryptionKey = this.mSubtitleData.getDecryptionKey();
        final byte[] decode = Base64.decode(decryptionKey);
        final byte[] iv = this.getIV(array);
        if (Log.isLoggable()) {
            Log.d("nf_subtitles", "TO REMOVE: decryption key: " + decryptionKey);
            Log.d("nf_subtitles", "TO REMOVE: IV: size " + iv.length + ", value: " + Base64.encodeBytes(iv));
        }
        final String s = new String(decrypt(decode, this.getEnc(array), iv), Charset.forName("UTF-8"));
        if (Log.isLoggable()) {
            Log.d("nf_subtitles", "XML: " + s);
        }
        return s;
    }
    
    private static byte[] decrypt(final byte[] array, final byte[] array2, final byte[] array3) {
        final SecretKeySpec secretKeySpec = new SecretKeySpec(array, "AES");
        final Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        instance.init(2, secretKeySpec, new IvParameterSpec(array3));
        return instance.doFinal(array2);
    }
    
    private byte[] getEnc(final byte[] array) {
        return Arrays.copyOfRange(array, 16, array.length);
    }
    
    private byte[] getIV(final byte[] array) {
        return Arrays.copyOf(array, 16);
    }
    
    @Override
    protected boolean handleImport() {
        return false;
    }
    
    @Override
    protected void injectContent(final byte[] array) {
        if (Log.isLoggable()) {
            Log.d("nf_subtitles", "Downloaded encrypyed subtitles metadata, size [bytes]:\n" + array.length);
        }
        this.parse(this.decrypt(array));
    }
}
