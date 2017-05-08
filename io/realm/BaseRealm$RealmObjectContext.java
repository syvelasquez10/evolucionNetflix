// 
// Decompiled by Procyon v0.5.30
// 

package io.realm;

import io.realm.internal.Row;
import java.util.List;
import io.realm.internal.ColumnInfo;

public final class BaseRealm$RealmObjectContext
{
    private boolean acceptDefaultValue;
    private ColumnInfo columnInfo;
    private List<String> excludeFields;
    private BaseRealm realm;
    private Row row;
    
    public void clear() {
        this.realm = null;
        this.row = null;
        this.columnInfo = null;
        this.acceptDefaultValue = false;
        this.excludeFields = null;
    }
    
    public boolean getAcceptDefaultValue() {
        return this.acceptDefaultValue;
    }
    
    public ColumnInfo getColumnInfo() {
        return this.columnInfo;
    }
    
    public List<String> getExcludeFields() {
        return this.excludeFields;
    }
    
    public BaseRealm getRealm() {
        return this.realm;
    }
    
    public Row getRow() {
        return this.row;
    }
    
    public void set(final BaseRealm realm, final Row row, final ColumnInfo columnInfo, final boolean acceptDefaultValue, final List<String> excludeFields) {
        this.realm = realm;
        this.row = row;
        this.columnInfo = columnInfo;
        this.acceptDefaultValue = acceptDefaultValue;
        this.excludeFields = excludeFields;
    }
}
