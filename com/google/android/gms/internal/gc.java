// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public final class gc
{
    public static String aG(final int n) {
        switch (n) {
            default: {
                throw new IllegalArgumentException("Unknown leaderboard collection: " + n);
            }
            case 0: {
                return "PUBLIC";
            }
            case 1: {
                return "SOCIAL";
            }
        }
    }
}
