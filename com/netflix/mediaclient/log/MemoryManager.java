// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.log;

import com.netflix.mediaclient.util.AndroidUtils;
import android.app.ActivityManager$MemoryInfo;
import android.app.ActivityManager;
import com.netflix.mediaclient.Log;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import android.content.Context;
import java.util.concurrent.ScheduledFuture;

public final class MemoryManager implements Runnable
{
    private static int DELTA = 0;
    private static final String TAG = "nf-memory";
    private ScheduledFuture<?> cancelHelper;
    private Context context;
    private ScheduledExecutorService executor;
    
    static {
        MemoryManager.DELTA = 60;
    }
    
    public MemoryManager(final Context context) {
        this(context, MemoryManager.DELTA);
    }
    
    public MemoryManager(final Context context, int delta) {
        this.executor = Executors.newSingleThreadScheduledExecutor();
        if (context == null) {
            throw new IllegalArgumentException("Context can not be null!");
        }
        this.context = context;
        if (delta <= 0) {
            delta = MemoryManager.DELTA;
        }
        this.cancelHelper = this.executor.scheduleAtFixedRate(this, 0L, 60L, TimeUnit.SECONDS);
    }
    
    private static void logLine(final String s, final int n, final int n2) {
        if (n > s.length()) {
            return;
        }
        if (n2 > s.length()) {
            Log.i("nf-memory", s.substring(n));
            return;
        }
        Log.i("nf-memory", s.substring(n, n2));
    }
    
    private static void parseMemoryDump(String substring) {
        int n = 82;
        int i = 0;
        if (substring != null) {
            final int n2 = substring.lastIndexOf("**") + 2;
            final int index = substring.indexOf("Objects");
            if (n2 < index) {
                substring = substring.substring(n2, index);
                logLine(substring, 0, 82);
                while (i < 6) {
                    logLine(substring, n, n + 81);
                    n += 80;
                    ++i;
                }
                Log.i("nf-memory", " \n");
                Log.i("nf-memory", " =================================");
            }
        }
    }
    
    public void destroy() {
        synchronized (this) {
            if (this.executor != null) {
                if (this.cancelHelper != null) {
                    this.executor.execute(new MemoryManager$1(this));
                }
                this.context = null;
            }
        }
    }
    
    @Override
    public void run() {
        final ActivityManager activityManager = (ActivityManager)this.context.getSystemService("activity");
        if (activityManager == null) {
            Log.e("nf-memory", "Unable to find activity manager! Unable to get memory data!");
            return;
        }
        final ActivityManager$MemoryInfo activityManager$MemoryInfo = new ActivityManager$MemoryInfo();
        activityManager.getMemoryInfo(activityManager$MemoryInfo);
        Log.i("nf-memory", "=================================");
        Log.i("nf-memory", "Memory usage:\n");
        Log.i("nf-memory", "minfo.availMem  [b]:" + activityManager$MemoryInfo.availMem);
        Log.i("nf-memory", "minfo.lowMemory [b]: " + activityManager$MemoryInfo.lowMemory);
        Log.i("nf-memory", "minfo.threshold [b]: " + activityManager$MemoryInfo.threshold);
        Log.i("nf-memory", "\n");
        try {
            parseMemoryDump(AndroidUtils.dumpMemInfo("com.netflix.mediaclient"));
        }
        catch (Throwable t) {
            Log.e("nf-memory", "Fail to execute dumpsys meminfo", t);
        }
    }
}
