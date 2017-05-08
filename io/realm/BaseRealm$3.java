// 
// Decompiled by Procyon v0.5.30
// 

package io.realm;

import io.realm.internal.Util;
import java.util.concurrent.atomic.AtomicBoolean;

final class BaseRealm$3 implements RealmCache$Callback
{
    final /* synthetic */ RealmConfiguration val$configuration;
    final /* synthetic */ AtomicBoolean val$realmDeleted;
    
    BaseRealm$3(final RealmConfiguration val$configuration, final AtomicBoolean val$realmDeleted) {
        this.val$configuration = val$configuration;
        this.val$realmDeleted = val$realmDeleted;
    }
    
    @Override
    public void onResult(final int n) {
        if (n != 0) {
            throw new IllegalStateException("It's not allowed to delete the file associated with an open Realm. Remember to close() all the instances of the Realm before deleting its file: " + this.val$configuration.getPath());
        }
        this.val$realmDeleted.set(Util.deleteRealm(this.val$configuration.getPath(), this.val$configuration.getRealmDirectory(), this.val$configuration.getRealmFileName()));
    }
}
