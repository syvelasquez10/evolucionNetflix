// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.cache.disk;

import java.io.IOException;
import com.facebook.binaryresource.BinaryResource;
import java.util.Collection;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.file.FileTree;
import com.facebook.common.file.FileUtils$CreateDirectoryException;
import com.facebook.cache.common.CacheErrorLogger$CacheErrorCategory;
import com.facebook.common.logging.FLog;
import com.facebook.common.file.FileUtils;
import com.facebook.cache.common.CacheErrorLogger;
import java.io.File;
import com.facebook.common.internal.Supplier;

public class DynamicDefaultDiskStorage implements DiskStorage
{
    private static final Class<?> TAG;
    private final String mBaseDirectoryName;
    private final Supplier<File> mBaseDirectoryPathSupplier;
    private final CacheErrorLogger mCacheErrorLogger;
    volatile DynamicDefaultDiskStorage$State mCurrentState;
    private final int mVersion;
    
    static {
        TAG = DynamicDefaultDiskStorage.class;
    }
    
    public DynamicDefaultDiskStorage(final int mVersion, final Supplier<File> mBaseDirectoryPathSupplier, final String mBaseDirectoryName, final CacheErrorLogger mCacheErrorLogger) {
        this.mVersion = mVersion;
        this.mCacheErrorLogger = mCacheErrorLogger;
        this.mBaseDirectoryPathSupplier = mBaseDirectoryPathSupplier;
        this.mBaseDirectoryName = mBaseDirectoryName;
        this.mCurrentState = new DynamicDefaultDiskStorage$State(null, null);
    }
    
    private void createStorage() {
        final File file = new File(this.mBaseDirectoryPathSupplier.get(), this.mBaseDirectoryName);
        this.createRootDirectoryIfNecessary(file);
        this.mCurrentState = new DynamicDefaultDiskStorage$State(file, new DefaultDiskStorage(file, this.mVersion, this.mCacheErrorLogger));
    }
    
    private boolean shouldCreateNewStorage() {
        final DynamicDefaultDiskStorage$State mCurrentState = this.mCurrentState;
        return mCurrentState.delegate == null || mCurrentState.rootDirectory == null || !mCurrentState.rootDirectory.exists();
    }
    
    @Override
    public void clearAll() {
        this.get().clearAll();
    }
    
    @Override
    public boolean contains(final String s, final Object o) {
        return this.get().contains(s, o);
    }
    
    void createRootDirectoryIfNecessary(final File file) {
        try {
            FileUtils.mkdirs(file);
            FLog.d(DynamicDefaultDiskStorage.TAG, "Created cache directory %s", file.getAbsolutePath());
        }
        catch (FileUtils$CreateDirectoryException ex) {
            this.mCacheErrorLogger.logError(CacheErrorLogger$CacheErrorCategory.WRITE_CREATE_DIR, DynamicDefaultDiskStorage.TAG, "createRootDirectoryIfNecessary", ex);
            throw ex;
        }
    }
    
    void deleteOldStorageIfNecessary() {
        if (this.mCurrentState.delegate != null && this.mCurrentState.rootDirectory != null) {
            FileTree.deleteRecursively(this.mCurrentState.rootDirectory);
        }
    }
    
    DiskStorage get() {
        synchronized (this) {
            if (this.shouldCreateNewStorage()) {
                this.deleteOldStorageIfNecessary();
                this.createStorage();
            }
            return Preconditions.checkNotNull(this.mCurrentState.delegate);
        }
    }
    
    @Override
    public Collection<DiskStorage$Entry> getEntries() {
        return this.get().getEntries();
    }
    
    @Override
    public BinaryResource getResource(final String s, final Object o) {
        return this.get().getResource(s, o);
    }
    
    @Override
    public DiskStorage$Inserter insert(final String s, final Object o) {
        return this.get().insert(s, o);
    }
    
    @Override
    public void purgeUnexpectedResources() {
        try {
            this.get().purgeUnexpectedResources();
        }
        catch (IOException ex) {
            FLog.e(DynamicDefaultDiskStorage.TAG, "purgeUnexpectedResources", ex);
        }
    }
    
    @Override
    public long remove(final DiskStorage$Entry diskStorage$Entry) {
        return this.get().remove(diskStorage$Entry);
    }
}
