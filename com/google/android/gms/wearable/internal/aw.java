// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import java.util.Collection;
import com.google.android.gms.wearable.Node;
import android.os.IInterface;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.wearable.DataItemBuffer;
import com.google.android.gms.common.internal.j;
import com.google.android.gms.common.internal.k;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.DataItemAsset;
import android.content.IntentFilter;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.DataItem;
import android.net.Uri;
import com.google.android.gms.common.api.BaseImplementation;
import java.util.Iterator;
import android.os.RemoteException;
import java.util.Map;
import com.google.android.gms.common.api.Status;
import android.util.Log;
import android.os.Bundle;
import android.os.IBinder;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import android.os.ParcelFileDescriptor;
import java.util.concurrent.Executors;
import com.google.android.gms.common.api.GoogleApiClient;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.DataApi;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import com.google.android.gms.common.internal.d;

public class aw extends d<af>
{
    private final ExecutorService aqp;
    private final HashMap<DataApi.DataListener, ax> avF;
    private final HashMap<MessageApi.MessageListener, ax> avG;
    private final HashMap<NodeApi.NodeListener, ax> avH;
    
    public aw(final Context context, final Looper looper, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, connectionCallbacks, onConnectionFailedListener, new String[0]);
        this.aqp = Executors.newCachedThreadPool();
        this.avF = new HashMap<DataApi.DataListener, ax>();
        this.avG = new HashMap<MessageApi.MessageListener, ax>();
        this.avH = new HashMap<NodeApi.NodeListener, ax>();
    }
    
    private FutureTask<Boolean> a(final ParcelFileDescriptor parcelFileDescriptor, final byte[] array) {
        return new FutureTask<Boolean>(new Callable<Boolean>() {
            public Boolean pY() {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     0: ldc             "WearableClient"
                //     2: iconst_3       
                //     3: invokestatic    android/util/Log.isLoggable:(Ljava/lang/String;I)Z
                //     6: ifeq            37
                //     9: ldc             "WearableClient"
                //    11: new             Ljava/lang/StringBuilder;
                //    14: dup            
                //    15: invokespecial   java/lang/StringBuilder.<init>:()V
                //    18: ldc             "processAssets: writing data to FD : "
                //    20: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //    23: aload_0        
                //    24: getfield        com/google/android/gms/wearable/internal/aw$2.avJ:Landroid/os/ParcelFileDescriptor;
                //    27: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
                //    30: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                //    33: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //    36: pop            
                //    37: new             Landroid/os/ParcelFileDescriptor$AutoCloseOutputStream;
                //    40: dup            
                //    41: aload_0        
                //    42: getfield        com/google/android/gms/wearable/internal/aw$2.avJ:Landroid/os/ParcelFileDescriptor;
                //    45: invokespecial   android/os/ParcelFileDescriptor$AutoCloseOutputStream.<init>:(Landroid/os/ParcelFileDescriptor;)V
                //    48: astore_1       
                //    49: aload_1        
                //    50: aload_0        
                //    51: getfield        com/google/android/gms/wearable/internal/aw$2.CY:[B
                //    54: invokevirtual   android/os/ParcelFileDescriptor$AutoCloseOutputStream.write:([B)V
                //    57: aload_1        
                //    58: invokevirtual   android/os/ParcelFileDescriptor$AutoCloseOutputStream.flush:()V
                //    61: ldc             "WearableClient"
                //    63: iconst_3       
                //    64: invokestatic    android/util/Log.isLoggable:(Ljava/lang/String;I)Z
                //    67: ifeq            98
                //    70: ldc             "WearableClient"
                //    72: new             Ljava/lang/StringBuilder;
                //    75: dup            
                //    76: invokespecial   java/lang/StringBuilder.<init>:()V
                //    79: ldc             "processAssets: wrote data: "
                //    81: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //    84: aload_0        
                //    85: getfield        com/google/android/gms/wearable/internal/aw$2.avJ:Landroid/os/ParcelFileDescriptor;
                //    88: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
                //    91: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                //    94: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //    97: pop            
                //    98: iconst_1       
                //    99: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
                //   102: astore_2       
                //   103: ldc             "WearableClient"
                //   105: iconst_3       
                //   106: invokestatic    android/util/Log.isLoggable:(Ljava/lang/String;I)Z
                //   109: ifeq            140
                //   112: ldc             "WearableClient"
                //   114: new             Ljava/lang/StringBuilder;
                //   117: dup            
                //   118: invokespecial   java/lang/StringBuilder.<init>:()V
                //   121: ldc             "processAssets: closing: "
                //   123: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   126: aload_0        
                //   127: getfield        com/google/android/gms/wearable/internal/aw$2.avJ:Landroid/os/ParcelFileDescriptor;
                //   130: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
                //   133: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                //   136: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //   139: pop            
                //   140: aload_1        
                //   141: invokevirtual   android/os/ParcelFileDescriptor$AutoCloseOutputStream.close:()V
                //   144: aload_2        
                //   145: areturn        
                //   146: astore_2       
                //   147: ldc             "WearableClient"
                //   149: new             Ljava/lang/StringBuilder;
                //   152: dup            
                //   153: invokespecial   java/lang/StringBuilder.<init>:()V
                //   156: ldc             "processAssets: writing data failed: "
                //   158: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   161: aload_0        
                //   162: getfield        com/google/android/gms/wearable/internal/aw$2.avJ:Landroid/os/ParcelFileDescriptor;
                //   165: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
                //   168: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                //   171: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
                //   174: pop            
                //   175: ldc             "WearableClient"
                //   177: iconst_3       
                //   178: invokestatic    android/util/Log.isLoggable:(Ljava/lang/String;I)Z
                //   181: ifeq            212
                //   184: ldc             "WearableClient"
                //   186: new             Ljava/lang/StringBuilder;
                //   189: dup            
                //   190: invokespecial   java/lang/StringBuilder.<init>:()V
                //   193: ldc             "processAssets: closing: "
                //   195: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   198: aload_0        
                //   199: getfield        com/google/android/gms/wearable/internal/aw$2.avJ:Landroid/os/ParcelFileDescriptor;
                //   202: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
                //   205: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                //   208: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //   211: pop            
                //   212: aload_1        
                //   213: invokevirtual   android/os/ParcelFileDescriptor$AutoCloseOutputStream.close:()V
                //   216: iconst_0       
                //   217: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
                //   220: areturn        
                //   221: astore_2       
                //   222: ldc             "WearableClient"
                //   224: iconst_3       
                //   225: invokestatic    android/util/Log.isLoggable:(Ljava/lang/String;I)Z
                //   228: ifeq            259
                //   231: ldc             "WearableClient"
                //   233: new             Ljava/lang/StringBuilder;
                //   236: dup            
                //   237: invokespecial   java/lang/StringBuilder.<init>:()V
                //   240: ldc             "processAssets: closing: "
                //   242: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   245: aload_0        
                //   246: getfield        com/google/android/gms/wearable/internal/aw$2.avJ:Landroid/os/ParcelFileDescriptor;
                //   249: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
                //   252: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                //   255: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //   258: pop            
                //   259: aload_1        
                //   260: invokevirtual   android/os/ParcelFileDescriptor$AutoCloseOutputStream.close:()V
                //   263: aload_2        
                //   264: athrow         
                //   265: astore_1       
                //   266: goto            263
                //   269: astore_1       
                //   270: goto            216
                //   273: astore_1       
                //   274: aload_2        
                //   275: areturn        
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                 
                //  -----  -----  -----  -----  ---------------------
                //  49     98     146    221    Ljava/io/IOException;
                //  49     98     221    269    Any
                //  98     103    146    221    Ljava/io/IOException;
                //  98     103    221    269    Any
                //  103    140    273    276    Ljava/io/IOException;
                //  140    144    273    276    Ljava/io/IOException;
                //  147    175    221    269    Any
                //  175    212    269    273    Ljava/io/IOException;
                //  212    216    269    273    Ljava/io/IOException;
                //  222    259    265    269    Ljava/io/IOException;
                //  259    263    265    269    Ljava/io/IOException;
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_0140:
                //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
                //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
                //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
                //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1163)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1010)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
                //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
                //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
                //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
                //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
                //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
                // 
                throw new IllegalStateException("An error occurred while decompiling this method.");
            }
        });
    }
    
    @Override
    protected void a(final int n, final IBinder binder, final Bundle bundle) {
        if (Log.isLoggable("WearableClient", 2)) {
            Log.d("WearableClient", "onPostInitHandler: statusCode " + n);
        }
        Label_0205: {
            if (n == 0) {
                com.google.android.gms.wearable.internal.a a = null;
                af bt = null;
                Label_0221: {
                    try {
                        a = new com.google.android.gms.wearable.internal.a() {
                            @Override
                            public void a(final Status status) {
                            }
                        };
                        if (Log.isLoggable("WearableClient", 2)) {
                            Log.d("WearableClient", "onPostInitHandler: service " + binder);
                        }
                        bt = af.a.bT(binder);
                        for (final Map.Entry<DataApi.DataListener, ax> entry : this.avF.entrySet()) {
                            if (Log.isLoggable("WearableClient", 2)) {
                                Log.d("WearableClient", "onPostInitHandler: adding Data listener " + entry.getValue());
                            }
                            bt.a(a, new com.google.android.gms.wearable.internal.b(entry.getValue()));
                        }
                        break Label_0221;
                    }
                    catch (RemoteException ex) {
                        Log.d("WearableClient", "WearableClientImpl.onPostInitHandler: error while adding listener", (Throwable)ex);
                    }
                    break Label_0205;
                }
                for (final Map.Entry<MessageApi.MessageListener, ax> entry2 : this.avG.entrySet()) {
                    if (Log.isLoggable("WearableClient", 2)) {
                        Log.d("WearableClient", "onPostInitHandler: adding Message listener " + entry2.getValue());
                    }
                    bt.a(a, new com.google.android.gms.wearable.internal.b(entry2.getValue()));
                }
                for (final Map.Entry<NodeApi.NodeListener, ax> entry3 : this.avH.entrySet()) {
                    if (Log.isLoggable("WearableClient", 2)) {
                        Log.d("WearableClient", "onPostInitHandler: adding Node listener " + entry3.getValue());
                    }
                    bt.a(a, new com.google.android.gms.wearable.internal.b(entry3.getValue()));
                }
            }
        }
        Log.d("WearableClient", "WearableClientImpl.onPostInitHandler: done");
        super.a(n, binder, bundle);
    }
    
    public void a(final BaseImplementation.b<DataApi.DataItemResult> b, final Uri uri) throws RemoteException {
        this.gS().a(new com.google.android.gms.wearable.internal.a() {
            @Override
            public void a(final x x) {
                b.b(new com.google.android.gms.wearable.internal.f.a(new Status(x.statusCode), x.avp));
            }
        }, uri);
    }
    
    public void a(final BaseImplementation.b<DataApi.GetFdForAssetResult> b, final Asset asset) throws RemoteException {
        this.gS().a(new com.google.android.gms.wearable.internal.a() {
            @Override
            public void a(final z z) {
                b.b(new com.google.android.gms.wearable.internal.f.c(new Status(z.statusCode), z.avq));
            }
        }, asset);
    }
    
    public void a(final BaseImplementation.b<Status> b, DataApi.DataListener dataListener) throws RemoteException {
        synchronized (this.avF) {
            dataListener = (DataApi.DataListener)this.avF.remove(dataListener);
            // monitorexit(this.avF)
            if (dataListener == null) {
                b.b(new Status(4002));
                return;
            }
        }
        final BaseImplementation.b<Status> b2;
        this.a(b2, (ae)dataListener);
    }
    
    public void a(final BaseImplementation.b<Status> b, final DataApi.DataListener dataListener, final IntentFilter[] array) throws RemoteException {
        final ax a = ax.a(dataListener, array);
        synchronized (this.avF) {
            if (this.avF.get(dataListener) != null) {
                b.b(new Status(4001));
                return;
            }
            this.avF.put(dataListener, a);
            // monitorexit(this.avF)
            this.gS().a(new com.google.android.gms.wearable.internal.a() {
                @Override
                public void a(final Status status) {
                    Label_0034: {
                        if (status.isSuccess()) {
                            break Label_0034;
                        }
                        synchronized (aw.this.avF) {
                            aw.this.avF.remove(dataListener);
                            // monitorexit(aw.b(this.avI))
                            b.b(status);
                        }
                    }
                }
            }, new com.google.android.gms.wearable.internal.b(a));
        }
    }
    
    public void a(final BaseImplementation.b<DataApi.GetFdForAssetResult> b, final DataItemAsset dataItemAsset) throws RemoteException {
        this.a(b, Asset.createFromRef(dataItemAsset.getId()));
    }
    
    public void a(final BaseImplementation.b<Status> b, final MessageApi.MessageListener messageListener) throws RemoteException {
        synchronized (this.avG) {
            final ax ax = this.avG.remove(messageListener);
            if (ax == null) {
                b.b(new Status(4002));
            }
            else {
                this.a(b, ax);
            }
        }
    }
    
    public void a(final BaseImplementation.b<Status> b, final MessageApi.MessageListener messageListener, final IntentFilter[] array) throws RemoteException {
        final ax a = ax.a(messageListener, array);
        synchronized (this.avG) {
            if (this.avG.get(messageListener) != null) {
                b.b(new Status(4001));
                return;
            }
            this.avG.put(messageListener, a);
            // monitorexit(this.avG)
            this.gS().a(new com.google.android.gms.wearable.internal.a() {
                @Override
                public void a(final Status status) {
                    Label_0034: {
                        if (status.isSuccess()) {
                            break Label_0034;
                        }
                        synchronized (aw.this.avG) {
                            aw.this.avG.remove(messageListener);
                            // monitorexit(aw.c(this.avI))
                            b.b(status);
                        }
                    }
                }
            }, new com.google.android.gms.wearable.internal.b(a));
        }
    }
    
    public void a(final BaseImplementation.b<Status> b, final NodeApi.NodeListener nodeListener) throws RemoteException {
        final ax a = ax.a(nodeListener);
        synchronized (this.avH) {
            if (this.avH.get(nodeListener) != null) {
                b.b(new Status(4001));
                return;
            }
            this.avH.put(nodeListener, a);
            // monitorexit(this.avH)
            this.gS().a(new com.google.android.gms.wearable.internal.a() {
                @Override
                public void a(final Status status) {
                    Label_0034: {
                        if (status.isSuccess()) {
                            break Label_0034;
                        }
                        synchronized (aw.this.avH) {
                            aw.this.avH.remove(nodeListener);
                            // monitorexit(aw.d(this.avI))
                            b.b(status);
                        }
                    }
                }
            }, new com.google.android.gms.wearable.internal.b(a));
        }
    }
    
    public void a(final BaseImplementation.b<DataApi.DataItemResult> b, final PutDataRequest putDataRequest) throws RemoteException {
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
            this.gS().a(new a(b, list), k);
        }
        catch (NullPointerException ex2) {
            throw new IllegalStateException("Unable to putDataItem: " + putDataRequest, ex2);
        }
    }
    
    public void a(final BaseImplementation.b<Status> b, final ae ae) throws RemoteException {
        this.gS().a(new com.google.android.gms.wearable.internal.a() {
            @Override
            public void a(final Status status) {
                b.b(status);
            }
        }, new aq(ae));
    }
    
    public void a(final BaseImplementation.b<MessageApi.SendMessageResult> b, final String s, final String s2, final byte[] array) throws RemoteException {
        this.gS().a(new com.google.android.gms.wearable.internal.a() {
            @Override
            public void a(final as as) {
                b.b(new ag.a(new Status(as.statusCode), as.avD));
            }
        }, s, s2, array);
    }
    
    @Override
    protected void a(final k k, final e e) throws RemoteException {
        k.e(e, 6111000, this.getContext().getPackageName());
    }
    
    public void b(final BaseImplementation.b<DataItemBuffer> b, final Uri uri) throws RemoteException {
        this.gS().b(new com.google.android.gms.wearable.internal.a() {
            @Override
            public void aa(final DataHolder dataHolder) {
                b.b(new DataItemBuffer(dataHolder));
            }
        }, uri);
    }
    
    public void b(final BaseImplementation.b<Status> b, final NodeApi.NodeListener nodeListener) throws RemoteException {
        synchronized (this.avH) {
            final ax ax = this.avH.remove(nodeListener);
            if (ax == null) {
                b.b(new Status(4002));
            }
            else {
                this.a(b, ax);
            }
        }
    }
    
    protected af bU(final IBinder binder) {
        return af.a.bT(binder);
    }
    
    public void c(final BaseImplementation.b<DataApi.DeleteDataItemsResult> b, final Uri uri) throws RemoteException {
        this.gS().c(new com.google.android.gms.wearable.internal.a() {
            @Override
            public void a(final p p) {
                b.b(new com.google.android.gms.wearable.internal.f.b(new Status(p.statusCode), p.avl));
            }
        }, uri);
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
    
    public void o(final BaseImplementation.b<DataItemBuffer> b) throws RemoteException {
        this.gS().b(new com.google.android.gms.wearable.internal.a() {
            @Override
            public void aa(final DataHolder dataHolder) {
                b.b(new DataItemBuffer(dataHolder));
            }
        });
    }
    
    public void p(final BaseImplementation.b<NodeApi.GetLocalNodeResult> b) throws RemoteException {
        this.gS().c(new com.google.android.gms.wearable.internal.a() {
            @Override
            public void a(final ab ab) {
                b.b(new aj.b(new Status(ab.statusCode), ab.avr));
            }
        });
    }
    
    public void q(final BaseImplementation.b<NodeApi.GetConnectedNodesResult> b) throws RemoteException {
        this.gS().d(new com.google.android.gms.wearable.internal.a() {
            @Override
            public void a(final v v) {
                final ArrayList<Object> list = new ArrayList<Object>();
                list.addAll(v.avo);
                b.b(new aj.a(new Status(v.statusCode), (List<Node>)list));
            }
        });
    }
    
    private static class a extends com.google.android.gms.wearable.internal.a
    {
        private final BaseImplementation.b<DataApi.DataItemResult> De;
        private final List<FutureTask<Boolean>> avL;
        
        a(final BaseImplementation.b<DataApi.DataItemResult> de, final List<FutureTask<Boolean>> avL) {
            this.De = de;
            this.avL = avL;
        }
        
        @Override
        public void a(final ao ao) {
            this.De.b(new com.google.android.gms.wearable.internal.f.a(new Status(ao.statusCode), ao.avp));
            if (ao.statusCode != 0) {
                final Iterator<FutureTask<Boolean>> iterator = this.avL.iterator();
                while (iterator.hasNext()) {
                    iterator.next().cancel(true);
                }
            }
        }
    }
}
