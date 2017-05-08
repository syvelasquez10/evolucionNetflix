// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_.offline.realm;

import java.util.ListIterator;
import java.util.Iterator;
import io.realm.internal.Collection;
import io.realm.BaseRealm;
import io.realm.OrderedRealmCollectionImpl;
import io.realm.RealmModel;
import io.realm.Realm;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import io.realm.Realm$Transaction;

final class RealmVideoDetails$2 implements Realm$Transaction
{
    final /* synthetic */ VideoDetails val$details;
    
    RealmVideoDetails$2(final VideoDetails val$details) {
        this.val$details = val$details;
    }
    
    public void execute(final Realm realm) {
        realm.where(RealmIncompleteVideoDetails.class).equalTo("playableId", this.val$details.getId()).findAll().deleteAllFromRealm();
    }
}
