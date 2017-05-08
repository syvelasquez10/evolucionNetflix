// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.push_notify;

import com.netflix.mediaclient.util.ViewUtils;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.util.DeviceUtils;
import android.app.Activity;
import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfig$Cell;
import android.view.View;
import android.widget.Button;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

public class PushOptInContentBinder
{
    private String body;
    private int layoutId;
    private TextView mBody;
    private ImageView mCloseButton;
    private Context mContext;
    private ImageView mCover;
    private boolean mIsTakeover;
    private Button mNegativeButton;
    private Button mPositiveButton;
    private View mRootView;
    private TextView mTitle;
    private String negativeButton;
    private String positiveButton;
    private String title;
    
    public PushOptInContentBinder(final Context mContext, final ABTestConfig$Cell abTestConfig$Cell) {
        this.mContext = mContext;
        switch (PushOptInContentBinder$4.$SwitchMap$com$netflix$mediaclient$service$webclient$model$leafs$ABTestConfig$Cell[abTestConfig$Cell.ordinal()]) {
            case 1:
            case 2: {
                this.layoutId = 2130903249;
                this.title = this.mContext.getString(2131231197);
                this.body = this.mContext.getString(2131231188);
                this.positiveButton = this.mContext.getString(2131231194);
                this.negativeButton = this.mContext.getString(2131231191);
                break;
            }
            case 3:
            case 4: {
                this.layoutId = 2130903249;
                this.title = this.mContext.getString(2131231198);
                this.body = this.mContext.getString(2131231189);
                this.positiveButton = this.mContext.getString(2131231195);
                this.negativeButton = this.mContext.getString(2131231192);
                break;
            }
        }
        switch (PushOptInContentBinder$4.$SwitchMap$com$netflix$mediaclient$service$webclient$model$leafs$ABTestConfig$Cell[abTestConfig$Cell.ordinal()]) {
            default: {}
            case 1:
            case 3: {
                this.mIsTakeover = false;
            }
            case 2:
            case 4: {
                this.mIsTakeover = true;
            }
        }
    }
    
    public void bind(final View view, final OptInResponseHandler optInResponseHandler, final Activity activity) {
        this.mTitle = (TextView)view.findViewById(2131689778);
        this.mBody = (TextView)view.findViewById(2131690223);
        this.mPositiveButton = (Button)view.findViewById(2131689675);
        this.mNegativeButton = (Button)view.findViewById(2131689676);
        this.mCloseButton = (ImageView)view.findViewById(2131690064);
        this.mRootView = view.findViewById(2131690221);
        this.mCover = (ImageView)view.findViewById(2131690222);
        this.mTitle.setText((CharSequence)this.title);
        this.mBody.setText((CharSequence)this.body);
        this.mPositiveButton.setText((CharSequence)this.positiveButton);
        this.mNegativeButton.setText((CharSequence)this.negativeButton);
        if (this.isTakeover()) {
            this.mRootView.setBackgroundColor(this.mContext.getResources().getColor(2131623961));
        }
        else {
            this.mRootView.setBackgroundResource(2130837585);
        }
        if (DeviceUtils.isTabletByContext((Context)activity) && DeviceUtils.isPortrait(this.mContext)) {
            this.mCover.setImageResource(2130837591);
        }
        this.mPositiveButton.setOnClickListener((View$OnClickListener)new PushOptInContentBinder$1(this, optInResponseHandler));
        this.mNegativeButton.setOnClickListener((View$OnClickListener)new PushOptInContentBinder$2(this, optInResponseHandler));
        this.mCloseButton.setOnClickListener((View$OnClickListener)new PushOptInContentBinder$3(this, optInResponseHandler));
        ViewUtils.setVisibleOrGone((View)this.mCloseButton, this.isTakeover());
        ViewUtils.setVisibleOrGone((View)this.mNegativeButton, !this.isTakeover());
    }
    
    public int getLayoutId() {
        return this.layoutId;
    }
    
    public View getRoot() {
        return this.mRootView;
    }
    
    public boolean isTakeover() {
        return this.mIsTakeover;
    }
}
