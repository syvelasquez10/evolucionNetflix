// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media;

import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.Log;
import android.os.Build;
import android.os.Build$VERSION;
import com.netflix.mediaclient.util.AndroidUtils;
import android.content.Context;

public final class HardwareAcceleration
{
    private static final String PREFERENCE_HARDWARE_ACCELERATION = "nflx_hardwarer_acc";
    private static final String TAG = "nf-hwac";
    private static Boolean hardwareAccelerationForced;
    
    static {
        HardwareAcceleration.hardwareAccelerationForced = null;
    }
    
    public static boolean candHardwareAccelerationBeForced(final Context context) {
        return AndroidUtils.getAndroidVersion() >= 14;
    }
    
    private static boolean isHardwareAccelerationApprovedApi() {
        return Build$VERSION.SDK_INT >= 14;
    }
    
    private static boolean isHardwareAccelerationApprovedDevice() {
        return Build.MANUFACTURER.equals("Amazon") && (Build.MODEL.equals("KFTT") || Build.MODEL.equals("KFOT"));
    }
    
    public static boolean shouldHardwareAccelerationBeForced(final Context context) {
        while (true) {
            boolean booleanValue = false;
            Label_0052: {
                synchronized (HardwareAcceleration.class) {
                    if (AndroidUtils.getAndroidVersion() >= 16) {
                        Log.d("nf-hwac", "Jelly Beans device, force hardware acceleration");
                        booleanValue = true;
                    }
                    else {
                        if (candHardwareAccelerationBeForced(context)) {
                            break Label_0052;
                        }
                        Log.d("nf-hwac", "Device is runing preICS Android. Do not apply hardware acceleration or check for it!");
                    }
                    return booleanValue;
                }
            }
            if (isHardwareAccelerationApprovedDevice()) {
                Log.d("nf-hwac", "Pre-approved device...");
                HardwareAcceleration.hardwareAccelerationForced = Boolean.TRUE;
            }
            if (HardwareAcceleration.hardwareAccelerationForced != Boolean.TRUE && isHardwareAccelerationApprovedApi()) {
                Log.d("nf-hwac", "Pre-approved api device ...");
                HardwareAcceleration.hardwareAccelerationForced = Boolean.TRUE;
            }
            if (HardwareAcceleration.hardwareAccelerationForced == null) {
                Log.d("nf-hwac", "Find if we already had set flag that hardware acceleration should be enforced from preferences...");
                final Context context2;
                HardwareAcceleration.hardwareAccelerationForced = PreferenceUtils.getBooleanPref(context2, "nflx_hardwarer_acc", false);
            }
            else {
                Log.d("nf-hwac", "Find if we have update for flag that hardware acceleration should be enforced from preferences...");
                final Context context2;
                HardwareAcceleration.hardwareAccelerationForced = PreferenceUtils.getBooleanPref(context2, "nflx_hardwarer_acc", HardwareAcceleration.hardwareAccelerationForced);
            }
            if (HardwareAcceleration.hardwareAccelerationForced == null) {
                Log.e("nf-hwac", "hardwareAccelerationForced == null. This should NOT happen!");
                HardwareAcceleration.hardwareAccelerationForced = Boolean.FALSE;
            }
            booleanValue = HardwareAcceleration.hardwareAccelerationForced;
            return booleanValue;
        }
    }
    
    public static void update(final Context context, final Boolean hardwareAccelerationForced) {
        while (true) {
            Label_0074: {
                synchronized (HardwareAcceleration.class) {
                    if (Log.isLoggable()) {
                        Log.d("nf-hwac", "Force hardware acceleration " + hardwareAccelerationForced);
                    }
                    if (hardwareAccelerationForced == null) {
                        Log.d("nf-hwac", "Hardware acceleration is NOT enforced, ignore");
                    }
                    else {
                        if (candHardwareAccelerationBeForced(context)) {
                            break Label_0074;
                        }
                        Log.d("nf-hwac", "Device is runing preICS Android. Ignore!");
                    }
                    return;
                }
            }
            if (HardwareAcceleration.hardwareAccelerationForced == null || !HardwareAcceleration.hardwareAccelerationForced.equals(hardwareAccelerationForced)) {
                HardwareAcceleration.hardwareAccelerationForced = hardwareAccelerationForced;
                final Context context2;
                PreferenceUtils.putBooleanPref(context2, "nflx_hardwarer_acc", hardwareAccelerationForced);
                Log.d("nf-hwac", "Forcing hardware acceleration on next start");
                return;
            }
            if (Log.isLoggable()) {
                Log.d("nf-hwac", "No need to do anything. The same as existed " + hardwareAccelerationForced);
            }
        }
    }
}
