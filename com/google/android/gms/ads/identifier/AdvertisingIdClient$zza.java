// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.identifier;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.CountDownLatch;
import java.lang.ref.WeakReference;

class AdvertisingIdClient$zza extends Thread
{
    private WeakReference<AdvertisingIdClient> zzoi;
    private long zzoj;
    CountDownLatch zzok;
    boolean zzol;
    
    public AdvertisingIdClient$zza(final AdvertisingIdClient advertisingIdClient, final long zzoj) {
        this.zzoi = new WeakReference<AdvertisingIdClient>(advertisingIdClient);
        this.zzoj = zzoj;
        this.zzok = new CountDownLatch(1);
        this.zzol = false;
        this.start();
    }
    
    private void disconnect() {
        final AdvertisingIdClient advertisingIdClient = this.zzoi.get();
        if (advertisingIdClient != null) {
            advertisingIdClient.finish();
            this.zzol = true;
        }
    }
    
    public void cancel() {
        this.zzok.countDown();
    }
    
    @Override
    public void run() {
        try {
            if (!this.zzok.await(this.zzoj, TimeUnit.MILLISECONDS)) {
                this.disconnect();
            }
        }
        catch (InterruptedException ex) {
            this.disconnect();
        }
    }
    
    public boolean zzaK() {
        return this.zzol;
    }
}
