// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer;

import android.text.TextUtils;

final class MediaCodecUtil$CodecKey
{
    public final String mimeType;
    public final boolean secure;
    
    public MediaCodecUtil$CodecKey(final String mimeType, final boolean secure) {
        this.mimeType = mimeType;
        this.secure = secure;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null || o.getClass() != MediaCodecUtil$CodecKey.class) {
                return false;
            }
            final MediaCodecUtil$CodecKey mediaCodecUtil$CodecKey = (MediaCodecUtil$CodecKey)o;
            if (!TextUtils.equals((CharSequence)this.mimeType, (CharSequence)mediaCodecUtil$CodecKey.mimeType) || this.secure != mediaCodecUtil$CodecKey.secure) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        int hashCode;
        if (this.mimeType == null) {
            hashCode = 0;
        }
        else {
            hashCode = this.mimeType.hashCode();
        }
        int n;
        if (this.secure) {
            n = 1231;
        }
        else {
            n = 1237;
        }
        return n + (hashCode + 31) * 31;
    }
}
