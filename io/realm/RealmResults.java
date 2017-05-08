// 
// Decompiled by Procyon v0.5.30
// 

package io.realm;

import java.util.ListIterator;
import java.util.Iterator;
import io.realm.internal.Collection;

public class RealmResults<E extends RealmModel> extends OrderedRealmCollectionImpl<E>
{
    RealmResults(final BaseRealm baseRealm, final io.realm.internal.Collection collection, final Class<E> clazz) {
        super(baseRealm, collection, clazz);
    }
    
    RealmResults(final BaseRealm baseRealm, final io.realm.internal.Collection collection, final String s) {
        super(baseRealm, collection, s);
    }
    
    public boolean isLoaded() {
        this.realm.checkIfValid();
        return this.collection.isLoaded();
    }
    
    public boolean load() {
        this.realm.checkIfValid();
        this.collection.load();
        return true;
    }
}
