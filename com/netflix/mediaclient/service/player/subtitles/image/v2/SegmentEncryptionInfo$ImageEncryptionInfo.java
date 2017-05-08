// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles.image.v2;

import com.netflix.mediaclient.Log;
import java.io.DataInputStream;

public class SegmentEncryptionInfo$ImageEncryptionInfo
{
    private static final byte[] ZERO_AES_CBC_IV;
    private SegmentEncryptionInfo$EncryptionMode encryptionMode;
    private byte encryptionModeRaw;
    private byte[] iv;
    private byte ivSize;
    
    static {
        ZERO_AES_CBC_IV = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    }
    
    private SegmentEncryptionInfo$ImageEncryptionInfo(final DataInputStream dataInputStream, final byte b) {
        final int unsignedByte = dataInputStream.readUnsignedByte();
        this.ivSize = (byte)(unsignedByte & 0x3F);
        this.encryptionModeRaw = (byte)(unsignedByte >>> 6);
        this.initEncryptionMode();
        if (this.verifyIV(b)) {
            this.iv = ParserUtils.readByteArray(dataInputStream, this.ivSize);
            return;
        }
        Log.d("nf_subtitles_imv2", "IV size is 0, use 0 as IV, set to empty array...");
    }
    
    private void initEncryptionMode() {
        switch (this.encryptionModeRaw) {
            default: {
                this.encryptionMode = SegmentEncryptionInfo$EncryptionMode.NONE;
                Log.e("nf_subtitles_imv2", "ImageEncryptionInfo:: Not recognized encryption mode. We should never be here! " + this.encryptionModeRaw);
            }
            case 0: {
                this.encryptionMode = SegmentEncryptionInfo$EncryptionMode.NONE;
            }
            case 1: {
                this.encryptionMode = SegmentEncryptionInfo$EncryptionMode.AES_CTR;
            }
            case 2: {
                this.encryptionMode = SegmentEncryptionInfo$EncryptionMode.AES_CBC;
            }
            case 3: {
                this.encryptionMode = SegmentEncryptionInfo$EncryptionMode.RESERVED;
            }
        }
    }
    
    private boolean verifyIV(final byte ivSize) {
        final boolean b = true;
        if (this.ivSize == 0 && this.encryptionMode != SegmentEncryptionInfo$EncryptionMode.NONE) {
            if (Log.isLoggable()) {
                Log.d("nf_subtitles_imv2", "IV size is 0 and encryption mode is not NONE, using default IV size " + ivSize);
            }
            this.ivSize = ivSize;
        }
        if (this.ivSize != 0 && this.ivSize != 8 && this.ivSize != 16) {
            throw new IllegalStateException("Invalid IV size, must be 0, 8 or 16 and NOT " + this.ivSize);
        }
        if (this.ivSize == 0 && this.encryptionMode == SegmentEncryptionInfo$EncryptionMode.AES_CTR && ivSize == 0) {
            throw new IllegalStateException("Invalid IV size for AES-CTR, must be 8 or 16 and NOT " + this.ivSize);
        }
        boolean b2 = b;
        if (this.ivSize == 0) {
            b2 = b;
            if (this.encryptionMode == SegmentEncryptionInfo$EncryptionMode.AES_CBC) {
                b2 = b;
                if (ivSize == 0) {
                    this.ivSize = 16;
                    this.iv = SegmentEncryptionInfo$ImageEncryptionInfo.ZERO_AES_CBC_IV;
                    b2 = false;
                }
            }
        }
        return this.ivSize != 0 && b2;
    }
    
    public SegmentEncryptionInfo$EncryptionMode getEncryptionMode() {
        return this.encryptionMode;
    }
    
    public byte[] getIV() {
        return this.iv;
    }
    
    @Override
    public String toString() {
        return "ImageEncryptionInfo{encryptionModeRaw=" + this.encryptionModeRaw + ", ivSize=" + this.ivSize + ", encryptionMode=" + this.encryptionMode + '}';
    }
}
