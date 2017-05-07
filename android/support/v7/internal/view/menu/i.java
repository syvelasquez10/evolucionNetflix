// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.view.menu;

import java.util.Collection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.Intent;
import android.content.ComponentName;
import android.support.v4.view.ActionProvider;
import android.view.SubMenu;
import android.util.SparseArray;
import android.support.v4.view.MenuItemCompat;
import android.os.Bundle;
import android.view.KeyCharacterMap$KeyData;
import java.util.List;
import android.view.KeyEvent;
import android.support.v7.appcompat.R$bool;
import java.util.Iterator;
import android.support.v4.content.ContextCompat;
import android.view.MenuItem;
import java.lang.ref.WeakReference;
import java.util.concurrent.CopyOnWriteArrayList;
import android.view.ContextMenu$ContextMenuInfo;
import java.util.ArrayList;
import android.content.res.Resources;
import android.content.Context;
import android.view.View;
import android.graphics.drawable.Drawable;
import android.support.v4.internal.view.SupportMenu;

public class i implements SupportMenu
{
    private static final int[] d;
    CharSequence a;
    Drawable b;
    View c;
    private final Context e;
    private final Resources f;
    private boolean g;
    private boolean h;
    private j i;
    private ArrayList<m> j;
    private ArrayList<m> k;
    private boolean l;
    private ArrayList<m> m;
    private ArrayList<m> n;
    private boolean o;
    private int p;
    private ContextMenu$ContextMenuInfo q;
    private boolean r;
    private boolean s;
    private boolean t;
    private boolean u;
    private ArrayList<m> v;
    private CopyOnWriteArrayList<WeakReference<x>> w;
    private m x;
    
    static {
        d = new int[] { 1, 4, 5, 3, 2, 0 };
    }
    
    public i(final Context e) {
        this.p = 0;
        this.r = false;
        this.s = false;
        this.t = false;
        this.u = false;
        this.v = new ArrayList<m>();
        this.w = new CopyOnWriteArrayList<WeakReference<x>>();
        this.e = e;
        this.f = e.getResources();
        this.j = new ArrayList<m>();
        this.k = new ArrayList<m>();
        this.l = true;
        this.m = new ArrayList<m>();
        this.n = new ArrayList<m>();
        this.d(this.o = true);
    }
    
    private static int a(final ArrayList<m> list, final int n) {
        for (int i = list.size() - 1; i >= 0; --i) {
            if (list.get(i).b() <= n) {
                return i + 1;
            }
        }
        return 0;
    }
    
    private m a(final int n, final int n2, final int n3, final int n4, final CharSequence charSequence, final int n5) {
        return new m(this, n, n2, n3, n4, charSequence, n5);
    }
    
    private MenuItem a(final int n, final int n2, final int n3, final CharSequence charSequence) {
        final int d = d(n3);
        final m a = this.a(n, n2, n3, d, charSequence, this.p);
        if (this.q != null) {
            a.a(this.q);
        }
        this.j.add(a(this.j, d), a);
        this.b(true);
        return (MenuItem)a;
    }
    
    private void a(final int n, final CharSequence a, final int n2, final Drawable b, final View c) {
        final Resources d = this.d();
        if (c != null) {
            this.c = c;
            this.a = null;
            this.b = null;
        }
        else {
            if (n > 0) {
                this.a = d.getText(n);
            }
            else if (a != null) {
                this.a = a;
            }
            if (n2 > 0) {
                this.b = ContextCompat.getDrawable(this.e(), n2);
            }
            else if (b != null) {
                this.b = b;
            }
            this.c = null;
        }
        this.b(false);
    }
    
    private void a(final int n, final boolean b) {
        if (n >= 0 && n < this.j.size()) {
            this.j.remove(n);
            if (b) {
                this.b(true);
            }
        }
    }
    
    private boolean a(final ad ad, final x x) {
        boolean b = false;
        if (this.w.isEmpty()) {
            return false;
        }
        if (x != null) {
            b = x.onSubMenuSelected(ad);
        }
        for (final WeakReference<x> weakReference : this.w) {
            final x x2 = weakReference.get();
            if (x2 == null) {
                this.w.remove(weakReference);
            }
            else {
                if (b) {
                    continue;
                }
                b = x2.onSubMenuSelected(ad);
            }
        }
        return b;
    }
    
    private void c(final boolean b) {
        if (this.w.isEmpty()) {
            return;
        }
        this.g();
        for (final WeakReference<x> weakReference : this.w) {
            final x x = weakReference.get();
            if (x == null) {
                this.w.remove(weakReference);
            }
            else {
                x.updateMenuView(b);
            }
        }
        this.h();
    }
    
    private static int d(final int n) {
        final int n2 = (0xFFFF0000 & n) >> 16;
        if (n2 < 0 || n2 >= i.d.length) {
            throw new IllegalArgumentException("order does not contain a valid category.");
        }
        return i.d[n2] << 16 | (0xFFFF & n);
    }
    
    private void d(final boolean b) {
        final boolean b2 = true;
        this.h = (b && this.f.getConfiguration().keyboard != 1 && this.f.getBoolean(R$bool.abc_config_showMenuShortcutsWhenKeyboardPresent) && b2);
    }
    
    public int a(final int n, final int n2) {
        final int size = this.size();
        int i = n2;
        if (n2 < 0) {
            i = 0;
        }
        while (i < size) {
            if (this.j.get(i).getGroupId() == n) {
                return i;
            }
            ++i;
        }
        return -1;
    }
    
    public i a(final int p) {
        this.p = p;
        return this;
    }
    
    protected i a(final Drawable drawable) {
        this.a(0, null, 0, drawable, null);
        return this;
    }
    
    protected i a(final View view) {
        this.a(0, null, 0, null, view);
        return this;
    }
    
    protected i a(final CharSequence charSequence) {
        this.a(0, charSequence, 0, null, null);
        return this;
    }
    
    m a(final int n, final KeyEvent keyEvent) {
        final ArrayList<m> v = this.v;
        v.clear();
        this.a(v, n, keyEvent);
        m m;
        if (v.isEmpty()) {
            m = null;
        }
        else {
            final int metaState = keyEvent.getMetaState();
            final KeyCharacterMap$KeyData keyCharacterMap$KeyData = new KeyCharacterMap$KeyData();
            keyEvent.getKeyData(keyCharacterMap$KeyData);
            final int size = v.size();
            if (size == 1) {
                return v.get(0);
            }
            final boolean b = this.b();
            for (int i = 0; i < size; ++i) {
                final m j = v.get(i);
                char c;
                if (b) {
                    c = j.getAlphabeticShortcut();
                }
                else {
                    c = j.getNumericShortcut();
                }
                if (c == keyCharacterMap$KeyData.meta[0]) {
                    m = j;
                    if ((metaState & 0x2) == 0x0) {
                        return m;
                    }
                }
                if (c == keyCharacterMap$KeyData.meta[2]) {
                    m = j;
                    if ((metaState & 0x2) != 0x0) {
                        return m;
                    }
                }
                if (b && c == '\b') {
                    m = j;
                    if (n == 67) {
                        return m;
                    }
                }
            }
            return null;
        }
        return m;
    }
    
    protected String a() {
        return "android:menu:actionviewstates";
    }
    
    public void a(final Bundle bundle) {
        final int size = this.size();
        int i = 0;
        SparseArray sparseArray = null;
        while (i < size) {
            final MenuItem item = this.getItem(i);
            final View actionView = MenuItemCompat.getActionView(item);
            SparseArray sparseArray2 = sparseArray;
            if (actionView != null) {
                sparseArray2 = sparseArray;
                if (actionView.getId() != -1) {
                    SparseArray sparseArray3;
                    if ((sparseArray3 = sparseArray) == null) {
                        sparseArray3 = new SparseArray();
                    }
                    actionView.saveHierarchyState(sparseArray3);
                    sparseArray2 = sparseArray3;
                    if (MenuItemCompat.isActionViewExpanded(item)) {
                        bundle.putInt("android:menu:expandedactionview", item.getItemId());
                        sparseArray2 = sparseArray3;
                    }
                }
            }
            if (item.hasSubMenu()) {
                ((ad)item.getSubMenu()).a(bundle);
            }
            ++i;
            sparseArray = sparseArray2;
        }
        if (sparseArray != null) {
            bundle.putSparseParcelableArray(this.a(), sparseArray);
        }
    }
    
    public void a(final j i) {
        this.i = i;
    }
    
    void a(final m m) {
        this.b(this.l = true);
    }
    
    public void a(final x x) {
        this.a(x, this.e);
    }
    
    public void a(final x x, final Context context) {
        this.w.add(new WeakReference<x>(x));
        x.initForMenu(context, this);
        this.o = true;
    }
    
    void a(final MenuItem menuItem) {
        final int groupId = menuItem.getGroupId();
        for (int size = this.j.size(), i = 0; i < size; ++i) {
            final m m = this.j.get(i);
            if (m.getGroupId() == groupId && m.f() && m.isCheckable()) {
                m.b(m == menuItem);
            }
        }
    }
    
    void a(final List<m> list, final int n, final KeyEvent keyEvent) {
        final boolean b = this.b();
        final int metaState = keyEvent.getMetaState();
        final KeyCharacterMap$KeyData keyCharacterMap$KeyData = new KeyCharacterMap$KeyData();
        if (keyEvent.getKeyData(keyCharacterMap$KeyData) || n == 67) {
            for (int size = this.j.size(), i = 0; i < size; ++i) {
                final m m = this.j.get(i);
                if (m.hasSubMenu()) {
                    ((i)m.getSubMenu()).a(list, n, keyEvent);
                }
                char c;
                if (b) {
                    c = m.getAlphabeticShortcut();
                }
                else {
                    c = m.getNumericShortcut();
                }
                if ((metaState & 0x5) == 0x0 && c != '\0' && (c == keyCharacterMap$KeyData.meta[0] || c == keyCharacterMap$KeyData.meta[2] || (b && c == '\b' && n == 67)) && m.isEnabled()) {
                    list.add(m);
                }
            }
        }
    }
    
    public final void a(final boolean b) {
        if (this.u) {
            return;
        }
        this.u = true;
        for (final WeakReference<x> weakReference : this.w) {
            final x x = weakReference.get();
            if (x == null) {
                this.w.remove(weakReference);
            }
            else {
                x.onCloseMenu(this, b);
            }
        }
        this.u = false;
    }
    
    boolean a(final i i, final MenuItem menuItem) {
        return this.i != null && this.i.onMenuItemSelected(i, menuItem);
    }
    
    public boolean a(final MenuItem menuItem, final int n) {
        return this.a(menuItem, null, n);
    }
    
    public boolean a(final MenuItem menuItem, final x x, final int n) {
        final boolean b = false;
        final m m = (m)menuItem;
        boolean b2 = b;
        if (m != null) {
            if (!m.isEnabled()) {
                b2 = b;
            }
            else {
                final boolean a = m.a();
                final ActionProvider l = m.l();
                final boolean b3 = l != null && l.hasSubMenu();
                if (m.m()) {
                    final boolean b4 = b2 = (m.expandActionView() | a);
                    if (b4) {
                        this.a(true);
                        return b4;
                    }
                }
                else {
                    if (!m.hasSubMenu() && !b3) {
                        if ((n & 0x1) == 0x0) {
                            this.a(true);
                        }
                        return a;
                    }
                    this.a(false);
                    if (!m.hasSubMenu()) {
                        m.a(new ad(this.e(), this, m));
                    }
                    final ad ad = (ad)m.getSubMenu();
                    if (b3) {
                        l.onPrepareSubMenu((SubMenu)ad);
                    }
                    final boolean b5 = b2 = (this.a(ad, x) | a);
                    if (!b5) {
                        this.a(true);
                        return b5;
                    }
                }
            }
        }
        return b2;
    }
    
    public MenuItem add(final int n) {
        return this.a(0, 0, 0, this.f.getString(n));
    }
    
    public MenuItem add(final int n, final int n2, final int n3, final int n4) {
        return this.a(n, n2, n3, this.f.getString(n4));
    }
    
    public MenuItem add(final int n, final int n2, final int n3, final CharSequence charSequence) {
        return this.a(n, n2, n3, charSequence);
    }
    
    public MenuItem add(final CharSequence charSequence) {
        return this.a(0, 0, 0, charSequence);
    }
    
    public int addIntentOptions(final int n, final int n2, final int n3, final ComponentName componentName, final Intent[] array, final Intent intent, int i, final MenuItem[] array2) {
        final PackageManager packageManager = this.e.getPackageManager();
        final List queryIntentActivityOptions = packageManager.queryIntentActivityOptions(componentName, array, intent, 0);
        int size;
        if (queryIntentActivityOptions != null) {
            size = queryIntentActivityOptions.size();
        }
        else {
            size = 0;
        }
        if ((i & 0x1) == 0x0) {
            this.removeGroup(n);
        }
        ResolveInfo resolveInfo;
        Intent intent2;
        Intent intent3;
        MenuItem setIntent;
        for (i = 0; i < size; ++i) {
            resolveInfo = queryIntentActivityOptions.get(i);
            if (resolveInfo.specificIndex < 0) {
                intent2 = intent;
            }
            else {
                intent2 = array[resolveInfo.specificIndex];
            }
            intent3 = new Intent(intent2);
            intent3.setComponent(new ComponentName(resolveInfo.activityInfo.applicationInfo.packageName, resolveInfo.activityInfo.name));
            setIntent = this.add(n, n2, n3, resolveInfo.loadLabel(packageManager)).setIcon(resolveInfo.loadIcon(packageManager)).setIntent(intent3);
            if (array2 != null && resolveInfo.specificIndex >= 0) {
                array2[resolveInfo.specificIndex] = setIntent;
            }
        }
        return size;
    }
    
    public SubMenu addSubMenu(final int n) {
        return this.addSubMenu(0, 0, 0, this.f.getString(n));
    }
    
    public SubMenu addSubMenu(final int n, final int n2, final int n3, final int n4) {
        return this.addSubMenu(n, n2, n3, this.f.getString(n4));
    }
    
    public SubMenu addSubMenu(final int n, final int n2, final int n3, final CharSequence charSequence) {
        final m m = (m)this.a(n, n2, n3, charSequence);
        final ad ad = new ad(this.e, this, m);
        m.a(ad);
        return (SubMenu)ad;
    }
    
    public SubMenu addSubMenu(final CharSequence charSequence) {
        return this.addSubMenu(0, 0, 0, charSequence);
    }
    
    public int b(final int n) {
        for (int size = this.size(), i = 0; i < size; ++i) {
            if (this.j.get(i).getItemId() == n) {
                return i;
            }
        }
        return -1;
    }
    
    public void b(final Bundle bundle) {
        if (bundle != null) {
            final SparseArray sparseParcelableArray = bundle.getSparseParcelableArray(this.a());
            for (int size = this.size(), i = 0; i < size; ++i) {
                final MenuItem item = this.getItem(i);
                final View actionView = MenuItemCompat.getActionView(item);
                if (actionView != null && actionView.getId() != -1) {
                    actionView.restoreHierarchyState(sparseParcelableArray);
                }
                if (item.hasSubMenu()) {
                    ((ad)item.getSubMenu()).b(bundle);
                }
            }
            final int int1 = bundle.getInt("android:menu:expandedactionview");
            if (int1 > 0) {
                final MenuItem item2 = this.findItem(int1);
                if (item2 != null) {
                    MenuItemCompat.expandActionView(item2);
                }
            }
        }
    }
    
    void b(final m m) {
        this.b(this.o = true);
    }
    
    public void b(final x x) {
        for (final WeakReference<x> weakReference : this.w) {
            final x x2 = weakReference.get();
            if (x2 == null || x2 == x) {
                this.w.remove(weakReference);
            }
        }
    }
    
    public void b(final boolean b) {
        if (!this.r) {
            if (b) {
                this.l = true;
                this.o = true;
            }
            this.c(b);
            return;
        }
        this.s = true;
    }
    
    boolean b() {
        return this.g;
    }
    
    public int c(final int n) {
        return this.a(n, 0);
    }
    
    public boolean c() {
        return this.h;
    }
    
    public boolean c(final m x) {
        boolean b = false;
        if (!this.w.isEmpty()) {
            this.g();
            final Iterator<WeakReference<x>> iterator = this.w.iterator();
            boolean expandItemActionView = false;
            while (true) {
                while (iterator.hasNext()) {
                    final WeakReference<x> weakReference = iterator.next();
                    final x x2 = weakReference.get();
                    if (x2 == null) {
                        this.w.remove(weakReference);
                    }
                    else {
                        final boolean b2 = expandItemActionView = x2.expandItemActionView(this, x);
                        if (!b2) {
                            continue;
                        }
                        expandItemActionView = b2;
                        this.h();
                        b = expandItemActionView;
                        if (expandItemActionView) {
                            this.x = x;
                            return expandItemActionView;
                        }
                        return b;
                    }
                }
                continue;
            }
        }
        return b;
    }
    
    public void clear() {
        if (this.x != null) {
            this.d(this.x);
        }
        this.j.clear();
        this.b(true);
    }
    
    public void clearHeader() {
        this.b = null;
        this.a = null;
        this.c = null;
        this.b(false);
    }
    
    public void close() {
        this.a(true);
    }
    
    Resources d() {
        return this.f;
    }
    
    public boolean d(final m m) {
        boolean b = false;
        if (!this.w.isEmpty()) {
            if (this.x == m) {
                this.g();
                final Iterator<WeakReference<x>> iterator = this.w.iterator();
                boolean collapseItemActionView = false;
                while (true) {
                    while (iterator.hasNext()) {
                        final WeakReference<x> weakReference = iterator.next();
                        final x x = weakReference.get();
                        if (x == null) {
                            this.w.remove(weakReference);
                        }
                        else {
                            final boolean b2 = collapseItemActionView = x.collapseItemActionView(this, m);
                            if (!b2) {
                                continue;
                            }
                            collapseItemActionView = b2;
                            this.h();
                            b = collapseItemActionView;
                            if (collapseItemActionView) {
                                this.x = null;
                                return collapseItemActionView;
                            }
                            return b;
                        }
                    }
                    continue;
                }
            }
            b = b;
        }
        return b;
    }
    
    public Context e() {
        return this.e;
    }
    
    public void f() {
        if (this.i != null) {
            this.i.onMenuModeChange(this);
        }
    }
    
    public MenuItem findItem(final int n) {
        for (int size = this.size(), i = 0; i < size; ++i) {
            Object item = this.j.get(i);
            if (((m)item).getItemId() == n || (((m)item).hasSubMenu() && (item = ((m)item).getSubMenu().findItem(n)) != null)) {
                return (MenuItem)item;
            }
        }
        return null;
    }
    
    public void g() {
        if (!this.r) {
            this.r = true;
            this.s = false;
        }
    }
    
    public MenuItem getItem(final int n) {
        return (MenuItem)this.j.get(n);
    }
    
    public void h() {
        this.r = false;
        if (this.s) {
            this.s = false;
            this.b(true);
        }
    }
    
    public boolean hasVisibleItems() {
        for (int size = this.size(), i = 0; i < size; ++i) {
            if (this.j.get(i).isVisible()) {
                return true;
            }
        }
        return false;
    }
    
    public ArrayList<m> i() {
        if (!this.l) {
            return this.k;
        }
        this.k.clear();
        for (int size = this.j.size(), i = 0; i < size; ++i) {
            final m m = this.j.get(i);
            if (m.isVisible()) {
                this.k.add(m);
            }
        }
        this.l = false;
        this.o = true;
        return this.k;
    }
    
    public boolean isShortcutKey(final int n, final KeyEvent keyEvent) {
        return this.a(n, keyEvent) != null;
    }
    
    public void j() {
        final ArrayList<m> i = this.i();
        if (!this.o) {
            return;
        }
        final Iterator<WeakReference<x>> iterator = this.w.iterator();
        boolean b = false;
        while (iterator.hasNext()) {
            final WeakReference<x> weakReference = iterator.next();
            final x x = weakReference.get();
            if (x == null) {
                this.w.remove(weakReference);
            }
            else {
                b |= x.flagActionItems();
            }
        }
        if (b) {
            this.m.clear();
            this.n.clear();
            for (int size = i.size(), j = 0; j < size; ++j) {
                final m m = i.get(j);
                if (m.h()) {
                    this.m.add(m);
                }
                else {
                    this.n.add(m);
                }
            }
        }
        else {
            this.m.clear();
            this.n.clear();
            this.n.addAll(this.i());
        }
        this.o = false;
    }
    
    public ArrayList<m> k() {
        this.j();
        return this.m;
    }
    
    public ArrayList<m> l() {
        this.j();
        return this.n;
    }
    
    public CharSequence m() {
        return this.a;
    }
    
    public Drawable n() {
        return this.b;
    }
    
    public View o() {
        return this.c;
    }
    
    public i p() {
        return this;
    }
    
    public boolean performIdentifierAction(final int n, final int n2) {
        return this.a(this.findItem(n), n2);
    }
    
    public boolean performShortcut(final int n, final KeyEvent keyEvent, final int n2) {
        final m a = this.a(n, keyEvent);
        boolean a2 = false;
        if (a != null) {
            a2 = this.a((MenuItem)a, n2);
        }
        if ((n2 & 0x2) != 0x0) {
            this.a(true);
        }
        return a2;
    }
    
    boolean q() {
        return this.t;
    }
    
    public m r() {
        return this.x;
    }
    
    public void removeGroup(final int n) {
        final int c = this.c(n);
        if (c >= 0) {
            for (int size = this.j.size(), n2 = 0; n2 < size - c && this.j.get(c).getGroupId() == n; ++n2) {
                this.a(c, false);
            }
            this.b(true);
        }
    }
    
    public void removeItem(final int n) {
        this.a(this.b(n), true);
    }
    
    public void setGroupCheckable(final int n, final boolean checkable, final boolean b) {
        for (int size = this.j.size(), i = 0; i < size; ++i) {
            final m m = this.j.get(i);
            if (m.getGroupId() == n) {
                m.a(b);
                m.setCheckable(checkable);
            }
        }
    }
    
    public void setGroupEnabled(final int n, final boolean enabled) {
        for (int size = this.j.size(), i = 0; i < size; ++i) {
            final m m = this.j.get(i);
            if (m.getGroupId() == n) {
                m.setEnabled(enabled);
            }
        }
    }
    
    public void setGroupVisible(final int n, final boolean b) {
        final int size = this.j.size();
        int i = 0;
        boolean b2 = false;
        while (i < size) {
            final m m = this.j.get(i);
            if (m.getGroupId() == n && m.c(b)) {
                b2 = true;
            }
            ++i;
        }
        if (b2) {
            this.b(true);
        }
    }
    
    public void setQwertyMode(final boolean g) {
        this.g = g;
        this.b(false);
    }
    
    public int size() {
        return this.j.size();
    }
}
