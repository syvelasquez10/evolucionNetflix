// 
// Decompiled by Procyon v0.5.30
// 

package bolts;

import java.io.Closeable;

public class CancellationTokenRegistration implements Closeable
{
    private Runnable action;
    private boolean closed;
    private final Object lock;
    private CancellationTokenSource tokenSource;
    
    @Override
    public void close() {
        synchronized (this.lock) {
            if (this.closed) {
                return;
            }
            this.closed = true;
            this.tokenSource.unregister(this);
            this.tokenSource = null;
            this.action = null;
        }
    }
}
