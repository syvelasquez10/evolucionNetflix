// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import java.io.IOException;
import java.io.InputStream;
import android.graphics.drawable.Drawable;

class fo$2 implements go$a<Drawable>
{
    final /* synthetic */ fo ud;
    final /* synthetic */ boolean ue;
    
    fo$2(final fo ud, final boolean ue) {
        this.ud = ud;
        this.ue = ue;
    }
    
    public Drawable a(final InputStream inputStream) {
        byte[] d;
        while (true) {
            try {
                d = jy.d(inputStream);
                if (d == null) {
                    this.ud.a(2, this.ue);
                    return null;
                }
            }
            catch (IOException ex) {
                d = null;
                continue;
            }
            break;
        }
        final Bitmap decodeByteArray = BitmapFactory.decodeByteArray(d, 0, d.length);
        if (decodeByteArray == null) {
            this.ud.a(2, this.ue);
            return null;
        }
        return (Drawable)new BitmapDrawable(Resources.getSystem(), decodeByteArray);
    }
    
    public Drawable cJ() {
        this.ud.a(2, this.ue);
        return null;
    }
}
