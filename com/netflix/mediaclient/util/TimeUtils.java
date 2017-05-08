// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import android.content.Context;

public class TimeUtils
{
    public static final int DAYS_PER_MONTH = 30;
    public static final int HOURS_PER_DAY = 24;
    private static final int MARGIN_FOR_BOOKMARK_RESET_SECONDS = 30;
    public static final int MILLISECONDS_PER_HOUR = 3600000;
    public static final int MILLISECONDS_PER_MINUTE = 60000;
    public static final long MILLISECONDS_PER_MONTH = 2592000000L;
    public static final int MILLISECONDS_PER_SECOND = 1000;
    public static final int MINUTES_PER_HOUR = 60;
    public static final int NANOSECONDS_PER_MILLISECOND = 1000000;
    public static final int NANOSECONDS_PER_SECOND = 1000000000;
    public static final int SECONDS_PER_DAY = 86400;
    public static final int SECONDS_PER_HOUR = 3600;
    public static final int SECONDS_PER_MINUTE = 60;
    
    public static int computePlayPos(final int n, int n2, final int n3) {
        if (n2 > 0 && n >= n2) {
            n2 = 0;
        }
        else {
            if (n3 > 0 && n >= n3 - 30) {
                return 0;
            }
            if ((n2 = n) < 0) {
                return 0;
            }
        }
        return n2;
    }
    
    public static long computeTimeInMsSinceStart(final long n) {
        return (System.nanoTime() - n) / 1000000L;
    }
    
    public static long convertNsToMs(final long n) {
        return n / 1000000L;
    }
    
    public static int convertSecondsToMinutes(final int n) {
        return (int)(n / 60.0f + 0.5f);
    }
    
    public static String getFormattedTime(int n, final Context context) {
        final int n2 = n / 3600;
        n = (n - n2 * 3600) / 60;
        if (n2 > 0) {
            return context.getResources().getString(2131231123, new Object[] { n2, n });
        }
        return context.getResources().getString(2131231124, new Object[] { n });
    }
}
