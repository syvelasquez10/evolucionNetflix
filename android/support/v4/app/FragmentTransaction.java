// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

public abstract class FragmentTransaction
{
    public abstract FragmentTransaction add(final Fragment p0, final String p1);
    
    public abstract int commit();
    
    public abstract int commitAllowingStateLoss();
    
    public abstract FragmentTransaction remove(final Fragment p0);
    
    public abstract FragmentTransaction replace(final int p0, final Fragment p1);
    
    public abstract FragmentTransaction setCustomAnimations(final int p0, final int p1);
}
