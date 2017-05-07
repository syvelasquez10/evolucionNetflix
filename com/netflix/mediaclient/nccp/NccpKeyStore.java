// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.nccp;

import android.content.Context;
import java.util.Enumeration;
import com.netflix.mediaclient.Log;
import java.security.KeyStore;

public final class NccpKeyStore
{
    private static final String TAG = "nf_nccp";
    private static KeyStore nccpKeyStore;
    
    private static void dump() {
        if (NccpKeyStore.nccpKeyStore == null) {
            Log.e("nf_nccp", "Key store is empty!");
        }
        else {
            try {
                final Enumeration<String> aliases = NccpKeyStore.nccpKeyStore.aliases();
                while (aliases.hasMoreElements()) {
                    Log.d("nf_nccp", "Alias " + aliases.nextElement());
                }
            }
            catch (Throwable t) {
                Log.e("nf_nccp", "Failed to list aliases", t);
            }
        }
    }
    
    public static KeyStore getInstance() {
        return NccpKeyStore.nccpKeyStore;
    }
    
    public static void init(final Context p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: invokestatic    com/netflix/mediaclient/util/AndroidUtils.getAndroidVersion:()I
        //     3: bipush          18
        //     5: if_icmpge       17
        //     8: ldc             "nf_nccp"
        //    10: ldc             "Do NOT load NCCP trust store on pre JB MR2 devices."
        //    12: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    15: pop            
        //    16: return         
        //    17: ldc             "nf_nccp"
        //    19: ldc             "Load NCCP trust store..."
        //    21: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    24: pop            
        //    25: aconst_null    
        //    26: astore          4
        //    28: aconst_null    
        //    29: astore_3       
        //    30: aload_3        
        //    31: astore_2       
        //    32: aload           4
        //    34: astore_1       
        //    35: ldc             "BKS"
        //    37: invokestatic    java/security/KeyStore.getInstance:(Ljava/lang/String;)Ljava/security/KeyStore;
        //    40: putstatic       com/netflix/mediaclient/nccp/NccpKeyStore.nccpKeyStore:Ljava/security/KeyStore;
        //    43: aload_3        
        //    44: astore_2       
        //    45: aload           4
        //    47: astore_1       
        //    48: aload_0        
        //    49: invokevirtual   android/content/Context.getResources:()Landroid/content/res/Resources;
        //    52: ldc             2131099648
        //    54: invokevirtual   android/content/res/Resources.openRawResource:(I)Ljava/io/InputStream;
        //    57: astore_0       
        //    58: aload_0        
        //    59: astore_2       
        //    60: aload_0        
        //    61: astore_1       
        //    62: getstatic       com/netflix/mediaclient/nccp/NccpKeyStore.nccpKeyStore:Ljava/security/KeyStore;
        //    65: aload_0        
        //    66: ldc             "spyder"
        //    68: invokevirtual   java/lang/String.toCharArray:()[C
        //    71: invokevirtual   java/security/KeyStore.load:(Ljava/io/InputStream;[C)V
        //    74: aload_0        
        //    75: ifnull          82
        //    78: aload_0        
        //    79: invokevirtual   java/io/InputStream.close:()V
        //    82: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //    85: ifeq            91
        //    88: invokestatic    com/netflix/mediaclient/nccp/NccpKeyStore.dump:()V
        //    91: ldc             "nf_nccp"
        //    93: ldc             "Load NCCP trust store done."
        //    95: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    98: pop            
        //    99: return         
        //   100: astore_0       
        //   101: aload_2        
        //   102: astore_1       
        //   103: ldc             "nf_nccp"
        //   105: ldc             "We failed to load NCCP trust store from res/raw"
        //   107: aload_0        
        //   108: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   111: pop            
        //   112: aload_2        
        //   113: ifnull          82
        //   116: aload_2        
        //   117: invokevirtual   java/io/InputStream.close:()V
        //   120: goto            82
        //   123: astore_0       
        //   124: goto            82
        //   127: astore_0       
        //   128: aload_1        
        //   129: ifnull          136
        //   132: aload_1        
        //   133: invokevirtual   java/io/InputStream.close:()V
        //   136: aload_0        
        //   137: athrow         
        //   138: astore_0       
        //   139: goto            82
        //   142: astore_1       
        //   143: goto            136
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  35     43     100    127    Ljava/lang/Throwable;
        //  35     43     127    138    Any
        //  48     58     100    127    Ljava/lang/Throwable;
        //  48     58     127    138    Any
        //  62     74     100    127    Ljava/lang/Throwable;
        //  62     74     127    138    Any
        //  78     82     138    142    Ljava/io/IOException;
        //  103    112    127    138    Any
        //  116    120    123    127    Ljava/io/IOException;
        //  132    136    142    146    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 79, Size: 79
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
}
