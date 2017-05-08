// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.cache.disk;

import java.io.IOException;
import com.facebook.binaryresource.FileBinaryResource;
import com.facebook.binaryresource.BinaryResource;
import com.facebook.common.file.FileTreeVisitor;
import java.util.List;
import java.util.Collection;
import com.facebook.common.file.FileTree;
import com.facebook.common.file.FileUtils$CreateDirectoryException;
import com.facebook.cache.common.CacheErrorLogger$CacheErrorCategory;
import com.facebook.common.file.FileUtils;
import java.util.Locale;
import com.facebook.common.time.SystemClock;
import com.facebook.common.internal.Preconditions;
import java.util.concurrent.TimeUnit;
import java.io.File;
import com.facebook.common.time.Clock;
import com.facebook.cache.common.CacheErrorLogger;

public class DefaultDiskStorage implements DiskStorage
{
    private static final Class<?> TAG;
    static final long TEMP_FILE_LIFETIME_MS;
    private final CacheErrorLogger mCacheErrorLogger;
    private final Clock mClock;
    private final File mRootDirectory;
    private final File mVersionDirectory;
    
    static {
        TAG = DefaultDiskStorage.class;
        TEMP_FILE_LIFETIME_MS = TimeUnit.MINUTES.toMillis(30L);
    }
    
    public DefaultDiskStorage(final File mRootDirectory, final int n, final CacheErrorLogger mCacheErrorLogger) {
        Preconditions.checkNotNull(mRootDirectory);
        this.mRootDirectory = mRootDirectory;
        this.mVersionDirectory = new File(this.mRootDirectory, getVersionSubdirectoryName(n));
        this.mCacheErrorLogger = mCacheErrorLogger;
        this.recreateDirectoryIfVersionChanges();
        this.mClock = SystemClock.get();
    }
    
    private long doRemove(final File file) {
        long length;
        if (!file.exists()) {
            length = 0L;
        }
        else {
            length = file.length();
            if (!file.delete()) {
                return -1L;
            }
        }
        return length;
    }
    
    private String getFilename(final String s) {
        final DefaultDiskStorage$FileInfo defaultDiskStorage$FileInfo = new DefaultDiskStorage$FileInfo(DefaultDiskStorage$FileType.CONTENT, s, null);
        return defaultDiskStorage$FileInfo.toPath(this.getSubdirectoryPath(defaultDiskStorage$FileInfo.resourceId));
    }
    
    private DefaultDiskStorage$FileInfo getShardFileInfo(final File file) {
        final DefaultDiskStorage$FileInfo fromFile = DefaultDiskStorage$FileInfo.fromFile(file);
        if (fromFile == null) {
            return null;
        }
        DefaultDiskStorage$FileInfo defaultDiskStorage$FileInfo;
        if (this.getSubdirectory(fromFile.resourceId).equals(file.getParentFile())) {
            defaultDiskStorage$FileInfo = fromFile;
        }
        else {
            defaultDiskStorage$FileInfo = null;
        }
        return defaultDiskStorage$FileInfo;
    }
    
    private File getSubdirectory(final String s) {
        return new File(this.getSubdirectoryPath(s));
    }
    
    private String getSubdirectoryPath(final String s) {
        return this.mVersionDirectory + File.separator + String.valueOf(Math.abs(s.hashCode() % 100));
    }
    
    static String getVersionSubdirectoryName(final int n) {
        return String.format(null, "%s.ols%d.%d", "v2", 100, n);
    }
    
    private void mkdirs(final File file, final String s) {
        try {
            FileUtils.mkdirs(file);
        }
        catch (FileUtils$CreateDirectoryException ex) {
            this.mCacheErrorLogger.logError(CacheErrorLogger$CacheErrorCategory.WRITE_CREATE_DIR, DefaultDiskStorage.TAG, s, ex);
            throw ex;
        }
    }
    
    private boolean query(final String s, final boolean b) {
        final File contentFile = this.getContentFileFor(s);
        final boolean exists = contentFile.exists();
        if (b && exists) {
            contentFile.setLastModified(this.mClock.now());
        }
        return exists;
    }
    
    private void recreateDirectoryIfVersionChanges() {
        int n = 1;
        while (true) {
            Label_0024: {
                if (this.mRootDirectory.exists()) {
                    break Label_0024;
                }
                if (n == 0) {
                    return;
                }
                try {
                    FileUtils.mkdirs(this.mVersionDirectory);
                    return;
                    // iftrue(Label_0085:, this.mVersionDirectory.exists())
                    FileTree.deleteRecursively(this.mRootDirectory);
                    continue;
                }
                catch (FileUtils$CreateDirectoryException ex) {
                    this.mCacheErrorLogger.logError(CacheErrorLogger$CacheErrorCategory.WRITE_CREATE_DIR, DefaultDiskStorage.TAG, "version directory could not be created: " + this.mVersionDirectory, null);
                    return;
                }
            }
            Label_0085: {
                n = 0;
            }
            continue;
        }
    }
    
    @Override
    public void clearAll() {
        FileTree.deleteContents(this.mRootDirectory);
    }
    
    @Override
    public boolean contains(final String s, final Object o) {
        return this.query(s, false);
    }
    
    File getContentFileFor(final String s) {
        return new File(this.getFilename(s));
    }
    
    @Override
    public List<DiskStorage$Entry> getEntries() {
        final DefaultDiskStorage$EntriesCollector defaultDiskStorage$EntriesCollector = new DefaultDiskStorage$EntriesCollector(this, null);
        FileTree.walkFileTree(this.mVersionDirectory, defaultDiskStorage$EntriesCollector);
        return defaultDiskStorage$EntriesCollector.getEntries();
    }
    
    @Override
    public BinaryResource getResource(final String s, final Object o) {
        final File contentFile = this.getContentFileFor(s);
        if (contentFile.exists()) {
            contentFile.setLastModified(this.mClock.now());
            return FileBinaryResource.createOrNull(contentFile);
        }
        return null;
    }
    
    @Override
    public DiskStorage$Inserter insert(final String s, final Object o) {
        final DefaultDiskStorage$FileInfo defaultDiskStorage$FileInfo = new DefaultDiskStorage$FileInfo(DefaultDiskStorage$FileType.TEMP, s, null);
        final File subdirectory = this.getSubdirectory(defaultDiskStorage$FileInfo.resourceId);
        if (!subdirectory.exists()) {
            this.mkdirs(subdirectory, "insert");
        }
        try {
            return new DefaultDiskStorage$InserterImpl(this, s, defaultDiskStorage$FileInfo.createTempFile(subdirectory));
        }
        catch (IOException ex) {
            this.mCacheErrorLogger.logError(CacheErrorLogger$CacheErrorCategory.WRITE_CREATE_TEMPFILE, DefaultDiskStorage.TAG, "insert", ex);
            throw ex;
        }
    }
    
    @Override
    public void purgeUnexpectedResources() {
        FileTree.walkFileTree(this.mRootDirectory, new DefaultDiskStorage$PurgingVisitor(this, null));
    }
    
    @Override
    public long remove(final DiskStorage$Entry diskStorage$Entry) {
        return this.doRemove(((DefaultDiskStorage$EntryImpl)diskStorage$Entry).getResource().getFile());
    }
}
