// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.request;

import java.util.HashMap;
import java.util.Map;
import android.os.RemoteException;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.common.internal.n;

public class a extends k.a
{
    private final BleScanCallback TU;
    
    private a(final BleScanCallback bleScanCallback) {
        this.TU = n.i(bleScanCallback);
    }
    
    public void onDeviceFound(final BleDevice bleDevice) throws RemoteException {
        this.TU.onDeviceFound(bleDevice);
    }
    
    public void onScanStopped() throws RemoteException {
        this.TU.onScanStopped();
    }
    
    public static class a
    {
        private static final a TV;
        private final Map<BleScanCallback, com.google.android.gms.fitness.request.a> TW;
        
        static {
            TV = new a();
        }
        
        private a() {
            this.TW = new HashMap<BleScanCallback, com.google.android.gms.fitness.request.a>();
        }
        
        public static a iV() {
            return a.TV;
        }
        
        public com.google.android.gms.fitness.request.a a(final BleScanCallback bleScanCallback) {
            synchronized (this.TW) {
                com.google.android.gms.fitness.request.a a;
                if ((a = this.TW.get(bleScanCallback)) == null) {
                    a = new com.google.android.gms.fitness.request.a(bleScanCallback, null);
                    this.TW.put(bleScanCallback, a);
                }
                return a;
            }
        }
        
        public com.google.android.gms.fitness.request.a b(final BleScanCallback bleScanCallback) {
            synchronized (this.TW) {
                final com.google.android.gms.fitness.request.a a = this.TW.get(bleScanCallback);
                if (a != null) {
                    return a;
                }
                return new com.google.android.gms.fitness.request.a(bleScanCallback, null);
            }
        }
    }
}
