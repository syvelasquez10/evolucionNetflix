// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.IBinder;
import com.google.android.gms.dynamic.zzg$zza;
import android.os.RemoteException;
import android.content.Context;
import com.google.android.gms.ads.internal.purchase.zze;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.content.Intent;
import android.app.Activity;
import com.google.android.gms.dynamic.zzg;

@zzgk
public final class zzfs extends zzg<zzfo>
{
    private static final zzfs zzCo;
    
    static {
        zzCo = new zzfs();
    }
    
    private zzfs() {
        super("com.google.android.gms.ads.InAppPurchaseManagerCreatorImpl");
    }
    
    private static boolean zzc(final Activity activity) {
        final Intent intent = activity.getIntent();
        if (!intent.hasExtra("com.google.android.gms.ads.internal.purchase.useClientJar")) {
            throw new zzfs$zza("InAppPurchaseManager requires the useClientJar flag in intent extras.");
        }
        return intent.getBooleanExtra("com.google.android.gms.ads.internal.purchase.useClientJar", false);
    }
    
    public static zzfn zze(final Activity activity) {
        try {
            if (zzc(activity)) {
                zzb.zzaC("Using AdOverlay from the client jar.");
                return new zze(activity);
            }
            return zzfs.zzCo.zzf(activity);
        }
        catch (zzfs$zza zzfs$zza) {
            zzb.zzaE(zzfs$zza.getMessage());
            return null;
        }
    }
    
    private zzfn zzf(final Activity activity) {
        try {
            return zzfn$zza.zzP(this.zzar((Context)activity).zzf(com.google.android.gms.dynamic.zze.zzx(activity)));
        }
        catch (RemoteException ex) {
            zzb.zzd("Could not create remote InAppPurchaseManager.", (Throwable)ex);
            return null;
        }
        catch (zzg$zza zzg$zza) {
            zzb.zzd("Could not create remote InAppPurchaseManager.", zzg$zza);
            return null;
        }
    }
    
    protected zzfo zzT(final IBinder binder) {
        return zzfo$zza.zzQ(binder);
    }
}
