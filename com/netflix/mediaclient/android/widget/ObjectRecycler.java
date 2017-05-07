// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import com.netflix.mediaclient.util.ThreadUtils;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ObjectRecycler<T>
{
    private static final boolean LOG_VERBOSE = false;
    protected static final String TAG = "ObjectRecycler";
    private final Map<String, LinkedList<T>> scrapHeaps;
    
    public ObjectRecycler() {
        this.scrapHeaps = new HashMap<String, LinkedList<T>>();
    }
    
    private void logVerbose(final String s) {
    }
    
    public T pop(final Class<?> clazz) {
        ThreadUtils.assertOnMain();
        final String canonicalName = clazz.getCanonicalName();
        final LinkedList<T> list = this.scrapHeaps.get(canonicalName);
        if (list == null) {
            this.logVerbose("- No scrap heap available for tag: " + canonicalName);
            return null;
        }
        final T poll = list.poll();
        final StringBuilder sb = new StringBuilder();
        String s;
        if (poll == null) {
            s = "- No object available for tag: ";
        }
        else {
            s = "* Found scrap object for tag: ";
        }
        this.logVerbose(sb.append(s).append(canonicalName).append(", scrapHeap size:").append(list.size()).toString());
        return poll;
    }
    
    public void push(final T t) {
        ThreadUtils.assertOnMain();
        final String canonicalName = t.getClass().getCanonicalName();
        LinkedList<T> list;
        if ((list = this.scrapHeaps.get(canonicalName)) == null) {
            this.logVerbose("+ Creating new scrap heap for tag: " + canonicalName);
            list = new LinkedList<T>();
            this.scrapHeaps.put(canonicalName, list);
        }
        list.add(t);
        this.logVerbose("+ Added object to scrap heap with tag: " + canonicalName + ", scrapHeap size:" + list.size());
    }
}
