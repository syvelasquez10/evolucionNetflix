// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.purchase;

import com.google.android.gms.internal.zzfs;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.content.Intent;
import com.google.android.gms.internal.zzfn;
import android.app.Activity;

public class InAppPurchaseActivity extends Activity
{
    private zzfn zzJM;
    
    protected void onActivityResult(final int n, final int n2, final Intent intent) {
        while (true) {
            try {
                if (this.zzJM != null) {
                    this.zzJM.onActivityResult(n, n2, intent);
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
        this.zzJM = zzfs.zze(this);
        if (this.zzJM == null) {
            zzb.zzaE("Could not create in-app purchase manager.");
            this.finish();
            return;
        }
        try {
            this.zzJM.onCreate();
        }
        catch (RemoteException ex) {
            zzb.zzd("Could not forward onCreate to in-app purchase manager:", (Throwable)ex);
            this.finish();
        }
    }
    
    protected void onDestroy() {
        while (true) {
            try {
                if (this.zzJM != null) {
                    this.zzJM.onDestroy();
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
