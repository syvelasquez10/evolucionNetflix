// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.io.InputStream;

@ez
public class go
{
    public static final a<Void> wy;
    
    static {
        wy = (a)new a() {
            public Void c(final InputStream inputStream) {
                return null;
            }
            
            public Void dr() {
                return null;
            }
        };
    }
    
    public <T> Future<T> a(final String s, final a<T> a) {
        return gi.submit((Callable<T>)new Callable<T>() {
            @Override
            public T call() {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     0: aconst_null    
                //     1: astore          5
                //     3: aconst_null    
                //     4: astore_2       
                //     5: aconst_null    
                //     6: astore          4
                //     8: new             Ljava/net/URL;
                //    11: dup            
                //    12: aload_0        
                //    13: getfield        com/google/android/gms/internal/go$2.wz:Ljava/lang/String;
                //    16: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
                //    19: invokevirtual   java/net/URL.openConnection:()Ljava/net/URLConnection;
                //    22: checkcast       Ljava/net/HttpURLConnection;
                //    25: astore_3       
                //    26: aload_3        
                //    27: invokevirtual   java/net/HttpURLConnection.connect:()V
                //    30: aload_3        
                //    31: invokevirtual   java/net/HttpURLConnection.getResponseCode:()I
                //    34: istore_1       
                //    35: iload_1        
                //    36: sipush          200
                //    39: if_icmplt       73
                //    42: iload_1        
                //    43: sipush          299
                //    46: if_icmpgt       73
                //    49: aload_0        
                //    50: getfield        com/google/android/gms/internal/go$2.wA:Lcom/google/android/gms/internal/go$a;
                //    53: aload_3        
                //    54: invokevirtual   java/net/HttpURLConnection.getInputStream:()Ljava/io/InputStream;
                //    57: invokeinterface com/google/android/gms/internal/go$a.b:(Ljava/io/InputStream;)Ljava/lang/Object;
                //    62: astore_2       
                //    63: aload_3        
                //    64: ifnull          71
                //    67: aload_3        
                //    68: invokevirtual   java/net/HttpURLConnection.disconnect:()V
                //    71: aload_2        
                //    72: areturn        
                //    73: aload_3        
                //    74: ifnull          81
                //    77: aload_3        
                //    78: invokevirtual   java/net/HttpURLConnection.disconnect:()V
                //    81: aload_0        
                //    82: getfield        com/google/android/gms/internal/go$2.wA:Lcom/google/android/gms/internal/go$a;
                //    85: invokeinterface com/google/android/gms/internal/go$a.cK:()Ljava/lang/Object;
                //    90: areturn        
                //    91: astore_2       
                //    92: aload           4
                //    94: astore_3       
                //    95: aload_2        
                //    96: astore          4
                //    98: aload_3        
                //    99: astore_2       
                //   100: ldc             "Error making HTTP request."
                //   102: aload           4
                //   104: invokestatic    com/google/android/gms/internal/gs.d:(Ljava/lang/String;Ljava/lang/Throwable;)V
                //   107: aload_3        
                //   108: ifnull          81
                //   111: aload_3        
                //   112: invokevirtual   java/net/HttpURLConnection.disconnect:()V
                //   115: goto            81
                //   118: astore          4
                //   120: aload           5
                //   122: astore_3       
                //   123: aload_3        
                //   124: astore_2       
                //   125: ldc             "Error making HTTP request."
                //   127: aload           4
                //   129: invokestatic    com/google/android/gms/internal/gs.d:(Ljava/lang/String;Ljava/lang/Throwable;)V
                //   132: aload_3        
                //   133: ifnull          81
                //   136: aload_3        
                //   137: invokevirtual   java/net/HttpURLConnection.disconnect:()V
                //   140: goto            81
                //   143: astore          4
                //   145: aload_2        
                //   146: astore_3       
                //   147: aload           4
                //   149: astore_2       
                //   150: aload_3        
                //   151: ifnull          158
                //   154: aload_3        
                //   155: invokevirtual   java/net/HttpURLConnection.disconnect:()V
                //   158: aload_2        
                //   159: athrow         
                //   160: astore_2       
                //   161: goto            150
                //   164: astore          4
                //   166: goto            123
                //   169: astore          4
                //   171: goto            98
                //    Signature:
                //  ()TT;
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                            
                //  -----  -----  -----  -----  --------------------------------
                //  8      26     91     98     Ljava/net/MalformedURLException;
                //  8      26     118    123    Ljava/io/IOException;
                //  8      26     143    150    Any
                //  26     35     169    174    Ljava/net/MalformedURLException;
                //  26     35     164    169    Ljava/io/IOException;
                //  26     35     160    164    Any
                //  49     63     169    174    Ljava/net/MalformedURLException;
                //  49     63     164    169    Ljava/io/IOException;
                //  49     63     160    164    Any
                //  100    107    143    150    Any
                //  125    132    143    150    Any
                // 
                // The error that occurred was:
                // 
                // java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
                //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
                //     at java.util.ArrayList.get(ArrayList.java:429)
                //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:3035)
                //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
                //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1163)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1010)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
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
        });
    }
    
    public interface a<T>
    {
        T b(final InputStream p0);
        
        T cK();
    }
}
