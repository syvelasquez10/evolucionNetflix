// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.util;

public interface Pools$Pool<T>
{
    T acquire();
    
    boolean release(final T p0);
}
