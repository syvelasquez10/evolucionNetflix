// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;

public enum OpacityMapping
{
    private static final String TAG = "nf_subtitles";
    
    none(0.0f, "NONE"), 
    opaque(1.0f, "OPAQUE"), 
    semiTransparent(0.5f, "SEMI_TRANSPARENT");
    
    private String mLookupValue;
    private Float mSize;
    
    private OpacityMapping(final Float mSize, final String mLookupValue) {
        this.mSize = mSize;
        this.mLookupValue = mLookupValue;
    }
    
    public static Float lookup(final String s) {
        Float n;
        if (StringUtils.isEmpty(s)) {
            n = null;
        }
        else {
            final OpacityMapping[] values = values();
            for (int length = values.length, i = 0; i < length; ++i) {
                final OpacityMapping opacityMapping = values[i];
                if (opacityMapping.mLookupValue.equalsIgnoreCase(s)) {
                    return opacityMapping.getSize();
                }
            }
            try {
                final Float value = Float.valueOf(s);
                if (value >= OpacityMapping.opaque.getSize()) {
                    return OpacityMapping.opaque.getSize();
                }
                n = value;
                if (value < OpacityMapping.none.getSize()) {
                    return OpacityMapping.none.getSize();
                }
            }
            catch (Throwable t) {
                Log.e("nf_subtitles", "Failed to parse opacityt value " + s, t);
                return null;
            }
        }
        return n;
    }
    
    public static String opacityToHex(final Float n) {
        if (n == null) {
            return "FF";
        }
        if (n <= 0.0f) {
            return "00";
        }
        if (n >= 1.0f) {
            return "FF";
        }
        return Integer.toHexString((int)(255.0f * n));
    }
    
    public String getLookupValue() {
        return this.mLookupValue;
    }
    
    public Float getSize() {
        return this.mSize;
    }
}
