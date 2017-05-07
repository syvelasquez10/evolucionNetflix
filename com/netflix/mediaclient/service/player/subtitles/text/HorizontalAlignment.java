// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles.text;

import com.netflix.mediaclient.util.StringUtils;

public enum HorizontalAlignment
{
    center("center", 1), 
    left("left", 3), 
    right("right", 5);
    
    private int mGravity;
    private String mValue;
    
    private HorizontalAlignment(final String mValue, final int mGravity) {
        this.mValue = mValue;
        this.mGravity = mGravity;
    }
    
    public static HorizontalAlignment from(final String s) {
        HorizontalAlignment left;
        if (StringUtils.isEmpty(s)) {
            left = HorizontalAlignment.left;
        }
        else {
            final String trim = s.trim();
            final HorizontalAlignment[] values = values();
            for (int length = values.length, i = 0; i < length; ++i) {
                if ((left = values[i]).getValue().equalsIgnoreCase(trim)) {
                    return left;
                }
            }
            if ("right".equalsIgnoreCase(trim)) {
                return HorizontalAlignment.right;
            }
            return HorizontalAlignment.left;
        }
        return left;
    }
    
    public int getGravity() {
        return this.mGravity;
    }
    
    public String getValue() {
        return this.mValue;
    }
    
    @Override
    public String toString() {
        return this.mValue;
    }
}
