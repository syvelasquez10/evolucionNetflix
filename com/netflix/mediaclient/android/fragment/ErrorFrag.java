// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.fragment;

import com.netflix.mediaclient.android.widget.ErrorWrapper;
import android.view.View;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.LayoutInflater;

public class ErrorFrag extends NetflixFrag
{
    public boolean isLoadingData() {
        return false;
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        return ErrorView.create(layoutInflater, new ErrorWrapper.Callback() {
            @Override
            public void onRetryRequested() {
                ((Callback)ErrorFrag.this.getActivity()).onRetryRequested();
            }
        });
    }
}
