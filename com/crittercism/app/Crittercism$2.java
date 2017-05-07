// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.app;

import crittercism.android.ae;
import crittercism.android.l;
import java.util.concurrent.Callable;

final class Crittercism$2 implements Callable
{
    private static String a() {
        try {
            final l i = l.i();
            if (i.f == null) {
                return null;
            }
            final String a = i.a(ae.g.a(), ae.g.b(), null);
            if (a == null) {
                return i.a("com.crittercism.prefs", "com.crittercism.prefs.did", null);
            }
            return a;
        }
        catch (Exception ex) {
            new StringBuilder("Exception in getUserUUID.call(): ").append(ex.getClass().getName());
            return null;
        }
    }
}
