// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.storage;

import com.facebook.react.bridge.ReactMethod;
import android.content.Context;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import android.database.sqlite.SQLiteStatement;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.GuardedAsyncTask;

class AsyncStorageModule$2 extends GuardedAsyncTask<Void, Void>
{
    final /* synthetic */ AsyncStorageModule this$0;
    final /* synthetic */ Callback val$callback;
    final /* synthetic */ ReadableArray val$keyValueArray;
    
    AsyncStorageModule$2(final AsyncStorageModule this$0, final ReactContext reactContext, final Callback val$callback, final ReadableArray val$keyValueArray) {
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
                final SQLiteStatement compileStatement = this.this$0.mReactDatabaseSupplier.get().compileStatement("INSERT OR REPLACE INTO catalystLocalStorage VALUES (?, ?);");
                while (true) {
                    Label_0518: {
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
                                compileStatement.clearBindings();
                                compileStatement.bindString(1, this.val$keyValueArray.getArray(i).getString(0));
                                compileStatement.bindString(2, this.val$keyValueArray.getArray(i).getString(1));
                                compileStatement.execute();
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
                            catch (Exception ex4) {
                                FLog.w("React", ex4.getMessage(), ex4);
                                if (!false) {
                                    array = (Void[])(Object)AsyncStorageErrorUtil.getError(null, ex4.getMessage());
                                }
                                continue;
                            }
                        }
                        catch (Exception ex5) {
                            FLog.w("React", ex5.getMessage(), ex5);
                            array = (Void[])(Object)AsyncStorageErrorUtil.getError(null, ex5.getMessage());
                            try {
                                this.this$0.mReactDatabaseSupplier.get().endTransaction();
                                continue;
                            }
                            catch (Exception ex6) {
                                FLog.w("React", ex6.getMessage(), ex6);
                                if (array == null) {
                                    array = (Void[])(Object)AsyncStorageErrorUtil.getError(null, ex6.getMessage());
                                    continue;
                                }
                                break Label_0518;
                            }
                            continue;
                        }
                        finally {
                            try {
                                this.this$0.mReactDatabaseSupplier.get().endTransaction();
                            }
                            catch (Exception ex7) {
                                FLog.w("React", ex7.getMessage(), ex7);
                                if (!false) {
                                    AsyncStorageErrorUtil.getError(null, ex7.getMessage());
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
