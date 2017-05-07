// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import com.netflix.mediaclient.Log;
import java.util.Locale;
import java.util.Formatter;

public class TimeFormatterHelper
{
    private static final String HOUR_FORMAT = "%d:%02d:%02d";
    private static final String MINUTE_FORMAT = "%02d:%02d";
    private static final String TAG = "TimeFormatterHelper";
    private final StringBuilder formatBuilder;
    private final Formatter formatter;
    
    public TimeFormatterHelper() {
        this.formatBuilder = new StringBuilder();
        this.formatter = new Formatter(this.formatBuilder, Locale.getDefault());
    }
    
    public String getStringForMs(final int n) {
        return this.getStringForSeconds(n / 1000);
    }
    
    public String getStringForSeconds(int n) {
        this.formatBuilder.setLength(0);
        if (n < 0) {
            Log.w("TimeFormatterHelper", "Received negative time: " + n);
            return this.formatter.format("%02d:%02d", 0, 0).toString();
        }
        final int n2 = n % 60;
        final int n3 = n / 60 % 60;
        n /= 3600;
        if (n > 0) {
            return this.formatter.format("%d:%02d:%02d", n, n3, n2).toString();
        }
        return this.formatter.format("%02d:%02d", n3, n2).toString();
    }
}
