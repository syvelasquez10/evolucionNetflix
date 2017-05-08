// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import android.util.Log;
import android.view.View$MeasureSpec;
import com.facebook.react.animation.Animation;
import android.view.Menu;
import android.widget.PopupMenu$OnDismissListener;
import android.widget.PopupMenu$OnMenuItemClickListener;
import android.content.Context;
import android.widget.PopupMenu;
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
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.Callback;
import com.facebook.react.animation.AnimationListener;

class NativeViewHierarchyManager$2 implements AnimationListener
{
    final /* synthetic */ NativeViewHierarchyManager this$0;
    final /* synthetic */ Callback val$animationCallback;
    final /* synthetic */ int val$animationId;
    
    NativeViewHierarchyManager$2(final NativeViewHierarchyManager this$0, final int val$animationId, final Callback val$animationCallback) {
        this.this$0 = this$0;
        this.val$animationId = val$animationId;
        this.val$animationCallback = val$animationCallback;
    }
    
    @Override
    public void onCancel() {
        Assertions.assertNotNull(this.this$0.mAnimationRegistry.removeAnimation(this.val$animationId), "Animation was already removed somehow!");
        if (this.val$animationCallback != null) {
            this.val$animationCallback.invoke(false);
        }
    }
}
