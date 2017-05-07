// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.images;

import com.google.android.gms.internal.ee;
import android.graphics.drawable.BitmapDrawable;
import com.google.android.gms.internal.ds;
import android.graphics.Bitmap;
import android.content.Context;
import com.google.android.gms.internal.fg;
import com.google.android.gms.internal.dr;
import com.google.android.gms.internal.dq;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.TextView;
import android.widget.ImageView;
import java.lang.ref.WeakReference;

public final class a
{
    final a op;
    private int oq;
    private int or;
    int os;
    private int ot;
    private WeakReference<ImageManager.OnImageLoadedListener> ou;
    private WeakReference<ImageView> ov;
    private WeakReference<TextView> ow;
    private int ox;
    private boolean oy;
    private boolean oz;
    
    public a(final int or) {
        this.oq = 0;
        this.or = 0;
        this.ox = -1;
        this.oy = true;
        this.oz = false;
        this.op = new a(null);
        this.or = or;
    }
    
    public a(final Uri uri) {
        this.oq = 0;
        this.or = 0;
        this.ox = -1;
        this.oy = true;
        this.oz = false;
        this.op = new a(uri);
        this.or = 0;
    }
    
    private dq a(final Drawable drawable, final Drawable drawable2) {
        Drawable bc;
        if (drawable != null) {
            bc = drawable;
            if (drawable instanceof dq) {
                bc = ((dq)drawable).bC();
            }
        }
        else {
            bc = null;
        }
        return new dq(bc, drawable2);
    }
    
    private void a(final Drawable drawable, final boolean b, final boolean b2, final boolean b3) {
        switch (this.os) {
            case 1: {
                if (b2) {
                    break;
                }
                final ImageManager.OnImageLoadedListener onImageLoadedListener = this.ou.get();
                if (onImageLoadedListener != null) {
                    onImageLoadedListener.onImageLoaded(this.op.uri, drawable, b3);
                    return;
                }
                break;
            }
            case 2: {
                final ImageView imageView = this.ov.get();
                if (imageView != null) {
                    this.a(imageView, drawable, b, b2, b3);
                    return;
                }
                break;
            }
            case 3: {
                final TextView textView = this.ow.get();
                if (textView != null) {
                    this.a(textView, this.ox, drawable, b, b2);
                    return;
                }
                break;
            }
        }
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
            if (!b4 || !(imageView instanceof dr)) {
                break Label_0057;
            }
            final int be = ((dr)imageView).bE();
            if (this.or == 0 || be != this.or) {
                break Label_0057;
            }
            return;
        }
        final boolean a2 = this.a(b, b2);
        if (a2) {
            a = this.a(imageView.getDrawable(), a);
        }
        imageView.setImageDrawable(a);
        if (imageView instanceof dr) {
            final dr dr = (dr)imageView;
            Uri uri;
            if (b3) {
                uri = this.op.uri;
            }
            else {
                uri = null;
            }
            dr.d(uri);
            int or;
            if (b4) {
                or = this.or;
            }
            else {
                or = 0;
            }
            dr.H(or);
        }
        if (a2) {
            ((dq)a).startTransition(250);
        }
    }
    
    private void a(final TextView textView, final int n, Drawable a, final boolean b, final boolean b2) {
        final boolean a2 = this.a(b, b2);
        Drawable[] array;
        if (fg.cI()) {
            array = textView.getCompoundDrawablesRelative();
        }
        else {
            array = textView.getCompoundDrawables();
        }
        final Drawable drawable = array[n];
        if (a2) {
            a = this.a(drawable, a);
        }
        Drawable drawable2;
        if (n == 0) {
            drawable2 = a;
        }
        else {
            drawable2 = array[0];
        }
        Drawable drawable3;
        if (n == 1) {
            drawable3 = a;
        }
        else {
            drawable3 = array[1];
        }
        Drawable drawable4;
        if (n == 2) {
            drawable4 = a;
        }
        else {
            drawable4 = array[2];
        }
        Drawable drawable5;
        if (n == 3) {
            drawable5 = a;
        }
        else {
            drawable5 = array[3];
        }
        if (fg.cI()) {
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable2, drawable3, drawable4, drawable5);
        }
        else {
            textView.setCompoundDrawablesWithIntrinsicBounds(drawable2, drawable3, drawable4, drawable5);
        }
        if (a2) {
            ((dq)a).startTransition(250);
        }
    }
    
    private boolean a(final boolean b, final boolean b2) {
        return this.oy && !b2 && (!b || this.oz);
    }
    
    public void F(final int or) {
        this.or = or;
    }
    
    void a(final Context context, final Bitmap bitmap, final boolean b) {
        ds.d(bitmap);
        this.a((Drawable)new BitmapDrawable(context.getResources(), bitmap), b, false, true);
    }
    
    public void a(final ImageView imageView) {
        ds.d(imageView);
        this.ou = null;
        this.ov = new WeakReference<ImageView>(imageView);
        this.ow = null;
        this.ox = -1;
        this.os = 2;
        this.ot = imageView.hashCode();
    }
    
    public void a(final ImageManager.OnImageLoadedListener onImageLoadedListener) {
        ds.d(onImageLoadedListener);
        this.ou = new WeakReference<ImageManager.OnImageLoadedListener>(onImageLoadedListener);
        this.ov = null;
        this.ow = null;
        this.ox = -1;
        this.os = 1;
        this.ot = ee.hashCode(onImageLoadedListener, this.op);
    }
    
    void b(final Context context, final boolean b) {
        Drawable drawable = null;
        if (this.or != 0) {
            drawable = context.getResources().getDrawable(this.or);
        }
        this.a(drawable, b, false, false);
    }
    
    @Override
    public boolean equals(final Object o) {
        final boolean b = true;
        boolean b2;
        if (!(o instanceof a)) {
            b2 = false;
        }
        else {
            b2 = b;
            if (this != o) {
                b2 = b;
                if (((a)o).hashCode() != this.hashCode()) {
                    return false;
                }
            }
        }
        return b2;
    }
    
    @Override
    public int hashCode() {
        return this.ot;
    }
    
    void r(final Context context) {
        Drawable drawable = null;
        if (this.oq != 0) {
            drawable = context.getResources().getDrawable(this.oq);
        }
        this.a(drawable, false, true, false);
    }
    
    public static final class a
    {
        public final Uri uri;
        
        public a(final Uri uri) {
            this.uri = uri;
        }
        
        @Override
        public boolean equals(final Object o) {
            final boolean b = true;
            boolean b2;
            if (!(o instanceof a)) {
                b2 = false;
            }
            else {
                b2 = b;
                if (this != o) {
                    b2 = b;
                    if (((a)o).hashCode() != this.hashCode()) {
                        return false;
                    }
                }
            }
            return b2;
        }
        
        @Override
        public int hashCode() {
            return ee.hashCode(this.uri);
        }
    }
}
