// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.os.Parcelable;
import android.view.View$MeasureSpec;
import android.support.v4.view.KeyEventCompat;
import android.view.KeyEvent;
import android.support.v4.view.MotionEventCompat;
import android.support.annotation.Nullable;
import android.view.ViewGroup$MarginLayoutParams;
import android.support.v4.view.GravityCompat;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.os.SystemClock;
import android.view.ViewGroup$LayoutParams;
import android.support.v4.view.ViewGroupCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.content.Context;
import android.os.Build$VERSION;
import android.graphics.drawable.Drawable;
import android.graphics.Paint;
import android.view.ViewGroup;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.View;
import android.support.v4.view.AccessibilityDelegateCompat;

final class DrawerLayout$ChildAccessibilityDelegate extends AccessibilityDelegateCompat
{
    final /* synthetic */ DrawerLayout this$0;
    
    DrawerLayout$ChildAccessibilityDelegate(final DrawerLayout this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onInitializeAccessibilityNodeInfo(final View view, final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
        if (!includeChildForAccessibility(view)) {
            accessibilityNodeInfoCompat.setParent(null);
        }
    }
}
