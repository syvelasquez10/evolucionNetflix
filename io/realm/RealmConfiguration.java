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
import java.util.Set;
import io.realm.rx.RxObservableFactory;
import java.io.File;
import io.realm.internal.SharedRealm$Durability;
import io.realm.internal.RealmProxyMediator;

public class RealmConfiguration
{
    private static final Object DEFAULT_MODULE;
    private static final RealmProxyMediator DEFAULT_MODULE_MEDIATOR;
    private static Boolean rxJavaAvailable;
    private final String assetFilePath;
    private final String canonicalPath;
    private final boolean deleteRealmIfMigrationNeeded;
    private final SharedRealm$Durability durability;
    private final Realm$Transaction initialDataTransaction;
    private final byte[] key;
    private final RealmMigration migration;
    private final File realmDirectory;
    private final String realmFileName;
    private final RxObservableFactory rxObservableFactory;
    private final RealmProxyMediator schemaMediator;
    private final long schemaVersion;
    
    static {
        DEFAULT_MODULE = Realm.getDefaultModule();
        if (RealmConfiguration.DEFAULT_MODULE == null) {
            DEFAULT_MODULE_MEDIATOR = null;
            return;
        }
        final RealmProxyMediator moduleMediator = getModuleMediator(RealmConfiguration.DEFAULT_MODULE.getClass().getCanonicalName());
        if (!moduleMediator.transformerApplied()) {
            throw new ExceptionInInitializerError("RealmTransformer doesn't seem to be applied. Please update the project configuration to use the Realm Gradle plugin. See https://realm.io/news/android-installation-change/");
        }
        DEFAULT_MODULE_MEDIATOR = moduleMediator;
    }
    
    protected RealmConfiguration(final File realmDirectory, final String realmFileName, final String canonicalPath, final String assetFilePath, final byte[] key, final long schemaVersion, final RealmMigration migration, final boolean deleteRealmIfMigrationNeeded, final SharedRealm$Durability durability, final RealmProxyMediator schemaMediator, final RxObservableFactory rxObservableFactory, final Realm$Transaction initialDataTransaction) {
        this.realmDirectory = realmDirectory;
        this.realmFileName = realmFileName;
        this.canonicalPath = canonicalPath;
        this.assetFilePath = assetFilePath;
        this.key = key;
        this.schemaVersion = schemaVersion;
        this.migration = migration;
        this.deleteRealmIfMigrationNeeded = deleteRealmIfMigrationNeeded;
        this.durability = durability;
        this.schemaMediator = schemaMediator;
        this.rxObservableFactory = rxObservableFactory;
        this.initialDataTransaction = initialDataTransaction;
    }
    
    protected static RealmProxyMediator createSchemaMediator(final Set<Object> set, final Set<Class<? extends RealmModel>> set2) {
        if (set2.size() > 0) {
            return (RealmProxyMediator)new FilterableMediator(RealmConfiguration.DEFAULT_MODULE_MEDIATOR, (Collection)set2);
        }
        if (set.size() == 1) {
            return getModuleMediator(set.iterator().next().getClass().getCanonicalName());
        }
        final RealmProxyMediator[] array = new RealmProxyMediator[set.size()];
        int n = 0;
        final Iterator<Object> iterator = set.iterator();
        while (iterator.hasNext()) {
            array[n] = getModuleMediator(iterator.next().getClass().getCanonicalName());
            ++n;
        }
        return (RealmProxyMediator)new CompositeMediator(array);
    }
    
    protected static String getCanonicalPath(final File file) {
        try {
            return file.getCanonicalPath();
        }
        catch (IOException ex) {
            throw new RealmFileException(RealmFileException$Kind.ACCESS_ERROR, "Could not resolve the canonical path to the Realm file: " + file.getAbsolutePath(), (Throwable)ex);
        }
    }
    
    private static RealmProxyMediator getModuleMediator(String format) {
        final String[] split = format.split("\\.");
        format = String.format("io.realm.%s%s", split[split.length - 1], "Mediator");
        try {
            final Constructor<?> constructor = Class.forName(format).getDeclaredConstructors()[0];
            constructor.setAccessible(true);
            return (RealmProxyMediator)constructor.newInstance(new Object[0]);
        }
        catch (ClassNotFoundException ex) {
            throw new RealmException("Could not find " + format, (Throwable)ex);
        }
        catch (InvocationTargetException ex2) {
            throw new RealmException("Could not create an instance of " + format, (Throwable)ex2);
        }
        catch (InstantiationException ex3) {
            throw new RealmException("Could not create an instance of " + format, (Throwable)ex3);
        }
        catch (IllegalAccessException ex4) {
            throw new RealmException("Could not create an instance of " + format, (Throwable)ex4);
        }
    }
    
    private static boolean isRxJavaAvailable() {
        synchronized (RealmConfiguration.class) {
            Label_0024: {
                if (RealmConfiguration.rxJavaAvailable != null) {
                    break Label_0024;
                }
                try {
                    Class.forName("rx.Observable");
                    RealmConfiguration.rxJavaAvailable = true;
                    return RealmConfiguration.rxJavaAvailable;
                }
                catch (ClassNotFoundException ex) {
                    RealmConfiguration.rxJavaAvailable = false;
                }
            }
        }
    }
    
    @Override
    public boolean equals(final Object o) {
        final boolean b = false;
        boolean b2;
        if (this == o) {
            b2 = true;
        }
        else {
            b2 = b;
            if (o != null) {
                b2 = b;
                if (this.getClass() == o.getClass()) {
                    final RealmConfiguration realmConfiguration = (RealmConfiguration)o;
                    b2 = b;
                    if (this.schemaVersion == realmConfiguration.schemaVersion) {
                        b2 = b;
                        if (this.deleteRealmIfMigrationNeeded == realmConfiguration.deleteRealmIfMigrationNeeded) {
                            b2 = b;
                            if (this.realmDirectory.equals(realmConfiguration.realmDirectory)) {
                                b2 = b;
                                if (this.realmFileName.equals(realmConfiguration.realmFileName)) {
                                    b2 = b;
                                    if (this.canonicalPath.equals(realmConfiguration.canonicalPath)) {
                                        b2 = b;
                                        if (Arrays.equals(this.key, realmConfiguration.key)) {
                                            b2 = b;
                                            if (this.durability.equals((Object)realmConfiguration.durability)) {
                                                if (this.migration != null) {
                                                    b2 = b;
                                                    if (!this.migration.equals(realmConfiguration.migration)) {
                                                        return b2;
                                                    }
                                                }
                                                else if (realmConfiguration.migration != null) {
                                                    return false;
                                                }
                                                if (this.rxObservableFactory != null) {
                                                    b2 = b;
                                                    if (!this.rxObservableFactory.equals(realmConfiguration.rxObservableFactory)) {
                                                        return b2;
                                                    }
                                                }
                                                else if (realmConfiguration.rxObservableFactory != null) {
                                                    return false;
                                                }
                                                if (this.initialDataTransaction != null) {
                                                    b2 = b;
                                                    if (!this.initialDataTransaction.equals(realmConfiguration.initialDataTransaction)) {
                                                        return b2;
                                                    }
                                                }
                                                else if (realmConfiguration.initialDataTransaction != null) {
                                                    return false;
                                                }
                                                return this.schemaMediator.equals((Object)realmConfiguration.schemaMediator);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return b2;
    }
    
    InputStream getAssetFile() {
        return BaseRealm.applicationContext.getAssets().open(this.assetFilePath);
    }
    
    public SharedRealm$Durability getDurability() {
        return this.durability;
    }
    
    public byte[] getEncryptionKey() {
        if (this.key == null) {
            return null;
        }
        return Arrays.copyOf(this.key, this.key.length);
    }
    
    Realm$Transaction getInitialDataTransaction() {
        return this.initialDataTransaction;
    }
    
    public RealmMigration getMigration() {
        return this.migration;
    }
    
    public String getPath() {
        return this.canonicalPath;
    }
    
    public File getRealmDirectory() {
        return this.realmDirectory;
    }
    
    public String getRealmFileName() {
        return this.realmFileName;
    }
    
    public RxObservableFactory getRxFactory() {
        if (this.rxObservableFactory == null) {
            throw new UnsupportedOperationException("RxJava seems to be missing from the classpath. Remember to add it as a compile dependency. See https://realm.io/docs/java/latest/#rxjava for more details.");
        }
        return this.rxObservableFactory;
    }
    
    RealmProxyMediator getSchemaMediator() {
        return this.schemaMediator;
    }
    
    public long getSchemaVersion() {
        return this.schemaVersion;
    }
    
    boolean hasAssetFile() {
        return !TextUtils.isEmpty((CharSequence)this.assetFilePath);
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        final int hashCode2 = this.realmDirectory.hashCode();
        final int hashCode3 = this.realmFileName.hashCode();
        final int hashCode4 = this.canonicalPath.hashCode();
        int hashCode5;
        if (this.key != null) {
            hashCode5 = Arrays.hashCode(this.key);
        }
        else {
            hashCode5 = 0;
        }
        final int n = (int)this.schemaVersion;
        int hashCode6;
        if (this.migration != null) {
            hashCode6 = this.migration.hashCode();
        }
        else {
            hashCode6 = 0;
        }
        int n2;
        if (this.deleteRealmIfMigrationNeeded) {
            n2 = 1;
        }
        else {
            n2 = 0;
        }
        final int hashCode7 = this.schemaMediator.hashCode();
        final int hashCode8 = this.durability.hashCode();
        int hashCode9;
        if (this.rxObservableFactory != null) {
            hashCode9 = this.rxObservableFactory.hashCode();
        }
        else {
            hashCode9 = 0;
        }
        if (this.initialDataTransaction != null) {
            hashCode = this.initialDataTransaction.hashCode();
        }
        return (hashCode9 + (((n2 + (hashCode6 + ((hashCode5 + ((hashCode2 * 31 + hashCode3) * 31 + hashCode4) * 31) * 31 + n) * 31) * 31) * 31 + hashCode7) * 31 + hashCode8) * 31) * 31 + hashCode;
    }
    
    boolean isSyncConfiguration() {
        return false;
    }
    
    public boolean shouldDeleteRealmIfMigrationNeeded() {
        return this.deleteRealmIfMigrationNeeded;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("realmDirectory: ").append(this.realmDirectory.toString());
        sb.append("\n");
        sb.append("realmFileName : ").append(this.realmFileName);
        sb.append("\n");
        sb.append("canonicalPath: ").append(this.canonicalPath);
        sb.append("\n");
        final StringBuilder append = sb.append("key: ").append("[length: ");
        int n;
        if (this.key == null) {
            n = 0;
        }
        else {
            n = 64;
        }
        append.append(n).append("]");
        sb.append("\n");
        sb.append("schemaVersion: ").append(Long.toString(this.schemaVersion));
        sb.append("\n");
        sb.append("migration: ").append(this.migration);
        sb.append("\n");
        sb.append("deleteRealmIfMigrationNeeded: ").append(this.deleteRealmIfMigrationNeeded);
        sb.append("\n");
        sb.append("durability: ").append(this.durability);
        sb.append("\n");
        sb.append("schemaMediator: ").append(this.schemaMediator);
        return sb.toString();
    }
}
