// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.view.View;
import android.view.ContextMenu$ContextMenuInfo;

public class AdapterViewCompat$AdapterContextMenuInfo implements ContextMenu$ContextMenuInfo
{
    public long id;
    public int position;
    public View targetView;
    
    public AdapterViewCompat$AdapterContextMenuInfo(final View targetView, final int position, final long id) {
        this.targetView = targetView;
        this.position = position;
        this.id = id;
    }
}
