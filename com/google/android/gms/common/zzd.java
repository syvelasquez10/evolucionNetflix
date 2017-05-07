// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common;

import android.content.pm.PackageManager$NameNotFoundException;
import android.content.pm.PackageManager;
import java.util.Set;
import android.util.Base64;
import android.util.Log;
import android.content.pm.PackageInfo;

public class zzd
{
    private static final zzd zzaas;
    
    static {
        zzaas = new zzd();
    }
    
    private boolean zza(final PackageInfo packageInfo, final boolean b) {
        if (packageInfo.signatures.length != 1) {
            Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
            return false;
        }
        final zzc$zzb zzc$zzb = new zzc$zzb(packageInfo.signatures[0].toByteArray());
        Set<zzc$zza> set;
        if (b) {
            set = zzc.zznp();
        }
        else {
            set = zzc.zznq();
        }
        if (set.contains(zzc$zzb)) {
            return true;
        }
        if (Log.isLoggable("GoogleSignatureVerifier", 2)) {
            Log.v("GoogleSignatureVerifier", "Signature not valid.  Found: \n" + Base64.encodeToString(zzc$zzb.getBytes(), 0));
        }
        return false;
    }
    
    public static zzd zznu() {
        return zzd.zzaas;
    }
    
    zzc$zza zza(final PackageInfo packageInfo, final zzc$zza... array) {
        if (packageInfo.signatures.length != 1) {
            Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
            return null;
        }
        final zzc$zzb zzc$zzb = new zzc$zzb(packageInfo.signatures[0].toByteArray());
        for (int i = 0; i < array.length; ++i) {
            if (array[i].equals(zzc$zzb)) {
                return array[i];
            }
        }
        if (Log.isLoggable("GoogleSignatureVerifier", 2)) {
            Log.v("GoogleSignatureVerifier", "Signature not valid.  Found: \n" + Base64.encodeToString(zzc$zzb.getBytes(), 0));
        }
        return null;
    }
    
    public boolean zza(final PackageManager packageManager, final PackageInfo packageInfo) {
        boolean zza = false;
        if (packageInfo != null) {
            if (GooglePlayServicesUtil.zzc(packageManager)) {
                return this.zza(packageInfo, true);
            }
            final boolean b = zza = this.zza(packageInfo, 0 != 0);
            if (!b) {
                zza = b;
                if (this.zza(packageInfo, true)) {
                    Log.w("GoogleSignatureVerifier", "Test-keys aren't accepted on this build.");
                    return b;
                }
            }
        }
        return zza;
    }
    
    public boolean zzb(final PackageManager packageManager, final String s) {
        try {
            return this.zza(packageManager, packageManager.getPackageInfo(s, 64));
        }
        catch (PackageManager$NameNotFoundException ex) {
            if (Log.isLoggable("GoogleSignatureVerifier", 3)) {
                Log.d("GoogleSignatureVerifier", "Package manager can't find package " + s + ", defaulting to false");
            }
            return false;
        }
    }
}
