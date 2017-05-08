// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.cache.disk;

import java.io.IOException;
import com.facebook.common.file.FileTreeVisitor;
import java.util.List;
import java.util.Collection;
import com.facebook.common.file.FileTree;
import com.facebook.common.file.FileUtils$CreateDirectoryException;
import java.util.Locale;
import com.facebook.common.time.SystemClock;
import com.facebook.common.internal.Preconditions;
import java.util.concurrent.TimeUnit;
import com.facebook.common.time.Clock;
import com.facebook.cache.common.CacheErrorLogger;
import java.io.OutputStream;
import com.facebook.common.internal.CountingOutputStream;
import java.io.FileOutputStream;
import com.facebook.cache.common.WriterCallback;
import com.facebook.common.file.FileUtils$RenameException;
import java.io.FileNotFoundException;
import com.facebook.common.file.FileUtils$ParentDirNotFoundException;
import com.facebook.cache.common.CacheErrorLogger$CacheErrorCategory;
import com.facebook.binaryresource.FileBinaryResource;
import com.facebook.common.file.FileUtils;
import com.facebook.binaryresource.BinaryResource;
import java.io.File;

class DefaultDiskStorage$InserterImpl implements DiskStorage$Inserter
{
    private final String mResourceId;
    final File mTemporaryFile;
    final /* synthetic */ DefaultDiskStorage this$0;
    
    public DefaultDiskStorage$InserterImpl(final DefaultDiskStorage this$0, final String mResourceId, final File mTemporaryFile) {
        this.this$0 = this$0;
        this.mResourceId = mResourceId;
        this.mTemporaryFile = mTemporaryFile;
    }
    
    @Override
    public boolean cleanUp() {
        return !this.mTemporaryFile.exists() || this.mTemporaryFile.delete();
    }
    
    @Override
    public BinaryResource commit(final Object o) {
        final File contentFile = this.this$0.getContentFileFor(this.mResourceId);
        try {
            FileUtils.rename(this.mTemporaryFile, contentFile);
            if (contentFile.exists()) {
                contentFile.setLastModified(this.this$0.mClock.now());
            }
            return FileBinaryResource.createOrNull(contentFile);
        }
        catch (FileUtils$RenameException ex) {
            final Throwable cause = ex.getCause();
            CacheErrorLogger$CacheErrorCategory cacheErrorLogger$CacheErrorCategory;
            if (cause == null) {
                cacheErrorLogger$CacheErrorCategory = CacheErrorLogger$CacheErrorCategory.WRITE_RENAME_FILE_OTHER;
            }
            else if (cause instanceof FileUtils$ParentDirNotFoundException) {
                cacheErrorLogger$CacheErrorCategory = CacheErrorLogger$CacheErrorCategory.WRITE_RENAME_FILE_TEMPFILE_PARENT_NOT_FOUND;
            }
            else if (cause instanceof FileNotFoundException) {
                cacheErrorLogger$CacheErrorCategory = CacheErrorLogger$CacheErrorCategory.WRITE_RENAME_FILE_TEMPFILE_NOT_FOUND;
            }
            else {
                cacheErrorLogger$CacheErrorCategory = CacheErrorLogger$CacheErrorCategory.WRITE_RENAME_FILE_OTHER;
            }
            this.this$0.mCacheErrorLogger.logError(cacheErrorLogger$CacheErrorCategory, DefaultDiskStorage.TAG, "commit", ex);
            throw ex;
        }
    }
    
    @Override
    public void writeData(final WriterCallback writerCallback, Object o) {
        try {
            final Object o2;
            o = (o2 = new FileOutputStream(this.mTemporaryFile));
            final CountingOutputStream countingOutputStream = new CountingOutputStream((OutputStream)o2);
            final WriterCallback writerCallback2 = writerCallback;
            final CountingOutputStream countingOutputStream2 = countingOutputStream;
            writerCallback2.write(countingOutputStream2);
            final CountingOutputStream countingOutputStream3 = countingOutputStream;
            countingOutputStream3.flush();
            final CountingOutputStream countingOutputStream4 = countingOutputStream;
            final long n = countingOutputStream4.getCount();
            final Object o3 = o;
            ((FileOutputStream)o3).close();
            final DefaultDiskStorage$InserterImpl defaultDiskStorage$InserterImpl = this;
            final File file = defaultDiskStorage$InserterImpl.mTemporaryFile;
            final long n2 = file.length();
            final long n3 = n;
            final long n4 = lcmp(n2, n3);
            if (n4 != 0) {
                final long n5 = n;
                final DefaultDiskStorage$InserterImpl defaultDiskStorage$InserterImpl2 = this;
                final File file2 = defaultDiskStorage$InserterImpl2.mTemporaryFile;
                final long n6 = file2.length();
                throw new DefaultDiskStorage$IncompleteFileException(n5, n6);
            }
            return;
        }
        catch (FileNotFoundException ex2) {
            this.this$0.mCacheErrorLogger.logError(CacheErrorLogger$CacheErrorCategory.WRITE_UPDATE_FILE_NOT_FOUND, DefaultDiskStorage.TAG, "updateResource", ex2);
            throw ex2;
        }
        try {
            final Object o2 = o;
            final CountingOutputStream countingOutputStream = new CountingOutputStream((OutputStream)o2);
            final WriterCallback writerCallback2 = writerCallback;
            final CountingOutputStream countingOutputStream2 = countingOutputStream;
            writerCallback2.write(countingOutputStream2);
            final CountingOutputStream countingOutputStream3 = countingOutputStream;
            countingOutputStream3.flush();
            final CountingOutputStream countingOutputStream4 = countingOutputStream;
            final long n = countingOutputStream4.getCount();
            final Object o3 = o;
            ((FileOutputStream)o3).close();
            final DefaultDiskStorage$InserterImpl defaultDiskStorage$InserterImpl = this;
            final File file = defaultDiskStorage$InserterImpl.mTemporaryFile;
            final long n2 = file.length();
            final long n3 = n;
            final long n4 = lcmp(n2, n3);
            if (n4 != 0) {
                final long n5 = n;
                final DefaultDiskStorage$InserterImpl defaultDiskStorage$InserterImpl2 = this;
                final File file2 = defaultDiskStorage$InserterImpl2.mTemporaryFile;
                final long n6 = file2.length();
                throw new DefaultDiskStorage$IncompleteFileException(n5, n6);
            }
        }
        finally {
            ((FileOutputStream)o).close();
        }
    }
}
