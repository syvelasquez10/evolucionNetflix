// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.Collection;

public abstract class s extends r
{
    public static String c;
    public static String d;
    public static String e;
    public static String f;
    public static String g;
    public static String h;
    protected String a;
    protected Collection b;
    
    static {
        s.c = "uhe";
        s.d = "uhe-bg";
        s.e = "he";
        s.f = "he-bg";
        s.g = "appload";
        s.h = "error";
    }
    
    private static boolean b(final JSONObject jsonObject) {
        if (jsonObject.has("success")) {
            try {
                return jsonObject.getInt("success") == 1;
            }
            catch (JSONException ex) {}
        }
        return false;
    }
    
    public final void a(final Object o) {
        try {
            this.b.add(o);
        }
        catch (Exception ex) {}
    }
    
    public final void a(final Collection collection) {
        try {
            this.b.addAll(collection);
        }
        catch (Exception ex) {}
    }
    
    public void a(final JSONObject jsonObject) {
        new StringBuilder("class.name = ").append(this.getClass().getName());
        try {
            if (!b(jsonObject)) {
                this.c();
            }
            this.g();
        }
        catch (Exception ex) {
            new StringBuilder("Exception in CrittercismRequestData$handleResponseObject: ").append(ex.getClass().getName());
        }
    }
    
    public abstract JSONObject b();
    
    public JSONObject d() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Lorg/json/JSONObject;
        //     3: dup            
        //     4: invokespecial   org/json/JSONObject.<init>:()V
        //     7: astore_1       
        //     8: new             Lorg/json/JSONObject;
        //    11: dup            
        //    12: invokespecial   org/json/JSONObject.<init>:()V
        //    15: astore_2       
        //    16: aload_0        
        //    17: invokevirtual   crittercism/android/s.e:()V
        //    20: aload_0        
        //    21: getfield        crittercism/android/s.b:Ljava/util/Collection;
        //    24: invokeinterface java/util/Collection.size:()I
        //    29: ifne            87
        //    32: aload_2        
        //    33: ldc             "success"
        //    35: iconst_1       
        //    36: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
        //    39: pop            
        //    40: aload_2        
        //    41: areturn        
        //    42: astore_3       
        //    43: new             Ljava/lang/StringBuilder;
        //    46: dup            
        //    47: ldc             "Exception in CrittercismRequestData$sendData: "
        //    49: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //    52: aload_3        
        //    53: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //    56: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
        //    59: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    62: pop            
        //    63: goto            20
        //    66: astore_2       
        //    67: new             Ljava/lang/StringBuilder;
        //    70: dup            
        //    71: ldc             "Exception in CrittercismRequestData$sendData when data vector is size 0: "
        //    73: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //    76: aload_2        
        //    77: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //    80: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
        //    83: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    86: pop            
        //    87: new             Ljava/util/HashMap;
        //    90: dup            
        //    91: invokespecial   java/util/HashMap.<init>:()V
        //    94: astore_3       
        //    95: aload_3        
        //    96: ldc             "success"
        //    98: iconst_0       
        //    99: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   102: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   107: pop            
        //   108: aload_0        
        //   109: invokevirtual   crittercism/android/s.b:()Lorg/json/JSONObject;
        //   112: astore_2       
        //   113: aload_2        
        //   114: astore_1       
        //   115: invokestatic    crittercism/android/l.i:()Lcrittercism/android/l;
        //   118: getfield        crittercism/android/l.c:Lcrittercism/android/i;
        //   121: aload_1        
        //   122: invokevirtual   crittercism/android/i.a:(Lorg/json/JSONObject;)Lorg/json/JSONObject;
        //   125: astore_1       
        //   126: aload_1        
        //   127: areturn        
        //   128: astore_2       
        //   129: new             Ljava/lang/StringBuilder;
        //   132: dup            
        //   133: ldc             "Exception creating request object in CrittercismRequestData$sendData "
        //   135: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   138: aload_2        
        //   139: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //   142: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
        //   145: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   148: pop            
        //   149: goto            115
        //   152: astore_1       
        //   153: new             Ljava/lang/StringBuilder;
        //   156: dup            
        //   157: ldc             "Exception obtaining response object in CrittercismRequestData$sendData "
        //   159: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   162: aload_1        
        //   163: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //   166: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
        //   169: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   172: pop            
        //   173: new             Lorg/json/JSONObject;
        //   176: dup            
        //   177: aload_3        
        //   178: invokespecial   org/json/JSONObject.<init>:(Ljava/util/Map;)V
        //   181: areturn        
        //   182: astore_2       
        //   183: goto            87
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  16     20     42     66     Ljava/lang/Exception;
        //  20     40     182    186    Lorg/json/JSONException;
        //  20     40     66     87     Ljava/lang/Exception;
        //  108    113    128    152    Ljava/lang/Exception;
        //  115    126    152    182    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 92, Size: 92
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
    
    public final Collection f() {
        return this.b;
    }
    
    public final void g() {
        try {
            this.b.clear();
        }
        catch (Exception ex) {}
    }
}
