// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import android.os.Bundle;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import com.netflix.mediaclient.Log;
import android.content.DialogInterface;
import android.view.View;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;

public class LanguageSelectorKubrick$AudioSubtitlesDlg extends NetflixDialogFrag
{
    private static final String TAG = "AudioSubtitlesDlg";
    protected LanguageSelector$LanguageSelectorCallback callback;
    private View dlgContents;
    
    public void onCancel(final DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
        Log.d("AudioSubtitlesDlg", "AudioSubtitlesDlg::onCancel");
        if (this.callback != null) {
            this.callback.userCanceled();
        }
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        Log.v("AudioSubtitlesDlg", "onCreateView called");
        this.getDialog().getWindow().setGravity(48);
        if (this.dlgContents == null) {
            this.dismiss();
        }
        return this.dlgContents;
    }
    
    public void setViewAndCallback(final View dlgContents, final LanguageSelector$LanguageSelectorCallback callback) {
        this.dlgContents = dlgContents;
        this.callback = callback;
    }
}
