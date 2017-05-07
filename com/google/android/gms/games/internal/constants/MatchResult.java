// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.constants;

public final class MatchResult
{
    public static boolean isValid(final int n) {
        switch (n) {
            default: {
                return false;
            }
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5: {
                return true;
            }
        }
    }
}
