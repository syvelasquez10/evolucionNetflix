// 
// Decompiled by Procyon v0.5.30
// 

package bolts;

public class TaskCompletionSource<TResult>
{
    private final Task<TResult> task;
    
    public TaskCompletionSource() {
        this.task = new Task<TResult>();
    }
    
    public Task<TResult> getTask() {
        return this.task;
    }
    
    public void setCancelled() {
        if (!this.trySetCancelled()) {
            throw new IllegalStateException("Cannot cancel a completed task.");
        }
    }
    
    public void setError(final Exception ex) {
        if (!this.trySetError(ex)) {
            throw new IllegalStateException("Cannot set the error on a completed task.");
        }
    }
    
    public void setResult(final TResult tResult) {
        if (!this.trySetResult(tResult)) {
            throw new IllegalStateException("Cannot set the result of a completed task.");
        }
    }
    
    public boolean trySetCancelled() {
        return this.task.trySetCancelled();
    }
    
    public boolean trySetError(final Exception ex) {
        return this.task.trySetError(ex);
    }
    
    public boolean trySetResult(final TResult tResult) {
        return this.task.trySetResult(tResult);
    }
}
