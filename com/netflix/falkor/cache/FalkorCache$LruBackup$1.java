// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.cache;

import android.os.Looper;
import android.os.Handler;
import com.netflix.falkor.cache.lru.DiskLruJournal$Editor;
import java.io.IOException;
import com.netflix.mediaclient.Log;
import java.io.File;
import android.content.Context;
import java.io.Serializable;
import com.netflix.mediaclient.util.LogUtils;
import com.netflix.falkor.cache.lru.DiskLruJournal;
import java.util.LinkedList;
import android.os.Message;
import com.netflix.falkor.cache.lru.DiskLruJournal$Snapshot;
import com.netflix.falkor.cache.lru.DiskLruJournal$OnRemovedListener;

final class FalkorCache$LruBackup$1 implements DiskLruJournal$OnRemovedListener
{
    @Override
    public void onRemoved(final String s, final DiskLruJournal$Snapshot diskLruJournal$Snapshot) {
        synchronized (FalkorCache$LruBackup.LOCK) {
            FalkorCache$LruBackup.sKeysToDelete.push(s);
            // monitorexit(FalkorCache$LruBackup.access$300())
            final Message obtain = Message.obtain(FalkorCache$LruBackup.sLruLooper.handler, 1);
            FalkorCache$LruBackup.sLruLooper.handler.removeMessages(1);
            FalkorCache$LruBackup.sLruLooper.handler.sendMessageDelayed(obtain, 500L);
        }
    }
}
