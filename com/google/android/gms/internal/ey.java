// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.Xfermode;
import android.graphics.PorterDuffXfermode;
import android.graphics.PorterDuff$Mode;
import android.graphics.Paint;
import android.graphics.Canvas;
import android.graphics.Bitmap$Config;
import android.graphics.Bitmap;

public final class ey
{
    public static Bitmap a(final Bitmap bitmap) {
        int n = 0;
        if (bitmap == null) {
            return null;
        }
        final int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int n2;
        if (width >= height) {
            n = width / 2 - height / 2;
            n2 = 0;
        }
        else {
            n2 = height / 2 - width / 2;
            height = width;
        }
        final Bitmap bitmap2 = Bitmap.createBitmap(height, height, Bitmap$Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap2);
        final Paint paint = new Paint(1);
        paint.setColor(-16777216);
        canvas.drawCircle((float)(height / 2), (float)(height / 2), (float)(height / 2), paint);
        paint.setXfermode((Xfermode)new PorterDuffXfermode(PorterDuff$Mode.SRC_IN));
        canvas.drawBitmap(bitmap, (float)n, (float)n2, paint);
        return bitmap2;
    }
    
    private static Bitmap a(final Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable)drawable).getBitmap();
        }
        final Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap$Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }
    
    public static Drawable a(final Resources resources, final Drawable drawable) {
        return (Drawable)new BitmapDrawable(resources, a(a(drawable)));
    }
}
