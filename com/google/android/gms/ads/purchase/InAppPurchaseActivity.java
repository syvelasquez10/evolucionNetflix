// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.purchase;

import com.google.android.gms.internal.en;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.internal.gs;
import android.content.Intent;
import com.google.android.gms.internal.ei;
import android.app.Activity;

public final class InAppPurchaseActivity extends Activity
{
    public static final String CLASS_NAME = "com.google.android.gms.ads.purchase.InAppPurchaseActivity";
    public static final String SIMPLE_CLASS_NAME = "InAppPurchaseActivity";
    private ei xk;
    
    protected void onActivityResult(final int n, final int n2, final Intent intent) {
        while (true) {
            try {
                if (this.xk != null) {
                    this.xk.onActivityResult(n, n2, intent);
                }
                super.onActivityResult(n, n2, intent);
            }
            catch (RemoteException ex) {
                gs.d("Could not forward onActivityResult to in-app purchase manager:", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.xk = en.e(this);
        if (this.xk == null) {
            gs.W("Could not create in-app purchase manager.");
            this.finish();
            return;
        }
        try {
            this.xk.onCreate();
        }
        catch (RemoteException ex) {
            gs.d("Could not forward onCreate to in-app purchase manager:", (Throwable)ex);
            this.finish();
        }
    }
    
    protected void onDestroy() {
        while (true) {
            try {
                if (this.xk != null) {
                    this.xk.onDestroy();
                }
                super.onDestroy();
            }
            catch (RemoteException ex) {
                gs.d("Could not forward onDestroy to in-app purchase manager:", (Throwable)ex);
                continue;
            }
            break;
        }
    }
}
