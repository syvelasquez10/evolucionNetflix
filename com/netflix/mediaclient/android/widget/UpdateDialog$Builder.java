// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.app.AlertDialog;
import android.content.DialogInterface$OnKeyListener;
import android.content.DialogInterface$OnCancelListener;
import android.content.DialogInterface$OnClickListener;
import android.graphics.drawable.Drawable;
import android.content.Context;

public class UpdateDialog$Builder
{
    private UpdateDialog$AlertParams params;
    
    public UpdateDialog$Builder(final Context context) {
        this.params = new UpdateDialog$AlertParams(context, null);
    }
    
    public UpdateDialog create() {
        final UpdateDialog updateDialog = new UpdateDialog(this.params.mContext);
        updateDialog.setCancelable(this.params.mCancelable);
        updateDialog.setOnCancelListener(this.params.mOnCancelListener);
        updateDialog.setOnKeyListener(this.params.mOnKeyListener);
        if (this.params.mTitle != null) {
            updateDialog.setTitle(this.params.mTitle);
        }
        if (this.params.mIcon != null) {
            updateDialog.setIcon(this.params.mIcon);
        }
        if (this.params.mIconId >= 0) {
            updateDialog.setIcon(this.params.mIconId);
        }
        if (this.params.mMessage != null) {
            updateDialog.setMessage(this.params.mMessage);
        }
        if (this.params.mPositiveButtonText != null) {
            updateDialog.setButton(-1, this.params.mPositiveButtonText, this.params.mPositiveButtonListener);
        }
        if (this.params.mNegativeButtonText != null) {
            updateDialog.setButton(-2, this.params.mNegativeButtonText, this.params.mNegativeButtonListener);
        }
        return updateDialog;
    }
    
    public UpdateDialog$Builder setCancelable(final boolean mCancelable) {
        this.params.mCancelable = mCancelable;
        return this;
    }
    
    public UpdateDialog$Builder setIcon(final int mIconId) {
        this.params.mIconId = mIconId;
        return this;
    }
    
    public UpdateDialog$Builder setIcon(final Drawable mIcon) {
        this.params.mIcon = mIcon;
        return this;
    }
    
    public UpdateDialog$Builder setMessage(final int n) {
        this.params.mMessage = this.params.mContext.getText(n);
        return this;
    }
    
    public UpdateDialog$Builder setMessage(final CharSequence mMessage) {
        this.params.mMessage = mMessage;
        return this;
    }
    
    public UpdateDialog$Builder setNegativeButton(final int n, final DialogInterface$OnClickListener mNegativeButtonListener) {
        this.params.mNegativeButtonText = this.params.mContext.getText(n);
        this.params.mNegativeButtonListener = mNegativeButtonListener;
        return this;
    }
    
    public UpdateDialog$Builder setNegativeButton(final CharSequence mNegativeButtonText, final DialogInterface$OnClickListener mNegativeButtonListener) {
        this.params.mNegativeButtonText = mNegativeButtonText;
        this.params.mNegativeButtonListener = mNegativeButtonListener;
        return this;
    }
    
    public UpdateDialog$Builder setOnCancelListener(final DialogInterface$OnCancelListener mOnCancelListener) {
        this.params.mOnCancelListener = mOnCancelListener;
        return this;
    }
    
    public UpdateDialog$Builder setOnKeyListener(final DialogInterface$OnKeyListener mOnKeyListener) {
        this.params.mOnKeyListener = mOnKeyListener;
        return this;
    }
    
    public UpdateDialog$Builder setPositiveButton(final int n, final DialogInterface$OnClickListener mPositiveButtonListener) {
        this.params.mPositiveButtonText = this.params.mContext.getText(n);
        this.params.mPositiveButtonListener = mPositiveButtonListener;
        return this;
    }
    
    public UpdateDialog$Builder setPositiveButton(final CharSequence mPositiveButtonText, final DialogInterface$OnClickListener mPositiveButtonListener) {
        this.params.mPositiveButtonText = mPositiveButtonText;
        this.params.mPositiveButtonListener = mPositiveButtonListener;
        return this;
    }
    
    public UpdateDialog$Builder setTitle(final int n) {
        this.params.mTitle = this.params.mContext.getText(n);
        return this;
    }
    
    public UpdateDialog$Builder setTitle(final CharSequence mTitle) {
        this.params.mTitle = mTitle;
        return this;
    }
    
    public AlertDialog show() {
        final UpdateDialog create = this.create();
        create.show();
        return create;
    }
}
