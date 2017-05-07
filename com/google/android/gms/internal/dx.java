// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.IBinder;
import android.content.ComponentName;
import java.util.ArrayList;
import android.os.Bundle;
import java.util.Iterator;
import java.util.HashMap;
import android.os.SystemClock;
import android.content.Intent;
import java.util.List;
import android.content.Context;
import android.content.ServiceConnection;

@ez
public class dx extends gg implements ServiceConnection
{
    private Context mContext;
    private final Object mw;
    private boolean sl;
    private el sm;
    private dw sn;
    private ec so;
    private List<ea> sp;
    private ee sq;
    
    public dx(final Context mContext, final el sm, final ee sq) {
        this.mw = new Object();
        this.sl = false;
        this.sp = null;
        this.mContext = mContext;
        this.sm = sm;
        this.sq = sq;
        this.sn = new dw(mContext);
        this.so = ec.j(this.mContext);
        this.sp = this.so.d(10L);
    }
    
    private void a(final ea ea, final String s, final String s2) {
        final Intent intent = new Intent();
        intent.putExtra("RESPONSE_CODE", 0);
        intent.putExtra("INAPP_PURCHASE_DATA", s);
        intent.putExtra("INAPP_DATA_SIGNATURE", s2);
        gr.wC.post((Runnable)new dx$1(this, ea, intent));
    }
    
    private void b(final long n) {
        do {
            if (!this.c(n)) {
                gs.W("Timeout waiting for pending transaction to be processed.");
            }
        } while (!this.sl);
    }
    
    private boolean c(long n) {
        n = 60000L - (SystemClock.elapsedRealtime() - n);
        if (n <= 0L) {
            return false;
        }
        try {
            this.mw.wait(n);
            return true;
        }
        catch (InterruptedException ex) {
            gs.W("waitWithTimeout_lock interrupted");
            return true;
        }
    }
    
    private void cq() {
        if (!this.sp.isEmpty()) {
            final HashMap<String, ea> hashMap = new HashMap<String, ea>();
            for (final ea ea : this.sp) {
                hashMap.put(ea.sC, ea);
            }
            String string = null;
            do {
                final Bundle d = this.sn.d(this.mContext.getPackageName(), string);
                if (d == null || ed.b(d) != 0) {
                    break;
                }
                final ArrayList stringArrayList = d.getStringArrayList("INAPP_PURCHASE_ITEM_LIST");
                final ArrayList stringArrayList2 = d.getStringArrayList("INAPP_PURCHASE_DATA_LIST");
                final ArrayList stringArrayList3 = d.getStringArrayList("INAPP_DATA_SIGNATURE_LIST");
                string = d.getString("INAPP_CONTINUATION_TOKEN");
                for (int i = 0; i < stringArrayList.size(); ++i) {
                    if (hashMap.containsKey(stringArrayList.get(i))) {
                        final String s = stringArrayList.get(i);
                        final String s2 = stringArrayList2.get(i);
                        final String s3 = stringArrayList3.get(i);
                        final ea ea2 = hashMap.get(s);
                        if (ea2.sB.equals(ed.D(s2))) {
                            this.a(ea2, s2, s3);
                            hashMap.remove(s);
                        }
                    }
                }
            } while (string != null && !hashMap.isEmpty());
            final Iterator<String> iterator2 = hashMap.keySet().iterator();
            while (iterator2.hasNext()) {
                this.so.a(hashMap.get(iterator2.next()));
            }
        }
    }
    
    @Override
    public void cp() {
        synchronized (this.mw) {
            final Context mContext = this.mContext;
            final Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
            final Context mContext2 = this.mContext;
            mContext.bindService(intent, (ServiceConnection)this, 1);
            this.b(SystemClock.elapsedRealtime());
            this.mContext.unbindService((ServiceConnection)this);
            this.sn.destroy();
        }
    }
    
    public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
        synchronized (this.mw) {
            this.sn.r(binder);
            this.cq();
            this.sl = true;
            this.mw.notify();
        }
    }
    
    public void onServiceDisconnected(final ComponentName componentName) {
        gs.U("In-app billing service disconnected.");
        this.sn.destroy();
    }
    
    @Override
    public void onStop() {
        synchronized (this.mw) {
            this.mContext.unbindService((ServiceConnection)this);
            this.sn.destroy();
        }
    }
}
