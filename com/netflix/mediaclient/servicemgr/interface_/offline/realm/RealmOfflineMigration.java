// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_.offline.realm;

import io.realm.RealmSchema;
import io.realm.FieldAttribute;
import com.netflix.mediaclient.Log;
import io.realm.DynamicRealm;
import io.realm.RealmMigration;

public class RealmOfflineMigration implements RealmMigration
{
    static final int OFFLINE_REALM_DB_SCHEMA_VERSION = 2;
    private static final String TAG = "RealmOfflineMigration";
    
    public void migrate(final DynamicRealm dynamicRealm, final long n, final long n2) {
        final RealmSchema schema = dynamicRealm.getSchema();
        long n3 = n;
        if (n == 1L) {
            Log.d("RealmOfflineMigration", "Migrating from " + n);
            Log.d("RealmOfflineMigration", "Creating RealmIncompleteVideoDetails");
            schema.create("RealmIncompleteVideoDetails").addField("playableId", (Class)String.class, new FieldAttribute[0]).addPrimaryKey("playableId").addField("videoType", (Class)Integer.TYPE, new FieldAttribute[0]).addField("profileId", (Class)String.class, new FieldAttribute[0]);
            n3 = n + 1L;
        }
        if (n3 != n2) {
            throw new RuntimeException("you missed a migration. oldVersion is " + n3 + " and should be " + n2);
        }
        Log.d("RealmOfflineMigration", "Migrating to " + n3);
    }
}
