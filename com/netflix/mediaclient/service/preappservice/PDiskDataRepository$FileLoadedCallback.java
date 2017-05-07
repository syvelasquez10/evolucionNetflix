// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.preappservice;

import com.netflix.mediaclient.util.data.DataRepository;
import com.netflix.mediaclient.util.data.DataRepository$LoadedCallback;

abstract class PDiskDataRepository$FileLoadedCallback implements DataRepository$LoadedCallback
{
    private final PDiskDataRepository$LoadCallback callback;
    private final DataRepository repository;
    
    public PDiskDataRepository$FileLoadedCallback(final DataRepository repository, final PDiskDataRepository$LoadCallback callback) {
        this.callback = callback;
        this.repository = repository;
    }
    
    public PDiskDataRepository$LoadCallback getCallback() {
        return this.callback;
    }
    
    public DataRepository getRepository() {
        return this.repository;
    }
}
