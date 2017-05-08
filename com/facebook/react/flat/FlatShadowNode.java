// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.flat;

import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.ReactShadowNode;
import android.graphics.Rect;
import com.facebook.react.uimanager.LayoutShadowNode;

class FlatShadowNode extends LayoutShadowNode
{
    static final FlatShadowNode[] EMPTY_ARRAY;
    private static final DrawView EMPTY_DRAW_VIEW;
    private static final Rect LOGICAL_OFFSET_EMPTY;
    private AttachDetachListener[] mAttachDetachListeners;
    float mClipRadius;
    boolean mClipToBounds;
    private DrawBackgroundColor mDrawBackground;
    private DrawCommand[] mDrawCommands;
    private DrawView mDrawView;
    private boolean mForceMountChildrenToView;
    private boolean mIsUpdated;
    private Rect mLogicalOffset;
    private FlatShadowNode[] mNativeChildren;
    private NodeRegion mNodeRegion;
    private NodeRegion[] mNodeRegions;
    private boolean mOverflowsContainer;
    private int mViewBottom;
    private int mViewLeft;
    private int mViewRight;
    private int mViewTop;
    
    static {
        EMPTY_ARRAY = new FlatShadowNode[0];
        LOGICAL_OFFSET_EMPTY = new Rect();
        EMPTY_DRAW_VIEW = new DrawView(0);
    }
    
    FlatShadowNode() {
        this.mDrawCommands = DrawCommand.EMPTY_ARRAY;
        this.mAttachDetachListeners = AttachDetachListener.EMPTY_ARRAY;
        this.mNodeRegions = NodeRegion.EMPTY_ARRAY;
        this.mNativeChildren = FlatShadowNode.EMPTY_ARRAY;
        this.mNodeRegion = NodeRegion.EMPTY;
        this.mIsUpdated = true;
        this.mLogicalOffset = FlatShadowNode.LOGICAL_OFFSET_EMPTY;
        this.mClipToBounds = false;
    }
    
    @Override
    public void addChildAt(final ReactShadowNode reactShadowNode, final int n) {
        super.addChildAt(reactShadowNode, n);
        if (this.mForceMountChildrenToView && reactShadowNode instanceof FlatShadowNode) {
            ((FlatShadowNode)reactShadowNode).forceMountToView();
        }
    }
    
    final void forceMountChildrenToView() {
        if (!this.mForceMountChildrenToView) {
            this.mForceMountChildrenToView = true;
            for (int childCount = this.getChildCount(), i = 0; i != childCount; ++i) {
                final ReactShadowNode child = this.getChildAt(i);
                if (child instanceof FlatShadowNode) {
                    ((FlatShadowNode)child).forceMountToView();
                }
            }
        }
    }
    
    final void forceMountToView() {
        if (!this.isVirtual() && this.mDrawView == null) {
            this.mDrawView = FlatShadowNode.EMPTY_DRAW_VIEW;
            this.invalidate();
            this.mNodeRegion = NodeRegion.EMPTY;
        }
    }
    
    @Override
    public final int getScreenHeight() {
        if (this.mountsToView()) {
            return this.mViewBottom - this.mViewTop;
        }
        return Math.round(this.mNodeRegion.getBottom() - this.mNodeRegion.getTop());
    }
    
    @Override
    public final int getScreenWidth() {
        if (this.mountsToView()) {
            return this.mViewRight - this.mViewLeft;
        }
        return Math.round(this.mNodeRegion.getRight() - this.mNodeRegion.getLeft());
    }
    
    @Override
    public final int getScreenX() {
        return this.mViewLeft;
    }
    
    @Override
    public final int getScreenY() {
        return this.mViewTop;
    }
    
    protected final void invalidate() {
        FlatShadowNode flatShadowNode = this;
        while (true) {
            if (flatShadowNode.mountsToView()) {
                if (flatShadowNode.mIsUpdated) {
                    break;
                }
                flatShadowNode.mIsUpdated = true;
            }
            final ReactShadowNode parent = flatShadowNode.getParent();
            if (parent == null) {
                break;
            }
            flatShadowNode = (FlatShadowNode)parent;
        }
    }
    
    @Override
    public void markUpdated() {
        super.markUpdated();
        this.mIsUpdated = true;
        this.invalidate();
    }
    
    final boolean mountsToView() {
        return this.mDrawView != null;
    }
    
    @ReactProp(name = "backgroundColor")
    public void setBackgroundColor(final int n) {
        DrawBackgroundColor mDrawBackground;
        if (n == 0) {
            mDrawBackground = null;
        }
        else {
            mDrawBackground = new DrawBackgroundColor(n);
        }
        this.mDrawBackground = mDrawBackground;
        this.invalidate();
    }
    
    @Override
    public void setOverflow(final String overflow) {
        super.setOverflow(overflow);
        this.mClipToBounds = "hidden".equals(overflow);
        if (this.mClipToBounds) {
            this.mOverflowsContainer = false;
            if (this.mClipRadius > 0.5f) {
                this.forceMountToView();
            }
        }
        else {
            this.updateOverflowsContainer();
        }
        this.invalidate();
    }
    
    final void updateOverflowsContainer() {
        final boolean b = false;
        boolean b2 = false;
        final int n = (int)(this.mNodeRegion.getRight() - this.mNodeRegion.getLeft());
        final int n2 = (int)(this.mNodeRegion.getBottom() - this.mNodeRegion.getTop());
        float right = n;
        float top = 0.0f;
        float bottom = n2;
        boolean b3 = b;
        while (true) {
            Label_0409: {
                if (this.mClipToBounds) {
                    break Label_0409;
                }
                b3 = b;
                if (n2 <= 0) {
                    break Label_0409;
                }
                b3 = b;
                if (n <= 0) {
                    break Label_0409;
                }
                final NodeRegion[] mNodeRegions = this.mNodeRegions;
                final int length = mNodeRegions.length;
                float n3 = 0.0f;
                float left;
                for (int i = 0; i < length; ++i, n3 = left) {
                    final NodeRegion nodeRegion = mNodeRegions[i];
                    left = n3;
                    if (nodeRegion.getLeft() < n3) {
                        left = nodeRegion.getLeft();
                        b2 = true;
                    }
                    if (nodeRegion.getRight() > right) {
                        right = nodeRegion.getRight();
                        b2 = true;
                    }
                    if (nodeRegion.getTop() < top) {
                        top = nodeRegion.getTop();
                        b2 = true;
                    }
                    if (nodeRegion.getBottom() > bottom) {
                        bottom = nodeRegion.getBottom();
                        b2 = true;
                    }
                }
                b3 = b2;
                if (!b2) {
                    break Label_0409;
                }
                Rect rect = new Rect((int)n3, (int)top, (int)(right - n), (int)(bottom - n2));
                Rect rect2 = rect;
                boolean mOverflowsContainer = b2;
                if (!b2) {
                    rect2 = rect;
                    mOverflowsContainer = b2;
                    if (this.mNodeRegion != NodeRegion.EMPTY) {
                        final int childCount = this.getChildCount();
                        int n4 = 0;
                        while (true) {
                            rect2 = rect;
                            mOverflowsContainer = b2;
                            if (n4 >= childCount) {
                                break;
                            }
                            final ReactShadowNode child = this.getChildAt(n4);
                            if (child instanceof FlatShadowNode && ((FlatShadowNode)child).mOverflowsContainer) {
                                final Rect mLogicalOffset = ((FlatShadowNode)child).mLogicalOffset;
                                if (rect == null) {
                                    rect = new Rect();
                                }
                                rect.union(mLogicalOffset);
                                b2 = true;
                            }
                            ++n4;
                        }
                    }
                }
                if (this.mOverflowsContainer != mOverflowsContainer) {
                    this.mOverflowsContainer = mOverflowsContainer;
                    Rect logical_OFFSET_EMPTY;
                    if ((logical_OFFSET_EMPTY = rect2) == null) {
                        logical_OFFSET_EMPTY = FlatShadowNode.LOGICAL_OFFSET_EMPTY;
                    }
                    this.mLogicalOffset = logical_OFFSET_EMPTY;
                }
                return;
            }
            Rect rect = null;
            b2 = b3;
            continue;
        }
    }
}
