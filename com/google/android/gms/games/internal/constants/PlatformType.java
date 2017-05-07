// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.constants;

public final class PlatformType
{
    public static String bd(final int n) {
        switch (n) {
            default: {
                throw new IllegalArgumentException("Unknown platform type: " + n);
            }
            case 0: {
                return "ANDROID";
            }
            case 1: {
                return "IOS";
            }
            case 2: {
                return "WEB_APP";
            }
        }
    }
}
