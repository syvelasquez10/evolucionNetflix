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
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.LinkedHashMap;
import java.io.Writer;
import java.util.concurrent.ThreadPoolExecutor;
import java.io.File;
import java.util.concurrent.Callable;
import java.io.IOException;

public final class DiskLruJournal$Editor
{
    private boolean committed;
    private final DiskLruJournal$Entry entry;
    private boolean hasErrors;
    final /* synthetic */ DiskLruJournal this$0;
    
    private DiskLruJournal$Editor(final DiskLruJournal this$0, final DiskLruJournal$Entry entry) {
        this.this$0 = this$0;
        this.entry = entry;
    }
    
    public void abort() {
        this.this$0.completeEdit(this, false);
    }
    
    public void abortUnlessCommitted() {
        if (this.committed) {
            return;
        }
        try {
            this.abort();
        }
        catch (IOException ex) {}
    }
    
    public void commit() {
        if (this.hasErrors) {
            this.this$0.completeEdit(this, false);
            this.this$0.remove(this.entry.key);
        }
        else {
            this.this$0.completeEdit(this, true);
        }
        this.committed = true;
    }
}
