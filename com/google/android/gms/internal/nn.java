// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.IBinder;
import com.google.android.gms.common.internal.j;
import android.os.Bundle;
import com.google.android.gms.common.internal.k;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import com.google.android.gms.common.internal.a;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.GooglePlayServicesClient;
import android.content.Context;
import com.google.android.gms.common.internal.d;

public class nn extends d<ng>
{
    private final String BZ;
    private final nk akL;
    private final ni akM;
    private boolean akN;
    private final Object mw;
    
    public nn(final Context context, final nk nk) {
        super(context, nk, nk, new String[0]);
        this.BZ = context.getPackageName();
        (this.akL = n.i(nk)).a(this);
        this.akM = new ni();
        this.mw = new Object();
        this.akN = true;
    }
    
    private void c(final nl nl, final nh nh) {
        this.akM.a(nl, nh);
    }
    
    private void d(final nl nl, final nh nh) {
        try {
            this.mW();
            this.gS().a(this.BZ, nl, nh);
        }
        catch (RemoteException ex) {
            Log.e("PlayLoggerImpl", "Couldn't send log event.  Will try caching.");
            this.c(nl, nh);
        }
        catch (IllegalStateException ex2) {
            Log.e("PlayLoggerImpl", "Service was disconnected.  Will try caching.");
            this.c(nl, nh);
        }
    }
    
    private void mW() {
        com.google.android.gms.common.internal.a.I(!this.akN);
        if (!this.akM.isEmpty()) {
            nl akB;
            ArrayList<nh> list = null;
            while (true) {
                akB = null;
                while (true) {
                    Label_0122: {
                        try {
                            list = new ArrayList<nh>();
                            for (final ni.a a : this.akM.mU()) {
                                if (a.akD == null) {
                                    break Label_0122;
                                }
                                this.gS().a(this.BZ, a.akB, pm.f(a.akD));
                            }
                            break;
                        }
                        catch (RemoteException ex) {
                            Log.e("PlayLoggerImpl", "Couldn't send cached log events to AndroidLog service.  Retaining in memory cache.");
                        }
                        return;
                    }
                    Label_0228: {
                        final ni.a a;
                        if (a.akB.equals(akB)) {
                            list.add(a.akC);
                            break Label_0228;
                        }
                        if (!list.isEmpty()) {
                            this.gS().a(this.BZ, akB, list);
                            list.clear();
                        }
                        akB = a.akB;
                        list.add(a.akC);
                        break Label_0228;
                    }
                    continue;
                }
            }
            if (!list.isEmpty()) {
                this.gS().a(this.BZ, akB, list);
            }
            this.akM.clear();
        }
    }
    
    void S(final boolean akN) {
        synchronized (this.mw) {
            final boolean akN2 = this.akN;
            this.akN = akN;
            if (akN2 && !this.akN) {
                this.mW();
            }
        }
    }
    
    @Override
    protected void a(final k k, final e e) throws RemoteException {
        k.f(e, 6111000, this.getContext().getPackageName(), new Bundle());
    }
    
    public void b(final nl nl, final nh nh) {
        synchronized (this.mw) {
            if (this.akN) {
                this.c(nl, nh);
            }
            else {
                this.d(nl, nh);
            }
        }
    }
    
    protected ng bD(final IBinder binder) {
        return ng.a.bC(binder);
    }
    
    @Override
    protected String getServiceDescriptor() {
        return "com.google.android.gms.playlog.internal.IPlayLogService";
    }
    
    @Override
    protected String getStartServiceAction() {
        return "com.google.android.gms.playlog.service.START";
    }
    
    public void start() {
        synchronized (this.mw) {
            if (this.isConnecting() || this.isConnected()) {
                return;
            }
            this.akL.R(true);
            this.connect();
        }
    }
    
    public void stop() {
        synchronized (this.mw) {
            this.akL.R(false);
            this.disconnect();
        }
    }
}
