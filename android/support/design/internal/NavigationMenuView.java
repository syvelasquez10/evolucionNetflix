// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.internal;

import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.RecyclerView$LayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;
import android.content.Context;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;

public class NavigationMenuView extends RecyclerView implements MenuView
{
    public NavigationMenuView(final Context context) {
        this(context, null);
    }
    
    public NavigationMenuView(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public NavigationMenuView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.setLayoutManager(new LinearLayoutManager(context, 1, false));
    }
    
    public int getWindowAnimations() {
        return 0;
    }
    
    @Override
    public void initialize(final MenuBuilder menuBuilder) {
    }
}
