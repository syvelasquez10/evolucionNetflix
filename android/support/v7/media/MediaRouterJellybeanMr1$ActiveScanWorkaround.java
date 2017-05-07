// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import java.lang.reflect.InvocationTargetException;
import android.util.Log;
import android.os.Build$VERSION;
import android.content.Context;
import java.lang.reflect.Method;
import android.os.Handler;
import android.hardware.display.DisplayManager;

public final class MediaRouterJellybeanMr1$ActiveScanWorkaround implements Runnable
{
    private static final int WIFI_DISPLAY_SCAN_INTERVAL = 15000;
    private boolean mActivelyScanningWifiDisplays;
    private final DisplayManager mDisplayManager;
    private final Handler mHandler;
    private Method mScanWifiDisplaysMethod;
    
    public MediaRouterJellybeanMr1$ActiveScanWorkaround(final Context context, final Handler mHandler) {
        if (Build$VERSION.SDK_INT != 17) {
            throw new UnsupportedOperationException();
        }
        this.mDisplayManager = (DisplayManager)context.getSystemService("display");
        this.mHandler = mHandler;
        try {
            this.mScanWifiDisplaysMethod = DisplayManager.class.getMethod("scanWifiDisplays", (Class<?>[])new Class[0]);
        }
        catch (NoSuchMethodException ex) {}
    }
    
    @Override
    public void run() {
        if (!this.mActivelyScanningWifiDisplays) {
            return;
        }
        while (true) {
            try {
                this.mScanWifiDisplaysMethod.invoke(this.mDisplayManager, new Object[0]);
                this.mHandler.postDelayed((Runnable)this, 15000L);
            }
            catch (IllegalAccessException ex) {
                Log.w("MediaRouterJellybeanMr1", "Cannot scan for wifi displays.", (Throwable)ex);
                continue;
            }
            catch (InvocationTargetException ex2) {
                Log.w("MediaRouterJellybeanMr1", "Cannot scan for wifi displays.", (Throwable)ex2);
                continue;
            }
            break;
        }
    }
    
    public void setActiveScanRouteTypes(final int n) {
        if ((n & 0x2) != 0x0) {
            if (!this.mActivelyScanningWifiDisplays) {
                if (this.mScanWifiDisplaysMethod == null) {
                    Log.w("MediaRouterJellybeanMr1", "Cannot scan for wifi displays because the DisplayManager.scanWifiDisplays() method is not available on this device.");
                    return;
                }
                this.mActivelyScanningWifiDisplays = true;
                this.mHandler.post((Runnable)this);
            }
        }
        else if (this.mActivelyScanningWifiDisplays) {
            this.mActivelyScanningWifiDisplays = false;
            this.mHandler.removeCallbacks((Runnable)this);
        }
    }
}
