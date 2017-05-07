// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import java.io.FileDescriptor;
import java.io.Writer;
import java.io.PrintWriter;
import android.support.v4.util.LogWriter;
import android.util.Log;
import android.os.Build$VERSION;
import android.view.ViewTreeObserver$OnPreDrawListener;
import android.support.v4.util.SimpleArrayMap;
import android.view.ViewGroup;
import java.util.Map;
import java.util.List;
import java.util.Collection;
import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.support.v4.util.ArrayMap;
import java.util.ArrayList;

final class BackStackRecord extends FragmentTransaction implements FragmentManager$BackStackEntry, Runnable
{
    static final int OP_ADD = 1;
    static final int OP_ATTACH = 7;
    static final int OP_DETACH = 6;
    static final int OP_HIDE = 4;
    static final int OP_NULL = 0;
    static final int OP_REMOVE = 3;
    static final int OP_REPLACE = 2;
    static final int OP_SHOW = 5;
    static final String TAG = "FragmentManager";
    boolean mAddToBackStack;
    boolean mAllowAddToBackStack;
    int mBreadCrumbShortTitleRes;
    CharSequence mBreadCrumbShortTitleText;
    int mBreadCrumbTitleRes;
    CharSequence mBreadCrumbTitleText;
    boolean mCommitted;
    int mEnterAnim;
    int mExitAnim;
    BackStackRecord$Op mHead;
    int mIndex;
    final FragmentManagerImpl mManager;
    String mName;
    int mNumOp;
    int mPopEnterAnim;
    int mPopExitAnim;
    ArrayList<String> mSharedElementSourceNames;
    ArrayList<String> mSharedElementTargetNames;
    BackStackRecord$Op mTail;
    int mTransition;
    int mTransitionStyle;
    
    public BackStackRecord(final FragmentManagerImpl mManager) {
        this.mAllowAddToBackStack = true;
        this.mIndex = -1;
        this.mManager = mManager;
    }
    
    private BackStackRecord$TransitionState beginTransition(final SparseArray<Fragment> sparseArray, final SparseArray<Fragment> sparseArray2, final boolean b) {
        final int n = 0;
        final BackStackRecord$TransitionState backStackRecord$TransitionState = new BackStackRecord$TransitionState(this);
        backStackRecord$TransitionState.nonExistentView = new View((Context)this.mManager.mActivity);
        int n2 = 0;
        int n3 = 0;
        int i;
        int n4;
        while (true) {
            i = n;
            n4 = n3;
            if (n2 >= sparseArray.size()) {
                break;
            }
            if (this.configureTransitions(sparseArray.keyAt(n2), backStackRecord$TransitionState, b, sparseArray, sparseArray2)) {
                n3 = 1;
            }
            ++n2;
        }
        while (i < sparseArray2.size()) {
            final int key = sparseArray2.keyAt(i);
            int n5 = n4;
            if (sparseArray.get(key) == null) {
                n5 = n4;
                if (this.configureTransitions(key, backStackRecord$TransitionState, b, sparseArray, sparseArray2)) {
                    n5 = 1;
                }
            }
            ++i;
            n4 = n5;
        }
        BackStackRecord$TransitionState backStackRecord$TransitionState2 = backStackRecord$TransitionState;
        if (n4 == 0) {
            backStackRecord$TransitionState2 = null;
        }
        return backStackRecord$TransitionState2;
    }
    
    private void calculateFragments(final SparseArray<Fragment> sparseArray, final SparseArray<Fragment> sparseArray2) {
        if (this.mManager.mContainer.hasView()) {
            for (BackStackRecord$Op backStackRecord$Op = this.mHead; backStackRecord$Op != null; backStackRecord$Op = backStackRecord$Op.next) {
                switch (backStackRecord$Op.cmd) {
                    case 1: {
                        this.setLastIn(sparseArray2, backStackRecord$Op.fragment);
                        break;
                    }
                    case 2: {
                        Fragment fragment = backStackRecord$Op.fragment;
                        Fragment fragment2;
                        if (this.mManager.mAdded != null) {
                            int n = 0;
                            while (true) {
                                fragment2 = fragment;
                                if (n >= this.mManager.mAdded.size()) {
                                    break;
                                }
                                final Fragment fragment3 = this.mManager.mAdded.get(n);
                                Fragment fragment4 = null;
                                Label_0184: {
                                    if (fragment != null) {
                                        fragment4 = fragment;
                                        if (fragment3.mContainerId != fragment.mContainerId) {
                                            break Label_0184;
                                        }
                                    }
                                    if (fragment3 == fragment) {
                                        fragment4 = null;
                                    }
                                    else {
                                        setFirstOut(sparseArray, fragment3);
                                        fragment4 = fragment;
                                    }
                                }
                                ++n;
                                fragment = fragment4;
                            }
                        }
                        else {
                            fragment2 = fragment;
                        }
                        this.setLastIn(sparseArray2, fragment2);
                        break;
                    }
                    case 3: {
                        setFirstOut(sparseArray, backStackRecord$Op.fragment);
                        break;
                    }
                    case 4: {
                        setFirstOut(sparseArray, backStackRecord$Op.fragment);
                        break;
                    }
                    case 5: {
                        this.setLastIn(sparseArray2, backStackRecord$Op.fragment);
                        break;
                    }
                    case 6: {
                        setFirstOut(sparseArray, backStackRecord$Op.fragment);
                        break;
                    }
                    case 7: {
                        this.setLastIn(sparseArray2, backStackRecord$Op.fragment);
                        break;
                    }
                }
            }
        }
    }
    
    private void callSharedElementEnd(final BackStackRecord$TransitionState backStackRecord$TransitionState, final Fragment fragment, final Fragment fragment2, final boolean b, final ArrayMap<String, View> arrayMap) {
        SharedElementCallback sharedElementCallback;
        if (b) {
            sharedElementCallback = fragment2.mEnterTransitionCallback;
        }
        else {
            sharedElementCallback = fragment.mEnterTransitionCallback;
        }
        if (sharedElementCallback != null) {
            sharedElementCallback.onSharedElementEnd(new ArrayList<String>(arrayMap.keySet()), new ArrayList<View>(arrayMap.values()), null);
        }
    }
    
    private static Object captureExitingViews(final Object o, final Fragment fragment, final ArrayList<View> list, final ArrayMap<String, View> arrayMap) {
        Object captureExitingViews = o;
        if (o != null) {
            captureExitingViews = FragmentTransitionCompat21.captureExitingViews(o, fragment.getView(), list, arrayMap);
        }
        return captureExitingViews;
    }
    
    private boolean configureTransitions(final int n, final BackStackRecord$TransitionState backStackRecord$TransitionState, final boolean b, final SparseArray<Fragment> sparseArray, final SparseArray<Fragment> sparseArray2) {
        final ViewGroup viewGroup = (ViewGroup)this.mManager.mContainer.findViewById(n);
        if (viewGroup == null) {
            return false;
        }
        final Fragment fragment = (Fragment)sparseArray2.get(n);
        final Fragment fragment2 = (Fragment)sparseArray.get(n);
        final Object enterTransition = getEnterTransition(fragment, b);
        final Object sharedElementTransition = getSharedElementTransition(fragment, fragment2, b);
        final Object exitTransition = getExitTransition(fragment2, b);
        if (enterTransition == null && sharedElementTransition == null && exitTransition == null) {
            return false;
        }
        SimpleArrayMap<Object, View> simpleArrayMap = null;
        final ArrayList<View> list = new ArrayList<View>();
        if (sharedElementTransition != null) {
            final ArrayMap<String, View> remapSharedElements = this.remapSharedElements(backStackRecord$TransitionState, fragment2, b);
            if (remapSharedElements.isEmpty()) {
                list.add(backStackRecord$TransitionState.nonExistentView);
            }
            else {
                list.addAll(remapSharedElements.values());
            }
            SharedElementCallback sharedElementCallback;
            if (b) {
                sharedElementCallback = fragment2.mEnterTransitionCallback;
            }
            else {
                sharedElementCallback = fragment.mEnterTransitionCallback;
            }
            simpleArrayMap = (SimpleArrayMap<Object, View>)remapSharedElements;
            if (sharedElementCallback != null) {
                sharedElementCallback.onSharedElementStart(new ArrayList<String>(remapSharedElements.keySet()), new ArrayList<View>(remapSharedElements.values()), null);
                simpleArrayMap = (SimpleArrayMap<Object, View>)remapSharedElements;
            }
        }
        final ArrayList<View> list2 = new ArrayList<View>();
        final Object captureExitingViews = captureExitingViews(exitTransition, fragment2, list2, (ArrayMap<String, View>)simpleArrayMap);
        if (this.mSharedElementTargetNames != null && simpleArrayMap != null) {
            final View view = simpleArrayMap.get(this.mSharedElementTargetNames.get(0));
            if (view != null) {
                if (captureExitingViews != null) {
                    FragmentTransitionCompat21.setEpicenter(captureExitingViews, view);
                }
                if (sharedElementTransition != null) {
                    FragmentTransitionCompat21.setEpicenter(sharedElementTransition, view);
                }
            }
        }
        final BackStackRecord$1 backStackRecord$1 = new BackStackRecord$1(this, fragment);
        if (sharedElementTransition != null) {
            this.prepareSharedElementTransition(backStackRecord$TransitionState, (View)viewGroup, sharedElementTransition, fragment, fragment2, b, list);
        }
        final ArrayList<View> list3 = new ArrayList<View>();
        final ArrayMap<String, View> arrayMap = new ArrayMap<String, View>();
        boolean b2;
        if (b) {
            b2 = fragment.getAllowReturnTransitionOverlap();
        }
        else {
            b2 = fragment.getAllowEnterTransitionOverlap();
        }
        final Object mergeTransitions = FragmentTransitionCompat21.mergeTransitions(enterTransition, captureExitingViews, sharedElementTransition, b2);
        if (mergeTransitions != null) {
            FragmentTransitionCompat21.addTransitionTargets(enterTransition, sharedElementTransition, (View)viewGroup, backStackRecord$1, backStackRecord$TransitionState.nonExistentView, backStackRecord$TransitionState.enteringEpicenterView, backStackRecord$TransitionState.nameOverrides, list3, arrayMap, list);
            this.excludeHiddenFragmentsAfterEnter((View)viewGroup, backStackRecord$TransitionState, n, mergeTransitions);
            FragmentTransitionCompat21.excludeTarget(mergeTransitions, backStackRecord$TransitionState.nonExistentView, true);
            this.excludeHiddenFragments(backStackRecord$TransitionState, n, mergeTransitions);
            FragmentTransitionCompat21.beginDelayedTransition(viewGroup, mergeTransitions);
            FragmentTransitionCompat21.cleanupTransitions((View)viewGroup, backStackRecord$TransitionState.nonExistentView, enterTransition, list3, captureExitingViews, list2, sharedElementTransition, list, mergeTransitions, backStackRecord$TransitionState.hiddenFragmentViews, arrayMap);
        }
        return mergeTransitions != null;
    }
    
    private void doAddOp(final int n, final Fragment fragment, final String mTag, final int cmd) {
        fragment.mFragmentManager = this.mManager;
        if (mTag != null) {
            if (fragment.mTag != null && !mTag.equals(fragment.mTag)) {
                throw new IllegalStateException("Can't change tag of fragment " + fragment + ": was " + fragment.mTag + " now " + mTag);
            }
            fragment.mTag = mTag;
        }
        if (n != 0) {
            if (fragment.mFragmentId != 0 && fragment.mFragmentId != n) {
                throw new IllegalStateException("Can't change container ID of fragment " + fragment + ": was " + fragment.mFragmentId + " now " + n);
            }
            fragment.mFragmentId = n;
            fragment.mContainerId = n;
        }
        final BackStackRecord$Op backStackRecord$Op = new BackStackRecord$Op();
        backStackRecord$Op.cmd = cmd;
        backStackRecord$Op.fragment = fragment;
        this.addOp(backStackRecord$Op);
    }
    
    private void excludeHiddenFragments(final BackStackRecord$TransitionState backStackRecord$TransitionState, final int n, final Object o) {
        if (this.mManager.mAdded != null) {
            for (int i = 0; i < this.mManager.mAdded.size(); ++i) {
                final Fragment fragment = this.mManager.mAdded.get(i);
                if (fragment.mView != null && fragment.mContainer != null && fragment.mContainerId == n) {
                    if (fragment.mHidden) {
                        if (!backStackRecord$TransitionState.hiddenFragmentViews.contains(fragment.mView)) {
                            FragmentTransitionCompat21.excludeTarget(o, fragment.mView, true);
                            backStackRecord$TransitionState.hiddenFragmentViews.add(fragment.mView);
                        }
                    }
                    else {
                        FragmentTransitionCompat21.excludeTarget(o, fragment.mView, false);
                        backStackRecord$TransitionState.hiddenFragmentViews.remove(fragment.mView);
                    }
                }
            }
        }
    }
    
    private void excludeHiddenFragmentsAfterEnter(final View view, final BackStackRecord$TransitionState backStackRecord$TransitionState, final int n, final Object o) {
        view.getViewTreeObserver().addOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)new BackStackRecord$3(this, view, backStackRecord$TransitionState, n, o));
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
        return FragmentTransitionCompat21.cloneTransition(o);
    }
    
    private ArrayMap<String, View> mapEnteringSharedElements(final BackStackRecord$TransitionState backStackRecord$TransitionState, final Fragment fragment, final boolean b) {
        final ArrayMap<String, View> arrayMap = new ArrayMap<String, View>();
        final View view = fragment.getView();
        ArrayMap<String, View> remapNames = arrayMap;
        if (view != null) {
            remapNames = arrayMap;
            if (this.mSharedElementSourceNames != null) {
                FragmentTransitionCompat21.findNamedViews(arrayMap, view);
                if (!b) {
                    arrayMap.retainAll(this.mSharedElementTargetNames);
                    return arrayMap;
                }
                remapNames = remapNames(this.mSharedElementSourceNames, this.mSharedElementTargetNames, arrayMap);
            }
        }
        return remapNames;
    }
    
    private ArrayMap<String, View> mapSharedElementsIn(final BackStackRecord$TransitionState backStackRecord$TransitionState, final boolean b, final Fragment fragment) {
        final ArrayMap<String, View> mapEnteringSharedElements = this.mapEnteringSharedElements(backStackRecord$TransitionState, fragment, b);
        if (b) {
            if (fragment.mExitTransitionCallback != null) {
                fragment.mExitTransitionCallback.onMapSharedElements(this.mSharedElementTargetNames, mapEnteringSharedElements);
            }
            this.setBackNameOverrides(backStackRecord$TransitionState, mapEnteringSharedElements, true);
            return mapEnteringSharedElements;
        }
        if (fragment.mEnterTransitionCallback != null) {
            fragment.mEnterTransitionCallback.onMapSharedElements(this.mSharedElementTargetNames, mapEnteringSharedElements);
        }
        this.setNameOverrides(backStackRecord$TransitionState, mapEnteringSharedElements, true);
        return mapEnteringSharedElements;
    }
    
    private void prepareSharedElementTransition(final BackStackRecord$TransitionState backStackRecord$TransitionState, final View view, final Object o, final Fragment fragment, final Fragment fragment2, final boolean b, final ArrayList<View> list) {
        view.getViewTreeObserver().addOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)new BackStackRecord$2(this, view, o, list, backStackRecord$TransitionState, b, fragment, fragment2));
    }
    
    private static ArrayMap<String, View> remapNames(final ArrayList<String> list, final ArrayList<String> list2, final ArrayMap<String, View> arrayMap) {
        if (arrayMap.isEmpty()) {
            return arrayMap;
        }
        final ArrayMap<String, View> arrayMap2 = new ArrayMap<String, View>();
        for (int size = list.size(), i = 0; i < size; ++i) {
            final View view = arrayMap.get(list.get(i));
            if (view != null) {
                arrayMap2.put(list2.get(i), view);
            }
        }
        return arrayMap2;
    }
    
    private ArrayMap<String, View> remapSharedElements(final BackStackRecord$TransitionState backStackRecord$TransitionState, final Fragment fragment, final boolean b) {
        ArrayMap<String, View> remapNames;
        final ArrayMap<String, View> arrayMap = remapNames = new ArrayMap<String, View>();
        if (this.mSharedElementSourceNames != null) {
            FragmentTransitionCompat21.findNamedViews(arrayMap, fragment.getView());
            if (b) {
                arrayMap.retainAll(this.mSharedElementTargetNames);
                remapNames = arrayMap;
            }
            else {
                remapNames = remapNames(this.mSharedElementSourceNames, this.mSharedElementTargetNames, arrayMap);
            }
        }
        if (b) {
            if (fragment.mEnterTransitionCallback != null) {
                fragment.mEnterTransitionCallback.onMapSharedElements(this.mSharedElementTargetNames, remapNames);
            }
            this.setBackNameOverrides(backStackRecord$TransitionState, remapNames, false);
            return remapNames;
        }
        if (fragment.mExitTransitionCallback != null) {
            fragment.mExitTransitionCallback.onMapSharedElements(this.mSharedElementTargetNames, remapNames);
        }
        this.setNameOverrides(backStackRecord$TransitionState, remapNames, false);
        return remapNames;
    }
    
    private void setBackNameOverrides(final BackStackRecord$TransitionState backStackRecord$TransitionState, final ArrayMap<String, View> arrayMap, final boolean b) {
        int size;
        if (this.mSharedElementTargetNames == null) {
            size = 0;
        }
        else {
            size = this.mSharedElementTargetNames.size();
        }
        for (int i = 0; i < size; ++i) {
            final String s = this.mSharedElementSourceNames.get(i);
            final View view = arrayMap.get(this.mSharedElementTargetNames.get(i));
            if (view != null) {
                final String transitionName = FragmentTransitionCompat21.getTransitionName(view);
                if (b) {
                    setNameOverride(backStackRecord$TransitionState.nameOverrides, s, transitionName);
                }
                else {
                    setNameOverride(backStackRecord$TransitionState.nameOverrides, transitionName, s);
                }
            }
        }
    }
    
    private void setEpicenterIn(final ArrayMap<String, View> arrayMap, final BackStackRecord$TransitionState backStackRecord$TransitionState) {
        if (this.mSharedElementTargetNames != null && !arrayMap.isEmpty()) {
            final View epicenter = arrayMap.get(this.mSharedElementTargetNames.get(0));
            if (epicenter != null) {
                backStackRecord$TransitionState.enteringEpicenterView.epicenter = epicenter;
            }
        }
    }
    
    private static void setFirstOut(final SparseArray<Fragment> sparseArray, final Fragment fragment) {
        if (fragment != null) {
            final int mContainerId = fragment.mContainerId;
            if (mContainerId != 0 && !fragment.isHidden() && fragment.isAdded() && fragment.getView() != null && sparseArray.get(mContainerId) == null) {
                sparseArray.put(mContainerId, (Object)fragment);
            }
        }
    }
    
    private void setLastIn(final SparseArray<Fragment> sparseArray, final Fragment fragment) {
        if (fragment != null) {
            final int mContainerId = fragment.mContainerId;
            if (mContainerId != 0) {
                sparseArray.put(mContainerId, (Object)fragment);
            }
        }
    }
    
    private static void setNameOverride(final ArrayMap<String, String> arrayMap, final String s, final String s2) {
        if (s != null && s2 != null && !s.equals(s2)) {
            for (int i = 0; i < arrayMap.size(); ++i) {
                if (s.equals(arrayMap.valueAt(i))) {
                    arrayMap.setValueAt(i, s2);
                    return;
                }
            }
            arrayMap.put(s, s2);
        }
    }
    
    private void setNameOverrides(final BackStackRecord$TransitionState backStackRecord$TransitionState, final ArrayMap<String, View> arrayMap, final boolean b) {
        for (int size = arrayMap.size(), i = 0; i < size; ++i) {
            final String s = arrayMap.keyAt(i);
            final String transitionName = FragmentTransitionCompat21.getTransitionName(arrayMap.valueAt(i));
            if (b) {
                setNameOverride(backStackRecord$TransitionState.nameOverrides, s, transitionName);
            }
            else {
                setNameOverride(backStackRecord$TransitionState.nameOverrides, transitionName, s);
            }
        }
    }
    
    private static void setNameOverrides(final BackStackRecord$TransitionState backStackRecord$TransitionState, final ArrayList<String> list, final ArrayList<String> list2) {
        if (list != null) {
            for (int i = 0; i < list.size(); ++i) {
                setNameOverride(backStackRecord$TransitionState.nameOverrides, list.get(i), list2.get(i));
            }
        }
    }
    
    @Override
    public FragmentTransaction add(final int n, final Fragment fragment) {
        this.doAddOp(n, fragment, null, 1);
        return this;
    }
    
    @Override
    public FragmentTransaction add(final int n, final Fragment fragment, final String s) {
        this.doAddOp(n, fragment, s, 1);
        return this;
    }
    
    @Override
    public FragmentTransaction add(final Fragment fragment, final String s) {
        this.doAddOp(0, fragment, s, 1);
        return this;
    }
    
    void addOp(final BackStackRecord$Op backStackRecord$Op) {
        if (this.mHead == null) {
            this.mTail = backStackRecord$Op;
            this.mHead = backStackRecord$Op;
        }
        else {
            backStackRecord$Op.prev = this.mTail;
            this.mTail.next = backStackRecord$Op;
            this.mTail = backStackRecord$Op;
        }
        backStackRecord$Op.enterAnim = this.mEnterAnim;
        backStackRecord$Op.exitAnim = this.mExitAnim;
        backStackRecord$Op.popEnterAnim = this.mPopEnterAnim;
        backStackRecord$Op.popExitAnim = this.mPopExitAnim;
        ++this.mNumOp;
    }
    
    @Override
    public FragmentTransaction addSharedElement(final View view, final String s) {
        if (Build$VERSION.SDK_INT >= 21) {
            final String transitionName = FragmentTransitionCompat21.getTransitionName(view);
            if (transitionName == null) {
                throw new IllegalArgumentException("Unique transitionNames are required for all sharedElements");
            }
            if (this.mSharedElementSourceNames == null) {
                this.mSharedElementSourceNames = new ArrayList<String>();
                this.mSharedElementTargetNames = new ArrayList<String>();
            }
            this.mSharedElementSourceNames.add(transitionName);
            this.mSharedElementTargetNames.add(s);
        }
        return this;
    }
    
    @Override
    public FragmentTransaction addToBackStack(final String mName) {
        if (!this.mAllowAddToBackStack) {
            throw new IllegalStateException("This FragmentTransaction is not allowed to be added to the back stack.");
        }
        this.mAddToBackStack = true;
        this.mName = mName;
        return this;
    }
    
    @Override
    public FragmentTransaction attach(final Fragment fragment) {
        final BackStackRecord$Op backStackRecord$Op = new BackStackRecord$Op();
        backStackRecord$Op.cmd = 7;
        backStackRecord$Op.fragment = fragment;
        this.addOp(backStackRecord$Op);
        return this;
    }
    
    void bumpBackStackNesting(final int n) {
        if (this.mAddToBackStack) {
            if (FragmentManagerImpl.DEBUG) {
                Log.v("FragmentManager", "Bump nesting in " + this + " by " + n);
            }
            for (BackStackRecord$Op backStackRecord$Op = this.mHead; backStackRecord$Op != null; backStackRecord$Op = backStackRecord$Op.next) {
                if (backStackRecord$Op.fragment != null) {
                    final Fragment fragment = backStackRecord$Op.fragment;
                    fragment.mBackStackNesting += n;
                    if (FragmentManagerImpl.DEBUG) {
                        Log.v("FragmentManager", "Bump nesting of " + backStackRecord$Op.fragment + " to " + backStackRecord$Op.fragment.mBackStackNesting);
                    }
                }
                if (backStackRecord$Op.removed != null) {
                    for (int i = backStackRecord$Op.removed.size() - 1; i >= 0; --i) {
                        final Fragment fragment2 = backStackRecord$Op.removed.get(i);
                        fragment2.mBackStackNesting += n;
                        if (FragmentManagerImpl.DEBUG) {
                            Log.v("FragmentManager", "Bump nesting of " + fragment2 + " to " + fragment2.mBackStackNesting);
                        }
                    }
                }
            }
        }
    }
    
    public void calculateBackFragments(final SparseArray<Fragment> sparseArray, final SparseArray<Fragment> sparseArray2) {
        if (this.mManager.mContainer.hasView()) {
            for (BackStackRecord$Op backStackRecord$Op = this.mHead; backStackRecord$Op != null; backStackRecord$Op = backStackRecord$Op.next) {
                switch (backStackRecord$Op.cmd) {
                    case 1: {
                        setFirstOut(sparseArray, backStackRecord$Op.fragment);
                        break;
                    }
                    case 2: {
                        if (backStackRecord$Op.removed != null) {
                            for (int i = backStackRecord$Op.removed.size() - 1; i >= 0; --i) {
                                this.setLastIn(sparseArray2, backStackRecord$Op.removed.get(i));
                            }
                        }
                        setFirstOut(sparseArray, backStackRecord$Op.fragment);
                        break;
                    }
                    case 3: {
                        this.setLastIn(sparseArray2, backStackRecord$Op.fragment);
                        break;
                    }
                    case 4: {
                        this.setLastIn(sparseArray2, backStackRecord$Op.fragment);
                        break;
                    }
                    case 5: {
                        setFirstOut(sparseArray, backStackRecord$Op.fragment);
                        break;
                    }
                    case 6: {
                        this.setLastIn(sparseArray2, backStackRecord$Op.fragment);
                        break;
                    }
                    case 7: {
                        setFirstOut(sparseArray, backStackRecord$Op.fragment);
                        break;
                    }
                }
            }
        }
    }
    
    @Override
    public int commit() {
        return this.commitInternal(false);
    }
    
    @Override
    public int commitAllowingStateLoss() {
        return this.commitInternal(true);
    }
    
    int commitInternal(final boolean b) {
        if (this.mCommitted) {
            throw new IllegalStateException("commit already called");
        }
        if (FragmentManagerImpl.DEBUG) {
            Log.v("FragmentManager", "Commit: " + this);
            this.dump("  ", null, new PrintWriter(new LogWriter("FragmentManager")), null);
        }
        this.mCommitted = true;
        if (this.mAddToBackStack) {
            this.mIndex = this.mManager.allocBackStackIndex(this);
        }
        else {
            this.mIndex = -1;
        }
        this.mManager.enqueueAction(this, b);
        return this.mIndex;
    }
    
    @Override
    public FragmentTransaction detach(final Fragment fragment) {
        final BackStackRecord$Op backStackRecord$Op = new BackStackRecord$Op();
        backStackRecord$Op.cmd = 6;
        backStackRecord$Op.fragment = fragment;
        this.addOp(backStackRecord$Op);
        return this;
    }
    
    @Override
    public FragmentTransaction disallowAddToBackStack() {
        if (this.mAddToBackStack) {
            throw new IllegalStateException("This transaction is already being added to the back stack");
        }
        this.mAllowAddToBackStack = false;
        return this;
    }
    
    public void dump(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        this.dump(s, printWriter, true);
    }
    
    public void dump(final String s, final PrintWriter printWriter, final boolean b) {
        if (b) {
            printWriter.print(s);
            printWriter.print("mName=");
            printWriter.print(this.mName);
            printWriter.print(" mIndex=");
            printWriter.print(this.mIndex);
            printWriter.print(" mCommitted=");
            printWriter.println(this.mCommitted);
            if (this.mTransition != 0) {
                printWriter.print(s);
                printWriter.print("mTransition=#");
                printWriter.print(Integer.toHexString(this.mTransition));
                printWriter.print(" mTransitionStyle=#");
                printWriter.println(Integer.toHexString(this.mTransitionStyle));
            }
            if (this.mEnterAnim != 0 || this.mExitAnim != 0) {
                printWriter.print(s);
                printWriter.print("mEnterAnim=#");
                printWriter.print(Integer.toHexString(this.mEnterAnim));
                printWriter.print(" mExitAnim=#");
                printWriter.println(Integer.toHexString(this.mExitAnim));
            }
            if (this.mPopEnterAnim != 0 || this.mPopExitAnim != 0) {
                printWriter.print(s);
                printWriter.print("mPopEnterAnim=#");
                printWriter.print(Integer.toHexString(this.mPopEnterAnim));
                printWriter.print(" mPopExitAnim=#");
                printWriter.println(Integer.toHexString(this.mPopExitAnim));
            }
            if (this.mBreadCrumbTitleRes != 0 || this.mBreadCrumbTitleText != null) {
                printWriter.print(s);
                printWriter.print("mBreadCrumbTitleRes=#");
                printWriter.print(Integer.toHexString(this.mBreadCrumbTitleRes));
                printWriter.print(" mBreadCrumbTitleText=");
                printWriter.println(this.mBreadCrumbTitleText);
            }
            if (this.mBreadCrumbShortTitleRes != 0 || this.mBreadCrumbShortTitleText != null) {
                printWriter.print(s);
                printWriter.print("mBreadCrumbShortTitleRes=#");
                printWriter.print(Integer.toHexString(this.mBreadCrumbShortTitleRes));
                printWriter.print(" mBreadCrumbShortTitleText=");
                printWriter.println(this.mBreadCrumbShortTitleText);
            }
        }
        if (this.mHead != null) {
            printWriter.print(s);
            printWriter.println("Operations:");
            final String string = s + "    ";
            BackStackRecord$Op backStackRecord$Op = this.mHead;
            for (int n = 0; backStackRecord$Op != null; backStackRecord$Op = backStackRecord$Op.next, ++n) {
                String string2 = null;
                switch (backStackRecord$Op.cmd) {
                    default: {
                        string2 = "cmd=" + backStackRecord$Op.cmd;
                        break;
                    }
                    case 0: {
                        string2 = "NULL";
                        break;
                    }
                    case 1: {
                        string2 = "ADD";
                        break;
                    }
                    case 2: {
                        string2 = "REPLACE";
                        break;
                    }
                    case 3: {
                        string2 = "REMOVE";
                        break;
                    }
                    case 4: {
                        string2 = "HIDE";
                        break;
                    }
                    case 5: {
                        string2 = "SHOW";
                        break;
                    }
                    case 6: {
                        string2 = "DETACH";
                        break;
                    }
                    case 7: {
                        string2 = "ATTACH";
                        break;
                    }
                }
                printWriter.print(s);
                printWriter.print("  Op #");
                printWriter.print(n);
                printWriter.print(": ");
                printWriter.print(string2);
                printWriter.print(" ");
                printWriter.println(backStackRecord$Op.fragment);
                if (b) {
                    if (backStackRecord$Op.enterAnim != 0 || backStackRecord$Op.exitAnim != 0) {
                        printWriter.print(s);
                        printWriter.print("enterAnim=#");
                        printWriter.print(Integer.toHexString(backStackRecord$Op.enterAnim));
                        printWriter.print(" exitAnim=#");
                        printWriter.println(Integer.toHexString(backStackRecord$Op.exitAnim));
                    }
                    if (backStackRecord$Op.popEnterAnim != 0 || backStackRecord$Op.popExitAnim != 0) {
                        printWriter.print(s);
                        printWriter.print("popEnterAnim=#");
                        printWriter.print(Integer.toHexString(backStackRecord$Op.popEnterAnim));
                        printWriter.print(" popExitAnim=#");
                        printWriter.println(Integer.toHexString(backStackRecord$Op.popExitAnim));
                    }
                }
                if (backStackRecord$Op.removed != null && backStackRecord$Op.removed.size() > 0) {
                    for (int i = 0; i < backStackRecord$Op.removed.size(); ++i) {
                        printWriter.print(string);
                        if (backStackRecord$Op.removed.size() == 1) {
                            printWriter.print("Removed: ");
                        }
                        else {
                            if (i == 0) {
                                printWriter.println("Removed:");
                            }
                            printWriter.print(string);
                            printWriter.print("  #");
                            printWriter.print(i);
                            printWriter.print(": ");
                        }
                        printWriter.println(backStackRecord$Op.removed.get(i));
                    }
                }
            }
        }
    }
    
    @Override
    public CharSequence getBreadCrumbShortTitle() {
        if (this.mBreadCrumbShortTitleRes != 0) {
            return this.mManager.mActivity.getText(this.mBreadCrumbShortTitleRes);
        }
        return this.mBreadCrumbShortTitleText;
    }
    
    @Override
    public int getBreadCrumbShortTitleRes() {
        return this.mBreadCrumbShortTitleRes;
    }
    
    @Override
    public CharSequence getBreadCrumbTitle() {
        if (this.mBreadCrumbTitleRes != 0) {
            return this.mManager.mActivity.getText(this.mBreadCrumbTitleRes);
        }
        return this.mBreadCrumbTitleText;
    }
    
    @Override
    public int getBreadCrumbTitleRes() {
        return this.mBreadCrumbTitleRes;
    }
    
    @Override
    public int getId() {
        return this.mIndex;
    }
    
    @Override
    public String getName() {
        return this.mName;
    }
    
    public int getTransition() {
        return this.mTransition;
    }
    
    public int getTransitionStyle() {
        return this.mTransitionStyle;
    }
    
    @Override
    public FragmentTransaction hide(final Fragment fragment) {
        final BackStackRecord$Op backStackRecord$Op = new BackStackRecord$Op();
        backStackRecord$Op.cmd = 4;
        backStackRecord$Op.fragment = fragment;
        this.addOp(backStackRecord$Op);
        return this;
    }
    
    @Override
    public boolean isAddToBackStackAllowed() {
        return this.mAllowAddToBackStack;
    }
    
    @Override
    public boolean isEmpty() {
        return this.mNumOp == 0;
    }
    
    public BackStackRecord$TransitionState popFromBackStack(final boolean b, final BackStackRecord$TransitionState backStackRecord$TransitionState, final SparseArray<Fragment> sparseArray, final SparseArray<Fragment> sparseArray2) {
        if (FragmentManagerImpl.DEBUG) {
            Log.v("FragmentManager", "popFromBackStack: " + this);
            this.dump("  ", null, new PrintWriter(new LogWriter("FragmentManager")), null);
        }
        BackStackRecord$TransitionState beginTransition = null;
        Label_0089: {
            if (backStackRecord$TransitionState == null) {
                if (sparseArray.size() == 0) {
                    beginTransition = backStackRecord$TransitionState;
                    if (sparseArray2.size() == 0) {
                        break Label_0089;
                    }
                }
                beginTransition = this.beginTransition(sparseArray, sparseArray2, true);
            }
            else {
                beginTransition = backStackRecord$TransitionState;
                if (!b) {
                    setNameOverrides(backStackRecord$TransitionState, this.mSharedElementTargetNames, this.mSharedElementSourceNames);
                    beginTransition = backStackRecord$TransitionState;
                }
            }
        }
        this.bumpBackStackNesting(-1);
        int mTransitionStyle;
        if (beginTransition != null) {
            mTransitionStyle = 0;
        }
        else {
            mTransitionStyle = this.mTransitionStyle;
        }
        int mTransition;
        if (beginTransition != null) {
            mTransition = 0;
        }
        else {
            mTransition = this.mTransition;
        }
        for (BackStackRecord$Op backStackRecord$Op = this.mTail; backStackRecord$Op != null; backStackRecord$Op = backStackRecord$Op.prev) {
            int popEnterAnim;
            if (beginTransition != null) {
                popEnterAnim = 0;
            }
            else {
                popEnterAnim = backStackRecord$Op.popEnterAnim;
            }
            int popExitAnim;
            if (beginTransition != null) {
                popExitAnim = 0;
            }
            else {
                popExitAnim = backStackRecord$Op.popExitAnim;
            }
            switch (backStackRecord$Op.cmd) {
                default: {
                    throw new IllegalArgumentException("Unknown cmd: " + backStackRecord$Op.cmd);
                }
                case 1: {
                    final Fragment fragment = backStackRecord$Op.fragment;
                    fragment.mNextAnim = popExitAnim;
                    this.mManager.removeFragment(fragment, FragmentManagerImpl.reverseTransit(mTransition), mTransitionStyle);
                    break;
                }
                case 2: {
                    final Fragment fragment2 = backStackRecord$Op.fragment;
                    if (fragment2 != null) {
                        fragment2.mNextAnim = popExitAnim;
                        this.mManager.removeFragment(fragment2, FragmentManagerImpl.reverseTransit(mTransition), mTransitionStyle);
                    }
                    if (backStackRecord$Op.removed != null) {
                        for (int i = 0; i < backStackRecord$Op.removed.size(); ++i) {
                            final Fragment fragment3 = backStackRecord$Op.removed.get(i);
                            fragment3.mNextAnim = popEnterAnim;
                            this.mManager.addFragment(fragment3, false);
                        }
                        break;
                    }
                    break;
                }
                case 3: {
                    final Fragment fragment4 = backStackRecord$Op.fragment;
                    fragment4.mNextAnim = popEnterAnim;
                    this.mManager.addFragment(fragment4, false);
                    break;
                }
                case 4: {
                    final Fragment fragment5 = backStackRecord$Op.fragment;
                    fragment5.mNextAnim = popEnterAnim;
                    this.mManager.showFragment(fragment5, FragmentManagerImpl.reverseTransit(mTransition), mTransitionStyle);
                    break;
                }
                case 5: {
                    final Fragment fragment6 = backStackRecord$Op.fragment;
                    fragment6.mNextAnim = popExitAnim;
                    this.mManager.hideFragment(fragment6, FragmentManagerImpl.reverseTransit(mTransition), mTransitionStyle);
                    break;
                }
                case 6: {
                    final Fragment fragment7 = backStackRecord$Op.fragment;
                    fragment7.mNextAnim = popEnterAnim;
                    this.mManager.attachFragment(fragment7, FragmentManagerImpl.reverseTransit(mTransition), mTransitionStyle);
                    break;
                }
                case 7: {
                    final Fragment fragment8 = backStackRecord$Op.fragment;
                    fragment8.mNextAnim = popEnterAnim;
                    this.mManager.detachFragment(fragment8, FragmentManagerImpl.reverseTransit(mTransition), mTransitionStyle);
                    break;
                }
            }
        }
        if (b) {
            this.mManager.moveToState(this.mManager.mCurState, FragmentManagerImpl.reverseTransit(mTransition), mTransitionStyle, true);
            beginTransition = null;
        }
        if (this.mIndex >= 0) {
            this.mManager.freeBackStackIndex(this.mIndex);
            this.mIndex = -1;
        }
        return beginTransition;
    }
    
    @Override
    public FragmentTransaction remove(final Fragment fragment) {
        final BackStackRecord$Op backStackRecord$Op = new BackStackRecord$Op();
        backStackRecord$Op.cmd = 3;
        backStackRecord$Op.fragment = fragment;
        this.addOp(backStackRecord$Op);
        return this;
    }
    
    @Override
    public FragmentTransaction replace(final int n, final Fragment fragment) {
        return this.replace(n, fragment, null);
    }
    
    @Override
    public FragmentTransaction replace(final int n, final Fragment fragment, final String s) {
        if (n == 0) {
            throw new IllegalArgumentException("Must use non-zero containerViewId");
        }
        this.doAddOp(n, fragment, s, 2);
        return this;
    }
    
    @Override
    public void run() {
        if (FragmentManagerImpl.DEBUG) {
            Log.v("FragmentManager", "Run: " + this);
        }
        if (this.mAddToBackStack && this.mIndex < 0) {
            throw new IllegalStateException("addToBackStack() called after commit()");
        }
        this.bumpBackStackNesting(1);
        BackStackRecord$TransitionState beginTransition;
        if (Build$VERSION.SDK_INT >= 21) {
            final SparseArray sparseArray = new SparseArray();
            final SparseArray sparseArray2 = new SparseArray();
            this.calculateFragments((SparseArray<Fragment>)sparseArray, (SparseArray<Fragment>)sparseArray2);
            beginTransition = this.beginTransition((SparseArray<Fragment>)sparseArray, (SparseArray<Fragment>)sparseArray2, false);
        }
        else {
            beginTransition = null;
        }
        int mTransitionStyle;
        if (beginTransition != null) {
            mTransitionStyle = 0;
        }
        else {
            mTransitionStyle = this.mTransitionStyle;
        }
        int mTransition;
        if (beginTransition != null) {
            mTransition = 0;
        }
        else {
            mTransition = this.mTransition;
        }
        for (BackStackRecord$Op backStackRecord$Op = this.mHead; backStackRecord$Op != null; backStackRecord$Op = backStackRecord$Op.next) {
            int enterAnim;
            if (beginTransition != null) {
                enterAnim = 0;
            }
            else {
                enterAnim = backStackRecord$Op.enterAnim;
            }
            int exitAnim;
            if (beginTransition != null) {
                exitAnim = 0;
            }
            else {
                exitAnim = backStackRecord$Op.exitAnim;
            }
            switch (backStackRecord$Op.cmd) {
                default: {
                    throw new IllegalArgumentException("Unknown cmd: " + backStackRecord$Op.cmd);
                }
                case 1: {
                    final Fragment fragment = backStackRecord$Op.fragment;
                    fragment.mNextAnim = enterAnim;
                    this.mManager.addFragment(fragment, false);
                    break;
                }
                case 2: {
                    Fragment fragment2 = backStackRecord$Op.fragment;
                    Fragment fragment3;
                    if (this.mManager.mAdded != null) {
                        int n = 0;
                        while (true) {
                            fragment3 = fragment2;
                            if (n >= this.mManager.mAdded.size()) {
                                break;
                            }
                            final Fragment fragment4 = this.mManager.mAdded.get(n);
                            if (FragmentManagerImpl.DEBUG) {
                                Log.v("FragmentManager", "OP_REPLACE: adding=" + fragment2 + " old=" + fragment4);
                            }
                            Fragment fragment5 = null;
                            Label_0434: {
                                if (fragment2 != null) {
                                    fragment5 = fragment2;
                                    if (fragment4.mContainerId != fragment2.mContainerId) {
                                        break Label_0434;
                                    }
                                }
                                if (fragment4 == fragment2) {
                                    backStackRecord$Op.fragment = null;
                                    fragment5 = null;
                                }
                                else {
                                    if (backStackRecord$Op.removed == null) {
                                        backStackRecord$Op.removed = new ArrayList<Fragment>();
                                    }
                                    backStackRecord$Op.removed.add(fragment4);
                                    fragment4.mNextAnim = exitAnim;
                                    if (this.mAddToBackStack) {
                                        ++fragment4.mBackStackNesting;
                                        if (FragmentManagerImpl.DEBUG) {
                                            Log.v("FragmentManager", "Bump nesting of " + fragment4 + " to " + fragment4.mBackStackNesting);
                                        }
                                    }
                                    this.mManager.removeFragment(fragment4, mTransition, mTransitionStyle);
                                    fragment5 = fragment2;
                                }
                            }
                            ++n;
                            fragment2 = fragment5;
                        }
                    }
                    else {
                        fragment3 = fragment2;
                    }
                    if (fragment3 != null) {
                        fragment3.mNextAnim = enterAnim;
                        this.mManager.addFragment(fragment3, false);
                        break;
                    }
                    break;
                }
                case 3: {
                    final Fragment fragment6 = backStackRecord$Op.fragment;
                    fragment6.mNextAnim = exitAnim;
                    this.mManager.removeFragment(fragment6, mTransition, mTransitionStyle);
                    break;
                }
                case 4: {
                    final Fragment fragment7 = backStackRecord$Op.fragment;
                    fragment7.mNextAnim = exitAnim;
                    this.mManager.hideFragment(fragment7, mTransition, mTransitionStyle);
                    break;
                }
                case 5: {
                    final Fragment fragment8 = backStackRecord$Op.fragment;
                    fragment8.mNextAnim = enterAnim;
                    this.mManager.showFragment(fragment8, mTransition, mTransitionStyle);
                    break;
                }
                case 6: {
                    final Fragment fragment9 = backStackRecord$Op.fragment;
                    fragment9.mNextAnim = exitAnim;
                    this.mManager.detachFragment(fragment9, mTransition, mTransitionStyle);
                    break;
                }
                case 7: {
                    final Fragment fragment10 = backStackRecord$Op.fragment;
                    fragment10.mNextAnim = enterAnim;
                    this.mManager.attachFragment(fragment10, mTransition, mTransitionStyle);
                    break;
                }
            }
        }
        this.mManager.moveToState(this.mManager.mCurState, mTransition, mTransitionStyle, true);
        if (this.mAddToBackStack) {
            this.mManager.addBackStackState(this);
        }
    }
    
    @Override
    public FragmentTransaction setBreadCrumbShortTitle(final int mBreadCrumbShortTitleRes) {
        this.mBreadCrumbShortTitleRes = mBreadCrumbShortTitleRes;
        this.mBreadCrumbShortTitleText = null;
        return this;
    }
    
    @Override
    public FragmentTransaction setBreadCrumbShortTitle(final CharSequence mBreadCrumbShortTitleText) {
        this.mBreadCrumbShortTitleRes = 0;
        this.mBreadCrumbShortTitleText = mBreadCrumbShortTitleText;
        return this;
    }
    
    @Override
    public FragmentTransaction setBreadCrumbTitle(final int mBreadCrumbTitleRes) {
        this.mBreadCrumbTitleRes = mBreadCrumbTitleRes;
        this.mBreadCrumbTitleText = null;
        return this;
    }
    
    @Override
    public FragmentTransaction setBreadCrumbTitle(final CharSequence mBreadCrumbTitleText) {
        this.mBreadCrumbTitleRes = 0;
        this.mBreadCrumbTitleText = mBreadCrumbTitleText;
        return this;
    }
    
    @Override
    public FragmentTransaction setCustomAnimations(final int n, final int n2) {
        return this.setCustomAnimations(n, n2, 0, 0);
    }
    
    @Override
    public FragmentTransaction setCustomAnimations(final int mEnterAnim, final int mExitAnim, final int mPopEnterAnim, final int mPopExitAnim) {
        this.mEnterAnim = mEnterAnim;
        this.mExitAnim = mExitAnim;
        this.mPopEnterAnim = mPopEnterAnim;
        this.mPopExitAnim = mPopExitAnim;
        return this;
    }
    
    @Override
    public FragmentTransaction setTransition(final int mTransition) {
        this.mTransition = mTransition;
        return this;
    }
    
    @Override
    public FragmentTransaction setTransitionStyle(final int mTransitionStyle) {
        this.mTransitionStyle = mTransitionStyle;
        return this;
    }
    
    @Override
    public FragmentTransaction show(final Fragment fragment) {
        final BackStackRecord$Op backStackRecord$Op = new BackStackRecord$Op();
        backStackRecord$Op.cmd = 5;
        backStackRecord$Op.fragment = fragment;
        this.addOp(backStackRecord$Op);
        return this;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(128);
        sb.append("BackStackEntry{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        if (this.mIndex >= 0) {
            sb.append(" #");
            sb.append(this.mIndex);
        }
        if (this.mName != null) {
            sb.append(" ");
            sb.append(this.mName);
        }
        sb.append("}");
        return sb.toString();
    }
}
