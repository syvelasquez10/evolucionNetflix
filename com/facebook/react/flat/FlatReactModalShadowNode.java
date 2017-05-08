// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.flat;

import android.content.Context;
import com.facebook.yoga.YogaValue;
import com.facebook.yoga.YogaUnit;
import android.annotation.TargetApi;
import android.view.Display;
import android.view.WindowManager;
import com.facebook.react.uimanager.ReactShadowNode;
import android.graphics.Point;

class FlatReactModalShadowNode extends FlatShadowNode
{
    private final Point mMaxPoint;
    private final Point mMinPoint;
    private boolean mPaddingChanged;
    
    FlatReactModalShadowNode() {
        this.mMinPoint = new Point();
        this.mMaxPoint = new Point();
        this.forceMountToView();
        this.forceMountChildrenToView();
    }
    
    @TargetApi(16)
    @Override
    public void addChildAt(final ReactShadowNode reactShadowNode, int n) {
        super.addChildAt(reactShadowNode, n);
        final Display defaultDisplay = ((WindowManager)((Context)this.getThemedContext()).getSystemService("window")).getDefaultDisplay();
        defaultDisplay.getCurrentSizeRange(this.mMinPoint, this.mMaxPoint);
        n = defaultDisplay.getRotation();
        int n2;
        if (n == 0 || n == 2) {
            n2 = this.mMinPoint.x;
            n = this.mMaxPoint.y;
        }
        else {
            n2 = this.mMaxPoint.x;
            n = this.mMinPoint.y;
        }
        reactShadowNode.setStyleWidth(n2);
        reactShadowNode.setStyleHeight(n);
    }
    
    @Override
    public void setPadding(final int n, final float n2) {
        final YogaValue stylePadding = this.getStylePadding(n);
        if (stylePadding.unit != YogaUnit.PIXEL || stylePadding.value != n2) {
            super.setPadding(n, n2);
            this.mPaddingChanged = true;
            this.markUpdated();
        }
    }
    
    @Override
    public void setPaddingPercent(final int n, final float n2) {
        final YogaValue stylePadding = this.getStylePadding(n);
        if (stylePadding.unit != YogaUnit.PERCENT || stylePadding.value != n2) {
            super.setPadding(n, n2);
            this.mPaddingChanged = true;
            this.markUpdated();
        }
    }
}
