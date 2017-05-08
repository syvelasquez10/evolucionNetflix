// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.cache.disk;

import java.io.IOException;
import com.facebook.binaryresource.FileBinaryResource;
import com.facebook.binaryresource.BinaryResource;
import java.util.List;
import java.util.Collection;
import com.facebook.common.file.FileTree;
import com.facebook.common.file.FileUtils$CreateDirectoryException;
import com.facebook.cache.common.CacheErrorLogger$CacheErrorCategory;
import com.facebook.common.file.FileUtils;
import java.util.Locale;
import com.facebook.common.time.SystemClock;
import java.util.concurrent.TimeUnit;
import com.facebook.common.time.Clock;
import com.facebook.cache.common.CacheErrorLogger;
import com.facebook.common.internal.Preconditions;
import java.io.File;
import com.facebook.common.file.FileTreeVisitor;

class DefaultDiskStorage$PurgingVisitor implements FileTreeVisitor
{
    private boolean insideBaseDirectory;
    final /* synthetic */ DefaultDiskStorage this$0;
    
    private DefaultDiskStorage$PurgingVisitor(final DefaultDiskStorage this$0) {
        this.this$0 = this$0;
    }
    
    private boolean isExpectedFile(final File file) {
        boolean b = false;
        final DefaultDiskStorage$FileInfo access$000 = this.this$0.getShardFileInfo(file);
        if (access$000 == null) {
            return false;
        }
        if (access$000.type == DefaultDiskStorage$FileType.TEMP) {
            return this.isRecentFile(file);
        }
        if (access$000.type == DefaultDiskStorage$FileType.CONTENT) {
            b = true;
        }
        Preconditions.checkState(b);
        return true;
    }
    
    private boolean isRecentFile(final File file) {
        return file.lastModified() > this.this$0.mClock.now() - DefaultDiskStorage.TEMP_FILE_LIFETIME_MS;
    }
    
    @Override
    public void postVisitDirectory(final File file) {
        if (!this.this$0.mRootDirectory.equals(file) && !this.insideBaseDirectory) {
            file.delete();
        }
        if (this.insideBaseDirectory && file.equals(this.this$0.mVersionDirectory)) {
            this.insideBaseDirectory = false;
        }
    }
    
    @Override
    public void preVisitDirectory(final File file) {
        if (!this.insideBaseDirectory && file.equals(this.this$0.mVersionDirectory)) {
            this.insideBaseDirectory = true;
        }
    }
    
    @Override
    public void visitFile(final File file) {
        if (!this.insideBaseDirectory || !this.isExpectedFile(file)) {
            file.delete();
        }
    }
}
