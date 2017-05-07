// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import java.util.List;
import java.util.Comparator;
import android.widget.AdapterView;
import android.text.TextUtils;
import android.widget.TextView;
import android.view.ViewGroup;
import android.view.View;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.ListAdapter;
import android.support.v7.mediarouter.R;
import android.os.Bundle;
import android.content.Context;
import android.support.v7.media.MediaRouteSelector;
import android.support.v7.media.MediaRouter;
import android.widget.ListView;
import android.app.Dialog;

public class MediaRouteChooserDialog extends Dialog
{
    private RouteAdapter mAdapter;
    private boolean mAttachedToWindow;
    private final MediaRouterCallback mCallback;
    private ListView mListView;
    private final MediaRouter mRouter;
    private MediaRouteSelector mSelector;
    
    public MediaRouteChooserDialog(final Context context) {
        this(context, 0);
    }
    
    public MediaRouteChooserDialog(final Context context, final int n) {
        super(MediaRouterThemeHelper.createThemedContext(context, true), n);
        this.mSelector = MediaRouteSelector.EMPTY;
        this.mRouter = MediaRouter.getInstance(this.getContext());
        this.mCallback = new MediaRouterCallback();
    }
    
    public MediaRouteSelector getRouteSelector() {
        return this.mSelector;
    }
    
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mAttachedToWindow = true;
        this.mRouter.addCallback(this.mSelector, (MediaRouter.Callback)this.mCallback, 1);
        this.refreshRoutes();
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.getWindow().requestFeature(3);
        this.setContentView(R.layout.mr_media_route_chooser_dialog);
        this.setTitle(R.string.mr_media_route_chooser_title);
        this.getWindow().setFeatureDrawableResource(3, MediaRouterThemeHelper.getThemeResource(this.getContext(), R.attr.mediaRouteOffDrawable));
        this.mAdapter = new RouteAdapter(this.getContext());
        (this.mListView = (ListView)this.findViewById(R.id.media_route_list)).setAdapter((ListAdapter)this.mAdapter);
        this.mListView.setOnItemClickListener((AdapterView$OnItemClickListener)this.mAdapter);
        this.mListView.setEmptyView(this.findViewById(16908292));
    }
    
    public void onDetachedFromWindow() {
        this.mAttachedToWindow = false;
        this.mRouter.removeCallback((MediaRouter.Callback)this.mCallback);
        super.onDetachedFromWindow();
    }
    
    public boolean onFilterRoute(final MediaRouter.RouteInfo routeInfo) {
        return !routeInfo.isDefault() && routeInfo.isEnabled() && routeInfo.matchesSelector(this.mSelector);
    }
    
    public void refreshRoutes() {
        if (this.mAttachedToWindow) {
            this.mAdapter.update();
        }
    }
    
    public void setRouteSelector(final MediaRouteSelector mSelector) {
        if (mSelector == null) {
            throw new IllegalArgumentException("selector must not be null");
        }
        if (!this.mSelector.equals(mSelector)) {
            this.mSelector = mSelector;
            if (this.mAttachedToWindow) {
                this.mRouter.removeCallback((MediaRouter.Callback)this.mCallback);
                this.mRouter.addCallback(mSelector, (MediaRouter.Callback)this.mCallback, 1);
            }
            this.refreshRoutes();
        }
    }
    
    private final class MediaRouterCallback extends Callback
    {
        @Override
        public void onRouteAdded(final MediaRouter mediaRouter, final RouteInfo routeInfo) {
            MediaRouteChooserDialog.this.refreshRoutes();
        }
        
        @Override
        public void onRouteChanged(final MediaRouter mediaRouter, final RouteInfo routeInfo) {
            MediaRouteChooserDialog.this.refreshRoutes();
        }
        
        @Override
        public void onRouteRemoved(final MediaRouter mediaRouter, final RouteInfo routeInfo) {
            MediaRouteChooserDialog.this.refreshRoutes();
        }
        
        @Override
        public void onRouteSelected(final MediaRouter mediaRouter, final RouteInfo routeInfo) {
            MediaRouteChooserDialog.this.dismiss();
        }
    }
    
    private final class RouteAdapter extends ArrayAdapter<MediaRouter.RouteInfo> implements AdapterView$OnItemClickListener
    {
        private final LayoutInflater mInflater;
        
        public RouteAdapter(final Context context) {
            super(context, 0);
            this.mInflater = LayoutInflater.from(context);
        }
        
        public boolean areAllItemsEnabled() {
            return false;
        }
        
        public View getView(final int n, View inflate, final ViewGroup viewGroup) {
            if ((inflate = inflate) == null) {
                inflate = this.mInflater.inflate(R.layout.mr_media_route_list_item, viewGroup, false);
            }
            final MediaRouter.RouteInfo routeInfo = (MediaRouter.RouteInfo)this.getItem(n);
            final TextView textView = (TextView)inflate.findViewById(16908308);
            final TextView textView2 = (TextView)inflate.findViewById(16908309);
            textView.setText((CharSequence)routeInfo.getName());
            final String description = routeInfo.getDescription();
            if (TextUtils.isEmpty((CharSequence)description)) {
                textView2.setVisibility(8);
                textView2.setText((CharSequence)"");
            }
            else {
                textView2.setVisibility(0);
                textView2.setText((CharSequence)description);
            }
            inflate.setEnabled(routeInfo.isEnabled());
            return inflate;
        }
        
        public boolean isEnabled(final int n) {
            return ((MediaRouter.RouteInfo)this.getItem(n)).isEnabled();
        }
        
        public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
            final MediaRouter.RouteInfo routeInfo = (MediaRouter.RouteInfo)this.getItem(n);
            if (routeInfo.isEnabled()) {
                routeInfo.select();
                MediaRouteChooserDialog.this.dismiss();
            }
        }
        
        public void update() {
            this.clear();
            final List<MediaRouter.RouteInfo> routes = MediaRouteChooserDialog.this.mRouter.getRoutes();
            for (int size = routes.size(), i = 0; i < size; ++i) {
                final MediaRouter.RouteInfo routeInfo = routes.get(i);
                if (MediaRouteChooserDialog.this.onFilterRoute(routeInfo)) {
                    this.add((Object)routeInfo);
                }
            }
            this.sort((Comparator)RouteComparator.sInstance);
            this.notifyDataSetChanged();
        }
    }
    
    private static final class RouteComparator implements Comparator<MediaRouter.RouteInfo>
    {
        public static final RouteComparator sInstance;
        
        static {
            sInstance = new RouteComparator();
        }
        
        @Override
        public int compare(final MediaRouter.RouteInfo routeInfo, final MediaRouter.RouteInfo routeInfo2) {
            return routeInfo.getName().compareTo(routeInfo2.getName());
        }
    }
}
