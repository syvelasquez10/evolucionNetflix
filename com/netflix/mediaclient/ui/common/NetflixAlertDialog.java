// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import android.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.content.Intent;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.util.ViewUtils;
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
    public static NetflixAlertDialog newInstance(final AlertDialogDescriptor alertDialogDescriptor) {
        final boolean b = true;
        if (alertDialogDescriptor == null) {
            throw new IllegalArgumentException("Dialog metadata can not be null!");
        }
        final NetflixAlertDialog netflixAlertDialog = new NetflixAlertDialog();
        netflixAlertDialog.setStyle(1, 2131558594);
        netflixAlertDialog.setCancelable(alertDialogDescriptor.isCancelOnBack());
        final Bundle arguments = new Bundle();
        netflixAlertDialog.setArguments(arguments);
        arguments.putString("nflx_Message", alertDialogDescriptor.getMessage());
        arguments.putBoolean("nflx_CancelableOnTouchOutside", alertDialogDescriptor.isCancelOnTouchOutside());
        arguments.putString("nflx_DialogId", alertDialogDescriptor.getMessage());
        if (alertDialogDescriptor.getPositiveButton() == null && alertDialogDescriptor.getNegativeButton() == null && alertDialogDescriptor.getNeutralButton() == null) {
            alertDialogDescriptor.setPositiveButton();
        }
        arguments.putBoolean("nflx_PositiveButtonUsed", alertDialogDescriptor.getPositiveButton() != null);
        if (alertDialogDescriptor.getPositiveButton() != null) {
            arguments.putString("nflx_PositiveButtonLabel", alertDialogDescriptor.getPositiveButton().getLabel());
            arguments.putString("nflx_PositiveButtonId", alertDialogDescriptor.getPositiveButton().getButtonId());
        }
        arguments.putBoolean("nflx_NegativeButtonUsed", alertDialogDescriptor.getNegativeButton() != null);
        if (alertDialogDescriptor.getNegativeButton() != null) {
            arguments.putString("nflx_NegativeButtonLabel", alertDialogDescriptor.getNegativeButton().getLabel());
            arguments.putString("nflx_NegativeButtonId", alertDialogDescriptor.getNegativeButton().getButtonId());
        }
        arguments.putBoolean("nflx_NeutralButtonUsed", alertDialogDescriptor.getNeutralButton() != null && b);
        if (alertDialogDescriptor.getNeutralButton() != null) {
            arguments.putString("nflx_NeutralButtonLabel", alertDialogDescriptor.getNeutralButton().getLabel());
            arguments.putString("nflx_NeutralButtonId", alertDialogDescriptor.getNeutralButton().getButtonId());
        }
        return netflixAlertDialog;
    }
    
    public boolean isCancelableOnTouchOutside() {
        return this.mCancelableOnTouchOutside;
    }
    
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
        final View inflate = layoutInflater.inflate(2130903066, viewGroup, false);
        this.mMessageView = (TextView)inflate.findViewById(2131230802);
        this.mPositiveButton = (Button)inflate.findViewById(2131230807);
        this.mNegativeButton = (Button)inflate.findViewById(2131230803);
        this.mNeutralButton = (Button)inflate.findViewById(2131230805);
        this.mNegativeDivider = inflate.findViewById(2131230804);
        this.mNeutralDivider = inflate.findViewById(2131230806);
        this.mMessageView.setText((CharSequence)this.mMessage);
        if (this.mNeutralButtonUsed) {
            this.mNeutralButton.setText((CharSequence)this.mNeutralButtonLabel);
            this.mNeutralButton.setOnClickListener((View$OnClickListener)new AlertDialogOnClickListener(this.mNeutralButtonId));
        }
        else {
            ViewUtils.setVisibility((View)this.mNeutralButton, ViewUtils.Visibility.GONE);
            ViewUtils.setVisibility(this.mNeutralDivider, ViewUtils.Visibility.GONE);
        }
        if (this.mNegativeButtonUsed) {
            this.mNegativeButton.setText((CharSequence)this.mNegativeButtonLabel);
            this.mNegativeButton.setOnClickListener((View$OnClickListener)new AlertDialogOnClickListener(this.mNegativeButtonId));
        }
        else {
            ViewUtils.setVisibility((View)this.mNegativeButton, ViewUtils.Visibility.GONE);
            ViewUtils.setVisibility(this.mNegativeDivider, ViewUtils.Visibility.GONE);
        }
        if (this.mPositiveButtonUsed) {
            this.mPositiveButton.setText((CharSequence)this.mPositiveButtonLabel);
            this.mPositiveButton.setOnClickListener((View$OnClickListener)new AlertDialogOnClickListener(this.mPositiveButtonId));
            return inflate;
        }
        ViewUtils.setVisibility((View)this.mPositiveButton, ViewUtils.Visibility.GONE);
        return inflate;
    }
    
    private static class ActionButtonDescriptor
    {
        private final String buttonId;
        private final String label;
        
        public ActionButtonDescriptor(final String label, final String buttonId) {
            if (buttonId == null || label == null) {
                throw new IllegalArgumentException("Button ID and label can not be null!");
            }
            this.label = label;
            this.buttonId = buttonId;
        }
        
        public String getButtonId() {
            return this.buttonId;
        }
        
        public String getLabel() {
            return this.label;
        }
        
        @Override
        public String toString() {
            return "ActionButtonDescriptor [labelResourceId=" + this.label + ", buttonId=" + this.buttonId + "]";
        }
    }
    
    public static class AlertDialogDescriptor
    {
        public static final String ACTION_ID_NEGATIVE_BUTTON_CLICKED = "nflx_negative_button_clicked";
        public static final String ACTION_ID_NEUTRAL_BUTTON_CLICKED = "nflx_neutral_button_clicked";
        public static final String ACTION_ID_POSITIVE_BUTTON_CLICKED = "nflx_positive_button_clicked";
        private final boolean mCancelOnBack;
        private final boolean mCancelOnTouchOutside;
        private final Context mContext;
        private final String mDialogId;
        private final String mMessage;
        private ActionButtonDescriptor mNegativeButton;
        private ActionButtonDescriptor mNeutralButton;
        private ActionButtonDescriptor mPositiveButton;
        
        public AlertDialogDescriptor(final Context mContext, final String mDialogId, final String mMessage, final boolean mCancelOnTouchOutside, final boolean mCancelOnBack) {
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
        
        public ActionButtonDescriptor getNegativeButton() {
            return this.mNegativeButton;
        }
        
        public ActionButtonDescriptor getNeutralButton() {
            return this.mNeutralButton;
        }
        
        public ActionButtonDescriptor getPositiveButton() {
            return this.mPositiveButton;
        }
        
        public boolean isCancelOnBack() {
            return this.mCancelOnBack;
        }
        
        public boolean isCancelOnTouchOutside() {
            return this.mCancelOnTouchOutside;
        }
        
        public void setNegativeButton() {
            this.mNegativeButton = new ActionButtonDescriptor(this.mContext.getString(2131296494), "nflx_negative_button_clicked");
        }
        
        public void setNegativeButton(final String s) {
            this.mNegativeButton = new ActionButtonDescriptor(this.mContext.getString(2131296494), s);
        }
        
        public void setNegativeButton(final String s, final String s2) {
            this.mNegativeButton = new ActionButtonDescriptor(s, s2);
        }
        
        public void setNeutralButton(final String s) {
            this.mNeutralButton = new ActionButtonDescriptor(s, "nflx_neutral_button_clicked");
        }
        
        public void setNeutralButton(final String s, final String s2) {
            this.mNeutralButton = new ActionButtonDescriptor(s, s2);
        }
        
        public void setPositiveButton() {
            this.mPositiveButton = new ActionButtonDescriptor(this.mContext.getString(2131296347), "nflx_positive_button_clicked");
        }
        
        public void setPositiveButton(final String s) {
            this.mPositiveButton = new ActionButtonDescriptor(this.mContext.getString(2131296347), s);
        }
        
        public void setPositiveButton(final String s, final String s2) {
            this.mPositiveButton = new ActionButtonDescriptor(s, s2);
        }
    }
    
    private class AlertDialogOnClickListener implements View$OnClickListener
    {
        private final String mActionId;
        
        public AlertDialogOnClickListener(final String mActionId) {
            this.mActionId = mActionId;
        }
        
        public void onClick(final View view) {
            if (Log.isLoggable("dialog", 3)) {
                Log.d("dialog", "Clicked on " + this.mActionId);
            }
            if (NetflixAlertDialog.this.getActivity() == null) {
                Log.e("dialog", "Activity is NULL, we can update rating!");
                return;
            }
            synchronized (NetflixAlertDialog.this.mClicked) {
                if (NetflixAlertDialog.this.mClicked.get()) {
                    Log.w("dialog", "Already clicked!");
                    return;
                }
            }
            NetflixAlertDialog.this.mClicked.set(true);
            // monitorexit(atomicBoolean)
            final Intent intent = new Intent("nflx_button_selected");
            intent.putExtra("nflx_action_selected", this.mActionId);
            intent.putExtra("nflx_dialog_id", NetflixAlertDialog.this.mDialogId);
            intent.addCategory("LocalIntentNflxUi");
            LocalBroadcastManager.getInstance((Context)NetflixAlertDialog.this.getActivity()).sendBroadcast(intent);
            final Context context;
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            NetflixAlertDialog.this.dismissAllowingStateLoss();
            NetflixAlertDialog.this.getFragmentManager().beginTransaction().remove((Fragment)NetflixAlertDialog.this).commit();
        }
    }
}
