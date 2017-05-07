// 
// Decompiled by Procyon v0.5.30
// 

package bolts;

public class Capture<T>
{
    private T value;
    
    public Capture() {
    }
    
    public Capture(final T value) {
        this.value = value;
    }
    
    public T get() {
        return this.value;
    }
    
    public void set(final T value) {
        this.value = value;
    }
}
