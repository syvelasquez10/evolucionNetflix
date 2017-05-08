// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.cache;

import io.realm.RealmQuery;
import java.util.concurrent.ThreadPoolExecutor;
import io.realm.internal.async.RealmAsyncTaskImpl;
import io.realm.Realm$1;
import io.realm.Realm$Transaction$OnError;
import io.realm.Realm$Transaction$OnSuccess;
import io.realm.RealmAsyncTask;
import io.realm.log.RealmLog;
import io.realm.internal.Table;
import java.util.Collections;
import io.realm.BaseRealm$MigrationCallback;
import io.realm.RealmMigration;
import io.realm.Realm$2;
import io.realm.Realm$Transaction;
import java.util.Iterator;
import java.util.Set;
import io.realm.internal.RealmProxyMediator;
import io.realm.internal.ColumnInfo;
import java.util.HashMap;
import io.realm.RealmSchema;
import io.realm.RealmObjectSchema;
import io.realm.internal.SharedRealm;
import io.realm.internal.ObjectServerFacade;
import io.realm.RealmConfiguration$Builder;
import io.realm.internal.RealmCore;
import android.content.Context;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import io.realm.exceptions.RealmException;
import java.io.FileNotFoundException;
import io.realm.exceptions.RealmFileException;
import io.realm.exceptions.RealmFileException$Kind;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.RealmCache;
import io.realm.internal.ColumnIndices;
import io.realm.internal.RealmObjectProxy;
import java.util.Map;
import io.realm.RealmModel;
import io.realm.RealmConfiguration;
import io.realm.BaseRealm;
import com.netflix.mediaclient.android.app.ApplicationStateListener;
import com.netflix.mediaclient.NetflixApplication;
import org.json.JSONException;
import com.netflix.mediaclient.Log;
import org.json.JSONObject;
import java.util.ArrayList;
import android.os.Looper;
import io.realm.Realm;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicInteger;
import android.os.Handler;
import java.io.File;

class FalkorCacheMonitor$2 implements Runnable
{
    final /* synthetic */ FalkorCacheMonitor this$0;
    
    FalkorCacheMonitor$2(final FalkorCacheMonitor this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        if (this.this$0.mRealm != null) {
            final long length = new File(this.this$0.mRealm.getConfiguration().getPath()).length();
            if (length != this.this$0.realmFileSize.get()) {
                this.this$0.realmFileSize.set(length);
                this.this$0.sendUpdate(FalkorCacheMonitor$DataUpdated.FileSize);
            }
            FalkorCacheMonitor.sHandler.postDelayed(this.this$0.mMeasureFileSize, 5000L);
        }
    }
}
