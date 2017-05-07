// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles;

import com.netflix.mediaclient.util.StringUtils;

public enum SizeMapping
{
    large(200, "LARGE"), 
    medium(100, "MEDIUM"), 
    small(75, "SMALL");
    
    private String mLookupValue;
    private int mSize;
    
    private SizeMapping(final int mSize, final String mLookupValue) {
        this.mSize = mSize;
        this.mLookupValue = mLookupValue;
    }
    
    public static int lookup(final String s) {
        if (StringUtils.isEmpty(s)) {
            return SizeMapping.medium.getSize();
        }
        final SizeMapping[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final SizeMapping sizeMapping = values[i];
            if (sizeMapping.mLookupValue.equalsIgnoreCase(s)) {
                return sizeMapping.mSize;
            }
        }
        return SizeMapping.medium.getSize();
    }
    
    public String getLookupValue() {
        return this.mLookupValue;
    }
    
    public int getSize() {
        return this.mSize;
    }
}
