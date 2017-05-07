// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

import org.json.JSONException;
import org.json.JSONTokener;
import java.security.InvalidParameterException;
import java.io.FilenameFilter;
import java.io.OutputStream;
import org.json.JSONObject;
import java.util.Date;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.util.PriorityQueue;
import com.facebook.LoggingBehavior;
import com.facebook.Settings;
import android.content.Context;
import java.io.File;
import java.util.concurrent.atomic.AtomicLong;

public final class FileLruCache
{
    private static final String HEADER_CACHEKEY_KEY = "key";
    private static final String HEADER_CACHE_CONTENT_TAG_KEY = "tag";
    static final String TAG;
    private static final AtomicLong bufferIndex;
    private final File directory;
    private boolean isTrimPending;
    private final Limits limits;
    private final Object lock;
    private final String tag;
    
    static {
        TAG = FileLruCache.class.getSimpleName();
        bufferIndex = new AtomicLong();
    }
    
    public FileLruCache(final Context context, final String tag, final Limits limits) {
        this.tag = tag;
        this.limits = limits;
        this.directory = new File(context.getCacheDir(), tag);
        this.lock = new Object();
        this.directory.mkdirs();
        BufferFile.deleteAll(this.directory);
    }
    
    private void postTrim() {
        synchronized (this.lock) {
            if (!this.isTrimPending) {
                this.isTrimPending = true;
                Settings.getExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        FileLruCache.this.trim();
                    }
                });
            }
        }
    }
    
    private void renameToTargetAndTrim(final String s, final File file) {
        if (!file.renameTo(new File(this.directory, Utility.md5hash(s)))) {
            file.delete();
        }
        this.postTrim();
    }
    
    private void trim() {
        Label_0279: {
            try {
                Logger.log(LoggingBehavior.CACHE, FileLruCache.TAG, "trim started");
                final PriorityQueue<ModifiedFile> priorityQueue = new PriorityQueue<ModifiedFile>();
                long n = 0L;
                long n2 = 0L;
                final File[] listFiles = this.directory.listFiles(BufferFile.excludeBufferFiles());
                final int length = listFiles.length;
                int n3 = 0;
                long n4;
                long n5;
                while (true) {
                    n4 = n2;
                    n5 = n;
                    if (n3 >= length) {
                        break;
                    }
                    final File file = listFiles[n3];
                    final ModifiedFile modifiedFile = new ModifiedFile(file);
                    priorityQueue.add(modifiedFile);
                    Logger.log(LoggingBehavior.CACHE, FileLruCache.TAG, "  trim considering time=" + (Object)modifiedFile.getModified() + " name=" + modifiedFile.getFile().getName());
                    n += file.length();
                    ++n2;
                    ++n3;
                }
                while (n5 > this.limits.getByteCount() || n4 > this.limits.getFileCount()) {
                    final File file2 = priorityQueue.remove().getFile();
                    Logger.log(LoggingBehavior.CACHE, FileLruCache.TAG, "  trim removing " + file2.getName());
                    n5 -= file2.length();
                    --n4;
                    file2.delete();
                }
                break Label_0279;
            }
            finally {
                EndFinally_2: {
                    synchronized (this.lock) {
                        this.isTrimPending = false;
                        this.lock.notifyAll();
                        // monitorexit(this.lock)
                        break EndFinally_2;
                        final Object lock = this.lock;
                        synchronized (this.lock) {
                            this.isTrimPending = false;
                            this.lock.notifyAll();
                            return;
                        }
                    }
                }
            }
        }
    }
    
    public void clearForTest() throws IOException {
        final File[] listFiles = this.directory.listFiles();
        for (int length = listFiles.length, i = 0; i < length; ++i) {
            listFiles[i].delete();
        }
    }
    
    public InputStream get(final String s) throws IOException {
        return this.get(s, null);
    }
    
    public InputStream get(String optString, final String s) throws IOException {
        final File file = new File(this.directory, Utility.md5hash(optString));
        Label_0068: {
            InputStream inputStream;
            try {
                inputStream = new FileInputStream(file);
                final InputStream inputStream2;
                inputStream = (inputStream2 = new BufferedInputStream(inputStream, 8192));
                final JSONObject jsonObject = StreamHeader.readHeader(inputStream2);
                final JSONObject jsonObject3;
                final JSONObject jsonObject2 = jsonObject3 = jsonObject;
                if (jsonObject3 == null) {
                    return null;
                }
                break Label_0068;
            }
            catch (IOException ex) {
                return null;
            }
            try {
                final InputStream inputStream2 = inputStream;
                final JSONObject jsonObject = StreamHeader.readHeader(inputStream2);
                final JSONObject jsonObject3;
                final JSONObject jsonObject2 = jsonObject3 = jsonObject;
                if (jsonObject3 == null) {
                    return null;
                }
                final String optString2 = jsonObject2.optString("key");
                if (optString2 == null || !optString2.equals(optString)) {
                    return null;
                }
                optString = jsonObject2.optString("tag", (String)null);
                if ((s == null && optString != null) || (s != null && !s.equals(optString))) {
                    return null;
                }
                final long time = new Date().getTime();
                Logger.log(LoggingBehavior.CACHE, FileLruCache.TAG, "Setting lastModified to " + (Object)time + " for " + file.getName());
                file.setLastModified(time);
                if (!true) {
                    ((BufferedInputStream)inputStream).close();
                }
                return inputStream;
            }
            finally {
                if (!false) {
                    ((BufferedInputStream)inputStream).close();
                }
            }
        }
    }
    
    public InputStream interceptAndPut(final String s, final InputStream inputStream) throws IOException {
        return new CopyingInputStream(inputStream, this.openPutStream(s));
    }
    
    OutputStream openPutStream(final String s) throws IOException {
        return this.openPutStream(s, null);
    }
    
    public OutputStream openPutStream(final String p0, final String p1) throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/facebook/internal/FileLruCache.directory:Ljava/io/File;
        //     4: invokestatic    com/facebook/internal/FileLruCache$BufferFile.newFile:(Ljava/io/File;)Ljava/io/File;
        //     7: astore_3       
        //     8: aload_3        
        //     9: invokevirtual   java/io/File.delete:()Z
        //    12: pop            
        //    13: aload_3        
        //    14: invokevirtual   java/io/File.createNewFile:()Z
        //    17: ifne            51
        //    20: new             Ljava/io/IOException;
        //    23: dup            
        //    24: new             Ljava/lang/StringBuilder;
        //    27: dup            
        //    28: invokespecial   java/lang/StringBuilder.<init>:()V
        //    31: ldc_w           "Could not create file at "
        //    34: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    37: aload_3        
        //    38: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //    41: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    44: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    47: invokespecial   java/io/IOException.<init>:(Ljava/lang/String;)V
        //    50: athrow         
        //    51: new             Ljava/io/FileOutputStream;
        //    54: dup            
        //    55: aload_3        
        //    56: invokespecial   java/io/FileOutputStream.<init>:(Ljava/io/File;)V
        //    59: astore          4
        //    61: new             Ljava/io/BufferedOutputStream;
        //    64: dup            
        //    65: new             Lcom/facebook/internal/FileLruCache$CloseCallbackOutputStream;
        //    68: dup            
        //    69: aload           4
        //    71: new             Lcom/facebook/internal/FileLruCache$1;
        //    74: dup            
        //    75: aload_0        
        //    76: aload_1        
        //    77: aload_3        
        //    78: invokespecial   com/facebook/internal/FileLruCache$1.<init>:(Lcom/facebook/internal/FileLruCache;Ljava/lang/String;Ljava/io/File;)V
        //    81: invokespecial   com/facebook/internal/FileLruCache$CloseCallbackOutputStream.<init>:(Ljava/io/OutputStream;Lcom/facebook/internal/FileLruCache$StreamCloseCallback;)V
        //    84: sipush          8192
        //    87: invokespecial   java/io/BufferedOutputStream.<init>:(Ljava/io/OutputStream;I)V
        //    90: astore_3       
        //    91: new             Lorg/json/JSONObject;
        //    94: dup            
        //    95: invokespecial   org/json/JSONObject.<init>:()V
        //    98: astore          4
        //   100: aload           4
        //   102: ldc             "key"
        //   104: aload_1        
        //   105: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   108: pop            
        //   109: aload_2        
        //   110: invokestatic    com/facebook/internal/Utility.isNullOrEmpty:(Ljava/lang/String;)Z
        //   113: ifne            125
        //   116: aload           4
        //   118: ldc             "tag"
        //   120: aload_2        
        //   121: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   124: pop            
        //   125: aload_3        
        //   126: aload           4
        //   128: invokestatic    com/facebook/internal/FileLruCache$StreamHeader.writeHeader:(Ljava/io/OutputStream;Lorg/json/JSONObject;)V
        //   131: iconst_1       
        //   132: ifne            139
        //   135: aload_3        
        //   136: invokevirtual   java/io/BufferedOutputStream.close:()V
        //   139: aload_3        
        //   140: areturn        
        //   141: astore_1       
        //   142: getstatic       com/facebook/LoggingBehavior.CACHE:Lcom/facebook/LoggingBehavior;
        //   145: iconst_5       
        //   146: getstatic       com/facebook/internal/FileLruCache.TAG:Ljava/lang/String;
        //   149: new             Ljava/lang/StringBuilder;
        //   152: dup            
        //   153: invokespecial   java/lang/StringBuilder.<init>:()V
        //   156: ldc_w           "Error creating buffer output stream: "
        //   159: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   162: aload_1        
        //   163: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   166: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   169: invokestatic    com/facebook/internal/Logger.log:(Lcom/facebook/LoggingBehavior;ILjava/lang/String;Ljava/lang/String;)V
        //   172: new             Ljava/io/IOException;
        //   175: dup            
        //   176: aload_1        
        //   177: invokevirtual   java/io/FileNotFoundException.getMessage:()Ljava/lang/String;
        //   180: invokespecial   java/io/IOException.<init>:(Ljava/lang/String;)V
        //   183: athrow         
        //   184: astore_1       
        //   185: getstatic       com/facebook/LoggingBehavior.CACHE:Lcom/facebook/LoggingBehavior;
        //   188: iconst_5       
        //   189: getstatic       com/facebook/internal/FileLruCache.TAG:Ljava/lang/String;
        //   192: new             Ljava/lang/StringBuilder;
        //   195: dup            
        //   196: invokespecial   java/lang/StringBuilder.<init>:()V
        //   199: ldc_w           "Error creating JSON header for cache file: "
        //   202: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   205: aload_1        
        //   206: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   209: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   212: invokestatic    com/facebook/internal/Logger.log:(Lcom/facebook/LoggingBehavior;ILjava/lang/String;Ljava/lang/String;)V
        //   215: new             Ljava/io/IOException;
        //   218: dup            
        //   219: aload_1        
        //   220: invokevirtual   org/json/JSONException.getMessage:()Ljava/lang/String;
        //   223: invokespecial   java/io/IOException.<init>:(Ljava/lang/String;)V
        //   226: athrow         
        //   227: astore_1       
        //   228: iconst_0       
        //   229: ifne            236
        //   232: aload_3        
        //   233: invokevirtual   java/io/BufferedOutputStream.close:()V
        //   236: aload_1        
        //   237: athrow         
        //    Exceptions:
        //  throws java.io.IOException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                           
        //  -----  -----  -----  -----  -------------------------------
        //  51     61     141    184    Ljava/io/FileNotFoundException;
        //  91     125    184    227    Lorg/json/JSONException;
        //  91     125    227    238    Any
        //  125    131    184    227    Lorg/json/JSONException;
        //  125    131    227    238    Any
        //  185    227    227    238    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0125:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    long sizeInBytesForTest() {
        Object o = this.lock;
        long n;
        synchronized (o) {
            while (this.isTrimPending) {
                try {
                    this.lock.wait();
                }
                catch (InterruptedException ex) {}
            }
            // monitorexit(o)
            o = this.directory.listFiles();
            n = 0L;
            for (int length = o.length, i = 0; i < length; ++i) {
                n += o[i].length();
            }
        }
        return n;
    }
    
    @Override
    public String toString() {
        return "{FileLruCache: tag:" + this.tag + " file:" + this.directory.getName() + "}";
    }
    
    private static class BufferFile
    {
        private static final String FILE_NAME_PREFIX = "buffer";
        private static final FilenameFilter filterExcludeBufferFiles;
        private static final FilenameFilter filterExcludeNonBufferFiles;
        
        static {
            filterExcludeBufferFiles = new FilenameFilter() {
                @Override
                public boolean accept(final File file, final String s) {
                    return !s.startsWith("buffer");
                }
            };
            filterExcludeNonBufferFiles = new FilenameFilter() {
                @Override
                public boolean accept(final File file, final String s) {
                    return s.startsWith("buffer");
                }
            };
        }
        
        static void deleteAll(final File file) {
            final File[] listFiles = file.listFiles(excludeNonBufferFiles());
            for (int length = listFiles.length, i = 0; i < length; ++i) {
                listFiles[i].delete();
            }
        }
        
        static FilenameFilter excludeBufferFiles() {
            return BufferFile.filterExcludeBufferFiles;
        }
        
        static FilenameFilter excludeNonBufferFiles() {
            return BufferFile.filterExcludeNonBufferFiles;
        }
        
        static File newFile(final File file) {
            return new File(file, "buffer" + Long.valueOf(FileLruCache.bufferIndex.incrementAndGet()).toString());
        }
    }
    
    private static class CloseCallbackOutputStream extends OutputStream
    {
        final StreamCloseCallback callback;
        final OutputStream innerStream;
        
        CloseCallbackOutputStream(final OutputStream innerStream, final StreamCloseCallback callback) {
            this.innerStream = innerStream;
            this.callback = callback;
        }
        
        @Override
        public void close() throws IOException {
            try {
                this.innerStream.close();
            }
            finally {
                this.callback.onClose();
            }
        }
        
        @Override
        public void flush() throws IOException {
            this.innerStream.flush();
        }
        
        @Override
        public void write(final int n) throws IOException {
            this.innerStream.write(n);
        }
        
        @Override
        public void write(final byte[] array) throws IOException {
            this.innerStream.write(array);
        }
        
        @Override
        public void write(final byte[] array, final int n, final int n2) throws IOException {
            this.innerStream.write(array, n, n2);
        }
    }
    
    private static final class CopyingInputStream extends InputStream
    {
        final InputStream input;
        final OutputStream output;
        
        CopyingInputStream(final InputStream input, final OutputStream output) {
            this.input = input;
            this.output = output;
        }
        
        @Override
        public int available() throws IOException {
            return this.input.available();
        }
        
        @Override
        public void close() throws IOException {
            try {
                this.input.close();
            }
            finally {
                this.output.close();
            }
        }
        
        @Override
        public void mark(final int n) {
            throw new UnsupportedOperationException();
        }
        
        @Override
        public boolean markSupported() {
            return false;
        }
        
        @Override
        public int read() throws IOException {
            final int read = this.input.read();
            if (read >= 0) {
                this.output.write(read);
            }
            return read;
        }
        
        @Override
        public int read(final byte[] array) throws IOException {
            final int read = this.input.read(array);
            if (read > 0) {
                this.output.write(array, 0, read);
            }
            return read;
        }
        
        @Override
        public int read(final byte[] array, final int n, int read) throws IOException {
            read = this.input.read(array, n, read);
            if (read > 0) {
                this.output.write(array, n, read);
            }
            return read;
        }
        
        @Override
        public void reset() {
            synchronized (this) {
                throw new UnsupportedOperationException();
            }
        }
        
        @Override
        public long skip(final long n) throws IOException {
            final byte[] array = new byte[1024];
            long n2;
            int read;
            for (n2 = 0L; n2 < n; n2 += read) {
                read = this.read(array, 0, (int)Math.min(n - n2, array.length));
                if (read < 0) {
                    break;
                }
            }
            return n2;
        }
    }
    
    public static final class Limits
    {
        private int byteCount;
        private int fileCount;
        
        public Limits() {
            this.fileCount = 1024;
            this.byteCount = 1048576;
        }
        
        int getByteCount() {
            return this.byteCount;
        }
        
        int getFileCount() {
            return this.fileCount;
        }
        
        void setByteCount(final int byteCount) {
            if (byteCount < 0) {
                throw new InvalidParameterException("Cache byte-count limit must be >= 0");
            }
            this.byteCount = byteCount;
        }
        
        void setFileCount(final int fileCount) {
            if (fileCount < 0) {
                throw new InvalidParameterException("Cache file count limit must be >= 0");
            }
            this.fileCount = fileCount;
        }
    }
    
    private static final class ModifiedFile implements Comparable<ModifiedFile>
    {
        private final File file;
        private final long modified;
        
        ModifiedFile(final File file) {
            this.file = file;
            this.modified = file.lastModified();
        }
        
        @Override
        public int compareTo(final ModifiedFile modifiedFile) {
            if (this.getModified() < modifiedFile.getModified()) {
                return -1;
            }
            if (this.getModified() > modifiedFile.getModified()) {
                return 1;
            }
            return this.getFile().compareTo(modifiedFile.getFile());
        }
        
        @Override
        public boolean equals(final Object o) {
            return o instanceof ModifiedFile && this.compareTo((ModifiedFile)o) == 0;
        }
        
        File getFile() {
            return this.file;
        }
        
        long getModified() {
            return this.modified;
        }
    }
    
    private interface StreamCloseCallback
    {
        void onClose();
    }
    
    private static final class StreamHeader
    {
        private static final int HEADER_VERSION = 0;
        
        static JSONObject readHeader(final InputStream inputStream) throws IOException {
            if (inputStream.read() != 0) {
                return null;
            }
            int n = 0;
            for (int i = 0; i < 3; ++i) {
                final int read = inputStream.read();
                if (read == -1) {
                    Logger.log(LoggingBehavior.CACHE, FileLruCache.TAG, "readHeader: stream.read returned -1 while reading header size");
                    return null;
                }
                n = (n << 8) + (read & 0xFF);
            }
            final byte[] array = new byte[n];
            int read2;
            for (int j = 0; j < array.length; j += read2) {
                read2 = inputStream.read(array, j, array.length - j);
                if (read2 < 1) {
                    Logger.log(LoggingBehavior.CACHE, FileLruCache.TAG, "readHeader: stream.read stopped at " + (Object)j + " when expected " + array.length);
                    return null;
                }
            }
            final JSONTokener jsonTokener = new JSONTokener(new String(array));
            try {
                final Object nextValue = jsonTokener.nextValue();
                if (!(nextValue instanceof JSONObject)) {
                    Logger.log(LoggingBehavior.CACHE, FileLruCache.TAG, "readHeader: expected JSONObject, got " + ((JSONObject)nextValue).getClass().getCanonicalName());
                    return null;
                }
                return (JSONObject)nextValue;
            }
            catch (JSONException ex) {
                throw new IOException(ex.getMessage());
            }
        }
        
        static void writeHeader(final OutputStream outputStream, final JSONObject jsonObject) throws IOException {
            final byte[] bytes = jsonObject.toString().getBytes();
            outputStream.write(0);
            outputStream.write(bytes.length >> 16 & 0xFF);
            outputStream.write(bytes.length >> 8 & 0xFF);
            outputStream.write(bytes.length >> 0 & 0xFF);
            outputStream.write(bytes);
        }
    }
}
