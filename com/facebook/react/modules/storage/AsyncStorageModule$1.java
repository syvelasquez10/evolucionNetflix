// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.storage;

import java.util.Iterator;
import android.database.Cursor;
import com.facebook.react.bridge.ReactMethod;
import android.content.Context;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.WritableArray;
import java.util.HashSet;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.common.SetBuilder;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.GuardedAsyncTask;

class AsyncStorageModule$1 extends GuardedAsyncTask<Void, Void>
{
    final /* synthetic */ AsyncStorageModule this$0;
    final /* synthetic */ Callback val$callback;
    final /* synthetic */ ReadableArray val$keys;
    
    AsyncStorageModule$1(final AsyncStorageModule this$0, final ReactContext reactContext, final Callback val$callback, final ReadableArray val$keys) {
        this.this$0 = this$0;
        this.val$callback = val$callback;
        this.val$keys = val$keys;
        super(reactContext);
    }
    
    @Override
    protected void doInBackgroundGuarded(final Void... array) {
        if (!this.this$0.ensureDatabase()) {
            this.val$callback.invoke(AsyncStorageErrorUtil.getDBError(null), null);
            return;
        }
        final HashSet<Object> hashSet = SetBuilder.newHashSet();
        final WritableArray array2 = Arguments.createArray();
        for (int i = 0; i < this.val$keys.size(); i += 999) {
            final int min = Math.min(this.val$keys.size() - i, 999);
            Object o = this.this$0.mReactDatabaseSupplier.get().query("catalystLocalStorage", new String[] { "key", "value" }, AsyncLocalStorageUtil.buildKeySelection(min), AsyncLocalStorageUtil.buildKeySelectionArgs(this.val$keys, i, min), (String)null, (String)null, (String)null);
            hashSet.clear();
            try {
                if (((Cursor)o).getCount() != this.val$keys.size()) {
                    for (int j = i; j < i + min; ++j) {
                        hashSet.add(this.val$keys.getString(j));
                    }
                }
                if (((Cursor)o).moveToFirst()) {
                    do {
                        final WritableArray array3 = Arguments.createArray();
                        array3.pushString(((Cursor)o).getString(0));
                        array3.pushString(((Cursor)o).getString(1));
                        array2.pushArray(array3);
                        hashSet.remove(((Cursor)o).getString(0));
                    } while (((Cursor)o).moveToNext());
                }
                ((Cursor)o).close();
                o = hashSet.iterator();
                while (((Iterator)o).hasNext()) {
                    final String s = ((Iterator<String>)o).next();
                    final WritableArray array4 = Arguments.createArray();
                    array4.pushString(s);
                    array4.pushNull();
                    array2.pushArray(array4);
                }
            }
            catch (Exception ex) {
                FLog.w("React", ex.getMessage(), ex);
                this.val$callback.invoke(AsyncStorageErrorUtil.getError(null, ex.getMessage()), null);
                return;
            }
            finally {
                ((Cursor)o).close();
            }
            hashSet.clear();
        }
        this.val$callback.invoke(null, array2);
    }
}
