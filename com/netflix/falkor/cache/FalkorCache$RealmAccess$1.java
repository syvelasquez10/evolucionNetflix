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
import java.util.List;
import java.util.Collections;
import io.realm.BaseRealm$MigrationCallback;
import io.realm.RealmMigration;
import io.realm.Realm$2;
import java.util.Iterator;
import java.util.Set;
import io.realm.internal.RealmProxyMediator;
import io.realm.internal.ColumnInfo;
import java.util.HashMap;
import io.realm.RealmSchema;
import io.realm.RealmObjectSchema;
import java.util.ArrayList;
import io.realm.internal.SharedRealm;
import java.io.File;
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
import io.realm.Realm;
import io.realm.Realm$Transaction;

final class FalkorCache$RealmAccess$1 implements Realm$Transaction
{
    public void execute(final Realm realm) {
        realm.deleteAll();
    }
}
