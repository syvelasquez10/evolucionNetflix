// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.content.DialogInterface$OnKeyListener;
import android.content.DialogInterface$OnCancelListener;
import android.content.DialogInterface$OnClickListener;
import android.graphics.drawable.Drawable;
import android.content.Context;

class UpdateDialog$AlertParams
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
    
    private UpdateDialog$AlertParams(final Context mContext) {
        this.mIconId = -1;
        this.mContext = mContext;
    }
}
