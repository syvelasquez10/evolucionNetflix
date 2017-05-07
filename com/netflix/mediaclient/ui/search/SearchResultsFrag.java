// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.search;

import android.view.MotionEvent;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.ui.kids.search.KidsSearchResultView;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import android.widget.BaseAdapter;
import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;
import java.util.List;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.SearchVideoList;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import com.netflix.mediaclient.servicemgr.model.search.SearchSuggestion;
import com.netflix.mediaclient.servicemgr.model.search.SearchPerson;
import com.netflix.mediaclient.servicemgr.model.search.SearchVideo;
import android.app.Activity;
import android.util.Pair;
import com.netflix.mediaclient.service.logging.search.utils.SearchLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import android.widget.ScrollView;
import android.view.View$OnTouchListener;
import com.netflix.mediaclient.util.StringUtils;
import android.widget.AdapterView;
import android.widget.AdapterView$OnItemClickListener;
import java.util.Locale;
import com.netflix.mediaclient.util.ViewUtils;
import android.content.Context;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import com.netflix.mediaclient.util.DeviceUtils;
import android.widget.EditText;
import java.util.ArrayList;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.widget.ListAdapter;
import android.widget.GridView;
import com.netflix.mediaclient.Log;
import android.view.View;
import com.netflix.mediaclient.servicemgr.model.trackable.SearchTrackable;
import com.netflix.mediaclient.ui.common.SearchSimilarItemsGridViewAdapter;
import com.netflix.mediaclient.android.widget.LoggingScrollView;
import com.netflix.mediaclient.servicemgr.model.search.ISearchResults;
import android.widget.ProgressBar;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.widget.TextView;
import com.netflix.mediaclient.android.widget.StaticGridView;
import java.util.Stack;
import com.netflix.mediaclient.android.fragment.NetflixFrag;

public class SearchResultsFrag extends NetflixFrag
{
    private static final String TAG = "SearchResultsFrag";
    private SearchResultsAdapter adapterPeople;
    private SearchResultsAdapter adapterSuggestions;
    private SearchResultsAdapter adapterVideos;
    private Stack<ItemClick> clickPresseHistory;
    private String currentlyDisplaying;
    private StaticGridView gridViewPeople;
    private StaticGridView gridViewSuggestions;
    private StaticGridView gridViewVideos;
    private int imgHeightPeople;
    private int imgHeightVideo;
    private TextView labelPeople;
    private TextView labelSuggestions;
    private TextView labelVideos;
    private ServiceManager manager;
    private int maxResultsPeople;
    private int maxResultsSuggestions;
    private int maxResultsVideos;
    private ProgressBar progressBar;
    private String query;
    private TextView relatedlabel;
    private ISearchResults results;
    private LoggingScrollView scrollView;
    private LoggingScrollView scrollView2;
    private SearchCategory secondarySearch;
    private SearchSimilarItemsGridViewAdapter simAdapter;
    private SearchTrackable trackableTitles;
    
    public SearchResultsFrag() {
        this.clickPresseHistory = new Stack<ItemClick>();
        this.secondarySearch = SearchCategory.VIDEOS;
    }
    
    static /* synthetic */ int access$1116(final SearchResultsFrag searchResultsFrag, final float n) {
        return searchResultsFrag.imgHeightPeople += (int)n;
    }
    
    private void addToClickHistory(final StaticGridView staticGridView, final View view, final int n, final long n2) {
        final Object tag = view.getTag(2131165244);
        if ((tag == null || tag == Boolean.FALSE) && (this.clickPresseHistory.size() == 0 || (this.clickPresseHistory.size() > 0 && this.clickPresseHistory.peek().position != n))) {
            this.clickPresseHistory.push(new ItemClick(staticGridView, view, n, n2, ((SearchResultView)view).getDisplayName()));
        }
        view.setTag(2131165244, (Object)Boolean.TRUE);
    }
    
    private void clearGridSelections() {
        for (int i = 0; i < this.gridViewPeople.getCount(); ++i) {
            ((SearchResultView)this.gridViewPeople.getChildAt(i)).clearTitleTextHighlighting();
        }
        for (int j = 0; j < this.gridViewSuggestions.getCount(); ++j) {
            ((SearchResultView)this.gridViewSuggestions.getChildAt(j)).clearTitleTextHighlighting();
        }
    }
    
    public static SearchResultsFrag create() {
        return new SearchResultsFrag();
    }
    
    private void fetchPeopleVideoData(final String s) {
        if (this.manager == null || !this.manager.isReady()) {
            Log.w("SearchResultsFrag", "Manager is null/notReady - can't load data");
            return;
        }
        this.simAdapter = new SearchSimilarItemsGridViewAdapter(this.getActivity(), this.gridViewVideos, false);
        if (this.gridViewVideos != null) {
            this.gridViewVideos.setAdapter((ListAdapter)this.simAdapter);
            this.setupGridViewObserverForTitles(this.gridViewVideos);
        }
        this.manager.getBrowse().fetchSimilarVideosForPerson(s, 40, new OnSimsFetchedCallback(System.nanoTime(), s, SearchCategory.PEOPLE), this.query);
    }
    
    private void fetchSuggestedVideoData(final String s) {
        if (this.manager == null || !this.manager.isReady()) {
            Log.w("SearchResultsFrag", "Manager is null/notReady - can't load data");
            return;
        }
        this.simAdapter = new SearchSimilarItemsGridViewAdapter(this.getActivity(), this.gridViewVideos, false);
        if (this.gridViewVideos != null) {
            this.gridViewVideos.setAdapter((ListAdapter)this.simAdapter);
            this.setupGridViewObserverForTitles(this.gridViewVideos);
        }
        this.manager.getBrowse().fetchSimilarVideosForQuerySuggestion(s, 40, new OnSimsFetchedCallback(System.nanoTime(), s, SearchCategory.SUGGESTIONS), this.query);
    }
    
    private void findViews(final View view) {
        this.gridViewSuggestions = (StaticGridView)view.findViewById(2131165625);
        this.gridViewVideos = (StaticGridView)view.findViewById(2131165621);
        this.gridViewPeople = (StaticGridView)view.findViewById(2131165623);
        this.labelSuggestions = (TextView)view.findViewById(2131165624);
        this.relatedlabel = (TextView)view.findViewById(2131165626);
        this.scrollView2 = (LoggingScrollView)view.findViewById(2131165627);
        this.labelVideos = (TextView)view.findViewById(2131165620);
        this.labelPeople = (TextView)view.findViewById(2131165622);
        this.scrollView = (LoggingScrollView)view.findViewById(2131165619);
        this.progressBar = (ProgressBar)view.findViewById(2131165443);
    }
    
    public static Object getItem(final ISearchResults searchResults, final SearchCategory searchCategory, final int n) {
        if (searchResults == null) {
            return null;
        }
        switch (searchCategory) {
            default: {
                return null;
            }
            case SUGGESTIONS: {
                return searchResults.getResultsSuggestions(n);
            }
            case VIDEOS: {
                return searchResults.getResultsVideos(n);
            }
            case PEOPLE: {
                return searchResults.getResultsPeople(n);
            }
        }
    }
    
    private String[] getItemIdsForRange(final SearchCategory searchCategory, int i, final int n) {
        if (n < i) {
            return null;
        }
        final ArrayList<String> list = new ArrayList<String>();
        while (i <= n) {
            final Object item = getItem(this.results, searchCategory, i);
            if (item != null) {
                final String searchObjectId = this.getSearchObjectId(item);
                if (searchObjectId != null) {
                    list.add(searchObjectId);
                }
            }
            ++i;
        }
        return list.toArray(new String[list.size()]);
    }
    
    private String getReferenceId(final SearchTrackable searchTrackable) {
        if (searchTrackable != null) {
            return searchTrackable.getReferenceId();
        }
        return null;
    }
    
    private void hideKeyboard() {
        final View currentFocus = this.getActivity().getCurrentFocus();
        if (currentFocus instanceof EditText) {
            DeviceUtils.forceHideKeyboard(this.getActivity(), (EditText)currentFocus);
        }
    }
    
    private String highlightTitle(final View view) {
        final SearchResultView searchResultView = (SearchResultView)view;
        final String playablId = searchResultView.getPlayablId();
        searchResultView.setTitleTextWithSelectdHighlighting();
        return playablId;
    }
    
    private void notifyAdapters() {
        if (this.adapterVideos != null) {
            this.adapterVideos.setMaxCount(this.maxResultsVideos);
            this.adapterVideos.notifyDataSetChanged();
        }
        if (this.adapterPeople != null) {
            this.adapterPeople.setMaxCount(this.maxResultsPeople);
            this.adapterPeople.notifyDataSetChanged();
        }
        if (this.adapterSuggestions != null) {
            this.adapterSuggestions.setMaxCount(this.maxResultsSuggestions);
            this.adapterSuggestions.notifyDataSetChanged();
        }
    }
    
    private void resetGridViews() {
        this.setupVideosGridView();
        this.setupPeopleGridView();
        this.setupSuggestionsGridView();
    }
    
    private void resetScrollPosition() {
        if (this.scrollView != null) {
            this.scrollView.scrollTo(0, 0);
        }
        if (this.scrollView2 != null) {
            this.scrollView2.scrollTo(0, 0);
        }
    }
    
    private void setupGridViewObserver(final StaticGridView staticGridView) {
        staticGridView.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)new ViewTreeObserver$OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                final int n = staticGridView.getWidth() - staticGridView.getPaddingLeft() - staticGridView.getPaddingRight();
                Log.v("SearchResultsFrag", "View dimens: " + n + ", " + staticGridView.getHeight());
                final int numVideoGridCols = SearchUtils.getNumVideoGridCols((Context)SearchResultsFrag.this.getActivity());
                if (numVideoGridCols > 0) {
                    SearchResultsFrag.this.imgHeightVideo = (int)(n / numVideoGridCols * SearchUtils.getVideoImageAspectRatio() + 0.5);
                    Log.v("SearchResultsFrag", "imgHeight: " + SearchResultsFrag.this.imgHeightVideo);
                }
                final int numPeopleGridCols = SearchUtils.getNumPeopleGridCols((Context)SearchResultsFrag.this.getActivity());
                if (numPeopleGridCols > 0) {
                    SearchResultsFrag.this.imgHeightPeople = (int)(n / numPeopleGridCols * SearchUtils.getPeopleImageAspectRatio() + 0.5);
                    SearchResultsFrag.access$1116(SearchResultsFrag.this, SearchResultsFrag.this.getActivity().getResources().getDimension(2131361982));
                    Log.v("SearchResultsFrag", "imgHeightPeople: " + SearchResultsFrag.this.imgHeightPeople);
                }
                SearchResultsFrag.this.fireImpressionEvents();
                ViewUtils.removeGlobalLayoutListener((View)staticGridView, (ViewTreeObserver$OnGlobalLayoutListener)this);
            }
        });
    }
    
    private void setupGridViewObserverForTitles(final StaticGridView staticGridView) {
        staticGridView.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)new ViewTreeObserver$OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                SearchResultsFrag.this.fireGridViewVideosImpressionEvents();
                if (staticGridView.getCount() > 0) {
                    ViewUtils.removeGlobalLayoutListener((View)staticGridView, (ViewTreeObserver$OnGlobalLayoutListener)this);
                }
            }
        });
    }
    
    private void setupGridViews() {
        this.setupVideosGridView();
        this.setupPeopleGridView();
        this.setupSuggestionsGridView();
    }
    
    private void setupLabels() {
        if (this.labelSuggestions != null) {
            final TextView labelSuggestions = this.labelSuggestions;
            String text;
            if (SearchUtils.shouldUpperCaseTitleLabels()) {
                text = this.getString(2131493285).toUpperCase(Locale.US);
            }
            else {
                text = this.getString(2131493285);
            }
            labelSuggestions.setText((CharSequence)text);
        }
        if (this.labelPeople != null) {
            final TextView labelPeople = this.labelPeople;
            String text2;
            if (SearchUtils.shouldUpperCaseTitleLabels()) {
                text2 = this.getString(2131493284).toUpperCase(Locale.US);
            }
            else {
                text2 = this.getString(2131493284);
            }
            labelPeople.setText((CharSequence)text2);
        }
        if (this.labelVideos != null) {
            this.labelVideos.setText((CharSequence)this.getString(2131493283).toUpperCase(Locale.US));
        }
    }
    
    private void setupPeopleGridView() {
        if (this.gridViewPeople == null) {
            return;
        }
        this.gridViewPeople.setAdapter((ListAdapter)null);
        this.adapterPeople = new SearchResultsAdapter(SearchCategory.PEOPLE, !SearchUtils.shouldOpenNewActivityForRelated());
        this.gridViewPeople.setAdapter((ListAdapter)this.adapterPeople);
        this.gridViewPeople.setOnItemClickListener((AdapterView$OnItemClickListener)this.adapterPeople);
        if (!SearchUtils.shouldOpenNewActivityForRelated()) {
            this.setupPeopleOnItemClick();
        }
        this.gridViewPeople.setNumColumns(SearchUtils.getNumPeopleGridCols((Context)this.getActivity()));
    }
    
    private void setupPeopleOnItemClick() {
        this.gridViewPeople.setOnItemClickListener((AdapterView$OnItemClickListener)new AdapterView$OnItemClickListener() {
            public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                if (view != null && view instanceof SearchResultView) {
                    SearchResultsFrag.this.hideKeyboard();
                    SearchResultsFrag.this.clearGridSelections();
                    final String access$200 = SearchResultsFrag.this.highlightTitle(view);
                    if (StringUtils.isNotEmpty(access$200)) {
                        SearchResultsFrag.this.updateSimilarLabel(view);
                        SearchResultsFrag.this.fetchPeopleVideoData(access$200);
                        SearchResultsFrag.this.addToClickHistory(SearchResultsFrag.this.gridViewPeople, view, n, n2);
                        if (SearchResultsFrag.this.progressBar != null) {
                            SearchResultsFrag.this.progressBar.setVisibility(0);
                        }
                    }
                }
            }
        });
    }
    
    private void setupScrollViewKeyboardHiding() {
        if (this.scrollView != null) {
            this.scrollView.setOnTouchListener((View$OnTouchListener)new keyboardHider());
        }
        if (this.scrollView2 != null) {
            this.scrollView2.setOnTouchListener((View$OnTouchListener)new keyboardHider());
        }
    }
    
    private void setupScrollViewLogging() {
        final ScrollLoggingListener scrollLoggingListener = new ScrollLoggingListener();
        if (this.scrollView != null) {
            this.scrollView.setOnScrollStopListener((LoggingScrollView.OnScrollStopListener)scrollLoggingListener);
        }
        if (this.scrollView2 != null) {
            this.scrollView2.setOnScrollStopListener((LoggingScrollView.OnScrollStopListener)scrollLoggingListener);
        }
    }
    
    private void setupScrollViews(final View view) {
        this.setupScrollViewKeyboardHiding();
        this.setupScrollViewLogging();
    }
    
    private void setupSuggestionsGridView() {
        if (this.gridViewSuggestions == null) {
            return;
        }
        this.gridViewSuggestions.setAdapter((ListAdapter)null);
        this.adapterSuggestions = new SearchResultsAdapter(SearchCategory.SUGGESTIONS, !SearchUtils.shouldOpenNewActivityForRelated());
        this.gridViewSuggestions.setAdapter((ListAdapter)this.adapterSuggestions);
        if (!SearchUtils.shouldOpenNewActivityForRelated()) {
            this.setupSuggestionsOnItemClick();
        }
        this.gridViewSuggestions.setNumColumns(SearchUtils.getNumRelatedGridCols((Context)this.getActivity()));
    }
    
    private void setupSuggestionsOnItemClick() {
        this.gridViewSuggestions.setOnItemClickListener((AdapterView$OnItemClickListener)new AdapterView$OnItemClickListener() {
            public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                if (view != null && view instanceof SearchResultView) {
                    SearchResultsFrag.this.hideKeyboard();
                    SearchResultsFrag.this.clearGridSelections();
                    final String access$200 = SearchResultsFrag.this.highlightTitle(view);
                    if (StringUtils.isNotEmpty(access$200)) {
                        SearchResultsFrag.this.updateSimilarLabel(view);
                        SearchResultsFrag.this.fetchSuggestedVideoData(access$200);
                        SearchResultsFrag.this.addToClickHistory(SearchResultsFrag.this.gridViewSuggestions, view, n, n2);
                        if (SearchResultsFrag.this.progressBar != null) {
                            SearchResultsFrag.this.progressBar.setVisibility(0);
                        }
                    }
                }
            }
        });
    }
    
    private void setupVideosGridView() {
        if (this.gridViewVideos == null) {
            return;
        }
        this.gridViewVideos.setAdapter((ListAdapter)null);
        this.adapterVideos = new SearchResultsAdapter(SearchCategory.VIDEOS, false);
        this.gridViewVideos.setAdapter((ListAdapter)this.adapterVideos);
        this.gridViewVideos.setOnItemClickListener((AdapterView$OnItemClickListener)this.adapterVideos);
        if (!SearchUtils.shouldOpenNewActivityForRelated()) {
            this.setupSuggestionsOnItemClick();
        }
        this.setupGridViewObserver(this.gridViewVideos);
        this.gridViewVideos.setNumColumns(SearchUtils.getNumVideoGridCols((Context)this.getActivity()));
    }
    
    private void updateLabelVisibilty() {
        this.updateVideosLabel();
        this.updatePeopleLabel();
        this.updateSuggestionsLabel();
    }
    
    private void updateMaxResults() {
        this.maxResultsPeople = SearchUtils.getMaxResultsPeople((Context)this.getActivity());
        this.maxResultsSuggestions = SearchUtils.getMaxResultsRelated((Context)this.getActivity());
        this.maxResultsVideos = SearchUtils.getMaxResultsVideos((Context)this.getActivity());
    }
    
    private void updatePeopleLabel() {
        if (this.results.getNumResultsPeople() > 0) {
            if (this.labelPeople != null) {
                this.labelPeople.setVisibility(0);
            }
        }
        else if (this.labelPeople != null) {
            this.labelPeople.setVisibility(8);
        }
    }
    
    private void updateSimilarLabel(final View view) {
        this.currentlyDisplaying = ((SearchResultView)view).getDisplayName();
        if (this.relatedlabel != null && StringUtils.isNotEmpty(this.currentlyDisplaying)) {
            this.relatedlabel.setVisibility(0);
            this.relatedlabel.setText((CharSequence)this.currentlyDisplaying);
        }
    }
    
    private void updateSuggestionsLabel() {
        if (this.results.getNumResultsSuggestions() > 0) {
            if (this.labelSuggestions != null) {
                this.labelSuggestions.setVisibility(0);
            }
        }
        else if (this.labelSuggestions != null) {
            this.labelSuggestions.setVisibility(8);
        }
    }
    
    private void updateVideosLabel() {
        if (this.results.getNumResultsVideos() > 0) {
            if (this.labelVideos != null) {
                this.labelVideos.setVisibility(0);
            }
        }
        else if (this.labelVideos != null) {
            this.labelVideos.setVisibility(8);
        }
    }
    
    public void clearSelectedStack() {
        this.clickPresseHistory.clear();
    }
    
    public void fireGridViewVideosImpressionEvents() {
        boolean b = true;
        boolean b2;
        if (this.gridViewVideos != null) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        if (this.gridViewVideos.getCount() <= 0) {
            b = false;
        }
        if (b2 & b) {
            final Pair<Integer, Integer> visiblePositions = ViewUtils.getVisiblePositions(this.gridViewVideos, this.scrollView);
            if (visiblePositions != null) {
                final int intValue = (int)visiblePositions.first;
                final int intValue2 = (int)visiblePositions.second;
                IClientLogging.ModalView modalView = null;
                switch (this.secondarySearch) {
                    default: {
                        modalView = IClientLogging.ModalView.titleResults;
                        break;
                    }
                    case SUGGESTIONS: {
                        modalView = IClientLogging.ModalView.suggestionTitleResults;
                        break;
                    }
                    case PEOPLE: {
                        modalView = IClientLogging.ModalView.peopleTitleResults;
                        break;
                    }
                }
                final Activity activity = this.getActivity();
                final IClientLogging.ModalView searchResults = IClientLogging.ModalView.searchResults;
                SearchTrackable searchTrackable;
                if (this.secondarySearch == SearchCategory.VIDEOS) {
                    searchTrackable = this.results.getVideosListTrackable();
                }
                else {
                    searchTrackable = this.trackableTitles;
                }
                SearchLogUtils.reportSearchImpression(1L, (Context)activity, searchResults, this.getReferenceId(searchTrackable), null, intValue, intValue2, modalView);
            }
        }
    }
    
    public void fireImpressionEvents() {
        final boolean b = true;
        this.fireGridViewVideosImpressionEvents();
        boolean b2;
        if (this.gridViewPeople != null) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        boolean b3;
        if (this.gridViewPeople.getCount() > 0) {
            b3 = true;
        }
        else {
            b3 = false;
        }
        if (b2 & b3) {
            final Pair<Integer, Integer> visiblePositions = ViewUtils.getVisiblePositions(this.gridViewPeople, this.scrollView);
            if (visiblePositions != null) {
                SearchLogUtils.reportSearchImpression(1L, (Context)this.getActivity(), IClientLogging.ModalView.searchResults, this.getReferenceId(this.results.getPeopleListTrackable()), null, (int)visiblePositions.first, (int)visiblePositions.second, IClientLogging.ModalView.peopleResults);
            }
        }
        boolean b4;
        if (this.gridViewSuggestions != null) {
            b4 = true;
        }
        else {
            b4 = false;
        }
        if (b4 & (this.gridViewSuggestions.getCount() > 0 && b)) {
            final Pair<Integer, Integer> visiblePositions2 = ViewUtils.getVisiblePositions(this.gridViewSuggestions, this.scrollView);
            if (visiblePositions2 != null) {
                SearchLogUtils.reportSearchImpression(1L, (Context)this.getActivity(), IClientLogging.ModalView.searchResults, this.getReferenceId(this.results.getSuggestionsListTrackable()), null, (int)visiblePositions2.first, (int)visiblePositions2.second, IClientLogging.ModalView.suggestionResults);
            }
        }
    }
    
    public String getSearchObjectId(final Object o) {
        if (o instanceof SearchVideo) {
            return ((SearchVideo)o).getId();
        }
        if (o instanceof SearchPerson) {
            return ((SearchPerson)o).getId();
        }
        if (o instanceof SearchSuggestion) {
            return ((SearchSuggestion)o).getTitle();
        }
        throw new IllegalStateException("Unknown search result type");
    }
    
    public boolean isLoadingData() {
        return false;
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        final View inflate = layoutInflater.inflate(SearchUtils.getSearchFragLayout(), (ViewGroup)null);
        this.findViews(inflate);
        this.setupLabels();
        this.setupGridViews();
        this.setupScrollViews(inflate);
        return inflate;
    }
    
    public void refresh() {
        this.notifyAdapters();
    }
    
    public void setServiceManager(final ServiceManager manager) {
        this.manager = manager;
    }
    
    public boolean showLastSelectedItem() {
        while (!this.clickPresseHistory.isEmpty()) {
            final ItemClick itemClick = this.clickPresseHistory.pop();
            if (itemClick.displayName != null && this.currentlyDisplaying != null && itemClick.displayName.compareToIgnoreCase(this.currentlyDisplaying) != 0) {
                itemClick.view.setTag(2131165244, (Object)Boolean.TRUE);
                itemClick.gridView.performItemClick(itemClick.view, itemClick.position, itemClick.id);
                return true;
            }
        }
        return false;
    }
    
    public void update(final ISearchResults results, final String query) {
        Log.v("SearchResultsFrag", "Updating...");
        this.results = results;
        this.secondarySearch = SearchCategory.VIDEOS;
        this.clearSelectedStack();
        if (this.progressBar != null) {
            this.progressBar.setVisibility(8);
        }
        this.query = query;
        if (this.relatedlabel != null) {
            this.relatedlabel.setVisibility(8);
        }
        this.updateMaxResults();
        this.updateLabelVisibilty();
        this.resetGridViews();
        this.notifyAdapters();
        this.resetScrollPosition();
        if (this.progressBar != null) {
            this.progressBar.setVisibility(8);
        }
    }
    
    class ItemClick
    {
        public String displayName;
        public StaticGridView gridView;
        public long id;
        public int position;
        public View view;
        
        ItemClick(final StaticGridView gridView, final View view, final int position, final long id, final String displayName) {
            this.displayName = displayName;
            this.gridView = gridView;
            this.position = position;
            this.view = view;
            this.id = id;
        }
    }
    
    private class OnSimsFetchedCallback extends LoggingManagerCallback
    {
        SearchCategory searchCategory;
        
        public OnSimsFetchedCallback(final long n, final String s, final SearchCategory searchCategory) {
            super("SearchResultsFrag");
            this.searchCategory = searchCategory;
        }
        
        @Override
        public void onSimilarVideosFetched(final SearchVideoList list, final Status status) {
            super.onSimilarVideosFetched(list, status);
            if (status.isError()) {
                Log.w("SearchResultsFrag", "Invalid status code");
                ((SearchActivity)SearchResultsFrag.this.getActivity()).showError();
                return;
            }
            if (list == null || list.size() < 1) {
                Log.v("SearchResultsFrag", "No details in response");
                ((SearchActivity)SearchResultsFrag.this.getActivity()).showError();
                return;
            }
            if (SearchResultsFrag.this.simAdapter != null) {
                SearchResultsFrag.this.secondarySearch = this.searchCategory;
                SearchResultsFrag.this.trackableTitles = list.getVideosListTrackable();
                SearchResultsFrag.this.simAdapter.setData(list, SearchResultsFrag.this.trackableTitles);
            }
            if (SearchResultsFrag.this.progressBar != null) {
                SearchResultsFrag.this.progressBar.setVisibility(8);
            }
            SearchResultsFrag.this.fireImpressionEvents();
        }
    }
    
    class ScrollLoggingListener implements OnScrollStopListener
    {
        @Override
        public void log() {
            SearchResultsFrag.this.fireImpressionEvents();
        }
    }
    
    enum SearchCategory
    {
        PEOPLE, 
        SUGGESTIONS, 
        VIDEOS;
    }
    
    private class SearchResultsAdapter extends BaseAdapter implements AdapterView$OnItemClickListener, ListAdapter
    {
        private boolean ignoreClicks;
        private int maxCount;
        private int resId;
        private SearchCategory searchCategory;
        
        public SearchResultsAdapter(final SearchCategory searchCategory, final boolean ignoreClicks) {
            this.searchCategory = searchCategory;
            this.ignoreClicks = ignoreClicks;
            this.setResid();
        }
        
        private void adjustHeight(final SearchResultView searchResultView) {
            int n = 0;
            if (searchResultView.getImage() != null) {
                switch (this.searchCategory) {
                    default: {
                        n = SearchResultsFrag.this.imgHeightVideo;
                        break;
                    }
                    case PEOPLE: {
                        n = SearchResultsFrag.this.imgHeightPeople;
                        break;
                    }
                }
            }
            else {
                n = -2;
            }
            searchResultView.setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(-1, n));
        }
        
        private View createView() {
            final NetflixActivity netflixActivity = (NetflixActivity)SearchResultsFrag.this.getActivity();
            SearchResultView searchResultView;
            if (netflixActivity.isForKids()) {
                searchResultView = new KidsSearchResultView((Context)netflixActivity, this.resId);
            }
            else {
                searchResultView = new SearchResultView((Context)netflixActivity, this.resId);
            }
            this.adjustHeight(searchResultView);
            if (this.ignoreClicks) {
                searchResultView.setIgnoreClicks();
            }
            return (View)searchResultView;
        }
        
        private SearchTrackable getTrackableForPos(final int n) {
            switch (this.searchCategory) {
                default: {
                    return null;
                }
                case VIDEOS: {
                    return SearchResultsFrag.this.results.getVideosListTrackable();
                }
                case PEOPLE: {
                    return SearchResultsFrag.this.results.getPeopleListTrackable();
                }
                case SUGGESTIONS: {
                    return SearchResultsFrag.this.results.getSuggestionsListTrackable();
                }
            }
        }
        
        private void setResid() {
            switch (this.searchCategory) {
                default: {
                    this.resId = 2130903169;
                }
                case PEOPLE: {
                    this.resId = SearchUtils.getSearchViewLayoutPeople();
                }
                case SUGGESTIONS: {
                    this.resId = SearchUtils.getSearchViewLayoutRelated();
                }
            }
        }
        
        public int getCount() {
            int n = 0;
            if (SearchResultsFrag.this.results == null) {
                n = 0;
            }
            else {
                switch (this.searchCategory) {
                    default: {
                        n = 0;
                        break;
                    }
                    case VIDEOS: {
                        n = SearchResultsFrag.this.results.getNumResultsVideos();
                        break;
                    }
                    case PEOPLE: {
                        n = SearchResultsFrag.this.results.getNumResultsPeople();
                        break;
                    }
                    case SUGGESTIONS: {
                        n = SearchResultsFrag.this.results.getNumResultsSuggestions();
                        break;
                    }
                }
            }
            return Math.min(n, this.maxCount);
        }
        
        public Object getItem(final int n) {
            return SearchResultsFrag.getItem(SearchResultsFrag.this.results, this.searchCategory, n);
        }
        
        public long getItemId(final int n) {
            return n;
        }
        
        public View getView(final int n, final View view, final ViewGroup viewGroup) {
            View view2 = null;
            Label_0018: {
                if (view != null) {
                    view2 = view;
                    if (view instanceof SearchResultView) {
                        break Label_0018;
                    }
                }
                view2 = this.createView();
            }
            final SearchTrackable trackableForPos = this.getTrackableForPos(n);
            ((SearchResultView)view2).update(this.getItem(n), new PlayContextImp(trackableForPos, n), SearchResultsFrag.this.query, trackableForPos.getReferenceId());
            return view2;
        }
        
        public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
            if (SearchResultsFrag.this.progressBar != null) {
                SearchResultsFrag.this.progressBar.setVisibility(0);
            }
            view.performClick();
        }
        
        public void setMaxCount(final int maxCount) {
            this.maxCount = maxCount;
        }
    }
    
    class keyboardHider implements View$OnTouchListener
    {
        public boolean onTouch(final View view, final MotionEvent motionEvent) {
            SearchResultsFrag.this.hideKeyboard();
            return false;
        }
    }
}
