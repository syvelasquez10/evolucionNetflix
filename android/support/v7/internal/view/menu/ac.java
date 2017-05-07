// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.view.menu;

import android.view.KeyEvent;
import android.view.SubMenu;
import android.content.Intent;
import android.content.ComponentName;
import android.view.MenuItem;
import android.content.Context;
import android.view.Menu;
import android.support.v4.internal.view.SupportMenu;

class ac extends e<SupportMenu> implements Menu
{
    ac(final Context context, final SupportMenu supportMenu) {
        super(context, supportMenu);
    }
    
    public MenuItem add(final int n) {
        return this.a(((SupportMenu)this.b).add(n));
    }
    
    public MenuItem add(final int n, final int n2, final int n3, final int n4) {
        return this.a(((SupportMenu)this.b).add(n, n2, n3, n4));
    }
    
    public MenuItem add(final int n, final int n2, final int n3, final CharSequence charSequence) {
        return this.a(((SupportMenu)this.b).add(n, n2, n3, charSequence));
    }
    
    public MenuItem add(final CharSequence charSequence) {
        return this.a(((SupportMenu)this.b).add(charSequence));
    }
    
    public int addIntentOptions(int i, int addIntentOptions, int length, final ComponentName componentName, final Intent[] array, final Intent intent, final int n, final MenuItem[] array2) {
        MenuItem[] array3 = null;
        if (array2 != null) {
            array3 = new MenuItem[array2.length];
        }
        addIntentOptions = ((SupportMenu)this.b).addIntentOptions(i, addIntentOptions, length, componentName, array, intent, n, array3);
        if (array3 != null) {
            for (i = 0, length = array3.length; i < length; ++i) {
                array2[i] = this.a(array3[i]);
            }
        }
        return addIntentOptions;
    }
    
    public SubMenu addSubMenu(final int n) {
        return this.a(((SupportMenu)this.b).addSubMenu(n));
    }
    
    public SubMenu addSubMenu(final int n, final int n2, final int n3, final int n4) {
        return this.a(((SupportMenu)this.b).addSubMenu(n, n2, n3, n4));
    }
    
    public SubMenu addSubMenu(final int n, final int n2, final int n3, final CharSequence charSequence) {
        return this.a(((SupportMenu)this.b).addSubMenu(n, n2, n3, charSequence));
    }
    
    public SubMenu addSubMenu(final CharSequence charSequence) {
        return this.a(((SupportMenu)this.b).addSubMenu(charSequence));
    }
    
    public void clear() {
        this.a();
        ((SupportMenu)this.b).clear();
    }
    
    public void close() {
        ((SupportMenu)this.b).close();
    }
    
    public MenuItem findItem(final int n) {
        return this.a(((SupportMenu)this.b).findItem(n));
    }
    
    public MenuItem getItem(final int n) {
        return this.a(((SupportMenu)this.b).getItem(n));
    }
    
    public boolean hasVisibleItems() {
        return ((SupportMenu)this.b).hasVisibleItems();
    }
    
    public boolean isShortcutKey(final int n, final KeyEvent keyEvent) {
        return ((SupportMenu)this.b).isShortcutKey(n, keyEvent);
    }
    
    public boolean performIdentifierAction(final int n, final int n2) {
        return ((SupportMenu)this.b).performIdentifierAction(n, n2);
    }
    
    public boolean performShortcut(final int n, final KeyEvent keyEvent, final int n2) {
        return ((SupportMenu)this.b).performShortcut(n, keyEvent, n2);
    }
    
    public void removeGroup(final int n) {
        this.a(n);
        ((SupportMenu)this.b).removeGroup(n);
    }
    
    public void removeItem(final int n) {
        this.b(n);
        ((SupportMenu)this.b).removeItem(n);
    }
    
    public void setGroupCheckable(final int n, final boolean b, final boolean b2) {
        ((SupportMenu)this.b).setGroupCheckable(n, b, b2);
    }
    
    public void setGroupEnabled(final int n, final boolean b) {
        ((SupportMenu)this.b).setGroupEnabled(n, b);
    }
    
    public void setGroupVisible(final int n, final boolean b) {
        ((SupportMenu)this.b).setGroupVisible(n, b);
    }
    
    public void setQwertyMode(final boolean qwertyMode) {
        ((SupportMenu)this.b).setQwertyMode(qwertyMode);
    }
    
    public int size() {
        return ((SupportMenu)this.b).size();
    }
}
