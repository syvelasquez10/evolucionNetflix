// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.database.DataSetObserver;
import android.content.res.Resources$NotFoundException;
import android.view.View$MeasureSpec;
import android.view.ViewConfiguration;
import android.os.Build$VERSION;
import android.graphics.Canvas;
import android.view.accessibility.AccessibilityEvent;
import android.view.KeyEvent;
import android.os.SystemClock;
import android.view.SoundEffectConstants;
import android.view.FocusFinder;
import android.util.Log;
import android.view.ViewGroup$LayoutParams;
import java.util.List;
import java.util.Collections;
import android.view.MotionEvent;
import android.view.ViewParent;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.content.Context;
import android.view.VelocityTracker;
import android.graphics.Rect;
import java.lang.reflect.Method;
import android.widget.Scroller;
import android.os.Parcelable;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.EdgeEffectCompat;
import android.view.View;
import java.util.ArrayList;
import android.view.animation.Interpolator;
import java.util.Comparator;
import android.view.ViewGroup;

class ViewPager$3 implements Runnable
{
    final /* synthetic */ ViewPager this$0;
    
    ViewPager$3(final ViewPager this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        this.this$0.setScrollState(0);
        this.this$0.populate();
    }
}
