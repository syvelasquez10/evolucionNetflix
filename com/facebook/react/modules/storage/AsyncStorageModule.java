// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.storage;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.Callback;
import android.content.Context;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

public final class AsyncStorageModule extends ReactContextBaseJavaModule
{
    private static final int MAX_SQL_KEYS = 999;
    protected static final String NAME = "AsyncSQLiteDBStorage";
    private ReactDatabaseSupplier mReactDatabaseSupplier;
    private boolean mShuttingDown;
    
    public AsyncStorageModule(final ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mShuttingDown = false;
        this.mReactDatabaseSupplier = ReactDatabaseSupplier.getInstance((Context)reactApplicationContext);
    }
    
    private boolean ensureDatabase() {
        return !this.mShuttingDown && this.mReactDatabaseSupplier.ensureDatabase();
    }
    
    @ReactMethod
    public void clear(final Callback callback) {
        new AsyncStorageModule$5(this, this.getReactApplicationContext(), callback).execute((Object[])new Void[0]);
    }
    
    public void clearSensitiveData() {
        this.mReactDatabaseSupplier.clearAndCloseDatabase();
    }
    
    @ReactMethod
    public void getAllKeys(final Callback callback) {
        new AsyncStorageModule$6(this, this.getReactApplicationContext(), callback).execute((Object[])new Void[0]);
    }
    
    @Override
    public String getName() {
        return "AsyncSQLiteDBStorage";
    }
    
    @Override
    public void initialize() {
        super.initialize();
        this.mShuttingDown = false;
    }
    
    @ReactMethod
    public void multiGet(final ReadableArray readableArray, final Callback callback) {
        if (readableArray == null) {
            callback.invoke(AsyncStorageErrorUtil.getInvalidKeyError(null), null);
            return;
        }
        new AsyncStorageModule$1(this, this.getReactApplicationContext(), callback, readableArray).execute((Object[])new Void[0]);
    }
    
    @ReactMethod
    public void multiMerge(final ReadableArray readableArray, final Callback callback) {
        new AsyncStorageModule$4(this, this.getReactApplicationContext(), callback, readableArray).execute((Object[])new Void[0]);
    }
    
    @ReactMethod
    public void multiRemove(final ReadableArray readableArray, final Callback callback) {
        if (readableArray.size() == 0) {
            callback.invoke(AsyncStorageErrorUtil.getInvalidKeyError(null));
            return;
        }
        new AsyncStorageModule$3(this, this.getReactApplicationContext(), callback, readableArray).execute((Object[])new Void[0]);
    }
    
    @ReactMethod
    public void multiSet(final ReadableArray readableArray, final Callback callback) {
        if (readableArray.size() == 0) {
            callback.invoke(AsyncStorageErrorUtil.getInvalidKeyError(null));
            return;
        }
        new AsyncStorageModule$2(this, this.getReactApplicationContext(), callback, readableArray).execute((Object[])new Void[0]);
    }
    
    @Override
    public void onCatalystInstanceDestroy() {
        this.mShuttingDown = true;
    }
}
