// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import java.util.List;

public abstract class FragmentManager
{
    public abstract FragmentTransaction beginTransaction();
    
    public abstract boolean executePendingTransactions();
    
    public abstract Fragment findFragmentByTag(final String p0);
    
    public abstract List<Fragment> getFragments();
    
    public abstract void popBackStack(final int p0, final int p1);
}
