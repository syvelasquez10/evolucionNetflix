// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.content.ComponentName;
import android.view.View;
import android.content.Context;
import android.os.Build$VERSION;

public abstract class SearchViewCompat$OnCloseListenerCompat
{
    final Object mListener;
    
    public SearchViewCompat$OnCloseListenerCompat() {
        this.mListener = SearchViewCompat.IMPL.newOnCloseListener(this);
    }
    
    public boolean onClose() {
        return false;
    }
}
