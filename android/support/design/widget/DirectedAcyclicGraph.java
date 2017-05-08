// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import java.util.List;
import android.support.v4.util.Pools$SimplePool;
import java.util.HashSet;
import android.support.v4.util.Pools$Pool;
import java.util.ArrayList;
import android.support.v4.util.SimpleArrayMap;

final class DirectedAcyclicGraph<T>
{
    private final SimpleArrayMap<T, ArrayList<T>> mGraph;
    private final Pools$Pool<ArrayList<T>> mListPool;
    private final ArrayList<T> mSortResult;
    private final HashSet<T> mSortTmpMarked;
    
    DirectedAcyclicGraph() {
        this.mListPool = new Pools$SimplePool<ArrayList<T>>(10);
        this.mGraph = new SimpleArrayMap<T, ArrayList<T>>();
        this.mSortResult = new ArrayList<T>();
        this.mSortTmpMarked = new HashSet<T>();
    }
    
    private void dfs(final T t, final ArrayList<T> list, final HashSet<T> set) {
        if (list.contains(t)) {
            return;
        }
        if (set.contains(t)) {
            throw new RuntimeException("This graph contains cyclic dependencies");
        }
        set.add(t);
        final ArrayList<T> list2 = this.mGraph.get(t);
        if (list2 != null) {
            for (int i = 0; i < list2.size(); ++i) {
                this.dfs(list2.get(i), list, set);
            }
        }
        set.remove(t);
        list.add(t);
    }
    
    private ArrayList<T> getEmptyList() {
        ArrayList<T> list;
        if ((list = this.mListPool.acquire()) == null) {
            list = new ArrayList<T>();
        }
        return list;
    }
    
    private void poolList(final ArrayList<T> list) {
        list.clear();
        this.mListPool.release(list);
    }
    
    void addEdge(final T t, final T t2) {
        if (!this.mGraph.containsKey(t) || !this.mGraph.containsKey(t2)) {
            throw new IllegalArgumentException("All nodes must be present in the graph before being added as an edge");
        }
        ArrayList<T> emptyList;
        if ((emptyList = this.mGraph.get(t)) == null) {
            emptyList = this.getEmptyList();
            this.mGraph.put(t, emptyList);
        }
        emptyList.add(t2);
    }
    
    void addNode(final T t) {
        if (!this.mGraph.containsKey(t)) {
            this.mGraph.put(t, null);
        }
    }
    
    void clear() {
        for (int size = this.mGraph.size(), i = 0; i < size; ++i) {
            final ArrayList<T> list = this.mGraph.valueAt(i);
            if (list != null) {
                this.poolList(list);
            }
        }
        this.mGraph.clear();
    }
    
    boolean contains(final T t) {
        return this.mGraph.containsKey(t);
    }
    
    List getIncomingEdges(final T t) {
        return this.mGraph.get(t);
    }
    
    List getOutgoingEdges(final T t) {
        ArrayList<T> list = null;
        ArrayList<T> list3;
        for (int size = this.mGraph.size(), i = 0; i < size; ++i, list = list3) {
            final ArrayList<T> list2 = this.mGraph.valueAt(i);
            list3 = list;
            if (list2 != null) {
                list3 = list;
                if (list2.contains(t)) {
                    if (list == null) {
                        list = new ArrayList<T>();
                    }
                    list.add(this.mGraph.keyAt(i));
                    list3 = list;
                }
            }
        }
        return list;
    }
    
    ArrayList<T> getSortedList() {
        this.mSortResult.clear();
        this.mSortTmpMarked.clear();
        for (int i = 0; i < this.mGraph.size(); ++i) {
            this.dfs(this.mGraph.keyAt(i), this.mSortResult, this.mSortTmpMarked);
        }
        return this.mSortResult;
    }
    
    boolean hasOutgoingEdges(final T t) {
        for (int size = this.mGraph.size(), i = 0; i < size; ++i) {
            final ArrayList<T> list = this.mGraph.valueAt(i);
            if (list != null && list.contains(t)) {
                return true;
            }
        }
        return false;
    }
    
    int size() {
        return this.mGraph.size();
    }
}
