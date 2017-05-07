// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import android.util.Log;

public final class dy
{
    public static dy$a a;
    private static ed b;
    
    static {
        dy.a = dy$a.a;
    }
    
    public static void a() {
    }
    
    public static void a(final ed b) {
        dy.b = b;
    }
    
    public static void a(final String s) {
        Log.i("Crittercism", s);
    }
    
    public static void a(final String s, final Throwable t) {
        Log.e("Crittercism", s, t);
    }
    
    public static void a(final Throwable t) {
        if (t instanceof cp) {
            return;
        }
        try {
            final ed b = dy.b;
            if (dy.b != null && dy.a == dy$a.b) {
                final ed b2 = dy.b;
                final ed$1 ed$1 = new ed$1(b2, t, Thread.currentThread().getId());
                if (!b2.c.a(ed$1)) {
                    b2.b.execute(ed$1);
                }
            }
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {}
    }
    
    public static void b() {
    }
    
    public static void b(final String s) {
        Log.e("Crittercism", s);
    }
    
    public static void b(final String s, final Throwable t) {
        Log.w("Crittercism", s, t);
    }
    
    public static void c() {
    }
    
    public static void c(final String s) {
        Log.w("Crittercism", s);
    }
}
