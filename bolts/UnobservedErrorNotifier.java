// 
// Decompiled by Procyon v0.5.30
// 

package bolts;

class UnobservedErrorNotifier
{
    private Task<?> task;
    
    public UnobservedErrorNotifier(final Task<?> task) {
        this.task = task;
    }
    
    @Override
    protected void finalize() {
        try {
            final Task<?> task = this.task;
            if (task != null) {
                final Task$UnobservedExceptionHandler unobservedExceptionHandler = Task.getUnobservedExceptionHandler();
                if (unobservedExceptionHandler != null) {
                    unobservedExceptionHandler.unobservedException(task, new UnobservedTaskException(task.getError()));
                }
            }
        }
        finally {
            super.finalize();
        }
    }
    
    public void setObserved() {
        this.task = null;
    }
}
