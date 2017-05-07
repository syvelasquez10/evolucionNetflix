// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.SocketImpl;
import java.util.Iterator;
import java.lang.reflect.Field;
import java.net.Socket;
import java.net.SocketImplFactory;
import javax.net.ssl.SSLSocketFactory;
import android.os.Build$VERSION;
import java.net.URL;
import java.net.URLStreamHandler;
import java.util.LinkedList;
import java.util.List;

public final class i
{
    public static final v$a a;
    public static i$b b;
    private static final List c;
    private ad d;
    private ab e;
    private ab f;
    private v g;
    private e h;
    private d i;
    private i$b j;
    private v$a k;
    
    static {
        a = v$a.b;
        i.b = i$b.c;
        c = new LinkedList();
        try {
            if (((URLStreamHandler)j.a(j.a(URL.class, URLStreamHandler.class), new URL("https://www.google.com"))).getClass().getName().contains("okhttp") && Build$VERSION.SDK_INT >= 19) {
                i.b = i$b.a;
                return;
            }
            i.b = i$b.b;
        }
        catch (Exception ex) {
            i.b = i$b.c;
        }
    }
    
    public i(final e h, final d i) {
        this.j = crittercism.android.i.b;
        this.k = crittercism.android.i.a;
        this.h = h;
        this.i = i;
    }
    
    private static void a(final String s, final Throwable t) {
        synchronized (i.c) {
            i.c.add(t);
            // monitorexit(i.c)
            dy.c(s);
        }
    }
    
    private static void a(final SSLSocketFactory sslSocketFactory) {
        j.a(org.apache.http.conn.ssl.SSLSocketFactory.class, SSLSocketFactory.class).set(org.apache.http.conn.ssl.SSLSocketFactory.getSocketFactory(), sslSocketFactory);
    }
    
    private static boolean a(final SocketImplFactory socketImplFactory) {
        Field field;
        try {
            final Field a;
            field = (a = j.a(Socket.class, SocketImplFactory.class));
            final boolean b = true;
            a.setAccessible(b);
            final Field field2 = field;
            final Object o = null;
            final SocketImplFactory socketImplFactory2 = socketImplFactory;
            field2.set(o, socketImplFactory2);
            return true;
        }
        catch (cl cl) {
            a("Unable to install OPTIMZ for http connections", cl);
            return false;
        }
        try {
            final Field a = field;
            final boolean b = true;
            a.setAccessible(b);
            final Field field2 = field;
            final Object o = null;
            final SocketImplFactory socketImplFactory2 = socketImplFactory;
            field2.set(o, socketImplFactory2);
            return true;
        }
        catch (IllegalArgumentException ex) {
            a("Unable to install OPTIMZ for http connections", ex);
            return true;
        }
        catch (IllegalAccessException ex2) {
            a("Unable to install OPTIMZ for http connections", ex2);
            return false;
        }
        catch (NullPointerException ex3) {
            a("Unable to install OPTIMZ for http connections", ex3);
            return false;
        }
    }
    
    public static void d() {
        synchronized (i.c) {
            final Iterator<Throwable> iterator = i.c.iterator();
            while (iterator.hasNext()) {
                dy.a(iterator.next());
            }
        }
        i.c.clear();
    }
    // monitorexit(list)
    
    private boolean e() {
        final i$a i$a = new i$a(this);
        final Thread thread = new Thread(i$a);
        thread.start();
        try {
            thread.join();
            return i$a.a();
        }
        catch (InterruptedException ex) {
            return i$a.a();
        }
    }
    
    private boolean f() {
        try {
            this.g = new v(this.k, this.h, this.i);
            return this.g.b();
        }
        catch (ClassNotFoundException ex) {
            return false;
        }
    }
    
    private static SSLSocketFactory g() {
        return (SSLSocketFactory)j.a(org.apache.http.conn.ssl.SSLSocketFactory.class, SSLSocketFactory.class).get(org.apache.http.conn.ssl.SSLSocketFactory.getSocketFactory());
    }
    
    private boolean h() {
        Class<? extends SocketImpl> class1 = null;
        Label_0112: {
            SocketImplFactory socketImplFactory;
            SocketImpl socketImpl;
            try {
                socketImplFactory = (SocketImplFactory)crittercism.android.j.a(crittercism.android.j.a(Socket.class, SocketImplFactory.class), null);
                if (socketImplFactory != null) {
                    break Label_0112;
                }
                try {
                    socketImpl = (SocketImpl)crittercism.android.j.a(crittercism.android.j.a(Socket.class, SocketImpl.class), new Socket());
                    if (socketImpl == null) {
                        throw new cl("SocketImpl was null");
                    }
                }
                catch (cl cl) {
                    a("Unable to install OPTIMZ for http connections", cl);
                    return false;
                }
            }
            catch (cl cl2) {
                a("Unable to install OPTIMZ for http connections", cl2);
                return false;
            }
            class1 = socketImpl.getClass();
            Label_0121: {
                if (socketImplFactory == null) {
                    break Label_0121;
                }
                try {
                    ad ad = new ad(socketImplFactory, this.h, this.i);
                    a(ad);
                    while (true) {
                        while (true) {
                            this.d = ad;
                            return true;
                            ad = new ad(class1, this.h, this.i);
                            Socket.setSocketImplFactory(ad);
                            continue;
                        }
                        return true;
                        continue;
                    }
                }
                // iftrue(Label_0079:, !socketImplFactory instanceof ad)
                // iftrue(Label_0158:, class1 == null)
                catch (cl cl3) {
                    a("Unable to install OPTIMZ for http connections", cl3);
                    return false;
                }
                catch (IOException ex) {
                    a("Unable to install OPTIMZ for http connections", ex);
                    return false;
                }
            }
        }
    }
    
    public final boolean a() {
        boolean b = false;
        if (!ac.c()) {
            a("Unable to install OPTMZ", ac.d());
        }
        else {
            boolean b3 = false;
        Label_0065_Outer:
            while (true) {
                while (true) {
                    Label_0175: {
                        while (true) {
                            SSLSocketFactory defaultSSLSocketFactory = null;
                        Label_0125:
                            while (true) {
                                boolean b2;
                                try {
                                    ac.e();
                                    b2 = (this.h() | false);
                                    if (Build$VERSION.SDK_INT >= 19) {
                                        b3 = (b2 | this.e());
                                        if (Build$VERSION.SDK_INT < 17) {
                                            break Label_0175;
                                        }
                                        b3 |= y.a(this.h, this.i);
                                        if (this.j != i$b.a) {
                                            break;
                                        }
                                        defaultSSLSocketFactory = HttpsURLConnection.getDefaultSSLSocketFactory();
                                        if (defaultSSLSocketFactory instanceof ab) {
                                            this.e = (ab)defaultSSLSocketFactory;
                                            return b3 | true;
                                        }
                                        break Label_0125;
                                    }
                                }
                                catch (ck ck) {
                                    dy.a(ck.toString(), ck);
                                    return false;
                                }
                                b3 = (b2 | this.c());
                                continue Label_0065_Outer;
                            }
                            HttpsURLConnection.setDefaultSSLSocketFactory(this.e = new ab(defaultSSLSocketFactory, this.h, this.i));
                            continue;
                        }
                    }
                    continue;
                }
            }
            b = b3;
            if (this.j == i$b.b) {
                return b3 | this.f();
            }
        }
        return b;
    }
    
    public final void b() {
        try {
            final SSLSocketFactory g = g();
            if (g instanceof ab) {
                a(((ab)g).a());
            }
            this.f = null;
        }
        catch (IllegalArgumentException ex) {
            a("Unable to install OPTIMZ for SSL HttpClient connections", ex);
        }
        catch (IllegalAccessException ex2) {
            a("Unable to install OPTIMZ for SSL HttpClient connections", ex2);
        }
        catch (cl cl) {
            a("Unable to install OPTIMZ for SSL HttpClient connections", cl);
        }
    }
    
    public final boolean c() {
        SSLSocketFactory g;
        while (true) {
            try {
                g = g();
                if (g == null) {
                    a("Unable to install OPTIMZ for SSL HttpClient connections", new NullPointerException("Delegate factory was null"));
                    return false;
                }
            }
            catch (IllegalArgumentException ex) {
                a("Unable to install OPTIMZ for SSL HttpClient connections", ex);
                return false;
            }
            catch (IllegalAccessException ex2) {
                a("Unable to install OPTIMZ for SSL HttpClient connections", ex2);
                return false;
            }
            catch (ClassCastException ex3) {
                a("Unable to install OPTIMZ for SSL HttpClient connections", ex3);
                return false;
            }
            catch (cl cl) {
                a("Unable to install OPTIMZ for SSL HttpClient connections", cl);
                return false;
            }
            if (!(g instanceof ab)) {
                break;
            }
            return false;
        }
        final ab f = new ab(g, this.h, this.i);
        try {
            a(f);
            this.f = f;
            return true;
        }
        catch (IllegalArgumentException ex4) {
            a("Unable to install OPTIMZ for SSL HttpClient connections", ex4);
            return false;
        }
        catch (IllegalAccessException ex5) {
            a("Unable to install OPTIMZ for SSL HttpClient connections", ex5);
            return false;
        }
        catch (cl cl2) {
            a("Unable to install OPTIMZ for SSL HttpClient connections", cl2);
            return false;
        }
    }
}
