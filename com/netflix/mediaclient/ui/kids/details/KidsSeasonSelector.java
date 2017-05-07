// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.details;

import com.netflix.mediaclient.util.StringUtils;
import android.view.View$OnClickListener;
import android.widget.LinearLayout$LayoutParams;
import android.widget.TextView;
import com.netflix.mediaclient.servicemgr.model.details.SeasonDetails;
import java.util.ArrayList;
import android.view.MotionEvent;
import android.view.View$OnTouchListener;
import android.view.ViewGroup;
import com.netflix.mediaclient.util.DeviceUtils;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import com.netflix.mediaclient.Log;
import android.content.Context;
import android.widget.LinearLayout;
import android.widget.HorizontalScrollView;
import android.view.View;
import java.util.List;
import android.app.Activity;
import android.annotation.SuppressLint;
import android.widget.RelativeLayout;

@SuppressLint({ "ViewConstructor" })
public class KidsSeasonSelector extends RelativeLayout
{
    private static final String TAG = "KidsSeasonSelector";
    private final Activity activity;
    private final KidsShowDetailsAdapter adapter;
    private final int centerOffset;
    private final int halfItemWidth;
    private final List<View> itemViews;
    private final int itemWidth;
    private final int padViewWidth;
    private final HorizontalScrollView scroller;
    private final LinearLayout seasonsGroup;
    private final int selectedColor;
    private final int unselectedColor;
    
    public KidsSeasonSelector(final KidsShowDetailsFrag kidsShowDetailsFrag, final KidsShowDetailsAdapter adapter) {
        super((Context)kidsShowDetailsFrag.getActivity());
        this.activity = kidsShowDetailsFrag.getActivity();
        this.adapter = adapter;
        final List<SeasonDetails> seasons = this.adapter.getSeasons();
        Log.v("KidsSeasonSelector", "Creating Season Selector, num seasons: " + seasons.size());
        this.setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(-1, -2));
        this.selectedColor = this.getResources().getColor(2131296420);
        this.unselectedColor = this.getResources().getColor(2131296421);
        this.itemWidth = this.getResources().getDimensionPixelSize(2131361965);
        this.halfItemWidth = this.itemWidth / 2;
        this.centerOffset = (DeviceUtils.getScreenWidthInPixels((Context)this.activity) - this.itemWidth) / 2;
        this.padViewWidth = this.centerOffset;
        Log.v("KidsSeasonSelector", "Item width: " + this.itemWidth + ", centerOffset: " + this.centerOffset);
        this.activity.getLayoutInflater().inflate(2130903106, (ViewGroup)this);
        this.setBackgroundColor(this.activity.getResources().getColor(2131296419));
        this.seasonsGroup = (LinearLayout)this.findViewById(2131165428);
        (this.scroller = (HorizontalScrollView)this.findViewById(2131165427)).setHorizontalScrollBarEnabled(false);
        this.scroller.setOnTouchListener((View$OnTouchListener)new View$OnTouchListener() {
            public boolean onTouch(final View view, final MotionEvent motionEvent) {
                if (motionEvent.getAction() == 1) {
                    Log.v("KidsSeasonSelector", "onTouch, " + motionEvent);
                    KidsSeasonSelector.this.getCenteredChildView().performClick();
                }
                return false;
            }
        });
        this.itemViews = new ArrayList<View>(seasons.size());
        Log.v("KidsSeasonSelector", "Creating views, num: " + seasons.size());
        this.seasonsGroup.addView(this.createPaddingView());
        for (int i = 0; i < seasons.size(); ++i) {
            final View itemView = this.createItemView(seasons.get(i).getSeasonNumber(), i == 0);
            this.itemViews.add(itemView);
            this.seasonsGroup.addView(itemView);
        }
        this.seasonsGroup.addView(this.createPaddingView());
    }
    
    private View createItemView(final int n, final boolean b) {
        final TextView textView = (TextView)this.activity.getLayoutInflater().inflate(2130903107, (ViewGroup)null);
        textView.setLayoutParams((ViewGroup$LayoutParams)new LinearLayout$LayoutParams(this.itemWidth, -1));
        int textColor;
        if (b) {
            textColor = this.selectedColor;
        }
        else {
            textColor = this.unselectedColor;
        }
        textView.setTextColor(textColor);
        textView.setText((CharSequence)String.valueOf(n));
        textView.setTag((Object)n);
        textView.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                Log.v("KidsSeasonSelector", "Clicked on: " + (Object)textView.getText());
                KidsSeasonSelector.this.adapter.selectSeasonByNumber((int)textView.getTag());
            }
        });
        return (View)textView;
    }
    
    private View createPaddingView() {
        final View view = new View((Context)this.activity);
        view.setLayoutParams((ViewGroup$LayoutParams)new LinearLayout$LayoutParams(this.padViewWidth, -1));
        return view;
    }
    
    private View getCenteredChildView() {
        final int n = (int)Math.floor((this.scroller.getScrollX() + this.halfItemWidth) / this.itemWidth);
        final View view = this.itemViews.get(n);
        if (Log.isLoggable("KidsSeasonSelector", 2)) {
            Log.v("KidsSeasonSelector", "getCenteredChildView(), id: " + n + ", number: " + view.getTag());
        }
        return view;
    }
    
    protected void onAttachedToWindow() {
        Log.v("KidsSeasonSelector", "onAttachedToWindow,   " + this.hashCode());
        super.onAttachedToWindow();
    }
    
    protected void onDetachedFromWindow() {
        Log.v("KidsSeasonSelector", "onDetachedFromWindow, " + this.hashCode());
        super.onDetachedFromWindow();
    }
    
    public void setSeasonNumber(final int n, final boolean b) {
        Log.v("KidsSeasonSelector", "Setting curr season number: " + n);
        for (int i = 0; i < this.itemViews.size(); ++i) {
            final TextView textView = (TextView)this.itemViews.get(i);
            final boolean safeEquals = StringUtils.safeEquals((String)textView.getText(), String.valueOf(n));
            int textColor;
            if (safeEquals) {
                textColor = this.selectedColor;
            }
            else {
                textColor = this.unselectedColor;
            }
            textView.setTextColor(textColor);
            if (safeEquals) {
                final int n2 = this.itemWidth * i - this.centerOffset + this.padViewWidth;
                Log.v("KidsSeasonSelector", "Scrolling to: " + n2);
                this.scroller.post((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        if (b) {
                            KidsSeasonSelector.this.scroller.smoothScrollTo(n2, 0);
                            return;
                        }
                        KidsSeasonSelector.this.scroller.scrollTo(n2, 0);
                    }
                });
            }
        }
    }
}
