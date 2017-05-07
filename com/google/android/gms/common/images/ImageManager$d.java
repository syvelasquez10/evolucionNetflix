// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.images;

import android.widget.ImageView;
import android.content.ComponentCallbacks;
import java.util.HashMap;
import com.google.android.gms.internal.kc;
import java.util.concurrent.Executors;
import android.os.Looper;
import android.os.Handler;
import android.content.Context;
import java.util.Map;
import com.google.android.gms.internal.iz;
import java.util.concurrent.ExecutorService;
import android.net.Uri;
import java.util.HashSet;
import android.graphics.Bitmap;
import android.os.SystemClock;

final class ImageManager$d implements Runnable
{
    final /* synthetic */ ImageManager Kw;
    private final a Ky;
    
    public ImageManager$d(final ImageManager kw, final a ky) {
        this.Kw = kw;
        this.Ky = ky;
    }
    
    @Override
    public void run() {
        com.google.android.gms.common.internal.a.aT("LoadImageRunnable must be executed on the main thread");
        final ImageManager$ImageReceiver imageManager$ImageReceiver = this.Kw.Ks.get(this.Ky);
        if (imageManager$ImageReceiver != null) {
            this.Kw.Ks.remove(this.Ky);
            imageManager$ImageReceiver.c(this.Ky);
        }
        final a$a ka = this.Ky.KA;
        if (ka.uri == null) {
            this.Ky.a(this.Kw.mContext, this.Kw.Kr, true);
            return;
        }
        final Bitmap a = this.Kw.a(ka);
        if (a != null) {
            this.Ky.a(this.Kw.mContext, a, true);
            return;
        }
        final Long n = this.Kw.Ku.get(ka.uri);
        if (n != null) {
            if (SystemClock.elapsedRealtime() - n < 3600000L) {
                this.Ky.a(this.Kw.mContext, this.Kw.Kr, true);
                return;
            }
            this.Kw.Ku.remove(ka.uri);
        }
        this.Ky.a(this.Kw.mContext, this.Kw.Kr);
        ImageManager$ImageReceiver imageManager$ImageReceiver2;
        if ((imageManager$ImageReceiver2 = this.Kw.Kt.get(ka.uri)) == null) {
            imageManager$ImageReceiver2 = new ImageManager$ImageReceiver(this.Kw, ka.uri);
            this.Kw.Kt.put(ka.uri, imageManager$ImageReceiver2);
        }
        imageManager$ImageReceiver2.b(this.Ky);
        if (!(this.Ky instanceof a$c)) {
            this.Kw.Ks.put(this.Ky, imageManager$ImageReceiver2);
        }
        synchronized (ImageManager.Kl) {
            if (!ImageManager.Km.contains(ka.uri)) {
                ImageManager.Km.add(ka.uri);
                imageManager$ImageReceiver2.gK();
            }
        }
    }
}
