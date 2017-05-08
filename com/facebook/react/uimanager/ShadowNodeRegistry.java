// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import com.facebook.react.common.SingleThreadAsserter;
import android.util.SparseArray;
import android.util.SparseBooleanArray;

class ShadowNodeRegistry
{
    private final SparseBooleanArray mRootTags;
    private final SparseArray<ReactShadowNode> mTagsToCSSNodes;
    private final SingleThreadAsserter mThreadAsserter;
    
    public ShadowNodeRegistry() {
        this.mTagsToCSSNodes = (SparseArray<ReactShadowNode>)new SparseArray();
        this.mRootTags = new SparseBooleanArray();
        this.mThreadAsserter = new SingleThreadAsserter();
    }
    
    public void addNode(final ReactShadowNode reactShadowNode) {
        this.mThreadAsserter.assertNow();
        this.mTagsToCSSNodes.put(reactShadowNode.getReactTag(), (Object)reactShadowNode);
    }
    
    public void addRootNode(final ReactShadowNode reactShadowNode) {
        final int reactTag = reactShadowNode.getReactTag();
        this.mTagsToCSSNodes.put(reactTag, (Object)reactShadowNode);
        this.mRootTags.put(reactTag, true);
    }
    
    public ReactShadowNode getNode(final int n) {
        this.mThreadAsserter.assertNow();
        return (ReactShadowNode)this.mTagsToCSSNodes.get(n);
    }
    
    public int getRootNodeCount() {
        this.mThreadAsserter.assertNow();
        return this.mRootTags.size();
    }
    
    public int getRootTag(final int n) {
        this.mThreadAsserter.assertNow();
        return this.mRootTags.keyAt(n);
    }
    
    public boolean isRootNode(final int n) {
        this.mThreadAsserter.assertNow();
        return this.mRootTags.get(n);
    }
    
    public void removeNode(final int n) {
        this.mThreadAsserter.assertNow();
        if (this.mRootTags.get(n)) {
            throw new IllegalViewOperationException("Trying to remove root node " + n + " without using removeRootNode!");
        }
        this.mTagsToCSSNodes.remove(n);
    }
    
    public void removeRootNode(final int n) {
        this.mThreadAsserter.assertNow();
        if (!this.mRootTags.get(n)) {
            throw new IllegalViewOperationException("View with tag " + n + " is not registered as a root view");
        }
        this.mTagsToCSSNodes.remove(n);
        this.mRootTags.delete(n);
    }
}
