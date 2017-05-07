// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.view.WindowManager;
import android.util.DisplayMetrics;
import com.google.android.gms.internal.d;
import java.util.Map;
import com.google.android.gms.internal.a;
import android.content.Context;

class ci extends aj
{
    private static final String ID;
    private final Context mContext;
    
    static {
        ID = a.Q.toString();
    }
    
    public ci(final Context mContext) {
        super(ci.ID, new String[0]);
        this.mContext = mContext;
    }
    
    @Override
    public boolean jX() {
        return true;
    }
    
    @Override
    public d.a x(final Map<String, d.a> map) {
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager)this.mContext.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return dh.r(displayMetrics.widthPixels + "x" + displayMetrics.heightPixels);
    }
}
