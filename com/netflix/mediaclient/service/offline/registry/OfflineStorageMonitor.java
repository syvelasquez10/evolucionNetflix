// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.registry;

import android.os.StatFs;
import com.netflix.mediaclient.util.AndroidUtils;
import android.os.storage.StorageManager;
import android.os.Environment;
import java.util.ArrayList;
import java.util.List;
import android.content.IntentFilter;
import java.io.IOException;
import com.netflix.mediaclient.Log;
import java.io.File;
import android.os.Looper;
import java.util.concurrent.TimeUnit;
import android.os.Handler;
import android.content.BroadcastReceiver;
import android.content.Context;

public class OfflineStorageMonitor
{
    private static final long STORAGE_MOUNT_DELAY_MS;
    private static final long STORAGE_UN_MOUNT_DELAY_MS;
    private static final String TAG = "offlineStorageMonitor";
    private final Context mContext;
    private final BroadcastReceiver mExtStorageMountReceiver;
    private boolean mIsBroadcastReceiverRegistered;
    private final OfflineStorageMonitor$StorageChangeListener mStorageChangeListener;
    private final Runnable mStorageInfoUpdater;
    private final Handler mWorkHandler;
    
    static {
        STORAGE_MOUNT_DELAY_MS = TimeUnit.SECONDS.toMillis(1L);
        STORAGE_UN_MOUNT_DELAY_MS = TimeUnit.SECONDS.toMillis(1L);
    }
    
    public OfflineStorageMonitor(final Context mContext, final Looper looper, final OfflineStorageMonitor$StorageChangeListener mStorageChangeListener) {
        this.mStorageInfoUpdater = new OfflineStorageMonitor$1(this);
        this.mExtStorageMountReceiver = new OfflineStorageMonitor$2(this);
        this.mStorageChangeListener = mStorageChangeListener;
        this.mContext = mContext;
        this.mWorkHandler = new Handler(looper);
        this.registerReceiver();
    }
    
    private static boolean passesNfWriteTest(File s) {
        s = new File(((File)s).getAbsolutePath() + "nf.test");
        try {
            if (((File)s).exists()) {
                final boolean delete = ((File)s).delete();
                Log.i("offlineStorageMonitor", "passesNfWriteTest first deleteResult=%b", delete);
                if (!delete) {
                    return false;
                }
            }
            Log.i("offlineStorageMonitor", "passesNfWriteTest createNewFile=%b", ((File)s).createNewFile());
            final boolean exists = ((File)s).exists();
            try {
                Log.i("offlineStorageMonitor", "passesNfWriteTest file exists=%b", exists);
                Log.i("offlineStorageMonitor", "passesNfWriteTest delete=%b", ((File)s).delete());
                return exists;
            }
            catch (IOException ex) {}
            catch (Exception ex2) {}
        }
        catch (Exception ex3) {}
        catch (IOException s) {
            final boolean exists = false;
            goto Label_0138;
        }
    }
    
    private void registerReceiver() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.MEDIA_BAD_REMOVAL");
        intentFilter.addAction("android.intent.action.MEDIA_EJECT");
        intentFilter.addAction("android.intent.action.MEDIA_MOUNTED");
        intentFilter.addAction("android.intent.action.MEDIA_NOFS");
        intentFilter.addAction("android.intent.action.MEDIA_REMOVED");
        intentFilter.addAction("android.intent.action.MEDIA_SHARED");
        intentFilter.addAction("android.intent.action.MEDIA_UNMOUNTABLE");
        intentFilter.addAction("android.intent.action.MEDIA_UNMOUNTED");
        intentFilter.addDataScheme("file");
        this.mIsBroadcastReceiverRegistered = true;
        this.mContext.registerReceiver(this.mExtStorageMountReceiver, intentFilter);
    }
    
    private void sendStorageAddedOrRemoved() {
        this.mStorageChangeListener.onStorageAddedOrRemoved();
    }
    
    private void unregisterReceiver() {
        if (this.mIsBroadcastReceiverRegistered) {
            this.mContext.unregisterReceiver(this.mExtStorageMountReceiver);
        }
        this.mIsBroadcastReceiverRegistered = false;
    }
    
    public List<OfflineStorageVolumeInfo> buildOfflineStorageVolumeInfoList() {
        Log.i("offlineStorageMonitor", "buildOfflineStorageVolumeInfoList");
        final ArrayList<OfflineStorageVolumeInfo> list = new ArrayList<OfflineStorageVolumeInfo>();
        final File[] externalFilesDirs = this.mContext.getExternalFilesDirs(Environment.DIRECTORY_DOWNLOADS);
        if (externalFilesDirs != null) {
            final StorageManager storageManager = (StorageManager)this.mContext.getSystemService("storage");
            for (int i = 0; i < externalFilesDirs.length; ++i) {
                Log.i("offlineStorageMonitor", "\n i=%d", i);
                final File file = externalFilesDirs[i];
                if (file == null) {
                    Log.i("offlineStorageMonitor", "downloadDir null, ignore");
                }
                else {
                    if (!file.exists()) {
                        Log.i("offlineStorageMonitor", "mkdirsResult=%b", file.mkdirs());
                        if (!file.exists()) {
                            Log.i("offlineStorageMonitor", "downloadDir still doesn't exist, ignore");
                            continue;
                        }
                    }
                    final StatFs statFsForExternalStorageDir = AndroidUtils.getStatFsForExternalStorageDir(file);
                    if (statFsForExternalStorageDir == null) {
                        Log.i("offlineStorageMonitor", "statFs null, ignore");
                    }
                    else if (!passesNfWriteTest(file)) {
                        Log.i("offlineStorageMonitor", "downloadDir not writable, ignore");
                    }
                    else {
                        list.add(new OfflineStorageVolumeInfo(this.mContext, storageManager, file, statFsForExternalStorageDir, i == 0));
                    }
                }
            }
        }
        return list;
    }
    
    public void destroy() {
        this.unregisterReceiver();
    }
}
