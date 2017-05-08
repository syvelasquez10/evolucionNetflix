// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.os.Message;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.SimpleCursorAdapter;
import android.view.ViewGroup;
import android.widget.ListView;
import android.content.DialogInterface$OnKeyListener;
import android.widget.AdapterView$OnItemSelectedListener;
import android.content.DialogInterface$OnDismissListener;
import android.content.DialogInterface$OnMultiChoiceClickListener;
import android.content.DialogInterface$OnCancelListener;
import android.content.DialogInterface$OnClickListener;
import android.view.LayoutInflater;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.database.Cursor;
import android.content.Context;
import android.widget.ListAdapter;

public class AlertController$AlertParams
{
    public ListAdapter mAdapter;
    public boolean mCancelable;
    public int mCheckedItem;
    public boolean[] mCheckedItems;
    public final Context mContext;
    public Cursor mCursor;
    public View mCustomTitleView;
    public boolean mForceInverseBackground;
    public Drawable mIcon;
    public int mIconAttrId;
    public int mIconId;
    public final LayoutInflater mInflater;
    public String mIsCheckedColumn;
    public boolean mIsMultiChoice;
    public boolean mIsSingleChoice;
    public CharSequence[] mItems;
    public String mLabelColumn;
    public CharSequence mMessage;
    public DialogInterface$OnClickListener mNegativeButtonListener;
    public CharSequence mNegativeButtonText;
    public DialogInterface$OnClickListener mNeutralButtonListener;
    public CharSequence mNeutralButtonText;
    public DialogInterface$OnCancelListener mOnCancelListener;
    public DialogInterface$OnMultiChoiceClickListener mOnCheckboxClickListener;
    public DialogInterface$OnClickListener mOnClickListener;
    public DialogInterface$OnDismissListener mOnDismissListener;
    public AdapterView$OnItemSelectedListener mOnItemSelectedListener;
    public DialogInterface$OnKeyListener mOnKeyListener;
    public AlertController$AlertParams$OnPrepareListViewListener mOnPrepareListViewListener;
    public DialogInterface$OnClickListener mPositiveButtonListener;
    public CharSequence mPositiveButtonText;
    public boolean mRecycleOnMeasure;
    public CharSequence mTitle;
    public View mView;
    public int mViewLayoutResId;
    public int mViewSpacingBottom;
    public int mViewSpacingLeft;
    public int mViewSpacingRight;
    public boolean mViewSpacingSpecified;
    public int mViewSpacingTop;
    
    public AlertController$AlertParams(final Context mContext) {
        this.mIconId = 0;
        this.mIconAttrId = 0;
        this.mViewSpacingSpecified = false;
        this.mCheckedItem = -1;
        this.mRecycleOnMeasure = true;
        this.mContext = mContext;
        this.mCancelable = true;
        this.mInflater = (LayoutInflater)mContext.getSystemService("layout_inflater");
    }
    
    private void createListView(final AlertController alertController) {
        final ListView mListView = (ListView)this.mInflater.inflate(alertController.mListLayout, (ViewGroup)null);
        Object mAdapter;
        if (this.mIsMultiChoice) {
            if (this.mCursor == null) {
                mAdapter = new AlertController$AlertParams$1(this, this.mContext, alertController.mMultiChoiceItemLayout, 16908308, this.mItems, mListView);
            }
            else {
                mAdapter = new AlertController$AlertParams$2(this, this.mContext, this.mCursor, false, mListView, alertController);
            }
        }
        else {
            int n;
            if (this.mIsSingleChoice) {
                n = alertController.mSingleChoiceItemLayout;
            }
            else {
                n = alertController.mListItemLayout;
            }
            if (this.mCursor != null) {
                mAdapter = new SimpleCursorAdapter(this.mContext, n, this.mCursor, new String[] { this.mLabelColumn }, new int[] { 16908308 });
            }
            else if (this.mAdapter != null) {
                mAdapter = this.mAdapter;
            }
            else {
                mAdapter = new AlertController$CheckedItemAdapter(this.mContext, n, 16908308, this.mItems);
            }
        }
        if (this.mOnPrepareListViewListener != null) {
            this.mOnPrepareListViewListener.onPrepareListView(mListView);
        }
        alertController.mAdapter = (ListAdapter)mAdapter;
        alertController.mCheckedItem = this.mCheckedItem;
        if (this.mOnClickListener != null) {
            mListView.setOnItemClickListener((AdapterView$OnItemClickListener)new AlertController$AlertParams$3(this, alertController));
        }
        else if (this.mOnCheckboxClickListener != null) {
            mListView.setOnItemClickListener((AdapterView$OnItemClickListener)new AlertController$AlertParams$4(this, mListView, alertController));
        }
        if (this.mOnItemSelectedListener != null) {
            mListView.setOnItemSelectedListener(this.mOnItemSelectedListener);
        }
        if (this.mIsSingleChoice) {
            mListView.setChoiceMode(1);
        }
        else if (this.mIsMultiChoice) {
            mListView.setChoiceMode(2);
        }
        alertController.mListView = mListView;
    }
    
    public void apply(final AlertController alertController) {
        if (this.mCustomTitleView != null) {
            alertController.setCustomTitle(this.mCustomTitleView);
        }
        else {
            if (this.mTitle != null) {
                alertController.setTitle(this.mTitle);
            }
            if (this.mIcon != null) {
                alertController.setIcon(this.mIcon);
            }
            if (this.mIconId != 0) {
                alertController.setIcon(this.mIconId);
            }
            if (this.mIconAttrId != 0) {
                alertController.setIcon(alertController.getIconAttributeResId(this.mIconAttrId));
            }
        }
        if (this.mMessage != null) {
            alertController.setMessage(this.mMessage);
        }
        if (this.mPositiveButtonText != null) {
            alertController.setButton(-1, this.mPositiveButtonText, this.mPositiveButtonListener, null);
        }
        if (this.mNegativeButtonText != null) {
            alertController.setButton(-2, this.mNegativeButtonText, this.mNegativeButtonListener, null);
        }
        if (this.mNeutralButtonText != null) {
            alertController.setButton(-3, this.mNeutralButtonText, this.mNeutralButtonListener, null);
        }
        if (this.mItems != null || this.mCursor != null || this.mAdapter != null) {
            this.createListView(alertController);
        }
        if (this.mView != null) {
            if (!this.mViewSpacingSpecified) {
                alertController.setView(this.mView);
                return;
            }
            alertController.setView(this.mView, this.mViewSpacingLeft, this.mViewSpacingTop, this.mViewSpacingRight, this.mViewSpacingBottom);
        }
        else if (this.mViewLayoutResId != 0) {
            alertController.setView(this.mViewLayoutResId);
        }
    }
}
