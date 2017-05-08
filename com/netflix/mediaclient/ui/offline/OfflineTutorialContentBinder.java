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
        this.header = mContext.getString(2131296939);
        this.closeButton = mContext.getString(2131296724);
        this.nextButton = mContext.getString(2131296875);
        switch (OfflineTutorialContentBinder$3.$SwitchMap$com$netflix$mediaclient$service$webclient$model$leafs$ABTestConfig$Cell[offlineTutorial.ordinal()]) {
            default: {}
            case 1:
            case 2: {
                this.title = mContext.getString(2131296935);
                this.body = mContext.getString(2131296937);
            }
            case 3: {
                this.title = mContext.getString(2131296936);
                this.body = mContext.getString(2131296937);
            }
        }
    }
    
    public View bind(final ViewGroup viewGroup, final OfflineTutorialDialogFrag offlineTutorialDialogFrag) {
        final View inflate = LayoutInflater.from(this.mContext).inflate(2130903242, viewGroup, true);
        ((TextView)inflate.findViewById(2131755637)).setText((CharSequence)this.header);
        ((TextView)inflate.findViewById(2131755349)).setText((CharSequence)this.title);
        ((TextView)inflate.findViewById(2131755636)).setText((CharSequence)this.body);
        ((TextView)inflate.findViewById(2131755639)).setText((CharSequence)this.nextButton);
        ((TextView)inflate.findViewById(2131755640)).setText((CharSequence)this.closeButton);
        inflate.findViewById(2131755638).setOnClickListener((View$OnClickListener)new OfflineTutorialContentBinder$1(this, offlineTutorialDialogFrag));
        inflate.findViewById(2131755640).setOnClickListener((View$OnClickListener)new OfflineTutorialContentBinder$2(this, offlineTutorialDialogFrag));
        return inflate;
    }
}
