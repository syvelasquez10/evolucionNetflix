// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import com.google.android.gms.wearable.NodeApi$GetConnectedNodesResult;
import com.google.android.gms.wearable.NodeApi$GetLocalNodeResult;
import android.os.IInterface;
import com.google.android.gms.wearable.DataApi$DeleteDataItemsResult;
import com.google.android.gms.wearable.DataItemBuffer;
import com.google.android.gms.common.internal.j;
import com.google.android.gms.common.internal.d$e;
import com.google.android.gms.common.internal.k;
import com.google.android.gms.wearable.MessageApi$SendMessageResult;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.DataItemAsset;
import android.content.IntentFilter;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.DataApi$GetFdForAssetResult;
import android.net.Uri;
import com.google.android.gms.wearable.DataApi$DataItemResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import java.util.Iterator;
import android.os.RemoteException;
import java.util.Map;
import android.util.Log;
import android.os.Bundle;
import android.os.IBinder;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import android.os.ParcelFileDescriptor;
import java.util.concurrent.Executors;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.wearable.NodeApi$NodeListener;
import com.google.android.gms.wearable.MessageApi$MessageListener;
import com.google.android.gms.wearable.DataApi$DataListener;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import com.google.android.gms.common.internal.d;

public class aw extends d<af>
{
    private final ExecutorService aqp;
    private final HashMap<DataApi$DataListener, ax> avF;
    private final HashMap<MessageApi$MessageListener, ax> avG;
    private final HashMap<NodeApi$NodeListener, ax> avH;
    
    public aw(final Context context, final Looper looper, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        super(context, looper, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener, new String[0]);
        this.aqp = Executors.newCachedThreadPool();
        this.avF = new HashMap<DataApi$DataListener, ax>();
        this.avG = new HashMap<MessageApi$MessageListener, ax>();
        this.avH = new HashMap<NodeApi$NodeListener, ax>();
    }
    
    private FutureTask<Boolean> a(final ParcelFileDescriptor parcelFileDescriptor, final byte[] array) {
        return new FutureTask<Boolean>(new aw$2(this, parcelFileDescriptor, array));
    }
    
    @Override
    protected void a(final int n, final IBinder binder, final Bundle bundle) {
        if (Log.isLoggable("WearableClient", 2)) {
            Log.d("WearableClient", "onPostInitHandler: statusCode " + n);
        }
        Label_0205: {
            if (n == 0) {
                aw$1 aw$1 = null;
                af bt = null;
                Label_0221: {
                    try {
                        aw$1 = new aw$1(this);
                        if (Log.isLoggable("WearableClient", 2)) {
                            Log.d("WearableClient", "onPostInitHandler: service " + binder);
                        }
                        bt = af$a.bT(binder);
                        for (final Map.Entry<DataApi$DataListener, ax> entry : this.avF.entrySet()) {
                            if (Log.isLoggable("WearableClient", 2)) {
                                Log.d("WearableClient", "onPostInitHandler: adding Data listener " + entry.getValue());
                            }
                            bt.a(aw$1, new b(entry.getValue()));
                        }
                        break Label_0221;
                    }
                    catch (RemoteException ex) {
                        Log.d("WearableClient", "WearableClientImpl.onPostInitHandler: error while adding listener", (Throwable)ex);
                    }
                    break Label_0205;
                }
                for (final Map.Entry<MessageApi$MessageListener, ax> entry2 : this.avG.entrySet()) {
                    if (Log.isLoggable("WearableClient", 2)) {
                        Log.d("WearableClient", "onPostInitHandler: adding Message listener " + entry2.getValue());
                    }
                    bt.a(aw$1, new b(entry2.getValue()));
                }
                for (final Map.Entry<NodeApi$NodeListener, ax> entry3 : this.avH.entrySet()) {
                    if (Log.isLoggable("WearableClient", 2)) {
                        Log.d("WearableClient", "onPostInitHandler: adding Node listener " + entry3.getValue());
                    }
                    bt.a(aw$1, new b(entry3.getValue()));
                }
            }
        }
        Log.d("WearableClient", "WearableClientImpl.onPostInitHandler: done");
        super.a(n, binder, bundle);
    }
    
    public void a(final BaseImplementation$b<DataApi$DataItemResult> baseImplementation$b, final Uri uri) {
        this.gS().a(new aw$3(this, baseImplementation$b), uri);
    }
    
    public void a(final BaseImplementation$b<DataApi$GetFdForAssetResult> baseImplementation$b, final Asset asset) {
        this.gS().a(new aw$8(this, baseImplementation$b), asset);
    }
    
    public void a(final BaseImplementation$b<Status> baseImplementation$b, DataApi$DataListener dataApi$DataListener) {
        synchronized (this.avF) {
            dataApi$DataListener = (DataApi$DataListener)this.avF.remove(dataApi$DataListener);
            // monitorexit(this.avF)
            if (dataApi$DataListener == null) {
                baseImplementation$b.b(new Status(4002));
                return;
            }
        }
        final BaseImplementation$b<Status> baseImplementation$b2;
        this.a(baseImplementation$b2, (ae)dataApi$DataListener);
    }
    
    public void a(final BaseImplementation$b<Status> baseImplementation$b, final DataApi$DataListener dataApi$DataListener, final IntentFilter[] array) {
        final ax a = ax.a(dataApi$DataListener, array);
        synchronized (this.avF) {
            if (this.avF.get(dataApi$DataListener) != null) {
                baseImplementation$b.b(new Status(4001));
                return;
            }
            this.avF.put(dataApi$DataListener, a);
            // monitorexit(this.avF)
            this.gS().a(new aw$11(this, dataApi$DataListener, baseImplementation$b), new b(a));
        }
    }
    
    public void a(final BaseImplementation$b<DataApi$GetFdForAssetResult> baseImplementation$b, final DataItemAsset dataItemAsset) {
        this.a(baseImplementation$b, Asset.createFromRef(dataItemAsset.getId()));
    }
    
    public void a(final BaseImplementation$b<Status> baseImplementation$b, final MessageApi$MessageListener messageApi$MessageListener) {
        synchronized (this.avG) {
            final ax ax = this.avG.remove(messageApi$MessageListener);
            if (ax == null) {
                baseImplementation$b.b(new Status(4002));
            }
            else {
                this.a(baseImplementation$b, ax);
            }
        }
    }
    
    public void a(final BaseImplementation$b<Status> baseImplementation$b, final MessageApi$MessageListener messageApi$MessageListener, final IntentFilter[] array) {
        final ax a = ax.a(messageApi$MessageListener, array);
        synchronized (this.avG) {
            if (this.avG.get(messageApi$MessageListener) != null) {
                baseImplementation$b.b(new Status(4001));
                return;
            }
            this.avG.put(messageApi$MessageListener, a);
            // monitorexit(this.avG)
            this.gS().a(new aw$12(this, messageApi$MessageListener, baseImplementation$b), new b(a));
        }
    }
    
    public void a(final BaseImplementation$b<Status> baseImplementation$b, final NodeApi$NodeListener nodeApi$NodeListener) {
        final ax a = ax.a(nodeApi$NodeListener);
        synchronized (this.avH) {
            if (this.avH.get(nodeApi$NodeListener) != null) {
                baseImplementation$b.b(new Status(4001));
                return;
            }
            this.avH.put(nodeApi$NodeListener, a);
            // monitorexit(this.avH)
            this.gS().a(new aw$13(this, nodeApi$NodeListener, baseImplementation$b), new b(a));
        }
    }
    
    public void a(final BaseImplementation$b<DataApi$DataItemResult> baseImplementation$b, final PutDataRequest putDataRequest) {
        final Iterator<Map.Entry<String, Asset>> iterator = putDataRequest.getAssets().entrySet().iterator();
        while (iterator.hasNext()) {
            final Asset asset = iterator.next().getValue();
            if (asset.getData() == null && asset.getDigest() == null && asset.getFd() == null && asset.getUri() == null) {
                throw new IllegalArgumentException("Put for " + putDataRequest.getUri() + " contains invalid asset: " + asset);
            }
        }
        final PutDataRequest k = PutDataRequest.k(putDataRequest.getUri());
        k.setData(putDataRequest.getData());
        final ArrayList<FutureTask<Boolean>> list = new ArrayList<FutureTask<Boolean>>();
        for (final Map.Entry<String, Asset> entry : putDataRequest.getAssets().entrySet()) {
            final Asset asset2 = entry.getValue();
            if (asset2.getData() != null) {
                try {
                    final ParcelFileDescriptor[] pipe = ParcelFileDescriptor.createPipe();
                    if (Log.isLoggable("WearableClient", 3)) {
                        Log.d("WearableClient", "processAssets: replacing data with FD in asset: " + asset2 + " read:" + pipe[0] + " write:" + pipe[1]);
                    }
                    k.putAsset(entry.getKey(), Asset.createFromFd(pipe[0]));
                    final FutureTask<Boolean> a = this.a(pipe[1], asset2.getData());
                    list.add(a);
                    this.aqp.submit(a);
                    continue;
                }
                catch (IOException ex) {
                    throw new IllegalStateException("Unable to create ParcelFileDescriptor for asset in request: " + putDataRequest, ex);
                }
                break;
            }
            k.putAsset(entry.getKey(), entry.getValue());
        }
        try {
            this.gS().a(new aw$a(baseImplementation$b, list), k);
        }
        catch (NullPointerException ex2) {
            throw new IllegalStateException("Unable to putDataItem: " + putDataRequest, ex2);
        }
    }
    
    public void a(final BaseImplementation$b<Status> baseImplementation$b, final ae ae) {
        this.gS().a(new aw$14(this, baseImplementation$b), new aq(ae));
    }
    
    public void a(final BaseImplementation$b<MessageApi$SendMessageResult> baseImplementation$b, final String s, final String s2, final byte[] array) {
        this.gS().a(new aw$7(this, baseImplementation$b), s, s2, array);
    }
    
    @Override
    protected void a(final k k, final d$e d$e) {
        k.e(d$e, 6111000, this.getContext().getPackageName());
    }
    
    public void b(final BaseImplementation$b<DataItemBuffer> baseImplementation$b, final Uri uri) {
        this.gS().b(new aw$5(this, baseImplementation$b), uri);
    }
    
    public void b(final BaseImplementation$b<Status> baseImplementation$b, final NodeApi$NodeListener nodeApi$NodeListener) {
        synchronized (this.avH) {
            final ax ax = this.avH.remove(nodeApi$NodeListener);
            if (ax == null) {
                baseImplementation$b.b(new Status(4002));
            }
            else {
                this.a(baseImplementation$b, ax);
            }
        }
    }
    
    protected af bU(final IBinder binder) {
        return af$a.bT(binder);
    }
    
    public void c(final BaseImplementation$b<DataApi$DeleteDataItemsResult> baseImplementation$b, final Uri uri) {
        this.gS().c(new aw$6(this, baseImplementation$b), uri);
    }
    
    @Override
    public void disconnect() {
        super.disconnect();
        this.avF.clear();
        this.avG.clear();
        this.avH.clear();
    }
    
    @Override
    protected String getServiceDescriptor() {
        return "com.google.android.gms.wearable.internal.IWearableService";
    }
    
    @Override
    protected String getStartServiceAction() {
        return "com.google.android.gms.wearable.BIND";
    }
    
    public void o(final BaseImplementation$b<DataItemBuffer> baseImplementation$b) {
        this.gS().b(new aw$4(this, baseImplementation$b));
    }
    
    public void p(final BaseImplementation$b<NodeApi$GetLocalNodeResult> baseImplementation$b) {
        this.gS().c(new aw$9(this, baseImplementation$b));
    }
    
    public void q(final BaseImplementation$b<NodeApi$GetConnectedNodesResult> baseImplementation$b) {
        this.gS().d(new aw$10(this, baseImplementation$b));
    }
}
