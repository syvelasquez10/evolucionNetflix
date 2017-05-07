// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.view.menu;

import android.view.KeyEvent;
import android.view.SubMenu;
import android.content.Intent;
import android.content.ComponentName;
import android.view.MenuItem;
import android.support.v4.internal.view.SupportMenu;
import android.view.Menu;

class MenuWrapperICS extends BaseMenuWrapper<Menu> implements SupportMenu
{
    MenuWrapperICS(final Menu menu) {
        super(menu);
    }
    
    public MenuItem add(final int n) {
        return (MenuItem)this.getMenuItemWrapper(((Menu)this.mWrappedObject).add(n));
    }
    
    public MenuItem add(final int n, final int n2, final int n3, final int n4) {
        return (MenuItem)this.getMenuItemWrapper(((Menu)this.mWrappedObject).add(n, n2, n3, n4));
    }
    
    public MenuItem add(final int n, final int n2, final int n3, final CharSequence charSequence) {
        return (MenuItem)this.getMenuItemWrapper(((Menu)this.mWrappedObject).add(n, n2, n3, charSequence));
    }
    
    public MenuItem add(final CharSequence charSequence) {
        return (MenuItem)this.getMenuItemWrapper(((Menu)this.mWrappedObject).add(charSequence));
    }
    
    public int addIntentOptions(int i, int addIntentOptions, int length, final ComponentName componentName, final Intent[] array, final Intent intent, final int n, final MenuItem[] array2) {
        MenuItem[] array3 = null;
        if (array2 != null) {
            array3 = new MenuItem[array2.length];
        }
        addIntentOptions = ((Menu)this.mWrappedObject).addIntentOptions(i, addIntentOptions, length, componentName, array, intent, n, array3);
        if (array3 != null) {
            for (i = 0, length = array3.length; i < length; ++i) {
                array2[i] = (MenuItem)this.getMenuItemWrapper(array3[i]);
            }
        }
        return addIntentOptions;
    }
    
    public SubMenu addSubMenu(final int n) {
        return this.getSubMenuWrapper(((Menu)this.mWrappedObject).addSubMenu(n));
    }
    
    public SubMenu addSubMenu(final int n, final int n2, final int n3, final int n4) {
        return this.getSubMenuWrapper(((Menu)this.mWrappedObject).addSubMenu(n, n2, n3, n4));
    }
    
    public SubMenu addSubMenu(final int n, final int n2, final int n3, final CharSequence charSequence) {
        return this.getSubMenuWrapper(((Menu)this.mWrappedObject).addSubMenu(n, n2, n3, charSequence));
    }
    
    public SubMenu addSubMenu(final CharSequence charSequence) {
        return this.getSubMenuWrapper(((Menu)this.mWrappedObject).addSubMenu(charSequence));
    }
    
    public void clear() {
        this.internalClear();
        ((Menu)this.mWrappedObject).clear();
    }
    
    public void close() {
        ((Menu)this.mWrappedObject).close();
    }
    
    public MenuItem findItem(final int n) {
        return (MenuItem)this.getMenuItemWrapper(((Menu)this.mWrappedObject).findItem(n));
    }
    
    public MenuItem getItem(final int n) {
        return (MenuItem)this.getMenuItemWrapper(((Menu)this.mWrappedObject).getItem(n));
    }
    
    public boolean hasVisibleItems() {
        return ((Menu)this.mWrappedObject).hasVisibleItems();
    }
    
    public boolean isShortcutKey(final int n, final KeyEvent keyEvent) {
        return ((Menu)this.mWrappedObject).isShortcutKey(n, keyEvent);
    }
    
    public boolean performIdentifierAction(final int n, final int n2) {
        return ((Menu)this.mWrappedObject).performIdentifierAction(n, n2);
    }
    
    public boolean performShortcut(final int n, final KeyEvent keyEvent, final int n2) {
        return ((Menu)this.mWrappedObject).performShortcut(n, keyEvent, n2);
    }
    
    public void removeGroup(final int n) {
        this.internalRemoveGroup(n);
        ((Menu)this.mWrappedObject).removeGroup(n);
    }
    
    public void removeItem(final int n) {
        this.internalRemoveItem(n);
        ((Menu)this.mWrappedObject).removeItem(n);
    }
    
    public void setGroupCheckable(final int n, final boolean b, final boolean b2) {
        ((Menu)this.mWrappedObject).setGroupCheckable(n, b, b2);
    }
    
    public void setGroupEnabled(final int n, final boolean b) {
        ((Menu)this.mWrappedObject).setGroupEnabled(n, b);
    }
    
    public void setGroupVisible(final int n, final boolean b) {
        ((Menu)this.mWrappedObject).setGroupVisible(n, b);
    }
    
    public void setQwertyMode(final boolean qwertyMode) {
        ((Menu)this.mWrappedObject).setQwertyMode(qwertyMode);
    }
    
    public int size() {
        return ((Menu)this.mWrappedObject).size();
    }
}
