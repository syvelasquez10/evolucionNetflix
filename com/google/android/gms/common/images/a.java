// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.images;

import com.google.android.gms.internal.ez;
import android.widget.ImageView;
import java.lang.ref.WeakReference;
import com.google.android.gms.internal.fo;
import android.graphics.drawable.BitmapDrawable;
import com.google.android.gms.internal.fb;
import android.graphics.Bitmap;
import com.google.android.gms.internal.ex;
import com.google.android.gms.internal.ey;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import com.google.android.gms.internal.fa;
import android.content.Context;
import android.net.Uri;

public abstract class a
{
    final a Cm;
    protected int Cn;
    protected int Co;
    private boolean Cp;
    private boolean Cq;
    protected int Cr;
    
    public a(final Uri uri, final int co) {
        this.Cn = 0;
        this.Co = 0;
        this.Cp = true;
        this.Cq = false;
        this.Cm = new a(uri);
        this.Co = co;
    }
    
    private Drawable a(final Context context, final fa fa, final int n) {
        final Resources resources = context.getResources();
        if (this.Cr > 0) {
            final fa.a a = new fa.a(n, this.Cr);
            Drawable drawable;
            if ((drawable = fa.get(a)) == null) {
                final Drawable drawable2 = drawable = resources.getDrawable(n);
                if ((this.Cr & 0x1) != 0x0) {
                    drawable = this.a(resources, drawable2);
                }
                fa.put(a, drawable);
            }
            return drawable;
        }
        return resources.getDrawable(n);
    }
    
    public void J(final int co) {
        this.Co = co;
    }
    
    protected Drawable a(final Resources resources, final Drawable drawable) {
        return ey.a(resources, drawable);
    }
    
    protected ex a(final Drawable drawable, final Drawable drawable2) {
        Drawable ez;
        if (drawable != null) {
            ez = drawable;
            if (drawable instanceof ex) {
                ez = ((ex)drawable).ez();
            }
        }
        else {
            ez = null;
        }
        return new ex(ez, drawable2);
    }
    
    void a(final Context context, final Bitmap bitmap, final boolean b) {
        fb.d(bitmap);
        Bitmap a = bitmap;
        if ((this.Cr & 0x1) != 0x0) {
            a = ey.a(bitmap);
        }
        this.a((Drawable)new BitmapDrawable(context.getResources(), a), b, false, true);
    }
    
    void a(final Context context, final fa fa) {
        Drawable a = null;
        if (this.Cn != 0) {
            a = this.a(context, fa, this.Cn);
        }
        this.a(a, false, true, false);
    }
    
    void a(final Context context, final fa fa, final boolean b) {
        Drawable a = null;
        if (this.Co != 0) {
            a = this.a(context, fa, this.Co);
        }
        this.a(a, b, false, false);
    }
    
    protected abstract void a(final Drawable p0, final boolean p1, final boolean p2, final boolean p3);
    
    protected boolean b(final boolean b, final boolean b2) {
        return this.Cp && !b2 && (!b || this.Cq);
    }
    
    static final class a
    {
        public final Uri uri;
        
        public a(final Uri uri) {
            this.uri = uri;
        }
        
        @Override
        public boolean equals(final Object o) {
            return o instanceof a && (this == o || fo.equal(((a)o).uri, this.uri));
        }
        
        @Override
        public int hashCode() {
            return fo.hashCode(this.uri);
        }
    }
    
    public static final class b extends a
    {
        private WeakReference<ImageView> Cs;
        
        public b(final ImageView imageView, final int n) {
            super(null, n);
            fb.d(imageView);
            this.Cs = new WeakReference<ImageView>(imageView);
        }
        
        public b(final ImageView imageView, final Uri uri) {
            super(uri, 0);
            fb.d(imageView);
            this.Cs = new WeakReference<ImageView>(imageView);
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
                if (!b4 || !(imageView instanceof ez)) {
                    break Label_0057;
                }
                final int eb = ((ez)imageView).eB();
                if (this.Co == 0 || eb != this.Co) {
                    break Label_0057;
                }
                return;
            }
            final boolean b5 = this.b(b, b2);
            if (b5) {
                a = this.a(imageView.getDrawable(), a);
            }
            imageView.setImageDrawable(a);
            if (imageView instanceof ez) {
                final ez ez = (ez)imageView;
                Uri uri;
                if (b3) {
                    uri = this.Cm.uri;
                }
                else {
                    uri = null;
                }
                ez.e(uri);
                int co;
                if (b4) {
                    co = this.Co;
                }
                else {
                    co = 0;
                }
                ez.L(co);
            }
            if (b5) {
                ((ex)a).startTransition(250);
            }
        }
        
        @Override
        protected void a(final Drawable drawable, final boolean b, final boolean b2, final boolean b3) {
            final ImageView imageView = this.Cs.get();
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
            final ImageView imageView = this.Cs.get();
            final ImageView imageView2 = b.Cs.get();
            return imageView2 != null && imageView != null && fo.equal(imageView2, imageView);
        }
        
        @Override
        public int hashCode() {
            return 0;
        }
    }
    
    public static final class c extends a
    {
        private WeakReference<ImageManager.OnImageLoadedListener> Ct;
        
        public c(final ImageManager.OnImageLoadedListener onImageLoadedListener, final Uri uri) {
            super(uri, 0);
            fb.d(onImageLoadedListener);
            this.Ct = new WeakReference<ImageManager.OnImageLoadedListener>(onImageLoadedListener);
        }
        
        @Override
        protected void a(final Drawable drawable, final boolean b, final boolean b2, final boolean b3) {
            if (!b2) {
                final ImageManager.OnImageLoadedListener onImageLoadedListener = this.Ct.get();
                if (onImageLoadedListener != null) {
                    onImageLoadedListener.onImageLoaded(this.Cm.uri, drawable, b3);
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
            final ImageManager.OnImageLoadedListener onImageLoadedListener = this.Ct.get();
            final ImageManager.OnImageLoadedListener onImageLoadedListener2 = c.Ct.get();
            return onImageLoadedListener2 != null && onImageLoadedListener != null && fo.equal(onImageLoadedListener2, onImageLoadedListener) && fo.equal(c.Cm, this.Cm);
        }
        
        @Override
        public int hashCode() {
            return fo.hashCode(this.Cm);
        }
    }
}
