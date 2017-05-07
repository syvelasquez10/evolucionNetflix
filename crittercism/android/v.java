// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import org.json.JSONObject;
import java.util.Iterator;
import android.content.Context;
import java.util.Collection;
import java.io.FilenameFilter;
import java.io.File;
import java.util.Vector;

public final class v extends s
{
    public v(final m m) {
        this(m.a() + "/android_v2/ndk_crash");
    }
    
    private v(final String a) {
        this.a = a;
        this.b = new Vector();
    }
    
    public static v a(m m) {
        m = (m)new v(m.a() + "/android_v2/ndk_crash");
        try {
            final Context f = l.i().f;
            final String string = f.getFilesDir().getAbsolutePath() + "/" + l.i().t.getNativeDumpPath();
            final String packageName = f.getPackageName();
            if (packageName != null && !packageName.equals("")) {
                final File file = new File(string);
                if (file.exists()) {
                    final String[] list = file.list(new v$a(".dmp"));
                    if (list != null && list.length > 0) {
                        final Vector<String> vector = new Vector<String>();
                        for (int i = 0; i < list.length; ++i) {
                            new StringBuilder("filename = ").append(file.getAbsolutePath()).append("/").append(list[i]);
                            vector.add(file.getAbsolutePath() + "/" + list[i]);
                        }
                        ((s)m).a(vector);
                    }
                }
            }
            return (v)m;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return (v)m;
        }
    }
    
    public final void a() {
        try {
            if (this.b != null) {
                for (final String s : this.b) {
                    new StringBuilder("deleting filename: ").append(s);
                    new File(s).delete();
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public final void a(final JSONObject jsonObject) {
    }
    
    @Override
    public final JSONObject b() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Lorg/json/JSONObject;
        //     3: dup            
        //     4: invokespecial   org/json/JSONObject.<init>:()V
        //     7: astore_3       
        //     8: new             Lorg/json/JSONObject;
        //    11: dup            
        //    12: invokespecial   org/json/JSONObject.<init>:()V
        //    15: pop            
        //    16: new             Lorg/json/JSONArray;
        //    19: dup            
        //    20: invokespecial   org/json/JSONArray.<init>:()V
        //    23: pop            
        //    24: new             Lorg/json/JSONArray;
        //    27: dup            
        //    28: aload_0        
        //    29: getfield        crittercism/android/v.b:Ljava/util/Collection;
        //    32: invokespecial   org/json/JSONArray.<init>:(Ljava/util/Collection;)V
        //    35: astore_1       
        //    36: invokestatic    crittercism/android/l.i:()Lcrittercism/android/l;
        //    39: getfield        crittercism/android/l.c:Lcrittercism/android/i;
        //    42: invokevirtual   crittercism/android/i.d:()Lorg/json/JSONObject;
        //    45: astore_2       
        //    46: aload_2        
        //    47: ldc             "app_state"
        //    49: invokestatic    crittercism/android/l.i:()Lcrittercism/android/l;
        //    52: getfield        crittercism/android/l.c:Lcrittercism/android/i;
        //    55: invokevirtual   crittercism/android/i.f:()Lorg/json/JSONObject;
        //    58: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //    61: pop            
        //    62: aload_2        
        //    63: ldc             "num_ndk_crashes"
        //    65: aload_0        
        //    66: getfield        crittercism/android/v.b:Ljava/util/Collection;
        //    69: invokeinterface java/util/Collection.size:()I
        //    74: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
        //    77: pop            
        //    78: aload_2        
        //    79: ldc             "filenames"
        //    81: aload_1        
        //    82: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //    85: pop            
        //    86: aload_2        
        //    87: ldc             "filename_prefix"
        //    89: ldc             "ndk_crash_"
        //    91: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //    94: pop            
        //    95: aload_2        
        //    96: astore_1       
        //    97: aload_3        
        //    98: ldc             "requestUrl"
        //   100: aload_0        
        //   101: getfield        crittercism/android/v.a:Ljava/lang/String;
        //   104: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   107: pop            
        //   108: aload_3        
        //   109: ldc             "requestData"
        //   111: aload_1        
        //   112: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   115: pop            
        //   116: aload_3        
        //   117: areturn        
        //   118: astore_1       
        //   119: new             Lorg/json/JSONArray;
        //   122: dup            
        //   123: invokespecial   org/json/JSONArray.<init>:()V
        //   126: astore_1       
        //   127: goto            36
        //   130: astore_1       
        //   131: new             Lorg/json/JSONObject;
        //   134: dup            
        //   135: invokespecial   org/json/JSONObject.<init>:()V
        //   138: astore_1       
        //   139: goto            97
        //   142: astore_1       
        //   143: new             Lorg/json/JSONObject;
        //   146: dup            
        //   147: invokespecial   org/json/JSONObject.<init>:()V
        //   150: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  24     36     118    130    Ljava/lang/Exception;
        //  36     95     130    142    Ljava/lang/Exception;
        //  97     116    142    151    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 78, Size: 78
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
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
    
    @Override
    public final void c() {
    }
    
    @Override
    public final JSONObject d() {
        return null;
    }
    
    @Override
    protected final void e() {
    }
}
