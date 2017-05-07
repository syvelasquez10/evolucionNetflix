// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.images;

import java.util.concurrent.CountDownLatch;
import android.content.res.Configuration;
import android.content.ComponentCallbacks2;
import android.os.SystemClock;
import com.google.android.gms.internal.ja;
import android.app.ActivityManager;
import android.graphics.drawable.Drawable;
import android.os.ParcelFileDescriptor;
import android.os.Bundle;
import android.os.Parcelable;
import android.content.Intent;
import java.util.ArrayList;
import android.os.ResultReceiver;
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
    private final b Kq;
    private final iz Kr;
    private final Map<com.google.android.gms.common.images.a, ImageReceiver> Ks;
    private final Map<Uri, ImageReceiver> Kt;
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
            this.Kq = new b(this.mContext);
            if (kc.hE()) {
                this.gH();
            }
        }
        else {
            this.Kq = null;
        }
        this.Kr = new iz();
        this.Ks = new HashMap<com.google.android.gms.common.images.a, ImageReceiver>();
        this.Kt = new HashMap<Uri, ImageReceiver>();
        this.Ku = new HashMap<Uri, Long>();
    }
    
    private Bitmap a(final com.google.android.gms.common.images.a.a a) {
        if (this.Kq == null) {
            return null;
        }
        return this.Kq.get(a);
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
        this.mContext.registerComponentCallbacks((ComponentCallbacks)new e(this.Kq));
    }
    
    public void a(final com.google.android.gms.common.images.a a) {
        com.google.android.gms.common.internal.a.aT("ImageManager.loadImage() must be called in the main thread");
        new d(a).run();
    }
    
    public void loadImage(final ImageView imageView, final int n) {
        this.a(new com.google.android.gms.common.images.a.b(imageView, n));
    }
    
    public void loadImage(final ImageView imageView, final Uri uri) {
        this.a(new com.google.android.gms.common.images.a.b(imageView, uri));
    }
    
    public void loadImage(final ImageView imageView, final Uri uri, final int n) {
        final com.google.android.gms.common.images.a.b b = new com.google.android.gms.common.images.a.b(imageView, uri);
        b.aw(n);
        this.a(b);
    }
    
    public void loadImage(final OnImageLoadedListener onImageLoadedListener, final Uri uri) {
        this.a(new com.google.android.gms.common.images.a.c(onImageLoadedListener, uri));
    }
    
    public void loadImage(final OnImageLoadedListener onImageLoadedListener, final Uri uri, final int n) {
        final com.google.android.gms.common.images.a.c c = new com.google.android.gms.common.images.a.c(onImageLoadedListener, uri);
        c.aw(n);
        this.a(c);
    }
    
    private final class ImageReceiver extends ResultReceiver
    {
        private final ArrayList<com.google.android.gms.common.images.a> Kv;
        private final Uri mUri;
        
        ImageReceiver(final Uri mUri) {
            super(new Handler(Looper.getMainLooper()));
            this.mUri = mUri;
            this.Kv = new ArrayList<com.google.android.gms.common.images.a>();
        }
        
        public void b(final com.google.android.gms.common.images.a a) {
            com.google.android.gms.common.internal.a.aT("ImageReceiver.addImageRequest() must be called in the main thread");
            this.Kv.add(a);
        }
        
        public void c(final com.google.android.gms.common.images.a a) {
            com.google.android.gms.common.internal.a.aT("ImageReceiver.removeImageRequest() must be called in the main thread");
            this.Kv.remove(a);
        }
        
        public void gK() {
            final Intent intent = new Intent("com.google.android.gms.common.images.LOAD_IMAGE");
            intent.putExtra("com.google.android.gms.extras.uri", (Parcelable)this.mUri);
            intent.putExtra("com.google.android.gms.extras.resultReceiver", (Parcelable)this);
            intent.putExtra("com.google.android.gms.extras.priority", 3);
            ImageManager.this.mContext.sendBroadcast(intent);
        }
        
        public void onReceiveResult(final int n, final Bundle bundle) {
            ImageManager.this.Kp.execute(new c(this.mUri, (ParcelFileDescriptor)bundle.getParcelable("com.google.android.gms.extra.fileDescriptor")));
        }
    }
    
    public interface OnImageLoadedListener
    {
        void onImageLoaded(final Uri p0, final Drawable p1, final boolean p2);
    }
    
    private static final class a
    {
        static int a(final ActivityManager activityManager) {
            return activityManager.getLargeMemoryClass();
        }
    }
    
    private static final class b extends ja<com.google.android.gms.common.images.a.a, Bitmap>
    {
        public b(final Context context) {
            super(I(context));
        }
        
        private static int I(final Context context) {
            final ActivityManager activityManager = (ActivityManager)context.getSystemService("activity");
            boolean b;
            if ((context.getApplicationInfo().flags & 0x100000) != 0x0) {
                b = true;
            }
            else {
                b = false;
            }
            int n;
            if (b && kc.hB()) {
                n = a.a(activityManager);
            }
            else {
                n = activityManager.getMemoryClass();
            }
            return (int)(n * 1048576 * 0.33f);
        }
        
        protected int a(final com.google.android.gms.common.images.a.a a, final Bitmap bitmap) {
            return bitmap.getHeight() * bitmap.getRowBytes();
        }
        
        protected void a(final boolean b, final com.google.android.gms.common.images.a.a a, final Bitmap bitmap, final Bitmap bitmap2) {
            super.entryRemoved(b, a, bitmap, bitmap2);
        }
    }
    
    private final class c implements Runnable
    {
        private final ParcelFileDescriptor Kx;
        private final Uri mUri;
        
        public c(final Uri mUri, final ParcelFileDescriptor kx) {
            this.mUri = mUri;
            this.Kx = kx;
        }
        
        @Override
        public void run() {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: ldc             "LoadBitmapFromDiskRunnable can't be executed in the main thread"
            //     2: invokestatic    com/google/android/gms/common/internal/a.aU:(Ljava/lang/String;)V
            //     5: iconst_0       
            //     6: istore_1       
            //     7: iconst_0       
            //     8: istore_2       
            //     9: aconst_null    
            //    10: astore_3       
            //    11: aconst_null    
            //    12: astore          4
            //    14: aload_0        
            //    15: getfield        com/google/android/gms/common/images/ImageManager$c.Kx:Landroid/os/ParcelFileDescriptor;
            //    18: ifnull          41
            //    21: aload_0        
            //    22: getfield        com/google/android/gms/common/images/ImageManager$c.Kx:Landroid/os/ParcelFileDescriptor;
            //    25: invokevirtual   android/os/ParcelFileDescriptor.getFileDescriptor:()Ljava/io/FileDescriptor;
            //    28: invokestatic    android/graphics/BitmapFactory.decodeFileDescriptor:(Ljava/io/FileDescriptor;)Landroid/graphics/Bitmap;
            //    31: astore_3       
            //    32: iload_2        
            //    33: istore_1       
            //    34: aload_0        
            //    35: getfield        com/google/android/gms/common/images/ImageManager$c.Kx:Landroid/os/ParcelFileDescriptor;
            //    38: invokevirtual   android/os/ParcelFileDescriptor.close:()V
            //    41: new             Ljava/util/concurrent/CountDownLatch;
            //    44: dup            
            //    45: iconst_1       
            //    46: invokespecial   java/util/concurrent/CountDownLatch.<init>:(I)V
            //    49: astore          4
            //    51: aload_0        
            //    52: getfield        com/google/android/gms/common/images/ImageManager$c.Kw:Lcom/google/android/gms/common/images/ImageManager;
            //    55: invokestatic    com/google/android/gms/common/images/ImageManager.g:(Lcom/google/android/gms/common/images/ImageManager;)Landroid/os/Handler;
            //    58: new             Lcom/google/android/gms/common/images/ImageManager$f;
            //    61: dup            
            //    62: aload_0        
            //    63: getfield        com/google/android/gms/common/images/ImageManager$c.Kw:Lcom/google/android/gms/common/images/ImageManager;
            //    66: aload_0        
            //    67: getfield        com/google/android/gms/common/images/ImageManager$c.mUri:Landroid/net/Uri;
            //    70: aload_3        
            //    71: iload_1        
            //    72: aload           4
            //    74: invokespecial   com/google/android/gms/common/images/ImageManager$f.<init>:(Lcom/google/android/gms/common/images/ImageManager;Landroid/net/Uri;Landroid/graphics/Bitmap;ZLjava/util/concurrent/CountDownLatch;)V
            //    77: invokevirtual   android/os/Handler.post:(Ljava/lang/Runnable;)Z
            //    80: pop            
            //    81: aload           4
            //    83: invokevirtual   java/util/concurrent/CountDownLatch.await:()V
            //    86: return         
            //    87: astore_3       
            //    88: ldc             "ImageManager"
            //    90: new             Ljava/lang/StringBuilder;
            //    93: dup            
            //    94: invokespecial   java/lang/StringBuilder.<init>:()V
            //    97: ldc             "OOM while loading bitmap for uri: "
            //    99: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   102: aload_0        
            //   103: getfield        com/google/android/gms/common/images/ImageManager$c.mUri:Landroid/net/Uri;
            //   106: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
            //   109: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   112: aload_3        
            //   113: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
            //   116: pop            
            //   117: iconst_1       
            //   118: istore_1       
            //   119: aload           4
            //   121: astore_3       
            //   122: goto            34
            //   125: astore          4
            //   127: ldc             "ImageManager"
            //   129: ldc             "closed failed"
            //   131: aload           4
            //   133: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
            //   136: pop            
            //   137: goto            41
            //   140: astore_3       
            //   141: ldc             "ImageManager"
            //   143: new             Ljava/lang/StringBuilder;
            //   146: dup            
            //   147: invokespecial   java/lang/StringBuilder.<init>:()V
            //   150: ldc             "Latch interrupted while posting "
            //   152: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   155: aload_0        
            //   156: getfield        com/google/android/gms/common/images/ImageManager$c.mUri:Landroid/net/Uri;
            //   159: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
            //   162: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   165: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
            //   168: pop            
            //   169: return         
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                            
            //  -----  -----  -----  -----  --------------------------------
            //  21     32     87     125    Ljava/lang/OutOfMemoryError;
            //  34     41     125    140    Ljava/io/IOException;
            //  81     86     140    170    Ljava/lang/InterruptedException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IndexOutOfBoundsException: Index: 86, Size: 86
            //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
            //     at java.util.ArrayList.get(ArrayList.java:429)
            //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
            //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
    }
    
    private final class d implements Runnable
    {
        private final com.google.android.gms.common.images.a Ky;
        
        public d(final com.google.android.gms.common.images.a ky) {
            this.Ky = ky;
        }
        
        @Override
        public void run() {
            com.google.android.gms.common.internal.a.aT("LoadImageRunnable must be executed on the main thread");
            final ImageReceiver imageReceiver = ImageManager.this.Ks.get(this.Ky);
            if (imageReceiver != null) {
                ImageManager.this.Ks.remove(this.Ky);
                imageReceiver.c(this.Ky);
            }
            final com.google.android.gms.common.images.a.a ka = this.Ky.KA;
            if (ka.uri == null) {
                this.Ky.a(ImageManager.this.mContext, ImageManager.this.Kr, true);
                return;
            }
            final Bitmap a = ImageManager.this.a(ka);
            if (a != null) {
                this.Ky.a(ImageManager.this.mContext, a, true);
                return;
            }
            final Long n = ImageManager.this.Ku.get(ka.uri);
            if (n != null) {
                if (SystemClock.elapsedRealtime() - n < 3600000L) {
                    this.Ky.a(ImageManager.this.mContext, ImageManager.this.Kr, true);
                    return;
                }
                ImageManager.this.Ku.remove(ka.uri);
            }
            this.Ky.a(ImageManager.this.mContext, ImageManager.this.Kr);
            ResultReceiver resultReceiver;
            if ((resultReceiver = ImageManager.this.Kt.get(ka.uri)) == null) {
                resultReceiver = new ImageReceiver(ka.uri);
                ImageManager.this.Kt.put(ka.uri, resultReceiver);
            }
            ((ImageReceiver)resultReceiver).b(this.Ky);
            if (!(this.Ky instanceof com.google.android.gms.common.images.a.c)) {
                ImageManager.this.Ks.put(this.Ky, resultReceiver);
            }
            synchronized (ImageManager.Kl) {
                if (!ImageManager.Km.contains(ka.uri)) {
                    ImageManager.Km.add(ka.uri);
                    ((ImageReceiver)resultReceiver).gK();
                }
            }
        }
    }
    
    private static final class e implements ComponentCallbacks2
    {
        private final b Kq;
        
        public e(final b kq) {
            this.Kq = kq;
        }
        
        public void onConfigurationChanged(final Configuration configuration) {
        }
        
        public void onLowMemory() {
            this.Kq.evictAll();
        }
        
        public void onTrimMemory(final int n) {
            if (n >= 60) {
                this.Kq.evictAll();
            }
            else if (n >= 20) {
                this.Kq.trimToSize(this.Kq.size() / 2);
            }
        }
    }
    
    private final class f implements Runnable
    {
        private boolean Kz;
        private final Bitmap mBitmap;
        private final Uri mUri;
        private final CountDownLatch mg;
        
        public f(final Uri mUri, final Bitmap mBitmap, final boolean kz, final CountDownLatch mg) {
            this.mUri = mUri;
            this.mBitmap = mBitmap;
            this.Kz = kz;
            this.mg = mg;
        }
        
        private void a(final ImageReceiver imageReceiver, final boolean b) {
            final ArrayList a = imageReceiver.Kv;
            for (int size = a.size(), i = 0; i < size; ++i) {
                final com.google.android.gms.common.images.a a2 = a.get(i);
                if (b) {
                    a2.a(ImageManager.this.mContext, this.mBitmap, false);
                }
                else {
                    ImageManager.this.Ku.put(this.mUri, SystemClock.elapsedRealtime());
                    a2.a(ImageManager.this.mContext, ImageManager.this.Kr, false);
                }
                if (!(a2 instanceof com.google.android.gms.common.images.a.c)) {
                    ImageManager.this.Ks.remove(a2);
                }
            }
        }
        
        @Override
        public void run() {
            com.google.android.gms.common.internal.a.aT("OnBitmapLoadedRunnable must be executed in the main thread");
            final boolean b = this.mBitmap != null;
            if (ImageManager.this.Kq != null) {
                if (this.Kz) {
                    ImageManager.this.Kq.evictAll();
                    System.gc();
                    this.Kz = false;
                    ImageManager.this.mHandler.post((Runnable)this);
                    return;
                }
                if (b) {
                    ImageManager.this.Kq.put(new com.google.android.gms.common.images.a.a(this.mUri), this.mBitmap);
                }
            }
            final ImageReceiver imageReceiver = ImageManager.this.Kt.remove(this.mUri);
            if (imageReceiver != null) {
                this.a(imageReceiver, b);
            }
            this.mg.countDown();
            synchronized (ImageManager.Kl) {
                ImageManager.Km.remove(this.mUri);
            }
        }
    }
}
