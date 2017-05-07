// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.images;

import java.util.concurrent.CountDownLatch;
import android.content.res.Configuration;
import android.content.ComponentCallbacks2;
import com.google.android.gms.internal.ek;
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
import com.google.android.gms.internal.ds;
import android.graphics.Bitmap;
import java.util.HashMap;
import com.google.android.gms.internal.fg;
import java.util.concurrent.Executors;
import android.os.Looper;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import android.os.Handler;
import android.content.Context;
import android.net.Uri;
import java.util.HashSet;

public final class ImageManager
{
    private static final Object ob;
    private static HashSet<Uri> oc;
    private static ImageManager od;
    private static ImageManager oe;
    private final Context mContext;
    private final Handler mHandler;
    private final ExecutorService of;
    private final b og;
    private final Map<com.google.android.gms.common.images.a, ImageReceiver> oh;
    private final Map<Uri, ImageReceiver> oi;
    
    static {
        ob = new Object();
        ImageManager.oc = new HashSet<Uri>();
    }
    
    private ImageManager(final Context context, final boolean b) {
        this.mContext = context.getApplicationContext();
        this.mHandler = new Handler(Looper.getMainLooper());
        this.of = Executors.newFixedThreadPool(4);
        if (b) {
            this.og = new b(this.mContext);
            if (fg.cG()) {
                this.bz();
            }
        }
        else {
            this.og = null;
        }
        this.oh = new HashMap<com.google.android.gms.common.images.a, ImageReceiver>();
        this.oi = new HashMap<Uri, ImageReceiver>();
    }
    
    private Bitmap a(final com.google.android.gms.common.images.a.a a) {
        if (this.og == null) {
            return null;
        }
        return this.og.get(a);
    }
    
    public static ImageManager a(final Context context, final boolean b) {
        if (b) {
            if (ImageManager.oe == null) {
                ImageManager.oe = new ImageManager(context, true);
            }
            return ImageManager.oe;
        }
        if (ImageManager.od == null) {
            ImageManager.od = new ImageManager(context, false);
        }
        return ImageManager.od;
    }
    
    private boolean b(final com.google.android.gms.common.images.a a) {
        ds.N("ImageManager.cleanupHashMaps() must be called in the main thread");
        if (a.os == 1) {
            return true;
        }
        final ImageReceiver imageReceiver = this.oh.get(a);
        if (imageReceiver == null) {
            return true;
        }
        if (imageReceiver.ok) {
            return false;
        }
        this.oh.remove(a);
        imageReceiver.d(a);
        return true;
    }
    
    private void bz() {
        this.mContext.registerComponentCallbacks((ComponentCallbacks)new e(this.og));
    }
    
    public static ImageManager create(final Context context) {
        return a(context, false);
    }
    
    public void a(final com.google.android.gms.common.images.a a) {
        ds.N("ImageManager.loadImage() must be called in the main thread");
        final boolean b = this.b(a);
        final d d = new d(a);
        if (b) {
            d.run();
            return;
        }
        this.mHandler.post((Runnable)d);
    }
    
    public void loadImage(final ImageView imageView, final int n) {
        final com.google.android.gms.common.images.a a = new com.google.android.gms.common.images.a(n);
        a.a(imageView);
        this.a(a);
    }
    
    public void loadImage(final ImageView imageView, final Uri uri) {
        final com.google.android.gms.common.images.a a = new com.google.android.gms.common.images.a(uri);
        a.a(imageView);
        this.a(a);
    }
    
    public void loadImage(final ImageView imageView, final Uri uri, final int n) {
        final com.google.android.gms.common.images.a a = new com.google.android.gms.common.images.a(uri);
        a.F(n);
        a.a(imageView);
        this.a(a);
    }
    
    public void loadImage(final OnImageLoadedListener onImageLoadedListener, final Uri uri) {
        final com.google.android.gms.common.images.a a = new com.google.android.gms.common.images.a(uri);
        a.a(onImageLoadedListener);
        this.a(a);
    }
    
    public void loadImage(final OnImageLoadedListener onImageLoadedListener, final Uri uri, final int n) {
        final com.google.android.gms.common.images.a a = new com.google.android.gms.common.images.a(uri);
        a.F(n);
        a.a(onImageLoadedListener);
        this.a(a);
    }
    
    private final class ImageReceiver extends ResultReceiver
    {
        private final Uri mUri;
        private final ArrayList<com.google.android.gms.common.images.a> oj;
        boolean ok;
        
        ImageReceiver(final Uri mUri) {
            super(new Handler(Looper.getMainLooper()));
            this.ok = false;
            this.mUri = mUri;
            this.oj = new ArrayList<com.google.android.gms.common.images.a>();
        }
        
        public void bB() {
            final Intent intent = new Intent("com.google.android.gms.common.images.LOAD_IMAGE");
            intent.putExtra("com.google.android.gms.extras.uri", (Parcelable)this.mUri);
            intent.putExtra("com.google.android.gms.extras.resultReceiver", (Parcelable)this);
            intent.putExtra("com.google.android.gms.extras.priority", 3);
            ImageManager.this.mContext.sendBroadcast(intent);
        }
        
        public void c(final com.google.android.gms.common.images.a a) {
            ds.a(!this.ok, "Cannot add an ImageRequest when mHandlingRequests is true");
            ds.N("ImageReceiver.addImageRequest() must be called in the main thread");
            this.oj.add(a);
        }
        
        public void d(final com.google.android.gms.common.images.a a) {
            ds.a(!this.ok, "Cannot remove an ImageRequest when mHandlingRequests is true");
            ds.N("ImageReceiver.removeImageRequest() must be called in the main thread");
            this.oj.remove(a);
        }
        
        public void onReceiveResult(final int n, final Bundle bundle) {
            ImageManager.this.of.execute(new c(this.mUri, (ParcelFileDescriptor)bundle.getParcelable("com.google.android.gms.extra.fileDescriptor")));
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
    
    private static final class b extends ek<com.google.android.gms.common.images.a.a, Bitmap>
    {
        public b(final Context context) {
            super(q(context));
        }
        
        private static int q(final Context context) {
            final ActivityManager activityManager = (ActivityManager)context.getSystemService("activity");
            boolean b;
            if ((context.getApplicationInfo().flags & 0x100000) != 0x0) {
                b = true;
            }
            else {
                b = false;
            }
            int n;
            if (b && fg.cD()) {
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
        private final Uri mUri;
        private final ParcelFileDescriptor om;
        
        public c(final Uri mUri, final ParcelFileDescriptor om) {
            this.mUri = mUri;
            this.om = om;
        }
        
        @Override
        public void run() {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: ldc             "LoadBitmapFromDiskRunnable can't be executed in the main thread"
            //     2: invokestatic    com/google/android/gms/internal/ds.O:(Ljava/lang/String;)V
            //     5: iconst_0       
            //     6: istore_1       
            //     7: iconst_0       
            //     8: istore_2       
            //     9: aconst_null    
            //    10: astore_3       
            //    11: aconst_null    
            //    12: astore          4
            //    14: aload_0        
            //    15: getfield        com/google/android/gms/common/images/ImageManager$c.om:Landroid/os/ParcelFileDescriptor;
            //    18: ifnull          41
            //    21: aload_0        
            //    22: getfield        com/google/android/gms/common/images/ImageManager$c.om:Landroid/os/ParcelFileDescriptor;
            //    25: invokevirtual   android/os/ParcelFileDescriptor.getFileDescriptor:()Ljava/io/FileDescriptor;
            //    28: invokestatic    android/graphics/BitmapFactory.decodeFileDescriptor:(Ljava/io/FileDescriptor;)Landroid/graphics/Bitmap;
            //    31: astore_3       
            //    32: iload_2        
            //    33: istore_1       
            //    34: aload_0        
            //    35: getfield        com/google/android/gms/common/images/ImageManager$c.om:Landroid/os/ParcelFileDescriptor;
            //    38: invokevirtual   android/os/ParcelFileDescriptor.close:()V
            //    41: new             Ljava/util/concurrent/CountDownLatch;
            //    44: dup            
            //    45: iconst_1       
            //    46: invokespecial   java/util/concurrent/CountDownLatch.<init>:(I)V
            //    49: astore          4
            //    51: aload_0        
            //    52: getfield        com/google/android/gms/common/images/ImageManager$c.ol:Lcom/google/android/gms/common/images/ImageManager;
            //    55: invokestatic    com/google/android/gms/common/images/ImageManager.e:(Lcom/google/android/gms/common/images/ImageManager;)Landroid/os/Handler;
            //    58: new             Lcom/google/android/gms/common/images/ImageManager$f;
            //    61: dup            
            //    62: aload_0        
            //    63: getfield        com/google/android/gms/common/images/ImageManager$c.ol:Lcom/google/android/gms/common/images/ImageManager;
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
        private final com.google.android.gms.common.images.a on;
        
        public d(final com.google.android.gms.common.images.a on) {
            this.on = on;
        }
        
        @Override
        public void run() {
            ds.N("LoadImageRunnable must be executed on the main thread");
            ImageManager.this.b(this.on);
            final com.google.android.gms.common.images.a.a op = this.on.op;
            if (op.uri == null) {
                this.on.b(ImageManager.this.mContext, true);
                return;
            }
            final Bitmap a = ImageManager.this.a(op);
            if (a != null) {
                this.on.a(ImageManager.this.mContext, a, true);
                return;
            }
            this.on.r(ImageManager.this.mContext);
            ResultReceiver resultReceiver;
            if ((resultReceiver = ImageManager.this.oi.get(op.uri)) == null) {
                resultReceiver = new ImageReceiver(op.uri);
                ImageManager.this.oi.put(op.uri, resultReceiver);
            }
            ((ImageReceiver)resultReceiver).c(this.on);
            if (this.on.os != 1) {
                ImageManager.this.oh.put(this.on, resultReceiver);
            }
            synchronized (ImageManager.ob) {
                if (!ImageManager.oc.contains(op.uri)) {
                    ImageManager.oc.add(op.uri);
                    ((ImageReceiver)resultReceiver).bB();
                }
            }
        }
    }
    
    private static final class e implements ComponentCallbacks2
    {
        private final b og;
        
        public e(final b og) {
            this.og = og;
        }
        
        public void onConfigurationChanged(final Configuration configuration) {
        }
        
        public void onLowMemory() {
            this.og.evictAll();
        }
        
        public void onTrimMemory(final int n) {
            if (n >= 60) {
                this.og.evictAll();
            }
            else if (n >= 20) {
                this.og.trimToSize(this.og.size() / 2);
            }
        }
    }
    
    private final class f implements Runnable
    {
        private final Bitmap mBitmap;
        private final Uri mUri;
        private final CountDownLatch mX;
        private boolean oo;
        
        public f(final Uri mUri, final Bitmap mBitmap, final boolean oo, final CountDownLatch mx) {
            this.mUri = mUri;
            this.mBitmap = mBitmap;
            this.oo = oo;
            this.mX = mx;
        }
        
        private void a(final ImageReceiver imageReceiver, final boolean b) {
            imageReceiver.ok = true;
            final ArrayList a = imageReceiver.oj;
            for (int size = a.size(), i = 0; i < size; ++i) {
                final com.google.android.gms.common.images.a a2 = a.get(i);
                if (b) {
                    a2.a(ImageManager.this.mContext, this.mBitmap, false);
                }
                else {
                    a2.b(ImageManager.this.mContext, false);
                }
                if (a2.os != 1) {
                    ImageManager.this.oh.remove(a2);
                }
            }
            imageReceiver.ok = false;
        }
        
        @Override
        public void run() {
            ds.N("OnBitmapLoadedRunnable must be executed in the main thread");
            final boolean b = this.mBitmap != null;
            if (ImageManager.this.og != null) {
                if (this.oo) {
                    ImageManager.this.og.evictAll();
                    System.gc();
                    this.oo = false;
                    ImageManager.this.mHandler.post((Runnable)this);
                    return;
                }
                if (b) {
                    ImageManager.this.og.put(new com.google.android.gms.common.images.a.a(this.mUri), this.mBitmap);
                }
            }
            final ImageReceiver imageReceiver = ImageManager.this.oi.remove(this.mUri);
            if (imageReceiver != null) {
                this.a(imageReceiver, b);
            }
            this.mX.countDown();
            synchronized (ImageManager.ob) {
                ImageManager.oc.remove(this.mUri);
            }
        }
    }
}
