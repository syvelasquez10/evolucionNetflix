// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import java.util.Date;
import java.text.ParseException;
import java.util.TimeZone;
import java.util.Locale;
import java.text.SimpleDateFormat;

public final class eb
{
    public static final eb a;
    private ec b;
    private ThreadLocal c;
    private ThreadLocal d;
    
    static {
        a = new eb();
    }
    
    private eb() {
        this.b = new eb$a(this, (byte)0);
        this.c = new ThreadLocal();
        this.d = new ThreadLocal();
    }
    
    private SimpleDateFormat b() {
        SimpleDateFormat simpleDateFormat;
        if ((simpleDateFormat = this.c.get()) == null) {
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            simpleDateFormat.setLenient(false);
            this.c.set(simpleDateFormat);
        }
        return simpleDateFormat;
    }
    
    public final long a(String s) {
        try {
            s = this.b().parse((String)s);
            return ((Date)s).getTime();
        }
        catch (ParseException ex) {
            SimpleDateFormat simpleDateFormat;
            if ((simpleDateFormat = this.d.get()) == null) {
                simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.US);
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
                simpleDateFormat.setLenient(false);
                this.d.set(simpleDateFormat);
            }
            s = simpleDateFormat.parse((String)s);
            return ((Date)s).getTime();
        }
    }
    
    public final String a() {
        return this.a(this.b.a());
    }
    
    public final String a(final Date date) {
        return this.b().format(date);
    }
}
