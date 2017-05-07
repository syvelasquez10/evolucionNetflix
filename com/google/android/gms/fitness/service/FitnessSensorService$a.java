// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.service;

import com.google.android.gms.internal.lh;
import com.google.android.gms.fitness.result.DataSourcesResult;
import com.google.android.gms.internal.km;
import com.google.android.gms.internal.lf;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.ks;
import android.app.AppOpsManager;
import com.google.android.gms.internal.kc;
import android.os.Binder;
import com.google.android.gms.internal.lj$a;

class FitnessSensorService$a extends lj$a
{
    private final FitnessSensorService UQ;
    
    private FitnessSensorService$a(final FitnessSensorService uq) {
        this.UQ = uq;
    }
    
    private void jK() {
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
    
    public void a(final FitnessSensorServiceRequest fitnessSensorServiceRequest, final ks ks) {
        this.jK();
        if (this.UQ.onRegister(fitnessSensorServiceRequest)) {
            ks.k(Status.Jo);
            return;
        }
        ks.k(new Status(13));
    }
    
    public void a(final lf lf, final km km) {
        this.jK();
        km.a(new DataSourcesResult(this.UQ.onFindDataSources(lf.getDataTypes()), Status.Jo));
    }
    
    public void a(final lh lh, final ks ks) {
        this.jK();
        if (this.UQ.onUnregister(lh.getDataSource())) {
            ks.k(Status.Jo);
            return;
        }
        ks.k(new Status(13));
    }
}
