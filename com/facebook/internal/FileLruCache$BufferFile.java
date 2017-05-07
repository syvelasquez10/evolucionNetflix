// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

import java.io.OutputStream;
import org.json.JSONObject;
import java.io.IOException;
import java.util.Date;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.AbstractQueue;
import java.util.PriorityQueue;
import com.facebook.LoggingBehavior;
import com.facebook.Settings;
import android.content.Context;
import java.util.concurrent.atomic.AtomicLong;
import java.io.File;
import java.io.FilenameFilter;

class FileLruCache$BufferFile
{
    private static final String FILE_NAME_PREFIX = "buffer";
    private static final FilenameFilter filterExcludeBufferFiles;
    private static final FilenameFilter filterExcludeNonBufferFiles;
    
    static {
        filterExcludeBufferFiles = new FileLruCache$BufferFile$1();
        filterExcludeNonBufferFiles = new FileLruCache$BufferFile$2();
    }
    
    static void deleteAll(final File file) {
        final File[] listFiles = file.listFiles(excludeNonBufferFiles());
        for (int length = listFiles.length, i = 0; i < length; ++i) {
            listFiles[i].delete();
        }
    }
    
    static FilenameFilter excludeBufferFiles() {
        return FileLruCache$BufferFile.filterExcludeBufferFiles;
    }
    
    static FilenameFilter excludeNonBufferFiles() {
        return FileLruCache$BufferFile.filterExcludeNonBufferFiles;
    }
    
    static File newFile(final File file) {
        return new File(file, "buffer" + Long.valueOf(FileLruCache.bufferIndex.incrementAndGet()).toString());
    }
}
