// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.images;

import com.google.android.gms.common.internal.m;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import java.lang.ref.WeakReference;

public final class a$c extends a
{
    private WeakReference<ImageManager$OnImageLoadedListener> KI;
    
    public a$c(final ImageManager$OnImageLoadedListener imageManager$OnImageLoadedListener, final Uri uri) {
        super(uri, 0);
        com.google.android.gms.common.internal.a.f(imageManager$OnImageLoadedListener);
        this.KI = new WeakReference<ImageManager$OnImageLoadedListener>(imageManager$OnImageLoadedListener);
    }
    
    @Override
    protected void a(final Drawable drawable, final boolean b, final boolean b2, final boolean b3) {
        if (!b2) {
            final ImageManager$OnImageLoadedListener imageManager$OnImageLoadedListener = this.KI.get();
            if (imageManager$OnImageLoadedListener != null) {
                imageManager$OnImageLoadedListener.onImageLoaded(this.KA.uri, drawable, b3);
            }
        }
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof a$c)) {
            return false;
        }
        if (this == o) {
            return true;
        }
        final a$c a$c = (a$c)o;
        final ImageManager$OnImageLoadedListener imageManager$OnImageLoadedListener = this.KI.get();
        final ImageManager$OnImageLoadedListener imageManager$OnImageLoadedListener2 = a$c.KI.get();
        return imageManager$OnImageLoadedListener2 != null && imageManager$OnImageLoadedListener != null && m.equal(imageManager$OnImageLoadedListener2, imageManager$OnImageLoadedListener) && m.equal(a$c.KA, this.KA);
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.KA);
    }
}
