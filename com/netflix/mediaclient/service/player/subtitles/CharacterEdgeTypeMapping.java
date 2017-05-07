// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles;

import com.netflix.mediaclient.util.StringUtils;

public enum CharacterEdgeTypeMapping
{
    DEPRESSED("DEPRESSED", Outline.Shadow.DEPRESSED), 
    DROP_SHADOW("DROP_SHADOW", Outline.Shadow.DROP_SHADOW), 
    NONE("NONE", (Outline.Shadow)null), 
    RAISED("RAISED", Outline.Shadow.RAISED), 
    UNIFORM("UNIFORM", Outline.Shadow.UNIFORM);
    
    private String mLookupValue;
    private Outline.Shadow mShadow;
    
    private CharacterEdgeTypeMapping(final String mLookupValue, final Outline.Shadow mShadow) {
        this.mLookupValue = mLookupValue;
        this.mShadow = mShadow;
    }
    
    public static CharacterEdgeTypeMapping lookup(final String s) {
        if (!StringUtils.isEmpty(s)) {
            final CharacterEdgeTypeMapping[] values = values();
            for (int length = values.length, i = 0; i < length; ++i) {
                final CharacterEdgeTypeMapping characterEdgeTypeMapping;
                if ((characterEdgeTypeMapping = values[i]).mLookupValue.equalsIgnoreCase(s)) {
                    return characterEdgeTypeMapping;
                }
            }
            return null;
        }
        return null;
    }
    
    public String getLookupValue() {
        return this.mLookupValue;
    }
    
    public Outline.Shadow getShadow() {
        return this.mShadow;
    }
    
    @Override
    public String toString() {
        return this.mLookupValue;
    }
}
