// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.model.details.ShowDetails;
import android.content.Context;

public class ShowDetailsFrag$ShowDetailsStringProvider implements VideoDetailsViewGroup$DetailsStringProvider
{
    private final Context context;
    private final ShowDetails details;
    
    public ShowDetailsFrag$ShowDetailsStringProvider(final Context context, final ShowDetails details) {
        this.context = context;
        this.details = details;
    }
    
    @Override
    public CharSequence getBasicInfoString() {
        return StringUtils.getBasicShowInfoString(this.context, this.details);
    }
    
    @Override
    public CharSequence getCreatorsText() {
        if (StringUtils.isEmpty(this.details.getCreators())) {
            return null;
        }
        return StringUtils.createBoldLabeledText(this.context, 2131493148, this.details.getCreators());
    }
    
    @Override
    public CharSequence getStarringText() {
        return StringUtils.createBoldLabeledText(this.context, 2131493147, this.details.getActors());
    }
}
