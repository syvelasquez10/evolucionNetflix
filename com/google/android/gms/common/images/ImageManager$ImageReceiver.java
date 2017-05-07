// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.images;

import android.widget.ImageView;
import android.content.ComponentCallbacks;
import android.graphics.Bitmap;
import java.util.HashMap;
import com.google.android.gms.internal.kc;
import java.util.concurrent.Executors;
import android.content.Context;
import java.util.Map;
import com.google.android.gms.internal.iz;
import java.util.concurrent.ExecutorService;
import java.util.HashSet;
import android.os.ParcelFileDescriptor;
import android.os.Bundle;
import android.os.Parcelable;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.net.Uri;
import java.util.ArrayList;
import android.os.ResultReceiver;

final class ImageManager$ImageReceiver extends ResultReceiver
{
    private final ArrayList<a> Kv;
    final /* synthetic */ ImageManager Kw;
    private final Uri mUri;
    
    ImageManager$ImageReceiver(final ImageManager kw, final Uri mUri) {
        this.Kw = kw;
        super(new Handler(Looper.getMainLooper()));
        this.mUri = mUri;
        this.Kv = new ArrayList<a>();
    }
    
    public void b(final a a) {
        com.google.android.gms.common.internal.a.aT("ImageReceiver.addImageRequest() must be called in the main thread");
        this.Kv.add(a);
    }
    
    public void c(final a a) {
        com.google.android.gms.common.internal.a.aT("ImageReceiver.removeImageRequest() must be called in the main thread");
        this.Kv.remove(a);
    }
    
    public void gK() {
        final Intent intent = new Intent("com.google.android.gms.common.images.LOAD_IMAGE");
        intent.putExtra("com.google.android.gms.extras.uri", (Parcelable)this.mUri);
        intent.putExtra("com.google.android.gms.extras.resultReceiver", (Parcelable)this);
        intent.putExtra("com.google.android.gms.extras.priority", 3);
        this.Kw.mContext.sendBroadcast(intent);
    }
    
    public void onReceiveResult(final int n, final Bundle bundle) {
        this.Kw.Kp.execute(new ImageManager$c(this.Kw, this.mUri, (ParcelFileDescriptor)bundle.getParcelable("com.google.android.gms.extra.fileDescriptor")));
    }
}
