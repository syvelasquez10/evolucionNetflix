// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.overlay;

import android.app.Activity;
import com.google.android.gms.internal.zzlv;
import android.content.Intent;
import com.google.android.gms.ads.internal.zzp;
import android.content.Context;

public class zze
{
    public void zza(final Context context, final AdOverlayInfoParcel adOverlayInfoParcel, final boolean b) {
        if (adOverlayInfoParcel.zzAX == 4 && adOverlayInfoParcel.zzAQ == null) {
            if (adOverlayInfoParcel.zzAP != null) {
                adOverlayInfoParcel.zzAP.onAdClicked();
            }
            zzp.zzbu().zza(context, adOverlayInfoParcel.zzAO, adOverlayInfoParcel.zzAW);
            return;
        }
        final Intent intent = new Intent();
        intent.setClassName(context, "com.google.android.gms.ads.AdActivity");
        intent.putExtra("com.google.android.gms.ads.internal.overlay.useClientJar", adOverlayInfoParcel.zzqb.zzIC);
        intent.putExtra("shouldCallOnOverlayOpened", b);
        AdOverlayInfoParcel.zza(intent, adOverlayInfoParcel);
        if (!zzlv.isAtLeastL()) {
            intent.addFlags(524288);
        }
        if (!(context instanceof Activity)) {
            intent.addFlags(268435456);
        }
        context.startActivity(intent);
    }
}
