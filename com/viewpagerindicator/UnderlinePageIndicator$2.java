// 
// Decompiled by Procyon v0.5.30
// 

package com.viewpagerindicator;

import android.annotation.SuppressLint;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.os.Parcelable;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.content.res.TypedArray;
import android.content.res.Resources;
import android.support.v4.view.ViewConfigurationCompat;
import android.view.ViewConfiguration;
import android.util.AttributeSet;
import android.content.Context;
import com.viewpagerindicator.android.osp.ViewPager;
import android.graphics.Paint;
import com.viewpagerindicator.android.osp.ViewPager$OnPageChangeListener;
import android.view.View;

class UnderlinePageIndicator$2 implements Runnable
{
    final /* synthetic */ UnderlinePageIndicator this$0;
    
    UnderlinePageIndicator$2(final UnderlinePageIndicator this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        if (this.this$0.mFades) {
            this.this$0.post(this.this$0.mFadeRunnable);
        }
    }
}
