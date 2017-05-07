// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.view.ActionMode;
import android.view.ActionMode$Callback;
import android.view.View;
import android.util.AttributeSet;
import android.content.Context;
import android.annotation.TargetApi;

@TargetApi(11)
public class NativeActionModeAwareLayout extends ContentFrameLayout
{
    private OnActionModeForChildListener mActionModeForChildListener;
    
    public NativeActionModeAwareLayout(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    public void setActionModeForChildListener(final OnActionModeForChildListener mActionModeForChildListener) {
        this.mActionModeForChildListener = mActionModeForChildListener;
    }
    
    public ActionMode startActionModeForChild(final View view, final ActionMode$Callback actionMode$Callback) {
        if (this.mActionModeForChildListener != null) {
            return this.mActionModeForChildListener.startActionModeForChild(view, actionMode$Callback);
        }
        return super.startActionModeForChild(view, actionMode$Callback);
    }
    
    public interface OnActionModeForChildListener
    {
        ActionMode startActionModeForChild(final View p0, final ActionMode$Callback p1);
    }
}
