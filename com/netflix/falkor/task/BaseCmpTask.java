// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.task;

import com.netflix.falkor.PQL;
import com.netflix.mediaclient.util.DataUtil$StringPair;
import java.util.List;
import com.google.gson.JsonObject;

public abstract class BaseCmpTask implements CmpTaskDetails
{
    protected final String TAG;
    
    public BaseCmpTask() {
        this.TAG = "CachedModelProxy";
    }
    
    @Override
    public void customHandleResponse(final JsonObject jsonObject) {
    }
    
    @Override
    public List<DataUtil$StringPair> getOptionalRequestParams() {
        return null;
    }
    
    @Override
    public boolean shouldCollapseMissingPql(final List<PQL> list) {
        return false;
    }
    
    @Override
    public boolean shouldCustomHandleResponse() {
        return false;
    }
    
    @Override
    public boolean shouldSkipCache() {
        return false;
    }
    
    @Override
    public boolean shouldUseAuthorization() {
        return true;
    }
    
    @Override
    public boolean shouldUseCacheOnly() {
        return false;
    }
    
    @Override
    public boolean shouldUseCallMethod() {
        return false;
    }
}
