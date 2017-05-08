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
import com.netflix.mediaclient.servicemgr.interface_.offline.realm.RealmPlayable;

public class RealmPlayableRealmProxy extends RealmPlayable implements RealmPlayableRealmProxyInterface, RealmObjectProxy
{
    private static final List<String> FIELD_NAMES;
    private RealmPlayableRealmProxy$RealmPlayableColumnInfo columnInfo;
    private ProxyState proxyState;
    
    static {
        final ArrayList<String> list = new ArrayList<String>();
        list.add("playableId");
        list.add("parentId");
        list.add("title");
        list.add("seasonLabel");
        list.add("parentTitle");
        list.add("advisoriesString");
        list.add("isEpisode");
        list.add("isNSRE");
        list.add("isAutoPlay");
        list.add("isExemptFromLimit");
        list.add("isNextPlayableEpisode");
        list.add("isAgeProtected");
        list.add("isPinProtected");
        list.add("isAdvisoryDisabled");
        list.add("isAvailableToStream");
        list.add("isSupplementalVideo");
        list.add("duration");
        list.add("seasonNumber");
        list.add("episodeNumber");
        list.add("logicalStart");
        list.add("endtime");
        list.add("maxAutoplay");
        list.add("expTime");
        list.add("watchedTime");
        list.add("bookmark");
        FIELD_NAMES = Collections.unmodifiableList((List<?>)list);
    }
    
    RealmPlayableRealmProxy() {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        this.proxyState.setConstructionFinished();
    }
    
    public static RealmPlayable copy(final Realm realm, final RealmPlayable realmPlayable, final boolean b, final Map<RealmModel, RealmObjectProxy> map) {
        final RealmObjectProxy realmObjectProxy = map.get(realmPlayable);
        if (realmObjectProxy != null) {
            return (RealmPlayable)realmObjectProxy;
        }
        final RealmPlayable realmPlayable2 = realm.createObjectInternal(RealmPlayable.class, realmPlayable.realmGet$playableId(), false, Collections.emptyList());
        map.put(realmPlayable, (RealmObjectProxy)realmPlayable2);
        realmPlayable2.realmSet$parentId(realmPlayable.realmGet$parentId());
        realmPlayable2.realmSet$title(realmPlayable.realmGet$title());
        realmPlayable2.realmSet$seasonLabel(realmPlayable.realmGet$seasonLabel());
        realmPlayable2.realmSet$parentTitle(realmPlayable.realmGet$parentTitle());
        realmPlayable2.realmSet$advisoriesString(realmPlayable.realmGet$advisoriesString());
        realmPlayable2.realmSet$isEpisode(realmPlayable.realmGet$isEpisode());
        realmPlayable2.realmSet$isNSRE(realmPlayable.realmGet$isNSRE());
        realmPlayable2.realmSet$isAutoPlay(realmPlayable.realmGet$isAutoPlay());
        realmPlayable2.realmSet$isExemptFromLimit(realmPlayable.realmGet$isExemptFromLimit());
        realmPlayable2.realmSet$isNextPlayableEpisode(realmPlayable.realmGet$isNextPlayableEpisode());
        realmPlayable2.realmSet$isAgeProtected(realmPlayable.realmGet$isAgeProtected());
        realmPlayable2.realmSet$isPinProtected(realmPlayable.realmGet$isPinProtected());
        realmPlayable2.realmSet$isAdvisoryDisabled(realmPlayable.realmGet$isAdvisoryDisabled());
        realmPlayable2.realmSet$isAvailableToStream(realmPlayable.realmGet$isAvailableToStream());
        realmPlayable2.realmSet$isSupplementalVideo(realmPlayable.realmGet$isSupplementalVideo());
        realmPlayable2.realmSet$duration(realmPlayable.realmGet$duration());
        realmPlayable2.realmSet$seasonNumber(realmPlayable.realmGet$seasonNumber());
        realmPlayable2.realmSet$episodeNumber(realmPlayable.realmGet$episodeNumber());
        realmPlayable2.realmSet$logicalStart(realmPlayable.realmGet$logicalStart());
        realmPlayable2.realmSet$endtime(realmPlayable.realmGet$endtime());
        realmPlayable2.realmSet$maxAutoplay(realmPlayable.realmGet$maxAutoplay());
        realmPlayable2.realmSet$expTime(realmPlayable.realmGet$expTime());
        realmPlayable2.realmSet$watchedTime(realmPlayable.realmGet$watchedTime());
        realmPlayable2.realmSet$bookmark(realmPlayable.realmGet$bookmark());
        return realmPlayable2;
    }
    
    public static RealmPlayable copyOrUpdate(final Realm realm, final RealmPlayable realmPlayable, final boolean b, final Map<RealmModel, RealmObjectProxy> map) {
        if (realmPlayable instanceof RealmObjectProxy && ((RealmObjectProxy)realmPlayable).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)realmPlayable).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (realmPlayable instanceof RealmObjectProxy && ((RealmObjectProxy)realmPlayable).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)realmPlayable).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return realmPlayable;
        }
        final BaseRealm$RealmObjectContext baseRealm$RealmObjectContext = BaseRealm.objectContext.get();
        final RealmObjectProxy realmObjectProxy = map.get(realmPlayable);
        if (realmObjectProxy != null) {
            return (RealmPlayable)realmObjectProxy;
        }
        while (true) {
            if (!b) {
                final int n = b ? 1 : 0;
                final Object table = null;
                break Label_0248;
            }
            Object table = realm.getTable(RealmPlayable.class);
            long n2 = ((Table)table).getPrimaryKey();
            final String realmGet$playableId = realmPlayable.realmGet$playableId();
            Label_0262: {
                if (realmGet$playableId != null) {
                    break Label_0262;
                }
                n2 = ((Table)table).findFirstNull(n2);
                while (true) {
                    if (n2 == -1L) {
                        break Label_0262;
                    }
                    try {
                        baseRealm$RealmObjectContext.set(realm, (Row)((Table)table).getUncheckedRow(n2), realm.schema.getColumnInfo(RealmPlayable.class), false, Collections.emptyList());
                        table = new RealmPlayableRealmProxy();
                        map.put(realmPlayable, (RealmObjectProxy)table);
                        baseRealm$RealmObjectContext.clear();
                        final int n = b ? 1 : 0;
                        if (n != 0) {
                            return update(realm, (RealmPlayable)table, realmPlayable, map);
                        }
                        return copy(realm, realmPlayable, b, map);
                        n2 = ((Table)table).findFirstString(n2, realmGet$playableId);
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
        if (!realmSchema.contains("RealmPlayable")) {
            final RealmObjectSchema create = realmSchema.create("RealmPlayable");
            create.add(new Property("playableId", RealmFieldType.STRING, Property.PRIMARY_KEY, Property.INDEXED, !Property.REQUIRED));
            create.add(new Property("parentId", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            create.add(new Property("title", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            create.add(new Property("seasonLabel", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            create.add(new Property("parentTitle", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            create.add(new Property("advisoriesString", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            create.add(new Property("isEpisode", RealmFieldType.BOOLEAN, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED));
            create.add(new Property("isNSRE", RealmFieldType.BOOLEAN, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED));
            create.add(new Property("isAutoPlay", RealmFieldType.BOOLEAN, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED));
            create.add(new Property("isExemptFromLimit", RealmFieldType.BOOLEAN, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED));
            create.add(new Property("isNextPlayableEpisode", RealmFieldType.BOOLEAN, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED));
            create.add(new Property("isAgeProtected", RealmFieldType.BOOLEAN, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED));
            create.add(new Property("isPinProtected", RealmFieldType.BOOLEAN, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED));
            create.add(new Property("isAdvisoryDisabled", RealmFieldType.BOOLEAN, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED));
            create.add(new Property("isAvailableToStream", RealmFieldType.BOOLEAN, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED));
            create.add(new Property("isSupplementalVideo", RealmFieldType.BOOLEAN, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED));
            create.add(new Property("duration", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED));
            create.add(new Property("seasonNumber", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED));
            create.add(new Property("episodeNumber", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED));
            create.add(new Property("logicalStart", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED));
            create.add(new Property("endtime", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED));
            create.add(new Property("maxAutoplay", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED));
            create.add(new Property("expTime", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED));
            create.add(new Property("watchedTime", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED));
            create.add(new Property("bookmark", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED));
            return create;
        }
        return realmSchema.get("RealmPlayable");
    }
    
    public static String getTableName() {
        return "class_RealmPlayable";
    }
    
    public static Table initTable(final SharedRealm sharedRealm) {
        if (!sharedRealm.hasTable("class_RealmPlayable")) {
            final Table table = sharedRealm.getTable("class_RealmPlayable");
            table.addColumn(RealmFieldType.STRING, "playableId", true);
            table.addColumn(RealmFieldType.STRING, "parentId", true);
            table.addColumn(RealmFieldType.STRING, "title", true);
            table.addColumn(RealmFieldType.STRING, "seasonLabel", true);
            table.addColumn(RealmFieldType.STRING, "parentTitle", true);
            table.addColumn(RealmFieldType.STRING, "advisoriesString", true);
            table.addColumn(RealmFieldType.BOOLEAN, "isEpisode", false);
            table.addColumn(RealmFieldType.BOOLEAN, "isNSRE", false);
            table.addColumn(RealmFieldType.BOOLEAN, "isAutoPlay", false);
            table.addColumn(RealmFieldType.BOOLEAN, "isExemptFromLimit", false);
            table.addColumn(RealmFieldType.BOOLEAN, "isNextPlayableEpisode", false);
            table.addColumn(RealmFieldType.BOOLEAN, "isAgeProtected", false);
            table.addColumn(RealmFieldType.BOOLEAN, "isPinProtected", false);
            table.addColumn(RealmFieldType.BOOLEAN, "isAdvisoryDisabled", false);
            table.addColumn(RealmFieldType.BOOLEAN, "isAvailableToStream", false);
            table.addColumn(RealmFieldType.BOOLEAN, "isSupplementalVideo", false);
            table.addColumn(RealmFieldType.INTEGER, "duration", false);
            table.addColumn(RealmFieldType.INTEGER, "seasonNumber", false);
            table.addColumn(RealmFieldType.INTEGER, "episodeNumber", false);
            table.addColumn(RealmFieldType.INTEGER, "logicalStart", false);
            table.addColumn(RealmFieldType.INTEGER, "endtime", false);
            table.addColumn(RealmFieldType.INTEGER, "maxAutoplay", false);
            table.addColumn(RealmFieldType.INTEGER, "expTime", false);
            table.addColumn(RealmFieldType.INTEGER, "watchedTime", false);
            table.addColumn(RealmFieldType.INTEGER, "bookmark", false);
            table.addSearchIndex(table.getColumnIndex("playableId"));
            table.setPrimaryKey("playableId");
            return table;
        }
        return sharedRealm.getTable("class_RealmPlayable");
    }
    
    private void injectObjectContext() {
        final BaseRealm$RealmObjectContext baseRealm$RealmObjectContext = BaseRealm.objectContext.get();
        this.columnInfo = (RealmPlayableRealmProxy$RealmPlayableColumnInfo)baseRealm$RealmObjectContext.getColumnInfo();
        (this.proxyState = new ProxyState(RealmPlayable.class, (E)this)).setRealm$realm(baseRealm$RealmObjectContext.getRealm());
        this.proxyState.setRow$realm(baseRealm$RealmObjectContext.getRow());
        this.proxyState.setAcceptDefaultValue$realm(baseRealm$RealmObjectContext.getAcceptDefaultValue());
        this.proxyState.setExcludeFields$realm(baseRealm$RealmObjectContext.getExcludeFields());
    }
    
    static RealmPlayable update(final Realm realm, final RealmPlayable realmPlayable, final RealmPlayable realmPlayable2, final Map<RealmModel, RealmObjectProxy> map) {
        realmPlayable.realmSet$parentId(realmPlayable2.realmGet$parentId());
        realmPlayable.realmSet$title(realmPlayable2.realmGet$title());
        realmPlayable.realmSet$seasonLabel(realmPlayable2.realmGet$seasonLabel());
        realmPlayable.realmSet$parentTitle(realmPlayable2.realmGet$parentTitle());
        realmPlayable.realmSet$advisoriesString(realmPlayable2.realmGet$advisoriesString());
        realmPlayable.realmSet$isEpisode(realmPlayable2.realmGet$isEpisode());
        realmPlayable.realmSet$isNSRE(realmPlayable2.realmGet$isNSRE());
        realmPlayable.realmSet$isAutoPlay(realmPlayable2.realmGet$isAutoPlay());
        realmPlayable.realmSet$isExemptFromLimit(realmPlayable2.realmGet$isExemptFromLimit());
        realmPlayable.realmSet$isNextPlayableEpisode(realmPlayable2.realmGet$isNextPlayableEpisode());
        realmPlayable.realmSet$isAgeProtected(realmPlayable2.realmGet$isAgeProtected());
        realmPlayable.realmSet$isPinProtected(realmPlayable2.realmGet$isPinProtected());
        realmPlayable.realmSet$isAdvisoryDisabled(realmPlayable2.realmGet$isAdvisoryDisabled());
        realmPlayable.realmSet$isAvailableToStream(realmPlayable2.realmGet$isAvailableToStream());
        realmPlayable.realmSet$isSupplementalVideo(realmPlayable2.realmGet$isSupplementalVideo());
        realmPlayable.realmSet$duration(realmPlayable2.realmGet$duration());
        realmPlayable.realmSet$seasonNumber(realmPlayable2.realmGet$seasonNumber());
        realmPlayable.realmSet$episodeNumber(realmPlayable2.realmGet$episodeNumber());
        realmPlayable.realmSet$logicalStart(realmPlayable2.realmGet$logicalStart());
        realmPlayable.realmSet$endtime(realmPlayable2.realmGet$endtime());
        realmPlayable.realmSet$maxAutoplay(realmPlayable2.realmGet$maxAutoplay());
        realmPlayable.realmSet$expTime(realmPlayable2.realmGet$expTime());
        realmPlayable.realmSet$watchedTime(realmPlayable2.realmGet$watchedTime());
        realmPlayable.realmSet$bookmark(realmPlayable2.realmGet$bookmark());
        return realmPlayable;
    }
    
    public static RealmPlayableRealmProxy$RealmPlayableColumnInfo validateTable(final SharedRealm sharedRealm, final boolean b) {
        if (!sharedRealm.hasTable("class_RealmPlayable")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "The 'RealmPlayable' class is missing from the schema for this Realm.");
        }
        final Table table = sharedRealm.getTable("class_RealmPlayable");
        final long columnCount = table.getColumnCount();
        if (columnCount != 25L) {
            if (columnCount < 25L) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is less than expected - expected 25 but was " + columnCount);
            }
            if (!b) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is more than expected - expected 25 but was " + columnCount);
            }
            RealmLog.debug("Field count is more than expected - expected 25 but was %1$d", new Object[] { columnCount });
        }
        final HashMap<String, RealmFieldType> hashMap = new HashMap<String, RealmFieldType>();
        for (long n = 0L; n < 25L; ++n) {
            hashMap.put(table.getColumnName(n), table.getColumnType(n));
        }
        final RealmPlayableRealmProxy$RealmPlayableColumnInfo realmPlayableRealmProxy$RealmPlayableColumnInfo = new RealmPlayableRealmProxy$RealmPlayableColumnInfo(sharedRealm.getPath(), table);
        if (!hashMap.containsKey("playableId")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'playableId' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (hashMap.get("playableId") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'playableId' in existing Realm file.");
        }
        if (!table.isColumnNullable(realmPlayableRealmProxy$RealmPlayableColumnInfo.playableIdIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "@PrimaryKey field 'playableId' does not support null values in the existing Realm file. Migrate using RealmObjectSchema.setNullable(), or mark the field as @Required.");
        }
        if (table.getPrimaryKey() != table.getColumnIndex("playableId")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Primary key not defined for field 'playableId' in existing Realm file. Add @PrimaryKey.");
        }
        if (!table.hasSearchIndex(table.getColumnIndex("playableId"))) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Index not defined for field 'playableId' in existing Realm file. Either set @Index or migrate using io.realm.internal.Table.removeSearchIndex().");
        }
        if (!hashMap.containsKey("parentId")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'parentId' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (hashMap.get("parentId") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'parentId' in existing Realm file.");
        }
        if (!table.isColumnNullable(realmPlayableRealmProxy$RealmPlayableColumnInfo.parentIdIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'parentId' is required. Either set @Required to field 'parentId' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!hashMap.containsKey("title")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'title' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (hashMap.get("title") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'title' in existing Realm file.");
        }
        if (!table.isColumnNullable(realmPlayableRealmProxy$RealmPlayableColumnInfo.titleIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'title' is required. Either set @Required to field 'title' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!hashMap.containsKey("seasonLabel")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'seasonLabel' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (hashMap.get("seasonLabel") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'seasonLabel' in existing Realm file.");
        }
        if (!table.isColumnNullable(realmPlayableRealmProxy$RealmPlayableColumnInfo.seasonLabelIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'seasonLabel' is required. Either set @Required to field 'seasonLabel' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!hashMap.containsKey("parentTitle")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'parentTitle' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (hashMap.get("parentTitle") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'parentTitle' in existing Realm file.");
        }
        if (!table.isColumnNullable(realmPlayableRealmProxy$RealmPlayableColumnInfo.parentTitleIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'parentTitle' is required. Either set @Required to field 'parentTitle' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!hashMap.containsKey("advisoriesString")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'advisoriesString' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (hashMap.get("advisoriesString") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'advisoriesString' in existing Realm file.");
        }
        if (!table.isColumnNullable(realmPlayableRealmProxy$RealmPlayableColumnInfo.advisoriesStringIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'advisoriesString' is required. Either set @Required to field 'advisoriesString' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!hashMap.containsKey("isEpisode")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'isEpisode' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (hashMap.get("isEpisode") != RealmFieldType.BOOLEAN) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'boolean' for field 'isEpisode' in existing Realm file.");
        }
        if (table.isColumnNullable(realmPlayableRealmProxy$RealmPlayableColumnInfo.isEpisodeIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'isEpisode' does support null values in the existing Realm file. Use corresponding boxed type for field 'isEpisode' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!hashMap.containsKey("isNSRE")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'isNSRE' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (hashMap.get("isNSRE") != RealmFieldType.BOOLEAN) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'boolean' for field 'isNSRE' in existing Realm file.");
        }
        if (table.isColumnNullable(realmPlayableRealmProxy$RealmPlayableColumnInfo.isNSREIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'isNSRE' does support null values in the existing Realm file. Use corresponding boxed type for field 'isNSRE' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!hashMap.containsKey("isAutoPlay")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'isAutoPlay' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (hashMap.get("isAutoPlay") != RealmFieldType.BOOLEAN) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'boolean' for field 'isAutoPlay' in existing Realm file.");
        }
        if (table.isColumnNullable(realmPlayableRealmProxy$RealmPlayableColumnInfo.isAutoPlayIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'isAutoPlay' does support null values in the existing Realm file. Use corresponding boxed type for field 'isAutoPlay' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!hashMap.containsKey("isExemptFromLimit")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'isExemptFromLimit' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (hashMap.get("isExemptFromLimit") != RealmFieldType.BOOLEAN) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'boolean' for field 'isExemptFromLimit' in existing Realm file.");
        }
        if (table.isColumnNullable(realmPlayableRealmProxy$RealmPlayableColumnInfo.isExemptFromLimitIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'isExemptFromLimit' does support null values in the existing Realm file. Use corresponding boxed type for field 'isExemptFromLimit' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!hashMap.containsKey("isNextPlayableEpisode")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'isNextPlayableEpisode' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (hashMap.get("isNextPlayableEpisode") != RealmFieldType.BOOLEAN) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'boolean' for field 'isNextPlayableEpisode' in existing Realm file.");
        }
        if (table.isColumnNullable(realmPlayableRealmProxy$RealmPlayableColumnInfo.isNextPlayableEpisodeIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'isNextPlayableEpisode' does support null values in the existing Realm file. Use corresponding boxed type for field 'isNextPlayableEpisode' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!hashMap.containsKey("isAgeProtected")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'isAgeProtected' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (hashMap.get("isAgeProtected") != RealmFieldType.BOOLEAN) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'boolean' for field 'isAgeProtected' in existing Realm file.");
        }
        if (table.isColumnNullable(realmPlayableRealmProxy$RealmPlayableColumnInfo.isAgeProtectedIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'isAgeProtected' does support null values in the existing Realm file. Use corresponding boxed type for field 'isAgeProtected' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!hashMap.containsKey("isPinProtected")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'isPinProtected' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (hashMap.get("isPinProtected") != RealmFieldType.BOOLEAN) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'boolean' for field 'isPinProtected' in existing Realm file.");
        }
        if (table.isColumnNullable(realmPlayableRealmProxy$RealmPlayableColumnInfo.isPinProtectedIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'isPinProtected' does support null values in the existing Realm file. Use corresponding boxed type for field 'isPinProtected' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!hashMap.containsKey("isAdvisoryDisabled")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'isAdvisoryDisabled' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (hashMap.get("isAdvisoryDisabled") != RealmFieldType.BOOLEAN) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'boolean' for field 'isAdvisoryDisabled' in existing Realm file.");
        }
        if (table.isColumnNullable(realmPlayableRealmProxy$RealmPlayableColumnInfo.isAdvisoryDisabledIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'isAdvisoryDisabled' does support null values in the existing Realm file. Use corresponding boxed type for field 'isAdvisoryDisabled' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!hashMap.containsKey("isAvailableToStream")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'isAvailableToStream' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (hashMap.get("isAvailableToStream") != RealmFieldType.BOOLEAN) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'boolean' for field 'isAvailableToStream' in existing Realm file.");
        }
        if (table.isColumnNullable(realmPlayableRealmProxy$RealmPlayableColumnInfo.isAvailableToStreamIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'isAvailableToStream' does support null values in the existing Realm file. Use corresponding boxed type for field 'isAvailableToStream' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!hashMap.containsKey("isSupplementalVideo")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'isSupplementalVideo' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (hashMap.get("isSupplementalVideo") != RealmFieldType.BOOLEAN) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'boolean' for field 'isSupplementalVideo' in existing Realm file.");
        }
        if (table.isColumnNullable(realmPlayableRealmProxy$RealmPlayableColumnInfo.isSupplementalVideoIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'isSupplementalVideo' does support null values in the existing Realm file. Use corresponding boxed type for field 'isSupplementalVideo' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!hashMap.containsKey("duration")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'duration' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (hashMap.get("duration") != RealmFieldType.INTEGER) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'int' for field 'duration' in existing Realm file.");
        }
        if (table.isColumnNullable(realmPlayableRealmProxy$RealmPlayableColumnInfo.durationIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'duration' does support null values in the existing Realm file. Use corresponding boxed type for field 'duration' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!hashMap.containsKey("seasonNumber")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'seasonNumber' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (hashMap.get("seasonNumber") != RealmFieldType.INTEGER) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'int' for field 'seasonNumber' in existing Realm file.");
        }
        if (table.isColumnNullable(realmPlayableRealmProxy$RealmPlayableColumnInfo.seasonNumberIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'seasonNumber' does support null values in the existing Realm file. Use corresponding boxed type for field 'seasonNumber' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!hashMap.containsKey("episodeNumber")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'episodeNumber' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (hashMap.get("episodeNumber") != RealmFieldType.INTEGER) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'int' for field 'episodeNumber' in existing Realm file.");
        }
        if (table.isColumnNullable(realmPlayableRealmProxy$RealmPlayableColumnInfo.episodeNumberIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'episodeNumber' does support null values in the existing Realm file. Use corresponding boxed type for field 'episodeNumber' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!hashMap.containsKey("logicalStart")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'logicalStart' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (hashMap.get("logicalStart") != RealmFieldType.INTEGER) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'int' for field 'logicalStart' in existing Realm file.");
        }
        if (table.isColumnNullable(realmPlayableRealmProxy$RealmPlayableColumnInfo.logicalStartIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'logicalStart' does support null values in the existing Realm file. Use corresponding boxed type for field 'logicalStart' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!hashMap.containsKey("endtime")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'endtime' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (hashMap.get("endtime") != RealmFieldType.INTEGER) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'int' for field 'endtime' in existing Realm file.");
        }
        if (table.isColumnNullable(realmPlayableRealmProxy$RealmPlayableColumnInfo.endtimeIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'endtime' does support null values in the existing Realm file. Use corresponding boxed type for field 'endtime' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!hashMap.containsKey("maxAutoplay")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'maxAutoplay' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (hashMap.get("maxAutoplay") != RealmFieldType.INTEGER) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'int' for field 'maxAutoplay' in existing Realm file.");
        }
        if (table.isColumnNullable(realmPlayableRealmProxy$RealmPlayableColumnInfo.maxAutoplayIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'maxAutoplay' does support null values in the existing Realm file. Use corresponding boxed type for field 'maxAutoplay' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!hashMap.containsKey("expTime")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'expTime' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (hashMap.get("expTime") != RealmFieldType.INTEGER) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'long' for field 'expTime' in existing Realm file.");
        }
        if (table.isColumnNullable(realmPlayableRealmProxy$RealmPlayableColumnInfo.expTimeIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'expTime' does support null values in the existing Realm file. Use corresponding boxed type for field 'expTime' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!hashMap.containsKey("watchedTime")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'watchedTime' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (hashMap.get("watchedTime") != RealmFieldType.INTEGER) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'long' for field 'watchedTime' in existing Realm file.");
        }
        if (table.isColumnNullable(realmPlayableRealmProxy$RealmPlayableColumnInfo.watchedTimeIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'watchedTime' does support null values in the existing Realm file. Use corresponding boxed type for field 'watchedTime' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!hashMap.containsKey("bookmark")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'bookmark' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (hashMap.get("bookmark") != RealmFieldType.INTEGER) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'int' for field 'bookmark' in existing Realm file.");
        }
        if (table.isColumnNullable(realmPlayableRealmProxy$RealmPlayableColumnInfo.bookmarkIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'bookmark' does support null values in the existing Realm file. Use corresponding boxed type for field 'bookmark' or migrate using RealmObjectSchema.setNullable().");
        }
        return realmPlayableRealmProxy$RealmPlayableColumnInfo;
    }
    
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            final RealmPlayableRealmProxy realmPlayableRealmProxy = (RealmPlayableRealmProxy)o;
            final String path = this.proxyState.getRealm$realm().getPath();
            final String path2 = realmPlayableRealmProxy.proxyState.getRealm$realm().getPath();
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
            final String name2 = realmPlayableRealmProxy.proxyState.getRow$realm().getTable().getName();
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
            if (this.proxyState.getRow$realm().getIndex() != realmPlayableRealmProxy.proxyState.getRow$realm().getIndex()) {
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
    public String realmGet$advisoriesString() {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.advisoriesStringIndex);
    }
    
    @Override
    public int realmGet$bookmark() {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        this.proxyState.getRealm$realm().checkIfValid();
        return (int)this.proxyState.getRow$realm().getLong(this.columnInfo.bookmarkIndex);
    }
    
    @Override
    public int realmGet$duration() {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        this.proxyState.getRealm$realm().checkIfValid();
        return (int)this.proxyState.getRow$realm().getLong(this.columnInfo.durationIndex);
    }
    
    @Override
    public int realmGet$endtime() {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        this.proxyState.getRealm$realm().checkIfValid();
        return (int)this.proxyState.getRow$realm().getLong(this.columnInfo.endtimeIndex);
    }
    
    @Override
    public int realmGet$episodeNumber() {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        this.proxyState.getRealm$realm().checkIfValid();
        return (int)this.proxyState.getRow$realm().getLong(this.columnInfo.episodeNumberIndex);
    }
    
    @Override
    public long realmGet$expTime() {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getLong(this.columnInfo.expTimeIndex);
    }
    
    @Override
    public boolean realmGet$isAdvisoryDisabled() {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBoolean(this.columnInfo.isAdvisoryDisabledIndex);
    }
    
    @Override
    public boolean realmGet$isAgeProtected() {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBoolean(this.columnInfo.isAgeProtectedIndex);
    }
    
    @Override
    public boolean realmGet$isAutoPlay() {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBoolean(this.columnInfo.isAutoPlayIndex);
    }
    
    @Override
    public boolean realmGet$isAvailableToStream() {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBoolean(this.columnInfo.isAvailableToStreamIndex);
    }
    
    @Override
    public boolean realmGet$isEpisode() {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBoolean(this.columnInfo.isEpisodeIndex);
    }
    
    @Override
    public boolean realmGet$isExemptFromLimit() {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBoolean(this.columnInfo.isExemptFromLimitIndex);
    }
    
    @Override
    public boolean realmGet$isNSRE() {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBoolean(this.columnInfo.isNSREIndex);
    }
    
    @Override
    public boolean realmGet$isNextPlayableEpisode() {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBoolean(this.columnInfo.isNextPlayableEpisodeIndex);
    }
    
    @Override
    public boolean realmGet$isPinProtected() {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBoolean(this.columnInfo.isPinProtectedIndex);
    }
    
    @Override
    public boolean realmGet$isSupplementalVideo() {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBoolean(this.columnInfo.isSupplementalVideoIndex);
    }
    
    @Override
    public int realmGet$logicalStart() {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        this.proxyState.getRealm$realm().checkIfValid();
        return (int)this.proxyState.getRow$realm().getLong(this.columnInfo.logicalStartIndex);
    }
    
    @Override
    public int realmGet$maxAutoplay() {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        this.proxyState.getRealm$realm().checkIfValid();
        return (int)this.proxyState.getRow$realm().getLong(this.columnInfo.maxAutoplayIndex);
    }
    
    @Override
    public String realmGet$parentId() {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.parentIdIndex);
    }
    
    @Override
    public String realmGet$parentTitle() {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.parentTitleIndex);
    }
    
    @Override
    public String realmGet$playableId() {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.playableIdIndex);
    }
    
    public ProxyState realmGet$proxyState() {
        return this.proxyState;
    }
    
    @Override
    public String realmGet$seasonLabel() {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.seasonLabelIndex);
    }
    
    @Override
    public int realmGet$seasonNumber() {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        this.proxyState.getRealm$realm().checkIfValid();
        return (int)this.proxyState.getRow$realm().getLong(this.columnInfo.seasonNumberIndex);
    }
    
    @Override
    public String realmGet$title() {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.titleIndex);
    }
    
    @Override
    public long realmGet$watchedTime() {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getLong(this.columnInfo.watchedTimeIndex);
    }
    
    @Override
    public void realmSet$advisoriesString(final String s) {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        if (this.proxyState.isUnderConstruction()) {
            if (!this.proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row$realm = this.proxyState.getRow$realm();
            if (s == null) {
                row$realm.getTable().setNull(this.columnInfo.advisoriesStringIndex, row$realm.getIndex(), true);
                return;
            }
            row$realm.getTable().setString(this.columnInfo.advisoriesStringIndex, row$realm.getIndex(), s, true);
        }
        else {
            this.proxyState.getRealm$realm().checkIfValid();
            if (s == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.advisoriesStringIndex);
                return;
            }
            this.proxyState.getRow$realm().setString(this.columnInfo.advisoriesStringIndex, s);
        }
    }
    
    @Override
    public void realmSet$bookmark(final int n) {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.bookmarkIndex, (long)n);
            return;
        }
        if (!this.proxyState.getAcceptDefaultValue$realm()) {
            return;
        }
        final Row row$realm = this.proxyState.getRow$realm();
        row$realm.getTable().setLong(this.columnInfo.bookmarkIndex, row$realm.getIndex(), (long)n, true);
    }
    
    @Override
    public void realmSet$duration(final int n) {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.durationIndex, (long)n);
            return;
        }
        if (!this.proxyState.getAcceptDefaultValue$realm()) {
            return;
        }
        final Row row$realm = this.proxyState.getRow$realm();
        row$realm.getTable().setLong(this.columnInfo.durationIndex, row$realm.getIndex(), (long)n, true);
    }
    
    @Override
    public void realmSet$endtime(final int n) {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.endtimeIndex, (long)n);
            return;
        }
        if (!this.proxyState.getAcceptDefaultValue$realm()) {
            return;
        }
        final Row row$realm = this.proxyState.getRow$realm();
        row$realm.getTable().setLong(this.columnInfo.endtimeIndex, row$realm.getIndex(), (long)n, true);
    }
    
    @Override
    public void realmSet$episodeNumber(final int n) {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.episodeNumberIndex, (long)n);
            return;
        }
        if (!this.proxyState.getAcceptDefaultValue$realm()) {
            return;
        }
        final Row row$realm = this.proxyState.getRow$realm();
        row$realm.getTable().setLong(this.columnInfo.episodeNumberIndex, row$realm.getIndex(), (long)n, true);
    }
    
    @Override
    public void realmSet$expTime(final long n) {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.expTimeIndex, n);
            return;
        }
        if (!this.proxyState.getAcceptDefaultValue$realm()) {
            return;
        }
        final Row row$realm = this.proxyState.getRow$realm();
        row$realm.getTable().setLong(this.columnInfo.expTimeIndex, row$realm.getIndex(), n, true);
    }
    
    @Override
    public void realmSet$isAdvisoryDisabled(final boolean b) {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setBoolean(this.columnInfo.isAdvisoryDisabledIndex, b);
            return;
        }
        if (!this.proxyState.getAcceptDefaultValue$realm()) {
            return;
        }
        final Row row$realm = this.proxyState.getRow$realm();
        row$realm.getTable().setBoolean(this.columnInfo.isAdvisoryDisabledIndex, row$realm.getIndex(), b, true);
    }
    
    @Override
    public void realmSet$isAgeProtected(final boolean b) {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setBoolean(this.columnInfo.isAgeProtectedIndex, b);
            return;
        }
        if (!this.proxyState.getAcceptDefaultValue$realm()) {
            return;
        }
        final Row row$realm = this.proxyState.getRow$realm();
        row$realm.getTable().setBoolean(this.columnInfo.isAgeProtectedIndex, row$realm.getIndex(), b, true);
    }
    
    @Override
    public void realmSet$isAutoPlay(final boolean b) {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setBoolean(this.columnInfo.isAutoPlayIndex, b);
            return;
        }
        if (!this.proxyState.getAcceptDefaultValue$realm()) {
            return;
        }
        final Row row$realm = this.proxyState.getRow$realm();
        row$realm.getTable().setBoolean(this.columnInfo.isAutoPlayIndex, row$realm.getIndex(), b, true);
    }
    
    @Override
    public void realmSet$isAvailableToStream(final boolean b) {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setBoolean(this.columnInfo.isAvailableToStreamIndex, b);
            return;
        }
        if (!this.proxyState.getAcceptDefaultValue$realm()) {
            return;
        }
        final Row row$realm = this.proxyState.getRow$realm();
        row$realm.getTable().setBoolean(this.columnInfo.isAvailableToStreamIndex, row$realm.getIndex(), b, true);
    }
    
    @Override
    public void realmSet$isEpisode(final boolean b) {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setBoolean(this.columnInfo.isEpisodeIndex, b);
            return;
        }
        if (!this.proxyState.getAcceptDefaultValue$realm()) {
            return;
        }
        final Row row$realm = this.proxyState.getRow$realm();
        row$realm.getTable().setBoolean(this.columnInfo.isEpisodeIndex, row$realm.getIndex(), b, true);
    }
    
    @Override
    public void realmSet$isExemptFromLimit(final boolean b) {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setBoolean(this.columnInfo.isExemptFromLimitIndex, b);
            return;
        }
        if (!this.proxyState.getAcceptDefaultValue$realm()) {
            return;
        }
        final Row row$realm = this.proxyState.getRow$realm();
        row$realm.getTable().setBoolean(this.columnInfo.isExemptFromLimitIndex, row$realm.getIndex(), b, true);
    }
    
    @Override
    public void realmSet$isNSRE(final boolean b) {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setBoolean(this.columnInfo.isNSREIndex, b);
            return;
        }
        if (!this.proxyState.getAcceptDefaultValue$realm()) {
            return;
        }
        final Row row$realm = this.proxyState.getRow$realm();
        row$realm.getTable().setBoolean(this.columnInfo.isNSREIndex, row$realm.getIndex(), b, true);
    }
    
    @Override
    public void realmSet$isNextPlayableEpisode(final boolean b) {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setBoolean(this.columnInfo.isNextPlayableEpisodeIndex, b);
            return;
        }
        if (!this.proxyState.getAcceptDefaultValue$realm()) {
            return;
        }
        final Row row$realm = this.proxyState.getRow$realm();
        row$realm.getTable().setBoolean(this.columnInfo.isNextPlayableEpisodeIndex, row$realm.getIndex(), b, true);
    }
    
    @Override
    public void realmSet$isPinProtected(final boolean b) {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setBoolean(this.columnInfo.isPinProtectedIndex, b);
            return;
        }
        if (!this.proxyState.getAcceptDefaultValue$realm()) {
            return;
        }
        final Row row$realm = this.proxyState.getRow$realm();
        row$realm.getTable().setBoolean(this.columnInfo.isPinProtectedIndex, row$realm.getIndex(), b, true);
    }
    
    @Override
    public void realmSet$isSupplementalVideo(final boolean b) {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setBoolean(this.columnInfo.isSupplementalVideoIndex, b);
            return;
        }
        if (!this.proxyState.getAcceptDefaultValue$realm()) {
            return;
        }
        final Row row$realm = this.proxyState.getRow$realm();
        row$realm.getTable().setBoolean(this.columnInfo.isSupplementalVideoIndex, row$realm.getIndex(), b, true);
    }
    
    @Override
    public void realmSet$logicalStart(final int n) {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.logicalStartIndex, (long)n);
            return;
        }
        if (!this.proxyState.getAcceptDefaultValue$realm()) {
            return;
        }
        final Row row$realm = this.proxyState.getRow$realm();
        row$realm.getTable().setLong(this.columnInfo.logicalStartIndex, row$realm.getIndex(), (long)n, true);
    }
    
    @Override
    public void realmSet$maxAutoplay(final int n) {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.maxAutoplayIndex, (long)n);
            return;
        }
        if (!this.proxyState.getAcceptDefaultValue$realm()) {
            return;
        }
        final Row row$realm = this.proxyState.getRow$realm();
        row$realm.getTable().setLong(this.columnInfo.maxAutoplayIndex, row$realm.getIndex(), (long)n, true);
    }
    
    @Override
    public void realmSet$parentId(final String s) {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        if (this.proxyState.isUnderConstruction()) {
            if (!this.proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row$realm = this.proxyState.getRow$realm();
            if (s == null) {
                row$realm.getTable().setNull(this.columnInfo.parentIdIndex, row$realm.getIndex(), true);
                return;
            }
            row$realm.getTable().setString(this.columnInfo.parentIdIndex, row$realm.getIndex(), s, true);
        }
        else {
            this.proxyState.getRealm$realm().checkIfValid();
            if (s == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.parentIdIndex);
                return;
            }
            this.proxyState.getRow$realm().setString(this.columnInfo.parentIdIndex, s);
        }
    }
    
    @Override
    public void realmSet$parentTitle(final String s) {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        if (this.proxyState.isUnderConstruction()) {
            if (!this.proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row$realm = this.proxyState.getRow$realm();
            if (s == null) {
                row$realm.getTable().setNull(this.columnInfo.parentTitleIndex, row$realm.getIndex(), true);
                return;
            }
            row$realm.getTable().setString(this.columnInfo.parentTitleIndex, row$realm.getIndex(), s, true);
        }
        else {
            this.proxyState.getRealm$realm().checkIfValid();
            if (s == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.parentTitleIndex);
                return;
            }
            this.proxyState.getRow$realm().setString(this.columnInfo.parentTitleIndex, s);
        }
    }
    
    @Override
    public void realmSet$playableId(final String s) {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        if (this.proxyState.isUnderConstruction()) {
            return;
        }
        this.proxyState.getRealm$realm().checkIfValid();
        throw new RealmException("Primary key field 'playableId' cannot be changed after object was created.");
    }
    
    @Override
    public void realmSet$seasonLabel(final String s) {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        if (this.proxyState.isUnderConstruction()) {
            if (!this.proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row$realm = this.proxyState.getRow$realm();
            if (s == null) {
                row$realm.getTable().setNull(this.columnInfo.seasonLabelIndex, row$realm.getIndex(), true);
                return;
            }
            row$realm.getTable().setString(this.columnInfo.seasonLabelIndex, row$realm.getIndex(), s, true);
        }
        else {
            this.proxyState.getRealm$realm().checkIfValid();
            if (s == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.seasonLabelIndex);
                return;
            }
            this.proxyState.getRow$realm().setString(this.columnInfo.seasonLabelIndex, s);
        }
    }
    
    @Override
    public void realmSet$seasonNumber(final int n) {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.seasonNumberIndex, (long)n);
            return;
        }
        if (!this.proxyState.getAcceptDefaultValue$realm()) {
            return;
        }
        final Row row$realm = this.proxyState.getRow$realm();
        row$realm.getTable().setLong(this.columnInfo.seasonNumberIndex, row$realm.getIndex(), (long)n, true);
    }
    
    @Override
    public void realmSet$title(final String s) {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        if (this.proxyState.isUnderConstruction()) {
            if (!this.proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row$realm = this.proxyState.getRow$realm();
            if (s == null) {
                row$realm.getTable().setNull(this.columnInfo.titleIndex, row$realm.getIndex(), true);
                return;
            }
            row$realm.getTable().setString(this.columnInfo.titleIndex, row$realm.getIndex(), s, true);
        }
        else {
            this.proxyState.getRealm$realm().checkIfValid();
            if (s == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.titleIndex);
                return;
            }
            this.proxyState.getRow$realm().setString(this.columnInfo.titleIndex, s);
        }
    }
    
    @Override
    public void realmSet$watchedTime(final long n) {
        if (this.proxyState == null) {
            this.injectObjectContext();
        }
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.watchedTimeIndex, n);
            return;
        }
        if (!this.proxyState.getAcceptDefaultValue$realm()) {
            return;
        }
        final Row row$realm = this.proxyState.getRow$realm();
        row$realm.getTable().setLong(this.columnInfo.watchedTimeIndex, row$realm.getIndex(), n, true);
    }
}
