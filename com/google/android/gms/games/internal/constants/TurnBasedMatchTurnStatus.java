// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.constants;

import com.google.android.gms.games.internal.GamesLog;

public final class TurnBasedMatchTurnStatus
{
    public static String dH(final int n) {
        switch (n) {
            default: {
                GamesLog.q("MatchTurnStatus", "Unknown match turn status: " + n);
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
