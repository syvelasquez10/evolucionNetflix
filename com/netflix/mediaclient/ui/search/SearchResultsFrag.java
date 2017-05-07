// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.search;

import android.view.MotionEvent;
import com.netflix.mediaclient.ui.kids.search.KidsSearchResultView;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.AbsListView$LayoutParams;
import android.widget.BaseAdapter;
import java.util.List;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.SearchVideoList;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;
import android.os.Handler;
import android.os.Parcelable;
import java.util.Collection;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import com.netflix.mediaclient.servicemgr.model.search.SearchVideo;
import android.app.Activity;
import android.util.Pair;
import com.netflix.mediaclient.service.logging.search.utils.SearchLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import android.widget.ScrollView;
import android.view.View$OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView$OnItemClickListener;
import java.util.Locale;
import com.netflix.mediaclient.util.ViewUtils;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import com.netflix.mediaclient.servicemgr.model.search.SearchSuggestion;
import android.view.View$OnClickListener;
import android.view.ViewGroup$LayoutParams;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.model.search.SearchPerson;
import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import android.content.Context;
import com.netflix.mediaclient.util.StringUtils;
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
import com.netflix.mediaclient.android.widget.FlowLayout;
import android.widget.TextView;
import com.netflix.mediaclient.android.widget.StaticGridView;
import java.util.Stack;
import com.netflix.mediaclient.android.fragment.NetflixFrag;

public class SearchResultsFrag extends NetflixFrag
{
    private static final String INSTANCE_STATE_CLICK_HISTORY = "instance_state__click_history";
    private static final String INSTANCE_STATE_PEOPLE_SELECTED_POS = "instance_state_people_selected_pos";
    private static final String INSTANCE_STATE_QUERY = "instance_state_query";
    private static final String INSTANCE_STATE_SUGGESTIONS_SELECTED_POS = "instance_state_suggestions_selected_pos";
    private static final String TAG = "SearchResultsFrag";
    private SearchResultsAdapter adapterPeople;
    private SearchResultsAdapter adapterSuggestions;
    private SearchResultsAdapter adapterVideos;
    private Stack<SearchItemClick> clickPresseHistory;
    private String currentlyDisplaying;
    private StaticGridView gridViewPeople;
    private StaticGridView gridViewSuggestions;
    private StaticGridView gridViewVideos;
    private int imgHeightPeople;
    private int imgHeightVideo;
    private TextView labelPeople;
    private TextView labelSuggestions;
    private TextView labelVideos;
    private FlowLayout layoutPeople;
    private FlowLayout layoutSuggestions;
    private ServiceManager manager;
    private int maxResultsPeople;
    private int maxResultsSuggestions;
    private int maxResultsVideos;
    private int peopleSelectedPos;
    private ProgressBar progressBar;
    private String query;
    private TextView relatedlabel;
    private ISearchResults results;
    private LoggingScrollView scrollView;
    private LoggingScrollView scrollView2;
    private SearchCategory secondarySearch;
    private SearchSimilarItemsGridViewAdapter simAdapter;
    private InstanceState state;
    private int suggestionsSelectedPos;
    private SearchTrackable trackableTitles;
    
    public SearchResultsFrag() {
        this.clickPresseHistory = new Stack<SearchItemClick>();
        this.secondarySearch = SearchCategory.VIDEOS;
        this.suggestionsSelectedPos = -1;
        this.peopleSelectedPos = -1;
        this.state = new InstanceState();
        this.query = "";
    }
    
    static /* synthetic */ int access$316(final SearchResultsFrag searchResultsFrag, final float n) {
        return searchResultsFrag.imgHeightPeople += (int)n;
    }
    
    private void addToClickHistory(final SearchCategory searchCategory, final View view, final int n, final long n2) {
        final Object tag = view.getTag(2131165247);
        if ((tag == null || tag == Boolean.FALSE) && (this.clickPresseHistory.size() == 0 || (this.clickPresseHistory.size() > 0 && this.clickPresseHistory.peek().position != n))) {
            this.clickPresseHistory.push(new SearchItemClick(searchCategory, n, n2, ((SearchResultView)view).getDisplayName()));
        }
        view.setTag(2131165247, (Object)Boolean.TRUE);
    }
    
    private void clearGridSelections() {
        if (this.gridViewPeople != null) {
            for (int i = 0; i < this.gridViewPeople.getCount(); ++i) {
                ((SearchResultView)this.gridViewPeople.getChildAt(i)).clearTitleTextHighlighting();
            }
        }
        if (this.gridViewSuggestions != null) {
            for (int j = 0; j < this.gridViewSuggestions.getCount(); ++j) {
                ((SearchResultView)this.gridViewSuggestions.getChildAt(j)).clearTitleTextHighlighting();
            }
        }
    }
    
    private void clearLayoutSelections() {
        if (this.layoutPeople != null) {
            for (int i = 0; i < this.layoutPeople.getChildCount(); ++i) {
                ((SearchResultView)this.layoutPeople.getChildAt(i)).clearTitleTextHighlighting();
            }
        }
        if (this.layoutSuggestions != null) {
            for (int j = 0; j < this.layoutSuggestions.getChildCount(); ++j) {
                ((SearchResultView)this.layoutSuggestions.getChildAt(j)).clearTitleTextHighlighting();
            }
        }
    }
    
    private void clearSelections() {
        this.clearGridSelections();
        this.clearLayoutSelections();
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
        this.gridViewSuggestions = (StaticGridView)view.findViewById(2131165642);
        this.layoutSuggestions = (FlowLayout)view.findViewById(2131165643);
        this.gridViewVideos = (StaticGridView)view.findViewById(2131165638);
        this.gridViewPeople = (StaticGridView)view.findViewById(2131165640);
        this.labelSuggestions = (TextView)view.findViewById(2131165641);
        this.scrollView2 = (LoggingScrollView)view.findViewById(2131165646);
        this.layoutPeople = (FlowLayout)view.findViewById(2131165644);
        this.scrollView = (LoggingScrollView)view.findViewById(2131165636);
        this.relatedlabel = (TextView)view.findViewById(2131165645);
        this.labelVideos = (TextView)view.findViewById(2131165637);
        this.labelPeople = (TextView)view.findViewById(2131165639);
        this.progressBar = (ProgressBar)view.findViewById(2131165446);
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
    
    private void onPeopleClick(final SearchResultView searchResultView, final int peopleSelectedPos, final long n) {
        this.peopleSelectedPos = peopleSelectedPos;
        this.hideKeyboard();
        this.clearSelections();
        final String highlightTitle = this.highlightTitle((View)searchResultView);
        if (StringUtils.isNotEmpty(highlightTitle)) {
            this.updateSimilarLabel((View)searchResultView);
            this.fetchPeopleVideoData(highlightTitle);
            this.addToClickHistory(SearchCategory.PEOPLE, (View)searchResultView, peopleSelectedPos, n);
            if (this.progressBar != null) {
                this.progressBar.setVisibility(0);
            }
        }
    }
    
    private void onSuggestionClick(final SearchResultView searchResultView, final int suggestionsSelectedPos, final long n) {
        this.suggestionsSelectedPos = suggestionsSelectedPos;
        this.hideKeyboard();
        this.clearSelections();
        final String highlightTitle = this.highlightTitle((View)searchResultView);
        if (StringUtils.isNotEmpty(highlightTitle)) {
            this.updateSimilarLabel((View)searchResultView);
            this.fetchSuggestedVideoData(highlightTitle);
            this.addToClickHistory(SearchCategory.SUGGESTIONS, (View)searchResultView, suggestionsSelectedPos, n);
            if (this.progressBar != null) {
                this.progressBar.setVisibility(0);
            }
        }
    }
    
    private void resetGridViews() {
        if (this.suggestionsSelectedPos == -1 && this.peopleSelectedPos == -1) {
            this.setupVideosGridView();
        }
        this.setupPeopleGridView();
        this.setupSuggestionsGridView();
    }
    
    private void resetPeopleLayout(final String s) {
        if (this.layoutPeople != null) {
            this.layoutPeople.removeAllViews();
            for (int min = Math.min(this.results.getNumResultsPeople(), SearchUtils.getMaxResultsPeople((Context)this.getActivity())), i = 0; i < min; ++i) {
                final SearchTrackable peopleListTrackable = this.results.getPeopleListTrackable();
                final PlayContextImp playContextImp = new PlayContextImp(peopleListTrackable, i);
                final SearchPerson searchPerson = (SearchPerson)this.results.getResultsPeople(i);
                final SearchResultView searchResultView = new SearchResultView((Context)this.getActivity(), SearchUtils.getSearchViewLayoutPeople());
                searchResultView.update(searchPerson, playContextImp, s, peopleListTrackable.getReferenceId());
                this.layoutPeople.addView((View)searchResultView, (ViewGroup$LayoutParams)this.layoutPeople.generateDefaultLayoutParams());
                searchResultView.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                    public void onClick(final View view) {
                        if (view != null && view instanceof SearchResultView) {
                            SearchResultsFrag.this.onPeopleClick((SearchResultView)view, i, 0L);
                        }
                    }
                });
            }
        }
    }
    
    private void resetScrollPosition() {
        if (this.scrollView != null) {
            this.scrollView.scrollTo(0, 0);
        }
        if (this.scrollView2 != null) {
            this.scrollView2.scrollTo(0, 0);
        }
    }
    
    private void resetSuggestionsLayout(final String s) {
        if (this.layoutSuggestions != null) {
            this.layoutSuggestions.removeAllViews();
            for (int min = Math.min(this.results.getNumResultsSuggestions(), SearchUtils.getMaxResultsRelated((Context)this.getActivity())), i = 0; i < min; ++i) {
                final SearchTrackable suggestionsListTrackable = this.results.getSuggestionsListTrackable();
                final PlayContextImp playContextImp = new PlayContextImp(suggestionsListTrackable, i);
                final SearchSuggestion searchSuggestion = (SearchSuggestion)this.results.getResultsSuggestions(i);
                final SearchResultView searchResultView = new SearchResultView((Context)this.getActivity(), SearchUtils.getSearchViewLayoutRelated());
                searchResultView.update(searchSuggestion, playContextImp, s, suggestionsListTrackable.getReferenceId());
                this.layoutSuggestions.addView((View)searchResultView, (ViewGroup$LayoutParams)this.layoutSuggestions.generateDefaultLayoutParams());
                searchResultView.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                    public void onClick(final View view) {
                        if (view != null && view instanceof SearchResultView) {
                            SearchResultsFrag.this.onSuggestionClick((SearchResultView)view, i, 0L);
                        }
                    }
                });
            }
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
                    SearchResultsFrag.access$316(SearchResultsFrag.this, SearchResultsFrag.this.getActivity().getResources().getDimension(2131362021));
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
    
    private void setupLabels() {
        if (this.labelSuggestions != null) {
            final TextView labelSuggestions = this.labelSuggestions;
            String text;
            if (SearchUtils.shouldUpperCaseTitleLabels()) {
                text = this.getString(2131493293).toUpperCase(Locale.US);
            }
            else {
                text = this.getString(2131493293);
            }
            labelSuggestions.setText((CharSequence)text);
        }
        if (this.labelPeople != null) {
            final TextView labelPeople = this.labelPeople;
            String text2;
            if (SearchUtils.shouldUpperCaseTitleLabels()) {
                text2 = this.getString(2131493292).toUpperCase(Locale.US);
            }
            else {
                text2 = this.getString(2131493292);
            }
            labelPeople.setText((CharSequence)text2);
        }
        if (this.labelVideos != null) {
            this.labelVideos.setText((CharSequence)this.getString(2131493291).toUpperCase(Locale.US));
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
        if (this.gridViewPeople == null) {
            return;
        }
        this.gridViewPeople.setOnItemClickListener((AdapterView$OnItemClickListener)new AdapterView$OnItemClickListener() {
            public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                if (view != null && view instanceof SearchResultView) {
                    SearchResultsFrag.this.onPeopleClick((SearchResultView)view, n, n2);
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
        if (this.gridViewSuggestions == null) {
            return;
        }
        this.gridViewSuggestions.setOnItemClickListener((AdapterView$OnItemClickListener)new AdapterView$OnItemClickListener() {
            public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                if (view != null && view instanceof SearchResultView) {
                    SearchResultsFrag.this.onSuggestionClick((SearchResultView)view, n, n2);
                }
            }
        });
    }
    
    private void setupVideosGridView() {
        if (this.gridViewVideos == null) {
            return;
        }
        if (this.peopleSelectedPos == -1 && this.suggestionsSelectedPos == -1) {
            this.gridViewVideos.setAdapter((ListAdapter)null);
            this.adapterVideos = new SearchResultsAdapter(SearchCategory.VIDEOS, false);
            this.gridViewVideos.setAdapter((ListAdapter)this.adapterVideos);
            this.gridViewVideos.setOnItemClickListener((AdapterView$OnItemClickListener)this.adapterVideos);
        }
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
        this.maxResultsSuggestions = SearchUtils.getMaxResultsRelated((Context)this.getActivity());
        this.maxResultsPeople = SearchUtils.getMaxResultsPeople((Context)this.getActivity());
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
    
    public void clearGridSelected() {
        this.suggestionsSelectedPos = -1;
        this.peopleSelectedPos = -1;
    }
    
    public void clearSelectedStack() {
        this.clickPresseHistory.clear();
    }
    
    public void fireGridViewVideosImpressionEvents() {
        if (this.gridViewVideos != null && this.gridViewVideos.getCount() > 0) {
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
        this.fireGridViewVideosImpressionEvents();
        if (this.gridViewPeople != null && this.gridViewPeople.getCount() > 0) {
            final Pair<Integer, Integer> visiblePositions = ViewUtils.getVisiblePositions(this.gridViewPeople, this.scrollView);
            if (visiblePositions != null) {
                SearchLogUtils.reportSearchImpression(1L, (Context)this.getActivity(), IClientLogging.ModalView.searchResults, this.getReferenceId(this.results.getPeopleListTrackable()), null, (int)visiblePositions.first, (int)visiblePositions.second, IClientLogging.ModalView.peopleResults);
            }
        }
        if (this.gridViewSuggestions != null && this.gridViewSuggestions.getCount() > 0) {
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
        this.state.restore(bundle);
        if (this.suggestionsSelectedPos == -1 && this.peopleSelectedPos == -1) {
            this.setupVideosGridView();
        }
        this.setupPeopleGridView();
        this.setupSuggestionsGridView();
        this.setupScrollViews(inflate);
        return inflate;
    }
    
    public void onSaveInstanceState(final Bundle bundle) {
        this.state.save(bundle);
        super.onSaveInstanceState(bundle);
    }
    
    public void refresh() {
        this.notifyAdapters();
    }
    
    public void setServiceManager(final ServiceManager manager) {
        this.manager = manager;
    }
    
    public boolean showLastSelectedItem() {
        final boolean b = false;
        SearchItemClick searchItemClick;
        do {
            final boolean b2 = b;
            if (this.clickPresseHistory.isEmpty()) {
                return b2;
            }
            searchItemClick = this.clickPresseHistory.pop();
        } while (searchItemClick.displayName == null || this.currentlyDisplaying == null || searchItemClick.displayName.compareToIgnoreCase(this.currentlyDisplaying) == 0);
        View view = null;
        final StaticGridView staticGridView = null;
        StaticGridView staticGridView2;
        if (searchItemClick.searchCategory == SearchCategory.SUGGESTIONS) {
            if (this.gridViewSuggestions != null) {
                view = this.gridViewSuggestions.getChildAt(searchItemClick.position);
                staticGridView2 = this.gridViewSuggestions;
            }
            else {
                staticGridView2 = staticGridView;
                if (this.layoutSuggestions != null) {
                    final View child = this.layoutSuggestions.getChildAt(searchItemClick.position);
                    staticGridView2 = staticGridView;
                    if ((view = child) != null) {
                        child.performClick();
                        staticGridView2 = staticGridView;
                        view = child;
                    }
                }
            }
        }
        else {
            final boolean b2 = b;
            if (searchItemClick.searchCategory != SearchCategory.PEOPLE) {
                return b2;
            }
            if (this.gridViewPeople != null) {
                view = this.gridViewPeople.getChildAt(searchItemClick.position);
                staticGridView2 = this.gridViewPeople;
            }
            else {
                staticGridView2 = staticGridView;
                if (this.layoutPeople != null) {
                    final View child2 = this.layoutPeople.getChildAt(searchItemClick.position);
                    staticGridView2 = staticGridView;
                    if ((view = child2) != null) {
                        child2.performClick();
                        staticGridView2 = staticGridView;
                        view = child2;
                    }
                }
            }
        }
        if (staticGridView2 != null) {
            staticGridView2.performItemClick(view, searchItemClick.position, searchItemClick.id);
        }
        return true;
    }
    
    public void update(final ISearchResults results, final String query) {
        Log.v("SearchResultsFrag", "Updating...");
        this.results = results;
        this.secondarySearch = SearchCategory.VIDEOS;
        if (this.progressBar != null) {
            this.progressBar.setVisibility(8);
        }
        if (this.query.compareToIgnoreCase(query) != 0) {
            this.query = query;
            this.clearSelectedStack();
        }
        if (this.relatedlabel != null) {
            this.relatedlabel.setVisibility(8);
        }
        this.updateMaxResults();
        this.updateLabelVisibilty();
        this.resetGridViews();
        this.resetPeopleLayout(query);
        this.resetSuggestionsLayout(query);
        this.notifyAdapters();
        this.resetScrollPosition();
        if (this.progressBar != null) {
            this.progressBar.setVisibility(8);
        }
    }
    
    class InstanceState
    {
        private void restoreClickHistory(final Bundle bundle) {
            if (bundle != null && bundle.containsKey("instance_state__click_history")) {
                final Parcelable[] parcelableArray = bundle.getParcelableArray("instance_state__click_history");
                if (parcelableArray != null && parcelableArray.length != 0) {
                    final ArrayList list = new ArrayList<SearchItemClick>(parcelableArray.length);
                    for (int length = parcelableArray.length, i = 0; i < length; ++i) {
                        list.add((SearchItemClick)parcelableArray[i]);
                    }
                    if (list.size() > 0 && SearchResultsFrag.this.clickPresseHistory != null) {
                        SearchResultsFrag.this.clickPresseHistory.addAll(list);
                    }
                }
            }
        }
        
        private void restoreGridViewPositions(final Bundle bundle) {
            if (bundle == null) {
                return;
            }
            this.restoreGridViewPositions(bundle, SearchResultsFrag.this.gridViewSuggestions, "instance_state_suggestions_selected_pos");
            this.restoreGridViewPositions(bundle, SearchResultsFrag.this.gridViewPeople, "instance_state_people_selected_pos");
        }
        
        private void restoreGridViewPositions(final Bundle bundle, final StaticGridView staticGridView, final String s) {
            if (bundle != null && staticGridView != null && bundle.containsKey(s)) {
                final int int1 = bundle.getInt(s, -1);
                if (int1 != -1) {
                    if (staticGridView == SearchResultsFrag.this.gridViewPeople) {
                        SearchResultsFrag.this.peopleSelectedPos = int1;
                    }
                    else if (staticGridView == SearchResultsFrag.this.gridViewSuggestions) {
                        SearchResultsFrag.this.suggestionsSelectedPos = int1;
                    }
                    new Handler().postDelayed((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            staticGridView.performItemClick(staticGridView.getChildAt(int1), int1, staticGridView.getAdapter().getItemId(int1));
                        }
                    }, 300L);
                }
            }
        }
        
        private void restoreLayoutPositions(final Bundle bundle) {
            if (bundle == null) {
                return;
            }
            this.restoreLayoutPositions(bundle, SearchResultsFrag.this.layoutSuggestions, "instance_state_suggestions_selected_pos");
            this.restoreLayoutPositions(bundle, SearchResultsFrag.this.layoutPeople, "instance_state_people_selected_pos");
        }
        
        private void restoreLayoutPositions(final Bundle bundle, final FlowLayout flowLayout, final String s) {
            if (bundle != null && flowLayout != null && bundle.containsKey(s)) {
                final int int1 = bundle.getInt(s, -1);
                if (int1 != -1) {
                    if (flowLayout == SearchResultsFrag.this.layoutPeople) {
                        SearchResultsFrag.this.peopleSelectedPos = int1;
                    }
                    else if (flowLayout == SearchResultsFrag.this.layoutSuggestions) {
                        SearchResultsFrag.this.suggestionsSelectedPos = int1;
                    }
                    new Handler().postDelayed((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            final View child = flowLayout.getChildAt(int1);
                            if (child != null) {
                                child.performClick();
                            }
                        }
                    }, 300L);
                }
            }
        }
        
        private void restoreQuery(final Bundle bundle) {
            if (bundle != null && bundle.containsKey("instance_state_query")) {
                SearchResultsFrag.this.query = bundle.getString("instance_state_query");
            }
        }
        
        private void saveClickHistoryState(final Bundle bundle) {
            if (SearchResultsFrag.this.clickPresseHistory.size() > 0) {
                final SearchItemClick[] array = (SearchItemClick[])SearchResultsFrag.this.clickPresseHistory.toArray(new SearchItemClick[SearchResultsFrag.this.clickPresseHistory.size()]);
                if (array != null && array.length > 0) {
                    bundle.putParcelableArray("instance_state__click_history", (Parcelable[])array);
                }
            }
        }
        
        private void saveGridViewState(final Bundle bundle) {
            if (SearchResultsFrag.this.peopleSelectedPos != -1) {
                bundle.putInt("instance_state_people_selected_pos", SearchResultsFrag.this.peopleSelectedPos);
            }
            if (SearchResultsFrag.this.suggestionsSelectedPos != -1) {
                bundle.putInt("instance_state_suggestions_selected_pos", SearchResultsFrag.this.suggestionsSelectedPos);
            }
        }
        
        private void saveQueryState(final Bundle bundle) {
            if (StringUtils.isNotEmpty(SearchResultsFrag.this.query)) {
                bundle.putString("instance_state_query", SearchResultsFrag.this.query);
            }
        }
        
        void restore(final Bundle bundle) {
            this.restoreGridViewPositions(bundle);
            this.restoreLayoutPositions(bundle);
            this.restoreClickHistory(bundle);
            this.restoreQuery(bundle);
        }
        
        void save(final Bundle bundle) {
            this.saveGridViewState(bundle);
            this.saveClickHistoryState(bundle);
            this.saveQueryState(bundle);
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
        
        private SearchTrackable getTrackableForPos() {
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
                    this.resId = 2130903171;
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
            final SearchTrackable trackableForPos = this.getTrackableForPos();
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
