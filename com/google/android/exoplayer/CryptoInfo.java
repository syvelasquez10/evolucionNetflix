// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer;

import android.annotation.TargetApi;
import com.google.android.exoplayer.util.Util;
import android.media.MediaCodec$CryptoInfo;

public final class CryptoInfo
{
    private final MediaCodec$CryptoInfo frameworkCryptoInfo;
    public byte[] iv;
    public byte[] key;
    public int mode;
    public int[] numBytesOfClearData;
    public int[] numBytesOfEncryptedData;
    public int numSubSamples;
    
    public CryptoInfo() {
        MediaCodec$CryptoInfo frameworkCryptoInfoV16;
        if (Util.SDK_INT >= 16) {
            frameworkCryptoInfoV16 = this.newFrameworkCryptoInfoV16();
        }
        else {
            frameworkCryptoInfoV16 = null;
        }
        this.frameworkCryptoInfo = frameworkCryptoInfoV16;
    }
    
    @TargetApi(16)
    private MediaCodec$CryptoInfo newFrameworkCryptoInfoV16() {
        return new MediaCodec$CryptoInfo();
    }
    
    @TargetApi(16)
    private void updateFrameworkCryptoInfoV16() {
        this.frameworkCryptoInfo.set(this.numSubSamples, this.numBytesOfClearData, this.numBytesOfEncryptedData, this.key, this.iv, this.mode);
    }
    
    @TargetApi(16)
    public MediaCodec$CryptoInfo getFrameworkCryptoInfoV16() {
        return this.frameworkCryptoInfo;
    }
    
    public void set(final int numSubSamples, final int[] numBytesOfClearData, final int[] numBytesOfEncryptedData, final byte[] key, final byte[] iv, final int mode) {
        this.numSubSamples = numSubSamples;
        this.numBytesOfClearData = numBytesOfClearData;
        this.numBytesOfEncryptedData = numBytesOfEncryptedData;
        this.key = key;
        this.iv = iv;
        this.mode = mode;
        if (Util.SDK_INT >= 16) {
            this.updateFrameworkCryptoInfoV16();
        }
    }
}
