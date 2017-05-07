// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.support.v4.view.ViewConfigurationCompat;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.VelocityTrackerCompat;
import android.os.Parcelable;
import android.view.ViewParent;
import android.view.FocusFinder;
import android.graphics.Canvas;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.ViewGroup$LayoutParams;
import android.support.v4.view.ViewCompat;
import android.view.ViewConfiguration;
import android.util.AttributeSet;
import android.content.Context;
import android.os.Build$VERSION;
import android.view.VelocityTracker;
import android.graphics.Rect;
import java.util.ArrayList;
import android.view.View;
import java.util.List;
import android.support.v4.widget.EdgeEffectCompat;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Interpolator;
import android.view.ViewGroup;

class RecyclerView$1 implements Runnable
{
    final /* synthetic */ RecyclerView this$0;
    
    RecyclerView$1(final RecyclerView this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        if (this.this$0.mFirstLayoutComplete) {
            if (this.this$0.mDataSetHasChangedAfterLayout) {
                this.this$0.dispatchLayout();
                return;
            }
            if (this.this$0.mAdapterHelper.hasPendingUpdates()) {
                this.this$0.eatRequestLayout();
                this.this$0.mAdapterHelper.preProcess();
                if (!this.this$0.mLayoutRequestEaten) {
                    this.this$0.rebindUpdatedViewHolders();
                }
                this.this$0.resumeRequestLayout(true);
            }
        }
    }
}
