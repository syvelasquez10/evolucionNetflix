// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import java.util.Iterator;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import com.netflix.mediaclient.servicemgr.UiLocation;
import com.netflix.mediaclient.servicemgr.interface_.LoMoType;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.view.View;
import com.netflix.mediaclient.servicemgr.interface_.BasicLoMo;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import android.app.Activity;
import com.netflix.mediaclient.util.MathUtils;
import java.util.ArrayList;
import com.netflix.mediaclient.util.DataUtil;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.l10n.LocalizationUtils;
import android.content.Context;
import java.util.List;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.interface_.Video;

public abstract class BasePaginatedAdapter<T extends Video>
{
    private static final String TAG = "BasePaginatedAdapter";
    protected final NetflixActivity activity;
    private String currTitle;
    protected List<T> data;
    protected boolean isRtlLocale;
    private int listViewPos;
    protected final int numItemsPerPage;
    
    public BasePaginatedAdapter(final Context context) {
        this.activity = (NetflixActivity)context;
        this.numItemsPerPage = this.computeNumItemsPerPage();
        this.isRtlLocale = LocalizationUtils.isCurrentLocaleRTL();
    }
    
    private void appendOrUpdate(final List<T> list, final List<T> list2, int i, final boolean b) {
        for (int j = 0; j < list2.size(); ++j) {
            final Video video = list2.get(j);
            final int n = i + j;
            if (n < list.size()) {
                int n3;
                if (this.isRtlLocale) {
                    final int n2 = list.size() - n - 1;
                    if (Log.isLoggable()) {
                        Log.d("BasePaginatedAdapter", "RTL locale, update to position " + j + ", item " + video);
                    }
                    if ((n3 = n2) < 0) {
                        if (Log.isLoggable()) {
                            Log.e("BasePaginatedAdapter", "Adjusted index for RTL is less than 0 " + n2 + ". Default to 0!");
                        }
                        n3 = 0;
                    }
                }
                else {
                    n3 = n;
                    if (Log.isLoggable()) {
                        Log.d("BasePaginatedAdapter", "LTR locale, update to position " + n + ", item " + video);
                        n3 = n;
                    }
                }
                list.set(n3, (T)video);
            }
            else if (this.isRtlLocale) {
                if (Log.isLoggable()) {
                    Log.d("BasePaginatedAdapter", "RTL locale, adding to start from " + j + ", item " + video);
                }
                list.add(0, (T)video);
            }
            else {
                list.add((T)video);
            }
        }
        if (!b && this.isRtlLocale) {
            Log.d("BasePaginatedAdapter", "All data is retrieved and RTL locale is in force. Check if we need to add empty items, just to push items whole way up to right");
            i = list.size() % this.numItemsPerPage;
            if (i > 0) {
                final int n4 = this.numItemsPerPage - i;
                if (Log.isLoggable()) {
                    Log.d("BasePaginatedAdapter", "We do not populate fully last page, just " + i + " video is there. Adding " + n4 + " more empty videos...");
                }
                for (i = 0; i < n4; ++i) {
                    list.add(0, null);
                }
            }
        }
    }
    
    public void appendData(final List<T> list, final String s, final int n, final int n2, final boolean b) {
        if (StringUtils.isEmpty(this.currTitle)) {
            if (Log.isLoggable()) {
                Log.v("BasePaginatedAdapter", this.getCurrTitleFormatted() + "new title: " + s);
            }
            this.currTitle = s;
        }
        if (!StringUtils.safeEquals(s, this.currTitle)) {
            if (Log.isLoggable()) {
                Log.e("BasePaginatedAdapter", this.getCurrTitleFormatted() + "***** title mismatch:" + this.getCurrTitleFormatted() + "\n    curr: " + this.currTitle + this.getCurrTitleFormatted() + "\n    new: " + s);
            }
            this.currTitle = s;
        }
        if (list != null) {
            final int size = this.data.size();
            this.appendOrUpdate(this.data, list, n, b);
            if (Log.isLoggable()) {
                Log.v("BasePaginatedAdapter", this.getCurrTitleFormatted() + "appending data starting with item: " + DataUtil.getFirstItemSafely(list) + ", prev size: " + size + ", new size: " + this.data.size());
                if (this.data.size() == size) {
                    Log.w("BasePaginatedAdapter", this.getCurrTitleFormatted() + "***** append called but no items added");
                }
            }
        }
        if (Log.isLoggable()) {
            Log.v("BasePaginatedAdapter", this.getCurrTitleFormatted() + "currTitle: " + this.currTitle + ", new title: " + s + ", start: " + n + ", end: " + n2 + ", listViewPos: " + this.listViewPos);
        }
    }
    
    public void clearData() {
        if (Log.isLoggable()) {
            Log.v("BasePaginatedAdapter", this.getCurrTitleFormatted() + "Clearing data...");
        }
        this.data = new ArrayList<T>();
        this.currTitle = "";
        this.listViewPos = -1;
    }
    
    protected abstract int computeNumItemsPerPage();
    
    protected int computeNumPages() {
        return MathUtils.ceiling(this.data.size(), this.numItemsPerPage);
    }
    
    protected abstract int computeNumVideosToFetchPerBatch(final int p0);
    
    public Activity getActivity() {
        return this.activity;
    }
    
    public int getCount() {
        return this.computeNumPages();
    }
    
    protected String getCurrTitleFormatted() {
        if (StringUtils.isEmpty(this.currTitle)) {
            return "";
        }
        return this.currTitle + ": ";
    }
    
    public List<T> getDataForPage(int min) {
        if (min >= this.computeNumPages()) {
            return null;
        }
        min = Math.min(this.numItemsPerPage * min, this.data.size());
        final int min2 = Math.min(this.numItemsPerPage + min, this.data.size());
        if (Log.isLoggable()) {
            Log.v("BasePaginatedAdapter", this.getCurrTitleFormatted() + String.format("Getting [%d, %d], data set size: %d", min, min2, this.data.size()));
        }
        return this.data.subList(min, min2);
    }
    
    protected int getListViewPos() {
        return this.listViewPos;
    }
    
    public int getRowHeightInPx() {
        final float n = LoMoViewPager.computeViewPagerWidth(this.activity, true) / this.numItemsPerPage;
        float n2;
        if (BrowseExperience.useLolomoBoxArt()) {
            n2 = 1.43f;
        }
        else {
            n2 = 0.5625f;
        }
        final int n3 = (int)(n2 * n + 0.5f);
        if (Log.isLoggable()) {
            Log.v("BasePaginatedAdapter", "Computed view height: " + n3);
        }
        return n3;
    }
    
    public View getView(final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler, final BasicLoMo basicLoMo, final int n) {
        if (Log.isLoggable()) {
            Log.v("BasePaginatedAdapter", this.getCurrTitleFormatted() + "Getting page for position: " + n);
        }
        return this.getView(objectRecycler$ViewRecycler, this.getDataForPage(n), this.numItemsPerPage, n, basicLoMo);
    }
    
    protected abstract View getView(final ObjectRecycler$ViewRecycler p0, final List<T> p1, final int p2, final int p3, final BasicLoMo p4);
    
    public boolean isLastItem(final int n) {
        return n == this.computeNumPages() - 1;
    }
    
    public void restoreFromMemento(final BasePaginatedAdapter$Memento<T> basePaginatedAdapter$Memento) {
        this.data = basePaginatedAdapter$Memento.data;
        this.listViewPos = basePaginatedAdapter$Memento.listViewPos;
        this.currTitle = basePaginatedAdapter$Memento.currTitle;
        if (Log.isLoggable()) {
            Log.v("BasePaginatedAdapter", this.getCurrTitleFormatted() + "restored from memento: " + basePaginatedAdapter$Memento);
        }
    }
    
    public BasePaginatedAdapter$Memento<T> saveToMemento() {
        final BasePaginatedAdapter$Memento<T> basePaginatedAdapter$Memento = new BasePaginatedAdapter$Memento<T>(this.data, this.listViewPos, this.currTitle);
        if (Log.isLoggable()) {
            Log.v("BasePaginatedAdapter", this.getCurrTitleFormatted() + "saving memento: " + basePaginatedAdapter$Memento);
        }
        return basePaginatedAdapter$Memento;
    }
    
    public void setListViewPos(final int listViewPos) {
        this.listViewPos = listViewPos;
    }
    
    public void trackPresentation(final ServiceManager serviceManager, final BasicLoMo basicLoMo, int n, final Boolean b) {
        final List<T> dataForPage = this.getDataForPage(n);
        if (dataForPage == null || dataForPage.size() == 0) {
            if (Log.isLoggable()) {
                Log.d("nf_presentation", "No videos input videos found in page data");
            }
        }
        else {
            final ArrayList list = new ArrayList<String>(dataForPage.size());
            final ArrayList list2 = new ArrayList<String>(dataForPage.size());
            for (final Video video : dataForPage) {
                if (video != null && VideoType.isPresentationTrackingType(video.getType())) {
                    list.add(video.getId());
                    String boxartImageTypeIdentifier;
                    if (BrowseExperience.useLolomoBoxArt() && LoMoType.usesVerticalBoxArtType(basicLoMo.getType())) {
                        boxartImageTypeIdentifier = video.getBoxartImageTypeIdentifier();
                    }
                    else {
                        boxartImageTypeIdentifier = null;
                    }
                    list2.add(boxartImageTypeIdentifier);
                }
            }
            if (list.size() != 0) {
                n *= this.numItemsPerPage;
                UiLocation uiLocation;
                if (b) {
                    uiLocation = UiLocation.GENRE_LOLOMO;
                }
                else {
                    uiLocation = UiLocation.HOME_LOLOMO;
                }
                if (Log.isLoggable()) {
                    Log.v("nf_presentation", String.format("%s, %s, offset %d %s %s", basicLoMo.getTitle(), uiLocation, n, list, list2));
                }
                serviceManager.getClientLogging().getPresentationTracking().reportPresentation(basicLoMo, (List<String>)list, (List<String>)list2, n, uiLocation);
                return;
            }
            if (Log.isLoggable()) {
                Log.d("nf_presentation", "No videos found for presentation tracking - row: " + basicLoMo.getTitle());
            }
        }
    }
}
