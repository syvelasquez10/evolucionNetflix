// 
// Decompiled by Procyon v0.5.30
// 

package bolts;

import java.util.List;

public class AggregateException extends Exception
{
    private static final long serialVersionUID = 1L;
    private List<Exception> errors;
    
    public AggregateException(final List<Exception> errors) {
        super("There were multiple errors.");
        this.errors = errors;
    }
    
    public List<Exception> getErrors() {
        return this.errors;
    }
}
