// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.constants;

import com.google.android.gms.games.internal.GamesLog;

public final class RequestType
{
    public static String bd(final int n) {
        switch (n) {
            default: {
                GamesLog.h("RequestType", "Unknown request type: " + n);
                return "UNKNOWN_TYPE";
            }
            case 1: {
                return "GIFT";
            }
            case 2: {
                return "WISH";
            }
        }
    }
}
