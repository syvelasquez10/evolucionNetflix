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

public final class DiskLruJournal$Snapshot
{
    private final String key;
    private final long sequenceNumber;
    final /* synthetic */ DiskLruJournal this$0;
    
    private DiskLruJournal$Snapshot(final DiskLruJournal this$0, final String key, final long sequenceNumber) {
        this.this$0 = this$0;
        this.key = key;
        this.sequenceNumber = sequenceNumber;
    }
    
    public DiskLruJournal$Editor edit() {
        return this.this$0.edit(this.key, this.sequenceNumber);
    }
}
