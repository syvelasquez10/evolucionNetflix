// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast.internal;

import android.text.TextUtils;
import java.util.Locale;
import android.util.Log;
import com.google.android.gms.common.internal.zzx;

public class zzl
{
    private static boolean zzXv;
    protected final String mTag;
    private final boolean zzXw;
    private boolean zzXx;
    private boolean zzXy;
    private String zzXz;
    
    static {
        zzl.zzXv = false;
    }
    
    public zzl(final String s) {
        this(s, zzmL());
    }
    
    public zzl(final String mTag, final boolean zzXx) {
        zzx.zzh(mTag, "The log tag cannot be null or empty.");
        this.mTag = mTag;
        this.zzXw = (mTag.length() <= 23);
        this.zzXx = zzXx;
        this.zzXy = false;
    }
    
    public static boolean zzmL() {
        return zzl.zzXv;
    }
    
    public void zzb(final String s, final Object... array) {
        if (this.zzmJ() || zzl.zzXv) {
            Log.d(this.mTag, this.zzg(s, array));
        }
    }
    
    public void zzb(final Throwable t, final String s, final Object... array) {
        if (this.zzmJ() || zzl.zzXv) {
            Log.d(this.mTag, this.zzg(s, array), t);
        }
    }
    
    protected String zzg(String format, final Object... array) {
        if (array.length != 0) {
            format = String.format(Locale.ROOT, format, array);
        }
        String string = format;
        if (!TextUtils.isEmpty((CharSequence)this.zzXz)) {
            string = this.zzXz + format;
        }
        return string;
    }
    
    public boolean zzmJ() {
        return this.zzXx || (this.zzXw && Log.isLoggable(this.mTag, 3));
    }
}
