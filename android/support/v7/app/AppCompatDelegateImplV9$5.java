// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.view.View$MeasureSpec;
import android.support.v7.widget.ViewUtils;
import android.os.Build$VERSION;
import android.view.accessibility.AccessibilityEvent;
import android.view.MotionEvent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.widget.ActionMenuPresenter;
import android.support.v7.view.menu.MenuBuilder;
import android.view.View$OnClickListener;
import android.support.v7.widget.ActionBarContextView$1;
import android.support.v7.view.ActionMode;
import android.view.ViewGroup$MarginLayoutParams;
import android.view.ViewGroup$LayoutParams;
import android.text.TextUtils;
import android.support.v7.appcompat.R$id;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.support.v7.appcompat.R$layout;
import android.support.v7.widget.TintTypedArray;
import android.support.v7.appcompat.R$styleable;
import android.support.v7.appcompat.R$attr;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v7.widget.AbsActionBarView;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.ViewCompat;
import android.view.View;

class AppCompatDelegateImplV9$5 implements Runnable
{
    final /* synthetic */ AppCompatDelegateImplV9 this$0;
    
    AppCompatDelegateImplV9$5(final AppCompatDelegateImplV9 this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        this.this$0.mActionModePopup.showAtLocation((View)this.this$0.mActionModeView, 55, 0, 0);
        this.this$0.endOnGoingFadeAnimation();
        if (this.this$0.shouldAnimateActionModeView()) {
            ViewCompat.setAlpha((View)this.this$0.mActionModeView, 0.0f);
            (this.this$0.mFadeAnim = ViewCompat.animate((View)this.this$0.mActionModeView).alpha(1.0f)).setListener(new AppCompatDelegateImplV9$5$1(this));
            return;
        }
        ViewCompat.setAlpha((View)this.this$0.mActionModeView, 1.0f);
        this.this$0.mActionModeView.setVisibility(0);
    }
}
