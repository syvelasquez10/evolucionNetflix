// 
// Decompiled by Procyon v0.5.30
// 

package io.realm;

import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;

final class BaseRealm$4 implements RealmCache$Callback
{
    final /* synthetic */ BaseRealm$MigrationCallback val$callback;
    final /* synthetic */ RealmConfiguration val$configuration;
    final /* synthetic */ AtomicBoolean val$fileNotFound;
    final /* synthetic */ RealmMigration val$migration;
    
    BaseRealm$4(final RealmConfiguration val$configuration, final AtomicBoolean val$fileNotFound, final RealmMigration val$migration, final BaseRealm$MigrationCallback val$callback) {
        this.val$configuration = val$configuration;
        this.val$fileNotFound = val$fileNotFound;
        this.val$migration = val$migration;
        this.val$callback = val$callback;
    }
    
    @Override
    public void onResult(final int n) {
        if (n != 0) {
            throw new IllegalStateException("Cannot migrate a Realm file that is already open: " + this.val$configuration.getPath());
        }
        if (!new File(this.val$configuration.getPath()).exists()) {
            this.val$fileNotFound.set(true);
            return;
        }
        Label_0182: {
            if (this.val$migration != null) {
                break Label_0182;
            }
            RealmMigration realmMigration = this.val$configuration.getMigration();
            while (true) {
                DynamicRealm dynamicRealm = null;
                DynamicRealm instance = null;
                try {
                    final DynamicRealm dynamicRealm2 = dynamicRealm = (instance = DynamicRealm.getInstance(this.val$configuration));
                    dynamicRealm2.beginTransaction();
                    instance = dynamicRealm2;
                    dynamicRealm = dynamicRealm2;
                    realmMigration.migrate(dynamicRealm2, dynamicRealm2.getVersion(), this.val$configuration.getSchemaVersion());
                    instance = dynamicRealm2;
                    dynamicRealm = dynamicRealm2;
                    dynamicRealm2.setVersion(this.val$configuration.getSchemaVersion());
                    instance = dynamicRealm2;
                    dynamicRealm = dynamicRealm2;
                    dynamicRealm2.commitTransaction();
                    return;
                    realmMigration = this.val$migration;
                }
                catch (RuntimeException realmMigration) {
                    if (instance != null) {
                        dynamicRealm = instance;
                        instance.cancelTransaction();
                    }
                    dynamicRealm = instance;
                    throw realmMigration;
                }
                finally {
                    if (dynamicRealm != null) {
                        dynamicRealm.close();
                        this.val$callback.migrationComplete();
                    }
                }
            }
        }
    }
}
