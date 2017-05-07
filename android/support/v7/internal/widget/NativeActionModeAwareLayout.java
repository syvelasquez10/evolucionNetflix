// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.view.ActionMode;
import android.view.ActionMode$Callback;
import android.view.View;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.LinearLayout;

public class NativeActionModeAwareLayout extends LinearLayout
{
    private OnActionModeForChildListener mActionModeForChildListener;
    
    public NativeActionModeAwareLayout(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    public void setActionModeForChildListener(final OnActionModeForChildListener mActionModeForChildListener) {
        this.mActionModeForChildListener = mActionModeForChildListener;
    }
    
    public ActionMode startActionModeForChild(final View view, final ActionMode$Callback actionMode$Callback) {
        ActionMode$Callback onActionModeForChild = actionMode$Callback;
        if (this.mActionModeForChildListener != null) {
            onActionModeForChild = this.mActionModeForChildListener.onActionModeForChild(actionMode$Callback);
        }
        return super.startActionModeForChild(view, onActionModeForChild);
    }
    
    public interface OnActionModeForChildListener
    {
        ActionMode$Callback onActionModeForChild(final ActionMode$Callback p0);
    }
}
