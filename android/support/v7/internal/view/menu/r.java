// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.view.menu;

import android.view.View;
import android.view.CollapsibleActionView;
import android.widget.FrameLayout;

class r extends FrameLayout implements CollapsibleActionView
{
    final android.support.v7.view.CollapsibleActionView a;
    
    r(final View view) {
        super(view.getContext());
        this.a = (android.support.v7.view.CollapsibleActionView)view;
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
