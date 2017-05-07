// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.util.Date;
import java.util.TimeZone;
import java.util.Locale;
import java.text.SimpleDateFormat;

public final class au
{
    public static final au a;
    private av b;
    private SimpleDateFormat c;
    
    static {
        a = new au();
    }
    
    private au() {
        this.b = new a((byte)0);
        this.c = null;
        try {
            this.c = b();
        }
        catch (Exception ex) {}
    }
    
    private static SimpleDateFormat b() {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return simpleDateFormat;
    }
    
    public final long a(final String s) {
        return this.c.parse(s).getTime();
    }
    
    public final String a() {
        return this.a(this.b.a());
    }
    
    public final String a(final Date date) {
        if (this.c != null) {
            return this.c.format(date);
        }
        b();
        return "";
    }
    
    final class a implements av
    {
        @Override
        public final Date a() {
            return new Date();
        }
    }
}
