// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pservice;

import com.netflix.mediaclient.Log;

final class PDiskDataRepository$2 extends PDiskDataRepository$FileDataLoadedCallback
{
    PDiskDataRepository$2(final PDiskDataRepository$LoadCallback pDiskDataRepository$LoadCallback) {
        super(pDiskDataRepository$LoadCallback);
    }
    
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
                final Object o = s = (String)(fromJsonString = PDiskData.fromJsonString(new String(array, "utf-8")));
                if (PDiskDataRepository.ENABLE_VERBOSE_LOGGING) {
                    s = (String)o;
                    Log.d("nf_preapp_dataRepo", "onDataLoaded - diskData.print");
                    s = (String)o;
                    ((PDiskData)o).print();
                    fromJsonString = o;
                }
            }
            catch (Throwable t) {
                Log.e("nf_preapp_dataRepo", "Failed to build object from stored data.", t);
                fromJsonString = s;
            }
        }
        this.getCallback().onDataLoaded((PDiskData)fromJsonString);
    }
}
