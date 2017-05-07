// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.fragment;

import android.view.ViewGroup;
import android.view.View;
import com.netflix.mediaclient.android.widget.ErrorWrapper;
import android.view.LayoutInflater;

public class ErrorView
{
    public static View create(final LayoutInflater layoutInflater, final ErrorWrapper.Callback callback) {
        final View inflate = layoutInflater.inflate(2130903060, (ViewGroup)null);
        new ErrorWrapper(inflate, callback);
        return inflate;
    }
}
