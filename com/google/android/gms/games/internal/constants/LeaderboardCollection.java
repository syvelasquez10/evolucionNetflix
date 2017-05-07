// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.constants;

public final class LeaderboardCollection
{
    public static String bd(final int n) {
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
