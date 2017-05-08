// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.flat;

import com.facebook.react.uimanager.ReactClippingViewGroupHelper;
import android.graphics.Canvas;
import com.facebook.infer.annotation.Assertions;
import android.view.animation.Animation;
import android.util.SparseIntArray;
import com.facebook.react.uimanager.ReactClippingViewGroup;
import java.util.ArrayList;
import android.graphics.Rect;
import android.view.View;
import android.util.SparseArray;

abstract class ClippingDrawCommandManager extends DrawCommandManager
{
    private final SparseArray<View> mClippedSubviews;
    protected final Rect mClippingRect;
    private final ArrayList<ReactClippingViewGroup> mClippingViewGroups;
    protected float[] mCommandMaxBottom;
    protected float[] mCommandMinTop;
    private DrawCommand[] mDrawCommands;
    private SparseIntArray mDrawViewIndexMap;
    private final FlatViewGroup mFlatViewGroup;
    private NodeRegion[] mNodeRegions;
    protected float[] mRegionMaxBottom;
    protected float[] mRegionMinTop;
    private int mStart;
    private int mStop;
    private final ArrayList<View> mViewsToKeep;
    private final SparseArray<View> mViewsToRemove;
    
    ClippingDrawCommandManager(final FlatViewGroup mFlatViewGroup, final DrawCommand[] array) {
        this.mDrawCommands = DrawCommand.EMPTY_ARRAY;
        this.mCommandMaxBottom = StateBuilder.EMPTY_FLOAT_ARRAY;
        this.mCommandMinTop = StateBuilder.EMPTY_FLOAT_ARRAY;
        this.mNodeRegions = NodeRegion.EMPTY_ARRAY;
        this.mRegionMaxBottom = StateBuilder.EMPTY_FLOAT_ARRAY;
        this.mRegionMinTop = StateBuilder.EMPTY_FLOAT_ARRAY;
        this.mDrawViewIndexMap = StateBuilder.EMPTY_SPARSE_INT;
        this.mClippedSubviews = (SparseArray<View>)new SparseArray();
        this.mClippingRect = new Rect();
        this.mViewsToRemove = (SparseArray<View>)new SparseArray();
        this.mViewsToKeep = new ArrayList<View>();
        this.mClippingViewGroups = new ArrayList<ReactClippingViewGroup>();
        this.mFlatViewGroup = mFlatViewGroup;
        this.initialSetup(array);
    }
    
    private static boolean animating(final View view) {
        final Animation animation = view.getAnimation();
        return animation != null && !animation.hasEnded();
    }
    
    private void clip(final int n, final View view) {
        this.mClippedSubviews.put(n, (Object)view);
    }
    
    private void initialSetup(final DrawCommand[] array) {
        this.mountDrawCommands(array, this.mDrawViewIndexMap, this.mCommandMaxBottom, this.mCommandMinTop, true);
        this.updateClippingRect();
    }
    
    private boolean isNotClipped(final int n) {
        return this.mClippedSubviews.get(n) == null;
    }
    
    private void unclip(final int n) {
        this.mClippedSubviews.remove(n);
    }
    
    private void updateClippingRecursively() {
        for (int size = this.mClippingViewGroups.size(), i = 0; i < size; ++i) {
            final ReactClippingViewGroup reactClippingViewGroup = this.mClippingViewGroups.get(i);
            if (this.isNotClipped(((View)reactClippingViewGroup).getId())) {
                reactClippingViewGroup.updateClippingRect();
            }
        }
    }
    
    private void updateClippingToCurrentRect() {
        for (int childCount = this.mFlatViewGroup.getChildCount(), i = 0; i < childCount; ++i) {
            final View child = this.mFlatViewGroup.getChildAt(i);
            if (this.withinBounds(this.mDrawViewIndexMap.get(child.getId())) || animating(child)) {
                this.mViewsToKeep.add(child);
            }
            else {
                this.mViewsToRemove.append(i, (Object)child);
                this.clip(child.getId(), child);
            }
        }
        final int size = this.mViewsToRemove.size();
        boolean b;
        if (size > 2) {
            b = true;
        }
        else {
            b = false;
        }
        if (b) {
            this.mFlatViewGroup.detachAllViewsFromParent();
            for (int j = 0; j < size; ++j) {
                this.mFlatViewGroup.removeDetachedView((View)this.mViewsToRemove.valueAt(j));
            }
        }
        else {
            int n = size;
            while (true) {
                final int n2 = n - 1;
                if (n <= 0) {
                    break;
                }
                this.mFlatViewGroup.removeViewsInLayout(this.mViewsToRemove.keyAt(n2), 1);
                n = n2;
            }
        }
        this.mViewsToRemove.clear();
        int k = this.mStart;
        final int size2 = this.mViewsToKeep.size();
        int l = 0;
        int n3 = 0;
        while (l < size2) {
            final View view = this.mViewsToKeep.get(l);
            final int value = this.mDrawViewIndexMap.get(view.getId());
            int n4 = k;
            int n5 = n3;
            if (k <= value) {
                while (k != value) {
                    if (this.mDrawCommands[k] instanceof DrawView) {
                        final DrawView drawView = (DrawView)this.mDrawCommands[k];
                        this.mFlatViewGroup.addViewInLayout(Assertions.assumeNotNull(this.mClippedSubviews.get(drawView.reactTag)), n3);
                        this.unclip(drawView.reactTag);
                        ++n3;
                    }
                    ++k;
                }
                n4 = k + 1;
                n5 = n3;
            }
            if (b) {
                this.mFlatViewGroup.attachViewToParent(view, n5);
            }
            n3 = n5 + 1;
            ++l;
            k = n4;
        }
        this.mViewsToKeep.clear();
        while (k < this.mStop) {
            if (this.mDrawCommands[k] instanceof DrawView) {
                final DrawView drawView2 = (DrawView)this.mDrawCommands[k];
                this.mFlatViewGroup.addViewInLayout(Assertions.assumeNotNull(this.mClippedSubviews.get(drawView2.reactTag)), n3);
                this.unclip(drawView2.reactTag);
                ++n3;
            }
            ++k;
        }
    }
    
    private boolean withinBounds(final int n) {
        return this.mStart <= n && n < this.mStop;
    }
    
    public NodeRegion anyNodeRegionWithinBounds(final float n, final float n2) {
        int regionStopIndex = this.regionStopIndex(n, n2);
        while (true) {
            final int n3 = regionStopIndex - 1;
            if (regionStopIndex <= 0) {
                break;
            }
            final NodeRegion nodeRegion = this.mNodeRegions[n3];
            if (this.regionAboveTouch(n3, n, n2)) {
                break;
            }
            final NodeRegion nodeRegion2 = nodeRegion;
            if (nodeRegion.withinBounds(n, n2)) {
                return nodeRegion2;
            }
            regionStopIndex = n3;
        }
        return null;
    }
    
    abstract int commandStartIndex();
    
    abstract int commandStopIndex(final int p0);
    
    @Override
    void debugDraw(final Canvas canvas) {
        final DrawCommand[] mDrawCommands = this.mDrawCommands;
        for (int length = mDrawCommands.length, i = 0; i < length; ++i) {
            final DrawCommand drawCommand = mDrawCommands[i];
            if (drawCommand instanceof DrawView) {
                if (this.isNotClipped(((DrawView)drawCommand).reactTag)) {
                    drawCommand.debugDraw(this.mFlatViewGroup, canvas);
                }
            }
            else {
                drawCommand.debugDraw(this.mFlatViewGroup, canvas);
            }
        }
    }
    
    public void draw(final Canvas canvas) {
        int i = this.mStart;
        final int childCount = this.mFlatViewGroup.getChildCount();
        int n = 0;
        int j;
        while (true) {
            j = i;
            if (n >= childCount) {
                break;
            }
            final int value = this.mDrawViewIndexMap.get(this.mFlatViewGroup.getChildAt(n).getId());
            int n2;
            if (this.mStop < value) {
                while ((n2 = i) < this.mStop) {
                    this.mDrawCommands[i].draw(this.mFlatViewGroup, canvas);
                    ++i;
                }
            }
            else if ((n2 = i) <= value) {
                while (i < value) {
                    this.mDrawCommands[i].draw(this.mFlatViewGroup, canvas);
                    ++i;
                }
                n2 = i + 1;
            }
            this.mDrawCommands[value].draw(this.mFlatViewGroup, canvas);
            ++n;
            i = n2;
        }
        while (j < this.mStop) {
            this.mDrawCommands[j].draw(this.mFlatViewGroup, canvas);
            ++j;
        }
    }
    
    public void getClippingRect(final Rect rect) {
        rect.set(this.mClippingRect);
    }
    
    public void mountDrawCommands(final DrawCommand[] mDrawCommands, final SparseIntArray mDrawViewIndexMap, final float[] mCommandMaxBottom, final float[] mCommandMinTop, final boolean b) {
        this.mDrawCommands = mDrawCommands;
        this.mCommandMaxBottom = mCommandMaxBottom;
        this.mCommandMinTop = mCommandMinTop;
        this.mDrawViewIndexMap = mDrawViewIndexMap;
        if (this.mClippingRect.bottom != this.mClippingRect.top) {
            this.mStart = this.commandStartIndex();
            this.mStop = this.commandStopIndex(this.mStart);
            if (!b) {
                this.updateClippingToCurrentRect();
            }
        }
    }
    
    abstract boolean regionAboveTouch(final int p0, final float p1, final float p2);
    
    abstract int regionStopIndex(final float p0, final float p1);
    
    public boolean updateClippingRect() {
        ReactClippingViewGroupHelper.calculateClippingRect((View)this.mFlatViewGroup, this.mClippingRect);
        if (this.mFlatViewGroup.getParent() == null || this.mClippingRect.top == this.mClippingRect.bottom) {
            return false;
        }
        final int commandStartIndex = this.commandStartIndex();
        final int commandStopIndex = this.commandStopIndex(commandStartIndex);
        if (this.mStart <= commandStartIndex && commandStopIndex <= this.mStop) {
            this.updateClippingRecursively();
            return false;
        }
        this.mStart = commandStartIndex;
        this.mStop = commandStopIndex;
        this.updateClippingToCurrentRect();
        this.updateClippingRecursively();
        return true;
    }
    
    public NodeRegion virtualNodeRegionWithinBounds(final float n, final float n2) {
        int regionStopIndex = this.regionStopIndex(n, n2);
        while (true) {
            final int n3 = regionStopIndex - 1;
            if (regionStopIndex <= 0) {
                break;
            }
            final NodeRegion nodeRegion = this.mNodeRegions[n3];
            if (!nodeRegion.mIsVirtual) {
                regionStopIndex = n3;
            }
            else {
                if (this.regionAboveTouch(n3, n, n2)) {
                    break;
                }
                final NodeRegion nodeRegion2 = nodeRegion;
                if (nodeRegion.withinBounds(n, n2)) {
                    return nodeRegion2;
                }
                regionStopIndex = n3;
            }
        }
        return null;
    }
}
