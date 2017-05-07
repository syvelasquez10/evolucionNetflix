// 
// Decompiled by Procyon v0.5.30
// 

package bolts;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

final class Task$3 implements Continuation<Object, Void>
{
    final /* synthetic */ Task$TaskCompletionSource val$allFinished;
    final /* synthetic */ AtomicInteger val$count;
    final /* synthetic */ Object val$errorLock;
    final /* synthetic */ ArrayList val$errors;
    final /* synthetic */ AtomicBoolean val$isCancelled;
    
    Task$3(final Object val$errorLock, final ArrayList val$errors, final AtomicBoolean val$isCancelled, final AtomicInteger val$count, final Task$TaskCompletionSource val$allFinished) {
        this.val$errorLock = val$errorLock;
        this.val$errors = val$errors;
        this.val$isCancelled = val$isCancelled;
        this.val$count = val$count;
        this.val$allFinished = val$allFinished;
    }
    
    @Override
    public Void then(final Task<Object> task) {
        Label_0119: {
            Label_0028: {
                if (!task.isFaulted()) {
                    break Label_0028;
                }
                synchronized (this.val$errorLock) {
                    this.val$errors.add(task.getError());
                    // monitorexit(this.val$errorLock)
                    if (task.isCancelled()) {
                        this.val$isCancelled.set(true);
                    }
                    if (this.val$count.decrementAndGet() == 0) {
                        if (this.val$errors.size() == 0) {
                            break Label_0119;
                        }
                        if (this.val$errors.size() != 1) {
                            break Label_0028;
                        }
                        this.val$allFinished.setError(this.val$errors.get(0));
                    }
                    return null;
                }
            }
            this.val$allFinished.setError(new AggregateException(this.val$errors));
            return null;
        }
        if (this.val$isCancelled.get()) {
            this.val$allFinished.setCancelled();
            return null;
        }
        this.val$allFinished.setResult(null);
        return null;
    }
}
