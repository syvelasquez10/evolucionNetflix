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
import android.os.Looper;
import android.os.Handler;
import android.content.Context;
import java.util.Map;
import com.google.android.gms.internal.iz;
import java.util.concurrent.ExecutorService;
import android.net.Uri;
import java.util.HashSet;

public final class ImageManager
{
    private static final Object Kl;
    private static HashSet<Uri> Km;
    private static ImageManager Kn;
    private static ImageManager Ko;
    private final ExecutorService Kp;
    private final ImageManager$b Kq;
    private final iz Kr;
    private final Map<a, ImageManager$ImageReceiver> Ks;
    private final Map<Uri, ImageManager$ImageReceiver> Kt;
    private final Map<Uri, Long> Ku;
    private final Context mContext;
    private final Handler mHandler;
    
    static {
        Kl = new Object();
        ImageManager.Km = new HashSet<Uri>();
    }
    
    private ImageManager(final Context context, final boolean b) {
        this.mContext = context.getApplicationContext();
        this.mHandler = new Handler(Looper.getMainLooper());
        this.Kp = Executors.newFixedThreadPool(4);
        if (b) {
            this.Kq = new ImageManager$b(this.mContext);
            if (kc.hE()) {
                this.gH();
            }
        }
        else {
            this.Kq = null;
        }
        this.Kr = new iz();
        this.Ks = new HashMap<a, ImageManager$ImageReceiver>();
        this.Kt = new HashMap<Uri, ImageManager$ImageReceiver>();
        this.Ku = new HashMap<Uri, Long>();
    }
    
    private Bitmap a(final a$a a$a) {
        if (this.Kq == null) {
            return null;
        }
        return this.Kq.get(a$a);
    }
    
    public static ImageManager c(final Context context, final boolean b) {
        if (b) {
            if (ImageManager.Ko == null) {
                ImageManager.Ko = new ImageManager(context, true);
            }
            return ImageManager.Ko;
        }
        if (ImageManager.Kn == null) {
            ImageManager.Kn = new ImageManager(context, false);
        }
        return ImageManager.Kn;
    }
    
    public static ImageManager create(final Context context) {
        return c(context, false);
    }
    
    private void gH() {
        this.mContext.registerComponentCallbacks((ComponentCallbacks)new ImageManager$e(this.Kq));
    }
    
    public void a(final a a) {
        com.google.android.gms.common.internal.a.aT("ImageManager.loadImage() must be called in the main thread");
        new ImageManager$d(this, a).run();
    }
    
    public void loadImage(final ImageView imageView, final int n) {
        this.a(new a$b(imageView, n));
    }
    
    public void loadImage(final ImageView imageView, final Uri uri) {
        this.a(new a$b(imageView, uri));
    }
    
    public void loadImage(final ImageView imageView, final Uri uri, final int n) {
        final a$b a$b = new a$b(imageView, uri);
        a$b.aw(n);
        this.a(a$b);
    }
    
    public void loadImage(final ImageManager$OnImageLoadedListener imageManager$OnImageLoadedListener, final Uri uri) {
        this.a(new a$c(imageManager$OnImageLoadedListener, uri));
    }
    
    public void loadImage(final ImageManager$OnImageLoadedListener imageManager$OnImageLoadedListener, final Uri uri, final int n) {
        final a$c a$c = new a$c(imageManager$OnImageLoadedListener, uri);
        a$c.aw(n);
        this.a(a$c);
    }
}
