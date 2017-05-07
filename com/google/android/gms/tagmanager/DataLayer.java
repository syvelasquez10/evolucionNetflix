// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.concurrent.CountedCompleter;
import java.lang.reflect.Array;
import java.util.Arrays;
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
import java.util.regex.Matcher;
import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

public class DataLayer
{
    public static final Object OBJECT_NOT_PRESENT;
    private static final Pattern zzaPA;
    static final String[] zzaPz;
    private final ConcurrentHashMap<DataLayer$zzb, Integer> zzaPB;
    private final Map<String, Object> zzaPC;
    private final ReentrantLock zzaPD;
    private final LinkedList<Map<String, Object>> zzaPE;
    private final DataLayer$zzc zzaPF;
    private final CountDownLatch zzaPG;
    
    static {
        OBJECT_NOT_PRESENT = new Object();
        zzaPz = "gtm.lifetime".toString().split("\\.");
        zzaPA = Pattern.compile("(\\d+)\\s*([smhd]?)");
    }
    
    DataLayer() {
        this(new DataLayer$1());
    }
    
    DataLayer(final DataLayer$zzc zzaPF) {
        this.zzaPF = zzaPF;
        this.zzaPB = new ConcurrentHashMap<DataLayer$zzb, Integer>();
        this.zzaPC = new HashMap<String, Object>();
        this.zzaPD = new ReentrantLock();
        this.zzaPE = new LinkedList<Map<String, Object>>();
        this.zzaPG = new CountDownLatch(1);
        this.zzzO();
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
    
    private void zzJ(final Map<String, Object> map) {
        this.zzaPD.lock();
        try {
            this.zzaPE.offer(map);
            if (this.zzaPD.getHoldCount() == 1) {
                this.zzzP();
            }
            this.zzK(map);
        }
        finally {
            this.zzaPD.unlock();
        }
    }
    
    private void zzK(final Map<String, Object> map) {
        final Long zzL = this.zzL(map);
        if (zzL == null) {
            return;
        }
        final List<DataLayer$zza> zzN = this.zzN(map);
        zzN.remove("gtm.lifetime");
        this.zzaPF.zza(zzN, zzL);
    }
    
    private Long zzL(final Map<String, Object> map) {
        final Object zzM = this.zzM(map);
        if (zzM == null) {
            return null;
        }
        return zzeD(zzM.toString());
    }
    
    private Object zzM(Map<String, Object> value) {
        final String[] zzaPz = DataLayer.zzaPz;
        final int length = zzaPz.length;
        int n = 0;
        Object o;
        while (true) {
            o = value;
            if (n >= length) {
                break;
            }
            final String s = zzaPz[n];
            if (!(value instanceof Map)) {
                o = null;
                break;
            }
            value = ((Map<String, Object>)value).get(s);
            ++n;
        }
        return o;
    }
    
    private List<DataLayer$zza> zzN(final Map<String, Object> map) {
        final ArrayList<DataLayer$zza> list = new ArrayList<DataLayer$zza>();
        this.zza(map, "", list);
        return list;
    }
    
    private void zzO(final Map<String, Object> map) {
        synchronized (this.zzaPC) {
            for (final String s : map.keySet()) {
                this.zzc(this.zzj(s, map.get(s)), this.zzaPC);
            }
        }
        // monitorexit(map2)
        final Map<String, Object> map3;
        this.zzP(map3);
    }
    
    private void zzP(final Map<String, Object> map) {
        final Iterator<DataLayer$zzb> iterator = this.zzaPB.keySet().iterator();
        while (iterator.hasNext()) {
            iterator.next().zzH(map);
        }
    }
    
    private void zza(final Map<String, Object> map, final String s, final Collection<DataLayer$zza> collection) {
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
                this.zza(entry.getValue(), string, collection);
            }
            else {
                if (string.equals("gtm.lifetime")) {
                    continue;
                }
                collection.add(new DataLayer$zza(string, entry.getValue()));
            }
        }
    }
    
    static Long zzeD(final String s) {
        final Matcher matcher = DataLayer.zzaPA.matcher(s);
        if (!matcher.matches()) {
            zzbg.zzaD("unknown _lifetime: " + s);
            return null;
        }
        long long1;
        while (true) {
            try {
                long1 = Long.parseLong(matcher.group(1));
                if (long1 <= 0L) {
                    zzbg.zzaD("non-positive _lifetime: " + s);
                    return null;
                }
            }
            catch (NumberFormatException ex) {
                zzbg.zzaE("illegal number in _lifetime value: " + s);
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
                zzbg.zzaE("unknown units in _lifetime: " + s);
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
    
    private void zzzO() {
        this.zzaPF.zza(new DataLayer$2(this));
    }
    
    private void zzzP() {
        int n = 0;
        while (true) {
            final Map<String, Object> map = this.zzaPE.poll();
            if (map == null) {
                return;
            }
            this.zzO(map);
            ++n;
            if (n > 500) {
                this.zzaPE.clear();
                throw new RuntimeException("Seems like an infinite loop of pushing to the data layer");
            }
        }
    }
    
    public Object get(final String s) {
        while (true) {
            while (true) {
                int n;
                synchronized (this.zzaPC) {
                    final Map<String, Object> zzaPC = this.zzaPC;
                    final String[] split = s.split("\\.");
                    final int length = split.length;
                    final Map<String, Object> map = zzaPC;
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
        this.push(this.zzj(s, o));
    }
    
    public void push(final Map<String, Object> map) {
        while (true) {
            try {
                this.zzaPG.await();
                this.zzJ(map);
            }
            catch (InterruptedException ex) {
                zzbg.zzaE("DataLayer.push: unexpected InterruptedException");
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
    
    @Override
    public String toString() {
        synchronized (this.zzaPC) {
            final StringBuilder sb = new StringBuilder();
            for (final Map.Entry<String, Object> entry : this.zzaPC.entrySet()) {
                sb.append(String.format("{\n\tKey: %s\n\tValue: %s\n}\n", entry.getKey(), entry.getValue()));
            }
        }
        final StringBuilder sb2;
        // monitorexit(map)
        return sb2.toString();
    }
    
    void zza(final DataLayer$zzb dataLayer$zzb) {
        this.zzaPB.put(dataLayer$zzb, 0);
    }
    
    void zzb(final List<Object> list, final List<Object> list2) {
        while (list2.size() < list.size()) {
            list2.add(null);
        }
        for (int i = 0; i < list.size(); ++i) {
            final List<Object> value = list.get(i);
            if (value instanceof List) {
                if (!(list2.get(i) instanceof List)) {
                    list2.set(i, new ArrayList());
                }
                this.zzb(value, (List)list2.get(i));
            }
            else if (value instanceof Map) {
                if (!(list2.get(i) instanceof Map)) {
                    list2.set(i, new HashMap<String, Object>());
                }
                this.zzc((Map<String, Object>)value, list2.get(i));
            }
            else if (value != DataLayer.OBJECT_NOT_PRESENT) {
                list2.set(i, value);
            }
        }
    }
    
    void zzc(final Map<String, Object> map, final Map<String, Object> map2) {
        for (final String s : map.keySet()) {
            final ArrayList<Object> value = map.get(s);
            if (value instanceof List) {
                if (!(map2.get(s) instanceof List)) {
                    map2.put(s, new ArrayList<Object>());
                }
                this.zzb(value, map2.get(s));
            }
            else if (value instanceof Map) {
                if (!(map2.get(s) instanceof Map)) {
                    map2.put(s, new HashMap());
                }
                this.zzc((Map<String, Object>)value, (Map)map2.get(s));
            }
            else {
                map2.put(s, value);
            }
        }
    }
    
    void zzeC(final String s) {
        this.push(s, null);
        this.zzaPF.zzeE(s);
    }
    
    Map<String, Object> zzj(final String s, final Object o) {
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
}
