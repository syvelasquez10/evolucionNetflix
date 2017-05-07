// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.view.menu;

import android.support.v4.view.MenuItemCompat$OnActionExpandListener;
import android.view.MenuItem$OnActionExpandListener;
import android.support.v4.content.ContextCompat;
import android.view.SubMenu;
import android.view.ContextMenu$ContextMenuInfo;
import android.view.ActionProvider;
import android.view.MenuItem;
import android.view.View;
import android.view.MenuItem$OnMenuItemClickListener;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.content.Intent;
import android.support.v4.internal.view.SupportMenuItem;

public class a implements SupportMenuItem
{
    private static final int n = 0;
    private static final int p = 1;
    private static final int q = 2;
    private static final int r = 4;
    private static final int s = 8;
    private static final int t = 16;
    private final int a;
    private final int b;
    private final int c;
    private final int d;
    private CharSequence e;
    private CharSequence f;
    private Intent g;
    private char h;
    private char i;
    private Drawable j;
    private int k;
    private Context l;
    private MenuItem$OnMenuItemClickListener m;
    private int o;
    
    public a(final Context l, final int b, final int a, final int c, final int d, final CharSequence e) {
        this.k = 0;
        this.o = 16;
        this.l = l;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
    }
    
    public SupportMenuItem a(final int n) {
        throw new UnsupportedOperationException();
    }
    
    public SupportMenuItem a(final View view) {
        throw new UnsupportedOperationException();
    }
    
    public a a(final boolean b) {
        final int o = this.o;
        int n;
        if (b) {
            n = 4;
        }
        else {
            n = 0;
        }
        this.o = (n | (o & 0xFFFFFFFB));
        return this;
    }
    
    public boolean a() {
        if (this.m != null && this.m.onMenuItemClick((MenuItem)this)) {
            return true;
        }
        if (this.g != null) {
            this.l.startActivity(this.g);
            return true;
        }
        return false;
    }
    
    public SupportMenuItem b(final int showAsAction) {
        this.setShowAsAction(showAsAction);
        return this;
    }
    
    @Override
    public boolean collapseActionView() {
        return false;
    }
    
    @Override
    public boolean expandActionView() {
        return false;
    }
    
    public ActionProvider getActionProvider() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public View getActionView() {
        return null;
    }
    
    public char getAlphabeticShortcut() {
        return this.i;
    }
    
    public int getGroupId() {
        return this.b;
    }
    
    public Drawable getIcon() {
        return this.j;
    }
    
    public Intent getIntent() {
        return this.g;
    }
    
    public int getItemId() {
        return this.a;
    }
    
    public ContextMenu$ContextMenuInfo getMenuInfo() {
        return null;
    }
    
    public char getNumericShortcut() {
        return this.h;
    }
    
    public int getOrder() {
        return this.d;
    }
    
    public SubMenu getSubMenu() {
        return null;
    }
    
    @Override
    public android.support.v4.view.ActionProvider getSupportActionProvider() {
        return null;
    }
    
    public CharSequence getTitle() {
        return this.e;
    }
    
    public CharSequence getTitleCondensed() {
        if (this.f != null) {
            return this.f;
        }
        return this.e;
    }
    
    public boolean hasSubMenu() {
        return false;
    }
    
    @Override
    public boolean isActionViewExpanded() {
        return false;
    }
    
    public boolean isCheckable() {
        return (this.o & 0x1) != 0x0;
    }
    
    public boolean isChecked() {
        return (this.o & 0x2) != 0x0;
    }
    
    public boolean isEnabled() {
        return (this.o & 0x10) != 0x0;
    }
    
    public boolean isVisible() {
        return (this.o & 0x8) == 0x0;
    }
    
    public MenuItem setActionProvider(final ActionProvider actionProvider) {
        throw new UnsupportedOperationException();
    }
    
    public MenuItem setAlphabeticShortcut(final char i) {
        this.i = i;
        return (MenuItem)this;
    }
    
    public MenuItem setCheckable(final boolean b) {
        final int o = this.o;
        boolean b2;
        if (b) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        this.o = ((b2 ? 1 : 0) | (o & 0xFFFFFFFE));
        return (MenuItem)this;
    }
    
    public MenuItem setChecked(final boolean b) {
        final int o = this.o;
        int n;
        if (b) {
            n = 2;
        }
        else {
            n = 0;
        }
        this.o = (n | (o & 0xFFFFFFFD));
        return (MenuItem)this;
    }
    
    public MenuItem setEnabled(final boolean b) {
        final int o = this.o;
        int n;
        if (b) {
            n = 16;
        }
        else {
            n = 0;
        }
        this.o = (n | (o & 0xFFFFFFEF));
        return (MenuItem)this;
    }
    
    public MenuItem setIcon(final int k) {
        this.k = k;
        this.j = ContextCompat.getDrawable(this.l, k);
        return (MenuItem)this;
    }
    
    public MenuItem setIcon(final Drawable j) {
        this.j = j;
        this.k = 0;
        return (MenuItem)this;
    }
    
    public MenuItem setIntent(final Intent g) {
        this.g = g;
        return (MenuItem)this;
    }
    
    public MenuItem setNumericShortcut(final char h) {
        this.h = h;
        return (MenuItem)this;
    }
    
    public MenuItem setOnActionExpandListener(final MenuItem$OnActionExpandListener menuItem$OnActionExpandListener) {
        throw new UnsupportedOperationException();
    }
    
    public MenuItem setOnMenuItemClickListener(final MenuItem$OnMenuItemClickListener m) {
        this.m = m;
        return (MenuItem)this;
    }
    
    public MenuItem setShortcut(final char h, final char i) {
        this.h = h;
        this.i = i;
        return (MenuItem)this;
    }
    
    @Override
    public void setShowAsAction(final int n) {
    }
    
    @Override
    public SupportMenuItem setSupportActionProvider(final android.support.v4.view.ActionProvider actionProvider) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public SupportMenuItem setSupportOnActionExpandListener(final MenuItemCompat$OnActionExpandListener menuItemCompat$OnActionExpandListener) {
        return this;
    }
    
    public MenuItem setTitle(final int n) {
        this.e = this.l.getResources().getString(n);
        return (MenuItem)this;
    }
    
    public MenuItem setTitle(final CharSequence e) {
        this.e = e;
        return (MenuItem)this;
    }
    
    public MenuItem setTitleCondensed(final CharSequence f) {
        this.f = f;
        return (MenuItem)this;
    }
    
    public MenuItem setVisible(final boolean b) {
        final int o = this.o;
        int n;
        if (b) {
            n = 0;
        }
        else {
            n = 8;
        }
        this.o = (n | (o & 0x8));
        return (MenuItem)this;
    }
}
