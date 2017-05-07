// 
// Decompiled by Procyon v0.5.30
// 

package bolts;

final class Task$9 implements Runnable
{
    final /* synthetic */ Continuation val$continuation;
    final /* synthetic */ Task val$task;
    final /* synthetic */ Task$TaskCompletionSource val$tcs;
    
    Task$9(final Continuation val$continuation, final Task val$task, final Task$TaskCompletionSource val$tcs) {
        this.val$continuation = val$continuation;
        this.val$task = val$task;
        this.val$tcs = val$tcs;
    }
    
    @Override
    public void run() {
        try {
            this.val$tcs.setResult(this.val$continuation.then(this.val$task));
        }
        catch (Exception error) {
            this.val$tcs.setError(error);
        }
    }
}
