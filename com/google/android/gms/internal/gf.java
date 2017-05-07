// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public final class gf
{
    public static String aG(final int n) {
        switch (n) {
            default: {
                fn.d("MatchTurnStatus", "Unknown match turn status: " + n);
                return "TURN_STATUS_UNKNOWN";
            }
            case 0: {
                return "TURN_STATUS_INVITED";
            }
            case 1: {
                return "TURN_STATUS_MY_TURN";
            }
            case 2: {
                return "TURN_STATUS_THEIR_TURN";
            }
            case 3: {
                return "TURN_STATUS_COMPLETE";
            }
        }
    }
}
