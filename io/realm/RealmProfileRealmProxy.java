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
import java.io.FileNotFoundException;
import io.realm.exceptions.RealmFileException;
import io.realm.exceptions.RealmFileException$Kind;
import io.realm.internal.ColumnIndices;
import io.realm.exceptions.RealmException;
import java.util.HashMap;
import io.realm.log.RealmLog;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.internal.Table;
import io.realm.internal.SharedRealm;
import io.realm.internal.Row;
import java.util.Map;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import io.realm.internal.RealmObjectProxy;
import com.netflix.mediaclient.servicemgr.interface_.offline.realm.RealmProfile;

public class RealmProfileRealmProxy extends RealmProfile implements RealmProfileRealmProxyInterface, RealmObjectProxy
{
    private static final List<String> FIELD_NAMES;
    private RealmProfileRealmProxy$RealmProfileColumnInfo columnInfo;
    private ProxyState proxyState;
    
    static {
        final ArrayList<String> list = new ArrayList<String>();
        list.add("id");
        list.add("name");
        list.add("kids");
        list.add("iconUrl");
        FIELD_NAMES = Collections.unmodifiableList((List<?>)list);
    }
    
    RealmProfileRealmProxy() {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        this.proxyState.setConstructionFinished();
    }
    
    public static RealmProfile copy(final Realm realm, final RealmProfile realmProfile, final boolean b, final Map<RealmModel, RealmObjectProxy> map) {
        final RealmObjectProxy realmObjectProxy = map.get(realmProfile);
        if (realmObjectProxy != null) {
            return (RealmProfile)realmObjectProxy;
        }
        final RealmProfile realmProfile2 = realm.createObjectInternal(RealmProfile.class, realmProfile.realmGet$id(), false, Collections.emptyList());
        map.put(realmProfile, (RealmObjectProxy)realmProfile2);
        realmProfile2.realmSet$name(realmProfile.realmGet$name());
        realmProfile2.realmSet$kids(realmProfile.realmGet$kids());
        realmProfile2.realmSet$iconUrl(realmProfile.realmGet$iconUrl());
        return realmProfile2;
    }
    
    public static RealmProfile copyOrUpdate(final Realm realm, final RealmProfile realmProfile, final boolean b, final Map<RealmModel, RealmObjectProxy> map) {
        if (realmProfile instanceof RealmObjectProxy && ((RealmObjectProxy)realmProfile).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)realmProfile).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (realmProfile instanceof RealmObjectProxy && ((RealmObjectProxy)realmProfile).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)realmProfile).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return realmProfile;
        }
        final BaseRealm$RealmObjectContext baseRealm$RealmObjectContext = BaseRealm.objectContext.get();
        final RealmObjectProxy realmObjectProxy = map.get(realmProfile);
        if (realmObjectProxy != null) {
            return (RealmProfile)realmObjectProxy;
        }
        while (true) {
            if (!b) {
                final int n = b ? 1 : 0;
                final Object table = null;
                break Label_0247;
            }
            Object table = realm.getTable(RealmProfile.class);
            long n2 = ((Table)table).getPrimaryKey();
            final String realmGet$id = realmProfile.realmGet$id();
            Label_0261: {
                if (realmGet$id != null) {
                    break Label_0261;
                }
                n2 = ((Table)table).findFirstNull(n2);
                while (true) {
                    if (n2 == -1L) {
                        break Label_0261;
                    }
                    try {
                        baseRealm$RealmObjectContext.set(realm, (Row)((Table)table).getUncheckedRow(n2), realm.schema.getColumnInfo(RealmProfile.class), false, Collections.emptyList());
                        table = new RealmProfileRealmProxy();
                        map.put(realmProfile, (RealmObjectProxy)table);
                        baseRealm$RealmObjectContext.clear();
                        final int n = b ? 1 : 0;
                        if (n != 0) {
                            return update(realm, (RealmProfile)table, realmProfile, map);
                        }
                        return copy(realm, realmProfile, b, map);
                        n2 = ((Table)table).findFirstString(n2, realmGet$id);
                        continue;
                    }
                    finally {
                        baseRealm$RealmObjectContext.clear();
                    }
                    break;
                }
            }
            final int n = 0;
            table = null;
            continue;
        }
    }
    
    public static RealmObjectSchema createRealmObjectSchema(final RealmSchema realmSchema) {
        if (!realmSchema.contains("RealmProfile")) {
            final RealmObjectSchema create = realmSchema.create("RealmProfile");
            create.add(new Property("id", RealmFieldType.STRING, Property.PRIMARY_KEY, Property.INDEXED, !Property.REQUIRED));
            create.add(new Property("name", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            create.add(new Property("kids", RealmFieldType.BOOLEAN, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED));
            create.add(new Property("iconUrl", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            return create;
        }
        return realmSchema.get("RealmProfile");
    }
    
    public static String getTableName() {
        return "class_RealmProfile";
    }
    
    public static Table initTable(final SharedRealm sharedRealm) {
        if (!sharedRealm.hasTable("class_RealmProfile")) {
            final Table table = sharedRealm.getTable("class_RealmProfile");
            table.addColumn(RealmFieldType.STRING, "id", true);
            table.addColumn(RealmFieldType.STRING, "name", true);
            table.addColumn(RealmFieldType.BOOLEAN, "kids", false);
            table.addColumn(RealmFieldType.STRING, "iconUrl", true);
            table.addSearchIndex(table.getColumnIndex("id"));
            table.setPrimaryKey("id");
            return table;
        }
        return sharedRealm.getTable("class_RealmProfile");
    }
    
    private void injectObjectContext() {
        final BaseRealm$RealmObjectContext baseRealm$RealmObjectContext = BaseRealm.objectContext.get();
        this.columnInfo = (RealmProfileRealmProxy$RealmProfileColumnInfo)baseRealm$RealmObjectContext.getColumnInfo();
        (this.proxyState = new ProxyState(RealmProfile.class, (E)this)).setRealm$realm(baseRealm$RealmObjectContext.getRealm());
        this.proxyState.setRow$realm(baseRealm$RealmObjectContext.getRow());
        this.proxyState.setAcceptDefaultValue$realm(baseRealm$RealmObjectContext.getAcceptDefaultValue());
        this.proxyState.setExcludeFields$realm(baseRealm$RealmObjectContext.getExcludeFields());
    }
    
    static RealmProfile update(final Realm realm, final RealmProfile realmProfile, final RealmProfile realmProfile2, final Map<RealmModel, RealmObjectProxy> map) {
        realmProfile.realmSet$name(realmProfile2.realmGet$name());
        realmProfile.realmSet$kids(realmProfile2.realmGet$kids());
        realmProfile.realmSet$iconUrl(realmProfile2.realmGet$iconUrl());
        return realmProfile;
    }
    
    public static RealmProfileRealmProxy$RealmProfileColumnInfo validateTable(final SharedRealm sharedRealm, final boolean b) {
        if (!sharedRealm.hasTable("class_RealmProfile")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "The 'RealmProfile' class is missing from the schema for this Realm.");
        }
        final Table table = sharedRealm.getTable("class_RealmProfile");
        final long columnCount = table.getColumnCount();
        if (columnCount != 4L) {
            if (columnCount < 4L) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is less than expected - expected 4 but was " + columnCount);
            }
            if (!b) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is more than expected - expected 4 but was " + columnCount);
            }
            RealmLog.debug("Field count is more than expected - expected 4 but was %1$d", new Object[] { columnCount });
        }
        final HashMap<String, RealmFieldType> hashMap = new HashMap<String, RealmFieldType>();
        for (long n = 0L; n < 4L; ++n) {
            hashMap.put(table.getColumnName(n), table.getColumnType(n));
        }
        final RealmProfileRealmProxy$RealmProfileColumnInfo realmProfileRealmProxy$RealmProfileColumnInfo = new RealmProfileRealmProxy$RealmProfileColumnInfo(sharedRealm.getPath(), table);
        if (!hashMap.containsKey("id")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'id' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (hashMap.get("id") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'id' in existing Realm file.");
        }
        if (!table.isColumnNullable(realmProfileRealmProxy$RealmProfileColumnInfo.idIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "@PrimaryKey field 'id' does not support null values in the existing Realm file. Migrate using RealmObjectSchema.setNullable(), or mark the field as @Required.");
        }
        if (table.getPrimaryKey() != table.getColumnIndex("id")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Primary key not defined for field 'id' in existing Realm file. Add @PrimaryKey.");
        }
        if (!table.hasSearchIndex(table.getColumnIndex("id"))) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Index not defined for field 'id' in existing Realm file. Either set @Index or migrate using io.realm.internal.Table.removeSearchIndex().");
        }
        if (!hashMap.containsKey("name")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'name' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (hashMap.get("name") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'name' in existing Realm file.");
        }
        if (!table.isColumnNullable(realmProfileRealmProxy$RealmProfileColumnInfo.nameIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'name' is required. Either set @Required to field 'name' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!hashMap.containsKey("kids")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'kids' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (hashMap.get("kids") != RealmFieldType.BOOLEAN) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'boolean' for field 'kids' in existing Realm file.");
        }
        if (table.isColumnNullable(realmProfileRealmProxy$RealmProfileColumnInfo.kidsIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'kids' does support null values in the existing Realm file. Use corresponding boxed type for field 'kids' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!hashMap.containsKey("iconUrl")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'iconUrl' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (hashMap.get("iconUrl") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'iconUrl' in existing Realm file.");
        }
        if (!table.isColumnNullable(realmProfileRealmProxy$RealmProfileColumnInfo.iconUrlIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'iconUrl' is required. Either set @Required to field 'iconUrl' or migrate using RealmObjectSchema.setNullable().");
        }
        return realmProfileRealmProxy$RealmProfileColumnInfo;
    }
    
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            final RealmProfileRealmProxy realmProfileRealmProxy = (RealmProfileRealmProxy)o;
            final String path = this.proxyState.getRealm$realm().getPath();
            final String path2 = realmProfileRealmProxy.proxyState.getRealm$realm().getPath();
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
            final String name2 = realmProfileRealmProxy.proxyState.getRow$realm().getTable().getName();
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
            if (this.proxyState.getRow$realm().getIndex() != realmProfileRealmProxy.proxyState.getRow$realm().getIndex()) {
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
    public String realmGet$iconUrl() {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.iconUrlIndex);
    }
    
    @Override
    public String realmGet$id() {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.idIndex);
    }
    
    @Override
    public boolean realmGet$kids() {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBoolean(this.columnInfo.kidsIndex);
    }
    
    @Override
    public String realmGet$name() {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.nameIndex);
    }
    
    public ProxyState realmGet$proxyState() {
        return this.proxyState;
    }
    
    @Override
    public void realmSet$iconUrl(final String s) {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        if (this.proxyState.isUnderConstruction()) {
            if (!this.proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row$realm = this.proxyState.getRow$realm();
            if (s == null) {
                row$realm.getTable().setNull(this.columnInfo.iconUrlIndex, row$realm.getIndex(), true);
                return;
            }
            row$realm.getTable().setString(this.columnInfo.iconUrlIndex, row$realm.getIndex(), s, true);
        }
        else {
            this.proxyState.getRealm$realm().checkIfValid();
            if (s == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.iconUrlIndex);
                return;
            }
            this.proxyState.getRow$realm().setString(this.columnInfo.iconUrlIndex, s);
        }
    }
    
    @Override
    public void realmSet$id(final String s) {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        if (this.proxyState.isUnderConstruction()) {
            return;
        }
        this.proxyState.getRealm$realm().checkIfValid();
        throw new RealmException("Primary key field 'id' cannot be changed after object was created.");
    }
    
    @Override
    public void realmSet$kids(final boolean b) {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setBoolean(this.columnInfo.kidsIndex, b);
            return;
        }
        if (!this.proxyState.getAcceptDefaultValue$realm()) {
            return;
        }
        final Row row$realm = this.proxyState.getRow$realm();
        row$realm.getTable().setBoolean(this.columnInfo.kidsIndex, row$realm.getIndex(), b, true);
    }
    
    @Override
    public void realmSet$name(final String s) {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        if (this.proxyState.isUnderConstruction()) {
            if (!this.proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row$realm = this.proxyState.getRow$realm();
            if (s == null) {
                row$realm.getTable().setNull(this.columnInfo.nameIndex, row$realm.getIndex(), true);
                return;
            }
            row$realm.getTable().setString(this.columnInfo.nameIndex, row$realm.getIndex(), s, true);
        }
        else {
            this.proxyState.getRealm$realm().checkIfValid();
            if (s == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.nameIndex);
                return;
            }
            this.proxyState.getRow$realm().setString(this.columnInfo.nameIndex, s);
        }
    }
    
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        final StringBuilder sb = new StringBuilder("RealmProfile = [");
        sb.append("{id:");
        String realmGet$id;
        if (this.realmGet$id() != null) {
            realmGet$id = this.realmGet$id();
        }
        else {
            realmGet$id = "null";
        }
        sb.append(realmGet$id);
        sb.append("}");
        sb.append(",");
        sb.append("{name:");
        String realmGet$name;
        if (this.realmGet$name() != null) {
            realmGet$name = this.realmGet$name();
        }
        else {
            realmGet$name = "null";
        }
        sb.append(realmGet$name);
        sb.append("}");
        sb.append(",");
        sb.append("{kids:");
        sb.append(this.realmGet$kids());
        sb.append("}");
        sb.append(",");
        sb.append("{iconUrl:");
        String realmGet$iconUrl;
        if (this.realmGet$iconUrl() != null) {
            realmGet$iconUrl = this.realmGet$iconUrl();
        }
        else {
            realmGet$iconUrl = "null";
        }
        sb.append(realmGet$iconUrl);
        sb.append("}");
        sb.append("]");
        return sb.toString();
    }
}
