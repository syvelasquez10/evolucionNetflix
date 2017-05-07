// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick_kids.details;

import com.netflix.mediaclient.util.DeviceUtils;
import android.content.Context;
import com.netflix.mediaclient.ui.kubrick.details.KubrickVideoDetailsViewGroup;

class KubrickKidsCharacterDetailsFrag$KubrickKidsVideoDetailsViewGroup extends KubrickVideoDetailsViewGroup
{
    final /* synthetic */ KubrickKidsCharacterDetailsFrag this$0;
    
    public KubrickKidsCharacterDetailsFrag$KubrickKidsVideoDetailsViewGroup(final KubrickKidsCharacterDetailsFrag this$0, final Context context) {
        this.this$0 = this$0;
        super(context);
        this.setupViews();
    }
    
    private void setupViews() {
        final int screenWidthInPixels = DeviceUtils.getScreenWidthInPixels((Context)this.this$0.getActivity());
        final int height = (int)(screenWidthInPixels * 0.5625f);
        this.getBackgroundImage().getLayoutParams().width = screenWidthInPixels;
        this.getBackgroundImage().getLayoutParams().height = height;
        this.getHeroImage().getLayoutParams().width = screenWidthInPixels / 2;
        this.getHeroImage().getLayoutParams().height = height / 2;
    }
    
    @Override
    protected void alignViews() {
    }
    
    @Override
    protected int getlayoutId() {
        return 2130903100;
    }
}
