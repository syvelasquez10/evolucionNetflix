// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics.internal;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.math.BigInteger;
import java.util.Locale;
import android.text.TextUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient$Info;

public class zza extends zzd
{
    public static boolean zzLk;
    private AdvertisingIdClient$Info zzLl;
    private final zzaj zzLm;
    private String zzLn;
    private boolean zzLo;
    private Object zzLp;
    
    zza(final zzf zzf) {
        super(zzf);
        this.zzLo = false;
        this.zzLp = new Object();
        this.zzLm = new zzaj(zzf.zzid());
    }
    
    private boolean zza(final AdvertisingIdClient$Info advertisingIdClient$Info, AdvertisingIdClient$Info id) {
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
            final String zzjd = this.zzij().zzjd();
            final AdvertisingIdClient$Info advertisingIdClient$Info2;
        Label_0192:
            while (true) {
                synchronized (this.zzLp) {
                    if (!this.zzLo) {
                        this.zzLn = this.zzhT();
                        this.zzLo = true;
                        if (TextUtils.isEmpty((CharSequence)zzaW((String)id + zzjd))) {
                            return false;
                        }
                        break Label_0192;
                    }
                }
                if (!TextUtils.isEmpty((CharSequence)this.zzLn)) {
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
                    return this.zzaX((String)id + zzjd);
                }
                this.zzLn = zzaW(id2 + zzjd);
                continue;
            }
            if (((String)advertisingIdClient$Info2).equals(this.zzLn)) {
                // monitorexit(o)
                return true;
            }
            String zzje;
            if (!TextUtils.isEmpty((CharSequence)this.zzLn)) {
                this.zzaY("Resetting the client id because Advertising Id changed.");
                zzje = this.zzij().zzje();
                this.zza("New client Id", zzje);
            }
            else {
                zzje = zzjd;
            }
            // monitorexit(o)
            return this.zzaX((String)id + zzje);
        }
    }
    
    private static String zzaW(final String s) {
        final MessageDigest zzbq = zzam.zzbq("MD5");
        if (zzbq == null) {
            return null;
        }
        return String.format(Locale.US, "%032X", new BigInteger(1, zzbq.digest(s.getBytes())));
    }
    
    private boolean zzaX(String zzaW) {
        try {
            zzaW = zzaW(zzaW);
            this.zzaY("Storing hashed adid.");
            final FileOutputStream openFileOutput = this.getContext().openFileOutput("gaClientIdData", 0);
            openFileOutput.write(zzaW.getBytes());
            openFileOutput.close();
            this.zzLn = zzaW;
            return true;
        }
        catch (IOException ex) {
            this.zze("Error creating hash file", ex);
            return false;
        }
    }
    
    private AdvertisingIdClient$Info zzhR() {
        synchronized (this) {
            if (this.zzLm.zzv(1000L)) {
                this.zzLm.start();
                final AdvertisingIdClient$Info zzhS = this.zzhS();
                if (this.zza(this.zzLl, zzhS)) {
                    this.zzLl = zzhS;
                }
                else {
                    this.zzbc("Failed to reset client id on adid change. Not using adid");
                    this.zzLl = new AdvertisingIdClient$Info("", false);
                }
            }
            return this.zzLl;
        }
    }
    
    @Override
    protected void zzhB() {
    }
    
    public boolean zzhM() {
        final boolean b = false;
        this.zzio();
        final AdvertisingIdClient$Info zzhR = this.zzhR();
        boolean b2 = b;
        if (zzhR != null) {
            b2 = b;
            if (!zzhR.isLimitAdTrackingEnabled()) {
                b2 = true;
            }
        }
        return b2;
    }
    
    public String zzhQ() {
        this.zzio();
        final AdvertisingIdClient$Info zzhR = this.zzhR();
        String id;
        if (zzhR != null) {
            id = zzhR.getId();
        }
        else {
            id = null;
        }
        if (TextUtils.isEmpty((CharSequence)id)) {
            return null;
        }
        return id;
    }
    
    protected AdvertisingIdClient$Info zzhS() {
        AdvertisingIdClient$Info advertisingIdInfo = null;
        try {
            advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.getContext());
            return advertisingIdInfo;
        }
        catch (IllegalStateException ex) {
            this.zzbb("IllegalStateException getting Ad Id Info. If you would like to see Audience reports, please ensure that you have added '<meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />' to your application manifest file. See http://goo.gl/naFqQk for details.");
            return null;
        }
        catch (Throwable t) {
            if (!zza.zzLk) {
                zza.zzLk = true;
                this.zzd("Error getting advertiser id", t);
                return null;
            }
            return advertisingIdInfo;
        }
    }
    
    protected String zzhT() {
        FileInputStream openFileInput = null;
        Object o = null;
        try {
            openFileInput = this.getContext().openFileInput("gaClientIdData");
            o = new byte[128];
            final int read = openFileInput.read((byte[])o, 0, 128);
            if (openFileInput.available() > 0) {
                this.zzbb("Hash file seems corrupted, deleting it.");
                openFileInput.close();
                this.getContext().deleteFile("gaClientIdData");
                return null;
            }
            if (read <= 0) {
                this.zzaY("Hash file is empty.");
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
}
