// 
// Decompiled by Procyon v0.5.30
// 

package bolts;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

class Task$4 implements Continuation<Void, Task<Void>>
{
    final /* synthetic */ Task this$0;
    final /* synthetic */ Continuation val$continuation;
    final /* synthetic */ Executor val$executor;
    final /* synthetic */ Callable val$predicate;
    final /* synthetic */ Capture val$predicateContinuation;
    
    Task$4(final Task this$0, final Callable val$predicate, final Continuation val$continuation, final Executor val$executor, final Capture val$predicateContinuation) {
        this.this$0 = this$0;
        this.val$predicate = val$predicate;
        this.val$continuation = val$continuation;
        this.val$executor = val$executor;
        this.val$predicateContinuation = val$predicateContinuation;
    }
    
    @Override
    public Task<Void> then(final Task<Void> task) {
        if (this.val$predicate.call()) {
            return Task.forResult((Object)null).onSuccessTask((Continuation<Object, Task<Object>>)this.val$continuation, this.val$executor).onSuccessTask(this.val$predicateContinuation.get(), this.val$executor);
        }
        return Task.forResult((Void)null);
    }
}
