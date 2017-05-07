// 
// Decompiled by Procyon v0.5.30
// 

package bolts;

public interface Continuation<TTaskResult, TContinuationResult>
{
    TContinuationResult then(final Task<TTaskResult> p0);
}
