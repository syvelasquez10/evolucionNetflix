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
import com.google.android.gms.internal.zzfl;
import android.content.Context;
import android.app.Activity;
import com.google.android.gms.internal.zzgk;
import android.content.ServiceConnection;
import com.google.android.gms.internal.zzfn$zza;

@zzgk
public class zze extends zzfn$zza implements ServiceConnection
{
    private final Activity mActivity;
    private zzb zzBQ;
    zzh zzBR;
    private zzk zzBT;
    private Context zzBY;
    private zzfl zzBZ;
    private zzf zzCa;
    private zzj zzCb;
    private String zzCc;
    
    public zze(final Activity mActivity) {
        this.zzCc = null;
        this.mActivity = mActivity;
        this.zzBR = zzh.zzx(this.mActivity.getApplicationContext());
    }
    
    public void onActivityResult(int zzd, final int n, final Intent intent) {
        if (zzd != 1001) {
            return;
        }
        boolean b = false;
        try {
            zzd = zzp.zzbH().zzd(intent);
            while (true) {
                Label_0090: {
                    if (n != -1) {
                        break Label_0090;
                    }
                    zzp.zzbH();
                    if (zzd != 0) {
                        break Label_0090;
                    }
                    if (this.zzBT.zza(this.zzCc, n, intent)) {
                        b = true;
                    }
                    this.zzBZ.recordPlayBillingResolution(zzd);
                    this.mActivity.finish();
                    this.zza(this.zzBZ.getProductId(), b, n, intent);
                    return;
                }
                this.zzBR.zza(this.zzCa);
                continue;
            }
        }
        catch (RemoteException ex) {
            com.google.android.gms.ads.internal.util.client.zzb.zzaE("Fail to process purchase result.");
            this.mActivity.finish();
        }
        finally {
            this.zzCc = null;
        }
    }
    
    public void onCreate() {
        final GInAppPurchaseManagerInfoParcel zzc = GInAppPurchaseManagerInfoParcel.zzc(this.mActivity.getIntent());
        this.zzCb = zzc.zzBL;
        this.zzBT = zzc.zzqw;
        this.zzBZ = zzc.zzBJ;
        this.zzBQ = new zzb(this.mActivity.getApplicationContext());
        this.zzBY = zzc.zzBK;
        if (this.mActivity.getResources().getConfiguration().orientation == 2) {
            this.mActivity.setRequestedOrientation(zzp.zzbz().zzgv());
        }
        else {
            this.mActivity.setRequestedOrientation(zzp.zzbz().zzgw());
        }
        final Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
        intent.setPackage("com.android.vending");
        this.mActivity.bindService(intent, (ServiceConnection)this, 1);
    }
    
    public void onDestroy() {
        this.mActivity.unbindService((ServiceConnection)this);
        this.zzBQ.destroy();
    }
    
    public void onServiceConnected(ComponentName zzb, final IBinder binder) {
        this.zzBQ.zzM(binder);
        try {
            this.zzCc = this.zzBT.zzfk();
            zzb = (IntentSender$SendIntentException)this.zzBQ.zzb(this.mActivity.getPackageName(), this.zzBZ.getProductId(), this.zzCc);
            final PendingIntent pendingIntent = (PendingIntent)((Bundle)zzb).getParcelable("BUY_INTENT");
            if (pendingIntent == null) {
                final int zzc = zzp.zzbH().zzc((Bundle)zzb);
                this.zzBZ.recordPlayBillingResolution(zzc);
                this.zza(this.zzBZ.getProductId(), false, zzc, null);
                this.mActivity.finish();
                return;
            }
            this.zzCa = new zzf(this.zzBZ.getProductId(), this.zzCc);
            this.zzBR.zzb(this.zzCa);
            this.mActivity.startIntentSenderForResult(pendingIntent.getIntentSender(), 1001, new Intent(), (int)Integer.valueOf(0), (int)Integer.valueOf(0), (int)Integer.valueOf(0));
        }
        catch (RemoteException ex) {}
        catch (IntentSender$SendIntentException zzb) {
            goto Label_0182;
        }
    }
    
    public void onServiceDisconnected(final ComponentName componentName) {
        com.google.android.gms.ads.internal.util.client.zzb.zzaD("In-app billing service disconnected.");
        this.zzBQ.destroy();
    }
    
    protected void zza(final String s, final boolean b, final int n, final Intent intent) {
        if (this.zzCb != null) {
            this.zzCb.zza(s, b, n, intent, this.zzCa);
        }
    }
}
