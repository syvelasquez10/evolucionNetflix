// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.text.frescosupport;

import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.views.text.TextInlineImageSpan;
import android.net.Uri$Builder;
import java.util.Locale;
import android.content.Context;
import android.net.Uri;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.react.views.text.ReactTextInlineImageShadowNode;

public class FrescoBasedReactTextInlineImageShadowNode extends ReactTextInlineImageShadowNode
{
    private final Object mCallerContext;
    private final AbstractDraweeControllerBuilder mDraweeControllerBuilder;
    private float mHeight;
    private Uri mUri;
    private float mWidth;
    
    public FrescoBasedReactTextInlineImageShadowNode(final AbstractDraweeControllerBuilder mDraweeControllerBuilder, final Object mCallerContext) {
        this.mWidth = Float.NaN;
        this.mHeight = Float.NaN;
        this.mDraweeControllerBuilder = mDraweeControllerBuilder;
        this.mCallerContext = mCallerContext;
    }
    
    private static Uri getResourceDrawableUri(final Context context, String replace) {
        if (replace == null || replace.isEmpty()) {
            return null;
        }
        replace = replace.toLowerCase(Locale.getDefault()).replace("-", "_");
        return new Uri$Builder().scheme("res").path(String.valueOf(context.getResources().getIdentifier(replace, "drawable", context.getPackageName()))).build();
    }
    
    @Override
    public TextInlineImageSpan buildInlineImageSpan() {
        return new FrescoBasedReactTextInlineImageSpan(this.getThemedContext().getResources(), (int)Math.ceil(this.mWidth), (int)Math.ceil(this.mHeight), this.getUri(), this.getDraweeControllerBuilder(), this.getCallerContext());
    }
    
    public Object getCallerContext() {
        return this.mCallerContext;
    }
    
    public AbstractDraweeControllerBuilder getDraweeControllerBuilder() {
        return this.mDraweeControllerBuilder;
    }
    
    public Uri getUri() {
        return this.mUri;
    }
    
    @Override
    public boolean isVirtual() {
        return true;
    }
    
    @Override
    public void setHeight(final float mHeight) {
        this.mHeight = mHeight;
    }
    
    @ReactProp(name = "src")
    public void setSource(final ReadableArray p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore_2       
        //     2: aconst_null    
        //     3: astore          4
        //     5: aload_1        
        //     6: ifnull          18
        //     9: aload_1        
        //    10: invokeinterface com/facebook/react/bridge/ReadableArray.size:()I
        //    15: ifne            74
        //    18: aconst_null    
        //    19: astore_3       
        //    20: aload_3        
        //    21: ifnull          56
        //    24: aload_3        
        //    25: invokestatic    android/net/Uri.parse:(Ljava/lang/String;)Landroid/net/Uri;
        //    28: astore_1       
        //    29: aload_1        
        //    30: invokevirtual   android/net/Uri.getScheme:()Ljava/lang/String;
        //    33: astore_2       
        //    34: aload_2        
        //    35: ifnonnull       102
        //    38: aload           4
        //    40: astore_1       
        //    41: aload_1        
        //    42: astore_2       
        //    43: aload_1        
        //    44: ifnonnull       56
        //    47: aload_0        
        //    48: invokevirtual   com/facebook/react/views/text/frescosupport/FrescoBasedReactTextInlineImageShadowNode.getThemedContext:()Lcom/facebook/react/uimanager/ThemedReactContext;
        //    51: aload_3        
        //    52: invokestatic    com/facebook/react/views/text/frescosupport/FrescoBasedReactTextInlineImageShadowNode.getResourceDrawableUri:(Landroid/content/Context;Ljava/lang/String;)Landroid/net/Uri;
        //    55: astore_2       
        //    56: aload_2        
        //    57: aload_0        
        //    58: getfield        com/facebook/react/views/text/frescosupport/FrescoBasedReactTextInlineImageShadowNode.mUri:Landroid/net/Uri;
        //    61: if_acmpeq       68
        //    64: aload_0        
        //    65: invokevirtual   com/facebook/react/views/text/frescosupport/FrescoBasedReactTextInlineImageShadowNode.markUpdated:()V
        //    68: aload_0        
        //    69: aload_2        
        //    70: putfield        com/facebook/react/views/text/frescosupport/FrescoBasedReactTextInlineImageShadowNode.mUri:Landroid/net/Uri;
        //    73: return         
        //    74: aload_1        
        //    75: iconst_0       
        //    76: invokeinterface com/facebook/react/bridge/ReadableArray.getMap:(I)Lcom/facebook/react/bridge/ReadableMap;
        //    81: ldc             "uri"
        //    83: invokeinterface com/facebook/react/bridge/ReadableMap.getString:(Ljava/lang/String;)Ljava/lang/String;
        //    88: astore_3       
        //    89: goto            20
        //    92: astore_1       
        //    93: aconst_null    
        //    94: astore_1       
        //    95: goto            41
        //    98: astore_2       
        //    99: goto            95
        //   102: goto            41
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  24     29     92     95     Ljava/lang/Exception;
        //  29     34     98     102    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0041:
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
    
    @Override
    public void setWidth(final float mWidth) {
        this.mWidth = mWidth;
    }
}
