// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.overlay;

import android.app.Activity;
import com.google.android.gms.internal.zzmx;
import android.content.Intent;
import com.google.android.gms.ads.internal.zzp;
import android.content.Context;

public class zze
{
    public void zza(final Context context, final AdOverlayInfoParcel adOverlayInfoParcel, final boolean b) {
        if (adOverlayInfoParcel.zzBJ == 4 && adOverlayInfoParcel.zzBC == null) {
            if (adOverlayInfoParcel.zzBB != null) {
                adOverlayInfoParcel.zzBB.onAdClicked();
            }
            zzp.zzbs().zza(context, adOverlayInfoParcel.zzBA, adOverlayInfoParcel.zzBI);
            return;
        }
        final Intent intent = new Intent();
        intent.setClassName(context, "com.google.android.gms.ads.AdActivity");
        intent.putExtra("com.google.android.gms.ads.internal.overlay.useClientJar", adOverlayInfoParcel.zzqj.zzJx);
        intent.putExtra("shouldCallOnOverlayOpened", b);
        AdOverlayInfoParcel.zza(intent, adOverlayInfoParcel);
        if (!zzmx.isAtLeastL()) {
            intent.addFlags(524288);
        }
        if (!(context instanceof Activity)) {
            intent.addFlags(268435456);
        }
        zzp.zzbv().zzb(context, intent);
    }
}
