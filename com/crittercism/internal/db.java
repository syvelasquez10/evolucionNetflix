// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import java.net.MalformedURLException;
import java.net.URL;

public final class db
{
    private String a;
    private String b;
    
    public db(final String a, final String b) {
        a.endsWith("/");
        b.startsWith("/");
        this.a = a;
        this.b = b;
    }
    
    public final URL a() {
        try {
            return new URL(this.a + this.b);
        }
        catch (MalformedURLException ex) {
            dw.d("Invalid url: " + this.a + this.b);
            return null;
        }
    }
}
