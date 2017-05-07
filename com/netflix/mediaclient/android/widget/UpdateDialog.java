// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.content.DialogInterface$OnKeyListener;
import android.content.DialogInterface$OnClickListener;
import android.graphics.drawable.Drawable;
import com.netflix.mediaclient.Log;
import android.view.KeyEvent;
import android.content.DialogInterface$OnCancelListener;
import android.content.Context;
import android.app.AlertDialog;

public class UpdateDialog extends AlertDialog
{
    private static final String TAG = "Update";
    
    public UpdateDialog(final Context context) {
        super(context);
    }
    
    public UpdateDialog(final Context context, final int n) {
        super(context, n);
    }
    
    public UpdateDialog(final Context context, final boolean b, final DialogInterface$OnCancelListener dialogInterface$OnCancelListener) {
        super(context, b, dialogInterface$OnCancelListener);
    }
    
    public boolean onKeyDown(final int n, final KeyEvent keyEvent) {
        Log.i("Update", "Key " + n);
        if (n == 84) {
            Log.i("Update", "Ignore search key");
            return true;
        }
        if (n == 4) {
            Log.i("Update", "Ignore back key");
            return true;
        }
        return super.onKeyDown(n, keyEvent);
    }
    
    public boolean onSearchRequested() {
        return false;
    }
    
    static class AlertParams
    {
        public boolean mCancelable;
        public final Context mContext;
        public Drawable mIcon;
        public int mIconId;
        public CharSequence mMessage;
        public DialogInterface$OnClickListener mNegativeButtonListener;
        public CharSequence mNegativeButtonText;
        public DialogInterface$OnClickListener mNeutralButtonListener;
        public CharSequence mNeutralButtonText;
        public DialogInterface$OnCancelListener mOnCancelListener;
        public DialogInterface$OnClickListener mOnClickListener;
        public DialogInterface$OnKeyListener mOnKeyListener;
        public DialogInterface$OnClickListener mPositiveButtonListener;
        public CharSequence mPositiveButtonText;
        public CharSequence mTitle;
        
        private AlertParams(final Context mContext) {
            this.mIconId = -1;
            this.mContext = mContext;
        }
    }
    
    public static class Builder
    {
        private AlertParams params;
        
        public Builder(final Context context) {
            this.params = new AlertParams(context);
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
        
        public Builder setCancelable(final boolean mCancelable) {
            this.params.mCancelable = mCancelable;
            return this;
        }
        
        public Builder setIcon(final int mIconId) {
            this.params.mIconId = mIconId;
            return this;
        }
        
        public Builder setIcon(final Drawable mIcon) {
            this.params.mIcon = mIcon;
            return this;
        }
        
        public Builder setMessage(final int n) {
            this.params.mMessage = this.params.mContext.getText(n);
            return this;
        }
        
        public Builder setMessage(final CharSequence mMessage) {
            this.params.mMessage = mMessage;
            return this;
        }
        
        public Builder setNegativeButton(final int n, final DialogInterface$OnClickListener mNegativeButtonListener) {
            this.params.mNegativeButtonText = this.params.mContext.getText(n);
            this.params.mNegativeButtonListener = mNegativeButtonListener;
            return this;
        }
        
        public Builder setNegativeButton(final CharSequence mNegativeButtonText, final DialogInterface$OnClickListener mNegativeButtonListener) {
            this.params.mNegativeButtonText = mNegativeButtonText;
            this.params.mNegativeButtonListener = mNegativeButtonListener;
            return this;
        }
        
        public Builder setOnCancelListener(final DialogInterface$OnCancelListener mOnCancelListener) {
            this.params.mOnCancelListener = mOnCancelListener;
            return this;
        }
        
        public Builder setOnKeyListener(final DialogInterface$OnKeyListener mOnKeyListener) {
            this.params.mOnKeyListener = mOnKeyListener;
            return this;
        }
        
        public Builder setPositiveButton(final int n, final DialogInterface$OnClickListener mPositiveButtonListener) {
            this.params.mPositiveButtonText = this.params.mContext.getText(n);
            this.params.mPositiveButtonListener = mPositiveButtonListener;
            return this;
        }
        
        public Builder setPositiveButton(final CharSequence mPositiveButtonText, final DialogInterface$OnClickListener mPositiveButtonListener) {
            this.params.mPositiveButtonText = mPositiveButtonText;
            this.params.mPositiveButtonListener = mPositiveButtonListener;
            return this;
        }
        
        public Builder setTitle(final int n) {
            this.params.mTitle = this.params.mContext.getText(n);
            return this;
        }
        
        public Builder setTitle(final CharSequence mTitle) {
            this.params.mTitle = mTitle;
            return this;
        }
        
        public AlertDialog show() {
            final UpdateDialog create = this.create();
            create.show();
            return create;
        }
    }
}
