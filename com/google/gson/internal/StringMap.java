// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson.internal;

import java.util.Arrays;
import java.util.Random;
import java.util.Collection;
import java.util.Set;
import java.util.Map;
import java.util.AbstractMap;

public final class StringMap<V> extends AbstractMap<String, V>
{
    private static final Entry[] EMPTY_TABLE;
    private static final int seed;
    private Set<Entry<String, V>> entrySet;
    private StringMap$LinkedEntry<V> header;
    private Set<String> keySet;
    private int size;
    private StringMap$LinkedEntry<V>[] table;
    private int threshold;
    private Collection<V> values;
    
    static {
        EMPTY_TABLE = new StringMap$LinkedEntry[2];
        seed = new Random().nextInt();
    }
    
    public StringMap() {
        this.table = (StringMap$LinkedEntry<V>[])StringMap.EMPTY_TABLE;
        this.threshold = -1;
        this.header = new StringMap$LinkedEntry<V>();
    }
    
    private void addNewEntry(final String s, final V v, final int n, final int n2) {
        final StringMap$LinkedEntry<V> header = this.header;
        final StringMap$LinkedEntry<V> prv = header.prv;
        final StringMap$LinkedEntry stringMap$LinkedEntry = new StringMap$LinkedEntry<Object>(s, v, n, (StringMap$LinkedEntry<Object>)this.table[n2], (StringMap$LinkedEntry<Object>)header, (StringMap$LinkedEntry<Object>)prv);
        final StringMap$LinkedEntry<V>[] table = this.table;
        header.prv = (StringMap$LinkedEntry<V>)stringMap$LinkedEntry;
        table[n2] = (prv.nxt = (StringMap$LinkedEntry<V>)stringMap$LinkedEntry);
    }
    
    private StringMap$LinkedEntry<V>[] doubleCapacity() {
        final StringMap$LinkedEntry<V>[] table = this.table;
        final int length = table.length;
        if (length == 1073741824) {
            return table;
        }
        final StringMap$LinkedEntry<V>[] table2 = this.makeTable(length * 2);
        if (this.size == 0) {
            return table2;
        }
        for (int i = 0; i < length; ++i) {
            StringMap$LinkedEntry<V> stringMap$LinkedEntry = table[i];
            if (stringMap$LinkedEntry != null) {
                int n = stringMap$LinkedEntry.hash & length;
                table2[i | n] = stringMap$LinkedEntry;
                StringMap$LinkedEntry<V> next = stringMap$LinkedEntry.next;
                StringMap$LinkedEntry<V> stringMap$LinkedEntry2 = null;
                while (next != null) {
                    final int n2 = next.hash & length;
                    if (n2 != n) {
                        if (stringMap$LinkedEntry2 == null) {
                            table2[i | n2] = next;
                        }
                        else {
                            stringMap$LinkedEntry2.next = next;
                        }
                        n = n2;
                    }
                    else {
                        stringMap$LinkedEntry = stringMap$LinkedEntry2;
                    }
                    final StringMap$LinkedEntry<V> next2 = next.next;
                    stringMap$LinkedEntry2 = stringMap$LinkedEntry;
                    stringMap$LinkedEntry = next;
                    next = next2;
                }
                if (stringMap$LinkedEntry2 != null) {
                    stringMap$LinkedEntry2.next = null;
                }
            }
        }
        return table2;
    }
    
    private StringMap$LinkedEntry<V> getEntry(final String s) {
        if (s != null) {
            final int hash = hash(s);
            final StringMap$LinkedEntry<V>[] table = this.table;
            for (StringMap$LinkedEntry<V> next = table[table.length - 1 & hash]; next != null; next = next.next) {
                final String key = next.key;
                if (key == s || (next.hash == hash && s.equals(key))) {
                    return next;
                }
            }
        }
        return null;
    }
    
    private static int hash(final String s) {
        int seed = StringMap.seed;
        for (int i = 0; i < s.length(); ++i) {
            final char c = (char)(seed + s.charAt(i));
            final char c2 = (char)(c + c << 10);
            seed = (c2 ^ c2 >>> 6);
        }
        final char c3 = (char)(seed >>> 20 ^ seed >>> 12 ^ seed);
        return c3 >>> 4 ^ (c3 >>> 7 ^ c3);
    }
    
    private StringMap$LinkedEntry<V>[] makeTable(final int n) {
        final StringMap$LinkedEntry[] table = new StringMap$LinkedEntry[n];
        this.table = (StringMap$LinkedEntry<V>[])table;
        this.threshold = (n >> 1) + (n >> 2);
        return (StringMap$LinkedEntry<V>[])table;
    }
    
    private boolean removeMapping(final Object o, final Object o2) {
        if (o == null || !(o instanceof String)) {
            return false;
        }
        final int hash = hash((String)o);
        final StringMap$LinkedEntry<V>[] table = this.table;
        final int n = hash & table.length - 1;
        StringMap$LinkedEntry<V> stringMap$LinkedEntry = table[n];
        StringMap$LinkedEntry<V> stringMap$LinkedEntry2 = null;
        while (stringMap$LinkedEntry != null) {
            if (stringMap$LinkedEntry.hash == hash && o.equals(stringMap$LinkedEntry.key)) {
                Label_0098: {
                    if (o2 == null) {
                        if (stringMap$LinkedEntry.value == null) {
                            break Label_0098;
                        }
                    }
                    else if (o2.equals(stringMap$LinkedEntry.value)) {
                        break Label_0098;
                    }
                    return false;
                }
                if (stringMap$LinkedEntry2 == null) {
                    table[n] = stringMap$LinkedEntry.next;
                }
                else {
                    stringMap$LinkedEntry2.next = stringMap$LinkedEntry.next;
                }
                --this.size;
                this.unlink(stringMap$LinkedEntry);
                return true;
            }
            final StringMap$LinkedEntry<V> next = stringMap$LinkedEntry.next;
            stringMap$LinkedEntry2 = stringMap$LinkedEntry;
            stringMap$LinkedEntry = next;
        }
        return false;
    }
    
    private void unlink(final StringMap$LinkedEntry<V> stringMap$LinkedEntry) {
        stringMap$LinkedEntry.prv.nxt = stringMap$LinkedEntry.nxt;
        stringMap$LinkedEntry.nxt.prv = stringMap$LinkedEntry.prv;
        stringMap$LinkedEntry.prv = null;
        stringMap$LinkedEntry.nxt = null;
    }
    
    @Override
    public void clear() {
        if (this.size != 0) {
            Arrays.fill(this.table, null);
            this.size = 0;
        }
        final StringMap$LinkedEntry<V> header = this.header;
        StringMap$LinkedEntry<V> nxt2;
        for (StringMap$LinkedEntry<V> nxt = header.nxt; nxt != header; nxt = nxt2) {
            nxt2 = nxt.nxt;
            nxt.prv = null;
            nxt.nxt = null;
        }
        header.prv = header;
        header.nxt = header;
    }
    
    @Override
    public boolean containsKey(final Object o) {
        return o instanceof String && this.getEntry((String)o) != null;
    }
    
    @Override
    public Set<Entry<String, V>> entrySet() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/google/gson/internal/StringMap.entrySet:Ljava/util/Set;
        //     4: astore_1       
        //     5: aload_1        
        //     6: ifnull          11
        //     9: aload_1        
        //    10: areturn        
        //    11: new             new            !!! ERROR
        //    14: dup            
        //    15: aload_0        
        //    16: aconst_null    
        //    17: invokespecial   invokespecial  !!! ERROR
        //    20: astore_1       
        //    21: aload_0        
        //    22: aload_1        
        //    23: putfield        com/google/gson/internal/StringMap.entrySet:Ljava/util/Set;
        //    26: aload_1        
        //    27: areturn        
        //    Signature:
        //  ()Ljava/util/Set<Ljava/util/Map$Entry<Ljava/lang/String;TV;>;>;
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
    public V get(final Object o) {
        Object value;
        final Object o2 = value = null;
        if (o instanceof String) {
            final StringMap$LinkedEntry<V> entry = this.getEntry((String)o);
            value = o2;
            if (entry != null) {
                value = entry.value;
            }
        }
        return (V)value;
    }
    
    @Override
    public Set<String> keySet() {
        final Set<String> keySet = this.keySet;
        if (keySet != null) {
            return keySet;
        }
        return this.keySet = new StringMap$KeySet(this, null);
    }
    
    @Override
    public V put(final String s, final V value) {
        if (s == null) {
            throw new NullPointerException("key == null");
        }
        final int hash = hash(s);
        final StringMap$LinkedEntry<V>[] table = this.table;
        int n = table.length - 1 & hash;
        for (StringMap$LinkedEntry<V> next = table[n]; next != null; next = next.next) {
            if (next.hash == hash && s.equals(next.key)) {
                final V value2 = next.value;
                next.value = value;
                return value2;
            }
        }
        if (this.size++ > this.threshold) {
            n = (this.doubleCapacity().length - 1 & hash);
        }
        this.addNewEntry(s, value, hash, n);
        return null;
    }
    
    @Override
    public V remove(final Object o) {
        if (o == null || !(o instanceof String)) {
            return null;
        }
        final int hash = hash((String)o);
        final StringMap$LinkedEntry<V>[] table = this.table;
        final int n = hash & table.length - 1;
        StringMap$LinkedEntry<V> stringMap$LinkedEntry = table[n];
        StringMap$LinkedEntry<V> stringMap$LinkedEntry2 = null;
        while (stringMap$LinkedEntry != null) {
            if (stringMap$LinkedEntry.hash == hash && o.equals(stringMap$LinkedEntry.key)) {
                if (stringMap$LinkedEntry2 == null) {
                    table[n] = stringMap$LinkedEntry.next;
                }
                else {
                    stringMap$LinkedEntry2.next = stringMap$LinkedEntry.next;
                }
                --this.size;
                this.unlink(stringMap$LinkedEntry);
                return stringMap$LinkedEntry.value;
            }
            final StringMap$LinkedEntry<V> next = stringMap$LinkedEntry.next;
            stringMap$LinkedEntry2 = stringMap$LinkedEntry;
            stringMap$LinkedEntry = next;
        }
        return null;
    }
    
    @Override
    public int size() {
        return this.size;
    }
    
    @Override
    public Collection<V> values() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/google/gson/internal/StringMap.values:Ljava/util/Collection;
        //     4: astore_1       
        //     5: aload_1        
        //     6: ifnull          11
        //     9: aload_1        
        //    10: areturn        
        //    11: new             new            !!! ERROR
        //    14: dup            
        //    15: aload_0        
        //    16: aconst_null    
        //    17: invokespecial   invokespecial  !!! ERROR
        //    20: astore_1       
        //    21: aload_0        
        //    22: aload_1        
        //    23: putfield        com/google/gson/internal/StringMap.values:Ljava/util/Collection;
        //    26: aload_1        
        //    27: areturn        
        //    Signature:
        //  ()Ljava/util/Collection<TV;>;
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
}
