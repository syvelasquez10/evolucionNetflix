// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.preappservice;

import com.netflix.mediaclient.util.data.DataRepository$DataLoadedCallback;

abstract class PDiskDataRepository$FileDataLoadedCallback implements DataRepository$DataLoadedCallback
{
    private final PDiskDataRepository$LoadCallback callback;
    
    public PDiskDataRepository$FileDataLoadedCallback(final PDiskDataRepository$LoadCallback callback) {
        this.callback = callback;
    }
    
    public PDiskDataRepository$LoadCallback getCallback() {
        return this.callback;
    }
}
