// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import java.util.List;
import java.io.PrintWriter;
import java.io.FileDescriptor;

public abstract class FragmentManager
{
    public abstract FragmentTransaction beginTransaction();
    
    public abstract void dump(final String p0, final FileDescriptor p1, final PrintWriter p2, final String[] p3);
    
    public abstract boolean executePendingTransactions();
    
    public abstract Fragment findFragmentByTag(final String p0);
    
    public abstract List<Fragment> getFragments();
    
    public abstract boolean isDestroyed();
    
    public abstract void popBackStack(final int p0, final int p1);
    
    public abstract boolean popBackStackImmediate();
}
