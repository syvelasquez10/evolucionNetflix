// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.util.client;

import android.util.TypedValue;
import android.view.Display;
import com.google.android.gms.internal.zzmx;
import android.view.WindowManager;
import android.util.DisplayMetrics;
import android.content.Context;
import android.os.Looper;
import android.os.Handler;
import com.google.android.gms.internal.zzgr;

@zzgr
public class zza
{
    public static final Handler zzJt;
    
    static {
        zzJt = new Handler(Looper.getMainLooper());
    }
    
    public boolean zzS(final Context context) {
        if (context.getResources().getConfiguration().orientation == 2) {
            final DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            if ((int)(displayMetrics.heightPixels / displayMetrics.density) < 600) {
                return true;
            }
        }
        return false;
    }
    
    public boolean zzT(final Context context) {
        final DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        final Display defaultDisplay = ((WindowManager)context.getSystemService("window")).getDefaultDisplay();
        Label_0086: {
            if (!zzmx.zzqz()) {
                break Label_0086;
            }
            defaultDisplay.getRealMetrics(displayMetrics);
            int n = displayMetrics.heightPixels;
            int n2 = displayMetrics.widthPixels;
            while (true) {
                defaultDisplay.getMetrics(displayMetrics);
                final int heightPixels = displayMetrics.heightPixels;
                final int widthPixels = displayMetrics.widthPixels;
                Label_0141: {
                    if (heightPixels != n || widthPixels != n2) {
                        break Label_0141;
                    }
                    return true;
                    try {
                        n = (int)Display.class.getMethod("getRawHeight", (Class<?>[])new Class[0]).invoke(defaultDisplay, new Object[0]);
                        n2 = (int)Display.class.getMethod("getRawWidth", (Class<?>[])new Class[0]).invoke(defaultDisplay, new Object[0]);
                        continue;
                        b = false;
                        return b;
                    }
                    catch (Exception ex) {
                        return false;
                    }
                }
                break;
            }
        }
    }
    
    public int zzU(final Context context) {
        final int identifier = context.getResources().getIdentifier("navigation_bar_width", "dimen", "android");
        if (identifier > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }
    
    public int zza(final DisplayMetrics displayMetrics, final int n) {
        return (int)TypedValue.applyDimension(1, (float)n, displayMetrics);
    }
    
    public int zzb(final Context context, final int n) {
        return this.zza(context.getResources().getDisplayMetrics(), n);
    }
    
    public int zzb(final DisplayMetrics displayMetrics, final int n) {
        return Math.round(n / displayMetrics.density);
    }
    
    public int zzc(final Context context, final int n) {
        final Display defaultDisplay = ((WindowManager)context.getSystemService("window")).getDefaultDisplay();
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        return this.zzb(displayMetrics, n);
    }
    
    public boolean zzgT() {
        return Looper.myLooper() == Looper.getMainLooper();
    }
}
