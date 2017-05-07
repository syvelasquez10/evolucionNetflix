// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.view.menu;

import android.view.View;
import android.support.v7.view.CollapsibleActionView;
import android.widget.FrameLayout;

class q extends FrameLayout implements CollapsibleActionView
{
    final android.view.CollapsibleActionView a;
    
    q(final View view) {
        super(view.getContext());
        this.a = (android.view.CollapsibleActionView)view;
        this.addView(view);
    }
    
    View a() {
        return (View)this.a;
    }
    
    public void onActionViewCollapsed() {
        this.a.onActionViewCollapsed();
    }
    
    public void onActionViewExpanded() {
        this.a.onActionViewExpanded();
    }
}
