// 
// Decompiled by Procyon v0.5.30
// 

package io.realm;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Date;
import java.util.HashMap;
import io.realm.internal.Table;
import java.util.Map;

public final class RealmObjectSchema
{
    private static final Map<Class<?>, RealmObjectSchema$FieldMetaData> SUPPORTED_LINKED_FIELDS;
    private static final Map<Class<?>, RealmObjectSchema$FieldMetaData> SUPPORTED_SIMPLE_FIELDS;
    private final Map<String, Long> columnIndices;
    private final long nativePtr;
    private final BaseRealm realm;
    final Table table;
    
    static {
        (SUPPORTED_SIMPLE_FIELDS = new HashMap<Class<?>, RealmObjectSchema$FieldMetaData>()).put(String.class, new RealmObjectSchema$FieldMetaData(RealmFieldType.STRING, true));
        RealmObjectSchema.SUPPORTED_SIMPLE_FIELDS.put(Short.TYPE, new RealmObjectSchema$FieldMetaData(RealmFieldType.INTEGER, false));
        RealmObjectSchema.SUPPORTED_SIMPLE_FIELDS.put(Short.class, new RealmObjectSchema$FieldMetaData(RealmFieldType.INTEGER, true));
        RealmObjectSchema.SUPPORTED_SIMPLE_FIELDS.put(Integer.TYPE, new RealmObjectSchema$FieldMetaData(RealmFieldType.INTEGER, false));
        RealmObjectSchema.SUPPORTED_SIMPLE_FIELDS.put(Integer.class, new RealmObjectSchema$FieldMetaData(RealmFieldType.INTEGER, true));
        RealmObjectSchema.SUPPORTED_SIMPLE_FIELDS.put(Long.TYPE, new RealmObjectSchema$FieldMetaData(RealmFieldType.INTEGER, false));
        RealmObjectSchema.SUPPORTED_SIMPLE_FIELDS.put(Long.class, new RealmObjectSchema$FieldMetaData(RealmFieldType.INTEGER, true));
        RealmObjectSchema.SUPPORTED_SIMPLE_FIELDS.put(Float.TYPE, new RealmObjectSchema$FieldMetaData(RealmFieldType.FLOAT, false));
        RealmObjectSchema.SUPPORTED_SIMPLE_FIELDS.put(Float.class, new RealmObjectSchema$FieldMetaData(RealmFieldType.FLOAT, true));
        RealmObjectSchema.SUPPORTED_SIMPLE_FIELDS.put(Double.TYPE, new RealmObjectSchema$FieldMetaData(RealmFieldType.DOUBLE, false));
        RealmObjectSchema.SUPPORTED_SIMPLE_FIELDS.put(Double.class, new RealmObjectSchema$FieldMetaData(RealmFieldType.DOUBLE, true));
        RealmObjectSchema.SUPPORTED_SIMPLE_FIELDS.put(Boolean.TYPE, new RealmObjectSchema$FieldMetaData(RealmFieldType.BOOLEAN, false));
        RealmObjectSchema.SUPPORTED_SIMPLE_FIELDS.put(Boolean.class, new RealmObjectSchema$FieldMetaData(RealmFieldType.BOOLEAN, true));
        RealmObjectSchema.SUPPORTED_SIMPLE_FIELDS.put(Byte.TYPE, new RealmObjectSchema$FieldMetaData(RealmFieldType.INTEGER, false));
        RealmObjectSchema.SUPPORTED_SIMPLE_FIELDS.put(Byte.class, new RealmObjectSchema$FieldMetaData(RealmFieldType.INTEGER, true));
        RealmObjectSchema.SUPPORTED_SIMPLE_FIELDS.put(byte[].class, new RealmObjectSchema$FieldMetaData(RealmFieldType.BINARY, true));
        RealmObjectSchema.SUPPORTED_SIMPLE_FIELDS.put(Date.class, new RealmObjectSchema$FieldMetaData(RealmFieldType.DATE, true));
        (SUPPORTED_LINKED_FIELDS = new HashMap<Class<?>, RealmObjectSchema$FieldMetaData>()).put(RealmObject.class, new RealmObjectSchema$FieldMetaData(RealmFieldType.OBJECT, false));
        RealmObjectSchema.SUPPORTED_LINKED_FIELDS.put(RealmList.class, new RealmObjectSchema$FieldMetaData(RealmFieldType.LIST, false));
    }
    
    protected RealmObjectSchema(final long nativePtr) {
        this.realm = null;
        this.table = null;
        this.columnIndices = null;
        this.nativePtr = nativePtr;
    }
    
    RealmObjectSchema(final BaseRealm realm, final Table table, final Map<String, Long> columnIndices) {
        this.realm = realm;
        this.table = table;
        this.columnIndices = columnIndices;
        this.nativePtr = 0L;
    }
    
    RealmObjectSchema(final String s) {
        this.realm = null;
        this.table = null;
        this.columnIndices = null;
        this.nativePtr = nativeCreateRealmObjectSchema(s);
    }
    
    private Set<Property> getProperties() {
        if (this.realm == null) {
            final long[] nativeGetProperties = nativeGetProperties(this.nativePtr);
            final LinkedHashSet set = new LinkedHashSet<Property>(nativeGetProperties.length);
            for (int i = 0; i < nativeGetProperties.length; ++i) {
                set.add(new Property(nativeGetProperties[i]));
            }
            return (Set<Property>)set;
        }
        throw new IllegalArgumentException("Not possible");
    }
    
    private boolean isValidType(final RealmFieldType realmFieldType, final RealmFieldType[] array) {
        final boolean b = false;
        int n = 0;
        boolean b2;
        while (true) {
            b2 = b;
            if (n >= array.length) {
                break;
            }
            if (array[n] == realmFieldType) {
                b2 = true;
                break;
            }
            ++n;
        }
        return b2;
    }
    
    static native void nativeAddProperty(final long p0, final long p1);
    
    static native void nativeClose(final long p0);
    
    static native long nativeCreateRealmObjectSchema(final String p0);
    
    static native String nativeGetClassName(final long p0);
    
    static native long[] nativeGetProperties(final long p0);
    
    protected RealmObjectSchema add(final Property property) {
        if (this.realm != null && this.nativePtr == 0L) {
            throw new IllegalArgumentException("Don't use this method.");
        }
        nativeAddProperty(this.nativePtr, property.getNativePtr());
        return this;
    }
    
    public void close() {
        if (this.nativePtr != 0L) {
            final Iterator<Property> iterator = this.getProperties().iterator();
            while (iterator.hasNext()) {
                iterator.next().close();
            }
            nativeClose(this.nativePtr);
        }
    }
    
    public String getClassName() {
        if (this.realm == null) {
            return nativeGetClassName(this.nativePtr);
        }
        return this.table.getName().substring(Table.TABLE_PREFIX.length());
    }
    
    long[] getColumnIndices(final String s, final RealmFieldType... array) {
        if (s == null || s.equals("")) {
            throw new IllegalArgumentException("Non-empty fieldname must be provided");
        }
        if (s.startsWith(".") || s.endsWith(".")) {
            throw new IllegalArgumentException("Illegal field name. It cannot start or end with a '.': " + s);
        }
        final Table table = this.table;
        final boolean b = array != null && array.length > 0;
        if (s.contains(".")) {
            final String[] split = s.split("\\.");
            final long[] array2 = new long[split.length];
            int i = 0;
            Table linkTarget = table;
            while (i < split.length - 1) {
                final long columnIndex = linkTarget.getColumnIndex(split[i]);
                if (columnIndex < 0L) {
                    throw new IllegalArgumentException("Invalid query: " + split[i] + " does not refer to a class.");
                }
                final RealmFieldType columnType = linkTarget.getColumnType(columnIndex);
                if (columnType != RealmFieldType.OBJECT && columnType != RealmFieldType.LIST) {
                    throw new IllegalArgumentException("Invalid query: " + split[i] + " does not refer to a class.");
                }
                linkTarget = linkTarget.getLinkTarget(columnIndex);
                array2[i] = columnIndex;
                ++i;
            }
            final String s2 = split[split.length - 1];
            final long columnIndex2 = linkTarget.getColumnIndex(s2);
            array2[split.length - 1] = columnIndex2;
            if (columnIndex2 < 0L) {
                throw new IllegalArgumentException(s2 + " is not a field name in class " + linkTarget.getName());
            }
            if (b && !this.isValidType(linkTarget.getColumnType(columnIndex2), array)) {
                throw new IllegalArgumentException(String.format("Field '%s': type mismatch.", split[split.length - 1]));
            }
            return array2;
        }
        else {
            final Long fieldIndex = this.getFieldIndex(s);
            if (fieldIndex == null) {
                throw new IllegalArgumentException(String.format("Field '%s' does not exist.", s));
            }
            final RealmFieldType columnType2 = table.getColumnType((long)fieldIndex);
            if (b && !this.isValidType(columnType2, array)) {
                throw new IllegalArgumentException(String.format("Field '%s': type mismatch. Was %s, expected %s.", s, columnType2, Arrays.toString(array)));
            }
            return new long[] { fieldIndex };
        }
    }
    
    Long getFieldIndex(final String s) {
        return this.columnIndices.get(s);
    }
    
    protected long getNativePtr() {
        return this.nativePtr;
    }
}
