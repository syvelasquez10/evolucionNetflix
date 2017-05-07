// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.Log;
import android.view.ViewGroup;
import android.view.View;
import com.netflix.mediaclient.media.AudioSource;
import com.netflix.mediaclient.media.Language;
import android.app.Activity;
import android.widget.BaseAdapter;

public class LanguageSelector$AudioAdapter extends BaseAdapter
{
    private final Activity activity;
    private final Language language;
    
    public LanguageSelector$AudioAdapter(final Language language, final Activity activity) {
        this.language = language;
        this.activity = activity;
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
            int n2;
            if (BrowseExperience.isKubrick()) {
                n2 = 2130903112;
            }
            else {
                n2 = 2130903125;
            }
            inflate = this.activity.getLayoutInflater().inflate(n2, viewGroup, false);
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
