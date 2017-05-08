// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.widgetry.widget;

import android.view.View$OnTouchListener;
import android.view.View$OnLongClickListener;
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
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.TextView;
import android.widget.ImageView;
import android.content.res.ColorStateList;
import android.widget.FrameLayout;
import android.view.View;
import android.support.design.widget.CoordinatorLayout;
import android.view.View$OnClickListener;

class UserRatingButton$2 implements View$OnClickListener
{
    final /* synthetic */ UserRatingButton this$0;
    final /* synthetic */ int val$animationMultiplier;
    final /* synthetic */ CoordinatorLayout val$host;
    
    UserRatingButton$2(final UserRatingButton this$0, final CoordinatorLayout val$host, final int val$animationMultiplier) {
        this.this$0 = this$0;
        this.val$host = val$host;
        this.val$animationMultiplier = val$animationMultiplier;
    }
    
    public void onClick(final View view) {
        this.this$0.doAction(this.val$host, this.val$animationMultiplier, false);
    }
}
