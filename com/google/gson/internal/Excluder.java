// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson.internal;

import java.util.Collection;
import java.util.ArrayList;
import com.google.gson.FieldAttributes;
import com.google.gson.annotations.Expose;
import java.lang.reflect.Field;
import java.util.Iterator;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.annotations.Until;
import com.google.gson.annotations.Since;
import java.util.Collections;
import com.google.gson.ExclusionStrategy;
import java.util.List;
import com.google.gson.TypeAdapterFactory;

public final class Excluder implements TypeAdapterFactory, Cloneable
{
    public static final Excluder DEFAULT;
    private static final double IGNORE_VERSIONS = -1.0;
    private List<ExclusionStrategy> deserializationStrategies;
    private int modifiers;
    private boolean requireExpose;
    private List<ExclusionStrategy> serializationStrategies;
    private boolean serializeInnerClasses;
    private double version;
    
    static {
        DEFAULT = new Excluder();
    }
    
    public Excluder() {
        this.version = -1.0;
        this.modifiers = 136;
        this.serializeInnerClasses = true;
        this.serializationStrategies = Collections.emptyList();
        this.deserializationStrategies = Collections.emptyList();
    }
    
    private boolean isAnonymousOrLocal(final Class<?> clazz) {
        return !Enum.class.isAssignableFrom(clazz) && (clazz.isAnonymousClass() || clazz.isLocalClass());
    }
    
    private boolean isInnerClass(final Class<?> clazz) {
        return clazz.isMemberClass() && !this.isStatic(clazz);
    }
    
    private boolean isStatic(final Class<?> clazz) {
        return (clazz.getModifiers() & 0x8) != 0x0;
    }
    
    private boolean isValidSince(final Since since) {
        return since == null || since.value() <= this.version;
    }
    
    private boolean isValidUntil(final Until until) {
        return until == null || until.value() > this.version;
    }
    
    private boolean isValidVersion(final Since since, final Until until) {
        return this.isValidSince(since) && this.isValidUntil(until);
    }
    
    @Override
    protected Excluder clone() {
        try {
            return (Excluder)super.clone();
        }
        catch (CloneNotSupportedException ex) {
            throw new AssertionError();
        }
    }
    
    @Override
    public <T> TypeAdapter<T> create(final Gson p0, final TypeToken<T> p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2        
        //     1: invokevirtual   com/google/gson/reflect/TypeToken.getRawType:()Ljava/lang/Class;
        //     4: astore          5
        //     6: aload_0        
        //     7: aload           5
        //     9: iconst_1       
        //    10: invokevirtual   com/google/gson/internal/Excluder.excludeClass:(Ljava/lang/Class;Z)Z
        //    13: istore_3       
        //    14: aload_0        
        //    15: aload           5
        //    17: iconst_0       
        //    18: invokevirtual   com/google/gson/internal/Excluder.excludeClass:(Ljava/lang/Class;Z)Z
        //    21: istore          4
        //    23: iload_3        
        //    24: ifne            34
        //    27: iload           4
        //    29: ifne            34
        //    32: aconst_null    
        //    33: areturn        
        //    34: new             new            !!! ERROR
        //    37: dup            
        //    38: aload_0        
        //    39: iload           4
        //    41: iload_3        
        //    42: aload_1        
        //    43: aload_2        
        //    44: invokespecial   invokespecial  !!! ERROR
        //    47: areturn        
        //    Signature:
        //  <T:Ljava/lang/Object;>(Lcom/google/gson/Gson;Lcom/google/gson/reflect/TypeToken<TT;>;)Lcom/google/gson/TypeAdapter<TT;>;
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
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedType.resolve(CoreMetadataFactory.java:589)
        //     at com.strobel.assembler.metadata.MetadataResolver.resolve(MetadataResolver.java:128)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedType.resolve(CoreMetadataFactory.java:599)
        //     at com.strobel.assembler.metadata.MethodReference.resolve(MethodReference.java:172)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2428)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
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
    
    public Excluder disableInnerClassSerialization() {
        final Excluder clone = this.clone();
        clone.serializeInnerClasses = false;
        return clone;
    }
    
    public boolean excludeClass(final Class<?> clazz, final boolean b) {
        if (this.version != -1.0 && !this.isValidVersion(clazz.getAnnotation(Since.class), clazz.getAnnotation(Until.class))) {
            return true;
        }
        if (!this.serializeInnerClasses && this.isInnerClass(clazz)) {
            return true;
        }
        if (this.isAnonymousOrLocal(clazz)) {
            return true;
        }
        List<ExclusionStrategy> list;
        if (b) {
            list = this.serializationStrategies;
        }
        else {
            list = this.deserializationStrategies;
        }
        final Iterator<ExclusionStrategy> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().shouldSkipClass(clazz)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean excludeField(final Field field, final boolean b) {
        if ((this.modifiers & field.getModifiers()) != 0x0) {
            return true;
        }
        if (this.version != -1.0 && !this.isValidVersion(field.getAnnotation(Since.class), field.getAnnotation(Until.class))) {
            return true;
        }
        if (field.isSynthetic()) {
            return true;
        }
        Label_0106: {
            if (this.requireExpose) {
                final Expose expose = field.getAnnotation(Expose.class);
                if (expose != null) {
                    if (b) {
                        if (expose.serialize()) {
                            break Label_0106;
                        }
                    }
                    else if (expose.deserialize()) {
                        break Label_0106;
                    }
                }
                return true;
            }
        }
        if (!this.serializeInnerClasses && this.isInnerClass(field.getType())) {
            return true;
        }
        if (this.isAnonymousOrLocal(field.getType())) {
            return true;
        }
        List<ExclusionStrategy> list;
        if (b) {
            list = this.serializationStrategies;
        }
        else {
            list = this.deserializationStrategies;
        }
        if (!list.isEmpty()) {
            final FieldAttributes fieldAttributes = new FieldAttributes(field);
            final Iterator<ExclusionStrategy> iterator = list.iterator();
            while (iterator.hasNext()) {
                if (iterator.next().shouldSkipField(fieldAttributes)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public Excluder excludeFieldsWithoutExposeAnnotation() {
        final Excluder clone = this.clone();
        clone.requireExpose = true;
        return clone;
    }
    
    public Excluder withExclusionStrategy(final ExclusionStrategy exclusionStrategy, final boolean b, final boolean b2) {
        final Excluder clone = this.clone();
        if (b) {
            (clone.serializationStrategies = new ArrayList<ExclusionStrategy>(this.serializationStrategies)).add(exclusionStrategy);
        }
        if (b2) {
            (clone.deserializationStrategies = new ArrayList<ExclusionStrategy>(this.deserializationStrategies)).add(exclusionStrategy);
        }
        return clone;
    }
    
    public Excluder withModifiers(final int... array) {
        int i = 0;
        final Excluder clone = this.clone();
        clone.modifiers = 0;
        while (i < array.length) {
            clone.modifiers |= array[i];
            ++i;
        }
        return clone;
    }
    
    public Excluder withVersion(final double version) {
        final Excluder clone = this.clone();
        clone.version = version;
        return clone;
    }
}
