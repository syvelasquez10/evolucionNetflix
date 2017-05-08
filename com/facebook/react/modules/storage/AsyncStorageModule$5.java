// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.storage;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReactMethod;
import android.content.Context;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.GuardedAsyncTask;

class AsyncStorageModule$5 extends GuardedAsyncTask<Void, Void>
{
    final /* synthetic */ AsyncStorageModule this$0;
    final /* synthetic */ Callback val$callback;
    
    AsyncStorageModule$5(final AsyncStorageModule this$0, final ReactContext reactContext, final Callback val$callback) {
        this.this$0 = this$0;
        this.val$callback = val$callback;
        super(reactContext);
    }
    
    @Override
    protected void doInBackgroundGuarded(final Void... array) {
        if (!this.this$0.mReactDatabaseSupplier.ensureDatabase()) {
            this.val$callback.invoke(AsyncStorageErrorUtil.getDBError(null));
            return;
        }
        try {
            this.this$0.mReactDatabaseSupplier.clear();
            this.val$callback.invoke(new Object[0]);
        }
        catch (Exception ex) {
            FLog.w("React", ex.getMessage(), ex);
            this.val$callback.invoke(AsyncStorageErrorUtil.getError(null, ex.getMessage()));
        }
    }
}
