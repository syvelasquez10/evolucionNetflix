// 
// Decompiled by Procyon v0.5.30
// 

package io.realm;

import io.realm.internal.TableOrView;
import io.realm.internal.TableQuery;
import io.realm.internal.LinkView;
import io.realm.internal.async.ArgumentsHolder;

public final class RealmQuery<E extends RealmModel>
{
    private static final Long INVALID_NATIVE_POINTER;
    private ArgumentsHolder argumentsHolder;
    private String className;
    private Class<E> clazz;
    private LinkView linkView;
    private TableQuery query;
    private BaseRealm realm;
    private RealmObjectSchema schema;
    private TableOrView table;
    
    static {
        INVALID_NATIVE_POINTER = 0L;
    }
    
    private RealmQuery(final Realm realm, final Class<E> clazz) {
        this.realm = realm;
        this.clazz = clazz;
        this.schema = realm.schema.getSchemaForClass(clazz);
        this.table = (TableOrView)this.schema.table;
        this.linkView = null;
        this.query = this.table.where();
    }
    
    private void checkQueryIsNotReused() {
        if (this.argumentsHolder != null) {
            throw new IllegalStateException("This RealmQuery is already used by a find* query, please create a new query");
        }
    }
    
    public static <E extends RealmModel> RealmQuery<E> createQuery(final Realm realm, final Class<E> clazz) {
        return new RealmQuery<E>(realm, clazz);
    }
    
    private long getSourceRowIndexForFirstObject() {
        return this.query.find();
    }
    
    private boolean isDynamicQuery() {
        return this.className != null;
    }
    
    public long count() {
        return this.query.count();
    }
    
    public RealmQuery<E> equalTo(final String s, final Integer n) {
        final long[] columnIndices = this.schema.getColumnIndices(s, RealmFieldType.INTEGER);
        if (n == null) {
            this.query.isNull(columnIndices);
            return this;
        }
        this.query.equalTo(columnIndices, (long)n);
        return this;
    }
    
    public RealmQuery<E> equalTo(final String s, final String s2) {
        return this.equalTo(s, s2, Case.SENSITIVE);
    }
    
    public RealmQuery<E> equalTo(final String s, final String s2, final Case case1) {
        this.query.equalTo(this.schema.getColumnIndices(s, RealmFieldType.STRING), s2, case1);
        return this;
    }
    
    public RealmResults<E> findAll() {
        this.checkQueryIsNotReused();
        if (this.isDynamicQuery()) {
            return (RealmResults<E>)RealmResults.createFromDynamicTableOrView(this.realm, (TableOrView)this.query.findAll(), this.className);
        }
        return RealmResults.createFromTableOrView(this.realm, (TableOrView)this.query.findAll(), this.clazz);
    }
    
    public E findFirst() {
        this.checkQueryIsNotReused();
        final long sourceRowIndexForFirstObject = this.getSourceRowIndexForFirstObject();
        if (sourceRowIndexForFirstObject >= 0L) {
            return this.realm.get(this.clazz, this.className, sourceRowIndexForFirstObject);
        }
        return null;
    }
    
    public ArgumentsHolder getArgument() {
        return this.argumentsHolder;
    }
    
    long handoverQueryPointer() {
        return this.query.handoverQuery(this.realm.sharedRealm);
    }
    
    public RealmQuery<E> notEqualTo(final String s, final Integer n) {
        final long[] columnIndices = this.schema.getColumnIndices(s, RealmFieldType.INTEGER);
        if (n == null) {
            this.query.isNotNull(columnIndices);
            return this;
        }
        this.query.notEqualTo(columnIndices, (long)n);
        return this;
    }
}
