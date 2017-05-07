// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import android.graphics.drawable.Drawable;
import android.util.Pair;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.Log;
import android.view.Menu;
import com.netflix.mediaclient.android.activity.NetflixActivity;
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
        this.this$0.activity.displayDialog((Dialog)MdxUtils.createMdxTargetSelectionDialog(this.this$0.activity, this.this$0.activity.getMdxMiniPlayerFrag()));
        return true;
    }
}
