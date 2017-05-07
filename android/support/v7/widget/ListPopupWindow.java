// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.widget.AbsListView;
import android.os.SystemClock;
import android.view.ViewConfiguration;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.internal.widget.ListViewCompat;
import android.support.v4.widget.PopupWindowCompat;
import android.os.Build$VERSION;
import android.widget.PopupWindow$OnDismissListener;
import android.view.KeyEvent$DispatcherState;
import android.view.KeyEvent;
import android.widget.ListView;
import android.view.View$OnTouchListener;
import android.view.ViewParent;
import android.view.ViewGroup;
import android.view.View$MeasureSpec;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import android.widget.LinearLayout;
import android.widget.AbsListView$OnScrollListener;
import android.widget.AdapterView;
import android.content.res.TypedArray;
import android.support.v4.text.TextUtilsCompat;
import android.support.v7.internal.widget.AppCompatPopupWindow;
import android.util.AttributeSet;
import android.support.v7.appcompat.R;
import android.util.Log;
import android.graphics.Rect;
import android.widget.PopupWindow;
import android.database.DataSetObserver;
import android.widget.AdapterView$OnItemSelectedListener;
import android.widget.AdapterView$OnItemClickListener;
import android.os.Handler;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.content.Context;
import android.widget.ListAdapter;
import java.lang.reflect.Method;

public class ListPopupWindow
{
    private static final boolean DEBUG = false;
    private static final int EXPAND_LIST_TIMEOUT = 250;
    public static final int INPUT_METHOD_FROM_FOCUSABLE = 0;
    public static final int INPUT_METHOD_NEEDED = 1;
    public static final int INPUT_METHOD_NOT_NEEDED = 2;
    public static final int MATCH_PARENT = -1;
    public static final int POSITION_PROMPT_ABOVE = 0;
    public static final int POSITION_PROMPT_BELOW = 1;
    private static final String TAG = "ListPopupWindow";
    public static final int WRAP_CONTENT = -2;
    private static Method sClipToWindowEnabledMethod;
    private ListAdapter mAdapter;
    private Context mContext;
    private boolean mDropDownAlwaysVisible;
    private View mDropDownAnchorView;
    private int mDropDownGravity;
    private int mDropDownHeight;
    private int mDropDownHorizontalOffset;
    private DropDownListView mDropDownList;
    private Drawable mDropDownListHighlight;
    private int mDropDownVerticalOffset;
    private boolean mDropDownVerticalOffsetSet;
    private int mDropDownWidth;
    private boolean mForceIgnoreOutsideTouch;
    private Handler mHandler;
    private final ListSelectorHider mHideSelector;
    private AdapterView$OnItemClickListener mItemClickListener;
    private AdapterView$OnItemSelectedListener mItemSelectedListener;
    private int mLayoutDirection;
    int mListItemExpandMaximum;
    private boolean mModal;
    private DataSetObserver mObserver;
    private PopupWindow mPopup;
    private int mPromptPosition;
    private View mPromptView;
    private final ResizePopupRunnable mResizePopupRunnable;
    private final PopupScrollListener mScrollListener;
    private Runnable mShowDropDownRunnable;
    private Rect mTempRect;
    private final PopupTouchInterceptor mTouchInterceptor;
    
    static {
        try {
            ListPopupWindow.sClipToWindowEnabledMethod = PopupWindow.class.getDeclaredMethod("setClipToScreenEnabled", Boolean.TYPE);
        }
        catch (NoSuchMethodException ex) {
            Log.i("ListPopupWindow", "Could not find method setClipToScreenEnabled() on PopupWindow. Oh well.");
        }
    }
    
    public ListPopupWindow(final Context context) {
        this(context, null, R.attr.listPopupWindowStyle);
    }
    
    public ListPopupWindow(final Context context, final AttributeSet set) {
        this(context, set, R.attr.listPopupWindowStyle);
    }
    
    public ListPopupWindow(final Context mContext, final AttributeSet set, final int n) {
        this.mDropDownHeight = -2;
        this.mDropDownWidth = -2;
        this.mDropDownGravity = 0;
        this.mDropDownAlwaysVisible = false;
        this.mForceIgnoreOutsideTouch = false;
        this.mListItemExpandMaximum = Integer.MAX_VALUE;
        this.mPromptPosition = 0;
        this.mResizePopupRunnable = new ResizePopupRunnable();
        this.mTouchInterceptor = new PopupTouchInterceptor();
        this.mScrollListener = new PopupScrollListener();
        this.mHideSelector = new ListSelectorHider();
        this.mHandler = new Handler();
        this.mTempRect = new Rect();
        this.mContext = mContext;
        final TypedArray obtainStyledAttributes = mContext.obtainStyledAttributes(set, R.styleable.ListPopupWindow, n, 0);
        this.mDropDownHorizontalOffset = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.ListPopupWindow_android_dropDownHorizontalOffset, 0);
        this.mDropDownVerticalOffset = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.ListPopupWindow_android_dropDownVerticalOffset, 0);
        if (this.mDropDownVerticalOffset != 0) {
            this.mDropDownVerticalOffsetSet = true;
        }
        obtainStyledAttributes.recycle();
        (this.mPopup = new AppCompatPopupWindow(mContext, set, n)).setInputMethodMode(1);
        this.mLayoutDirection = TextUtilsCompat.getLayoutDirectionFromLocale(this.mContext.getResources().getConfiguration().locale);
    }
    
    private int buildDropDown() {
        final int n = 0;
        int n2 = 0;
        if (this.mDropDownList == null) {
            final Context mContext = this.mContext;
            this.mShowDropDownRunnable = new Runnable() {
                @Override
                public void run() {
                    final View anchorView = ListPopupWindow.this.getAnchorView();
                    if (anchorView != null && anchorView.getWindowToken() != null) {
                        ListPopupWindow.this.show();
                    }
                }
            };
            this.mDropDownList = new DropDownListView(mContext, !this.mModal);
            if (this.mDropDownListHighlight != null) {
                this.mDropDownList.setSelector(this.mDropDownListHighlight);
            }
            this.mDropDownList.setAdapter(this.mAdapter);
            this.mDropDownList.setOnItemClickListener(this.mItemClickListener);
            this.mDropDownList.setFocusable(true);
            this.mDropDownList.setFocusableInTouchMode(true);
            this.mDropDownList.setOnItemSelectedListener((AdapterView$OnItemSelectedListener)new AdapterView$OnItemSelectedListener() {
                public void onItemSelected(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                    if (n != -1) {
                        final DropDownListView access$600 = ListPopupWindow.this.mDropDownList;
                        if (access$600 != null) {
                            access$600.mListSelectionHidden = false;
                        }
                    }
                }
                
                public void onNothingSelected(final AdapterView<?> adapterView) {
                }
            });
            this.mDropDownList.setOnScrollListener((AbsListView$OnScrollListener)this.mScrollListener);
            if (this.mItemSelectedListener != null) {
                this.mDropDownList.setOnItemSelectedListener(this.mItemSelectedListener);
            }
            final DropDownListView mDropDownList = this.mDropDownList;
            final View mPromptView = this.mPromptView;
            Object contentView = mDropDownList;
            if (mPromptView != null) {
                contentView = new LinearLayout(mContext);
                ((LinearLayout)contentView).setOrientation(1);
                final LinearLayout$LayoutParams linearLayout$LayoutParams = new LinearLayout$LayoutParams(-1, 0, 1.0f);
                switch (this.mPromptPosition) {
                    default: {
                        Log.e("ListPopupWindow", "Invalid hint position " + this.mPromptPosition);
                        break;
                    }
                    case 1: {
                        ((LinearLayout)contentView).addView((View)mDropDownList, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
                        ((LinearLayout)contentView).addView(mPromptView);
                        break;
                    }
                    case 0: {
                        ((LinearLayout)contentView).addView(mPromptView);
                        ((LinearLayout)contentView).addView((View)mDropDownList, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
                        break;
                    }
                }
                mPromptView.measure(View$MeasureSpec.makeMeasureSpec(this.mDropDownWidth, Integer.MIN_VALUE), 0);
                final LinearLayout$LayoutParams linearLayout$LayoutParams2 = (LinearLayout$LayoutParams)mPromptView.getLayoutParams();
                n2 = mPromptView.getMeasuredHeight() + linearLayout$LayoutParams2.topMargin + linearLayout$LayoutParams2.bottomMargin;
            }
            this.mPopup.setContentView((View)contentView);
        }
        else {
            final ViewGroup viewGroup = (ViewGroup)this.mPopup.getContentView();
            final View mPromptView2 = this.mPromptView;
            n2 = n;
            if (mPromptView2 != null) {
                final LinearLayout$LayoutParams linearLayout$LayoutParams3 = (LinearLayout$LayoutParams)mPromptView2.getLayoutParams();
                n2 = mPromptView2.getMeasuredHeight() + linearLayout$LayoutParams3.topMargin + linearLayout$LayoutParams3.bottomMargin;
            }
        }
        int n3 = 0;
        final Drawable background = this.mPopup.getBackground();
        if (background != null) {
            background.getPadding(this.mTempRect);
            final int n4 = n3 = this.mTempRect.top + this.mTempRect.bottom;
            if (!this.mDropDownVerticalOffsetSet) {
                this.mDropDownVerticalOffset = -this.mTempRect.top;
                n3 = n4;
            }
        }
        else {
            this.mTempRect.setEmpty();
        }
        if (this.mPopup.getInputMethodMode() == 2) {}
        final int maxAvailableHeight = this.mPopup.getMaxAvailableHeight(this.getAnchorView(), this.mDropDownVerticalOffset);
        if (this.mDropDownAlwaysVisible || this.mDropDownHeight == -1) {
            return maxAvailableHeight + n3;
        }
        int n5 = 0;
        switch (this.mDropDownWidth) {
            default: {
                n5 = View$MeasureSpec.makeMeasureSpec(this.mDropDownWidth, 1073741824);
                break;
            }
            case -2: {
                n5 = View$MeasureSpec.makeMeasureSpec(this.mContext.getResources().getDisplayMetrics().widthPixels - (this.mTempRect.left + this.mTempRect.right), Integer.MIN_VALUE);
                break;
            }
            case -1: {
                n5 = View$MeasureSpec.makeMeasureSpec(this.mContext.getResources().getDisplayMetrics().widthPixels - (this.mTempRect.left + this.mTempRect.right), 1073741824);
                break;
            }
        }
        final int measureHeightOfChildrenCompat = this.mDropDownList.measureHeightOfChildrenCompat(n5, 0, -1, maxAvailableHeight - n2, -1);
        int n6 = n2;
        if (measureHeightOfChildrenCompat > 0) {
            n6 = n2 + n3;
        }
        return measureHeightOfChildrenCompat + n6;
    }
    
    private static boolean isConfirmKey(final int n) {
        return n == 66 || n == 23;
    }
    
    private void removePromptView() {
        if (this.mPromptView != null) {
            final ViewParent parent = this.mPromptView.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup)parent).removeView(this.mPromptView);
            }
        }
    }
    
    private void setPopupClipToScreenEnabled(final boolean b) {
        if (ListPopupWindow.sClipToWindowEnabledMethod == null) {
            return;
        }
        try {
            ListPopupWindow.sClipToWindowEnabledMethod.invoke(this.mPopup, b);
        }
        catch (Exception ex) {
            Log.i("ListPopupWindow", "Could not call setClipToScreenEnabled() on PopupWindow. Oh well.");
        }
    }
    
    public void clearListSelection() {
        final DropDownListView mDropDownList = this.mDropDownList;
        if (mDropDownList != null) {
            mDropDownList.mListSelectionHidden = true;
            mDropDownList.requestLayout();
        }
    }
    
    public View$OnTouchListener createDragToOpenListener(final View view) {
        return (View$OnTouchListener)new ForwardingListener(view) {
            @Override
            public ListPopupWindow getPopup() {
                return ListPopupWindow.this;
            }
        };
    }
    
    public void dismiss() {
        this.mPopup.dismiss();
        this.removePromptView();
        this.mPopup.setContentView((View)null);
        this.mDropDownList = null;
        this.mHandler.removeCallbacks((Runnable)this.mResizePopupRunnable);
    }
    
    public View getAnchorView() {
        return this.mDropDownAnchorView;
    }
    
    public int getAnimationStyle() {
        return this.mPopup.getAnimationStyle();
    }
    
    public Drawable getBackground() {
        return this.mPopup.getBackground();
    }
    
    public int getHeight() {
        return this.mDropDownHeight;
    }
    
    public int getHorizontalOffset() {
        return this.mDropDownHorizontalOffset;
    }
    
    public int getInputMethodMode() {
        return this.mPopup.getInputMethodMode();
    }
    
    public ListView getListView() {
        return this.mDropDownList;
    }
    
    public int getPromptPosition() {
        return this.mPromptPosition;
    }
    
    public Object getSelectedItem() {
        if (!this.isShowing()) {
            return null;
        }
        return this.mDropDownList.getSelectedItem();
    }
    
    public long getSelectedItemId() {
        if (!this.isShowing()) {
            return Long.MIN_VALUE;
        }
        return this.mDropDownList.getSelectedItemId();
    }
    
    public int getSelectedItemPosition() {
        if (!this.isShowing()) {
            return -1;
        }
        return this.mDropDownList.getSelectedItemPosition();
    }
    
    public View getSelectedView() {
        if (!this.isShowing()) {
            return null;
        }
        return this.mDropDownList.getSelectedView();
    }
    
    public int getSoftInputMode() {
        return this.mPopup.getSoftInputMode();
    }
    
    public int getVerticalOffset() {
        if (!this.mDropDownVerticalOffsetSet) {
            return 0;
        }
        return this.mDropDownVerticalOffset;
    }
    
    public int getWidth() {
        return this.mDropDownWidth;
    }
    
    public boolean isDropDownAlwaysVisible() {
        return this.mDropDownAlwaysVisible;
    }
    
    public boolean isInputMethodNotNeeded() {
        return this.mPopup.getInputMethodMode() == 2;
    }
    
    public boolean isModal() {
        return this.mModal;
    }
    
    public boolean isShowing() {
        return this.mPopup.isShowing();
    }
    
    public boolean onKeyDown(final int n, final KeyEvent keyEvent) {
        if (this.isShowing() && n != 62 && (this.mDropDownList.getSelectedItemPosition() >= 0 || !isConfirmKey(n))) {
            final int selectedItemPosition = this.mDropDownList.getSelectedItemPosition();
            boolean b;
            if (!this.mPopup.isAboveAnchor()) {
                b = true;
            }
            else {
                b = false;
            }
            final ListAdapter mAdapter = this.mAdapter;
            int lookForSelectablePosition = Integer.MAX_VALUE;
            int lookForSelectablePosition2 = Integer.MIN_VALUE;
            if (mAdapter != null) {
                final boolean allItemsEnabled = mAdapter.areAllItemsEnabled();
                if (allItemsEnabled) {
                    lookForSelectablePosition = 0;
                }
                else {
                    lookForSelectablePosition = this.mDropDownList.lookForSelectablePosition(0, true);
                }
                if (allItemsEnabled) {
                    lookForSelectablePosition2 = mAdapter.getCount() - 1;
                }
                else {
                    lookForSelectablePosition2 = this.mDropDownList.lookForSelectablePosition(mAdapter.getCount() - 1, false);
                }
            }
            if ((b && n == 19 && selectedItemPosition <= lookForSelectablePosition) || (!b && n == 20 && selectedItemPosition >= lookForSelectablePosition2)) {
                this.clearListSelection();
                this.mPopup.setInputMethodMode(1);
                this.show();
            }
            else {
                this.mDropDownList.mListSelectionHidden = false;
                if (this.mDropDownList.onKeyDown(n, keyEvent)) {
                    this.mPopup.setInputMethodMode(2);
                    this.mDropDownList.requestFocusFromTouch();
                    this.show();
                    switch (n) {
                        case 19:
                        case 20:
                        case 23:
                        case 66: {
                            break;
                        }
                        default: {
                            return false;
                        }
                    }
                }
                else if (b && n == 20) {
                    if (selectedItemPosition == lookForSelectablePosition2) {
                        return true;
                    }
                    return false;
                }
                else {
                    if (!b && n == 19 && selectedItemPosition == lookForSelectablePosition) {
                        return true;
                    }
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    public boolean onKeyPreIme(final int n, final KeyEvent keyEvent) {
        if (n == 4 && this.isShowing()) {
            final View mDropDownAnchorView = this.mDropDownAnchorView;
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                final KeyEvent$DispatcherState keyDispatcherState = mDropDownAnchorView.getKeyDispatcherState();
                if (keyDispatcherState != null) {
                    keyDispatcherState.startTracking(keyEvent, (Object)this);
                }
                return true;
            }
            if (keyEvent.getAction() == 1) {
                final KeyEvent$DispatcherState keyDispatcherState2 = mDropDownAnchorView.getKeyDispatcherState();
                if (keyDispatcherState2 != null) {
                    keyDispatcherState2.handleUpEvent(keyEvent);
                }
                if (keyEvent.isTracking() && !keyEvent.isCanceled()) {
                    this.dismiss();
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean onKeyUp(final int n, final KeyEvent keyEvent) {
        if (this.isShowing() && this.mDropDownList.getSelectedItemPosition() >= 0) {
            final boolean onKeyUp = this.mDropDownList.onKeyUp(n, keyEvent);
            if (onKeyUp && isConfirmKey(n)) {
                this.dismiss();
            }
            return onKeyUp;
        }
        return false;
    }
    
    public boolean performItemClick(final int n) {
        if (this.isShowing()) {
            if (this.mItemClickListener != null) {
                final DropDownListView mDropDownList = this.mDropDownList;
                this.mItemClickListener.onItemClick((AdapterView)mDropDownList, mDropDownList.getChildAt(n - mDropDownList.getFirstVisiblePosition()), n, mDropDownList.getAdapter().getItemId(n));
            }
            return true;
        }
        return false;
    }
    
    public void postShow() {
        this.mHandler.post(this.mShowDropDownRunnable);
    }
    
    public void setAdapter(final ListAdapter mAdapter) {
        if (this.mObserver == null) {
            this.mObserver = new PopupDataSetObserver();
        }
        else if (this.mAdapter != null) {
            this.mAdapter.unregisterDataSetObserver(this.mObserver);
        }
        this.mAdapter = mAdapter;
        if (this.mAdapter != null) {
            mAdapter.registerDataSetObserver(this.mObserver);
        }
        if (this.mDropDownList != null) {
            this.mDropDownList.setAdapter(this.mAdapter);
        }
    }
    
    public void setAnchorView(final View mDropDownAnchorView) {
        this.mDropDownAnchorView = mDropDownAnchorView;
    }
    
    public void setAnimationStyle(final int animationStyle) {
        this.mPopup.setAnimationStyle(animationStyle);
    }
    
    public void setBackgroundDrawable(final Drawable backgroundDrawable) {
        this.mPopup.setBackgroundDrawable(backgroundDrawable);
    }
    
    public void setContentWidth(final int width) {
        final Drawable background = this.mPopup.getBackground();
        if (background != null) {
            background.getPadding(this.mTempRect);
            this.mDropDownWidth = this.mTempRect.left + this.mTempRect.right + width;
            return;
        }
        this.setWidth(width);
    }
    
    public void setDropDownAlwaysVisible(final boolean mDropDownAlwaysVisible) {
        this.mDropDownAlwaysVisible = mDropDownAlwaysVisible;
    }
    
    public void setDropDownGravity(final int mDropDownGravity) {
        this.mDropDownGravity = mDropDownGravity;
    }
    
    public void setForceIgnoreOutsideTouch(final boolean mForceIgnoreOutsideTouch) {
        this.mForceIgnoreOutsideTouch = mForceIgnoreOutsideTouch;
    }
    
    public void setHeight(final int mDropDownHeight) {
        this.mDropDownHeight = mDropDownHeight;
    }
    
    public void setHorizontalOffset(final int mDropDownHorizontalOffset) {
        this.mDropDownHorizontalOffset = mDropDownHorizontalOffset;
    }
    
    public void setInputMethodMode(final int inputMethodMode) {
        this.mPopup.setInputMethodMode(inputMethodMode);
    }
    
    void setListItemExpandMax(final int mListItemExpandMaximum) {
        this.mListItemExpandMaximum = mListItemExpandMaximum;
    }
    
    public void setListSelector(final Drawable mDropDownListHighlight) {
        this.mDropDownListHighlight = mDropDownListHighlight;
    }
    
    public void setModal(final boolean b) {
        this.mModal = b;
        this.mPopup.setFocusable(b);
    }
    
    public void setOnDismissListener(final PopupWindow$OnDismissListener onDismissListener) {
        this.mPopup.setOnDismissListener(onDismissListener);
    }
    
    public void setOnItemClickListener(final AdapterView$OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
    
    public void setOnItemSelectedListener(final AdapterView$OnItemSelectedListener mItemSelectedListener) {
        this.mItemSelectedListener = mItemSelectedListener;
    }
    
    public void setPromptPosition(final int mPromptPosition) {
        this.mPromptPosition = mPromptPosition;
    }
    
    public void setPromptView(final View mPromptView) {
        final boolean showing = this.isShowing();
        if (showing) {
            this.removePromptView();
        }
        this.mPromptView = mPromptView;
        if (showing) {
            this.show();
        }
    }
    
    public void setSelection(final int selection) {
        final DropDownListView mDropDownList = this.mDropDownList;
        if (this.isShowing() && mDropDownList != null) {
            mDropDownList.mListSelectionHidden = false;
            mDropDownList.setSelection(selection);
            if (Build$VERSION.SDK_INT >= 11 && mDropDownList.getChoiceMode() != 0) {
                mDropDownList.setItemChecked(selection, true);
            }
        }
    }
    
    public void setSoftInputMode(final int softInputMode) {
        this.mPopup.setSoftInputMode(softInputMode);
    }
    
    public void setVerticalOffset(final int mDropDownVerticalOffset) {
        this.mDropDownVerticalOffset = mDropDownVerticalOffset;
        this.mDropDownVerticalOffsetSet = true;
    }
    
    public void setWidth(final int mDropDownWidth) {
        this.mDropDownWidth = mDropDownWidth;
    }
    
    public void show() {
        boolean outsideTouchable = true;
        final boolean b = false;
        int n = -1;
        int height = this.buildDropDown();
        int n2 = 0;
        final int n3 = 0;
        final boolean inputMethodNotNeeded = this.isInputMethodNotNeeded();
        if (this.mPopup.isShowing()) {
            int n4;
            if (this.mDropDownWidth == -1) {
                n4 = -1;
            }
            else if (this.mDropDownWidth == -2) {
                n4 = this.getAnchorView().getWidth();
            }
            else {
                n4 = this.mDropDownWidth;
            }
            if (this.mDropDownHeight == -1) {
                if (!inputMethodNotNeeded) {
                    height = -1;
                }
                if (inputMethodNotNeeded) {
                    final PopupWindow mPopup = this.mPopup;
                    if (this.mDropDownWidth != -1) {
                        n = 0;
                    }
                    mPopup.setWindowLayoutMode(n, 0);
                }
                else {
                    final PopupWindow mPopup2 = this.mPopup;
                    int n5;
                    if (this.mDropDownWidth == -1) {
                        n5 = -1;
                    }
                    else {
                        n5 = 0;
                    }
                    mPopup2.setWindowLayoutMode(n5, -1);
                }
            }
            else if (this.mDropDownHeight != -2) {
                height = this.mDropDownHeight;
            }
            final PopupWindow mPopup3 = this.mPopup;
            boolean outsideTouchable2 = b;
            if (!this.mForceIgnoreOutsideTouch) {
                outsideTouchable2 = b;
                if (!this.mDropDownAlwaysVisible) {
                    outsideTouchable2 = true;
                }
            }
            mPopup3.setOutsideTouchable(outsideTouchable2);
            this.mPopup.update(this.getAnchorView(), this.mDropDownHorizontalOffset, this.mDropDownVerticalOffset, n4, height);
        }
        else {
            if (this.mDropDownWidth == -1) {
                n2 = -1;
            }
            else if (this.mDropDownWidth == -2) {
                this.mPopup.setWidth(this.getAnchorView().getWidth());
            }
            else {
                this.mPopup.setWidth(this.mDropDownWidth);
            }
            int n6;
            if (this.mDropDownHeight == -1) {
                n6 = -1;
            }
            else if (this.mDropDownHeight == -2) {
                this.mPopup.setHeight(height);
                n6 = n3;
            }
            else {
                this.mPopup.setHeight(this.mDropDownHeight);
                n6 = n3;
            }
            this.mPopup.setWindowLayoutMode(n2, n6);
            this.setPopupClipToScreenEnabled(true);
            final PopupWindow mPopup4 = this.mPopup;
            if (this.mForceIgnoreOutsideTouch || this.mDropDownAlwaysVisible) {
                outsideTouchable = false;
            }
            mPopup4.setOutsideTouchable(outsideTouchable);
            this.mPopup.setTouchInterceptor((View$OnTouchListener)this.mTouchInterceptor);
            PopupWindowCompat.showAsDropDown(this.mPopup, this.getAnchorView(), this.mDropDownHorizontalOffset, this.mDropDownVerticalOffset, this.mDropDownGravity);
            this.mDropDownList.setSelection(-1);
            if (!this.mModal || this.mDropDownList.isInTouchMode()) {
                this.clearListSelection();
            }
            if (!this.mModal) {
                this.mHandler.post((Runnable)this.mHideSelector);
            }
        }
    }
    
    private static class DropDownListView extends ListViewCompat
    {
        private ViewPropertyAnimatorCompat mClickAnimation;
        private boolean mDrawsInPressedState;
        private boolean mHijackFocus;
        private boolean mListSelectionHidden;
        private ListViewAutoScrollHelper mScrollHelper;
        
        public DropDownListView(final Context context, final boolean mHijackFocus) {
            super(context, null, R.attr.dropDownListViewStyle);
            this.mHijackFocus = mHijackFocus;
            this.setCacheColorHint(0);
        }
        
        private void clearPressedItem() {
            this.setPressed(this.mDrawsInPressedState = false);
            this.drawableStateChanged();
            if (this.mClickAnimation != null) {
                this.mClickAnimation.cancel();
                this.mClickAnimation = null;
            }
        }
        
        private void clickPressedItem(final View view, final int n) {
            this.performItemClick(view, n, this.getItemIdAtPosition(n));
        }
        
        private void setPressedItem(final View view, final int selection, final float n, final float n2) {
            this.setPressed(this.mDrawsInPressedState = true);
            this.layoutChildren();
            this.setSelection(selection);
            this.positionSelectorLikeTouchCompat(selection, view, n, n2);
            this.setSelectorEnabled(false);
            this.refreshDrawableState();
        }
        
        public boolean hasFocus() {
            return this.mHijackFocus || super.hasFocus();
        }
        
        public boolean hasWindowFocus() {
            return this.mHijackFocus || super.hasWindowFocus();
        }
        
        public boolean isFocused() {
            return this.mHijackFocus || super.isFocused();
        }
        
        public boolean isInTouchMode() {
            return (this.mHijackFocus && this.mListSelectionHidden) || super.isInTouchMode();
        }
        
        public boolean onForwardedEvent(final MotionEvent motionEvent, int n) {
            boolean b = true;
            final boolean b2 = true;
            final int n2 = 0;
            final int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
            switch (actionMasked) {
                default: {
                    b = b2;
                    n = n2;
                    break;
                }
                case 3: {
                    b = false;
                    n = n2;
                    break;
                }
                case 1: {
                    b = false;
                }
                case 2: {
                    final int pointerIndex = motionEvent.findPointerIndex(n);
                    if (pointerIndex < 0) {
                        b = false;
                        n = n2;
                        break;
                    }
                    n = (int)motionEvent.getX(pointerIndex);
                    final int n3 = (int)motionEvent.getY(pointerIndex);
                    final int pointToPosition = this.pointToPosition(n, n3);
                    if (pointToPosition == -1) {
                        n = 1;
                        break;
                    }
                    final View child = this.getChildAt(pointToPosition - this.getFirstVisiblePosition());
                    this.setPressedItem(child, pointToPosition, n, n3);
                    final boolean b3 = true;
                    n = n2;
                    b = b3;
                    if (actionMasked == 1) {
                        this.clickPressedItem(child, pointToPosition);
                        n = n2;
                        b = b3;
                        break;
                    }
                    break;
                }
            }
            if (!b || n != 0) {
                this.clearPressedItem();
            }
            if (b) {
                if (this.mScrollHelper == null) {
                    this.mScrollHelper = new ListViewAutoScrollHelper(this);
                }
                this.mScrollHelper.setEnabled(true);
                this.mScrollHelper.onTouch((View)this, motionEvent);
            }
            else if (this.mScrollHelper != null) {
                this.mScrollHelper.setEnabled(false);
                return b;
            }
            return b;
        }
        
        @Override
        protected boolean touchModeDrawsInPressedStateCompat() {
            return this.mDrawsInPressedState || super.touchModeDrawsInPressedStateCompat();
        }
    }
    
    public abstract static class ForwardingListener implements View$OnTouchListener
    {
        private int mActivePointerId;
        private Runnable mDisallowIntercept;
        private boolean mForwarding;
        private final int mLongPressTimeout;
        private final float mScaledTouchSlop;
        private final View mSrc;
        private final int mTapTimeout;
        private final int[] mTmpLocation;
        private Runnable mTriggerLongPress;
        private boolean mWasLongPress;
        
        public ForwardingListener(final View mSrc) {
            this.mTmpLocation = new int[2];
            this.mSrc = mSrc;
            this.mScaledTouchSlop = ViewConfiguration.get(mSrc.getContext()).getScaledTouchSlop();
            this.mTapTimeout = ViewConfiguration.getTapTimeout();
            this.mLongPressTimeout = (this.mTapTimeout + ViewConfiguration.getLongPressTimeout()) / 2;
        }
        
        private void clearCallbacks() {
            if (this.mTriggerLongPress != null) {
                this.mSrc.removeCallbacks(this.mTriggerLongPress);
            }
            if (this.mDisallowIntercept != null) {
                this.mSrc.removeCallbacks(this.mDisallowIntercept);
            }
        }
        
        private void onLongPress() {
            this.clearCallbacks();
            if (this.mSrc.isEnabled() && this.onForwardingStarted()) {
                this.mSrc.getParent().requestDisallowInterceptTouchEvent(true);
                final long uptimeMillis = SystemClock.uptimeMillis();
                final MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
                this.mSrc.onTouchEvent(obtain);
                obtain.recycle();
                this.mForwarding = true;
                this.mWasLongPress = true;
            }
        }
        
        private boolean onTouchForwarded(final MotionEvent motionEvent) {
            boolean b = true;
            final View mSrc = this.mSrc;
            final ListPopupWindow popup = this.getPopup();
            if (popup != null && popup.isShowing()) {
                final DropDownListView access$600 = popup.mDropDownList;
                if (access$600 != null && access$600.isShown()) {
                    final MotionEvent obtainNoHistory = MotionEvent.obtainNoHistory(motionEvent);
                    this.toGlobalMotionEvent(mSrc, obtainNoHistory);
                    this.toLocalMotionEvent((View)access$600, obtainNoHistory);
                    final boolean onForwardedEvent = access$600.onForwardedEvent(obtainNoHistory, this.mActivePointerId);
                    obtainNoHistory.recycle();
                    final int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
                    boolean b2;
                    if (actionMasked != 1 && actionMasked != 3) {
                        b2 = true;
                    }
                    else {
                        b2 = false;
                    }
                    if (!onForwardedEvent || !b2) {
                        b = false;
                    }
                    return b;
                }
            }
            return false;
        }
        
        private boolean onTouchObserved(final MotionEvent motionEvent) {
            final View mSrc = this.mSrc;
            if (mSrc.isEnabled()) {
                switch (MotionEventCompat.getActionMasked(motionEvent)) {
                    default: {
                        return false;
                    }
                    case 0: {
                        this.mActivePointerId = motionEvent.getPointerId(0);
                        this.mWasLongPress = false;
                        if (this.mDisallowIntercept == null) {
                            this.mDisallowIntercept = new DisallowIntercept();
                        }
                        mSrc.postDelayed(this.mDisallowIntercept, (long)this.mTapTimeout);
                        if (this.mTriggerLongPress == null) {
                            this.mTriggerLongPress = new TriggerLongPress();
                        }
                        mSrc.postDelayed(this.mTriggerLongPress, (long)this.mLongPressTimeout);
                        return false;
                    }
                    case 2: {
                        final int pointerIndex = motionEvent.findPointerIndex(this.mActivePointerId);
                        if (pointerIndex >= 0 && !pointInView(mSrc, motionEvent.getX(pointerIndex), motionEvent.getY(pointerIndex), this.mScaledTouchSlop)) {
                            this.clearCallbacks();
                            mSrc.getParent().requestDisallowInterceptTouchEvent(true);
                            return true;
                        }
                        break;
                    }
                    case 1:
                    case 3: {
                        this.clearCallbacks();
                        return false;
                    }
                }
            }
            return false;
        }
        
        private static boolean pointInView(final View view, final float n, final float n2, final float n3) {
            return n >= -n3 && n2 >= -n3 && n < view.getRight() - view.getLeft() + n3 && n2 < view.getBottom() - view.getTop() + n3;
        }
        
        private boolean toGlobalMotionEvent(final View view, final MotionEvent motionEvent) {
            final int[] mTmpLocation = this.mTmpLocation;
            view.getLocationOnScreen(mTmpLocation);
            motionEvent.offsetLocation((float)mTmpLocation[0], (float)mTmpLocation[1]);
            return true;
        }
        
        private boolean toLocalMotionEvent(final View view, final MotionEvent motionEvent) {
            final int[] mTmpLocation = this.mTmpLocation;
            view.getLocationOnScreen(mTmpLocation);
            motionEvent.offsetLocation((float)(-mTmpLocation[0]), (float)(-mTmpLocation[1]));
            return true;
        }
        
        public abstract ListPopupWindow getPopup();
        
        protected boolean onForwardingStarted() {
            final ListPopupWindow popup = this.getPopup();
            if (popup != null && !popup.isShowing()) {
                popup.show();
            }
            return true;
        }
        
        protected boolean onForwardingStopped() {
            final ListPopupWindow popup = this.getPopup();
            if (popup != null && popup.isShowing()) {
                popup.dismiss();
            }
            return true;
        }
        
        public boolean onTouch(final View view, final MotionEvent motionEvent) {
            final boolean b = false;
            final boolean mForwarding = this.mForwarding;
            boolean onTouchForwarded;
            if (mForwarding) {
                if (this.mWasLongPress) {
                    onTouchForwarded = this.onTouchForwarded(motionEvent);
                }
                else {
                    onTouchForwarded = (this.onTouchForwarded(motionEvent) || !this.onForwardingStopped());
                }
            }
            else {
                final boolean b2 = onTouchForwarded = (this.onTouchObserved(motionEvent) && this.onForwardingStarted());
                if (b2) {
                    final long uptimeMillis = SystemClock.uptimeMillis();
                    final MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
                    this.mSrc.onTouchEvent(obtain);
                    obtain.recycle();
                    onTouchForwarded = b2;
                }
            }
            if (!(this.mForwarding = onTouchForwarded)) {
                final boolean b3 = b;
                if (!mForwarding) {
                    return b3;
                }
            }
            return true;
        }
        
        private class DisallowIntercept implements Runnable
        {
            @Override
            public void run() {
                ForwardingListener.this.mSrc.getParent().requestDisallowInterceptTouchEvent(true);
            }
        }
        
        private class TriggerLongPress implements Runnable
        {
            @Override
            public void run() {
                ForwardingListener.this.onLongPress();
            }
        }
    }
    
    private class ListSelectorHider implements Runnable
    {
        @Override
        public void run() {
            ListPopupWindow.this.clearListSelection();
        }
    }
    
    private class PopupDataSetObserver extends DataSetObserver
    {
        public void onChanged() {
            if (ListPopupWindow.this.isShowing()) {
                ListPopupWindow.this.show();
            }
        }
        
        public void onInvalidated() {
            ListPopupWindow.this.dismiss();
        }
    }
    
    private class PopupScrollListener implements AbsListView$OnScrollListener
    {
        public void onScroll(final AbsListView absListView, final int n, final int n2, final int n3) {
        }
        
        public void onScrollStateChanged(final AbsListView absListView, final int n) {
            if (n == 1 && !ListPopupWindow.this.isInputMethodNotNeeded() && ListPopupWindow.this.mPopup.getContentView() != null) {
                ListPopupWindow.this.mHandler.removeCallbacks((Runnable)ListPopupWindow.this.mResizePopupRunnable);
                ListPopupWindow.this.mResizePopupRunnable.run();
            }
        }
    }
    
    private class PopupTouchInterceptor implements View$OnTouchListener
    {
        public boolean onTouch(final View view, final MotionEvent motionEvent) {
            final int action = motionEvent.getAction();
            final int n = (int)motionEvent.getX();
            final int n2 = (int)motionEvent.getY();
            if (action == 0 && ListPopupWindow.this.mPopup != null && ListPopupWindow.this.mPopup.isShowing() && n >= 0 && n < ListPopupWindow.this.mPopup.getWidth() && n2 >= 0 && n2 < ListPopupWindow.this.mPopup.getHeight()) {
                ListPopupWindow.this.mHandler.postDelayed((Runnable)ListPopupWindow.this.mResizePopupRunnable, 250L);
            }
            else if (action == 1) {
                ListPopupWindow.this.mHandler.removeCallbacks((Runnable)ListPopupWindow.this.mResizePopupRunnable);
            }
            return false;
        }
    }
    
    private class ResizePopupRunnable implements Runnable
    {
        @Override
        public void run() {
            if (ListPopupWindow.this.mDropDownList != null && ListPopupWindow.this.mDropDownList.getCount() > ListPopupWindow.this.mDropDownList.getChildCount() && ListPopupWindow.this.mDropDownList.getChildCount() <= ListPopupWindow.this.mListItemExpandMaximum) {
                ListPopupWindow.this.mPopup.setInputMethodMode(2);
                ListPopupWindow.this.show();
            }
        }
    }
}
