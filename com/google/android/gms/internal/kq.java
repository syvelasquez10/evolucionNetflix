// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class kq<M extends kp<M>, T>
{
    protected final Class<T> adV;
    protected final boolean adW;
    protected final int tag;
    protected final int type;
    
    private kq(final int type, final Class<T> adV, final int tag, final boolean adW) {
        this.type = type;
        this.adV = adV;
        this.tag = tag;
        this.adW = adW;
    }
    
    public static <M extends kp<M>, T extends kt> kq<M, T> a(final int n, final Class<T> clazz, final int n2) {
        return new kq<M, T>(n, clazz, n2, false);
    }
    
    protected void a(final kv kv, final List<Object> list) {
        list.add(this.o(kn.n(kv.adZ)));
    }
    
    protected boolean dd(final int n) {
        return n == this.tag;
    }
    
    final T f(final List<kv> list) {
        final int n = 0;
        T t;
        if (list == null) {
            t = null;
        }
        else if (this.adW) {
            final ArrayList<Object> list2 = new ArrayList<Object>();
            for (int i = 0; i < list.size(); ++i) {
                final kv kv = list.get(i);
                if (this.dd(kv.tag) && kv.adZ.length != 0) {
                    this.a(kv, list2);
                }
            }
            final int size = list2.size();
            if (size == 0) {
                return null;
            }
            final T cast = this.adV.cast(Array.newInstance(this.adV.getComponentType(), size));
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
        else {
            int n3;
            kv kv2;
            kv kv3;
            for (n3 = list.size() - 1, kv2 = null; kv2 == null && n3 >= 0; --n3) {
                kv3 = list.get(n3);
                if (this.dd(kv3.tag) && kv3.adZ.length != 0) {
                    kv2 = kv3;
                }
            }
            if (kv2 == null) {
                return null;
            }
            return this.adV.cast(this.o(kn.n(kv2.adZ)));
        }
        return t;
    }
    
    protected Object o(final kn kn) {
        if (!this.adW) {
            goto Label_0099;
        }
        while (true) {
            final Class<?> componentType = this.adV.getComponentType();
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
