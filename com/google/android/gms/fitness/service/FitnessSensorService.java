// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.service;

import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import java.util.List;
import android.util.Log;
import android.os.IBinder;
import android.content.Intent;
import android.app.Service;

public abstract class FitnessSensorService extends Service
{
    public static final String SERVICE_ACTION = "com.google.android.gms.fitness.service.FitnessSensorService";
    private FitnessSensorService$a UP;
    
    public final IBinder onBind(final Intent intent) {
        if ("com.google.android.gms.fitness.service.FitnessSensorService".equals(intent.getAction())) {
            if (Log.isLoggable("FitnessSensorService", 3)) {
                Log.d("FitnessSensorService", "Intent " + intent + " received by " + this.getClass().getName());
            }
            return this.UP.asBinder();
        }
        return null;
    }
    
    public void onCreate() {
        super.onCreate();
        this.UP = new FitnessSensorService$a(this, null);
    }
    
    public abstract List<DataSource> onFindDataSources(final List<DataType> p0);
    
    public abstract boolean onRegister(final FitnessSensorServiceRequest p0);
    
    public abstract boolean onUnregister(final DataSource p0);
}
