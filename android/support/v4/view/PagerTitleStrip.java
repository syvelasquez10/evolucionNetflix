// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.database.DataSetObserver;
import android.view.View$MeasureSpec;
import android.view.ViewParent;
import android.graphics.drawable.Drawable;
import android.content.res.TypedArray;
import android.text.TextUtils$TruncateAt;
import android.view.View;
import android.util.AttributeSet;
import android.content.Context;
import android.os.Build$VERSION;
import java.lang.ref.WeakReference;
import android.widget.TextView;
import android.view.ViewGroup;

public class PagerTitleStrip extends ViewGroup implements ViewPager$Decor
{
    private static final int[] ATTRS;
    private static final PagerTitleStrip$PagerTitleStripImpl IMPL;
    private static final float SIDE_ALPHA = 0.6f;
    private static final String TAG = "PagerTitleStrip";
    private static final int[] TEXT_ATTRS;
    private static final int TEXT_SPACING = 16;
    TextView mCurrText;
    private int mGravity;
    private int mLastKnownCurrentPage;
    private float mLastKnownPositionOffset;
    TextView mNextText;
    private int mNonPrimaryAlpha;
    private final PagerTitleStrip$PageListener mPageListener;
    ViewPager mPager;
    TextView mPrevText;
    private int mScaledTextSpacing;
    int mTextColor;
    private boolean mUpdatingPositions;
    private boolean mUpdatingText;
    private WeakReference<PagerAdapter> mWatchingAdapter;
    
    static {
        ATTRS = new int[] { 16842804, 16842901, 16842904, 16842927 };
        TEXT_ATTRS = new int[] { 16843660 };
        if (Build$VERSION.SDK_INT >= 14) {
            IMPL = new PagerTitleStrip$PagerTitleStripImplIcs();
            return;
        }
        IMPL = new PagerTitleStrip$PagerTitleStripImplBase();
    }
    
    public PagerTitleStrip(final Context context) {
        this(context, null);
    }
    
    public PagerTitleStrip(final Context context, final AttributeSet set) {
        boolean boolean1 = false;
        super(context, set);
        this.mLastKnownCurrentPage = -1;
        this.mLastKnownPositionOffset = -1.0f;
        this.mPageListener = new PagerTitleStrip$PageListener(this, null);
        this.addView((View)(this.mPrevText = new TextView(context)));
        this.addView((View)(this.mCurrText = new TextView(context)));
        this.addView((View)(this.mNextText = new TextView(context)));
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, PagerTitleStrip.ATTRS);
        final int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        if (resourceId != 0) {
            this.mPrevText.setTextAppearance(context, resourceId);
            this.mCurrText.setTextAppearance(context, resourceId);
            this.mNextText.setTextAppearance(context, resourceId);
        }
        final int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(1, 0);
        if (dimensionPixelSize != 0) {
            this.setTextSize(0, dimensionPixelSize);
        }
        if (obtainStyledAttributes.hasValue(2)) {
            final int color = obtainStyledAttributes.getColor(2, 0);
            this.mPrevText.setTextColor(color);
            this.mCurrText.setTextColor(color);
            this.mNextText.setTextColor(color);
        }
        this.mGravity = obtainStyledAttributes.getInteger(3, 80);
        obtainStyledAttributes.recycle();
        this.mTextColor = this.mCurrText.getTextColors().getDefaultColor();
        this.setNonPrimaryAlpha(0.6f);
        this.mPrevText.setEllipsize(TextUtils$TruncateAt.END);
        this.mCurrText.setEllipsize(TextUtils$TruncateAt.END);
        this.mNextText.setEllipsize(TextUtils$TruncateAt.END);
        if (resourceId != 0) {
            final TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(resourceId, PagerTitleStrip.TEXT_ATTRS);
            boolean1 = obtainStyledAttributes2.getBoolean(0, false);
            obtainStyledAttributes2.recycle();
        }
        if (boolean1) {
            setSingleLineAllCaps(this.mPrevText);
            setSingleLineAllCaps(this.mCurrText);
            setSingleLineAllCaps(this.mNextText);
        }
        else {
            this.mPrevText.setSingleLine();
            this.mCurrText.setSingleLine();
            this.mNextText.setSingleLine();
        }
        this.mScaledTextSpacing = (int)(context.getResources().getDisplayMetrics().density * 16.0f);
    }
    
    private static void setSingleLineAllCaps(final TextView singleLineAllCaps) {
        PagerTitleStrip.IMPL.setSingleLineAllCaps(singleLineAllCaps);
    }
    
    int getMinHeight() {
        int intrinsicHeight = 0;
        final Drawable background = this.getBackground();
        if (background != null) {
            intrinsicHeight = background.getIntrinsicHeight();
        }
        return intrinsicHeight;
    }
    
    public int getTextSpacing() {
        return this.mScaledTextSpacing;
    }
    
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        final ViewParent parent = this.getParent();
        if (!(parent instanceof ViewPager)) {
            throw new IllegalStateException("PagerTitleStrip must be a direct child of a ViewPager.");
        }
        final ViewPager mPager = (ViewPager)parent;
        final PagerAdapter adapter = mPager.getAdapter();
        mPager.setInternalPageChangeListener(this.mPageListener);
        mPager.setOnAdapterChangeListener(this.mPageListener);
        this.mPager = mPager;
        PagerAdapter pagerAdapter;
        if (this.mWatchingAdapter != null) {
            pagerAdapter = this.mWatchingAdapter.get();
        }
        else {
            pagerAdapter = null;
        }
        this.updateAdapter(pagerAdapter, adapter);
    }
    
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mPager != null) {
            this.updateAdapter(this.mPager.getAdapter(), null);
            this.mPager.setInternalPageChangeListener(null);
            this.mPager.setOnAdapterChangeListener(null);
            this.mPager = null;
        }
    }
    
    protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
        float mLastKnownPositionOffset = 0.0f;
        if (this.mPager != null) {
            if (this.mLastKnownPositionOffset >= 0.0f) {
                mLastKnownPositionOffset = this.mLastKnownPositionOffset;
            }
            this.updateTextPositions(this.mLastKnownCurrentPage, mLastKnownPositionOffset, true);
        }
    }
    
    protected void onMeasure(int size, int size2) {
        final int mode = View$MeasureSpec.getMode(size);
        final int mode2 = View$MeasureSpec.getMode(size2);
        size = View$MeasureSpec.getSize(size);
        size2 = View$MeasureSpec.getSize(size2);
        if (mode != 1073741824) {
            throw new IllegalStateException("Must measure with an exact width");
        }
        final int minHeight = this.getMinHeight();
        final int n = this.getPaddingTop() + this.getPaddingBottom();
        final int measureSpec = View$MeasureSpec.makeMeasureSpec((int)(size * 0.8f), Integer.MIN_VALUE);
        final int measureSpec2 = View$MeasureSpec.makeMeasureSpec(size2 - n, Integer.MIN_VALUE);
        this.mPrevText.measure(measureSpec, measureSpec2);
        this.mCurrText.measure(measureSpec, measureSpec2);
        this.mNextText.measure(measureSpec, measureSpec2);
        if (mode2 == 1073741824) {
            this.setMeasuredDimension(size, size2);
            return;
        }
        this.setMeasuredDimension(size, Math.max(minHeight, this.mCurrText.getMeasuredHeight() + n));
    }
    
    public void requestLayout() {
        if (!this.mUpdatingText) {
            super.requestLayout();
        }
    }
    
    public void setGravity(final int mGravity) {
        this.mGravity = mGravity;
        this.requestLayout();
    }
    
    public void setNonPrimaryAlpha(final float n) {
        this.mNonPrimaryAlpha = ((int)(255.0f * n) & 0xFF);
        final int n2 = this.mNonPrimaryAlpha << 24 | (this.mTextColor & 0xFFFFFF);
        this.mPrevText.setTextColor(n2);
        this.mNextText.setTextColor(n2);
    }
    
    public void setTextColor(int n) {
        this.mTextColor = n;
        this.mCurrText.setTextColor(n);
        n = (this.mNonPrimaryAlpha << 24 | (this.mTextColor & 0xFFFFFF));
        this.mPrevText.setTextColor(n);
        this.mNextText.setTextColor(n);
    }
    
    public void setTextSize(final int n, final float n2) {
        this.mPrevText.setTextSize(n, n2);
        this.mCurrText.setTextSize(n, n2);
        this.mNextText.setTextSize(n, n2);
    }
    
    public void setTextSpacing(final int mScaledTextSpacing) {
        this.mScaledTextSpacing = mScaledTextSpacing;
        this.requestLayout();
    }
    
    void updateAdapter(final PagerAdapter pagerAdapter, final PagerAdapter pagerAdapter2) {
        if (pagerAdapter != null) {
            pagerAdapter.unregisterDataSetObserver(this.mPageListener);
            this.mWatchingAdapter = null;
        }
        if (pagerAdapter2 != null) {
            pagerAdapter2.registerDataSetObserver(this.mPageListener);
            this.mWatchingAdapter = new WeakReference<PagerAdapter>(pagerAdapter2);
        }
        if (this.mPager != null) {
            this.mLastKnownCurrentPage = -1;
            this.mLastKnownPositionOffset = -1.0f;
            this.updateText(this.mPager.getCurrentItem(), pagerAdapter2);
            this.requestLayout();
        }
    }
    
    void updateText(final int mLastKnownCurrentPage, final PagerAdapter pagerAdapter) {
        final CharSequence charSequence = null;
        int count;
        if (pagerAdapter != null) {
            count = pagerAdapter.getCount();
        }
        else {
            count = 0;
        }
        this.mUpdatingText = true;
        CharSequence pageTitle;
        if (mLastKnownCurrentPage >= 1 && pagerAdapter != null) {
            pageTitle = pagerAdapter.getPageTitle(mLastKnownCurrentPage - 1);
        }
        else {
            pageTitle = null;
        }
        this.mPrevText.setText(pageTitle);
        final TextView mCurrText = this.mCurrText;
        CharSequence pageTitle2;
        if (pagerAdapter != null && mLastKnownCurrentPage < count) {
            pageTitle2 = pagerAdapter.getPageTitle(mLastKnownCurrentPage);
        }
        else {
            pageTitle2 = null;
        }
        mCurrText.setText(pageTitle2);
        CharSequence pageTitle3 = charSequence;
        if (mLastKnownCurrentPage + 1 < count) {
            pageTitle3 = charSequence;
            if (pagerAdapter != null) {
                pageTitle3 = pagerAdapter.getPageTitle(mLastKnownCurrentPage + 1);
            }
        }
        this.mNextText.setText(pageTitle3);
        final int width = this.getWidth();
        final int paddingLeft = this.getPaddingLeft();
        final int paddingRight = this.getPaddingRight();
        final int height = this.getHeight();
        final int paddingTop = this.getPaddingTop();
        final int paddingBottom = this.getPaddingBottom();
        final int measureSpec = View$MeasureSpec.makeMeasureSpec((int)((width - paddingLeft - paddingRight) * 0.8f), Integer.MIN_VALUE);
        final int measureSpec2 = View$MeasureSpec.makeMeasureSpec(height - paddingTop - paddingBottom, Integer.MIN_VALUE);
        this.mPrevText.measure(measureSpec, measureSpec2);
        this.mCurrText.measure(measureSpec, measureSpec2);
        this.mNextText.measure(measureSpec, measureSpec2);
        this.mLastKnownCurrentPage = mLastKnownCurrentPage;
        if (!this.mUpdatingPositions) {
            this.updateTextPositions(mLastKnownCurrentPage, this.mLastKnownPositionOffset, false);
        }
        this.mUpdatingText = false;
    }
    
    void updateTextPositions(int paddingTop, final float mLastKnownPositionOffset, final boolean b) {
        if (paddingTop != this.mLastKnownCurrentPage) {
            this.updateText(paddingTop, this.mPager.getAdapter());
        }
        else if (!b && mLastKnownPositionOffset == this.mLastKnownPositionOffset) {
            return;
        }
        this.mUpdatingPositions = true;
        final int measuredWidth = this.mPrevText.getMeasuredWidth();
        final int measuredWidth2 = this.mCurrText.getMeasuredWidth();
        final int measuredWidth3 = this.mNextText.getMeasuredWidth();
        final int n = measuredWidth2 / 2;
        final int width = this.getWidth();
        final int height = this.getHeight();
        final int paddingLeft = this.getPaddingLeft();
        final int paddingRight = this.getPaddingRight();
        paddingTop = this.getPaddingTop();
        final int paddingBottom = this.getPaddingBottom();
        final int n2 = paddingRight + n;
        float n4;
        final float n3 = n4 = 0.5f + mLastKnownPositionOffset;
        if (n3 > 1.0f) {
            n4 = n3 - 1.0f;
        }
        final int n5 = width - n2 - (int)(n4 * (width - (paddingLeft + n) - n2)) - measuredWidth2 / 2;
        final int n6 = n5 + measuredWidth2;
        final int baseline = this.mPrevText.getBaseline();
        final int baseline2 = this.mCurrText.getBaseline();
        final int baseline3 = this.mNextText.getBaseline();
        final int max = Math.max(Math.max(baseline, baseline2), baseline3);
        final int n7 = max - baseline;
        final int n8 = max - baseline2;
        final int n9 = max - baseline3;
        final int max2 = Math.max(Math.max(this.mPrevText.getMeasuredHeight() + n7, this.mCurrText.getMeasuredHeight() + n8), this.mNextText.getMeasuredHeight() + n9);
        int n10 = 0;
        int n11 = 0;
        switch (this.mGravity & 0x70) {
            default: {
                n10 = paddingTop + n7;
                n11 = n8 + paddingTop;
                paddingTop += n9;
                break;
            }
            case 16: {
                paddingTop = (height - paddingTop - paddingBottom - max2) / 2;
                n10 = paddingTop + n7;
                n11 = n8 + paddingTop;
                paddingTop += n9;
                break;
            }
            case 80: {
                paddingTop = height - paddingBottom - max2;
                n10 = paddingTop + n7;
                n11 = n8 + paddingTop;
                paddingTop += n9;
                break;
            }
        }
        this.mCurrText.layout(n5, n11, n6, this.mCurrText.getMeasuredHeight() + n11);
        final int min = Math.min(paddingLeft, n5 - this.mScaledTextSpacing - measuredWidth);
        this.mPrevText.layout(min, n10, measuredWidth + min, this.mPrevText.getMeasuredHeight() + n10);
        final int max3 = Math.max(width - paddingRight - measuredWidth3, this.mScaledTextSpacing + n6);
        this.mNextText.layout(max3, paddingTop, max3 + measuredWidth3, this.mNextText.getMeasuredHeight() + paddingTop);
        this.mLastKnownPositionOffset = mLastKnownPositionOffset;
        this.mUpdatingPositions = false;
    }
}
