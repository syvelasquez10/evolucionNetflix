// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.yoga;

import java.util.ArrayList;
import com.facebook.soloader.SoLoader;
import java.util.List;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public class YogaNode implements YogaNodeAPI<YogaNode>
{
    private List<YogaNode> mChildren;
    private Object mData;
    private boolean mHasSetBorder;
    private boolean mHasSetMargin;
    private boolean mHasSetPadding;
    private boolean mHasSetPosition;
    @DoNotStrip
    private float mHeight;
    @DoNotStrip
    private int mLayoutDirection;
    @DoNotStrip
    private float mLeft;
    private YogaMeasureFunction mMeasureFunction;
    private long mNativePointer;
    private YogaNode mParent;
    @DoNotStrip
    private float mTop;
    @DoNotStrip
    private float mWidth;
    
    static {
        SoLoader.loadLibrary("yoga");
    }
    
    public YogaNode() {
        this.mHasSetPadding = false;
        this.mHasSetMargin = false;
        this.mHasSetBorder = false;
        this.mHasSetPosition = false;
        this.mWidth = Float.NaN;
        this.mHeight = Float.NaN;
        this.mTop = Float.NaN;
        this.mLeft = Float.NaN;
        this.mLayoutDirection = 0;
        this.mNativePointer = this.jni_YGNodeNew();
        if (this.mNativePointer == 0L) {
            throw new IllegalStateException("Failed to allocate native memory");
        }
    }
    
    private static native boolean jni_YGIsExperimentalFeatureEnabled(final int p0);
    
    static native void jni_YGLog(final int p0, final String p1);
    
    private native void jni_YGNodeCalculateLayout(final long p0);
    
    private native void jni_YGNodeCopyStyle(final long p0, final long p1);
    
    private native void jni_YGNodeFree(final long p0);
    
    static native int jni_YGNodeGetInstanceCount();
    
    private native boolean jni_YGNodeHasNewLayout(final long p0);
    
    private native void jni_YGNodeInsertChild(final long p0, final long p1, final int p2);
    
    private native boolean jni_YGNodeIsDirty(final long p0);
    
    private native void jni_YGNodeMarkDirty(final long p0);
    
    private native void jni_YGNodeMarkLayoutSeen(final long p0);
    
    private native long jni_YGNodeNew();
    
    private native void jni_YGNodeRemoveChild(final long p0, final long p1);
    
    private native void jni_YGNodeReset(final long p0);
    
    private native void jni_YGNodeSetHasMeasureFunc(final long p0, final boolean p1);
    
    private native int jni_YGNodeStyleGetAlignContent(final long p0);
    
    private native int jni_YGNodeStyleGetAlignItems(final long p0);
    
    private native int jni_YGNodeStyleGetAlignSelf(final long p0);
    
    private native float jni_YGNodeStyleGetAspectRatio(final long p0);
    
    private native float jni_YGNodeStyleGetBorder(final long p0, final int p1);
    
    private native int jni_YGNodeStyleGetDirection(final long p0);
    
    private native float jni_YGNodeStyleGetFlexBasis(final long p0);
    
    private native int jni_YGNodeStyleGetFlexDirection(final long p0);
    
    private native float jni_YGNodeStyleGetFlexGrow(final long p0);
    
    private native float jni_YGNodeStyleGetFlexShrink(final long p0);
    
    private native float jni_YGNodeStyleGetHeight(final long p0);
    
    private native int jni_YGNodeStyleGetJustifyContent(final long p0);
    
    private native float jni_YGNodeStyleGetMargin(final long p0, final int p1);
    
    private native float jni_YGNodeStyleGetMaxHeight(final long p0);
    
    private native float jni_YGNodeStyleGetMaxWidth(final long p0);
    
    private native float jni_YGNodeStyleGetMinHeight(final long p0);
    
    private native float jni_YGNodeStyleGetMinWidth(final long p0);
    
    private native int jni_YGNodeStyleGetOverflow(final long p0);
    
    private native float jni_YGNodeStyleGetPadding(final long p0, final int p1);
    
    private native float jni_YGNodeStyleGetPosition(final long p0, final int p1);
    
    private native int jni_YGNodeStyleGetPositionType(final long p0);
    
    private native float jni_YGNodeStyleGetWidth(final long p0);
    
    private native void jni_YGNodeStyleSetAlignContent(final long p0, final int p1);
    
    private native void jni_YGNodeStyleSetAlignItems(final long p0, final int p1);
    
    private native void jni_YGNodeStyleSetAlignSelf(final long p0, final int p1);
    
    private native void jni_YGNodeStyleSetAspectRatio(final long p0, final float p1);
    
    private native void jni_YGNodeStyleSetBorder(final long p0, final int p1, final float p2);
    
    private native void jni_YGNodeStyleSetDirection(final long p0, final int p1);
    
    private native void jni_YGNodeStyleSetFlex(final long p0, final float p1);
    
    private native void jni_YGNodeStyleSetFlexBasis(final long p0, final float p1);
    
    private native void jni_YGNodeStyleSetFlexDirection(final long p0, final int p1);
    
    private native void jni_YGNodeStyleSetFlexGrow(final long p0, final float p1);
    
    private native void jni_YGNodeStyleSetFlexShrink(final long p0, final float p1);
    
    private native void jni_YGNodeStyleSetFlexWrap(final long p0, final int p1);
    
    private native void jni_YGNodeStyleSetHeight(final long p0, final float p1);
    
    private native void jni_YGNodeStyleSetJustifyContent(final long p0, final int p1);
    
    private native void jni_YGNodeStyleSetMargin(final long p0, final int p1, final float p2);
    
    private native void jni_YGNodeStyleSetMaxHeight(final long p0, final float p1);
    
    private native void jni_YGNodeStyleSetMaxWidth(final long p0, final float p1);
    
    private native void jni_YGNodeStyleSetMinHeight(final long p0, final float p1);
    
    private native void jni_YGNodeStyleSetMinWidth(final long p0, final float p1);
    
    private native void jni_YGNodeStyleSetOverflow(final long p0, final int p1);
    
    private native void jni_YGNodeStyleSetPadding(final long p0, final int p1, final float p2);
    
    private native void jni_YGNodeStyleSetPosition(final long p0, final int p1, final float p2);
    
    private native void jni_YGNodeStyleSetPositionType(final long p0, final int p1);
    
    private native void jni_YGNodeStyleSetWidth(final long p0, final float p1);
    
    private static native void jni_YGSetExperimentalFeatureEnabled(final int p0, final boolean p1);
    
    private static native void jni_YGSetLogger(final Object p0);
    
    public void addChildAt(final YogaNode yogaNode, final int n) {
        if (yogaNode.mParent != null) {
            throw new IllegalStateException("Child already has a parent, it must be removed first.");
        }
        if (this.mChildren == null) {
            this.mChildren = new ArrayList<YogaNode>(4);
        }
        this.mChildren.add(n, yogaNode);
        (yogaNode.mParent = this).jni_YGNodeInsertChild(this.mNativePointer, yogaNode.mNativePointer, n);
    }
    
    public void calculateLayout() {
        this.jni_YGNodeCalculateLayout(this.mNativePointer);
    }
    
    public void dirty() {
        this.jni_YGNodeMarkDirty(this.mNativePointer);
    }
    
    @Override
    protected void finalize() {
        try {
            this.jni_YGNodeFree(this.mNativePointer);
        }
        finally {
            super.finalize();
        }
    }
    
    public YogaDirection getLayoutDirection() {
        return YogaDirection.values()[this.mLayoutDirection];
    }
    
    public float getLayoutHeight() {
        return this.mHeight;
    }
    
    public float getLayoutWidth() {
        return this.mWidth;
    }
    
    public float getLayoutX() {
        return this.mLeft;
    }
    
    public float getLayoutY() {
        return this.mTop;
    }
    
    public float getPadding(final YogaEdge yogaEdge) {
        if (this.mHasSetPadding) {
            return this.jni_YGNodeStyleGetPadding(this.mNativePointer, yogaEdge.intValue());
        }
        if (yogaEdge.intValue() < YogaEdge.START.intValue()) {
            return 0.0f;
        }
        return Float.NaN;
    }
    
    public boolean hasNewLayout() {
        return this.jni_YGNodeHasNewLayout(this.mNativePointer);
    }
    
    public boolean isDirty() {
        return this.jni_YGNodeIsDirty(this.mNativePointer);
    }
    
    public boolean isMeasureDefined() {
        return this.mMeasureFunction != null;
    }
    
    public void markLayoutSeen() {
        this.jni_YGNodeMarkLayoutSeen(this.mNativePointer);
    }
    
    @DoNotStrip
    public final long measure(final float n, final int n2, final float n3, final int n4) {
        if (!this.isMeasureDefined()) {
            throw new RuntimeException("Measure function isn't defined!");
        }
        return this.mMeasureFunction.measure(this, n, YogaMeasureMode.values()[n2], n3, YogaMeasureMode.values()[n4]);
    }
    
    public YogaNode removeChildAt(final int n) {
        final YogaNode yogaNode = this.mChildren.remove(n);
        yogaNode.mParent = null;
        this.jni_YGNodeRemoveChild(this.mNativePointer, yogaNode.mNativePointer);
        return yogaNode;
    }
    
    public void reset() {
        this.mHasSetPadding = false;
        this.mHasSetMargin = false;
        this.mHasSetBorder = false;
        this.mHasSetPosition = false;
        this.mWidth = Float.NaN;
        this.mHeight = Float.NaN;
        this.mTop = Float.NaN;
        this.mLeft = Float.NaN;
        this.mLayoutDirection = 0;
        this.mMeasureFunction = null;
        this.mData = null;
        this.jni_YGNodeReset(this.mNativePointer);
    }
    
    public void setAlignItems(final YogaAlign yogaAlign) {
        this.jni_YGNodeStyleSetAlignItems(this.mNativePointer, yogaAlign.intValue());
    }
    
    public void setAlignSelf(final YogaAlign yogaAlign) {
        this.jni_YGNodeStyleSetAlignSelf(this.mNativePointer, yogaAlign.intValue());
    }
    
    public void setAspectRatio(final float n) {
        this.jni_YGNodeStyleSetAspectRatio(this.mNativePointer, n);
    }
    
    public void setBorder(final YogaEdge yogaEdge, final float n) {
        this.mHasSetBorder = true;
        this.jni_YGNodeStyleSetBorder(this.mNativePointer, yogaEdge.intValue(), n);
    }
    
    public void setDirection(final YogaDirection yogaDirection) {
        this.jni_YGNodeStyleSetDirection(this.mNativePointer, yogaDirection.intValue());
    }
    
    public void setFlex(final float n) {
        this.jni_YGNodeStyleSetFlex(this.mNativePointer, n);
    }
    
    public void setFlexBasis(final float n) {
        this.jni_YGNodeStyleSetFlexBasis(this.mNativePointer, n);
    }
    
    public void setFlexDirection(final YogaFlexDirection yogaFlexDirection) {
        this.jni_YGNodeStyleSetFlexDirection(this.mNativePointer, yogaFlexDirection.intValue());
    }
    
    public void setFlexGrow(final float n) {
        this.jni_YGNodeStyleSetFlexGrow(this.mNativePointer, n);
    }
    
    public void setFlexShrink(final float n) {
        this.jni_YGNodeStyleSetFlexShrink(this.mNativePointer, n);
    }
    
    public void setHeight(final float n) {
        this.jni_YGNodeStyleSetHeight(this.mNativePointer, n);
    }
    
    public void setJustifyContent(final YogaJustify yogaJustify) {
        this.jni_YGNodeStyleSetJustifyContent(this.mNativePointer, yogaJustify.intValue());
    }
    
    public void setMargin(final YogaEdge yogaEdge, final float n) {
        this.mHasSetMargin = true;
        this.jni_YGNodeStyleSetMargin(this.mNativePointer, yogaEdge.intValue(), n);
    }
    
    public void setMaxHeight(final float n) {
        this.jni_YGNodeStyleSetMaxHeight(this.mNativePointer, n);
    }
    
    public void setMaxWidth(final float n) {
        this.jni_YGNodeStyleSetMaxWidth(this.mNativePointer, n);
    }
    
    public void setMeasureFunction(final YogaMeasureFunction mMeasureFunction) {
        this.mMeasureFunction = mMeasureFunction;
        this.jni_YGNodeSetHasMeasureFunc(this.mNativePointer, mMeasureFunction != null);
    }
    
    public void setMinHeight(final float n) {
        this.jni_YGNodeStyleSetMinHeight(this.mNativePointer, n);
    }
    
    public void setMinWidth(final float n) {
        this.jni_YGNodeStyleSetMinWidth(this.mNativePointer, n);
    }
    
    public void setOverflow(final YogaOverflow yogaOverflow) {
        this.jni_YGNodeStyleSetOverflow(this.mNativePointer, yogaOverflow.intValue());
    }
    
    public void setPadding(final YogaEdge yogaEdge, final float n) {
        this.mHasSetPadding = true;
        this.jni_YGNodeStyleSetPadding(this.mNativePointer, yogaEdge.intValue(), n);
    }
    
    public void setPosition(final YogaEdge yogaEdge, final float n) {
        this.mHasSetPosition = true;
        this.jni_YGNodeStyleSetPosition(this.mNativePointer, yogaEdge.intValue(), n);
    }
    
    public void setPositionType(final YogaPositionType yogaPositionType) {
        this.jni_YGNodeStyleSetPositionType(this.mNativePointer, yogaPositionType.intValue());
    }
    
    public void setWidth(final float n) {
        this.jni_YGNodeStyleSetWidth(this.mNativePointer, n);
    }
    
    public void setWrap(final YogaWrap yogaWrap) {
        this.jni_YGNodeStyleSetFlexWrap(this.mNativePointer, yogaWrap.intValue());
    }
}
