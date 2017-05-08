// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.infer.annotation.Assertions;
import android.util.SparseBooleanArray;

public class NativeViewHierarchyOptimizer
{
    private final ShadowNodeRegistry mShadowNodeRegistry;
    private final SparseBooleanArray mTagsWithLayoutVisited;
    private final UIViewOperationQueue mUIViewOperationQueue;
    
    public NativeViewHierarchyOptimizer(final UIViewOperationQueue muiViewOperationQueue, final ShadowNodeRegistry mShadowNodeRegistry) {
        this.mTagsWithLayoutVisited = new SparseBooleanArray();
        this.mUIViewOperationQueue = muiViewOperationQueue;
        this.mShadowNodeRegistry = mShadowNodeRegistry;
    }
    
    private void addGrandchildren(final ReactShadowNode reactShadowNode, final ReactShadowNode reactShadowNode2, int n) {
        Assertions.assertCondition(!reactShadowNode.isLayoutOnly());
        for (int i = 0; i < reactShadowNode2.getChildCount(); ++i) {
            final ReactShadowNode child = reactShadowNode2.getChildAt(i);
            Assertions.assertCondition(child.getNativeParent() == null);
            if (child.isLayoutOnly()) {
                final int nativeChildCount = reactShadowNode.getNativeChildCount();
                this.addLayoutOnlyNode(reactShadowNode, child, n);
                n += reactShadowNode.getNativeChildCount() - nativeChildCount;
            }
            else {
                this.addNonLayoutNode(reactShadowNode, child, n);
                ++n;
            }
        }
    }
    
    private void addLayoutOnlyNode(final ReactShadowNode reactShadowNode, final ReactShadowNode reactShadowNode2, final int n) {
        this.addGrandchildren(reactShadowNode, reactShadowNode2, n);
    }
    
    private void addNodeToNode(final ReactShadowNode reactShadowNode, final ReactShadowNode reactShadowNode2, int n) {
        final int n2 = n = reactShadowNode.getNativeOffsetForChild(reactShadowNode.getChildAt(n));
        ReactShadowNode node = reactShadowNode;
        if (reactShadowNode.isLayoutOnly()) {
            final NativeViewHierarchyOptimizer$NodeIndexPair walkUpUntilNonLayoutOnly = this.walkUpUntilNonLayoutOnly(reactShadowNode, n2);
            if (walkUpUntilNonLayoutOnly == null) {
                return;
            }
            node = walkUpUntilNonLayoutOnly.node;
            n = walkUpUntilNonLayoutOnly.index;
        }
        if (!reactShadowNode2.isLayoutOnly()) {
            this.addNonLayoutNode(node, reactShadowNode2, n);
            return;
        }
        this.addLayoutOnlyNode(node, reactShadowNode2, n);
    }
    
    private void addNonLayoutNode(final ReactShadowNode reactShadowNode, final ReactShadowNode reactShadowNode2, final int n) {
        reactShadowNode.addNativeChildAt(reactShadowNode2, n);
        this.mUIViewOperationQueue.enqueueManageChildren(reactShadowNode.getReactTag(), null, new ViewAtIndex[] { new ViewAtIndex(reactShadowNode2.getReactTag(), n) }, null);
    }
    
    private void applyLayoutBase(final ReactShadowNode reactShadowNode) {
        final int reactTag = reactShadowNode.getReactTag();
        if (this.mTagsWithLayoutVisited.get(reactTag)) {
            return;
        }
        this.mTagsWithLayoutVisited.put(reactTag, true);
        ReactShadowNode reactShadowNode2 = reactShadowNode.getParent();
        int screenX = reactShadowNode.getScreenX();
        int screenY = reactShadowNode.getScreenY();
        while (reactShadowNode2 != null && reactShadowNode2.isLayoutOnly()) {
            screenX += Math.round(reactShadowNode2.getLayoutX());
            screenY += Math.round(reactShadowNode2.getLayoutY());
            reactShadowNode2 = reactShadowNode2.getParent();
        }
        this.applyLayoutRecursive(reactShadowNode, screenX, screenY);
    }
    
    private void applyLayoutRecursive(final ReactShadowNode reactShadowNode, final int n, final int n2) {
        if (!reactShadowNode.isLayoutOnly() && reactShadowNode.getNativeParent() != null) {
            this.mUIViewOperationQueue.enqueueUpdateLayout(reactShadowNode.getNativeParent().getReactTag(), reactShadowNode.getReactTag(), n, n2, reactShadowNode.getScreenWidth(), reactShadowNode.getScreenHeight());
        }
        else {
            for (int i = 0; i < reactShadowNode.getChildCount(); ++i) {
                final ReactShadowNode child = reactShadowNode.getChildAt(i);
                final int reactTag = child.getReactTag();
                if (!this.mTagsWithLayoutVisited.get(reactTag)) {
                    this.mTagsWithLayoutVisited.put(reactTag, true);
                    this.applyLayoutRecursive(child, child.getScreenX() + n, child.getScreenY() + n2);
                }
            }
        }
    }
    
    public static void handleRemoveNode(final ReactShadowNode reactShadowNode) {
        reactShadowNode.removeAllNativeChildren();
    }
    
    private static boolean isLayoutOnlyAndCollapsable(final ReactStylesDiffMap reactStylesDiffMap) {
        if (reactStylesDiffMap != null) {
            if (reactStylesDiffMap.hasKey("collapsable") && !reactStylesDiffMap.getBoolean("collapsable", true)) {
                return false;
            }
            final ReadableMapKeySetIterator keySetIterator = reactStylesDiffMap.mBackingMap.keySetIterator();
            while (keySetIterator.hasNextKey()) {
                if (!ViewProps.isLayoutOnly(keySetIterator.nextKey())) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private void removeNodeFromParent(final ReactShadowNode reactShadowNode, final boolean b) {
        final ReactShadowNode nativeParent = reactShadowNode.getNativeParent();
        if (nativeParent != null) {
            final int indexOfNativeChild = nativeParent.indexOfNativeChild(reactShadowNode);
            nativeParent.removeNativeChildAt(indexOfNativeChild);
            final UIViewOperationQueue muiViewOperationQueue = this.mUIViewOperationQueue;
            final int reactTag = nativeParent.getReactTag();
            int[] array;
            if (b) {
                array = new int[] { reactShadowNode.getReactTag() };
            }
            else {
                array = null;
            }
            muiViewOperationQueue.enqueueManageChildren(reactTag, new int[] { indexOfNativeChild }, null, array);
        }
        else {
            for (int i = reactShadowNode.getChildCount() - 1; i >= 0; --i) {
                this.removeNodeFromParent(reactShadowNode.getChildAt(i), b);
            }
        }
    }
    
    private void transitionLayoutOnlyViewToNativeView(final ReactShadowNode reactShadowNode, final ReactStylesDiffMap reactStylesDiffMap) {
        final int n = 0;
        final ReactShadowNode parent = reactShadowNode.getParent();
        if (parent == null) {
            reactShadowNode.setIsLayoutOnly(false);
            return;
        }
        final int index = parent.indexOf(reactShadowNode);
        parent.removeChildAt(index);
        this.removeNodeFromParent(reactShadowNode, false);
        reactShadowNode.setIsLayoutOnly(false);
        this.mUIViewOperationQueue.enqueueCreateView(reactShadowNode.getRootNode().getThemedContext(), reactShadowNode.getReactTag(), reactShadowNode.getViewClass(), reactStylesDiffMap);
        parent.addChildAt(reactShadowNode, index);
        this.addNodeToNode(parent, reactShadowNode, index);
        for (int i = 0; i < reactShadowNode.getChildCount(); ++i) {
            this.addNodeToNode(reactShadowNode, reactShadowNode.getChildAt(i), i);
        }
        Assertions.assertCondition(this.mTagsWithLayoutVisited.size() == 0);
        this.applyLayoutBase(reactShadowNode);
        for (int j = n; j < reactShadowNode.getChildCount(); ++j) {
            this.applyLayoutBase(reactShadowNode.getChildAt(j));
        }
        this.mTagsWithLayoutVisited.clear();
    }
    
    private NativeViewHierarchyOptimizer$NodeIndexPair walkUpUntilNonLayoutOnly(ReactShadowNode reactShadowNode, int n) {
        while (reactShadowNode.isLayoutOnly()) {
            final ReactShadowNode parent = reactShadowNode.getParent();
            if (parent == null) {
                return null;
            }
            n += parent.getNativeOffsetForChild(reactShadowNode);
            reactShadowNode = parent;
        }
        return new NativeViewHierarchyOptimizer$NodeIndexPair(reactShadowNode, n);
    }
    
    public void handleCreateView(final ReactShadowNode reactShadowNode, final ThemedReactContext themedReactContext, final ReactStylesDiffMap reactStylesDiffMap) {
        final boolean isLayoutOnly = reactShadowNode.getViewClass().equals("RCTView") && isLayoutOnlyAndCollapsable(reactStylesDiffMap);
        reactShadowNode.setIsLayoutOnly(isLayoutOnly);
        if (!isLayoutOnly) {
            this.mUIViewOperationQueue.enqueueCreateView(themedReactContext, reactShadowNode.getReactTag(), reactShadowNode.getViewClass(), reactStylesDiffMap);
        }
    }
    
    public void handleManageChildren(final ReactShadowNode reactShadowNode, final int[] array, final int[] array2, final ViewAtIndex[] array3, final int[] array4) {
        final int n = 0;
        int n2 = 0;
        int i = 0;
    Label_0006:
        while (true) {
            i = n;
            if (n2 < array2.length) {
                final int n3 = array2[n2];
                while (true) {
                    for (int j = 0; j < array4.length; ++j) {
                        if (array4[j] == n3) {
                            final boolean b = true;
                            this.removeNodeFromParent(this.mShadowNodeRegistry.getNode(n3), b);
                            ++n2;
                            continue Label_0006;
                        }
                    }
                    final boolean b = false;
                    continue;
                }
            }
            break;
        }
        while (i < array3.length) {
            final ViewAtIndex viewAtIndex = array3[i];
            this.addNodeToNode(reactShadowNode, this.mShadowNodeRegistry.getNode(viewAtIndex.mTag), viewAtIndex.mIndex);
            ++i;
        }
    }
    
    public void handleSetChildren(final ReactShadowNode reactShadowNode, final ReadableArray readableArray) {
        for (int i = 0; i < readableArray.size(); ++i) {
            this.addNodeToNode(reactShadowNode, this.mShadowNodeRegistry.getNode(readableArray.getInt(i)), i);
        }
    }
    
    public void handleUpdateLayout(final ReactShadowNode reactShadowNode) {
        this.applyLayoutBase(reactShadowNode);
    }
    
    public void handleUpdateView(final ReactShadowNode reactShadowNode, final String s, final ReactStylesDiffMap reactStylesDiffMap) {
        int n;
        if (reactShadowNode.isLayoutOnly() && !isLayoutOnlyAndCollapsable(reactStylesDiffMap)) {
            n = 1;
        }
        else {
            n = 0;
        }
        if (n != 0) {
            this.transitionLayoutOnlyViewToNativeView(reactShadowNode, reactStylesDiffMap);
        }
        else if (!reactShadowNode.isLayoutOnly()) {
            this.mUIViewOperationQueue.enqueueUpdateProperties(reactShadowNode.getReactTag(), s, reactStylesDiffMap);
        }
    }
    
    public void onBatchComplete() {
        this.mTagsWithLayoutVisited.clear();
    }
}
