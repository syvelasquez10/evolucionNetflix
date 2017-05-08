// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import org.json.JSONArray;
import java.io.OutputStream;

public final class cd extends bn
{
    public static final cd a;
    private String b;
    private String c;
    private String d;
    private int e;
    
    static {
        a = new cd("session_start", cd$a.a);
    }
    
    public cd(final String s, final int n) {
        this(s, eb.a.a(), n);
    }
    
    private cd(final String s, final String c, final int e) {
        this.d = ce.a.a();
        String substring = s;
        if (s.length() > 140) {
            substring = s.substring(0, 140);
        }
        this.b = substring;
        this.c = c;
        this.e = e;
    }
    
    @Override
    public final void a(final OutputStream outputStream) {
        final JSONArray jsonArray = new JSONArray();
        jsonArray.put((Object)this.b);
        jsonArray.put((Object)this.c);
        final String string = jsonArray.toString();
        dw.d("BREADCRUMB WRITING " + string);
        outputStream.write(string.getBytes());
    }
    
    @Override
    public final String e() {
        return this.d;
    }
}
