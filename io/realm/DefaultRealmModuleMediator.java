// 
// Decompiled by Procyon v0.5.30
// 

package io.realm;

import java.util.List;
import io.realm.internal.ColumnInfo;
import io.realm.internal.Row;
import io.realm.internal.Table;
import io.realm.internal.SharedRealm;
import java.io.Serializable;
import io.realm.internal.RealmObjectProxy;
import java.util.Map;
import java.util.Collections;
import com.netflix.mediaclient.servicemgr.interface_.offline.realm.RealmVideoDetails;
import com.netflix.mediaclient.servicemgr.interface_.offline.realm.RealmSeason;
import com.netflix.mediaclient.servicemgr.interface_.offline.realm.RealmProfile;
import com.netflix.mediaclient.servicemgr.interface_.offline.realm.RealmPlayable;
import java.util.HashSet;
import java.util.Set;
import io.realm.annotations.RealmModule;
import io.realm.internal.RealmProxyMediator;

@RealmModule
class DefaultRealmModuleMediator extends RealmProxyMediator
{
    private static final Set<Class<? extends RealmModel>> MODEL_CLASSES;
    
    static {
        final HashSet<Class<RealmPlayable>> set = new HashSet<Class<RealmPlayable>>();
        set.add(RealmPlayable.class);
        set.add((Class<RealmPlayable>)RealmProfile.class);
        set.add((Class<RealmPlayable>)RealmSeason.class);
        set.add((Class<RealmPlayable>)RealmVideoDetails.class);
        MODEL_CLASSES = Collections.unmodifiableSet((Set<?>)set);
    }
    
    public <E extends RealmModel> E copyOrUpdate(final Realm realm, final E e, final boolean b, final Map<RealmModel, RealmObjectProxy> map) {
        Serializable s;
        if (e instanceof RealmObjectProxy) {
            s = e.getClass().getSuperclass();
        }
        else {
            s = e.getClass();
        }
        if (s.equals(RealmPlayable.class)) {
            return ((Class<E>)s).cast(RealmPlayableRealmProxy.copyOrUpdate(realm, (RealmPlayable)e, b, map));
        }
        if (s.equals(RealmProfile.class)) {
            return ((Class<E>)s).cast(RealmProfileRealmProxy.copyOrUpdate(realm, (RealmProfile)e, b, map));
        }
        if (s.equals(RealmSeason.class)) {
            return ((Class<E>)s).cast(RealmSeasonRealmProxy.copyOrUpdate(realm, (RealmSeason)e, b, map));
        }
        if (s.equals(RealmVideoDetails.class)) {
            return ((Class<E>)s).cast(RealmVideoDetailsRealmProxy.copyOrUpdate(realm, (RealmVideoDetails)e, b, (Map)map));
        }
        throw getMissingProxyClassException((Class)s);
    }
    
    public RealmObjectSchema createRealmObjectSchema(final Class<? extends RealmModel> clazz, final RealmSchema realmSchema) {
        checkClass((Class)clazz);
        if (clazz.equals(RealmPlayable.class)) {
            return RealmPlayableRealmProxy.createRealmObjectSchema(realmSchema);
        }
        if (clazz.equals(RealmProfile.class)) {
            return RealmProfileRealmProxy.createRealmObjectSchema(realmSchema);
        }
        if (clazz.equals(RealmSeason.class)) {
            return RealmSeasonRealmProxy.createRealmObjectSchema(realmSchema);
        }
        if (clazz.equals(RealmVideoDetails.class)) {
            return RealmVideoDetailsRealmProxy.createRealmObjectSchema(realmSchema);
        }
        throw getMissingProxyClassException((Class)clazz);
    }
    
    public Table createTable(final Class<? extends RealmModel> clazz, final SharedRealm sharedRealm) {
        checkClass((Class)clazz);
        if (clazz.equals(RealmPlayable.class)) {
            return RealmPlayableRealmProxy.initTable(sharedRealm);
        }
        if (clazz.equals(RealmProfile.class)) {
            return RealmProfileRealmProxy.initTable(sharedRealm);
        }
        if (clazz.equals(RealmSeason.class)) {
            return RealmSeasonRealmProxy.initTable(sharedRealm);
        }
        if (clazz.equals(RealmVideoDetails.class)) {
            return RealmVideoDetailsRealmProxy.initTable(sharedRealm);
        }
        throw getMissingProxyClassException((Class)clazz);
    }
    
    public Set<Class<? extends RealmModel>> getModelClasses() {
        return DefaultRealmModuleMediator.MODEL_CLASSES;
    }
    
    public String getTableName(final Class<? extends RealmModel> clazz) {
        checkClass((Class)clazz);
        if (clazz.equals(RealmPlayable.class)) {
            return RealmPlayableRealmProxy.getTableName();
        }
        if (clazz.equals(RealmProfile.class)) {
            return RealmProfileRealmProxy.getTableName();
        }
        if (clazz.equals(RealmSeason.class)) {
            return RealmSeasonRealmProxy.getTableName();
        }
        if (clazz.equals(RealmVideoDetails.class)) {
            return RealmVideoDetailsRealmProxy.getTableName();
        }
        throw getMissingProxyClassException((Class)clazz);
    }
    
    public <E extends RealmModel> E newInstance(final Class<E> clazz, final Object o, final Row row, final ColumnInfo columnInfo, final boolean b, final List<String> list) {
        final BaseRealm$RealmObjectContext baseRealm$RealmObjectContext = BaseRealm.objectContext.get();
        try {
            baseRealm$RealmObjectContext.set((BaseRealm)o, row, columnInfo, b, list);
            checkClass((Class)clazz);
            if (clazz.equals(RealmPlayable.class)) {
                return clazz.cast(new RealmPlayableRealmProxy());
            }
            if (clazz.equals(RealmProfile.class)) {
                return clazz.cast(new RealmProfileRealmProxy());
            }
            if (clazz.equals(RealmSeason.class)) {
                return clazz.cast(new RealmSeasonRealmProxy());
            }
            if (clazz.equals(RealmVideoDetails.class)) {
                return clazz.cast(new RealmVideoDetailsRealmProxy());
            }
            throw getMissingProxyClassException((Class)clazz);
        }
        finally {
            baseRealm$RealmObjectContext.clear();
        }
    }
    
    public boolean transformerApplied() {
        return true;
    }
    
    public ColumnInfo validateTable(final Class<? extends RealmModel> clazz, final SharedRealm sharedRealm, final boolean b) {
        checkClass((Class)clazz);
        if (clazz.equals(RealmPlayable.class)) {
            return RealmPlayableRealmProxy.validateTable(sharedRealm, b);
        }
        if (clazz.equals(RealmProfile.class)) {
            return RealmProfileRealmProxy.validateTable(sharedRealm, b);
        }
        if (clazz.equals(RealmSeason.class)) {
            return RealmSeasonRealmProxy.validateTable(sharedRealm, b);
        }
        if (clazz.equals(RealmVideoDetails.class)) {
            return (ColumnInfo)RealmVideoDetailsRealmProxy.validateTable(sharedRealm, b);
        }
        throw getMissingProxyClassException((Class)clazz);
    }
}
