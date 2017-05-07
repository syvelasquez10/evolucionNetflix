// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.Log;
import android.view.ViewGroup;
import android.view.View;
import com.netflix.mediaclient.media.AudioSource;
import com.netflix.mediaclient.media.Language;
import android.widget.BaseAdapter;

public class LanguageSelector$AudioAdapter extends BaseAdapter
{
    private final Language language;
    final /* synthetic */ LanguageSelector this$0;
    
    public LanguageSelector$AudioAdapter(final LanguageSelector this$0, final Language language) {
        this.this$0 = this$0;
        this.language = language;
    }
    
    public int getCount() {
        return this.language.getAltAudios().length;
    }
    
    public AudioSource getItem(final int n) {
        return this.language.getAltAudios()[n];
    }
    
    public long getItemId(final int n) {
        return n;
    }
    
    public View getView(final int n, final View view, final ViewGroup viewGroup) {
        View inflate = view;
        if (view == null) {
            Log.d("nf_language_selector", "Audio create row " + n);
            inflate = this.this$0.mController.getLayoutInflater().inflate(2130903122, viewGroup, false);
            inflate.setTag((Object)new LanguageSelector$RowHolder(inflate));
        }
        final LanguageSelector$RowHolder languageSelector$RowHolder = (LanguageSelector$RowHolder)inflate.getTag();
        final AudioSource item = this.getItem(n);
        final boolean equals = item.equals(this.language.getSelectedAudio());
        languageSelector$RowHolder.name.setText((CharSequence)item.getLanguageDescription());
        languageSelector$RowHolder.choice.setChecked(equals);
        if (equals) {
            if (Log.isLoggable()) {
                Log.d("nf_language_selector", "Audio is selected " + item);
            }
            ViewUtils.setTextOpacityToSelected(languageSelector$RowHolder.name);
            return inflate;
        }
        ViewUtils.setTextOpacityToUnselected(languageSelector$RowHolder.name);
        return inflate;
    }
}
