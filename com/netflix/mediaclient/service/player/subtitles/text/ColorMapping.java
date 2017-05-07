// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles.text;

import com.netflix.mediaclient.Log;
import android.graphics.Color;
import com.netflix.mediaclient.util.StringUtils;

public enum ColorMapping
{
    private static final String TAG = "nf_subtitles";
    
    aqua(65535.0, "aqua", "00ffff"), 
    black(0.0, "black", "000000"), 
    blue(255.0, "blue", "0000ff"), 
    cyan(65535.0, "cyan", "00ffff"), 
    fuchsia(1.6711935E7, "fuchsia", "ff00ff"), 
    gray(8421504.0, "gray", "808080"), 
    green(65280.0, "green", "00ff00"), 
    lime(65280.0, "lime", "00ff00"), 
    magenta(1.6711935E7, "magenta", "ff00ff"), 
    maroon(8388608.0, "maroon", "800000"), 
    navy(128.0, "navy", "000080"), 
    olive(8421376.0, "olive", "808000"), 
    orange(1.675392E7, "orange", "ffa500"), 
    pink(1.6761035E7, "pink", "ffc0cb"), 
    purple(8388736.0, "purple", "800080"), 
    red(1.671168E7, "red", "ff0000"), 
    silver(1.2632256E7, "silver", "c0c0c0"), 
    teal(32896.0, "teal", "008080"), 
    transparent(-1.0, "transparent", ""), 
    white(1.6777215E7, "white", "ffffff"), 
    yellow(1.677696E7, "yellow", "ffff00");
    
    private String mColorStringValue;
    private String mLookupValue;
    private double mValue;
    
    private ColorMapping(final double mValue, final String mLookupValue, final String mColorStringValue) {
        this.mValue = mValue;
        this.mLookupValue = mLookupValue;
        this.mColorStringValue = mColorStringValue;
    }
    
    public static String findColor(final String s) {
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        final ColorMapping lookup = lookup(s);
        if (lookup != null) {
            return lookup.getColorStringValue();
        }
        return parseColor(s);
    }
    
    public static ColorMapping lookup(final String s) {
        if (!StringUtils.isEmpty(s)) {
            final ColorMapping[] values = values();
            for (int length = values.length, i = 0; i < length; ++i) {
                final ColorMapping colorMapping = values[i];
                if (colorMapping.mLookupValue.equalsIgnoreCase(s)) {
                    return colorMapping;
                }
            }
        }
        return null;
    }
    
    private static String parseColor(String string) {
        final String s = null;
        final char[] charArray = string.toCharArray();
        string = s;
        if (charArray[0] == '#') {
            final StringBuilder sb = new StringBuilder();
            if (charArray.length == 7) {
                sb.append(charArray[1]).append(charArray[2]);
                sb.append(charArray[3]).append(charArray[4]);
                sb.append(charArray[5]).append(charArray[6]);
            }
            else {
                string = s;
                if (charArray.length != 4) {
                    return string;
                }
                sb.append(charArray[1]).append(charArray[1]);
                sb.append(charArray[2]).append(charArray[2]);
                sb.append(charArray[3]).append(charArray[3]);
            }
            string = sb.toString();
        }
        return string;
    }
    
    public static Integer resolveColor(Float string, final String s) {
        if (s == null) {
            return null;
        }
        string = (Float)("#" + OpacityMapping.opacityToHex(string) + s);
        try {
            return Color.parseColor((String)string);
        }
        catch (Throwable t) {
            Log.e("nf_subtitles", "Resolve color failed for " + (String)string);
            return null;
        }
    }
    
    public String getColorStringValue() {
        return this.mColorStringValue;
    }
    
    public String getLookupValue() {
        return this.mLookupValue;
    }
    
    public double getValue() {
        return this.mValue;
    }
}
