// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.modal;

import android.graphics.Point;
import android.content.Context;
import com.facebook.react.uimanager.ReactShadowNode;
import com.facebook.react.uimanager.LayoutShadowNode;

class ModalHostShadowNode extends LayoutShadowNode
{
    @Override
    public void addChildAt(final ReactShadowNode reactShadowNode, final int n) {
        super.addChildAt(reactShadowNode, n);
        final Point modalHostSize = ModalHostHelper.getModalHostSize((Context)this.getThemedContext());
        reactShadowNode.setStyleWidth(modalHostSize.x);
        reactShadowNode.setStyleHeight(modalHostSize.y);
    }
}
