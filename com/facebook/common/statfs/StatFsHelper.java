// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.common.statfs;

import android.annotation.SuppressLint;
import android.os.Build$VERSION;
import com.facebook.common.internal.Throwables;
import android.os.SystemClock;
import android.os.Environment;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;
import android.os.StatFs;
import java.io.File;
import java.util.concurrent.locks.Lock;

public class StatFsHelper
{
    private static final long RESTAT_INTERVAL_MS;
    private static StatFsHelper sStatsFsHelper;
    private final Lock lock;
    private volatile File mExternalPath;
    private volatile StatFs mExternalStatFs;
    private volatile boolean mInitialized;
    private volatile File mInternalPath;
    private volatile StatFs mInternalStatFs;
    private long mLastRestatTime;
    
    static {
        RESTAT_INTERVAL_MS = TimeUnit.MINUTES.toMillis(2L);
    }
    
    protected StatFsHelper() {
        this.mInternalStatFs = null;
        this.mExternalStatFs = null;
        this.mInitialized = false;
        this.lock = new ReentrantLock();
    }
    
    protected static StatFs createStatFs(final String s) {
        return new StatFs(s);
    }
    
    private void ensureInitialized() {
        if (this.mInitialized) {
            return;
        }
        this.lock.lock();
        try {
            if (!this.mInitialized) {
                this.mInternalPath = Environment.getDataDirectory();
                this.mExternalPath = Environment.getExternalStorageDirectory();
                this.updateStats();
                this.mInitialized = true;
            }
        }
        finally {
            this.lock.unlock();
        }
    }
    
    public static StatFsHelper getInstance() {
        synchronized (StatFsHelper.class) {
            if (StatFsHelper.sStatsFsHelper == null) {
                StatFsHelper.sStatsFsHelper = new StatFsHelper();
            }
            return StatFsHelper.sStatsFsHelper;
        }
    }
    
    private void maybeUpdateStats() {
        if (!this.lock.tryLock()) {
            return;
        }
        try {
            if (SystemClock.uptimeMillis() - this.mLastRestatTime > StatFsHelper.RESTAT_INTERVAL_MS) {
                this.updateStats();
            }
        }
        finally {
            this.lock.unlock();
        }
    }
    
    private void updateStats() {
        this.mInternalStatFs = this.updateStatsHelper(this.mInternalStatFs, this.mInternalPath);
        this.mExternalStatFs = this.updateStatsHelper(this.mExternalStatFs, this.mExternalPath);
        this.mLastRestatTime = SystemClock.uptimeMillis();
    }
    
    private StatFs updateStatsHelper(final StatFs statFs, final File file) {
        if (file == null || !file.exists()) {
            return null;
        }
        Label_0025: {
            if (statFs != null) {
                break Label_0025;
            }
            try {
                return createStatFs(file.getAbsolutePath());
                statFs.restat(file.getAbsolutePath());
                return statFs;
            }
            catch (IllegalArgumentException ex) {
                return null;
            }
            catch (Throwable t) {
                throw Throwables.propagate(t);
            }
        }
    }
    
    @SuppressLint({ "DeprecatedMethod" })
    public long getAvailableStorageSpace(final StatFsHelper$StorageType statFsHelper$StorageType) {
        this.ensureInitialized();
        this.maybeUpdateStats();
        StatFs statFs;
        if (statFsHelper$StorageType == StatFsHelper$StorageType.INTERNAL) {
            statFs = this.mInternalStatFs;
        }
        else {
            statFs = this.mExternalStatFs;
        }
        if (statFs != null) {
            long blockSizeLong;
            long availableBlocksLong;
            if (Build$VERSION.SDK_INT >= 18) {
                blockSizeLong = statFs.getBlockSizeLong();
                availableBlocksLong = statFs.getAvailableBlocksLong();
            }
            else {
                blockSizeLong = statFs.getBlockSize();
                availableBlocksLong = statFs.getAvailableBlocks();
            }
            return availableBlocksLong * blockSizeLong;
        }
        return 0L;
    }
    
    public boolean testLowDiskSpace(final StatFsHelper$StorageType statFsHelper$StorageType, final long n) {
        this.ensureInitialized();
        final long availableStorageSpace = this.getAvailableStorageSpace(statFsHelper$StorageType);
        return availableStorageSpace <= 0L || availableStorageSpace < n;
    }
}
