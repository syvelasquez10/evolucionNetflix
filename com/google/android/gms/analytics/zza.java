// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import com.google.android.gms.internal.zzod;
import java.util.ListIterator;
import android.net.Uri;
import com.google.android.gms.internal.zzoh;
import com.google.android.gms.common.internal.zzx;
import android.text.TextUtils;
import com.google.android.gms.internal.zzjb;
import com.google.android.gms.internal.zzob;
import com.google.android.gms.analytics.internal.zzf;
import com.google.android.gms.internal.zzoe;

public class zza extends zzoe<zza>
{
    private final zzf zzKa;
    private boolean zzKb;
    
    public zza(final zzf zzKa) {
        super(zzKa.zzig(), zzKa.zzid());
        this.zzKa = zzKa;
    }
    
    public void enableAdvertisingIdCollection(final boolean zzKb) {
        this.zzKb = zzKb;
    }
    
    @Override
    protected void zza(final zzob zzob) {
        final zzjb zzjb = zzob.zze(zzjb.class);
        if (TextUtils.isEmpty((CharSequence)zzjb.getClientId())) {
            zzjb.setClientId(this.zzKa.zziv().zzjd());
        }
        if (this.zzKb && TextUtils.isEmpty((CharSequence)zzjb.zzhL())) {
            final com.google.android.gms.analytics.internal.zza zziu = this.zzKa.zziu();
            zzjb.zzaT(zziu.zzhQ());
            zzjb.zzG(zziu.zzhM());
        }
    }
    
    public void zzaN(final String s) {
        zzx.zzcs(s);
        this.zzaO(s);
        this.zzxt().add(new zzb(this.zzKa, s));
    }
    
    public void zzaO(final String s) {
        final Uri zzaP = zzb.zzaP(s);
        final ListIterator<zzoh> listIterator = this.zzxt().listIterator();
        while (listIterator.hasNext()) {
            if (zzaP.equals((Object)listIterator.next().zzhs())) {
                listIterator.remove();
            }
        }
    }
    
    zzf zzhp() {
        return this.zzKa;
    }
    
    @Override
    public zzob zzhq() {
        final zzob zzxh = this.zzxs().zzxh();
        zzxh.zzb(this.zzKa.zzil().zziL());
        zzxh.zzb(this.zzKa.zzim().zzjS());
        this.zzd(zzxh);
        return zzxh;
    }
}
