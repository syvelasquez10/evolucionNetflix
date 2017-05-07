// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import java.io.IOException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import android.content.Context;

class a implements m
{
    private static Object sf;
    private static a sg;
    private Context mContext;
    private AdvertisingIdClient.Info sh;
    private long si;
    
    static {
        a.sf = new Object();
    }
    
    a(final Context mContext) {
        this.mContext = mContext;
    }
    
    private AdvertisingIdClient.Info bQ() {
        try {
            return AdvertisingIdClient.getAdvertisingIdInfo(this.mContext);
        }
        catch (IllegalStateException ex) {
            aa.z("IllegalStateException getting Ad Id Info. If you would like to see Audience reports, please ensure that you have added '<meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />' to your application manifest file. See http://goo.gl/naFqQk for details.");
            return null;
        }
        catch (GooglePlayServicesRepairableException ex2) {
            aa.z("GooglePlayServicesRepairableException getting Ad Id Info");
            return null;
        }
        catch (IOException ex3) {
            aa.z("IOException getting Ad Id Info");
            return null;
        }
        catch (GooglePlayServicesNotAvailableException ex4) {
            aa.z("GooglePlayServicesNotAvailableException getting Ad Id Info");
            return null;
        }
        catch (Exception ex5) {
            aa.z("Unknown exception. Could not get the ad Id.");
            return null;
        }
    }
    
    public static m m(final Context context) {
        Label_0031: {
            if (a.sg != null) {
                break Label_0031;
            }
            synchronized (a.sf) {
                if (a.sg == null) {
                    a.sg = new a(context);
                }
                return a.sg;
            }
        }
    }
    
    @Override
    public String getValue(final String s) {
        final long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.si > 1000L) {
            this.sh = this.bQ();
            this.si = currentTimeMillis;
        }
        if (this.sh != null) {
            if ("&adid".equals(s)) {
                return this.sh.getId();
            }
            if ("&ate".equals(s)) {
                if (this.sh.isLimitAdTrackingEnabled()) {
                    return "0";
                }
                return "1";
            }
        }
        return null;
    }
}
