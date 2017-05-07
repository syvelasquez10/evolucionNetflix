// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.settings;

import com.netflix.mediaclient.android.widget.NetflixActionBar;
import android.widget.ListAdapter;
import com.netflix.mediaclient.util.ViewUtils;
import android.widget.ListView;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import android.content.Intent;
import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.text.util.Linkify;
import android.widget.TextView;
import android.view.ViewGroup;
import android.view.View;
import android.widget.BaseAdapter;

class OpenSourceLicensesActivity$OslAdapter extends BaseAdapter
{
    final /* synthetic */ OpenSourceLicensesActivity this$0;
    
    private OpenSourceLicensesActivity$OslAdapter(final OpenSourceLicensesActivity this$0) {
        this.this$0 = this$0;
    }
    
    public int getCount() {
        return OpenSourceLicensesActivity.oslInfo.size();
    }
    
    public OpenSourceLicensesActivity$OslInfo getItem(final int n) {
        return OpenSourceLicensesActivity.oslInfo.get(n);
    }
    
    public long getItemId(final int n) {
        return n;
    }
    
    public View getView(final int n, final View view, final ViewGroup viewGroup) {
        View inflate = view;
        if (view == null) {
            inflate = this.this$0.getLayoutInflater().inflate(2130903156, (ViewGroup)null);
            inflate.setTag((Object)new OpenSourceLicensesActivity$Holder((TextView)inflate.findViewById(2131165546), (TextView)inflate.findViewById(2131165547)));
        }
        final OpenSourceLicensesActivity$Holder openSourceLicensesActivity$Holder = (OpenSourceLicensesActivity$Holder)inflate.getTag();
        final OpenSourceLicensesActivity$OslInfo item = this.getItem(n);
        openSourceLicensesActivity$Holder.title.setText((CharSequence)item.title);
        openSourceLicensesActivity$Holder.license.setText((CharSequence)item.license);
        Linkify.addLinks(openSourceLicensesActivity$Holder.license, 1);
        return inflate;
    }
}
