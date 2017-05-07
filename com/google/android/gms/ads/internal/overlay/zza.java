// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import com.google.android.gms.internal.zzgk;

@zzgk
public class zza
{
    public boolean zza(final Context p0, final AdLauncherIntentInfoParcel p1, final zzn p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2        
        //     1: ifnonnull       11
        //     4: ldc             "No intent data for launcher overlay."
        //     6: invokestatic    com/google/android/gms/ads/internal/util/client/zzb.zzaE:(Ljava/lang/String;)V
        //     9: iconst_0       
        //    10: ireturn        
        //    11: new             Landroid/content/Intent;
        //    14: dup            
        //    15: invokespecial   android/content/Intent.<init>:()V
        //    18: astore          5
        //    20: aload_2        
        //    21: getfield        com/google/android/gms/ads/internal/overlay/AdLauncherIntentInfoParcel.url:Ljava/lang/String;
        //    24: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //    27: ifeq            37
        //    30: ldc             "Open GMSG did not contain a URL."
        //    32: invokestatic    com/google/android/gms/ads/internal/util/client/zzb.zzaE:(Ljava/lang/String;)V
        //    35: iconst_0       
        //    36: ireturn        
        //    37: aload_2        
        //    38: getfield        com/google/android/gms/ads/internal/overlay/AdLauncherIntentInfoParcel.mimeType:Ljava/lang/String;
        //    41: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //    44: ifne            148
        //    47: aload           5
        //    49: aload_2        
        //    50: getfield        com/google/android/gms/ads/internal/overlay/AdLauncherIntentInfoParcel.url:Ljava/lang/String;
        //    53: invokestatic    android/net/Uri.parse:(Ljava/lang/String;)Landroid/net/Uri;
        //    56: aload_2        
        //    57: getfield        com/google/android/gms/ads/internal/overlay/AdLauncherIntentInfoParcel.mimeType:Ljava/lang/String;
        //    60: invokevirtual   android/content/Intent.setDataAndType:(Landroid/net/Uri;Ljava/lang/String;)Landroid/content/Intent;
        //    63: pop            
        //    64: aload           5
        //    66: ldc             "android.intent.action.VIEW"
        //    68: invokevirtual   android/content/Intent.setAction:(Ljava/lang/String;)Landroid/content/Intent;
        //    71: pop            
        //    72: aload_2        
        //    73: getfield        com/google/android/gms/ads/internal/overlay/AdLauncherIntentInfoParcel.packageName:Ljava/lang/String;
        //    76: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //    79: ifne            92
        //    82: aload           5
        //    84: aload_2        
        //    85: getfield        com/google/android/gms/ads/internal/overlay/AdLauncherIntentInfoParcel.packageName:Ljava/lang/String;
        //    88: invokevirtual   android/content/Intent.setPackage:(Ljava/lang/String;)Landroid/content/Intent;
        //    91: pop            
        //    92: aload_2        
        //    93: getfield        com/google/android/gms/ads/internal/overlay/AdLauncherIntentInfoParcel.zzzY:Ljava/lang/String;
        //    96: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //    99: ifne            178
        //   102: aload_2        
        //   103: getfield        com/google/android/gms/ads/internal/overlay/AdLauncherIntentInfoParcel.zzzY:Ljava/lang/String;
        //   106: ldc             "/"
        //   108: iconst_2       
        //   109: invokevirtual   java/lang/String.split:(Ljava/lang/String;I)[Ljava/lang/String;
        //   112: astore          6
        //   114: aload           6
        //   116: arraylength    
        //   117: iconst_2       
        //   118: if_icmpge       164
        //   121: new             Ljava/lang/StringBuilder;
        //   124: dup            
        //   125: invokespecial   java/lang/StringBuilder.<init>:()V
        //   128: ldc             "Could not parse component name from open GMSG: "
        //   130: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   133: aload_2        
        //   134: getfield        com/google/android/gms/ads/internal/overlay/AdLauncherIntentInfoParcel.zzzY:Ljava/lang/String;
        //   137: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   140: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   143: invokestatic    com/google/android/gms/ads/internal/util/client/zzb.zzaE:(Ljava/lang/String;)V
        //   146: iconst_0       
        //   147: ireturn        
        //   148: aload           5
        //   150: aload_2        
        //   151: getfield        com/google/android/gms/ads/internal/overlay/AdLauncherIntentInfoParcel.url:Ljava/lang/String;
        //   154: invokestatic    android/net/Uri.parse:(Ljava/lang/String;)Landroid/net/Uri;
        //   157: invokevirtual   android/content/Intent.setData:(Landroid/net/Uri;)Landroid/content/Intent;
        //   160: pop            
        //   161: goto            64
        //   164: aload           5
        //   166: aload           6
        //   168: iconst_0       
        //   169: aaload         
        //   170: aload           6
        //   172: iconst_1       
        //   173: aaload         
        //   174: invokevirtual   android/content/Intent.setClassName:(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
        //   177: pop            
        //   178: aload_2        
        //   179: getfield        com/google/android/gms/ads/internal/overlay/AdLauncherIntentInfoParcel.zzzZ:Ljava/lang/String;
        //   182: astore_2       
        //   183: aload_2        
        //   184: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   187: ifne            204
        //   190: aload_2        
        //   191: invokestatic    java/lang/Integer.parseInt:(Ljava/lang/String;)I
        //   194: istore          4
        //   196: aload           5
        //   198: iload           4
        //   200: invokevirtual   android/content/Intent.addFlags:(I)Landroid/content/Intent;
        //   203: pop            
        //   204: new             Ljava/lang/StringBuilder;
        //   207: dup            
        //   208: invokespecial   java/lang/StringBuilder.<init>:()V
        //   211: ldc             "Launching an intent: "
        //   213: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   216: aload           5
        //   218: invokevirtual   android/content/Intent.toURI:()Ljava/lang/String;
        //   221: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   224: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   227: invokestatic    com/google/android/gms/ads/internal/util/client/zzb.v:(Ljava/lang/String;)V
        //   230: aload_1        
        //   231: aload           5
        //   233: invokevirtual   android/content/Context.startActivity:(Landroid/content/Intent;)V
        //   236: aload_3        
        //   237: ifnull          246
        //   240: aload_3        
        //   241: invokeinterface com/google/android/gms/ads/internal/overlay/zzn.zzaO:()V
        //   246: iconst_1       
        //   247: ireturn        
        //   248: astore_2       
        //   249: ldc             "Could not parse intent flags."
        //   251: invokestatic    com/google/android/gms/ads/internal/util/client/zzb.zzaE:(Ljava/lang/String;)V
        //   254: iconst_0       
        //   255: istore          4
        //   257: goto            196
        //   260: astore_1       
        //   261: aload_1        
        //   262: invokevirtual   android/content/ActivityNotFoundException.getMessage:()Ljava/lang/String;
        //   265: invokestatic    com/google/android/gms/ads/internal/util/client/zzb.zzaE:(Ljava/lang/String;)V
        //   268: iconst_0       
        //   269: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                       
        //  -----  -----  -----  -----  -------------------------------------------
        //  190    196    248    260    Ljava/lang/NumberFormatException;
        //  204    236    260    270    Landroid/content/ActivityNotFoundException;
        //  240    246    260    270    Landroid/content/ActivityNotFoundException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0204:
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
