// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.text.TextUtils;

final class zzcd$3 extends zzcd
{
    private String zzS(final String s) {
        if (!TextUtils.isEmpty((CharSequence)s)) {
            int n = 0;
            final int length = s.length();
            int n2;
            while (true) {
                n2 = length;
                if (n >= s.length()) {
                    break;
                }
                n2 = length;
                if (s.charAt(n) != ',') {
                    break;
                }
                ++n;
            }
            while (n2 > 0 && s.charAt(n2 - 1) == ',') {
                --n2;
            }
            if (n != 0 || n2 != s.length()) {
                return s.substring(n, n2);
            }
        }
        return s;
    }
    
    @Override
    public String zzd(String zzS, String zzS2) {
        zzS = this.zzS(zzS);
        zzS2 = this.zzS(zzS2);
        if (TextUtils.isEmpty((CharSequence)zzS)) {
            return zzS2;
        }
        if (TextUtils.isEmpty((CharSequence)zzS2)) {
            return zzS;
        }
        return zzS + "," + zzS2;
    }
}
