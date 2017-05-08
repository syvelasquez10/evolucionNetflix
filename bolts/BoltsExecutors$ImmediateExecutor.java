// 
// Decompiled by Procyon v0.5.30
// 

package bolts;

import java.util.concurrent.Executor;

class BoltsExecutors$ImmediateExecutor implements Executor
{
    private ThreadLocal<Integer> executionDepth;
    
    private BoltsExecutors$ImmediateExecutor() {
        this.executionDepth = new ThreadLocal<Integer>();
    }
    
    private int decrementDepth() {
        Integer value;
        if ((value = this.executionDepth.get()) == null) {
            value = 0;
        }
        final int n = value - 1;
        if (n == 0) {
            this.executionDepth.remove();
            return n;
        }
        this.executionDepth.set(n);
        return n;
    }
    
    private int incrementDepth() {
        Integer value;
        if ((value = this.executionDepth.get()) == null) {
            value = 0;
        }
        final int n = value + 1;
        this.executionDepth.set(n);
        return n;
    }
    
    @Override
    public void execute(final Runnable runnable) {
        Label_0021: {
            if (this.incrementDepth() > 15) {
                break Label_0021;
            }
            try {
                runnable.run();
                return;
                BoltsExecutors.background().execute(runnable);
            }
            finally {
                this.decrementDepth();
            }
        }
    }
}
