// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.storage;

import android.database.Cursor;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReactMethod;
import android.content.Context;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.WritableArray;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.GuardedAsyncTask;

class AsyncStorageModule$6 extends GuardedAsyncTask<Void, Void>
{
    final /* synthetic */ AsyncStorageModule this$0;
    final /* synthetic */ Callback val$callback;
    
    AsyncStorageModule$6(final AsyncStorageModule this$0, final ReactContext reactContext, final Callback val$callback) {
        this.this$0 = this$0;
        this.val$callback = val$callback;
        super(reactContext);
    }
    
    @Override
    protected void doInBackgroundGuarded(Void... query) {
        if (!this.this$0.ensureDatabase()) {
            this.val$callback.invoke(AsyncStorageErrorUtil.getDBError(null), null);
            return;
        }
        final WritableArray array = Arguments.createArray();
        query = (Void[])(Object)this.this$0.mReactDatabaseSupplier.get().query("catalystLocalStorage", new String[] { "key" }, (String)null, (String[])null, (String)null, (String)null, (String)null);
        try {
            if (((Cursor)(Object)query).moveToFirst()) {
                do {
                    array.pushString(((Cursor)(Object)query).getString(0));
                } while (((Cursor)(Object)query).moveToNext());
            }
            ((Cursor)(Object)query).close();
            this.val$callback.invoke(null, array);
        }
        catch (Exception ex) {
            FLog.w("React", ex.getMessage(), ex);
            this.val$callback.invoke(AsyncStorageErrorUtil.getError(null, ex.getMessage()), null);
        }
        finally {
            ((Cursor)(Object)query).close();
        }
    }
}
