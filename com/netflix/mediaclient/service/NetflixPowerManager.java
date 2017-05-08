// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service;

import com.netflix.mediaclient.Log;
import android.os.PowerManager;
import java.util.HashSet;
import java.util.Set;
import android.os.PowerManager$WakeLock;
import android.content.Context;

public class NetflixPowerManager
{
    private static final String TAG = "nf_power_manager";
    private final Context mContext;
    private PowerManager$WakeLock mPartialWakeLock;
    private final Set<NetflixPowerManager$PartialWakeLockReason> mPartialWakeLockReasonSet;
    
    public NetflixPowerManager(final Context mContext) {
        this.mPartialWakeLockReasonSet = new HashSet<NetflixPowerManager$PartialWakeLockReason>();
        this.mContext = mContext;
    }
    
    public void acquirePartialWakeLockFor(final NetflixPowerManager$PartialWakeLockReason netflixPowerManager$PartialWakeLockReason) {
        if (!this.mPartialWakeLockReasonSet.contains(netflixPowerManager$PartialWakeLockReason)) {
            this.mPartialWakeLockReasonSet.add(netflixPowerManager$PartialWakeLockReason);
            final PowerManager powerManager = (PowerManager)this.mContext.getSystemService("power");
            if (powerManager != null) {
                if (this.mPartialWakeLock == null) {
                    this.mPartialWakeLock = powerManager.newWakeLock(1, "nf_power_manager");
                }
                if (this.mPartialWakeLock != null && !this.mPartialWakeLock.isHeld()) {
                    if (Log.isLoggable()) {
                        Log.i("nf_power_manager", "acquirePartialWakeLockFor " + netflixPowerManager$PartialWakeLockReason);
                    }
                    this.mPartialWakeLock.acquire();
                }
            }
        }
    }
    
    public void forceReleasePartialWakeLock() {
        this.mPartialWakeLockReasonSet.clear();
        if (this.mPartialWakeLock != null && this.mPartialWakeLock.isHeld()) {
            this.mPartialWakeLock.release();
            this.mPartialWakeLock = null;
            if (Log.isLoggable()) {
                Log.i("nf_power_manager", "forceReleasePartialWakeLock");
            }
        }
    }
    
    public void releasePartialWakeLockFor(final NetflixPowerManager$PartialWakeLockReason netflixPowerManager$PartialWakeLockReason) {
        this.mPartialWakeLockReasonSet.remove(netflixPowerManager$PartialWakeLockReason);
        if (this.mPartialWakeLockReasonSet.isEmpty() && this.mPartialWakeLock != null && this.mPartialWakeLock.isHeld()) {
            this.mPartialWakeLock.release();
            if (Log.isLoggable()) {
                Log.i("nf_power_manager", "releasePartialWakeLockFor " + netflixPowerManager$PartialWakeLockReason);
            }
        }
    }
}
