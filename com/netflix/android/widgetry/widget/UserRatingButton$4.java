// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.widgetry.widget;

import android.view.View$OnLongClickListener;
import android.view.View$OnClickListener;
import android.text.TextUtils;
import android.content.res.TypedArray;
import com.netflix.android.widgetry.R$color;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.content.ContextCompat;
import com.netflix.android.widgetry.R$drawable;
import com.netflix.android.widgetry.R$styleable;
import com.netflix.android.widgetry.R$id;
import android.view.ViewGroup;
import com.netflix.android.widgetry.R$layout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.TextView;
import android.widget.ImageView;
import android.content.res.ColorStateList;
import android.widget.FrameLayout;
import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import android.view.View$OnTouchListener;

class UserRatingButton$4 implements View$OnTouchListener
{
    final /* synthetic */ UserRatingButton this$0;
    
    UserRatingButton$4(final UserRatingButton this$0) {
        this.this$0 = this$0;
    }
    
    @SuppressLint({ "ClickableViewAccessibility" })
    public boolean onTouch(final View view, final MotionEvent motionEvent) {
        if (this.this$0.mUserRatingButtonOverlay != null && this.this$0.mUserRatingButtonOverlay.isShowing()) {
            this.this$0.mUserRatingButtonOverlay.onDragTouchEvent(motionEvent);
        }
        return false;
    }
}
