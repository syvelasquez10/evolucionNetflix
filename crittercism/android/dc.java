// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.net.MalformedURLException;
import java.net.URL;

public final class dc
{
    private String a;
    private String b;
    
    public dc(final String a, final String b) {
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
            new StringBuilder("Invalid url: ").append(this.a).append(this.b);
            dy.b();
            return null;
        }
    }
}
