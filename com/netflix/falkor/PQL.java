// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

import java.util.LinkedHashSet;
import com.google.gson.JsonElement;
import com.google.gson.JsonArray;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.util.Comparator;
import java.util.Arrays;
import com.netflix.mediaclient.NetflixApplication;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

public class PQL implements Cloneable
{
    public static final PQL EMPTY;
    private static final Gson gson;
    private final List keySegments;
    
    static {
        EMPTY = new PQL(new ArrayList(0));
        gson = NetflixApplication.getGson();
    }
    
    private PQL(final List keySegments) {
        this.keySegments = keySegments;
    }
    
    public static List array(final Object... array) {
        return Arrays.asList(array);
    }
    
    public static List<PQL> collapse(final List<PQL> list) {
        int size = list.size();
        PQL pql = null;
        PQL pql2 = null;
        int i = 1;
        boolean b = false;
        if (list.size() >= 2) {
            Collections.sort((List<Object>)list, (Comparator<? super Object>)new Comparator<PQL>() {
                @Override
                public int compare(final PQL pql, final PQL pql2) {
                    return comparePaths(pql, pql2);
                }
            });
            while (i != 0) {
                i = 0;
                int n = 0;
                int n2 = 0;
                int j = 1;
                int n3 = -1;
                while (j < size) {
                    final PQL pql3 = list.get(n2);
                    final List keySegments = pql3.keySegments;
                    final PQL pql4 = list.get(j);
                    final List keySegments2 = pql4.keySegments;
                    final Integer value = 100;
                    int n4 = n3;
                    Integer n5 = value;
                    if (keySegments.size() == keySegments2.size()) {
                        n4 = n3;
                        n5 = value;
                        if (keySegments.size() > 1) {
                            Integer value2 = 0;
                            int n6 = keySegments.size() - 1;
                            while (true) {
                                n4 = n3;
                                n5 = value2;
                                if (n6 < 0) {
                                    break;
                                }
                                n4 = n3;
                                n5 = value2;
                                if (value2 > 1) {
                                    break;
                                }
                                Integer value3 = value2;
                                if (!segmentsEqual(keySegments.get(n6), keySegments2.get(n6))) {
                                    value3 = value2 + 1;
                                    n3 = n6;
                                }
                                --n6;
                                value2 = value3;
                            }
                        }
                    }
                    if (n5 == 1) {
                        final List<Object> value4 = keySegments.get(n4);
                        List<Object> list2;
                        if (!(value4 instanceof List)) {
                            list2 = (List<Object>)new ArrayList<HashMap<String, Integer>>();
                            list2.add(value4);
                            keySegments.set(n4, list2);
                        }
                        else {
                            list2 = value4;
                        }
                        final Map<String, Integer> value5 = list2.get(list2.size() - 1);
                        Object o;
                        if (value5 instanceof Map) {
                            o = value5;
                        }
                        else {
                            o = null;
                        }
                        final Integer integer = parseInteger(value5);
                        final Integer integer2 = parseInteger(keySegments2.get(n4));
                        if (o != null && ((Map<K, Integer>)o).get("to") != null && integer2 != null && ((Map<K, Integer>)o).get("to").equals(integer2 - 1)) {
                            ((Map<K, Integer>)o).put((K)"to", ((Map<K, Integer>)o).get("to") + 1);
                        }
                        else if (integer != null && integer2 != null && integer.equals(integer2 - 1)) {
                            final HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
                            hashMap.put("from", integer);
                            hashMap.put("to", integer2);
                            list2.set(list2.size() - 1, hashMap);
                        }
                        else {
                            list2.add(keySegments2.get(n4));
                        }
                        i = 1;
                        b = true;
                        ++j;
                        n3 = n4;
                        pql = pql3;
                        pql2 = pql4;
                    }
                    else if (n5 == 0) {
                        i = 1;
                        b = true;
                        ++j;
                        n3 = n4;
                        pql = pql3;
                        pql2 = pql4;
                    }
                    else {
                        b = false;
                        n3 = -1;
                        n2 = j;
                        ++j;
                        list.set(n, pql3);
                        ++n;
                        pql = pql3;
                        pql2 = pql4;
                    }
                }
                if (!b) {
                    list.set(n, pql2);
                }
                else if (i != 0) {
                    list.set(n, pql);
                }
                size = n + 1;
            }
            for (int k = list.size() - 1; k >= size; --k) {
                list.remove(k);
            }
            final Iterator<PQL> iterator = list.iterator();
            while (iterator.hasNext()) {
                sanitize(iterator.next().getKeySegments());
            }
        }
        return list;
    }
    
    private static int comparePaths(final PQL pql, final PQL pql2) {
        final List keySegments = pql.keySegments;
        final List keySegments2 = pql2.keySegments;
        final int size = keySegments.size();
        final int size2 = keySegments2.size();
        int n = size - size2;
        final ArrayList<Integer> list = new ArrayList<Integer>();
        final ArrayList<Integer> list2 = new ArrayList<Integer>();
        if (n == 0) {
            for (int i = 0; i < size; ++i) {
                if (keySegments.get(i) instanceof String) {
                    list2.add(i);
                }
                else {
                    list.add(i);
                }
            }
            list2.addAll((Collection<?>)list);
            int j = 0;
            while (j < size) {
                final int intValue = list2.get(j);
                final List<Object> value = keySegments.get(intValue);
                final List<Object> value2 = keySegments2.get(intValue);
                Object o;
                if (value instanceof List) {
                    o = value;
                }
                else {
                    o = null;
                }
                Object o2;
                if (value2 instanceof List) {
                    o2 = value2;
                }
                else {
                    o2 = null;
                }
                Object o3 = value;
                List<Map<K, Object>> list3 = (List<Map<K, Object>>)o;
                if (o != null) {
                    o3 = value;
                    list3 = (List<Map<K, Object>>)o;
                    if (((List)o).size() != 0) {
                        o3 = value;
                        list3 = (List<Map<K, Object>>)o;
                        if (o2 == null) {
                            o3 = ((List<Map<K, Object>>)o).get(0);
                            list3 = null;
                        }
                    }
                }
                Object o4 = value2;
                List<Map<K, Object>> list4;
                if ((list4 = (List<Map<K, Object>>)o2) != null) {
                    o4 = value2;
                    list4 = (List<Map<K, Object>>)o2;
                    if (((List)o2).size() == 1) {
                        o4 = value2;
                        list4 = (List<Map<K, Object>>)o2;
                        if (list3 == null) {
                            o4 = ((List<Map<K, Object>>)o2).get(0);
                            list4 = null;
                        }
                    }
                }
                if (list3 != null && list4 != null) {
                    final int n2 = list4.size() - list4.size();
                    if (n2 != 0) {
                        return n2;
                    }
                    for (int k = 0; k < list3.size(); ++k) {
                        final Map<K, Object> value3 = list3.get(k);
                        final Map<K, Object> value4 = list4.get(k);
                        Object value5 = value3;
                        if (value3 instanceof Map) {
                            value5 = value3.get("from");
                        }
                        Object value6 = value4;
                        if (value4 instanceof Map) {
                            value6 = value4.get("from");
                        }
                        final Double double1 = parseDouble(value5);
                        final Double double2 = parseDouble(value6);
                        if (double2 != null && double2 != null && !double1.equals(double2)) {
                            final double n3 = double1 - double2;
                            if (n3 != 0.0) {
                                return (int)n3;
                            }
                        }
                        else if ((n = value5.toString().compareTo(value6.toString())) != 0) {
                            return n;
                        }
                    }
                    return 0;
                }
                else {
                    Map<K, Map<K, Map>> map;
                    if (o3 instanceof Map) {
                        map = (Map<K, Map<K, Map>>)o3;
                    }
                    else {
                        map = null;
                    }
                    if (map != null && (o3 = map.get("from")) == null) {
                        o3 = 0;
                    }
                    Map<K, Map<K, Map>> map2;
                    if (o4 instanceof Map) {
                        map2 = (Map<K, Map<K, Map>>)o4;
                    }
                    else {
                        map2 = null;
                    }
                    if (map2 != null && (o4 = map2.get("from")) == null) {
                        o4 = 0;
                    }
                    final Double double3 = parseDouble(o3);
                    final Double double4 = parseDouble(o4);
                    if (double3 != null && double4 != null && !double3.equals(double4)) {
                        return (int)(double3 - double4);
                    }
                    if ((n = o3.toString().compareTo(o4.toString())) != 0) {
                        return n;
                    }
                    ++j;
                }
            }
            if (size > size2) {
                return 1;
            }
            if (size2 > size) {
                return -1;
            }
            return 0;
        }
        return n;
    }
    
    public static PQL create(final Object... array) {
        return new PQL(sanitize(Arrays.asList(array)));
    }
    
    public static PQL fromJsonArray(final JsonArray jsonArray) {
        final ArrayList<String> list = new ArrayList<String>(jsonArray.size());
        final Iterator<JsonElement> iterator = jsonArray.iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next().getAsString());
        }
        return fromList(list);
    }
    
    public static PQL fromList(final List list) {
        return new PQL(sanitize(list));
    }
    
    private static boolean objectEquals(final Object o, final Object o2) {
        boolean b = false;
        if (o == null) {
            if (o2 == null) {
                b = true;
            }
        }
        else if (o2 != null) {
            return o.equals(o2);
        }
        return b;
    }
    
    private static Double parseDouble(final Object o) {
        if (o instanceof Map) {
            return null;
        }
        if (o instanceof String) {
            try {
                return Double.parseDouble((String)o);
            }
            catch (Exception ex) {
                return null;
            }
        }
        if (o instanceof Integer) {
            return (double)o;
        }
        return (Double)o;
    }
    
    private static Integer parseInteger(final Object o) {
        if (o instanceof Map) {
            return null;
        }
        if (o instanceof String) {
            try {
                return Integer.parseInt((String)o);
            }
            catch (Exception ex) {
                return null;
            }
        }
        return (Integer)o;
    }
    
    public static Map range(final int n) {
        return range(0, n);
    }
    
    public static Map range(final int n, final int n2) {
        final HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        hashMap.put("from", n);
        hashMap.put("to", n2);
        return hashMap;
    }
    
    private static List sanitize(final List list) {
        for (int i = 0; i < list.size(); ++i) {
            final List<Object> value = list.get(i);
            if (value instanceof List) {
                final List<Object> list2 = value;
                if (list2 != null && list2.size() == 1) {
                    list.set(i, sanitizeKey(value));
                }
                else {
                    for (int j = 0; j < list2.size(); ++j) {
                        list2.set(j, sanitizeKey(list2.get(j)));
                    }
                }
            }
            else {
                list.set(i, sanitizeKey(value));
            }
        }
        return list;
    }
    
    private static Object sanitizeKey(final Object o) {
        Object value = o;
        if (o instanceof List) {
            final List list = (List)o;
            value = o;
            if (list != null) {
                value = o;
                if (list.size() == 1) {
                    value = list.get(0);
                }
            }
        }
        if (value instanceof Map) {
            final Map<K, Object> map = (Map<K, Object>)value;
            final Object value2 = map.get("from");
            if (value2 == null) {
                map.put((K)"from", 0);
            }
            else if (value2 instanceof Long) {
                map.put((K)"from", (int)value2);
            }
            final Object value3 = map.get("to");
            if (value3 instanceof Long) {
                map.put((K)"to", (int)value3);
            }
        }
        else if (!(value instanceof String)) {
            return value.toString();
        }
        return value;
    }
    
    private static boolean segmentsEqual(final Object o, final Object o2) {
        if (objectEquals(o.toString(), o2.toString())) {
            return true;
        }
        Map<K, Object> map;
        if (o instanceof Map) {
            map = (Map<K, Object>)o;
        }
        else {
            map = null;
        }
        Map<K, Object> map2;
        if (o2 instanceof Map) {
            map2 = (Map<K, Object>)o2;
        }
        else {
            map2 = null;
        }
        if (map != null && map2 != null && map.get("to") != null && objectEquals(map.get("to"), map2.get("to")) && objectEquals(map.get("from"), map2.get("from"))) {
            return true;
        }
        List<Object> list;
        if (o instanceof List) {
            list = (List<Object>)o;
        }
        else {
            list = null;
        }
        List<Object> list2;
        if (o2 instanceof List) {
            list2 = (List<Object>)o2;
        }
        else {
            list2 = null;
        }
        if (list != null && list2 != null && list.size() == list2.size()) {
            int n;
            for (n = list.size() - 1; n >= 0 && segmentsEqual(list.get(n), list2.get(n)); --n) {}
            if (n < 0) {
                return true;
            }
        }
        return false;
    }
    
    public PQL addPathSegments(final List list) {
        final ArrayList list2 = new ArrayList((Collection<? extends E>)this.getKeySegments());
        list2.addAll(list);
        return new PQL(list2);
    }
    
    public PQL append(final PQL pql) {
        final ArrayList list = new ArrayList(this.keySegments);
        list.addAll(pql.keySegments);
        return new PQL(list);
    }
    
    public PQL append(final Object o) {
        final ArrayList<Object> list = new ArrayList<Object>(this.keySegments);
        list.add(sanitizeKey(o));
        return new PQL(list);
    }
    
    public Object clone() {
        return new PQL(new ArrayList(this.keySegments));
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null) {
                return false;
            }
            if (!(o instanceof PQL)) {
                return false;
            }
            final PQL pql = (PQL)o;
            if (this.keySegments == null) {
                if (pql.keySegments != null) {
                    return false;
                }
            }
            else if (!this.keySegments.equals(pql.keySegments)) {
                return false;
            }
        }
        return true;
    }
    
    public LinkedHashSet<PQL> explode() {
        final List keySegments = this.keySegments;
        final LinkedHashSet<PQL> set = new LinkedHashSet<PQL>();
        final Map<K, Integer> map = null;
        final Object[] array = new Object[this.keySegments.size()];
        final Integer[] array2 = new Integer[this.keySegments.size()];
        final Integer[] array3 = new Integer[this.keySegments.size()];
        final Integer[] array4 = new Integer[this.keySegments.size()];
        final int n = 0;
        if (keySegments.size() == 0) {
            set.add(new PQL(keySegments));
        }
        else {
            final int n2 = keySegments.size() - 1;
            int n3 = 0;
            int i;
            Object o;
            while (true) {
                i = n;
                o = map;
                if (n3 > n2) {
                    break;
                }
                final List<Object> value = keySegments.get(n3);
                array2[n3] = 0;
                array3[n3] = null;
                int n4;
                if (value instanceof List) {
                    n4 = value.size() - 1;
                }
                else {
                    n4 = 0;
                }
                array4[n3] = n4;
                ++n3;
            }
            while (i >= 0) {
                while (true) {
                    int j = i;
                    Object o2 = o;
                    while (j <= n2) {
                        Object o3;
                        final List<Object> list = (List<Object>)(o3 = keySegments.get(j));
                        if (list instanceof List) {
                            o3 = list.get(array2[j]);
                        }
                        if (o3 instanceof Map) {
                            o2 = o3;
                        }
                        else {
                            o2 = null;
                        }
                        Integer n5;
                        if (o2 != null) {
                            n5 = ((Map<K, Integer>)o2).get("to");
                        }
                        else {
                            n5 = null;
                        }
                        if (n5 != null) {
                            int intValue;
                            if (((Map<K, Integer>)o2).get("from") != null) {
                                intValue = ((Map<K, Integer>)o2).get("from");
                            }
                            else {
                                intValue = 0;
                            }
                            final Integer value2 = intValue;
                            if (n5 < value2) {
                                throw new RuntimeException("Invalid range in path.  Range to value is smaller than from value (" + value2 + " > " + n5 + ")");
                            }
                            if (array3[j] == null) {
                                array3[j] = value2;
                            }
                            array[j] = array3[j].toString();
                        }
                        else {
                            array[j] = o3.toString();
                        }
                        ++j;
                    }
                    set.add(new PQL(Arrays.asList((Object[])array.clone())));
                    int k = n2;
                    if (array3[k] != null) {
                        final Integer n6 = array3[k];
                        ++array3[k];
                        if (o2 != null && array3[k] > ((Map<K, Integer>)o2).get("to")) {
                            array3[k] = null;
                            final Integer n7 = array2[k];
                            ++array2[k];
                        }
                    }
                    else {
                        final Integer n8 = array2[k];
                        ++array2[k];
                    }
                    i = k;
                    o = o2;
                    if (array2[k] > array4[k]) {
                        array2[k] = array4[k];
                        while (k >= 0) {
                            final int intValue2 = array2[k];
                            Object o4 = keySegments.get(k);
                            if (o4 instanceof List) {
                                o4 = ((List<Object>)o4).get(array2[k]);
                            }
                            Object o5;
                            if (o4 instanceof Map) {
                                o5 = o4;
                            }
                            else {
                                o5 = null;
                            }
                            Integer n9;
                            if (o5 != null) {
                                n9 = ((Map<K, Integer>)o5).get("to");
                            }
                            else {
                                n9 = null;
                            }
                            Integer n10;
                            if (o5 != null) {
                                n10 = ((Map<K, Integer>)o5).get("from");
                            }
                            else {
                                n10 = null;
                            }
                            if (intValue2 != array4[k] || (array3[k] != null && !array3[k].equals(n9))) {
                                break;
                            }
                            array2[k] = 0;
                            final List<Object> value3 = keySegments.get(k);
                            if (value3 instanceof List) {
                                value3.get(array2[k]);
                            }
                            Integer value4;
                            if (n9 != null) {
                                int intValue3;
                                if (n10 != null) {
                                    intValue3 = n10;
                                }
                                else {
                                    intValue3 = 0;
                                }
                                value4 = intValue3;
                            }
                            else {
                                value4 = null;
                            }
                            array3[k] = value4;
                            --k;
                        }
                        i = k;
                        o = o2;
                        if (k < 0) {
                            break;
                        }
                        Object o6 = keySegments.get(k);
                        if (o6 instanceof List) {
                            o6 = ((List<Object>)o6).get(array2[k]);
                        }
                        Object o7;
                        if (o6 instanceof Map) {
                            o7 = o6;
                        }
                        else {
                            o7 = null;
                        }
                        Integer n11;
                        if (o7 != null) {
                            n11 = ((Map<K, Integer>)o7).get("to");
                        }
                        else {
                            n11 = null;
                        }
                        Integer n12;
                        if (o6 instanceof Map) {
                            n12 = ((Map<K, Integer>)o7).get("from");
                        }
                        else {
                            n12 = null;
                        }
                        if (n11 != null) {
                            int n13;
                            if (array3[k] == null) {
                                if (n12 != null) {
                                    n13 = n12;
                                }
                                else {
                                    n13 = 0;
                                }
                            }
                            else {
                                n13 = array3[k];
                            }
                            array3[k] = n13;
                        }
                        if (array3[k] != null && !array3[k].equals(n11)) {
                            final Integer n14 = array3[k];
                            ++array3[k];
                            o = o2;
                            i = k;
                            break;
                        }
                        array3[k] = null;
                        final Integer n15 = array2[k];
                        ++array2[k];
                        i = k;
                        o = o2;
                        break;
                    }
                }
            }
        }
        return set;
    }
    
    public List<Object> getKeySegments() {
        return (List<Object>)this.keySegments;
    }
    
    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }
    
    public PQL prepend(final PQL pql) {
        final ArrayList list = new ArrayList(pql.keySegments);
        list.addAll(this.keySegments);
        return new PQL(list);
    }
    
    public PQL replaceKeySegment(final int n, final Object o) {
        final List<Object> list = IterableBuilder.fromList(this.getKeySegments()).toList();
        list.set(n, o);
        return new PQL(list);
    }
    
    public PQL rewrite(final PQL pql, int i) {
        final ArrayList<Object> list = new ArrayList<Object>(pql.getKeySegments());
        while (i < this.keySegments.size()) {
            list.add(this.keySegments.get(i));
            ++i;
        }
        return new PQL(list);
    }
    
    public PQL slice(final int n) {
        return this.slice(n, this.keySegments.size() - n);
    }
    
    public PQL slice(final int n, final int n2) {
        final ArrayList<Object> list = new ArrayList<Object>();
        for (int i = n; i < n + n2; ++i) {
            list.add(this.keySegments.get(i));
        }
        return new PQL(list);
    }
    
    @Override
    public String toString() {
        return PQL.gson.toJson(this.keySegments);
    }
}
