// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.IInterface;
import com.google.android.gms.internal.fl;
import com.google.android.gms.internal.fm;
import android.os.Bundle;
import com.google.android.gms.common.api.a;
import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.drive.events.c;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import android.os.IBinder;
import com.google.android.gms.internal.fq;
import java.util.HashMap;
import com.google.android.gms.internal.fc;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.drive.events.DriveEvent;
import java.util.Map;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.internal.ff;

public class n extends ff<u>
{
    private DriveId Fh;
    private DriveId Fi;
    final GoogleApiClient.ConnectionCallbacks Fj;
    Map<DriveId, Map<DriveEvent.Listener<?>, s<?>>> Fk;
    private final String wG;
    
    public n(final Context context, final Looper looper, final fc fc, final GoogleApiClient.ConnectionCallbacks fj, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, final String[] array) {
        super(context, looper, fj, onConnectionFailedListener, array);
        this.Fk = new HashMap<DriveId, Map<DriveEvent.Listener<?>, s<?>>>();
        this.wG = fq.b(fc.eC(), (Object)"Must call Api.ClientBuilder.setAccountName()");
        this.Fj = fj;
    }
    
    protected u F(final IBinder binder) {
        return u.a.G(binder);
    }
    
     <C extends DriveEvent> PendingResult<Status> a(final GoogleApiClient googleApiClient, final DriveId driveId, final int n, final DriveEvent.Listener<C> listener) {
        fq.b(com.google.android.gms.drive.events.c.a(n, driveId), "id");
        fq.b(listener, "listener");
        fq.a(this.isConnected(), (Object)"Client must be connected");
        synchronized (this.Fk) {
            Object o;
            if ((o = this.Fk.get(driveId)) == null) {
                o = new HashMap<DriveEvent.Listener<C>, s<Object>>();
                this.Fk.put(driveId, (Map<DriveEvent.Listener<?>, s<?>>)o);
            }
            if (((Map)o).containsKey(listener)) {
                return (PendingResult<Status>)new l.k(googleApiClient, Status.Bv);
            }
            final s<Object> s = new s<Object>(this.getLooper(), n, (DriveEvent.Listener<Object>)listener);
            ((Map<DriveEvent.Listener<C>, s<Object>>)o).put(listener, s);
            return googleApiClient.b((PendingResult<Status>)new l.j() {
                protected void a(final n n) throws RemoteException {
                    n.fE().a(new AddEventListenerRequest(driveId, n, null), s, null, new al((a.d<Status>)this));
                }
            });
        }
    }
    
    @Override
    protected void a(final int n, final IBinder binder, final Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(this.getClass().getClassLoader());
            this.Fh = (DriveId)bundle.getParcelable("com.google.android.gms.drive.root_id");
            this.Fi = (DriveId)bundle.getParcelable("com.google.android.gms.drive.appdata_id");
        }
        super.a(n, binder, bundle);
    }
    
    @Override
    protected void a(final fm fm, final e e) throws RemoteException {
        final String packageName = this.getContext().getPackageName();
        fq.f(e);
        fq.f(packageName);
        fq.f(this.eL());
        fm.a(e, 4452000, packageName, this.eL(), this.wG, new Bundle());
    }
    
    PendingResult<Status> b(final GoogleApiClient googleApiClient, final DriveId driveId, final int n, DriveEvent.Listener<?> s) {
        fq.b(com.google.android.gms.drive.events.c.a(n, driveId), "id");
        fq.b(s, "listener");
        fq.a(this.isConnected(), (Object)"Client must be connected");
        final Map<DriveEvent.Listener<?>, s<?>> map;
        synchronized (this.Fk) {
            map = this.Fk.get(driveId);
            if (map == null) {
                return (PendingResult<Status>)new l.k(googleApiClient, Status.Bv);
            }
            s = map.remove(s);
            if (s == null) {
                return (PendingResult<Status>)new l.k(googleApiClient, Status.Bv);
            }
        }
        if (map.isEmpty()) {
            this.Fk.remove(driveId);
        }
        final GoogleApiClient googleApiClient2;
        final l.j b = googleApiClient2.b(new l.j() {
            protected void a(final n n) throws RemoteException {
                n.fE().a(new RemoveEventListenerRequest(driveId, n), s, null, new al((a.d<Status>)this));
            }
        });
        // monitorexit(map2)
        return (PendingResult<Status>)b;
    }
    
    @Override
    protected String bg() {
        return "com.google.android.gms.drive.ApiService.START";
    }
    
    @Override
    protected String bh() {
        return "com.google.android.gms.drive.internal.IDriveService";
    }
    
    @Override
    public void disconnect() {
        final u u = this.eM();
        while (true) {
            if (u == null) {
                break Label_0025;
            }
            try {
                u.a(new DisconnectRequest());
                super.disconnect();
                this.Fk.clear();
            }
            catch (RemoteException ex) {
                continue;
            }
            break;
        }
    }
    
    public u fE() {
        return this.eM();
    }
    
    public DriveId fF() {
        return this.Fh;
    }
    
    public DriveId fG() {
        return this.Fi;
    }
}
