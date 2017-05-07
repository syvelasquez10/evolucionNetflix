// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.IInterface;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.j;
import com.google.android.gms.common.internal.d$e;
import com.google.android.gms.common.internal.k;
import com.google.android.gms.appstate.AppStateManager$StateResult;
import com.google.android.gms.appstate.AppStateManager$StateDeletedResult;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.appstate.AppStateManager$StateListResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import android.os.IBinder;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.common.internal.d;

public final class ib extends d<id>
{
    private final String Dd;
    
    public ib(final Context context, final Looper looper, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener, final String s, final String[] array) {
        super(context, looper, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener, array);
        this.Dd = n.i(s);
    }
    
    protected id I(final IBinder binder) {
        return id$a.K(binder);
    }
    
    public void a(final BaseImplementation$b<AppStateManager$StateListResult> baseImplementation$b) {
        try {
            this.gS().a(new ib$c(baseImplementation$b));
        }
        catch (RemoteException ex) {
            Log.w("AppStateClient", "service died");
        }
    }
    
    public void a(final BaseImplementation$b<AppStateManager$StateDeletedResult> baseImplementation$b, final int n) {
        try {
            this.gS().b(new ib$a(baseImplementation$b), n);
        }
        catch (RemoteException ex) {
            Log.w("AppStateClient", "service died");
        }
    }
    
    public void a(final BaseImplementation$b<AppStateManager$StateResult> baseImplementation$b, final int n, final String s, final byte[] array) {
        try {
            this.gS().a(new ib$e(baseImplementation$b), n, s, array);
        }
        catch (RemoteException ex) {
            Log.w("AppStateClient", "service died");
        }
    }
    
    public void a(final BaseImplementation$b<AppStateManager$StateResult> baseImplementation$b, final int n, final byte[] array) {
        Label_0022: {
            if (baseImplementation$b != null) {
                break Label_0022;
            }
            ic ic = null;
            try {
                while (true) {
                    this.gS().a(ic, n, array);
                    return;
                    ic = new ib$e(baseImplementation$b);
                    continue;
                }
            }
            catch (RemoteException ex) {
                Log.w("AppStateClient", "service died");
            }
        }
    }
    
    @Override
    protected void a(final k k, final d$e d$e) {
        k.a(d$e, 6111000, this.getContext().getPackageName(), this.Dd, this.gR());
    }
    
    public void b(final BaseImplementation$b<Status> baseImplementation$b) {
        try {
            this.gS().b(new ib$g(baseImplementation$b));
        }
        catch (RemoteException ex) {
            Log.w("AppStateClient", "service died");
        }
    }
    
    public void b(final BaseImplementation$b<AppStateManager$StateResult> baseImplementation$b, final int n) {
        try {
            this.gS().a(new ib$e(baseImplementation$b), n);
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
}
