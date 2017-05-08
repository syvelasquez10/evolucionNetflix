// 
// Decompiled by Procyon v0.5.30
// 

package bolts;

import java.util.concurrent.CancellationException;

final class Task$15 implements Runnable
{
    final /* synthetic */ Continuation val$continuation;
    final /* synthetic */ CancellationToken val$ct;
    final /* synthetic */ Task val$task;
    final /* synthetic */ TaskCompletionSource val$tcs;
    
    Task$15(final CancellationToken val$ct, final TaskCompletionSource val$tcs, final Continuation val$continuation, final Task val$task) {
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
            if (this.val$continuation.then(this.val$task) == null) {
                this.val$tcs.setResult(null);
                return;
            }
            goto Label_0064;
        }
        catch (CancellationException ex) {
            this.val$tcs.setCancelled();
        }
        catch (Exception error) {
            this.val$tcs.setError(error);
        }
    }
}
