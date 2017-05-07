// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.service;

import com.google.android.gms.internal.lh;
import com.google.android.gms.fitness.result.DataSourcesResult;
import com.google.android.gms.internal.km;
import com.google.android.gms.internal.lf;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.ks;
import android.app.AppOpsManager;
import com.google.android.gms.internal.kc;
import android.os.Binder;
import com.google.android.gms.internal.lj;
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
    private a UP;
    
    public final IBinder onBind(final Intent intent) {
        if ("com.google.android.gms.fitness.service.FitnessSensorService".equals(intent.getAction())) {
            if (Log.isLoggable("FitnessSensorService", 3)) {
                Log.d("FitnessSensorService", "Intent " + intent + " received by " + this.getClass().getName());
            }
            return ((lj.a)this.UP).asBinder();
        }
        return null;
    }
    
    public void onCreate() {
        super.onCreate();
        this.UP = new a(this);
    }
    
    public abstract List<DataSource> onFindDataSources(final List<DataType> p0);
    
    public abstract boolean onRegister(final FitnessSensorServiceRequest p0);
    
    public abstract boolean onUnregister(final DataSource p0);
    
    private static class a extends lj.a
    {
        private final FitnessSensorService UQ;
        
        private a(final FitnessSensorService uq) {
            this.UQ = uq;
        }
        
        private void jK() throws SecurityException {
            final int callingUid = Binder.getCallingUid();
            if (!kc.hI()) {
                final String[] packagesForUid = this.UQ.getPackageManager().getPackagesForUid(callingUid);
                if (packagesForUid != null) {
                    for (int length = packagesForUid.length, i = 0; i < length; ++i) {
                        if (packagesForUid[i].equals("com.google.android.gms")) {
                            return;
                        }
                    }
                }
                throw new SecurityException("Unauthorized caller");
            }
            ((AppOpsManager)this.UQ.getSystemService("appops")).checkPackage(callingUid, "com.google.android.gms");
        }
        
        public void a(final FitnessSensorServiceRequest fitnessSensorServiceRequest, final ks ks) throws RemoteException {
            this.jK();
            if (this.UQ.onRegister(fitnessSensorServiceRequest)) {
                ks.k(Status.Jo);
                return;
            }
            ks.k(new Status(13));
        }
        
        public void a(final lf lf, final km km) throws RemoteException {
            this.jK();
            km.a(new DataSourcesResult(this.UQ.onFindDataSources(lf.getDataTypes()), Status.Jo));
        }
        
        public void a(final lh lh, final ks ks) throws RemoteException {
            this.jK();
            if (this.UQ.onUnregister(lh.getDataSource())) {
                ks.k(Status.Jo);
                return;
            }
            ks.k(new Status(13));
        }
    }
}
