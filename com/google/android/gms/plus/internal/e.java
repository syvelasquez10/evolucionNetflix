// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus.internal;

import com.google.android.gms.plus.model.people.PersonBuffer;
import com.google.android.gms.plus.model.moments.MomentBuffer;
import android.os.IInterface;
import java.util.Arrays;
import com.google.android.gms.common.internal.j;
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
import com.google.android.gms.plus.Moments;
import com.google.android.gms.internal.ny;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.i;
import com.google.android.gms.plus.People;
import com.google.android.gms.common.api.BaseImplementation;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.api.GoogleApiClient;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.plus.model.people.Person;
import com.google.android.gms.common.internal.d;

public class e extends com.google.android.gms.common.internal.d<com.google.android.gms.plus.internal.d>
{
    private Person ali;
    private final com.google.android.gms.plus.internal.h alj;
    
    public e(final Context context, final Looper looper, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, final com.google.android.gms.plus.internal.h alj) {
        super(context, looper, connectionCallbacks, onConnectionFailedListener, alj.ne());
        this.alj = alj;
    }
    
    public e(final Context context, final GooglePlayServicesClient.ConnectionCallbacks connectionCallbacks, final GooglePlayServicesClient.OnConnectionFailedListener onConnectionFailedListener, final com.google.android.gms.plus.internal.h h) {
        this(context, context.getMainLooper(), new com.google.android.gms.common.internal.d.c(connectionCallbacks), new com.google.android.gms.common.internal.d.g(onConnectionFailedListener), h);
    }
    
    public i a(BaseImplementation.b<People.LoadPeopleResult> e, final int n, final String s) {
        this.dK();
        e = new e((BaseImplementation.b<People.LoadPeopleResult>)e);
        try {
            return this.gS().a(e, 1, n, -1, s);
        }
        catch (RemoteException ex) {
            e.a(DataHolder.as(8), null);
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
    
    public void a(BaseImplementation.b<Moments.LoadMomentsResult> b, final int n, final String s, final Uri uri, final String s2, final String s3) {
        this.dK();
        Label_0040: {
            if (b == null) {
                break Label_0040;
            }
            b = new b((BaseImplementation.b<Moments.LoadMomentsResult>)b);
            try {
                while (true) {
                    this.gS().a(b, n, s, uri, s2, s3);
                    return;
                    b = null;
                    continue;
                }
            }
            catch (RemoteException ex) {
                b.a(DataHolder.as(8), null, null);
            }
        }
    }
    
    public void a(BaseImplementation.b<Status> a, final Moment moment) {
        this.dK();
        Label_0041: {
            if (a == null) {
                break Label_0041;
            }
            a = new a((BaseImplementation.b<Status>)a);
            try {
                while (true) {
                    this.gS().a(a, jp.a(moment));
                    return;
                    a = null;
                    continue;
                }
            }
            catch (RemoteException ex) {
                if (a == null) {
                    throw new IllegalStateException((Throwable)ex);
                }
                a.aB(new Status(8, null, null));
            }
        }
    }
    
    public void a(BaseImplementation.b<People.LoadPeopleResult> e, final Collection<String> collection) {
        this.dK();
        e = new e((BaseImplementation.b<People.LoadPeopleResult>)e);
        try {
            this.gS().a(e, new ArrayList<String>(collection));
        }
        catch (RemoteException ex) {
            e.a(DataHolder.as(8), null);
        }
    }
    
    @Override
    protected void a(final k k, final com.google.android.gms.common.internal.d.e e) throws RemoteException {
        final Bundle nm = this.alj.nm();
        nm.putStringArray("request_visible_actions", this.alj.nf());
        k.a(e, 6111000, this.alj.ni(), this.alj.nh(), this.gR(), this.alj.getAccountName(), nm);
    }
    
    protected com.google.android.gms.plus.internal.d bH(final IBinder binder) {
        return com.google.android.gms.plus.internal.d.a.bG(binder);
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
    
    public void d(final BaseImplementation.b<People.LoadPeopleResult> b, final String[] array) {
        this.a(b, Arrays.asList(array));
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
    
    public void k(final BaseImplementation.b<Moments.LoadMomentsResult> b) {
        this.a(b, 20, null, null, null, "me");
    }
    
    public void l(BaseImplementation.b<People.LoadPeopleResult> e) {
        this.dK();
        e = new e((BaseImplementation.b<People.LoadPeopleResult>)e);
        try {
            this.gS().a(e, 2, 1, -1, null);
        }
        catch (RemoteException ex) {
            e.a(DataHolder.as(8), null);
        }
    }
    
    public void m(BaseImplementation.b<Status> g) {
        this.dK();
        this.clearDefaultAccount();
        g = new g((BaseImplementation.b<Status>)g);
        try {
            this.gS().b(g);
        }
        catch (RemoteException ex) {
            g.h(8, null);
        }
    }
    
    public i r(final BaseImplementation.b<People.LoadPeopleResult> b, final String s) {
        return this.a(b, 0, s);
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
    
    final class a extends com.google.android.gms.plus.internal.a
    {
        private final BaseImplementation.b<Status> alk;
        
        public a(final BaseImplementation.b<Status> alk) {
            this.alk = alk;
        }
        
        @Override
        public void aB(final Status status) {
            e.this.a((com.google.android.gms.common.internal.d.b<?>)new d(this.alk, status));
        }
    }
    
    final class b extends a
    {
        private final BaseImplementation.b<Moments.LoadMomentsResult> alk;
        
        public b(final BaseImplementation.b<Moments.LoadMomentsResult> alk) {
            this.alk = alk;
        }
        
        @Override
        public void a(DataHolder dataHolder, final String s, final String s2) {
            PendingIntent pendingIntent;
            if (dataHolder.gz() != null) {
                pendingIntent = (PendingIntent)dataHolder.gz().getParcelable("pendingIntent");
            }
            else {
                pendingIntent = null;
            }
            final Status status = new Status(dataHolder.getStatusCode(), null, pendingIntent);
            if (!status.isSuccess() && dataHolder != null) {
                if (!dataHolder.isClosed()) {
                    dataHolder.close();
                }
                dataHolder = null;
            }
            e.this.a((com.google.android.gms.common.internal.d.b<?>)new c(this.alk, status, dataHolder, s, s2));
        }
    }
    
    final class c extends d<BaseImplementation.b<LoadMomentsResult>> implements LoadMomentsResult
    {
        private final Status CM;
        private final String Ni;
        private final String alm;
        private MomentBuffer aln;
        
        public c(final BaseImplementation.b<LoadMomentsResult> b, final Status cm, final DataHolder dataHolder, final String ni, final String alm) {
            super(b, dataHolder);
            this.CM = cm;
            this.Ni = ni;
            this.alm = alm;
        }
        
        protected void a(final BaseImplementation.b<LoadMomentsResult> b, final DataHolder dataHolder) {
            MomentBuffer aln;
            if (dataHolder != null) {
                aln = new MomentBuffer(dataHolder);
            }
            else {
                aln = null;
            }
            this.aln = aln;
            b.b(this);
        }
        
        @Override
        public MomentBuffer getMomentBuffer() {
            return this.aln;
        }
        
        @Override
        public String getNextPageToken() {
            return this.Ni;
        }
        
        @Override
        public Status getStatus() {
            return this.CM;
        }
        
        @Override
        public String getUpdated() {
            return this.alm;
        }
        
        @Override
        public void release() {
            if (this.aln != null) {
                this.aln.close();
            }
        }
    }
    
    final class d extends com.google.android.gms.common.internal.d.b<BaseImplementation.b<Status>>
    {
        private final Status CM;
        
        public d(final BaseImplementation.b<Status> b, final Status cm) {
            super(b);
            this.CM = cm;
        }
        
        @Override
        protected void gT() {
        }
        
        protected void n(final BaseImplementation.b<Status> b) {
            if (b != null) {
                b.b(this.CM);
            }
        }
    }
    
    final class e extends a
    {
        private final BaseImplementation.b<People.LoadPeopleResult> alk;
        
        public e(final BaseImplementation.b<People.LoadPeopleResult> alk) {
            this.alk = alk;
        }
        
        @Override
        public void a(DataHolder dataHolder, final String s) {
            PendingIntent pendingIntent;
            if (dataHolder.gz() != null) {
                pendingIntent = (PendingIntent)dataHolder.gz().getParcelable("pendingIntent");
            }
            else {
                pendingIntent = null;
            }
            final Status status = new Status(dataHolder.getStatusCode(), null, pendingIntent);
            if (!status.isSuccess() && dataHolder != null) {
                if (!dataHolder.isClosed()) {
                    dataHolder.close();
                }
                dataHolder = null;
            }
            com.google.android.gms.plus.internal.e.this.a((com.google.android.gms.common.internal.d.b<?>)new f(this.alk, status, dataHolder, s));
        }
    }
    
    final class f extends d<BaseImplementation.b<LoadPeopleResult>> implements LoadPeopleResult
    {
        private final Status CM;
        private final String Ni;
        private PersonBuffer alo;
        
        public f(final BaseImplementation.b<LoadPeopleResult> b, final Status cm, final DataHolder dataHolder, final String ni) {
            super(b, dataHolder);
            this.CM = cm;
            this.Ni = ni;
        }
        
        protected void a(final BaseImplementation.b<LoadPeopleResult> b, final DataHolder dataHolder) {
            PersonBuffer alo;
            if (dataHolder != null) {
                alo = new PersonBuffer(dataHolder);
            }
            else {
                alo = null;
            }
            this.alo = alo;
            b.b(this);
        }
        
        @Override
        public String getNextPageToken() {
            return this.Ni;
        }
        
        @Override
        public PersonBuffer getPersonBuffer() {
            return this.alo;
        }
        
        @Override
        public Status getStatus() {
            return this.CM;
        }
        
        @Override
        public void release() {
            if (this.alo != null) {
                this.alo.close();
            }
        }
    }
    
    final class g extends a
    {
        private final BaseImplementation.b<Status> alk;
        
        public g(final BaseImplementation.b<Status> alk) {
            this.alk = alk;
        }
        
        @Override
        public void h(final int n, final Bundle bundle) {
            PendingIntent pendingIntent;
            if (bundle != null) {
                pendingIntent = (PendingIntent)bundle.getParcelable("pendingIntent");
            }
            else {
                pendingIntent = null;
            }
            e.this.a((com.google.android.gms.common.internal.d.b<?>)new h(this.alk, new Status(n, null, pendingIntent)));
        }
    }
    
    final class h extends b<BaseImplementation.b<Status>>
    {
        private final Status CM;
        
        public h(final BaseImplementation.b<Status> b, final Status cm) {
            super(b);
            this.CM = cm;
        }
        
        @Override
        protected void gT() {
        }
        
        protected void n(final BaseImplementation.b<Status> b) {
            e.this.disconnect();
            if (b != null) {
                b.b(this.CM);
            }
        }
    }
}
