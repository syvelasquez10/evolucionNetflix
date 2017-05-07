// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast.internal;

import android.text.TextUtils;
import java.util.Locale;
import android.util.Log;
import com.google.android.gms.common.internal.zzu;

public class zzl
{
    private static boolean zzUS;
    protected final String mTag;
    private final boolean zzUT;
    private boolean zzUU;
    private boolean zzUV;
    private String zzUW;
    
    static {
        zzl.zzUS = false;
    }
    
    public zzl(final String s) {
        this(s, zzlZ());
    }
    
    public zzl(final String mTag, final boolean zzUU) {
        zzu.zzh(mTag, "The log tag cannot be null or empty.");
        this.mTag = mTag;
        this.zzUT = (mTag.length() <= 23);
        this.zzUU = zzUU;
        this.zzUV = false;
    }
    
    public static boolean zzlZ() {
        return zzl.zzUS;
    }
    
    public void zzb(final String s, final Object... array) {
        if (this.zzlX() || zzl.zzUS) {
            Log.d(this.mTag, this.zzg(s, array));
        }
    }
    
    public void zzb(final Throwable t, final String s, final Object... array) {
        if (this.zzlX() || zzl.zzUS) {
            Log.d(this.mTag, this.zzg(s, array), t);
        }
    }
    
    protected String zzg(String format, final Object... array) {
        if (array.length != 0) {
            format = String.format(Locale.ROOT, format, array);
        }
        String string = format;
        if (!TextUtils.isEmpty((CharSequence)this.zzUW)) {
            string = this.zzUW + format;
        }
        return string;
    }
    
    public boolean zzlX() {
        return this.zzUU || (this.zzUT && Log.isLoggable(this.mTag, 3));
    }
}
