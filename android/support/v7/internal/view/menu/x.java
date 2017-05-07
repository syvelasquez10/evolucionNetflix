// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.view.menu;

import android.content.Context;

public interface x
{
    boolean collapseItemActionView(final i p0, final m p1);
    
    boolean expandItemActionView(final i p0, final m p1);
    
    boolean flagActionItems();
    
    void initForMenu(final Context p0, final i p1);
    
    void onCloseMenu(final i p0, final boolean p1);
    
    boolean onSubMenuSelected(final ad p0);
    
    void updateMenuView(final boolean p0);
}
