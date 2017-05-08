// 
// Decompiled by Procyon v0.5.30
// 

package bolts;

import java.util.Locale;

public class CancellationToken
{
    private final CancellationTokenSource tokenSource;
    
    public boolean isCancellationRequested() {
        return this.tokenSource.isCancellationRequested();
    }
    
    @Override
    public String toString() {
        return String.format(Locale.US, "%s@%s[cancellationRequested=%s]", this.getClass().getName(), Integer.toHexString(this.hashCode()), Boolean.toString(this.tokenSource.isCancellationRequested()));
    }
}
