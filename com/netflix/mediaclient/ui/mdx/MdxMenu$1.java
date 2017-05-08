// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.AnimationDrawable;
import com.netflix.mediaclient.Log;
import android.view.Menu;
import android.os.Handler;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.support.v7.app.AlertDialog;
import android.app.Dialog;
import com.netflix.mediaclient.util.MdxUtils$MdxTargetSelectionDialogInterface;
import com.netflix.mediaclient.util.MdxUtils;
import android.view.MenuItem;
import android.view.MenuItem$OnMenuItemClickListener;

class MdxMenu$1 implements MenuItem$OnMenuItemClickListener
{
    final /* synthetic */ MdxMenu this$0;
    
    MdxMenu$1(final MdxMenu this$0) {
        this.this$0 = this$0;
    }
    
    public boolean onMenuItemClick(final MenuItem menuItem) {
        final AlertDialog mdxMenuDialog = MdxUtils.createMdxMenuDialog(this.this$0.activity, this.this$0.activity.getMdxMiniPlayerFrag());
        if (mdxMenuDialog != null) {
            this.this$0.activity.displayDialog(mdxMenuDialog);
        }
        return true;
    }
}
