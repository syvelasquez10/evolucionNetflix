// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.images;

import java.util.concurrent.CountDownLatch;
import android.content.res.Configuration;
import android.content.ComponentCallbacks2;
import com.google.android.gms.internal.fu;
import android.app.ActivityManager;
import android.graphics.drawable.Drawable;
import android.os.ParcelFileDescriptor;
import android.os.Bundle;
import android.os.Parcelable;
import android.content.Intent;
import java.util.ArrayList;
import android.os.ResultReceiver;
import android.widget.ImageView;
import com.google.android.gms.internal.fb;
import android.content.ComponentCallbacks;
import android.graphics.Bitmap;
import java.util.HashMap;
import com.google.android.gms.internal.gr;
import java.util.concurrent.Executors;
import android.os.Looper;
import android.os.Handler;
import android.content.Context;
import java.util.Map;
import com.google.android.gms.internal.fa;
import java.util.concurrent.ExecutorService;
import android.net.Uri;
import java.util.HashSet;

public final class ImageManager
{
    private static final Object BY;
    private static HashSet<Uri> BZ;
    private static ImageManager Ca;
    private static ImageManager Cb;
    private final ExecutorService Cc;
    private final b Cd;
    private final fa Ce;
    private final Map<com.google.android.gms.common.images.a, ImageReceiver> Cf;
    private final Map<Uri, ImageReceiver> Cg;
    private final Context mContext;
    private final Handler mHandler;
    
    static {
        BY = new Object();
        ImageManager.BZ = new HashSet<Uri>();
    }
    
    private ImageManager(final Context context, final boolean b) {
        this.mContext = context.getApplicationContext();
        this.mHandler = new Handler(Looper.getMainLooper());
        this.Cc = Executors.newFixedThreadPool(4);
        if (b) {
            this.Cd = new b(this.mContext);
            if (gr.fx()) {
                this.ev();
            }
        }
        else {
            this.Cd = null;
        }
        this.Ce = new fa();
        this.Cf = new HashMap<com.google.android.gms.common.images.a, ImageReceiver>();
        this.Cg = new HashMap<Uri, ImageReceiver>();
    }
    
    private Bitmap a(final com.google.android.gms.common.images.a.a a) {
        if (this.Cd == null) {
            return null;
        }
        return this.Cd.get(a);
    }
    
    public static ImageManager a(final Context context, final boolean b) {
        if (b) {
            if (ImageManager.Cb == null) {
                ImageManager.Cb = new ImageManager(context, true);
            }
            return ImageManager.Cb;
        }
        if (ImageManager.Ca == null) {
            ImageManager.Ca = new ImageManager(context, false);
        }
        return ImageManager.Ca;
    }
    
    public static ImageManager create(final Context context) {
        return a(context, false);
    }
    
    private void ev() {
        this.mContext.registerComponentCallbacks((ComponentCallbacks)new e(this.Cd));
    }
    
    public void a(final com.google.android.gms.common.images.a a) {
        fb.aj("ImageManager.loadImage() must be called in the main thread");
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
        b.J(n);
        this.a(b);
    }
    
    public void loadImage(final OnImageLoadedListener onImageLoadedListener, final Uri uri) {
        this.a(new com.google.android.gms.common.images.a.c(onImageLoadedListener, uri));
    }
    
    public void loadImage(final OnImageLoadedListener onImageLoadedListener, final Uri uri, final int n) {
        final com.google.android.gms.common.images.a.c c = new com.google.android.gms.common.images.a.c(onImageLoadedListener, uri);
        c.J(n);
        this.a(c);
    }
    
    private final class ImageReceiver extends ResultReceiver
    {
        private final ArrayList<com.google.android.gms.common.images.a> Ch;
        private final Uri mUri;
        
        ImageReceiver(final Uri mUri) {
            super(new Handler(Looper.getMainLooper()));
            this.mUri = mUri;
            this.Ch = new ArrayList<com.google.android.gms.common.images.a>();
        }
        
        public void b(final com.google.android.gms.common.images.a a) {
            fb.aj("ImageReceiver.addImageRequest() must be called in the main thread");
            this.Ch.add(a);
        }
        
        public void c(final com.google.android.gms.common.images.a a) {
            fb.aj("ImageReceiver.removeImageRequest() must be called in the main thread");
            this.Ch.remove(a);
        }
        
        public void ey() {
            final Intent intent = new Intent("com.google.android.gms.common.images.LOAD_IMAGE");
            intent.putExtra("com.google.android.gms.extras.uri", (Parcelable)this.mUri);
            intent.putExtra("com.google.android.gms.extras.resultReceiver", (Parcelable)this);
            intent.putExtra("com.google.android.gms.extras.priority", 3);
            ImageManager.this.mContext.sendBroadcast(intent);
        }
        
        public void onReceiveResult(final int n, final Bundle bundle) {
            ImageManager.this.Cc.execute(new c(this.mUri, (ParcelFileDescriptor)bundle.getParcelable("com.google.android.gms.extra.fileDescriptor")));
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
    
    private static final class b extends fu<com.google.android.gms.common.images.a.a, Bitmap>
    {
        public b(final Context context) {
            super(w(context));
        }
        
        private static int w(final Context context) {
            final ActivityManager activityManager = (ActivityManager)context.getSystemService("activity");
            boolean b;
            if ((context.getApplicationInfo().flags & 0x100000) != 0x0) {
                b = true;
            }
            else {
                b = false;
            }
            int n;
            if (b && gr.fu()) {
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
        private final ParcelFileDescriptor Cj;
        private final Uri mUri;
        
        public c(final Uri mUri, final ParcelFileDescriptor cj) {
            this.mUri = mUri;
            this.Cj = cj;
        }
        
        @Override
        public void run() {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: ldc             "LoadBitmapFromDiskRunnable can't be executed in the main thread"
            //     2: invokestatic    com/google/android/gms/internal/fb.ak:(Ljava/lang/String;)V
            //     5: iconst_0       
            //     6: istore_1       
            //     7: iconst_0       
            //     8: istore_2       
            //     9: aconst_null    
            //    10: astore_3       
            //    11: aconst_null    
            //    12: astore          4
            //    14: aload_0        
            //    15: getfield        com/google/android/gms/common/images/ImageManager$c.Cj:Landroid/os/ParcelFileDescriptor;
            //    18: ifnull          41
            //    21: aload_0        
            //    22: getfield        com/google/android/gms/common/images/ImageManager$c.Cj:Landroid/os/ParcelFileDescriptor;
            //    25: invokevirtual   android/os/ParcelFileDescriptor.getFileDescriptor:()Ljava/io/FileDescriptor;
            //    28: invokestatic    android/graphics/BitmapFactory.decodeFileDescriptor:(Ljava/io/FileDescriptor;)Landroid/graphics/Bitmap;
            //    31: astore_3       
            //    32: iload_2        
            //    33: istore_1       
            //    34: aload_0        
            //    35: getfield        com/google/android/gms/common/images/ImageManager$c.Cj:Landroid/os/ParcelFileDescriptor;
            //    38: invokevirtual   android/os/ParcelFileDescriptor.close:()V
            //    41: new             Ljava/util/concurrent/CountDownLatch;
            //    44: dup            
            //    45: iconst_1       
            //    46: invokespecial   java/util/concurrent/CountDownLatch.<init>:(I)V
            //    49: astore          4
            //    51: aload_0        
            //    52: getfield        com/google/android/gms/common/images/ImageManager$c.Ci:Lcom/google/android/gms/common/images/ImageManager;
            //    55: invokestatic    com/google/android/gms/common/images/ImageManager.f:(Lcom/google/android/gms/common/images/ImageManager;)Landroid/os/Handler;
            //    58: new             Lcom/google/android/gms/common/images/ImageManager$f;
            //    61: dup            
            //    62: aload_0        
            //    63: getfield        com/google/android/gms/common/images/ImageManager$c.Ci:Lcom/google/android/gms/common/images/ImageManager;
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
        private final com.google.android.gms.common.images.a Ck;
        
        public d(final com.google.android.gms.common.images.a ck) {
            this.Ck = ck;
        }
        
        @Override
        public void run() {
            fb.aj("LoadImageRunnable must be executed on the main thread");
            final ImageReceiver imageReceiver = ImageManager.this.Cf.get(this.Ck);
            if (imageReceiver != null) {
                ImageManager.this.Cf.remove(this.Ck);
                imageReceiver.c(this.Ck);
            }
            final com.google.android.gms.common.images.a.a cm = this.Ck.Cm;
            if (cm.uri == null) {
                this.Ck.a(ImageManager.this.mContext, ImageManager.this.Ce, true);
                return;
            }
            final Bitmap a = ImageManager.this.a(cm);
            if (a != null) {
                this.Ck.a(ImageManager.this.mContext, a, true);
                return;
            }
            this.Ck.a(ImageManager.this.mContext, ImageManager.this.Ce);
            ResultReceiver resultReceiver;
            if ((resultReceiver = ImageManager.this.Cg.get(cm.uri)) == null) {
                resultReceiver = new ImageReceiver(cm.uri);
                ImageManager.this.Cg.put(cm.uri, resultReceiver);
            }
            ((ImageReceiver)resultReceiver).b(this.Ck);
            if (!(this.Ck instanceof com.google.android.gms.common.images.a.c)) {
                ImageManager.this.Cf.put(this.Ck, resultReceiver);
            }
            synchronized (ImageManager.BY) {
                if (!ImageManager.BZ.contains(cm.uri)) {
                    ImageManager.BZ.add(cm.uri);
                    ((ImageReceiver)resultReceiver).ey();
                }
            }
        }
    }
    
    private static final class e implements ComponentCallbacks2
    {
        private final b Cd;
        
        public e(final b cd) {
            this.Cd = cd;
        }
        
        public void onConfigurationChanged(final Configuration configuration) {
        }
        
        public void onLowMemory() {
            this.Cd.evictAll();
        }
        
        public void onTrimMemory(final int n) {
            if (n >= 60) {
                this.Cd.evictAll();
            }
            else if (n >= 20) {
                this.Cd.trimToSize(this.Cd.size() / 2);
            }
        }
    }
    
    private final class f implements Runnable
    {
        private final CountDownLatch AD;
        private boolean Cl;
        private final Bitmap mBitmap;
        private final Uri mUri;
        
        public f(final Uri mUri, final Bitmap mBitmap, final boolean cl, final CountDownLatch ad) {
            this.mUri = mUri;
            this.mBitmap = mBitmap;
            this.Cl = cl;
            this.AD = ad;
        }
        
        private void a(final ImageReceiver imageReceiver, final boolean b) {
            final ArrayList a = imageReceiver.Ch;
            for (int size = a.size(), i = 0; i < size; ++i) {
                final com.google.android.gms.common.images.a a2 = a.get(i);
                if (b) {
                    a2.a(ImageManager.this.mContext, this.mBitmap, false);
                }
                else {
                    a2.a(ImageManager.this.mContext, ImageManager.this.Ce, false);
                }
                if (!(a2 instanceof com.google.android.gms.common.images.a.c)) {
                    ImageManager.this.Cf.remove(a2);
                }
            }
        }
        
        @Override
        public void run() {
            fb.aj("OnBitmapLoadedRunnable must be executed in the main thread");
            final boolean b = this.mBitmap != null;
            if (ImageManager.this.Cd != null) {
                if (this.Cl) {
                    ImageManager.this.Cd.evictAll();
                    System.gc();
                    this.Cl = false;
                    ImageManager.this.mHandler.post((Runnable)this);
                    return;
                }
                if (b) {
                    ImageManager.this.Cd.put(new com.google.android.gms.common.images.a.a(this.mUri), this.mBitmap);
                }
            }
            final ImageReceiver imageReceiver = ImageManager.this.Cg.remove(this.mUri);
            if (imageReceiver != null) {
                this.a(imageReceiver, b);
            }
            this.AD.countDown();
            synchronized (ImageManager.BY) {
                ImageManager.BZ.remove(this.mUri);
            }
        }
    }
}
