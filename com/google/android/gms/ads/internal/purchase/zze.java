// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.purchase;

import android.content.IntentSender$SendIntentException;
import android.os.Bundle;
import android.app.PendingIntent;
import android.os.IBinder;
import android.content.ComponentName;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.zzp;
import android.content.Intent;
import com.google.android.gms.internal.zzfr;
import android.content.Context;
import android.app.Activity;
import com.google.android.gms.internal.zzgr;
import android.content.ServiceConnection;
import com.google.android.gms.internal.zzft$zza;

@zzgr
public class zze extends zzft$zza implements ServiceConnection
{
    private final Activity mActivity;
    private zzb zzCD;
    zzh zzCE;
    private zzk zzCG;
    private Context zzCL;
    private zzfr zzCM;
    private zzf zzCN;
    private zzj zzCO;
    private String zzCP;
    
    public zze(final Activity mActivity) {
        this.zzCP = null;
        this.mActivity = mActivity;
        this.zzCE = zzh.zzw(this.mActivity.getApplicationContext());
    }
    
    public void onActivityResult(int zzd, final int n, final Intent intent) {
        if (zzd != 1001) {
            return;
        }
        boolean b = false;
        try {
            zzd = zzp.zzbF().zzd(intent);
            while (true) {
                Label_0090: {
                    if (n != -1) {
                        break Label_0090;
                    }
                    zzp.zzbF();
                    if (zzd != 0) {
                        break Label_0090;
                    }
                    if (this.zzCG.zza(this.zzCP, n, intent)) {
                        b = true;
                    }
                    this.zzCM.recordPlayBillingResolution(zzd);
                    this.mActivity.finish();
                    this.zza(this.zzCM.getProductId(), b, n, intent);
                    return;
                }
                this.zzCE.zza(this.zzCN);
                continue;
            }
        }
        catch (RemoteException ex) {
            com.google.android.gms.ads.internal.util.client.zzb.zzaH("Fail to process purchase result.");
            this.mActivity.finish();
        }
        finally {
            this.zzCP = null;
        }
    }
    
    public void onCreate() {
        final GInAppPurchaseManagerInfoParcel zzc = GInAppPurchaseManagerInfoParcel.zzc(this.mActivity.getIntent());
        this.zzCO = zzc.zzCy;
        this.zzCG = zzc.zzqE;
        this.zzCM = zzc.zzCw;
        this.zzCD = new zzb(this.mActivity.getApplicationContext());
        this.zzCL = zzc.zzCx;
        if (this.mActivity.getResources().getConfiguration().orientation == 2) {
            this.mActivity.setRequestedOrientation(zzp.zzbx().zzgG());
        }
        else {
            this.mActivity.setRequestedOrientation(zzp.zzbx().zzgH());
        }
        final Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
        intent.setPackage("com.android.vending");
        this.mActivity.bindService(intent, (ServiceConnection)this, 1);
    }
    
    public void onDestroy() {
        this.mActivity.unbindService((ServiceConnection)this);
        this.zzCD.destroy();
    }
    
    public void onServiceConnected(ComponentName zzb, final IBinder binder) {
        this.zzCD.zzN(binder);
        try {
            this.zzCP = this.zzCG.zzfq();
            zzb = (IntentSender$SendIntentException)this.zzCD.zzb(this.mActivity.getPackageName(), this.zzCM.getProductId(), this.zzCP);
            final PendingIntent pendingIntent = (PendingIntent)((Bundle)zzb).getParcelable("BUY_INTENT");
            if (pendingIntent == null) {
                final int zzc = zzp.zzbF().zzc((Bundle)zzb);
                this.zzCM.recordPlayBillingResolution(zzc);
                this.zza(this.zzCM.getProductId(), false, zzc, null);
                this.mActivity.finish();
                return;
            }
            this.zzCN = new zzf(this.zzCM.getProductId(), this.zzCP);
            this.zzCE.zzb(this.zzCN);
            this.mActivity.startIntentSenderForResult(pendingIntent.getIntentSender(), 1001, new Intent(), (int)Integer.valueOf(0), (int)Integer.valueOf(0), (int)Integer.valueOf(0));
        }
        catch (RemoteException ex) {}
        catch (IntentSender$SendIntentException zzb) {
            goto Label_0182;
        }
    }
    
    public void onServiceDisconnected(final ComponentName componentName) {
        com.google.android.gms.ads.internal.util.client.zzb.zzaG("In-app billing service disconnected.");
        this.zzCD.destroy();
    }
    
    protected void zza(final String s, final boolean b, final int n, final Intent intent) {
        if (this.zzCO != null) {
            this.zzCO.zza(s, b, n, intent, this.zzCN);
        }
    }
}
