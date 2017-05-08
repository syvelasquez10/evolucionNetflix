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

class AsyncStorageModule$4 extends GuardedAsyncTask<Void, Void>
{
    final /* synthetic */ AsyncStorageModule this$0;
    final /* synthetic */ Callback val$callback;
    final /* synthetic */ ReadableArray val$keyValueArray;
    
    AsyncStorageModule$4(final AsyncStorageModule this$0, final ReactContext reactContext, final Callback val$callback, final ReadableArray val$keyValueArray) {
        this.this$0 = this$0;
        this.val$callback = val$callback;
        this.val$keyValueArray = val$keyValueArray;
        super(reactContext);
    }
    
    @Override
    protected void doInBackgroundGuarded(Void... array) {
        array = null;
        if (this.this$0.ensureDatabase()) {
            while (true) {
                while (true) {
                    Label_0544: {
                        try {
                            this.this$0.mReactDatabaseSupplier.get().beginTransaction();
                            for (int i = 0; i < this.val$keyValueArray.size(); ++i) {
                                if (this.val$keyValueArray.getArray(i).size() != 2) {
                                    array = (Void[])(Object)AsyncStorageErrorUtil.getInvalidValueError(null);
                                    try {
                                        this.this$0.mReactDatabaseSupplier.get().endTransaction();
                                        return;
                                    }
                                    catch (Exception ex) {
                                        FLog.w("React", ex.getMessage(), ex);
                                        if (array == null) {
                                            AsyncStorageErrorUtil.getError(null, ex.getMessage());
                                        }
                                        return;
                                    }
                                }
                                if (this.val$keyValueArray.getArray(i).getString(0) == null) {
                                    array = (Void[])(Object)AsyncStorageErrorUtil.getInvalidKeyError(null);
                                    try {
                                        this.this$0.mReactDatabaseSupplier.get().endTransaction();
                                        return;
                                    }
                                    catch (Exception ex2) {
                                        FLog.w("React", ex2.getMessage(), ex2);
                                        if (array == null) {
                                            AsyncStorageErrorUtil.getError(null, ex2.getMessage());
                                        }
                                        return;
                                    }
                                }
                                if (this.val$keyValueArray.getArray(i).getString(1) == null) {
                                    array = (Void[])(Object)AsyncStorageErrorUtil.getInvalidValueError(null);
                                    try {
                                        this.this$0.mReactDatabaseSupplier.get().endTransaction();
                                        return;
                                    }
                                    catch (Exception ex3) {
                                        FLog.w("React", ex3.getMessage(), ex3);
                                        if (array == null) {
                                            AsyncStorageErrorUtil.getError(null, ex3.getMessage());
                                        }
                                        return;
                                    }
                                }
                                if (!AsyncLocalStorageUtil.mergeImpl(this.this$0.mReactDatabaseSupplier.get(), this.val$keyValueArray.getArray(i).getString(0), this.val$keyValueArray.getArray(i).getString(1))) {
                                    array = (Void[])(Object)AsyncStorageErrorUtil.getDBError(null);
                                    try {
                                        this.this$0.mReactDatabaseSupplier.get().endTransaction();
                                        return;
                                    }
                                    catch (Exception ex4) {
                                        FLog.w("React", ex4.getMessage(), ex4);
                                        if (array == null) {
                                            AsyncStorageErrorUtil.getError(null, ex4.getMessage());
                                        }
                                        return;
                                    }
                                }
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
                            catch (Exception ex5) {
                                FLog.w("React", ex5.getMessage(), ex5);
                                if (!false) {
                                    array = (Void[])(Object)AsyncStorageErrorUtil.getError(null, ex5.getMessage());
                                }
                                continue;
                            }
                        }
                        catch (Exception ex6) {
                            FLog.w("React", ex6.getMessage(), ex6);
                            array = (Void[])(Object)AsyncStorageErrorUtil.getError(null, ex6.getMessage());
                            try {
                                this.this$0.mReactDatabaseSupplier.get().endTransaction();
                                continue;
                            }
                            catch (Exception ex7) {
                                FLog.w("React", ex7.getMessage(), ex7);
                                if (array == null) {
                                    array = (Void[])(Object)AsyncStorageErrorUtil.getError(null, ex7.getMessage());
                                    continue;
                                }
                                break Label_0544;
                            }
                            continue;
                        }
                        finally {
                            try {
                                this.this$0.mReactDatabaseSupplier.get().endTransaction();
                            }
                            catch (Exception ex8) {
                                FLog.w("React", ex8.getMessage(), ex8);
                                if (!false) {
                                    AsyncStorageErrorUtil.getError(null, ex8.getMessage());
                                }
                            }
                        }
                        break;
                    }
                    continue;
                }
            }
            this.val$callback.invoke(new Object[0]);
            return;
        }
        this.val$callback.invoke(AsyncStorageErrorUtil.getDBError(null));
    }
}
