// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.storage;

import com.facebook.react.bridge.ReactMethod;
import android.content.Context;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.GuardedAsyncTask;

class AsyncStorageModule$3 extends GuardedAsyncTask<Void, Void>
{
    final /* synthetic */ AsyncStorageModule this$0;
    final /* synthetic */ Callback val$callback;
    final /* synthetic */ ReadableArray val$keys;
    
    AsyncStorageModule$3(final AsyncStorageModule this$0, final ReactContext reactContext, final Callback val$callback, final ReadableArray val$keys) {
        this.this$0 = this$0;
        this.val$callback = val$callback;
        this.val$keys = val$keys;
        super(reactContext);
    }
    
    @Override
    protected void doInBackgroundGuarded(Void... array) {
        array = null;
        if (!this.this$0.ensureDatabase()) {
            this.val$callback.invoke(AsyncStorageErrorUtil.getDBError(null));
            return;
        }
        while (true) {
            while (true) {
                Label_0324: {
                    try {
                        this.this$0.mReactDatabaseSupplier.get().beginTransaction();
                        for (int i = 0; i < this.val$keys.size(); i += 999) {
                            final int min = Math.min(this.val$keys.size() - i, 999);
                            this.this$0.mReactDatabaseSupplier.get().delete("catalystLocalStorage", AsyncLocalStorageUtil.buildKeySelection(min), AsyncLocalStorageUtil.buildKeySelectionArgs(this.val$keys, i, min));
                        }
                        this.this$0.mReactDatabaseSupplier.get().setTransactionSuccessful();
                        try {
                            this.this$0.mReactDatabaseSupplier.get().endTransaction();
                            if (array != null) {
                                this.val$callback.invoke(array);
                                return;
                            }
                            break;
                        }
                        catch (Exception ex) {
                            FLog.w("React", ex.getMessage(), ex);
                            if (!false) {
                                array = (Void[])(Object)AsyncStorageErrorUtil.getError(null, ex.getMessage());
                            }
                            continue;
                        }
                    }
                    catch (Exception ex2) {
                        FLog.w("React", ex2.getMessage(), ex2);
                        array = (Void[])(Object)AsyncStorageErrorUtil.getError(null, ex2.getMessage());
                        try {
                            this.this$0.mReactDatabaseSupplier.get().endTransaction();
                            continue;
                        }
                        catch (Exception ex3) {
                            FLog.w("React", ex3.getMessage(), ex3);
                            if (array == null) {
                                array = (Void[])(Object)AsyncStorageErrorUtil.getError(null, ex3.getMessage());
                                continue;
                            }
                            break Label_0324;
                        }
                        continue;
                    }
                    finally {
                        try {
                            this.this$0.mReactDatabaseSupplier.get().endTransaction();
                        }
                        catch (Exception ex4) {
                            FLog.w("React", ex4.getMessage(), ex4);
                            if (!false) {
                                AsyncStorageErrorUtil.getError(null, ex4.getMessage());
                            }
                        }
                    }
                    break;
                }
                continue;
            }
        }
        this.val$callback.invoke(new Object[0]);
    }
}
