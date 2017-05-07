// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.io.OutputStream;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.io.FileDescriptor;
import java.util.concurrent.Executor;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.net.SocketAddress;
import java.net.InetAddress;
import java.lang.reflect.AccessibleObject;
import java.util.Queue;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.net.SocketImpl;

public final class ac extends SocketImpl implements ae
{
    private static Field a;
    private static Field b;
    private static Field c;
    private static Field d;
    private static Method[] e;
    private static boolean f;
    private static Throwable g;
    private final Queue h;
    private e i;
    private d j;
    private SocketImpl k;
    private w l;
    private x m;
    
    static {
        while (true) {
            ac.e = new Method[20];
            ac.f = false;
            ac.g = null;
        Label_0562:
            while (true) {
                Label_0688: {
                    try {
                        ac.a = SocketImpl.class.getDeclaredField("address");
                        ac.b = SocketImpl.class.getDeclaredField("fd");
                        ac.c = SocketImpl.class.getDeclaredField("localport");
                        ac.d = SocketImpl.class.getDeclaredField("port");
                        final Field a = ac.a;
                        final AccessibleObject[] array = { ac.b, ac.c, ac.d };
                        if (a != null) {
                            a.setAccessible(true);
                        }
                        if (array.length > 0) {
                            j.a(array);
                        }
                        ac.e[0] = SocketImpl.class.getDeclaredMethod("accept", SocketImpl.class);
                        ac.e[1] = SocketImpl.class.getDeclaredMethod("available", (Class<?>[])new Class[0]);
                        ac.e[2] = SocketImpl.class.getDeclaredMethod("bind", InetAddress.class, Integer.TYPE);
                        ac.e[3] = SocketImpl.class.getDeclaredMethod("close", (Class<?>[])new Class[0]);
                        ac.e[4] = SocketImpl.class.getDeclaredMethod("connect", InetAddress.class, Integer.TYPE);
                        ac.e[5] = SocketImpl.class.getDeclaredMethod("connect", SocketAddress.class, Integer.TYPE);
                        ac.e[6] = SocketImpl.class.getDeclaredMethod("connect", String.class, Integer.TYPE);
                        ac.e[7] = SocketImpl.class.getDeclaredMethod("create", Boolean.TYPE);
                        ac.e[8] = SocketImpl.class.getDeclaredMethod("getFileDescriptor", (Class<?>[])new Class[0]);
                        ac.e[9] = SocketImpl.class.getDeclaredMethod("getInetAddress", (Class<?>[])new Class[0]);
                        ac.e[10] = SocketImpl.class.getDeclaredMethod("getInputStream", (Class<?>[])new Class[0]);
                        ac.e[11] = SocketImpl.class.getDeclaredMethod("getLocalPort", (Class<?>[])new Class[0]);
                        ac.e[12] = SocketImpl.class.getDeclaredMethod("getOutputStream", (Class<?>[])new Class[0]);
                        ac.e[13] = SocketImpl.class.getDeclaredMethod("getPort", (Class<?>[])new Class[0]);
                        ac.e[14] = SocketImpl.class.getDeclaredMethod("listen", Integer.TYPE);
                        ac.e[15] = SocketImpl.class.getDeclaredMethod("sendUrgentData", Integer.TYPE);
                        ac.e[16] = SocketImpl.class.getDeclaredMethod("setPerformancePreferences", Integer.TYPE, Integer.TYPE, Integer.TYPE);
                        ac.e[17] = SocketImpl.class.getDeclaredMethod("shutdownInput", (Class<?>[])new Class[0]);
                        ac.e[18] = SocketImpl.class.getDeclaredMethod("shutdownOutput", (Class<?>[])new Class[0]);
                        ac.e[19] = SocketImpl.class.getDeclaredMethod("supportsUrgentData", (Class<?>[])new Class[0]);
                        j.a(ac.e);
                        ac.f = true;
                        return;
                    }
                    catch (SecurityException g) {
                        ac.f = false;
                        ac.g = g;
                        return;
                    }
                    catch (NoSuchMethodException ex) {
                        ac.f = false;
                        for (int i = 0; i < 20; ++i) {
                            if (ac.e[i] == null) {
                                break Label_0562;
                            }
                        }
                        break Label_0688;
                        int i = 0;
                        ac.g = new ck("Bad method: " + i, ex);
                        return;
                    }
                    catch (NoSuchFieldException ex2) {
                        ac.f = false;
                        String s = "unknown";
                        if (ac.a == null) {
                            s = "address";
                        }
                        else if (ac.b == null) {
                            s = "fd";
                        }
                        else if (ac.c == null) {
                            s = "localport";
                        }
                        else if (ac.d == null) {
                            s = "port";
                        }
                        ac.g = new ck("No such field: " + s, ex2);
                        return;
                    }
                    catch (Throwable g2) {
                        ac.f = false;
                        ac.g = g2;
                        return;
                    }
                }
                int i = 20;
                continue Label_0562;
            }
        }
    }
    
    public ac(final e i, final d j, final SocketImpl k) {
        this.h = new LinkedList();
        if (i == null) {
            throw new NullPointerException("dispatch was null");
        }
        if (k == null) {
            throw new NullPointerException("delegate was null");
        }
        this.i = i;
        this.j = j;
        this.k = k;
        this.f();
    }
    
    private c a(final boolean b) {
        final c c = new c();
        final InetAddress inetAddress = this.getInetAddress();
        if (inetAddress != null) {
            c.a(inetAddress);
        }
        final int port = this.getPort();
        if (port > 0) {
            c.a(port);
        }
        if (b) {
            c.a(k$a.a);
        }
        if (this.j != null) {
            c.j = this.j.a();
        }
        if (bc.b()) {
            c.a(bc.a());
        }
        return c;
    }
    
    private Object a(final int n, final Object... array) {
        try {
            ac.a.set(this.k, this.address);
            ac.b.set(this.k, this.fd);
            ac.c.setInt(this.k, this.localport);
            ac.d.setInt(this.k, this.port);
            final Method[] array2 = ac.e;
            final int n2 = n;
            final Method method = array2[n2];
            final ac ac = this;
            final SocketImpl socketImpl = ac.k;
            final Object[] array3 = array;
            final Object invoke = method.invoke(socketImpl, array3);
            return invoke;
        }
        catch (IllegalArgumentException ex) {
            throw new ck(ex);
        }
        catch (IllegalAccessException ex2) {
            throw new ck(ex2);
        }
        try {
            final Method[] array2 = ac.e;
            final int n2 = n;
            final Method method = array2[n2];
            final ac ac = this;
            final SocketImpl socketImpl = ac.k;
            final Object[] array3 = array;
            final Object invoke2;
            final Object invoke = invoke2 = method.invoke(socketImpl, array3);
            return invoke2;
        }
        catch (IllegalArgumentException ex3) {
            throw new ck(ex3);
        }
        catch (IllegalAccessException ex7) {}
        catch (InvocationTargetException ex8) {
            final InvocationTargetException ex4;
            final Throwable targetException = ex4.getTargetException();
            if (targetException == null) {
                throw new ck(ex4);
            }
            if (targetException instanceof Exception) {
                throw (Exception)targetException;
            }
            if (targetException instanceof Error) {
                throw (Error)targetException;
            }
            throw new ck(targetException);
        }
        catch (ClassCastException ex5) {
            throw new ck(ex5);
        }
        catch (Exception ex6) {
            throw new ck(ex6);
        }
    }
    
    private Object b(final int n, final Object... array) {
        try {
            return this.a(n, array);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        catch (Exception ex2) {
            throw new ck(ex2);
        }
    }
    
    private Object c(final int n, final Object... array) {
        try {
            return this.a(n, array);
        }
        catch (IOException ex) {
            throw ex;
        }
        catch (RuntimeException ex2) {
            throw ex2;
        }
        catch (Exception ex3) {
            throw new ck(ex3);
        }
    }
    
    public static boolean c() {
        return ac.f;
    }
    
    public static Throwable d() {
        return ac.g;
    }
    
    public static void e() {
        if (!ac.f) {
            throw new ck(ac.g);
        }
        final ac ac = new ac(new e(new ac$2()), null, new ac$1());
        final Object o = new Object();
        try {
            ac.setOption(0, o);
            ac.getOption(0);
            ac.sendUrgentData(0);
            ac.listen(0);
            ac.getOutputStream();
            ac.getInputStream();
            ac.create(false);
            ac.connect((SocketAddress)null, 0);
            ac.connect((InetAddress)null, 0);
            ac.connect((String)null, 0);
            ac.close();
            ac.bind(null, 0);
            ac.available();
            ac.accept(ac);
            ac.getFileDescriptor();
            ac.getInetAddress();
            ac.getLocalPort();
            ac.getPort();
            ac.setPerformancePreferences(0, 0, 0);
            ac.shutdownInput();
            ac.shutdownOutput();
            ac.supportsUrgentData();
        }
        catch (ck ck) {
            throw ck;
        }
        catch (Throwable t) {
            throw new ck(t);
        }
        catch (IOException ex) {}
    }
    
    private void f() {
        try {
            this.address = (InetAddress)ac.a.get(this.k);
            this.fd = (FileDescriptor)ac.b.get(this.k);
            this.localport = ac.c.getInt(this.k);
            this.port = ac.d.getInt(this.k);
        }
        catch (IllegalArgumentException ex) {
            throw new ck(ex);
        }
        catch (IllegalAccessException ex2) {
            throw new ck(ex2);
        }
    }
    
    @Override
    public final c a() {
        return this.a(true);
    }
    
    @Override
    public final void a(final c c) {
        synchronized (this.h) {
            this.h.add(c);
        }
    }
    
    public final void accept(final SocketImpl socketImpl) {
        this.c(0, socketImpl);
    }
    
    public final int available() {
        final Integer n = (Integer)this.c(1, new Object[0]);
        if (n == null) {
            throw new ck("Received a null Integer");
        }
        return n;
    }
    
    @Override
    public final c b() {
        synchronized (this.h) {
            return this.h.poll();
        }
    }
    
    public final void bind(final InetAddress inetAddress, final int n) {
        this.c(2, inetAddress, n);
    }
    
    public final void close() {
        this.c(3, new Object[0]);
        try {
            if (this.m != null) {
                this.m.d();
            }
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dy.a(t);
        }
    }
    
    public final void connect(final String s, final int n) {
        try {
            this.c(6, s, n);
        }
        catch (IOException ex) {
            Label_0080: {
                if (s == null) {
                    break Label_0080;
                }
                try {
                    final c a = this.a(false);
                    a.b();
                    a.c();
                    a.f();
                    a.b(s);
                    a.a(n);
                    a.a(ex);
                    this.i.a(a, c$a.i);
                    throw ex;
                }
                catch (ThreadDeath threadDeath) {
                    throw threadDeath;
                }
                catch (Throwable t) {
                    dy.a(t);
                }
            }
        }
    }
    
    public final void connect(final InetAddress inetAddress, final int n) {
        try {
            this.c(4, inetAddress, n);
        }
        catch (IOException ex) {
            Label_0079: {
                if (inetAddress == null) {
                    break Label_0079;
                }
                try {
                    final c a = this.a(false);
                    a.b();
                    a.c();
                    a.f();
                    a.a(inetAddress);
                    a.a(n);
                    a.a(ex);
                    this.i.a(a, c$a.i);
                    throw ex;
                }
                catch (ThreadDeath threadDeath) {
                    throw threadDeath;
                }
                catch (Throwable t) {
                    dy.a(t);
                }
            }
        }
    }
    
    public final void connect(final SocketAddress socketAddress, final int n) {
        try {
            this.c(5, socketAddress, n);
        }
        catch (IOException ex) {
            Label_0097: {
                if (socketAddress == null) {
                    break Label_0097;
                }
                try {
                    if (socketAddress instanceof InetSocketAddress) {
                        final c a = this.a(false);
                        final InetSocketAddress inetSocketAddress = (InetSocketAddress)socketAddress;
                        a.b();
                        a.c();
                        a.f();
                        a.a(inetSocketAddress.getAddress());
                        a.a(inetSocketAddress.getPort());
                        a.a(ex);
                        this.i.a(a, c$a.i);
                    }
                    throw ex;
                }
                catch (ThreadDeath threadDeath) {
                    throw threadDeath;
                }
                catch (Throwable t) {
                    dy.a(t);
                }
            }
        }
    }
    
    public final void create(final boolean b) {
        this.c(7, b);
    }
    
    public final FileDescriptor getFileDescriptor() {
        return (FileDescriptor)this.b(8, new Object[0]);
    }
    
    public final InetAddress getInetAddress() {
        return (InetAddress)this.b(9, new Object[0]);
    }
    
    public final InputStream getInputStream() {
        final InputStream inputStream = (InputStream)this.c(10, new Object[0]);
        if (inputStream != null) {
            try {
                if (this.m != null && this.m.a(inputStream)) {
                    return this.m;
                }
                return this.m = new x(this, inputStream, this.i);
            }
            catch (ThreadDeath threadDeath) {
                throw threadDeath;
            }
            catch (Throwable t) {
                dy.a(t);
            }
        }
        return inputStream;
    }
    
    public final int getLocalPort() {
        return (int)this.b(11, new Object[0]);
    }
    
    @Override
    public final Object getOption(final int n) {
        return this.k.getOption(n);
    }
    
    public final OutputStream getOutputStream() {
        final OutputStream outputStream = (OutputStream)this.c(12, new Object[0]);
        if (outputStream != null) {
            try {
                if (this.l != null && this.l.a(outputStream)) {
                    return this.l;
                }
                return this.l = new w(this, outputStream);
            }
            catch (ThreadDeath threadDeath) {
                throw threadDeath;
            }
            catch (Throwable t) {
                dy.a(t);
            }
        }
        return outputStream;
    }
    
    public final int getPort() {
        return (int)this.b(13, new Object[0]);
    }
    
    public final void listen(final int n) {
        this.c(14, n);
    }
    
    public final void sendUrgentData(final int n) {
        this.c(15, n);
    }
    
    @Override
    public final void setOption(final int n, final Object o) {
        this.k.setOption(n, o);
    }
    
    public final void setPerformancePreferences(final int n, final int n2, final int n3) {
        this.b(16, n, n2, n3);
    }
    
    public final void shutdownInput() {
        this.c(17, new Object[0]);
    }
    
    public final void shutdownOutput() {
        this.c(18, new Object[0]);
    }
    
    public final boolean supportsUrgentData() {
        return (boolean)this.b(19, new Object[0]);
    }
    
    @Override
    public final String toString() {
        return this.k.toString();
    }
}
