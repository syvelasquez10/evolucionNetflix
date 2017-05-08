// 
// Decompiled by Procyon v0.5.30
// 

package bolts;

import java.util.Locale;
import java.util.Iterator;
import java.util.concurrent.ScheduledFuture;
import java.util.List;
import java.io.Closeable;

public class CancellationTokenSource implements Closeable
{
    private boolean cancellationRequested;
    private boolean closed;
    private final Object lock;
    private final List<CancellationTokenRegistration> registrations;
    private ScheduledFuture<?> scheduledCancellation;
    
    private void cancelScheduledCancellation() {
        if (this.scheduledCancellation != null) {
            this.scheduledCancellation.cancel(true);
            this.scheduledCancellation = null;
        }
    }
    
    private void throwIfClosed() {
        if (this.closed) {
            throw new IllegalStateException("Object already closed");
        }
    }
    
    @Override
    public void close() {
        synchronized (this.lock) {
            if (this.closed) {
                return;
            }
            this.cancelScheduledCancellation();
            final Iterator<CancellationTokenRegistration> iterator = this.registrations.iterator();
            while (iterator.hasNext()) {
                iterator.next().close();
            }
        }
        this.registrations.clear();
        this.closed = true;
    }
    // monitorexit(o)
    
    public boolean isCancellationRequested() {
        synchronized (this.lock) {
            this.throwIfClosed();
            return this.cancellationRequested;
        }
    }
    
    @Override
    public String toString() {
        return String.format(Locale.US, "%s@%s[cancellationRequested=%s]", this.getClass().getName(), Integer.toHexString(this.hashCode()), Boolean.toString(this.isCancellationRequested()));
    }
    
    void unregister(final CancellationTokenRegistration cancellationTokenRegistration) {
        synchronized (this.lock) {
            this.throwIfClosed();
            this.registrations.remove(cancellationTokenRegistration);
        }
    }
}
