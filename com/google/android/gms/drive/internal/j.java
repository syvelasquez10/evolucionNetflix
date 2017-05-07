// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.internal.eb;
import com.google.android.gms.internal.ec;
import android.os.Bundle;
import android.os.IBinder;
import com.google.android.gms.internal.eg;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.dt;
import android.content.Context;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.internal.dw;

public class j extends dw<o>
{
    private final String jG;
    private DriveId rg;
    
    public j(final Context context, final dt dt, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, final String[] array) {
        super(context, connectionCallbacks, onConnectionFailedListener, array);
        this.jG = eg.b(dt.bF(), "Must call Api.ClientBuilder.setAccountName()");
    }
    
    protected o B(final IBinder binder) {
        return o.a.C(binder);
    }
    
    @Override
    protected void a(final int n, final IBinder binder, final Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(this.getClass().getClassLoader());
            this.rg = (DriveId)bundle.getParcelable("com.google.android.gms.drive.root_id");
        }
        super.a(n, binder, bundle);
    }
    
    @Override
    protected void a(final ec ec, final e e) throws RemoteException {
        final String packageName = this.getContext().getPackageName();
        eg.f(e);
        eg.f(packageName);
        eg.f(this.bO());
        ec.a(e, 4242000, packageName, this.bO(), this.jG, new Bundle());
    }
    
    @Override
    protected String am() {
        return "com.google.android.gms.drive.ApiService.START";
    }
    
    @Override
    protected String an() {
        return "com.google.android.gms.drive.internal.IDriveService";
    }
    
    public o cN() {
        return this.bQ();
    }
    
    public DriveId cO() {
        return this.rg;
    }
}
