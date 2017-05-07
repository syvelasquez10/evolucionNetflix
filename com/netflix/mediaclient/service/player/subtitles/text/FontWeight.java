// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles.text;

public enum FontWeight
{
    Black(800), 
    Bold(700), 
    Book(300), 
    ExtraBlack(800), 
    Light(200), 
    Medium(500);
    
    private static final String NORMAL = "normal";
    
    Regular(400), 
    SemiBold(600), 
    Thin(100);
    
    private int mValue;
    
    private FontWeight(final int mValue) {
        this.mValue = mValue;
    }
    
    private static String convertName(final String s) {
        String name = s;
        if ("normal".equalsIgnoreCase(s)) {
            name = FontWeight.Regular.name();
        }
        return name;
    }
    
    public static FontWeight createFontWeight(String convertName) {
        convertName = convertName(convertName);
        final FontWeight[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final FontWeight fontWeight = values[i];
            if (fontWeight.name().equalsIgnoreCase(convertName)) {
                return fontWeight;
            }
        }
        return null;
    }
    
    public int getValue() {
        return this.mValue;
    }
}
