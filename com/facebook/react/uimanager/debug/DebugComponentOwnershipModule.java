// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager.debug;

import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.JSApplicationCausedNativeException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReactApplicationContext;
import android.util.SparseArray;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

public class DebugComponentOwnershipModule extends ReactContextBaseJavaModule
{
    private int mNextRequestId;
    private DebugComponentOwnershipModule$RCTDebugComponentOwnership mRCTDebugComponentOwnership;
    private final SparseArray<DebugComponentOwnershipModule$OwnerHierarchyCallback> mRequestIdToCallback;
    
    public DebugComponentOwnershipModule(final ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mRequestIdToCallback = (SparseArray<DebugComponentOwnershipModule$OwnerHierarchyCallback>)new SparseArray();
        this.mNextRequestId = 0;
    }
    
    @Override
    public String getName() {
        return "DebugComponentOwnershipModule";
    }
    
    @Override
    public void initialize() {
        super.initialize();
        this.mRCTDebugComponentOwnership = this.getReactApplicationContext().getJSModule(DebugComponentOwnershipModule$RCTDebugComponentOwnership.class);
    }
    
    public void loadComponentOwnerHierarchy(final int n, final DebugComponentOwnershipModule$OwnerHierarchyCallback debugComponentOwnershipModule$OwnerHierarchyCallback) {
        synchronized (this) {
            final int mNextRequestId = this.mNextRequestId;
            ++this.mNextRequestId;
            this.mRequestIdToCallback.put(mNextRequestId, (Object)debugComponentOwnershipModule$OwnerHierarchyCallback);
            Assertions.assertNotNull(this.mRCTDebugComponentOwnership).getOwnerHierarchy(mNextRequestId, n);
        }
    }
    
    @Override
    public void onCatalystInstanceDestroy() {
        super.onCatalystInstanceDestroy();
        this.mRCTDebugComponentOwnership = null;
    }
    
    @ReactMethod
    public void receiveOwnershipHierarchy(final int n, final int n2, final ReadableArray readableArray) {
        final DebugComponentOwnershipModule$OwnerHierarchyCallback debugComponentOwnershipModule$OwnerHierarchyCallback;
        synchronized (this) {
            debugComponentOwnershipModule$OwnerHierarchyCallback = (DebugComponentOwnershipModule$OwnerHierarchyCallback)this.mRequestIdToCallback.get(n);
            if (debugComponentOwnershipModule$OwnerHierarchyCallback == null) {
                throw new JSApplicationCausedNativeException("Got receiveOwnershipHierarchy for invalid request id: " + n);
            }
        }
        this.mRequestIdToCallback.delete(n);
        final ReadableArray readableArray2;
        debugComponentOwnershipModule$OwnerHierarchyCallback.onOwnerHierarchyLoaded(n2, readableArray2);
    }
    // monitorexit(this)
}
