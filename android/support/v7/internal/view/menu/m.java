// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.view.menu;

import android.support.v4.view.ActionProvider$VisibilityListener;
import android.view.MenuItem$OnActionExpandListener;
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
import android.view.MenuItem$OnMenuItemClickListener;
import android.graphics.drawable.Drawable;
import android.content.Intent;
import android.view.ContextMenu$ContextMenuInfo;
import android.support.v4.view.MenuItemCompat$OnActionExpandListener;
import android.support.v4.view.ActionProvider;
import android.view.View;
import android.support.v4.internal.view.SupportMenuItem;

public final class m implements SupportMenuItem
{
    private static String F;
    private static String G;
    private static String H;
    private static String I;
    static final int a = 0;
    private static final String b = "MenuItemImpl";
    private static final int c = 3;
    private static final int t = 1;
    private static final int u = 2;
    private static final int v = 4;
    private static final int w = 8;
    private static final int x = 16;
    private static final int y = 32;
    private View A;
    private ActionProvider B;
    private MenuItemCompat$OnActionExpandListener C;
    private boolean D;
    private ContextMenu$ContextMenuInfo E;
    private final int d;
    private final int e;
    private final int f;
    private final int g;
    private CharSequence h;
    private CharSequence i;
    private Intent j;
    private char k;
    private char l;
    private Drawable m;
    private int n;
    private i o;
    private ae p;
    private Runnable q;
    private MenuItem$OnMenuItemClickListener r;
    private int s;
    private int z;
    
    m(final i o, final int e, final int d, final int f, final int g, final CharSequence h, final int z) {
        this.n = 0;
        this.s = 16;
        this.z = 0;
        this.D = false;
        this.o = o;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
        this.h = h;
        this.z = z;
    }
    
    public SupportMenuItem a(final int n) {
        final Context f = this.o.f();
        this.a(LayoutInflater.from(f).inflate(n, (ViewGroup)new LinearLayout(f), false));
        return this;
    }
    
    public SupportMenuItem a(final View a) {
        this.A = a;
        this.B = null;
        if (a != null && a.getId() == -1 && this.d > 0) {
            a.setId(this.d);
        }
        this.o.b(this);
        return this;
    }
    
    public MenuItem a(final Runnable q) {
        this.q = q;
        return (MenuItem)this;
    }
    
    CharSequence a(final ab ab) {
        if (ab != null && ab.b()) {
            return this.getTitleCondensed();
        }
        return this.getTitle();
    }
    
    void a(final ae p) {
        (this.p = p).setHeaderTitle(this.getTitle());
    }
    
    void a(final ContextMenu$ContextMenuInfo e) {
        this.E = e;
    }
    
    public void a(final boolean b) {
        final int s = this.s;
        int n;
        if (b) {
            n = 4;
        }
        else {
            n = 0;
        }
        this.s = (n | (s & 0xFFFFFFFB));
    }
    
    public boolean a() {
        if ((this.r == null || !this.r.onMenuItemClick((MenuItem)this)) && !this.o.a(this.o.q(), (MenuItem)this)) {
            if (this.q != null) {
                this.q.run();
                return true;
            }
            if (this.j != null) {
                try {
                    this.o.f().startActivity(this.j);
                    return true;
                }
                catch (ActivityNotFoundException ex) {
                    Log.e("MenuItemImpl", "Can't find activity to handle intent; ignoring", (Throwable)ex);
                }
            }
            if (this.B == null || !this.B.onPerformDefaultAction()) {
                return false;
            }
        }
        return true;
    }
    
    public int b() {
        return this.g;
    }
    
    public SupportMenuItem b(final int showAsAction) {
        this.setShowAsAction(showAsAction);
        return this;
    }
    
    void b(final boolean b) {
        final int s = this.s;
        final int s2 = this.s;
        int n;
        if (b) {
            n = 2;
        }
        else {
            n = 0;
        }
        this.s = (n | (s2 & 0xFFFFFFFD));
        if (s != this.s) {
            this.o.c(false);
        }
    }
    
    Runnable c() {
        return this.q;
    }
    
    boolean c(final boolean b) {
        final boolean b2 = false;
        final int s = this.s;
        final int s2 = this.s;
        int n;
        if (b) {
            n = 0;
        }
        else {
            n = 8;
        }
        this.s = (n | (s2 & 0xFFFFFFF7));
        boolean b3 = b2;
        if (s != this.s) {
            b3 = true;
        }
        return b3;
    }
    
    @Override
    public boolean collapseActionView() {
        if ((this.z & 0x8) != 0x0) {
            if (this.A == null) {
                return true;
            }
            if (this.C == null || this.C.onMenuItemActionCollapse((MenuItem)this)) {
                return this.o.d(this);
            }
        }
        return false;
    }
    
    char d() {
        if (this.o.c()) {
            return this.l;
        }
        return this.k;
    }
    
    public void d(final boolean b) {
        if (b) {
            this.s |= 0x20;
            return;
        }
        this.s &= 0xFFFFFFDF;
    }
    
    String e() {
        final char d = this.d();
        if (d == '\0') {
            return "";
        }
        final StringBuilder sb = new StringBuilder(android.support.v7.internal.view.menu.m.F);
        switch (d) {
            default: {
                sb.append(d);
                break;
            }
            case 10: {
                sb.append(android.support.v7.internal.view.menu.m.G);
                break;
            }
            case 8: {
                sb.append(android.support.v7.internal.view.menu.m.H);
                break;
            }
            case 32: {
                sb.append(android.support.v7.internal.view.menu.m.I);
                break;
            }
        }
        return sb.toString();
    }
    
    public void e(final boolean d) {
        this.D = d;
        this.o.c(false);
    }
    
    @Override
    public boolean expandActionView() {
        return this.n() && (this.C == null || this.C.onMenuItemActionExpand((MenuItem)this)) && this.o.c(this);
    }
    
    boolean f() {
        return this.o.d() && this.d() != '\0';
    }
    
    public boolean g() {
        return (this.s & 0x4) != 0x0;
    }
    
    public android.view.ActionProvider getActionProvider() {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.getActionProvider()");
    }
    
    @Override
    public View getActionView() {
        if (this.A != null) {
            return this.A;
        }
        if (this.B != null) {
            return this.A = this.B.onCreateActionView((MenuItem)this);
        }
        return null;
    }
    
    public char getAlphabeticShortcut() {
        return this.l;
    }
    
    public int getGroupId() {
        return this.e;
    }
    
    public Drawable getIcon() {
        if (this.m != null) {
            return this.m;
        }
        if (this.n != 0) {
            final Drawable drawable = TintManager.getDrawable(this.o.f(), this.n);
            this.n = 0;
            return this.m = drawable;
        }
        return null;
    }
    
    public Intent getIntent() {
        return this.j;
    }
    
    @ViewDebug$CapturedViewProperty
    public int getItemId() {
        return this.d;
    }
    
    public ContextMenu$ContextMenuInfo getMenuInfo() {
        return this.E;
    }
    
    public char getNumericShortcut() {
        return this.k;
    }
    
    public int getOrder() {
        return this.f;
    }
    
    public SubMenu getSubMenu() {
        return (SubMenu)this.p;
    }
    
    @Override
    public ActionProvider getSupportActionProvider() {
        return this.B;
    }
    
    @ViewDebug$CapturedViewProperty
    public CharSequence getTitle() {
        return this.h;
    }
    
    public CharSequence getTitleCondensed() {
        if (this.i != null) {
            return this.i;
        }
        return this.h;
    }
    
    public void h() {
        this.o.b(this);
    }
    
    public boolean hasSubMenu() {
        return this.p != null;
    }
    
    public boolean i() {
        return this.o.r();
    }
    
    @Override
    public boolean isActionViewExpanded() {
        return this.D;
    }
    
    public boolean isCheckable() {
        return (this.s & 0x1) == 0x1;
    }
    
    public boolean isChecked() {
        return (this.s & 0x2) == 0x2;
    }
    
    public boolean isEnabled() {
        return (this.s & 0x10) != 0x0;
    }
    
    public boolean isVisible() {
        if (this.B != null && this.B.overridesItemVisibility()) {
            if ((this.s & 0x8) != 0x0 || !this.B.isVisible()) {
                return false;
            }
        }
        else if ((this.s & 0x8) != 0x0) {
            return false;
        }
        return true;
    }
    
    public boolean j() {
        return (this.s & 0x20) == 0x20;
    }
    
    public boolean k() {
        return (this.z & 0x1) == 0x1;
    }
    
    public boolean l() {
        return (this.z & 0x2) == 0x2;
    }
    
    public boolean m() {
        return (this.z & 0x4) == 0x4;
    }
    
    public boolean n() {
        boolean b = false;
        if ((this.z & 0x8) != 0x0) {
            if (this.A == null && this.B != null) {
                this.A = this.B.onCreateActionView((MenuItem)this);
            }
            b = b;
            if (this.A != null) {
                b = true;
            }
        }
        return b;
    }
    
    public MenuItem setActionProvider(final android.view.ActionProvider actionProvider) {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.setActionProvider()");
    }
    
    public MenuItem setAlphabeticShortcut(final char c) {
        if (this.l == c) {
            return (MenuItem)this;
        }
        this.l = Character.toLowerCase(c);
        this.o.c(false);
        return (MenuItem)this;
    }
    
    public MenuItem setCheckable(final boolean b) {
        final int s = this.s;
        final int s2 = this.s;
        boolean b2;
        if (b) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        this.s = ((b2 ? 1 : 0) | (s2 & 0xFFFFFFFE));
        if (s != this.s) {
            this.o.c(false);
        }
        return (MenuItem)this;
    }
    
    public MenuItem setChecked(final boolean b) {
        if ((this.s & 0x4) != 0x0) {
            this.o.a((MenuItem)this);
            return (MenuItem)this;
        }
        this.b(b);
        return (MenuItem)this;
    }
    
    public MenuItem setEnabled(final boolean b) {
        if (b) {
            this.s |= 0x10;
        }
        else {
            this.s &= 0xFFFFFFEF;
        }
        this.o.c(false);
        return (MenuItem)this;
    }
    
    public MenuItem setIcon(final int n) {
        this.m = null;
        this.n = n;
        this.o.c(false);
        return (MenuItem)this;
    }
    
    public MenuItem setIcon(final Drawable m) {
        this.n = 0;
        this.m = m;
        this.o.c(false);
        return (MenuItem)this;
    }
    
    public MenuItem setIntent(final Intent j) {
        this.j = j;
        return (MenuItem)this;
    }
    
    public MenuItem setNumericShortcut(final char k) {
        if (this.k == k) {
            return (MenuItem)this;
        }
        this.k = k;
        this.o.c(false);
        return (MenuItem)this;
    }
    
    public MenuItem setOnActionExpandListener(final MenuItem$OnActionExpandListener menuItem$OnActionExpandListener) {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.setOnActionExpandListener()");
    }
    
    public MenuItem setOnMenuItemClickListener(final MenuItem$OnMenuItemClickListener r) {
        this.r = r;
        return (MenuItem)this;
    }
    
    public MenuItem setShortcut(final char k, final char c) {
        this.k = k;
        this.l = Character.toLowerCase(c);
        this.o.c(false);
        return (MenuItem)this;
    }
    
    @Override
    public void setShowAsAction(final int z) {
        switch (z & 0x3) {
            default: {
                throw new IllegalArgumentException("SHOW_AS_ACTION_ALWAYS, SHOW_AS_ACTION_IF_ROOM, and SHOW_AS_ACTION_NEVER are mutually exclusive.");
            }
            case 0:
            case 1:
            case 2: {
                this.z = z;
                this.o.b(this);
            }
        }
    }
    
    @Override
    public SupportMenuItem setSupportActionProvider(final ActionProvider b) {
        if (this.B != null) {
            this.B.setVisibilityListener(null);
        }
        this.A = null;
        this.B = b;
        this.o.c(true);
        if (this.B != null) {
            this.B.setVisibilityListener(new n(this));
        }
        return this;
    }
    
    @Override
    public SupportMenuItem setSupportOnActionExpandListener(final MenuItemCompat$OnActionExpandListener c) {
        this.C = c;
        return this;
    }
    
    public MenuItem setTitle(final int n) {
        return this.setTitle(this.o.f().getString(n));
    }
    
    public MenuItem setTitle(final CharSequence charSequence) {
        this.h = charSequence;
        this.o.c(false);
        if (this.p != null) {
            this.p.setHeaderTitle(charSequence);
        }
        return (MenuItem)this;
    }
    
    public MenuItem setTitleCondensed(CharSequence h) {
        this.i = h;
        if (h == null) {
            h = this.h;
        }
        this.o.c(false);
        return (MenuItem)this;
    }
    
    public MenuItem setVisible(final boolean b) {
        if (this.c(b)) {
            this.o.a(this);
        }
        return (MenuItem)this;
    }
    
    @Override
    public String toString() {
        return this.h.toString();
    }
}
