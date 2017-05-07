// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.images;

import com.google.android.gms.internal.kc;
import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import com.google.android.gms.internal.ja;

final class ImageManager$b extends ja<a$a, Bitmap>
{
    public ImageManager$b(final Context context) {
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
            n = ImageManager$a.a(activityManager);
        }
        else {
            n = activityManager.getMemoryClass();
        }
        return (int)(n * 1048576 * 0.33f);
    }
    
    protected int a(final a$a a$a, final Bitmap bitmap) {
        return bitmap.getHeight() * bitmap.getRowBytes();
    }
    
    protected void a(final boolean b, final a$a a$a, final Bitmap bitmap, final Bitmap bitmap2) {
        super.entryRemoved(b, a$a, bitmap, bitmap2);
    }
}
