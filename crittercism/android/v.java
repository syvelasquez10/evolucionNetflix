// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.util.Iterator;
import java.net.URLStreamHandler;
import java.lang.reflect.ParameterizedType;
import java.util.Hashtable;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.LinkedList;
import java.net.URLStreamHandlerFactory;

public final class v implements URLStreamHandlerFactory
{
    private static final Object a;
    private static v b;
    private LinkedList c;
    private boolean d;
    private boolean e;
    
    static {
        a = new Object();
    }
    
    public v(final v$a v$a, final e e, final d d) {
        this.c = new LinkedList();
        this.d = false;
        this.e = false;
        if (v$a == v$a.c || v$a == v$a.a) {
            this.c.add(new o(e, d));
        }
        if (v$a == v$a.c || v$a == v$a.b) {
            this.c.add(new q(e, d));
        }
    }
    
    public static v a() {
        return v.b;
    }
    
    private boolean d() {
        boolean d = false;
        synchronized (this) {
            synchronized (v.a) {
                if (v.b != this) {
                    final boolean d2 = this.d;
                }
                else {
                    if (this.d && e()) {
                        this.d = false;
                        v.b = null;
                    }
                    // monitorexit(v.a)
                    d = this.d;
                }
                return d;
            }
        }
    }
    
    private static boolean e() {
        final Field[] declaredFields = URL.class.getDeclaredFields();
        final int length = declaredFields.length;
        final int n = 0;
        if (n < length) {
            final Field field = declaredFields[n];
            if (!URLStreamHandlerFactory.class.isAssignableFrom(field.getType())) {
                goto Label_0063;
            }
            try {
                final eb f = eb.f;
                field.setAccessible(true);
                field.set(null, null);
                field.setAccessible(false);
                URL.setURLStreamHandlerFactory(null);
                return true;
            }
            catch (IllegalAccessException ex) {
                dy.c();
            }
            catch (SecurityException ex2) {
                dy.c();
                goto Label_0063;
            }
            catch (Throwable t) {
                dy.c();
                goto Label_0063;
            }
        }
        return false;
    }
    
    private static boolean f() {
        final Field[] declaredFields = URL.class.getDeclaredFields();
        final int length = declaredFields.length;
        final int n = 0;
        if (n < length) {
            final Field field = declaredFields[n];
            if (!Hashtable.class.isAssignableFrom(field.getType())) {
                goto Label_0133;
            }
            final ParameterizedType parameterizedType = (ParameterizedType)field.getGenericType();
            final Class clazz = (Class)parameterizedType.getActualTypeArguments()[0];
            final Class clazz2 = (Class)parameterizedType.getActualTypeArguments()[1];
            if (!String.class.isAssignableFrom(clazz) || !URLStreamHandler.class.isAssignableFrom(clazz2)) {
                goto Label_0133;
            }
            try {
                final eb g = eb.g;
                field.setAccessible(true);
                final Hashtable hashtable = (Hashtable)field.get(null);
                if (hashtable != null) {
                    hashtable.clear();
                }
                field.setAccessible(false);
                return true;
            }
            catch (IllegalArgumentException ex) {
                dy.c();
            }
            catch (SecurityException ex2) {
                dy.c();
                goto Label_0133;
            }
            catch (IllegalAccessException ex3) {
                dy.c();
                goto Label_0133;
            }
        }
        return false;
    }
    
    public final boolean b() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iconst_1       
        //     1: istore_1       
        //     2: getstatic       crittercism/android/v.a:Ljava/lang/Object;
        //     5: astore_2       
        //     6: aload_2        
        //     7: monitorenter   
        //     8: getstatic       crittercism/android/v.b:Lcrittercism/android/v;
        //    11: ifnull          25
        //    14: getstatic       crittercism/android/v.b:Lcrittercism/android/v;
        //    17: aload_0        
        //    18: if_acmpne       70
        //    21: aload_2        
        //    22: monitorexit    
        //    23: iload_1        
        //    24: ireturn        
        //    25: aload_0        
        //    26: getfield        crittercism/android/v.d:Z
        //    29: ifne            54
        //    32: aload_0        
        //    33: getfield        crittercism/android/v.e:Z
        //    36: istore_1       
        //    37: iload_1        
        //    38: ifne            54
        //    41: aload_0        
        //    42: invokestatic    java/net/URL.setURLStreamHandlerFactory:(Ljava/net/URLStreamHandlerFactory;)V
        //    45: aload_0        
        //    46: iconst_1       
        //    47: putfield        crittercism/android/v.d:Z
        //    50: aload_0        
        //    51: putstatic       crittercism/android/v.b:Lcrittercism/android/v;
        //    54: aload_2        
        //    55: monitorexit    
        //    56: aload_0        
        //    57: getfield        crittercism/android/v.d:Z
        //    60: ireturn        
        //    61: astore_3       
        //    62: aload_2        
        //    63: monitorexit    
        //    64: aload_3        
        //    65: athrow         
        //    66: astore_3       
        //    67: goto            54
        //    70: iconst_0       
        //    71: istore_1       
        //    72: goto            21
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  8      21     61     66     Any
        //  21     23     61     66     Any
        //  25     37     61     66     Any
        //  41     54     66     70     Ljava/lang/Throwable;
        //  41     54     61     66     Any
        //  54     56     61     66     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0054:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public final boolean c() {
        while (true) {
            boolean b = false;
            while (true) {
                synchronized (this) {
                    this.d();
                    if (this.d) {
                        this.e = true;
                        final int f = f() ? 1 : 0;
                        if (!this.d || f != 0) {
                            b = true;
                        }
                        return b;
                    }
                }
                final int f = 0;
                continue;
            }
        }
    }
    
    @Override
    public final URLStreamHandler createURLStreamHandler(final String s) {
        try {
            if (!this.e) {
                for (final m m : this.c) {
                    if (m.a().equals(s)) {
                        return m;
                    }
                }
            }
            return null;
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            this.e = true;
            dy.a(t);
            return null;
        }
    }
}
