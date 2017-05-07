// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.InputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import android.net.NetworkInfo;
import android.net.ConnectivityManager;
import com.google.android.gms.tagmanager.zzbg;
import com.google.android.gms.common.internal.zzx;
import android.content.Context;

public class zzqy implements Runnable
{
    private final Context mContext;
    private final zzqw zzaRm;
    private final zzqn zzaUo;
    private final zzqx zzaUv;
    private final zzqs zzaUw;
    
    public zzqy(final Context context, final zzqn zzqn, final zzqx zzqx) {
        this(context, zzqn, zzqx, new zzqw(), new zzqs());
    }
    
    zzqy(final Context mContext, final zzqn zzaUo, final zzqx zzaUv, final zzqw zzaRm, final zzqs zzaUw) {
        zzx.zzv(mContext);
        zzx.zzv(zzaUv);
        this.mContext = mContext;
        this.zzaUo = zzaUo;
        this.zzaUv = zzaUv;
        this.zzaRm = zzaRm;
        this.zzaUw = zzaUw;
    }
    
    public zzqy(final Context context, final zzqn zzqn, final zzqx zzqx, final String s) {
        this(context, zzqn, zzqx, new zzqw(), new zzqs());
        this.zzaUw.zzfj(s);
    }
    
    @Override
    public void run() {
        this.zzeP();
    }
    
    boolean zzBY() {
        if (!this.zzbf("android.permission.INTERNET")) {
            zzbg.e("Missing android.permission.INTERNET. Please add the following declaration to your AndroidManifest.xml: <uses-permission android:name=\"android.permission.INTERNET\" />");
            return false;
        }
        if (!this.zzbf("android.permission.ACCESS_NETWORK_STATE")) {
            zzbg.e("Missing android.permission.ACCESS_NETWORK_STATE. Please add the following declaration to your AndroidManifest.xml: <uses-permission android:name=\"android.permission.ACCESS_NETWORK_STATE\" />");
            return false;
        }
        final NetworkInfo activeNetworkInfo = ((ConnectivityManager)this.mContext.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            zzbg.zzaE("NetworkLoader: No network connectivity - Offline");
            return false;
        }
        return true;
    }
    
    boolean zzbf(final String s) {
        return this.mContext.getPackageManager().checkPermission(s, this.mContext.getPackageName()) == 0;
    }
    
    void zzeP() {
        if (!this.zzBY()) {
            this.zzaUv.zza(zzqx$zza.zzaUq);
            return;
        }
        zzbg.v("NetworkLoader: Starting to load resource from Network.");
        final zzqv zzBW = this.zzaRm.zzBW();
        try {
            final String zzt = this.zzaUw.zzt(this.zzaUo.zzBv());
            InputStream zzfs;
            try {
                zzfs = zzBW.zzfs(zzt);
                final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                final InputStream inputStream = zzfs;
                final ByteArrayOutputStream byteArrayOutputStream2 = byteArrayOutputStream;
                zzlr.zza(inputStream, byteArrayOutputStream2);
                final zzqy zzqy = this;
                final zzqx zzqx = zzqy.zzaUv;
                final ByteArrayOutputStream byteArrayOutputStream3 = byteArrayOutputStream;
                final byte[] array = byteArrayOutputStream3.toByteArray();
                zzqx.zzu(array);
                final zzqv zzqv = zzBW;
                zzqv.close();
                final String s = "NetworkLoader: Resource loaded.";
                zzbg.v(s);
                return;
            }
            catch (FileNotFoundException ex3) {
                zzbg.e("NetworkLoader: No data is retrieved from the given url: " + zzt);
                this.zzaUv.zza(zzqx$zza.zzaUs);
                return;
            }
            catch (IOException ex) {
                zzbg.zzb("NetworkLoader: Error when loading resource from url: " + zzt + " " + ex.getMessage(), ex);
                this.zzaUv.zza(zzqx$zza.zzaUr);
                return;
            }
            try {
                final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                final InputStream inputStream = zzfs;
                final ByteArrayOutputStream byteArrayOutputStream2 = byteArrayOutputStream;
                zzlr.zza(inputStream, byteArrayOutputStream2);
                final zzqy zzqy = this;
                final zzqx zzqx = zzqy.zzaUv;
                final ByteArrayOutputStream byteArrayOutputStream3 = byteArrayOutputStream;
                final byte[] array = byteArrayOutputStream3.toByteArray();
                zzqx.zzu(array);
                final zzqv zzqv = zzBW;
                zzqv.close();
                final String s = "NetworkLoader: Resource loaded.";
                zzbg.v(s);
            }
            catch (IOException ex2) {
                zzbg.zzb("NetworkLoader: Error when parsing downloaded resources from url: " + zzt + " " + ex2.getMessage(), ex2);
                this.zzaUv.zza(zzqx$zza.zzaUs);
            }
        }
        finally {
            zzBW.close();
        }
    }
}
