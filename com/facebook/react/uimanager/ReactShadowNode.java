// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import com.facebook.yoga.YogaPositionType;
import com.facebook.yoga.YogaOverflow;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.yoga.YogaJustify;
import com.facebook.yoga.YogaWrap;
import com.facebook.yoga.YogaFlexDirection;
import com.facebook.yoga.YogaAlign;
import com.facebook.yoga.YogaValue;
import com.facebook.yoga.YogaDirection;
import com.facebook.infer.annotation.Assertions;
import com.facebook.yoga.YogaEdge;
import com.facebook.yoga.YogaConstants;
import java.util.Arrays;
import com.facebook.yoga.YogaNode;
import java.util.ArrayList;

public class ReactShadowNode
{
    private float mAbsoluteBottom;
    private float mAbsoluteLeft;
    private float mAbsoluteRight;
    private float mAbsoluteTop;
    private ArrayList<ReactShadowNode> mChildren;
    private final Spacing mDefaultPadding;
    private boolean mIsLayoutOnly;
    private ArrayList<ReactShadowNode> mNativeChildren;
    private ReactShadowNode mNativeParent;
    private boolean mNodeUpdated;
    private final float[] mPadding;
    private final boolean[] mPaddingIsPercent;
    private ReactShadowNode mParent;
    private int mReactTag;
    private ReactShadowNode mRootNode;
    private boolean mShouldNotifyOnLayout;
    private ThemedReactContext mThemedContext;
    private int mTotalNativeChildren;
    private String mViewClassName;
    private final YogaNode mYogaNode;
    
    public ReactShadowNode() {
        this.mNodeUpdated = true;
        this.mTotalNativeChildren = 0;
        this.mDefaultPadding = new Spacing(0.0f);
        this.mPadding = new float[9];
        this.mPaddingIsPercent = new boolean[9];
        if (!this.isVirtual()) {
            YogaNode mYogaNode;
            if ((mYogaNode = YogaNodePool.get().acquire()) == null) {
                mYogaNode = new YogaNode();
            }
            this.mYogaNode = mYogaNode;
            Arrays.fill(this.mPadding, Float.NaN);
            return;
        }
        this.mYogaNode = null;
    }
    
    private void updateNativeChildrenCountInParent(final int n) {
        if (this.mIsLayoutOnly) {
            for (ReactShadowNode reactShadowNode = this.getParent(); reactShadowNode != null; reactShadowNode = reactShadowNode.getParent()) {
                reactShadowNode.mTotalNativeChildren += n;
                if (!reactShadowNode.mIsLayoutOnly) {
                    break;
                }
            }
        }
    }
    
    private void updatePadding() {
        int i = 0;
    Label_0084_Outer:
        while (i <= 8) {
            while (true) {
                Label_0195: {
                    if (i == 0 || i == 2 || i == 4 || i == 5) {
                        if (!YogaConstants.isUndefined(this.mPadding[i]) || !YogaConstants.isUndefined(this.mPadding[6]) || !YogaConstants.isUndefined(this.mPadding[8])) {
                            break Label_0195;
                        }
                        this.mYogaNode.setPadding(YogaEdge.fromInt(i), this.mDefaultPadding.getRaw(i));
                    }
                    else if (i == 1 || i == 3) {
                        if (!YogaConstants.isUndefined(this.mPadding[i]) || !YogaConstants.isUndefined(this.mPadding[7]) || !YogaConstants.isUndefined(this.mPadding[8])) {
                            break Label_0195;
                        }
                        this.mYogaNode.setPadding(YogaEdge.fromInt(i), this.mDefaultPadding.getRaw(i));
                    }
                    else {
                        if (!YogaConstants.isUndefined(this.mPadding[i])) {
                            break Label_0195;
                        }
                        this.mYogaNode.setPadding(YogaEdge.fromInt(i), this.mDefaultPadding.getRaw(i));
                    }
                    ++i;
                    continue Label_0084_Outer;
                }
                if (this.mPaddingIsPercent[i]) {
                    this.mYogaNode.setPaddingPercent(YogaEdge.fromInt(i), this.mPadding[i]);
                    continue;
                }
                this.mYogaNode.setPadding(YogaEdge.fromInt(i), this.mPadding[i]);
                continue;
            }
        }
    }
    
    public void addChildAt(final ReactShadowNode reactShadowNode, int mTotalNativeChildren) {
        if (reactShadowNode.mParent != null) {
            throw new IllegalViewOperationException("Tried to add child that already has a parent! Remove it from its parent first.");
        }
        if (this.mChildren == null) {
            this.mChildren = new ArrayList<ReactShadowNode>(4);
        }
        this.mChildren.add(mTotalNativeChildren, reactShadowNode);
        reactShadowNode.mParent = this;
        if (this.mYogaNode != null && !this.mYogaNode.isMeasureDefined()) {
            final YogaNode mYogaNode = reactShadowNode.mYogaNode;
            if (mYogaNode == null) {
                throw new RuntimeException("Cannot add a child that doesn't have a YogaNode to a parent without a measure function! (Trying to add a '" + reactShadowNode.getClass().getSimpleName() + "' to a '" + this.getClass().getSimpleName() + "')");
            }
            this.mYogaNode.addChildAt(mYogaNode, mTotalNativeChildren);
        }
        this.markUpdated();
        if (reactShadowNode.mIsLayoutOnly) {
            mTotalNativeChildren = reactShadowNode.mTotalNativeChildren;
        }
        else {
            mTotalNativeChildren = 1;
        }
        this.mTotalNativeChildren += mTotalNativeChildren;
        this.updateNativeChildrenCountInParent(mTotalNativeChildren);
    }
    
    public final void addNativeChildAt(final ReactShadowNode reactShadowNode, final int n) {
        final boolean b = true;
        Assertions.assertCondition(!this.mIsLayoutOnly);
        Assertions.assertCondition(!reactShadowNode.mIsLayoutOnly && b);
        if (this.mNativeChildren == null) {
            this.mNativeChildren = new ArrayList<ReactShadowNode>(4);
        }
        this.mNativeChildren.add(n, reactShadowNode);
        reactShadowNode.mNativeParent = this;
    }
    
    public void calculateLayout() {
        this.mYogaNode.calculateLayout();
    }
    
    public void dirty() {
        if (!this.isVirtual()) {
            this.mYogaNode.dirty();
        }
    }
    
    boolean dispatchUpdates(final float n, final float n2, final UIViewOperationQueue uiViewOperationQueue, final NativeViewHierarchyOptimizer nativeViewHierarchyOptimizer) {
        if (this.mNodeUpdated) {
            this.onCollectExtraUpdates(uiViewOperationQueue);
        }
        if (this.hasNewLayout()) {
            this.mAbsoluteLeft = Math.round(this.getLayoutX() + n);
            this.mAbsoluteTop = Math.round(this.getLayoutY() + n2);
            this.mAbsoluteRight = Math.round(this.getLayoutX() + n + this.getLayoutWidth());
            this.mAbsoluteBottom = Math.round(this.getLayoutY() + n2 + this.getLayoutHeight());
            nativeViewHierarchyOptimizer.handleUpdateLayout(this);
            return true;
        }
        return false;
    }
    
    public void dispose() {
        if (this.mYogaNode != null) {
            this.mYogaNode.reset();
            YogaNodePool.get().release(this.mYogaNode);
        }
    }
    
    public final ReactShadowNode getChildAt(final int n) {
        if (this.mChildren == null) {
            throw new ArrayIndexOutOfBoundsException("Index " + n + " out of bounds: node has no children");
        }
        return this.mChildren.get(n);
    }
    
    public final int getChildCount() {
        if (this.mChildren == null) {
            return 0;
        }
        return this.mChildren.size();
    }
    
    public final YogaDirection getLayoutDirection() {
        return this.mYogaNode.getLayoutDirection();
    }
    
    public final float getLayoutHeight() {
        return this.mYogaNode.getLayoutHeight();
    }
    
    public final float getLayoutWidth() {
        return this.mYogaNode.getLayoutWidth();
    }
    
    public final float getLayoutX() {
        return this.mYogaNode.getLayoutX();
    }
    
    public final float getLayoutY() {
        return this.mYogaNode.getLayoutY();
    }
    
    public final int getNativeChildCount() {
        if (this.mNativeChildren == null) {
            return 0;
        }
        return this.mNativeChildren.size();
    }
    
    public final int getNativeOffsetForChild(final ReactShadowNode reactShadowNode) {
        final boolean b = true;
        int i = 0;
        int n = 0;
        while (true) {
            while (i < this.getChildCount()) {
                final ReactShadowNode child = this.getChildAt(i);
                if (reactShadowNode == child) {
                    final int n2 = b ? 1 : 0;
                    if (n2 == 0) {
                        throw new RuntimeException("Child " + reactShadowNode.mReactTag + " was not a child of " + this.mReactTag);
                    }
                    return n;
                }
                else {
                    int totalNativeChildren;
                    if (child.mIsLayoutOnly) {
                        totalNativeChildren = child.getTotalNativeChildren();
                    }
                    else {
                        totalNativeChildren = 1;
                    }
                    n += totalNativeChildren;
                    ++i;
                }
            }
            final int n2 = 0;
            continue;
        }
    }
    
    public final ReactShadowNode getNativeParent() {
        return this.mNativeParent;
    }
    
    public final float getPadding(final int n) {
        return this.mYogaNode.getLayoutPadding(YogaEdge.fromInt(n));
    }
    
    public final ReactShadowNode getParent() {
        return this.mParent;
    }
    
    public final int getReactTag() {
        return this.mReactTag;
    }
    
    public final ReactShadowNode getRootNode() {
        return Assertions.assertNotNull(this.mRootNode);
    }
    
    public int getScreenHeight() {
        return Math.round(this.mAbsoluteBottom - this.mAbsoluteTop);
    }
    
    public int getScreenWidth() {
        return Math.round(this.mAbsoluteRight - this.mAbsoluteLeft);
    }
    
    public int getScreenX() {
        return Math.round(this.getLayoutX());
    }
    
    public int getScreenY() {
        return Math.round(this.getLayoutY());
    }
    
    public final YogaValue getStylePadding(final int n) {
        return this.mYogaNode.getPadding(YogaEdge.fromInt(n));
    }
    
    public final ThemedReactContext getThemedContext() {
        return Assertions.assertNotNull(this.mThemedContext);
    }
    
    public final int getTotalNativeChildren() {
        return this.mTotalNativeChildren;
    }
    
    public final String getViewClass() {
        return Assertions.assertNotNull(this.mViewClassName);
    }
    
    public final boolean hasNewLayout() {
        return this.mYogaNode != null && this.mYogaNode.hasNewLayout();
    }
    
    public final boolean hasUpdates() {
        return this.mNodeUpdated || this.hasNewLayout() || this.isDirty();
    }
    
    public final int indexOf(final ReactShadowNode reactShadowNode) {
        if (this.mChildren == null) {
            return -1;
        }
        return this.mChildren.indexOf(reactShadowNode);
    }
    
    public final int indexOfNativeChild(final ReactShadowNode reactShadowNode) {
        Assertions.assertNotNull(this.mNativeChildren);
        return this.mNativeChildren.indexOf(reactShadowNode);
    }
    
    public final boolean isDirty() {
        return this.mYogaNode != null && this.mYogaNode.isDirty();
    }
    
    public final boolean isLayoutOnly() {
        return this.mIsLayoutOnly;
    }
    
    public boolean isVirtual() {
        return false;
    }
    
    public boolean isVirtualAnchor() {
        return false;
    }
    
    public final void markLayoutSeen() {
        if (this.mYogaNode != null) {
            this.mYogaNode.markLayoutSeen();
        }
    }
    
    public final void markUpdateSeen() {
        this.mNodeUpdated = false;
        if (this.hasNewLayout()) {
            this.markLayoutSeen();
        }
    }
    
    public void markUpdated() {
        if (!this.mNodeUpdated) {
            this.mNodeUpdated = true;
            final ReactShadowNode parent = this.getParent();
            if (parent != null) {
                parent.markUpdated();
            }
        }
    }
    
    public void onAfterUpdateTransaction() {
    }
    
    public void onBeforeLayout() {
    }
    
    public void onCollectExtraUpdates(final UIViewOperationQueue uiViewOperationQueue) {
    }
    
    public final void removeAllNativeChildren() {
        if (this.mNativeChildren != null) {
            for (int i = this.mNativeChildren.size() - 1; i >= 0; --i) {
                this.mNativeChildren.get(i).mNativeParent = null;
            }
            this.mNativeChildren.clear();
        }
    }
    
    public void removeAndDisposeAllChildren() {
        if (this.getChildCount() == 0) {
            return;
        }
        final int childCount = this.getChildCount();
        int n = 0;
        for (int i = childCount - 1; i >= 0; --i) {
            if (this.mYogaNode != null && !this.mYogaNode.isMeasureDefined()) {
                this.mYogaNode.removeChildAt(i);
            }
            final ReactShadowNode child = this.getChildAt(i);
            child.mParent = null;
            child.dispose();
            int mTotalNativeChildren;
            if (child.mIsLayoutOnly) {
                mTotalNativeChildren = child.mTotalNativeChildren;
            }
            else {
                mTotalNativeChildren = 1;
            }
            n += mTotalNativeChildren;
        }
        Assertions.assertNotNull(this.mChildren).clear();
        this.markUpdated();
        this.mTotalNativeChildren -= n;
        this.updateNativeChildrenCountInParent(-n);
    }
    
    public ReactShadowNode removeChildAt(int mTotalNativeChildren) {
        if (this.mChildren == null) {
            throw new ArrayIndexOutOfBoundsException("Index " + mTotalNativeChildren + " out of bounds: node has no children");
        }
        final ReactShadowNode reactShadowNode = this.mChildren.remove(mTotalNativeChildren);
        reactShadowNode.mParent = null;
        if (this.mYogaNode != null && !this.mYogaNode.isMeasureDefined()) {
            this.mYogaNode.removeChildAt(mTotalNativeChildren);
        }
        this.markUpdated();
        if (reactShadowNode.mIsLayoutOnly) {
            mTotalNativeChildren = reactShadowNode.mTotalNativeChildren;
        }
        else {
            mTotalNativeChildren = 1;
        }
        this.mTotalNativeChildren -= mTotalNativeChildren;
        this.updateNativeChildrenCountInParent(-mTotalNativeChildren);
        return reactShadowNode;
    }
    
    public final ReactShadowNode removeNativeChildAt(final int n) {
        Assertions.assertNotNull(this.mNativeChildren);
        final ReactShadowNode reactShadowNode = this.mNativeChildren.remove(n);
        reactShadowNode.mNativeParent = null;
        return reactShadowNode;
    }
    
    public void setAlignItems(final YogaAlign alignItems) {
        this.mYogaNode.setAlignItems(alignItems);
    }
    
    public void setAlignSelf(final YogaAlign alignSelf) {
        this.mYogaNode.setAlignSelf(alignSelf);
    }
    
    public void setBorder(final int n, final float n2) {
        this.mYogaNode.setBorder(YogaEdge.fromInt(n), n2);
    }
    
    public void setDefaultPadding(final int n, final float n2) {
        this.mDefaultPadding.set(n, n2);
        this.updatePadding();
    }
    
    public void setFlex(final float flex) {
        this.mYogaNode.setFlex(flex);
    }
    
    public void setFlexBasis(final float flexBasis) {
        this.mYogaNode.setFlexBasis(flexBasis);
    }
    
    public void setFlexBasisPercent(final float flexBasisPercent) {
        this.mYogaNode.setFlexBasisPercent(flexBasisPercent);
    }
    
    public void setFlexDirection(final YogaFlexDirection flexDirection) {
        this.mYogaNode.setFlexDirection(flexDirection);
    }
    
    public void setFlexGrow(final float flexGrow) {
        this.mYogaNode.setFlexGrow(flexGrow);
    }
    
    public void setFlexShrink(final float flexShrink) {
        this.mYogaNode.setFlexShrink(flexShrink);
    }
    
    public void setFlexWrap(final YogaWrap wrap) {
        this.mYogaNode.setWrap(wrap);
    }
    
    public final void setIsLayoutOnly(final boolean mIsLayoutOnly) {
        final boolean b = true;
        Assertions.assertCondition(this.getParent() == null, "Must remove from no opt parent first");
        Assertions.assertCondition(this.mNativeParent == null, "Must remove from native parent first");
        Assertions.assertCondition(this.getNativeChildCount() == 0 && b, "Must remove all native children first");
        this.mIsLayoutOnly = mIsLayoutOnly;
    }
    
    public void setJustifyContent(final YogaJustify justifyContent) {
        this.mYogaNode.setJustifyContent(justifyContent);
    }
    
    public void setLayoutDirection(final YogaDirection direction) {
        this.mYogaNode.setDirection(direction);
    }
    
    public void setMargin(final int n, final float n2) {
        this.mYogaNode.setMargin(YogaEdge.fromInt(n), n2);
    }
    
    public void setMarginPercent(final int n, final float n2) {
        this.mYogaNode.setMarginPercent(YogaEdge.fromInt(n), n2);
    }
    
    public void setMeasureFunction(final YogaMeasureFunction measureFunction) {
        if ((measureFunction == null ^ this.mYogaNode.isMeasureDefined()) && this.getChildCount() != 0) {
            throw new RuntimeException("Since a node with a measure function does not add any native yoga children, it's not safe to transition to/from having a measure function unless a node has no children");
        }
        this.mYogaNode.setMeasureFunction(measureFunction);
    }
    
    public void setOverflow(final YogaOverflow overflow) {
        this.mYogaNode.setOverflow(overflow);
    }
    
    public void setPadding(final int n, final float n2) {
        this.mPadding[n] = n2;
        this.mPaddingIsPercent[n] = false;
        this.updatePadding();
    }
    
    public void setPaddingPercent(final int n, final float n2) {
        this.mPadding[n] = n2;
        this.mPaddingIsPercent[n] = !YogaConstants.isUndefined(n2);
        this.updatePadding();
    }
    
    public void setPosition(final int n, final float n2) {
        this.mYogaNode.setPosition(YogaEdge.fromInt(n), n2);
    }
    
    public void setPositionPercent(final int n, final float n2) {
        this.mYogaNode.setPositionPercent(YogaEdge.fromInt(n), n2);
    }
    
    public void setPositionType(final YogaPositionType positionType) {
        this.mYogaNode.setPositionType(positionType);
    }
    
    public void setReactTag(final int mReactTag) {
        this.mReactTag = mReactTag;
    }
    
    final void setRootNode(final ReactShadowNode mRootNode) {
        this.mRootNode = mRootNode;
    }
    
    public void setShouldNotifyOnLayout(final boolean mShouldNotifyOnLayout) {
        this.mShouldNotifyOnLayout = mShouldNotifyOnLayout;
    }
    
    public void setStyleAspectRatio(final float aspectRatio) {
        this.mYogaNode.setAspectRatio(aspectRatio);
    }
    
    public void setStyleHeight(final float height) {
        this.mYogaNode.setHeight(height);
    }
    
    public void setStyleHeightPercent(final float heightPercent) {
        this.mYogaNode.setHeightPercent(heightPercent);
    }
    
    public void setStyleMaxHeight(final float maxHeight) {
        this.mYogaNode.setMaxHeight(maxHeight);
    }
    
    public void setStyleMaxHeightPercent(final float maxHeightPercent) {
        this.mYogaNode.setMaxHeightPercent(maxHeightPercent);
    }
    
    public void setStyleMaxWidth(final float maxWidth) {
        this.mYogaNode.setMaxWidth(maxWidth);
    }
    
    public void setStyleMaxWidthPercent(final float maxWidthPercent) {
        this.mYogaNode.setMaxWidthPercent(maxWidthPercent);
    }
    
    public void setStyleMinHeight(final float minHeight) {
        this.mYogaNode.setMinHeight(minHeight);
    }
    
    public void setStyleMinHeightPercent(final float minHeightPercent) {
        this.mYogaNode.setMinHeightPercent(minHeightPercent);
    }
    
    public void setStyleMinWidth(final float minWidth) {
        this.mYogaNode.setMinWidth(minWidth);
    }
    
    public void setStyleMinWidthPercent(final float minWidthPercent) {
        this.mYogaNode.setMinWidthPercent(minWidthPercent);
    }
    
    public void setStyleWidth(final float width) {
        this.mYogaNode.setWidth(width);
    }
    
    public void setStyleWidthPercent(final float widthPercent) {
        this.mYogaNode.setWidthPercent(widthPercent);
    }
    
    public void setThemedContext(final ThemedReactContext mThemedContext) {
        this.mThemedContext = mThemedContext;
    }
    
    final void setViewClassName(final String mViewClassName) {
        this.mViewClassName = mViewClassName;
    }
    
    public final boolean shouldNotifyOnLayout() {
        return this.mShouldNotifyOnLayout;
    }
    
    @Override
    public String toString() {
        return this.mYogaNode.toString();
    }
    
    public final void updateProperties(final ReactStylesDiffMap reactStylesDiffMap) {
        ViewManagerPropertyUpdater.updateProps(this, reactStylesDiffMap);
        this.onAfterUpdateTransaction();
    }
}
