// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus.internal;

import android.os.IInterface;
import java.util.Arrays;
import com.google.android.gms.common.internal.j;
import com.google.android.gms.common.internal.d$e;
import com.google.android.gms.common.internal.k;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import android.app.PendingIntent;
import com.google.android.gms.internal.jp;
import com.google.android.gms.internal.nv;
import com.google.android.gms.plus.model.moments.Moment;
import com.google.android.gms.common.api.Status;
import android.net.Uri;
import com.google.android.gms.plus.Moments$LoadMomentsResult;
import com.google.android.gms.internal.ny;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.i;
import com.google.android.gms.plus.People$LoadPeopleResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.common.internal.d$g;
import com.google.android.gms.common.internal.d$c;
import com.google.android.gms.common.GooglePlayServicesClient$OnConnectionFailedListener;
import com.google.android.gms.common.GooglePlayServicesClient$ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.plus.model.people.Person;
import com.google.android.gms.common.internal.d;

public class e extends d<com.google.android.gms.plus.internal.d>
{
    private Person ali;
    private final h alj;
    
    public e(final Context context, final Looper looper, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener, final h alj) {
        super(context, looper, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener, alj.ne());
        this.alj = alj;
    }
    
    public e(final Context context, final GooglePlayServicesClient$ConnectionCallbacks googlePlayServicesClient$ConnectionCallbacks, final GooglePlayServicesClient$OnConnectionFailedListener googlePlayServicesClient$OnConnectionFailedListener, final h h) {
        this(context, context.getMainLooper(), new d$c(googlePlayServicesClient$ConnectionCallbacks), new d$g(googlePlayServicesClient$OnConnectionFailedListener), h);
    }
    
    public i a(BaseImplementation$b<People$LoadPeopleResult> e$e, final int n, final String s) {
        this.dK();
        e$e = new e$e((BaseImplementation$b<People$LoadPeopleResult>)e$e);
        try {
            return this.gS().a(e$e, 1, n, -1, s);
        }
        catch (RemoteException ex) {
            e$e.a(DataHolder.as(8), null);
            return null;
        }
    }
    
    @Override
    protected void a(final int n, final IBinder binder, final Bundle bundle) {
        if (n == 0 && bundle != null && bundle.containsKey("loaded_person")) {
            this.ali = ny.i(bundle.getByteArray("loaded_person"));
        }
        super.a(n, binder, bundle);
    }
    
    public void a(BaseImplementation$b<Moments$LoadMomentsResult> e$b, final int n, final String s, final Uri uri, final String s2, final String s3) {
        this.dK();
        Label_0040: {
            if (e$b == null) {
                break Label_0040;
            }
            e$b = new com.google.android.gms.plus.internal.e$b((BaseImplementation$b<Moments$LoadMomentsResult>)e$b);
            try {
                while (true) {
                    this.gS().a(e$b, n, s, uri, s2, s3);
                    return;
                    e$b = null;
                    continue;
                }
            }
            catch (RemoteException ex) {
                e$b.a(DataHolder.as(8), null, null);
            }
        }
    }
    
    public void a(BaseImplementation$b<Status> e$a, final Moment moment) {
        this.dK();
        Label_0041: {
            if (e$a == null) {
                break Label_0041;
            }
            e$a = new e$a((BaseImplementation$b<Status>)e$a);
            try {
                while (true) {
                    this.gS().a(e$a, jp.a(moment));
                    return;
                    e$a = null;
                    continue;
                }
            }
            catch (RemoteException ex) {
                if (e$a == null) {
                    throw new IllegalStateException((Throwable)ex);
                }
                e$a.aB(new Status(8, null, null));
            }
        }
    }
    
    public void a(BaseImplementation$b<People$LoadPeopleResult> e$e, final Collection<String> collection) {
        this.dK();
        e$e = new e$e((BaseImplementation$b<People$LoadPeopleResult>)e$e);
        try {
            this.gS().a(e$e, new ArrayList<String>(collection));
        }
        catch (RemoteException ex) {
            e$e.a(DataHolder.as(8), null);
        }
    }
    
    @Override
    protected void a(final k k, final d$e d$e) {
        final Bundle nm = this.alj.nm();
        nm.putStringArray("request_visible_actions", this.alj.nf());
        k.a(d$e, 6111000, this.alj.ni(), this.alj.nh(), this.gR(), this.alj.getAccountName(), nm);
    }
    
    protected com.google.android.gms.plus.internal.d bH(final IBinder binder) {
        return d$a.bG(binder);
    }
    
    public boolean cd(final String s) {
        return Arrays.asList(this.gR()).contains(s);
    }
    
    public void clearDefaultAccount() {
        this.dK();
        try {
            this.ali = null;
            this.gS().clearDefaultAccount();
        }
        catch (RemoteException ex) {
            throw new IllegalStateException((Throwable)ex);
        }
    }
    
    public void d(final BaseImplementation$b<People$LoadPeopleResult> baseImplementation$b, final String[] array) {
        this.a(baseImplementation$b, Arrays.asList(array));
    }
    
    public String getAccountName() {
        this.dK();
        try {
            return this.gS().getAccountName();
        }
        catch (RemoteException ex) {
            throw new IllegalStateException((Throwable)ex);
        }
    }
    
    public Person getCurrentPerson() {
        this.dK();
        return this.ali;
    }
    
    @Override
    protected String getServiceDescriptor() {
        return "com.google.android.gms.plus.internal.IPlusService";
    }
    
    @Override
    protected String getStartServiceAction() {
        return "com.google.android.gms.plus.service.START";
    }
    
    public void k(final BaseImplementation$b<Moments$LoadMomentsResult> baseImplementation$b) {
        this.a(baseImplementation$b, 20, null, null, null, "me");
    }
    
    public void l(BaseImplementation$b<People$LoadPeopleResult> e$e) {
        this.dK();
        e$e = new e$e((BaseImplementation$b<People$LoadPeopleResult>)e$e);
        try {
            this.gS().a(e$e, 2, 1, -1, null);
        }
        catch (RemoteException ex) {
            e$e.a(DataHolder.as(8), null);
        }
    }
    
    public void m(BaseImplementation$b<Status> e$g) {
        this.dK();
        this.clearDefaultAccount();
        e$g = new e$g((BaseImplementation$b<Status>)e$g);
        try {
            this.gS().b(e$g);
        }
        catch (RemoteException ex) {
            e$g.h(8, null);
        }
    }
    
    public i r(final BaseImplementation$b<People$LoadPeopleResult> baseImplementation$b, final String s) {
        return this.a(baseImplementation$b, 0, s);
    }
    
    public void removeMoment(final String s) {
        this.dK();
        try {
            this.gS().removeMoment(s);
        }
        catch (RemoteException ex) {
            throw new IllegalStateException((Throwable)ex);
        }
    }
}
