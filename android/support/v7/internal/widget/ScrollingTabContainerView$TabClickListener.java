// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.view.View$MeasureSpec;
import android.os.Build$VERSION;
import android.content.res.Configuration;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.ViewCompat;
import android.widget.SpinnerAdapter;
import android.widget.AbsListView$LayoutParams;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutCompat$LayoutParams;
import android.util.AttributeSet;
import android.support.v7.appcompat.R$attr;
import android.support.v7.app.ActionBar$Tab;
import android.view.ViewGroup$LayoutParams;
import android.support.v7.internal.view.ActionBarPolicy;
import android.content.Context;
import android.view.animation.DecelerateInterpolator;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.animation.Interpolator;
import android.widget.HorizontalScrollView;
import android.view.View;
import android.view.View$OnClickListener;

class ScrollingTabContainerView$TabClickListener implements View$OnClickListener
{
    final /* synthetic */ ScrollingTabContainerView this$0;
    
    private ScrollingTabContainerView$TabClickListener(final ScrollingTabContainerView this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        ((ScrollingTabContainerView$TabView)view).getTab().select();
        for (int childCount = this.this$0.mTabLayout.getChildCount(), i = 0; i < childCount; ++i) {
            final View child = this.this$0.mTabLayout.getChildAt(i);
            child.setSelected(child == view);
        }
    }
}
