// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.view.menu;

import android.view.View;
import android.graphics.drawable.Drawable;
import android.view.MenuItem;
import android.view.Menu;
import android.view.SubMenu;
import android.support.v4.internal.view.SupportSubMenu;

class ae extends ac implements SupportSubMenu
{
    ae(final SubMenu subMenu) {
        super((Menu)subMenu);
    }
    
    public void clearHeader() {
        ((SubMenu)this.a).clearHeader();
    }
    
    public MenuItem getItem() {
        return (MenuItem)this.a(((SubMenu)this.a).getItem());
    }
    
    public SubMenu setHeaderIcon(final int headerIcon) {
        ((SubMenu)this.a).setHeaderIcon(headerIcon);
        return (SubMenu)this;
    }
    
    public SubMenu setHeaderIcon(final Drawable headerIcon) {
        ((SubMenu)this.a).setHeaderIcon(headerIcon);
        return (SubMenu)this;
    }
    
    public SubMenu setHeaderTitle(final int headerTitle) {
        ((SubMenu)this.a).setHeaderTitle(headerTitle);
        return (SubMenu)this;
    }
    
    public SubMenu setHeaderTitle(final CharSequence headerTitle) {
        ((SubMenu)this.a).setHeaderTitle(headerTitle);
        return (SubMenu)this;
    }
    
    public SubMenu setHeaderView(final View headerView) {
        ((SubMenu)this.a).setHeaderView(headerView);
        return (SubMenu)this;
    }
    
    public SubMenu setIcon(final int icon) {
        ((SubMenu)this.a).setIcon(icon);
        return (SubMenu)this;
    }
    
    public SubMenu setIcon(final Drawable icon) {
        ((SubMenu)this.a).setIcon(icon);
        return (SubMenu)this;
    }
}
