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

@zzgr
public final class zzfy extends zzg<zzfu>
{
    private static final zzfy zzDb;
    
    static {
        zzDb = new zzfy();
    }
    
    private zzfy() {
        super("com.google.android.gms.ads.InAppPurchaseManagerCreatorImpl");
    }
    
    private static boolean zzc(final Activity activity) {
        final Intent intent = activity.getIntent();
        if (!intent.hasExtra("com.google.android.gms.ads.internal.purchase.useClientJar")) {
            throw new zzfy$zza("InAppPurchaseManager requires the useClientJar flag in intent extras.");
        }
        return intent.getBooleanExtra("com.google.android.gms.ads.internal.purchase.useClientJar", false);
    }
    
    public static zzft zze(final Activity activity) {
        try {
            if (zzc(activity)) {
                zzb.zzaF("Using AdOverlay from the client jar.");
                return new zze(activity);
            }
            return zzfy.zzDb.zzf(activity);
        }
        catch (zzfy$zza zzfy$zza) {
            zzb.zzaH(zzfy$zza.getMessage());
            return null;
        }
    }
    
    private zzft zzf(final Activity activity) {
        try {
            return zzft$zza.zzQ(this.zzas((Context)activity).zzf(com.google.android.gms.dynamic.zze.zzy(activity)));
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
    
    protected zzfu zzU(final IBinder binder) {
        return zzfu$zza.zzR(binder);
    }
}
