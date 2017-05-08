// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pservice;

import com.netflix.mediaclient.util.data.DataRepository$LoadedCallback;
import com.netflix.mediaclient.util.data.DataRepository$DataSavedCallback;
import com.netflix.mediaclient.util.data.DataRepository$DataLoadedCallback;
import com.netflix.mediaclient.util.data.FileSystemDataRepositoryImpl;
import java.io.File;
import android.content.Context;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.data.DataRepository$Entry;
import com.netflix.mediaclient.util.data.DataRepository;

final class PDiskDataRepository$1 extends PDiskDataRepository$FileLoadedCallback
{
    final /* synthetic */ PDiskDataRepository$LoadCallback val$loadCallback;
    
    PDiskDataRepository$1(final DataRepository dataRepository, final PDiskDataRepository$LoadCallback pDiskDataRepository$LoadCallback, final PDiskDataRepository$LoadCallback val$loadCallback) {
        this.val$loadCallback = val$loadCallback;
        super(dataRepository, pDiskDataRepository$LoadCallback);
    }
    
    public void onLoaded(final DataRepository$Entry[] array) {
        if (array != null && array.length > 0) {
            initFromDiskData(this.getRepository(), array, this.val$loadCallback);
            return;
        }
        Log.d("nf_preapp_dataRepo", "No saved preAppData found.");
        this.getCallback().onDataLoaded(null);
    }
}
