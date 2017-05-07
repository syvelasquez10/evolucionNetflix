// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.util.ViewUtils$Visibility;
import android.view.View$OnClickListener;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.concurrent.atomic.AtomicBoolean;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;

public class NetflixAlertDialog extends NetflixDialogFrag
{
    public static final String EXTRA_PARAM_ACTION_ID = "nflx_action_selected";
    public static final String EXTRA_PARAM_DIALOG_ID = "nflx_dialog_id";
    public static final String INTENT_NAME_BUTTON_CANCELED = "nflx_button_canceled";
    public static final String INTENT_NAME_BUTTON_SELECTED = "nflx_button_selected";
    public static final String PARAM_CANCEL_ON_TOUCH_OUTSIDE = "nflx_CancelableOnTouchOutside";
    public static final String PARAM_DIALOG_ID = "nflx_DialogId";
    public static final String PARAM_MESSAGE = "nflx_Message";
    public static final String PARAM_NEGATIVE_BUTTON_ID = "nflx_NegativeButtonId";
    public static final String PARAM_NEGATIVE_BUTTON_LABEL = "nflx_NegativeButtonLabel";
    public static final String PARAM_NEGATIVE_BUTTON_USED = "nflx_NegativeButtonUsed";
    public static final String PARAM_NEUTRAL_BUTTON_ID = "nflx_NeutralButtonId";
    public static final String PARAM_NEUTRAL_BUTTON_LABEL = "nflx_NeutralButtonLabel";
    public static final String PARAM_NEUTRAL_BUTTON_USED = "nflx_NeutralButtonUsed";
    public static final String PARAM_POSITIVE_BUTTON_ID = "nflx_PositiveButtonId";
    public static final String PARAM_POSITIVE_BUTTON_LABEL = "nflx_PositiveButtonLabel";
    public static final String PARAM_POSITIVE_BUTTON_USED = "nflx_PositiveButtonUsed";
    private static final String TAG = "dialog";
    private boolean mCancelableOnTouchOutside;
    private final AtomicBoolean mClicked;
    private String mDialogId;
    private String mMessage;
    private TextView mMessageView;
    private Button mNegativeButton;
    private String mNegativeButtonId;
    private String mNegativeButtonLabel;
    private boolean mNegativeButtonUsed;
    private View mNegativeDivider;
    private Button mNeutralButton;
    private String mNeutralButtonId;
    private String mNeutralButtonLabel;
    private boolean mNeutralButtonUsed;
    private View mNeutralDivider;
    private Button mPositiveButton;
    private String mPositiveButtonId;
    private String mPositiveButtonLabel;
    private boolean mPositiveButtonUsed;
    
    public NetflixAlertDialog() {
        this.mPositiveButtonId = "nflx_positive_button_clicked";
        this.mNegativeButtonId = "nflx_negative_button_clicked";
        this.mNeutralButtonId = "nflx_neutral_button_clicked";
        this.mClicked = new AtomicBoolean(false);
    }
    
    @SuppressLint({ "InlinedApi" })
    public static NetflixAlertDialog newInstance(final NetflixAlertDialog$AlertDialogDescriptor netflixAlertDialog$AlertDialogDescriptor) {
        final boolean b = true;
        if (netflixAlertDialog$AlertDialogDescriptor == null) {
            throw new IllegalArgumentException("Dialog metadata can not be null!");
        }
        final NetflixAlertDialog netflixAlertDialog = new NetflixAlertDialog();
        netflixAlertDialog.setStyle(1, 2131558713);
        netflixAlertDialog.setCancelable(netflixAlertDialog$AlertDialogDescriptor.isCancelOnBack());
        final Bundle arguments = new Bundle();
        netflixAlertDialog.setArguments(arguments);
        arguments.putString("nflx_Message", netflixAlertDialog$AlertDialogDescriptor.getMessage());
        arguments.putBoolean("nflx_CancelableOnTouchOutside", netflixAlertDialog$AlertDialogDescriptor.isCancelOnTouchOutside());
        arguments.putString("nflx_DialogId", netflixAlertDialog$AlertDialogDescriptor.getMessage());
        if (netflixAlertDialog$AlertDialogDescriptor.getPositiveButton() == null && netflixAlertDialog$AlertDialogDescriptor.getNegativeButton() == null && netflixAlertDialog$AlertDialogDescriptor.getNeutralButton() == null) {
            netflixAlertDialog$AlertDialogDescriptor.setPositiveButton();
        }
        arguments.putBoolean("nflx_PositiveButtonUsed", netflixAlertDialog$AlertDialogDescriptor.getPositiveButton() != null);
        if (netflixAlertDialog$AlertDialogDescriptor.getPositiveButton() != null) {
            arguments.putString("nflx_PositiveButtonLabel", netflixAlertDialog$AlertDialogDescriptor.getPositiveButton().getLabel());
            arguments.putString("nflx_PositiveButtonId", netflixAlertDialog$AlertDialogDescriptor.getPositiveButton().getButtonId());
        }
        arguments.putBoolean("nflx_NegativeButtonUsed", netflixAlertDialog$AlertDialogDescriptor.getNegativeButton() != null);
        if (netflixAlertDialog$AlertDialogDescriptor.getNegativeButton() != null) {
            arguments.putString("nflx_NegativeButtonLabel", netflixAlertDialog$AlertDialogDescriptor.getNegativeButton().getLabel());
            arguments.putString("nflx_NegativeButtonId", netflixAlertDialog$AlertDialogDescriptor.getNegativeButton().getButtonId());
        }
        arguments.putBoolean("nflx_NeutralButtonUsed", netflixAlertDialog$AlertDialogDescriptor.getNeutralButton() != null && b);
        if (netflixAlertDialog$AlertDialogDescriptor.getNeutralButton() != null) {
            arguments.putString("nflx_NeutralButtonLabel", netflixAlertDialog$AlertDialogDescriptor.getNeutralButton().getLabel());
            arguments.putString("nflx_NeutralButtonId", netflixAlertDialog$AlertDialogDescriptor.getNeutralButton().getButtonId());
        }
        return netflixAlertDialog;
    }
    
    public boolean isCancelableOnTouchOutside() {
        return this.mCancelableOnTouchOutside;
    }
    
    @Override
    public boolean isLoadingData() {
        return false;
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.mCancelableOnTouchOutside = this.getArguments().getBoolean("nflx_CancelableOnTouchOutside");
        this.mMessage = this.getArguments().getString("nflx_Message");
        this.mPositiveButtonUsed = this.getArguments().getBoolean("nflx_PositiveButtonUsed", false);
        this.mPositiveButtonId = this.getArguments().getString("nflx_PositiveButtonId");
        this.mPositiveButtonLabel = this.getArguments().getString("nflx_PositiveButtonLabel");
        this.mNegativeButtonUsed = this.getArguments().getBoolean("nflx_NegativeButtonUsed", false);
        this.mNegativeButtonId = this.getArguments().getString("nflx_NegativeButtonId");
        this.mNegativeButtonLabel = this.getArguments().getString("nflx_NegativeButtonLabel");
        this.mNeutralButtonUsed = this.getArguments().getBoolean("nflx_NeutralButtonUsed", false);
        this.mNeutralButtonId = this.getArguments().getString("nflx_NeutralButtonId");
        this.mNeutralButtonLabel = this.getArguments().getString("nflx_NeutralButtonLabel");
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        final View inflate = layoutInflater.inflate(2130903067, viewGroup, false);
        this.mMessageView = (TextView)inflate.findViewById(2131165287);
        this.mPositiveButton = (Button)inflate.findViewById(2131165292);
        this.mNegativeButton = (Button)inflate.findViewById(2131165288);
        this.mNeutralButton = (Button)inflate.findViewById(2131165290);
        this.mNegativeDivider = inflate.findViewById(2131165289);
        this.mNeutralDivider = inflate.findViewById(2131165291);
        this.mMessageView.setText((CharSequence)this.mMessage);
        if (this.mNeutralButtonUsed) {
            this.mNeutralButton.setText((CharSequence)this.mNeutralButtonLabel);
            this.mNeutralButton.setOnClickListener((View$OnClickListener)new NetflixAlertDialog$AlertDialogOnClickListener(this, this.mNeutralButtonId));
        }
        else {
            ViewUtils.setVisibility((View)this.mNeutralButton, ViewUtils$Visibility.GONE);
            ViewUtils.setVisibility(this.mNeutralDivider, ViewUtils$Visibility.GONE);
        }
        if (this.mNegativeButtonUsed) {
            this.mNegativeButton.setText((CharSequence)this.mNegativeButtonLabel);
            this.mNegativeButton.setOnClickListener((View$OnClickListener)new NetflixAlertDialog$AlertDialogOnClickListener(this, this.mNegativeButtonId));
        }
        else {
            ViewUtils.setVisibility((View)this.mNegativeButton, ViewUtils$Visibility.GONE);
            ViewUtils.setVisibility(this.mNegativeDivider, ViewUtils$Visibility.GONE);
        }
        if (this.mPositiveButtonUsed) {
            this.mPositiveButton.setText((CharSequence)this.mPositiveButtonLabel);
            this.mPositiveButton.setOnClickListener((View$OnClickListener)new NetflixAlertDialog$AlertDialogOnClickListener(this, this.mPositiveButtonId));
            return inflate;
        }
        ViewUtils.setVisibility((View)this.mPositiveButton, ViewUtils$Visibility.GONE);
        return inflate;
    }
}
