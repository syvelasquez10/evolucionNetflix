// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import android.widget.AdapterView$OnItemClickListener;
import android.widget.ListAdapter;
import android.view.ViewGroup$LayoutParams;
import android.app.Dialog;
import android.content.DialogInterface$OnCancelListener;
import android.content.DialogInterface$OnClickListener;
import android.content.Context;
import com.netflix.mediaclient.media.AudioSource;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.ListView;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.Log;
import android.view.ViewGroup;
import android.view.View;
import com.netflix.mediaclient.media.Subtitle;
import com.netflix.mediaclient.media.Language;
import android.widget.BaseAdapter;

public class LanguageSelector$SubtitleAdapter extends BaseAdapter
{
    private final Language language;
    final /* synthetic */ LanguageSelector this$0;
    
    public LanguageSelector$SubtitleAdapter(final LanguageSelector this$0, final Language language) {
        this.this$0 = this$0;
        this.language = language;
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
            inflate = this.this$0.mController.getLayoutInflater().inflate(2130903127, viewGroup, false);
            inflate.setTag((Object)new LanguageSelector$RowHolder(inflate));
        }
        final LanguageSelector$RowHolder languageSelector$RowHolder = (LanguageSelector$RowHolder)inflate.getTag();
        final Subtitle item = this.getItem(n);
        Subtitle subtitle = this.language.getSelectedSubtitle();
        if (this.this$0.shouldForceFirst(this.language, n, item)) {
            Log.d("nf_language_selector", "Previously selected subtitle is not allowed anymore, reset to first on list, reload seleted subtitle");
            subtitle = this.language.getSelectedSubtitle();
        }
        String text;
        if (item != null) {
            final StringBuilder sb = new StringBuilder(item.getLanguageDescription());
            if (item.isCC()) {
                Log.d("nf_language_selector", "Add CC");
                sb.append(' ');
                sb.append(this.this$0.mController.getText(2131493118));
            }
            text = sb.toString();
            equals = item.equals(subtitle);
        }
        else {
            text = this.this$0.mController.getString(2131493111);
            if (subtitle == null) {
                equals = true;
                text = text;
            }
        }
        languageSelector$RowHolder.name.setText((CharSequence)text);
        languageSelector$RowHolder.choice.setChecked(equals);
        if (equals) {
            if (Log.isLoggable("nf_language_selector", 3)) {
                Log.d("nf_language_selector", "Subtitle is selected " + item);
            }
            ViewUtils.setTextOpacityToSelected(languageSelector$RowHolder.name);
            return inflate;
        }
        ViewUtils.setTextOpacityToUnselected(languageSelector$RowHolder.name);
        return inflate;
    }
}
