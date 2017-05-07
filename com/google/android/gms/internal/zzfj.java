// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.IBinder;
import com.google.android.gms.dynamic.zzg$zza;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zze;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.ads.internal.overlay.zzd;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.app.Activity;
import com.google.android.gms.dynamic.zzg;

@zzgr
public final class zzfj extends zzg<zzfl>
{
    private static final zzfj zzCp;
    
    static {
        zzCp = new zzfj();
    }
    
    private zzfj() {
        super("com.google.android.gms.ads.AdOverlayCreatorImpl");
    }
    
    public static zzfk zzb(final Activity activity) {
        try {
            if (zzc(activity)) {
                zzb.zzaF("Using AdOverlay from the client jar.");
                return new zzd(activity);
            }
            return zzfj.zzCp.zzd(activity);
        }
        catch (zzfj$zza zzfj$zza) {
            zzb.zzaH(zzfj$zza.getMessage());
            return null;
        }
    }
    
    private static boolean zzc(final Activity activity) {
        final Intent intent = activity.getIntent();
        if (!intent.hasExtra("com.google.android.gms.ads.internal.overlay.useClientJar")) {
            throw new zzfj$zza("Ad overlay requires the useClientJar flag in intent extras.");
        }
        return intent.getBooleanExtra("com.google.android.gms.ads.internal.overlay.useClientJar", false);
    }
    
    private zzfk zzd(final Activity activity) {
        try {
            return zzfk$zza.zzL(this.zzas((Context)activity).zze(zze.zzy(activity)));
        }
        catch (RemoteException ex) {
            zzb.zzd("Could not create remote AdOverlay.", (Throwable)ex);
            return null;
        }
        catch (zzg$zza zzg$zza) {
            zzb.zzd("Could not create remote AdOverlay.", zzg$zza);
            return null;
        }
    }
    
    protected zzfl zzK(final IBinder binder) {
        return zzfl$zza.zzM(binder);
    }
}
