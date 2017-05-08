// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import java.io.IOException;
import android.content.Context;

public final class ef
{
    public ax a;
    public e b;
    public d c;
    public String d;
    
    public ef(final ax a, final e b, final Context context) {
        this.a = a;
        this.b = b;
        this.c = new d(context);
        this.d = a(context);
    }
    
    private static String a(final Context context) {
        try {
            return dz.a(context, "www/error.js");
        }
        catch (IOException ex) {
            dw.b(ex);
            return "";
        }
    }
}
