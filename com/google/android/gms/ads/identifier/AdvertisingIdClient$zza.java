// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.identifier;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.CountDownLatch;
import java.lang.ref.WeakReference;

class AdvertisingIdClient$zza extends Thread
{
    private WeakReference<AdvertisingIdClient> zzoh;
    private long zzoi;
    CountDownLatch zzoj;
    boolean zzok;
    
    public AdvertisingIdClient$zza(final AdvertisingIdClient advertisingIdClient, final long zzoi) {
        this.zzoh = new WeakReference<AdvertisingIdClient>(advertisingIdClient);
        this.zzoi = zzoi;
        this.zzoj = new CountDownLatch(1);
        this.zzok = false;
        this.start();
    }
    
    private void disconnect() {
        final AdvertisingIdClient advertisingIdClient = this.zzoh.get();
        if (advertisingIdClient != null) {
            advertisingIdClient.finish();
            this.zzok = true;
        }
    }
    
    public void cancel() {
        this.zzoj.countDown();
    }
    
    @Override
    public void run() {
        try {
            if (!this.zzoj.await(this.zzoi, TimeUnit.MILLISECONDS)) {
                this.disconnect();
            }
        }
        catch (InterruptedException ex) {
            this.disconnect();
        }
    }
    
    public boolean zzaK() {
        return this.zzok;
    }
}
