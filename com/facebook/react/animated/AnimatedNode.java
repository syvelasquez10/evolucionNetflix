// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.animated;

import com.facebook.infer.annotation.Assertions;
import java.util.ArrayList;
import java.util.List;

abstract class AnimatedNode
{
    int mActiveIncomingNodes;
    int mBFSColor;
    List<AnimatedNode> mChildren;
    int mTag;
    
    AnimatedNode() {
        this.mActiveIncomingNodes = 0;
        this.mBFSColor = 0;
        this.mTag = -1;
    }
    
    public final void addChild(final AnimatedNode animatedNode) {
        if (this.mChildren == null) {
            this.mChildren = new ArrayList<AnimatedNode>(1);
        }
        Assertions.assertNotNull(this.mChildren).add(animatedNode);
        animatedNode.onAttachedToNode(this);
    }
    
    public void onAttachedToNode(final AnimatedNode animatedNode) {
    }
    
    public void onDetachedFromNode(final AnimatedNode animatedNode) {
    }
    
    public final void removeChild(final AnimatedNode animatedNode) {
        if (this.mChildren == null) {
            return;
        }
        animatedNode.onDetachedFromNode(this);
        this.mChildren.remove(animatedNode);
    }
    
    public void update() {
    }
}
