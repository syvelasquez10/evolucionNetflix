// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.images;

import com.google.android.gms.common.internal.m;
import com.google.android.gms.internal.iw;
import com.google.android.gms.internal.iy;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import java.lang.ref.WeakReference;

public final class a$b extends a
{
    private WeakReference<ImageView> KH;
    
    public a$b(final ImageView imageView, final int n) {
        super(null, n);
        com.google.android.gms.common.internal.a.f(imageView);
        this.KH = new WeakReference<ImageView>(imageView);
    }
    
    public a$b(final ImageView imageView, final Uri uri) {
        super(uri, 0);
        com.google.android.gms.common.internal.a.f(imageView);
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
        if (!(o instanceof a$b)) {
            return false;
        }
        if (this == o) {
            return true;
        }
        final a$b a$b = (a$b)o;
        final ImageView imageView = this.KH.get();
        final ImageView imageView2 = a$b.KH.get();
        return imageView2 != null && imageView != null && m.equal(imageView2, imageView);
    }
    
    @Override
    public int hashCode() {
        return 0;
    }
}
