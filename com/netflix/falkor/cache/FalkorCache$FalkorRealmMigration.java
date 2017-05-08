// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.cache;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;

class FalkorCache$FalkorRealmMigration implements RealmMigration
{
    static int SCHEMA_VERSION;
    
    static {
        FalkorCache$FalkorRealmMigration.SCHEMA_VERSION = 1;
    }
    
    public void migrate(final DynamicRealm dynamicRealm, final long n, final long n2) {
    }
}
