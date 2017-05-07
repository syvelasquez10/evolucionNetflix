// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.search;

import android.os.Bundle;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import com.netflix.mediaclient.servicemgr.interface_.search.SearchVideo;
import android.app.Activity;
import android.util.Pair;
import com.netflix.mediaclient.service.logging.search.utils.SearchLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import android.widget.ScrollView;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.android.widget.LoggingScrollView$OnScrollStopListener;
import android.annotation.SuppressLint;
import android.view.View$OnTouchListener;
import android.widget.AdapterView$OnItemClickListener;
import java.util.Locale;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import com.netflix.mediaclient.servicemgr.interface_.search.SearchSuggestion;
import com.netflix.mediaclient.servicemgr.interface_.search.SearchPerson;
import android.view.View$OnClickListener;
import android.view.ViewGroup$LayoutParams;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
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
import com.netflix.mediaclient.servicemgr.interface_.trackable.SearchTrackable;
import com.netflix.mediaclient.ui.common.SearchSimilarItemsGridViewAdapter;
import com.netflix.mediaclient.android.widget.LoggingScrollView;
import com.netflix.mediaclient.servicemgr.interface_.search.ISearchResults;
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
    private SearchResultsFrag$SearchResultsAdapter adapterPeople;
    private SearchResultsFrag$SearchResultsAdapter adapterSuggestions;
    private SearchResultsFrag$SearchResultsAdapter adapterVideos;
    private final Stack<SearchItemClick> clickPresseHistory;
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
    private SearchResultsFrag$SearchCategory secondarySearch;
    private SearchSimilarItemsGridViewAdapter simAdapter;
    private final SearchResultsFrag$InstanceState state;
    private int suggestionsSelectedPos;
    private SearchTrackable trackableTitles;
    
    public SearchResultsFrag() {
        this.clickPresseHistory = new Stack<SearchItemClick>();
        this.secondarySearch = SearchResultsFrag$SearchCategory.VIDEOS;
        this.suggestionsSelectedPos = -1;
        this.peopleSelectedPos = -1;
        this.state = new SearchResultsFrag$InstanceState(this);
        this.query = "";
    }
    
    private void addToClickHistory(final SearchResultsFrag$SearchCategory searchResultsFrag$SearchCategory, final View view, final int n, final long n2) {
        final Object tag = view.getTag(2131427345);
        if ((tag == null || tag == Boolean.FALSE) && (this.clickPresseHistory.size() == 0 || (this.clickPresseHistory.size() > 0 && this.clickPresseHistory.peek().position != n))) {
            this.clickPresseHistory.push(new SearchItemClick(searchResultsFrag$SearchCategory, n, n2, ((SearchResultView)view).getDisplayName()));
        }
        view.setTag(2131427345, (Object)Boolean.TRUE);
    }
    
    private void clearGridSelections() {
        final int n = 0;
        if (this.gridViewPeople != null) {
            for (int i = 0; i < this.gridViewPeople.getCount(); ++i) {
                ((SearchResultView)this.gridViewPeople.getChildAt(i)).clearTitleTextHighlighting();
            }
        }
        if (this.gridViewSuggestions != null) {
            for (int j = n; j < this.gridViewSuggestions.getCount(); ++j) {
                ((SearchResultView)this.gridViewSuggestions.getChildAt(j)).clearTitleTextHighlighting();
            }
        }
    }
    
    private void clearLayoutSelections() {
        final int n = 0;
        if (this.layoutPeople != null) {
            for (int i = 0; i < this.layoutPeople.getChildCount(); ++i) {
                ((SearchResultView)this.layoutPeople.getChildAt(i)).clearTitleTextHighlighting();
            }
        }
        if (this.layoutSuggestions != null) {
            for (int j = n; j < this.layoutSuggestions.getChildCount(); ++j) {
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
        this.manager.getBrowse().fetchSimilarVideosForPerson(s, 40, new SearchResultsFrag$OnSimsFetchedCallback(this, System.nanoTime(), s, SearchResultsFrag$SearchCategory.PEOPLE), this.query);
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
        this.manager.getBrowse().fetchSimilarVideosForQuerySuggestion(s, 40, new SearchResultsFrag$OnSimsFetchedCallback(this, System.nanoTime(), s, SearchResultsFrag$SearchCategory.SUGGESTIONS), this.query);
    }
    
    private void findViews(final View view) {
        this.gridViewSuggestions = (StaticGridView)view.findViewById(2131427811);
        this.layoutSuggestions = (FlowLayout)view.findViewById(2131427812);
        this.gridViewVideos = (StaticGridView)view.findViewById(2131427807);
        this.gridViewPeople = (StaticGridView)view.findViewById(2131427809);
        this.labelSuggestions = (TextView)view.findViewById(2131427810);
        this.scrollView2 = (LoggingScrollView)view.findViewById(2131427814);
        this.layoutPeople = (FlowLayout)view.findViewById(2131427813);
        this.scrollView = (LoggingScrollView)view.findViewById(2131427805);
        this.relatedlabel = (TextView)view.findViewById(2131427539);
        this.labelVideos = (TextView)view.findViewById(2131427806);
        this.labelPeople = (TextView)view.findViewById(2131427808);
        this.progressBar = (ProgressBar)view.findViewById(2131427522);
    }
    
    public static Object getItem(final ISearchResults searchResults, final SearchResultsFrag$SearchCategory searchResultsFrag$SearchCategory, final int n) {
        if (searchResults == null) {
            return null;
        }
        switch (SearchResultsFrag$7.$SwitchMap$com$netflix$mediaclient$ui$search$SearchResultsFrag$SearchCategory[searchResultsFrag$SearchCategory.ordinal()]) {
            default: {
                return null;
            }
            case 1: {
                return searchResults.getResultsSuggestions(n);
            }
            case 3: {
                return searchResults.getResultsVideos(n);
            }
            case 2: {
                return searchResults.getResultsPeople(n);
            }
        }
    }
    
    private String[] getItemIdsForRange(final SearchResultsFrag$SearchCategory searchResultsFrag$SearchCategory, int i, final int n) {
        if (n < i) {
            return null;
        }
        final ArrayList<String> list = new ArrayList<String>();
        while (i <= n) {
            final Object item = getItem(this.results, searchResultsFrag$SearchCategory, i);
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
        if (this.getActivity() != null) {
            final View currentFocus = this.getActivity().getCurrentFocus();
            if (currentFocus != null && currentFocus instanceof EditText) {
                DeviceUtils.forceHideKeyboard(this.getActivity(), (EditText)currentFocus);
            }
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
            this.addToClickHistory(SearchResultsFrag$SearchCategory.PEOPLE, (View)searchResultView, peopleSelectedPos, n);
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
            this.addToClickHistory(SearchResultsFrag$SearchCategory.SUGGESTIONS, (View)searchResultView, suggestionsSelectedPos, n);
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
                final SearchPerson resultsPeople = this.results.getResultsPeople(i);
                final SearchResultView searchResultView = new SearchResultView((Context)this.getActivity(), SearchUtils.getSearchViewLayoutPeople());
                searchResultView.update(resultsPeople, playContextImp, s, peopleListTrackable.getReferenceId());
                this.layoutPeople.addView((View)searchResultView, (ViewGroup$LayoutParams)this.layoutPeople.generateDefaultLayoutParams());
                searchResultView.setOnClickListener((View$OnClickListener)new SearchResultsFrag$5(this, i));
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
                final SearchSuggestion resultsSuggestions = this.results.getResultsSuggestions(i);
                final SearchResultView searchResultView = new SearchResultView((Context)this.getActivity(), SearchUtils.getSearchViewLayoutRelated());
                searchResultView.update(resultsSuggestions, playContextImp, s, suggestionsListTrackable.getReferenceId());
                this.layoutSuggestions.addView((View)searchResultView, (ViewGroup$LayoutParams)this.layoutSuggestions.generateDefaultLayoutParams());
                searchResultView.setOnClickListener((View$OnClickListener)new SearchResultsFrag$6(this, i));
            }
        }
    }
    
    private void setupGridViewObserver(final StaticGridView staticGridView) {
        staticGridView.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)new SearchResultsFrag$3(this, staticGridView));
    }
    
    private void setupGridViewObserverForTitles(final StaticGridView staticGridView) {
        staticGridView.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)new SearchResultsFrag$4(this, staticGridView));
    }
    
    private void setupLabels() {
        if (this.labelSuggestions != null) {
            final TextView labelSuggestions = this.labelSuggestions;
            String text;
            if (SearchUtils.shouldUpperCaseTitleLabels()) {
                text = this.getString(2131493268).toUpperCase(Locale.US);
            }
            else {
                text = this.getString(2131493268);
            }
            labelSuggestions.setText((CharSequence)text);
        }
        if (this.labelPeople != null) {
            final TextView labelPeople = this.labelPeople;
            String text2;
            if (SearchUtils.shouldUpperCaseTitleLabels()) {
                text2 = this.getString(2131493267).toUpperCase(Locale.US);
            }
            else {
                text2 = this.getString(2131493267);
            }
            labelPeople.setText((CharSequence)text2);
        }
        if (this.labelVideos != null) {
            this.labelVideos.setText((CharSequence)this.getString(2131493266).toUpperCase(Locale.US));
        }
    }
    
    private void setupPeopleGridView() {
        if (this.gridViewPeople == null) {
            return;
        }
        this.gridViewPeople.setAdapter((ListAdapter)null);
        this.adapterPeople = new SearchResultsFrag$SearchResultsAdapter(this, SearchResultsFrag$SearchCategory.PEOPLE, !SearchUtils.shouldOpenNewActivityForRelated());
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
        this.gridViewPeople.setOnItemClickListener((AdapterView$OnItemClickListener)new SearchResultsFrag$2(this));
    }
    
    @SuppressLint({ "ClickableViewAccessibility" })
    private void setupScrollViewKeyboardHiding() {
        if (this.scrollView != null) {
            this.scrollView.setOnTouchListener((View$OnTouchListener)new SearchResultsFrag$keyboardHider(this));
        }
        if (this.scrollView2 != null) {
            this.scrollView2.setOnTouchListener((View$OnTouchListener)new SearchResultsFrag$keyboardHider(this));
        }
    }
    
    private void setupScrollViewLogging() {
        final SearchResultsFrag$ScrollLoggingListener searchResultsFrag$ScrollLoggingListener = new SearchResultsFrag$ScrollLoggingListener(this);
        if (this.scrollView != null) {
            this.scrollView.setOnScrollStopListener(searchResultsFrag$ScrollLoggingListener);
        }
        if (this.scrollView2 != null) {
            this.scrollView2.setOnScrollStopListener(searchResultsFrag$ScrollLoggingListener);
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
        this.adapterSuggestions = new SearchResultsFrag$SearchResultsAdapter(this, SearchResultsFrag$SearchCategory.SUGGESTIONS, !SearchUtils.shouldOpenNewActivityForRelated());
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
        this.gridViewSuggestions.setOnItemClickListener((AdapterView$OnItemClickListener)new SearchResultsFrag$1(this));
    }
    
    private void setupVideosGridView() {
        if (this.gridViewVideos == null) {
            return;
        }
        if (this.peopleSelectedPos == -1 && this.suggestionsSelectedPos == -1) {
            this.gridViewVideos.setAdapter((ListAdapter)null);
            this.adapterVideos = new SearchResultsFrag$SearchResultsAdapter(this, SearchResultsFrag$SearchCategory.VIDEOS, false);
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
        final boolean b = true;
        ViewUtils.setVisibleOrGone((View)this.labelVideos, this.results.getNumResultsVideos() > 0);
        ViewUtils.setVisibleOrGone((View)this.labelPeople, this.results.getNumResultsPeople() > 0);
        ViewUtils.setVisibleOrGone((View)this.labelSuggestions, this.results.getNumResultsSuggestions() > 0 && b);
    }
    
    private void updateMaxResults() {
        this.maxResultsSuggestions = SearchUtils.getMaxResultsRelated((Context)this.getActivity());
        this.maxResultsPeople = SearchUtils.getMaxResultsPeople((Context)this.getActivity());
        this.maxResultsVideos = SearchUtils.getMaxResultsVideos((Context)this.getActivity());
    }
    
    private void updateSimilarLabel(final View view) {
        this.currentlyDisplaying = ((SearchResultView)view).getDisplayName();
        if (this.relatedlabel != null && StringUtils.isNotEmpty(this.currentlyDisplaying)) {
            this.relatedlabel.setVisibility(0);
            this.relatedlabel.setText((CharSequence)this.currentlyDisplaying);
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
                IClientLogging$ModalView clientLogging$ModalView = null;
                switch (SearchResultsFrag$7.$SwitchMap$com$netflix$mediaclient$ui$search$SearchResultsFrag$SearchCategory[this.secondarySearch.ordinal()]) {
                    default: {
                        clientLogging$ModalView = IClientLogging$ModalView.titleResults;
                        break;
                    }
                    case 1: {
                        clientLogging$ModalView = IClientLogging$ModalView.suggestionTitleResults;
                        break;
                    }
                    case 2: {
                        clientLogging$ModalView = IClientLogging$ModalView.peopleTitleResults;
                        break;
                    }
                }
                final Activity activity = this.getActivity();
                final IClientLogging$ModalView searchResults = IClientLogging$ModalView.searchResults;
                SearchTrackable searchTrackable;
                if (this.secondarySearch == SearchResultsFrag$SearchCategory.VIDEOS) {
                    searchTrackable = this.results.getVideosListTrackable();
                }
                else {
                    searchTrackable = this.trackableTitles;
                }
                SearchLogUtils.reportSearchImpression(1L, (Context)activity, searchResults, this.getReferenceId(searchTrackable), null, intValue, intValue2, clientLogging$ModalView);
            }
        }
    }
    
    public void fireImpressionEvents() {
        this.fireGridViewVideosImpressionEvents();
        if (this.gridViewPeople != null && this.gridViewPeople.getCount() > 0) {
            final Pair<Integer, Integer> visiblePositions = ViewUtils.getVisiblePositions(this.gridViewPeople, this.scrollView);
            if (visiblePositions != null) {
                SearchLogUtils.reportSearchImpression(1L, (Context)this.getActivity(), IClientLogging$ModalView.searchResults, this.getReferenceId(this.results.getPeopleListTrackable()), null, (int)visiblePositions.first, (int)visiblePositions.second, IClientLogging$ModalView.peopleResults);
            }
        }
        if (this.gridViewSuggestions != null && this.gridViewSuggestions.getCount() > 0) {
            final Pair<Integer, Integer> visiblePositions2 = ViewUtils.getVisiblePositions(this.gridViewSuggestions, this.scrollView);
            if (visiblePositions2 != null) {
                SearchLogUtils.reportSearchImpression(1L, (Context)this.getActivity(), IClientLogging$ModalView.searchResults, this.getReferenceId(this.results.getSuggestionsListTrackable()), null, (int)visiblePositions2.first, (int)visiblePositions2.second, IClientLogging$ModalView.suggestionResults);
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
        View view = null;
        while (!this.clickPresseHistory.isEmpty()) {
            final SearchItemClick searchItemClick = this.clickPresseHistory.pop();
            if (searchItemClick.displayName != null && this.currentlyDisplaying != null && searchItemClick.displayName.compareToIgnoreCase(this.currentlyDisplaying) != 0) {
                while (true) {
                    Label_0223: {
                        View view2 = null;
                        Label_0216: {
                            StaticGridView staticGridView;
                            if (searchItemClick.searchCategory == SearchResultsFrag$SearchCategory.SUGGESTIONS) {
                                if (this.gridViewSuggestions != null) {
                                    view = this.gridViewSuggestions.getChildAt(searchItemClick.position);
                                    staticGridView = this.gridViewSuggestions;
                                }
                                else {
                                    if (this.layoutSuggestions == null) {
                                        break Label_0223;
                                    }
                                    view = this.layoutSuggestions.getChildAt(searchItemClick.position);
                                    if ((view2 = view) == null) {
                                        break Label_0216;
                                    }
                                    view.performClick();
                                    staticGridView = null;
                                }
                            }
                            else {
                                if (searchItemClick.searchCategory != SearchResultsFrag$SearchCategory.PEOPLE) {
                                    return false;
                                }
                                if (this.gridViewPeople != null) {
                                    view = this.gridViewPeople.getChildAt(searchItemClick.position);
                                    staticGridView = this.gridViewPeople;
                                }
                                else {
                                    if (this.layoutPeople == null) {
                                        break Label_0223;
                                    }
                                    view = this.layoutPeople.getChildAt(searchItemClick.position);
                                    if ((view2 = view) == null) {
                                        break Label_0216;
                                    }
                                    view.performClick();
                                    staticGridView = null;
                                }
                            }
                            if (staticGridView != null) {
                                staticGridView.performItemClick(view, searchItemClick.position, searchItemClick.id);
                            }
                            return true;
                        }
                        view = view2;
                        StaticGridView staticGridView = null;
                        continue;
                    }
                    StaticGridView staticGridView = null;
                    continue;
                }
            }
        }
        return false;
    }
    
    public void update(final ISearchResults results, final String query) {
        Log.v("SearchResultsFrag", "Updating...");
        this.results = results;
        this.secondarySearch = SearchResultsFrag$SearchCategory.VIDEOS;
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
}
