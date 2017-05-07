// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.IBinder;
import android.content.ComponentName;
import android.content.Intent;
import android.content.Context;
import android.content.ServiceConnection;

@ez
public final class eb extends ek.a implements ServiceConnection
{
    private Context mContext;
    private boolean sD;
    private int sE;
    private Intent sF;
    private dw sn;
    private String su;
    private ea sy;
    
    public eb(final Context mContext, final String su, final boolean sd, final int se, final Intent sf, final ea sy) {
        this.sD = false;
        this.su = su;
        this.sE = se;
        this.sF = sf;
        this.sD = sd;
        this.mContext = mContext;
        this.sy = sy;
    }
    
    public void finishPurchase() {
        final int d = ed.d(this.sF);
        if (this.sE != -1 || d != 0) {
            return;
        }
        this.sn = new dw(this.mContext);
        final Context mContext = this.mContext;
        final Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
        final Context mContext2 = this.mContext;
        mContext.bindService(intent, (ServiceConnection)this, 1);
    }
    
    public String getProductId() {
        return this.su;
    }
    
    public Intent getPurchaseData() {
        return this.sF;
    }
    
    public int getResultCode() {
        return this.sE;
    }
    
    public boolean isVerified() {
        return this.sD;
    }
    
    public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
        gs.U("In-app billing service connected.");
        this.sn.r(binder);
        final String e = ed.E(ed.e(this.sF));
        if (e == null) {
            return;
        }
        if (this.sn.c(this.mContext.getPackageName(), e) == 0) {
            ec.j(this.mContext).a(this.sy);
        }
        this.mContext.unbindService((ServiceConnection)this);
        this.sn.destroy();
    }
    
    public void onServiceDisconnected(final ComponentName componentName) {
        gs.U("In-app billing service disconnected.");
        this.sn.destroy();
    }
}
