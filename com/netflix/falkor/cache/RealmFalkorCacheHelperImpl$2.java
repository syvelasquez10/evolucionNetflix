// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.cache;

import java.util.ListIterator;
import java.util.Iterator;
import io.realm.internal.Collection;
import io.realm.BaseRealm;
import io.realm.OrderedRealmCollectionImpl;
import io.realm.RealmModel;
import io.realm.RealmResults;
import java.util.Date;
import io.realm.Realm;
import io.realm.Realm$Transaction;

class RealmFalkorCacheHelperImpl$2 implements Realm$Transaction
{
    final /* synthetic */ RealmFalkorCacheHelperImpl this$0;
    
    RealmFalkorCacheHelperImpl$2(final RealmFalkorCacheHelperImpl this$0) {
        this.this$0 = this$0;
    }
    
    public void execute(final Realm realm) {
        final Date date = new Date();
        final RealmResults all = realm.where(FalkorRealmCacheHomeLolomo.class).lessThan("expiry", date).findAll();
        FalkorCache.getMonitor().delete(all.size());
        all.deleteAllFromRealm();
        final RealmResults all2 = realm.where(FalkorRealmCacheTimeBased.class).lessThan("expiry", date).findAll();
        FalkorCache.getMonitor().delete(all2.size());
        all2.deleteAllFromRealm();
    }
}
