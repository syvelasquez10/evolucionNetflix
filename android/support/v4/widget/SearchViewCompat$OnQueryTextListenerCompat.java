// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.content.ComponentName;
import android.view.View;
import android.content.Context;
import android.os.Build$VERSION;

public abstract class SearchViewCompat$OnQueryTextListenerCompat
{
    final Object mListener;
    
    public SearchViewCompat$OnQueryTextListenerCompat() {
        this.mListener = SearchViewCompat.IMPL.newOnQueryTextListener(this);
    }
    
    public boolean onQueryTextChange(final String s) {
        return false;
    }
    
    public boolean onQueryTextSubmit(final String s) {
        return false;
    }
}
