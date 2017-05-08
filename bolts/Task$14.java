// 
// Decompiled by Procyon v0.5.30
// 

package bolts;

import java.util.concurrent.CancellationException;

final class Task$14 implements Runnable
{
    final /* synthetic */ Continuation val$continuation;
    final /* synthetic */ CancellationToken val$ct;
    final /* synthetic */ Task val$task;
    final /* synthetic */ TaskCompletionSource val$tcs;
    
    Task$14(final CancellationToken val$ct, final TaskCompletionSource val$tcs, final Continuation val$continuation, final Task val$task) {
        this.val$ct = val$ct;
        this.val$tcs = val$tcs;
        this.val$continuation = val$continuation;
        this.val$task = val$task;
    }
    
    @Override
    public void run() {
        if (this.val$ct != null && this.val$ct.isCancellationRequested()) {
            this.val$tcs.setCancelled();
            return;
        }
        try {
            this.val$tcs.setResult(this.val$continuation.then(this.val$task));
        }
        catch (CancellationException ex) {
            this.val$tcs.setCancelled();
        }
        catch (Exception error) {
            this.val$tcs.setError(error);
        }
    }
}
