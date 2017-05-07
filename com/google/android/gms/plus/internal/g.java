// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus.internal;

import android.os.IBinder;
import com.google.android.gms.dynamic.e;
import com.google.android.gms.plus.PlusOneDummyView;
import android.view.View;
import android.content.Context;

public final class g extends com.google.android.gms.dynamic.g<c>
{
    private static final g alr;
    
    static {
        alr = new g();
    }
    
    private g() {
        super("com.google.android.gms.plus.plusone.PlusOneButtonCreatorImpl");
    }
    
    public static View a(final Context context, final int n, final int n2, final String s, final int n3) {
        if (s == null) {
            try {
                throw new NullPointerException();
            }
            catch (Exception ex) {
                return (View)new PlusOneDummyView(context, n);
            }
        }
        return e.f(g.alr.L(context).a(e.k(context), n, n2, s, n3));
    }
    
    protected c bI(final IBinder binder) {
        return c.a.bF(binder);
    }
}
