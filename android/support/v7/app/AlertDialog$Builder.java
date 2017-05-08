// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.view.KeyEvent;
import android.os.Bundle;
import android.support.v7.appcompat.R$attr;
import android.util.TypedValue;
import android.content.DialogInterface;
import android.content.DialogInterface$OnKeyListener;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.content.DialogInterface$OnClickListener;
import android.widget.ListAdapter;
import android.view.ContextThemeWrapper;
import android.content.Context;

public class AlertDialog$Builder
{
    private final AlertController$AlertParams P;
    private int mTheme;
    
    public AlertDialog$Builder(final Context context) {
        this(context, AlertDialog.resolveDialogTheme(context, 0));
    }
    
    public AlertDialog$Builder(final Context context, final int mTheme) {
        this.P = new AlertController$AlertParams((Context)new ContextThemeWrapper(context, AlertDialog.resolveDialogTheme(context, mTheme)));
        this.mTheme = mTheme;
    }
    
    public AlertDialog create() {
        final AlertDialog alertDialog = new AlertDialog(this.P.mContext, this.mTheme, false);
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
    
    public AlertDialog$Builder setCustomTitle(final View mCustomTitleView) {
        this.P.mCustomTitleView = mCustomTitleView;
        return this;
    }
    
    public AlertDialog$Builder setIcon(final Drawable mIcon) {
        this.P.mIcon = mIcon;
        return this;
    }
    
    public AlertDialog$Builder setOnKeyListener(final DialogInterface$OnKeyListener mOnKeyListener) {
        this.P.mOnKeyListener = mOnKeyListener;
        return this;
    }
    
    public AlertDialog$Builder setSingleChoiceItems(final CharSequence[] mItems, final int mCheckedItem, final DialogInterface$OnClickListener mOnClickListener) {
        this.P.mItems = mItems;
        this.P.mOnClickListener = mOnClickListener;
        this.P.mCheckedItem = mCheckedItem;
        this.P.mIsSingleChoice = true;
        return this;
    }
    
    public AlertDialog$Builder setTitle(final CharSequence mTitle) {
        this.P.mTitle = mTitle;
        return this;
    }
    
    public AlertDialog show() {
        final AlertDialog create = this.create();
        create.show();
        return create;
    }
}
