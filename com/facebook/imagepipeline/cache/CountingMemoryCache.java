// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.cache;

import com.android.internal.util.Predicate;
import android.os.SystemClock;
import com.facebook.common.references.CloseableReference;
import java.util.Iterator;
import java.util.ArrayList;
import com.facebook.common.internal.Preconditions;
import java.util.concurrent.TimeUnit;
import com.facebook.common.internal.Supplier;
import com.facebook.common.memory.MemoryTrimmable;

public class CountingMemoryCache<K, V> implements MemoryTrimmable, MemoryCache<K, V>
{
    static final long PARAMS_INTERCHECK_INTERVAL_MS;
    private final CountingMemoryCache$CacheTrimStrategy mCacheTrimStrategy;
    final CountingLruMap<K, CountingMemoryCache$Entry<K, V>> mCachedEntries;
    final CountingLruMap<K, CountingMemoryCache$Entry<K, V>> mExclusiveEntries;
    private long mLastCacheParamsCheck;
    protected MemoryCacheParams mMemoryCacheParams;
    private final Supplier<MemoryCacheParams> mMemoryCacheParamsSupplier;
    private final ValueDescriptor<V> mValueDescriptor;
    
    static {
        PARAMS_INTERCHECK_INTERVAL_MS = TimeUnit.MINUTES.toMillis(5L);
    }
    
    public CountingMemoryCache(final ValueDescriptor<V> p0, final CountingMemoryCache$CacheTrimStrategy p1, final Supplier<MemoryCacheParams> p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokespecial   java/lang/Object.<init>:()V
        //     4: aload_0        
        //     5: aload_1        
        //     6: putfield        com/facebook/imagepipeline/cache/CountingMemoryCache.mValueDescriptor:Lcom/facebook/imagepipeline/cache/ValueDescriptor;
        //     9: aload_0        
        //    10: new             Lcom/facebook/imagepipeline/cache/CountingLruMap;
        //    13: dup            
        //    14: aload_0        
        //    15: aload_1        
        //    16: invokespecial   com/facebook/imagepipeline/cache/CountingMemoryCache.wrapValueDescriptor:(Lcom/facebook/imagepipeline/cache/ValueDescriptor;)Lcom/facebook/imagepipeline/cache/ValueDescriptor;
        //    19: invokespecial   com/facebook/imagepipeline/cache/CountingLruMap.<init>:(Lcom/facebook/imagepipeline/cache/ValueDescriptor;)V
        //    22: putfield        com/facebook/imagepipeline/cache/CountingMemoryCache.mExclusiveEntries:Lcom/facebook/imagepipeline/cache/CountingLruMap;
        //    25: aload_0        
        //    26: new             Lcom/facebook/imagepipeline/cache/CountingLruMap;
        //    29: dup            
        //    30: aload_0        
        //    31: aload_1        
        //    32: invokespecial   com/facebook/imagepipeline/cache/CountingMemoryCache.wrapValueDescriptor:(Lcom/facebook/imagepipeline/cache/ValueDescriptor;)Lcom/facebook/imagepipeline/cache/ValueDescriptor;
        //    35: invokespecial   com/facebook/imagepipeline/cache/CountingLruMap.<init>:(Lcom/facebook/imagepipeline/cache/ValueDescriptor;)V
        //    38: putfield        com/facebook/imagepipeline/cache/CountingMemoryCache.mCachedEntries:Lcom/facebook/imagepipeline/cache/CountingLruMap;
        //    41: aload_0        
        //    42: aload_2        
        //    43: putfield        com/facebook/imagepipeline/cache/CountingMemoryCache.mCacheTrimStrategy:Lcom/facebook/imagepipeline/cache/CountingMemoryCache$CacheTrimStrategy;
        //    46: aload_0        
        //    47: aload_3        
        //    48: putfield        com/facebook/imagepipeline/cache/CountingMemoryCache.mMemoryCacheParamsSupplier:Lcom/facebook/common/internal/Supplier;
        //    51: aload_0        
        //    52: aload_0        
        //    53: getfield        com/facebook/imagepipeline/cache/CountingMemoryCache.mMemoryCacheParamsSupplier:Lcom/facebook/common/internal/Supplier;
        //    56: invokeinterface com/facebook/common/internal/Supplier.get:()Ljava/lang/Object;
        //    61: checkcast       Lcom/facebook/imagepipeline/cache/MemoryCacheParams;
        //    64: putfield        com/facebook/imagepipeline/cache/CountingMemoryCache.mMemoryCacheParams:Lcom/facebook/imagepipeline/cache/MemoryCacheParams;
        //    67: aload_0        
        //    68: invokestatic    android/os/SystemClock.uptimeMillis:()J
        //    71: putfield        com/facebook/imagepipeline/cache/CountingMemoryCache.mLastCacheParamsCheck:J
        //    74: return         
        //    Signature:
        //  (Lcom/facebook/imagepipeline/cache/ValueDescriptor<TV;>;Lcom/facebook/imagepipeline/cache/CountingMemoryCache$CacheTrimStrategy;Lcom/facebook/common/internal/Supplier<Lcom/facebook/imagepipeline/cache/MemoryCacheParams;>;)V
        // 
        // The error that occurred was:
        // 
        // com.strobel.assembler.metadata.MetadataHelper$AdaptFailure
        //     at com.strobel.assembler.metadata.MetadataHelper$Adapter.visitGenericParameter(MetadataHelper.java:2234)
        //     at com.strobel.assembler.metadata.MetadataHelper$Adapter.visitGenericParameter(MetadataHelper.java:2156)
        //     at com.strobel.assembler.metadata.GenericParameter.accept(GenericParameter.java:85)
        //     at com.strobel.assembler.metadata.DefaultTypeVisitor.visit(DefaultTypeVisitor.java:25)
        //     at com.strobel.assembler.metadata.MetadataHelper$Adapter.adaptRecursive(MetadataHelper.java:2190)
        //     at com.strobel.assembler.metadata.MetadataHelper$Adapter.adaptRecursive(MetadataHelper.java:2167)
        //     at com.strobel.assembler.metadata.MetadataHelper$Adapter.visitParameterizedType(MetadataHelper.java:2180)
        //     at com.strobel.assembler.metadata.MetadataHelper$Adapter.visitParameterizedType(MetadataHelper.java:2156)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedGenericType.accept(CoreMetadataFactory.java:626)
        //     at com.strobel.assembler.metadata.DefaultTypeVisitor.visit(DefaultTypeVisitor.java:25)
        //     at com.strobel.assembler.metadata.MetadataHelper$Adapter.adaptRecursive(MetadataHelper.java:2190)
        //     at com.strobel.assembler.metadata.MetadataHelper$Adapter.adaptRecursive(MetadataHelper.java:2167)
        //     at com.strobel.assembler.metadata.MetadataHelper$Adapter.visitParameterizedType(MetadataHelper.java:2180)
        //     at com.strobel.assembler.metadata.MetadataHelper$Adapter.visitParameterizedType(MetadataHelper.java:2156)
        //     at com.strobel.assembler.metadata.ParameterizedType.accept(ParameterizedType.java:103)
        //     at com.strobel.assembler.metadata.DefaultTypeVisitor.visit(DefaultTypeVisitor.java:25)
        //     at com.strobel.assembler.metadata.MetadataHelper.adapt(MetadataHelper.java:1271)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:932)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1061)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:672)
        //     at com.strobel.decompiler.ast.TypeAnalysis.invalidateDependentExpressions(TypeAnalysis.java:759)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1011)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:778)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2659)
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
    
    private boolean canCacheNewValue(final V v) {
        synchronized (this) {
            final int sizeInBytes = this.mValueDescriptor.getSizeInBytes(v);
            return sizeInBytes <= this.mMemoryCacheParams.maxCacheEntrySize && this.getInUseCount() <= this.mMemoryCacheParams.maxCacheEntries - 1 && this.getInUseSizeInBytes() <= this.mMemoryCacheParams.maxCacheSize - sizeInBytes;
        }
    }
    
    private void decreaseClientCount(final CountingMemoryCache$Entry<K, V> countingMemoryCache$Entry) {
        synchronized (this) {
            Preconditions.checkNotNull(countingMemoryCache$Entry);
            Preconditions.checkState(countingMemoryCache$Entry.clientCount > 0);
            --countingMemoryCache$Entry.clientCount;
        }
    }
    
    private void increaseClientCount(final CountingMemoryCache$Entry<K, V> countingMemoryCache$Entry) {
        synchronized (this) {
            Preconditions.checkNotNull(countingMemoryCache$Entry);
            Preconditions.checkState(!countingMemoryCache$Entry.isOrphan);
            ++countingMemoryCache$Entry.clientCount;
        }
    }
    
    private void makeOrphan(final CountingMemoryCache$Entry<K, V> countingMemoryCache$Entry) {
        boolean b = true;
        synchronized (this) {
            Preconditions.checkNotNull(countingMemoryCache$Entry);
            if (countingMemoryCache$Entry.isOrphan) {
                b = false;
            }
            Preconditions.checkState(b);
            countingMemoryCache$Entry.isOrphan = true;
        }
    }
    
    private void makeOrphans(final ArrayList<CountingMemoryCache$Entry<K, V>> list) {
        // monitorenter(this)
        if (list != null) {
            try {
                final Iterator<CountingMemoryCache$Entry<K, V>> iterator = list.iterator();
                while (iterator.hasNext()) {
                    this.makeOrphan(iterator.next());
                }
            }
            finally {
            }
            // monitorexit(this)
        }
    }
    // monitorexit(this)
    
    private boolean maybeAddToExclusives(final CountingMemoryCache$Entry<K, V> countingMemoryCache$Entry) {
        synchronized (this) {
            boolean b;
            if (!countingMemoryCache$Entry.isOrphan && countingMemoryCache$Entry.clientCount == 0) {
                this.mExclusiveEntries.put(countingMemoryCache$Entry.key, countingMemoryCache$Entry);
                b = true;
            }
            else {
                b = false;
            }
            return b;
        }
    }
    
    private void maybeClose(final ArrayList<CountingMemoryCache$Entry<K, V>> list) {
        if (list != null) {
            final Iterator<CountingMemoryCache$Entry<K, V>> iterator = list.iterator();
            while (iterator.hasNext()) {
                CloseableReference.closeSafely(this.referenceToClose(iterator.next()));
            }
        }
    }
    
    private void maybeEvictEntries() {
        synchronized (this) {
            final ArrayList<CountingMemoryCache$Entry<K, V>> trimExclusivelyOwnedEntries = this.trimExclusivelyOwnedEntries(Math.min(this.mMemoryCacheParams.maxEvictionQueueEntries, this.mMemoryCacheParams.maxCacheEntries - this.getInUseCount()), Math.min(this.mMemoryCacheParams.maxEvictionQueueSize, this.mMemoryCacheParams.maxCacheSize - this.getInUseSizeInBytes()));
            this.makeOrphans(trimExclusivelyOwnedEntries);
            // monitorexit(this)
            this.maybeClose(trimExclusivelyOwnedEntries);
            this.maybeNotifyExclusiveEntryRemoval(trimExclusivelyOwnedEntries);
        }
    }
    
    private static <K, V> void maybeNotifyExclusiveEntryInsertion(final CountingMemoryCache$Entry<K, V> countingMemoryCache$Entry) {
        if (countingMemoryCache$Entry != null && countingMemoryCache$Entry.observer != null) {
            countingMemoryCache$Entry.observer.onExclusivityChanged(countingMemoryCache$Entry.key, true);
        }
    }
    
    private static <K, V> void maybeNotifyExclusiveEntryRemoval(final CountingMemoryCache$Entry<K, V> countingMemoryCache$Entry) {
        if (countingMemoryCache$Entry != null && countingMemoryCache$Entry.observer != null) {
            countingMemoryCache$Entry.observer.onExclusivityChanged(countingMemoryCache$Entry.key, false);
        }
    }
    
    private void maybeNotifyExclusiveEntryRemoval(final ArrayList<CountingMemoryCache$Entry<K, V>> list) {
        if (list != null) {
            final Iterator<CountingMemoryCache$Entry<K, V>> iterator = list.iterator();
            while (iterator.hasNext()) {
                maybeNotifyExclusiveEntryRemoval(iterator.next());
            }
        }
    }
    
    private void maybeUpdateCacheParams() {
        synchronized (this) {
            if (this.mLastCacheParamsCheck + CountingMemoryCache.PARAMS_INTERCHECK_INTERVAL_MS <= SystemClock.uptimeMillis()) {
                this.mLastCacheParamsCheck = SystemClock.uptimeMillis();
                this.mMemoryCacheParams = this.mMemoryCacheParamsSupplier.get();
            }
        }
    }
    
    private CloseableReference<V> newClientReference(final CountingMemoryCache$Entry<K, V> p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: monitorenter   
        //     2: aload_0        
        //     3: aload_1        
        //     4: invokespecial   com/facebook/imagepipeline/cache/CountingMemoryCache.increaseClientCount:(Lcom/facebook/imagepipeline/cache/CountingMemoryCache$Entry;)V
        //     7: aload_1        
        //     8: getfield        com/facebook/imagepipeline/cache/CountingMemoryCache$Entry.valueRef:Lcom/facebook/common/references/CloseableReference;
        //    11: invokevirtual   com/facebook/common/references/CloseableReference.get:()Ljava/lang/Object;
        //    14: new             new            !!! ERROR
        //    17: dup            
        //    18: aload_0        
        //    19: aload_1        
        //    20: invokespecial   invokespecial  !!! ERROR
        //    23: invokestatic    com/facebook/common/references/CloseableReference.of:(Ljava/lang/Object;Lcom/facebook/common/references/ResourceReleaser;)Lcom/facebook/common/references/CloseableReference;
        //    26: astore_1       
        //    27: aload_0        
        //    28: monitorexit    
        //    29: aload_1        
        //    30: areturn        
        //    31: astore_1       
        //    32: aload_0        
        //    33: monitorexit    
        //    34: aload_1        
        //    35: athrow         
        //    Signature:
        //  (Lcom/facebook/imagepipeline/cache/CountingMemoryCache$Entry<TK;TV;>;)Lcom/facebook/common/references/CloseableReference<TV;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  2      27     31     36     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalArgumentException: Argument 'typeArguments' must not have any null elements.
        //     at com.strobel.core.VerifyArgument.noNullElementsAndNotEmpty(VerifyArgument.java:145)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedType.makeGenericType(CoreMetadataFactory.java:570)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory.makeParameterizedType(CoreMetadataFactory.java:156)
        //     at com.strobel.assembler.metadata.signatures.Reifier.visitClassTypeSignature(Reifier.java:125)
        //     at com.strobel.assembler.metadata.signatures.ClassTypeSignature.accept(ClassTypeSignature.java:46)
        //     at com.strobel.assembler.metadata.MetadataParser.parseClassSignature(MetadataParser.java:404)
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
    
    private CloseableReference<V> referenceToClose(final CountingMemoryCache$Entry<K, V> countingMemoryCache$Entry) {
        synchronized (this) {
            Preconditions.checkNotNull(countingMemoryCache$Entry);
            CloseableReference<V> valueRef;
            if (countingMemoryCache$Entry.isOrphan && countingMemoryCache$Entry.clientCount == 0) {
                valueRef = countingMemoryCache$Entry.valueRef;
            }
            else {
                valueRef = null;
            }
            return valueRef;
        }
    }
    
    private void releaseClientReference(CountingMemoryCache$Entry<K, V> countingMemoryCache$Entry) {
        while (true) {
            Preconditions.checkNotNull(countingMemoryCache$Entry);
            while (true) {
                synchronized (this) {
                    this.decreaseClientCount(countingMemoryCache$Entry);
                    final boolean maybeAddToExclusives = this.maybeAddToExclusives(countingMemoryCache$Entry);
                    final CloseableReference<?> referenceToClose = this.referenceToClose(countingMemoryCache$Entry);
                    // monitorexit(this)
                    CloseableReference.closeSafely(referenceToClose);
                    if (maybeAddToExclusives) {
                        maybeNotifyExclusiveEntryInsertion(countingMemoryCache$Entry);
                        this.maybeUpdateCacheParams();
                        this.maybeEvictEntries();
                        return;
                    }
                }
                countingMemoryCache$Entry = null;
                continue;
            }
        }
    }
    
    private ArrayList<CountingMemoryCache$Entry<K, V>> trimExclusivelyOwnedEntries(int max, int max2) {
        synchronized (this) {
            max = Math.max(max, 0);
            max2 = Math.max(max2, 0);
            ArrayList<CountingMemoryCache$Entry<K, V>> list;
            if (this.mExclusiveEntries.getCount() <= max && this.mExclusiveEntries.getSizeInBytes() <= max2) {
                list = null;
            }
            else {
                final ArrayList<CountingMemoryCache$Entry<K, V>> list2 = new ArrayList<CountingMemoryCache$Entry<K, V>>();
                while (true) {
                    if (this.mExclusiveEntries.getCount() <= max) {
                        list = list2;
                        if (this.mExclusiveEntries.getSizeInBytes() <= max2) {
                            break;
                        }
                    }
                    final K firstKey = this.mExclusiveEntries.getFirstKey();
                    this.mExclusiveEntries.remove(firstKey);
                    list2.add(this.mCachedEntries.remove(firstKey));
                }
            }
            return list;
        }
    }
    
    private ValueDescriptor<CountingMemoryCache$Entry<K, V>> wrapValueDescriptor(final ValueDescriptor<V> p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             new            !!! ERROR
        //     3: dup            
        //     4: aload_0        
        //     5: aload_1        
        //     6: invokespecial   invokespecial  !!! ERROR
        //     9: areturn        
        //    Signature:
        //  (Lcom/facebook/imagepipeline/cache/ValueDescriptor<TV;>;)Lcom/facebook/imagepipeline/cache/ValueDescriptor<Lcom/facebook/imagepipeline/cache/CountingMemoryCache$Entry<TK;TV;>;>;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalArgumentException: Argument 'typeArguments' must not have any null elements.
        //     at com.strobel.core.VerifyArgument.noNullElementsAndNotEmpty(VerifyArgument.java:145)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedType.makeGenericType(CoreMetadataFactory.java:570)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory.makeParameterizedType(CoreMetadataFactory.java:156)
        //     at com.strobel.assembler.metadata.signatures.Reifier.visitClassTypeSignature(Reifier.java:125)
        //     at com.strobel.assembler.metadata.signatures.ClassTypeSignature.accept(ClassTypeSignature.java:46)
        //     at com.strobel.assembler.metadata.signatures.Reifier.reifyTypeArguments(Reifier.java:53)
        //     at com.strobel.assembler.metadata.signatures.Reifier.visitClassTypeSignature(Reifier.java:123)
        //     at com.strobel.assembler.metadata.signatures.ClassTypeSignature.accept(ClassTypeSignature.java:46)
        //     at com.strobel.assembler.metadata.MetadataParser.parseClassSignature(MetadataParser.java:404)
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
    
    @Override
    public CloseableReference<V> cache(final K k, final CloseableReference<V> closeableReference) {
        return this.cache(k, closeableReference, null);
    }
    
    public CloseableReference<V> cache(final K k, CloseableReference<V> of, final CountingMemoryCache$EntryStateObserver<K> countingMemoryCache$EntryStateObserver) {
        while (true) {
            Preconditions.checkNotNull(k);
            Preconditions.checkNotNull((CountingMemoryCache$Entry<Object, V>)of);
            this.maybeUpdateCacheParams();
            while (true) {
                Label_0123: {
                    while (true) {
                        synchronized (this) {
                            final CountingMemoryCache$Entry<K, V> countingMemoryCache$Entry = this.mExclusiveEntries.remove(k);
                            Object referenceToClose = this.mCachedEntries.remove(k);
                            if (referenceToClose == null) {
                                break Label_0123;
                            }
                            this.makeOrphan((CountingMemoryCache$Entry<K, V>)referenceToClose);
                            referenceToClose = this.referenceToClose((CountingMemoryCache$Entry<K, V>)referenceToClose);
                            if (this.canCacheNewValue(((CloseableReference<V>)of).get())) {
                                of = CountingMemoryCache$Entry.of(k, (CloseableReference<V>)of, countingMemoryCache$EntryStateObserver);
                                this.mCachedEntries.put(k, of);
                                final CloseableReference<V> clientReference = this.newClientReference(of);
                                // monitorexit(this)
                                CloseableReference.closeSafely((CloseableReference)referenceToClose);
                                maybeNotifyExclusiveEntryRemoval((CountingMemoryCache$Entry<Object, Object>)countingMemoryCache$Entry);
                                this.maybeEvictEntries();
                                return clientReference;
                            }
                        }
                        final CloseableReference<V> clientReference = null;
                        continue;
                    }
                }
                Object referenceToClose = null;
                continue;
            }
        }
    }
    
    @Override
    public boolean contains(final Predicate<K> predicate) {
        synchronized (this) {
            return !this.mCachedEntries.getMatchingEntries(predicate).isEmpty();
        }
    }
    
    @Override
    public CloseableReference<V> get(final K k) {
        while (true) {
            Preconditions.checkNotNull(k);
            while (true) {
                synchronized (this) {
                    final CountingMemoryCache$Entry<K, V> countingMemoryCache$Entry = this.mExclusiveEntries.remove(k);
                    final CountingMemoryCache$Entry<K, V> countingMemoryCache$Entry2 = this.mCachedEntries.get(k);
                    if (countingMemoryCache$Entry2 != null) {
                        final CloseableReference<V> clientReference = this.newClientReference(countingMemoryCache$Entry2);
                        // monitorexit(this)
                        maybeNotifyExclusiveEntryRemoval((CountingMemoryCache$Entry<Object, Object>)countingMemoryCache$Entry);
                        this.maybeUpdateCacheParams();
                        this.maybeEvictEntries();
                        return clientReference;
                    }
                }
                final CloseableReference<V> clientReference = null;
                continue;
            }
        }
    }
    
    public int getInUseCount() {
        synchronized (this) {
            return this.mCachedEntries.getCount() - this.mExclusiveEntries.getCount();
        }
    }
    
    public int getInUseSizeInBytes() {
        synchronized (this) {
            return this.mCachedEntries.getSizeInBytes() - this.mExclusiveEntries.getSizeInBytes();
        }
    }
    
    @Override
    public int removeAll(final Predicate<K> predicate) {
        synchronized (this) {
            final ArrayList<CountingMemoryCache$Entry<K, V>> removeAll = this.mExclusiveEntries.removeAll(predicate);
            final ArrayList<CountingMemoryCache$Entry<K, V>> removeAll2 = this.mCachedEntries.removeAll(predicate);
            this.makeOrphans(removeAll2);
            // monitorexit(this)
            this.maybeClose(removeAll2);
            this.maybeNotifyExclusiveEntryRemoval(removeAll);
            this.maybeUpdateCacheParams();
            this.maybeEvictEntries();
            return removeAll2.size();
        }
    }
}
