// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.view.accessibility.AccessibilityNodeInfo;
import android.os.Build$VERSION;
import android.view.accessibility.AccessibilityEvent;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.graphics.Canvas;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.view.View$MeasureSpec;
import android.support.v7.appcompat.R$styleable;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;

public class LinearLayoutCompat extends ViewGroup
{
    public static final int HORIZONTAL = 0;
    private static final int INDEX_BOTTOM = 2;
    private static final int INDEX_CENTER_VERTICAL = 0;
    private static final int INDEX_FILL = 3;
    private static final int INDEX_TOP = 1;
    public static final int SHOW_DIVIDER_BEGINNING = 1;
    public static final int SHOW_DIVIDER_END = 4;
    public static final int SHOW_DIVIDER_MIDDLE = 2;
    public static final int SHOW_DIVIDER_NONE = 0;
    public static final int VERTICAL = 1;
    private static final int VERTICAL_GRAVITY_COUNT = 4;
    private boolean mBaselineAligned;
    private int mBaselineAlignedChildIndex;
    private int mBaselineChildTop;
    private Drawable mDivider;
    private int mDividerHeight;
    private int mDividerPadding;
    private int mDividerWidth;
    private int mGravity;
    private int[] mMaxAscent;
    private int[] mMaxDescent;
    private int mOrientation;
    private int mShowDividers;
    private int mTotalLength;
    private boolean mUseLargestChild;
    private float mWeightSum;
    
    public LinearLayoutCompat(final Context context) {
        this(context, null);
    }
    
    public LinearLayoutCompat(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public LinearLayoutCompat(final Context context, final AttributeSet set, int n) {
        super(context, set, n);
        this.mBaselineAligned = true;
        this.mBaselineAlignedChildIndex = -1;
        this.mBaselineChildTop = 0;
        this.mGravity = 8388659;
        final TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, set, R$styleable.LinearLayoutCompat, n, 0);
        n = obtainStyledAttributes.getInt(R$styleable.LinearLayoutCompat_android_orientation, -1);
        if (n >= 0) {
            this.setOrientation(n);
        }
        n = obtainStyledAttributes.getInt(R$styleable.LinearLayoutCompat_android_gravity, -1);
        if (n >= 0) {
            this.setGravity(n);
        }
        final boolean boolean1 = obtainStyledAttributes.getBoolean(R$styleable.LinearLayoutCompat_android_baselineAligned, true);
        if (!boolean1) {
            this.setBaselineAligned(boolean1);
        }
        this.mWeightSum = obtainStyledAttributes.getFloat(R$styleable.LinearLayoutCompat_android_weightSum, -1.0f);
        this.mBaselineAlignedChildIndex = obtainStyledAttributes.getInt(R$styleable.LinearLayoutCompat_android_baselineAlignedChildIndex, -1);
        this.mUseLargestChild = obtainStyledAttributes.getBoolean(R$styleable.LinearLayoutCompat_measureWithLargestChild, false);
        this.setDividerDrawable(obtainStyledAttributes.getDrawable(R$styleable.LinearLayoutCompat_divider));
        this.mShowDividers = obtainStyledAttributes.getInt(R$styleable.LinearLayoutCompat_showDividers, 0);
        this.mDividerPadding = obtainStyledAttributes.getDimensionPixelSize(R$styleable.LinearLayoutCompat_dividerPadding, 0);
        obtainStyledAttributes.recycle();
    }
    
    private void forceUniformHeight(final int n, final int n2) {
        final int measureSpec = View$MeasureSpec.makeMeasureSpec(this.getMeasuredHeight(), 1073741824);
        for (int i = 0; i < n; ++i) {
            final View virtualChild = this.getVirtualChildAt(i);
            if (virtualChild.getVisibility() != 8) {
                final LinearLayoutCompat$LayoutParams linearLayoutCompat$LayoutParams = (LinearLayoutCompat$LayoutParams)virtualChild.getLayoutParams();
                if (linearLayoutCompat$LayoutParams.height == -1) {
                    final int width = linearLayoutCompat$LayoutParams.width;
                    linearLayoutCompat$LayoutParams.width = virtualChild.getMeasuredWidth();
                    this.measureChildWithMargins(virtualChild, n2, 0, measureSpec, 0);
                    linearLayoutCompat$LayoutParams.width = width;
                }
            }
        }
    }
    
    private void forceUniformWidth(final int n, final int n2) {
        final int measureSpec = View$MeasureSpec.makeMeasureSpec(this.getMeasuredWidth(), 1073741824);
        for (int i = 0; i < n; ++i) {
            final View virtualChild = this.getVirtualChildAt(i);
            if (virtualChild.getVisibility() != 8) {
                final LinearLayoutCompat$LayoutParams linearLayoutCompat$LayoutParams = (LinearLayoutCompat$LayoutParams)virtualChild.getLayoutParams();
                if (linearLayoutCompat$LayoutParams.width == -1) {
                    final int height = linearLayoutCompat$LayoutParams.height;
                    linearLayoutCompat$LayoutParams.height = virtualChild.getMeasuredHeight();
                    this.measureChildWithMargins(virtualChild, measureSpec, 0, n2, 0);
                    linearLayoutCompat$LayoutParams.height = height;
                }
            }
        }
    }
    
    private void setChildFrame(final View view, final int n, final int n2, final int n3, final int n4) {
        view.layout(n, n2, n + n3, n2 + n4);
    }
    
    protected boolean checkLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        return viewGroup$LayoutParams instanceof LinearLayoutCompat$LayoutParams;
    }
    
    void drawDividersHorizontal(final Canvas canvas) {
        final int virtualChildCount = this.getVirtualChildCount();
        final boolean layoutRtl = ViewUtils.isLayoutRtl((View)this);
        for (int i = 0; i < virtualChildCount; ++i) {
            final View virtualChild = this.getVirtualChildAt(i);
            if (virtualChild != null && virtualChild.getVisibility() != 8 && this.hasDividerBeforeChildAt(i)) {
                final LinearLayoutCompat$LayoutParams linearLayoutCompat$LayoutParams = (LinearLayoutCompat$LayoutParams)virtualChild.getLayoutParams();
                int n;
                if (layoutRtl) {
                    n = linearLayoutCompat$LayoutParams.rightMargin + virtualChild.getRight();
                }
                else {
                    n = virtualChild.getLeft() - linearLayoutCompat$LayoutParams.leftMargin - this.mDividerWidth;
                }
                this.drawVerticalDivider(canvas, n);
            }
        }
        if (this.hasDividerBeforeChildAt(virtualChildCount)) {
            final View virtualChild2 = this.getVirtualChildAt(virtualChildCount - 1);
            int paddingLeft;
            if (virtualChild2 == null) {
                if (layoutRtl) {
                    paddingLeft = this.getPaddingLeft();
                }
                else {
                    paddingLeft = this.getWidth() - this.getPaddingRight() - this.mDividerWidth;
                }
            }
            else {
                final LinearLayoutCompat$LayoutParams linearLayoutCompat$LayoutParams2 = (LinearLayoutCompat$LayoutParams)virtualChild2.getLayoutParams();
                if (layoutRtl) {
                    paddingLeft = virtualChild2.getLeft() - linearLayoutCompat$LayoutParams2.leftMargin - this.mDividerWidth;
                }
                else {
                    paddingLeft = linearLayoutCompat$LayoutParams2.rightMargin + virtualChild2.getRight();
                }
            }
            this.drawVerticalDivider(canvas, paddingLeft);
        }
    }
    
    void drawDividersVertical(final Canvas canvas) {
        final int virtualChildCount = this.getVirtualChildCount();
        for (int i = 0; i < virtualChildCount; ++i) {
            final View virtualChild = this.getVirtualChildAt(i);
            if (virtualChild != null && virtualChild.getVisibility() != 8 && this.hasDividerBeforeChildAt(i)) {
                this.drawHorizontalDivider(canvas, virtualChild.getTop() - ((LinearLayoutCompat$LayoutParams)virtualChild.getLayoutParams()).topMargin - this.mDividerHeight);
            }
        }
        if (this.hasDividerBeforeChildAt(virtualChildCount)) {
            final View virtualChild2 = this.getVirtualChildAt(virtualChildCount - 1);
            int n;
            if (virtualChild2 == null) {
                n = this.getHeight() - this.getPaddingBottom() - this.mDividerHeight;
            }
            else {
                n = ((LinearLayoutCompat$LayoutParams)virtualChild2.getLayoutParams()).bottomMargin + virtualChild2.getBottom();
            }
            this.drawHorizontalDivider(canvas, n);
        }
    }
    
    void drawHorizontalDivider(final Canvas canvas, final int n) {
        this.mDivider.setBounds(this.getPaddingLeft() + this.mDividerPadding, n, this.getWidth() - this.getPaddingRight() - this.mDividerPadding, this.mDividerHeight + n);
        this.mDivider.draw(canvas);
    }
    
    void drawVerticalDivider(final Canvas canvas, final int n) {
        this.mDivider.setBounds(n, this.getPaddingTop() + this.mDividerPadding, this.mDividerWidth + n, this.getHeight() - this.getPaddingBottom() - this.mDividerPadding);
        this.mDivider.draw(canvas);
    }
    
    protected LinearLayoutCompat$LayoutParams generateDefaultLayoutParams() {
        if (this.mOrientation == 0) {
            return new LinearLayoutCompat$LayoutParams(-2, -2);
        }
        if (this.mOrientation == 1) {
            return new LinearLayoutCompat$LayoutParams(-1, -2);
        }
        return null;
    }
    
    public LinearLayoutCompat$LayoutParams generateLayoutParams(final AttributeSet set) {
        return new LinearLayoutCompat$LayoutParams(this.getContext(), set);
    }
    
    protected LinearLayoutCompat$LayoutParams generateLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        return new LinearLayoutCompat$LayoutParams(viewGroup$LayoutParams);
    }
    
    public int getBaseline() {
        int baseline = -1;
        if (this.mBaselineAlignedChildIndex < 0) {
            baseline = super.getBaseline();
        }
        else {
            if (this.getChildCount() <= this.mBaselineAlignedChildIndex) {
                throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
            }
            final View child = this.getChildAt(this.mBaselineAlignedChildIndex);
            final int baseline2 = child.getBaseline();
            if (baseline2 != -1) {
                int mBaselineChildTop = this.mBaselineChildTop;
                if (this.mOrientation == 1) {
                    final int n = this.mGravity & 0x70;
                    if (n != 48) {
                        switch (n) {
                            case 80: {
                                mBaselineChildTop = this.getBottom() - this.getTop() - this.getPaddingBottom() - this.mTotalLength;
                                break;
                            }
                            case 16: {
                                mBaselineChildTop += (this.getBottom() - this.getTop() - this.getPaddingTop() - this.getPaddingBottom() - this.mTotalLength) / 2;
                                break;
                            }
                        }
                    }
                }
                return ((LinearLayoutCompat$LayoutParams)child.getLayoutParams()).topMargin + mBaselineChildTop + baseline2;
            }
            if (this.mBaselineAlignedChildIndex != 0) {
                throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
            }
        }
        return baseline;
    }
    
    public int getBaselineAlignedChildIndex() {
        return this.mBaselineAlignedChildIndex;
    }
    
    int getChildrenSkipCount(final View view, final int n) {
        return 0;
    }
    
    public Drawable getDividerDrawable() {
        return this.mDivider;
    }
    
    public int getDividerPadding() {
        return this.mDividerPadding;
    }
    
    public int getDividerWidth() {
        return this.mDividerWidth;
    }
    
    public int getGravity() {
        return this.mGravity;
    }
    
    int getLocationOffset(final View view) {
        return 0;
    }
    
    int getNextLocationOffset(final View view) {
        return 0;
    }
    
    public int getOrientation() {
        return this.mOrientation;
    }
    
    public int getShowDividers() {
        return this.mShowDividers;
    }
    
    View getVirtualChildAt(final int n) {
        return this.getChildAt(n);
    }
    
    int getVirtualChildCount() {
        return this.getChildCount();
    }
    
    public float getWeightSum() {
        return this.mWeightSum;
    }
    
    protected boolean hasDividerBeforeChildAt(int i) {
        if (i == 0) {
            if ((this.mShowDividers & 0x1) == 0x0) {
                return false;
            }
        }
        else if (i == this.getChildCount()) {
            if ((this.mShowDividers & 0x4) == 0x0) {
                return false;
            }
        }
        else {
            if ((this.mShowDividers & 0x2) != 0x0) {
                for (--i; i >= 0; --i) {
                    if (this.getChildAt(i).getVisibility() != 8) {
                        return true;
                    }
                }
                return false;
            }
            return false;
        }
        return true;
    }
    
    public boolean isBaselineAligned() {
        return this.mBaselineAligned;
    }
    
    public boolean isMeasureWithLargestChildEnabled() {
        return this.mUseLargestChild;
    }
    
    void layoutHorizontal(int n, int i, int n2, int n3) {
        final boolean layoutRtl = ViewUtils.isLayoutRtl((View)this);
        final int paddingTop = this.getPaddingTop();
        final int n4 = n3 - i;
        final int paddingBottom = this.getPaddingBottom();
        final int paddingBottom2 = this.getPaddingBottom();
        final int virtualChildCount = this.getVirtualChildCount();
        i = this.mGravity;
        final int mGravity = this.mGravity;
        final boolean mBaselineAligned = this.mBaselineAligned;
        final int[] mMaxAscent = this.mMaxAscent;
        final int[] mMaxDescent = this.mMaxDescent;
        switch (GravityCompat.getAbsoluteGravity(i & 0x800007, ViewCompat.getLayoutDirection((View)this))) {
            default: {
                n = this.getPaddingLeft();
                break;
            }
            case 5: {
                n = this.getPaddingLeft() + n2 - n - this.mTotalLength;
                break;
            }
            case 1: {
                n = this.getPaddingLeft() + (n2 - n - this.mTotalLength) / 2;
                break;
            }
        }
        int n5;
        if (layoutRtl) {
            n3 = -1;
            n5 = virtualChildCount - 1;
        }
        else {
            n3 = 1;
            n5 = 0;
        }
        i = 0;
        n2 = n;
        while (i < virtualChildCount) {
            final int n6 = n5 + n3 * i;
            final View virtualChild = this.getVirtualChildAt(n6);
            if (virtualChild == null) {
                n2 += this.measureNullChild(n6);
                n = i;
            }
            else if (virtualChild.getVisibility() != 8) {
                final int measuredWidth = virtualChild.getMeasuredWidth();
                final int measuredHeight = virtualChild.getMeasuredHeight();
                final LinearLayoutCompat$LayoutParams linearLayoutCompat$LayoutParams = (LinearLayoutCompat$LayoutParams)virtualChild.getLayoutParams();
                int baseline;
                if (mBaselineAligned && linearLayoutCompat$LayoutParams.height != -1) {
                    baseline = virtualChild.getBaseline();
                }
                else {
                    baseline = -1;
                }
                if ((n = linearLayoutCompat$LayoutParams.gravity) < 0) {
                    n = (mGravity & 0x70);
                }
                switch (n & 0x70) {
                    default: {
                        n = paddingTop;
                        break;
                    }
                    case 48: {
                        final int n7 = n = paddingTop + linearLayoutCompat$LayoutParams.topMargin;
                        if (baseline != -1) {
                            n = n7 + (mMaxAscent[1] - baseline);
                            break;
                        }
                        break;
                    }
                    case 16: {
                        n = (n4 - paddingTop - paddingBottom2 - measuredHeight) / 2 + paddingTop + linearLayoutCompat$LayoutParams.topMargin - linearLayoutCompat$LayoutParams.bottomMargin;
                        break;
                    }
                    case 80: {
                        final int n8 = n = n4 - paddingBottom - measuredHeight - linearLayoutCompat$LayoutParams.bottomMargin;
                        if (baseline != -1) {
                            n = virtualChild.getMeasuredHeight();
                            n = n8 - (mMaxDescent[2] - (n - baseline));
                            break;
                        }
                        break;
                    }
                }
                if (this.hasDividerBeforeChildAt(n6)) {
                    n2 += this.mDividerWidth;
                }
                n2 += linearLayoutCompat$LayoutParams.leftMargin;
                this.setChildFrame(virtualChild, n2 + this.getLocationOffset(virtualChild), n, measuredWidth, measuredHeight);
                n2 += linearLayoutCompat$LayoutParams.rightMargin + measuredWidth + this.getNextLocationOffset(virtualChild);
                n = this.getChildrenSkipCount(virtualChild, n6) + i;
            }
            else {
                n = i;
            }
            i = n + 1;
        }
    }
    
    void layoutVertical(int i, int n, int n2, int gravity) {
        final int paddingLeft = this.getPaddingLeft();
        final int n3 = n2 - i;
        final int paddingRight = this.getPaddingRight();
        final int paddingRight2 = this.getPaddingRight();
        final int virtualChildCount = this.getVirtualChildCount();
        i = this.mGravity;
        final int mGravity = this.mGravity;
        switch (i & 0x70) {
            default: {
                i = this.getPaddingTop();
                break;
            }
            case 80: {
                i = this.getPaddingTop() + gravity - n - this.mTotalLength;
                break;
            }
            case 16: {
                i = this.getPaddingTop() + (gravity - n - this.mTotalLength) / 2;
                break;
            }
        }
        n2 = 0;
        n = i;
        View virtualChild;
        int measuredWidth;
        int measuredHeight;
        LinearLayoutCompat$LayoutParams linearLayoutCompat$LayoutParams;
        for (i = n2; i < virtualChildCount; ++i) {
            virtualChild = this.getVirtualChildAt(i);
            if (virtualChild == null) {
                n += this.measureNullChild(i);
            }
            else if (virtualChild.getVisibility() != 8) {
                measuredWidth = virtualChild.getMeasuredWidth();
                measuredHeight = virtualChild.getMeasuredHeight();
                linearLayoutCompat$LayoutParams = (LinearLayoutCompat$LayoutParams)virtualChild.getLayoutParams();
                gravity = linearLayoutCompat$LayoutParams.gravity;
                if ((n2 = gravity) < 0) {
                    n2 = (mGravity & 0x800007);
                }
                switch (GravityCompat.getAbsoluteGravity(n2, ViewCompat.getLayoutDirection((View)this)) & 0x7) {
                    default: {
                        n2 = paddingLeft + linearLayoutCompat$LayoutParams.leftMargin;
                        break;
                    }
                    case 1: {
                        n2 = (n3 - paddingLeft - paddingRight2 - measuredWidth) / 2 + paddingLeft + linearLayoutCompat$LayoutParams.leftMargin - linearLayoutCompat$LayoutParams.rightMargin;
                        break;
                    }
                    case 5: {
                        n2 = n3 - paddingRight - measuredWidth - linearLayoutCompat$LayoutParams.rightMargin;
                        break;
                    }
                }
                if (this.hasDividerBeforeChildAt(i)) {
                    n += this.mDividerHeight;
                }
                n += linearLayoutCompat$LayoutParams.topMargin;
                this.setChildFrame(virtualChild, n2, n + this.getLocationOffset(virtualChild), measuredWidth, measuredHeight);
                n += linearLayoutCompat$LayoutParams.bottomMargin + measuredHeight + this.getNextLocationOffset(virtualChild);
                i += this.getChildrenSkipCount(virtualChild, i);
            }
        }
    }
    
    void measureChildBeforeLayout(final View view, final int n, final int n2, final int n3, final int n4, final int n5) {
        this.measureChildWithMargins(view, n2, n3, n4, n5);
    }
    
    void measureHorizontal(final int n, final int n2) {
        this.mTotalLength = 0;
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        int n7 = 1;
        float mWeightSum = 0.0f;
        final int virtualChildCount = this.getVirtualChildCount();
        final int mode = View$MeasureSpec.getMode(n);
        final int mode2 = View$MeasureSpec.getMode(n2);
        int n8 = 0;
        int n9 = 0;
        if (this.mMaxAscent == null || this.mMaxDescent == null) {
            this.mMaxAscent = new int[4];
            this.mMaxDescent = new int[4];
        }
        final int[] mMaxAscent = this.mMaxAscent;
        final int[] mMaxDescent = this.mMaxDescent;
        mMaxAscent[2] = (mMaxAscent[3] = -1);
        mMaxAscent[0] = (mMaxAscent[1] = -1);
        mMaxDescent[2] = (mMaxDescent[3] = -1);
        mMaxDescent[0] = (mMaxDescent[1] = -1);
        final boolean mBaselineAligned = this.mBaselineAligned;
        final boolean mUseLargestChild = this.mUseLargestChild;
        final boolean b = mode == 1073741824;
        int n10 = Integer.MIN_VALUE;
        int n13;
        int n14;
        int n15;
        int n16;
        int n17;
        int n32;
        int n33;
        for (int i = 0; i < virtualChildCount; ++i, n32 = n14, n33 = n13, n10 = n17, n9 = n16, n7 = n15, n4 = n32, n3 = n33) {
            final View virtualChild = this.getVirtualChildAt(i);
            if (virtualChild == null) {
                this.mTotalLength += this.measureNullChild(i);
                final int n11 = n10;
                final int n12 = n9;
                n13 = n3;
                n14 = n4;
                n15 = n7;
                n16 = n12;
                n17 = n11;
            }
            else if (virtualChild.getVisibility() == 8) {
                final int n18 = i + this.getChildrenSkipCount(virtualChild, i);
                final int n19 = n10;
                final int n20 = n9;
                final int n21 = n7;
                final int n22 = n4;
                final int n23 = n3;
                n17 = n19;
                n16 = n20;
                i = n18;
                n15 = n21;
                n14 = n22;
                n13 = n23;
            }
            else {
                if (this.hasDividerBeforeChildAt(i)) {
                    this.mTotalLength += this.mDividerWidth;
                }
                final LinearLayoutCompat$LayoutParams linearLayoutCompat$LayoutParams = (LinearLayoutCompat$LayoutParams)virtualChild.getLayoutParams();
                mWeightSum += linearLayoutCompat$LayoutParams.weight;
                int n24;
                int max;
                if (mode == 1073741824 && linearLayoutCompat$LayoutParams.width == 0 && linearLayoutCompat$LayoutParams.weight > 0.0f) {
                    if (b) {
                        this.mTotalLength += linearLayoutCompat$LayoutParams.leftMargin + linearLayoutCompat$LayoutParams.rightMargin;
                    }
                    else {
                        final int mTotalLength = this.mTotalLength;
                        this.mTotalLength = Math.max(mTotalLength, linearLayoutCompat$LayoutParams.leftMargin + mTotalLength + linearLayoutCompat$LayoutParams.rightMargin);
                    }
                    if (mBaselineAligned) {
                        final int measureSpec = View$MeasureSpec.makeMeasureSpec(0, 0);
                        virtualChild.measure(measureSpec, measureSpec);
                        n24 = n9;
                        max = n10;
                    }
                    else {
                        n24 = 1;
                        max = n10;
                    }
                }
                else {
                    int width;
                    final int n25 = width = Integer.MIN_VALUE;
                    if (linearLayoutCompat$LayoutParams.width == 0) {
                        width = n25;
                        if (linearLayoutCompat$LayoutParams.weight > 0.0f) {
                            width = 0;
                            linearLayoutCompat$LayoutParams.width = -2;
                        }
                    }
                    int mTotalLength2;
                    if (mWeightSum == 0.0f) {
                        mTotalLength2 = this.mTotalLength;
                    }
                    else {
                        mTotalLength2 = 0;
                    }
                    this.measureChildBeforeLayout(virtualChild, i, n, mTotalLength2, n2, 0);
                    if (width != Integer.MIN_VALUE) {
                        linearLayoutCompat$LayoutParams.width = width;
                    }
                    final int measuredWidth = virtualChild.getMeasuredWidth();
                    if (b) {
                        this.mTotalLength += linearLayoutCompat$LayoutParams.leftMargin + measuredWidth + linearLayoutCompat$LayoutParams.rightMargin + this.getNextLocationOffset(virtualChild);
                    }
                    else {
                        final int mTotalLength3 = this.mTotalLength;
                        this.mTotalLength = Math.max(mTotalLength3, mTotalLength3 + measuredWidth + linearLayoutCompat$LayoutParams.leftMargin + linearLayoutCompat$LayoutParams.rightMargin + this.getNextLocationOffset(virtualChild));
                    }
                    max = n10;
                    n24 = n9;
                    if (mUseLargestChild) {
                        max = Math.max(measuredWidth, n10);
                        n24 = n9;
                    }
                }
                boolean b2 = false;
                int n26;
                if (mode2 != 1073741824 && linearLayoutCompat$LayoutParams.height == -1) {
                    n26 = 1;
                    b2 = true;
                }
                else {
                    n26 = n8;
                }
                int n27 = linearLayoutCompat$LayoutParams.bottomMargin + linearLayoutCompat$LayoutParams.topMargin;
                final int n28 = virtualChild.getMeasuredHeight() + n27;
                final int combineMeasuredStates = ViewUtils.combineMeasuredStates(n4, ViewCompat.getMeasuredState(virtualChild));
                if (mBaselineAligned) {
                    final int baseline = virtualChild.getBaseline();
                    if (baseline != -1) {
                        int n29;
                        if (linearLayoutCompat$LayoutParams.gravity < 0) {
                            n29 = this.mGravity;
                        }
                        else {
                            n29 = linearLayoutCompat$LayoutParams.gravity;
                        }
                        final int n30 = ((n29 & 0x70) >> 4 & 0xFFFFFFFE) >> 1;
                        mMaxAscent[n30] = Math.max(mMaxAscent[n30], baseline);
                        mMaxDescent[n30] = Math.max(mMaxDescent[n30], n28 - baseline);
                    }
                }
                final int max2 = Math.max(n3, n28);
                if (n7 != 0 && linearLayoutCompat$LayoutParams.height == -1) {
                    n15 = 1;
                }
                else {
                    n15 = 0;
                }
                int max3;
                int max4;
                if (linearLayoutCompat$LayoutParams.weight > 0.0f) {
                    if (!b2) {
                        n27 = n28;
                    }
                    max3 = Math.max(n6, n27);
                    max4 = n5;
                }
                else {
                    if (!b2) {
                        n27 = n28;
                    }
                    max4 = Math.max(n5, n27);
                    max3 = n6;
                }
                i += this.getChildrenSkipCount(virtualChild, i);
                n6 = max3;
                n5 = max4;
                n13 = max2;
                n17 = max;
                final int n31 = combineMeasuredStates;
                n8 = n26;
                n16 = n24;
                n14 = n31;
            }
        }
        if (this.mTotalLength > 0 && this.hasDividerBeforeChildAt(virtualChildCount)) {
            this.mTotalLength += this.mDividerWidth;
        }
        int max5;
        if (mMaxAscent[1] != -1 || mMaxAscent[0] != -1 || mMaxAscent[2] != -1 || mMaxAscent[3] != -1) {
            max5 = Math.max(n3, Math.max(mMaxAscent[3], Math.max(mMaxAscent[0], Math.max(mMaxAscent[1], mMaxAscent[2]))) + Math.max(mMaxDescent[3], Math.max(mMaxDescent[0], Math.max(mMaxDescent[1], mMaxDescent[2]))));
        }
        else {
            max5 = n3;
        }
        if (mUseLargestChild && (mode == Integer.MIN_VALUE || mode == 0)) {
            this.mTotalLength = 0;
            for (int j = 0; j < virtualChildCount; ++j) {
                final View virtualChild2 = this.getVirtualChildAt(j);
                if (virtualChild2 == null) {
                    this.mTotalLength += this.measureNullChild(j);
                }
                else if (virtualChild2.getVisibility() == 8) {
                    j += this.getChildrenSkipCount(virtualChild2, j);
                }
                else {
                    final LinearLayoutCompat$LayoutParams linearLayoutCompat$LayoutParams2 = (LinearLayoutCompat$LayoutParams)virtualChild2.getLayoutParams();
                    if (b) {
                        this.mTotalLength += linearLayoutCompat$LayoutParams2.rightMargin + (linearLayoutCompat$LayoutParams2.leftMargin + n10) + this.getNextLocationOffset(virtualChild2);
                    }
                    else {
                        final int mTotalLength4 = this.mTotalLength;
                        this.mTotalLength = Math.max(mTotalLength4, linearLayoutCompat$LayoutParams2.rightMargin + (mTotalLength4 + n10 + linearLayoutCompat$LayoutParams2.leftMargin) + this.getNextLocationOffset(virtualChild2));
                    }
                }
            }
        }
        this.mTotalLength += this.getPaddingLeft() + this.getPaddingRight();
        final int resolveSizeAndState = ViewCompat.resolveSizeAndState(Math.max(this.mTotalLength, this.getSuggestedMinimumWidth()), n, 0);
        final int n34 = (0xFFFFFF & resolveSizeAndState) - this.mTotalLength;
        int max8;
        int n59;
        int n60;
        int n61;
        if (n9 != 0 || (n34 != 0 && mWeightSum > 0.0f)) {
            if (this.mWeightSum > 0.0f) {
                mWeightSum = this.mWeightSum;
            }
            mMaxAscent[2] = (mMaxAscent[3] = -1);
            mMaxAscent[0] = (mMaxAscent[1] = -1);
            mMaxDescent[2] = (mMaxDescent[3] = -1);
            mMaxDescent[0] = (mMaxDescent[1] = -1);
            this.mTotalLength = 0;
            final int n35 = 0;
            final int n36 = n5;
            int combineMeasuredStates2 = n4;
            int n37 = n34;
            int n38 = -1;
            int k = n35;
            int n39 = n36;
            while (k < virtualChildCount) {
                final View virtualChild3 = this.getVirtualChildAt(k);
                int n41;
                int max6;
                int n42;
                int n43;
                if (virtualChild3 != null) {
                    if (virtualChild3.getVisibility() == 8) {
                        final int n40 = n37;
                        n41 = n38;
                        max6 = n39;
                        n42 = n7;
                        n43 = n40;
                    }
                    else {
                        final LinearLayoutCompat$LayoutParams linearLayoutCompat$LayoutParams3 = (LinearLayoutCompat$LayoutParams)virtualChild3.getLayoutParams();
                        final float weight = linearLayoutCompat$LayoutParams3.weight;
                        if (weight > 0.0f) {
                            final int n44 = (int)(n37 * weight / mWeightSum);
                            final int n45 = n37 - n44;
                            final int childMeasureSpec = getChildMeasureSpec(n2, this.getPaddingTop() + this.getPaddingBottom() + linearLayoutCompat$LayoutParams3.topMargin + linearLayoutCompat$LayoutParams3.bottomMargin, linearLayoutCompat$LayoutParams3.height);
                            if (linearLayoutCompat$LayoutParams3.width != 0 || mode != 1073741824) {
                                int n46;
                                if ((n46 = n44 + virtualChild3.getMeasuredWidth()) < 0) {
                                    n46 = 0;
                                }
                                virtualChild3.measure(View$MeasureSpec.makeMeasureSpec(n46, 1073741824), childMeasureSpec);
                            }
                            else {
                                int n47;
                                if (n44 > 0) {
                                    n47 = n44;
                                }
                                else {
                                    n47 = 0;
                                }
                                virtualChild3.measure(View$MeasureSpec.makeMeasureSpec(n47, 1073741824), childMeasureSpec);
                            }
                            combineMeasuredStates2 = ViewUtils.combineMeasuredStates(combineMeasuredStates2, ViewCompat.getMeasuredState(virtualChild3) & 0xFF000000);
                            mWeightSum -= weight;
                            n37 = n45;
                        }
                        if (b) {
                            this.mTotalLength += virtualChild3.getMeasuredWidth() + linearLayoutCompat$LayoutParams3.leftMargin + linearLayoutCompat$LayoutParams3.rightMargin + this.getNextLocationOffset(virtualChild3);
                        }
                        else {
                            final int mTotalLength5 = this.mTotalLength;
                            this.mTotalLength = Math.max(mTotalLength5, virtualChild3.getMeasuredWidth() + mTotalLength5 + linearLayoutCompat$LayoutParams3.leftMargin + linearLayoutCompat$LayoutParams3.rightMargin + this.getNextLocationOffset(virtualChild3));
                        }
                        int n48;
                        if (mode2 != 1073741824 && linearLayoutCompat$LayoutParams3.height == -1) {
                            n48 = 1;
                        }
                        else {
                            n48 = 0;
                        }
                        final int n49 = linearLayoutCompat$LayoutParams3.topMargin + linearLayoutCompat$LayoutParams3.bottomMargin;
                        final int n50 = virtualChild3.getMeasuredHeight() + n49;
                        final int max7 = Math.max(n38, n50);
                        int n51;
                        if (n48 != 0) {
                            n51 = n49;
                        }
                        else {
                            n51 = n50;
                        }
                        max6 = Math.max(n39, n51);
                        int n52;
                        if (n7 != 0 && linearLayoutCompat$LayoutParams3.height == -1) {
                            n52 = 1;
                        }
                        else {
                            n52 = 0;
                        }
                        if (mBaselineAligned) {
                            final int baseline2 = virtualChild3.getBaseline();
                            if (baseline2 != -1) {
                                int n53;
                                if (linearLayoutCompat$LayoutParams3.gravity < 0) {
                                    n53 = this.mGravity;
                                }
                                else {
                                    n53 = linearLayoutCompat$LayoutParams3.gravity;
                                }
                                final int n54 = ((n53 & 0x70) >> 4 & 0xFFFFFFFE) >> 1;
                                mMaxAscent[n54] = Math.max(mMaxAscent[n54], baseline2);
                                mMaxDescent[n54] = Math.max(mMaxDescent[n54], n50 - baseline2);
                            }
                        }
                        n42 = n52;
                        n43 = n37;
                        n41 = max7;
                    }
                }
                else {
                    final int n55 = n37;
                    n41 = n38;
                    max6 = n39;
                    n42 = n7;
                    n43 = n55;
                }
                final int n56 = k + 1;
                final int n57 = n41;
                n37 = n43;
                n7 = n42;
                n39 = max6;
                n38 = n57;
                k = n56;
            }
            this.mTotalLength += this.getPaddingLeft() + this.getPaddingRight();
            Label_2125: {
                if (mMaxAscent[1] == -1 && mMaxAscent[0] == -1 && mMaxAscent[2] == -1) {
                    max8 = n38;
                    if (mMaxAscent[3] == -1) {
                        break Label_2125;
                    }
                }
                max8 = Math.max(n38, Math.max(mMaxAscent[3], Math.max(mMaxAscent[0], Math.max(mMaxAscent[1], mMaxAscent[2]))) + Math.max(mMaxDescent[3], Math.max(mMaxDescent[0], Math.max(mMaxDescent[1], mMaxDescent[2]))));
            }
            final int n58 = n7;
            n59 = combineMeasuredStates2;
            n60 = n58;
            n61 = n39;
        }
        else {
            final int max9 = Math.max(n5, n6);
            if (mUseLargestChild && mode != 1073741824) {
                for (int l = 0; l < virtualChildCount; ++l) {
                    final View virtualChild4 = this.getVirtualChildAt(l);
                    if (virtualChild4 != null && virtualChild4.getVisibility() != 8 && ((LinearLayoutCompat$LayoutParams)virtualChild4.getLayoutParams()).weight > 0.0f) {
                        virtualChild4.measure(View$MeasureSpec.makeMeasureSpec(n10, 1073741824), View$MeasureSpec.makeMeasureSpec(virtualChild4.getMeasuredHeight(), 1073741824));
                    }
                }
            }
            n61 = max9;
            max8 = max5;
            n60 = n7;
            n59 = n4;
        }
        if (n60 != 0 || mode2 == 1073741824) {
            n61 = max8;
        }
        this.setMeasuredDimension((0xFF000000 & n59) | resolveSizeAndState, ViewCompat.resolveSizeAndState(Math.max(n61 + (this.getPaddingTop() + this.getPaddingBottom()), this.getSuggestedMinimumHeight()), n2, n59 << 16));
        if (n8 != 0) {
            this.forceUniformHeight(virtualChildCount, n);
        }
    }
    
    int measureNullChild(final int n) {
        return 0;
    }
    
    void measureVertical(final int n, final int n2) {
        this.mTotalLength = 0;
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        int n7 = 1;
        float mWeightSum = 0.0f;
        final int virtualChildCount = this.getVirtualChildCount();
        final int mode = View$MeasureSpec.getMode(n);
        final int mode2 = View$MeasureSpec.getMode(n2);
        int n8 = 0;
        int n9 = 0;
        final int mBaselineAlignedChildIndex = this.mBaselineAlignedChildIndex;
        final boolean mUseLargestChild = this.mUseLargestChild;
        int n10 = Integer.MIN_VALUE;
        int n13;
        int n14;
        int n15;
        int n16;
        int n26;
        int n27;
        for (int i = 0; i < virtualChildCount; ++i, n26 = n14, n27 = n13, n10 = n16, n9 = n15, n4 = n26, n3 = n27) {
            final View virtualChild = this.getVirtualChildAt(i);
            if (virtualChild == null) {
                this.mTotalLength += this.measureNullChild(i);
                final int n11 = n10;
                final int n12 = n9;
                n13 = n3;
                n14 = n4;
                n15 = n12;
                n16 = n11;
            }
            else if (virtualChild.getVisibility() == 8) {
                final int n17 = i + this.getChildrenSkipCount(virtualChild, i);
                final int n18 = n10;
                final int n19 = n9;
                n14 = n4;
                n13 = n3;
                n16 = n18;
                n15 = n19;
                i = n17;
            }
            else {
                if (this.hasDividerBeforeChildAt(i)) {
                    this.mTotalLength += this.mDividerHeight;
                }
                final LinearLayoutCompat$LayoutParams linearLayoutCompat$LayoutParams = (LinearLayoutCompat$LayoutParams)virtualChild.getLayoutParams();
                mWeightSum += linearLayoutCompat$LayoutParams.weight;
                int n20;
                int max;
                if (mode2 == 1073741824 && linearLayoutCompat$LayoutParams.height == 0 && linearLayoutCompat$LayoutParams.weight > 0.0f) {
                    final int mTotalLength = this.mTotalLength;
                    this.mTotalLength = Math.max(mTotalLength, linearLayoutCompat$LayoutParams.topMargin + mTotalLength + linearLayoutCompat$LayoutParams.bottomMargin);
                    n20 = 1;
                    max = n10;
                }
                else {
                    int height;
                    final int n21 = height = Integer.MIN_VALUE;
                    if (linearLayoutCompat$LayoutParams.height == 0) {
                        height = n21;
                        if (linearLayoutCompat$LayoutParams.weight > 0.0f) {
                            height = 0;
                            linearLayoutCompat$LayoutParams.height = -2;
                        }
                    }
                    int mTotalLength2;
                    if (mWeightSum == 0.0f) {
                        mTotalLength2 = this.mTotalLength;
                    }
                    else {
                        mTotalLength2 = 0;
                    }
                    this.measureChildBeforeLayout(virtualChild, i, n, 0, n2, mTotalLength2);
                    if (height != Integer.MIN_VALUE) {
                        linearLayoutCompat$LayoutParams.height = height;
                    }
                    final int measuredHeight = virtualChild.getMeasuredHeight();
                    final int mTotalLength3 = this.mTotalLength;
                    this.mTotalLength = Math.max(mTotalLength3, mTotalLength3 + measuredHeight + linearLayoutCompat$LayoutParams.topMargin + linearLayoutCompat$LayoutParams.bottomMargin + this.getNextLocationOffset(virtualChild));
                    max = n10;
                    n20 = n9;
                    if (mUseLargestChild) {
                        max = Math.max(measuredHeight, n10);
                        n20 = n9;
                    }
                }
                if (mBaselineAlignedChildIndex >= 0 && mBaselineAlignedChildIndex == i + 1) {
                    this.mBaselineChildTop = this.mTotalLength;
                }
                if (i < mBaselineAlignedChildIndex && linearLayoutCompat$LayoutParams.weight > 0.0f) {
                    throw new RuntimeException("A child of LinearLayout with index less than mBaselineAlignedChildIndex has weight > 0, which won't work.  Either remove the weight, or don't set mBaselineAlignedChildIndex.");
                }
                boolean b = false;
                int n22;
                if (mode != 1073741824 && linearLayoutCompat$LayoutParams.width == -1) {
                    n22 = 1;
                    b = true;
                }
                else {
                    n22 = n8;
                }
                int n23 = linearLayoutCompat$LayoutParams.rightMargin + linearLayoutCompat$LayoutParams.leftMargin;
                final int n24 = virtualChild.getMeasuredWidth() + n23;
                final int max2 = Math.max(n3, n24);
                final int combineMeasuredStates = ViewUtils.combineMeasuredStates(n4, ViewCompat.getMeasuredState(virtualChild));
                if (n7 != 0 && linearLayoutCompat$LayoutParams.width == -1) {
                    n7 = 1;
                }
                else {
                    n7 = 0;
                }
                int max3;
                int max4;
                if (linearLayoutCompat$LayoutParams.weight > 0.0f) {
                    if (!b) {
                        n23 = n24;
                    }
                    max3 = Math.max(n6, n23);
                    max4 = n5;
                }
                else {
                    if (!b) {
                        n23 = n24;
                    }
                    max4 = Math.max(n5, n23);
                    max3 = n6;
                }
                i += this.getChildrenSkipCount(virtualChild, i);
                n6 = max3;
                n5 = max4;
                n13 = max2;
                n16 = max;
                final int n25 = combineMeasuredStates;
                n8 = n22;
                n15 = n20;
                n14 = n25;
            }
        }
        if (this.mTotalLength > 0 && this.hasDividerBeforeChildAt(virtualChildCount)) {
            this.mTotalLength += this.mDividerHeight;
        }
        if (mUseLargestChild && (mode2 == Integer.MIN_VALUE || mode2 == 0)) {
            this.mTotalLength = 0;
            for (int j = 0; j < virtualChildCount; ++j) {
                final View virtualChild2 = this.getVirtualChildAt(j);
                if (virtualChild2 == null) {
                    this.mTotalLength += this.measureNullChild(j);
                }
                else if (virtualChild2.getVisibility() == 8) {
                    j += this.getChildrenSkipCount(virtualChild2, j);
                }
                else {
                    final LinearLayoutCompat$LayoutParams linearLayoutCompat$LayoutParams2 = (LinearLayoutCompat$LayoutParams)virtualChild2.getLayoutParams();
                    final int mTotalLength4 = this.mTotalLength;
                    this.mTotalLength = Math.max(mTotalLength4, linearLayoutCompat$LayoutParams2.bottomMargin + (mTotalLength4 + n10 + linearLayoutCompat$LayoutParams2.topMargin) + this.getNextLocationOffset(virtualChild2));
                }
            }
        }
        this.mTotalLength += this.getPaddingTop() + this.getPaddingBottom();
        final int resolveSizeAndState = ViewCompat.resolveSizeAndState(Math.max(this.mTotalLength, this.getSuggestedMinimumHeight()), n2, 0);
        final int n28 = (0xFFFFFF & resolveSizeAndState) - this.mTotalLength;
        int n33;
        int n52;
        if (n9 != 0 || (n28 != 0 && mWeightSum > 0.0f)) {
            if (this.mWeightSum > 0.0f) {
                mWeightSum = this.mWeightSum;
            }
            this.mTotalLength = 0;
            final int n29 = 0;
            final int n30 = n7;
            int n31 = n5;
            final int n32 = n28;
            int k = n29;
            n33 = n3;
            int n34 = n30;
            int n35 = n32;
            while (k < virtualChildCount) {
                final View virtualChild3 = this.getVirtualChildAt(k);
                int n37;
                int max5;
                if (virtualChild3.getVisibility() == 8) {
                    final int n36 = n31;
                    n37 = n33;
                    max5 = n36;
                }
                else {
                    final LinearLayoutCompat$LayoutParams linearLayoutCompat$LayoutParams3 = (LinearLayoutCompat$LayoutParams)virtualChild3.getLayoutParams();
                    final float weight = linearLayoutCompat$LayoutParams3.weight;
                    int n42;
                    int n43;
                    if (weight > 0.0f) {
                        final int n38 = (int)(n35 * weight / mWeightSum);
                        final int childMeasureSpec = getChildMeasureSpec(n, this.getPaddingLeft() + this.getPaddingRight() + linearLayoutCompat$LayoutParams3.leftMargin + linearLayoutCompat$LayoutParams3.rightMargin, linearLayoutCompat$LayoutParams3.width);
                        if (linearLayoutCompat$LayoutParams3.height != 0 || mode2 != 1073741824) {
                            int n39;
                            if ((n39 = n38 + virtualChild3.getMeasuredHeight()) < 0) {
                                n39 = 0;
                            }
                            virtualChild3.measure(childMeasureSpec, View$MeasureSpec.makeMeasureSpec(n39, 1073741824));
                        }
                        else {
                            int n40;
                            if (n38 > 0) {
                                n40 = n38;
                            }
                            else {
                                n40 = 0;
                            }
                            virtualChild3.measure(childMeasureSpec, View$MeasureSpec.makeMeasureSpec(n40, 1073741824));
                        }
                        final int combineMeasuredStates2 = ViewUtils.combineMeasuredStates(n4, ViewCompat.getMeasuredState(virtualChild3) & 0xFFFFFF00);
                        final int n41 = n35 - n38;
                        n42 = combineMeasuredStates2;
                        mWeightSum -= weight;
                        n43 = n41;
                    }
                    else {
                        final int n44 = n4;
                        n43 = n35;
                        n42 = n44;
                    }
                    final int n45 = linearLayoutCompat$LayoutParams3.leftMargin + linearLayoutCompat$LayoutParams3.rightMargin;
                    final int n46 = virtualChild3.getMeasuredWidth() + n45;
                    final int max6 = Math.max(n33, n46);
                    int n47;
                    if (mode != 1073741824 && linearLayoutCompat$LayoutParams3.width == -1) {
                        n47 = 1;
                    }
                    else {
                        n47 = 0;
                    }
                    int n48;
                    if (n47 != 0) {
                        n48 = n45;
                    }
                    else {
                        n48 = n46;
                    }
                    max5 = Math.max(n31, n48);
                    if (n34 != 0 && linearLayoutCompat$LayoutParams3.width == -1) {
                        n34 = 1;
                    }
                    else {
                        n34 = 0;
                    }
                    final int mTotalLength5 = this.mTotalLength;
                    this.mTotalLength = Math.max(mTotalLength5, linearLayoutCompat$LayoutParams3.bottomMargin + (virtualChild3.getMeasuredHeight() + mTotalLength5 + linearLayoutCompat$LayoutParams3.topMargin) + this.getNextLocationOffset(virtualChild3));
                    n37 = max6;
                    final int n49 = n43;
                    n4 = n42;
                    n35 = n49;
                }
                ++k;
                final int n50 = n37;
                n31 = max5;
                n33 = n50;
            }
            this.mTotalLength += this.getPaddingTop() + this.getPaddingBottom();
            final int n51 = n34;
            n52 = n31;
            n7 = n51;
        }
        else {
            final int max7 = Math.max(n5, n6);
            if (mUseLargestChild && mode2 != 1073741824) {
                for (int l = 0; l < virtualChildCount; ++l) {
                    final View virtualChild4 = this.getVirtualChildAt(l);
                    if (virtualChild4 != null && virtualChild4.getVisibility() != 8 && ((LinearLayoutCompat$LayoutParams)virtualChild4.getLayoutParams()).weight > 0.0f) {
                        virtualChild4.measure(View$MeasureSpec.makeMeasureSpec(virtualChild4.getMeasuredWidth(), 1073741824), View$MeasureSpec.makeMeasureSpec(n10, 1073741824));
                    }
                }
            }
            final int n53 = max7;
            final int n54 = n3;
            n52 = n53;
            n33 = n54;
        }
        if (n7 != 0 || mode == 1073741824) {
            n52 = n33;
        }
        this.setMeasuredDimension(ViewCompat.resolveSizeAndState(Math.max(n52 + (this.getPaddingLeft() + this.getPaddingRight()), this.getSuggestedMinimumWidth()), n, n4), resolveSizeAndState);
        if (n8 != 0) {
            this.forceUniformWidth(virtualChildCount, n2);
        }
    }
    
    protected void onDraw(final Canvas canvas) {
        if (this.mDivider == null) {
            return;
        }
        if (this.mOrientation == 1) {
            this.drawDividersVertical(canvas);
            return;
        }
        this.drawDividersHorizontal(canvas);
    }
    
    public void onInitializeAccessibilityEvent(final AccessibilityEvent accessibilityEvent) {
        if (Build$VERSION.SDK_INT >= 14) {
            super.onInitializeAccessibilityEvent(accessibilityEvent);
            accessibilityEvent.setClassName((CharSequence)LinearLayoutCompat.class.getName());
        }
    }
    
    public void onInitializeAccessibilityNodeInfo(final AccessibilityNodeInfo accessibilityNodeInfo) {
        if (Build$VERSION.SDK_INT >= 14) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            accessibilityNodeInfo.setClassName((CharSequence)LinearLayoutCompat.class.getName());
        }
    }
    
    protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
        if (this.mOrientation == 1) {
            this.layoutVertical(n, n2, n3, n4);
            return;
        }
        this.layoutHorizontal(n, n2, n3, n4);
    }
    
    protected void onMeasure(final int n, final int n2) {
        if (this.mOrientation == 1) {
            this.measureVertical(n, n2);
            return;
        }
        this.measureHorizontal(n, n2);
    }
    
    public void setBaselineAligned(final boolean mBaselineAligned) {
        this.mBaselineAligned = mBaselineAligned;
    }
    
    public void setBaselineAlignedChildIndex(final int mBaselineAlignedChildIndex) {
        if (mBaselineAlignedChildIndex < 0 || mBaselineAlignedChildIndex >= this.getChildCount()) {
            throw new IllegalArgumentException("base aligned child index out of range (0, " + this.getChildCount() + ")");
        }
        this.mBaselineAlignedChildIndex = mBaselineAlignedChildIndex;
    }
    
    public void setDividerDrawable(final Drawable mDivider) {
        boolean willNotDraw = false;
        if (mDivider == this.mDivider) {
            return;
        }
        if ((this.mDivider = mDivider) != null) {
            this.mDividerWidth = mDivider.getIntrinsicWidth();
            this.mDividerHeight = mDivider.getIntrinsicHeight();
        }
        else {
            this.mDividerWidth = 0;
            this.mDividerHeight = 0;
        }
        if (mDivider == null) {
            willNotDraw = true;
        }
        this.setWillNotDraw(willNotDraw);
        this.requestLayout();
    }
    
    public void setDividerPadding(final int mDividerPadding) {
        this.mDividerPadding = mDividerPadding;
    }
    
    public void setGravity(int n) {
        if (this.mGravity != n) {
            if ((0x800007 & n) == 0x0) {
                n |= 0x800003;
            }
            int mGravity = n;
            if ((n & 0x70) == 0x0) {
                mGravity = (n | 0x30);
            }
            this.mGravity = mGravity;
            this.requestLayout();
        }
    }
    
    public void setHorizontalGravity(int n) {
        n &= 0x800007;
        if ((this.mGravity & 0x800007) != n) {
            this.mGravity = (n | (this.mGravity & 0xFF7FFFF8));
            this.requestLayout();
        }
    }
    
    public void setMeasureWithLargestChildEnabled(final boolean mUseLargestChild) {
        this.mUseLargestChild = mUseLargestChild;
    }
    
    public void setOrientation(final int mOrientation) {
        if (this.mOrientation != mOrientation) {
            this.mOrientation = mOrientation;
            this.requestLayout();
        }
    }
    
    public void setShowDividers(final int mShowDividers) {
        if (mShowDividers != this.mShowDividers) {
            this.requestLayout();
        }
        this.mShowDividers = mShowDividers;
    }
    
    public void setVerticalGravity(int n) {
        n &= 0x70;
        if ((this.mGravity & 0x70) != n) {
            this.mGravity = (n | (this.mGravity & 0xFFFFFF8F));
            this.requestLayout();
        }
    }
    
    public void setWeightSum(final float n) {
        this.mWeightSum = Math.max(0.0f, n);
    }
    
    public boolean shouldDelayChildPressedState() {
        return false;
    }
}
