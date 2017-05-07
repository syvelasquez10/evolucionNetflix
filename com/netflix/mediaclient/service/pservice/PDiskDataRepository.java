// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pservice;

import com.netflix.mediaclient.util.data.DataRepository$LoadedCallback;
import com.netflix.mediaclient.util.data.DataRepository$DataSavedCallback;
import com.netflix.mediaclient.util.data.DataRepository$DataLoadedCallback;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.data.FileSystemDataRepositoryImpl;
import java.io.File;
import android.content.Context;
import com.netflix.mediaclient.util.data.DataRepository$Entry;
import com.netflix.mediaclient.util.data.DataRepository;

public final class PDiskDataRepository
{
    public static final Boolean ENABLE_VERBOSE_LOGGING;
    private static final String TAG = "nf_preapp_dataRepo";
    private static final Object repoLock;
    
    static {
        ENABLE_VERBOSE_LOGGING = false;
        repoLock = new Object();
    }
    
    public static void clearDiskData(final Context context) {
        final DataRepository dataRepository = getDataRepository(context);
        synchronized (PDiskDataRepository.repoLock) {
            dataRepository.clear();
        }
    }
    
    private static DataRepository getDataRepository(final Context context) {
        final File file = new File(context.getFilesDir(), "preAppData");
        if (!file.isDirectory()) {
            file.mkdirs();
        }
        return new FileSystemDataRepositoryImpl(file);
    }
    
    private static void initFromDiskData(final DataRepository dataRepository, final DataRepository$Entry[] array, final PDiskDataRepository$LoadCallback pDiskDataRepository$LoadCallback) {
        if (array == null || array.length < 1) {
            Log.d("nf_preapp_dataRepo", "No saved data found");
            pDiskDataRepository$LoadCallback.onDataLoaded(null);
            return;
        }
        if (Log.isLoggable("nf_preapp_dataRepo", 3)) {
            Log.d("nf_preapp_dataRepo", "Found " + array.length + " entries ");
        }
        final String key = array[0].getKey();
        if (Log.isLoggable("nf_preapp_dataRepo", 3)) {
            Log.d("nf_preapp_dataRepo", "Load data for " + key);
        }
        dataRepository.load(key, new PDiskDataRepository$2(pDiskDataRepository$LoadCallback));
    }
    
    public static void saveData(final Context context, final String s, final DataRepository$DataSavedCallback dataRepository$DataSavedCallback) {
        final DataRepository dataRepository = getDataRepository(context);
        try {
            Log.d("nf_preapp_dataRepo", "saving payload to file: REPOSITORY_FILE_NAME");
            if (PDiskDataRepository.ENABLE_VERBOSE_LOGGING) {
                Log.d("nf_preapp_dataRepo", String.format("payload: %s", s));
            }
            synchronized (PDiskDataRepository.repoLock) {
                dataRepository.save("preAppDiskDataFile", s.getBytes("utf-8"), dataRepository$DataSavedCallback);
            }
        }
        catch (Throwable t) {
            Log.e("nf_preapp_dataRepo", "Failed to save payload to repository", t);
        }
    }
    
    public static void startLoadFromDisk(final Context context, final PDiskDataRepository$LoadCallback pDiskDataRepository$LoadCallback) {
        Log.d("nf_preapp_dataRepo", "starting load from Disk");
        final DataRepository dataRepository = getDataRepository(context);
        final PDiskDataRepository$1 pDiskDataRepository$1 = new PDiskDataRepository$1(dataRepository, pDiskDataRepository$LoadCallback, pDiskDataRepository$LoadCallback);
        synchronized (PDiskDataRepository.repoLock) {
            dataRepository.loadAll(pDiskDataRepository$1);
        }
    }
}
