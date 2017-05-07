// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.app.Dialog;
import com.netflix.mediaclient.media.Subtitle;
import com.netflix.mediaclient.media.AudioSource;
import com.netflix.mediaclient.Log;
import android.widget.SeekBar$OnSeekBarChangeListener;
import com.netflix.mediaclient.ui.common.LanguageSelector$LanguageSelectorCallback;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.MenuItem$OnMenuItemClickListener;
import android.view.Menu;
import com.netflix.mediaclient.servicemgr.IPlayer;
import android.content.DialogInterface$OnCancelListener;
import android.widget.AdapterView$OnItemClickListener;
import android.content.Context;
import com.netflix.mediaclient.ui.mdx.MdxTargetSelectionDialog$Builder;
import android.app.AlertDialog;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import com.netflix.mediaclient.media.Language;
import android.app.Activity;
import com.netflix.mediaclient.ui.mdx.MdxTargetSelection;
import android.view.View;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import com.netflix.mediaclient.ui.common.Social;
import com.netflix.mediaclient.ui.common.LanguageSelector;
import android.view.MenuItem;
import android.animation.Animator;
import android.view.View$OnClickListener;
import android.support.v7.app.ActionBar;
import android.text.Html;
import com.netflix.mediaclient.util.StringUtils;

class TopPanel$10 implements Runnable
{
    final /* synthetic */ TopPanel this$0;
    final /* synthetic */ String val$title;
    
    TopPanel$10(final TopPanel this$0, final String val$title) {
        this.this$0 = this$0;
        this.val$title = val$title;
    }
    
    @Override
    public void run() {
        if (StringUtils.isEmpty(this.val$title)) {
            this.this$0.mTitleLabel.setText((CharSequence)"");
            return;
        }
        this.this$0.mTitleLabel.setText((CharSequence)Html.fromHtml(this.val$title));
    }
}
