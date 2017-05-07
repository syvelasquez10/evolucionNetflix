// 
// Decompiled by Procyon v0.5.30
// 

package bolts;

import java.util.concurrent.Callable;

final class Task$2 implements Runnable
{
    final /* synthetic */ Callable val$callable;
    final /* synthetic */ Task$TaskCompletionSource val$tcs;
    
    Task$2(final Task$TaskCompletionSource val$tcs, final Callable val$callable) {
        this.val$tcs = val$tcs;
        this.val$callable = val$callable;
    }
    
    @Override
    public void run() {
        try {
            this.val$tcs.setResult(this.val$callable.call());
        }
        catch (Exception error) {
            this.val$tcs.setError(error);
        }
    }
}
