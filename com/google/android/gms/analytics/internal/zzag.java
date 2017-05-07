// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics.internal;

import android.net.NetworkInfo;
import android.net.ConnectivityManager;
import android.os.Build$VERSION;
import android.content.IntentFilter;
import android.content.Intent;
import android.content.Context;
import com.google.android.gms.common.internal.zzx;
import android.content.BroadcastReceiver;

class zzag extends BroadcastReceiver
{
    static final String zzOo;
    private final zzf zzLy;
    private boolean zzOp;
    private boolean zzOq;
    
    static {
        zzOo = zzag.class.getName();
    }
    
    zzag(final zzf zzLy) {
        zzx.zzv(zzLy);
        this.zzLy = zzLy;
    }
    
    private Context getContext() {
        return this.zzLy.getContext();
    }
    
    private zzb zzhz() {
        return this.zzLy.zzhz();
    }
    
    private zzaf zzie() {
        return this.zzLy.zzie();
    }
    
    private void zzks() {
        this.zzie();
        this.zzhz();
    }
    
    public boolean isConnected() {
        if (!this.zzOp) {
            this.zzLy.zzie().zzbb("Connectivity unknown. Receiver not registered");
        }
        return this.zzOq;
    }
    
    public boolean isRegistered() {
        return this.zzOp;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        this.zzks();
        final String action = intent.getAction();
        this.zzLy.zzie().zza("NetworkBroadcastReceiver received action", action);
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
            final boolean zzku = this.zzku();
            if (this.zzOq != zzku) {
                this.zzOq = zzku;
                this.zzhz().zzI(zzku);
            }
        }
        else {
            if (!"com.google.analytics.RADIO_POWERED".equals(action)) {
                this.zzLy.zzie().zzd("NetworkBroadcastReceiver received unknown action", action);
                return;
            }
            if (!intent.hasExtra(zzag.zzOo)) {
                this.zzhz().zzhY();
            }
        }
    }
    
    public void unregister() {
        if (!this.isRegistered()) {
            return;
        }
        this.zzLy.zzie().zzaY("Unregistering connectivity change receiver");
        this.zzOp = false;
        this.zzOq = false;
        final Context context = this.getContext();
        try {
            context.unregisterReceiver((BroadcastReceiver)this);
        }
        catch (IllegalArgumentException ex) {
            this.zzie().zze("Failed to unregister the network broadcast receiver", ex);
        }
    }
    
    public void zzkr() {
        this.zzks();
        if (this.zzOp) {
            return;
        }
        final Context context = this.getContext();
        context.registerReceiver((BroadcastReceiver)this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        final IntentFilter intentFilter = new IntentFilter("com.google.analytics.RADIO_POWERED");
        intentFilter.addCategory(context.getPackageName());
        context.registerReceiver((BroadcastReceiver)this, intentFilter);
        this.zzOq = this.zzku();
        this.zzLy.zzie().zza("Registering connectivity change receiver. Network connected", this.zzOq);
        this.zzOp = true;
    }
    
    public void zzkt() {
        if (Build$VERSION.SDK_INT <= 10) {
            return;
        }
        final Context context = this.getContext();
        final Intent intent = new Intent("com.google.analytics.RADIO_POWERED");
        intent.addCategory(context.getPackageName());
        intent.putExtra(zzag.zzOo, true);
        context.sendOrderedBroadcast(intent, (String)null);
    }
    
    protected boolean zzku() {
        final ConnectivityManager connectivityManager = (ConnectivityManager)this.getContext().getSystemService("connectivity");
        try {
            final NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        catch (SecurityException ex) {
            return false;
        }
    }
}
