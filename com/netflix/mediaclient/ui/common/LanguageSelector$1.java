// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import com.netflix.mediaclient.media.AudioSource;
import com.netflix.mediaclient.Log;
import android.view.View;
import android.widget.AdapterView;
import com.netflix.mediaclient.media.Language;
import android.widget.AdapterView$OnItemClickListener;

class LanguageSelector$1 implements AdapterView$OnItemClickListener
{
    final /* synthetic */ LanguageSelector this$0;
    final /* synthetic */ LanguageSelector$AudioAdapter val$audioAdapter;
    final /* synthetic */ Language val$language;
    final /* synthetic */ LanguageSelector$SubtitleAdapter val$subtitleAdapter;
    
    LanguageSelector$1(final LanguageSelector this$0, final LanguageSelector$AudioAdapter val$audioAdapter, final Language val$language, final LanguageSelector$SubtitleAdapter val$subtitleAdapter) {
        this.this$0 = this$0;
        this.val$audioAdapter = val$audioAdapter;
        this.val$language = val$language;
        this.val$subtitleAdapter = val$subtitleAdapter;
    }
    
    public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
        final AudioSource item = this.val$audioAdapter.getItem(n);
        if (Log.isLoggable("nf_language_selector", 3)) {
            Log.d("nf_language_selector", "Audio selected on position " + n + ", audio choosen: " + item);
        }
        if (this.val$language.getSelectedAudio() != item) {
            Log.v("nf_language_selector", "Audio is changed, refresh both views");
            this.val$language.setSelectedAudio(item);
            this.val$audioAdapter.notifyDataSetChanged();
            this.val$subtitleAdapter.notifyDataSetChanged();
            return;
        }
        Log.v("nf_language_selector", "Audio is not changed, do not refresh");
    }
}
