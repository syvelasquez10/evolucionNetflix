// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import java.util.Iterator;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import android.view.View;
import java.util.WeakHashMap;
import android.view.ViewGroup;

public abstract class ViewGroupManager<T extends ViewGroup> extends BaseViewManager<T, LayoutShadowNode>
{
    public static WeakHashMap<View, Integer> mZIndexHash;
    
    static {
        ViewGroupManager.mZIndexHash = new WeakHashMap<View, Integer>();
    }
    
    public static void reorderChildrenByZIndex(final ViewGroup viewGroup) {
        final int n = 0;
        final Iterator<Integer> iterator = ViewGroupManager.mZIndexHash.values().iterator();
        while (true) {
            while (iterator.hasNext()) {
                if (iterator.next() != 0) {
                    final int n2 = 1;
                    if (n2 == 0) {
                        return;
                    }
                    final ArrayList<View> list = (ArrayList<View>)new ArrayList<Object>();
                    for (int i = 0; i < viewGroup.getChildCount(); ++i) {
                        list.add(viewGroup.getChildAt(i));
                    }
                    Collections.sort((List<Object>)list, (Comparator<? super Object>)new ViewGroupManager$1());
                    for (int j = n; j < list.size(); ++j) {
                        list.get(j).bringToFront();
                    }
                    viewGroup.invalidate();
                    return;
                }
            }
            final int n2 = 0;
            continue;
        }
    }
    
    public static void setViewZIndex(final View view, final int n) {
        ViewGroupManager.mZIndexHash.put(view, n);
        final ViewGroup viewGroup = (ViewGroup)view.getParent();
        if (viewGroup != null) {
            reorderChildrenByZIndex(viewGroup);
        }
    }
    
    public void addView(final T t, final View view, final int n) {
        t.addView(view, n);
        reorderChildrenByZIndex(t);
    }
    
    public void addViews(final T t, final List<View> list) {
        for (int size = list.size(), i = 0; i < size; ++i) {
            this.addView(t, list.get(i), i);
        }
    }
    
    @Override
    public LayoutShadowNode createShadowNodeInstance() {
        return new LayoutShadowNode();
    }
    
    public View getChildAt(final T t, final int n) {
        return t.getChildAt(n);
    }
    
    public int getChildCount(final T t) {
        return t.getChildCount();
    }
    
    @Override
    public Class<? extends LayoutShadowNode> getShadowNodeClass() {
        return LayoutShadowNode.class;
    }
    
    public boolean needsCustomLayoutForChildren() {
        return false;
    }
    
    public void removeAllViews(final T t) {
        for (int i = this.getChildCount(t) - 1; i >= 0; --i) {
            this.removeViewAt(t, i);
        }
    }
    
    public void removeView(final T t, final View view) {
        for (int i = 0; i < this.getChildCount(t); ++i) {
            if (this.getChildAt(t, i) == view) {
                this.removeViewAt(t, i);
                break;
            }
        }
    }
    
    public void removeViewAt(final T t, final int n) {
        t.removeViewAt(n);
    }
    
    public boolean shouldPromoteGrandchildren() {
        return false;
    }
    
    @Override
    public void updateExtraData(final T t, final Object o) {
    }
}
