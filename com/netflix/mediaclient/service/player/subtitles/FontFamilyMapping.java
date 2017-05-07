// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles;

import com.netflix.mediaclient.util.StringUtils;
import android.graphics.Typeface;

public enum FontFamilyMapping
{
    casual(Typeface.SANS_SERIF, "casual"), 
    cursive(Typeface.SANS_SERIF, "cursive"), 
    defaultType(Typeface.SANS_SERIF, "default"), 
    monospace(Typeface.MONOSPACE, "monospace"), 
    monospaceSansSerif(Typeface.MONOSPACE, "monospaceSansSerif"), 
    monospaceSansSerifApi(Typeface.MONOSPACE, "monospaced_sans_serif"), 
    monospaceSerif(Typeface.MONOSPACE, "monospaceSerif"), 
    monospaceSerifApi(Typeface.MONOSPACE, "monospaced_serif"), 
    proportionalSansSerif(Typeface.SANS_SERIF, "proportionalSansSerif"), 
    proportionalSansSerifApi(Typeface.SANS_SERIF, "proportional_sans_serif"), 
    proportionalSerif(Typeface.SERIF, "proportionalSerif"), 
    proportionalSerifApi(Typeface.SERIF, "proportional_serif"), 
    sansSerif(Typeface.SANS_SERIF, "sansSerif"), 
    serif(Typeface.SERIF, "serif"), 
    smallCapitals(Typeface.SANS_SERIF, "smallCapitals"), 
    smallCapitalsApi(Typeface.SANS_SERIF, "small_capitals");
    
    private String mLookup;
    private Typeface mValue;
    
    private FontFamilyMapping(final Typeface mValue, final String mLookup) {
        this.mValue = mValue;
        this.mLookup = mLookup;
    }
    
    public static boolean isMonospace(final FontFamilyMapping fontFamilyMapping) {
        return fontFamilyMapping != null && fontFamilyMapping.getTypeface() == Typeface.MONOSPACE;
    }
    
    public static boolean isProportional(final FontFamilyMapping fontFamilyMapping) {
        return !isMonospace(fontFamilyMapping);
    }
    
    public static boolean isSansSerif(final FontFamilyMapping fontFamilyMapping) {
        return fontFamilyMapping == null || fontFamilyMapping.getTypeface() == Typeface.SANS_SERIF;
    }
    
    public static boolean isSerif(final FontFamilyMapping fontFamilyMapping) {
        return fontFamilyMapping != null && fontFamilyMapping.getTypeface() == Typeface.SERIF;
    }
    
    public static FontFamilyMapping lookup(final String s) {
        if (!StringUtils.isEmpty(s)) {
            final FontFamilyMapping[] values = values();
            for (int length = values.length, i = 0; i < length; ++i) {
                final FontFamilyMapping fontFamilyMapping = values[i];
                if (fontFamilyMapping.getLookupValue().equalsIgnoreCase(s)) {
                    return fontFamilyMapping;
                }
            }
        }
        return null;
    }
    
    public String getLookupValue() {
        return this.mLookup;
    }
    
    public Typeface getTypeface() {
        return this.mValue;
    }
}
