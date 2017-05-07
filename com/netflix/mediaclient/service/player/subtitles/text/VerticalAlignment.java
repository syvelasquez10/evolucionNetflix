// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles.text;

import com.netflix.mediaclient.util.StringUtils;

public enum VerticalAlignment
{
    bottom("bottom", 80), 
    center("center", 16), 
    top("top", 48);
    
    private int mGravity;
    private String mValue;
    
    private VerticalAlignment(final String mValue, final int mGravity) {
        this.mValue = mValue;
        this.mGravity = mGravity;
    }
    
    public static VerticalAlignment from(final String s) {
        VerticalAlignment top;
        if (StringUtils.isEmpty(s)) {
            top = VerticalAlignment.top;
        }
        else {
            final String trim = s.trim();
            final VerticalAlignment[] values = values();
            for (int length = values.length, i = 0; i < length; ++i) {
                if ((top = values[i]).getValue().equalsIgnoreCase(trim)) {
                    return top;
                }
            }
            if ("after".equalsIgnoreCase(trim)) {
                return VerticalAlignment.bottom;
            }
            return VerticalAlignment.top;
        }
        return top;
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
