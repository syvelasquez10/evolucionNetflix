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

@zzgk
public final class zzfd extends zzg<zzff>
{
    private static final zzfd zzBC;
    
    static {
        zzBC = new zzfd();
    }
    
    private zzfd() {
        super("com.google.android.gms.ads.AdOverlayCreatorImpl");
    }
    
    public static zzfe zzb(final Activity activity) {
        try {
            if (zzc(activity)) {
                zzb.zzaC("Using AdOverlay from the client jar.");
                return new zzd(activity);
            }
            return zzfd.zzBC.zzd(activity);
        }
        catch (zzfd$zza zzfd$zza) {
            zzb.zzaE(zzfd$zza.getMessage());
            return null;
        }
    }
    
    private static boolean zzc(final Activity activity) {
        final Intent intent = activity.getIntent();
        if (!intent.hasExtra("com.google.android.gms.ads.internal.overlay.useClientJar")) {
            throw new zzfd$zza("Ad overlay requires the useClientJar flag in intent extras.");
        }
        return intent.getBooleanExtra("com.google.android.gms.ads.internal.overlay.useClientJar", false);
    }
    
    private zzfe zzd(final Activity activity) {
        try {
            return zzfe$zza.zzK(this.zzar((Context)activity).zze(zze.zzx(activity)));
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
    
    protected zzff zzJ(final IBinder binder) {
        return zzff$zza.zzL(binder);
    }
}
