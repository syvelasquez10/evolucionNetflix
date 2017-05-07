// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.renderscript.Allocation;
import android.renderscript.ScriptIntrinsicBlur;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.Bitmap;
import android.webkit.WebSettings;
import android.content.Context;

public class zzie$zzd extends zzie$zzf
{
    @Override
    public String getDefaultUserAgent(final Context context) {
        return WebSettings.getDefaultUserAgent(context);
    }
    
    @Override
    public Drawable zza(final Context context, final Bitmap bitmap, final boolean b, final float radius) {
        if (!b || radius <= 0.0f || radius > 25.0f) {
            return (Drawable)new BitmapDrawable(context.getResources(), bitmap);
        }
        try {
            final Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), false);
            final Bitmap bitmap2 = Bitmap.createBitmap(scaledBitmap);
            final RenderScript create = RenderScript.create(context);
            final ScriptIntrinsicBlur create2 = ScriptIntrinsicBlur.create(create, Element.U8_4(create));
            final Allocation fromBitmap = Allocation.createFromBitmap(create, scaledBitmap);
            final Allocation fromBitmap2 = Allocation.createFromBitmap(create, bitmap2);
            create2.setRadius(radius);
            create2.setInput(fromBitmap);
            create2.forEach(fromBitmap2);
            fromBitmap2.copyTo(bitmap2);
            return (Drawable)new BitmapDrawable(context.getResources(), bitmap2);
        }
        catch (RuntimeException ex) {
            return (Drawable)new BitmapDrawable(context.getResources(), bitmap);
        }
    }
    
    @Override
    public boolean zza(final Context context, final WebSettings webSettings) {
        super.zza(context, webSettings);
        webSettings.setMediaPlaybackRequiresUserGesture(false);
        return true;
    }
}
