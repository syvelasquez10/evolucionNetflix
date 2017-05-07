// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import android.widget.CheckBox;
import java.util.Collections;
import com.facebook.FacebookException;
import android.support.v4.content.Loader;
import android.support.v4.app.LoaderManager;
import android.text.TextUtils;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.app.Activity;
import android.widget.ListAdapter;
import android.view.View$OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView$OnItemClickListener;
import android.view.LayoutInflater;
import android.content.Context;
import com.facebook.SessionState;
import java.util.List;
import com.facebook.Session;
import java.util.Set;
import java.util.Collection;
import java.util.Arrays;
import android.view.animation.Animation;
import android.view.animation.AlphaAnimation;
import com.facebook.Request;
import android.view.View$OnClickListener;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import com.facebook.android.R;
import android.view.ViewStub;
import android.view.ViewGroup;
import android.view.View;
import android.widget.AbsListView;
import android.os.Bundle;
import android.widget.TextView;
import com.facebook.internal.SessionTracker;
import android.widget.AbsListView$OnScrollListener;
import android.widget.ListView;
import java.util.HashSet;
import android.graphics.drawable.Drawable;
import android.widget.Button;
import android.widget.ProgressBar;
import android.support.v4.app.Fragment;
import com.facebook.model.GraphObject;

public abstract class PickerFragment<T extends GraphObject> extends Fragment
{
    private static final String ACTIVITY_CIRCLE_SHOW_KEY = "com.facebook.android.PickerFragment.ActivityCircleShown";
    public static final String DONE_BUTTON_TEXT_BUNDLE_KEY = "com.facebook.widget.PickerFragment.DoneButtonText";
    public static final String EXTRA_FIELDS_BUNDLE_KEY = "com.facebook.widget.PickerFragment.ExtraFields";
    private static final int PROFILE_PICTURE_PREFETCH_BUFFER = 5;
    private static final String SELECTION_BUNDLE_KEY = "com.facebook.android.PickerFragment.Selection";
    public static final String SHOW_PICTURES_BUNDLE_KEY = "com.facebook.widget.PickerFragment.ShowPictures";
    public static final String SHOW_TITLE_BAR_BUNDLE_KEY = "com.facebook.widget.PickerFragment.ShowTitleBar";
    public static final String TITLE_TEXT_BUNDLE_KEY = "com.facebook.widget.PickerFragment.TitleText";
    private ProgressBar activityCircle;
    GraphObjectAdapter<T> adapter;
    private Button doneButton;
    private Drawable doneButtonBackground;
    private String doneButtonText;
    HashSet<String> extraFields;
    private GraphObjectFilter<T> filter;
    private final Class<T> graphObjectClass;
    private final int layout;
    private ListView listView;
    private LoadingStrategy loadingStrategy;
    private OnDataChangedListener onDataChangedListener;
    private OnDoneButtonClickedListener onDoneButtonClickedListener;
    private OnErrorListener onErrorListener;
    private final AbsListView$OnScrollListener onScrollListener;
    private OnSelectionChangedListener onSelectionChangedListener;
    private SelectionStrategy selectionStrategy;
    private SessionTracker sessionTracker;
    private boolean showPictures;
    private boolean showTitleBar;
    private Drawable titleBarBackground;
    private String titleText;
    private TextView titleTextView;
    
    PickerFragment(final Class<T> graphObjectClass, final int layout, final Bundle pickerFragmentSettingsFromBundle) {
        this.showPictures = true;
        this.showTitleBar = true;
        this.extraFields = new HashSet<String>();
        this.onScrollListener = (AbsListView$OnScrollListener)new AbsListView$OnScrollListener() {
            public void onScroll(final AbsListView absListView, final int n, final int n2, final int n3) {
                PickerFragment.this.reprioritizeDownloads();
            }
            
            public void onScrollStateChanged(final AbsListView absListView, final int n) {
            }
        };
        this.graphObjectClass = graphObjectClass;
        this.layout = layout;
        this.setPickerFragmentSettingsFromBundle(pickerFragmentSettingsFromBundle);
    }
    
    private void clearResults() {
        if (this.adapter != null) {
            boolean b;
            if (!this.selectionStrategy.isEmpty()) {
                b = true;
            }
            else {
                b = false;
            }
            boolean b2;
            if (!this.adapter.isEmpty()) {
                b2 = true;
            }
            else {
                b2 = false;
            }
            this.loadingStrategy.clearResults();
            this.selectionStrategy.clear();
            this.adapter.notifyDataSetChanged();
            if (b2 && this.onDataChangedListener != null) {
                this.onDataChangedListener.onDataChanged(this);
            }
            if (b && this.onSelectionChangedListener != null) {
                this.onSelectionChangedListener.onSelectionChanged(this);
            }
        }
    }
    
    private void inflateTitleBar(final ViewGroup viewGroup) {
        final ViewStub viewStub = (ViewStub)viewGroup.findViewById(R.id.com_facebook_picker_title_bar_stub);
        if (viewStub != null) {
            final View inflate = viewStub.inflate();
            final RelativeLayout$LayoutParams layoutParams = new RelativeLayout$LayoutParams(-1, -1);
            layoutParams.addRule(3, R.id.com_facebook_picker_title_bar);
            this.listView.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
            if (this.titleBarBackground != null) {
                inflate.setBackground(this.titleBarBackground);
            }
            this.doneButton = (Button)viewGroup.findViewById(R.id.com_facebook_picker_done_button);
            if (this.doneButton != null) {
                this.doneButton.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                    public void onClick(final View view) {
                        if (PickerFragment.this.onDoneButtonClickedListener != null) {
                            PickerFragment.this.onDoneButtonClickedListener.onDoneButtonClicked(PickerFragment.this);
                        }
                    }
                });
                if (this.getDoneButtonText() != null) {
                    this.doneButton.setText((CharSequence)this.getDoneButtonText());
                }
                if (this.doneButtonBackground != null) {
                    this.doneButton.setBackground(this.doneButtonBackground);
                }
            }
            this.titleTextView = (TextView)viewGroup.findViewById(R.id.com_facebook_picker_title);
            if (this.titleTextView != null && this.getTitleText() != null) {
                this.titleTextView.setText((CharSequence)this.getTitleText());
            }
        }
    }
    
    private void loadDataSkippingRoundTripIfCached() {
        this.clearResults();
        final Request requestForLoadData = this.getRequestForLoadData(this.getSession());
        if (requestForLoadData != null) {
            this.onLoadingData();
            this.loadingStrategy.startLoading(requestForLoadData);
        }
    }
    
    private void onListItemClick(final ListView listView, final View view, final int n) {
        this.selectionStrategy.toggleSelection(this.adapter.getIdOfGraphObject((T)listView.getItemAtPosition(n)));
        this.adapter.notifyDataSetChanged();
        if (this.onSelectionChangedListener != null) {
            this.onSelectionChangedListener.onSelectionChanged(this);
        }
    }
    
    private void reprioritizeDownloads() {
        final int lastVisiblePosition = this.listView.getLastVisiblePosition();
        if (lastVisiblePosition >= 0) {
            this.adapter.prioritizeViewRange(this.listView.getFirstVisiblePosition(), lastVisiblePosition, 5);
        }
    }
    
    private static void setAlpha(final View view, final float n) {
        final AlphaAnimation alphaAnimation = new AlphaAnimation(n, n);
        alphaAnimation.setDuration(0L);
        alphaAnimation.setFillAfter(true);
        view.startAnimation((Animation)alphaAnimation);
    }
    
    private void setPickerFragmentSettingsFromBundle(final Bundle bundle) {
        if (bundle != null) {
            this.showPictures = bundle.getBoolean("com.facebook.widget.PickerFragment.ShowPictures", this.showPictures);
            final String string = bundle.getString("com.facebook.widget.PickerFragment.ExtraFields");
            if (string != null) {
                this.setExtraFields(Arrays.asList(string.split(",")));
            }
            this.showTitleBar = bundle.getBoolean("com.facebook.widget.PickerFragment.ShowTitleBar", this.showTitleBar);
            final String string2 = bundle.getString("com.facebook.widget.PickerFragment.TitleText");
            if (string2 != null) {
                this.titleText = string2;
                if (this.titleTextView != null) {
                    this.titleTextView.setText((CharSequence)this.titleText);
                }
            }
            final String string3 = bundle.getString("com.facebook.widget.PickerFragment.DoneButtonText");
            if (string3 != null) {
                this.doneButtonText = string3;
                if (this.doneButton != null) {
                    this.doneButton.setText((CharSequence)this.doneButtonText);
                }
            }
        }
    }
    
    abstract PickerFragmentAdapter<T> createAdapter();
    
    abstract LoadingStrategy createLoadingStrategy();
    
    abstract SelectionStrategy createSelectionStrategy();
    
    void displayActivityCircle() {
        if (this.activityCircle != null) {
            this.layoutActivityCircle();
            this.activityCircle.setVisibility(0);
        }
    }
    
    boolean filterIncludesItem(final T t) {
        return this.filter == null || this.filter.includeItem(t);
    }
    
    String getDefaultDoneButtonText() {
        return this.getString(R.string.com_facebook_picker_done_button_text);
    }
    
    String getDefaultTitleText() {
        return null;
    }
    
    public String getDoneButtonText() {
        if (this.doneButtonText == null) {
            this.doneButtonText = this.getDefaultDoneButtonText();
        }
        return this.doneButtonText;
    }
    
    public Set<String> getExtraFields() {
        return new HashSet<String>(this.extraFields);
    }
    
    public GraphObjectFilter<T> getFilter() {
        return this.filter;
    }
    
    public OnDataChangedListener getOnDataChangedListener() {
        return this.onDataChangedListener;
    }
    
    public OnDoneButtonClickedListener getOnDoneButtonClickedListener() {
        return this.onDoneButtonClickedListener;
    }
    
    public OnErrorListener getOnErrorListener() {
        return this.onErrorListener;
    }
    
    public OnSelectionChangedListener getOnSelectionChangedListener() {
        return this.onSelectionChangedListener;
    }
    
    abstract Request getRequestForLoadData(final Session p0);
    
    List<T> getSelectedGraphObjects() {
        return this.adapter.getGraphObjectsById(this.selectionStrategy.getSelectedIds());
    }
    
    public Session getSession() {
        return this.sessionTracker.getSession();
    }
    
    public boolean getShowPictures() {
        return this.showPictures;
    }
    
    public boolean getShowTitleBar() {
        return this.showTitleBar;
    }
    
    public String getTitleText() {
        if (this.titleText == null) {
            this.titleText = this.getDefaultTitleText();
        }
        return this.titleText;
    }
    
    void hideActivityCircle() {
        if (this.activityCircle != null) {
            this.activityCircle.clearAnimation();
            this.activityCircle.setVisibility(4);
        }
    }
    
    void layoutActivityCircle() {
        float n;
        if (!this.adapter.isEmpty()) {
            n = 0.25f;
        }
        else {
            n = 1.0f;
        }
        setAlpha((View)this.activityCircle, n);
    }
    
    public void loadData(final boolean b) {
        if (!b && this.loadingStrategy.isDataPresentOrLoading()) {
            return;
        }
        this.loadDataSkippingRoundTripIfCached();
    }
    
    @Override
    public void onActivityCreated(final Bundle settingsFromBundle) {
        super.onActivityCreated(settingsFromBundle);
        this.sessionTracker = new SessionTracker((Context)this.getActivity(), new Session.StatusCallback() {
            @Override
            public void call(final Session session, final SessionState sessionState, final Exception ex) {
                if (!session.isOpened()) {
                    PickerFragment.this.clearResults();
                }
            }
        });
        this.setSettingsFromBundle(settingsFromBundle);
        (this.loadingStrategy = this.createLoadingStrategy()).attach(this.adapter);
        (this.selectionStrategy = this.createSelectionStrategy()).readSelectionFromBundle(settingsFromBundle, "com.facebook.android.PickerFragment.Selection");
        if (this.showTitleBar) {
            this.inflateTitleBar((ViewGroup)this.getView());
        }
        if (this.activityCircle != null && settingsFromBundle != null) {
            if (!settingsFromBundle.getBoolean("com.facebook.android.PickerFragment.ActivityCircleShown", false)) {
                this.hideActivityCircle();
                return;
            }
            this.displayActivityCircle();
        }
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        (this.adapter = this.createAdapter()).setFilter((GraphObjectAdapter.Filter<T>)new GraphObjectAdapter.Filter<T>() {
            public boolean includeItem(final T t) {
                return PickerFragment.this.filterIncludesItem(t);
            }
        });
    }
    
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        final ViewGroup viewGroup2 = (ViewGroup)layoutInflater.inflate(this.layout, viewGroup, false);
        (this.listView = (ListView)viewGroup2.findViewById(R.id.com_facebook_picker_list_view)).setOnItemClickListener((AdapterView$OnItemClickListener)new AdapterView$OnItemClickListener() {
            public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                PickerFragment.this.onListItemClick((ListView)adapterView, view, n);
            }
        });
        this.listView.setOnLongClickListener((View$OnLongClickListener)new View$OnLongClickListener() {
            public boolean onLongClick(final View view) {
                return false;
            }
        });
        this.listView.setOnScrollListener(this.onScrollListener);
        this.listView.setAdapter((ListAdapter)this.adapter);
        this.activityCircle = (ProgressBar)viewGroup2.findViewById(R.id.com_facebook_picker_activity_circle);
        return (View)viewGroup2;
    }
    
    @Override
    public void onDetach() {
        super.onDetach();
        this.listView.setOnScrollListener((AbsListView$OnScrollListener)null);
        this.listView.setAdapter((ListAdapter)null);
        this.loadingStrategy.detach();
        this.sessionTracker.stopTracking();
    }
    
    @Override
    public void onInflate(final Activity activity, final AttributeSet set, final Bundle bundle) {
        super.onInflate(activity, set, bundle);
        final TypedArray obtainStyledAttributes = activity.obtainStyledAttributes(set, R.styleable.com_facebook_picker_fragment);
        this.setShowPictures(obtainStyledAttributes.getBoolean(0, this.showPictures));
        final String string = obtainStyledAttributes.getString(1);
        if (string != null) {
            this.setExtraFields(Arrays.asList(string.split(",")));
        }
        this.showTitleBar = obtainStyledAttributes.getBoolean(2, this.showTitleBar);
        this.titleText = obtainStyledAttributes.getString(3);
        this.doneButtonText = obtainStyledAttributes.getString(4);
        this.titleBarBackground = obtainStyledAttributes.getDrawable(5);
        this.doneButtonBackground = obtainStyledAttributes.getDrawable(6);
        obtainStyledAttributes.recycle();
    }
    
    void onLoadingData() {
    }
    
    @Override
    public void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.saveSettingsToBundle(bundle);
        this.selectionStrategy.saveSelectionToBundle(bundle, "com.facebook.android.PickerFragment.Selection");
        if (this.activityCircle != null) {
            bundle.putBoolean("com.facebook.android.PickerFragment.ActivityCircleShown", this.activityCircle.getVisibility() == 0);
        }
    }
    
    void saveSettingsToBundle(final Bundle bundle) {
        bundle.putBoolean("com.facebook.widget.PickerFragment.ShowPictures", this.showPictures);
        if (!this.extraFields.isEmpty()) {
            bundle.putString("com.facebook.widget.PickerFragment.ExtraFields", TextUtils.join((CharSequence)",", (Iterable)this.extraFields));
        }
        bundle.putBoolean("com.facebook.widget.PickerFragment.ShowTitleBar", this.showTitleBar);
        bundle.putString("com.facebook.widget.PickerFragment.TitleText", this.titleText);
        bundle.putString("com.facebook.widget.PickerFragment.DoneButtonText", this.doneButtonText);
    }
    
    @Override
    public void setArguments(final Bundle bundle) {
        super.setArguments(bundle);
        this.setSettingsFromBundle(bundle);
    }
    
    public void setDoneButtonText(final String doneButtonText) {
        this.doneButtonText = doneButtonText;
    }
    
    public void setExtraFields(final Collection<String> collection) {
        this.extraFields = new HashSet<String>();
        if (collection != null) {
            this.extraFields.addAll((Collection<?>)collection);
        }
    }
    
    public void setFilter(final GraphObjectFilter<T> filter) {
        this.filter = filter;
    }
    
    public void setOnDataChangedListener(final OnDataChangedListener onDataChangedListener) {
        this.onDataChangedListener = onDataChangedListener;
    }
    
    public void setOnDoneButtonClickedListener(final OnDoneButtonClickedListener onDoneButtonClickedListener) {
        this.onDoneButtonClickedListener = onDoneButtonClickedListener;
    }
    
    public void setOnErrorListener(final OnErrorListener onErrorListener) {
        this.onErrorListener = onErrorListener;
    }
    
    public void setOnSelectionChangedListener(final OnSelectionChangedListener onSelectionChangedListener) {
        this.onSelectionChangedListener = onSelectionChangedListener;
    }
    
    void setSelectionStrategy(final SelectionStrategy selectionStrategy) {
        if (selectionStrategy != this.selectionStrategy) {
            this.selectionStrategy = selectionStrategy;
            if (this.adapter != null) {
                this.adapter.notifyDataSetChanged();
            }
        }
    }
    
    public void setSession(final Session session) {
        this.sessionTracker.setSession(session);
    }
    
    public void setSettingsFromBundle(final Bundle pickerFragmentSettingsFromBundle) {
        this.setPickerFragmentSettingsFromBundle(pickerFragmentSettingsFromBundle);
    }
    
    public void setShowPictures(final boolean showPictures) {
        this.showPictures = showPictures;
    }
    
    public void setShowTitleBar(final boolean showTitleBar) {
        this.showTitleBar = showTitleBar;
    }
    
    public void setTitleText(final String titleText) {
        this.titleText = titleText;
    }
    
    void updateAdapter(final SimpleGraphObjectCursor<T> simpleGraphObjectCursor) {
        if (this.adapter != null) {
            final View child = this.listView.getChildAt(1);
            final int firstVisiblePosition = this.listView.getFirstVisiblePosition();
            int n;
            if ((n = firstVisiblePosition) > 0) {
                n = firstVisiblePosition + 1;
            }
            final GraphObjectAdapter.SectionAndItem<T> sectionAndItem = this.adapter.getSectionAndItem(n);
            int top;
            if (child != null && sectionAndItem.getType() != GraphObjectAdapter.SectionAndItem.Type.ACTIVITY_CIRCLE) {
                top = child.getTop();
            }
            else {
                top = 0;
            }
            final boolean changeCursor = this.adapter.changeCursor(simpleGraphObjectCursor);
            if (child != null && sectionAndItem != null) {
                final int position = this.adapter.getPosition(sectionAndItem.sectionKey, sectionAndItem.graphObject);
                if (position != -1) {
                    this.listView.setSelectionFromTop(position, top);
                }
            }
            if (changeCursor && this.onDataChangedListener != null) {
                this.onDataChangedListener.onDataChanged(this);
            }
        }
    }
    
    public interface GraphObjectFilter<T>
    {
        boolean includeItem(final T p0);
    }
    
    abstract class LoadingStrategy
    {
        protected static final int CACHED_RESULT_REFRESH_DELAY = 2000;
        protected GraphObjectAdapter<T> adapter;
        protected GraphObjectPagingLoader<T> loader;
        
        public void attach(final GraphObjectAdapter<T> adapter) {
            (this.loader = (GraphObjectPagingLoader<T>)(GraphObjectPagingLoader)PickerFragment.this.getLoaderManager().initLoader(0, null, (LoaderManager.LoaderCallbacks<?>)new LoaderManager.LoaderCallbacks<SimpleGraphObjectCursor<T>>() {
                @Override
                public Loader<SimpleGraphObjectCursor<T>> onCreateLoader(final int n, final Bundle bundle) {
                    return (Loader<SimpleGraphObjectCursor<T>>)LoadingStrategy.this.onCreateLoader();
                }
                
                public void onLoadFinished(final Loader<SimpleGraphObjectCursor<T>> loader, final SimpleGraphObjectCursor<T> simpleGraphObjectCursor) {
                    if (loader != LoadingStrategy.this.loader) {
                        throw new FacebookException("Received callback for unknown loader.");
                    }
                    LoadingStrategy.this.onLoadFinished((GraphObjectPagingLoader<T>)loader, simpleGraphObjectCursor);
                }
                
                @Override
                public void onLoaderReset(final Loader<SimpleGraphObjectCursor<T>> loader) {
                    if (loader != LoadingStrategy.this.loader) {
                        throw new FacebookException("Received callback for unknown loader.");
                    }
                    LoadingStrategy.this.onLoadReset((GraphObjectPagingLoader<T>)loader);
                }
            })).setOnErrorListener((GraphObjectPagingLoader.OnErrorListener)new GraphObjectPagingLoader.OnErrorListener() {
                @Override
                public void onError(final FacebookException ex, final GraphObjectPagingLoader<?> graphObjectPagingLoader) {
                    PickerFragment.this.hideActivityCircle();
                    if (PickerFragment.this.onErrorListener != null) {
                        PickerFragment.this.onErrorListener.onError(PickerFragment.this, ex);
                    }
                }
            });
            (this.adapter = adapter).changeCursor(this.loader.getCursor());
            this.adapter.setOnErrorListener((GraphObjectAdapter.OnErrorListener)new GraphObjectAdapter.OnErrorListener() {
                @Override
                public void onError(final GraphObjectAdapter<?> graphObjectAdapter, final FacebookException ex) {
                    if (PickerFragment.this.onErrorListener != null) {
                        PickerFragment.this.onErrorListener.onError(PickerFragment.this, ex);
                    }
                }
            });
        }
        
        public void clearResults() {
            if (this.loader != null) {
                this.loader.clearResults();
            }
        }
        
        public void detach() {
            this.adapter.setDataNeededListener(null);
            this.adapter.setOnErrorListener(null);
            this.loader.setOnErrorListener(null);
            this.loader = null;
            this.adapter = null;
        }
        
        public boolean isDataPresentOrLoading() {
            return !this.adapter.isEmpty() || this.loader.isLoading();
        }
        
        protected GraphObjectPagingLoader<T> onCreateLoader() {
            return new GraphObjectPagingLoader<T>((Context)PickerFragment.this.getActivity(), PickerFragment.this.graphObjectClass);
        }
        
        protected void onLoadFinished(final GraphObjectPagingLoader<T> graphObjectPagingLoader, final SimpleGraphObjectCursor<T> simpleGraphObjectCursor) {
            PickerFragment.this.updateAdapter(simpleGraphObjectCursor);
        }
        
        protected void onLoadReset(final GraphObjectPagingLoader<T> graphObjectPagingLoader) {
            this.adapter.changeCursor(null);
        }
        
        protected void onStartLoading(final GraphObjectPagingLoader<T> graphObjectPagingLoader, final Request request) {
            PickerFragment.this.displayActivityCircle();
        }
        
        public void startLoading(final Request request) {
            if (this.loader != null) {
                this.loader.startLoading(request, true);
                this.onStartLoading(this.loader, request);
            }
        }
    }
    
    class MultiSelectionStrategy extends SelectionStrategy
    {
        private final Set<String> selectedIds;
        
        MultiSelectionStrategy() {
            this.selectedIds = new HashSet<String>();
        }
        
        public void clear() {
            this.selectedIds.clear();
        }
        
        public Collection<String> getSelectedIds() {
            return this.selectedIds;
        }
        
        @Override
        boolean isEmpty() {
            return this.selectedIds.isEmpty();
        }
        
        @Override
        boolean isSelected(final String s) {
            return s != null && this.selectedIds.contains(s);
        }
        
        @Override
        void readSelectionFromBundle(final Bundle bundle, final String s) {
            if (bundle != null) {
                final String string = bundle.getString(s);
                if (string != null) {
                    final String[] split = TextUtils.split(string, ",");
                    this.selectedIds.clear();
                    Collections.addAll(this.selectedIds, split);
                }
            }
        }
        
        @Override
        void saveSelectionToBundle(final Bundle bundle, final String s) {
            if (!this.selectedIds.isEmpty()) {
                bundle.putString(s, TextUtils.join((CharSequence)",", (Iterable)this.selectedIds));
            }
        }
        
        @Override
        boolean shouldShowCheckBoxIfUnselected() {
            return true;
        }
        
        @Override
        void toggleSelection(final String s) {
            if (s != null) {
                if (!this.selectedIds.contains(s)) {
                    this.selectedIds.add(s);
                    return;
                }
                this.selectedIds.remove(s);
            }
        }
    }
    
    public interface OnDataChangedListener
    {
        void onDataChanged(final PickerFragment<?> p0);
    }
    
    public interface OnDoneButtonClickedListener
    {
        void onDoneButtonClicked(final PickerFragment<?> p0);
    }
    
    public interface OnErrorListener
    {
        void onError(final PickerFragment<?> p0, final FacebookException p1);
    }
    
    public interface OnSelectionChangedListener
    {
        void onSelectionChanged(final PickerFragment<?> p0);
    }
    
    abstract class PickerFragmentAdapter<U extends GraphObject> extends GraphObjectAdapter<T>
    {
        public PickerFragmentAdapter(final Context context) {
            super(context);
        }
        
        @Override
        boolean isGraphObjectSelected(final String s) {
            return PickerFragment.this.selectionStrategy.isSelected(s);
        }
        
        @Override
        void updateCheckboxState(final CheckBox checkBox, final boolean checked) {
            checkBox.setChecked(checked);
            int visibility;
            if (checked || PickerFragment.this.selectionStrategy.shouldShowCheckBoxIfUnselected()) {
                visibility = 0;
            }
            else {
                visibility = 8;
            }
            checkBox.setVisibility(visibility);
        }
    }
    
    abstract class SelectionStrategy
    {
        abstract void clear();
        
        abstract Collection<String> getSelectedIds();
        
        abstract boolean isEmpty();
        
        abstract boolean isSelected(final String p0);
        
        abstract void readSelectionFromBundle(final Bundle p0, final String p1);
        
        abstract void saveSelectionToBundle(final Bundle p0, final String p1);
        
        abstract boolean shouldShowCheckBoxIfUnselected();
        
        abstract void toggleSelection(final String p0);
    }
    
    class SingleSelectionStrategy extends SelectionStrategy
    {
        private String selectedId;
        
        public void clear() {
            this.selectedId = null;
        }
        
        public Collection<String> getSelectedIds() {
            return Arrays.asList(this.selectedId);
        }
        
        @Override
        boolean isEmpty() {
            return this.selectedId == null;
        }
        
        @Override
        boolean isSelected(final String s) {
            return this.selectedId != null && s != null && this.selectedId.equals(s);
        }
        
        @Override
        void readSelectionFromBundle(final Bundle bundle, final String s) {
            if (bundle != null) {
                this.selectedId = bundle.getString(s);
            }
        }
        
        @Override
        void saveSelectionToBundle(final Bundle bundle, final String s) {
            if (!TextUtils.isEmpty((CharSequence)this.selectedId)) {
                bundle.putString(s, this.selectedId);
            }
        }
        
        @Override
        boolean shouldShowCheckBoxIfUnselected() {
            return false;
        }
        
        @Override
        void toggleSelection(final String selectedId) {
            if (this.selectedId != null && this.selectedId.equals(selectedId)) {
                this.selectedId = null;
                return;
            }
            this.selectedId = selectedId;
        }
    }
}
