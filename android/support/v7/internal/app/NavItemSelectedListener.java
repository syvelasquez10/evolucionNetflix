// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.app;

import android.view.View;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.support.v7.app.ActionBar$OnNavigationListener;
import android.support.v7.internal.widget.AdapterViewCompat$OnItemSelectedListener;

class NavItemSelectedListener implements AdapterViewCompat$OnItemSelectedListener
{
    private final ActionBar$OnNavigationListener mListener;
    
    public NavItemSelectedListener(final ActionBar$OnNavigationListener mListener) {
        this.mListener = mListener;
    }
    
    @Override
    public void onItemSelected(final AdapterViewCompat<?> adapterViewCompat, final View view, final int n, final long n2) {
        if (this.mListener != null) {
            this.mListener.onNavigationItemSelected(n, n2);
        }
    }
    
    @Override
    public void onNothingSelected(final AdapterViewCompat<?> adapterViewCompat) {
    }
}
