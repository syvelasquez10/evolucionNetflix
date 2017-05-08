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

class DiskLruJournal$1 implements Callable<Void>
{
    final /* synthetic */ DiskLruJournal this$0;
    
    DiskLruJournal$1(final DiskLruJournal this$0) {
        this.this$0 = this$0;
    }
    
    private void cleanup() {
        synchronized (this.this$0) {
            if (this.this$0.journalWriter == null) {
                return;
            }
            this.this$0.trimToSize();
            if (this.this$0.journalRebuildRequired()) {
                this.this$0.rebuildJournal();
                this.this$0.redundantOpCount = 0;
            }
        }
    }
    
    @Override
    public Void call() {
        this.cleanup();
        return null;
    }
}
