// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.view.menu;

import android.support.v4.view.ActionProvider$VisibilityListener;
import android.view.MenuItem$OnActionExpandListener;
import android.os.Build$VERSION;
import android.view.SubMenu;
import android.view.ViewDebug$CapturedViewProperty;
import android.support.v7.internal.widget.TintManager;
import android.content.ActivityNotFoundException;
import android.util.Log;
import android.view.MenuItem;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.view.LayoutInflater;
import android.view.ContextMenu$ContextMenuInfo;
import android.support.v4.view.MenuItemCompat$OnActionExpandListener;
import android.support.v4.view.ActionProvider;
import android.view.View;
import android.view.MenuItem$OnMenuItemClickListener;
import android.graphics.drawable.Drawable;
import android.content.Intent;
import android.support.v4.internal.view.SupportMenuItem;

public final class m implements SupportMenuItem
{
    private static String w;
    private static String x;
    private static String y;
    private static String z;
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
    private i l;
    private ad m;
    private Runnable n;
    private MenuItem$OnMenuItemClickListener o;
    private int p;
    private int q;
    private View r;
    private ActionProvider s;
    private MenuItemCompat$OnActionExpandListener t;
    private boolean u;
    private ContextMenu$ContextMenuInfo v;
    
    m(final i l, final int b, final int a, final int c, final int d, final CharSequence e, final int q) {
        this.k = 0;
        this.p = 16;
        this.q = 0;
        this.u = false;
        this.l = l;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.q = q;
    }
    
    public SupportMenuItem a(final int n) {
        final Context e = this.l.e();
        this.a(LayoutInflater.from(e).inflate(n, (ViewGroup)new LinearLayout(e), false));
        return this;
    }
    
    public SupportMenuItem a(final View r) {
        this.r = r;
        this.s = null;
        if (r != null && r.getId() == -1 && this.a > 0) {
            r.setId(this.a);
        }
        this.l.b(this);
        return this;
    }
    
    CharSequence a(final aa aa) {
        if (aa != null && aa.prefersCondensedTitle()) {
            return this.getTitleCondensed();
        }
        return this.getTitle();
    }
    
    public void a(final ad m) {
        (this.m = m).setHeaderTitle(this.getTitle());
    }
    
    void a(final ContextMenu$ContextMenuInfo v) {
        this.v = v;
    }
    
    public void a(final boolean b) {
        final int p = this.p;
        int n;
        if (b) {
            n = 4;
        }
        else {
            n = 0;
        }
        this.p = (n | (p & 0xFFFFFFFB));
    }
    
    public boolean a() {
        if ((this.o == null || !this.o.onMenuItemClick((MenuItem)this)) && !this.l.a(this.l.p(), (MenuItem)this)) {
            if (this.n != null) {
                this.n.run();
                return true;
            }
            if (this.g != null) {
                try {
                    this.l.e().startActivity(this.g);
                    return true;
                }
                catch (ActivityNotFoundException ex) {
                    Log.e("MenuItemImpl", "Can't find activity to handle intent; ignoring", (Throwable)ex);
                }
            }
            if (this.s == null || !this.s.onPerformDefaultAction()) {
                return false;
            }
        }
        return true;
    }
    
    public int b() {
        return this.d;
    }
    
    public SupportMenuItem b(final int showAsAction) {
        this.setShowAsAction(showAsAction);
        return this;
    }
    
    void b(final boolean b) {
        final int p = this.p;
        final int p2 = this.p;
        int n;
        if (b) {
            n = 2;
        }
        else {
            n = 0;
        }
        this.p = (n | (p2 & 0xFFFFFFFD));
        if (p != this.p) {
            this.l.b(false);
        }
    }
    
    char c() {
        if (this.l.b()) {
            return this.i;
        }
        return this.h;
    }
    
    boolean c(final boolean b) {
        final boolean b2 = false;
        final int p = this.p;
        final int p2 = this.p;
        int n;
        if (b) {
            n = 0;
        }
        else {
            n = 8;
        }
        this.p = (n | (p2 & 0xFFFFFFF7));
        boolean b3 = b2;
        if (p != this.p) {
            b3 = true;
        }
        return b3;
    }
    
    @Override
    public boolean collapseActionView() {
        if ((this.q & 0x8) != 0x0) {
            if (this.r == null) {
                return true;
            }
            if (this.t == null || this.t.onMenuItemActionCollapse((MenuItem)this)) {
                return this.l.d(this);
            }
        }
        return false;
    }
    
    String d() {
        final char c = this.c();
        if (c == '\0') {
            return "";
        }
        final StringBuilder sb = new StringBuilder(android.support.v7.internal.view.menu.m.w);
        switch (c) {
            default: {
                sb.append(c);
                break;
            }
            case 10: {
                sb.append(android.support.v7.internal.view.menu.m.x);
                break;
            }
            case 8: {
                sb.append(android.support.v7.internal.view.menu.m.y);
                break;
            }
            case 32: {
                sb.append(android.support.v7.internal.view.menu.m.z);
                break;
            }
        }
        return sb.toString();
    }
    
    public void d(final boolean b) {
        if (b) {
            this.p |= 0x20;
            return;
        }
        this.p &= 0xFFFFFFDF;
    }
    
    public void e(final boolean u) {
        this.u = u;
        this.l.b(false);
    }
    
    boolean e() {
        return this.l.c() && this.c() != '\0';
    }
    
    @Override
    public boolean expandActionView() {
        return this.m() && (this.t == null || this.t.onMenuItemActionExpand((MenuItem)this)) && this.l.c(this);
    }
    
    public boolean f() {
        return (this.p & 0x4) != 0x0;
    }
    
    public void g() {
        this.l.b(this);
    }
    
    public android.view.ActionProvider getActionProvider() {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.getActionProvider()");
    }
    
    @Override
    public View getActionView() {
        if (this.r != null) {
            return this.r;
        }
        if (this.s != null) {
            return this.r = this.s.onCreateActionView((MenuItem)this);
        }
        return null;
    }
    
    public char getAlphabeticShortcut() {
        return this.i;
    }
    
    public int getGroupId() {
        return this.b;
    }
    
    public Drawable getIcon() {
        if (this.j != null) {
            return this.j;
        }
        if (this.k != 0) {
            final Drawable drawable = TintManager.getDrawable(this.l.e(), this.k);
            this.k = 0;
            return this.j = drawable;
        }
        return null;
    }
    
    public Intent getIntent() {
        return this.g;
    }
    
    @ViewDebug$CapturedViewProperty
    public int getItemId() {
        return this.a;
    }
    
    public ContextMenu$ContextMenuInfo getMenuInfo() {
        return this.v;
    }
    
    public char getNumericShortcut() {
        return this.h;
    }
    
    public int getOrder() {
        return this.c;
    }
    
    public SubMenu getSubMenu() {
        return (SubMenu)this.m;
    }
    
    @Override
    public ActionProvider getSupportActionProvider() {
        return this.s;
    }
    
    @ViewDebug$CapturedViewProperty
    public CharSequence getTitle() {
        return this.e;
    }
    
    public CharSequence getTitleCondensed() {
        CharSequence charSequence;
        if (this.f != null) {
            charSequence = this.f;
        }
        else {
            charSequence = this.e;
        }
        CharSequence string = charSequence;
        if (Build$VERSION.SDK_INT < 18 && (string = charSequence) != null) {
            string = charSequence;
            if (!(charSequence instanceof String)) {
                string = charSequence.toString();
            }
        }
        return string;
    }
    
    public boolean h() {
        return this.l.q();
    }
    
    public boolean hasSubMenu() {
        return this.m != null;
    }
    
    public boolean i() {
        return (this.p & 0x20) == 0x20;
    }
    
    @Override
    public boolean isActionViewExpanded() {
        return this.u;
    }
    
    public boolean isCheckable() {
        return (this.p & 0x1) == 0x1;
    }
    
    public boolean isChecked() {
        return (this.p & 0x2) == 0x2;
    }
    
    public boolean isEnabled() {
        return (this.p & 0x10) != 0x0;
    }
    
    public boolean isVisible() {
        if (this.s != null && this.s.overridesItemVisibility()) {
            if ((this.p & 0x8) != 0x0 || !this.s.isVisible()) {
                return false;
            }
        }
        else if ((this.p & 0x8) != 0x0) {
            return false;
        }
        return true;
    }
    
    public boolean j() {
        return (this.q & 0x1) == 0x1;
    }
    
    public boolean k() {
        return (this.q & 0x2) == 0x2;
    }
    
    public boolean l() {
        return (this.q & 0x4) == 0x4;
    }
    
    public boolean m() {
        boolean b = false;
        if ((this.q & 0x8) != 0x0) {
            if (this.r == null && this.s != null) {
                this.r = this.s.onCreateActionView((MenuItem)this);
            }
            b = b;
            if (this.r != null) {
                b = true;
            }
        }
        return b;
    }
    
    public MenuItem setActionProvider(final android.view.ActionProvider actionProvider) {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.setActionProvider()");
    }
    
    public MenuItem setAlphabeticShortcut(final char c) {
        if (this.i == c) {
            return (MenuItem)this;
        }
        this.i = Character.toLowerCase(c);
        this.l.b(false);
        return (MenuItem)this;
    }
    
    public MenuItem setCheckable(final boolean b) {
        final int p = this.p;
        final int p2 = this.p;
        boolean b2;
        if (b) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        this.p = ((b2 ? 1 : 0) | (p2 & 0xFFFFFFFE));
        if (p != this.p) {
            this.l.b(false);
        }
        return (MenuItem)this;
    }
    
    public MenuItem setChecked(final boolean b) {
        if ((this.p & 0x4) != 0x0) {
            this.l.a((MenuItem)this);
            return (MenuItem)this;
        }
        this.b(b);
        return (MenuItem)this;
    }
    
    public MenuItem setEnabled(final boolean b) {
        if (b) {
            this.p |= 0x10;
        }
        else {
            this.p &= 0xFFFFFFEF;
        }
        this.l.b(false);
        return (MenuItem)this;
    }
    
    public MenuItem setIcon(final int k) {
        this.j = null;
        this.k = k;
        this.l.b(false);
        return (MenuItem)this;
    }
    
    public MenuItem setIcon(final Drawable j) {
        this.k = 0;
        this.j = j;
        this.l.b(false);
        return (MenuItem)this;
    }
    
    public MenuItem setIntent(final Intent g) {
        this.g = g;
        return (MenuItem)this;
    }
    
    public MenuItem setNumericShortcut(final char h) {
        if (this.h == h) {
            return (MenuItem)this;
        }
        this.h = h;
        this.l.b(false);
        return (MenuItem)this;
    }
    
    public MenuItem setOnActionExpandListener(final MenuItem$OnActionExpandListener menuItem$OnActionExpandListener) {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.setOnActionExpandListener()");
    }
    
    public MenuItem setOnMenuItemClickListener(final MenuItem$OnMenuItemClickListener o) {
        this.o = o;
        return (MenuItem)this;
    }
    
    public MenuItem setShortcut(final char h, final char c) {
        this.h = h;
        this.i = Character.toLowerCase(c);
        this.l.b(false);
        return (MenuItem)this;
    }
    
    @Override
    public void setShowAsAction(final int q) {
        switch (q & 0x3) {
            default: {
                throw new IllegalArgumentException("SHOW_AS_ACTION_ALWAYS, SHOW_AS_ACTION_IF_ROOM, and SHOW_AS_ACTION_NEVER are mutually exclusive.");
            }
            case 0:
            case 1:
            case 2: {
                this.q = q;
                this.l.b(this);
            }
        }
    }
    
    @Override
    public SupportMenuItem setSupportActionProvider(final ActionProvider s) {
        if (this.s != null) {
            this.s.reset();
        }
        this.r = null;
        this.s = s;
        this.l.b(true);
        if (this.s != null) {
            this.s.setVisibilityListener(new n(this));
        }
        return this;
    }
    
    @Override
    public SupportMenuItem setSupportOnActionExpandListener(final MenuItemCompat$OnActionExpandListener t) {
        this.t = t;
        return this;
    }
    
    public MenuItem setTitle(final int n) {
        return this.setTitle(this.l.e().getString(n));
    }
    
    public MenuItem setTitle(final CharSequence charSequence) {
        this.e = charSequence;
        this.l.b(false);
        if (this.m != null) {
            this.m.setHeaderTitle(charSequence);
        }
        return (MenuItem)this;
    }
    
    public MenuItem setTitleCondensed(CharSequence e) {
        this.f = e;
        if (e == null) {
            e = this.e;
        }
        this.l.b(false);
        return (MenuItem)this;
    }
    
    public MenuItem setVisible(final boolean b) {
        if (this.c(b)) {
            this.l.a(this);
        }
        return (MenuItem)this;
    }
    
    @Override
    public String toString() {
        return this.e.toString();
    }
}
