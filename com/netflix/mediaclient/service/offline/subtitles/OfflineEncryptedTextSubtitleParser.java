// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.subtitles;

import com.netflix.mediaclient.util.FileUtils;
import java.io.File;
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
import com.netflix.mediaclient.ui.offline.OfflineSubtitle;
import com.netflix.mediaclient.servicemgr.IPlayer;

public class OfflineEncryptedTextSubtitleParser extends OfflineTextSubtitleParser
{
    public OfflineEncryptedTextSubtitleParser(final IPlayer player, final OfflineSubtitle offlineSubtitle, final TextStyle textStyle, final TextStyle textStyle2, final float n) {
        super(player, offlineSubtitle, textStyle, textStyle2, n);
        Log.d("nf_subtitles", "Create encrypted offline text based subtitle parser");
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
        Log.d("nf_subtitles", "Check if cache exist!");
        final File file = new File(this.mOfflineSubtitle.getLocalFilePath());
        if (file != null && file.exists()) {
            if (Log.isLoggable()) {
                Log.d("nf_subtitles", "File " + file.getAbsolutePath() + " exist");
            }
            try {
                final byte[] fileToByteArray = FileUtils.readFileToByteArray(file);
                Log.d("nf_subtitles", "Importing subtitles metadata from offline directory size [bytes] %d", fileToByteArray.length);
                this.parse(this.decrypt(fileToByteArray));
                Log.d("nf_subtitles", "Imported enc data from offline directory!");
                return true;
            }
            catch (Throwable t) {
                Log.e("nf_subtitles", "We failed to parse subtitle metadata from cached file", t);
            }
        }
        else {
            Log.e("nf_subtitles", "Offline text subtitle NOT found at " + this.mOfflineSubtitle.getLocalFilePath());
        }
        return false;
    }
}
