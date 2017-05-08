// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import android.view.ViewGroup;
import android.view.View;
import com.netflix.mediaclient.media.AudioSource;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.media.Subtitle;
import com.netflix.mediaclient.media.Language;
import android.app.Activity;
import android.widget.BaseAdapter;

public class LanguageSelector$SubtitleAdapter extends BaseAdapter
{
    private final Activity activity;
    private final Language language;
    
    public LanguageSelector$SubtitleAdapter(final Language language, final Activity activity) {
        this.language = language;
        this.activity = activity;
    }
    
    private boolean shouldForceFirst(final Language language, final int n, final Subtitle selectedSubtitle) {
        if (n != 0) {
            return false;
        }
        final Subtitle selectedSubtitle2 = language.getSelectedSubtitle();
        final AudioSource selectedAudio = language.getSelectedAudio();
        if (selectedAudio != null && selectedAudio.isAllowedSubtitle(selectedSubtitle2)) {
            Log.d("nf_language_selector", "Selected subtitle is allowed");
            return false;
        }
        if (Log.isLoggable()) {
            Log.d("nf_language_selector", "Selected subtitle is not allowed, set to firsyt subtitle " + selectedSubtitle);
        }
        language.setSelectedSubtitle(selectedSubtitle);
        return true;
    }
    
    public int getCount() {
        return this.language.getUsedSubtitles().size();
    }
    
    public Subtitle getItem(final int n) {
        return this.language.getUsedSubtitles().get(n);
    }
    
    public long getItemId(final int n) {
        return n;
    }
    
    public View getView(final int n, final View view, final ViewGroup viewGroup) {
        boolean equals = false;
        View inflate = view;
        if (view == null) {
            Log.d("nf_language_selector", "Subtitle create row " + n);
            int n2;
            if (BrowseExperience.isKubrick()) {
                n2 = 2130903166;
            }
            else {
                n2 = 2130903174;
            }
            inflate = this.activity.getLayoutInflater().inflate(n2, viewGroup, false);
            inflate.setTag((Object)new LanguageSelector$RowHolder(inflate));
        }
        final LanguageSelector$RowHolder languageSelector$RowHolder = (LanguageSelector$RowHolder)inflate.getTag();
        final Subtitle item = this.getItem(n);
        Subtitle subtitle = this.language.getSelectedSubtitle();
        if (this.shouldForceFirst(this.language, n, item)) {
            Log.d("nf_language_selector", "Previously selected subtitle is not allowed anymore, reset to first on list, reload seleted subtitle");
            subtitle = this.language.getSelectedSubtitle();
        }
        String text;
        if (item != null) {
            final StringBuilder sb = new StringBuilder(item.getLanguageDescription());
            if (item.isCC()) {
                Log.d("nf_language_selector", "Add CC");
                sb.append(' ');
                sb.append(this.activity.getText(2131231011));
            }
            text = sb.toString();
            equals = item.equals(subtitle);
        }
        else {
            text = this.activity.getString(2131231147);
            if (subtitle == null) {
                equals = true;
                text = text;
            }
        }
        languageSelector$RowHolder.name.setText((CharSequence)text);
        languageSelector$RowHolder.choice.setChecked(equals);
        if (equals) {
            if (Log.isLoggable()) {
                Log.d("nf_language_selector", "Subtitle is selected " + item);
            }
            ViewUtils.setTextOpacityToSelected(languageSelector$RowHolder.name);
            return inflate;
        }
        ViewUtils.setTextOpacityToUnselected(languageSelector$RowHolder.name);
        return inflate;
    }
}
