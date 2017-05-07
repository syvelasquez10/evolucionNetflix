// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.identifier;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.CountDownLatch;
import java.lang.ref.WeakReference;

class AdvertisingIdClient$zza extends Thread
{
    private WeakReference<AdvertisingIdClient> zzod;
    private long zzoe;
    CountDownLatch zzof;
    boolean zzog;
    
    public AdvertisingIdClient$zza(final AdvertisingIdClient advertisingIdClient, final long zzoe) {
        this.zzod = new WeakReference<AdvertisingIdClient>(advertisingIdClient);
        this.zzoe = zzoe;
        this.zzof = new CountDownLatch(1);
        this.zzog = false;
        this.start();
    }
    
    private void disconnect() {
        final AdvertisingIdClient advertisingIdClient = this.zzod.get();
        if (advertisingIdClient != null) {
            advertisingIdClient.finish();
            this.zzog = true;
        }
    }
    
    public void cancel() {
        this.zzof.countDown();
    }
    
    @Override
    public void run() {
        try {
            if (!this.zzof.await(this.zzoe, TimeUnit.MILLISECONDS)) {
                this.disconnect();
            }
        }
        catch (InterruptedException ex) {
            this.disconnect();
        }
    }
    
    public boolean zzaK() {
        return this.zzog;
    }
}
