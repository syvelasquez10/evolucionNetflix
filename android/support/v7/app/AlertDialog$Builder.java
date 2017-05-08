// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.content.DialogInterface$OnKeyListener;
import android.widget.AdapterView$OnItemSelectedListener;
import android.content.DialogInterface$OnDismissListener;
import android.content.DialogInterface$OnCancelListener;
import android.content.DialogInterface$OnMultiChoiceClickListener;
import android.util.TypedValue;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.database.Cursor;
import android.content.DialogInterface$OnClickListener;
import android.widget.ListAdapter;
import android.view.ContextThemeWrapper;
import android.content.Context;

public class AlertDialog$Builder
{
    private final AlertController$AlertParams P;
    private final int mTheme;
    
    public AlertDialog$Builder(final Context context) {
        this(context, AlertDialog.resolveDialogTheme(context, 0));
    }
    
    public AlertDialog$Builder(final Context context, final int mTheme) {
        this.P = new AlertController$AlertParams((Context)new ContextThemeWrapper(context, AlertDialog.resolveDialogTheme(context, mTheme)));
        this.mTheme = mTheme;
    }
    
    public AlertDialog create() {
        final AlertDialog alertDialog = new AlertDialog(this.P.mContext, this.mTheme);
        this.P.apply(alertDialog.mAlert);
        alertDialog.setCancelable(this.P.mCancelable);
        if (this.P.mCancelable) {
            alertDialog.setCanceledOnTouchOutside(true);
        }
        alertDialog.setOnCancelListener(this.P.mOnCancelListener);
        alertDialog.setOnDismissListener(this.P.mOnDismissListener);
        if (this.P.mOnKeyListener != null) {
            alertDialog.setOnKeyListener(this.P.mOnKeyListener);
        }
        return alertDialog;
    }
    
    public Context getContext() {
        return this.P.mContext;
    }
    
    public AlertDialog$Builder setAdapter(final ListAdapter mAdapter, final DialogInterface$OnClickListener mOnClickListener) {
        this.P.mAdapter = mAdapter;
        this.P.mOnClickListener = mOnClickListener;
        return this;
    }
    
    public AlertDialog$Builder setCancelable(final boolean mCancelable) {
        this.P.mCancelable = mCancelable;
        return this;
    }
    
    public AlertDialog$Builder setCursor(final Cursor mCursor, final DialogInterface$OnClickListener mOnClickListener, final String mLabelColumn) {
        this.P.mCursor = mCursor;
        this.P.mLabelColumn = mLabelColumn;
        this.P.mOnClickListener = mOnClickListener;
        return this;
    }
    
    public AlertDialog$Builder setCustomTitle(final View mCustomTitleView) {
        this.P.mCustomTitleView = mCustomTitleView;
        return this;
    }
    
    public AlertDialog$Builder setIcon(final int mIconId) {
        this.P.mIconId = mIconId;
        return this;
    }
    
    public AlertDialog$Builder setIcon(final Drawable mIcon) {
        this.P.mIcon = mIcon;
        return this;
    }
    
    public AlertDialog$Builder setIconAttribute(final int n) {
        final TypedValue typedValue = new TypedValue();
        this.P.mContext.getTheme().resolveAttribute(n, typedValue, true);
        this.P.mIconId = typedValue.resourceId;
        return this;
    }
    
    @Deprecated
    public AlertDialog$Builder setInverseBackgroundForced(final boolean mForceInverseBackground) {
        this.P.mForceInverseBackground = mForceInverseBackground;
        return this;
    }
    
    public AlertDialog$Builder setItems(final int n, final DialogInterface$OnClickListener mOnClickListener) {
        this.P.mItems = this.P.mContext.getResources().getTextArray(n);
        this.P.mOnClickListener = mOnClickListener;
        return this;
    }
    
    public AlertDialog$Builder setItems(final CharSequence[] mItems, final DialogInterface$OnClickListener mOnClickListener) {
        this.P.mItems = mItems;
        this.P.mOnClickListener = mOnClickListener;
        return this;
    }
    
    public AlertDialog$Builder setMessage(final int n) {
        this.P.mMessage = this.P.mContext.getText(n);
        return this;
    }
    
    public AlertDialog$Builder setMessage(final CharSequence mMessage) {
        this.P.mMessage = mMessage;
        return this;
    }
    
    public AlertDialog$Builder setMultiChoiceItems(final int n, final boolean[] mCheckedItems, final DialogInterface$OnMultiChoiceClickListener mOnCheckboxClickListener) {
        this.P.mItems = this.P.mContext.getResources().getTextArray(n);
        this.P.mOnCheckboxClickListener = mOnCheckboxClickListener;
        this.P.mCheckedItems = mCheckedItems;
        this.P.mIsMultiChoice = true;
        return this;
    }
    
    public AlertDialog$Builder setMultiChoiceItems(final Cursor mCursor, final String mIsCheckedColumn, final String mLabelColumn, final DialogInterface$OnMultiChoiceClickListener mOnCheckboxClickListener) {
        this.P.mCursor = mCursor;
        this.P.mOnCheckboxClickListener = mOnCheckboxClickListener;
        this.P.mIsCheckedColumn = mIsCheckedColumn;
        this.P.mLabelColumn = mLabelColumn;
        this.P.mIsMultiChoice = true;
        return this;
    }
    
    public AlertDialog$Builder setMultiChoiceItems(final CharSequence[] mItems, final boolean[] mCheckedItems, final DialogInterface$OnMultiChoiceClickListener mOnCheckboxClickListener) {
        this.P.mItems = mItems;
        this.P.mOnCheckboxClickListener = mOnCheckboxClickListener;
        this.P.mCheckedItems = mCheckedItems;
        this.P.mIsMultiChoice = true;
        return this;
    }
    
    public AlertDialog$Builder setNegativeButton(final int n, final DialogInterface$OnClickListener mNegativeButtonListener) {
        this.P.mNegativeButtonText = this.P.mContext.getText(n);
        this.P.mNegativeButtonListener = mNegativeButtonListener;
        return this;
    }
    
    public AlertDialog$Builder setNegativeButton(final CharSequence mNegativeButtonText, final DialogInterface$OnClickListener mNegativeButtonListener) {
        this.P.mNegativeButtonText = mNegativeButtonText;
        this.P.mNegativeButtonListener = mNegativeButtonListener;
        return this;
    }
    
    public AlertDialog$Builder setNeutralButton(final int n, final DialogInterface$OnClickListener mNeutralButtonListener) {
        this.P.mNeutralButtonText = this.P.mContext.getText(n);
        this.P.mNeutralButtonListener = mNeutralButtonListener;
        return this;
    }
    
    public AlertDialog$Builder setNeutralButton(final CharSequence mNeutralButtonText, final DialogInterface$OnClickListener mNeutralButtonListener) {
        this.P.mNeutralButtonText = mNeutralButtonText;
        this.P.mNeutralButtonListener = mNeutralButtonListener;
        return this;
    }
    
    public AlertDialog$Builder setOnCancelListener(final DialogInterface$OnCancelListener mOnCancelListener) {
        this.P.mOnCancelListener = mOnCancelListener;
        return this;
    }
    
    public AlertDialog$Builder setOnDismissListener(final DialogInterface$OnDismissListener mOnDismissListener) {
        this.P.mOnDismissListener = mOnDismissListener;
        return this;
    }
    
    public AlertDialog$Builder setOnItemSelectedListener(final AdapterView$OnItemSelectedListener mOnItemSelectedListener) {
        this.P.mOnItemSelectedListener = mOnItemSelectedListener;
        return this;
    }
    
    public AlertDialog$Builder setOnKeyListener(final DialogInterface$OnKeyListener mOnKeyListener) {
        this.P.mOnKeyListener = mOnKeyListener;
        return this;
    }
    
    public AlertDialog$Builder setPositiveButton(final int n, final DialogInterface$OnClickListener mPositiveButtonListener) {
        this.P.mPositiveButtonText = this.P.mContext.getText(n);
        this.P.mPositiveButtonListener = mPositiveButtonListener;
        return this;
    }
    
    public AlertDialog$Builder setPositiveButton(final CharSequence mPositiveButtonText, final DialogInterface$OnClickListener mPositiveButtonListener) {
        this.P.mPositiveButtonText = mPositiveButtonText;
        this.P.mPositiveButtonListener = mPositiveButtonListener;
        return this;
    }
    
    public AlertDialog$Builder setRecycleOnMeasureEnabled(final boolean mRecycleOnMeasure) {
        this.P.mRecycleOnMeasure = mRecycleOnMeasure;
        return this;
    }
    
    public AlertDialog$Builder setSingleChoiceItems(final int n, final int mCheckedItem, final DialogInterface$OnClickListener mOnClickListener) {
        this.P.mItems = this.P.mContext.getResources().getTextArray(n);
        this.P.mOnClickListener = mOnClickListener;
        this.P.mCheckedItem = mCheckedItem;
        this.P.mIsSingleChoice = true;
        return this;
    }
    
    public AlertDialog$Builder setSingleChoiceItems(final Cursor mCursor, final int mCheckedItem, final String mLabelColumn, final DialogInterface$OnClickListener mOnClickListener) {
        this.P.mCursor = mCursor;
        this.P.mOnClickListener = mOnClickListener;
        this.P.mCheckedItem = mCheckedItem;
        this.P.mLabelColumn = mLabelColumn;
        this.P.mIsSingleChoice = true;
        return this;
    }
    
    public AlertDialog$Builder setSingleChoiceItems(final ListAdapter mAdapter, final int mCheckedItem, final DialogInterface$OnClickListener mOnClickListener) {
        this.P.mAdapter = mAdapter;
        this.P.mOnClickListener = mOnClickListener;
        this.P.mCheckedItem = mCheckedItem;
        this.P.mIsSingleChoice = true;
        return this;
    }
    
    public AlertDialog$Builder setSingleChoiceItems(final CharSequence[] mItems, final int mCheckedItem, final DialogInterface$OnClickListener mOnClickListener) {
        this.P.mItems = mItems;
        this.P.mOnClickListener = mOnClickListener;
        this.P.mCheckedItem = mCheckedItem;
        this.P.mIsSingleChoice = true;
        return this;
    }
    
    public AlertDialog$Builder setTitle(final int n) {
        this.P.mTitle = this.P.mContext.getText(n);
        return this;
    }
    
    public AlertDialog$Builder setTitle(final CharSequence mTitle) {
        this.P.mTitle = mTitle;
        return this;
    }
    
    public AlertDialog$Builder setView(final int mViewLayoutResId) {
        this.P.mView = null;
        this.P.mViewLayoutResId = mViewLayoutResId;
        this.P.mViewSpacingSpecified = false;
        return this;
    }
    
    public AlertDialog$Builder setView(final View mView) {
        this.P.mView = mView;
        this.P.mViewLayoutResId = 0;
        this.P.mViewSpacingSpecified = false;
        return this;
    }
    
    @Deprecated
    public AlertDialog$Builder setView(final View mView, final int mViewSpacingLeft, final int mViewSpacingTop, final int mViewSpacingRight, final int mViewSpacingBottom) {
        this.P.mView = mView;
        this.P.mViewLayoutResId = 0;
        this.P.mViewSpacingSpecified = true;
        this.P.mViewSpacingLeft = mViewSpacingLeft;
        this.P.mViewSpacingTop = mViewSpacingTop;
        this.P.mViewSpacingRight = mViewSpacingRight;
        this.P.mViewSpacingBottom = mViewSpacingBottom;
        return this;
    }
    
    public AlertDialog show() {
        final AlertDialog create = this.create();
        create.show();
        return create;
    }
}
