// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

import com.netflix.mediaclient.Log;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class ObjectPathEvaluator extends BasePathEvaluator
{
    private static final String TAG = "ObjectPathEvaluator";
    Map<String, Object> map;
    
    public ObjectPathEvaluator() {
        this.map = new HashMap<String, Object>();
    }
    
    private void deletePath(final Map map, final PQL pql, final int n, final Watcher<PathBoundValue> watcher) {
        Label_0198: {
            Object o;
            synchronized (this) {
                o = pql.getKeySegments().get(n);
                if (o instanceof List) {
                    o = ((List)o).iterator();
                    while (((Iterator)o).hasNext()) {
                        this.deletePath(map, pql.replaceKeySegment(n, ((Iterator<Object>)o).next()), n, watcher);
                    }
                    break Label_0198;
                }
            }
            if (o instanceof Map) {
                final Map<K, Integer> map2 = (Map<K, Integer>)o;
                Integer value;
                if ((value = map2.get("from")) == null) {
                    value = 0;
                }
                final Integer n2 = map2.get("to");
                for (int i = value; i <= n2; ++i) {
                    final Map<K, Option<PQL>> map3;
                    this.deletePath(map3, pql.replaceKeySegment(n, i), n, watcher);
                }
            }
            else if (n == pql.getKeySegments().size() - 1) {
                final Map<K, Option<PQL>> map3;
                map3.remove(o);
            }
            else {
                final String string = o.toString();
                final Map<K, Option<PQL>> map3;
                final Option<PQL> value2 = map3.get(string);
                Option<PQL> option;
                if (value2 != null && value2 instanceof Option) {
                    option = value2;
                }
                else {
                    option = null;
                }
                if (option != null && option.getHasValue() && option.getValue() instanceof PQL) {
                    watcher.onNext(new PathBoundValue(pql.slice(0, n + 1), (Option<Object>)option));
                    this.deletePath(this.map, pql.rewrite(option.getValue(), n + 1), 0, watcher);
                }
                else {
                    Map map4;
                    if (value2 instanceof Map) {
                        map4 = (Map)value2;
                    }
                    else {
                        map4 = new HashMap();
                    }
                    map3.put((K)string, (Option<PQL>)map4);
                    this.deletePath(map4, pql, n + 1, watcher);
                }
            }
        }
    }
    // monitorexit(this)
    
    private void getPath(final Map map, final PQL pql, final int n, final Watcher<PathBoundValue> watcher) {
        Label_0282: {
            Object o;
            synchronized (this) {
                o = pql.getKeySegments().get(n);
                if (o instanceof List) {
                    o = ((List)o).iterator();
                    while (((Iterator)o).hasNext()) {
                        this.getPath(map, pql.replaceKeySegment(n, ((Iterator<Object>)o).next()), n, watcher);
                    }
                    break Label_0282;
                }
            }
            if (o instanceof Map) {
                final Map<K, Integer> map2 = (Map<K, Integer>)o;
                Integer value;
                if ((value = map2.get("from")) == null) {
                    value = 0;
                }
                final Integer n2 = map2.get("to");
                for (int i = value; i <= n2; ++i) {
                    final Map<K, Option<PQL>> map3;
                    this.getPath(map3, pql.replaceKeySegment(n, Integer.valueOf(i).toString()), n, watcher);
                }
            }
            else {
                final Map<K, Option<PQL>> map3;
                final Option<PQL> value2 = map3.get(o.toString());
                if (value2 != null) {
                    Option<PQL> option;
                    if (value2 instanceof Option) {
                        option = value2;
                    }
                    else {
                        option = null;
                    }
                    if (option != null && option.getHasValue() && option.getValue() instanceof PQL) {
                        watcher.onNext(new PathBoundValue(pql.slice(0, n + 1), (Option<Object>)value2));
                        this.getPath(this.map, pql.rewrite(option.getValue(), n + 1), 0, watcher);
                    }
                    else if (n == pql.getKeySegments().size() - 1) {
                        watcher.onNext(new PathBoundValue(pql, (Option<Object>)value2));
                    }
                    else {
                        this.getPath((Map)value2, pql, n + 1, watcher);
                    }
                }
            }
        }
    }
    // monitorexit(this)
    
    private void setPath(final Map map, final PQL pql, final int n, final Option option, final Watcher<PathBoundValue> watcher) {
        Label_0224: {
            Object o;
            synchronized (this) {
                o = pql.getKeySegments().get(n);
                if (o instanceof List) {
                    o = ((List)o).iterator();
                    while (((Iterator)o).hasNext()) {
                        this.setPath(map, pql.replaceKeySegment(n, ((Iterator<Object>)o).next()), n, option, watcher);
                    }
                    break Label_0224;
                }
            }
            if (o instanceof Map) {
                final Map<Map<Map<Map<K, Integer>, Integer>, Integer>, Integer> map2 = (Map<Map<Map<Map<K, Integer>, Integer>, Integer>, Integer>)o;
                Integer value;
                if ((value = map2.get("from")) == null) {
                    value = 0;
                }
                final Integer n2 = map2.get("to");
                for (int i = value; i <= n2; ++i) {
                    final Map<Map<Map<Map<Map, Integer>, Integer>, Integer>, Option<PQL>> map3;
                    this.setPath(map3, pql.replaceKeySegment(n, Integer.valueOf(i).toString()), n, option, watcher);
                }
            }
            else if (n == pql.getKeySegments().size() - 1) {
                final Map<Map<Map<Map<Map, Integer>, Integer>, Integer>, Option<PQL>> map3;
                map3.put((Map<Map<Map<Map, Integer>, Integer>, Integer>)o, option);
                watcher.onNext(new PathBoundValue(pql, option));
            }
            else {
                final String string = o.toString();
                final Map<Map<Map<Map<Map, Integer>, Integer>, Integer>, Option<PQL>> map3;
                final Option<PQL> value2 = map3.get(string);
                Option<PQL> option2;
                if (value2 != null && value2 instanceof Option) {
                    option2 = value2;
                }
                else {
                    option2 = null;
                }
                if (option2 != null && option2.getHasValue() && option2.getValue() instanceof PQL) {
                    watcher.onNext(new PathBoundValue(pql.slice(0, n + 1), (Option<Object>)option2));
                    this.setPath(this.map, pql.rewrite(option2.getValue(), n + 1), 0, option, watcher);
                }
                else {
                    Map map4;
                    if (value2 instanceof Map) {
                        map4 = (Map)value2;
                    }
                    else {
                        map4 = new HashMap();
                    }
                    map3.put((Map<Map<Map<Map, Integer>, Integer>, Integer>)string, (Option<PQL>)map4);
                    this.setPath(map4, pql, n + 1, option, watcher);
                }
            }
        }
    }
    // monitorexit(this)
    
    @Override
    public Iterable<PathBoundValue> deleteAbsolute(final Iterable<PQL> iterable) {
        final ArrayList<PathBoundValue> list = new ArrayList<PathBoundValue>();
        final Iterator<PQL> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            this.deletePath(this.map, iterator.next(), 0, new Watcher<PathBoundValue>() {
                @Override
                public void onCompleted() {
                }
                
                @Override
                public void onError(final Exception ex) {
                }
                
                @Override
                public void onNext(final PathBoundValue pathBoundValue) {
                    list.add(pathBoundValue);
                }
            });
        }
        return list;
    }
    
    public void dumpMemory() {
        Log.v("ObjectPathEvaluator", "ObjectPathEvaluator map=" + this.map.toString());
    }
    
    @Override
    public Iterable<PathBoundValue> getAbsolute(final Iterable<PQL> iterable) {
        final ArrayList<PathBoundValue> list = new ArrayList<PathBoundValue>();
        final Iterator<PQL> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            this.getPath(this.map, iterator.next(), 0, new Watcher<PathBoundValue>() {
                @Override
                public void onCompleted() {
                }
                
                @Override
                public void onError(final Exception ex) {
                }
                
                @Override
                public void onNext(final PathBoundValue pathBoundValue) {
                    list.add(pathBoundValue);
                }
            });
        }
        return list;
    }
    
    @Override
    public AbstractPathEvaluator getRoot() {
        return this;
    }
    
    @Override
    public Iterable<PathBoundValue> setAbsolute(final Iterable<PathBoundValue> iterable) {
        final ArrayList<PathBoundValue> list = new ArrayList<PathBoundValue>();
        for (final PathBoundValue pathBoundValue : iterable) {
            this.setPath(this.map, pathBoundValue.getPath(), 0, pathBoundValue.getValue(), new Watcher<PathBoundValue>() {
                @Override
                public void onCompleted() {
                }
                
                @Override
                public void onError(final Exception ex) {
                }
                
                @Override
                public void onNext(final PathBoundValue pathBoundValue) {
                    list.add(pathBoundValue);
                }
            });
        }
        return list;
    }
}
