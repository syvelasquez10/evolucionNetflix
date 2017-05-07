// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.ui;

import com.netflix.mediaclient.util.StringUtils;

public enum IMedia$SubtitleProfile
{
    ENHANCED(1, "dfxp-ls-sdh"), 
    SIMPLE(0, "simplesdh");
    
    private String mNccpCode;
    private int mValue;
    
    private IMedia$SubtitleProfile(final int mValue, final String mNccpCode) {
        this.mValue = mValue;
        this.mNccpCode = mNccpCode;
    }
    
    public static IMedia$SubtitleProfile fromNccpCode(final String s) {
        if (!StringUtils.isEmpty(s)) {
            final String trim = s.trim();
            final IMedia$SubtitleProfile[] values = values();
            for (int length = values.length, i = 0; i < length; ++i) {
                final IMedia$SubtitleProfile simple;
                if ((simple = values[i]).getNccpCode().equalsIgnoreCase(trim)) {
                    return simple;
                }
            }
            return IMedia$SubtitleProfile.SIMPLE;
        }
        return IMedia$SubtitleProfile.SIMPLE;
    }
    
    public String getNccpCode() {
        return this.mNccpCode;
    }
    
    public final int getValue() {
        return this.mValue;
    }
}
