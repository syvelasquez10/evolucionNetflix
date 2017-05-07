// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.util.Date;
import java.util.TimeZone;
import java.util.Locale;
import java.text.SimpleDateFormat;

public final class ee
{
    public static final ee a;
    private ef b;
    private ThreadLocal c;
    
    static {
        a = new ee();
    }
    
    private ee() {
        this.b = new ee$a(this, (byte)0);
        this.c = new ThreadLocal();
    }
    
    private SimpleDateFormat b() {
        SimpleDateFormat simpleDateFormat;
        if ((simpleDateFormat = this.c.get()) == null) {
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            simpleDateFormat.setLenient(false);
            this.c.set(simpleDateFormat);
        }
        return simpleDateFormat;
    }
    
    public final long a(final String s) {
        return this.b().parse(s).getTime();
    }
    
    public final String a() {
        return this.a(this.b.a());
    }
    
    public final String a(final Date date) {
        return this.b().format(date);
    }
}
