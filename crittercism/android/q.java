// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import org.json.JSONArray;
import java.util.Collection;
import java.util.ArrayList;

public final class q implements t
{
    private boolean a;
    private ArrayList b;
    private final int c;
    private final String d;
    private final p e;
    
    public q(final int n, final boolean a, final String d, final p e) {
        this.a = false;
        this.c = Math.max(n, 10);
        this.d = d;
        this.b = new ArrayList();
        this.a = a;
        if (a) {
            this.a(w.a);
        }
        this.e = e;
    }
    
    private void a(final ArrayList list) {
        synchronized (this) {
            this.b.addAll(list);
            this.c();
        }
    }
    
    private void c() {
        int i = 0;
        final int n = this.b.size() - this.c;
        if (n > 0) {
            int n2;
            if (this.a) {
                n2 = 1;
            }
            else {
                n2 = 0;
            }
            while (i < n) {
                this.b.remove(n2);
                ++i;
            }
        }
    }
    
    public final JSONArray a() {
        final JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < this.b.size(); ++i) {
            jsonArray.put(((o)this.b.get(i)).b());
        }
        return jsonArray;
    }
    
    public final void a(final o o) {
        synchronized (this) {
            this.b.add(o);
            this.c();
        }
    }
    
    public final boolean a(final h h, final String s, final String s2) {
        synchronized (this) {
            h.b(s, s2, this.a().toString());
            return true;
        }
    }
    
    public final boolean b(final h p0, final String p1, final String p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iconst_0       
        //     1: istore          6
        //     3: aload_0        
        //     4: monitorenter   
        //     5: aload_1        
        //     6: aload_2        
        //     7: aload_3        
        //     8: new             Lorg/json/JSONArray;
        //    11: dup            
        //    12: invokespecial   org/json/JSONArray.<init>:()V
        //    15: invokevirtual   org/json/JSONArray.toString:()Ljava/lang/String;
        //    18: invokeinterface crittercism/android/h.a:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //    23: astore_2       
        //    24: new             Lorg/json/JSONArray;
        //    27: dup            
        //    28: invokespecial   org/json/JSONArray.<init>:()V
        //    31: astore_1       
        //    32: new             Lorg/json/JSONArray;
        //    35: dup            
        //    36: aload_2        
        //    37: invokespecial   org/json/JSONArray.<init>:(Ljava/lang/String;)V
        //    40: astore_2       
        //    41: aload_2        
        //    42: astore_1       
        //    43: new             Ljava/util/ArrayList;
        //    46: dup            
        //    47: invokespecial   java/util/ArrayList.<init>:()V
        //    50: astore_2       
        //    51: iconst_0       
        //    52: istore          4
        //    54: aload_1        
        //    55: invokevirtual   org/json/JSONArray.length:()I
        //    58: istore          5
        //    60: iload           4
        //    62: iload           5
        //    64: if_icmpge       96
        //    67: aload_2        
        //    68: aload_0        
        //    69: getfield        crittercism/android/q.e:Lcrittercism/android/p;
        //    72: aload_1        
        //    73: iload           4
        //    75: invokevirtual   org/json/JSONArray.getJSONArray:(I)Lorg/json/JSONArray;
        //    78: invokeinterface crittercism/android/p.a:(Lorg/json/JSONArray;)Lcrittercism/android/o;
        //    83: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //    86: pop            
        //    87: iload           4
        //    89: iconst_1       
        //    90: iadd           
        //    91: istore          4
        //    93: goto            54
        //    96: aload_0        
        //    97: aload_2        
        //    98: invokespecial   crittercism/android/q.a:(Ljava/util/ArrayList;)V
        //   101: iconst_1       
        //   102: istore          6
        //   104: aload_0        
        //   105: monitorexit    
        //   106: iload           6
        //   108: ireturn        
        //   109: astore_1       
        //   110: aload_0        
        //   111: monitorexit    
        //   112: aload_1        
        //   113: athrow         
        //   114: astore_1       
        //   115: goto            104
        //   118: astore_1       
        //   119: goto            104
        //   122: astore_2       
        //   123: goto            43
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                      
        //  -----  -----  -----  -----  --------------------------
        //  5      32     109    114    Any
        //  32     41     122    126    Lorg/json/JSONException;
        //  32     41     109    114    Any
        //  43     51     109    114    Any
        //  54     60     109    114    Any
        //  67     87     118    122    Lorg/json/JSONException;
        //  67     87     114    118    Ljava/text/ParseException;
        //  67     87     109    114    Any
        //  96     101    109    114    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0043:
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
}
