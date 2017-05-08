// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.offline;

import android.view.View$OnClickListener;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfig$Cell;
import com.netflix.mediaclient.service.configuration.PersistentConfig;
import android.content.Context;

public class OfflineTutorialContentBinder
{
    private String body;
    private String closeButton;
    private String header;
    private Context mContext;
    private String nextButton;
    private String title;
    
    public OfflineTutorialContentBinder(final Context mContext) {
        final ABTestConfig$Cell offlineTutorial = PersistentConfig.getOfflineTutorial(mContext);
        this.mContext = mContext;
        this.header = mContext.getString(2131231382);
        this.closeButton = mContext.getString(2131231167);
        this.nextButton = mContext.getString(2131231322);
        switch (OfflineTutorialContentBinder$3.$SwitchMap$com$netflix$mediaclient$service$webclient$model$leafs$ABTestConfig$Cell[offlineTutorial.ordinal()]) {
            default: {}
            case 1:
            case 2: {
                this.title = mContext.getString(2131231378);
                this.body = mContext.getString(2131231377);
            }
            case 3: {
                this.title = mContext.getString(2131231380);
                this.body = mContext.getString(2131231379);
            }
        }
    }
    
    public View bind(final ViewGroup viewGroup, final OfflineTutorialDialogFrag offlineTutorialDialogFrag) {
        final View inflate = LayoutInflater.from(this.mContext).inflate(2130903229, viewGroup, true);
        ((TextView)inflate.findViewById(2131690070)).setText((CharSequence)this.header);
        ((TextView)inflate.findViewById(2131689797)).setText((CharSequence)this.title);
        ((TextView)inflate.findViewById(2131690069)).setText((CharSequence)this.body);
        ((TextView)inflate.findViewById(2131690071)).setText((CharSequence)this.nextButton);
        ((TextView)inflate.findViewById(2131690072)).setText((CharSequence)this.closeButton);
        inflate.findViewById(2131690071).setOnClickListener((View$OnClickListener)new OfflineTutorialContentBinder$1(this, offlineTutorialDialogFrag));
        inflate.findViewById(2131690072).setOnClickListener((View$OnClickListener)new OfflineTutorialContentBinder$2(this, offlineTutorialDialogFrag));
        return inflate;
    }
}
