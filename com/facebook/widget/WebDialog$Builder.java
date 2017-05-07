// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import android.os.Bundle;
import com.facebook.Session;
import android.content.Context;

public class WebDialog$Builder extends WebDialog$BuilderBase<WebDialog$Builder>
{
    public WebDialog$Builder(final Context context, final Session session, final String s, final Bundle bundle) {
        super(context, session, s, bundle);
    }
    
    public WebDialog$Builder(final Context context, final String s) {
        super(context, s);
    }
    
    public WebDialog$Builder(final Context context, final String s, final String s2, final Bundle bundle) {
        super(context, s, s2, bundle);
    }
}
