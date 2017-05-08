// 
// Decompiled by Procyon v0.5.30
// 

package io.realm;

import java.util.Map;
import java.util.HashMap;
import io.realm.internal.Table;
import io.realm.internal.ColumnInfo;

final class RealmProfileRealmProxy$RealmProfileColumnInfo extends ColumnInfo implements Cloneable
{
    public long iconUrlIndex;
    public long idIndex;
    public long kidsIndex;
    public long nameIndex;
    
    RealmProfileRealmProxy$RealmProfileColumnInfo(final String s, final Table table) {
        final HashMap<String, Long> indicesMap = new HashMap<String, Long>(4);
        this.idIndex = this.getValidColumnIndex(s, table, "RealmProfile", "id");
        indicesMap.put("id", this.idIndex);
        this.nameIndex = this.getValidColumnIndex(s, table, "RealmProfile", "name");
        indicesMap.put("name", this.nameIndex);
        this.kidsIndex = this.getValidColumnIndex(s, table, "RealmProfile", "kids");
        indicesMap.put("kids", this.kidsIndex);
        this.iconUrlIndex = this.getValidColumnIndex(s, table, "RealmProfile", "iconUrl");
        indicesMap.put("iconUrl", this.iconUrlIndex);
        this.setIndicesMap((Map)indicesMap);
    }
    
    public final RealmProfileRealmProxy$RealmProfileColumnInfo clone() {
        return (RealmProfileRealmProxy$RealmProfileColumnInfo)super.clone();
    }
    
    public final void copyColumnInfoFrom(final ColumnInfo columnInfo) {
        final RealmProfileRealmProxy$RealmProfileColumnInfo realmProfileRealmProxy$RealmProfileColumnInfo = (RealmProfileRealmProxy$RealmProfileColumnInfo)columnInfo;
        this.idIndex = realmProfileRealmProxy$RealmProfileColumnInfo.idIndex;
        this.nameIndex = realmProfileRealmProxy$RealmProfileColumnInfo.nameIndex;
        this.kidsIndex = realmProfileRealmProxy$RealmProfileColumnInfo.kidsIndex;
        this.iconUrlIndex = realmProfileRealmProxy$RealmProfileColumnInfo.iconUrlIndex;
        this.setIndicesMap(realmProfileRealmProxy$RealmProfileColumnInfo.getIndicesMap());
    }
}
