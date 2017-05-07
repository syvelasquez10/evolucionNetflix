// 
// Decompiled by Procyon v0.5.30
// 

package com.vailsys.whistleengine;

import android.util.Log;
import android.os.Build$VERSION;
import android.net.wifi.WifiManager;
import android.os.PowerManager;
import android.content.Context;
import android.net.wifi.WifiManager$WifiLock;
import android.os.PowerManager$WakeLock;

public class PowerManagement
{
    private static final String FULL_HIGH_PERF_NAME = "WIFI_MODE_FULL_HIGH_PERF";
    private static final String TAG;
    private static final boolean USE_PROXIMITY_WAKE_LOCK = false;
    private static final int WIFI_LOCK_MODE;
    private PowerManager$WakeLock partialWakeLock;
    private PowerManager$WakeLock proximityWakeLock;
    private WifiManager$WifiLock wifiLock;
    
    static {
        TAG = PowerManagement.class.getName();
        WIFI_LOCK_MODE = wifiLockMode();
    }
    
    public PowerManagement(final Context context) {
        this.partialWakeLock = ((PowerManager)context.getSystemService("power")).newWakeLock(1, PowerManagement.TAG);
        this.wifiLock = ((WifiManager)context.getSystemService("wifi")).createWifiLock(PowerManagement.WIFI_LOCK_MODE, PowerManagement.TAG);
    }
    
    private static int wifiLockMode() {
        if (Integer.parseInt(Build$VERSION.SDK) >= 12) {
            try {
                return (int)WifiManager.class.getDeclaredField("WIFI_MODE_FULL_HIGH_PERF").get(null);
            }
            catch (Exception ex) {
                Log.v(PowerManagement.TAG, "failed to query WIFI_MODE_FULL_HIGH_PERF");
            }
        }
        return 1;
    }
    
    public void acquireLocks() {
        synchronized (this) {
            this.partialWakeLock.acquire();
            this.wifiLock.acquire();
        }
    }
    
    public void releaseLocks() {
        synchronized (this) {
            this.wifiLock.release();
            this.partialWakeLock.release();
        }
    }
    
    public void setProximitySensorActive(final boolean b) {
        synchronized (this) {
            if (this.proximityWakeLock != null) {
                if (b && !this.proximityWakeLock.isHeld()) {
                    this.proximityWakeLock.acquire();
                }
                else if (!b && this.proximityWakeLock.isHeld()) {
                    this.proximityWakeLock.release();
                }
            }
        }
    }
}
