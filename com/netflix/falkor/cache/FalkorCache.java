// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.cache;

import java.io.IOException;
import com.netflix.mediaclient.util.LogUtils;
import io.realm.Realm;
import android.content.Context;
import com.netflix.mediaclient.NetflixApplication;
import io.realm.RealmMigration;
import io.realm.RealmConfiguration$Builder;
import io.realm.RealmConfiguration;

public class FalkorCache
{
    private static boolean sInit;
    private static FalkorCacheMonitor sMonitor;
    public static final RealmConfiguration sRealmConfiguration;
    
    static {
        FalkorCache.sInit = false;
        FalkorCache.sMonitor = new FalkorCacheMonitor();
        sRealmConfiguration = new RealmConfiguration$Builder().name("falkor.realm").modules((Object)new FalkorRealmModule(), new Object[0]).migration((RealmMigration)new FalkorCache$FalkorRealmMigration(null)).schemaVersion((long)FalkorCache$FalkorRealmMigration.SCHEMA_VERSION).deleteRealmIfMigrationNeeded().build();
    }
    
    public static FalkorCacheMonitor getMonitor() {
        return FalkorCache.sMonitor;
    }
    
    public static void init(final NetflixApplication netflixApplication) {
        if (FalkorCache.sInit) {
            return;
        }
        try {
            FalkorCache$LruBackup.init((Context)netflixApplication);
            Realm.init((Context)netflixApplication);
            FalkorCache.sMonitor.init(FalkorCache$RealmAccess.getInstance(), netflixApplication);
            FalkorCache.sInit = true;
        }
        catch (IOException ex) {
            LogUtils.reportErrorSafely("FalkorCache.init -> " + ex.toString());
        }
    }
}
