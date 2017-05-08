// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.cache;

import com.netflix.falkor.cache.lru.DiskLruJournal$Editor;
import com.netflix.falkor.cache.lru.DiskLruJournal$Snapshot;
import java.io.IOException;
import com.netflix.mediaclient.Log;
import com.netflix.falkor.cache.lru.DiskLruJournal$OnRemovedListener;
import java.io.File;
import android.content.Context;
import java.io.Serializable;
import com.netflix.mediaclient.util.LogUtils;
import com.netflix.falkor.cache.lru.DiskLruJournal;
import java.util.LinkedList;

class FalkorCache$LruBackup
{
    private static final Object[] LOCK;
    private static final int MAX_CACHED_ENTRIES = 100000;
    private static final String TAG = "FalkorCache.LruBackup";
    private static volatile LinkedList<String> sKeysToDelete;
    private static DiskLruJournal sLruJournal;
    private static FalkorCache$LruBackup$LruLooper sLruLooper;
    
    static {
        LOCK = new Object[0];
        FalkorCache$LruBackup.sLruLooper = null;
        FalkorCache$LruBackup.sKeysToDelete = new LinkedList<String>();
    }
    
    private static DiskLruJournal getCache() {
        if (FalkorCache$LruBackup.sLruJournal != null) {
            return FalkorCache$LruBackup.sLruJournal;
        }
        throw new IllegalStateException("Falkor cache accessed without calling FalkorCache.init(context)");
    }
    
    private static String getKey(final FalkorCache$FalkorEvictable falkorCache$FalkorEvictable) {
        Serializable s = null;
        if (falkorCache$FalkorEvictable instanceof FalkorRealmCacheTimeBased) {
            s = FalkorRealmCacheTimeBased.class;
        }
        else if (falkorCache$FalkorEvictable instanceof FalkorRealmCacheLruBased) {
            s = FalkorRealmCacheLruBased.class;
        }
        else {
            LogUtils.reportErrorSafely("FalkorCache.LruBackup.push(" + falkorCache$FalkorEvictable.getPath() + ", " + falkorCache$FalkorEvictable.getClass() + ") -> not managed");
        }
        return falkorCache$FalkorEvictable.getPath() + " " + ((Class)s).getName();
    }
    
    public static void init(final Context context) {
        (FalkorCache$LruBackup.sLruLooper = new FalkorCache$LruBackup$LruLooper((FalkorCache$1)null)).start();
        FalkorCache$LruBackup.sLruJournal = DiskLruJournal.open(new File(context.getFilesDir(), "falkor.realm.lru_backup"), FalkorCache$FalkorRealmMigration.SCHEMA_VERSION, 100000L, new FalkorCache$LruBackup$1());
    }
    
    static void markAccessed(final FalkorCache$FalkorEvictable falkorCache$FalkorEvictable) {
        Log.d("FalkorCache.LruBackup", "markAccessed %s", falkorCache$FalkorEvictable.getPath());
        try {
            if (getCache().get(getKey(falkorCache$FalkorEvictable)) == null) {
                push(falkorCache$FalkorEvictable);
            }
        }
        catch (IOException ex) {
            LogUtils.reportErrorSafely("FalkorCache.LruBackup.markAccessed(" + falkorCache$FalkorEvictable.getPath() + ") -> " + ex.toString());
        }
    }
    
    static void push(final FalkorCache$FalkorEvictable falkorCache$FalkorEvictable) {
        try {
            final DiskLruJournal cache = getCache();
            Log.d("FalkorCache.LruBackup", "push %s", falkorCache$FalkorEvictable.getPath() + " " + cache.getEntriesCount());
            final String key = getKey(falkorCache$FalkorEvictable);
            final DiskLruJournal$Snapshot value = cache.get(getKey(falkorCache$FalkorEvictable));
            DiskLruJournal$Editor diskLruJournal$Editor;
            if (value != null) {
                diskLruJournal$Editor = value.edit();
            }
            else {
                diskLruJournal$Editor = cache.edit(key);
            }
            if (diskLruJournal$Editor != null) {
                diskLruJournal$Editor.commit();
                return;
            }
            LogUtils.reportErrorSafely("FalkorCache.LruBackup.push(" + falkorCache$FalkorEvictable.getPath() + ", " + falkorCache$FalkorEvictable.getClass() + ") -> cannot get an editor");
        }
        catch (IOException ex) {
            LogUtils.reportErrorSafely("FalkorCache.LruBackup.push(" + falkorCache$FalkorEvictable.getPath() + ") -> " + ex.toString());
        }
    }
}
