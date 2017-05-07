// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.math.BigInteger;
import java.util.Locale;
import android.text.TextUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient$Info;
import android.content.Context;

class a implements l
{
    private static a xA;
    private static Object xz;
    private Context mContext;
    private AdvertisingIdClient$Info xB;
    private long xC;
    private String xD;
    private boolean xE;
    private Object xF;
    
    static {
        a.xz = new Object();
    }
    
    a(final Context context) {
        this.xE = false;
        this.xF = new Object();
        this.mContext = context.getApplicationContext();
    }
    
    private boolean a(final AdvertisingIdClient$Info advertisingIdClient$Info, AdvertisingIdClient$Info id) {
        final String s = null;
        if (id == null) {
            id = null;
        }
        else {
            id = ((AdvertisingIdClient$Info)id).getId();
        }
        if (TextUtils.isEmpty((CharSequence)id)) {
            return true;
        }
        while (true) {
            h.y(this.mContext);
            final h dr = h.dR();
            final String value = dr.getValue("&cid");
            final AdvertisingIdClient$Info advertisingIdClient$Info2;
        Label_0207:
            while (true) {
                synchronized (this.xF) {
                    if (!this.xE) {
                        this.xD = x(this.mContext);
                        this.xE = true;
                        if (TextUtils.isEmpty((CharSequence)aa((String)id + value))) {
                            return false;
                        }
                        break Label_0207;
                    }
                }
                if (!TextUtils.isEmpty((CharSequence)this.xD)) {
                    continue;
                }
                String id2;
                if (advertisingIdClient$Info2 == null) {
                    id2 = s;
                }
                else {
                    id2 = advertisingIdClient$Info2.getId();
                }
                if (id2 == null) {
                    // monitorexit(o)
                    return this.ab((String)id + value);
                }
                this.xD = aa(id2 + value);
                continue;
            }
            if (((String)advertisingIdClient$Info2).equals(this.xD)) {
                // monitorexit(o)
                return true;
            }
            String ds;
            if (!TextUtils.isEmpty((CharSequence)this.xD)) {
                z.V("Resetting the client id because Advertising Id changed.");
                ds = dr.dS();
                z.V("New client Id: " + ds);
            }
            else {
                ds = value;
            }
            // monitorexit(o)
            return this.ab((String)id + ds);
        }
    }
    
    static String aa(final String s) {
        final MessageDigest ap = aj.ap("MD5");
        if (ap == null) {
            return null;
        }
        return String.format(Locale.US, "%032X", new BigInteger(1, ap.digest(s.getBytes())));
    }
    
    private boolean ab(String aa) {
        try {
            aa = aa(aa);
            z.V("Storing hashed adid.");
            final FileOutputStream openFileOutput = this.mContext.openFileOutput("gaClientIdData", 0);
            openFileOutput.write(aa.getBytes());
            openFileOutput.close();
            this.xD = aa;
            return true;
        }
        catch (FileNotFoundException ex) {
            z.T("Error creating hash file.");
            return false;
        }
        catch (IOException ex2) {
            z.T("Error writing to hash file.");
            return false;
        }
    }
    
    public static l w(final Context context) {
        Label_0031: {
            if (a.xA != null) {
                break Label_0031;
            }
            synchronized (a.xz) {
                if (a.xA == null) {
                    a.xA = new a(context);
                }
                return a.xA;
            }
        }
    }
    
    static String x(final Context context) {
        FileInputStream openFileInput = null;
        Object o = null;
        try {
            openFileInput = context.openFileInput("gaClientIdData");
            o = new byte[128];
            final int read = openFileInput.read((byte[])o, 0, 128);
            if (openFileInput.available() > 0) {
                z.W("Hash file seems corrupted, deleting it.");
                openFileInput.close();
                context.deleteFile("gaClientIdData");
                return null;
            }
            if (read <= 0) {
                z.U("Hash file is empty.");
                openFileInput.close();
                return null;
            }
            o = new String((byte[])o, 0, read);
            final FileInputStream fileInputStream = openFileInput;
            fileInputStream.close();
            final byte[] array = (byte[])o;
            return (String)(Object)array;
        }
        catch (IOException ex) {}
        catch (FileNotFoundException ex2) {
            return null;
        }
        try {
            final FileInputStream fileInputStream = openFileInput;
            fileInputStream.close();
            final byte[] array = (byte[])o;
            return (String)(Object)array;
        }
        catch (IOException ex3) {}
        catch (FileNotFoundException ex4) {}
    }
    
    AdvertisingIdClient$Info dH() {
        try {
            return AdvertisingIdClient.getAdvertisingIdInfo(this.mContext);
        }
        catch (IllegalStateException ex) {
            z.W("IllegalStateException getting Ad Id Info. If you would like to see Audience reports, please ensure that you have added '<meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />' to your application manifest file. See http://goo.gl/naFqQk for details.");
            return null;
        }
        catch (GooglePlayServicesRepairableException ex2) {
            z.W("GooglePlayServicesRepairableException getting Ad Id Info");
            return null;
        }
        catch (IOException ex3) {
            z.W("IOException getting Ad Id Info");
            return null;
        }
        catch (GooglePlayServicesNotAvailableException ex4) {
            z.W("GooglePlayServicesNotAvailableException getting Ad Id Info");
            return null;
        }
        catch (Throwable t) {
            z.W("Unknown exception. Could not get the ad Id.");
            return null;
        }
    }
    
    @Override
    public String getValue(final String s) {
        final long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.xC > 1000L) {
            final AdvertisingIdClient$Info dh = this.dH();
            if (this.a(this.xB, dh)) {
                this.xB = dh;
            }
            else {
                this.xB = new AdvertisingIdClient$Info("", false);
            }
            this.xC = currentTimeMillis;
        }
        if (this.xB != null) {
            if ("&adid".equals(s)) {
                return this.xB.getId();
            }
            if ("&ate".equals(s)) {
                if (this.xB.isLimitAdTrackingEnabled()) {
                    return "0";
                }
                return "1";
            }
        }
        return null;
    }
}
