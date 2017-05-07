// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.content.Context;
import com.netflix.mediaclient.ui.web.ExternalLinkActivity;
import android.view.View;
import android.os.Parcel;
import android.text.style.URLSpan;

public class InternalURLSpan extends URLSpan
{
    public InternalURLSpan(final Parcel parcel) {
        super(parcel);
    }
    
    public InternalURLSpan(final String s) {
        super(s);
    }
    
    public void onClick(final View view) {
        final Context context = view.getContext();
        if (context != null) {
            context.startActivity(ExternalLinkActivity.createStartIntent(context, this.getURL()));
        }
    }
}
