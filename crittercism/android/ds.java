// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import android.content.SharedPreferences$Editor;
import android.content.Context;
import android.content.SharedPreferences;

public final class ds
{
    private SharedPreferences a;
    private SharedPreferences b;
    private Context c;
    
    public ds(final Context c) {
        if (c == null) {
            throw new NullPointerException("context was null");
        }
        this.c = c;
        this.a = c.getSharedPreferences("com.crittercism.usersettings", 0);
        this.b = c.getSharedPreferences("com.crittercism.prefs", 0);
        if (this.a == null) {
            throw new NullPointerException("prefs were null");
        }
        if (this.b == null) {
            throw new NullPointerException("legacy prefs were null");
        }
    }
    
    private boolean a(final String s) {
        final SharedPreferences$Editor edit = this.a.edit();
        edit.putString("hashedDeviceID", s);
        return edit.commit();
    }
    
    private String b() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore_2       
        //     2: aload_0        
        //     3: getfield        crittercism/android/ds.c:Landroid/content/Context;
        //     6: invokevirtual   android/content/Context.getContentResolver:()Landroid/content/ContentResolver;
        //     9: ldc             "android_id"
        //    11: invokestatic    android/provider/Settings$Secure.getString:(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
        //    14: astore_3       
        //    15: aload_2        
        //    16: astore_1       
        //    17: aload_3        
        //    18: ifnull          62
        //    21: aload_2        
        //    22: astore_1       
        //    23: aload_3        
        //    24: invokevirtual   java/lang/String.length:()I
        //    27: ifle            62
        //    30: aload_2        
        //    31: astore_1       
        //    32: aload_3        
        //    33: ldc             "9774d56d682e549c"
        //    35: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    38: ifne            62
        //    41: aload_3        
        //    42: ldc             "utf8"
        //    44: invokevirtual   java/lang/String.getBytes:(Ljava/lang/String;)[B
        //    47: invokestatic    java/util/UUID.nameUUIDFromBytes:([B)Ljava/util/UUID;
        //    50: astore_3       
        //    51: aload_2        
        //    52: astore_1       
        //    53: aload_3        
        //    54: ifnull          62
        //    57: aload_3        
        //    58: invokevirtual   java/util/UUID.toString:()Ljava/lang/String;
        //    61: astore_1       
        //    62: aload_1        
        //    63: ifnull          75
        //    66: aload_1        
        //    67: astore_2       
        //    68: aload_1        
        //    69: invokevirtual   java/lang/String.length:()I
        //    72: ifne            82
        //    75: invokestatic    java/util/UUID.randomUUID:()Ljava/util/UUID;
        //    78: invokevirtual   java/util/UUID.toString:()Ljava/lang/String;
        //    81: astore_2       
        //    82: aload_2        
        //    83: areturn        
        //    84: astore_1       
        //    85: aload_1        
        //    86: athrow         
        //    87: astore_1       
        //    88: aload_1        
        //    89: invokestatic    crittercism/android/dy.a:(Ljava/lang/Throwable;)V
        //    92: aload_2        
        //    93: astore_1       
        //    94: goto            62
        //    97: astore_1       
        //    98: aload_1        
        //    99: athrow         
        //   100: astore_2       
        //   101: aload_2        
        //   102: invokestatic    crittercism/android/dy.a:(Ljava/lang/Throwable;)V
        //   105: aload_1        
        //   106: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                   
        //  -----  -----  -----  -----  -----------------------
        //  2      15     84     87     Ljava/lang/ThreadDeath;
        //  2      15     87     97     Ljava/lang/Throwable;
        //  23     30     84     87     Ljava/lang/ThreadDeath;
        //  23     30     87     97     Ljava/lang/Throwable;
        //  32     51     84     87     Ljava/lang/ThreadDeath;
        //  32     51     87     97     Ljava/lang/Throwable;
        //  57     62     84     87     Ljava/lang/ThreadDeath;
        //  57     62     87     97     Ljava/lang/Throwable;
        //  75     82     97     100    Ljava/lang/ThreadDeath;
        //  75     82     100    107    Ljava/lang/Throwable;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0075:
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
    
    public final String a() {
        String string;
        if ((string = this.a.getString("hashedDeviceID", (String)null)) == null) {
            final String string2 = this.b.getString("com.crittercism.prefs.did", (String)null);
            if ((string = string2) != null) {
                string = string2;
                if (this.a(string2)) {
                    final SharedPreferences$Editor edit = this.b.edit();
                    edit.remove("com.crittercism.prefs.did");
                    edit.commit();
                    string = string2;
                }
            }
        }
        String b;
        if ((b = string) == null) {
            b = this.b();
            this.a(b);
        }
        return b;
    }
}
