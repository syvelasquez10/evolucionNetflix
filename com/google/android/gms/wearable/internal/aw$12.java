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
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.DataApi$GetFdForAssetResult;
import android.net.Uri;
import com.google.android.gms.wearable.DataApi$DataItemResult;
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
import com.google.android.gms.wearable.DataApi$DataListener;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import com.google.android.gms.common.internal.d;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.MessageApi$MessageListener;
import com.google.android.gms.common.api.BaseImplementation$b;

class aw$12 extends a
{
    final /* synthetic */ aw avI;
    final /* synthetic */ BaseImplementation$b avK;
    final /* synthetic */ MessageApi$MessageListener avv;
    
    aw$12(final aw avI, final MessageApi$MessageListener avv, final BaseImplementation$b avK) {
        this.avI = avI;
        this.avv = avv;
        this.avK = avK;
    }
    
    @Override
    public void a(final Status status) {
        Label_0034: {
            if (status.isSuccess()) {
                break Label_0034;
            }
            synchronized (this.avI.avG) {
                this.avI.avG.remove(this.avv);
                // monitorexit(aw.c(this.avI))
                this.avK.b(status);
            }
        }
    }
}
