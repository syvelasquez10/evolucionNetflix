// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.purchase;

import com.google.android.gms.internal.zzfy;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.content.Intent;
import com.google.android.gms.internal.zzft;
import android.app.Activity;

public class InAppPurchaseActivity extends Activity
{
    private zzft zzKR;
    
    protected void onActivityResult(final int n, final int n2, final Intent intent) {
        while (true) {
            try {
                if (this.zzKR != null) {
                    this.zzKR.onActivityResult(n, n2, intent);
                }
                super.onActivityResult(n, n2, intent);
            }
            catch (RemoteException ex) {
                zzb.zzd("Could not forward onActivityResult to in-app purchase manager:", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.zzKR = zzfy.zze(this);
        if (this.zzKR == null) {
            zzb.zzaH("Could not create in-app purchase manager.");
            this.finish();
            return;
        }
        try {
            this.zzKR.onCreate();
        }
        catch (RemoteException ex) {
            zzb.zzd("Could not forward onCreate to in-app purchase manager:", (Throwable)ex);
            this.finish();
        }
    }
    
    protected void onDestroy() {
        while (true) {
            try {
                if (this.zzKR != null) {
                    this.zzKR.onDestroy();
                }
                super.onDestroy();
            }
            catch (RemoteException ex) {
                zzb.zzd("Could not forward onDestroy to in-app purchase manager:", (Throwable)ex);
                continue;
            }
            break;
        }
    }
}
