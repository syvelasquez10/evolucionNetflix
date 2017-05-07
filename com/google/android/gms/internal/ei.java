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
import android.os.Looper;
import android.content.Context;

public final class ei extends ff<ek>
{
    private final String wG;
    
    public ei(final Context context, final Looper looper, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, final String s, final String[] array) {
        super(context, looper, connectionCallbacks, onConnectionFailedListener, array);
        this.wG = fq.f(s);
    }
    
    public void a(final com.google.android.gms.common.api.a.d<AppStateManager.StateListResult> d) {
        try {
            this.eM().a(new c(d));
        }
        catch (RemoteException ex) {
            Log.w("AppStateClient", "service died");
        }
    }
    
    public void a(final com.google.android.gms.common.api.a.d<AppStateManager.StateDeletedResult> d, final int n) {
        try {
            this.eM().b(new a(d), n);
        }
        catch (RemoteException ex) {
            Log.w("AppStateClient", "service died");
        }
    }
    
    public void a(final com.google.android.gms.common.api.a.d<AppStateManager.StateResult> d, final int n, final String s, final byte[] array) {
        try {
            this.eM().a(new e(d), n, s, array);
        }
        catch (RemoteException ex) {
            Log.w("AppStateClient", "service died");
        }
    }
    
    public void a(final com.google.android.gms.common.api.a.d<AppStateManager.StateResult> d, final int n, final byte[] array) {
        Label_0022: {
            if (d != null) {
                break Label_0022;
            }
            ej ej = null;
            try {
                while (true) {
                    this.eM().a(ej, n, array);
                    return;
                    ej = new e(d);
                    continue;
                }
            }
            catch (RemoteException ex) {
                Log.w("AppStateClient", "service died");
            }
        }
    }
    
    @Override
    protected void a(final fm fm, final ff.e e) throws RemoteException {
        fm.a(e, 4452000, this.getContext().getPackageName(), this.wG, this.eL());
    }
    
    public void b(final com.google.android.gms.common.api.a.d<Status> d) {
        try {
            this.eM().b(new g(d));
        }
        catch (RemoteException ex) {
            Log.w("AppStateClient", "service died");
        }
    }
    
    public void b(final com.google.android.gms.common.api.a.d<AppStateManager.StateResult> d, final int n) {
        try {
            this.eM().a(new e(d), n);
        }
        catch (RemoteException ex) {
            Log.w("AppStateClient", "service died");
        }
    }
    
    @Override
    protected void b(final String... array) {
        int i = 0;
        boolean b = false;
        while (i < array.length) {
            if (array[i].equals("https://www.googleapis.com/auth/appstate")) {
                b = true;
            }
            ++i;
        }
        fq.a(b, (Object)String.format("App State APIs requires %s to function.", "https://www.googleapis.com/auth/appstate"));
    }
    
    @Override
    protected String bg() {
        return "com.google.android.gms.appstate.service.START";
    }
    
    @Override
    protected String bh() {
        return "com.google.android.gms.appstate.internal.IAppStateService";
    }
    
    public int dv() {
        try {
            return this.eM().dv();
        }
        catch (RemoteException ex) {
            Log.w("AppStateClient", "service died");
            return 2;
        }
    }
    
    public int dw() {
        try {
            return this.eM().dw();
        }
        catch (RemoteException ex) {
            Log.w("AppStateClient", "service died");
            return 2;
        }
    }
    
    protected ek u(final IBinder binder) {
        return ek.a.w(binder);
    }
    
    final class a extends eh
    {
        private final com.google.android.gms.common.api.a.d<AppStateManager.StateDeletedResult> wH;
        
        public a(final com.google.android.gms.common.api.a.d<AppStateManager.StateDeletedResult> d) {
            this.wH = (com.google.android.gms.common.api.a.d<AppStateManager.StateDeletedResult>)fq.b((com.google.android.gms.common.api.a.d)d, "Result holder must not be null");
        }
        
        @Override
        public void b(final int n, final int n2) {
            ei.this.a((ff.b<?>)new b(this.wH, new Status(n), n2));
        }
    }
    
    final class b extends ff.b<a.d<StateDeletedResult>> implements StateDeletedResult
    {
        private final Status wJ;
        private final int wK;
        
        public b(final a.d<StateDeletedResult> d, final Status wj, final int wk) {
            super(d);
            this.wJ = wj;
            this.wK = wk;
        }
        
        public void c(final a.d<StateDeletedResult> d) {
            d.b(this);
        }
        
        @Override
        protected void dx() {
        }
        
        @Override
        public int getStateKey() {
            return this.wK;
        }
        
        @Override
        public Status getStatus() {
            return this.wJ;
        }
    }
    
    final class c extends eh
    {
        private final com.google.android.gms.common.api.a.d<AppStateManager.StateListResult> wH;
        
        public c(final com.google.android.gms.common.api.a.d<AppStateManager.StateListResult> d) {
            this.wH = (com.google.android.gms.common.api.a.d<AppStateManager.StateListResult>)fq.b((com.google.android.gms.common.api.a.d)d, "Result holder must not be null");
        }
        
        @Override
        public void a(final DataHolder dataHolder) {
            ei.this.a((ff.b<?>)new d(this.wH, new Status(dataHolder.getStatusCode()), dataHolder));
        }
    }
    
    final class d extends ff.d<com.google.android.gms.common.api.a.d<StateListResult>> implements StateListResult
    {
        private final Status wJ;
        private final AppStateBuffer wL;
        
        public d(final com.google.android.gms.common.api.a.d<StateListResult> d, final Status wj, final DataHolder dataHolder) {
            super(d, dataHolder);
            this.wJ = wj;
            this.wL = new AppStateBuffer(dataHolder);
        }
        
        public void a(final com.google.android.gms.common.api.a.d<StateListResult> d, final DataHolder dataHolder) {
            d.b(this);
        }
        
        @Override
        public AppStateBuffer getStateBuffer() {
            return this.wL;
        }
        
        @Override
        public Status getStatus() {
            return this.wJ;
        }
    }
    
    final class e extends eh
    {
        private final com.google.android.gms.common.api.a.d<AppStateManager.StateResult> wH;
        
        public e(final com.google.android.gms.common.api.a.d<AppStateManager.StateResult> d) {
            this.wH = (com.google.android.gms.common.api.a.d<AppStateManager.StateResult>)fq.b((com.google.android.gms.common.api.a.d)d, "Result holder must not be null");
        }
        
        @Override
        public void a(final int n, final DataHolder dataHolder) {
            ei.this.a((ff.b<?>)new f(this.wH, n, dataHolder));
        }
    }
    
    final class f extends ff.d<a.d<StateResult>> implements StateConflictResult, StateLoadedResult, StateResult
    {
        private final Status wJ;
        private final int wK;
        private final AppStateBuffer wL;
        
        public f(final a.d<StateResult> d, final int wk, final DataHolder dataHolder) {
            super(d, dataHolder);
            this.wK = wk;
            this.wJ = new Status(dataHolder.getStatusCode());
            this.wL = new AppStateBuffer(dataHolder);
        }
        
        private boolean dy() {
            return this.wJ.getStatusCode() == 2000;
        }
        
        public void a(final a.d<StateResult> d, final DataHolder dataHolder) {
            d.b(this);
        }
        
        @Override
        public StateConflictResult getConflictResult() {
            if (this.dy()) {
                return this;
            }
            return null;
        }
        
        @Override
        public StateLoadedResult getLoadedResult() {
            f f = this;
            if (this.dy()) {
                f = null;
            }
            return f;
        }
        
        @Override
        public byte[] getLocalData() {
            if (this.wL.getCount() == 0) {
                return null;
            }
            return this.wL.get(0).getLocalData();
        }
        
        @Override
        public String getResolvedVersion() {
            if (this.wL.getCount() == 0) {
                return null;
            }
            return this.wL.get(0).getConflictVersion();
        }
        
        @Override
        public byte[] getServerData() {
            if (this.wL.getCount() == 0) {
                return null;
            }
            return this.wL.get(0).getConflictData();
        }
        
        @Override
        public int getStateKey() {
            return this.wK;
        }
        
        @Override
        public Status getStatus() {
            return this.wJ;
        }
        
        @Override
        public void release() {
            this.wL.close();
        }
    }
    
    final class g extends eh
    {
        com.google.android.gms.common.api.a.d<Status> wH;
        
        public g(final com.google.android.gms.common.api.a.d<Status> d) {
            this.wH = (com.google.android.gms.common.api.a.d<Status>)fq.b((com.google.android.gms.common.api.a.d)d, "Holder must not be null");
        }
        
        @Override
        public void du() {
            ei.this.a((ff.b<?>)new h(this.wH, new Status(0)));
        }
    }
    
    final class h extends ff.b<a.d<Status>>
    {
        private final Status wJ;
        
        public h(final a.d<Status> d, final Status wj) {
            super(d);
            this.wJ = wj;
        }
        
        public void c(final a.d<Status> d) {
            d.b(this.wJ);
        }
        
        @Override
        protected void dx() {
        }
    }
}
