// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import com.netflix.mediaclient.util.NetflixPreference;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.content.Context;
import com.google.gson.annotations.SerializedName;

public class SubtitleDownloadRetryPolicy
{
    public static final int DEFAULT_INITIAL_INTERVAL_MS = 10000;
    public static final int DEFAULT_MAX_ELAPSED_TIME_MS = 900000;
    public static final int DEFAULT_MAX_INTERVAL_MS = 60000;
    public static final double DEFAULT_MULTIPLIER = 2.0;
    public static final double DEFAULT_RANDOMIZATION_FACTOR = 0.5;
    private static String TAG;
    @SerializedName("initialIntervalInMs")
    private int initialIntervalInMs;
    @SerializedName("maxElapsedTimeInMs")
    private int maxElapsedTimeInMs;
    @SerializedName("maxIntervalInMs")
    private int maxIntervalInMs;
    @SerializedName("multiplier")
    private double multiplier;
    @SerializedName("randomizationFactor")
    private double randomizationFactor;
    
    static {
        SubtitleDownloadRetryPolicy.TAG = "nf_log";
    }
    
    public SubtitleDownloadRetryPolicy() {
        this.initialIntervalInMs = 10000;
        this.randomizationFactor = 0.5;
        this.multiplier = 2.0;
        this.maxIntervalInMs = 60000;
    }
    
    public static SubtitleDownloadRetryPolicy getDefault() {
        return new SubtitleDownloadRetryPolicy();
    }
    
    public static SubtitleDownloadRetryPolicy loadFromPreferences(Context context) {
        final Context context2 = null;
        final String stringPref = PreferenceUtils.getStringPref(context, "breadcrumb_log_configuration", null);
        Label_0028: {
            if (StringUtils.isEmpty(stringPref)) {
                Log.d(SubtitleDownloadRetryPolicy.TAG, "Breadcrumb specification not found in file system");
                context = null;
            }
            else {
                while (true) {
                    try {
                        context = (Context)FalkorParseUtils.getGson().fromJson(stringPref, SubtitleDownloadRetryPolicy.class);
                        try {
                            Log.d(SubtitleDownloadRetryPolicy.TAG, "Breadcrumb logging specification loaded from file system");
                            break Label_0028;
                        }
                        catch (Throwable t2) {}
                        final Throwable t;
                        Log.e(SubtitleDownloadRetryPolicy.TAG, "Failed to load Breadcrumb logging specification from file system", t);
                    }
                    catch (Throwable t) {
                        context = context2;
                        continue;
                    }
                    break;
                }
            }
        }
        Object default1 = context;
        if (context == null) {
            default1 = getDefault();
        }
        return (SubtitleDownloadRetryPolicy)default1;
    }
    
    public static SubtitleDownloadRetryPolicy saveToPreferences(final NetflixPreference netflixPreference, final SubtitleDownloadRetryPolicy subtitleDownloadRetryPolicy) {
        if (subtitleDownloadRetryPolicy == null) {
            netflixPreference.removePref("subtitle_download_retry_policy");
            Log.d(SubtitleDownloadRetryPolicy.TAG, "Subtitle retry policy not found, return default");
            return getDefault();
        }
        netflixPreference.putStringPref("subtitle_download_retry_policy", FalkorParseUtils.getGson().toJson(subtitleDownloadRetryPolicy));
        return subtitleDownloadRetryPolicy;
    }
    
    @Override
    public boolean equals(final Object o) {
        final boolean b = true;
        final boolean b2 = false;
        boolean b3;
        if (this == o) {
            b3 = true;
        }
        else {
            b3 = b2;
            if (o != null) {
                b3 = b2;
                if (this.getClass() == o.getClass()) {
                    final SubtitleDownloadRetryPolicy subtitleDownloadRetryPolicy = (SubtitleDownloadRetryPolicy)o;
                    b3 = b2;
                    if (this.initialIntervalInMs == subtitleDownloadRetryPolicy.initialIntervalInMs) {
                        b3 = b2;
                        if (Double.compare(subtitleDownloadRetryPolicy.randomizationFactor, this.randomizationFactor) == 0) {
                            b3 = b2;
                            if (Double.compare(subtitleDownloadRetryPolicy.multiplier, this.multiplier) == 0) {
                                b3 = b2;
                                if (this.maxIntervalInMs == subtitleDownloadRetryPolicy.maxIntervalInMs) {
                                    return this.maxElapsedTimeInMs == subtitleDownloadRetryPolicy.maxElapsedTimeInMs && b;
                                }
                            }
                        }
                    }
                }
            }
        }
        return b3;
    }
    
    public int getInitialIntervalInMs() {
        return this.initialIntervalInMs;
    }
    
    public int getMaxElapsedTimeInMs() {
        return this.maxElapsedTimeInMs;
    }
    
    public int getMaxIntervalInMs() {
        return this.maxIntervalInMs;
    }
    
    public double getMultiplier() {
        return this.multiplier;
    }
    
    public double getRandomizationFactor() {
        return this.randomizationFactor;
    }
    
    @Override
    public int hashCode() {
        final int initialIntervalInMs = this.initialIntervalInMs;
        final long doubleToLongBits = Double.doubleToLongBits(this.randomizationFactor);
        final int n = (int)(doubleToLongBits ^ doubleToLongBits >>> 32);
        final long doubleToLongBits2 = Double.doubleToLongBits(this.multiplier);
        return (((initialIntervalInMs * 31 + n) * 31 + (int)(doubleToLongBits2 ^ doubleToLongBits2 >>> 32)) * 31 + this.maxIntervalInMs) * 31 + this.maxElapsedTimeInMs;
    }
    
    @Override
    public String toString() {
        return "SubtitleDownloadRetryPolicy{initialIntervalInMs=" + this.initialIntervalInMs + ", randomizationFactor=" + this.randomizationFactor + ", multiplier=" + this.multiplier + ", maxIntervalInMs=" + this.maxIntervalInMs + ", maxElapsedTimeInMs=" + this.maxElapsedTimeInMs + '}';
    }
}
