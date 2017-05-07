// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.images;

import android.widget.ImageView;
import android.content.ComponentCallbacks;
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
import android.os.ResultReceiver;
import java.util.ArrayList;
import android.os.SystemClock;
import java.util.concurrent.CountDownLatch;
import android.net.Uri;
import android.graphics.Bitmap;

final class ImageManager$f implements Runnable
{
    final /* synthetic */ ImageManager Kw;
    private boolean Kz;
    private final Bitmap mBitmap;
    private final Uri mUri;
    private final CountDownLatch mg;
    
    public ImageManager$f(final ImageManager kw, final Uri mUri, final Bitmap mBitmap, final boolean kz, final CountDownLatch mg) {
        this.Kw = kw;
        this.mUri = mUri;
        this.mBitmap = mBitmap;
        this.Kz = kz;
        this.mg = mg;
    }
    
    private void a(final ImageManager$ImageReceiver imageManager$ImageReceiver, final boolean b) {
        final ArrayList a = imageManager$ImageReceiver.Kv;
        for (int size = a.size(), i = 0; i < size; ++i) {
            final a a2 = a.get(i);
            if (b) {
                a2.a(this.Kw.mContext, this.mBitmap, false);
            }
            else {
                this.Kw.Ku.put(this.mUri, SystemClock.elapsedRealtime());
                a2.a(this.Kw.mContext, this.Kw.Kr, false);
            }
            if (!(a2 instanceof a$c)) {
                this.Kw.Ks.remove(a2);
            }
        }
    }
    
    @Override
    public void run() {
        com.google.android.gms.common.internal.a.aT("OnBitmapLoadedRunnable must be executed in the main thread");
        final boolean b = this.mBitmap != null;
        if (this.Kw.Kq != null) {
            if (this.Kz) {
                this.Kw.Kq.evictAll();
                System.gc();
                this.Kz = false;
                this.Kw.mHandler.post((Runnable)this);
                return;
            }
            if (b) {
                this.Kw.Kq.put(new a$a(this.mUri), this.mBitmap);
            }
        }
        final ImageManager$ImageReceiver imageManager$ImageReceiver = this.Kw.Kt.remove(this.mUri);
        if (imageManager$ImageReceiver != null) {
            this.a(imageManager$ImageReceiver, b);
        }
        this.mg.countDown();
        synchronized (ImageManager.Kl) {
            ImageManager.Km.remove(this.mUri);
        }
    }
}
