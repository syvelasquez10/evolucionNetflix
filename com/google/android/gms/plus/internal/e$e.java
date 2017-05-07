// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.plus.People$LoadPeopleResult;
import com.google.android.gms.common.api.BaseImplementation$b;

final class e$e extends a
{
    private final BaseImplementation$b<People$LoadPeopleResult> alk;
    final /* synthetic */ e all;
    
    public e$e(final e all, final BaseImplementation$b<People$LoadPeopleResult> alk) {
        this.all = all;
        this.alk = alk;
    }
    
    @Override
    public void a(final DataHolder p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   com/google/android/gms/common/data/DataHolder.gz:()Landroid/os/Bundle;
        //     4: ifnull          87
        //     7: aload_1        
        //     8: invokevirtual   com/google/android/gms/common/data/DataHolder.gz:()Landroid/os/Bundle;
        //    11: ldc             "pendingIntent"
        //    13: invokevirtual   android/os/Bundle.getParcelable:(Ljava/lang/String;)Landroid/os/Parcelable;
        //    16: checkcast       Landroid/app/PendingIntent;
        //    19: astore_3       
        //    20: new             Lcom/google/android/gms/common/api/Status;
        //    23: dup            
        //    24: aload_1        
        //    25: invokevirtual   com/google/android/gms/common/data/DataHolder.getStatusCode:()I
        //    28: aconst_null    
        //    29: aload_3        
        //    30: invokespecial   com/google/android/gms/common/api/Status.<init>:(ILjava/lang/String;Landroid/app/PendingIntent;)V
        //    33: astore_3       
        //    34: aload_3        
        //    35: invokevirtual   com/google/android/gms/common/api/Status.isSuccess:()Z
        //    38: ifne            84
        //    41: aload_1        
        //    42: ifnull          84
        //    45: aload_1        
        //    46: invokevirtual   com/google/android/gms/common/data/DataHolder.isClosed:()Z
        //    49: ifne            56
        //    52: aload_1        
        //    53: invokevirtual   com/google/android/gms/common/data/DataHolder.close:()V
        //    56: aconst_null    
        //    57: astore_1       
        //    58: aload_0        
        //    59: getfield        com/google/android/gms/plus/internal/e$e.all:Lcom/google/android/gms/plus/internal/e;
        //    62: new             Lcom/google/android/gms/plus/internal/e$f;
        //    65: dup            
        //    66: aload_0        
        //    67: getfield        com/google/android/gms/plus/internal/e$e.all:Lcom/google/android/gms/plus/internal/e;
        //    70: aload_0        
        //    71: getfield        com/google/android/gms/plus/internal/e$e.alk:Lcom/google/android/gms/common/api/BaseImplementation$b;
        //    74: aload_3        
        //    75: aload_1        
        //    76: aload_2        
        //    77: invokespecial   com/google/android/gms/plus/internal/e$f.<init>:(Lcom/google/android/gms/plus/internal/e;Lcom/google/android/gms/common/api/BaseImplementation$b;Lcom/google/android/gms/common/api/Status;Lcom/google/android/gms/common/data/DataHolder;Ljava/lang/String;)V
        //    80: invokevirtual   com/google/android/gms/plus/internal/e.a:(Lcom/google/android/gms/common/internal/d$b;)V
        //    83: return         
        //    84: goto            58
        //    87: aconst_null    
        //    88: astore_3       
        //    89: goto            20
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalArgumentException: Argument 'typeArguments' must not have any null elements.
        //     at com.strobel.core.VerifyArgument.noNullElementsAndNotEmpty(VerifyArgument.java:145)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedType.makeGenericType(CoreMetadataFactory.java:570)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory.makeParameterizedType(CoreMetadataFactory.java:156)
        //     at com.strobel.assembler.metadata.signatures.Reifier.visitClassTypeSignature(Reifier.java:125)
        //     at com.strobel.assembler.metadata.signatures.ClassTypeSignature.accept(ClassTypeSignature.java:46)
        //     at com.strobel.assembler.metadata.MetadataParser.parseClassSignature(MetadataParser.java:394)
        //     at com.strobel.assembler.metadata.ClassFileReader.populateBaseTypes(ClassFileReader.java:665)
        //     at com.strobel.assembler.metadata.ClassFileReader.readClass(ClassFileReader.java:438)
        //     at com.strobel.assembler.metadata.ClassFileReader.readClass(ClassFileReader.java:366)
        //     at com.strobel.assembler.metadata.MetadataSystem.resolveType(MetadataSystem.java:124)
        //     at com.strobel.decompiler.NoRetryMetadataSystem.resolveType(DecompilerDriver.java:463)
        //     at com.strobel.assembler.metadata.MetadataSystem.resolveCore(MetadataSystem.java:76)
        //     at com.strobel.assembler.metadata.MetadataResolver.resolve(MetadataResolver.java:104)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedGenericType.resolve(CoreMetadataFactory.java:717)
        //     at com.strobel.assembler.metadata.MetadataHelper$9.visitClassType(MetadataHelper.java:2049)
        //     at com.strobel.assembler.metadata.MetadataHelper$9.visitParameterizedType(MetadataHelper.java:2100)
        //     at com.strobel.assembler.metadata.MetadataHelper$9.visitParameterizedType(MetadataHelper.java:2012)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedGenericType.accept(CoreMetadataFactory.java:626)
        //     at com.strobel.assembler.metadata.DefaultTypeVisitor.visit(DefaultTypeVisitor.java:25)
        //     at com.strobel.assembler.metadata.DefaultTypeVisitor.visit(DefaultTypeVisitor.java:21)
        //     at com.strobel.assembler.metadata.MetadataHelper.getSuperType(MetadataHelper.java:1223)
        //     at com.strobel.assembler.metadata.MetadataHelper$8.visitClassType(MetadataHelper.java:1948)
        //     at com.strobel.assembler.metadata.MetadataHelper$8.visitParameterizedType(MetadataHelper.java:1993)
        //     at com.strobel.assembler.metadata.MetadataHelper$8.visitParameterizedType(MetadataHelper.java:1931)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedGenericType.accept(CoreMetadataFactory.java:626)
        //     at com.strobel.assembler.metadata.DefaultTypeVisitor.visit(DefaultTypeVisitor.java:25)
        //     at com.strobel.assembler.metadata.MetadataHelper.asSuper(MetadataHelper.java:739)
        //     at com.strobel.assembler.metadata.MetadataHelper$8.visitClassType(MetadataHelper.java:1954)
        //     at com.strobel.assembler.metadata.MetadataHelper$8.visitClassType(MetadataHelper.java:1931)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedType.accept(CoreMetadataFactory.java:550)
        //     at com.strobel.assembler.metadata.DefaultTypeVisitor.visit(DefaultTypeVisitor.java:25)
        //     at com.strobel.assembler.metadata.MetadataHelper.asSuper(MetadataHelper.java:739)
        //     at com.strobel.assembler.metadata.MetadataHelper$6.visitClassType(MetadataHelper.java:1789)
        //     at com.strobel.assembler.metadata.MetadataHelper$6.visitClassType(MetadataHelper.java:1753)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedType.accept(CoreMetadataFactory.java:550)
        //     at com.strobel.assembler.metadata.DefaultTypeVisitor.visit(DefaultTypeVisitor.java:25)
        //     at com.strobel.assembler.metadata.MetadataHelper.isSubType(MetadataHelper.java:1261)
        //     at com.strobel.assembler.metadata.MetadataHelper.isSubType(MetadataHelper.java:585)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:922)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2505)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:672)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypesForVariables(TypeAnalysis.java:586)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:397)
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
