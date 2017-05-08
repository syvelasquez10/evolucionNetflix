// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.memory;

import com.facebook.common.logging.FLog;

class BasePool$Counter
{
    int mCount;
    int mNumBytes;
    
    public void decrement(final int n) {
        if (this.mNumBytes >= n && this.mCount > 0) {
            --this.mCount;
            this.mNumBytes -= n;
            return;
        }
        FLog.wtf("com.facebook.imagepipeline.memory.BasePool.Counter", "Unexpected decrement of %d. Current numBytes = %d, count = %d", n, this.mNumBytes, this.mCount);
    }
    
    public void increment(final int n) {
        ++this.mCount;
        this.mNumBytes += n;
    }
}
