// 
// Decompiled by Procyon v0.5.30
// 

package android.support.transition;

import java.util.ArrayList;

class TransitionPort$ArrayListManager
{
    static <T> ArrayList<T> add(final ArrayList<T> list, final T t) {
        ArrayList<T> list2 = list;
        if (list == null) {
            list2 = new ArrayList<T>();
        }
        if (!list2.contains(t)) {
            list2.add(t);
        }
        return list2;
    }
    
    static <T> ArrayList<T> remove(final ArrayList<T> list, final T t) {
        ArrayList<T> list2 = list;
        if (list != null) {
            list.remove(t);
            list2 = list;
            if (list.isEmpty()) {
                list2 = null;
            }
        }
        return list2;
    }
}
