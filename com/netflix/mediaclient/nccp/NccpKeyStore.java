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
        //    52: ldc             2131034112
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
        //    82: ldc             "nf_nccp"
        //    84: iconst_3       
        //    85: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //    88: ifeq            94
        //    91: invokestatic    com/netflix/mediaclient/nccp/NccpKeyStore.dump:()V
        //    94: ldc             "nf_nccp"
        //    96: ldc             "Load NCCP trust store done."
        //    98: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   101: pop            
        //   102: return         
        //   103: astore_0       
        //   104: aload_2        
        //   105: astore_1       
        //   106: ldc             "nf_nccp"
        //   108: ldc             "We failed to load NCCP trust store from res/raw"
        //   110: aload_0        
        //   111: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   114: pop            
        //   115: aload_2        
        //   116: ifnull          82
        //   119: aload_2        
        //   120: invokevirtual   java/io/InputStream.close:()V
        //   123: goto            82
        //   126: astore_0       
        //   127: goto            82
        //   130: astore_0       
        //   131: aload_1        
        //   132: ifnull          139
        //   135: aload_1        
        //   136: invokevirtual   java/io/InputStream.close:()V
        //   139: aload_0        
        //   140: athrow         
        //   141: astore_0       
        //   142: goto            82
        //   145: astore_1       
        //   146: goto            139
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  35     43     103    130    Ljava/lang/Throwable;
        //  35     43     130    141    Any
        //  48     58     103    130    Ljava/lang/Throwable;
        //  48     58     130    141    Any
        //  62     74     103    130    Ljava/lang/Throwable;
        //  62     74     130    141    Any
        //  78     82     141    145    Ljava/io/IOException;
        //  106    115    130    141    Any
        //  119    123    126    130    Ljava/io/IOException;
        //  135    139    145    149    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 81, Size: 81
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
