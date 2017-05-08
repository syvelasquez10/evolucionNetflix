// 
// Decompiled by Procyon v0.5.30
// 

package io.realm;

import io.realm.internal.async.BadVersionException;
import java.util.ListIterator;
import java.util.Iterator;
import io.realm.internal.TableView;
import io.realm.internal.InvalidRow;
import io.realm.internal.RealmObjectProxy;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;
import io.realm.internal.TableOrView;
import io.realm.internal.TableQuery;
import java.util.concurrent.Future;
import java.util.List;
import java.util.AbstractList;

public final class RealmResults<E extends RealmModel> extends AbstractList<E> implements OrderedRealmCollection<E>
{
    private boolean asyncQueryCompleted;
    String className;
    Class<E> classSpec;
    private long currentTableViewVersion;
    private final List<RealmChangeListener<RealmResults<E>>> listeners;
    private Future<Long> pendingQuery;
    private final TableQuery query;
    final BaseRealm realm;
    private TableOrView table;
    private boolean viewUpdated;
    
    private RealmResults(final BaseRealm realm, final TableOrView table, final Class<E> classSpec) {
        this.table = null;
        this.currentTableViewVersion = -1L;
        this.listeners = new CopyOnWriteArrayList<RealmChangeListener<RealmResults<E>>>();
        this.asyncQueryCompleted = false;
        this.viewUpdated = false;
        this.realm = realm;
        this.classSpec = classSpec;
        this.table = table;
        this.pendingQuery = null;
        this.query = null;
        this.currentTableViewVersion = table.syncIfNeeded();
    }
    
    private RealmResults(final BaseRealm baseRealm, final TableOrView table, final String s) {
        this(baseRealm, s);
        this.table = table;
        this.currentTableViewVersion = table.syncIfNeeded();
    }
    
    private RealmResults(final BaseRealm realm, final String className) {
        this.table = null;
        this.currentTableViewVersion = -1L;
        this.listeners = new CopyOnWriteArrayList<RealmChangeListener<RealmResults<E>>>();
        this.asyncQueryCompleted = false;
        this.viewUpdated = false;
        this.realm = realm;
        this.className = className;
        this.pendingQuery = null;
        this.query = null;
    }
    
    static RealmResults<DynamicRealmObject> createFromDynamicTableOrView(final BaseRealm baseRealm, final TableOrView tableOrView, final String s) {
        final RealmResults<DynamicRealmObject> realmResults = new RealmResults<DynamicRealmObject>(baseRealm, tableOrView, s);
        baseRealm.handlerController.addToRealmResults((RealmResults)realmResults);
        return realmResults;
    }
    
    static <E extends RealmModel> RealmResults<E> createFromTableOrView(final BaseRealm baseRealm, final TableOrView tableOrView, final Class<E> clazz) {
        final RealmResults<E> realmResults = new RealmResults<E>(baseRealm, tableOrView, clazz);
        baseRealm.handlerController.addToRealmResults((RealmResults)realmResults);
        return realmResults;
    }
    
    @Deprecated
    @Override
    public void add(final int n, final E e) {
        throw new UnsupportedOperationException("This method is not supported by RealmResults.");
    }
    
    @Deprecated
    @Override
    public boolean add(final E e) {
        throw new UnsupportedOperationException("This method is not supported by RealmResults.");
    }
    
    @Deprecated
    @Override
    public boolean addAll(final int n, final Collection<? extends E> collection) {
        throw new UnsupportedOperationException("This method is not supported by RealmResults.");
    }
    
    @Deprecated
    public boolean addAll(final Collection<? extends E> collection) {
        throw new UnsupportedOperationException("This method is not supported by RealmResults.");
    }
    
    @Deprecated
    @Override
    public void clear() {
        throw new UnsupportedOperationException("This method is not supported by RealmResults.");
    }
    
    public boolean contains(final Object o) {
        boolean b2;
        final boolean b = b2 = false;
        if (this.isLoaded()) {
            b2 = b;
            if (o instanceof RealmObjectProxy) {
                final RealmObjectProxy realmObjectProxy = (RealmObjectProxy)o;
                b2 = b;
                if (this.realm.getPath().equals(realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath())) {
                    b2 = b;
                    if (realmObjectProxy.realmGet$proxyState().getRow$realm() != InvalidRow.INSTANCE) {
                        b2 = b;
                        if (this.table.sourceRowIndex(realmObjectProxy.realmGet$proxyState().getRow$realm().getIndex()) != -1L) {
                            b2 = true;
                        }
                    }
                }
            }
        }
        return b2;
    }
    
    public boolean deleteAllFromRealm() {
        this.realm.checkIfValid();
        if (this.size() > 0) {
            this.getTableOrView().clear();
            return true;
        }
        return false;
    }
    
    @Override
    public E get(final int n) {
        this.realm.checkIfValid();
        final TableOrView tableOrView = this.getTableOrView();
        if (tableOrView instanceof TableView) {
            return this.realm.get(this.classSpec, this.className, ((TableView)tableOrView).getSourceRowIndex((long)n));
        }
        return this.realm.get(this.classSpec, this.className, n);
    }
    
    TableOrView getTableOrView() {
        if (this.table == null) {
            return (TableOrView)this.realm.schema.getTable((Class)this.classSpec);
        }
        return this.table;
    }
    
    public boolean isLoaded() {
        this.realm.checkIfValid();
        return this.pendingQuery == null || this.asyncQueryCompleted;
    }
    
    @Override
    public Iterator<E> iterator() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   io/realm/RealmResults.isLoaded:()Z
        //     4: ifne            16
        //     7: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //    10: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    15: areturn        
        //    16: new             new            !!! ERROR
        //    19: dup            
        //    20: aload_0        
        //    21: invokespecial   invokespecial  !!! ERROR
        //    24: areturn        
        //    Signature:
        //  ()Ljava/util/Iterator<TE;>;
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
    
    @Override
    public ListIterator<E> listIterator() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   io/realm/RealmResults.isLoaded:()Z
        //     4: ifne            16
        //     7: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //    10: invokeinterface java/util/List.listIterator:()Ljava/util/ListIterator;
        //    15: areturn        
        //    16: new             new            !!! ERROR
        //    19: dup            
        //    20: aload_0        
        //    21: iconst_0       
        //    22: invokespecial   invokespecial  !!! ERROR
        //    25: areturn        
        //    Signature:
        //  ()Ljava/util/ListIterator<TE;>;
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
    
    @Override
    public ListIterator<E> listIterator(final int p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   io/realm/RealmResults.isLoaded:()Z
        //     4: ifne            17
        //     7: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //    10: iload_1        
        //    11: invokeinterface java/util/List.listIterator:(I)Ljava/util/ListIterator;
        //    16: areturn        
        //    17: new             new            !!! ERROR
        //    20: dup            
        //    21: aload_0        
        //    22: iload_1        
        //    23: invokespecial   invokespecial  !!! ERROR
        //    26: areturn        
        //    Signature:
        //  (I)Ljava/util/ListIterator<TE;>;
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
    
    void notifyChangeListeners(final boolean b) {
        if (!this.listeners.isEmpty() && (this.pendingQuery == null || this.asyncQueryCompleted) && (this.viewUpdated || b)) {
            this.viewUpdated = false;
            final Iterator<RealmChangeListener<RealmResults<E>>> iterator = this.listeners.iterator();
            while (iterator.hasNext()) {
                iterator.next().onChange((Object)this);
            }
        }
    }
    
    @Deprecated
    @Override
    public E remove(final int n) {
        throw new UnsupportedOperationException("This method is not supported by RealmResults.");
    }
    
    @Deprecated
    public boolean remove(final Object o) {
        throw new UnsupportedOperationException("This method is not supported by RealmResults.");
    }
    
    @Deprecated
    public boolean removeAll(final Collection<?> collection) {
        throw new UnsupportedOperationException("This method is not supported by RealmResults.");
    }
    
    @Deprecated
    public boolean retainAll(final Collection<?> collection) {
        throw new UnsupportedOperationException("This method is not supported by RealmResults.");
    }
    
    @Deprecated
    @Override
    public E set(final int n, final E e) {
        throw new UnsupportedOperationException("This method is not supported by RealmResults.");
    }
    
    public int size() {
        if (!this.isLoaded()) {
            return 0;
        }
        final long size = this.getTableOrView().size();
        if (size > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int)size;
    }
    
    void swapTableViewPointer(final long n) {
        try {
            this.table = (TableOrView)this.query.importHandoverTableView(n, this.realm.sharedRealm);
            this.asyncQueryCompleted = true;
        }
        catch (BadVersionException ex) {
            throw new IllegalStateException("Caller and Worker Realm should have been at the same version");
        }
    }
    
    void syncIfNeeded() {
        final long syncIfNeeded = this.table.syncIfNeeded();
        this.viewUpdated = (syncIfNeeded != this.currentTableViewVersion);
        this.currentTableViewVersion = syncIfNeeded;
    }
}
