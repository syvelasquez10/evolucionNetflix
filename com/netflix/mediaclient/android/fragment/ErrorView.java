// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.fragment;

import com.netflix.mediaclient.android.widget.ErrorWrapper;
import android.view.ViewGroup;
import android.view.View;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import android.view.LayoutInflater;

public class ErrorView
{
    public static View create(final LayoutInflater layoutInflater, final ErrorWrapper$Callback errorWrapper$Callback) {
        final View inflate = layoutInflater.inflate(2130903131, (ViewGroup)null);
        new ErrorWrapper(inflate, errorWrapper$Callback);
        return inflate;
    }
}
