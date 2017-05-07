// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.IntentSender$SendIntentException;
import android.os.Bundle;
import android.app.PendingIntent;
import android.os.IBinder;
import android.content.ComponentName;
import android.os.RemoteException;
import android.content.Intent;
import android.content.Context;
import android.app.Activity;
import android.content.ServiceConnection;

@ez
public class dz extends ei.a implements ServiceConnection
{
    private final Activity nr;
    private el sm;
    private dw sn;
    private final ec so;
    private ee sq;
    private Context sw;
    private eg sx;
    private ea sy;
    private String sz;
    
    public dz(final Activity nr) {
        this.sz = null;
        this.nr = nr;
        this.so = ec.j(this.nr.getApplicationContext());
    }
    
    public static void a(final Context context, final boolean b, final dv dv) {
        final Intent intent = new Intent();
        intent.setClassName(context, "com.google.android.gms.ads.purchase.InAppPurchaseActivity");
        intent.putExtra("com.google.android.gms.ads.internal.purchase.useClientJar", b);
        dv.a(intent, dv);
        context.startActivity(intent);
    }
    
    private void a(final String s, final boolean b, final int n, final Intent intent) {
        try {
            this.sm.a(new eb(this.sw, s, b, n, intent, this.sy));
        }
        catch (RemoteException ex) {
            gs.W("Fail to invoke PlayStorePurchaseListener.");
        }
    }
    
    public void onActivityResult(int d, final int n, final Intent intent) {
        if (d != 1001) {
            return;
        }
        while (true) {
            Label_0114: {
                try {
                    d = ed.d(intent);
                    if (n == -1 && d == 0) {
                        if (this.sq.a(this.sz, n, intent)) {
                            this.a(this.sx.getProductId(), true, n, intent);
                        }
                        else {
                            this.a(this.sx.getProductId(), false, n, intent);
                        }
                        this.sx.recordPlayBillingResolution(d);
                        return;
                    }
                    break Label_0114;
                }
                catch (RemoteException ex) {
                    gs.W("Fail to process purchase result.");
                    return;
                    this.so.a(this.sy);
                    final Intent intent2;
                    this.a(this.sx.getProductId(), false, n, intent2);
                    continue;
                }
                finally {
                    this.sz = null;
                    this.nr.finish();
                }
            }
            break;
        }
    }
    
    public void onCreate() {
        final dv c = dv.c(this.nr.getIntent());
        this.sm = c.lM;
        this.sq = c.lT;
        this.sx = c.si;
        this.sn = new dw(this.nr.getApplicationContext());
        this.sw = c.sj;
        final Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
        intent.setPackage("com.android.vending");
        this.nr.bindService(intent, (ServiceConnection)this, 1);
    }
    
    public void onDestroy() {
        this.nr.unbindService((ServiceConnection)this);
        this.sn.destroy();
    }
    
    public void onServiceConnected(ComponentName a, final IBinder binder) {
        this.sn.r(binder);
        try {
            this.sz = this.sq.cu();
            a = (IntentSender$SendIntentException)this.sn.a(this.nr.getPackageName(), this.sx.getProductId(), this.sz);
            final PendingIntent pendingIntent = (PendingIntent)((Bundle)a).getParcelable("BUY_INTENT");
            if (pendingIntent == null) {
                final int b = ed.b((Bundle)a);
                this.sx.recordPlayBillingResolution(b);
                this.a(this.sx.getProductId(), false, b, null);
                this.nr.finish();
                return;
            }
            this.sy = new ea(this.sx.getProductId(), this.sz);
            this.so.b(this.sy);
            this.nr.startIntentSenderForResult(pendingIntent.getIntentSender(), 1001, new Intent(), (int)Integer.valueOf(0), (int)Integer.valueOf(0), (int)Integer.valueOf(0));
        }
        catch (RemoteException ex) {}
        catch (IntentSender$SendIntentException a) {
            goto Label_0179;
        }
    }
    
    public void onServiceDisconnected(final ComponentName componentName) {
        gs.U("In-app billing service disconnected.");
        this.sn.destroy();
    }
}
