// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.os.Build$VERSION;
import android.graphics.Rect;
import android.view.ViewGroup;
import java.util.Map;
import java.util.List;
import android.util.SparseArray;
import android.support.v4.view.ViewCompat;
import java.util.Collection;
import android.support.v4.util.ArrayMap;
import android.view.View;
import java.util.ArrayList;

class FragmentTransition
{
    private static final int[] INVERSE_OPS;
    
    static {
        INVERSE_OPS = new int[] { 0, 3, 0, 1, 5, 4, 7, 6 };
    }
    
    private static void addSharedElementsWithMatchingNames(final ArrayList<View> list, final ArrayMap<String, View> arrayMap, final Collection<String> collection) {
        for (int i = arrayMap.size() - 1; i >= 0; --i) {
            final View view = arrayMap.valueAt(i);
            if (collection.contains(ViewCompat.getTransitionName(view))) {
                list.add(view);
            }
        }
    }
    
    private static void addToFirstInLastOut(final BackStackRecord backStackRecord, final BackStackRecord$Op backStackRecord$Op, final SparseArray<FragmentTransition$FragmentContainerTransition> sparseArray, final boolean b, final boolean b2) {
        final Fragment fragment = backStackRecord$Op.fragment;
        final int mContainerId = fragment.mContainerId;
        if (mContainerId != 0) {
            int cmd;
            if (b) {
                cmd = FragmentTransition.INVERSE_OPS[backStackRecord$Op.cmd];
            }
            else {
                cmd = backStackRecord$Op.cmd;
            }
            boolean b3 = false;
            int n = 0;
            boolean b4 = false;
            int n2 = 0;
            switch (cmd) {
                default: {
                    b3 = false;
                    n = 0;
                    b4 = false;
                    n2 = 0;
                    break;
                }
                case 5: {
                    if (b2) {
                        if (fragment.mHiddenChanged && !fragment.mHidden && fragment.mAdded) {
                            n2 = 1;
                        }
                        else {
                            n2 = 0;
                        }
                    }
                    else {
                        n2 = (fragment.mHidden ? 1 : 0);
                    }
                    b3 = true;
                    n = 0;
                    b4 = false;
                    break;
                }
                case 1:
                case 7: {
                    if (b2) {
                        n2 = (fragment.mIsNewlyAdded ? 1 : 0);
                    }
                    else if (!fragment.mAdded && !fragment.mHidden) {
                        n2 = 1;
                    }
                    else {
                        n2 = 0;
                    }
                    b3 = true;
                    n = 0;
                    b4 = false;
                    break;
                }
                case 4: {
                    int n3;
                    if (b2) {
                        if (fragment.mHiddenChanged && fragment.mAdded && fragment.mHidden) {
                            n3 = 1;
                        }
                        else {
                            n3 = 0;
                        }
                    }
                    else if (fragment.mAdded && !fragment.mHidden) {
                        n3 = 1;
                    }
                    else {
                        n3 = 0;
                    }
                    final boolean b5 = false;
                    n = n3;
                    b4 = true;
                    n2 = 0;
                    b3 = b5;
                    break;
                }
                case 3:
                case 6: {
                    int n4;
                    if (b2) {
                        if (!fragment.mAdded && fragment.mView != null && fragment.mView.getVisibility() == 0 && fragment.mPostponedAlpha >= 0.0f) {
                            n4 = 1;
                        }
                        else {
                            n4 = 0;
                        }
                    }
                    else if (fragment.mAdded && !fragment.mHidden) {
                        n4 = 1;
                    }
                    else {
                        n4 = 0;
                    }
                    final boolean b6 = false;
                    n = n4;
                    b4 = true;
                    n2 = 0;
                    b3 = b6;
                    break;
                }
            }
            FragmentTransition$FragmentContainerTransition ensureContainer = (FragmentTransition$FragmentContainerTransition)sparseArray.get(mContainerId);
            if (n2 != 0) {
                ensureContainer = ensureContainer(ensureContainer, sparseArray, mContainerId);
                ensureContainer.lastIn = fragment;
                ensureContainer.lastInIsPop = b;
                ensureContainer.lastInTransaction = backStackRecord;
            }
            if (!b2 && b3) {
                if (ensureContainer != null && ensureContainer.firstOut == fragment) {
                    ensureContainer.firstOut = null;
                }
                final FragmentManagerImpl mManager = backStackRecord.mManager;
                if (fragment.mState < 1 && mManager.mCurState >= 1 && !backStackRecord.mAllowOptimization) {
                    mManager.makeActive(fragment);
                    mManager.moveToState(fragment, 1, 0, 0, false);
                }
            }
            FragmentTransition$FragmentContainerTransition fragmentTransition$FragmentContainerTransition;
            if (n != 0 && (ensureContainer == null || ensureContainer.firstOut == null)) {
                final FragmentTransition$FragmentContainerTransition ensureContainer2 = ensureContainer(ensureContainer, sparseArray, mContainerId);
                ensureContainer2.firstOut = fragment;
                ensureContainer2.firstOutIsPop = b;
                ensureContainer2.firstOutTransaction = backStackRecord;
                fragmentTransition$FragmentContainerTransition = ensureContainer2;
            }
            else {
                fragmentTransition$FragmentContainerTransition = ensureContainer;
            }
            if (!b2 && b4 && fragmentTransition$FragmentContainerTransition != null && fragmentTransition$FragmentContainerTransition.lastIn == fragment) {
                fragmentTransition$FragmentContainerTransition.lastIn = null;
            }
        }
    }
    
    public static void calculateFragments(final BackStackRecord backStackRecord, final SparseArray<FragmentTransition$FragmentContainerTransition> sparseArray, final boolean b) {
        for (int size = backStackRecord.mOps.size(), i = 0; i < size; ++i) {
            addToFirstInLastOut(backStackRecord, backStackRecord.mOps.get(i), sparseArray, false, b);
        }
    }
    
    private static ArrayMap<String, String> calculateNameOverrides(final int n, final ArrayList<BackStackRecord> list, final ArrayList<Boolean> list2, final int n2, int i) {
        final ArrayMap<Object, String> arrayMap = new ArrayMap<Object, String>();
        BackStackRecord backStackRecord;
        boolean booleanValue;
        int size;
        ArrayList<String> list3;
        ArrayList<String> list4;
        int j;
        String s;
        String s2;
        String s3;
        for (--i; i >= n2; --i) {
            backStackRecord = list.get(i);
            if (backStackRecord.interactsWith(n)) {
                booleanValue = list2.get(i);
                if (backStackRecord.mSharedElementSourceNames != null) {
                    size = backStackRecord.mSharedElementSourceNames.size();
                    if (booleanValue) {
                        list3 = backStackRecord.mSharedElementSourceNames;
                        list4 = backStackRecord.mSharedElementTargetNames;
                    }
                    else {
                        list4 = backStackRecord.mSharedElementSourceNames;
                        list3 = backStackRecord.mSharedElementTargetNames;
                    }
                    for (j = 0; j < size; ++j) {
                        s = list4.get(j);
                        s2 = list3.get(j);
                        s3 = arrayMap.remove(s2);
                        if (s3 != null) {
                            arrayMap.put(s, s3);
                        }
                        else {
                            arrayMap.put(s, s2);
                        }
                    }
                }
            }
        }
        return (ArrayMap<String, String>)arrayMap;
    }
    
    public static void calculatePopFragments(final BackStackRecord backStackRecord, final SparseArray<FragmentTransition$FragmentContainerTransition> sparseArray, final boolean b) {
        if (backStackRecord.mManager.mContainer.onHasView()) {
            for (int i = backStackRecord.mOps.size() - 1; i >= 0; --i) {
                addToFirstInLastOut(backStackRecord, backStackRecord.mOps.get(i), sparseArray, true, b);
            }
        }
    }
    
    private static void callSharedElementStartEnd(final Fragment fragment, final Fragment fragment2, final boolean b, final ArrayMap<String, View> arrayMap, final boolean b2) {
        int i = 0;
        SharedElementCallback sharedElementCallback;
        if (b) {
            sharedElementCallback = fragment2.getEnterTransitionCallback();
        }
        else {
            sharedElementCallback = fragment.getEnterTransitionCallback();
        }
        if (sharedElementCallback != null) {
            final ArrayList<View> list = new ArrayList<View>();
            final ArrayList<String> list2 = new ArrayList<String>();
            int size;
            if (arrayMap == null) {
                size = 0;
            }
            else {
                size = arrayMap.size();
            }
            while (i < size) {
                list2.add(arrayMap.keyAt(i));
                list.add(arrayMap.valueAt(i));
                ++i;
            }
            if (!b2) {
                sharedElementCallback.onSharedElementEnd(list2, list, null);
                return;
            }
            sharedElementCallback.onSharedElementStart(list2, list, null);
        }
    }
    
    private static ArrayMap<String, View> captureInSharedElements(final ArrayMap<String, String> arrayMap, final Object o, final FragmentTransition$FragmentContainerTransition fragmentTransition$FragmentContainerTransition) {
        final Fragment lastIn = fragmentTransition$FragmentContainerTransition.lastIn;
        final View view = lastIn.getView();
        if (arrayMap.isEmpty() || o == null || view == null) {
            arrayMap.clear();
            return null;
        }
        final ArrayMap<Object, View> arrayMap2 = new ArrayMap<Object, View>();
        FragmentTransitionCompat21.findNamedViews((Map<String, View>)arrayMap2, view);
        final BackStackRecord lastInTransaction = fragmentTransition$FragmentContainerTransition.lastInTransaction;
        SharedElementCallback sharedElementCallback;
        ArrayList<String> list;
        if (fragmentTransition$FragmentContainerTransition.lastInIsPop) {
            sharedElementCallback = lastIn.getExitTransitionCallback();
            list = lastInTransaction.mSharedElementSourceNames;
        }
        else {
            sharedElementCallback = lastIn.getEnterTransitionCallback();
            list = lastInTransaction.mSharedElementTargetNames;
        }
        arrayMap2.retainAll(list);
        if (sharedElementCallback != null) {
            sharedElementCallback.onMapSharedElements(list, (Map<String, View>)arrayMap2);
            for (int i = list.size() - 1; i >= 0; --i) {
                final String s = list.get(i);
                final View view2 = arrayMap2.get(s);
                if (view2 == null) {
                    final String keyForValue = findKeyForValue(arrayMap, s);
                    if (keyForValue != null) {
                        arrayMap.remove(keyForValue);
                    }
                }
                else if (!s.equals(ViewCompat.getTransitionName(view2))) {
                    final String keyForValue2 = findKeyForValue(arrayMap, s);
                    if (keyForValue2 != null) {
                        arrayMap.put(keyForValue2, ViewCompat.getTransitionName(view2));
                    }
                }
            }
        }
        else {
            retainValues(arrayMap, (ArrayMap<String, View>)arrayMap2);
        }
        return (ArrayMap<String, View>)arrayMap2;
    }
    
    private static ArrayMap<String, View> captureOutSharedElements(final ArrayMap<String, String> arrayMap, final Object o, final FragmentTransition$FragmentContainerTransition fragmentTransition$FragmentContainerTransition) {
        if (arrayMap.isEmpty() || o == null) {
            arrayMap.clear();
            return null;
        }
        final Fragment firstOut = fragmentTransition$FragmentContainerTransition.firstOut;
        final ArrayMap<Object, Object> arrayMap2 = new ArrayMap<Object, Object>();
        FragmentTransitionCompat21.findNamedViews((Map<String, View>)arrayMap2, firstOut.getView());
        final BackStackRecord firstOutTransaction = fragmentTransition$FragmentContainerTransition.firstOutTransaction;
        SharedElementCallback sharedElementCallback;
        ArrayList<String> list;
        if (fragmentTransition$FragmentContainerTransition.firstOutIsPop) {
            sharedElementCallback = firstOut.getEnterTransitionCallback();
            list = firstOutTransaction.mSharedElementTargetNames;
        }
        else {
            sharedElementCallback = firstOut.getExitTransitionCallback();
            list = firstOutTransaction.mSharedElementSourceNames;
        }
        arrayMap2.retainAll(list);
        if (sharedElementCallback != null) {
            sharedElementCallback.onMapSharedElements(list, (Map<String, View>)arrayMap2);
            for (int i = list.size() - 1; i >= 0; --i) {
                final String s = list.get(i);
                final View view = arrayMap2.get(s);
                if (view == null) {
                    arrayMap.remove(s);
                }
                else if (!s.equals(ViewCompat.getTransitionName(view))) {
                    arrayMap.put(ViewCompat.getTransitionName(view), arrayMap.remove(s));
                }
            }
        }
        else {
            arrayMap.retainAll(arrayMap2.keySet());
        }
        return (ArrayMap<String, View>)arrayMap2;
    }
    
    private static ArrayList<View> configureEnteringExitingViews(final Object o, final Fragment fragment, final ArrayList<View> list, final View view) {
        ArrayList<View> list2 = null;
        if (o != null) {
            final ArrayList<View> list3 = new ArrayList<View>();
            FragmentTransitionCompat21.captureTransitioningViews(list3, fragment.getView());
            if (list != null) {
                list3.removeAll(list);
            }
            list2 = list3;
            if (!list3.isEmpty()) {
                list3.add(view);
                FragmentTransitionCompat21.addTargets(o, list3);
                list2 = list3;
            }
        }
        return list2;
    }
    
    private static Object configureSharedElementsOptimized(final ViewGroup viewGroup, View inEpicenterView, final ArrayMap<String, String> arrayMap, final FragmentTransition$FragmentContainerTransition fragmentTransition$FragmentContainerTransition, final ArrayList<View> list, final ArrayList<View> list2, final Object o, final Object o2) {
        final View view = null;
        final Fragment lastIn = fragmentTransition$FragmentContainerTransition.lastIn;
        final Fragment firstOut = fragmentTransition$FragmentContainerTransition.firstOut;
        if (lastIn != null) {
            lastIn.getView().setVisibility(0);
        }
        if (lastIn != null && firstOut != null) {
            final boolean lastInIsPop = fragmentTransition$FragmentContainerTransition.lastInIsPop;
            Object sharedElementTransition;
            if (arrayMap.isEmpty()) {
                sharedElementTransition = null;
            }
            else {
                sharedElementTransition = getSharedElementTransition(lastIn, firstOut, lastInIsPop);
            }
            final ArrayMap<String, View> captureOutSharedElements = captureOutSharedElements(arrayMap, sharedElementTransition, fragmentTransition$FragmentContainerTransition);
            final ArrayMap<String, View> captureInSharedElements = captureInSharedElements(arrayMap, sharedElementTransition, fragmentTransition$FragmentContainerTransition);
            Object o3;
            if (arrayMap.isEmpty()) {
                if (captureOutSharedElements != null) {
                    captureOutSharedElements.clear();
                }
                if (captureInSharedElements != null) {
                    captureInSharedElements.clear();
                    o3 = null;
                }
                else {
                    o3 = null;
                }
            }
            else {
                addSharedElementsWithMatchingNames(list, captureOutSharedElements, arrayMap.keySet());
                addSharedElementsWithMatchingNames(list2, captureInSharedElements, arrayMap.values());
                o3 = sharedElementTransition;
            }
            if (o != null || o2 != null || o3 != null) {
                callSharedElementStartEnd(lastIn, firstOut, lastInIsPop, captureOutSharedElements, true);
                Rect rect2;
                if (o3 != null) {
                    list2.add(inEpicenterView);
                    FragmentTransitionCompat21.setSharedElementTargets(o3, inEpicenterView, list);
                    setOutEpicenter(o3, o2, captureOutSharedElements, fragmentTransition$FragmentContainerTransition.firstOutIsPop, fragmentTransition$FragmentContainerTransition.firstOutTransaction);
                    final Rect rect = new Rect();
                    final View view2 = inEpicenterView = getInEpicenterView(captureInSharedElements, fragmentTransition$FragmentContainerTransition, o, lastInIsPop);
                    rect2 = rect;
                    if (view2 != null) {
                        FragmentTransitionCompat21.setEpicenter(o, rect);
                        rect2 = rect;
                        inEpicenterView = view2;
                    }
                }
                else {
                    rect2 = null;
                    inEpicenterView = view;
                }
                OneShotPreDrawListener.add((View)viewGroup, new FragmentTransition$3(lastIn, firstOut, lastInIsPop, captureInSharedElements, inEpicenterView, rect2));
                return o3;
            }
        }
        return null;
    }
    
    private static Object configureSharedElementsUnoptimized(final ViewGroup viewGroup, final View view, final ArrayMap<String, String> arrayMap, final FragmentTransition$FragmentContainerTransition fragmentTransition$FragmentContainerTransition, final ArrayList<View> list, final ArrayList<View> list2, final Object o, Object o2) {
        final Fragment lastIn = fragmentTransition$FragmentContainerTransition.lastIn;
        final Fragment firstOut = fragmentTransition$FragmentContainerTransition.firstOut;
        if (lastIn == null || firstOut == null) {
            return null;
        }
        final boolean lastInIsPop = fragmentTransition$FragmentContainerTransition.lastInIsPop;
        Object sharedElementTransition;
        if (arrayMap.isEmpty()) {
            sharedElementTransition = null;
        }
        else {
            sharedElementTransition = getSharedElementTransition(lastIn, firstOut, lastInIsPop);
        }
        final ArrayMap<String, View> captureOutSharedElements = captureOutSharedElements(arrayMap, sharedElementTransition, fragmentTransition$FragmentContainerTransition);
        if (arrayMap.isEmpty()) {
            sharedElementTransition = null;
        }
        else {
            list.addAll(captureOutSharedElements.values());
        }
        if (o == null && o2 == null && sharedElementTransition == null) {
            return null;
        }
        callSharedElementStartEnd(lastIn, firstOut, lastInIsPop, captureOutSharedElements, true);
        if (sharedElementTransition != null) {
            final Rect rect = new Rect();
            FragmentTransitionCompat21.setSharedElementTargets(sharedElementTransition, view, list);
            setOutEpicenter(sharedElementTransition, o2, captureOutSharedElements, fragmentTransition$FragmentContainerTransition.firstOutIsPop, fragmentTransition$FragmentContainerTransition.firstOutTransaction);
            o2 = rect;
            if (o != null) {
                FragmentTransitionCompat21.setEpicenter(o, rect);
                o2 = rect;
            }
        }
        else {
            o2 = null;
        }
        OneShotPreDrawListener.add((View)viewGroup, new FragmentTransition$4(arrayMap, sharedElementTransition, fragmentTransition$FragmentContainerTransition, list2, view, lastIn, firstOut, lastInIsPop, list, o, (Rect)o2));
        return sharedElementTransition;
    }
    
    private static void configureTransitionsOptimized(final FragmentManagerImpl fragmentManagerImpl, final int n, final FragmentTransition$FragmentContainerTransition fragmentTransition$FragmentContainerTransition, final View view, final ArrayMap<String, String> arrayMap) {
        Object o = null;
        if (fragmentManagerImpl.mContainer.onHasView()) {
            o = fragmentManagerImpl.mContainer.onFindViewById(n);
        }
        if (o != null) {
            final Fragment lastIn = fragmentTransition$FragmentContainerTransition.lastIn;
            final Fragment firstOut = fragmentTransition$FragmentContainerTransition.firstOut;
            final boolean lastInIsPop = fragmentTransition$FragmentContainerTransition.lastInIsPop;
            final boolean firstOutIsPop = fragmentTransition$FragmentContainerTransition.firstOutIsPop;
            final ArrayList<View> list = new ArrayList<View>();
            final ArrayList<View> list2 = new ArrayList<View>();
            final Object enterTransition = getEnterTransition(lastIn, lastInIsPop);
            final Object exitTransition = getExitTransition(firstOut, firstOutIsPop);
            final Object configureSharedElementsOptimized = configureSharedElementsOptimized((ViewGroup)o, view, arrayMap, fragmentTransition$FragmentContainerTransition, list2, list, enterTransition, exitTransition);
            if (enterTransition != null || configureSharedElementsOptimized != null || exitTransition != null) {
                final ArrayList<View> configureEnteringExitingViews = configureEnteringExitingViews(exitTransition, firstOut, list2, view);
                final ArrayList<View> configureEnteringExitingViews2 = configureEnteringExitingViews(enterTransition, lastIn, list, view);
                setViewVisibility(configureEnteringExitingViews2, 4);
                final Object mergeTransitions = mergeTransitions(enterTransition, exitTransition, configureSharedElementsOptimized, lastIn, lastInIsPop);
                if (mergeTransitions != null) {
                    replaceHide(exitTransition, firstOut, configureEnteringExitingViews);
                    final ArrayList<String> prepareSetNameOverridesOptimized = FragmentTransitionCompat21.prepareSetNameOverridesOptimized(list);
                    FragmentTransitionCompat21.scheduleRemoveTargets(mergeTransitions, enterTransition, configureEnteringExitingViews2, exitTransition, configureEnteringExitingViews, configureSharedElementsOptimized, list);
                    FragmentTransitionCompat21.beginDelayedTransition((ViewGroup)o, mergeTransitions);
                    FragmentTransitionCompat21.setNameOverridesOptimized((View)o, list2, list, prepareSetNameOverridesOptimized, arrayMap);
                    setViewVisibility(configureEnteringExitingViews2, 0);
                    FragmentTransitionCompat21.swapSharedElementTargets(configureSharedElementsOptimized, list2, list);
                }
            }
        }
    }
    
    private static void configureTransitionsUnoptimized(final FragmentManagerImpl fragmentManagerImpl, final int n, final FragmentTransition$FragmentContainerTransition fragmentTransition$FragmentContainerTransition, final View view, final ArrayMap<String, String> arrayMap) {
        Object o = null;
        if (fragmentManagerImpl.mContainer.onHasView()) {
            o = fragmentManagerImpl.mContainer.onFindViewById(n);
        }
        if (o != null) {
            final Fragment lastIn = fragmentTransition$FragmentContainerTransition.lastIn;
            final Fragment firstOut = fragmentTransition$FragmentContainerTransition.firstOut;
            final boolean lastInIsPop = fragmentTransition$FragmentContainerTransition.lastInIsPop;
            final boolean firstOutIsPop = fragmentTransition$FragmentContainerTransition.firstOutIsPop;
            final Object enterTransition = getEnterTransition(lastIn, lastInIsPop);
            Object exitTransition = getExitTransition(firstOut, firstOutIsPop);
            final ArrayList<View> list = new ArrayList<View>();
            final ArrayList<View> list2 = new ArrayList<View>();
            final Object configureSharedElementsUnoptimized = configureSharedElementsUnoptimized((ViewGroup)o, view, arrayMap, fragmentTransition$FragmentContainerTransition, list, list2, enterTransition, exitTransition);
            if (enterTransition != null || configureSharedElementsUnoptimized != null || exitTransition != null) {
                final ArrayList<View> configureEnteringExitingViews = configureEnteringExitingViews(exitTransition, firstOut, list, view);
                if (configureEnteringExitingViews == null || configureEnteringExitingViews.isEmpty()) {
                    exitTransition = null;
                }
                FragmentTransitionCompat21.addTarget(enterTransition, view);
                final Object mergeTransitions = mergeTransitions(enterTransition, exitTransition, configureSharedElementsUnoptimized, lastIn, fragmentTransition$FragmentContainerTransition.lastInIsPop);
                if (mergeTransitions != null) {
                    final ArrayList<View> list3 = new ArrayList<View>();
                    FragmentTransitionCompat21.scheduleRemoveTargets(mergeTransitions, enterTransition, list3, exitTransition, configureEnteringExitingViews, configureSharedElementsUnoptimized, list2);
                    scheduleTargetChange((ViewGroup)o, lastIn, view, list2, enterTransition, list3, exitTransition, configureEnteringExitingViews);
                    FragmentTransitionCompat21.setNameOverridesUnoptimized((View)o, list2, arrayMap);
                    FragmentTransitionCompat21.beginDelayedTransition((ViewGroup)o, mergeTransitions);
                    FragmentTransitionCompat21.scheduleNameReset((ViewGroup)o, list2, arrayMap);
                }
            }
        }
    }
    
    private static FragmentTransition$FragmentContainerTransition ensureContainer(final FragmentTransition$FragmentContainerTransition fragmentTransition$FragmentContainerTransition, final SparseArray<FragmentTransition$FragmentContainerTransition> sparseArray, final int n) {
        FragmentTransition$FragmentContainerTransition fragmentTransition$FragmentContainerTransition2 = fragmentTransition$FragmentContainerTransition;
        if (fragmentTransition$FragmentContainerTransition == null) {
            fragmentTransition$FragmentContainerTransition2 = new FragmentTransition$FragmentContainerTransition();
            sparseArray.put(n, (Object)fragmentTransition$FragmentContainerTransition2);
        }
        return fragmentTransition$FragmentContainerTransition2;
    }
    
    private static String findKeyForValue(final ArrayMap<String, String> arrayMap, final String s) {
        for (int size = arrayMap.size(), i = 0; i < size; ++i) {
            if (s.equals(arrayMap.valueAt(i))) {
                return arrayMap.keyAt(i);
            }
        }
        return null;
    }
    
    private static Object getEnterTransition(final Fragment fragment, final boolean b) {
        if (fragment == null) {
            return null;
        }
        Object o;
        if (b) {
            o = fragment.getReenterTransition();
        }
        else {
            o = fragment.getEnterTransition();
        }
        return FragmentTransitionCompat21.cloneTransition(o);
    }
    
    private static Object getExitTransition(final Fragment fragment, final boolean b) {
        if (fragment == null) {
            return null;
        }
        Object o;
        if (b) {
            o = fragment.getReturnTransition();
        }
        else {
            o = fragment.getExitTransition();
        }
        return FragmentTransitionCompat21.cloneTransition(o);
    }
    
    private static View getInEpicenterView(final ArrayMap<String, View> arrayMap, final FragmentTransition$FragmentContainerTransition fragmentTransition$FragmentContainerTransition, final Object o, final boolean b) {
        final BackStackRecord lastInTransaction = fragmentTransition$FragmentContainerTransition.lastInTransaction;
        if (o != null && lastInTransaction.mSharedElementSourceNames != null && !lastInTransaction.mSharedElementSourceNames.isEmpty()) {
            String s;
            if (b) {
                s = lastInTransaction.mSharedElementSourceNames.get(0);
            }
            else {
                s = lastInTransaction.mSharedElementTargetNames.get(0);
            }
            return arrayMap.get(s);
        }
        return null;
    }
    
    private static Object getSharedElementTransition(final Fragment fragment, final Fragment fragment2, final boolean b) {
        if (fragment == null || fragment2 == null) {
            return null;
        }
        Object o;
        if (b) {
            o = fragment2.getSharedElementReturnTransition();
        }
        else {
            o = fragment.getSharedElementEnterTransition();
        }
        return FragmentTransitionCompat21.wrapTransitionInSet(FragmentTransitionCompat21.cloneTransition(o));
    }
    
    private static Object mergeTransitions(final Object o, final Object o2, final Object o3, final Fragment fragment, final boolean b) {
        boolean b3;
        final boolean b2 = b3 = true;
        if (o != null) {
            b3 = b2;
            if (o2 != null) {
                b3 = b2;
                if (fragment != null) {
                    if (b) {
                        b3 = fragment.getAllowReturnTransitionOverlap();
                    }
                    else {
                        b3 = fragment.getAllowEnterTransitionOverlap();
                    }
                }
            }
        }
        if (b3) {
            return FragmentTransitionCompat21.mergeTransitionsTogether(o2, o, o3);
        }
        return FragmentTransitionCompat21.mergeTransitionsInSequence(o2, o, o3);
    }
    
    private static void replaceHide(final Object o, final Fragment fragment, final ArrayList<View> list) {
        if (fragment != null && o != null && fragment.mAdded && fragment.mHidden && fragment.mHiddenChanged) {
            fragment.setHideReplaced(true);
            FragmentTransitionCompat21.scheduleHideFragmentView(o, fragment.getView(), list);
            OneShotPreDrawListener.add((View)fragment.mContainer, new FragmentTransition$1(list));
        }
    }
    
    private static void retainValues(final ArrayMap<String, String> arrayMap, final ArrayMap<String, View> arrayMap2) {
        for (int i = arrayMap.size() - 1; i >= 0; --i) {
            if (!arrayMap2.containsKey(arrayMap.valueAt(i))) {
                arrayMap.removeAt(i);
            }
        }
    }
    
    private static void scheduleTargetChange(final ViewGroup viewGroup, final Fragment fragment, final View view, final ArrayList<View> list, final Object o, final ArrayList<View> list2, final Object o2, final ArrayList<View> list3) {
        OneShotPreDrawListener.add((View)viewGroup, new FragmentTransition$2(o, view, fragment, list, list2, list3, o2));
    }
    
    private static void setOutEpicenter(final Object o, final Object o2, final ArrayMap<String, View> arrayMap, final boolean b, final BackStackRecord backStackRecord) {
        if (backStackRecord.mSharedElementSourceNames != null && !backStackRecord.mSharedElementSourceNames.isEmpty()) {
            String s;
            if (b) {
                s = backStackRecord.mSharedElementTargetNames.get(0);
            }
            else {
                s = backStackRecord.mSharedElementSourceNames.get(0);
            }
            final View view = arrayMap.get(s);
            FragmentTransitionCompat21.setEpicenter(o, view);
            if (o2 != null) {
                FragmentTransitionCompat21.setEpicenter(o2, view);
            }
        }
    }
    
    private static void setViewVisibility(final ArrayList<View> list, final int visibility) {
        if (list != null) {
            for (int i = list.size() - 1; i >= 0; --i) {
                list.get(i).setVisibility(visibility);
            }
        }
    }
    
    static void startTransitions(final FragmentManagerImpl fragmentManagerImpl, final ArrayList<BackStackRecord> list, final ArrayList<Boolean> list2, final int n, final int n2, final boolean b) {
        if (fragmentManagerImpl.mCurState >= 1 && Build$VERSION.SDK_INT >= 21) {
            final SparseArray sparseArray = new SparseArray();
            for (int i = n; i < n2; ++i) {
                final BackStackRecord backStackRecord = list.get(i);
                if (list2.get(i)) {
                    calculatePopFragments(backStackRecord, (SparseArray<FragmentTransition$FragmentContainerTransition>)sparseArray, b);
                }
                else {
                    calculateFragments(backStackRecord, (SparseArray<FragmentTransition$FragmentContainerTransition>)sparseArray, b);
                }
            }
            if (sparseArray.size() != 0) {
                final View view = new View(fragmentManagerImpl.mHost.getContext());
                for (int size = sparseArray.size(), j = 0; j < size; ++j) {
                    final int key = sparseArray.keyAt(j);
                    final ArrayMap<String, String> calculateNameOverrides = calculateNameOverrides(key, list, list2, n, n2);
                    final FragmentTransition$FragmentContainerTransition fragmentTransition$FragmentContainerTransition = (FragmentTransition$FragmentContainerTransition)sparseArray.valueAt(j);
                    if (b) {
                        configureTransitionsOptimized(fragmentManagerImpl, key, fragmentTransition$FragmentContainerTransition, view, calculateNameOverrides);
                    }
                    else {
                        configureTransitionsUnoptimized(fragmentManagerImpl, key, fragmentTransition$FragmentContainerTransition, view, calculateNameOverrides);
                    }
                }
            }
        }
    }
}
