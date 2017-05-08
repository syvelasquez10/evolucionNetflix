// 
// Decompiled by Procyon v0.5.30
// 

package io.realm;

import java.util.concurrent.ThreadPoolExecutor;
import io.realm.internal.async.RealmAsyncTaskImpl;
import io.realm.internal.ColumnInfo;
import java.util.Iterator;
import java.util.Set;
import io.realm.internal.RealmProxyMediator;
import io.realm.internal.ObjectServerFacade;
import io.realm.log.Logger;
import io.realm.log.AndroidLogger;
import io.realm.internal.RealmCore;
import android.content.Context;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import io.realm.exceptions.RealmException;
import java.io.FileNotFoundException;
import io.realm.exceptions.RealmFileException;
import io.realm.exceptions.RealmFileException$Kind;
import io.realm.internal.ColumnIndices;
import io.realm.internal.Row;
import java.util.HashMap;
import io.realm.log.RealmLog;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.internal.Table;
import io.realm.internal.SharedRealm;
import java.util.Map;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import io.realm.internal.RealmObjectProxy;
import com.netflix.mediaclient.servicemgr.interface_.offline.realm.RealmSeason;

public class RealmSeasonRealmProxy extends RealmSeason implements RealmSeasonRealmProxyInterface, RealmObjectProxy
{
    private static final List<String> FIELD_NAMES;
    private RealmSeasonRealmProxy$RealmSeasonColumnInfo columnInfo;
    private ProxyState proxyState;
    
    static {
        final ArrayList<String> list = new ArrayList<String>();
        list.add("number");
        list.add("label");
        FIELD_NAMES = Collections.unmodifiableList((List<?>)list);
    }
    
    RealmSeasonRealmProxy() {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        this.proxyState.setConstructionFinished();
    }
    
    public static RealmSeason copy(final Realm realm, final RealmSeason realmSeason, final boolean b, final Map<RealmModel, RealmObjectProxy> map) {
        final RealmObjectProxy realmObjectProxy = map.get(realmSeason);
        if (realmObjectProxy != null) {
            return (RealmSeason)realmObjectProxy;
        }
        final RealmSeason realmSeason2 = realm.createObjectInternal(RealmSeason.class, false, Collections.emptyList());
        map.put(realmSeason, (RealmObjectProxy)realmSeason2);
        realmSeason2.realmSet$number(realmSeason.realmGet$number());
        realmSeason2.realmSet$label(realmSeason.realmGet$label());
        return realmSeason2;
    }
    
    public static RealmSeason copyOrUpdate(final Realm realm, final RealmSeason realmSeason, final boolean b, final Map<RealmModel, RealmObjectProxy> map) {
        if (realmSeason instanceof RealmObjectProxy && ((RealmObjectProxy)realmSeason).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)realmSeason).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (realmSeason instanceof RealmObjectProxy && ((RealmObjectProxy)realmSeason).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)realmSeason).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return realmSeason;
        }
        final BaseRealm$RealmObjectContext baseRealm$RealmObjectContext = BaseRealm.objectContext.get();
        final RealmObjectProxy realmObjectProxy = map.get(realmSeason);
        if (realmObjectProxy != null) {
            return (RealmSeason)realmObjectProxy;
        }
        return copy(realm, realmSeason, b, map);
    }
    
    public static RealmObjectSchema createRealmObjectSchema(final RealmSchema realmSchema) {
        if (!realmSchema.contains("RealmSeason")) {
            final RealmObjectSchema create = realmSchema.create("RealmSeason");
            create.add(new Property("number", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED));
            create.add(new Property("label", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            return create;
        }
        return realmSchema.get("RealmSeason");
    }
    
    public static String getTableName() {
        return "class_RealmSeason";
    }
    
    public static Table initTable(final SharedRealm sharedRealm) {
        if (!sharedRealm.hasTable("class_RealmSeason")) {
            final Table table = sharedRealm.getTable("class_RealmSeason");
            table.addColumn(RealmFieldType.INTEGER, "number", false);
            table.addColumn(RealmFieldType.STRING, "label", true);
            table.setPrimaryKey("");
            return table;
        }
        return sharedRealm.getTable("class_RealmSeason");
    }
    
    private void injectObjectContext() {
        final BaseRealm$RealmObjectContext baseRealm$RealmObjectContext = BaseRealm.objectContext.get();
        this.columnInfo = (RealmSeasonRealmProxy$RealmSeasonColumnInfo)baseRealm$RealmObjectContext.getColumnInfo();
        (this.proxyState = new ProxyState(RealmSeason.class, (E)this)).setRealm$realm(baseRealm$RealmObjectContext.getRealm());
        this.proxyState.setRow$realm(baseRealm$RealmObjectContext.getRow());
        this.proxyState.setAcceptDefaultValue$realm(baseRealm$RealmObjectContext.getAcceptDefaultValue());
        this.proxyState.setExcludeFields$realm(baseRealm$RealmObjectContext.getExcludeFields());
    }
    
    public static RealmSeasonRealmProxy$RealmSeasonColumnInfo validateTable(final SharedRealm sharedRealm, final boolean b) {
        if (!sharedRealm.hasTable("class_RealmSeason")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "The 'RealmSeason' class is missing from the schema for this Realm.");
        }
        final Table table = sharedRealm.getTable("class_RealmSeason");
        final long columnCount = table.getColumnCount();
        if (columnCount != 2L) {
            if (columnCount < 2L) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is less than expected - expected 2 but was " + columnCount);
            }
            if (!b) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is more than expected - expected 2 but was " + columnCount);
            }
            RealmLog.debug("Field count is more than expected - expected 2 but was %1$d", new Object[] { columnCount });
        }
        final HashMap<String, RealmFieldType> hashMap = new HashMap<String, RealmFieldType>();
        for (long n = 0L; n < 2L; ++n) {
            hashMap.put(table.getColumnName(n), table.getColumnType(n));
        }
        final RealmSeasonRealmProxy$RealmSeasonColumnInfo realmSeasonRealmProxy$RealmSeasonColumnInfo = new RealmSeasonRealmProxy$RealmSeasonColumnInfo(sharedRealm.getPath(), table);
        if (!hashMap.containsKey("number")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'number' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (hashMap.get("number") != RealmFieldType.INTEGER) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'int' for field 'number' in existing Realm file.");
        }
        if (table.isColumnNullable(realmSeasonRealmProxy$RealmSeasonColumnInfo.numberIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'number' does support null values in the existing Realm file. Use corresponding boxed type for field 'number' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!hashMap.containsKey("label")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'label' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (hashMap.get("label") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'label' in existing Realm file.");
        }
        if (!table.isColumnNullable(realmSeasonRealmProxy$RealmSeasonColumnInfo.labelIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'label' is required. Either set @Required to field 'label' or migrate using RealmObjectSchema.setNullable().");
        }
        return realmSeasonRealmProxy$RealmSeasonColumnInfo;
    }
    
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            final RealmSeasonRealmProxy realmSeasonRealmProxy = (RealmSeasonRealmProxy)o;
            final String path = this.proxyState.getRealm$realm().getPath();
            final String path2 = realmSeasonRealmProxy.proxyState.getRealm$realm().getPath();
            Label_0069: {
                if (path != null) {
                    if (path.equals(path2)) {
                        break Label_0069;
                    }
                }
                else if (path2 == null) {
                    break Label_0069;
                }
                return false;
            }
            final String name = this.proxyState.getRow$realm().getTable().getName();
            final String name2 = realmSeasonRealmProxy.proxyState.getRow$realm().getTable().getName();
            Label_0119: {
                if (name != null) {
                    if (name.equals(name2)) {
                        break Label_0119;
                    }
                }
                else if (name2 == null) {
                    break Label_0119;
                }
                return false;
            }
            if (this.proxyState.getRow$realm().getIndex() != realmSeasonRealmProxy.proxyState.getRow$realm().getIndex()) {
                return false;
            }
        }
        return true;
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
    
    @Override
    public String realmGet$label() {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.labelIndex);
    }
    
    @Override
    public int realmGet$number() {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        this.proxyState.getRealm$realm().checkIfValid();
        return (int)this.proxyState.getRow$realm().getLong(this.columnInfo.numberIndex);
    }
    
    public ProxyState realmGet$proxyState() {
        return this.proxyState;
    }
    
    @Override
    public void realmSet$label(final String s) {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        if (this.proxyState.isUnderConstruction()) {
            if (!this.proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row$realm = this.proxyState.getRow$realm();
            if (s == null) {
                row$realm.getTable().setNull(this.columnInfo.labelIndex, row$realm.getIndex(), true);
                return;
            }
            row$realm.getTable().setString(this.columnInfo.labelIndex, row$realm.getIndex(), s, true);
        }
        else {
            this.proxyState.getRealm$realm().checkIfValid();
            if (s == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.labelIndex);
                return;
            }
            this.proxyState.getRow$realm().setString(this.columnInfo.labelIndex, s);
        }
    }
    
    @Override
    public void realmSet$number(final int n) {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.numberIndex, (long)n);
            return;
        }
        if (!this.proxyState.getAcceptDefaultValue$realm()) {
            return;
        }
        final Row row$realm = this.proxyState.getRow$realm();
        row$realm.getTable().setLong(this.columnInfo.numberIndex, row$realm.getIndex(), (long)n, true);
    }
    
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        final StringBuilder sb = new StringBuilder("RealmSeason = [");
        sb.append("{number:");
        sb.append(this.realmGet$number());
        sb.append("}");
        sb.append(",");
        sb.append("{label:");
        String realmGet$label;
        if (this.realmGet$label() != null) {
            realmGet$label = this.realmGet$label();
        }
        else {
            realmGet$label = "null";
        }
        sb.append(realmGet$label);
        sb.append("}");
        sb.append("]");
        return sb.toString();
    }
}
