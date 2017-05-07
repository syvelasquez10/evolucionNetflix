// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pservice;

import com.netflix.mediaclient.Log;

public class PDiskDataRepository$LoadCallback
{
    public void onDataLoaded(final PDiskData pDiskData) {
        if (PDiskDataRepository.ENABLE_VERBOSE_LOGGING) {
            Log.d("nf_preapp_dataRepo", String.format("mDiskData: %s", pDiskData.toJsonString()));
        }
    }
}
