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

class cj extends aj
{
    private static final String ID;
    private final Context mContext;
    
    static {
        ID = a.S.toString();
    }
    
    public cj(final Context mContext) {
        super(cj.ID, new String[0]);
        this.mContext = mContext;
    }
    
    @Override
    public d.a C(final Map<String, d.a> map) {
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager)this.mContext.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return di.u(displayMetrics.widthPixels + "x" + displayMetrics.heightPixels);
    }
    
    @Override
    public boolean nL() {
        return true;
    }
}
