// 
// Decompiled by Procyon v0.5.30
// 

package bolts;

import java.util.concurrent.CancellationException;
import java.util.concurrent.Callable;

final class Task$4 implements Runnable
{
    final /* synthetic */ Callable val$callable;
    final /* synthetic */ CancellationToken val$ct;
    final /* synthetic */ TaskCompletionSource val$tcs;
    
    Task$4(final CancellationToken val$ct, final TaskCompletionSource val$tcs, final Callable val$callable) {
        this.val$ct = val$ct;
        this.val$tcs = val$tcs;
        this.val$callable = val$callable;
    }
    
    @Override
    public void run() {
        if (this.val$ct != null && this.val$ct.isCancellationRequested()) {
            this.val$tcs.setCancelled();
            return;
        }
        try {
            this.val$tcs.setResult(this.val$callable.call());
        }
        catch (CancellationException ex) {
            this.val$tcs.setCancelled();
        }
        catch (Exception error) {
            this.val$tcs.setError(error);
        }
    }
}
