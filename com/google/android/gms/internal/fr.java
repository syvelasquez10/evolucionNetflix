// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.IBinder;
import com.google.android.gms.dynamic.e;
import android.view.View;
import android.content.Context;
import com.google.android.gms.dynamic.g;

public final class fr extends g<fn>
{
    private static final fr DK;
    
    static {
        DK = new fr();
    }
    
    private fr() {
        super("com.google.android.gms.common.ui.SignInButtonCreatorImpl");
    }
    
    public static View b(final Context context, final int n, final int n2) throws a {
        return fr.DK.c(context, n, n2);
    }
    
    private View c(final Context context, final int n, final int n2) throws a {
        try {
            return e.d(this.z(context).a(e.h(context), n, n2));
        }
        catch (Exception ex) {
            throw new a("Could not get button with size " + n + " and color " + n2, ex);
        }
    }
    
    public fn E(final IBinder binder) {
        return fn.a.D(binder);
    }
}
