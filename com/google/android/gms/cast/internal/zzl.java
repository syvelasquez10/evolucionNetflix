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
    private static boolean zzZl;
    protected final String mTag;
    private final boolean zzZm;
    private boolean zzZn;
    private boolean zzZo;
    private String zzZp;
    
    static {
        zzl.zzZl = false;
    }
    
    public zzl(final String s) {
        this(s, zznh());
    }
    
    public zzl(final String mTag, final boolean zzZn) {
        zzx.zzh(mTag, "The log tag cannot be null or empty.");
        this.mTag = mTag;
        this.zzZm = (mTag.length() <= 23);
        this.zzZn = zzZn;
        this.zzZo = false;
    }
    
    public static boolean zznh() {
        return zzl.zzZl;
    }
    
    public void zzb(final String s, final Object... array) {
        if (this.zznf() || zzl.zzZl) {
            Log.d(this.mTag, this.zzg(s, array));
        }
    }
    
    public void zzb(final Throwable t, final String s, final Object... array) {
        if (this.zznf() || zzl.zzZl) {
            Log.d(this.mTag, this.zzg(s, array), t);
        }
    }
    
    protected String zzg(String format, final Object... array) {
        if (array.length != 0) {
            format = String.format(Locale.ROOT, format, array);
        }
        String string = format;
        if (!TextUtils.isEmpty((CharSequence)this.zzZp)) {
            string = this.zzZp + format;
        }
        return string;
    }
    
    public boolean zznf() {
        return this.zzZn || (this.zzZm && Log.isLoggable(this.mTag, 3));
    }
}
