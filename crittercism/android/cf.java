// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import org.json.JSONArray;
import java.io.OutputStream;

public final class cf extends bp
{
    public static final cf a;
    private String b;
    private String c;
    private String d;
    private cf$a e;
    
    static {
        a = new cf("session_start", cf$a.a);
    }
    
    public cf(final String s, final cf$a cf$a) {
        this(s, ee.a.a(), cf$a);
    }
    
    private cf(final String s, final String c, final cf$a e) {
        this.d = cg.a.a();
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
        new StringBuilder("BREADCRUMB WRITING ").append(string);
        dy.b();
        outputStream.write(string.getBytes());
    }
    
    @Override
    public final String e() {
        return this.d;
    }
}
