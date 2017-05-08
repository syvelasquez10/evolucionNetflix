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
import com.netflix.mediaclient.servicemgr.interface_.offline.realm.RealmIncompleteVideoDetails;
import com.netflix.mediaclient.servicemgr.interface_.offline.realm.RealmProfile;
import com.netflix.mediaclient.servicemgr.interface_.offline.realm.RealmSeason;
import com.netflix.mediaclient.servicemgr.interface_.offline.realm.RealmPlayable;
import com.netflix.mediaclient.servicemgr.interface_.offline.realm.RealmVideoDetails;
import java.util.HashSet;
import java.util.Set;
import io.realm.annotations.RealmModule;
import io.realm.internal.RealmProxyMediator;

@RealmModule
class RealmOfflineModuleMediator extends RealmProxyMediator
{
    private static final Set<Class<? extends RealmModel>> MODEL_CLASSES;
    
    static {
        final HashSet<Class<? extends RealmModel>> set = new HashSet<Class<? extends RealmModel>>();
        set.add(RealmVideoDetails.class);
        set.add((Class<RealmVideoDetails>)RealmPlayable.class);
        set.add((Class<RealmVideoDetails>)RealmSeason.class);
        set.add((Class<RealmVideoDetails>)RealmProfile.class);
        set.add((Class<RealmVideoDetails>)RealmIncompleteVideoDetails.class);
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
        if (s.equals(RealmVideoDetails.class)) {
            return ((Class<E>)s).cast(RealmVideoDetailsRealmProxy.copyOrUpdate(realm, (RealmVideoDetails)e, b, (Map)map));
        }
        if (s.equals(RealmPlayable.class)) {
            return ((Class<E>)s).cast(RealmPlayableRealmProxy.copyOrUpdate(realm, (RealmPlayable)e, b, (Map)map));
        }
        if (s.equals(RealmSeason.class)) {
            return ((Class<E>)s).cast(RealmSeasonRealmProxy.copyOrUpdate(realm, (RealmSeason)e, b, (Map)map));
        }
        if (s.equals(RealmProfile.class)) {
            return ((Class<E>)s).cast(RealmProfileRealmProxy.copyOrUpdate(realm, (RealmProfile)e, b, (Map)map));
        }
        if (s.equals(RealmIncompleteVideoDetails.class)) {
            return ((Class<E>)s).cast(RealmIncompleteVideoDetailsRealmProxy.copyOrUpdate(realm, (RealmIncompleteVideoDetails)e, b, (Map)map));
        }
        throw getMissingProxyClassException((Class)s);
    }
    
    public RealmObjectSchema createRealmObjectSchema(final Class<? extends RealmModel> clazz, final RealmSchema realmSchema) {
        checkClass((Class)clazz);
        if (clazz.equals(RealmVideoDetails.class)) {
            return RealmVideoDetailsRealmProxy.createRealmObjectSchema(realmSchema);
        }
        if (clazz.equals(RealmPlayable.class)) {
            return RealmPlayableRealmProxy.createRealmObjectSchema(realmSchema);
        }
        if (clazz.equals(RealmSeason.class)) {
            return RealmSeasonRealmProxy.createRealmObjectSchema(realmSchema);
        }
        if (clazz.equals(RealmProfile.class)) {
            return RealmProfileRealmProxy.createRealmObjectSchema(realmSchema);
        }
        if (clazz.equals(RealmIncompleteVideoDetails.class)) {
            return RealmIncompleteVideoDetailsRealmProxy.createRealmObjectSchema(realmSchema);
        }
        throw getMissingProxyClassException((Class)clazz);
    }
    
    public Table createTable(final Class<? extends RealmModel> clazz, final SharedRealm sharedRealm) {
        checkClass((Class)clazz);
        if (clazz.equals(RealmVideoDetails.class)) {
            return RealmVideoDetailsRealmProxy.initTable(sharedRealm);
        }
        if (clazz.equals(RealmPlayable.class)) {
            return RealmPlayableRealmProxy.initTable(sharedRealm);
        }
        if (clazz.equals(RealmSeason.class)) {
            return RealmSeasonRealmProxy.initTable(sharedRealm);
        }
        if (clazz.equals(RealmProfile.class)) {
            return RealmProfileRealmProxy.initTable(sharedRealm);
        }
        if (clazz.equals(RealmIncompleteVideoDetails.class)) {
            return RealmIncompleteVideoDetailsRealmProxy.initTable(sharedRealm);
        }
        throw getMissingProxyClassException((Class)clazz);
    }
    
    public Set<Class<? extends RealmModel>> getModelClasses() {
        return RealmOfflineModuleMediator.MODEL_CLASSES;
    }
    
    public String getTableName(final Class<? extends RealmModel> clazz) {
        checkClass((Class)clazz);
        if (clazz.equals(RealmVideoDetails.class)) {
            return RealmVideoDetailsRealmProxy.getTableName();
        }
        if (clazz.equals(RealmPlayable.class)) {
            return RealmPlayableRealmProxy.getTableName();
        }
        if (clazz.equals(RealmSeason.class)) {
            return RealmSeasonRealmProxy.getTableName();
        }
        if (clazz.equals(RealmProfile.class)) {
            return RealmProfileRealmProxy.getTableName();
        }
        if (clazz.equals(RealmIncompleteVideoDetails.class)) {
            return RealmIncompleteVideoDetailsRealmProxy.getTableName();
        }
        throw getMissingProxyClassException((Class)clazz);
    }
    
    public <E extends RealmModel> E newInstance(final Class<E> clazz, final Object o, final Row row, final ColumnInfo columnInfo, final boolean b, final List<String> list) {
        final BaseRealm$RealmObjectContext baseRealm$RealmObjectContext = (BaseRealm$RealmObjectContext)BaseRealm.objectContext.get();
        try {
            baseRealm$RealmObjectContext.set((BaseRealm)o, row, columnInfo, b, (List)list);
            checkClass((Class)clazz);
            if (clazz.equals(RealmVideoDetails.class)) {
                return clazz.cast(new RealmVideoDetailsRealmProxy());
            }
            if (clazz.equals(RealmPlayable.class)) {
                return clazz.cast(new RealmPlayableRealmProxy());
            }
            if (clazz.equals(RealmSeason.class)) {
                return clazz.cast(new RealmSeasonRealmProxy());
            }
            if (clazz.equals(RealmProfile.class)) {
                return clazz.cast(new RealmProfileRealmProxy());
            }
            if (clazz.equals(RealmIncompleteVideoDetails.class)) {
                return clazz.cast(new RealmIncompleteVideoDetailsRealmProxy());
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
        if (clazz.equals(RealmVideoDetails.class)) {
            return (ColumnInfo)RealmVideoDetailsRealmProxy.validateTable(sharedRealm, b);
        }
        if (clazz.equals(RealmPlayable.class)) {
            return (ColumnInfo)RealmPlayableRealmProxy.validateTable(sharedRealm, b);
        }
        if (clazz.equals(RealmSeason.class)) {
            return (ColumnInfo)RealmSeasonRealmProxy.validateTable(sharedRealm, b);
        }
        if (clazz.equals(RealmProfile.class)) {
            return (ColumnInfo)RealmProfileRealmProxy.validateTable(sharedRealm, b);
        }
        if (clazz.equals(RealmIncompleteVideoDetails.class)) {
            return (ColumnInfo)RealmIncompleteVideoDetailsRealmProxy.validateTable(sharedRealm, b);
        }
        throw getMissingProxyClassException((Class)clazz);
    }
}
