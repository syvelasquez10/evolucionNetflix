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

class SubMenuWrapperICS extends MenuWrapperICS implements SupportSubMenu
{
    SubMenuWrapperICS(final SubMenu subMenu) {
        super((Menu)subMenu);
    }
    
    public void clearHeader() {
        ((SubMenu)this.mWrappedObject).clearHeader();
    }
    
    public MenuItem getItem() {
        return (MenuItem)this.getMenuItemWrapper(((SubMenu)this.mWrappedObject).getItem());
    }
    
    public SubMenu getWrappedObject() {
        return (SubMenu)this.mWrappedObject;
    }
    
    public SubMenu setHeaderIcon(final int headerIcon) {
        ((SubMenu)this.mWrappedObject).setHeaderIcon(headerIcon);
        return (SubMenu)this;
    }
    
    public SubMenu setHeaderIcon(final Drawable headerIcon) {
        ((SubMenu)this.mWrappedObject).setHeaderIcon(headerIcon);
        return (SubMenu)this;
    }
    
    public SubMenu setHeaderTitle(final int headerTitle) {
        ((SubMenu)this.mWrappedObject).setHeaderTitle(headerTitle);
        return (SubMenu)this;
    }
    
    public SubMenu setHeaderTitle(final CharSequence headerTitle) {
        ((SubMenu)this.mWrappedObject).setHeaderTitle(headerTitle);
        return (SubMenu)this;
    }
    
    public SubMenu setHeaderView(final View headerView) {
        ((SubMenu)this.mWrappedObject).setHeaderView(headerView);
        return (SubMenu)this;
    }
    
    public SubMenu setIcon(final int icon) {
        ((SubMenu)this.mWrappedObject).setIcon(icon);
        return (SubMenu)this;
    }
    
    public SubMenu setIcon(final Drawable icon) {
        ((SubMenu)this.mWrappedObject).setIcon(icon);
        return (SubMenu)this;
    }
}
