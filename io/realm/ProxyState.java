// 
// Decompiled by Procyon v0.5.30
// 

package io.realm;

import io.realm.log.RealmLog;
import io.realm.internal.TableQuery;
import java.util.Iterator;
import io.realm.internal.Table;
import java.util.concurrent.CopyOnWriteArrayList;
import io.realm.internal.Row;
import java.util.concurrent.Future;
import java.util.List;

public final class ProxyState<E extends RealmModel>
{
    private boolean acceptDefaultValue;
    private String className;
    private Class<? extends RealmModel> clazzName;
    protected long currentTableVersion;
    private List<String> excludeFields;
    private boolean isCompleted;
    private final List<RealmChangeListener<E>> listeners;
    private E model;
    private Future<Long> pendingQuery;
    private BaseRealm realm;
    private Row row;
    private boolean underConstruction;
    
    public ProxyState() {
        this.underConstruction = true;
        this.listeners = new CopyOnWriteArrayList<RealmChangeListener<E>>();
        this.isCompleted = false;
        this.currentTableVersion = -1L;
    }
    
    public ProxyState(final E model) {
        this.underConstruction = true;
        this.listeners = new CopyOnWriteArrayList<RealmChangeListener<E>>();
        this.isCompleted = false;
        this.currentTableVersion = -1L;
        this.model = model;
    }
    
    public ProxyState(final Class<? extends RealmModel> clazzName, final E model) {
        this.underConstruction = true;
        this.listeners = new CopyOnWriteArrayList<RealmChangeListener<E>>();
        this.isCompleted = false;
        this.currentTableVersion = -1L;
        this.clazzName = clazzName;
        this.model = model;
    }
    
    private Table getTable() {
        if (this.className != null) {
            return this.getRealm$realm().schema.getTable(this.className);
        }
        return this.getRealm$realm().schema.getTable(this.clazzName);
    }
    
    public boolean getAcceptDefaultValue$realm() {
        return this.acceptDefaultValue;
    }
    
    public List<String> getExcludeFields$realm() {
        return this.excludeFields;
    }
    
    public List<RealmChangeListener<E>> getListeners$realm() {
        return this.listeners;
    }
    
    public Object getPendingQuery$realm() {
        return this.pendingQuery;
    }
    
    public BaseRealm getRealm$realm() {
        return this.realm;
    }
    
    public Row getRow$realm() {
        return this.row;
    }
    
    public boolean isCompleted$realm() {
        return this.isCompleted;
    }
    
    public boolean isUnderConstruction() {
        return this.underConstruction;
    }
    
    void notifyChangeListeners$realm() {
        int n = 1;
        if (!this.listeners.isEmpty()) {
            final Table table = this.row.getTable();
            if (table != null) {
                final long version = table.getVersion();
                if (this.currentTableVersion != version) {
                    this.currentTableVersion = version;
                }
                else {
                    n = 0;
                }
            }
            if (n != 0) {
                final Iterator<RealmChangeListener<E>> iterator = this.listeners.iterator();
                while (iterator.hasNext()) {
                    iterator.next().onChange(this.model);
                }
            }
        }
    }
    
    public void onCompleted$realm(long importHandoverRow) {
        if (importHandoverRow == 0L) {
            this.isCompleted = true;
        }
        else if (!this.isCompleted || this.row == Row.EMPTY_ROW) {
            this.isCompleted = true;
            importHandoverRow = TableQuery.importHandoverRow(importHandoverRow, this.realm.sharedRealm);
            this.row = (Row)this.getTable().getUncheckedRowByPointer(importHandoverRow);
        }
    }
    
    public boolean onCompleted$realm() {
        try {
            final Long n = this.pendingQuery.get();
            if (n != 0L) {
                this.onCompleted$realm(n);
                this.notifyChangeListeners$realm();
            }
            else {
                this.isCompleted = true;
            }
        }
        catch (Exception ex) {
            RealmLog.debug((Throwable)ex);
            return false;
        }
        return true;
    }
    
    public void setAcceptDefaultValue$realm(final boolean acceptDefaultValue) {
        this.acceptDefaultValue = acceptDefaultValue;
    }
    
    public void setConstructionFinished() {
        this.underConstruction = false;
        this.excludeFields = null;
    }
    
    public void setExcludeFields$realm(final List<String> excludeFields) {
        this.excludeFields = excludeFields;
    }
    
    public void setRealm$realm(final BaseRealm realm) {
        this.realm = realm;
    }
    
    public void setRow$realm(final Row row) {
        this.row = row;
    }
    
    public void setTableVersion$realm() {
        if (this.row.getTable() != null) {
            this.currentTableVersion = this.row.getTable().getVersion();
        }
    }
}
