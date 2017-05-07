// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import org.json.JSONArray;

public final class w implements o
{
    public static final w a;
    private String b;
    private String c;
    
    static {
        a = new w("session_start");
    }
    
    public w(final String s) {
        this(s, au.a.a());
    }
    
    private w(final String s, final String c) {
        String substring = s;
        if (s.length() > 140) {
            substring = s.substring(0, 140);
        }
        this.b = substring;
        this.c = c;
    }
    
    public w(final JSONArray jsonArray) {
        this(jsonArray.getString(0), jsonArray.getString(1));
    }
    
    public static final class a implements p
    {
    }
}
