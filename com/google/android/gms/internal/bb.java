// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.res.TypedArray;
import android.text.TextUtils;
import com.google.android.gms.R;
import android.util.AttributeSet;
import android.content.Context;
import com.google.android.gms.ads.AdSize;

@ez
public final class bb
{
    private final AdSize[] oj;
    private final String ok;
    
    public bb(final Context context, final AttributeSet set) {
        boolean b = true;
        final TypedArray obtainAttributes = context.getResources().obtainAttributes(set, R.styleable.AdsAttrs);
        final String string = obtainAttributes.getString(R.styleable.AdsAttrs_adSize);
        final String string2 = obtainAttributes.getString(R.styleable.AdsAttrs_adSizes);
        boolean b2;
        if (!TextUtils.isEmpty((CharSequence)string)) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        if (TextUtils.isEmpty((CharSequence)string2)) {
            b = false;
        }
        if (b2 && !b) {
            this.oj = q(string);
        }
        else if (!b2 && b) {
            this.oj = q(string2);
        }
        else {
            if (b2 && b) {
                throw new IllegalArgumentException("Either XML attribute \"adSize\" or XML attribute \"supportedAdSizes\" should be specified, but not both.");
            }
            throw new IllegalArgumentException("Required XML attribute \"adSize\" was missing.");
        }
        this.ok = obtainAttributes.getString(R.styleable.AdsAttrs_adUnitId);
        if (TextUtils.isEmpty((CharSequence)this.ok)) {
            throw new IllegalArgumentException("Required XML attribute \"adUnitId\" was missing.");
        }
    }
    
    private static AdSize[] q(final String s) {
        final String[] split = s.split("\\s*,\\s*");
        final AdSize[] array = new AdSize[split.length];
        int i = 0;
    Label_0121_Outer:
        while (i < split.length) {
            final String trim = split[i].trim();
            while (true) {
                Label_0179: {
                    if (!trim.matches("^(\\d+|FULL_WIDTH)\\s*[xX]\\s*(\\d+|AUTO_HEIGHT)$")) {
                        break Label_0179;
                    }
                    final String[] split2 = trim.split("[xX]");
                    split2[0] = split2[0].trim();
                    split2[1] = split2[1].trim();
                    try {
                        int int1;
                        if ("FULL_WIDTH".equals(split2[0])) {
                            int1 = -1;
                        }
                        else {
                            int1 = Integer.parseInt(split2[0]);
                        }
                        int int2;
                        if ("AUTO_HEIGHT".equals(split2[1])) {
                            int2 = -2;
                        }
                        else {
                            int2 = Integer.parseInt(split2[1]);
                        }
                        array[i] = new AdSize(int1, int2);
                        ++i;
                        continue Label_0121_Outer;
                    }
                    catch (NumberFormatException ex) {
                        throw new IllegalArgumentException("Could not parse XML attribute \"adSize\": " + trim);
                    }
                }
                if ("BANNER".equals(trim)) {
                    array[i] = AdSize.BANNER;
                    continue;
                }
                if ("LARGE_BANNER".equals(trim)) {
                    array[i] = AdSize.LARGE_BANNER;
                    continue;
                }
                if ("FULL_BANNER".equals(trim)) {
                    array[i] = AdSize.FULL_BANNER;
                    continue;
                }
                if ("LEADERBOARD".equals(trim)) {
                    array[i] = AdSize.LEADERBOARD;
                    continue;
                }
                if ("MEDIUM_RECTANGLE".equals(trim)) {
                    array[i] = AdSize.MEDIUM_RECTANGLE;
                    continue;
                }
                if ("SMART_BANNER".equals(trim)) {
                    array[i] = AdSize.SMART_BANNER;
                    continue;
                }
                if ("WIDE_SKYSCRAPER".equals(trim)) {
                    array[i] = AdSize.WIDE_SKYSCRAPER;
                    continue;
                }
                break;
            }
            throw new IllegalArgumentException("Could not parse XML attribute \"adSize\": " + trim);
        }
        if (array.length == 0) {
            throw new IllegalArgumentException("Could not parse XML attribute \"adSize\": " + s);
        }
        return array;
    }
    
    public AdSize[] f(final boolean b) {
        if (!b && this.oj.length != 1) {
            throw new IllegalArgumentException("The adSizes XML attribute is only allowed on PublisherAdViews.");
        }
        return this.oj;
    }
    
    public String getAdUnitId() {
        return this.ok;
    }
}
