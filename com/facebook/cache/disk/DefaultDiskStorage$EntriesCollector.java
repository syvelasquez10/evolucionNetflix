// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.cache.disk;

import java.io.IOException;
import com.facebook.binaryresource.FileBinaryResource;
import com.facebook.binaryresource.BinaryResource;
import java.util.Collection;
import com.facebook.common.file.FileTree;
import com.facebook.common.file.FileUtils$CreateDirectoryException;
import com.facebook.cache.common.CacheErrorLogger$CacheErrorCategory;
import com.facebook.common.file.FileUtils;
import java.util.Locale;
import com.facebook.common.time.SystemClock;
import com.facebook.common.internal.Preconditions;
import java.util.concurrent.TimeUnit;
import com.facebook.common.time.Clock;
import com.facebook.cache.common.CacheErrorLogger;
import java.io.File;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import com.facebook.common.file.FileTreeVisitor;

class DefaultDiskStorage$EntriesCollector implements FileTreeVisitor
{
    private final List<DiskStorage$Entry> result;
    final /* synthetic */ DefaultDiskStorage this$0;
    
    private DefaultDiskStorage$EntriesCollector(final DefaultDiskStorage this$0) {
        this.this$0 = this$0;
        this.result = new ArrayList<DiskStorage$Entry>();
    }
    
    public List<DiskStorage$Entry> getEntries() {
        return Collections.unmodifiableList((List<? extends DiskStorage$Entry>)this.result);
    }
    
    @Override
    public void postVisitDirectory(final File file) {
    }
    
    @Override
    public void preVisitDirectory(final File file) {
    }
    
    @Override
    public void visitFile(final File file) {
        final DefaultDiskStorage$FileInfo access$000 = this.this$0.getShardFileInfo(file);
        if (access$000 != null && access$000.type == DefaultDiskStorage$FileType.CONTENT) {
            this.result.add(new DefaultDiskStorage$EntryImpl(access$000.resourceId, file, null));
        }
    }
}
