// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.view.View;
import android.os.Bundle;

public class ShowDetailsFrag_Ab7994 extends ShowDetailsFrag
{
    public static ShowDetailsFrag_Ab7994 create(final String s, final String s2) {
        final ShowDetailsFrag_Ab7994 showDetailsFrag_Ab7994 = new ShowDetailsFrag_Ab7994();
        final Bundle arguments = new Bundle();
        arguments.putString("extra_video_id", s);
        arguments.putString("extra_episode_id", s2);
        showDetailsFrag_Ab7994.setArguments(arguments);
        return showDetailsFrag_Ab7994;
    }
    
    @Override
    protected int getLayoutId() {
        return 2130903337;
    }
    
    @Override
    protected void initDetailsViewGroup(final View view) {
        this.detailsViewGroup = (VideoDetailsViewGroup_Ab7994)view.findViewById(2131821551);
    }
}
