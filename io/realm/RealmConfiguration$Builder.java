// 
// Decompiled by Procyon v0.5.30
// 

package io.realm;

import android.text.TextUtils;
import java.io.InputStream;
import java.util.Arrays;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import io.realm.exceptions.RealmException;
import java.io.IOException;
import io.realm.exceptions.RealmFileException;
import io.realm.exceptions.RealmFileException$Kind;
import java.util.Iterator;
import io.realm.internal.modules.CompositeMediator;
import java.util.Collection;
import io.realm.internal.modules.FilterableMediator;
import io.realm.internal.RealmProxyMediator;
import java.util.Set;
import io.realm.rx.RealmObservableFactory;
import io.realm.internal.RealmCore;
import android.content.Context;
import io.realm.rx.RxObservableFactory;
import io.realm.internal.SharedRealm$Durability;
import java.io.File;
import java.util.HashSet;

public class RealmConfiguration$Builder
{
    private String assetFilePath;
    private HashSet<Class<? extends RealmModel>> debugSchema;
    private boolean deleteRealmIfMigrationNeeded;
    private File directory;
    private SharedRealm$Durability durability;
    private String fileName;
    private Realm$Transaction initialDataTransaction;
    private byte[] key;
    private RealmMigration migration;
    private HashSet<Object> modules;
    private RxObservableFactory rxFactory;
    private long schemaVersion;
    
    public RealmConfiguration$Builder() {
        this(BaseRealm.applicationContext);
    }
    
    RealmConfiguration$Builder(final Context context) {
        this.modules = new HashSet<Object>();
        this.debugSchema = new HashSet<Class<? extends RealmModel>>();
        if (context == null) {
            throw new IllegalStateException("Call `Realm.init(Context)` before creating a RealmConfiguration");
        }
        RealmCore.loadLibrary(context);
        this.initializeBuilder(context);
    }
    
    private void initializeBuilder(final Context context) {
        this.directory = context.getFilesDir();
        this.fileName = "default.realm";
        this.key = null;
        this.schemaVersion = 0L;
        this.migration = null;
        this.deleteRealmIfMigrationNeeded = false;
        this.durability = SharedRealm$Durability.FULL;
        if (RealmConfiguration.DEFAULT_MODULE != null) {
            this.modules.add(RealmConfiguration.DEFAULT_MODULE);
        }
    }
    
    public RealmConfiguration build() {
        if (this.rxFactory == null && isRxJavaAvailable()) {
            this.rxFactory = (RxObservableFactory)new RealmObservableFactory();
        }
        return new RealmConfiguration(this.directory, this.fileName, RealmConfiguration.getCanonicalPath(new File(this.directory, this.fileName)), this.assetFilePath, this.key, this.schemaVersion, this.migration, this.deleteRealmIfMigrationNeeded, this.durability, RealmConfiguration.createSchemaMediator(this.modules, this.debugSchema), this.rxFactory, this.initialDataTransaction);
    }
    
    public RealmConfiguration$Builder name(final String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            throw new IllegalArgumentException("A non-empty filename must be provided");
        }
        this.fileName = fileName;
        return this;
    }
    
    public RealmConfiguration$Builder schemaVersion(final long schemaVersion) {
        if (schemaVersion < 0L) {
            throw new IllegalArgumentException("Realm schema version numbers must be 0 (zero) or higher. Yours was: " + schemaVersion);
        }
        this.schemaVersion = schemaVersion;
        return this;
    }
}
