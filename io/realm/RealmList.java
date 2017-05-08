// 
// Decompiled by Procyon v0.5.30
// 

package io.realm;

import java.util.Collection;
import java.util.ListIterator;
import java.util.Iterator;
import io.realm.internal.InvalidRow;
import io.realm.internal.RealmObjectProxy;
import java.util.ArrayList;
import io.realm.internal.LinkView;
import java.util.List;
import java.util.AbstractList;

public final class RealmList<E extends RealmModel> extends AbstractList<E> implements OrderedRealmCollection<E>
{
    protected String className;
    protected Class<E> clazz;
    private final boolean managedMode;
    protected BaseRealm realm;
    private List<E> unmanagedList;
    protected LinkView view;
    
    public RealmList() {
        this.managedMode = false;
        this.unmanagedList = new ArrayList<E>();
    }
    
    RealmList(final Class<E> clazz, final LinkView view, final BaseRealm realm) {
        this.managedMode = true;
        this.clazz = clazz;
        this.view = view;
        this.realm = realm;
    }
    
    private void checkValidObject(final E e) {
        if (e == null) {
            throw new IllegalArgumentException("RealmList does not accept null values");
        }
    }
    
    private void checkValidView() {
        this.realm.checkIfValid();
        if (this.view == null || !this.view.isAttached()) {
            throw new IllegalStateException("Realm instance has been closed or this object or its parent has been deleted.");
        }
    }
    
    private E copyToRealmIfNeeded(final E e) {
        Label_0198: {
            if (e instanceof RealmObjectProxy) {
                final RealmObjectProxy realmObjectProxy = (RealmObjectProxy)e;
                if (realmObjectProxy instanceof DynamicRealmObject) {
                    final String schemaForTable = RealmSchema.getSchemaForTable(this.view.getTargetTable());
                    final String type = ((DynamicRealmObject)e).getType();
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() == this.realm) {
                        if (!schemaForTable.equals(type)) {
                            throw new IllegalArgumentException(String.format("The object has a different type from list's. Type of the list is '%s', type of object is '%s'.", schemaForTable, type));
                        }
                    }
                    else {
                        if (this.realm.threadId == realmObjectProxy.realmGet$proxyState().getRealm$realm().threadId) {
                            throw new IllegalArgumentException("Cannot copy DynamicRealmObject between Realm instances.");
                        }
                        throw new IllegalStateException("Cannot copy an object to a Realm instance created in another thread.");
                    }
                }
                else {
                    if (realmObjectProxy.realmGet$proxyState().getRow$realm() == null || !realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(this.realm.getPath())) {
                        break Label_0198;
                    }
                    if (this.realm != realmObjectProxy.realmGet$proxyState().getRealm$realm()) {
                        throw new IllegalArgumentException("Cannot copy an object from another Realm instance.");
                    }
                }
                return e;
            }
        }
        final Realm realm = (Realm)this.realm;
        if (realm.getTable(e.getClass()).hasPrimaryKey()) {
            return realm.copyToRealmOrUpdate(e);
        }
        return realm.copyToRealm(e);
    }
    
    private boolean isAttached() {
        return this.view != null && this.view.isAttached();
    }
    
    @Override
    public void add(final int n, final E e) {
        this.checkValidObject(e);
        if (this.managedMode) {
            this.checkValidView();
            if (n < 0 || n > this.size()) {
                throw new IndexOutOfBoundsException("Invalid index " + n + ", size is " + this.size());
            }
            this.view.insert((long)n, this.copyToRealmIfNeeded((RealmObjectProxy)e).realmGet$proxyState().getRow$realm().getIndex());
        }
        else {
            this.unmanagedList.add(n, e);
        }
        ++this.modCount;
    }
    
    @Override
    public boolean add(final E e) {
        this.checkValidObject(e);
        if (this.managedMode) {
            this.checkValidView();
            this.view.add(this.copyToRealmIfNeeded((RealmObjectProxy)e).realmGet$proxyState().getRow$realm().getIndex());
        }
        else {
            this.unmanagedList.add(e);
        }
        ++this.modCount;
        return true;
    }
    
    @Override
    public void clear() {
        if (this.managedMode) {
            this.checkValidView();
            this.view.clear();
        }
        else {
            this.unmanagedList.clear();
        }
        ++this.modCount;
    }
    
    @Override
    public boolean contains(final Object o) {
        final boolean b = false;
        if (this.managedMode) {
            this.realm.checkIfValid();
            boolean contains = b;
            if (o instanceof RealmObjectProxy) {
                final RealmObjectProxy realmObjectProxy = (RealmObjectProxy)o;
                contains = b;
                if (realmObjectProxy.realmGet$proxyState().getRow$realm() != null) {
                    contains = b;
                    if (this.realm.getPath().equals(realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath())) {
                        contains = b;
                        if (realmObjectProxy.realmGet$proxyState().getRow$realm() != InvalidRow.INSTANCE) {
                            contains = this.view.contains(realmObjectProxy.realmGet$proxyState().getRow$realm().getIndex());
                        }
                    }
                }
            }
            return contains;
        }
        return this.unmanagedList.contains(o);
    }
    
    @Override
    public E get(final int n) {
        if (this.managedMode) {
            this.checkValidView();
            return this.realm.get(this.clazz, this.className, this.view.getTargetRowIndex((long)n));
        }
        return this.unmanagedList.get(n);
    }
    
    public boolean isManaged() {
        return this.realm != null;
    }
    
    @Override
    public Iterator<E> iterator() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        io/realm/RealmList.managedMode:Z
        //     4: ifeq            17
        //     7: new             new            !!! ERROR
        //    10: dup            
        //    11: aload_0        
        //    12: aconst_null    
        //    13: invokespecial   invokespecial  !!! ERROR
        //    16: areturn        
        //    17: aload_0        
        //    18: invokespecial   java/util/AbstractList.iterator:()Ljava/util/Iterator;
        //    21: areturn        
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
        return this.listIterator(0);
    }
    
    @Override
    public ListIterator<E> listIterator(final int p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        io/realm/RealmList.managedMode:Z
        //     4: ifeq            17
        //     7: new             new            !!! ERROR
        //    10: dup            
        //    11: aload_0        
        //    12: iload_1        
        //    13: invokespecial   invokespecial  !!! ERROR
        //    16: areturn        
        //    17: aload_0        
        //    18: iload_1        
        //    19: invokespecial   java/util/AbstractList.listIterator:(I)Ljava/util/ListIterator;
        //    22: areturn        
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
    
    @Override
    public E remove(final int n) {
        RealmModel value;
        if (this.managedMode) {
            this.checkValidView();
            value = this.get(n);
            this.view.remove((long)n);
        }
        else {
            value = this.unmanagedList.remove(n);
        }
        ++this.modCount;
        return (E)value;
    }
    
    @Override
    public boolean remove(final Object o) {
        if (this.managedMode && !this.realm.isInTransaction()) {
            throw new IllegalStateException("Objects can only be removed from inside a write transaction");
        }
        return super.remove(o);
    }
    
    @Override
    public boolean removeAll(final Collection<?> collection) {
        if (this.managedMode && !this.realm.isInTransaction()) {
            throw new IllegalStateException("Objects can only be removed from inside a write transaction");
        }
        return super.removeAll(collection);
    }
    
    @Override
    public E set(final int n, final E e) {
        this.checkValidObject(e);
        if (this.managedMode) {
            this.checkValidView();
            final RealmObjectProxy realmObjectProxy = this.copyToRealmIfNeeded((RealmObjectProxy)e);
            final RealmModel value = this.get(n);
            this.view.set((long)n, realmObjectProxy.realmGet$proxyState().getRow$realm().getIndex());
            return (E)value;
        }
        return this.unmanagedList.set(n, e);
    }
    
    @Override
    public int size() {
        if (!this.managedMode) {
            return this.unmanagedList.size();
        }
        this.checkValidView();
        final long size = this.view.size();
        if (size < 2147483647L) {
            return (int)size;
        }
        return Integer.MAX_VALUE;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        String s;
        if (this.managedMode) {
            s = this.clazz.getSimpleName();
        }
        else {
            s = this.getClass().getSimpleName();
        }
        sb.append(s);
        sb.append("@[");
        if (this.managedMode && !this.isAttached()) {
            sb.append("invalid");
        }
        else {
            for (int i = 0; i < this.size(); ++i) {
                if (this.managedMode) {
                    sb.append(((RealmObjectProxy)this.get(i)).realmGet$proxyState().getRow$realm().getIndex());
                }
                else {
                    sb.append(System.identityHashCode(this.get(i)));
                }
                if (i < this.size() - 1) {
                    sb.append(',');
                }
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
