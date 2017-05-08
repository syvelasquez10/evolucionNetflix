// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.msl;

import com.netflix.mediaclient.service.msl.volley.MSLVolleyRequest;
import java.util.HashMap;
import java.util.Map;
import com.netflix.mediaclient.servicemgr.IMSLClient$NetworkRequestInspector;

class MSLAgent$NetworkRequestInspectorManager
{
    private IMSLClient$NetworkRequestInspector mGlobalInspector;
    private Map<String, IMSLClient$NetworkRequestInspector> mInspectorMap;
    
    private MSLAgent$NetworkRequestInspectorManager() {
        this.mInspectorMap = new HashMap<String, IMSLClient$NetworkRequestInspector>();
    }
    
    private void doAddListener(final IMSLClient$NetworkRequestInspector mGlobalInspector, final Class[] array) {
        if (array == null || array.length < 1) {
            this.mGlobalInspector = mGlobalInspector;
        }
        else {
            for (int length = array.length, i = 0; i < length; ++i) {
                this.mInspectorMap.put(array[i].getSimpleName(), mGlobalInspector);
            }
        }
    }
    
    private void doRemoveListener(final Class[] array) {
        if (array == null || array.length < 1) {
            this.mGlobalInspector = null;
        }
        else {
            for (int length = array.length, i = 0; i < length; ++i) {
                this.mInspectorMap.remove(array[i].getSimpleName());
            }
        }
    }
    
    void addListener(final IMSLClient$NetworkRequestInspector imslClient$NetworkRequestInspector, final Class[] array) {
        if (imslClient$NetworkRequestInspector == null) {
            this.doRemoveListener(array);
            return;
        }
        this.doAddListener(imslClient$NetworkRequestInspector, array);
    }
    
    void injectInspector(final MSLVolleyRequest mslVolleyRequest) {
        final IMSLClient$NetworkRequestInspector inspector = this.mInspectorMap.get(mslVolleyRequest.getClass().getSimpleName());
        if (inspector != null) {
            mslVolleyRequest.setInspector(inspector);
            return;
        }
        mslVolleyRequest.setInspector(this.mGlobalInspector);
    }
    
    boolean shouldAddInspector(final MSLVolleyRequest mslVolleyRequest) {
        return this.mInspectorMap.get(mslVolleyRequest.getClass().getSimpleName()) != null || this.mGlobalInspector != null;
    }
}
