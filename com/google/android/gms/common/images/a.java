// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.images;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.Bitmap;
import com.google.android.gms.internal.iw;
import com.google.android.gms.internal.ix;
import android.content.res.Resources;
import com.google.android.gms.internal.iz$a;
import android.graphics.drawable.Drawable;
import com.google.android.gms.internal.iz;
import android.content.Context;
import android.net.Uri;

public abstract class a
{
    final a$a KA;
    protected int KB;
    protected int KC;
    protected ImageManager$OnImageLoadedListener KD;
    private boolean KE;
    private boolean KF;
    protected int KG;
    
    public a(final Uri uri, final int kc) {
        this.KB = 0;
        this.KC = 0;
        this.KE = true;
        this.KF = false;
        this.KA = new a$a(uri);
        this.KC = kc;
    }
    
    private Drawable a(final Context context, final iz iz, final int n) {
        final Resources resources = context.getResources();
        if (this.KG > 0) {
            final iz$a iz$a = new iz$a(n, this.KG);
            Drawable drawable;
            if ((drawable = iz.get(iz$a)) == null) {
                final Drawable drawable2 = drawable = resources.getDrawable(n);
                if ((this.KG & 0x1) != 0x0) {
                    drawable = this.a(resources, drawable2);
                }
                iz.put(iz$a, drawable);
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
        com.google.android.gms.common.internal.a.f(bitmap);
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
}
