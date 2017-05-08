// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfig$Cell;
import com.netflix.mediaclient.service.configuration.PersistentConfig;
import com.netflix.mediaclient.Log;
import android.renderscript.RenderScript;
import android.graphics.Bitmap;
import android.content.Context;
import android.app.Activity;

public final class Coppola2Utils
{
    private static final String TAG = "Coppola2Utils";
    
    public static void forceToPortraitIfNeeded(final Activity activity) {
        if (isCoppolaDiscovery((Context)activity)) {
            activity.setRequestedOrientation(1);
        }
    }
    
    public static Bitmap getBlurredBitmap(final Context p0, final Bitmap p1, final int p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: ldc             "Coppola2Utils"
        //     2: ldc             "Creating blur bitmap started.."
        //     4: invokestatic    com/netflix/mediaclient/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //     7: pop            
        //     8: invokestatic    java/lang/System.currentTimeMillis:()J
        //    11: lstore          4
        //    13: aload_0        
        //    14: invokestatic    com/netflix/mediaclient/util/Coppola2Utils.getRenderScript:(Landroid/content/Context;)Landroid/renderscript/RenderScript;
        //    17: astore          9
        //    19: aload           9
        //    21: ifnonnull       26
        //    24: aload_1        
        //    25: areturn        
        //    26: aload_1        
        //    27: invokevirtual   android/graphics/Bitmap.getConfig:()Landroid/graphics/Bitmap$Config;
        //    30: astore_0       
        //    31: aload_0        
        //    32: ifnonnull       203
        //    35: getstatic       android/graphics/Bitmap$Config.ARGB_8888:Landroid/graphics/Bitmap$Config;
        //    38: astore_0       
        //    39: ldc             "Coppola2Utils"
        //    41: ldc             "Config was null so setting 8888 by default"
        //    43: invokestatic    com/netflix/mediaclient/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //    46: pop            
        //    47: iconst_1       
        //    48: istore_3       
        //    49: aload_0        
        //    50: getstatic       android/graphics/Bitmap$Config.ARGB_8888:Landroid/graphics/Bitmap$Config;
        //    53: if_acmpeq       263
        //    56: aload_1        
        //    57: getstatic       android/graphics/Bitmap$Config.ARGB_8888:Landroid/graphics/Bitmap$Config;
        //    60: iconst_1       
        //    61: invokevirtual   android/graphics/Bitmap.copy:(Landroid/graphics/Bitmap$Config;Z)Landroid/graphics/Bitmap;
        //    64: astore          8
        //    66: getstatic       android/graphics/Bitmap$Config.ARGB_8888:Landroid/graphics/Bitmap$Config;
        //    69: astore_0       
        //    70: ldc             "Coppola2Utils"
        //    72: ldc             "Convertion to ARGB_8888 finished"
        //    74: invokestatic    com/netflix/mediaclient/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //    77: pop            
        //    78: aload           8
        //    80: aload_0        
        //    81: iconst_1       
        //    82: invokevirtual   android/graphics/Bitmap.copy:(Landroid/graphics/Bitmap$Config;Z)Landroid/graphics/Bitmap;
        //    85: astore_0       
        //    86: aload           9
        //    88: aload           8
        //    90: getstatic       android/renderscript/Allocation$MipmapControl.MIPMAP_NONE:Landroid/renderscript/Allocation$MipmapControl;
        //    93: iload_3        
        //    94: invokestatic    android/renderscript/Allocation.createFromBitmap:(Landroid/renderscript/RenderScript;Landroid/graphics/Bitmap;Landroid/renderscript/Allocation$MipmapControl;I)Landroid/renderscript/Allocation;
        //    97: astore_1       
        //    98: aload           9
        //   100: aload_1        
        //   101: invokevirtual   android/renderscript/Allocation.getType:()Landroid/renderscript/Type;
        //   104: invokestatic    android/renderscript/Allocation.createTyped:(Landroid/renderscript/RenderScript;Landroid/renderscript/Type;)Landroid/renderscript/Allocation;
        //   107: astore          8
        //   109: aload           9
        //   111: aload           9
        //   113: invokestatic    android/renderscript/Element.U8_4:(Landroid/renderscript/RenderScript;)Landroid/renderscript/Element;
        //   116: invokestatic    android/renderscript/ScriptIntrinsicBlur.create:(Landroid/renderscript/RenderScript;Landroid/renderscript/Element;)Landroid/renderscript/ScriptIntrinsicBlur;
        //   119: astore          10
        //   121: aload           10
        //   123: iload_2        
        //   124: i2f            
        //   125: invokevirtual   android/renderscript/ScriptIntrinsicBlur.setRadius:(F)V
        //   128: aload           10
        //   130: aload_1        
        //   131: invokevirtual   android/renderscript/ScriptIntrinsicBlur.setInput:(Landroid/renderscript/Allocation;)V
        //   134: aload           10
        //   136: aload           8
        //   138: invokevirtual   android/renderscript/ScriptIntrinsicBlur.forEach:(Landroid/renderscript/Allocation;)V
        //   141: aload           8
        //   143: aload_0        
        //   144: invokevirtual   android/renderscript/Allocation.copyTo:(Landroid/graphics/Bitmap;)V
        //   147: aload           9
        //   149: invokevirtual   android/renderscript/RenderScript.destroy:()V
        //   152: aload_0        
        //   153: astore          8
        //   155: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   158: ifeq            200
        //   161: invokestatic    java/lang/System.currentTimeMillis:()J
        //   164: lstore          6
        //   166: ldc             "Coppola2Utils"
        //   168: new             Ljava/lang/StringBuilder;
        //   171: dup            
        //   172: invokespecial   java/lang/StringBuilder.<init>:()V
        //   175: ldc             "Finished creating blur bitmap. Time spend: "
        //   177: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   180: lload           6
        //   182: lload           4
        //   184: lsub           
        //   185: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   188: ldc             "ms"
        //   190: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   193: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   196: invokestatic    com/netflix/mediaclient/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //   199: pop            
        //   200: aload           8
        //   202: areturn        
        //   203: sipush          129
        //   206: istore_3       
        //   207: goto            49
        //   210: astore_0       
        //   211: aload_1        
        //   212: astore          8
        //   214: aload_0        
        //   215: astore_1       
        //   216: aload           8
        //   218: astore_0       
        //   219: aload_0        
        //   220: astore          8
        //   222: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   225: ifeq            155
        //   228: ldc             "Coppola2Utils"
        //   230: new             Ljava/lang/StringBuilder;
        //   233: dup            
        //   234: invokespecial   java/lang/StringBuilder.<init>:()V
        //   237: ldc             "Got exception inside blurRenderScript(): "
        //   239: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   242: aload_1        
        //   243: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   246: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   249: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   252: pop            
        //   253: aload_0        
        //   254: astore          8
        //   256: goto            155
        //   259: astore_1       
        //   260: goto            219
        //   263: aload_1        
        //   264: astore          8
        //   266: goto            78
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  26     31     210    219    Ljava/lang/Exception;
        //  35     47     210    219    Ljava/lang/Exception;
        //  49     78     210    219    Ljava/lang/Exception;
        //  78     86     210    219    Ljava/lang/Exception;
        //  86     152    259    263    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0155:
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
    
    private static RenderScript getRenderScript(final Context context) {
        final RenderScript renderScript = null;
        try {
            return RenderScript.create(context);
        }
        catch (Exception ex) {
            final RenderScript create = renderScript;
            if (Log.isLoggable()) {
                Log.e("Coppola2Utils", "Got exception inside getRenderScript: " + ex);
                return null;
            }
            return create;
        }
    }
    
    public static boolean isCoppolaDiscovery(final Context context) {
        return PersistentConfig.getCoppola2ABTestCell(context).ordinal() > ABTestConfig$Cell.CELL_ONE.ordinal() && PersistentConfig.getCoppola2ABTestCell(context).ordinal() < ABTestConfig$Cell.CELL_EIGHT.ordinal();
    }
    
    public static boolean isCoppolaWithoutNormalCW(final Context context) {
        return PersistentConfig.getCoppola2ABTestCell(context).ordinal() == ABTestConfig$Cell.CELL_FOUR.ordinal() || PersistentConfig.getCoppola2ABTestCell(context).ordinal() == ABTestConfig$Cell.CELL_SEVEN.ordinal();
    }
    
    public static boolean shouldHideContinueWatchingLink(final Context context) {
        return PersistentConfig.getCoppola2ABTestCell(context).ordinal() > ABTestConfig$Cell.CELL_FOUR.ordinal() && PersistentConfig.getCoppola2ABTestCell(context).ordinal() < ABTestConfig$Cell.CELL_SEVEN.ordinal();
    }
}
