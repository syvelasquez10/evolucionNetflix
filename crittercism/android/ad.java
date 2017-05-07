// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.net.SocketImpl;
import java.net.SocketImplFactory;

public final class ad implements SocketImplFactory
{
    private Class a;
    private SocketImplFactory b;
    private e c;
    private d d;
    
    public ad(Class a, final e c, final d d) {
        this.c = c;
        this.d = d;
        this.a = a;
        a = this.a;
        if (a == null) {
            throw new cl("Class was null");
        }
        try {
            a.newInstance();
        }
        catch (Throwable t) {
            throw new cl("Unable to create new instance", t);
        }
    }
    
    public ad(SocketImplFactory b, final e c, final d d) {
        this.c = c;
        this.d = d;
        this.b = b;
        b = this.b;
        if (b == null) {
            throw new cl("Factory was null");
        }
        try {
            if (b.createSocketImpl() == null) {
                throw new cl("Factory does not work");
            }
        }
        catch (Throwable t) {
            throw new cl("Factory does not work", t);
        }
    }
    
    @Override
    public final SocketImpl createSocketImpl() {
        SocketImpl socketImpl = null;
        if (this.b != null) {
            socketImpl = this.b.createSocketImpl();
        }
        else {
            final Class a = this.a;
            try {
                socketImpl = this.a.newInstance();
            }
            catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
            catch (InstantiationException ex2) {
                ex2.printStackTrace();
            }
        }
        if (socketImpl != null) {
            return new ac(this.c, this.d, socketImpl);
        }
        return socketImpl;
    }
}
