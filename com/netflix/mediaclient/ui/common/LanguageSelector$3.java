// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import android.widget.AdapterView$OnItemClickListener;
import android.widget.ListAdapter;
import android.view.ViewGroup$LayoutParams;
import android.app.Dialog;
import android.content.DialogInterface$OnCancelListener;
import android.content.Context;
import android.view.View;
import com.netflix.mediaclient.media.AudioSource;
import com.netflix.mediaclient.media.Subtitle;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.ListView;
import com.netflix.mediaclient.media.Language;
import com.netflix.mediaclient.Log;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;

class LanguageSelector$3 implements DialogInterface$OnClickListener
{
    final /* synthetic */ LanguageSelector this$0;
    final /* synthetic */ boolean val$wasPlaying;
    
    LanguageSelector$3(final LanguageSelector this$0, final boolean val$wasPlaying) {
        this.this$0 = this$0;
        this.val$wasPlaying = val$wasPlaying;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        Log.d("nf_language_selector", "Languages::apply");
        this.this$0.mCallback.languageChanged(this.this$0.language, this.val$wasPlaying);
        dialogInterface.dismiss();
    }
}
