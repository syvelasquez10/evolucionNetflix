// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import android.os.ParcelFileDescriptor;
import java.util.concurrent.Callable;

class aw$2 implements Callable<Boolean>
{
    final /* synthetic */ byte[] CY;
    final /* synthetic */ aw avI;
    final /* synthetic */ ParcelFileDescriptor avJ;
    
    aw$2(final aw avI, final ParcelFileDescriptor avJ, final byte[] cy) {
        this.avI = avI;
        this.avJ = avJ;
        this.CY = cy;
    }
    
    public Boolean pY() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: ldc             "WearableClient"
        //     2: iconst_3       
        //     3: invokestatic    android/util/Log.isLoggable:(Ljava/lang/String;I)Z
        //     6: ifeq            37
        //     9: ldc             "WearableClient"
        //    11: new             Ljava/lang/StringBuilder;
        //    14: dup            
        //    15: invokespecial   java/lang/StringBuilder.<init>:()V
        //    18: ldc             "processAssets: writing data to FD : "
        //    20: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    23: aload_0        
        //    24: getfield        com/google/android/gms/wearable/internal/aw$2.avJ:Landroid/os/ParcelFileDescriptor;
        //    27: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    30: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    33: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    36: pop            
        //    37: new             Landroid/os/ParcelFileDescriptor$AutoCloseOutputStream;
        //    40: dup            
        //    41: aload_0        
        //    42: getfield        com/google/android/gms/wearable/internal/aw$2.avJ:Landroid/os/ParcelFileDescriptor;
        //    45: invokespecial   android/os/ParcelFileDescriptor$AutoCloseOutputStream.<init>:(Landroid/os/ParcelFileDescriptor;)V
        //    48: astore_1       
        //    49: aload_1        
        //    50: aload_0        
        //    51: getfield        com/google/android/gms/wearable/internal/aw$2.CY:[B
        //    54: invokevirtual   android/os/ParcelFileDescriptor$AutoCloseOutputStream.write:([B)V
        //    57: aload_1        
        //    58: invokevirtual   android/os/ParcelFileDescriptor$AutoCloseOutputStream.flush:()V
        //    61: ldc             "WearableClient"
        //    63: iconst_3       
        //    64: invokestatic    android/util/Log.isLoggable:(Ljava/lang/String;I)Z
        //    67: ifeq            98
        //    70: ldc             "WearableClient"
        //    72: new             Ljava/lang/StringBuilder;
        //    75: dup            
        //    76: invokespecial   java/lang/StringBuilder.<init>:()V
        //    79: ldc             "processAssets: wrote data: "
        //    81: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    84: aload_0        
        //    85: getfield        com/google/android/gms/wearable/internal/aw$2.avJ:Landroid/os/ParcelFileDescriptor;
        //    88: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    91: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    94: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    97: pop            
        //    98: iconst_1       
        //    99: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   102: astore_2       
        //   103: ldc             "WearableClient"
        //   105: iconst_3       
        //   106: invokestatic    android/util/Log.isLoggable:(Ljava/lang/String;I)Z
        //   109: ifeq            140
        //   112: ldc             "WearableClient"
        //   114: new             Ljava/lang/StringBuilder;
        //   117: dup            
        //   118: invokespecial   java/lang/StringBuilder.<init>:()V
        //   121: ldc             "processAssets: closing: "
        //   123: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   126: aload_0        
        //   127: getfield        com/google/android/gms/wearable/internal/aw$2.avJ:Landroid/os/ParcelFileDescriptor;
        //   130: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   133: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   136: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   139: pop            
        //   140: aload_1        
        //   141: invokevirtual   android/os/ParcelFileDescriptor$AutoCloseOutputStream.close:()V
        //   144: aload_2        
        //   145: areturn        
        //   146: astore_2       
        //   147: ldc             "WearableClient"
        //   149: new             Ljava/lang/StringBuilder;
        //   152: dup            
        //   153: invokespecial   java/lang/StringBuilder.<init>:()V
        //   156: ldc             "processAssets: writing data failed: "
        //   158: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   161: aload_0        
        //   162: getfield        com/google/android/gms/wearable/internal/aw$2.avJ:Landroid/os/ParcelFileDescriptor;
        //   165: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   168: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   171: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   174: pop            
        //   175: ldc             "WearableClient"
        //   177: iconst_3       
        //   178: invokestatic    android/util/Log.isLoggable:(Ljava/lang/String;I)Z
        //   181: ifeq            212
        //   184: ldc             "WearableClient"
        //   186: new             Ljava/lang/StringBuilder;
        //   189: dup            
        //   190: invokespecial   java/lang/StringBuilder.<init>:()V
        //   193: ldc             "processAssets: closing: "
        //   195: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   198: aload_0        
        //   199: getfield        com/google/android/gms/wearable/internal/aw$2.avJ:Landroid/os/ParcelFileDescriptor;
        //   202: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   205: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   208: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   211: pop            
        //   212: aload_1        
        //   213: invokevirtual   android/os/ParcelFileDescriptor$AutoCloseOutputStream.close:()V
        //   216: iconst_0       
        //   217: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   220: areturn        
        //   221: astore_2       
        //   222: ldc             "WearableClient"
        //   224: iconst_3       
        //   225: invokestatic    android/util/Log.isLoggable:(Ljava/lang/String;I)Z
        //   228: ifeq            259
        //   231: ldc             "WearableClient"
        //   233: new             Ljava/lang/StringBuilder;
        //   236: dup            
        //   237: invokespecial   java/lang/StringBuilder.<init>:()V
        //   240: ldc             "processAssets: closing: "
        //   242: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   245: aload_0        
        //   246: getfield        com/google/android/gms/wearable/internal/aw$2.avJ:Landroid/os/ParcelFileDescriptor;
        //   249: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   252: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   255: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   258: pop            
        //   259: aload_1        
        //   260: invokevirtual   android/os/ParcelFileDescriptor$AutoCloseOutputStream.close:()V
        //   263: aload_2        
        //   264: athrow         
        //   265: astore_1       
        //   266: goto            263
        //   269: astore_1       
        //   270: goto            216
        //   273: astore_1       
        //   274: aload_2        
        //   275: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  49     98     146    221    Ljava/io/IOException;
        //  49     98     221    269    Any
        //  98     103    146    221    Ljava/io/IOException;
        //  98     103    221    269    Any
        //  103    140    273    276    Ljava/io/IOException;
        //  140    144    273    276    Ljava/io/IOException;
        //  147    175    221    269    Any
        //  175    212    269    273    Ljava/io/IOException;
        //  212    216    269    273    Ljava/io/IOException;
        //  222    259    265    269    Ljava/io/IOException;
        //  259    263    265    269    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0140:
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
