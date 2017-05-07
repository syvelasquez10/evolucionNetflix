// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.os.IBinder;
import com.google.android.gms.dynamic.g$a;
import com.google.android.gms.dynamic.e;
import android.view.View;
import android.content.Context;
import com.google.android.gms.dynamic.g;

public final class o extends g<l>
{
    private static final o Ma;
    
    static {
        Ma = new o();
    }
    
    private o() {
        super("com.google.android.gms.common.ui.SignInButtonCreatorImpl");
    }
    
    public static View b(final Context context, final int n, final int n2) {
        return o.Ma.c(context, n, n2);
    }
    
    private View c(final Context context, final int n, final int n2) {
        try {
            return e.f(this.L(context).a(e.k(context), n, n2));
        }
        catch (Exception ex) {
            throw new g$a("Could not get button with size " + n + " and color " + n2, ex);
        }
    }
    
    public l S(final IBinder binder) {
        return l$a.R(binder);
    }
}
