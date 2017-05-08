// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.content;

class ModernAsyncTask$AsyncTaskResult<Data>
{
    final Data[] mData;
    final ModernAsyncTask mTask;
    
    ModernAsyncTask$AsyncTaskResult(final ModernAsyncTask mTask, final Data... mData) {
        this.mTask = mTask;
        this.mData = mData;
    }
}
