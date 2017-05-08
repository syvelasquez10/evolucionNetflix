// 
// Decompiled by Procyon v0.5.30
// 

package io.realm;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import io.realm.internal.Table;
import io.realm.internal.Row;
import io.realm.internal.RealmObjectProxy;

public final class DynamicRealmObject extends RealmObject implements RealmObjectProxy
{
    private final ProxyState proxyState;
    
    DynamicRealmObject(final BaseRealm realm$realm, final Row row$realm) {
        (this.proxyState = new ProxyState((E)this)).setRealm$realm(realm$realm);
        this.proxyState.setRow$realm(row$realm);
        this.proxyState.setConstructionFinished();
    }
    
    public boolean equals(final Object o) {
        final boolean b = true;
        final boolean b2 = false;
        boolean b3;
        if (this == o) {
            b3 = true;
        }
        else {
            b3 = b2;
            if (o != null) {
                b3 = b2;
                if (this.getClass() == o.getClass()) {
                    final DynamicRealmObject dynamicRealmObject = (DynamicRealmObject)o;
                    final String path = this.proxyState.getRealm$realm().getPath();
                    final String path2 = dynamicRealmObject.proxyState.getRealm$realm().getPath();
                    if (path != null) {
                        b3 = b2;
                        if (!path.equals(path2)) {
                            return b3;
                        }
                    }
                    else if (path2 != null) {
                        return false;
                    }
                    final String name = this.proxyState.getRow$realm().getTable().getName();
                    final String name2 = dynamicRealmObject.proxyState.getRow$realm().getTable().getName();
                    if (name != null) {
                        b3 = b2;
                        if (!name.equals(name2)) {
                            return b3;
                        }
                    }
                    else if (name2 != null) {
                        return false;
                    }
                    return this.proxyState.getRow$realm().getIndex() == dynamicRealmObject.proxyState.getRow$realm().getIndex() && b;
                }
            }
        }
        return b3;
    }
    
    public String[] getFieldNames() {
        final String[] array = new String[(int)this.proxyState.getRow$realm().getColumnCount()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = this.proxyState.getRow$realm().getColumnName((long)i);
        }
        return array;
    }
    
    public String getType() {
        return RealmSchema.getSchemaForTable(this.proxyState.getRow$realm().getTable());
    }
    
    public int hashCode() {
        int hashCode = 0;
        final String path = this.proxyState.getRealm$realm().getPath();
        final String name = this.proxyState.getRow$realm().getTable().getName();
        final long index = this.proxyState.getRow$realm().getIndex();
        int hashCode2;
        if (path != null) {
            hashCode2 = path.hashCode();
        }
        else {
            hashCode2 = 0;
        }
        if (name != null) {
            hashCode = name.hashCode();
        }
        return (hashCode + (hashCode2 + 527) * 31) * 31 + (int)(index >>> 32 ^ index);
    }
    
    public ProxyState realmGet$proxyState() {
        return this.proxyState;
    }
    
    public String toString() {
        if (this.proxyState.getRealm$realm() == null || !this.proxyState.getRow$realm().isAttached()) {
            return "Invalid object";
        }
        final StringBuilder sb = new StringBuilder(Table.tableNameToClassName(this.proxyState.getRow$realm().getTable().getName()) + " = [");
        final String[] fieldNames = this.getFieldNames();
        for (int length = fieldNames.length, i = 0; i < length; ++i) {
            final String s = fieldNames[i];
            final long columnIndex = this.proxyState.getRow$realm().getColumnIndex(s);
            final RealmFieldType columnType = this.proxyState.getRow$realm().getColumnType(columnIndex);
            sb.append("{");
            sb.append(s).append(":");
            switch (DynamicRealmObject$1.$SwitchMap$io$realm$RealmFieldType[columnType.ordinal()]) {
                default: {
                    sb.append("?");
                    break;
                }
                case 1: {
                    Serializable value;
                    if (this.proxyState.getRow$realm().isNull(columnIndex)) {
                        value = "null";
                    }
                    else {
                        value = this.proxyState.getRow$realm().getBoolean(columnIndex);
                    }
                    sb.append(value);
                    break;
                }
                case 2: {
                    Serializable value2;
                    if (this.proxyState.getRow$realm().isNull(columnIndex)) {
                        value2 = "null";
                    }
                    else {
                        value2 = this.proxyState.getRow$realm().getLong(columnIndex);
                    }
                    sb.append(value2);
                    break;
                }
                case 3: {
                    Serializable value3;
                    if (this.proxyState.getRow$realm().isNull(columnIndex)) {
                        value3 = "null";
                    }
                    else {
                        value3 = this.proxyState.getRow$realm().getFloat(columnIndex);
                    }
                    sb.append(value3);
                    break;
                }
                case 4: {
                    Serializable value4;
                    if (this.proxyState.getRow$realm().isNull(columnIndex)) {
                        value4 = "null";
                    }
                    else {
                        value4 = this.proxyState.getRow$realm().getDouble(columnIndex);
                    }
                    sb.append(value4);
                    break;
                }
                case 5: {
                    sb.append(this.proxyState.getRow$realm().getString(columnIndex));
                    break;
                }
                case 6: {
                    sb.append(Arrays.toString(this.proxyState.getRow$realm().getBinaryByteArray(columnIndex)));
                    break;
                }
                case 7: {
                    Serializable date;
                    if (this.proxyState.getRow$realm().isNull(columnIndex)) {
                        date = "null";
                    }
                    else {
                        date = this.proxyState.getRow$realm().getDate(columnIndex);
                    }
                    sb.append(date);
                    break;
                }
                case 8: {
                    String tableNameToClassName;
                    if (this.proxyState.getRow$realm().isNullLink(columnIndex)) {
                        tableNameToClassName = "null";
                    }
                    else {
                        tableNameToClassName = Table.tableNameToClassName(this.proxyState.getRow$realm().getTable().getLinkTarget(columnIndex).getName());
                    }
                    sb.append(tableNameToClassName);
                    break;
                }
                case 9: {
                    sb.append(String.format("RealmList<%s>[%s]", Table.tableNameToClassName(this.proxyState.getRow$realm().getTable().getLinkTarget(columnIndex).getName()), this.proxyState.getRow$realm().getLinkList(columnIndex).size()));
                    break;
                }
            }
            sb.append("}, ");
        }
        sb.replace(sb.length() - 2, sb.length(), "");
        sb.append("]");
        return sb.toString();
    }
}
