// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import java.util.Comparator;
import java.util.Collections;
import java.util.Collection;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.ListAdapter;
import android.support.v7.mediarouter.R$id;
import java.util.List;
import android.support.v7.mediarouter.R$attr;
import android.support.v7.mediarouter.R$string;
import android.support.v7.mediarouter.R$layout;
import android.os.Bundle;
import android.support.v7.media.MediaRouter$Callback;
import android.support.annotation.NonNull;
import android.content.Context;
import android.support.v7.media.MediaRouteSelector;
import android.support.v7.media.MediaRouter$RouteInfo;
import java.util.ArrayList;
import android.support.v7.media.MediaRouter;
import android.widget.ListView;
import android.app.Dialog;

public class MediaRouteChooserDialog extends Dialog
{
    private MediaRouteChooserDialog$RouteAdapter mAdapter;
    private boolean mAttachedToWindow;
    private final MediaRouteChooserDialog$MediaRouterCallback mCallback;
    private ListView mListView;
    private final MediaRouter mRouter;
    private ArrayList<MediaRouter$RouteInfo> mRoutes;
    private MediaRouteSelector mSelector;
    
    public MediaRouteChooserDialog(final Context context) {
        this(context, 0);
    }
    
    public MediaRouteChooserDialog(final Context context, final int n) {
        super(MediaRouterThemeHelper.createThemedContext(context, true), n);
        this.mSelector = MediaRouteSelector.EMPTY;
        this.mRouter = MediaRouter.getInstance(this.getContext());
        this.mCallback = new MediaRouteChooserDialog$MediaRouterCallback(this, null);
    }
    
    @NonNull
    public MediaRouteSelector getRouteSelector() {
        return this.mSelector;
    }
    
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mAttachedToWindow = true;
        this.mRouter.addCallback(this.mSelector, this.mCallback, 1);
        this.refreshRoutes();
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.getWindow().requestFeature(3);
        this.setContentView(R$layout.mr_media_route_chooser_dialog);
        this.setTitle(R$string.mr_media_route_chooser_title);
        this.getWindow().setFeatureDrawableResource(3, MediaRouterThemeHelper.getThemeResource(this.getContext(), R$attr.mediaRouteOffDrawable));
        this.mRoutes = new ArrayList<MediaRouter$RouteInfo>();
        this.mAdapter = new MediaRouteChooserDialog$RouteAdapter(this.getContext(), this.mRoutes);
        (this.mListView = (ListView)this.findViewById(R$id.media_route_list)).setAdapter((ListAdapter)this.mAdapter);
        this.mListView.setOnItemClickListener((AdapterView$OnItemClickListener)this.mAdapter);
        this.mListView.setEmptyView(this.findViewById(16908292));
    }
    
    public void onDetachedFromWindow() {
        this.mAttachedToWindow = false;
        this.mRouter.removeCallback(this.mCallback);
        super.onDetachedFromWindow();
    }
    
    public boolean onFilterRoute(@NonNull final MediaRouter$RouteInfo mediaRouter$RouteInfo) {
        return !mediaRouter$RouteInfo.isDefault() && mediaRouter$RouteInfo.isEnabled() && mediaRouter$RouteInfo.matchesSelector(this.mSelector);
    }
    
    public void onFilterRoutes(@NonNull final List<MediaRouter$RouteInfo> list) {
        int size = list.size();
        while (true) {
            final int n = size - 1;
            if (size <= 0) {
                break;
            }
            if (!this.onFilterRoute(list.get(n))) {
                list.remove(n);
                size = n;
            }
            else {
                size = n;
            }
        }
    }
    
    public void refreshRoutes() {
        if (this.mAttachedToWindow) {
            this.mRoutes.clear();
            this.mRoutes.addAll(this.mRouter.getRoutes());
            this.onFilterRoutes(this.mRoutes);
            Collections.sort(this.mRoutes, MediaRouteChooserDialog$RouteComparator.sInstance);
            this.mAdapter.notifyDataSetChanged();
        }
    }
    
    public void setRouteSelector(@NonNull final MediaRouteSelector mSelector) {
        if (mSelector == null) {
            throw new IllegalArgumentException("selector must not be null");
        }
        if (!this.mSelector.equals(mSelector)) {
            this.mSelector = mSelector;
            if (this.mAttachedToWindow) {
                this.mRouter.removeCallback(this.mCallback);
                this.mRouter.addCallback(mSelector, this.mCallback, 1);
            }
            this.refreshRoutes();
        }
    }
}
