// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import com.netflix.mediaclient.util.ThreadUtils;
import java.util.HashMap;
import android.view.View;
import java.util.LinkedList;
import java.util.Map;

public class ViewRecycler
{
    private static final boolean LOG_VERBOSE = false;
    private static final String TAG = "ViewRecycler";
    private final Map<String, LinkedList<View>> scrapHeaps;
    
    public ViewRecycler() {
        this.scrapHeaps = new HashMap<String, LinkedList<View>>();
    }
    
    private void logVerbose(final String s) {
    }
    
    public View pop(final Class<?> clazz) {
        ThreadUtils.assertOnMain();
        final String canonicalName = clazz.getCanonicalName();
        final LinkedList<View> list = this.scrapHeaps.get(canonicalName);
        if (list == null) {
            this.logVerbose("- No scrap heap available for tag: " + canonicalName);
            return null;
        }
        final View view = list.poll();
        final StringBuilder sb = new StringBuilder();
        String s;
        if (view == null) {
            s = "- No view available for tag: ";
        }
        else {
            s = "* Found scrap view for tag: ";
        }
        this.logVerbose(sb.append(s).append(canonicalName).append(", scrapHeap size:").append(list.size()).toString());
        return view;
    }
    
    public void push(final View view) {
        ThreadUtils.assertOnMain();
        final String canonicalName = view.getClass().getCanonicalName();
        LinkedList<View> list;
        if ((list = this.scrapHeaps.get(canonicalName)) == null) {
            this.logVerbose("+ Creating new scrap heap for tag: " + canonicalName);
            list = new LinkedList<View>();
            this.scrapHeaps.put(canonicalName, list);
        }
        list.add(view);
        this.logVerbose("+ Added view to scrap heap with tag: " + canonicalName + ", scrapHeap size:" + list.size());
    }
}
