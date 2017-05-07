// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import android.os.Bundle;
import com.facebook.Session;
import android.content.Context;

public class WebDialog$RequestsDialogBuilder extends WebDialog$BuilderBase<WebDialog$RequestsDialogBuilder>
{
    private static final String APPREQUESTS_DIALOG = "apprequests";
    private static final String DATA_PARAM = "data";
    private static final String MESSAGE_PARAM = "message";
    private static final String TITLE_PARAM = "title";
    private static final String TO_PARAM = "to";
    
    public WebDialog$RequestsDialogBuilder(final Context context) {
        super(context, "apprequests");
    }
    
    public WebDialog$RequestsDialogBuilder(final Context context, final Session session) {
        super(context, session, "apprequests", null);
    }
    
    public WebDialog$RequestsDialogBuilder(final Context context, final Session session, final Bundle bundle) {
        super(context, session, "apprequests", bundle);
    }
    
    public WebDialog$RequestsDialogBuilder(final Context context, final String s, final Bundle bundle) {
        super(context, s, "apprequests", bundle);
    }
    
    public WebDialog$RequestsDialogBuilder setData(final String s) {
        this.getParameters().putString("data", s);
        return this;
    }
    
    public WebDialog$RequestsDialogBuilder setMessage(final String s) {
        this.getParameters().putString("message", s);
        return this;
    }
    
    public WebDialog$RequestsDialogBuilder setTitle(final String s) {
        this.getParameters().putString("title", s);
        return this;
    }
    
    public WebDialog$RequestsDialogBuilder setTo(final String s) {
        this.getParameters().putString("to", s);
        return this;
    }
}
