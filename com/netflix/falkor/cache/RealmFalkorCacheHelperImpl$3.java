// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.cache;

import java.util.ListIterator;
import java.util.Iterator;
import io.realm.internal.Collection;
import io.realm.BaseRealm;
import io.realm.OrderedRealmCollectionImpl;
import io.realm.RealmResults;
import io.realm.RealmModel;
import io.realm.Realm;
import java.util.List;
import io.realm.Realm$Transaction;

class RealmFalkorCacheHelperImpl$3 implements Realm$Transaction
{
    final /* synthetic */ RealmFalkorCacheHelperImpl this$0;
    final /* synthetic */ Class val$clazz;
    final /* synthetic */ List val$path;
    
    RealmFalkorCacheHelperImpl$3(final RealmFalkorCacheHelperImpl this$0, final List val$path, final Class val$clazz) {
        this.this$0 = this$0;
        this.val$path = val$path;
        this.val$clazz = val$clazz;
    }
    
    public void execute(final Realm realm) {
        final String string = this.val$path.toString();
        final RealmResults all = realm.where((Class<RealmModel>)this.val$clazz).beginsWith("path", string.substring(0, string.length() - 2)).findAll();
        FalkorCache.getMonitor().delete(all.size());
        all.deleteAllFromRealm();
    }
}
