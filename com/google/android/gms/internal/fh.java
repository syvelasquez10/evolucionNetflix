// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.IInterface;
import com.google.android.gms.common.internal.j;
import android.os.Bundle;
import com.google.android.gms.common.internal.d$e;
import com.google.android.gms.common.internal.k;
import android.os.IBinder;
import com.google.android.gms.common.GooglePlayServicesClient$OnConnectionFailedListener;
import com.google.android.gms.common.GooglePlayServicesClient$ConnectionCallbacks;
import android.content.Context;
import com.google.android.gms.common.internal.d;

@ez
public class fh extends d<fm>
{
    final int pP;
    
    public fh(final Context context, final GooglePlayServicesClient$ConnectionCallbacks googlePlayServicesClient$ConnectionCallbacks, final GooglePlayServicesClient$OnConnectionFailedListener googlePlayServicesClient$OnConnectionFailedListener, final int pp) {
        super(context, googlePlayServicesClient$ConnectionCallbacks, googlePlayServicesClient$OnConnectionFailedListener, new String[0]);
        this.pP = pp;
    }
    
    protected fm C(final IBinder binder) {
        return fm$a.D(binder);
    }
    
    @Override
    protected void a(final k k, final d$e d$e) {
        k.g(d$e, this.pP, this.getContext().getPackageName(), new Bundle());
    }
    
    public fm cF() {
        return super.gS();
    }
    
    @Override
    protected String getServiceDescriptor() {
        return "com.google.android.gms.ads.internal.request.IAdRequestService";
    }
    
    @Override
    protected String getStartServiceAction() {
        return "com.google.android.gms.ads.service.START";
    }
}
