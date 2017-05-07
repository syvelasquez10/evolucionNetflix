// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.images;

import com.google.android.gms.internal.iy;
import android.widget.ImageView;
import java.lang.ref.WeakReference;
import com.google.android.gms.common.internal.m;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.Bitmap;
import com.google.android.gms.internal.iw;
import com.google.android.gms.internal.ix;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import com.google.android.gms.internal.iz;
import android.content.Context;
import android.net.Uri;

public abstract class a
{
    final a KA;
    protected int KB;
    protected int KC;
    protected ImageManager.OnImageLoadedListener KD;
    private boolean KE;
    private boolean KF;
    protected int KG;
    
    public a(final Uri uri, final int kc) {
        this.KB = 0;
        this.KC = 0;
        this.KE = true;
        this.KF = false;
        this.KA = new a(uri);
        this.KC = kc;
    }
    
    private Drawable a(final Context context, final iz iz, final int n) {
        final Resources resources = context.getResources();
        if (this.KG > 0) {
            final iz.a a = new iz.a(n, this.KG);
            Drawable drawable;
            if ((drawable = iz.get(a)) == null) {
                final Drawable drawable2 = drawable = resources.getDrawable(n);
                if ((this.KG & 0x1) != 0x0) {
                    drawable = this.a(resources, drawable2);
                }
                iz.put(a, drawable);
            }
            return drawable;
        }
        return resources.getDrawable(n);
    }
    
    protected Drawable a(final Resources resources, final Drawable drawable) {
        return ix.a(resources, drawable);
    }
    
    protected iw a(final Drawable drawable, final Drawable drawable2) {
        Drawable gl;
        if (drawable != null) {
            gl = drawable;
            if (drawable instanceof iw) {
                gl = ((iw)drawable).gL();
            }
        }
        else {
            gl = null;
        }
        return new iw(gl, drawable2);
    }
    
    void a(final Context context, final Bitmap bitmap, final boolean b) {
        a.f(bitmap);
        Bitmap a = bitmap;
        if ((this.KG & 0x1) != 0x0) {
            a = ix.a(bitmap);
        }
        final BitmapDrawable bitmapDrawable = new BitmapDrawable(context.getResources(), a);
        if (this.KD != null) {
            this.KD.onImageLoaded(this.KA.uri, (Drawable)bitmapDrawable, true);
        }
        this.a((Drawable)bitmapDrawable, b, false, true);
    }
    
    void a(final Context context, final iz iz) {
        Drawable a = null;
        if (this.KB != 0) {
            a = this.a(context, iz, this.KB);
        }
        this.a(a, false, true, false);
    }
    
    void a(final Context context, final iz iz, final boolean b) {
        Drawable a = null;
        if (this.KC != 0) {
            a = this.a(context, iz, this.KC);
        }
        if (this.KD != null) {
            this.KD.onImageLoaded(this.KA.uri, a, false);
        }
        this.a(a, b, false, false);
    }
    
    protected abstract void a(final Drawable p0, final boolean p1, final boolean p2, final boolean p3);
    
    public void aw(final int kc) {
        this.KC = kc;
    }
    
    protected boolean b(final boolean b, final boolean b2) {
        return this.KE && !b2 && (!b || this.KF);
    }
    
    static final class a
    {
        public final Uri uri;
        
        public a(final Uri uri) {
            this.uri = uri;
        }
        
        @Override
        public boolean equals(final Object o) {
            return o instanceof a && (this == o || m.equal(((a)o).uri, this.uri));
        }
        
        @Override
        public int hashCode() {
            return m.hashCode(this.uri);
        }
    }
    
    public static final class b extends a
    {
        private WeakReference<ImageView> KH;
        
        public b(final ImageView imageView, final int n) {
            super(null, n);
            a.f(imageView);
            this.KH = new WeakReference<ImageView>(imageView);
        }
        
        public b(final ImageView imageView, final Uri uri) {
            super(uri, 0);
            a.f(imageView);
            this.KH = new WeakReference<ImageView>(imageView);
        }
        
        private void a(final ImageView imageView, Drawable a, final boolean b, final boolean b2, final boolean b3) {
            boolean b4;
            if (!b2 && !b3) {
                b4 = true;
            }
            else {
                b4 = false;
            }
            Label_0057: {
                if (!b4 || !(imageView instanceof iy)) {
                    break Label_0057;
                }
                final int gn = ((iy)imageView).gN();
                if (this.KC == 0 || gn != this.KC) {
                    break Label_0057;
                }
                return;
            }
            final boolean b5 = this.b(b, b2);
            if (b5) {
                a = this.a(imageView.getDrawable(), a);
            }
            imageView.setImageDrawable(a);
            if (imageView instanceof iy) {
                final iy iy = (iy)imageView;
                Uri uri;
                if (b3) {
                    uri = this.KA.uri;
                }
                else {
                    uri = null;
                }
                iy.g(uri);
                int kc;
                if (b4) {
                    kc = this.KC;
                }
                else {
                    kc = 0;
                }
                iy.ay(kc);
            }
            if (b5) {
                ((iw)a).startTransition(250);
            }
        }
        
        @Override
        protected void a(final Drawable drawable, final boolean b, final boolean b2, final boolean b3) {
            final ImageView imageView = this.KH.get();
            if (imageView != null) {
                this.a(imageView, drawable, b, b2, b3);
            }
        }
        
        @Override
        public boolean equals(final Object o) {
            if (!(o instanceof b)) {
                return false;
            }
            if (this == o) {
                return true;
            }
            final b b = (b)o;
            final ImageView imageView = this.KH.get();
            final ImageView imageView2 = b.KH.get();
            return imageView2 != null && imageView != null && m.equal(imageView2, imageView);
        }
        
        @Override
        public int hashCode() {
            return 0;
        }
    }
    
    public static final class c extends a
    {
        private WeakReference<ImageManager.OnImageLoadedListener> KI;
        
        public c(final ImageManager.OnImageLoadedListener onImageLoadedListener, final Uri uri) {
            super(uri, 0);
            a.f(onImageLoadedListener);
            this.KI = new WeakReference<ImageManager.OnImageLoadedListener>(onImageLoadedListener);
        }
        
        @Override
        protected void a(final Drawable drawable, final boolean b, final boolean b2, final boolean b3) {
            if (!b2) {
                final ImageManager.OnImageLoadedListener onImageLoadedListener = this.KI.get();
                if (onImageLoadedListener != null) {
                    onImageLoadedListener.onImageLoaded(this.KA.uri, drawable, b3);
                }
            }
        }
        
        @Override
        public boolean equals(final Object o) {
            if (!(o instanceof c)) {
                return false;
            }
            if (this == o) {
                return true;
            }
            final c c = (c)o;
            final ImageManager.OnImageLoadedListener onImageLoadedListener = this.KI.get();
            final ImageManager.OnImageLoadedListener onImageLoadedListener2 = c.KI.get();
            return onImageLoadedListener2 != null && onImageLoadedListener != null && m.equal(onImageLoadedListener2, onImageLoadedListener) && m.equal(c.KA, this.KA);
        }
        
        @Override
        public int hashCode() {
            return m.hashCode(this.KA);
        }
    }
}
