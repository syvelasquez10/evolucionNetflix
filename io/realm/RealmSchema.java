// 
// Decompiled by Procyon v0.5.30
// 

package io.realm;

import io.realm.internal.Util;
import io.realm.internal.ColumnInfo;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.HashMap;
import io.realm.internal.ColumnIndices;
import io.realm.internal.Table;
import java.util.Map;

public final class RealmSchema
{
    private static final String TABLE_PREFIX;
    private final Map<Class<? extends RealmModel>, RealmObjectSchema> classToSchema;
    private final Map<Class<? extends RealmModel>, Table> classToTable;
    ColumnIndices columnIndices;
    private final Map<String, RealmObjectSchema> dynamicClassToSchema;
    private final Map<String, Table> dynamicClassToTable;
    private long nativePtr;
    private final BaseRealm realm;
    
    static {
        TABLE_PREFIX = Table.TABLE_PREFIX;
    }
    
    RealmSchema() {
        this.dynamicClassToTable = new HashMap<String, Table>();
        this.classToTable = new HashMap<Class<? extends RealmModel>, Table>();
        this.classToSchema = new HashMap<Class<? extends RealmModel>, RealmObjectSchema>();
        this.dynamicClassToSchema = new HashMap<String, RealmObjectSchema>();
        this.realm = null;
        this.nativePtr = 0L;
    }
    
    RealmSchema(final BaseRealm realm) {
        this.dynamicClassToTable = new HashMap<String, Table>();
        this.classToTable = new HashMap<Class<? extends RealmModel>, Table>();
        this.classToSchema = new HashMap<Class<? extends RealmModel>, RealmObjectSchema>();
        this.dynamicClassToSchema = new HashMap<String, RealmObjectSchema>();
        this.realm = realm;
        this.nativePtr = 0L;
    }
    
    RealmSchema(final ArrayList<RealmObjectSchema> list) {
        this.dynamicClassToTable = new HashMap<String, Table>();
        this.classToTable = new HashMap<Class<? extends RealmModel>, Table>();
        this.classToSchema = new HashMap<Class<? extends RealmModel>, RealmObjectSchema>();
        this.dynamicClassToSchema = new HashMap<String, RealmObjectSchema>();
        final long[] array = new long[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            array[i] = list.get(i).getNativePtr();
        }
        this.nativePtr = nativeCreateFromList(array);
        this.realm = null;
    }
    
    private void checkEmpty(final String s, final String s2) {
        if (s == null || s.isEmpty()) {
            throw new IllegalArgumentException(s2);
        }
    }
    
    static String getSchemaForTable(final Table table) {
        return table.getName().substring(Table.TABLE_PREFIX.length());
    }
    
    private static boolean isProxyClass(final Class<? extends RealmModel> clazz, final Class<? extends RealmModel> clazz2) {
        return clazz != clazz2;
    }
    
    static native void nativeClose(final long p0);
    
    static native long nativeCreateFromList(final long[] p0);
    
    static native long[] nativeGetAll(final long p0);
    
    public void close() {
        if (this.nativePtr != 0L) {
            final Iterator<RealmObjectSchema> iterator = this.getAll().iterator();
            while (iterator.hasNext()) {
                iterator.next().close();
            }
            nativeClose(this.nativePtr);
        }
    }
    
    public boolean contains(final String s) {
        if (this.realm == null) {
            return this.dynamicClassToSchema.containsKey(s);
        }
        return this.realm.sharedRealm.hasTable(Table.TABLE_PREFIX + s);
    }
    
    public RealmObjectSchema create(final String s) {
        this.checkEmpty(s, "Null or empty class names are not allowed");
        if (this.realm == null) {
            final RealmObjectSchema realmObjectSchema = new RealmObjectSchema(s);
            this.dynamicClassToSchema.put(s, realmObjectSchema);
            return realmObjectSchema;
        }
        final String string = RealmSchema.TABLE_PREFIX + s;
        if (string.length() > 56) {
            throw new IllegalArgumentException("Class name is to long. Limit is 57 characters: " + s.length());
        }
        if (this.realm.sharedRealm.hasTable(string)) {
            throw new IllegalArgumentException("Class already exists: " + s);
        }
        final Table table = this.realm.sharedRealm.getTable(string);
        return new RealmObjectSchema(this.realm, table, new RealmObjectSchema$DynamicColumnMap(table));
    }
    
    public RealmObjectSchema get(String string) {
        RealmObjectSchema realmObjectSchema = null;
        this.checkEmpty(string, "Null or empty class names are not allowed");
        if (this.realm == null) {
            if (this.contains(string)) {
                realmObjectSchema = this.dynamicClassToSchema.get(string);
            }
        }
        else {
            string = RealmSchema.TABLE_PREFIX + string;
            if (this.realm.sharedRealm.hasTable(string)) {
                final Table table = this.realm.sharedRealm.getTable(string);
                return new RealmObjectSchema(this.realm, table, new RealmObjectSchema$DynamicColumnMap(table));
            }
        }
        return realmObjectSchema;
    }
    
    public Set<RealmObjectSchema> getAll() {
        int i = 0;
        final int n = 0;
        if (this.realm == null) {
            final long[] nativeGetAll = nativeGetAll(this.nativePtr);
            final LinkedHashSet set = new LinkedHashSet<RealmObjectSchema>(nativeGetAll.length);
            for (int j = n; j < nativeGetAll.length; ++j) {
                set.add(new RealmObjectSchema(nativeGetAll[j]));
            }
            return (Set<RealmObjectSchema>)set;
        }
        final int n2 = (int)this.realm.sharedRealm.size();
        final LinkedHashSet set2 = new LinkedHashSet<RealmObjectSchema>(n2);
        while (i < n2) {
            final String tableName = this.realm.sharedRealm.getTableName(i);
            if (Table.isModelTable(tableName)) {
                final Table table = this.realm.sharedRealm.getTable(tableName);
                set2.add(new RealmObjectSchema(this.realm, table, new RealmObjectSchema$DynamicColumnMap(table)));
            }
            ++i;
        }
        return (Set<RealmObjectSchema>)set2;
    }
    
    ColumnInfo getColumnInfo(final Class<? extends RealmModel> clazz) {
        final ColumnInfo columnInfo = this.columnIndices.getColumnInfo((Class)clazz);
        if (columnInfo == null) {
            throw new IllegalStateException("No validated schema information found for " + this.realm.configuration.getSchemaMediator().getTableName((Class)clazz));
        }
        return columnInfo;
    }
    
    public long getNativePtr() {
        return this.nativePtr;
    }
    
    RealmObjectSchema getSchemaForClass(final Class<? extends RealmModel> clazz) {
        RealmObjectSchema realmObjectSchema2;
        final RealmObjectSchema realmObjectSchema = realmObjectSchema2 = this.classToSchema.get(clazz);
        if (realmObjectSchema == null) {
            final Class originalModelClass = Util.getOriginalModelClass((Class)clazz);
            RealmObjectSchema realmObjectSchema3 = realmObjectSchema;
            if (isProxyClass(originalModelClass, clazz)) {
                realmObjectSchema3 = this.classToSchema.get(originalModelClass);
            }
            RealmObjectSchema realmObjectSchema4;
            if ((realmObjectSchema4 = realmObjectSchema3) == null) {
                realmObjectSchema4 = new RealmObjectSchema(this.realm, this.getTable(clazz), this.columnIndices.getColumnInfo(originalModelClass).getIndicesMap());
                this.classToSchema.put(originalModelClass, realmObjectSchema4);
            }
            realmObjectSchema2 = realmObjectSchema4;
            if (isProxyClass(originalModelClass, clazz)) {
                this.classToSchema.put(clazz, realmObjectSchema4);
                realmObjectSchema2 = realmObjectSchema4;
            }
        }
        return realmObjectSchema2;
    }
    
    Table getTable(final Class<? extends RealmModel> clazz) {
        Table table2;
        final Table table = table2 = this.classToTable.get(clazz);
        if (table == null) {
            final Class originalModelClass = Util.getOriginalModelClass((Class)clazz);
            Table table3 = table;
            if (isProxyClass(originalModelClass, clazz)) {
                table3 = this.classToTable.get(originalModelClass);
            }
            Table table4;
            if ((table4 = table3) == null) {
                table4 = this.realm.sharedRealm.getTable(this.realm.configuration.getSchemaMediator().getTableName(originalModelClass));
                this.classToTable.put(originalModelClass, table4);
            }
            table2 = table4;
            if (isProxyClass(originalModelClass, clazz)) {
                this.classToTable.put(clazz, table4);
                table2 = table4;
            }
        }
        return table2;
    }
    
    Table getTable(final String s) {
        final String string = Table.TABLE_PREFIX + s;
        Table table;
        if ((table = this.dynamicClassToTable.get(string)) == null) {
            if (!this.realm.sharedRealm.hasTable(string)) {
                throw new IllegalArgumentException("The class " + string + " doesn't exist in this Realm.");
            }
            table = this.realm.sharedRealm.getTable(string);
            this.dynamicClassToTable.put(string, table);
        }
        return table;
    }
}
