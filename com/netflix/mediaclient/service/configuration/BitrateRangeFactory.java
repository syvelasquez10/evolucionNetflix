// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.media.bitrate.AudioBitrateRange;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.content.Context;

public class BitrateRangeFactory
{
    private static final String PREFERENCE_BITRATE_CAP = "nflx_bitrate_cap";
    private static final String TAG = "nf-bitrate";
    private static int mBitrateCap;
    
    static {
        BitrateRangeFactory.mBitrateCap = -1;
    }
    
    public static void clearRecords(final Context context) {
        PreferenceUtils.removePref(context, "nflx_bitrate_cap");
    }
    
    public static AudioBitrateRange getAudioBitrateRange() {
        return new AudioBitrateRange(0, 64);
    }
    
    public static int getBitrateCap(final Context context) {
        synchronized (BitrateRangeFactory.class) {
            if (BitrateRangeFactory.mBitrateCap == -1) {
                final int intPref = PreferenceUtils.getIntPref(context, "nflx_bitrate_cap", -1);
                if (intPref != -1) {
                    BitrateRangeFactory.mBitrateCap = intPref;
                }
            }
            return BitrateRangeFactory.mBitrateCap;
        }
    }
    
    public static void setBitrateCap(final Context context, final int n) {
        synchronized (BitrateRangeFactory.class) {
            PreferenceUtils.putIntPref(context, "nflx_bitrate_cap", n);
            Log.d("nf-bitrate", "Updating device configuration bitrate cap " + n);
        }
    }
    
    public static void updateDeviceBitrateCap(final Context context, final String s) {
        // monitorenter(BitrateRangeFactory.class)
        Label_0070: {
            if (s == null) {
                break Label_0070;
            }
            try {
                PreferenceUtils.putIntPref(context, "nflx_bitrate_cap", BitrateRangeFactory.mBitrateCap = Integer.parseInt(s));
                if (Log.isLoggable("nf-bitrate", 4) && BitrateRangeFactory.mBitrateCap > 0) {
                    Log.i("nf-bitrate", "Updating device configuration bitrate cap " + BitrateRangeFactory.mBitrateCap);
                }
                return;
                clearRecords(context);
            }
            finally {
            }
            // monitorexit(BitrateRangeFactory.class)
        }
    }
}
