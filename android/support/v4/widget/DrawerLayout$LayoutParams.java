// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.os.Parcelable;
import android.view.View$MeasureSpec;
import android.support.v4.view.KeyEventCompat;
import android.view.KeyEvent;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.GravityCompat;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.os.SystemClock;
import android.support.v4.view.ViewGroupCompat;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.view.View;
import android.support.v4.view.ViewCompat;
import android.os.Build$VERSION;
import android.graphics.drawable.Drawable;
import android.graphics.Paint;
import android.view.ViewGroup;
import android.view.ViewGroup$LayoutParams;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.content.Context;
import android.view.ViewGroup$MarginLayoutParams;

public class DrawerLayout$LayoutParams extends ViewGroup$MarginLayoutParams
{
    public int gravity;
    boolean isPeeking;
    boolean knownOpen;
    float onScreen;
    
    public DrawerLayout$LayoutParams(final int n, final int n2) {
        super(n, n2);
        this.gravity = 0;
    }
    
    public DrawerLayout$LayoutParams(final Context context, final AttributeSet set) {
        super(context, set);
        this.gravity = 0;
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, DrawerLayout.LAYOUT_ATTRS);
        this.gravity = obtainStyledAttributes.getInt(0, 0);
        obtainStyledAttributes.recycle();
    }
    
    public DrawerLayout$LayoutParams(final DrawerLayout$LayoutParams drawerLayout$LayoutParams) {
        super((ViewGroup$MarginLayoutParams)drawerLayout$LayoutParams);
        this.gravity = 0;
        this.gravity = drawerLayout$LayoutParams.gravity;
    }
    
    public DrawerLayout$LayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        super(viewGroup$LayoutParams);
        this.gravity = 0;
    }
    
    public DrawerLayout$LayoutParams(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
        super(viewGroup$MarginLayoutParams);
        this.gravity = 0;
    }
}
