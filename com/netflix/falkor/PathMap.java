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
        Map<Object, HashMap<Object, HashMap>> map = (Map<Object, HashMap<Object, HashMap>>)this.map;
        Map<K, V> map2;
        for (int i = 0; i < n; ++i, map = (Map<Object, HashMap<Object, HashMap>>)map2) {
            final Object value = pql.getKeySegments().get(i);
            if ((map2 = (Map<K, V>)map.get(value)) == null) {
                map2 = (Map<K, V>)new HashMap<Object, HashMap<Object, HashMap>>();
                map.put(value, (HashMap<Object, HashMap>)map2);
            }
        }
        final Object value2 = pql.getKeySegments().get(n);
        Object o;
        if (map.get(value2) != null) {
            o = map.get(value2).get("____");
        }
        else {
            map.put(value2, new HashMap<Object, HashMap>());
            o = null;
        }
        Object o2 = o;
        if (o == null) {
            o2 = new HashSet<PQL>();
            map.get(value2).put("____", (HashMap<Object, Set>)o2);
        }
        ((Set<PQL>)o2).add(pql2);
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
