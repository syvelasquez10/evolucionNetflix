// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.memory;

import com.facebook.common.memory.MemoryTrimmable;
import com.facebook.common.internal.Throwables;
import android.annotation.SuppressLint;
import com.facebook.common.logging.FLog;
import com.facebook.common.internal.Sets;
import android.util.SparseIntArray;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import java.util.Set;
import android.util.SparseArray;

public abstract class BasePool<V> implements Pool<V>
{
    private final Class<?> TAG;
    private boolean mAllowNewBuckets;
    final SparseArray<Bucket<V>> mBuckets;
    final BasePool$Counter mFree;
    final Set<V> mInUseValues;
    final MemoryTrimmableRegistry mMemoryTrimmableRegistry;
    final PoolParams mPoolParams;
    private final PoolStatsTracker mPoolStatsTracker;
    final BasePool$Counter mUsed;
    
    public BasePool(final MemoryTrimmableRegistry memoryTrimmableRegistry, final PoolParams poolParams, final PoolStatsTracker poolStatsTracker) {
        this.TAG = this.getClass();
        this.mMemoryTrimmableRegistry = Preconditions.checkNotNull(memoryTrimmableRegistry);
        this.mPoolParams = Preconditions.checkNotNull(poolParams);
        this.mPoolStatsTracker = Preconditions.checkNotNull(poolStatsTracker);
        this.mBuckets = (SparseArray<Bucket<V>>)new SparseArray();
        this.initBuckets(new SparseIntArray(0));
        this.mInUseValues = Sets.newIdentityHashSet();
        this.mFree = new BasePool$Counter();
        this.mUsed = new BasePool$Counter();
    }
    
    private void ensurePoolSizeInvariant() {
        while (true) {
            while (true) {
                synchronized (this) {
                    if (this.isMaxSizeSoftCapExceeded()) {
                        if (this.mFree.mNumBytes != 0) {
                            final boolean b = false;
                            Preconditions.checkState(b);
                            return;
                        }
                    }
                }
                final boolean b = true;
                continue;
            }
        }
    }
    
    private void initBuckets(final SparseIntArray sparseIntArray) {
        int i = 0;
        synchronized (this) {
            Preconditions.checkNotNull(sparseIntArray);
            this.mBuckets.clear();
            final SparseIntArray bucketSizes = this.mPoolParams.bucketSizes;
            if (bucketSizes != null) {
                while (i < bucketSizes.size()) {
                    final int key = bucketSizes.keyAt(i);
                    this.mBuckets.put(key, (Object)new Bucket(this.getSizeInBytes(key), bucketSizes.valueAt(i), sparseIntArray.get(key, 0)));
                    ++i;
                }
                this.mAllowNewBuckets = false;
            }
            else {
                this.mAllowNewBuckets = true;
            }
        }
    }
    
    @SuppressLint({ "InvalidAccessToGuardedField" })
    private void logStats() {
        if (FLog.isLoggable(2)) {
            FLog.v(this.TAG, "Used = (%d, %d); Free = (%d, %d)", (Object)this.mUsed.mCount, this.mUsed.mNumBytes, this.mFree.mCount, this.mFree.mNumBytes);
        }
    }
    
    protected abstract V alloc(final int p0);
    
    boolean canAllocate(final int n) {
        while (true) {
            boolean b = false;
            synchronized (this) {
                final int maxSizeHardCap = this.mPoolParams.maxSizeHardCap;
                if (n > maxSizeHardCap - this.mUsed.mNumBytes) {
                    this.mPoolStatsTracker.onHardCapReached();
                }
                else {
                    final int maxSizeSoftCap = this.mPoolParams.maxSizeSoftCap;
                    if (n > maxSizeSoftCap - (this.mUsed.mNumBytes + this.mFree.mNumBytes)) {
                        this.trimToSize(maxSizeSoftCap - n);
                    }
                    if (n <= maxSizeHardCap - (this.mUsed.mNumBytes + this.mFree.mNumBytes)) {
                        return true;
                    }
                    this.mPoolStatsTracker.onHardCapReached();
                }
                return b;
            }
            b = true;
            return b;
        }
    }
    
    protected abstract void free(final V p0);
    
    @Override
    public V get(int n) {
        this.ensurePoolSizeInvariant();
        n = this.getBucketedSize(n);
        int n2;
        synchronized (this) {
            final Bucket<V> bucket = this.getBucket(n);
            if (bucket != null) {
                final V value = bucket.get();
                if (value != null) {
                    Preconditions.checkState(this.mInUseValues.add(value));
                    n = this.getBucketedSizeForValue(value);
                    n2 = this.getSizeInBytes(n);
                    this.mUsed.increment(n2);
                    this.mFree.decrement(n2);
                    this.mPoolStatsTracker.onValueReuse(n2);
                    this.logStats();
                    if (FLog.isLoggable(2)) {
                        FLog.v(this.TAG, "get (reuse) (object, size) = (%x, %s)", (Object)System.identityHashCode(value), n);
                    }
                    return value;
                }
            }
            n2 = this.getSizeInBytes(n);
            if (!this.canAllocate(n2)) {
                throw new BasePool$PoolSizeViolationException(this.mPoolParams.maxSizeHardCap, this.mUsed.mNumBytes, this.mFree.mNumBytes, n2);
            }
        }
        this.mUsed.increment(n2);
        final Bucket bucket2;
        if (bucket2 != null) {
            bucket2.incrementInUseCount();
        }
        // monitorexit(this)
        try {
            final V alloc = this.alloc(n);
            synchronized (this) {
                Preconditions.checkState(this.mInUseValues.add(alloc));
                this.trimToSoftCap();
                this.mPoolStatsTracker.onAlloc(n2);
                this.logStats();
                if (FLog.isLoggable(2)) {
                    FLog.v(this.TAG, "get (alloc) (object, size) = (%x, %s)", (Object)System.identityHashCode(alloc), n);
                }
                return alloc;
            }
        }
        catch (Throwable t) {
            synchronized (this) {
                this.mUsed.decrement(n2);
                final Bucket<V> bucket3 = this.getBucket(n);
                if (bucket3 != null) {
                    bucket3.decrementInUseCount();
                }
                // monitorexit(this)
                Throwables.propagateIfPossible(t);
            }
        }
    }
    
    Bucket<V> getBucket(final int n) {
        synchronized (this) {
            Bucket<V> bucket2;
            final Bucket bucket = bucket2 = (Bucket<V>)this.mBuckets.get(n);
            if (bucket == null) {
                if (!this.mAllowNewBuckets) {
                    bucket2 = (Bucket<V>)bucket;
                }
                else {
                    if (FLog.isLoggable(2)) {
                        FLog.v(this.TAG, "creating new bucket %s", (Object)n);
                    }
                    bucket2 = this.newBucket(n);
                    this.mBuckets.put(n, (Object)bucket2);
                }
            }
            return bucket2;
        }
    }
    
    protected abstract int getBucketedSize(final int p0);
    
    protected abstract int getBucketedSizeForValue(final V p0);
    
    protected abstract int getSizeInBytes(final int p0);
    
    protected void initialize() {
        this.mMemoryTrimmableRegistry.registerMemoryTrimmable(this);
        this.mPoolStatsTracker.setBasePool(this);
    }
    
    boolean isMaxSizeSoftCapExceeded() {
        synchronized (this) {
            final boolean b = this.mUsed.mNumBytes + this.mFree.mNumBytes > this.mPoolParams.maxSizeSoftCap;
            if (b) {
                this.mPoolStatsTracker.onSoftCapReached();
            }
            return b;
        }
    }
    
    protected boolean isReusable(final V v) {
        Preconditions.checkNotNull(v);
        return true;
    }
    
    Bucket<V> newBucket(final int n) {
        return new Bucket<V>(this.getSizeInBytes(n), Integer.MAX_VALUE, 0);
    }
    
    @Override
    public void release(final V v) {
        while (true) {
            Preconditions.checkNotNull(v);
            final int bucketedSizeForValue = this.getBucketedSizeForValue(v);
            final int sizeInBytes = this.getSizeInBytes(bucketedSizeForValue);
            while (true) {
                final Bucket<V> bucket;
                Label_0189: {
                    synchronized (this) {
                        bucket = this.getBucket(bucketedSizeForValue);
                        if (!this.mInUseValues.remove(v)) {
                            FLog.e(this.TAG, "release (free, value unrecognized) (object, size) = (%x, %s)", System.identityHashCode(v), bucketedSizeForValue);
                            this.free(v);
                            this.mPoolStatsTracker.onFree(sizeInBytes);
                        }
                        else {
                            if (bucket != null && !bucket.isMaxLengthExceeded() && !this.isMaxSizeSoftCapExceeded() && this.isReusable(v)) {
                                break Label_0189;
                            }
                            if (bucket != null) {
                                bucket.decrementInUseCount();
                            }
                            if (FLog.isLoggable(2)) {
                                FLog.v(this.TAG, "release (free) (object, size) = (%x, %s)", (Object)System.identityHashCode(v), bucketedSizeForValue);
                            }
                            this.free(v);
                            this.mUsed.decrement(sizeInBytes);
                            this.mPoolStatsTracker.onFree(sizeInBytes);
                        }
                        this.logStats();
                        return;
                    }
                }
                final V v2;
                bucket.release(v2);
                this.mFree.increment(sizeInBytes);
                this.mUsed.decrement(sizeInBytes);
                this.mPoolStatsTracker.onValueRelease(sizeInBytes);
                if (FLog.isLoggable(2)) {
                    FLog.v(this.TAG, "release (reuse) (object, size) = (%x, %s)", (Object)System.identityHashCode(v2), bucketedSizeForValue);
                    continue;
                }
                continue;
            }
        }
    }
    
    void trimToSize(final int n) {
        while (true) {
            while (true) {
                int i = 0;
                int n2 = 0;
                Label_0154: {
                    synchronized (this) {
                        i = Math.min(this.mUsed.mNumBytes + this.mFree.mNumBytes - n, this.mFree.mNumBytes);
                        if (i > 0) {
                            if (FLog.isLoggable(2)) {
                                FLog.v(this.TAG, "trimToSize: TargetSize = %d; Initial Size = %d; Bytes to free = %d", (Object)n, this.mUsed.mNumBytes + this.mFree.mNumBytes, i);
                            }
                            this.logStats();
                            n2 = 0;
                            if (n2 < this.mBuckets.size() && i > 0) {
                                break Label_0154;
                            }
                            this.logStats();
                            if (FLog.isLoggable(2)) {
                                FLog.v(this.TAG, "trimToSize: TargetSize = %d; Final Size = %d", (Object)n, this.mUsed.mNumBytes + this.mFree.mNumBytes);
                            }
                        }
                        return;
                    }
                }
                final Bucket bucket = (Bucket)this.mBuckets.valueAt(n2);
                while (i > 0) {
                    final V pop = bucket.pop();
                    if (pop == null) {
                        break;
                    }
                    this.free(pop);
                    i -= bucket.mItemSize;
                    this.mFree.decrement(bucket.mItemSize);
                }
                ++n2;
                continue;
            }
        }
    }
    
    void trimToSoftCap() {
        synchronized (this) {
            if (this.isMaxSizeSoftCapExceeded()) {
                this.trimToSize(this.mPoolParams.maxSizeSoftCap);
            }
        }
    }
}
