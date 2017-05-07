// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.osp;

class AsyncTaskCompat$AsyncTaskResult<Data>
{
    final Data[] mData;
    final AsyncTaskCompat mTask;
    
    AsyncTaskCompat$AsyncTaskResult(final AsyncTaskCompat mTask, final Data... mData) {
        this.mTask = mTask;
        this.mData = mData;
    }
}
