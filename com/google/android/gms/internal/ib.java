// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.appstate.AppStateBuffer;
import com.google.android.gms.common.api.a;
import com.google.android.gms.common.data.DataHolder;
import android.os.IInterface;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.j;
import com.google.android.gms.common.internal.k;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.appstate.AppStateManager;
import com.google.android.gms.common.api.BaseImplementation;
import android.os.IBinder;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.api.GoogleApiClient;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.common.internal.d;

public final class ib extends com.google.android.gms.common.internal.d<id>
{
    private final String Dd;
    
    public ib(final Context context, final Looper looper, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, final String s, final String[] array) {
        super(context, looper, connectionCallbacks, onConnectionFailedListener, array);
        this.Dd = n.i(s);
    }
    
    protected id I(final IBinder binder) {
        return id.a.K(binder);
    }
    
    public void a(final BaseImplementation.b<AppStateManager.StateListResult> b) {
        try {
            this.gS().a(new c(b));
        }
        catch (RemoteException ex) {
            Log.w("AppStateClient", "service died");
        }
    }
    
    public void a(final BaseImplementation.b<AppStateManager.StateDeletedResult> b, final int n) {
        try {
            this.gS().b(new a(b), n);
        }
        catch (RemoteException ex) {
            Log.w("AppStateClient", "service died");
        }
    }
    
    public void a(final BaseImplementation.b<AppStateManager.StateResult> b, final int n, final String s, final byte[] array) {
        try {
            this.gS().a(new e(b), n, s, array);
        }
        catch (RemoteException ex) {
            Log.w("AppStateClient", "service died");
        }
    }
    
    public void a(final BaseImplementation.b<AppStateManager.StateResult> b, final int n, final byte[] array) {
        Label_0022: {
            if (b != null) {
                break Label_0022;
            }
            ic ic = null;
            try {
                while (true) {
                    this.gS().a(ic, n, array);
                    return;
                    ic = new e(b);
                    continue;
                }
            }
            catch (RemoteException ex) {
                Log.w("AppStateClient", "service died");
            }
        }
    }
    
    @Override
    protected void a(final k k, final com.google.android.gms.common.internal.d.e e) throws RemoteException {
        k.a(e, 6111000, this.getContext().getPackageName(), this.Dd, this.gR());
    }
    
    public void b(final BaseImplementation.b<Status> b) {
        try {
            this.gS().b(new g(b));
        }
        catch (RemoteException ex) {
            Log.w("AppStateClient", "service died");
        }
    }
    
    public void b(final BaseImplementation.b<AppStateManager.StateResult> b, final int n) {
        try {
            this.gS().a(new e(b), n);
        }
        catch (RemoteException ex) {
            Log.w("AppStateClient", "service died");
        }
    }
    
    @Override
    protected void c(final String... array) {
        int i = 0;
        boolean b = false;
        while (i < array.length) {
            if (array[i].equals("https://www.googleapis.com/auth/appstate")) {
                b = true;
            }
            ++i;
        }
        n.a(b, (Object)String.format("App State APIs requires %s to function.", "https://www.googleapis.com/auth/appstate"));
    }
    
    public int fr() {
        try {
            return this.gS().fr();
        }
        catch (RemoteException ex) {
            Log.w("AppStateClient", "service died");
            return 2;
        }
    }
    
    public int fs() {
        try {
            return this.gS().fs();
        }
        catch (RemoteException ex) {
            Log.w("AppStateClient", "service died");
            return 2;
        }
    }
    
    @Override
    protected String getServiceDescriptor() {
        return "com.google.android.gms.appstate.internal.IAppStateService";
    }
    
    @Override
    protected String getStartServiceAction() {
        return "com.google.android.gms.appstate.service.START";
    }
    
    private static final class a extends ia
    {
        private final BaseImplementation.b<AppStateManager.StateDeletedResult> De;
        
        public a(final BaseImplementation.b<AppStateManager.StateDeletedResult> b) {
            this.De = (BaseImplementation.b<AppStateManager.StateDeletedResult>)n.b((BaseImplementation.b)b, "Result holder must not be null");
        }
        
        @Override
        public void e(final int n, final int n2) {
            this.De.b(new b(new Status(n), n2));
        }
    }
    
    private static final class b implements StateDeletedResult
    {
        private final Status CM;
        private final int Df;
        
        public b(final Status cm, final int df) {
            this.CM = cm;
            this.Df = df;
        }
        
        @Override
        public int getStateKey() {
            return this.Df;
        }
        
        @Override
        public Status getStatus() {
            return this.CM;
        }
    }
    
    private static final class c extends ia
    {
        private final BaseImplementation.b<AppStateManager.StateListResult> De;
        
        public c(final BaseImplementation.b<AppStateManager.StateListResult> b) {
            this.De = (BaseImplementation.b<AppStateManager.StateListResult>)n.b((BaseImplementation.b)b, "Result holder must not be null");
        }
        
        @Override
        public void a(final DataHolder dataHolder) {
            this.De.b(new d(dataHolder));
        }
    }
    
    private static final class d extends a implements StateListResult
    {
        private final AppStateBuffer Dg;
        
        public d(final DataHolder dataHolder) {
            super(dataHolder);
            this.Dg = new AppStateBuffer(dataHolder);
        }
        
        @Override
        public AppStateBuffer getStateBuffer() {
            return this.Dg;
        }
    }
    
    private static final class e extends ia
    {
        private final BaseImplementation.b<AppStateManager.StateResult> De;
        
        public e(final BaseImplementation.b<AppStateManager.StateResult> b) {
            this.De = (BaseImplementation.b<AppStateManager.StateResult>)n.b((BaseImplementation.b)b, "Result holder must not be null");
        }
        
        @Override
        public void a(final int n, final DataHolder dataHolder) {
            this.De.b(new f(n, dataHolder));
        }
    }
    
    private static final class f extends a implements StateConflictResult, StateLoadedResult, StateResult
    {
        private final int Df;
        private final AppStateBuffer Dg;
        
        public f(final int df, final DataHolder dataHolder) {
            super(dataHolder);
            this.Df = df;
            this.Dg = new AppStateBuffer(dataHolder);
        }
        
        private boolean ft() {
            return this.CM.getStatusCode() == 2000;
        }
        
        @Override
        public StateConflictResult getConflictResult() {
            if (this.ft()) {
                return this;
            }
            return null;
        }
        
        @Override
        public StateLoadedResult getLoadedResult() {
            f f = this;
            if (this.ft()) {
                f = null;
            }
            return f;
        }
        
        @Override
        public byte[] getLocalData() {
            if (this.Dg.getCount() == 0) {
                return null;
            }
            return this.Dg.get(0).getLocalData();
        }
        
        @Override
        public String getResolvedVersion() {
            if (this.Dg.getCount() == 0) {
                return null;
            }
            return this.Dg.get(0).getConflictVersion();
        }
        
        @Override
        public byte[] getServerData() {
            if (this.Dg.getCount() == 0) {
                return null;
            }
            return this.Dg.get(0).getConflictData();
        }
        
        @Override
        public int getStateKey() {
            return this.Df;
        }
        
        @Override
        public void release() {
            this.Dg.close();
        }
    }
    
    private static final class g extends ia
    {
        private final BaseImplementation.b<Status> De;
        
        public g(final BaseImplementation.b<Status> b) {
            this.De = (BaseImplementation.b<Status>)n.b((BaseImplementation.b)b, "Holder must not be null");
        }
        
        @Override
        public void fq() {
            this.De.b(new Status(0));
        }
    }
}
