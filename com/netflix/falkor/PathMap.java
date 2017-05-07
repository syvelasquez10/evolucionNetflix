// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

import java.util.Iterator;
import java.util.Stack;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;

public class PathMap
{
    private static final String MARKER = "____";
    Map map;
    
    public PathMap() {
        this.map = new HashMap();
    }
    
    public void add(final PQL pql, final PQL pql2) {
        final int n = pql.getKeySegments().size() - 1;
        Map<K, V> map = (Map<K, V>)this.map;
        final HashMap<Object, Set> hashMap = null;
        HashMap<Object, HashMap> hashMap2;
        for (int i = 0; i < n; ++i, map = (Map<K, V>)hashMap2) {
            final Object value = pql.getKeySegments().get(i);
            if ((hashMap2 = (HashMap<Object, HashMap>)map.get(value)) == null) {
                hashMap2 = new HashMap<Object, HashMap>();
                map.put((K)value, (V)hashMap2);
            }
        }
        final Object value2 = pql.getKeySegments().get(n);
        Object o;
        if (map.get(value2) != null) {
            o = ((HashMap<Object, HashMap>)map.get(value2)).get("____");
        }
        else {
            map.put((K)value2, (V)new HashMap<Object, HashMap>());
            o = hashMap;
        }
        Cloneable cloneable = (Cloneable)o;
        if (o == null) {
            cloneable = new HashSet<Object>();
            ((HashMap<Object, HashMap>)map.get(value2)).put("____", (HashMap<Object, Set>)cloneable);
        }
        ((Set<PQL>)cloneable).add(pql2);
    }
    
    public List<PQL> translate(final PQL pql) {
        final Stack<PQL> stack = new Stack<PQL>();
        stack.add(pql);
        final Stack<PQL> stack2 = new Stack<PQL>();
        while (stack.size() > 0) {
            PQL pql2;
            Map map;
            int n;
            for (pql2 = stack.pop(), map = this.map, n = 0; map != null && !(map.get("____") instanceof Set) && n < pql2.getKeySegments().size(); map = (Map)map.get(pql2.getKeySegments().get(n)), ++n) {}
            if (map != null && map.get("____") != null && map.get("____") instanceof Set) {
                final Iterator<PQL> iterator = map.get("____").iterator();
                while (iterator.hasNext()) {
                    stack.push(iterator.next().addPathSegments(ListExtensions.sub(pql2.getKeySegments(), n)));
                }
            }
            else {
                stack2.push(pql2);
            }
        }
        return stack2;
    }
}
