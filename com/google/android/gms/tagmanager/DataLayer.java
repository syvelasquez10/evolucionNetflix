// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.concurrent.CountedCompleter;
import java.lang.reflect.Array;
import java.util.Spliterator;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.LockSupport;
import sun.misc.Contended;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.function.ToDoubleFunction;
import java.util.function.IntBinaryOperator;
import java.util.function.ToIntBiFunction;
import java.util.function.LongBinaryOperator;
import java.util.function.ToLongBiFunction;
import java.util.function.DoubleBinaryOperator;
import java.util.function.ToDoubleBiFunction;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Enumeration;
import java.util.function.Function;
import java.util.function.BiFunction;
import java.util.function.BiConsumer;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Set;
import java.lang.reflect.Type;
import java.lang.reflect.ParameterizedType;
import sun.misc.Unsafe;
import java.io.ObjectStreamField;
import java.io.Serializable;
import java.util.concurrent.ConcurrentMap;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.Iterator;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

public class DataLayer
{
    public static final String EVENT_KEY = "event";
    public static final Object OBJECT_NOT_PRESENT;
    static final String[] Xp;
    private static final Pattern Xq;
    private final ConcurrentHashMap<b, Integer> Xr;
    private final Map<String, Object> Xs;
    private final ReentrantLock Xt;
    private final LinkedList<Map<String, Object>> Xu;
    private final c Xv;
    private final CountDownLatch Xw;
    
    static {
        OBJECT_NOT_PRESENT = new Object();
        Xp = "gtm.lifetime".toString().split("\\.");
        Xq = Pattern.compile("(\\d+)\\s*([smhd]?)");
    }
    
    DataLayer() {
        this((c)new c() {
            @Override
            public void a(final c.a a) {
                a.a(new ArrayList<DataLayer.a>());
            }
            
            @Override
            public void a(final List<DataLayer.a> list, final long n) {
            }
            
            @Override
            public void bx(final String s) {
            }
        });
    }
    
    DataLayer(final c xv) {
        this.Xv = xv;
        this.Xr = new ConcurrentHashMap<b, Integer>();
        this.Xs = new HashMap<String, Object>();
        this.Xt = new ReentrantLock();
        this.Xu = new LinkedList<Map<String, Object>>();
        this.Xw = new CountDownLatch(1);
        this.ko();
    }
    
    private void A(final Map<String, Object> map) {
        this.Xt.lock();
        try {
            this.Xu.offer(map);
            if (this.Xt.getHoldCount() == 1) {
                this.kp();
            }
            this.B(map);
        }
        finally {
            this.Xt.unlock();
        }
    }
    
    private void B(final Map<String, Object> map) {
        final Long c = this.C(map);
        if (c == null) {
            return;
        }
        final List<a> e = this.E(map);
        e.remove("gtm.lifetime");
        this.Xv.a(e, c);
    }
    
    private Long C(final Map<String, Object> map) {
        final Object d = this.D(map);
        if (d == null) {
            return null;
        }
        return bw(d.toString());
    }
    
    private Object D(Map<String, Object> value) {
        final String[] xp = DataLayer.Xp;
        final int length = xp.length;
        int n = 0;
        Object o;
        while (true) {
            o = value;
            if (n >= length) {
                break;
            }
            final String s = xp[n];
            if (!(value instanceof Map)) {
                o = null;
                break;
            }
            value = ((Map<String, Object>)value).get(s);
            ++n;
        }
        return o;
    }
    
    private List<a> E(final Map<String, Object> map) {
        final ArrayList<a> list = new ArrayList<a>();
        this.a(map, "", list);
        return list;
    }
    
    private void F(final Map<String, Object> map) {
        synchronized (this.Xs) {
            for (final String s : map.keySet()) {
                this.a(this.c(s, map.get(s)), this.Xs);
            }
        }
        // monitorexit(map2)
        final Map<String, Object> map3;
        this.G(map3);
    }
    
    private void G(final Map<String, Object> map) {
        final Iterator<b> iterator = this.Xr.keySet().iterator();
        while (iterator.hasNext()) {
            iterator.next().y(map);
        }
    }
    
    private void a(final Map<String, Object> map, final String s, final Collection<a> collection) {
        for (final Map.Entry<String, Object> entry : map.entrySet()) {
            final StringBuilder append = new StringBuilder().append(s);
            String s2;
            if (s.length() == 0) {
                s2 = "";
            }
            else {
                s2 = ".";
            }
            final String string = append.append(s2).append(entry.getKey()).toString();
            if (entry.getValue() instanceof Map) {
                this.a(entry.getValue(), string, collection);
            }
            else {
                if (string.equals("gtm.lifetime")) {
                    continue;
                }
                collection.add(new a(string, entry.getValue()));
            }
        }
    }
    
    static Long bw(final String s) {
        final Matcher matcher = DataLayer.Xq.matcher(s);
        if (!matcher.matches()) {
            bh.x("unknown _lifetime: " + s);
            return null;
        }
        long long1;
        while (true) {
            try {
                long1 = Long.parseLong(matcher.group(1));
                if (long1 <= 0L) {
                    bh.x("non-positive _lifetime: " + s);
                    return null;
                }
            }
            catch (NumberFormatException ex) {
                bh.z("illegal number in _lifetime value: " + s);
                long1 = 0L;
                continue;
            }
            break;
        }
        final String group = matcher.group(2);
        if (group.length() == 0) {
            return long1;
        }
        switch (group.charAt(0)) {
            default: {
                bh.z("unknown units in _lifetime: " + s);
                return null;
            }
            case 's': {
                return long1 * 1000L;
            }
            case 'm': {
                return long1 * 1000L * 60L;
            }
            case 'h': {
                return long1 * 1000L * 60L * 60L;
            }
            case 'd': {
                return long1 * 1000L * 60L * 60L * 24L;
            }
        }
    }
    
    private void ko() {
        this.Xv.a((c.a)new c.a() {
            @Override
            public void a(final List<DataLayer.a> list) {
                for (final DataLayer.a a : list) {
                    DataLayer.this.A(DataLayer.this.c(a.Xy, a.Xz));
                }
                DataLayer.this.Xw.countDown();
            }
        });
    }
    
    private void kp() {
        int n = 0;
        while (true) {
            final Map<String, Object> map = this.Xu.poll();
            if (map == null) {
                return;
            }
            this.F(map);
            ++n;
            if (n > 500) {
                this.Xu.clear();
                throw new RuntimeException("Seems like an infinite loop of pushing to the data layer");
            }
        }
    }
    
    public static List<Object> listOf(final Object... array) {
        final ArrayList<Object> list = new ArrayList<Object>();
        for (int i = 0; i < array.length; ++i) {
            list.add(array[i]);
        }
        return list;
    }
    
    public static Map<String, Object> mapOf(final Object... array) {
        if (array.length % 2 != 0) {
            throw new IllegalArgumentException("expected even number of key-value pairs");
        }
        final HashMap<String, Object> hashMap = new HashMap<String, Object>();
        for (int i = 0; i < array.length; i += 2) {
            if (!(array[i] instanceof String)) {
                throw new IllegalArgumentException("key is not a string: " + array[i]);
            }
            hashMap.put((String)array[i], array[i + 1]);
        }
        return hashMap;
    }
    
    void a(final b b) {
        this.Xr.put(b, 0);
    }
    
    void a(final List<Object> list, final List<Object> list2) {
        while (list2.size() < list.size()) {
            list2.add(null);
        }
        for (int i = 0; i < list.size(); ++i) {
            final List<Object> value = list.get(i);
            if (value instanceof List) {
                if (!(list2.get(i) instanceof List)) {
                    list2.set(i, new ArrayList());
                }
                this.a(value, (List)list2.get(i));
            }
            else if (value instanceof Map) {
                if (!(list2.get(i) instanceof Map)) {
                    list2.set(i, new HashMap<String, Object>());
                }
                this.a((Map<String, Object>)value, list2.get(i));
            }
            else if (value != DataLayer.OBJECT_NOT_PRESENT) {
                list2.set(i, value);
            }
        }
    }
    
    void a(final Map<String, Object> map, final Map<String, Object> map2) {
        for (final String s : map.keySet()) {
            final ArrayList<Object> value = map.get(s);
            if (value instanceof List) {
                if (!(map2.get(s) instanceof List)) {
                    map2.put(s, new ArrayList<Object>());
                }
                this.a(value, map2.get(s));
            }
            else if (value instanceof Map) {
                if (!(map2.get(s) instanceof Map)) {
                    map2.put(s, new HashMap());
                }
                this.a((Map<String, Object>)value, (Map)map2.get(s));
            }
            else {
                map2.put(s, value);
            }
        }
    }
    
    void bv(final String s) {
        this.push(s, null);
        this.Xv.bx(s);
    }
    
    Map<String, Object> c(final String s, final Object o) {
        final HashMap<String, Map<String, Object>> hashMap = (HashMap<String, Map<String, Object>>)new HashMap<String, Map<String, Map<String, Object>>>();
        final String[] split = s.toString().split("\\.");
        int i = 0;
        Map<String, Object> map = (Map<String, Object>)hashMap;
        while (i < split.length - 1) {
            final HashMap<String, Map<String, Map<String, Object>>> hashMap2 = new HashMap<String, Map<String, Map<String, Object>>>();
            map.put(split[i], hashMap2);
            ++i;
            map = (Map<String, Object>)hashMap2;
        }
        map.put(split[split.length - 1], o);
        return (Map<String, Object>)hashMap;
    }
    
    public Object get(final String s) {
        while (true) {
            while (true) {
                int n;
                synchronized (this.Xs) {
                    final Map<String, Object> xs = this.Xs;
                    final String[] split = s.split("\\.");
                    final int length = split.length;
                    final Map<String, Object> map = xs;
                    n = 0;
                    if (n >= length) {
                        return map;
                    }
                    final String s2 = split[n];
                    if (!(map instanceof Map)) {
                        return null;
                    }
                    if (map.get(s2) == null) {
                        return null;
                    }
                }
                ++n;
                continue;
            }
        }
    }
    
    public void push(final String s, final Object o) {
        this.push(this.c(s, o));
    }
    
    public void push(final Map<String, Object> map) {
        while (true) {
            try {
                this.Xw.await();
                this.A(map);
            }
            catch (InterruptedException ex) {
                bh.z("DataLayer.push: unexpected InterruptedException");
                continue;
            }
            break;
        }
    }
    
    public void pushEvent(final String s, final Map<String, Object> map) {
        final HashMap<String, String> hashMap = new HashMap<String, String>((Map<? extends String, ? extends String>)map);
        hashMap.put("event", s);
        this.push((Map<String, Object>)hashMap);
    }
    
    static final class a
    {
        public final String Xy;
        public final Object Xz;
        
        a(final String xy, final Object xz) {
            this.Xy = xy;
            this.Xz = xz;
        }
        
        @Override
        public boolean equals(final Object o) {
            if (o instanceof a) {
                final a a = (a)o;
                if (this.Xy.equals(a.Xy) && this.Xz.equals(a.Xz)) {
                    return true;
                }
            }
            return false;
        }
        
        @Override
        public int hashCode() {
            return Arrays.hashCode(new Integer[] { this.Xy.hashCode(), this.Xz.hashCode() });
        }
        
        @Override
        public String toString() {
            return "Key: " + this.Xy + " value: " + this.Xz.toString();
        }
    }
    
    interface b
    {
        void y(final Map<String, Object> p0);
    }
    
    interface c
    {
        void a(final a p0);
        
        void a(final List<DataLayer.a> p0, final long p1);
        
        void bx(final String p0);
        
        public interface a
        {
            void a(final List<DataLayer.a> p0);
        }
    }
}
