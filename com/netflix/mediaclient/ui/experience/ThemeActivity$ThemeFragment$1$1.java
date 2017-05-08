// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.experience;

import com.netflix.mediaclient.util.AndroidUtils;
import android.content.Context;
import android.view.View;
import com.netflix.mediaclient.ui.kids.KidsUtils$Theme;
import android.view.View$OnClickListener;

class ThemeActivity$ThemeFragment$1$1 implements View$OnClickListener
{
    final /* synthetic */ ThemeActivity$ThemeFragment$1 this$1;
    final /* synthetic */ KidsUtils$Theme val$theme;
    
    ThemeActivity$ThemeFragment$1$1(final ThemeActivity$ThemeFragment$1 this$1, final KidsUtils$Theme val$theme) {
        this.this$1 = this$1;
        this.val$theme = val$theme;
    }
    
    public void onClick(final View view) {
        ThemeActivity.saveTheme((Context)this.this$1.this$0.getActivity(), this.val$theme);
        AndroidUtils.restartApplication(this.this$1.this$0.getActivity(), "ThemeActivity");
    }
}
