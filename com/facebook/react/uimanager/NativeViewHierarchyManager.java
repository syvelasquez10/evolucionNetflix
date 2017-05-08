// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import android.util.Log;
import android.view.View$MeasureSpec;
import com.facebook.react.animation.AnimationListener;
import com.facebook.react.animation.Animation;
import android.view.Menu;
import android.widget.PopupMenu$OnDismissListener;
import android.widget.PopupMenu$OnMenuItemClickListener;
import android.content.Context;
import android.widget.PopupMenu;
import com.facebook.react.bridge.Callback;
import android.view.ViewParent;
import com.facebook.react.bridge.SoftAssertions;
import android.content.res.Resources;
import com.facebook.react.uimanager.layoutanimation.LayoutAnimationListener;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.systrace.Systrace;
import com.facebook.systrace.SystraceMessage;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import android.view.ViewGroup;
import android.view.View;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import com.facebook.react.uimanager.layoutanimation.LayoutAnimationController;
import com.facebook.react.touch.JSResponderHandler;
import com.facebook.react.animation.AnimationRegistry;

public class NativeViewHierarchyManager
{
    private static final String TAG;
    private final AnimationRegistry mAnimationRegistry;
    private final JSResponderHandler mJSResponderHandler;
    private boolean mLayoutAnimationEnabled;
    private final LayoutAnimationController mLayoutAnimator;
    private final SparseBooleanArray mRootTags;
    private final RootViewManager mRootViewManager;
    private final SparseArray<ViewManager> mTagsToViewManagers;
    private final SparseArray<View> mTagsToViews;
    private final ViewManagerRegistry mViewManagers;
    
    static {
        TAG = NativeViewHierarchyManager.class.getSimpleName();
    }
    
    public NativeViewHierarchyManager(final ViewManagerRegistry viewManagerRegistry) {
        this(viewManagerRegistry, new RootViewManager());
    }
    
    public NativeViewHierarchyManager(final ViewManagerRegistry mViewManagers, final RootViewManager mRootViewManager) {
        this.mJSResponderHandler = new JSResponderHandler();
        this.mLayoutAnimator = new LayoutAnimationController();
        this.mAnimationRegistry = new AnimationRegistry();
        this.mViewManagers = mViewManagers;
        this.mTagsToViews = (SparseArray<View>)new SparseArray();
        this.mTagsToViewManagers = (SparseArray<ViewManager>)new SparseArray();
        this.mRootTags = new SparseBooleanArray();
        this.mRootViewManager = mRootViewManager;
    }
    
    private boolean arrayContains(final int[] array, final int n) {
        if (array != null) {
            for (int length = array.length, i = 0; i < length; ++i) {
                if (array[i] == n) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private static String constructManageChildrenErrorMessage(final ViewGroup viewGroup, final ViewGroupManager viewGroupManager, final int[] array, final ViewAtIndex[] array2, final int[] array3) {
        final StringBuilder sb = new StringBuilder();
        if (viewGroup != null) {
            sb.append("View tag:" + viewGroup.getId() + "\n");
            sb.append("  children(" + viewGroupManager.getChildCount(viewGroup) + "): [\n");
            for (int i = 0; i < viewGroupManager.getChildCount(viewGroup); i += 16) {
                for (int n = 0; i + n < viewGroupManager.getChildCount(viewGroup) && n < 16; ++n) {
                    sb.append(viewGroupManager.getChildAt(viewGroup, i + n).getId() + ",");
                }
                sb.append("\n");
            }
            sb.append(" ],\n");
        }
        if (array != null) {
            sb.append("  indicesToRemove(" + array.length + "): [\n");
            for (int j = 0; j < array.length; j += 16) {
                for (int n2 = 0; j + n2 < array.length && n2 < 16; ++n2) {
                    sb.append(array[j + n2] + ",");
                }
                sb.append("\n");
            }
            sb.append(" ],\n");
        }
        if (array2 != null) {
            sb.append("  viewsToAdd(" + array2.length + "): [\n");
            for (int k = 0; k < array2.length; k += 16) {
                for (int n3 = 0; k + n3 < array2.length && n3 < 16; ++n3) {
                    sb.append("[" + array2[k + n3].mIndex + "," + array2[k + n3].mTag + "],");
                }
                sb.append("\n");
            }
            sb.append(" ],\n");
        }
        if (array3 != null) {
            sb.append("  tagsToDelete(" + array3.length + "): [\n");
            for (int l = 0; l < array3.length; l += 16) {
                for (int n4 = 0; l + n4 < array3.length && n4 < 16; ++n4) {
                    sb.append(array3[l + n4] + ",");
                }
                sb.append("\n");
            }
            sb.append(" ]\n");
        }
        return sb.toString();
    }
    
    private ThemedReactContext getReactContextForView(final int n) {
        final View view = (View)this.mTagsToViews.get(n);
        if (view == null) {
            throw new JSApplicationIllegalArgumentException("Could not find view with tag " + n);
        }
        return (ThemedReactContext)view.getContext();
    }
    
    private void updateLayout(final View view, final int n, final int n2, final int n3, final int n4) {
        if (this.mLayoutAnimationEnabled && this.mLayoutAnimator.shouldAnimateLayout(view)) {
            this.mLayoutAnimator.applyLayoutUpdate(view, n, n2, n3, n4);
            return;
        }
        view.layout(n, n2, n + n3, n2 + n4);
    }
    
    public void addRootView(final int n, final SizeMonitoringFrameLayout sizeMonitoringFrameLayout, final ThemedReactContext themedReactContext) {
        this.addRootViewGroup(n, (ViewGroup)sizeMonitoringFrameLayout, themedReactContext);
    }
    
    protected final void addRootViewGroup(final int id, final ViewGroup viewGroup, final ThemedReactContext themedReactContext) {
        UiThreadUtil.assertOnUiThread();
        if (viewGroup.getId() != -1) {
            throw new IllegalViewOperationException("Trying to add a root view with an explicit id already set. React Native uses the id field to track react tags and will overwrite this field. If that is fine, explicitly overwrite the id field to View.NO_ID before calling addMeasuredRootView.");
        }
        this.mTagsToViews.put(id, (Object)viewGroup);
        this.mTagsToViewManagers.put(id, (Object)this.mRootViewManager);
        this.mRootTags.put(id, true);
        viewGroup.setId(id);
    }
    
    public void clearJSResponder() {
        this.mJSResponderHandler.clearJSResponder();
    }
    
    void clearLayoutAnimation() {
        this.mLayoutAnimator.reset();
    }
    
    void configureLayoutAnimation(final ReadableMap readableMap) {
        this.mLayoutAnimator.initializeFromConfig(readableMap);
    }
    
    public void createView(final ThemedReactContext themedReactContext, final int id, final String s, final ReactStylesDiffMap reactStylesDiffMap) {
        UiThreadUtil.assertOnUiThread();
        SystraceMessage.beginSection(0L, "NativeViewHierarchyManager_createView").arg("tag", id).arg("className", s).flush();
        try {
            final ViewManager value = this.mViewManagers.get(s);
            final View view = value.createView(themedReactContext, this.mJSResponderHandler);
            this.mTagsToViews.put(id, (Object)view);
            this.mTagsToViewManagers.put(id, (Object)value);
            view.setId(id);
            if (reactStylesDiffMap != null) {
                value.updateProperties(view, reactStylesDiffMap);
            }
        }
        finally {
            Systrace.endSection(0L);
        }
    }
    
    public void dispatchCommand(final int n, final int n2, final ReadableArray readableArray) {
        UiThreadUtil.assertOnUiThread();
        final View view = (View)this.mTagsToViews.get(n);
        if (view == null) {
            throw new IllegalViewOperationException("Trying to send command to a non-existing view with tag " + n);
        }
        this.resolveViewManager(n).receiveCommand(view, n2, readableArray);
    }
    
    protected void dropView(final View view) {
        UiThreadUtil.assertOnUiThread();
        if (!this.mRootTags.get(view.getId())) {
            this.resolveViewManager(view.getId()).onDropViewInstance(view);
        }
        final ViewManager viewManager = (ViewManager)this.mTagsToViewManagers.get(view.getId());
        if (view instanceof ViewGroup && viewManager instanceof ViewGroupManager) {
            final ViewGroup viewGroup = (ViewGroup)view;
            final ViewGroupManager<ViewGroup> viewGroupManager = (ViewGroupManager<ViewGroup>)viewManager;
            for (int i = viewGroupManager.getChildCount(viewGroup) - 1; i >= 0; --i) {
                final View child = viewGroupManager.getChildAt(viewGroup, i);
                if (this.mTagsToViews.get(child.getId()) != null) {
                    this.dropView(child);
                }
            }
            viewGroupManager.removeAllViews(viewGroup);
        }
        this.mTagsToViews.remove(view.getId());
        this.mTagsToViewManagers.remove(view.getId());
    }
    
    public int findTargetTagForTouch(final int n, final float n2, final float n3) {
        final View view = (View)this.mTagsToViews.get(n);
        if (view == null) {
            throw new JSApplicationIllegalArgumentException("Could not find view with tag " + n);
        }
        return TouchTargetHelper.findTargetTagForTouch(n2, n3, (ViewGroup)view);
    }
    
    public AnimationRegistry getAnimationRegistry() {
        return this.mAnimationRegistry;
    }
    
    public void manageChildren(int i, final int[] array, final ViewAtIndex[] array2, final int[] array3) {
        final int n = 0;
        final ViewGroup viewGroup = (ViewGroup)this.mTagsToViews.get(i);
        final ViewGroupManager viewGroupManager = (ViewGroupManager)this.resolveViewManager(i);
        if (viewGroup == null) {
            throw new IllegalViewOperationException("Trying to manageChildren view with tag " + i + " which doesn't exist\n detail: " + constructManageChildrenErrorMessage(viewGroup, viewGroupManager, array, array2, array3));
        }
        int childCount = viewGroupManager.getChildCount(viewGroup);
        if (array != null) {
            int n2;
            for (int j = array.length - 1; j >= 0; --j, childCount = n2) {
                n2 = array[j];
                if (n2 < 0) {
                    throw new IllegalViewOperationException("Trying to remove a negative view index:" + n2 + " view tag: " + i + "\n detail: " + constructManageChildrenErrorMessage(viewGroup, viewGroupManager, array, array2, array3));
                }
                if (n2 >= viewGroupManager.getChildCount(viewGroup)) {
                    throw new IllegalViewOperationException("Trying to remove a view index above child count " + n2 + " view tag: " + i + "\n detail: " + constructManageChildrenErrorMessage(viewGroup, viewGroupManager, array, array2, array3));
                }
                if (n2 >= childCount) {
                    throw new IllegalViewOperationException("Trying to remove an out of order view index:" + n2 + " view tag: " + i + "\n detail: " + constructManageChildrenErrorMessage(viewGroup, viewGroupManager, array, array2, array3));
                }
                final View child = viewGroupManager.getChildAt(viewGroup, n2);
                if (!this.mLayoutAnimationEnabled || !this.mLayoutAnimator.shouldAnimateLayout(child) || !this.arrayContains(array3, child.getId())) {
                    viewGroupManager.removeViewAt(viewGroup, n2);
                }
            }
        }
        if (array2 != null) {
            ViewAtIndex viewAtIndex;
            View view;
            for (i = 0; i < array2.length; ++i) {
                viewAtIndex = array2[i];
                view = (View)this.mTagsToViews.get(viewAtIndex.mTag);
                if (view == null) {
                    throw new IllegalViewOperationException("Trying to add unknown view tag: " + viewAtIndex.mTag + "\n detail: " + constructManageChildrenErrorMessage(viewGroup, viewGroupManager, array, array2, array3));
                }
                viewGroupManager.addView(viewGroup, view, viewAtIndex.mIndex);
            }
        }
        if (array3 != null) {
            int n3;
            View view2;
            for (i = n; i < array3.length; ++i) {
                n3 = array3[i];
                view2 = (View)this.mTagsToViews.get(n3);
                if (view2 == null) {
                    throw new IllegalViewOperationException("Trying to destroy unknown view tag: " + n3 + "\n detail: " + constructManageChildrenErrorMessage(viewGroup, viewGroupManager, array, array2, array3));
                }
                if (this.mLayoutAnimationEnabled && this.mLayoutAnimator.shouldAnimateLayout(view2)) {
                    this.mLayoutAnimator.deleteView(view2, new NativeViewHierarchyManager$1(this, viewGroupManager, viewGroup, view2));
                }
                else {
                    this.dropView(view2);
                }
            }
        }
    }
    
    public void measure(int n, final int[] array) {
        UiThreadUtil.assertOnUiThread();
        final View view = (View)this.mTagsToViews.get(n);
        if (view == null) {
            throw new NoSuchNativeViewException("No native view for " + n + " currently exists");
        }
        final View view2 = (View)RootViewUtil.getRootView(view);
        if (view2 == null) {
            throw new NoSuchNativeViewException("Native view " + n + " is no longer on screen");
        }
        view2.getLocationInWindow(array);
        n = array[0];
        final int n2 = array[1];
        view.getLocationInWindow(array);
        array[0] -= n;
        array[1] -= n2;
        array[2] = view.getWidth();
        array[3] = view.getHeight();
    }
    
    public void measureInWindow(int identifier, final int[] array) {
        UiThreadUtil.assertOnUiThread();
        final View view = (View)this.mTagsToViews.get(identifier);
        if (view == null) {
            throw new NoSuchNativeViewException("No native view for " + identifier + " currently exists");
        }
        view.getLocationOnScreen(array);
        final Resources resources = view.getContext().getResources();
        identifier = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            identifier = (int)resources.getDimension(identifier);
            array[1] -= identifier;
        }
        array[2] = view.getWidth();
        array[3] = view.getHeight();
    }
    
    public void removeRootView(final int n) {
        UiThreadUtil.assertOnUiThread();
        if (!this.mRootTags.get(n)) {
            SoftAssertions.assertUnreachable("View with tag " + n + " is not registered as a root view");
        }
        this.dropView((View)this.mTagsToViews.get(n));
        this.mRootTags.delete(n);
    }
    
    public final View resolveView(final int n) {
        final View view = (View)this.mTagsToViews.get(n);
        if (view == null) {
            throw new IllegalViewOperationException("Trying to resolve view with tag " + n + " which doesn't exist");
        }
        return view;
    }
    
    public final ViewManager resolveViewManager(final int n) {
        final ViewManager viewManager = (ViewManager)this.mTagsToViewManagers.get(n);
        if (viewManager == null) {
            throw new IllegalViewOperationException("ViewManager for tag " + n + " could not be found");
        }
        return viewManager;
    }
    
    public void sendAccessibilityEvent(final int n, final int n2) {
        final View view = (View)this.mTagsToViews.get(n);
        if (view == null) {
            throw new JSApplicationIllegalArgumentException("Could not find view with tag " + n);
        }
        AccessibilityHelper.sendAccessibilityEvent(view, n2);
    }
    
    public void setJSResponder(final int n, final int n2, final boolean b) {
        if (!b) {
            this.mJSResponderHandler.setJSResponder(n2, null);
            return;
        }
        final View view = (View)this.mTagsToViews.get(n);
        if (n2 != n && view instanceof ViewParent) {
            this.mJSResponderHandler.setJSResponder(n2, (ViewParent)view);
            return;
        }
        if (this.mRootTags.get(n)) {
            SoftAssertions.assertUnreachable("Cannot block native responder on " + n + " that is a root view");
        }
        this.mJSResponderHandler.setJSResponder(n2, view.getParent());
    }
    
    public void setLayoutAnimationEnabled(final boolean mLayoutAnimationEnabled) {
        this.mLayoutAnimationEnabled = mLayoutAnimationEnabled;
    }
    
    public void showPopupMenu(int i, final ReadableArray readableArray, final Callback callback) {
        UiThreadUtil.assertOnUiThread();
        final View view = (View)this.mTagsToViews.get(i);
        if (view == null) {
            throw new JSApplicationIllegalArgumentException("Could not find view with tag " + i);
        }
        final PopupMenu popupMenu = new PopupMenu((Context)this.getReactContextForView(i), view);
        final Menu menu = popupMenu.getMenu();
        for (i = 0; i < readableArray.size(); ++i) {
            menu.add(0, 0, i, (CharSequence)readableArray.getString(i));
        }
        final NativeViewHierarchyManager$PopupMenuCallbackHandler nativeViewHierarchyManager$PopupMenuCallbackHandler = new NativeViewHierarchyManager$PopupMenuCallbackHandler(callback, null);
        popupMenu.setOnMenuItemClickListener((PopupMenu$OnMenuItemClickListener)nativeViewHierarchyManager$PopupMenuCallbackHandler);
        popupMenu.setOnDismissListener((PopupMenu$OnDismissListener)nativeViewHierarchyManager$PopupMenuCallbackHandler);
        popupMenu.show();
    }
    
    void startAnimationForNativeView(final int n, final Animation animation, final Callback callback) {
        UiThreadUtil.assertOnUiThread();
        final View view = (View)this.mTagsToViews.get(n);
        final int animationID = animation.getAnimationID();
        if (view != null) {
            animation.setAnimationListener(new NativeViewHierarchyManager$2(this, animationID, callback));
            animation.start(view);
            return;
        }
        throw new IllegalViewOperationException("View with tag " + n + " not found");
    }
    
    public void updateLayout(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        while (true) {
            UiThreadUtil.assertOnUiThread();
            SystraceMessage.beginSection(0L, "NativeViewHierarchyManager_updateLayout").arg("parentTag", n).arg("tag", n2).flush();
            try {
                final View resolveView = this.resolveView(n2);
                resolveView.measure(View$MeasureSpec.makeMeasureSpec(n5, 1073741824), View$MeasureSpec.makeMeasureSpec(n6, 1073741824));
                if (!this.mRootTags.get(n)) {
                    final ViewManager viewManager = (ViewManager)this.mTagsToViewManagers.get(n);
                    if (viewManager instanceof ViewGroupManager) {
                        final ViewGroupManager viewGroupManager = (ViewGroupManager)viewManager;
                        if (viewGroupManager != null && !viewGroupManager.needsCustomLayoutForChildren()) {
                            this.updateLayout(resolveView, n3, n4, n5, n6);
                        }
                        return;
                    }
                    throw new IllegalViewOperationException("Trying to use view with tag " + n2 + " as a parent, but its Manager doesn't extends ViewGroupManager");
                }
            }
            finally {
                Systrace.endSection(0L);
            }
            final View view;
            this.updateLayout(view, n3, n4, n5, n6);
        }
    }
    
    public void updateProperties(final int n, final ReactStylesDiffMap reactStylesDiffMap) {
        UiThreadUtil.assertOnUiThread();
        try {
            this.resolveViewManager(n).updateProperties(this.resolveView(n), reactStylesDiffMap);
        }
        catch (IllegalViewOperationException ex) {
            Log.e(NativeViewHierarchyManager.TAG, "Unable to update properties for view tag " + n, (Throwable)ex);
        }
    }
    
    public void updateViewExtraData(final int n, final Object o) {
        UiThreadUtil.assertOnUiThread();
        this.resolveViewManager(n).updateExtraData(this.resolveView(n), o);
    }
}
