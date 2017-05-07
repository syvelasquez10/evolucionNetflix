// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.view.View$MeasureSpec;
import android.widget.AdapterView;
import android.support.v7.internal.view.ActionBarPolicy;
import android.os.Build$VERSION;
import android.content.res.Configuration;
import android.widget.SpinnerAdapter;
import android.view.View$OnClickListener;
import android.widget.AbsListView$LayoutParams;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup$LayoutParams;
import android.support.v7.widget.LinearLayoutCompat$LayoutParams;
import android.util.AttributeSet;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.appcompat.R$attr;
import android.view.animation.DecelerateInterpolator;
import android.widget.Spinner;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.animation.Interpolator;
import android.widget.AdapterView$OnItemSelectedListener;
import android.widget.HorizontalScrollView;
import android.support.v7.app.ActionBar$Tab;
import android.view.ViewGroup;
import android.view.View;
import android.widget.BaseAdapter;

class ScrollingTabContainerView$TabAdapter extends BaseAdapter
{
    final /* synthetic */ ScrollingTabContainerView this$0;
    
    private ScrollingTabContainerView$TabAdapter(final ScrollingTabContainerView this$0) {
        this.this$0 = this$0;
    }
    
    public int getCount() {
        return this.this$0.mTabLayout.getChildCount();
    }
    
    public Object getItem(final int n) {
        return ((ScrollingTabContainerView$TabView)this.this$0.mTabLayout.getChildAt(n)).getTab();
    }
    
    public long getItemId(final int n) {
        return n;
    }
    
    public View getView(final int n, final View view, final ViewGroup viewGroup) {
        if (view == null) {
            return (View)this.this$0.createTabView((ActionBar$Tab)this.getItem(n), true);
        }
        ((ScrollingTabContainerView$TabView)view).bindTab((ActionBar$Tab)this.getItem(n));
        return view;
    }
}
