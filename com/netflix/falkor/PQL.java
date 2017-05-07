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
import java.util.Comparator;
import java.util.Collections;
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
    
    public static void collapse(final List<PQL> list) {
        int size = list.size();
        PQL pql = null;
        PQL pql2 = null;
        int i = 1;
        boolean b = false;
        if (list.size() >= 2) {
            Collections.sort((List<Object>)list, (Comparator<? super Object>)new PQL$1());
            while (i != 0) {
                boolean b2 = false;
                int n = 0;
                int n2 = 0;
                int j = 1;
                int n3 = -1;
                while (j < size) {
                    pql = list.get(n2);
                    final List keySegments = pql.keySegments;
                    pql2 = list.get(j);
                    final List keySegments2 = pql2.keySegments;
                    Integer n4;
                    if (keySegments.size() == keySegments2.size() && keySegments.size() > 1) {
                        n4 = 0;
                        final int n5 = keySegments.size() - 1;
                        int n6 = n3;
                        Integer value;
                        for (int n7 = n5; n7 >= 0 && n4 <= 1; --n7, n4 = value) {
                            value = n4;
                            if (!segmentsEqual(keySegments.get(n7), keySegments2.get(n7))) {
                                value = n4 + 1;
                                n6 = n7;
                            }
                        }
                        n3 = n6;
                    }
                    else {
                        n4 = 100;
                    }
                    if (n4 == 1) {
                        final List<Object> value2 = keySegments.get(n3);
                        List<Object> list2;
                        if (!(value2 instanceof List)) {
                            list2 = (List<Object>)new ArrayList<HashMap<String, Integer>>();
                            list2.add(value2);
                            keySegments.set(n3, list2);
                        }
                        else {
                            list2 = value2;
                        }
                        final Map<String, Integer> value3 = list2.get(list2.size() - 1);
                        Object o;
                        if (value3 instanceof Map) {
                            o = value3;
                        }
                        else {
                            o = null;
                        }
                        final Integer integer = parseInteger(value3);
                        final Integer integer2 = parseInteger(keySegments2.get(n3));
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
                            list2.add(keySegments2.get(n3));
                        }
                        b2 = true;
                        b = true;
                        ++j;
                    }
                    else if (n4 == 0) {
                        b2 = true;
                        b = true;
                        ++j;
                    }
                    else {
                        b = false;
                        list.set(n, pql);
                        ++n;
                        n2 = j;
                        ++j;
                        n3 = -1;
                    }
                }
                if (!b) {
                    list.set(n, pql2);
                }
                else if (b2) {
                    list.set(n, pql);
                }
                size = n + 1;
                i = (b2 ? 1 : 0);
            }
            for (int k = list.size() - 1; k >= size; --k) {
                list.remove(k);
            }
            final Iterator<PQL> iterator = list.iterator();
            while (iterator.hasNext()) {
                sanitize(iterator.next().getKeySegments());
            }
        }
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
                Object o3;
                List<Map<K, Object>> list3;
                if (o != null && ((List)o).size() == 1 && o2 == null) {
                    o3 = ((List<Map<K, Object>>)o).get(0);
                    list3 = null;
                }
                else {
                    list3 = (List<Map<K, Object>>)o;
                    o3 = value;
                }
                Object o4;
                List<Map<K, Object>> list4;
                if (o2 != null && ((List)o2).size() == 1 && list3 == null) {
                    o4 = ((List<Map<K, Object>>)o2).get(0);
                    list4 = null;
                }
                else {
                    list4 = (List<Map<K, Object>>)o2;
                    o4 = value2;
                }
                if (list3 != null && list4 != null) {
                    if ((n = list4.size() - list4.size()) == 0) {
                        for (int k = 0; k < list3.size(); ++k) {
                            Object o5 = list3.get(k);
                            final Map<K, Object> value3 = list4.get(k);
                            if (o5 instanceof Map) {
                                o5 = ((Map<K, Object>)o5).get("from");
                            }
                            Object value4 = value3;
                            if (value3 instanceof Map) {
                                value4 = value3.get("from");
                            }
                            final Double double1 = parseDouble(o5);
                            final Double double2 = parseDouble(value4);
                            if (double2 != null && double2 != null && !double1.equals(double2)) {
                                final double n2 = double1 - double2;
                                if (n2 != 0.0) {
                                    return (int)n2;
                                }
                            }
                            else if ((n = o5.toString().compareTo(value4.toString())) != 0) {
                                return n;
                            }
                        }
                        return 0;
                    }
                    return n;
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
    
    private static Object sanitizeKey(Object value) {
        if (value instanceof List) {
            final List list = (List)value;
            if (list != null && list.size() == 1) {
                value = list.get(0);
            }
        }
        while (true) {
            if (value instanceof Map) {
                final Map map = (Map)value;
                final Long value2 = map.get("from");
                if (value2 == null) {
                    map.put("from", 0);
                }
                else if (value2 instanceof Long) {
                    map.put("from", (int)(Object)value2);
                }
                final Long value3 = map.get("to");
                if (value3 instanceof Long) {
                    map.put("to", (int)(Object)value3);
                }
            }
            else if (!(value instanceof String)) {
                return value.toString();
            }
            return value;
            continue;
        }
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
        final Object[] array = new Object[this.keySegments.size()];
        final Integer[] array2 = new Integer[this.keySegments.size()];
        final Integer[] array3 = new Integer[this.keySegments.size()];
        final Integer[] array4 = new Integer[this.keySegments.size()];
        if (keySegments.size() == 0) {
            set.add(new PQL(keySegments));
            return set;
        }
        final int n = keySegments.size() - 1;
        for (int i = 0; i <= n; ++i) {
            final List<Object> value = keySegments.get(i);
            array2[i] = 0;
            array3[i] = null;
            int n2;
            if (value instanceof List) {
                n2 = value.size() - 1;
            }
            else {
                n2 = 0;
            }
            array4[i] = n2;
        }
        int j = 0;
        Object o = null;
        while (j >= 0) {
            while (true) {
                if (j <= n) {
                    Object o2 = keySegments.get(j);
                    if (o2 instanceof List) {
                        o2 = ((List<Object>)o2).get(array2[j]);
                    }
                    if (o2 instanceof Map) {
                        o = o2;
                    }
                    else {
                        o = null;
                    }
                    Integer n3;
                    if (o != null) {
                        n3 = ((Map<K, Integer>)o).get("to");
                    }
                    else {
                        n3 = null;
                    }
                    if (n3 != null) {
                        int intValue;
                        if (((Map<K, Integer>)o).get("from") != null) {
                            intValue = ((Map<K, Integer>)o).get("from");
                        }
                        else {
                            intValue = 0;
                        }
                        final Integer value2 = intValue;
                        if (n3 < value2) {
                            throw new RuntimeException("Invalid range in path.  Range to value is smaller than from value (" + value2 + " > " + n3 + ")");
                        }
                        if (array3[j] == null) {
                            array3[j] = value2;
                        }
                        array[j] = array3[j].toString();
                    }
                    else {
                        array[j] = o2.toString();
                    }
                    ++j;
                }
                else {
                    set.add(new PQL(Arrays.asList((Object[])array.clone())));
                    if (array3[n] != null) {
                        final Integer n4 = array3[n];
                        ++array3[n];
                        if (o != null && array3[n] > ((Map<K, Integer>)o).get("to")) {
                            array3[n] = null;
                            final Integer n5 = array2[n];
                            ++array2[n];
                        }
                    }
                    else {
                        final Integer n6 = array2[n];
                        ++array2[n];
                    }
                    if (array2[n] > array4[n]) {
                        array2[n] = array4[n];
                        for (j = n; j >= 0; --j) {
                            final int intValue2 = array2[j];
                            Object o3;
                            final List<Object> list = (List<Object>)(o3 = keySegments.get(j));
                            if (list instanceof List) {
                                o3 = list.get(array2[j]);
                            }
                            Object o4;
                            if (o3 instanceof Map) {
                                o4 = o3;
                            }
                            else {
                                o4 = null;
                            }
                            Integer n7;
                            if (o4 != null) {
                                n7 = ((Map<K, Integer>)o4).get("to");
                            }
                            else {
                                n7 = null;
                            }
                            Integer n8;
                            if (o4 != null) {
                                n8 = ((Map<K, Integer>)o4).get("from");
                            }
                            else {
                                n8 = null;
                            }
                            if (intValue2 != array4[j] || (array3[j] != null && !array3[j].equals(n7))) {
                                break;
                            }
                            array2[j] = 0;
                            final List<Object> value3 = keySegments.get(j);
                            if (value3 instanceof List) {
                                value3.get(array2[j]);
                            }
                            Integer value4;
                            if (n7 != null) {
                                int intValue3;
                                if (n8 != null) {
                                    intValue3 = n8;
                                }
                                else {
                                    intValue3 = 0;
                                }
                                value4 = intValue3;
                            }
                            else {
                                value4 = null;
                            }
                            array3[j] = value4;
                        }
                        if (j < 0) {
                            break;
                        }
                        Object o5 = keySegments.get(j);
                        if (o5 instanceof List) {
                            o5 = ((List<Object>)o5).get(array2[j]);
                        }
                        Object o6;
                        if (o5 instanceof Map) {
                            o6 = o5;
                        }
                        else {
                            o6 = null;
                        }
                        Integer n9;
                        if (o6 != null) {
                            n9 = ((Map<K, Integer>)o6).get("to");
                        }
                        else {
                            n9 = null;
                        }
                        Integer n10;
                        if (o5 instanceof Map) {
                            n10 = ((Map<K, Integer>)o6).get("from");
                        }
                        else {
                            n10 = null;
                        }
                        if (n9 != null) {
                            int n11;
                            if (array3[j] == null) {
                                if (n10 != null) {
                                    n11 = n10;
                                }
                                else {
                                    n11 = 0;
                                }
                            }
                            else {
                                n11 = array3[j];
                            }
                            array3[j] = n11;
                        }
                        if (array3[j] != null && !array3[j].equals(n9)) {
                            final Integer n12 = array3[j];
                            ++array3[j];
                            break;
                        }
                        array3[j] = null;
                        final Integer n13 = array2[j];
                        ++array2[j];
                        break;
                    }
                    else {
                        j = n;
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
    
    public boolean isEmpty() {
        return this.keySegments == null || this.keySegments.size() == 0;
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
