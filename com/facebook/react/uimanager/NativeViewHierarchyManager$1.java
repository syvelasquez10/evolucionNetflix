// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import android.view.ViewGroup;
import android.view.View;
import com.facebook.react.uimanager.layoutanimation.LayoutAnimationListener;

class NativeViewHierarchyManager$1 implements LayoutAnimationListener
{
    final /* synthetic */ NativeViewHierarchyManager this$0;
    final /* synthetic */ ViewGroupManager val$viewManager;
    final /* synthetic */ View val$viewToDestroy;
    final /* synthetic */ ViewGroup val$viewToManage;
    
    NativeViewHierarchyManager$1(final NativeViewHierarchyManager this$0, final ViewGroupManager val$viewManager, final ViewGroup val$viewToManage, final View val$viewToDestroy) {
        this.this$0 = this$0;
        this.val$viewManager = val$viewManager;
        this.val$viewToManage = val$viewToManage;
        this.val$viewToDestroy = val$viewToDestroy;
    }
    
    @Override
    public void onAnimationEnd() {
        this.val$viewManager.removeView(this.val$viewToManage, this.val$viewToDestroy);
        this.this$0.dropView(this.val$viewToDestroy);
    }
}
