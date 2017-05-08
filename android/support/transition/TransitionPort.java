// 
// Decompiled by Procyon v0.5.30
// 

package android.support.transition;

import java.util.List;
import java.util.Iterator;
import android.support.v4.util.LongSparseArray;
import android.util.SparseArray;
import android.support.v4.util.SimpleArrayMap;
import android.animation.Animator$AnimatorListener;
import android.widget.ListView;
import android.view.View;
import android.view.ViewGroup;
import android.animation.TimeInterpolator;
import java.util.ArrayList;
import android.animation.Animator;
import android.support.v4.util.ArrayMap;
import android.annotation.TargetApi;

@TargetApi(14)
abstract class TransitionPort implements Cloneable
{
    private static ThreadLocal<ArrayMap<Animator, TransitionPort$AnimationInfo>> sRunningAnimators;
    ArrayList<Animator> mAnimators;
    boolean mCanRemoveViews;
    ArrayList<Animator> mCurrentAnimators;
    long mDuration;
    private TransitionValuesMaps mEndValues;
    private boolean mEnded;
    TimeInterpolator mInterpolator;
    ArrayList<TransitionPort$TransitionListener> mListeners;
    private String mName;
    int mNumInstances;
    TransitionSetPort mParent;
    boolean mPaused;
    ViewGroup mSceneRoot;
    long mStartDelay;
    private TransitionValuesMaps mStartValues;
    ArrayList<View> mTargetChildExcludes;
    ArrayList<View> mTargetExcludes;
    ArrayList<Integer> mTargetIdChildExcludes;
    ArrayList<Integer> mTargetIdExcludes;
    ArrayList<Integer> mTargetIds;
    ArrayList<Class> mTargetTypeChildExcludes;
    ArrayList<Class> mTargetTypeExcludes;
    ArrayList<View> mTargets;
    
    static {
        TransitionPort.sRunningAnimators = new ThreadLocal<ArrayMap<Animator, TransitionPort$AnimationInfo>>();
    }
    
    public TransitionPort() {
        this.mStartDelay = -1L;
        this.mDuration = -1L;
        this.mInterpolator = null;
        this.mTargetIds = new ArrayList<Integer>();
        this.mTargets = new ArrayList<View>();
        this.mTargetIdExcludes = null;
        this.mTargetExcludes = null;
        this.mTargetTypeExcludes = null;
        this.mTargetIdChildExcludes = null;
        this.mTargetChildExcludes = null;
        this.mTargetTypeChildExcludes = null;
        this.mParent = null;
        this.mSceneRoot = null;
        this.mCanRemoveViews = false;
        this.mNumInstances = 0;
        this.mPaused = false;
        this.mListeners = null;
        this.mAnimators = new ArrayList<Animator>();
        this.mName = this.getClass().getName();
        this.mStartValues = new TransitionValuesMaps();
        this.mEndValues = new TransitionValuesMaps();
        this.mCurrentAnimators = new ArrayList<Animator>();
        this.mEnded = false;
    }
    
    private void captureHierarchy(final View view, final boolean b) {
        if (view != null) {
            boolean b2;
            if (view.getParent() instanceof ListView) {
                b2 = true;
            }
            else {
                b2 = false;
            }
            if (!b2 || ((ListView)view.getParent()).getAdapter().hasStableIds()) {
                int id;
                long itemIdAtPosition;
                if (!b2) {
                    id = view.getId();
                    itemIdAtPosition = -1L;
                }
                else {
                    final ListView listView = (ListView)view.getParent();
                    itemIdAtPosition = listView.getItemIdAtPosition(listView.getPositionForView(view));
                    id = -1;
                }
                if ((this.mTargetIdExcludes == null || !this.mTargetIdExcludes.contains(id)) && (this.mTargetExcludes == null || !this.mTargetExcludes.contains(view))) {
                    if (this.mTargetTypeExcludes != null && view != null) {
                        for (int size = this.mTargetTypeExcludes.size(), i = 0; i < size; ++i) {
                            if (this.mTargetTypeExcludes.get(i).isInstance(view)) {
                                return;
                            }
                        }
                    }
                    final TransitionValues transitionValues = new TransitionValues();
                    transitionValues.view = view;
                    if (b) {
                        this.captureStartValues(transitionValues);
                    }
                    else {
                        this.captureEndValues(transitionValues);
                    }
                    if (b) {
                        if (!b2) {
                            this.mStartValues.viewValues.put(view, transitionValues);
                            if (id >= 0) {
                                this.mStartValues.idValues.put(id, (Object)transitionValues);
                            }
                        }
                        else {
                            this.mStartValues.itemIdValues.put(itemIdAtPosition, transitionValues);
                        }
                    }
                    else if (!b2) {
                        this.mEndValues.viewValues.put(view, transitionValues);
                        if (id >= 0) {
                            this.mEndValues.idValues.put(id, (Object)transitionValues);
                        }
                    }
                    else {
                        this.mEndValues.itemIdValues.put(itemIdAtPosition, transitionValues);
                    }
                    if (view instanceof ViewGroup && (this.mTargetIdChildExcludes == null || !this.mTargetIdChildExcludes.contains(id)) && (this.mTargetChildExcludes == null || !this.mTargetChildExcludes.contains(view))) {
                        if (this.mTargetTypeChildExcludes != null && view != null) {
                            for (int size2 = this.mTargetTypeChildExcludes.size(), j = 0; j < size2; ++j) {
                                if (this.mTargetTypeChildExcludes.get(j).isInstance(view)) {
                                    return;
                                }
                            }
                        }
                        final ViewGroup viewGroup = (ViewGroup)view;
                        for (int k = 0; k < viewGroup.getChildCount(); ++k) {
                            this.captureHierarchy(viewGroup.getChildAt(k), b);
                        }
                    }
                }
            }
        }
    }
    
    private ArrayList<Integer> excludeId(final ArrayList<Integer> list, final int n, final boolean b) {
        ArrayList<Integer> add = list;
        if (n > 0) {
            if (!b) {
                return TransitionPort$ArrayListManager.remove(list, n);
            }
            add = TransitionPort$ArrayListManager.add(list, n);
        }
        return add;
    }
    
    private ArrayList<Class> excludeType(final ArrayList<Class> list, final Class clazz, final boolean b) {
        ArrayList<Class> add = list;
        if (clazz != null) {
            if (!b) {
                return (ArrayList<Class>)TransitionPort$ArrayListManager.remove(list, clazz);
            }
            add = TransitionPort$ArrayListManager.add(list, clazz);
        }
        return (ArrayList<Class>)add;
    }
    
    private ArrayList<View> excludeView(final ArrayList<View> list, final View view, final boolean b) {
        ArrayList<View> add = list;
        if (view != null) {
            if (!b) {
                return TransitionPort$ArrayListManager.remove(list, view);
            }
            add = TransitionPort$ArrayListManager.add(list, view);
        }
        return add;
    }
    
    private static ArrayMap<Animator, TransitionPort$AnimationInfo> getRunningAnimators() {
        ArrayMap<Animator, TransitionPort$AnimationInfo> arrayMap;
        if ((arrayMap = TransitionPort.sRunningAnimators.get()) == null) {
            arrayMap = new ArrayMap<Animator, TransitionPort$AnimationInfo>();
            TransitionPort.sRunningAnimators.set(arrayMap);
        }
        return arrayMap;
    }
    
    private void runAnimator(final Animator animator, final ArrayMap<Animator, TransitionPort$AnimationInfo> arrayMap) {
        if (animator != null) {
            animator.addListener((Animator$AnimatorListener)new TransitionPort$1(this, arrayMap));
            this.animate(animator);
        }
    }
    
    public TransitionPort addListener(final TransitionPort$TransitionListener transitionPort$TransitionListener) {
        if (this.mListeners == null) {
            this.mListeners = new ArrayList<TransitionPort$TransitionListener>();
        }
        this.mListeners.add(transitionPort$TransitionListener);
        return this;
    }
    
    public TransitionPort addTarget(final int n) {
        if (n > 0) {
            this.mTargetIds.add(n);
        }
        return this;
    }
    
    public TransitionPort addTarget(final View view) {
        this.mTargets.add(view);
        return this;
    }
    
    protected void animate(final Animator animator) {
        if (animator == null) {
            this.end();
            return;
        }
        if (this.getDuration() >= 0L) {
            animator.setDuration(this.getDuration());
        }
        if (this.getStartDelay() >= 0L) {
            animator.setStartDelay(this.getStartDelay());
        }
        if (this.getInterpolator() != null) {
            animator.setInterpolator(this.getInterpolator());
        }
        animator.addListener((Animator$AnimatorListener)new TransitionPort$2(this));
        animator.start();
    }
    
    public abstract void captureEndValues(final TransitionValues p0);
    
    public abstract void captureStartValues(final TransitionValues p0);
    
    void captureValues(final ViewGroup viewGroup, final boolean b) {
        final int n = 0;
        this.clearValues(b);
        if (this.mTargetIds.size() > 0 || this.mTargets.size() > 0) {
            if (this.mTargetIds.size() > 0) {
                for (int i = 0; i < this.mTargetIds.size(); ++i) {
                    final int intValue = this.mTargetIds.get(i);
                    final View viewById = viewGroup.findViewById(intValue);
                    if (viewById != null) {
                        final TransitionValues transitionValues = new TransitionValues();
                        transitionValues.view = viewById;
                        if (b) {
                            this.captureStartValues(transitionValues);
                        }
                        else {
                            this.captureEndValues(transitionValues);
                        }
                        if (b) {
                            this.mStartValues.viewValues.put(viewById, transitionValues);
                            if (intValue >= 0) {
                                this.mStartValues.idValues.put(intValue, (Object)transitionValues);
                            }
                        }
                        else {
                            this.mEndValues.viewValues.put(viewById, transitionValues);
                            if (intValue >= 0) {
                                this.mEndValues.idValues.put(intValue, (Object)transitionValues);
                            }
                        }
                    }
                }
            }
            if (this.mTargets.size() > 0) {
                for (int j = n; j < this.mTargets.size(); ++j) {
                    final View view = this.mTargets.get(j);
                    if (view != null) {
                        final TransitionValues transitionValues2 = new TransitionValues();
                        transitionValues2.view = view;
                        if (b) {
                            this.captureStartValues(transitionValues2);
                        }
                        else {
                            this.captureEndValues(transitionValues2);
                        }
                        if (b) {
                            this.mStartValues.viewValues.put(view, transitionValues2);
                        }
                        else {
                            this.mEndValues.viewValues.put(view, transitionValues2);
                        }
                    }
                }
            }
        }
        else {
            this.captureHierarchy((View)viewGroup, b);
        }
    }
    
    void clearValues(final boolean b) {
        if (b) {
            this.mStartValues.viewValues.clear();
            this.mStartValues.idValues.clear();
            this.mStartValues.itemIdValues.clear();
            return;
        }
        this.mEndValues.viewValues.clear();
        this.mEndValues.idValues.clear();
        this.mEndValues.itemIdValues.clear();
    }
    
    public TransitionPort clone() {
        TransitionPort transitionPort;
        try {
            final TransitionPort transitionPort2;
            transitionPort = (transitionPort2 = (TransitionPort)super.clone());
            final ArrayList<Animator> list = new ArrayList<Animator>();
            transitionPort2.mAnimators = list;
            final TransitionPort transitionPort3 = transitionPort;
            final TransitionValuesMaps transitionValuesMaps = new TransitionValuesMaps();
            transitionPort3.mStartValues = transitionValuesMaps;
            final TransitionPort transitionPort4 = transitionPort;
            final TransitionValuesMaps transitionValuesMaps2 = new TransitionValuesMaps();
            transitionPort4.mEndValues = transitionValuesMaps2;
            return transitionPort;
        }
        catch (CloneNotSupportedException transitionPort) {
            return null;
        }
        try {
            final TransitionPort transitionPort2 = transitionPort;
            final ArrayList<Animator> list = new ArrayList<Animator>();
            transitionPort2.mAnimators = list;
            final TransitionPort transitionPort3 = transitionPort;
            final TransitionValuesMaps transitionValuesMaps = new TransitionValuesMaps();
            transitionPort3.mStartValues = transitionValuesMaps;
            final TransitionPort transitionPort4 = transitionPort;
            final TransitionValuesMaps transitionValuesMaps2 = new TransitionValuesMaps();
            transitionPort4.mEndValues = transitionValuesMaps2;
            return transitionPort;
        }
        catch (CloneNotSupportedException ex) {
            return transitionPort;
        }
    }
    
    public Animator createAnimator(final ViewGroup viewGroup, final TransitionValues transitionValues, final TransitionValues transitionValues2) {
        return null;
    }
    
    protected void createAnimators(final ViewGroup viewGroup, final TransitionValuesMaps transitionValuesMaps, final TransitionValuesMaps transitionValuesMaps2) {
        final ArrayMap<View, TransitionValues> arrayMap = new ArrayMap<View, TransitionValues>(transitionValuesMaps2.viewValues);
        final SparseArray sparseArray = new SparseArray(transitionValuesMaps2.idValues.size());
        for (int i = 0; i < transitionValuesMaps2.idValues.size(); ++i) {
            sparseArray.put(transitionValuesMaps2.idValues.keyAt(i), transitionValuesMaps2.idValues.valueAt(i));
        }
        final LongSparseArray<TransitionValues> longSparseArray = new LongSparseArray<TransitionValues>(transitionValuesMaps2.itemIdValues.size());
        for (int j = 0; j < transitionValuesMaps2.itemIdValues.size(); ++j) {
            longSparseArray.put(transitionValuesMaps2.itemIdValues.keyAt(j), transitionValuesMaps2.itemIdValues.valueAt(j));
        }
        final ArrayList<TransitionValues> list = new ArrayList<TransitionValues>();
        final ArrayList<TransitionValues> list2 = new ArrayList<TransitionValues>();
        for (final View view : transitionValuesMaps.viewValues.keySet()) {
            boolean b = false;
            if (view.getParent() instanceof ListView) {
                b = true;
            }
            if (!b) {
                final int id = view.getId();
                TransitionValues transitionValues;
                if (transitionValuesMaps.viewValues.get(view) != null) {
                    transitionValues = transitionValuesMaps.viewValues.get(view);
                }
                else {
                    transitionValues = (TransitionValues)transitionValuesMaps.idValues.get(id);
                }
                TransitionValues transitionValues2;
                if (transitionValuesMaps2.viewValues.get(view) != null) {
                    transitionValues2 = transitionValuesMaps2.viewValues.get(view);
                    arrayMap.remove(view);
                }
                else if (id != -1) {
                    final TransitionValues transitionValues3 = (TransitionValues)transitionValuesMaps2.idValues.get(id);
                    Object o = null;
                    for (final View view2 : arrayMap.keySet()) {
                        if (view2.getId() == id) {
                            o = view2;
                        }
                    }
                    transitionValues2 = transitionValues3;
                    if (o != null) {
                        arrayMap.remove(o);
                        transitionValues2 = transitionValues3;
                    }
                }
                else {
                    transitionValues2 = null;
                }
                sparseArray.remove(id);
                if (!this.isValidTarget(view, id)) {
                    continue;
                }
                list.add(transitionValues);
                list2.add(transitionValues2);
            }
            else {
                final ListView listView = (ListView)view.getParent();
                if (!listView.getAdapter().hasStableIds()) {
                    continue;
                }
                final long itemIdAtPosition = listView.getItemIdAtPosition(listView.getPositionForView(view));
                final TransitionValues transitionValues4 = transitionValuesMaps.itemIdValues.get(itemIdAtPosition);
                longSparseArray.remove(itemIdAtPosition);
                list.add(transitionValues4);
                list2.add(null);
            }
        }
        for (int size = transitionValuesMaps.itemIdValues.size(), k = 0; k < size; ++k) {
            final long key = transitionValuesMaps.itemIdValues.keyAt(k);
            if (this.isValidTarget(null, key)) {
                final TransitionValues transitionValues5 = transitionValuesMaps.itemIdValues.get(key);
                final TransitionValues transitionValues6 = transitionValuesMaps2.itemIdValues.get(key);
                longSparseArray.remove(key);
                list.add(transitionValues5);
                list2.add(transitionValues6);
            }
        }
        for (final View view3 : arrayMap.keySet()) {
            final int id2 = view3.getId();
            if (this.isValidTarget(view3, id2)) {
                TransitionValues transitionValues7;
                if (transitionValuesMaps.viewValues.get(view3) != null) {
                    transitionValues7 = transitionValuesMaps.viewValues.get(view3);
                }
                else {
                    transitionValues7 = (TransitionValues)transitionValuesMaps.idValues.get(id2);
                }
                final TransitionValues transitionValues8 = arrayMap.get(view3);
                sparseArray.remove(id2);
                list.add(transitionValues7);
                list2.add(transitionValues8);
            }
        }
        for (int size2 = sparseArray.size(), l = 0; l < size2; ++l) {
            final int key2 = sparseArray.keyAt(l);
            if (this.isValidTarget(null, key2)) {
                final TransitionValues transitionValues9 = (TransitionValues)transitionValuesMaps.idValues.get(key2);
                final TransitionValues transitionValues10 = (TransitionValues)sparseArray.get(key2);
                list.add(transitionValues9);
                list2.add(transitionValues10);
            }
        }
        for (int size3 = longSparseArray.size(), n = 0; n < size3; ++n) {
            final long key3 = longSparseArray.keyAt(n);
            final TransitionValues transitionValues11 = transitionValuesMaps.itemIdValues.get(key3);
            final TransitionValues transitionValues12 = longSparseArray.get(key3);
            list.add(transitionValues11);
            list2.add(transitionValues12);
        }
        final ArrayMap<Animator, TransitionPort$AnimationInfo> runningAnimators = getRunningAnimators();
        for (int n2 = 0; n2 < list.size(); ++n2) {
            final TransitionValues transitionValues13 = list.get(n2);
            final TransitionValues transitionValues14 = list2.get(n2);
            if ((transitionValues13 != null || transitionValues14 != null) && (transitionValues13 == null || !transitionValues13.equals(transitionValues14))) {
                final Animator animator = this.createAnimator(viewGroup, transitionValues13, transitionValues14);
                if (animator != null) {
                    View view5;
                    Animator animator5;
                    TransitionValues transitionValues19;
                    if (transitionValues14 != null) {
                        final View view4 = transitionValues14.view;
                        final String[] transitionProperties = this.getTransitionProperties();
                        TransitionValues transitionValues17 = null;
                        Animator animator3 = null;
                        Label_1219: {
                            if (view4 != null && transitionProperties != null && transitionProperties.length > 0) {
                                final TransitionValues transitionValues15 = new TransitionValues();
                                transitionValues15.view = view4;
                                final TransitionValues transitionValues16 = transitionValuesMaps2.viewValues.get(view4);
                                if (transitionValues16 != null) {
                                    for (int n3 = 0; n3 < transitionProperties.length; ++n3) {
                                        transitionValues15.values.put(transitionProperties[n3], transitionValues16.values.get(transitionProperties[n3]));
                                    }
                                }
                                for (int size4 = runningAnimators.size(), n4 = 0; n4 < size4; ++n4) {
                                    final TransitionPort$AnimationInfo transitionPort$AnimationInfo = runningAnimators.get(runningAnimators.keyAt(n4));
                                    if (transitionPort$AnimationInfo.values != null && transitionPort$AnimationInfo.view == view4 && ((transitionPort$AnimationInfo.name == null && this.getName() == null) || transitionPort$AnimationInfo.name.equals(this.getName())) && transitionPort$AnimationInfo.values.equals(transitionValues15)) {
                                        final Animator animator2 = null;
                                        transitionValues17 = transitionValues15;
                                        animator3 = animator2;
                                        break Label_1219;
                                    }
                                }
                                final Animator animator4 = animator;
                                transitionValues17 = transitionValues15;
                                animator3 = animator4;
                            }
                            else {
                                final TransitionValues transitionValues18 = null;
                                animator3 = animator;
                                transitionValues17 = transitionValues18;
                            }
                        }
                        view5 = view4;
                        animator5 = animator3;
                        transitionValues19 = transitionValues17;
                    }
                    else {
                        view5 = transitionValues13.view;
                        transitionValues19 = null;
                        animator5 = animator;
                    }
                    if (animator5 != null) {
                        runningAnimators.put(animator5, new TransitionPort$AnimationInfo(view5, this.getName(), WindowIdPort.getWindowId((View)viewGroup), transitionValues19));
                        this.mAnimators.add(animator5);
                    }
                }
            }
        }
    }
    
    protected void end() {
        final int n = 0;
        --this.mNumInstances;
        if (this.mNumInstances == 0) {
            if (this.mListeners != null && this.mListeners.size() > 0) {
                final ArrayList list = (ArrayList)this.mListeners.clone();
                for (int size = list.size(), i = 0; i < size; ++i) {
                    list.get(i).onTransitionEnd(this);
                }
            }
            int n2 = 0;
            int j;
            while (true) {
                j = n;
                if (n2 >= this.mStartValues.itemIdValues.size()) {
                    break;
                }
                final View view = this.mStartValues.itemIdValues.valueAt(n2).view;
                ++n2;
            }
            while (j < this.mEndValues.itemIdValues.size()) {
                final View view2 = this.mEndValues.itemIdValues.valueAt(j).view;
                ++j;
            }
            this.mEnded = true;
        }
    }
    
    public TransitionPort excludeChildren(final int n, final boolean b) {
        this.mTargetIdChildExcludes = this.excludeId(this.mTargetIdChildExcludes, n, b);
        return this;
    }
    
    public TransitionPort excludeChildren(final View view, final boolean b) {
        this.mTargetChildExcludes = this.excludeView(this.mTargetChildExcludes, view, b);
        return this;
    }
    
    public TransitionPort excludeChildren(final Class clazz, final boolean b) {
        this.mTargetTypeChildExcludes = this.excludeType(this.mTargetTypeChildExcludes, clazz, b);
        return this;
    }
    
    public TransitionPort excludeTarget(final int n, final boolean b) {
        this.mTargetIdExcludes = this.excludeId(this.mTargetIdExcludes, n, b);
        return this;
    }
    
    public TransitionPort excludeTarget(final View view, final boolean b) {
        this.mTargetExcludes = this.excludeView(this.mTargetExcludes, view, b);
        return this;
    }
    
    public TransitionPort excludeTarget(final Class clazz, final boolean b) {
        this.mTargetTypeExcludes = this.excludeType(this.mTargetTypeExcludes, clazz, b);
        return this;
    }
    
    public long getDuration() {
        return this.mDuration;
    }
    
    public TimeInterpolator getInterpolator() {
        return this.mInterpolator;
    }
    
    public String getName() {
        return this.mName;
    }
    
    public long getStartDelay() {
        return this.mStartDelay;
    }
    
    public List<Integer> getTargetIds() {
        return this.mTargetIds;
    }
    
    public List<View> getTargets() {
        return this.mTargets;
    }
    
    public String[] getTransitionProperties() {
        return null;
    }
    
    public TransitionValues getTransitionValues(final View view, final boolean b) {
        TransitionValues transitionValues;
        if (this.mParent != null) {
            transitionValues = this.mParent.getTransitionValues(view, b);
        }
        else {
            TransitionValuesMaps transitionValuesMaps;
            if (b) {
                transitionValuesMaps = this.mStartValues;
            }
            else {
                transitionValuesMaps = this.mEndValues;
            }
            TransitionValues transitionValues2 = transitionValues = (TransitionValues)transitionValuesMaps.viewValues.get(view);
            if (transitionValues2 == null) {
                final int id = view.getId();
                if (id >= 0) {
                    transitionValues2 = (TransitionValues)transitionValuesMaps.idValues.get(id);
                }
                if ((transitionValues = transitionValues2) == null) {
                    transitionValues = transitionValues2;
                    if (view.getParent() instanceof ListView) {
                        final ListView listView = (ListView)view.getParent();
                        return transitionValuesMaps.itemIdValues.get(listView.getItemIdAtPosition(listView.getPositionForView(view)));
                    }
                }
            }
        }
        return transitionValues;
    }
    
    boolean isValidTarget(final View view, final long n) {
        if ((this.mTargetIdExcludes == null || !this.mTargetIdExcludes.contains((int)n)) && (this.mTargetExcludes == null || !this.mTargetExcludes.contains(view))) {
            if (this.mTargetTypeExcludes != null && view != null) {
                for (int size = this.mTargetTypeExcludes.size(), i = 0; i < size; ++i) {
                    if (this.mTargetTypeExcludes.get(i).isInstance(view)) {
                        return false;
                    }
                }
            }
            if (this.mTargetIds.size() == 0 && this.mTargets.size() == 0) {
                return true;
            }
            if (this.mTargetIds.size() > 0) {
                for (int j = 0; j < this.mTargetIds.size(); ++j) {
                    if (this.mTargetIds.get(j) == n) {
                        return true;
                    }
                }
            }
            if (view != null && this.mTargets.size() > 0) {
                for (int k = 0; k < this.mTargets.size(); ++k) {
                    if (this.mTargets.get(k) == view) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public void pause(final View view) {
        if (!this.mEnded) {
            final ArrayMap<Animator, TransitionPort$AnimationInfo> runningAnimators = getRunningAnimators();
            final int size = runningAnimators.size();
            final WindowIdPort windowId = WindowIdPort.getWindowId(view);
            for (int i = size - 1; i >= 0; --i) {
                final TransitionPort$AnimationInfo transitionPort$AnimationInfo = runningAnimators.valueAt(i);
                if (transitionPort$AnimationInfo.view != null && windowId.equals(transitionPort$AnimationInfo.windowId)) {
                    runningAnimators.keyAt(i).cancel();
                }
            }
            if (this.mListeners != null && this.mListeners.size() > 0) {
                final ArrayList list = (ArrayList)this.mListeners.clone();
                for (int size2 = list.size(), j = 0; j < size2; ++j) {
                    list.get(j).onTransitionPause(this);
                }
            }
            this.mPaused = true;
        }
    }
    
    void playTransition(final ViewGroup viewGroup) {
        final ArrayMap<Animator, TransitionPort$AnimationInfo> runningAnimators = getRunningAnimators();
    Label_0263:
        for (int i = runningAnimators.size() - 1; i >= 0; --i) {
            final Animator animator = runningAnimators.keyAt(i);
            if (animator != null) {
                final TransitionPort$AnimationInfo transitionPort$AnimationInfo = runningAnimators.get(animator);
                if (transitionPort$AnimationInfo != null && transitionPort$AnimationInfo.view != null && transitionPort$AnimationInfo.view.getContext() == viewGroup.getContext()) {
                    final TransitionValues values = transitionPort$AnimationInfo.values;
                    final View view = transitionPort$AnimationInfo.view;
                    TransitionValues transitionValues;
                    if (this.mEndValues.viewValues != null) {
                        transitionValues = this.mEndValues.viewValues.get(view);
                    }
                    else {
                        transitionValues = null;
                    }
                    if (transitionValues == null) {
                        transitionValues = (TransitionValues)this.mEndValues.idValues.get(view.getId());
                    }
                Label_0238:
                    while (true) {
                        if (values != null && transitionValues != null) {
                            for (final String s : values.values.keySet()) {
                                final Object value = values.values.get(s);
                                final Object value2 = transitionValues.values.get(s);
                                if (value != null && value2 != null && !value.equals(value2)) {
                                    final int n = 1;
                                    break Label_0238;
                                }
                            }
                        }
                        Label_0305: {
                            break Label_0305;
                            final int n;
                            if (n == 0) {
                                continue Label_0263;
                            }
                            if (animator.isRunning() || animator.isStarted()) {
                                animator.cancel();
                                continue Label_0263;
                            }
                            runningAnimators.remove(animator);
                            continue Label_0263;
                        }
                        final int n = 0;
                        continue Label_0238;
                    }
                }
            }
        }
        this.createAnimators(viewGroup, this.mStartValues, this.mEndValues);
        this.runAnimators();
    }
    
    public TransitionPort removeListener(final TransitionPort$TransitionListener transitionPort$TransitionListener) {
        if (this.mListeners != null) {
            this.mListeners.remove(transitionPort$TransitionListener);
            if (this.mListeners.size() == 0) {
                this.mListeners = null;
                return this;
            }
        }
        return this;
    }
    
    public TransitionPort removeTarget(final int n) {
        if (n > 0) {
            this.mTargetIds.remove((Object)n);
        }
        return this;
    }
    
    public TransitionPort removeTarget(final View view) {
        if (view != null) {
            this.mTargets.remove(view);
        }
        return this;
    }
    
    public void resume(final View view) {
        if (this.mPaused) {
            if (!this.mEnded) {
                final ArrayMap<Animator, TransitionPort$AnimationInfo> runningAnimators = getRunningAnimators();
                final int size = runningAnimators.size();
                final WindowIdPort windowId = WindowIdPort.getWindowId(view);
                for (int i = size - 1; i >= 0; --i) {
                    final TransitionPort$AnimationInfo transitionPort$AnimationInfo = runningAnimators.valueAt(i);
                    if (transitionPort$AnimationInfo.view != null && windowId.equals(transitionPort$AnimationInfo.windowId)) {
                        runningAnimators.keyAt(i).end();
                    }
                }
                if (this.mListeners != null && this.mListeners.size() > 0) {
                    final ArrayList list = (ArrayList)this.mListeners.clone();
                    for (int size2 = list.size(), j = 0; j < size2; ++j) {
                        list.get(j).onTransitionResume(this);
                    }
                }
            }
            this.mPaused = false;
        }
    }
    
    protected void runAnimators() {
        this.start();
        final ArrayMap<Animator, TransitionPort$AnimationInfo> runningAnimators = getRunningAnimators();
        for (final Animator animator : this.mAnimators) {
            if (runningAnimators.containsKey(animator)) {
                this.start();
                this.runAnimator(animator, runningAnimators);
            }
        }
        this.mAnimators.clear();
        this.end();
    }
    
    public TransitionPort setDuration(final long mDuration) {
        this.mDuration = mDuration;
        return this;
    }
    
    public TransitionPort setInterpolator(final TimeInterpolator mInterpolator) {
        this.mInterpolator = mInterpolator;
        return this;
    }
    
    public TransitionPort setStartDelay(final long mStartDelay) {
        this.mStartDelay = mStartDelay;
        return this;
    }
    
    protected void start() {
        if (this.mNumInstances == 0) {
            if (this.mListeners != null && this.mListeners.size() > 0) {
                final ArrayList list = (ArrayList)this.mListeners.clone();
                for (int size = list.size(), i = 0; i < size; ++i) {
                    list.get(i).onTransitionStart(this);
                }
            }
            this.mEnded = false;
        }
        ++this.mNumInstances;
    }
    
    @Override
    public String toString() {
        return this.toString("");
    }
    
    String toString(String s) {
        final int n = 0;
        final String s2 = s = s + this.getClass().getSimpleName() + "@" + Integer.toHexString(this.hashCode()) + ": ";
        if (this.mDuration != -1L) {
            s = s2 + "dur(" + this.mDuration + ") ";
        }
        String string = s;
        if (this.mStartDelay != -1L) {
            string = s + "dly(" + this.mStartDelay + ") ";
        }
        s = string;
        if (this.mInterpolator != null) {
            s = string + "interp(" + this.mInterpolator + ") ";
        }
        if (this.mTargetIds.size() <= 0) {
            final String string2 = s;
            if (this.mTargets.size() <= 0) {
                return string2;
            }
        }
        s += "tgts(";
        String string3;
        if (this.mTargetIds.size() > 0) {
            int n2 = 0;
            while (true) {
                string3 = s;
                if (n2 >= this.mTargetIds.size()) {
                    break;
                }
                String string4 = s;
                if (n2 > 0) {
                    string4 = s + ", ";
                }
                s = string4 + this.mTargetIds.get(n2);
                ++n2;
            }
        }
        else {
            string3 = s;
        }
        s = string3;
        if (this.mTargets.size() > 0) {
            int n3 = n;
            while (true) {
                s = string3;
                if (n3 >= this.mTargets.size()) {
                    break;
                }
                s = string3;
                if (n3 > 0) {
                    s = string3 + ", ";
                }
                string3 = s + this.mTargets.get(n3);
                ++n3;
            }
        }
        return s + ")";
    }
}
