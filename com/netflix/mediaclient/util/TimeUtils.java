// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

public class TimeUtils
{
    public static final int HOURS_PER_DAY = 24;
    public static final int MILLISECONDS_PER_HOUR = 3600000;
    public static final int MILLISECONDS_PER_MINUTE = 60000;
    public static final int MILLISECONDS_PER_SECOND = 1000;
    public static final int MINUTES_PER_HOUR = 60;
    public static final int NANOSECONDS_PER_MILLISECOND = 1000000;
    public static final int NANOSECONDS_PER_SECOND = 1000000000;
    public static final int SECONDS_PER_DAY = 86400;
    public static final int SECONDS_PER_HOUR = 3600;
    public static final int SECONDS_PER_MINUTE = 60;
    
    public static long computeTimeInMsSinceStart(final long n) {
        return (System.nanoTime() - n) / 1000000L;
    }
    
    public static long convertNsToMs(final long n) {
        return n / 1000000L;
    }
    
    public static int convertSecondsToMinutes(final int n) {
        return (int)(n / 60.0f + 0.5f);
    }
}
