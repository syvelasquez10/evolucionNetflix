// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.content.DialogInterface$OnClickListener;
import android.view.KeyEvent;
import android.util.TypedValue;
import android.widget.LinearLayout$LayoutParams;
import android.widget.FrameLayout;
import android.view.LayoutInflater;
import android.view.ViewGroup$LayoutParams;
import android.text.TextUtils;
import android.widget.AbsListView$OnScrollListener;
import android.support.v4.widget.NestedScrollView$OnScrollChangeListener;
import android.os.Build$VERSION;
import android.support.v7.appcompat.R$id;
import android.view.ViewParent;
import android.view.ViewStub;
import android.support.v4.view.ViewCompat;
import android.view.ViewGroup;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.support.v7.appcompat.R$attr;
import android.support.v7.appcompat.R$styleable;
import android.content.DialogInterface;
import android.view.Window;
import android.support.v4.widget.NestedScrollView;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.ImageView;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.View;
import android.content.Context;
import android.os.Message;
import android.widget.Button;
import android.view.View$OnClickListener;
import android.widget.ListAdapter;

class AlertController
{
    ListAdapter mAdapter;
    private int mAlertDialogLayout;
    private final View$OnClickListener mButtonHandler;
    Button mButtonNegative;
    Message mButtonNegativeMessage;
    private CharSequence mButtonNegativeText;
    Button mButtonNeutral;
    Message mButtonNeutralMessage;
    private CharSequence mButtonNeutralText;
    private int mButtonPanelLayoutHint;
    private int mButtonPanelSideLayout;
    Button mButtonPositive;
    Message mButtonPositiveMessage;
    private CharSequence mButtonPositiveText;
    int mCheckedItem;
    private final Context mContext;
    private View mCustomTitleView;
    final AppCompatDialog mDialog;
    Handler mHandler;
    private Drawable mIcon;
    private int mIconId;
    private ImageView mIconView;
    int mListItemLayout;
    int mListLayout;
    ListView mListView;
    private CharSequence mMessage;
    private TextView mMessageView;
    int mMultiChoiceItemLayout;
    NestedScrollView mScrollView;
    int mSingleChoiceItemLayout;
    private CharSequence mTitle;
    private TextView mTitleView;
    private View mView;
    private int mViewLayoutResId;
    private int mViewSpacingBottom;
    private int mViewSpacingLeft;
    private int mViewSpacingRight;
    private boolean mViewSpacingSpecified;
    private int mViewSpacingTop;
    private final Window mWindow;
    
    public AlertController(final Context mContext, final AppCompatDialog mDialog, final Window mWindow) {
        this.mViewSpacingSpecified = false;
        this.mIconId = 0;
        this.mCheckedItem = -1;
        this.mButtonPanelLayoutHint = 0;
        this.mButtonHandler = (View$OnClickListener)new AlertController$1(this);
        this.mContext = mContext;
        this.mDialog = mDialog;
        this.mWindow = mWindow;
        this.mHandler = new AlertController$ButtonHandler((DialogInterface)mDialog);
        final TypedArray obtainStyledAttributes = mContext.obtainStyledAttributes((AttributeSet)null, R$styleable.AlertDialog, R$attr.alertDialogStyle, 0);
        this.mAlertDialogLayout = obtainStyledAttributes.getResourceId(R$styleable.AlertDialog_android_layout, 0);
        this.mButtonPanelSideLayout = obtainStyledAttributes.getResourceId(R$styleable.AlertDialog_buttonPanelSideLayout, 0);
        this.mListLayout = obtainStyledAttributes.getResourceId(R$styleable.AlertDialog_listLayout, 0);
        this.mMultiChoiceItemLayout = obtainStyledAttributes.getResourceId(R$styleable.AlertDialog_multiChoiceItemLayout, 0);
        this.mSingleChoiceItemLayout = obtainStyledAttributes.getResourceId(R$styleable.AlertDialog_singleChoiceItemLayout, 0);
        this.mListItemLayout = obtainStyledAttributes.getResourceId(R$styleable.AlertDialog_listItemLayout, 0);
        obtainStyledAttributes.recycle();
        mDialog.supportRequestWindowFeature(1);
    }
    
    static boolean canTextInput(final View view) {
        if (view.onCheckIsTextEditor()) {
            return true;
        }
        if (!(view instanceof ViewGroup)) {
            return false;
        }
        final ViewGroup viewGroup = (ViewGroup)view;
        int i = viewGroup.getChildCount();
        while (i > 0) {
            if (canTextInput(viewGroup.getChildAt(--i))) {
                return true;
            }
        }
        return false;
    }
    
    static void manageScrollIndicators(final View view, final View view2, final View view3) {
        final boolean b = false;
        if (view2 != null) {
            int visibility;
            if (ViewCompat.canScrollVertically(view, -1)) {
                visibility = 0;
            }
            else {
                visibility = 4;
            }
            view2.setVisibility(visibility);
        }
        if (view3 != null) {
            int visibility2;
            if (ViewCompat.canScrollVertically(view, 1)) {
                visibility2 = (b ? 1 : 0);
            }
            else {
                visibility2 = 4;
            }
            view3.setVisibility(visibility2);
        }
    }
    
    private ViewGroup resolvePanel(View view, final View view2) {
        if (view == null) {
            if (view2 instanceof ViewStub) {
                view = ((ViewStub)view2).inflate();
            }
            else {
                view = view2;
            }
            return (ViewGroup)view;
        }
        if (view2 != null) {
            final ViewParent parent = view2.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup)parent).removeView(view2);
            }
        }
        if (view instanceof ViewStub) {
            view = ((ViewStub)view).inflate();
        }
        return (ViewGroup)view;
    }
    
    private int selectContentView() {
        if (this.mButtonPanelSideLayout == 0) {
            return this.mAlertDialogLayout;
        }
        if (this.mButtonPanelLayoutHint == 1) {
            return this.mButtonPanelSideLayout;
        }
        return this.mAlertDialogLayout;
    }
    
    private void setScrollIndicators(final ViewGroup viewGroup, View view, final int n, final int n2) {
        View view2 = null;
        final View viewById = this.mWindow.findViewById(R$id.scrollIndicatorUp);
        final View viewById2 = this.mWindow.findViewById(R$id.scrollIndicatorDown);
        if (Build$VERSION.SDK_INT >= 23) {
            ViewCompat.setScrollIndicators(view, n, n2);
            if (viewById != null) {
                viewGroup.removeView(viewById);
            }
            if (viewById2 != null) {
                viewGroup.removeView(viewById2);
            }
        }
        else {
            if ((view = viewById) != null) {
                view = viewById;
                if ((n & 0x1) == 0x0) {
                    viewGroup.removeView(viewById);
                    view = null;
                }
            }
            if (viewById2 != null && (n & 0x2) == 0x0) {
                viewGroup.removeView(viewById2);
            }
            else {
                view2 = viewById2;
            }
            if (view != null || view2 != null) {
                if (this.mMessage != null) {
                    this.mScrollView.setOnScrollChangeListener(new AlertController$2(this, view, view2));
                    this.mScrollView.post((Runnable)new AlertController$3(this, view, view2));
                    return;
                }
                if (this.mListView != null) {
                    this.mListView.setOnScrollListener((AbsListView$OnScrollListener)new AlertController$4(this, view, view2));
                    this.mListView.post((Runnable)new AlertController$5(this, view, view2));
                    return;
                }
                if (view != null) {
                    viewGroup.removeView(view);
                }
                if (view2 != null) {
                    viewGroup.removeView(view2);
                }
            }
        }
    }
    
    private void setupButtons(final ViewGroup viewGroup) {
        final boolean b = true;
        (this.mButtonPositive = (Button)viewGroup.findViewById(16908313)).setOnClickListener(this.mButtonHandler);
        int n;
        if (TextUtils.isEmpty(this.mButtonPositiveText)) {
            this.mButtonPositive.setVisibility(8);
            n = 0;
        }
        else {
            this.mButtonPositive.setText(this.mButtonPositiveText);
            this.mButtonPositive.setVisibility(0);
            n = 1;
        }
        (this.mButtonNegative = (Button)viewGroup.findViewById(16908314)).setOnClickListener(this.mButtonHandler);
        if (TextUtils.isEmpty(this.mButtonNegativeText)) {
            this.mButtonNegative.setVisibility(8);
        }
        else {
            this.mButtonNegative.setText(this.mButtonNegativeText);
            this.mButtonNegative.setVisibility(0);
            n |= 0x2;
        }
        (this.mButtonNeutral = (Button)viewGroup.findViewById(16908315)).setOnClickListener(this.mButtonHandler);
        if (TextUtils.isEmpty(this.mButtonNeutralText)) {
            this.mButtonNeutral.setVisibility(8);
        }
        else {
            this.mButtonNeutral.setText(this.mButtonNeutralText);
            this.mButtonNeutral.setVisibility(0);
            n |= 0x4;
        }
        if (n == 0 || !b) {
            viewGroup.setVisibility(8);
        }
    }
    
    private void setupContent(ViewGroup viewGroup) {
        (this.mScrollView = (NestedScrollView)this.mWindow.findViewById(R$id.scrollView)).setFocusable(false);
        this.mScrollView.setNestedScrollingEnabled(false);
        this.mMessageView = (TextView)viewGroup.findViewById(16908299);
        if (this.mMessageView == null) {
            return;
        }
        if (this.mMessage != null) {
            this.mMessageView.setText(this.mMessage);
            return;
        }
        this.mMessageView.setVisibility(8);
        this.mScrollView.removeView((View)this.mMessageView);
        if (this.mListView != null) {
            viewGroup = (ViewGroup)this.mScrollView.getParent();
            final int indexOfChild = viewGroup.indexOfChild((View)this.mScrollView);
            viewGroup.removeViewAt(indexOfChild);
            viewGroup.addView((View)this.mListView, indexOfChild, new ViewGroup$LayoutParams(-1, -1));
            return;
        }
        viewGroup.setVisibility(8);
    }
    
    private void setupCustomContent(final ViewGroup viewGroup) {
        boolean b = false;
        View view;
        if (this.mView != null) {
            view = this.mView;
        }
        else if (this.mViewLayoutResId != 0) {
            view = LayoutInflater.from(this.mContext).inflate(this.mViewLayoutResId, viewGroup, false);
        }
        else {
            view = null;
        }
        if (view != null) {
            b = true;
        }
        if (!b || !canTextInput(view)) {
            this.mWindow.setFlags(131072, 131072);
        }
        if (b) {
            final FrameLayout frameLayout = (FrameLayout)this.mWindow.findViewById(R$id.custom);
            frameLayout.addView(view, new ViewGroup$LayoutParams(-1, -1));
            if (this.mViewSpacingSpecified) {
                frameLayout.setPadding(this.mViewSpacingLeft, this.mViewSpacingTop, this.mViewSpacingRight, this.mViewSpacingBottom);
            }
            if (this.mListView != null) {
                ((LinearLayout$LayoutParams)viewGroup.getLayoutParams()).weight = 0.0f;
            }
            return;
        }
        viewGroup.setVisibility(8);
    }
    
    private void setupTitle(final ViewGroup viewGroup) {
        if (this.mCustomTitleView != null) {
            viewGroup.addView(this.mCustomTitleView, 0, new ViewGroup$LayoutParams(-1, -2));
            this.mWindow.findViewById(R$id.title_template).setVisibility(8);
            return;
        }
        this.mIconView = (ImageView)this.mWindow.findViewById(16908294);
        int n;
        if (!TextUtils.isEmpty(this.mTitle)) {
            n = 1;
        }
        else {
            n = 0;
        }
        if (n == 0) {
            this.mWindow.findViewById(R$id.title_template).setVisibility(8);
            this.mIconView.setVisibility(8);
            viewGroup.setVisibility(8);
            return;
        }
        (this.mTitleView = (TextView)this.mWindow.findViewById(R$id.alertTitle)).setText(this.mTitle);
        if (this.mIconId != 0) {
            this.mIconView.setImageResource(this.mIconId);
            return;
        }
        if (this.mIcon != null) {
            this.mIconView.setImageDrawable(this.mIcon);
            return;
        }
        this.mTitleView.setPadding(this.mIconView.getPaddingLeft(), this.mIconView.getPaddingTop(), this.mIconView.getPaddingRight(), this.mIconView.getPaddingBottom());
        this.mIconView.setVisibility(8);
    }
    
    private void setupView() {
        final View viewById = this.mWindow.findViewById(R$id.parentPanel);
        final View viewById2 = viewById.findViewById(R$id.topPanel);
        final View viewById3 = viewById.findViewById(R$id.contentPanel);
        final View viewById4 = viewById.findViewById(R$id.buttonPanel);
        final ViewGroup viewGroup = (ViewGroup)viewById.findViewById(R$id.customPanel);
        this.setupCustomContent(viewGroup);
        final View viewById5 = viewGroup.findViewById(R$id.topPanel);
        final View viewById6 = viewGroup.findViewById(R$id.contentPanel);
        final View viewById7 = viewGroup.findViewById(R$id.buttonPanel);
        final ViewGroup resolvePanel = this.resolvePanel(viewById5, viewById2);
        final ViewGroup resolvePanel2 = this.resolvePanel(viewById6, viewById3);
        final ViewGroup resolvePanel3 = this.resolvePanel(viewById7, viewById4);
        this.setupContent(resolvePanel2);
        this.setupButtons(resolvePanel3);
        this.setupTitle(resolvePanel);
        int n;
        if (viewGroup != null && viewGroup.getVisibility() != 8) {
            n = 1;
        }
        else {
            n = 0;
        }
        boolean b;
        if (resolvePanel != null && resolvePanel.getVisibility() != 8) {
            b = true;
        }
        else {
            b = false;
        }
        boolean b2;
        if (resolvePanel3 != null && resolvePanel3.getVisibility() != 8) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        if (!b2 && resolvePanel2 != null) {
            final View viewById8 = resolvePanel2.findViewById(R$id.textSpacerNoButtons);
            if (viewById8 != null) {
                viewById8.setVisibility(0);
            }
        }
        if (b && this.mScrollView != null) {
            this.mScrollView.setClipToPadding(true);
        }
        if (n == 0) {
            Object o;
            if (this.mListView != null) {
                o = this.mListView;
            }
            else {
                o = this.mScrollView;
            }
            if (o != null) {
                boolean b3;
                if (b) {
                    b3 = true;
                }
                else {
                    b3 = false;
                }
                int n2;
                if (b2) {
                    n2 = 2;
                }
                else {
                    n2 = 0;
                }
                this.setScrollIndicators(resolvePanel2, (View)o, n2 | (b3 ? 1 : 0), 3);
            }
        }
        final ListView mListView = this.mListView;
        if (mListView != null && this.mAdapter != null) {
            mListView.setAdapter(this.mAdapter);
            final int mCheckedItem = this.mCheckedItem;
            if (mCheckedItem > -1) {
                mListView.setItemChecked(mCheckedItem, true);
                mListView.setSelection(mCheckedItem);
            }
        }
    }
    
    public Button getButton(final int n) {
        switch (n) {
            default: {
                return null;
            }
            case -1: {
                return this.mButtonPositive;
            }
            case -2: {
                return this.mButtonNegative;
            }
            case -3: {
                return this.mButtonNeutral;
            }
        }
    }
    
    public int getIconAttributeResId(final int n) {
        final TypedValue typedValue = new TypedValue();
        this.mContext.getTheme().resolveAttribute(n, typedValue, true);
        return typedValue.resourceId;
    }
    
    public ListView getListView() {
        return this.mListView;
    }
    
    public void installContent() {
        this.mDialog.setContentView(this.selectContentView());
        this.setupView();
    }
    
    public boolean onKeyDown(final int n, final KeyEvent keyEvent) {
        return this.mScrollView != null && this.mScrollView.executeKeyEvent(keyEvent);
    }
    
    public boolean onKeyUp(final int n, final KeyEvent keyEvent) {
        return this.mScrollView != null && this.mScrollView.executeKeyEvent(keyEvent);
    }
    
    public void setButton(final int n, final CharSequence mButtonNeutralText, final DialogInterface$OnClickListener dialogInterface$OnClickListener, final Message message) {
        Message obtainMessage = message;
        if (message == null) {
            obtainMessage = message;
            if (dialogInterface$OnClickListener != null) {
                obtainMessage = this.mHandler.obtainMessage(n, (Object)dialogInterface$OnClickListener);
            }
        }
        switch (n) {
            default: {
                throw new IllegalArgumentException("Button does not exist");
            }
            case -1: {
                this.mButtonPositiveText = mButtonNeutralText;
                this.mButtonPositiveMessage = obtainMessage;
            }
            case -2: {
                this.mButtonNegativeText = mButtonNeutralText;
                this.mButtonNegativeMessage = obtainMessage;
            }
            case -3: {
                this.mButtonNeutralText = mButtonNeutralText;
                this.mButtonNeutralMessage = obtainMessage;
            }
        }
    }
    
    public void setButtonPanelLayoutHint(final int mButtonPanelLayoutHint) {
        this.mButtonPanelLayoutHint = mButtonPanelLayoutHint;
    }
    
    public void setCustomTitle(final View mCustomTitleView) {
        this.mCustomTitleView = mCustomTitleView;
    }
    
    public void setIcon(final int mIconId) {
        this.mIcon = null;
        this.mIconId = mIconId;
        if (this.mIconView != null) {
            if (mIconId == 0) {
                this.mIconView.setVisibility(8);
                return;
            }
            this.mIconView.setVisibility(0);
            this.mIconView.setImageResource(this.mIconId);
        }
    }
    
    public void setIcon(final Drawable drawable) {
        this.mIcon = drawable;
        this.mIconId = 0;
        if (this.mIconView != null) {
            if (drawable == null) {
                this.mIconView.setVisibility(8);
                return;
            }
            this.mIconView.setVisibility(0);
            this.mIconView.setImageDrawable(drawable);
        }
    }
    
    public void setMessage(final CharSequence charSequence) {
        this.mMessage = charSequence;
        if (this.mMessageView != null) {
            this.mMessageView.setText(charSequence);
        }
    }
    
    public void setTitle(final CharSequence charSequence) {
        this.mTitle = charSequence;
        if (this.mTitleView != null) {
            this.mTitleView.setText(charSequence);
        }
    }
    
    public void setView(final int mViewLayoutResId) {
        this.mView = null;
        this.mViewLayoutResId = mViewLayoutResId;
        this.mViewSpacingSpecified = false;
    }
    
    public void setView(final View mView) {
        this.mView = mView;
        this.mViewLayoutResId = 0;
        this.mViewSpacingSpecified = false;
    }
    
    public void setView(final View mView, final int mViewSpacingLeft, final int mViewSpacingTop, final int mViewSpacingRight, final int mViewSpacingBottom) {
        this.mView = mView;
        this.mViewLayoutResId = 0;
        this.mViewSpacingSpecified = true;
        this.mViewSpacingLeft = mViewSpacingLeft;
        this.mViewSpacingTop = mViewSpacingTop;
        this.mViewSpacingRight = mViewSpacingRight;
        this.mViewSpacingBottom = mViewSpacingBottom;
    }
}
