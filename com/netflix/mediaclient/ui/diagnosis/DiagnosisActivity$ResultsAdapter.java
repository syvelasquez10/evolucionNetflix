// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.diagnosis;

import com.netflix.mediaclient.service.diagnostics.DiagnosisAgent$UrlStatus;
import com.netflix.mediaclient.service.diagnostics.UrlNetworkState;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.util.Log;
import android.content.Context;
import android.annotation.SuppressLint;
import android.widget.ArrayAdapter;

@SuppressLint({ "ViewHolder" })
public class DiagnosisActivity$ResultsAdapter extends ArrayAdapter<String>
{
    Context context;
    final /* synthetic */ DiagnosisActivity this$0;
    
    public DiagnosisActivity$ResultsAdapter(final DiagnosisActivity this$0, final Context context) {
        this.this$0 = this$0;
        super(context, 2130903090, 2131427507);
        this.context = context;
    }
    
    public boolean areAllItemsEnabled() {
        return false;
    }
    
    public int getCount() {
        if (this.this$0.mUrlList == null) {
            Log.d("DiagnosisActivity", "urlList is null");
            return 0;
        }
        if (Log.isLoggable("DiagnosisActivity", 3)) {
            Log.d("DiagnosisActivity", "urlList size: " + this.this$0.mUrlList.size());
        }
        return this.this$0.mUrlList.size();
    }
    
    public View getView(final int n, View inflate, final ViewGroup viewGroup) {
        inflate = ((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(2130903090, viewGroup, false);
        final TextView textView = (TextView)inflate.findViewById(2131427509);
        final TextView textView2 = (TextView)inflate.findViewById(2131427510);
        final ImageView imageView = (ImageView)inflate.findViewById(2131427508);
        imageView.setImageResource(2130837739);
        final UrlNetworkState urlNetworkState = this.this$0.mUrlList.get(n);
        this.setTitleText(textView, urlNetworkState.getUrl(), n);
        if (urlNetworkState.getStatus().equals(DiagnosisAgent$UrlStatus.COMPLETED)) {
            if (urlNetworkState.getResult() != 0) {
                textView2.setText((CharSequence)("nw-" + urlNetworkState.getErrorGroup() + "-" + urlNetworkState.getErrorCode()));
                imageView.setImageResource(2130837738);
                return inflate;
            }
            imageView.setImageResource(2130837739);
            textView2.setVisibility(4);
        }
        else {
            if (urlNetworkState.getStatus().equals(DiagnosisAgent$UrlStatus.TEST_ONGOING)) {
                imageView.setVisibility(4);
                textView2.setVisibility(4);
                return inflate;
            }
            if (urlNetworkState.getStatus().equals(DiagnosisAgent$UrlStatus.NOT_TESTED)) {
                imageView.setVisibility(4);
                textView2.setVisibility(4);
                textView.setVisibility(4);
                return inflate;
            }
        }
        return inflate;
    }
    
    public void setTitleText(final TextView textView, final String s, final int n) {
        if (s != null && s.contains("netflix")) {
            textView.setText((CharSequence)this.context.getString(2131493349, new Object[] { n + 1 }));
            return;
        }
        textView.setText(2131493350);
    }
}
