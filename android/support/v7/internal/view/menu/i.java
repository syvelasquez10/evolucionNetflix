// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.view.menu;

import java.util.Collection;
import android.support.v4.view.MenuItemCompat;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.Intent;
import android.content.ComponentName;
import android.support.v4.view.ActionProvider;
import android.view.SubMenu;
import android.view.KeyCharacterMap$KeyData;
import java.util.List;
import android.view.KeyEvent;
import android.support.v7.appcompat.R$bool;
import android.os.Bundle;
import java.util.Iterator;
import android.support.v4.content.ContextCompat;
import android.view.MenuItem;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.ContextMenu$ContextMenuInfo;
import android.content.res.Resources;
import android.content.Context;
import android.view.View;
import android.graphics.drawable.Drawable;
import java.lang.ref.WeakReference;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.ArrayList;
import android.support.v4.internal.view.SupportMenu;

public class i implements SupportMenu
{
    private static final String d = "MenuBuilder";
    private static final String e = "android:menu:presenters";
    private static final String f = "android:menu:actionviewstates";
    private static final String g = "android:menu:expandedactionview";
    private static final int[] h;
    private ArrayList<m> A;
    private CopyOnWriteArrayList<WeakReference<y>> B;
    private m C;
    CharSequence a;
    Drawable b;
    View c;
    private final Context i;
    private final Resources j;
    private boolean k;
    private boolean l;
    private j m;
    private ArrayList<m> n;
    private ArrayList<m> o;
    private boolean p;
    private ArrayList<m> q;
    private ArrayList<m> r;
    private boolean s;
    private int t;
    private ContextMenu$ContextMenuInfo u;
    private SparseArray<Parcelable> v;
    private boolean w;
    private boolean x;
    private boolean y;
    private boolean z;
    
    static {
        h = new int[] { 1, 4, 5, 3, 2, 0 };
    }
    
    public i(final Context i) {
        this.t = 0;
        this.w = false;
        this.x = false;
        this.y = false;
        this.z = false;
        this.A = new ArrayList<m>();
        this.B = new CopyOnWriteArrayList<WeakReference<y>>();
        this.i = i;
        this.j = i.getResources();
        this.n = new ArrayList<m>();
        this.o = new ArrayList<m>();
        this.p = true;
        this.q = new ArrayList<m>();
        this.r = new ArrayList<m>();
        this.f(this.s = true);
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
        final int g = g(n3);
        final m a = this.a(n, n2, n3, g, charSequence, this.t);
        if (this.u != null) {
            a.a(this.u);
        }
        this.n.add(a(this.n, g), a);
        this.c(true);
        return (MenuItem)a;
    }
    
    private void a(final int n, final CharSequence a, final int n2, final Drawable b, final View c) {
        final Resources e = this.e();
        if (c != null) {
            this.c = c;
            this.a = null;
            this.b = null;
        }
        else {
            if (n > 0) {
                this.a = e.getText(n);
            }
            else if (a != null) {
                this.a = a;
            }
            if (n2 > 0) {
                this.b = ContextCompat.getDrawable(this.f(), n2);
            }
            else if (b != null) {
                this.b = b;
            }
            this.c = null;
        }
        this.c(false);
    }
    
    private void a(final int n, final boolean b) {
        if (n >= 0 && n < this.n.size()) {
            this.n.remove(n);
            if (b) {
                this.c(true);
            }
        }
    }
    
    private boolean a(final ae ae, final y y) {
        boolean b = false;
        if (this.B.isEmpty()) {
            return false;
        }
        if (y != null) {
            b = y.onSubMenuSelected(ae);
        }
        for (final WeakReference<y> weakReference : this.B) {
            final y y2 = weakReference.get();
            if (y2 == null) {
                this.B.remove(weakReference);
            }
            else {
                if (b) {
                    continue;
                }
                b = y2.onSubMenuSelected(ae);
            }
        }
        return b;
    }
    
    private void e(final Bundle bundle) {
        if (this.B.isEmpty()) {
            return;
        }
        final SparseArray sparseArray = new SparseArray();
        for (final WeakReference<y> weakReference : this.B) {
            final y y = weakReference.get();
            if (y == null) {
                this.B.remove(weakReference);
            }
            else {
                final int id = y.getId();
                if (id <= 0) {
                    continue;
                }
                final Parcelable onSaveInstanceState = y.onSaveInstanceState();
                if (onSaveInstanceState == null) {
                    continue;
                }
                sparseArray.put(id, (Object)onSaveInstanceState);
            }
        }
        bundle.putSparseParcelableArray("android:menu:presenters", sparseArray);
    }
    
    private void e(final boolean b) {
        if (this.B.isEmpty()) {
            return;
        }
        this.h();
        for (final WeakReference<y> weakReference : this.B) {
            final y y = weakReference.get();
            if (y == null) {
                this.B.remove(weakReference);
            }
            else {
                y.updateMenuView(b);
            }
        }
        this.i();
    }
    
    private void f(final Bundle bundle) {
        final SparseArray sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:presenters");
        if (sparseParcelableArray != null && !this.B.isEmpty()) {
            for (final WeakReference<y> weakReference : this.B) {
                final y y = weakReference.get();
                if (y == null) {
                    this.B.remove(weakReference);
                }
                else {
                    final int id = y.getId();
                    if (id <= 0) {
                        continue;
                    }
                    final Parcelable parcelable = (Parcelable)sparseParcelableArray.get(id);
                    if (parcelable == null) {
                        continue;
                    }
                    y.onRestoreInstanceState(parcelable);
                }
            }
        }
    }
    
    private void f(final boolean b) {
        final boolean b2 = true;
        this.l = (b && this.j.getConfiguration().keyboard != 1 && this.j.getBoolean(R$bool.abc_config_showMenuShortcutsWhenKeyboardPresent) && b2);
    }
    
    private static int g(final int n) {
        final int n2 = (0xFFFF0000 & n) >> 16;
        if (n2 < 0 || n2 >= i.h.length) {
            throw new IllegalArgumentException("order does not contain a valid category.");
        }
        return i.h[n2] << 16 | (0xFFFF & n);
    }
    
    public int a(final int n, final int n2) {
        final int size = this.size();
        int i = n2;
        if (n2 < 0) {
            i = 0;
        }
        while (i < size) {
            if (this.n.get(i).getGroupId() == n) {
                return i;
            }
            ++i;
        }
        return -1;
    }
    
    public i a(final int t) {
        this.t = t;
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
        final ArrayList<m> a = this.A;
        a.clear();
        this.a(a, n, keyEvent);
        m m;
        if (a.isEmpty()) {
            m = null;
        }
        else {
            final int metaState = keyEvent.getMetaState();
            final KeyCharacterMap$KeyData keyCharacterMap$KeyData = new KeyCharacterMap$KeyData();
            keyEvent.getKeyData(keyCharacterMap$KeyData);
            final int size = a.size();
            if (size == 1) {
                return a.get(0);
            }
            final boolean c = this.c();
            for (int i = 0; i < size; ++i) {
                final m j = a.get(i);
                char c2;
                if (c) {
                    c2 = j.getAlphabeticShortcut();
                }
                else {
                    c2 = j.getNumericShortcut();
                }
                if (c2 == keyCharacterMap$KeyData.meta[0]) {
                    m = j;
                    if ((metaState & 0x2) == 0x0) {
                        return m;
                    }
                }
                if (c2 == keyCharacterMap$KeyData.meta[2]) {
                    m = j;
                    if ((metaState & 0x2) != 0x0) {
                        return m;
                    }
                }
                if (c && c2 == '\b') {
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
        this.e(bundle);
    }
    
    public void a(final j m) {
        this.m = m;
    }
    
    void a(final m m) {
        this.c(this.p = true);
    }
    
    public void a(final y y) {
        this.a(y, this.i);
    }
    
    public void a(final y y, final Context context) {
        this.B.add(new WeakReference<y>(y));
        y.initForMenu(context, this);
        this.s = true;
    }
    
    public void a(final ContextMenu$ContextMenuInfo u) {
        this.u = u;
    }
    
    void a(final MenuItem menuItem) {
        final int groupId = menuItem.getGroupId();
        for (int size = this.n.size(), i = 0; i < size; ++i) {
            final m m = this.n.get(i);
            if (m.getGroupId() == groupId && m.g() && m.isCheckable()) {
                m.b(m == menuItem);
            }
        }
    }
    
    void a(final List<m> list, final int n, final KeyEvent keyEvent) {
        final boolean c = this.c();
        final int metaState = keyEvent.getMetaState();
        final KeyCharacterMap$KeyData keyCharacterMap$KeyData = new KeyCharacterMap$KeyData();
        if (keyEvent.getKeyData(keyCharacterMap$KeyData) || n == 67) {
            for (int size = this.n.size(), i = 0; i < size; ++i) {
                final m m = this.n.get(i);
                if (m.hasSubMenu()) {
                    ((i)m.getSubMenu()).a(list, n, keyEvent);
                }
                char c2;
                if (c) {
                    c2 = m.getAlphabeticShortcut();
                }
                else {
                    c2 = m.getNumericShortcut();
                }
                if ((metaState & 0x5) == 0x0 && c2 != '\0' && (c2 == keyCharacterMap$KeyData.meta[0] || c2 == keyCharacterMap$KeyData.meta[2] || (c && c2 == '\b' && n == 67)) && m.isEnabled()) {
                    list.add(m);
                }
            }
        }
    }
    
    public void a(final boolean b) {
        if (this.l == b) {
            return;
        }
        this.f(b);
        this.c(false);
    }
    
    boolean a(final i i, final MenuItem menuItem) {
        return this.m != null && this.m.onMenuItemSelected(i, menuItem);
    }
    
    public boolean a(final MenuItem menuItem, final int n) {
        return this.a(menuItem, null, n);
    }
    
    public boolean a(final MenuItem menuItem, final y y, final int n) {
        final boolean b = false;
        final m m = (m)menuItem;
        boolean b2 = b;
        if (m != null) {
            if (!m.isEnabled()) {
                b2 = b;
            }
            else {
                final boolean a = m.a();
                final ActionProvider supportActionProvider = m.getSupportActionProvider();
                final boolean b3 = supportActionProvider != null && supportActionProvider.hasSubMenu();
                if (m.n()) {
                    final boolean b4 = b2 = (m.expandActionView() | a);
                    if (b4) {
                        this.b(true);
                        return b4;
                    }
                }
                else {
                    if (!m.hasSubMenu() && !b3) {
                        if ((n & 0x1) == 0x0) {
                            this.b(true);
                        }
                        return a;
                    }
                    this.b(false);
                    if (!m.hasSubMenu()) {
                        m.a(new ae(this.f(), this, m));
                    }
                    final ae ae = (ae)m.getSubMenu();
                    if (b3) {
                        supportActionProvider.onPrepareSubMenu((SubMenu)ae);
                    }
                    final boolean b5 = b2 = (this.a(ae, y) | a);
                    if (!b5) {
                        this.b(true);
                        return b5;
                    }
                }
            }
        }
        return b2;
    }
    
    public MenuItem add(final int n) {
        return this.a(0, 0, 0, this.j.getString(n));
    }
    
    public MenuItem add(final int n, final int n2, final int n3, final int n4) {
        return this.a(n, n2, n3, this.j.getString(n4));
    }
    
    public MenuItem add(final int n, final int n2, final int n3, final CharSequence charSequence) {
        return this.a(n, n2, n3, charSequence);
    }
    
    public MenuItem add(final CharSequence charSequence) {
        return this.a(0, 0, 0, charSequence);
    }
    
    public int addIntentOptions(final int n, final int n2, final int n3, final ComponentName componentName, final Intent[] array, final Intent intent, int i, final MenuItem[] array2) {
        final PackageManager packageManager = this.i.getPackageManager();
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
        return this.addSubMenu(0, 0, 0, this.j.getString(n));
    }
    
    public SubMenu addSubMenu(final int n, final int n2, final int n3, final int n4) {
        return this.addSubMenu(n, n2, n3, this.j.getString(n4));
    }
    
    public SubMenu addSubMenu(final int n, final int n2, final int n3, final CharSequence charSequence) {
        final m m = (m)this.a(n, n2, n3, charSequence);
        final ae ae = new ae(this.i, this, m);
        m.a(ae);
        return (SubMenu)ae;
    }
    
    public SubMenu addSubMenu(final CharSequence charSequence) {
        return this.addSubMenu(0, 0, 0, charSequence);
    }
    
    public void b() {
        this.w = true;
        this.clear();
        this.clearHeader();
        this.w = false;
        this.x = false;
        this.c(true);
    }
    
    public void b(final int n) {
        this.a(n, true);
    }
    
    public void b(final Bundle bundle) {
        this.f(bundle);
    }
    
    void b(final m m) {
        this.c(this.s = true);
    }
    
    public void b(final y y) {
        for (final WeakReference<y> weakReference : this.B) {
            final y y2 = weakReference.get();
            if (y2 == null || y2 == y) {
                this.B.remove(weakReference);
            }
        }
    }
    
    public final void b(final boolean b) {
        if (this.z) {
            return;
        }
        this.z = true;
        for (final WeakReference<y> weakReference : this.B) {
            final y y = weakReference.get();
            if (y == null) {
                this.B.remove(weakReference);
            }
            else {
                y.onCloseMenu(this, b);
            }
        }
        this.z = false;
    }
    
    public int c(final int n) {
        for (int size = this.size(), i = 0; i < size; ++i) {
            if (this.n.get(i).getItemId() == n) {
                return i;
            }
        }
        return -1;
    }
    
    public void c(final Bundle bundle) {
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
                ((ae)item.getSubMenu()).c(bundle);
            }
            ++i;
            sparseArray = sparseArray2;
        }
        if (sparseArray != null) {
            bundle.putSparseParcelableArray(this.a(), sparseArray);
        }
    }
    
    public void c(final boolean b) {
        if (!this.w) {
            if (b) {
                this.p = true;
                this.s = true;
            }
            this.e(b);
            return;
        }
        this.x = true;
    }
    
    boolean c() {
        return this.k;
    }
    
    public boolean c(final m c) {
        boolean b = false;
        if (!this.B.isEmpty()) {
            this.h();
            final Iterator<WeakReference<y>> iterator = this.B.iterator();
            boolean expandItemActionView = false;
            while (true) {
                while (iterator.hasNext()) {
                    final WeakReference<y> weakReference = iterator.next();
                    final y y = weakReference.get();
                    if (y == null) {
                        this.B.remove(weakReference);
                    }
                    else {
                        final boolean b2 = expandItemActionView = y.expandItemActionView(this, c);
                        if (!b2) {
                            continue;
                        }
                        expandItemActionView = b2;
                        this.i();
                        b = expandItemActionView;
                        if (expandItemActionView) {
                            this.C = c;
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
        if (this.C != null) {
            this.d(this.C);
        }
        this.n.clear();
        this.c(true);
    }
    
    public void clearHeader() {
        this.b = null;
        this.a = null;
        this.c = null;
        this.c(false);
    }
    
    public void close() {
        this.b(true);
    }
    
    public int d(final int n) {
        return this.a(n, 0);
    }
    
    public void d(final Bundle bundle) {
        if (bundle != null) {
            final SparseArray sparseParcelableArray = bundle.getSparseParcelableArray(this.a());
            for (int size = this.size(), i = 0; i < size; ++i) {
                final MenuItem item = this.getItem(i);
                final View actionView = MenuItemCompat.getActionView(item);
                if (actionView != null && actionView.getId() != -1) {
                    actionView.restoreHierarchyState(sparseParcelableArray);
                }
                if (item.hasSubMenu()) {
                    ((ae)item.getSubMenu()).d(bundle);
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
    
    void d(final boolean y) {
        this.y = y;
    }
    
    public boolean d() {
        return this.l;
    }
    
    public boolean d(final m m) {
        boolean b = false;
        if (!this.B.isEmpty()) {
            if (this.C == m) {
                this.h();
                final Iterator<WeakReference<y>> iterator = this.B.iterator();
                boolean collapseItemActionView = false;
                while (true) {
                    while (iterator.hasNext()) {
                        final WeakReference<y> weakReference = iterator.next();
                        final y y = weakReference.get();
                        if (y == null) {
                            this.B.remove(weakReference);
                        }
                        else {
                            final boolean b2 = collapseItemActionView = y.collapseItemActionView(this, m);
                            if (!b2) {
                                continue;
                            }
                            collapseItemActionView = b2;
                            this.i();
                            b = collapseItemActionView;
                            if (collapseItemActionView) {
                                this.C = null;
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
    
    Resources e() {
        return this.j;
    }
    
    protected i e(final int n) {
        this.a(n, null, 0, null, null);
        return this;
    }
    
    public Context f() {
        return this.i;
    }
    
    protected i f(final int n) {
        this.a(0, null, n, null, null);
        return this;
    }
    
    public MenuItem findItem(final int n) {
        for (int size = this.size(), i = 0; i < size; ++i) {
            Object item = this.n.get(i);
            if (((m)item).getItemId() == n || (((m)item).hasSubMenu() && (item = ((m)item).getSubMenu().findItem(n)) != null)) {
                return (MenuItem)item;
            }
        }
        return null;
    }
    
    public void g() {
        if (this.m != null) {
            this.m.onMenuModeChange(this);
        }
    }
    
    public MenuItem getItem(final int n) {
        return (MenuItem)this.n.get(n);
    }
    
    public void h() {
        if (!this.w) {
            this.w = true;
            this.x = false;
        }
    }
    
    public boolean hasVisibleItems() {
        for (int size = this.size(), i = 0; i < size; ++i) {
            if (this.n.get(i).isVisible()) {
                return true;
            }
        }
        return false;
    }
    
    public void i() {
        this.w = false;
        if (this.x) {
            this.x = false;
            this.c(true);
        }
    }
    
    public boolean isShortcutKey(final int n, final KeyEvent keyEvent) {
        return this.a(n, keyEvent) != null;
    }
    
    public ArrayList<m> j() {
        if (!this.p) {
            return this.o;
        }
        this.o.clear();
        for (int size = this.n.size(), i = 0; i < size; ++i) {
            final m m = this.n.get(i);
            if (m.isVisible()) {
                this.o.add(m);
            }
        }
        this.p = false;
        this.s = true;
        return this.o;
    }
    
    public void k() {
        final ArrayList<m> j = this.j();
        if (!this.s) {
            return;
        }
        final Iterator<WeakReference<y>> iterator = this.B.iterator();
        boolean b = false;
        while (iterator.hasNext()) {
            final WeakReference<y> weakReference = iterator.next();
            final y y = weakReference.get();
            if (y == null) {
                this.B.remove(weakReference);
            }
            else {
                b |= y.flagActionItems();
            }
        }
        if (b) {
            this.q.clear();
            this.r.clear();
            for (int size = j.size(), i = 0; i < size; ++i) {
                final m m = j.get(i);
                if (m.j()) {
                    this.q.add(m);
                }
                else {
                    this.r.add(m);
                }
            }
        }
        else {
            this.q.clear();
            this.r.clear();
            this.r.addAll(this.j());
        }
        this.s = false;
    }
    
    public ArrayList<m> l() {
        this.k();
        return this.q;
    }
    
    public ArrayList<m> m() {
        this.k();
        return this.r;
    }
    
    public CharSequence n() {
        return this.a;
    }
    
    public Drawable o() {
        return this.b;
    }
    
    public View p() {
        return this.c;
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
            this.b(true);
        }
        return a2;
    }
    
    public i q() {
        return this;
    }
    
    boolean r() {
        return this.y;
    }
    
    public void removeGroup(final int n) {
        final int d = this.d(n);
        if (d >= 0) {
            for (int size = this.n.size(), n2 = 0; n2 < size - d && this.n.get(d).getGroupId() == n; ++n2) {
                this.a(d, false);
            }
            this.c(true);
        }
    }
    
    public void removeItem(final int n) {
        this.a(this.c(n), true);
    }
    
    public m s() {
        return this.C;
    }
    
    public void setGroupCheckable(final int n, final boolean checkable, final boolean b) {
        for (int size = this.n.size(), i = 0; i < size; ++i) {
            final m m = this.n.get(i);
            if (m.getGroupId() == n) {
                m.a(b);
                m.setCheckable(checkable);
            }
        }
    }
    
    public void setGroupEnabled(final int n, final boolean enabled) {
        for (int size = this.n.size(), i = 0; i < size; ++i) {
            final m m = this.n.get(i);
            if (m.getGroupId() == n) {
                m.setEnabled(enabled);
            }
        }
    }
    
    public void setGroupVisible(final int n, final boolean b) {
        final int size = this.n.size();
        int i = 0;
        boolean b2 = false;
        while (i < size) {
            final m m = this.n.get(i);
            if (m.getGroupId() == n && m.c(b)) {
                b2 = true;
            }
            ++i;
        }
        if (b2) {
            this.c(true);
        }
    }
    
    public void setQwertyMode(final boolean k) {
        this.k = k;
        this.c(false);
    }
    
    public int size() {
        return this.n.size();
    }
}
