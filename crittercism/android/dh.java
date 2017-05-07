// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.util.Iterator;
import android.content.SharedPreferences;
import com.crittercism.app.CrittercismNDK;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.os.ConditionVariable;

public final class dh extends dj
{
    public ConditionVariable a;
    public bm b;
    private ConditionVariable c;
    private bb d;
    private Context e;
    private aw f;
    private ax g;
    private au h;
    private List i;
    private boolean j;
    private boolean k;
    private Exception l;
    
    public dh(final bb d, final Context e, final aw f, final ax g, final au h) {
        this.c = new ConditionVariable();
        this.a = new ConditionVariable();
        this.i = new ArrayList();
        this.j = false;
        this.b = null;
        this.l = null;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
        this.h = h;
        this.k = false;
    }
    
    private void a(final File file) {
        final Context e = this.e;
        h h = new h(e);
        final SharedPreferences sharedPreferences = e.getSharedPreferences("com.crittercism.optmz.config", 0);
        Label_0514: {
            if (!sharedPreferences.contains("interval")) {
                break Label_0514;
            }
            h.d = sharedPreferences.getInt("interval", 10);
            Label_0519: {
                if (!sharedPreferences.contains("kill")) {
                    break Label_0519;
                }
                h.c = sharedPreferences.getBoolean("kill", false);
                Label_0524: {
                    if (!sharedPreferences.contains("persist")) {
                        break Label_0524;
                    }
                    h.b = sharedPreferences.getBoolean("persist", false);
                    Label_0529: {
                        if (!sharedPreferences.contains("enabled")) {
                            break Label_0529;
                        }
                        h.a = sharedPreferences.getBoolean("enabled", false);
                    Label_0428_Outer:
                        while (true) {
                            if (h != null) {
                                az.A().a(h);
                            }
                            final boolean k = this.k;
                            this.f.z();
                            if (!az.A().t) {
                                final bh a = bh.a(this.e);
                                final bi y = new bi(this.e, this.h, this.f.x(), this.f.s(), this.f.t(), this.f.v(), this.d.d());
                                final az a2 = az.A();
                                a2.y = y;
                                new dz(y, "TXN Thread").start();
                                a2.a(a);
                            }
                            final boolean i = this.k;
                            final az a3 = az.A();
                        Label_0509_Outer:
                            while (true) {
                                Label_0450: {
                                    if (a3.t) {
                                        break Label_0450;
                                    }
                                    if (file != null && file.exists()) {
                                        file.isFile();
                                    }
                                    final aw f = this.f;
                                    final bs s = this.f.s();
                                    final bs t = this.f.t();
                                    final bs u = this.f.u();
                                    final bs v = this.f.v();
                                    final bs q = this.f.q();
                                    if (file == null) {
                                        break Label_0509_Outer;
                                    }
                                    dr.a = true;
                                    a3.e.open();
                                    q.a(new cd(file, s, u, t, v));
                                    file.delete();
                                    this.f.w().a();
                                    u.a();
                                    t.a();
                                    v.a();
                                    s.a(u);
                                }
                                this.a.open();
                                this.f.s().a(cf.a);
                                while (true) {
                                    if (az.A().t || !this.d.isNdkCrashReportingEnabled()) {
                                        break Label_0509;
                                    }
                                    dy.b();
                                    try {
                                        CrittercismNDK.installNdkLib(this.e, this.d.g());
                                        this.f();
                                        return;
                                        h = null;
                                        continue Label_0428_Outer;
                                        h = null;
                                        continue Label_0428_Outer;
                                        h = null;
                                        continue Label_0428_Outer;
                                        a3.e.open();
                                        bg.a(this.f);
                                        continue Label_0509_Outer;
                                        h = null;
                                        continue Label_0428_Outer;
                                    }
                                    catch (Throwable t2) {
                                        new StringBuilder("Exception installing ndk library: ").append(t2.getClass().getName());
                                        dy.b();
                                        continue;
                                    }
                                    break;
                                }
                                break;
                            }
                            break;
                        }
                    }
                }
            }
        }
    }
    
    private void c() {
        synchronized (this) {
            this.j = true;
        }
    }
    
    private boolean d() {
        synchronized (this) {
            return this.j;
        }
    }
    
    private File e() {
        int i = 0;
        final File file = new File(this.e.getFilesDir().getAbsolutePath() + "/" + this.d.g());
        if (file.exists() && file.isDirectory()) {
            final File[] listFiles = file.listFiles();
            if (listFiles != null) {
                if (listFiles.length == 1) {
                    final File file2 = listFiles[0];
                    file2.isFile();
                    if (file2.isFile()) {
                        return file2;
                    }
                }
                else if (listFiles.length > 1) {
                    while (i < listFiles.length) {
                        final File file3 = listFiles[i];
                        file3.isFile();
                        file3.delete();
                        ++i;
                    }
                }
            }
        }
        return null;
    }
    
    private void f() {
        if (!az.A().t) {
            final boolean k = this.k;
            final bs n = this.f.n();
            final bs o = this.f.o();
            final bs p = this.f.p();
            final bs q = this.f.q();
            final bs r = this.f.r();
            final dw y = this.f.y();
            this.d.b();
            this.b = new bm(this.h);
            if (!this.d.delaySendingAppLoad()) {
                n.a(this.b);
                final dg dg = new dg(this.e);
                dg.a(n, new cu$a(), this.d.e(), "/v0/appload/", this.d.b(), this.h, new ct$b());
                dg.a(o, new db$a(), this.d.b(), "/android_v2/handle_exceptions", null, this.h, new cv$a());
                dg.a(q, new db$a(), this.d.b(), "/android_v2/handle_ndk_crashes", null, this.h, new cv$a());
                dg.a(r, new db$a(), this.d.b(), "/android_v2/handle_crashes", null, this.h, new cv$a());
                dg.a(p, new db$a(), this.d.b(), "/android_v2/handle_exceptions", null, new ba(this.h, this.d), new cv$a());
                dg.a();
            }
            if (y.b()) {
                az.A().E();
            }
        }
    }
    
    @Override
    public final void a() {
    Label_0431_Outer:
        while (true) {
            while (true) {
                final File file2;
                Label_0509: {
                    while (true) {
                        try {
                            dy.b();
                            final File file = new File(this.e.getFilesDir().getAbsolutePath() + "/com.crittercism/pending");
                            if (!file.exists() || file.isDirectory()) {
                                break Label_0509;
                            }
                            dy.b();
                            final az a = az.A();
                            a.w.a();
                            final dx l = this.h.l();
                            a.d.open();
                            final ax g = this.g;
                            final Context e = this.e;
                            l.a(g);
                            dr.a = dr.a(this.e);
                            dr.a(this.e, false);
                            if (!l.b()) {
                                final du a2 = new du(this.e);
                                a2.a.edit().putInt("numAppLoads", a2.a() + 1).commit();
                                az.A().A = a2;
                                l.a().a(this.g, cr.j.a(), cr.j.b());
                            }
                            this.k = l.b();
                            final File e2 = this.e();
                            if (this.k) {
                                final boolean k = this.k;
                                if (!az.A().t) {
                                    if (e2 != null && e2.exists()) {
                                        e2.isFile();
                                    }
                                    new bs(this.e, br.a).a();
                                    new bs(this.e, br.b).a();
                                    new bs(this.e, br.c).a();
                                    new bs(this.e, br.d).a();
                                    new bs(this.e, br.e).a();
                                    new bs(this.e, br.f).a();
                                    new bs(this.e, br.h).a();
                                    new bs(this.e, br.g).a();
                                    new bs(this.e, br.k).a();
                                    if (e2 != null) {
                                        e2.delete();
                                    }
                                }
                                crittercism.android.h.b(this.e);
                                this.c();
                                final Iterator<Runnable> iterator = (Iterator<Runnable>)this.i.iterator();
                                while (iterator.hasNext()) {
                                    iterator.next().run();
                                }
                                break;
                            }
                        }
                        catch (Exception i) {
                            new StringBuilder("Exception in run(): ").append(i.getMessage());
                            dy.b();
                            dy.c();
                            this.l = i;
                            return;
                            ec.a(file2);
                            continue Label_0431_Outer;
                        }
                        finally {
                            this.c.open();
                        }
                        break;
                    }
                }
                this.a(file2);
                continue;
            }
        }
        this.c.open();
    }
    
    public final boolean a(final Runnable runnable) {
        synchronized (this) {
            boolean b;
            if (!this.d()) {
                this.i.add(runnable);
                b = true;
            }
            else {
                b = false;
            }
            return b;
        }
    }
    
    public final void b() {
        this.c.block();
    }
}
