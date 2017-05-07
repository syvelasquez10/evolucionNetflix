// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.view;

import android.content.res.XmlResourceParser;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;
import android.view.InflateException;
import android.util.Xml;
import android.support.v4.internal.view.SupportMenu;
import org.xmlpull.v1.XmlPullParser;
import android.content.ContextWrapper;
import android.app.Activity;
import android.content.Context;
import android.view.MenuInflater;
import android.content.res.TypedArray;
import android.support.v7.appcompat.R$styleable;
import android.util.AttributeSet;
import android.view.SubMenu;
import android.view.View;
import android.support.v7.internal.view.menu.o;
import android.support.v7.internal.view.menu.m;
import android.view.MenuItem$OnMenuItemClickListener;
import android.support.v4.view.MenuItemCompat;
import android.view.MenuItem;
import android.util.Log;
import android.view.Menu;
import android.support.v4.view.ActionProvider;

class SupportMenuInflater$MenuState
{
    private int groupCategory;
    private int groupCheckable;
    private boolean groupEnabled;
    private int groupId;
    private int groupOrder;
    private boolean groupVisible;
    private ActionProvider itemActionProvider;
    private String itemActionProviderClassName;
    private String itemActionViewClassName;
    private int itemActionViewLayout;
    private boolean itemAdded;
    private char itemAlphabeticShortcut;
    private int itemCategoryOrder;
    private int itemCheckable;
    private boolean itemChecked;
    private boolean itemEnabled;
    private int itemIconResId;
    private int itemId;
    private String itemListenerMethodName;
    private char itemNumericShortcut;
    private int itemShowAsAction;
    private CharSequence itemTitle;
    private CharSequence itemTitleCondensed;
    private boolean itemVisible;
    private Menu menu;
    final /* synthetic */ SupportMenuInflater this$0;
    
    public SupportMenuInflater$MenuState(final SupportMenuInflater this$0, final Menu menu) {
        this.this$0 = this$0;
        this.menu = menu;
        this.resetGroup();
    }
    
    private char getShortcut(final String s) {
        if (s == null) {
            return '\0';
        }
        return s.charAt(0);
    }
    
    private <T> T newInstance(final String s, final Class<?>[] array, final Object[] array2) {
        try {
            return (T)this.this$0.mContext.getClassLoader().loadClass(s).getConstructor(array).newInstance(array2);
        }
        catch (Exception ex) {
            Log.w("SupportMenuInflater", "Cannot instantiate class: " + s, (Throwable)ex);
            return null;
        }
    }
    
    private void setItem(final MenuItem menuItem) {
        int n = 1;
        menuItem.setChecked(this.itemChecked).setVisible(this.itemVisible).setEnabled(this.itemEnabled).setCheckable(this.itemCheckable >= 1).setTitleCondensed(this.itemTitleCondensed).setIcon(this.itemIconResId).setAlphabeticShortcut(this.itemAlphabeticShortcut).setNumericShortcut(this.itemNumericShortcut);
        if (this.itemShowAsAction >= 0) {
            MenuItemCompat.setShowAsAction(menuItem, this.itemShowAsAction);
        }
        if (this.itemListenerMethodName != null) {
            if (this.this$0.mContext.isRestricted()) {
                throw new IllegalStateException("The android:onClick attribute cannot be used within a restricted context");
            }
            menuItem.setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new SupportMenuInflater$InflatedOnMenuItemClickListener(this.this$0.getRealOwner(), this.itemListenerMethodName));
        }
        if (menuItem instanceof m) {
            final m m = (m)menuItem;
        }
        if (this.itemCheckable >= 2) {
            if (menuItem instanceof m) {
                ((m)menuItem).a(true);
            }
            else if (menuItem instanceof o) {
                ((o)menuItem).a(true);
            }
        }
        if (this.itemActionViewClassName != null) {
            MenuItemCompat.setActionView(menuItem, this.newInstance(this.itemActionViewClassName, SupportMenuInflater.ACTION_VIEW_CONSTRUCTOR_SIGNATURE, this.this$0.mActionViewConstructorArguments));
        }
        else {
            n = 0;
        }
        if (this.itemActionViewLayout > 0) {
            if (n == 0) {
                MenuItemCompat.setActionView(menuItem, this.itemActionViewLayout);
            }
            else {
                Log.w("SupportMenuInflater", "Ignoring attribute 'itemActionViewLayout'. Action view already specified.");
            }
        }
        if (this.itemActionProvider != null) {
            MenuItemCompat.setActionProvider(menuItem, this.itemActionProvider);
        }
    }
    
    public void addItem() {
        this.itemAdded = true;
        this.setItem(this.menu.add(this.groupId, this.itemId, this.itemCategoryOrder, this.itemTitle));
    }
    
    public SubMenu addSubMenuItem() {
        this.itemAdded = true;
        final SubMenu addSubMenu = this.menu.addSubMenu(this.groupId, this.itemId, this.itemCategoryOrder, this.itemTitle);
        this.setItem(addSubMenu.getItem());
        return addSubMenu;
    }
    
    public boolean hasAddedItem() {
        return this.itemAdded;
    }
    
    public void readGroup(final AttributeSet set) {
        final TypedArray obtainStyledAttributes = this.this$0.mContext.obtainStyledAttributes(set, R$styleable.MenuGroup);
        this.groupId = obtainStyledAttributes.getResourceId(R$styleable.MenuGroup_android_id, 0);
        this.groupCategory = obtainStyledAttributes.getInt(R$styleable.MenuGroup_android_menuCategory, 0);
        this.groupOrder = obtainStyledAttributes.getInt(R$styleable.MenuGroup_android_orderInCategory, 0);
        this.groupCheckable = obtainStyledAttributes.getInt(R$styleable.MenuGroup_android_checkableBehavior, 0);
        this.groupVisible = obtainStyledAttributes.getBoolean(R$styleable.MenuGroup_android_visible, true);
        this.groupEnabled = obtainStyledAttributes.getBoolean(R$styleable.MenuGroup_android_enabled, true);
        obtainStyledAttributes.recycle();
    }
    
    public void readItem(final AttributeSet set) {
        final boolean b = true;
        final TypedArray obtainStyledAttributes = this.this$0.mContext.obtainStyledAttributes(set, R$styleable.MenuItem);
        this.itemId = obtainStyledAttributes.getResourceId(R$styleable.MenuItem_android_id, 0);
        this.itemCategoryOrder = ((obtainStyledAttributes.getInt(R$styleable.MenuItem_android_menuCategory, this.groupCategory) & 0xFFFF0000) | (obtainStyledAttributes.getInt(R$styleable.MenuItem_android_orderInCategory, this.groupOrder) & 0xFFFF));
        this.itemTitle = obtainStyledAttributes.getText(R$styleable.MenuItem_android_title);
        this.itemTitleCondensed = obtainStyledAttributes.getText(R$styleable.MenuItem_android_titleCondensed);
        this.itemIconResId = obtainStyledAttributes.getResourceId(R$styleable.MenuItem_android_icon, 0);
        this.itemAlphabeticShortcut = this.getShortcut(obtainStyledAttributes.getString(R$styleable.MenuItem_android_alphabeticShortcut));
        this.itemNumericShortcut = this.getShortcut(obtainStyledAttributes.getString(R$styleable.MenuItem_android_numericShortcut));
        if (obtainStyledAttributes.hasValue(R$styleable.MenuItem_android_checkable)) {
            int itemCheckable;
            if (obtainStyledAttributes.getBoolean(R$styleable.MenuItem_android_checkable, false)) {
                itemCheckable = 1;
            }
            else {
                itemCheckable = 0;
            }
            this.itemCheckable = itemCheckable;
        }
        else {
            this.itemCheckable = this.groupCheckable;
        }
        this.itemChecked = obtainStyledAttributes.getBoolean(R$styleable.MenuItem_android_checked, false);
        this.itemVisible = obtainStyledAttributes.getBoolean(R$styleable.MenuItem_android_visible, this.groupVisible);
        this.itemEnabled = obtainStyledAttributes.getBoolean(R$styleable.MenuItem_android_enabled, this.groupEnabled);
        this.itemShowAsAction = obtainStyledAttributes.getInt(R$styleable.MenuItem_showAsAction, -1);
        this.itemListenerMethodName = obtainStyledAttributes.getString(R$styleable.MenuItem_android_onClick);
        this.itemActionViewLayout = obtainStyledAttributes.getResourceId(R$styleable.MenuItem_actionLayout, 0);
        this.itemActionViewClassName = obtainStyledAttributes.getString(R$styleable.MenuItem_actionViewClass);
        this.itemActionProviderClassName = obtainStyledAttributes.getString(R$styleable.MenuItem_actionProviderClass);
        final boolean b2 = this.itemActionProviderClassName != null && b;
        if (b2 && this.itemActionViewLayout == 0 && this.itemActionViewClassName == null) {
            this.itemActionProvider = this.newInstance(this.itemActionProviderClassName, SupportMenuInflater.ACTION_PROVIDER_CONSTRUCTOR_SIGNATURE, this.this$0.mActionProviderConstructorArguments);
        }
        else {
            if (b2) {
                Log.w("SupportMenuInflater", "Ignoring attribute 'actionProviderClass'. Action view already specified.");
            }
            this.itemActionProvider = null;
        }
        obtainStyledAttributes.recycle();
        this.itemAdded = false;
    }
    
    public void resetGroup() {
        this.groupId = 0;
        this.groupCategory = 0;
        this.groupOrder = 0;
        this.groupCheckable = 0;
        this.groupVisible = true;
        this.groupEnabled = true;
    }
}
