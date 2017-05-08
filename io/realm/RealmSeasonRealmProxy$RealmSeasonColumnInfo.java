// 
// Decompiled by Procyon v0.5.30
// 

package io.realm;

import java.util.Map;
import java.util.HashMap;
import io.realm.internal.Table;
import io.realm.internal.ColumnInfo;

final class RealmSeasonRealmProxy$RealmSeasonColumnInfo extends ColumnInfo implements Cloneable
{
    public long labelIndex;
    public long numberIndex;
    
    RealmSeasonRealmProxy$RealmSeasonColumnInfo(final String s, final Table table) {
        final HashMap<String, Long> indicesMap = new HashMap<String, Long>(2);
        this.numberIndex = this.getValidColumnIndex(s, table, "RealmSeason", "number");
        indicesMap.put("number", this.numberIndex);
        this.labelIndex = this.getValidColumnIndex(s, table, "RealmSeason", "label");
        indicesMap.put("label", this.labelIndex);
        this.setIndicesMap((Map)indicesMap);
    }
    
    public final RealmSeasonRealmProxy$RealmSeasonColumnInfo clone() {
        return (RealmSeasonRealmProxy$RealmSeasonColumnInfo)super.clone();
    }
    
    public final void copyColumnInfoFrom(final ColumnInfo columnInfo) {
        final RealmSeasonRealmProxy$RealmSeasonColumnInfo realmSeasonRealmProxy$RealmSeasonColumnInfo = (RealmSeasonRealmProxy$RealmSeasonColumnInfo)columnInfo;
        this.numberIndex = realmSeasonRealmProxy$RealmSeasonColumnInfo.numberIndex;
        this.labelIndex = realmSeasonRealmProxy$RealmSeasonColumnInfo.labelIndex;
        this.setIndicesMap(realmSeasonRealmProxy$RealmSeasonColumnInfo.getIndicesMap());
    }
}
