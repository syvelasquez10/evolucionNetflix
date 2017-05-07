// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.widget.AdapterView;
import android.text.TextUtils;
import android.widget.TextView;
import android.support.v7.mediarouter.R$layout;
import android.view.ViewGroup;
import android.view.View;
import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.AdapterView$OnItemClickListener;
import android.support.v7.media.MediaRouter$RouteInfo;
import android.widget.ArrayAdapter;

final class MediaRouteChooserDialog$RouteAdapter extends ArrayAdapter<MediaRouter$RouteInfo> implements AdapterView$OnItemClickListener
{
    private final LayoutInflater mInflater;
    final /* synthetic */ MediaRouteChooserDialog this$0;
    
    public MediaRouteChooserDialog$RouteAdapter(final MediaRouteChooserDialog this$0, final Context context, final List<MediaRouter$RouteInfo> list) {
        this.this$0 = this$0;
        super(context, 0, (List)list);
        this.mInflater = LayoutInflater.from(context);
    }
    
    public boolean areAllItemsEnabled() {
        return false;
    }
    
    public View getView(final int n, final View view, final ViewGroup viewGroup) {
        View inflate = view;
        if (view == null) {
            inflate = this.mInflater.inflate(R$layout.mr_media_route_list_item, viewGroup, false);
        }
        final MediaRouter$RouteInfo mediaRouter$RouteInfo = (MediaRouter$RouteInfo)this.getItem(n);
        final TextView textView = (TextView)inflate.findViewById(16908308);
        final TextView textView2 = (TextView)inflate.findViewById(16908309);
        textView.setText((CharSequence)mediaRouter$RouteInfo.getName());
        final String description = mediaRouter$RouteInfo.getDescription();
        if (TextUtils.isEmpty((CharSequence)description)) {
            textView2.setVisibility(8);
            textView2.setText((CharSequence)"");
        }
        else {
            textView2.setVisibility(0);
            textView2.setText((CharSequence)description);
        }
        inflate.setEnabled(mediaRouter$RouteInfo.isEnabled());
        return inflate;
    }
    
    public boolean isEnabled(final int n) {
        return ((MediaRouter$RouteInfo)this.getItem(n)).isEnabled();
    }
    
    public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
        final MediaRouter$RouteInfo mediaRouter$RouteInfo = (MediaRouter$RouteInfo)this.getItem(n);
        if (mediaRouter$RouteInfo.isEnabled()) {
            mediaRouter$RouteInfo.select();
            this.this$0.dismiss();
        }
    }
}
