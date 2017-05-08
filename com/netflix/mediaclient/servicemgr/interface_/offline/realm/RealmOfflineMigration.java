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
    static final int OFFLINE_REALM_DB_SCHEMA_VERSION = 4;
    private static final String TAG = "RealmOfflineMigration";
    
    public void migrate(final DynamicRealm dynamicRealm, long n, final long n2) {
        final RealmSchema schema = dynamicRealm.getSchema();
        long n3;
        if (n == 1L) {
            Log.d("RealmOfflineMigration", "Migrating from " + n);
            Log.d("RealmOfflineMigration", "Creating RealmIncompleteVideoDetails");
            schema.create("RealmIncompleteVideoDetails").addField("playableId", (Class)String.class, new FieldAttribute[0]).addPrimaryKey("playableId").addField("videoType", (Class)Integer.TYPE, new FieldAttribute[0]).addField("profileId", (Class)String.class, new FieldAttribute[0]);
            n3 = n + 1L;
        }
        else {
            n3 = n;
        }
        n = n3;
        if (n3 == 2L) {
            Log.d("RealmOfflineMigration", "Migrating from " + n3);
            Log.d("RealmOfflineMigration", "updating season details");
            schema.get("RealmSeason").renameField("label", "title");
            n = n3 + 1L;
        }
        long n4 = n;
        if (n == 3L) {
            Log.d("RealmOfflineMigration", "Migrating from " + n);
            schema.get("RealmPlayable").addField("isPreviewProtected", (Class)Boolean.TYPE, new FieldAttribute[0]);
            n4 = n + 1L;
        }
        if (n4 != n2) {
            throw new RuntimeException("you missed a migration. oldVersion is " + n4 + " and should be " + n2);
        }
        Log.d("RealmOfflineMigration", "Migrating to " + n4);
    }
}
