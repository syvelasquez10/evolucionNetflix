// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.IBinder;
import com.google.android.gms.dynamic.c;
import android.view.View;
import android.content.Context;
import com.google.android.gms.dynamic.e;

public final class eh extends e<ed>
{
    private static final eh pP;
    
    static {
        pP = new eh();
    }
    
    private eh() {
        super("com.google.android.gms.common.ui.SignInButtonCreatorImpl");
    }
    
    public static View d(final Context context, final int n, final int n2) throws a {
        return eh.pP.e(context, n, n2);
    }
    
    private View e(final Context context, final int n, final int n2) throws a {
        try {
            return c.b(this.t(context).a(c.h(context), n, n2));
        }
        catch (Exception ex) {
            throw new a("Could not get button with size " + n + " and color " + n2, ex);
        }
    }
    
    public ed A(final IBinder binder) {
        return ed.a.z(binder);
    }
}
