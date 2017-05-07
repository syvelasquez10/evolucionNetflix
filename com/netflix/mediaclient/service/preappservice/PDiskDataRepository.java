// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.preappservice;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.data.FileSystemDataRepositoryImpl;
import java.io.File;
import android.content.Context;
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
    
    private static void initFromDiskData(final DataRepository dataRepository, final DataRepository.Entry[] array, final LoadCallback loadCallback) {
        if (array == null || array.length < 1) {
            Log.d("nf_preapp_dataRepo", "No saved data found");
            loadCallback.onDataLoaded(null);
            return;
        }
        if (Log.isLoggable("nf_preapp_dataRepo", 3)) {
            Log.d("nf_preapp_dataRepo", "Found " + array.length + " entries ");
        }
        final String key = array[0].getKey();
        if (Log.isLoggable("nf_preapp_dataRepo", 3)) {
            Log.d("nf_preapp_dataRepo", "Load data for " + key);
        }
        dataRepository.load(key, (DataRepository.DataLoadedCallback)new FileDataLoadedCallback(loadCallback) {
            @Override
            public void onDataLoaded(String s, final byte[] array, final long n) {
                final String s2 = null;
                final PDiskData pDiskData = null;
                Object fromJsonString;
                if (array == null || array.length < 1) {
                    Log.e("nf_preapp_dataRepo", "We failed to retrieve payload.");
                    fromJsonString = pDiskData;
                }
                else {
                    s = s2;
                    try {
                        final String s3 = s = (String)(fromJsonString = PDiskData.fromJsonString(new String(array, "utf-8")));
                        if (PDiskDataRepository.ENABLE_VERBOSE_LOGGING) {
                            s = s3;
                            Log.d("nf_preapp_dataRepo", String.format("read from file payload: %s", ((PDiskData)s3).toJsonString()));
                            s = s3;
                            Log.d("nf_preapp_dataRepo", String.format("urlMap:%s", ((PDiskData)s3).urlMap));
                            fromJsonString = s3;
                        }
                    }
                    catch (Throwable t) {
                        Log.e("nf_preapp_dataRepo", "Failed to build object from stored data.", t);
                        fromJsonString = s;
                    }
                }
                ((FileDataLoadedCallback)this).getCallback().onDataLoaded((PDiskData)fromJsonString);
            }
        });
    }
    
    public static void saveData(final Context context, final String s, final DataRepository.DataSavedCallback dataSavedCallback) {
        final DataRepository dataRepository = getDataRepository(context);
        try {
            Log.d("nf_preapp_dataRepo", "saving payload to file: REPOSITORY_FILE_NAME");
            if (PDiskDataRepository.ENABLE_VERBOSE_LOGGING) {
                Log.d("nf_preapp_dataRepo", String.format("payload: %s", s));
            }
            synchronized (PDiskDataRepository.repoLock) {
                dataRepository.save("preAppDiskDataFile", s.getBytes("utf-8"), dataSavedCallback);
            }
        }
        catch (Throwable t) {
            Log.e("nf_preapp_dataRepo", "Failed to save payload to repository", t);
        }
    }
    
    public static void startLoadFromDisk(final Context context, final LoadCallback loadCallback) {
        Log.d("nf_preapp_dataRepo", "starting load from Disk");
        final DataRepository dataRepository = getDataRepository(context);
        final FileLoadedCallback fileLoadedCallback = new FileLoadedCallback(dataRepository, loadCallback) {
            @Override
            public void onLoaded(final Entry[] array) {
                if (array != null && array.length > 0) {
                    initFromDiskData(((FileLoadedCallback)this).getRepository(), array, loadCallback);
                    return;
                }
                Log.d("nf_preapp_dataRepo", "No saved preAppData found.");
                ((FileLoadedCallback)this).getCallback().onDataLoaded(null);
            }
        };
        synchronized (PDiskDataRepository.repoLock) {
            dataRepository.loadAll((DataRepository.LoadedCallback)fileLoadedCallback);
        }
    }
    
    private abstract static class FileDataLoadedCallback implements DataLoadedCallback
    {
        private final LoadCallback callback;
        
        public FileDataLoadedCallback(final LoadCallback callback) {
            this.callback = callback;
        }
        
        public LoadCallback getCallback() {
            return this.callback;
        }
    }
    
    private abstract static class FileLoadedCallback implements LoadedCallback
    {
        private final LoadCallback callback;
        private final DataRepository repository;
        
        public FileLoadedCallback(final DataRepository repository, final LoadCallback callback) {
            this.callback = callback;
            this.repository = repository;
        }
        
        public LoadCallback getCallback() {
            return this.callback;
        }
        
        public DataRepository getRepository() {
            return this.repository;
        }
    }
    
    public static class LoadCallback
    {
        public void onDataLoaded(final PDiskData pDiskData) {
            if (PDiskDataRepository.ENABLE_VERBOSE_LOGGING) {
                Log.d("nf_preapp_dataRepo", String.format("mDiskData: %s", pDiskData.toJsonString()));
            }
        }
    }
}
