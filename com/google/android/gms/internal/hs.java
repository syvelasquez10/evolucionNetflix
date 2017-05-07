// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.plus.model.people.PersonBuffer;
import com.google.android.gms.plus.model.moments.MomentBuffer;
import android.app.PendingIntent;
import com.google.android.gms.plus.model.moments.Moment;
import android.os.IInterface;
import com.google.android.gms.common.api.Status;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import android.net.Uri;
import com.google.android.gms.plus.Moments;
import android.os.RemoteException;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.plus.People;
import com.google.android.gms.common.api.a;
import android.os.Bundle;
import android.os.IBinder;
import com.google.android.gms.common.api.GoogleApiClient;
import android.content.Context;
import com.google.android.gms.plus.model.people.Person;
import com.google.android.gms.common.GooglePlayServicesClient;

public class hs extends dw<hr> implements GooglePlayServicesClient
{
    private Person DK;
    private hu DL;
    
    public hs(final Context context, final ConnectionCallbacks connectionCallbacks, final OnConnectionFailedListener onConnectionFailedListener, final hu hu) {
        this(context, new dw.c(connectionCallbacks), new g(onConnectionFailedListener), hu);
    }
    
    public hs(final Context context, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, final hu dl) {
        super(context, connectionCallbacks, onConnectionFailedListener, dl.eR());
        this.DL = dl;
    }
    
    @Override
    protected void a(final int n, final IBinder binder, final Bundle bundle) {
        if (n == 0 && bundle != null && bundle.containsKey("loaded_person")) {
            this.DK = ig.g(bundle.getByteArray("loaded_person"));
        }
        super.a(n, binder, bundle);
    }
    
    public void a(com.google.android.gms.common.api.a.c<People.LoadPeopleResult> c, final int n, final String s) {
        this.bP();
        c = new c((com.google.android.gms.common.api.a.c<People.LoadPeopleResult>)c);
        try {
            this.bQ().a(c, 1, n, -1, s);
        }
        catch (RemoteException ex) {
            c.a(DataHolder.empty(8), null);
        }
    }
    
    public void a(com.google.android.gms.common.api.a.c<Moments.LoadMomentsResult> a, final int n, final String s, final Uri uri, final String s2, final String s3) {
        this.bP();
        Label_0040: {
            if (a == null) {
                break Label_0040;
            }
            a = new a((com.google.android.gms.common.api.a.c<Moments.LoadMomentsResult>)a);
            try {
                while (true) {
                    this.bQ().a(a, n, s, uri, s2, s3);
                    return;
                    a = null;
                    continue;
                }
            }
            catch (RemoteException ex) {
                a.a(DataHolder.empty(8), null, null);
            }
        }
    }
    
    public void a(com.google.android.gms.common.api.a.c<People.LoadPeopleResult> c, final Collection<String> collection) {
        this.bP();
        c = new c((com.google.android.gms.common.api.a.c<People.LoadPeopleResult>)c);
        try {
            this.bQ().a(c, new ArrayList<String>(collection));
        }
        catch (RemoteException ex) {
            c.a(DataHolder.empty(8), null);
        }
    }
    
    public void a(final com.google.android.gms.common.api.a.c<People.LoadPeopleResult> c, final String[] array) {
        this.a(c, Arrays.asList(array));
    }
    
    @Override
    protected void a(final ec ec, final dw.e e) throws RemoteException {
        final Bundle bundle = new Bundle();
        bundle.putStringArray("request_visible_actions", this.DL.eS());
        ec.a(e, 4242000, this.DL.eV(), this.DL.eU(), this.bO(), this.DL.getAccountName(), bundle);
    }
    
    @Override
    protected String am() {
        return "com.google.android.gms.plus.service.START";
    }
    
    @Override
    protected String an() {
        return "com.google.android.gms.plus.internal.IPlusService";
    }
    
    protected hr ay(final IBinder binder) {
        return hr.a.ax(binder);
    }
    
    public boolean az(final String s) {
        return Arrays.asList(this.bO()).contains(s);
    }
    
    public void clearDefaultAccount() {
        this.bP();
        try {
            this.DK = null;
            this.bQ().clearDefaultAccount();
        }
        catch (RemoteException ex) {
            throw new IllegalStateException((Throwable)ex);
        }
    }
    
    public String getAccountName() {
        this.bP();
        try {
            return this.bQ().getAccountName();
        }
        catch (RemoteException ex) {
            throw new IllegalStateException((Throwable)ex);
        }
    }
    
    public Person getCurrentPerson() {
        this.bP();
        return this.DK;
    }
    
    public void i(final com.google.android.gms.common.api.a.c<People.LoadPeopleResult> c, final String s) {
        this.a(c, 0, s);
    }
    
    public void j(final com.google.android.gms.common.api.a.c<Moments.LoadMomentsResult> c) {
        this.a(c, 20, null, null, null, "me");
    }
    
    public void k(com.google.android.gms.common.api.a.c<People.LoadPeopleResult> c) {
        this.bP();
        c = new c((com.google.android.gms.common.api.a.c<People.LoadPeopleResult>)c);
        try {
            this.bQ().a(c, 2, 1, -1, null);
        }
        catch (RemoteException ex) {
            c.a(DataHolder.empty(8), null);
        }
    }
    
    public void l(com.google.android.gms.common.api.a.c<Status> e) {
        this.bP();
        this.clearDefaultAccount();
        e = new e((com.google.android.gms.common.api.a.c<Status>)e);
        try {
            this.bQ().b(e);
        }
        catch (RemoteException ex) {
            e.b(8, null);
        }
    }
    
    public void removeMoment(final String s) {
        this.bP();
        try {
            this.bQ().removeMoment(s);
        }
        catch (RemoteException ex) {
            throw new IllegalStateException((Throwable)ex);
        }
    }
    
    public void writeMoment(final Moment moment) {
        this.bP();
        try {
            this.bQ().a(ey.a(moment));
        }
        catch (RemoteException ex) {
            throw new IllegalStateException((Throwable)ex);
        }
    }
    
    final class a extends hn
    {
        private final com.google.android.gms.common.api.a.c<Moments.LoadMomentsResult> Dp;
        
        public a(final com.google.android.gms.common.api.a.c<Moments.LoadMomentsResult> dp) {
            this.Dp = dp;
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
            hs.this.a((dw.b<?>)new b(this.Dp, status, dataHolder, s, s2));
        }
    }
    
    final class b extends dw.d<a.c<LoadMomentsResult>> implements LoadMomentsResult
    {
        private final String DN;
        private MomentBuffer DO;
        private final Status jY;
        private final String qT;
        
        public b(final a.c<LoadMomentsResult> c, final Status jy, final DataHolder dataHolder, final String qt, final String dn) {
            super(c, dataHolder);
            this.jY = jy;
            this.qT = qt;
            this.DN = dn;
        }
        
        protected void a(final a.c<LoadMomentsResult> c, final DataHolder dataHolder) {
            MomentBuffer do1;
            if (dataHolder != null) {
                do1 = new MomentBuffer(dataHolder);
            }
            else {
                do1 = null;
            }
            this.DO = do1;
            c.a(this);
        }
        
        @Override
        public MomentBuffer getMomentBuffer() {
            return this.DO;
        }
        
        @Override
        public String getNextPageToken() {
            return this.qT;
        }
        
        @Override
        public Status getStatus() {
            return this.jY;
        }
        
        @Override
        public String getUpdated() {
            return this.DN;
        }
        
        @Override
        public void release() {
            if (this.DO != null) {
                this.DO.close();
            }
        }
    }
    
    final class c extends hn
    {
        private final com.google.android.gms.common.api.a.c<People.LoadPeopleResult> Dp;
        
        public c(final com.google.android.gms.common.api.a.c<People.LoadPeopleResult> dp) {
            this.Dp = dp;
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
            hs.this.a((dw.b<?>)new d(this.Dp, status, dataHolder, s));
        }
    }
    
    final class d extends dw.d<a.c<LoadPeopleResult>> implements LoadPeopleResult
    {
        private PersonBuffer DP;
        private final Status jY;
        private final String qT;
        
        public d(final a.c<LoadPeopleResult> c, final Status jy, final DataHolder dataHolder, final String qt) {
            super(c, dataHolder);
            this.jY = jy;
            this.qT = qt;
        }
        
        protected void a(final a.c<LoadPeopleResult> c, final DataHolder dataHolder) {
            PersonBuffer dp;
            if (dataHolder != null) {
                dp = new PersonBuffer(dataHolder);
            }
            else {
                dp = null;
            }
            this.DP = dp;
            c.a(this);
        }
        
        @Override
        public String getNextPageToken() {
            return this.qT;
        }
        
        @Override
        public PersonBuffer getPersonBuffer() {
            return this.DP;
        }
        
        @Override
        public Status getStatus() {
            return this.jY;
        }
        
        @Override
        public void release() {
            if (this.DP != null) {
                this.DP.close();
            }
        }
    }
    
    final class e extends hn
    {
        private final com.google.android.gms.common.api.a.c<Status> Dp;
        
        public e(final com.google.android.gms.common.api.a.c<Status> dp) {
            this.Dp = dp;
        }
        
        @Override
        public void b(final int n, final Bundle bundle) {
            PendingIntent pendingIntent;
            if (bundle != null) {
                pendingIntent = (PendingIntent)bundle.getParcelable("pendingIntent");
            }
            else {
                pendingIntent = null;
            }
            hs.this.a((dw.b<?>)new f(this.Dp, new Status(n, null, pendingIntent)));
        }
    }
    
    final class f extends dw.b<a.c<Status>>
    {
        private final Status jY;
        
        public f(final a.c<Status> c, final Status jy) {
            super(c);
            this.jY = jy;
        }
        
        @Override
        protected void aL() {
        }
        
        protected void c(final a.c<Status> c) {
            hs.this.disconnect();
            if (c != null) {
                c.a(this.jY);
            }
        }
    }
}
