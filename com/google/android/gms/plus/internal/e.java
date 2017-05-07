// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus.internal;

import com.google.android.gms.plus.model.people.PersonBuffer;
import com.google.android.gms.plus.model.moments.MomentBuffer;
import android.os.IInterface;
import java.util.Arrays;
import com.google.android.gms.internal.fl;
import com.google.android.gms.internal.fm;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import android.app.PendingIntent;
import com.google.android.gms.internal.gg;
import com.google.android.gms.internal.ie;
import com.google.android.gms.plus.model.moments.Moment;
import com.google.android.gms.common.api.Status;
import android.net.Uri;
import com.google.android.gms.plus.Moments;
import com.google.android.gms.internal.ih;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.internal.fk;
import com.google.android.gms.plus.People;
import com.google.android.gms.common.api.a;
import com.google.android.gms.common.api.GoogleApiClient;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.plus.model.people.Person;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.internal.ff;

public class e extends ff<com.google.android.gms.plus.internal.d> implements GooglePlayServicesClient
{
    private Person Ub;
    private final com.google.android.gms.plus.internal.h Uc;
    
    public e(final Context context, final Looper looper, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, final com.google.android.gms.plus.internal.h uc) {
        super(context, looper, connectionCallbacks, onConnectionFailedListener, uc.iP());
        this.Uc = uc;
    }
    
    public e(final Context context, final ConnectionCallbacks connectionCallbacks, final OnConnectionFailedListener onConnectionFailedListener, final com.google.android.gms.plus.internal.h h) {
        this(context, context.getMainLooper(), new ff.c(connectionCallbacks), new ff.g(onConnectionFailedListener), h);
    }
    
    public fk a(com.google.android.gms.common.api.a.d<People.LoadPeopleResult> e, final int n, final String s) {
        this.bT();
        e = new e((com.google.android.gms.common.api.a.d<People.LoadPeopleResult>)e);
        try {
            return this.eM().a(e, 1, n, -1, s);
        }
        catch (RemoteException ex) {
            e.a(DataHolder.empty(8), null);
            return null;
        }
    }
    
    @Override
    protected void a(final int n, final IBinder binder, final Bundle bundle) {
        if (n == 0 && bundle != null && bundle.containsKey("loaded_person")) {
            this.Ub = ih.i(bundle.getByteArray("loaded_person"));
        }
        super.a(n, binder, bundle);
    }
    
    public void a(com.google.android.gms.common.api.a.d<Moments.LoadMomentsResult> b, final int n, final String s, final Uri uri, final String s2, final String s3) {
        this.bT();
        Label_0040: {
            if (b == null) {
                break Label_0040;
            }
            b = new b((com.google.android.gms.common.api.a.d<Moments.LoadMomentsResult>)b);
            try {
                while (true) {
                    this.eM().a(b, n, s, uri, s2, s3);
                    return;
                    b = null;
                    continue;
                }
            }
            catch (RemoteException ex) {
                b.a(DataHolder.empty(8), null, null);
            }
        }
    }
    
    public void a(com.google.android.gms.common.api.a.d<Status> a, final Moment moment) {
        this.bT();
        Label_0041: {
            if (a == null) {
                break Label_0041;
            }
            a = new a((com.google.android.gms.common.api.a.d<Status>)a);
            try {
                while (true) {
                    this.eM().a(a, gg.a(moment));
                    return;
                    a = null;
                    continue;
                }
            }
            catch (RemoteException ex) {
                if (a == null) {
                    throw new IllegalStateException((Throwable)ex);
                }
                a.Z(new Status(8, null, null));
            }
        }
    }
    
    public void a(com.google.android.gms.common.api.a.d<People.LoadPeopleResult> e, final Collection<String> collection) {
        this.bT();
        e = new e((com.google.android.gms.common.api.a.d<People.LoadPeopleResult>)e);
        try {
            this.eM().a(e, new ArrayList<String>(collection));
        }
        catch (RemoteException ex) {
            e.a(DataHolder.empty(8), null);
        }
    }
    
    @Override
    protected void a(final fm fm, final ff.e e) throws RemoteException {
        final Bundle ix = this.Uc.iX();
        ix.putStringArray("request_visible_actions", this.Uc.iQ());
        fm.a(e, 4452000, this.Uc.iT(), this.Uc.iS(), this.eL(), this.Uc.getAccountName(), ix);
    }
    
    protected com.google.android.gms.plus.internal.d aR(final IBinder binder) {
        return com.google.android.gms.plus.internal.d.a.aQ(binder);
    }
    
    @Override
    protected String bg() {
        return "com.google.android.gms.plus.service.START";
    }
    
    public boolean bg(final String s) {
        return Arrays.asList(this.eL()).contains(s);
    }
    
    @Override
    protected String bh() {
        return "com.google.android.gms.plus.internal.IPlusService";
    }
    
    public void clearDefaultAccount() {
        this.bT();
        try {
            this.Ub = null;
            this.eM().clearDefaultAccount();
        }
        catch (RemoteException ex) {
            throw new IllegalStateException((Throwable)ex);
        }
    }
    
    public void d(final com.google.android.gms.common.api.a.d<People.LoadPeopleResult> d, final String[] array) {
        this.a(d, Arrays.asList(array));
    }
    
    public String getAccountName() {
        this.bT();
        try {
            return this.eM().getAccountName();
        }
        catch (RemoteException ex) {
            throw new IllegalStateException((Throwable)ex);
        }
    }
    
    public Person getCurrentPerson() {
        this.bT();
        return this.Ub;
    }
    
    public void l(final com.google.android.gms.common.api.a.d<Moments.LoadMomentsResult> d) {
        this.a(d, 20, null, null, null, "me");
    }
    
    public void m(com.google.android.gms.common.api.a.d<People.LoadPeopleResult> e) {
        this.bT();
        e = new e((com.google.android.gms.common.api.a.d<People.LoadPeopleResult>)e);
        try {
            this.eM().a(e, 2, 1, -1, null);
        }
        catch (RemoteException ex) {
            e.a(DataHolder.empty(8), null);
        }
    }
    
    public void n(com.google.android.gms.common.api.a.d<Status> g) {
        this.bT();
        this.clearDefaultAccount();
        g = new g((com.google.android.gms.common.api.a.d<Status>)g);
        try {
            this.eM().b(g);
        }
        catch (RemoteException ex) {
            g.e(8, null);
        }
    }
    
    public fk o(final com.google.android.gms.common.api.a.d<People.LoadPeopleResult> d, final String s) {
        return this.a(d, 0, s);
    }
    
    public void removeMoment(final String s) {
        this.bT();
        try {
            this.eM().removeMoment(s);
        }
        catch (RemoteException ex) {
            throw new IllegalStateException((Throwable)ex);
        }
    }
    
    final class a extends com.google.android.gms.plus.internal.a
    {
        private final com.google.android.gms.common.api.a.d<Status> TG;
        
        public a(final com.google.android.gms.common.api.a.d<Status> tg) {
            this.TG = tg;
        }
        
        @Override
        public void Z(final Status status) {
            e.this.a((ff.b<?>)new d(this.TG, status));
        }
    }
    
    final class b extends a
    {
        private final com.google.android.gms.common.api.a.d<Moments.LoadMomentsResult> TG;
        
        public b(final com.google.android.gms.common.api.a.d<Moments.LoadMomentsResult> tg) {
            this.TG = tg;
        }
        
        @Override
        public void a(DataHolder dataHolder, final String s, final String s2) {
            PendingIntent pendingIntent;
            if (dataHolder.getMetadata() != null) {
                pendingIntent = (PendingIntent)dataHolder.getMetadata().getParcelable("pendingIntent");
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
            e.this.a((ff.b<?>)new c(this.TG, status, dataHolder, s, s2));
        }
    }
    
    final class c extends ff.d<a.d<LoadMomentsResult>> implements LoadMomentsResult
    {
        private final String EM;
        private final String Ue;
        private MomentBuffer Uf;
        private final Status wJ;
        
        public c(final a.d<LoadMomentsResult> d, final Status wj, final DataHolder dataHolder, final String em, final String ue) {
            super(d, dataHolder);
            this.wJ = wj;
            this.EM = em;
            this.Ue = ue;
        }
        
        protected void a(final a.d<LoadMomentsResult> d, final DataHolder dataHolder) {
            MomentBuffer uf;
            if (dataHolder != null) {
                uf = new MomentBuffer(dataHolder);
            }
            else {
                uf = null;
            }
            this.Uf = uf;
            d.b(this);
        }
        
        @Override
        public MomentBuffer getMomentBuffer() {
            return this.Uf;
        }
        
        @Override
        public String getNextPageToken() {
            return this.EM;
        }
        
        @Override
        public Status getStatus() {
            return this.wJ;
        }
        
        @Override
        public String getUpdated() {
            return this.Ue;
        }
        
        @Override
        public void release() {
            if (this.Uf != null) {
                this.Uf.close();
            }
        }
    }
    
    final class d extends ff.b<com.google.android.gms.common.api.a.d<Status>>
    {
        private final Status wJ;
        
        public d(final com.google.android.gms.common.api.a.d<Status> d, final Status wj) {
            super(d);
            this.wJ = wj;
        }
        
        protected void c(final com.google.android.gms.common.api.a.d<Status> d) {
            if (d != null) {
                d.b(this.wJ);
            }
        }
        
        @Override
        protected void dx() {
        }
    }
    
    final class e extends a
    {
        private final com.google.android.gms.common.api.a.d<People.LoadPeopleResult> TG;
        
        public e(final com.google.android.gms.common.api.a.d<People.LoadPeopleResult> tg) {
            this.TG = tg;
        }
        
        @Override
        public void a(DataHolder dataHolder, final String s) {
            PendingIntent pendingIntent;
            if (dataHolder.getMetadata() != null) {
                pendingIntent = (PendingIntent)dataHolder.getMetadata().getParcelable("pendingIntent");
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
            com.google.android.gms.plus.internal.e.this.a((ff.b<?>)new f(this.TG, status, dataHolder, s));
        }
    }
    
    final class f extends ff.d<a.d<LoadPeopleResult>> implements LoadPeopleResult
    {
        private final String EM;
        private PersonBuffer Ug;
        private final Status wJ;
        
        public f(final a.d<LoadPeopleResult> d, final Status wj, final DataHolder dataHolder, final String em) {
            super(d, dataHolder);
            this.wJ = wj;
            this.EM = em;
        }
        
        protected void a(final a.d<LoadPeopleResult> d, final DataHolder dataHolder) {
            PersonBuffer ug;
            if (dataHolder != null) {
                ug = new PersonBuffer(dataHolder);
            }
            else {
                ug = null;
            }
            this.Ug = ug;
            d.b(this);
        }
        
        @Override
        public String getNextPageToken() {
            return this.EM;
        }
        
        @Override
        public PersonBuffer getPersonBuffer() {
            return this.Ug;
        }
        
        @Override
        public Status getStatus() {
            return this.wJ;
        }
        
        @Override
        public void release() {
            if (this.Ug != null) {
                this.Ug.close();
            }
        }
    }
    
    final class g extends a
    {
        private final com.google.android.gms.common.api.a.d<Status> TG;
        
        public g(final com.google.android.gms.common.api.a.d<Status> tg) {
            this.TG = tg;
        }
        
        @Override
        public void e(final int n, final Bundle bundle) {
            PendingIntent pendingIntent;
            if (bundle != null) {
                pendingIntent = (PendingIntent)bundle.getParcelable("pendingIntent");
            }
            else {
                pendingIntent = null;
            }
            e.this.a((ff.b<?>)new h(this.TG, new Status(n, null, pendingIntent)));
        }
    }
    
    final class h extends ff.b<a.d<Status>>
    {
        private final Status wJ;
        
        public h(final a.d<Status> d, final Status wj) {
            super(d);
            this.wJ = wj;
        }
        
        protected void c(final a.d<Status> d) {
            e.this.disconnect();
            if (d != null) {
                d.b(this.wJ);
            }
        }
        
        @Override
        protected void dx() {
        }
    }
}
