// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import android.content.Context;

public class NetflixAlertDialog$AlertDialogDescriptor
{
    public static final String ACTION_ID_NEGATIVE_BUTTON_CLICKED = "nflx_negative_button_clicked";
    public static final String ACTION_ID_NEUTRAL_BUTTON_CLICKED = "nflx_neutral_button_clicked";
    public static final String ACTION_ID_POSITIVE_BUTTON_CLICKED = "nflx_positive_button_clicked";
    private final boolean mCancelOnBack;
    private final boolean mCancelOnTouchOutside;
    private final Context mContext;
    private final String mDialogId;
    private final String mMessage;
    private NetflixAlertDialog$ActionButtonDescriptor mNegativeButton;
    private NetflixAlertDialog$ActionButtonDescriptor mNeutralButton;
    private NetflixAlertDialog$ActionButtonDescriptor mPositiveButton;
    
    public NetflixAlertDialog$AlertDialogDescriptor(final Context mContext, final String mDialogId, final String mMessage, final boolean mCancelOnTouchOutside, final boolean mCancelOnBack) {
        if (mMessage == null) {
            throw new IllegalArgumentException("Message can not be null!");
        }
        if (mContext == null) {
            throw new IllegalArgumentException("Context can not be null!");
        }
        if (mDialogId == null) {
            throw new IllegalArgumentException("Dialog id can not be null!");
        }
        this.mDialogId = mDialogId;
        this.mMessage = mMessage;
        this.mCancelOnTouchOutside = mCancelOnTouchOutside;
        this.mCancelOnBack = mCancelOnBack;
        this.mContext = mContext;
    }
    
    public String getDialogId() {
        return this.mDialogId;
    }
    
    public String getMessage() {
        return this.mMessage;
    }
    
    public NetflixAlertDialog$ActionButtonDescriptor getNegativeButton() {
        return this.mNegativeButton;
    }
    
    public NetflixAlertDialog$ActionButtonDescriptor getNeutralButton() {
        return this.mNeutralButton;
    }
    
    public NetflixAlertDialog$ActionButtonDescriptor getPositiveButton() {
        return this.mPositiveButton;
    }
    
    public boolean isCancelOnBack() {
        return this.mCancelOnBack;
    }
    
    public boolean isCancelOnTouchOutside() {
        return this.mCancelOnTouchOutside;
    }
    
    public void setNegativeButton() {
        this.mNegativeButton = new NetflixAlertDialog$ActionButtonDescriptor(this.mContext.getString(2131493101), "nflx_negative_button_clicked");
    }
    
    public void setNegativeButton(final String s) {
        this.mNegativeButton = new NetflixAlertDialog$ActionButtonDescriptor(this.mContext.getString(2131493101), s);
    }
    
    public void setNegativeButton(final String s, final String s2) {
        this.mNegativeButton = new NetflixAlertDialog$ActionButtonDescriptor(s, s2);
    }
    
    public void setNeutralButton(final String s) {
        this.mNeutralButton = new NetflixAlertDialog$ActionButtonDescriptor(s, "nflx_neutral_button_clicked");
    }
    
    public void setNeutralButton(final String s, final String s2) {
        this.mNeutralButton = new NetflixAlertDialog$ActionButtonDescriptor(s, s2);
    }
    
    public void setPositiveButton() {
        this.mPositiveButton = new NetflixAlertDialog$ActionButtonDescriptor(this.mContext.getString(2131492980), "nflx_positive_button_clicked");
    }
    
    public void setPositiveButton(final String s) {
        this.mPositiveButton = new NetflixAlertDialog$ActionButtonDescriptor(this.mContext.getString(2131492980), s);
    }
    
    public void setPositiveButton(final String s, final String s2) {
        this.mPositiveButton = new NetflixAlertDialog$ActionButtonDescriptor(s, s2);
    }
}
