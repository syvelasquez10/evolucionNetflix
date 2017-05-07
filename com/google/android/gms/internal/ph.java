// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ph<M extends pg<M>, T>
{
    protected final boolean awA;
    protected final Class<T> awz;
    protected final int tag;
    protected final int type;
    
    private ph(final int type, final Class<T> awz, final int tag, final boolean awA) {
        this.type = type;
        this.awz = awz;
        this.tag = tag;
        this.awA = awA;
    }
    
    public static <M extends pg<M>, T extends pm> ph<M, T> a(final int n, final Class<T> clazz, final int n2) {
        return new ph<M, T>(n, clazz, n2, false);
    }
    
    private T m(final List<po> list) {
        final int n = 0;
        final ArrayList<Object> list2 = new ArrayList<Object>();
        for (int i = 0; i < list.size(); ++i) {
            final po po = list.get(i);
            if (po.awK.length != 0) {
                this.a(po, list2);
            }
        }
        final int size = list2.size();
        T t;
        if (size == 0) {
            t = null;
        }
        else {
            final T cast = this.awz.cast(Array.newInstance(this.awz.getComponentType(), size));
            int n2 = n;
            while (true) {
                t = cast;
                if (n2 >= size) {
                    break;
                }
                Array.set(cast, n2, list2.get(n2));
                ++n2;
            }
        }
        return t;
    }
    
    private T n(final List<po> list) {
        if (list.isEmpty()) {
            return null;
        }
        return this.awz.cast(this.u(pe.p(list.get(list.size() - 1).awK)));
    }
    
    int A(final Object o) {
        if (this.awA) {
            return this.B(o);
        }
        return this.C(o);
    }
    
    protected int B(final Object o) {
        int n = 0;
        int n2;
        for (int length = Array.getLength(o), i = 0; i < length; ++i, n = n2) {
            n2 = n;
            if (Array.get(o, i) != null) {
                n2 = n + this.C(Array.get(o, i));
            }
        }
        return n;
    }
    
    protected int C(final Object o) {
        final int gh = pp.gH(this.tag);
        switch (this.type) {
            default: {
                throw new IllegalArgumentException("Unknown type " + this.type);
            }
            case 10: {
                return pf.b(gh, (pm)o);
            }
            case 11: {
                return pf.c(gh, (pm)o);
            }
        }
    }
    
    protected void a(final po po, final List<Object> list) {
        list.add(this.u(pe.p(po.awK)));
    }
    
    void a(final Object o, final pf pf) throws IOException {
        if (this.awA) {
            this.c(o, pf);
            return;
        }
        this.b(o, pf);
    }
    
    protected void b(final Object o, final pf pf) {
        while (true) {
            Label_0101: {
                Label_0076: {
                    while (true) {
                        Label_0110: {
                            try {
                                pf.gz(this.tag);
                                switch (this.type) {
                                    case 10: {
                                        break Label_0076;
                                    }
                                    case 11: {
                                        break Label_0101;
                                    }
                                    default: {
                                        break Label_0110;
                                    }
                                }
                                throw new IllegalArgumentException("Unknown type " + this.type);
                            }
                            catch (IOException ex) {
                                throw new IllegalStateException(ex);
                            }
                            break Label_0076;
                        }
                        continue;
                    }
                }
                final pm pm = (pm)o;
                final int gh = pp.gH(this.tag);
                pf.b(pm);
                pf.w(gh, 4);
                return;
            }
            pf.c((pm)o);
        }
    }
    
    protected void c(final Object o, final pf pf) {
        for (int length = Array.getLength(o), i = 0; i < length; ++i) {
            final Object value = Array.get(o, i);
            if (value != null) {
                this.b(value, pf);
            }
        }
    }
    
    final T l(final List<po> list) {
        if (list == null) {
            return null;
        }
        if (this.awA) {
            return this.m(list);
        }
        return this.n(list);
    }
    
    protected Object u(final pe pe) {
        if (!this.awA) {
            goto Label_0099;
        }
        while (true) {
            final Class<?> componentType = this.awz.getComponentType();
            while (true) {
                Label_0185: {
                    try {
                        switch (this.type) {
                            case 10: {
                                goto Label_0107;
                                goto Label_0107;
                            }
                            case 11: {
                                goto Label_0129;
                                goto Label_0129;
                            }
                            default: {
                                break Label_0185;
                            }
                        }
                        throw new IllegalArgumentException("Unknown type " + this.type);
                    }
                    catch (InstantiationException ex) {
                        throw new IllegalArgumentException("Error creating instance of class " + componentType, ex);
                    }
                    catch (IllegalAccessException ex2) {
                        throw new IllegalArgumentException("Error creating instance of class " + componentType, ex2);
                    }
                    catch (IOException ex3) {
                        throw new IllegalArgumentException("Error reading extension field", ex3);
                    }
                }
                continue;
            }
        }
    }
}
