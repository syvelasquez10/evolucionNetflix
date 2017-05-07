// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.transition.Transition$EpicenterCallback;
import android.transition.TransitionSet;
import java.util.Collection;
import android.transition.TransitionManager;
import android.view.ViewGroup;
import android.view.ViewTreeObserver$OnPreDrawListener;
import java.util.Map;
import android.transition.Transition;
import android.graphics.Rect;
import android.view.View;
import java.util.ArrayList;

class FragmentTransitionCompat21
{
    public static void addTargets(final Object o, final ArrayList<View> list) {
        final Transition transition = (Transition)o;
        for (int size = list.size(), i = 0; i < size; ++i) {
            transition.addTarget((View)list.get(i));
        }
    }
    
    public static void addTransitionTargets(final Object o, final Object o2, final View view, final FragmentTransitionCompat21$ViewRetriever fragmentTransitionCompat21$ViewRetriever, final View view2, final FragmentTransitionCompat21$EpicenterView fragmentTransitionCompat21$EpicenterView, final Map<String, String> map, final ArrayList<View> list, final Map<String, View> map2, final ArrayList<View> list2) {
        if (o != null || o2 != null) {
            final Transition transition = (Transition)o;
            if (transition != null) {
                transition.addTarget(view2);
            }
            if (o2 != null) {
                addTargets(o2, list2);
            }
            if (fragmentTransitionCompat21$ViewRetriever != null) {
                view.getViewTreeObserver().addOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)new FragmentTransitionCompat21$2(view, fragmentTransitionCompat21$ViewRetriever, map, map2, transition, list));
            }
            setSharedElementEpicenter(transition, fragmentTransitionCompat21$EpicenterView);
        }
    }
    
    public static void beginDelayedTransition(final ViewGroup viewGroup, final Object o) {
        TransitionManager.beginDelayedTransition(viewGroup, (Transition)o);
    }
    
    public static Object captureExitingViews(final Object o, final View view, final ArrayList<View> list, final Map<String, View> map) {
        Object o2 = o;
        if (o != null) {
            captureTransitioningViews(list, view);
            if (map != null) {
                list.removeAll(map.values());
            }
            if (!list.isEmpty()) {
                addTargets(o, list);
                return o;
            }
            o2 = null;
        }
        return o2;
    }
    
    private static void captureTransitioningViews(final ArrayList<View> list, final View view) {
        if (view.getVisibility() == 0) {
            if (!(view instanceof ViewGroup)) {
                list.add(view);
                return;
            }
            final ViewGroup viewGroup = (ViewGroup)view;
            if (viewGroup.isTransitionGroup()) {
                list.add((View)viewGroup);
            }
            else {
                for (int childCount = viewGroup.getChildCount(), i = 0; i < childCount; ++i) {
                    captureTransitioningViews(list, viewGroup.getChildAt(i));
                }
            }
        }
    }
    
    public static void cleanupTransitions(final View view, final View view2, final Object o, final ArrayList<View> list, final Object o2, final ArrayList<View> list2, final Object o3, final ArrayList<View> list3, final Object o4, final ArrayList<View> list4, final Map<String, View> map) {
        final Transition transition = (Transition)o;
        final Transition transition2 = (Transition)o2;
        final Transition transition3 = (Transition)o3;
        final Transition transition4 = (Transition)o4;
        if (transition4 != null) {
            view.getViewTreeObserver().addOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)new FragmentTransitionCompat21$4(view, transition, view2, list, transition2, list2, transition3, list3, map, list4, transition4));
        }
    }
    
    public static Object cloneTransition(final Object o) {
        Object clone = o;
        if (o != null) {
            clone = ((Transition)o).clone();
        }
        return clone;
    }
    
    public static void excludeTarget(final Object o, final View view, final boolean b) {
        ((Transition)o).excludeTarget(view, b);
    }
    
    public static void findNamedViews(final Map<String, View> map, final View view) {
        if (view.getVisibility() == 0) {
            final String transitionName = view.getTransitionName();
            if (transitionName != null) {
                map.put(transitionName, view);
            }
            if (view instanceof ViewGroup) {
                final ViewGroup viewGroup = (ViewGroup)view;
                for (int childCount = viewGroup.getChildCount(), i = 0; i < childCount; ++i) {
                    findNamedViews(map, viewGroup.getChildAt(i));
                }
            }
        }
    }
    
    private static Rect getBoundsOnScreen(final View view) {
        final Rect rect = new Rect();
        final int[] array = new int[2];
        view.getLocationOnScreen(array);
        rect.set(array[0], array[1], array[0] + view.getWidth(), array[1] + view.getHeight());
        return rect;
    }
    
    public static String getTransitionName(final View view) {
        return view.getTransitionName();
    }
    
    public static Object mergeTransitions(final Object o, Object o2, final Object o3, boolean b) {
        final Transition transition = (Transition)o;
        Object setOrdering = o2;
        final Transition transition2 = (Transition)o3;
        if (transition == null || setOrdering == null) {
            b = true;
        }
        if (b) {
            o2 = new TransitionSet();
            if (transition != null) {
                ((TransitionSet)o2).addTransition(transition);
            }
            if (setOrdering != null) {
                ((TransitionSet)o2).addTransition((Transition)setOrdering);
            }
            if (transition2 != null) {
                ((TransitionSet)o2).addTransition(transition2);
            }
            return o2;
        }
        final Transition transition3 = null;
        if (setOrdering != null && transition != null) {
            setOrdering = new TransitionSet().addTransition((Transition)setOrdering).addTransition(transition).setOrdering(1);
        }
        else if (setOrdering == null) {
            setOrdering = transition3;
            if (transition != null) {
                setOrdering = transition;
            }
        }
        if (transition2 != null) {
            o2 = new TransitionSet();
            if (setOrdering != null) {
                ((TransitionSet)o2).addTransition((Transition)setOrdering);
            }
            ((TransitionSet)o2).addTransition(transition2);
            return o2;
        }
        return setOrdering;
    }
    
    public static void removeTargets(final Object o, final ArrayList<View> list) {
        final Transition transition = (Transition)o;
        for (int size = list.size(), i = 0; i < size; ++i) {
            transition.removeTarget((View)list.get(i));
        }
    }
    
    public static void setEpicenter(final Object o, final View view) {
        ((Transition)o).setEpicenterCallback((Transition$EpicenterCallback)new FragmentTransitionCompat21$1(getBoundsOnScreen(view)));
    }
    
    private static void setSharedElementEpicenter(final Transition transition, final FragmentTransitionCompat21$EpicenterView fragmentTransitionCompat21$EpicenterView) {
        if (transition != null) {
            transition.setEpicenterCallback((Transition$EpicenterCallback)new FragmentTransitionCompat21$3(fragmentTransitionCompat21$EpicenterView));
        }
    }
}
