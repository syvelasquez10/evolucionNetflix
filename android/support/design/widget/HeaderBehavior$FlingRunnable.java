// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.view.View;

class HeaderBehavior$FlingRunnable implements Runnable
{
    private final mLayout;
    private final CoordinatorLayout mParent;
    
    HeaderBehavior$FlingRunnable(final HeaderBehavior p0, final CoordinatorLayout p1, final View p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: aload_1        
        //     2: putfield        android/support/design/widget/HeaderBehavior$FlingRunnable.this$0:Landroid/support/design/widget/HeaderBehavior;
        //     5: aload_0        
        //     6: invokespecial   java/lang/Object.<init>:()V
        //     9: aload_0        
        //    10: aload_2        
        //    11: putfield        android/support/design/widget/HeaderBehavior$FlingRunnable.mParent:Landroid/support/design/widget/CoordinatorLayout;
        //    14: aload_0        
        //    15: aload_3        
        //    16: putfield        android/support/design/widget/HeaderBehavior$FlingRunnable.mLayout:Landroid/view/View;
        //    19: return         
        //    Signature:
        //  (Landroid/support/design/widget/HeaderBehavior;Landroid/support/design/widget/CoordinatorLayout;Landroid/view/View;)V [from metadata: (Landroid/support/design/widget/CoordinatorLayout;TV;)V]
        //  
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.assembler.metadata.MetadataResolver.getField(MetadataResolver.java:219)
        //     at com.strobel.assembler.metadata.MetadataResolver.getField(MetadataResolver.java:143)
        //     at com.strobel.assembler.metadata.MetadataResolver.resolve(MetadataResolver.java:117)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedType.resolve(CoreMetadataFactory.java:594)
        //     at com.strobel.assembler.metadata.FieldReference.resolve(FieldReference.java:61)
        //     at com.strobel.decompiler.ast.TypeAnalysis.getFieldType(TypeAnalysis.java:2909)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1061)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:672)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:655)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:365)
        //     at com.strobel.decompiler.ast.TypeAnalysis.run(TypeAnalysis.java:96)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:109)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createConstructor(AstBuilder.java:692)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:529)
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
    public void run() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        android/support/design/widget/HeaderBehavior$FlingRunnable.mLayout:Landroid/view/View;
        //     4: ifnull          64
        //     7: aload_0        
        //     8: getfield        android/support/design/widget/HeaderBehavior$FlingRunnable.this$0:Landroid/support/design/widget/HeaderBehavior;
        //    11: getfield        android/support/design/widget/HeaderBehavior.mScroller:Landroid/support/v4/widget/ScrollerCompat;
        //    14: ifnull          64
        //    17: aload_0        
        //    18: getfield        android/support/design/widget/HeaderBehavior$FlingRunnable.this$0:Landroid/support/design/widget/HeaderBehavior;
        //    21: getfield        android/support/design/widget/HeaderBehavior.mScroller:Landroid/support/v4/widget/ScrollerCompat;
        //    24: invokevirtual   android/support/v4/widget/ScrollerCompat.computeScrollOffset:()Z
        //    27: ifeq            65
        //    30: aload_0        
        //    31: getfield        android/support/design/widget/HeaderBehavior$FlingRunnable.this$0:Landroid/support/design/widget/HeaderBehavior;
        //    34: aload_0        
        //    35: getfield        android/support/design/widget/HeaderBehavior$FlingRunnable.mParent:Landroid/support/design/widget/CoordinatorLayout;
        //    38: aload_0        
        //    39: getfield        android/support/design/widget/HeaderBehavior$FlingRunnable.mLayout:Landroid/view/View;
        //    42: aload_0        
        //    43: getfield        android/support/design/widget/HeaderBehavior$FlingRunnable.this$0:Landroid/support/design/widget/HeaderBehavior;
        //    46: getfield        android/support/design/widget/HeaderBehavior.mScroller:Landroid/support/v4/widget/ScrollerCompat;
        //    49: invokevirtual   android/support/v4/widget/ScrollerCompat.getCurrY:()I
        //    52: invokevirtual   android/support/design/widget/HeaderBehavior.setHeaderTopBottomOffset:(Landroid/support/design/widget/CoordinatorLayout;Landroid/view/View;I)I
        //    55: pop            
        //    56: aload_0        
        //    57: getfield        android/support/design/widget/HeaderBehavior$FlingRunnable.mLayout:Landroid/view/View;
        //    60: aload_0        
        //    61: invokestatic    android/support/v4/view/ViewCompat.postOnAnimation:(Landroid/view/View;Ljava/lang/Runnable;)V
        //    64: return         
        //    65: aload_0        
        //    66: getfield        android/support/design/widget/HeaderBehavior$FlingRunnable.this$0:Landroid/support/design/widget/HeaderBehavior;
        //    69: aload_0        
        //    70: getfield        android/support/design/widget/HeaderBehavior$FlingRunnable.mParent:Landroid/support/design/widget/CoordinatorLayout;
        //    73: aload_0        
        //    74: getfield        android/support/design/widget/HeaderBehavior$FlingRunnable.mLayout:Landroid/view/View;
        //    77: invokevirtual   android/support/design/widget/HeaderBehavior.onFlingFinished:(Landroid/support/design/widget/CoordinatorLayout;Landroid/view/View;)V
        //    80: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.assembler.metadata.MetadataResolver.getField(MetadataResolver.java:219)
        //     at com.strobel.assembler.metadata.MetadataResolver.getField(MetadataResolver.java:143)
        //     at com.strobel.assembler.metadata.MetadataResolver.resolve(MetadataResolver.java:117)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedType.resolve(CoreMetadataFactory.java:594)
        //     at com.strobel.assembler.metadata.FieldReference.resolve(FieldReference.java:61)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1036)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:672)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:655)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferBinaryExpression(TypeAnalysis.java:2104)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1531)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:778)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1551)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:672)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:655)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:365)
        //     at com.strobel.decompiler.ast.TypeAnalysis.run(TypeAnalysis.java:96)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:109)
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
