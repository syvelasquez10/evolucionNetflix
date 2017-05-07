// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import com.netflix.mediaclient.media.Subtitle;
import com.netflix.mediaclient.Log;
import android.view.View;
import android.widget.AdapterView;
import com.netflix.mediaclient.media.Language;
import android.widget.AdapterView$OnItemClickListener;

class LanguageSelector$2 implements AdapterView$OnItemClickListener
{
    final /* synthetic */ LanguageSelector this$0;
    final /* synthetic */ Language val$language;
    final /* synthetic */ LanguageSelector$SubtitleAdapter val$subtitleAdapter;
    
    LanguageSelector$2(final LanguageSelector this$0, final LanguageSelector$SubtitleAdapter val$subtitleAdapter, final Language val$language) {
        this.this$0 = this$0;
        this.val$subtitleAdapter = val$subtitleAdapter;
        this.val$language = val$language;
    }
    
    public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
        final Subtitle item = this.val$subtitleAdapter.getItem(n);
        if (Log.isLoggable()) {
            Log.d("nf_language_selector", "Subtitle selected on position " + n + ", data: " + item);
        }
        if (this.val$language.getSelectedSubtitle() != item) {
            Log.v("nf_language_selector", "Subtitle is changed, refresh subtitle list view");
            this.val$language.setSelectedSubtitle(item);
            this.val$subtitleAdapter.notifyDataSetChanged();
            return;
        }
        Log.v("nf_language_selector", "Subtitle is not changed, do not refresh");
    }
}
