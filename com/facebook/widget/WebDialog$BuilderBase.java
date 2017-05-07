// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import com.facebook.internal.Validate;
import com.facebook.internal.Utility;
import com.facebook.Session;
import android.os.Bundle;
import android.content.Context;

class WebDialog$BuilderBase<CONCRETE extends WebDialog$BuilderBase<?>>
{
    private String action;
    private String applicationId;
    private Context context;
    private WebDialog$OnCompleteListener listener;
    private Bundle parameters;
    private Session session;
    private int theme;
    
    protected WebDialog$BuilderBase(final Context context, final String s, final String s2, final Bundle bundle) {
        this.theme = 16973840;
        String metadataApplicationId = s;
        if (s == null) {
            metadataApplicationId = Utility.getMetadataApplicationId(context);
        }
        Validate.notNullOrEmpty(metadataApplicationId, "applicationId");
        this.applicationId = metadataApplicationId;
        this.finishInit(context, s2, bundle);
    }
    
    private void finishInit(final Context context, final String action, final Bundle parameters) {
        this.context = context;
        this.action = action;
        if (parameters != null) {
            this.parameters = parameters;
            return;
        }
        this.parameters = new Bundle();
    }
    
    public WebDialog build() {
        if (this.session != null && this.session.isOpened()) {
            this.parameters.putString("app_id", this.session.getApplicationId());
            this.parameters.putString("access_token", this.session.getAccessToken());
        }
        else {
            this.parameters.putString("app_id", this.applicationId);
        }
        return new WebDialog(this.context, this.action, this.parameters, this.theme, this.listener);
    }
    
    protected String getApplicationId() {
        return this.applicationId;
    }
    
    protected Context getContext() {
        return this.context;
    }
    
    protected WebDialog$OnCompleteListener getListener() {
        return this.listener;
    }
    
    protected Bundle getParameters() {
        return this.parameters;
    }
    
    protected int getTheme() {
        return this.theme;
    }
    
    public CONCRETE setOnCompleteListener(final WebDialog$OnCompleteListener listener) {
        this.listener = listener;
        return (CONCRETE)this;
    }
}