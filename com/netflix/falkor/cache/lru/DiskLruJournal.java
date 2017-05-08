// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.cache.lru;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Map;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.util.Iterator;
import com.netflix.mediaclient.Log;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.LinkedHashMap;
import java.io.Writer;
import java.util.concurrent.ThreadPoolExecutor;
import java.io.File;
import java.util.concurrent.Callable;

public final class DiskLruJournal
{
    private static final long ANY_SEQUENCE_NUMBER = -1L;
    private static final String CLEAN = "CLEAN";
    private static final String DIRTY = "DIRTY";
    private static final String JOURNAL_FILE = "journal";
    private static final String JOURNAL_FILE_BACKUP = "journal.bkp";
    private static final String JOURNAL_FILE_TEMP = "journal.tmp";
    private static final String MAGIC = "com.netflix.falkor.cache.lru.DiskLruJournal";
    private static final String READ = "READ";
    private static final String REMOVE = "REMOVE";
    private static final String TAG = "DiskLruJournal";
    private static final String VERSION_1 = "1";
    private final int appVersion;
    private final Callable<Void> cleanupCallable;
    private final File directory;
    private final ThreadPoolExecutor executorService;
    private final File journalFile;
    private final File journalFileBackup;
    private final File journalFileTmp;
    private Writer journalWriter;
    private final LinkedHashMap<String, DiskLruJournal$Entry> lruEntries;
    private final DiskLruJournal$OnRemovedListener mOnRemovedListener;
    private long maxSize;
    private long nextSequenceNumber;
    private int redundantOpCount;
    private long size;
    
    private DiskLruJournal(final File directory, final int appVersion, final long maxSize, final DiskLruJournal$OnRemovedListener mOnRemovedListener) {
        this.size = 0L;
        this.lruEntries = new LinkedHashMap<String, DiskLruJournal$Entry>(0, 0.75f, true);
        this.nextSequenceNumber = 0L;
        this.executorService = new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        this.cleanupCallable = new DiskLruJournal$1(this);
        this.directory = directory;
        this.appVersion = appVersion;
        this.journalFile = new File(directory, "journal");
        this.journalFileTmp = new File(directory, "journal.tmp");
        this.journalFileBackup = new File(directory, "journal.bkp");
        this.maxSize = maxSize;
        this.mOnRemovedListener = mOnRemovedListener;
    }
    
    private void checkNotClosed() {
        if (this.journalWriter == null) {
            throw new IllegalStateException("cache is closed");
        }
    }
    
    private void completeEdit(final DiskLruJournal$Editor diskLruJournal$Editor, final boolean b) {
        final DiskLruJournal$Entry access$1200;
        synchronized (this) {
            access$1200 = diskLruJournal$Editor.entry;
            if (access$1200.currentEditor != diskLruJournal$Editor) {
                throw new IllegalStateException();
            }
        }
        ++this.redundantOpCount;
        access$1200.currentEditor = null;
        if (access$1200.readable | b) {
            access$1200.readable = true;
            this.journalWriter.write("CLEAN " + access$1200.key + '\n');
            if (b) {
                final long nextSequenceNumber = this.nextSequenceNumber;
                this.nextSequenceNumber = 1L + nextSequenceNumber;
                access$1200.sequenceNumber = nextSequenceNumber;
            }
        }
        else {
            this.lruEntries.remove(access$1200.key);
            this.journalWriter.write("REMOVE " + access$1200.key + '\n');
        }
        this.journalWriter.flush();
        if (this.lruEntries.size() > this.maxSize || this.journalRebuildRequired()) {
            this.executorService.submit(this.cleanupCallable);
        }
    }
    // monitorexit(this)
    
    private static void deleteIfExists(final File file) {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }
    
    private DiskLruJournal$Editor edit(final String s, final long n) {
        while (true) {
            while (true) {
                DiskLruJournal$Entry diskLruJournal$Entry = null;
                Label_0151: {
                    synchronized (this) {
                        this.checkNotClosed();
                        diskLruJournal$Entry = this.lruEntries.get(s);
                        DiskLruJournal$Editor diskLruJournal$Editor;
                        if (n != -1L && (diskLruJournal$Entry == null || diskLruJournal$Entry.sequenceNumber != n)) {
                            diskLruJournal$Editor = null;
                        }
                        else {
                            if (diskLruJournal$Entry != null) {
                                break Label_0151;
                            }
                            diskLruJournal$Entry = new DiskLruJournal$Entry(this, s, null);
                            this.lruEntries.put(s, diskLruJournal$Entry);
                            final DiskLruJournal$Editor diskLruJournal$Editor2 = new DiskLruJournal$Editor(this, diskLruJournal$Entry, null);
                            diskLruJournal$Entry.currentEditor = diskLruJournal$Editor2;
                            this.journalWriter.write("DIRTY " + s + '\n');
                            this.journalWriter.flush();
                            diskLruJournal$Editor = diskLruJournal$Editor2;
                        }
                        return diskLruJournal$Editor;
                    }
                }
                if (diskLruJournal$Entry.currentEditor != null) {
                    return null;
                }
                continue;
            }
        }
    }
    
    private boolean journalRebuildRequired() {
        return this.redundantOpCount >= 2000 && this.redundantOpCount >= this.lruEntries.size();
    }
    
    public static DiskLruJournal open(final File file, final int n, final long n2, final DiskLruJournal$OnRemovedListener diskLruJournal$OnRemovedListener) {
        if (n2 <= 0L) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        Object o = new File(file, "journal.bkp");
        Label_0101: {
            if (((File)o).exists()) {
                final File file2 = new File(file, "journal");
                if (!file2.exists()) {
                    break Label_0101;
                }
                ((File)o).delete();
            }
            while (true) {
                o = new DiskLruJournal(file, n, n2, diskLruJournal$OnRemovedListener);
                if (!((DiskLruJournal)o).journalFile.exists()) {
                    break Label_0101;
                }
                try {
                    ((DiskLruJournal)o).readJournal();
                    ((DiskLruJournal)o).processJournal();
                    return (DiskLruJournal)o;
                    final File file2;
                    renameTo((File)o, file2, false);
                    continue;
                }
                catch (IOException ex) {
                    Log.e("DiskLruJournal", "DiskLruJournal " + file + " is corrupt: " + ex.getMessage() + ", removing");
                    ((DiskLruJournal)o).delete();
                }
                break;
            }
        }
        file.mkdirs();
        final DiskLruJournal diskLruJournal = new DiskLruJournal(file, n, n2, diskLruJournal$OnRemovedListener);
        diskLruJournal.rebuildJournal();
        return diskLruJournal;
    }
    
    private void processJournal() {
        deleteIfExists(this.journalFileTmp);
        final Iterator<DiskLruJournal$Entry> iterator = this.lruEntries.values().iterator();
        while (iterator.hasNext()) {
            final DiskLruJournal$Entry diskLruJournal$Entry = iterator.next();
            if (diskLruJournal$Entry.currentEditor != null) {
                diskLruJournal$Entry.currentEditor = null;
                iterator.remove();
            }
        }
    }
    
    private void readJournal() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore          4
        //     3: new             Ljava/io/FileInputStream;
        //     6: dup            
        //     7: aload_0        
        //     8: getfield        com/netflix/falkor/cache/lru/DiskLruJournal.journalFile:Ljava/io/File;
        //    11: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/File;)V
        //    14: astore          5
        //    16: new             Lcom/netflix/falkor/cache/lru/StrictLineReader;
        //    19: dup            
        //    20: aload           5
        //    22: getstatic       com/netflix/falkor/cache/lru/Util.US_ASCII:Ljava/nio/charset/Charset;
        //    25: invokespecial   com/netflix/falkor/cache/lru/StrictLineReader.<init>:(Ljava/io/InputStream;Ljava/nio/charset/Charset;)V
        //    28: astore          6
        //    30: aload           6
        //    32: invokevirtual   com/netflix/falkor/cache/lru/StrictLineReader.readLine:()Ljava/lang/String;
        //    35: astore_2       
        //    36: aload           6
        //    38: invokevirtual   com/netflix/falkor/cache/lru/StrictLineReader.readLine:()Ljava/lang/String;
        //    41: astore_3       
        //    42: aload           6
        //    44: invokevirtual   com/netflix/falkor/cache/lru/StrictLineReader.readLine:()Ljava/lang/String;
        //    47: astore          8
        //    49: aload           6
        //    51: invokevirtual   com/netflix/falkor/cache/lru/StrictLineReader.readLine:()Ljava/lang/String;
        //    54: astore          7
        //    56: ldc             "com.netflix.falkor.cache.lru.DiskLruJournal"
        //    58: aload_2        
        //    59: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    62: ifeq            100
        //    65: ldc             "1"
        //    67: aload_3        
        //    68: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    71: ifeq            100
        //    74: aload_0        
        //    75: getfield        com/netflix/falkor/cache/lru/DiskLruJournal.appVersion:I
        //    78: invokestatic    java/lang/Integer.toString:(I)Ljava/lang/String;
        //    81: aload           8
        //    83: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    86: ifeq            100
        //    89: ldc_w           ""
        //    92: aload           7
        //    94: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    97: ifne            195
        //   100: new             Ljava/io/IOException;
        //   103: dup            
        //   104: new             Ljava/lang/StringBuilder;
        //   107: dup            
        //   108: invokespecial   java/lang/StringBuilder.<init>:()V
        //   111: ldc_w           "unexpected journal header: ["
        //   114: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   117: aload_2        
        //   118: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   121: ldc_w           ", "
        //   124: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   127: aload_3        
        //   128: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   131: ldc_w           ", "
        //   134: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   137: aload           7
        //   139: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   142: ldc_w           "]"
        //   145: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   148: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   151: invokespecial   java/io/IOException.<init>:(Ljava/lang/String;)V
        //   154: athrow         
        //   155: astore_2       
        //   156: aload_2        
        //   157: athrow         
        //   158: astore_3       
        //   159: aload           6
        //   161: ifnull          173
        //   164: aload_2        
        //   165: ifnull          338
        //   168: aload           6
        //   170: invokevirtual   com/netflix/falkor/cache/lru/StrictLineReader.close:()V
        //   173: aload_3        
        //   174: athrow         
        //   175: astore_2       
        //   176: aload_2        
        //   177: athrow         
        //   178: astore_3       
        //   179: aload           5
        //   181: ifnull          193
        //   184: aload_2        
        //   185: ifnull          372
        //   188: aload           5
        //   190: invokevirtual   java/io/FileInputStream.close:()V
        //   193: aload_3        
        //   194: athrow         
        //   195: iconst_0       
        //   196: istore_1       
        //   197: aload_0        
        //   198: aload           6
        //   200: invokevirtual   com/netflix/falkor/cache/lru/StrictLineReader.readLine:()Ljava/lang/String;
        //   203: invokespecial   com/netflix/falkor/cache/lru/DiskLruJournal.readJournalLine:(Ljava/lang/String;)V
        //   206: iload_1        
        //   207: iconst_1       
        //   208: iadd           
        //   209: istore_1       
        //   210: goto            197
        //   213: astore_2       
        //   214: aload_0        
        //   215: iload_1        
        //   216: aload_0        
        //   217: getfield        com/netflix/falkor/cache/lru/DiskLruJournal.lruEntries:Ljava/util/LinkedHashMap;
        //   220: invokevirtual   java/util/LinkedHashMap.size:()I
        //   223: isub           
        //   224: putfield        com/netflix/falkor/cache/lru/DiskLruJournal.redundantOpCount:I
        //   227: aload           6
        //   229: invokevirtual   com/netflix/falkor/cache/lru/StrictLineReader.hasUnterminatedLine:()Z
        //   232: ifeq            268
        //   235: aload_0        
        //   236: invokespecial   com/netflix/falkor/cache/lru/DiskLruJournal.rebuildJournal:()V
        //   239: aload           6
        //   241: ifnull          253
        //   244: iconst_0       
        //   245: ifeq            319
        //   248: aload           6
        //   250: invokevirtual   com/netflix/falkor/cache/lru/StrictLineReader.close:()V
        //   253: aload           5
        //   255: ifnull          267
        //   258: iconst_0       
        //   259: ifeq            355
        //   262: aload           5
        //   264: invokevirtual   java/io/FileInputStream.close:()V
        //   267: return         
        //   268: aload_0        
        //   269: new             Ljava/io/BufferedWriter;
        //   272: dup            
        //   273: new             Ljava/io/OutputStreamWriter;
        //   276: dup            
        //   277: new             Ljava/io/FileOutputStream;
        //   280: dup            
        //   281: aload_0        
        //   282: getfield        com/netflix/falkor/cache/lru/DiskLruJournal.journalFile:Ljava/io/File;
        //   285: iconst_1       
        //   286: invokespecial   java/io/FileOutputStream.<init>:(Ljava/io/File;Z)V
        //   289: getstatic       com/netflix/falkor/cache/lru/Util.US_ASCII:Ljava/nio/charset/Charset;
        //   292: invokespecial   java/io/OutputStreamWriter.<init>:(Ljava/io/OutputStream;Ljava/nio/charset/Charset;)V
        //   295: invokespecial   java/io/BufferedWriter.<init>:(Ljava/io/Writer;)V
        //   298: putfield        com/netflix/falkor/cache/lru/DiskLruJournal.journalWriter:Ljava/io/Writer;
        //   301: goto            239
        //   304: astore_3       
        //   305: aconst_null    
        //   306: astore_2       
        //   307: goto            159
        //   310: astore_2       
        //   311: new             Ljava/lang/NullPointerException;
        //   314: dup            
        //   315: invokespecial   java/lang/NullPointerException.<init>:()V
        //   318: athrow         
        //   319: aload           6
        //   321: invokevirtual   com/netflix/falkor/cache/lru/StrictLineReader.close:()V
        //   324: goto            253
        //   327: astore          6
        //   329: aload_2        
        //   330: aload           6
        //   332: invokevirtual   java/lang/Throwable.addSuppressed:(Ljava/lang/Throwable;)V
        //   335: goto            173
        //   338: aload           6
        //   340: invokevirtual   com/netflix/falkor/cache/lru/StrictLineReader.close:()V
        //   343: goto            173
        //   346: astore_2       
        //   347: new             Ljava/lang/NullPointerException;
        //   350: dup            
        //   351: invokespecial   java/lang/NullPointerException.<init>:()V
        //   354: athrow         
        //   355: aload           5
        //   357: invokevirtual   java/io/FileInputStream.close:()V
        //   360: return         
        //   361: astore          4
        //   363: aload_2        
        //   364: aload           4
        //   366: invokevirtual   java/lang/Throwable.addSuppressed:(Ljava/lang/Throwable;)V
        //   369: goto            193
        //   372: aload           5
        //   374: invokevirtual   java/io/FileInputStream.close:()V
        //   377: goto            193
        //   380: astore_3       
        //   381: aload           4
        //   383: astore_2       
        //   384: goto            179
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                  
        //  -----  -----  -----  -----  ----------------------
        //  16     30     175    179    Ljava/lang/Throwable;
        //  16     30     380    387    Any
        //  30     100    155    159    Ljava/lang/Throwable;
        //  30     100    304    310    Any
        //  100    155    155    159    Ljava/lang/Throwable;
        //  100    155    304    310    Any
        //  156    158    158    159    Any
        //  168    173    327    338    Ljava/lang/Throwable;
        //  168    173    380    387    Any
        //  173    175    175    179    Ljava/lang/Throwable;
        //  173    175    380    387    Any
        //  176    178    178    179    Any
        //  188    193    361    372    Ljava/lang/Throwable;
        //  197    206    213    361    Ljava/io/EOFException;
        //  197    206    155    159    Ljava/lang/Throwable;
        //  197    206    304    310    Any
        //  214    239    155    159    Ljava/lang/Throwable;
        //  214    239    304    310    Any
        //  248    253    310    319    Ljava/lang/Throwable;
        //  248    253    380    387    Any
        //  262    267    346    355    Ljava/lang/Throwable;
        //  268    301    155    159    Ljava/lang/Throwable;
        //  268    301    304    310    Any
        //  311    319    175    179    Ljava/lang/Throwable;
        //  311    319    380    387    Any
        //  319    324    175    179    Ljava/lang/Throwable;
        //  319    324    380    387    Any
        //  329    335    175    179    Ljava/lang/Throwable;
        //  329    335    380    387    Any
        //  338    343    175    179    Ljava/lang/Throwable;
        //  338    343    380    387    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 183, Size: 183
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3551)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
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
    
    private void readJournalLine(final String s) {
        if (s.indexOf(32) == -1) {
            throw new IOException("unexpected journal line: " + s);
        }
        final String substring = s.substring(s.indexOf(" ") + 1);
        DiskLruJournal$Entry diskLruJournal$Entry;
        if ((diskLruJournal$Entry = this.lruEntries.get(substring)) == null) {
            diskLruJournal$Entry = new DiskLruJournal$Entry(this, substring, null);
            this.lruEntries.put(substring, diskLruJournal$Entry);
        }
        if (s.startsWith("CLEAN")) {
            diskLruJournal$Entry.readable = true;
            diskLruJournal$Entry.currentEditor = null;
        }
        else {
            if (s.startsWith("DIRTY")) {
                diskLruJournal$Entry.currentEditor = new DiskLruJournal$Editor(this, diskLruJournal$Entry, null);
                return;
            }
            if (!s.startsWith("READ")) {
                if (s.startsWith("REMOVE")) {
                    this.lruEntries.remove(substring);
                    return;
                }
                throw new IOException("unexpected journal line: " + s);
            }
        }
    }
    
    private void rebuildJournal() {
        BufferedWriter bufferedWriter;
        while (true) {
            while (true) {
                DiskLruJournal$Entry diskLruJournal$Entry = null;
                synchronized (this) {
                    if (this.journalWriter != null) {
                        this.journalWriter.close();
                    }
                    bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.journalFileTmp), Util.US_ASCII));
                    try {
                        bufferedWriter.write("com.netflix.falkor.cache.lru.DiskLruJournal");
                        bufferedWriter.write("\n");
                        bufferedWriter.write("1");
                        bufferedWriter.write("\n");
                        bufferedWriter.write(Integer.toString(this.appVersion));
                        bufferedWriter.write("\n");
                        bufferedWriter.write("\n");
                        final Iterator<DiskLruJournal$Entry> iterator = this.lruEntries.values().iterator();
                        while (iterator.hasNext()) {
                            diskLruJournal$Entry = iterator.next();
                            if (diskLruJournal$Entry.currentEditor == null) {
                                break;
                            }
                            bufferedWriter.write("DIRTY " + diskLruJournal$Entry.key + '\n');
                        }
                    }
                    finally {
                        bufferedWriter.close();
                    }
                }
                bufferedWriter.write("CLEAN " + diskLruJournal$Entry.key + '\n');
                continue;
            }
        }
        bufferedWriter.close();
        if (this.journalFile.exists()) {
            renameTo(this.journalFile, this.journalFileBackup, true);
        }
        renameTo(this.journalFileTmp, this.journalFile, false);
        this.journalFileBackup.delete();
        this.journalWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.journalFile, true), Util.US_ASCII));
    }
    // monitorexit(this)
    
    private static void renameTo(final File file, final File file2, final boolean b) {
        if (b) {
            deleteIfExists(file2);
        }
        if (!file.renameTo(file2)) {
            throw new IOException();
        }
    }
    
    private void trimToSize() {
        while (this.lruEntries.size() > this.maxSize) {
            this.remove(this.lruEntries.entrySet().iterator().next().getKey());
        }
    }
    
    public void close() {
        while (true) {
            synchronized (this) {
                if (this.journalWriter == null) {
                    return;
                }
                for (final DiskLruJournal$Entry diskLruJournal$Entry : new ArrayList<DiskLruJournal$Entry>(this.lruEntries.values())) {
                    if (diskLruJournal$Entry.currentEditor != null) {
                        diskLruJournal$Entry.currentEditor.abort();
                    }
                }
            }
            this.trimToSize();
            this.journalWriter.close();
            this.journalWriter = null;
        }
    }
    
    public void delete() {
        this.close();
        Util.deleteContents(this.directory);
    }
    
    public DiskLruJournal$Editor edit(final String s) {
        return this.edit(s, -1L);
    }
    
    public void flush() {
        synchronized (this) {
            this.checkNotClosed();
            this.trimToSize();
            this.journalWriter.flush();
        }
    }
    
    public DiskLruJournal$Snapshot get(final String s) {
        DiskLruJournal$Snapshot diskLruJournal$Snapshot = null;
        synchronized (this) {
            this.checkNotClosed();
            final DiskLruJournal$Entry diskLruJournal$Entry = this.lruEntries.get(s);
            if (diskLruJournal$Entry != null && diskLruJournal$Entry.readable) {
                ++this.redundantOpCount;
                this.journalWriter.append((CharSequence)"READ").append((CharSequence)" ").append((CharSequence)s).append('\n');
                if (this.journalRebuildRequired()) {
                    this.executorService.submit(this.cleanupCallable);
                }
                diskLruJournal$Snapshot = new DiskLruJournal$Snapshot(this, s, diskLruJournal$Entry.sequenceNumber, null);
            }
            return diskLruJournal$Snapshot;
        }
    }
    
    public File getDirectory() {
        return this.directory;
    }
    
    public int getEntriesCount() {
        return this.lruEntries.size();
    }
    
    public long getMaxSize() {
        synchronized (this) {
            return this.maxSize;
        }
    }
    
    public boolean isClosed() {
        synchronized (this) {
            return this.journalWriter == null;
        }
    }
    
    public boolean remove(final String s) {
        synchronized (this) {
            this.checkNotClosed();
            final DiskLruJournal$Entry diskLruJournal$Entry = this.lruEntries.get(s);
            boolean b;
            if (diskLruJournal$Entry == null || diskLruJournal$Entry.currentEditor != null) {
                b = false;
            }
            else {
                ++this.redundantOpCount;
                this.journalWriter.append((CharSequence)"REMOVE").append((CharSequence)" ").append((CharSequence)s).append((CharSequence)"\n");
                final DiskLruJournal$Snapshot value = this.get(s);
                this.lruEntries.remove(s);
                if (this.mOnRemovedListener != null) {
                    this.mOnRemovedListener.onRemoved(s, value);
                }
                if (this.journalRebuildRequired()) {
                    this.executorService.submit(this.cleanupCallable);
                }
                b = true;
            }
            return b;
        }
    }
    
    public void setMaxSize(final long maxSize) {
        synchronized (this) {
            this.maxSize = maxSize;
            this.executorService.submit(this.cleanupCallable);
        }
    }
    
    public long size() {
        synchronized (this) {
            return this.size;
        }
    }
}
