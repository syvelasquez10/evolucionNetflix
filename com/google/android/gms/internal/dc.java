// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.appstate.AppStateBuffer;
import com.google.android.gms.common.data.DataHolder;
import android.os.IInterface;
import android.os.IBinder;
import com.google.android.gms.common.api.Status;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.appstate.AppStateManager;
import com.google.android.gms.common.api.a;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.GooglePlayServicesClient;
import android.content.Context;

public final class dc extends dw<de>
{
    private final String jG;
    
    public dc(final Context context, final ConnectionCallbacks connectionCallbacks, final OnConnectionFailedListener onConnectionFailedListener, final String s, final String[] array) {
        this(context, new dw.c(connectionCallbacks), new dw.g(onConnectionFailedListener), s, array);
    }
    
    public dc(final Context context, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, final String s, final String[] array) {
        super(context, connectionCallbacks, onConnectionFailedListener, array);
        this.jG = eg.f(s);
    }
    
    public void a(final com.google.android.gms.common.api.a.c<AppStateManager.StateListResult> c) {
        try {
            this.bQ().a(new c(c));
        }
        catch (RemoteException ex) {
            Log.w("AppStateClient", "service died");
        }
    }
    
    public void a(final com.google.android.gms.common.api.a.c<AppStateManager.StateDeletedResult> c, final int n) {
        try {
            this.bQ().b(new a(c), n);
        }
        catch (RemoteException ex) {
            Log.w("AppStateClient", "service died");
        }
    }
    
    public void a(final com.google.android.gms.common.api.a.c<AppStateManager.StateResult> c, final int n, final String s, final byte[] array) {
        try {
            this.bQ().a(new e(c), n, s, array);
        }
        catch (RemoteException ex) {
            Log.w("AppStateClient", "service died");
        }
    }
    
    public void a(final com.google.android.gms.common.api.a.c<AppStateManager.StateResult> c, final int n, final byte[] array) {
        Label_0022: {
            if (c != null) {
                break Label_0022;
            }
            dd dd = null;
            try {
                while (true) {
                    this.bQ().a(dd, n, array);
                    return;
                    dd = new e(c);
                    continue;
                }
            }
            catch (RemoteException ex) {
                Log.w("AppStateClient", "service died");
            }
        }
    }
    
    @Override
    protected void a(final ec ec, final dw.e e) throws RemoteException {
        ec.a(e, 4242000, this.getContext().getPackageName(), this.jG, this.bO());
    }
    
    @Override
    protected void a(final String... array) {
        int i = 0;
        boolean b = false;
        while (i < array.length) {
            if (array[i].equals("https://www.googleapis.com/auth/appstate")) {
                b = true;
            }
            ++i;
        }
        eg.a(b, (Object)String.format("App State APIs requires %s to function.", "https://www.googleapis.com/auth/appstate"));
    }
    
    @Override
    protected String am() {
        return "com.google.android.gms.appstate.service.START";
    }
    
    @Override
    protected String an() {
        return "com.google.android.gms.appstate.internal.IAppStateService";
    }
    
    public void b(final com.google.android.gms.common.api.a.c<Status> c) {
        try {
            this.bQ().b(new g(c));
        }
        catch (RemoteException ex) {
            Log.w("AppStateClient", "service died");
        }
    }
    
    public void b(final com.google.android.gms.common.api.a.c<AppStateManager.StateResult> c, final int n) {
        try {
            this.bQ().a(new e(c), n);
        }
        catch (RemoteException ex) {
            Log.w("AppStateClient", "service died");
        }
    }
    
    public int getMaxNumKeys() {
        try {
            return this.bQ().getMaxNumKeys();
        }
        catch (RemoteException ex) {
            Log.w("AppStateClient", "service died");
            return 2;
        }
    }
    
    public int getMaxStateSize() {
        try {
            return this.bQ().getMaxStateSize();
        }
        catch (RemoteException ex) {
            Log.w("AppStateClient", "service died");
            return 2;
        }
    }
    
    protected de r(final IBinder binder) {
        return de.a.t(binder);
    }
    
    final class a extends db
    {
        private final com.google.android.gms.common.api.a.c<AppStateManager.StateDeletedResult> jW;
        
        public a(final com.google.android.gms.common.api.a.c<AppStateManager.StateDeletedResult> c) {
            this.jW = (com.google.android.gms.common.api.a.c<AppStateManager.StateDeletedResult>)eg.b((com.google.android.gms.common.api.a.c)c, "Result holder must not be null");
        }
        
        @Override
        public void onStateDeleted(final int n, final int n2) {
            dc.this.a((dw.b<?>)new b(this.jW, new Status(n), n2));
        }
    }
    
    final class b extends dw.b<a.c<StateDeletedResult>> implements StateDeletedResult
    {
        private final Status jY;
        private final int jZ;
        
        public b(final a.c<StateDeletedResult> c, final Status jy, final int jz) {
            super(c);
            this.jY = jy;
            this.jZ = jz;
        }
        
        @Override
        protected void aL() {
        }
        
        public void c(final a.c<StateDeletedResult> c) {
            c.a(this);
        }
        
        @Override
        public int getStateKey() {
            return this.jZ;
        }
        
        @Override
        public Status getStatus() {
            return this.jY;
        }
    }
    
    final class c extends db
    {
        private final com.google.android.gms.common.api.a.c<AppStateManager.StateListResult> jW;
        
        public c(final com.google.android.gms.common.api.a.c<AppStateManager.StateListResult> c) {
            this.jW = (com.google.android.gms.common.api.a.c<AppStateManager.StateListResult>)eg.b((com.google.android.gms.common.api.a.c)c, "Result holder must not be null");
        }
        
        @Override
        public void a(final DataHolder dataHolder) {
            dc.this.a((dw.b<?>)new d(this.jW, new Status(dataHolder.getStatusCode()), dataHolder));
        }
    }
    
    final class d extends dw.d<a.c<StateListResult>> implements StateListResult
    {
        private final Status jY;
        private final AppStateBuffer ka;
        
        public d(final a.c<StateListResult> c, final Status jy, final DataHolder dataHolder) {
            super(c, dataHolder);
            this.jY = jy;
            this.ka = new AppStateBuffer(dataHolder);
        }
        
        public void a(final a.c<StateListResult> c, final DataHolder dataHolder) {
            c.a(this);
        }
        
        @Override
        public AppStateBuffer getStateBuffer() {
            return this.ka;
        }
        
        @Override
        public Status getStatus() {
            return this.jY;
        }
    }
    
    final class e extends db
    {
        private final com.google.android.gms.common.api.a.c<AppStateManager.StateResult> jW;
        
        public e(final com.google.android.gms.common.api.a.c<AppStateManager.StateResult> c) {
            this.jW = (com.google.android.gms.common.api.a.c<AppStateManager.StateResult>)eg.b((com.google.android.gms.common.api.a.c)c, "Result holder must not be null");
        }
        
        @Override
        public void a(final int n, final DataHolder dataHolder) {
            dc.this.a((dw.b<?>)new f(this.jW, n, dataHolder));
        }
    }
    
    final class f extends dw.d<a.c<StateResult>> implements StateConflictResult, StateLoadedResult, StateResult
    {
        private final Status jY;
        private final int jZ;
        private final AppStateBuffer ka;
        
        public f(final a.c<StateResult> c, final int jz, final DataHolder dataHolder) {
            super(c, dataHolder);
            this.jZ = jz;
            this.jY = new Status(dataHolder.getStatusCode());
            this.ka = new AppStateBuffer(dataHolder);
        }
        
        private boolean aM() {
            return this.jY.getStatusCode() == 2000;
        }
        
        public void a(final a.c<StateResult> c, final DataHolder dataHolder) {
            c.a(this);
        }
        
        @Override
        public StateConflictResult getConflictResult() {
            if (this.aM()) {
                return this;
            }
            return null;
        }
        
        @Override
        public StateLoadedResult getLoadedResult() {
            f f = this;
            if (this.aM()) {
                f = null;
            }
            return f;
        }
        
        @Override
        public byte[] getLocalData() {
            if (this.ka.getCount() == 0) {
                return null;
            }
            return this.ka.get(0).getLocalData();
        }
        
        @Override
        public String getResolvedVersion() {
            if (this.ka.getCount() == 0) {
                return null;
            }
            return this.ka.get(0).getConflictVersion();
        }
        
        @Override
        public byte[] getServerData() {
            if (this.ka.getCount() == 0) {
                return null;
            }
            return this.ka.get(0).getConflictData();
        }
        
        @Override
        public int getStateKey() {
            return this.jZ;
        }
        
        @Override
        public Status getStatus() {
            return this.jY;
        }
    }
    
    final class g extends db
    {
        com.google.android.gms.common.api.a.c<Status> jW;
        
        public g(final com.google.android.gms.common.api.a.c<Status> c) {
            this.jW = (com.google.android.gms.common.api.a.c<Status>)eg.b((com.google.android.gms.common.api.a.c)c, "Holder must not be null");
        }
        
        @Override
        public void onSignOutComplete() {
            dc.this.a((dw.b<?>)new h(this.jW, new Status(0)));
        }
    }
    
    final class h extends dw.b<a.c<Status>>
    {
        private final Status jY;
        
        public h(final a.c<Status> c, final Status jy) {
            super(c);
            this.jY = jy;
        }
        
        @Override
        protected void aL() {
        }
        
        public void c(final a.c<Status> c) {
            c.a(this.jY);
        }
    }
}
