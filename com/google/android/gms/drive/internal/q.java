// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.internal.j;
import com.google.android.gms.common.internal.d$e;
import com.google.android.gms.common.internal.k;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;
import android.os.IBinder;
import android.content.pm.ServiceInfo;
import java.util.List;
import android.content.pm.ResolveInfo;
import android.content.Intent;
import com.google.android.gms.common.internal.n;
import java.util.HashMap;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.drive.events.c;
import java.util.Map;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.drive.DriveId;
import android.os.Bundle;
import com.google.android.gms.common.internal.d;

public class q extends d<ab>
{
    private final String Dd;
    private final String IH;
    private final Bundle Os;
    private final boolean Ot;
    private DriveId Ou;
    private DriveId Ov;
    final GoogleApiClient$ConnectionCallbacks Ow;
    final Map<DriveId, Map<c, y>> Ox;
    
    public q(final Context context, final Looper looper, final ClientSettings clientSettings, final GoogleApiClient$ConnectionCallbacks ow, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener, final String[] array, final Bundle os) {
        super(context, looper, ow, googleApiClient$OnConnectionFailedListener, array);
        this.Ox = new HashMap<DriveId, Map<c, y>>();
        this.Dd = n.b(clientSettings.getAccountNameOrDefault(), (Object)"Must call Api.ClientBuilder.setAccountName()");
        this.IH = clientSettings.getRealClientPackageName();
        this.Ow = ow;
        this.Os = os;
        final Intent intent = new Intent("com.google.android.gms.drive.events.HANDLE_EVENT");
        intent.setPackage(context.getPackageName());
        final List queryIntentServices = context.getPackageManager().queryIntentServices(intent, 0);
        switch (queryIntentServices.size()) {
            default: {
                throw new IllegalStateException("AndroidManifest.xml can only define one service that handles the " + intent.getAction() + " action");
            }
            case 0: {
                this.Ot = false;
            }
            case 1: {
                final ServiceInfo serviceInfo = queryIntentServices.get(0).serviceInfo;
                if (!serviceInfo.exported) {
                    throw new IllegalStateException("Drive event service " + serviceInfo.name + " must be exported in AndroidManifest.xml");
                }
                this.Ot = true;
            }
        }
    }
    
    protected ab T(final IBinder binder) {
        return ab$a.U(binder);
    }
    
    PendingResult<Status> a(final GoogleApiClient googleApiClient, final DriveId driveId, final int n) {
        n.b(com.google.android.gms.drive.events.d.a(n, driveId), (Object)"id");
        n.i("eventService");
        n.a(this.isConnected(), (Object)"Client must be connected");
        if (!this.Ot) {
            throw new IllegalStateException("Application must define an exported DriveEventService subclass in AndroidManifest.xml to add event subscriptions");
        }
        return googleApiClient.b((PendingResult<Status>)new q$3(this, driveId, n));
    }
    
    PendingResult<Status> a(final GoogleApiClient googleApiClient, final DriveId driveId, final int n, c c) {
        while (true) {
            n.b(com.google.android.gms.drive.events.d.a(n, driveId), (Object)"id");
            n.b(c, "listener");
            n.a(this.isConnected(), (Object)"Client must be connected");
            while (true) {
                Label_0199: {
                    synchronized (this.Ox) {
                        Map<c, y> map = this.Ox.get(driveId);
                        if (map == null) {
                            map = new HashMap<c, y>();
                            this.Ox.put(driveId, map);
                            final Object o = map.get(c);
                            if (o == null) {
                                final y y = new y(this.getLooper(), this.getContext(), n, c);
                                map.put(c, y);
                                c = (c)y;
                            }
                            else {
                                c = (c)o;
                                if (((y)o).br(n)) {
                                    return (PendingResult<Status>)new o$m(googleApiClient, Status.Jo);
                                }
                            }
                            ((y)c).bq(n);
                            return googleApiClient.b((PendingResult<Status>)new q$1(this, driveId, n, (y)c));
                        }
                        break Label_0199;
                    }
                }
                continue;
            }
        }
    }
    
    @Override
    protected void a(final int n, final IBinder binder, final Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(this.getClass().getClassLoader());
            this.Ou = (DriveId)bundle.getParcelable("com.google.android.gms.drive.root_id");
            this.Ov = (DriveId)bundle.getParcelable("com.google.android.gms.drive.appdata_id");
        }
        super.a(n, binder, bundle);
    }
    
    @Override
    protected void a(final k k, final d$e d$e) {
        final String packageName = this.getContext().getPackageName();
        n.i(d$e);
        n.i(packageName);
        n.i(this.gR());
        final Bundle bundle = new Bundle();
        if (!packageName.equals(this.IH)) {
            bundle.putString("proxy_package_name", this.IH);
        }
        bundle.putAll(this.Os);
        k.a(d$e, 6111000, packageName, this.gR(), this.Dd, bundle);
    }
    
    PendingResult<Status> b(final GoogleApiClient googleApiClient, final DriveId driveId, final int n) {
        n.b(com.google.android.gms.drive.events.d.a(n, driveId), (Object)"id");
        n.i("eventService");
        n.a(this.isConnected(), (Object)"Client must be connected");
        return googleApiClient.b((PendingResult<Status>)new q$4(this, driveId, n));
    }
    
    PendingResult<Status> b(final GoogleApiClient googleApiClient, final DriveId driveId, final int n, c c) {
        n.b(com.google.android.gms.drive.events.d.a(n, driveId), (Object)"id");
        n.a(this.isConnected(), (Object)"Client must be connected");
        n.b(c, "listener");
        final Map<c, y> map;
        synchronized (this.Ox) {
            map = this.Ox.get(driveId);
            if (map == null) {
                return (PendingResult<Status>)new o$m(googleApiClient, Status.Jo);
            }
            c = (c)map.remove(c);
            if (c == null) {
                return (PendingResult<Status>)new o$m(googleApiClient, Status.Jo);
            }
        }
        if (map.isEmpty()) {
            this.Ox.remove(driveId);
        }
        final GoogleApiClient googleApiClient2;
        final q$2 b = googleApiClient2.b(new q$2(this, driveId, n, (y)c));
        // monitorexit(map2)
        return (PendingResult<Status>)b;
    }
    
    @Override
    public void disconnect() {
        final ab ab = this.gS();
        while (true) {
            if (ab == null) {
                break Label_0025;
            }
            try {
                ab.a(new DisconnectRequest());
                super.disconnect();
                this.Ox.clear();
            }
            catch (RemoteException ex) {
                continue;
            }
            break;
        }
    }
    
    @Override
    protected String getServiceDescriptor() {
        return "com.google.android.gms.drive.internal.IDriveService";
    }
    
    @Override
    protected String getStartServiceAction() {
        return "com.google.android.gms.drive.ApiService.START";
    }
    
    public ab hY() {
        return this.gS();
    }
    
    public DriveId hZ() {
        return this.Ou;
    }
    
    public DriveId ia() {
        return this.Ov;
    }
    
    public boolean ib() {
        return this.Ot;
    }
}
