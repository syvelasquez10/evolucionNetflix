// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import android.widget.TextView$BufferType;
import android.widget.TextView;
import java.net.MalformedURLException;
import com.facebook.model.GraphObject$Factory;
import org.json.JSONObject;
import android.view.ViewGroup$LayoutParams;
import java.util.HashSet;
import com.facebook.android.R$drawable;
import android.widget.CheckBox;
import android.view.ViewStub;
import java.util.Comparator;
import java.util.Collections;
import android.graphics.Bitmap;
import com.facebook.android.R$id;
import android.widget.ProgressBar;
import com.facebook.android.R$layout;
import android.view.ViewGroup;
import android.view.View;
import java.net.URL;
import java.util.Iterator;
import com.facebook.FacebookException;
import android.widget.ImageView;
import java.text.Collator;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import android.view.LayoutInflater;
import java.util.ArrayList;
import java.util.Map;
import android.content.Context;
import android.widget.SectionIndexer;
import android.widget.BaseAdapter;
import com.facebook.model.GraphObject;

class GraphObjectAdapter<T extends GraphObject> extends BaseAdapter implements SectionIndexer
{
    private static final int ACTIVITY_CIRCLE_VIEW_TYPE = 2;
    private static final int DISPLAY_SECTIONS_THRESHOLD = 1;
    private static final int GRAPH_OBJECT_VIEW_TYPE = 1;
    private static final int HEADER_VIEW_TYPE = 0;
    private static final String ID = "id";
    private static final int MAX_PREFETCHED_PICTURES = 20;
    private static final String NAME = "name";
    private static final String PICTURE = "picture";
    private Context context;
    private GraphObjectCursor<T> cursor;
    private GraphObjectAdapter$DataNeededListener dataNeededListener;
    private boolean displaySections;
    private GraphObjectAdapter$Filter<T> filter;
    private Map<String, T> graphObjectsById;
    private Map<String, ArrayList<T>> graphObjectsBySection;
    private String groupByField;
    private final LayoutInflater inflater;
    private GraphObjectAdapter$OnErrorListener onErrorListener;
    private final Map<String, ImageRequest> pendingRequests;
    private Map<String, ImageResponse> prefetchedPictureCache;
    private ArrayList<String> prefetchedProfilePictureIds;
    private List<String> sectionKeys;
    private boolean showCheckbox;
    private boolean showPicture;
    private List<String> sortFields;
    
    public GraphObjectAdapter(final Context context) {
        this.pendingRequests = new HashMap<String, ImageRequest>();
        this.sectionKeys = new ArrayList<String>();
        this.graphObjectsBySection = new HashMap<String, ArrayList<T>>();
        this.graphObjectsById = new HashMap<String, T>();
        this.prefetchedPictureCache = new HashMap<String, ImageResponse>();
        this.prefetchedProfilePictureIds = new ArrayList<String>();
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }
    
    private void callOnErrorListener(Exception ex) {
        if (this.onErrorListener != null) {
            if (!(ex instanceof FacebookException)) {
                ex = new FacebookException(ex);
            }
            this.onErrorListener.onError(this, (FacebookException)ex);
        }
    }
    
    private static int compareGraphObjects(final GraphObject graphObject, final GraphObject graphObject2, final Collection<String> collection, final Collator collator) {
        for (final String s : collection) {
            final String s2 = (String)graphObject.getProperty(s);
            final String s3 = (String)graphObject2.getProperty(s);
            if (s2 != null && s3 != null) {
                final int compare = collator.compare(s2, s3);
                if (compare != 0) {
                    return compare;
                }
                continue;
            }
            else {
                if (s2 == null && s3 == null) {
                    continue;
                }
                if (s2 == null) {
                    return -1;
                }
                return 1;
            }
        }
        return 0;
    }
    
    private void downloadProfilePicture(final String tag, final URL url, final ImageView imageView) {
        if (url != null) {
            boolean b;
            if (imageView == null) {
                b = true;
            }
            else {
                b = false;
            }
            if (b || !url.equals(imageView.getTag())) {
                if (!b) {
                    imageView.setTag((Object)tag);
                    imageView.setImageResource(this.getDefaultPicture());
                }
                final ImageRequest build = new ImageRequest$Builder(this.context.getApplicationContext(), url).setCallerTag(this).setCallback(new GraphObjectAdapter$2(this, tag, imageView)).build();
                this.pendingRequests.put(tag, build);
                ImageDownloader.downloadAsync(build);
            }
        }
    }
    
    private View getActivityCircleView(final View view, final ViewGroup viewGroup) {
        View inflate = view;
        if (view == null) {
            inflate = this.inflater.inflate(R$layout.com_facebook_picker_activity_circle_row, (ViewGroup)null);
        }
        ((ProgressBar)inflate.findViewById(R$id.com_facebook_picker_row_activity_circle)).setVisibility(0);
        return inflate;
    }
    
    private void processImageResponse(final ImageResponse imageResponse, final String s, final ImageView imageView) {
        this.pendingRequests.remove(s);
        if (imageResponse.getError() != null) {
            this.callOnErrorListener(imageResponse.getError());
        }
        if (imageView == null) {
            if (imageResponse.getBitmap() != null) {
                if (this.prefetchedPictureCache.size() >= 20) {
                    this.prefetchedPictureCache.remove(this.prefetchedProfilePictureIds.remove(0));
                }
                this.prefetchedPictureCache.put(s, imageResponse);
            }
        }
        else if (imageView != null && s.equals(imageView.getTag())) {
            final Exception error = imageResponse.getError();
            final Bitmap bitmap = imageResponse.getBitmap();
            if (error == null && bitmap != null) {
                imageView.setImageBitmap(bitmap);
                imageView.setTag((Object)imageResponse.getRequest().getImageUrl());
            }
        }
    }
    
    private void rebuildSections() {
        final boolean b = false;
        this.sectionKeys = new ArrayList<String>();
        this.graphObjectsBySection = new HashMap<String, ArrayList<T>>();
        this.graphObjectsById = new HashMap<String, T>();
        this.displaySections = false;
        if (this.cursor == null || this.cursor.getCount() == 0) {
            return;
        }
        this.cursor.moveToFirst();
        int n = 0;
        do {
            final GraphObject graphObject = this.cursor.getGraphObject();
            if (this.filterIncludesItem((T)graphObject)) {
                ++n;
                final String sectionKeyOfGraphObject = this.getSectionKeyOfGraphObject((T)graphObject);
                if (!this.graphObjectsBySection.containsKey(sectionKeyOfGraphObject)) {
                    this.sectionKeys.add(sectionKeyOfGraphObject);
                    this.graphObjectsBySection.put(sectionKeyOfGraphObject, new ArrayList<T>());
                }
                this.graphObjectsBySection.get(sectionKeyOfGraphObject).add((T)graphObject);
                this.graphObjectsById.put(this.getIdOfGraphObject((T)graphObject), (T)graphObject);
            }
        } while (this.cursor.moveToNext());
        if (this.sortFields != null) {
            final Collator instance = Collator.getInstance();
            final Iterator<ArrayList<T>> iterator = this.graphObjectsBySection.values().iterator();
            while (iterator.hasNext()) {
                Collections.sort(iterator.next(), new GraphObjectAdapter$1(this, instance));
            }
        }
        Collections.sort(this.sectionKeys, Collator.getInstance());
        boolean displaySections = b;
        if (this.sectionKeys.size() > 1) {
            displaySections = b;
            if (n > 1) {
                displaySections = true;
            }
        }
        this.displaySections = displaySections;
    }
    
    private boolean shouldShowActivityCircleCell() {
        return this.cursor != null && this.cursor.areMoreObjectsAvailable() && this.dataNeededListener != null && !this.isEmpty();
    }
    
    public boolean areAllItemsEnabled() {
        return this.displaySections;
    }
    
    public boolean changeCursor(final GraphObjectCursor<T> cursor) {
        if (this.cursor == cursor) {
            return false;
        }
        if (this.cursor != null) {
            this.cursor.close();
        }
        this.cursor = cursor;
        this.rebuildAndNotify();
        return true;
    }
    
    protected View createGraphObjectView(final T t, final View view) {
        final View inflate = this.inflater.inflate(this.getGraphObjectRowLayoutId(t), (ViewGroup)null);
        final ViewStub viewStub = (ViewStub)inflate.findViewById(R$id.com_facebook_picker_checkbox_stub);
        if (viewStub != null) {
            if (!this.getShowCheckbox()) {
                viewStub.setVisibility(8);
            }
            else {
                this.updateCheckboxState((CheckBox)viewStub.inflate(), false);
            }
        }
        final ViewStub viewStub2 = (ViewStub)inflate.findViewById(R$id.com_facebook_picker_profile_pic_stub);
        if (!this.getShowPicture()) {
            viewStub2.setVisibility(8);
            return inflate;
        }
        ((ImageView)viewStub2.inflate()).setVisibility(0);
        return inflate;
    }
    
    boolean filterIncludesItem(final T t) {
        return this.filter == null || this.filter.includeItem(t);
    }
    
    public int getCount() {
        int size = 0;
        if (this.sectionKeys.size() == 0) {
            return 0;
        }
        if (this.displaySections) {
            size = this.sectionKeys.size();
        }
        final Iterator<ArrayList<T>> iterator = this.graphObjectsBySection.values().iterator();
        while (iterator.hasNext()) {
            size += iterator.next().size();
        }
        int n = size;
        if (this.shouldShowActivityCircleCell()) {
            n = size + 1;
        }
        return n;
    }
    
    public GraphObjectCursor<T> getCursor() {
        return this.cursor;
    }
    
    public GraphObjectAdapter$DataNeededListener getDataNeededListener() {
        return this.dataNeededListener;
    }
    
    protected int getDefaultPicture() {
        return R$drawable.com_facebook_profile_default_icon;
    }
    
    GraphObjectAdapter$Filter<T> getFilter() {
        return this.filter;
    }
    
    protected int getGraphObjectRowLayoutId(final T t) {
        return R$layout.com_facebook_picker_list_row;
    }
    
    protected View getGraphObjectView(final T t, final View view, final ViewGroup viewGroup) {
        View graphObjectView = view;
        if (view == null) {
            graphObjectView = this.createGraphObjectView(t, view);
        }
        this.populateGraphObjectView(graphObjectView, t);
        return graphObjectView;
    }
    
    public List<T> getGraphObjectsById(final Collection<String> collection) {
        final HashSet<String> set = new HashSet<String>();
        set.addAll((Collection<?>)collection);
        final ArrayList list = new ArrayList<GraphObject>(set.size());
        final Iterator<Object> iterator = set.iterator();
        while (iterator.hasNext()) {
            final GraphObject graphObject = this.graphObjectsById.get(iterator.next());
            if (graphObject != null) {
                list.add(graphObject);
            }
        }
        return (List<T>)list;
    }
    
    public String getGroupByField() {
        return this.groupByField;
    }
    
    String getIdOfGraphObject(final T t) {
        if (t.asMap().containsKey("id")) {
            final Object property = t.getProperty("id");
            if (property instanceof String) {
                return (String)property;
            }
        }
        throw new FacebookException("Received an object without an ID.");
    }
    
    public Object getItem(final int n) {
        final GraphObjectAdapter$SectionAndItem<T> sectionAndItem = this.getSectionAndItem(n);
        if (sectionAndItem.getType() == GraphObjectAdapter$SectionAndItem$Type.GRAPH_OBJECT) {
            return sectionAndItem.graphObject;
        }
        return null;
    }
    
    public long getItemId(final int n) {
        final GraphObjectAdapter$SectionAndItem<T> sectionAndItem = this.getSectionAndItem(n);
        if (sectionAndItem != null && sectionAndItem.graphObject != null) {
            final String idOfGraphObject = this.getIdOfGraphObject((T)sectionAndItem.graphObject);
            if (idOfGraphObject != null) {
                return Long.parseLong(idOfGraphObject);
            }
        }
        return 0L;
    }
    
    public int getItemViewType(final int n) {
        switch (GraphObjectAdapter$3.$SwitchMap$com$facebook$widget$GraphObjectAdapter$SectionAndItem$Type[this.getSectionAndItem(n).getType().ordinal()]) {
            default: {
                throw new FacebookException("Unexpected type of section and item.");
            }
            case 1: {
                return 0;
            }
            case 2: {
                return 1;
            }
            case 3: {
                return 2;
            }
        }
    }
    
    public GraphObjectAdapter$OnErrorListener getOnErrorListener() {
        return this.onErrorListener;
    }
    
    String getPictureFieldSpecifier() {
        final ImageView imageView = (ImageView)this.createGraphObjectView(null, null).findViewById(R$id.com_facebook_picker_image);
        if (imageView == null) {
            return null;
        }
        final ViewGroup$LayoutParams layoutParams = imageView.getLayoutParams();
        return String.format("picture.height(%d).width(%d)", layoutParams.height, layoutParams.width);
    }
    
    protected URL getPictureUrlOfGraphObject(final T t) {
        final Object property = t.getProperty("picture");
    Block_5_Outer:
        while (true) {
            Label_0036: {
                if (!(property instanceof String)) {
                    break Label_0036;
                }
                String url = (String)property;
                if (url == null) {
                    return null;
                }
                try {
                    return new URL(url);
                    // iftrue(Label_0084:, !property instanceof JSONObject)
                    while (true) {
                        Block_4: {
                            break Block_4;
                            final GraphObjectAdapter$ItemPictureData data;
                            url = data.getUrl();
                            continue Block_5_Outer;
                        }
                        final GraphObjectAdapter$ItemPictureData data = GraphObject$Factory.create((JSONObject)property).cast(GraphObjectAdapter$ItemPicture.class).getData();
                        continue;
                    }
                }
                // iftrue(Label_0084:, data == null)
                catch (MalformedURLException ex) {}
            }
            return null;
            Label_0084: {
                final String url = null;
            }
            continue;
        }
    }
    
    int getPosition(final String s, final T t) {
        final int n = 0;
        final Iterator<String> iterator = this.sectionKeys.iterator();
        int n2 = 0;
        while (true) {
            while (iterator.hasNext()) {
                final String s2 = iterator.next();
                int n3 = n2;
                if (this.displaySections) {
                    n3 = n2 + 1;
                }
                if (s2.equals(s)) {
                    final boolean b = true;
                    n2 = n3;
                    final int n4 = b ? 1 : 0;
                    int n5;
                    if (n4 == 0) {
                        n5 = -1;
                    }
                    else {
                        if (t == null) {
                            int n6 = n;
                            if (this.displaySections) {
                                n6 = 1;
                            }
                            return n2 - n6;
                        }
                        final Iterator<T> iterator2 = this.graphObjectsBySection.get(s).iterator();
                        while (iterator2.hasNext()) {
                            n5 = n2;
                            if (GraphObject$Factory.hasSameId(iterator2.next(), t)) {
                                return n5;
                            }
                            ++n2;
                        }
                        return -1;
                    }
                    return n5;
                }
                n2 = this.graphObjectsBySection.get(s2).size() + n3;
            }
            final int n4 = 0;
            continue;
        }
    }
    
    public int getPositionForSection(int max) {
        int position;
        final int n = position = 0;
        if (this.displaySections) {
            max = Math.max(0, Math.min(max, this.sectionKeys.size() - 1));
            position = n;
            if (max < this.sectionKeys.size()) {
                position = this.getPosition(this.sectionKeys.get(max), null);
            }
        }
        return position;
    }
    
    GraphObjectAdapter$SectionAndItem<T> getSectionAndItem(int n) {
        String s = null;
        if (this.sectionKeys.size() == 0) {
            return null;
        }
        GraphObject graphObject = null;
        Label_0090: {
            if (!this.displaySections) {
                s = this.sectionKeys.get(0);
                final ArrayList<T> list = this.graphObjectsBySection.get(s);
                if (n >= 0 && n < list.size()) {
                    graphObject = this.graphObjectsBySection.get(s).get(n);
                }
                else {
                    assert this.dataNeededListener != null && this.cursor.areMoreObjectsAvailable();
                    return new GraphObjectAdapter$SectionAndItem<T>(null, null);
                }
            }
            else {
                for (final String s2 : this.sectionKeys) {
                    final int n2 = n - 1;
                    if (n == 0) {
                        final GraphObject graphObject2 = null;
                        s = s2;
                        graphObject = graphObject2;
                        break Label_0090;
                    }
                    final ArrayList<T> list2 = this.graphObjectsBySection.get(s2);
                    if (n2 < list2.size()) {
                        final GraphObject graphObject3 = list2.get(n2);
                        s = s2;
                        graphObject = graphObject3;
                        break Label_0090;
                    }
                    n = n2 - list2.size();
                }
                graphObject = null;
            }
        }
        if (s != null) {
            return new GraphObjectAdapter$SectionAndItem<T>(s, graphObject);
        }
        throw new IndexOutOfBoundsException("position");
    }
    
    public int getSectionForPosition(int max) {
        final int n = 0;
        final GraphObjectAdapter$SectionAndItem<T> sectionAndItem = this.getSectionAndItem(max);
        max = n;
        if (sectionAndItem != null) {
            max = n;
            if (sectionAndItem.getType() != GraphObjectAdapter$SectionAndItem$Type.ACTIVITY_CIRCLE) {
                max = Math.max(0, Math.min(this.sectionKeys.indexOf(sectionAndItem.sectionKey), this.sectionKeys.size() - 1));
            }
        }
        return max;
    }
    
    protected View getSectionHeaderView(final String text, final View view, final ViewGroup viewGroup) {
        TextView textView = (TextView)view;
        if (textView == null) {
            textView = (TextView)this.inflater.inflate(R$layout.com_facebook_picker_list_section_header, (ViewGroup)null);
        }
        textView.setText((CharSequence)text);
        return (View)textView;
    }
    
    protected String getSectionKeyOfGraphObject(final T t) {
        String upperCase = null;
        if (this.groupByField != null) {
            final String s = (String)t.getProperty(this.groupByField);
            if ((upperCase = s) != null) {
                upperCase = s;
                if (s.length() > 0) {
                    upperCase = s.substring(0, 1).toUpperCase();
                }
            }
        }
        if (upperCase != null) {
            return upperCase;
        }
        return "";
    }
    
    public Object[] getSections() {
        if (this.displaySections) {
            return this.sectionKeys.toArray();
        }
        return new Object[0];
    }
    
    public boolean getShowCheckbox() {
        return this.showCheckbox;
    }
    
    public boolean getShowPicture() {
        return this.showPicture;
    }
    
    public List<String> getSortFields() {
        return this.sortFields;
    }
    
    protected CharSequence getSubTitleOfGraphObject(final T t) {
        return null;
    }
    
    protected CharSequence getTitleOfGraphObject(final T t) {
        return (String)t.getProperty("name");
    }
    
    public View getView(final int n, final View view, final ViewGroup viewGroup) {
        final GraphObjectAdapter$SectionAndItem<T> sectionAndItem = this.getSectionAndItem(n);
        switch (GraphObjectAdapter$3.$SwitchMap$com$facebook$widget$GraphObjectAdapter$SectionAndItem$Type[sectionAndItem.getType().ordinal()]) {
            default: {
                throw new FacebookException("Unexpected type of section and item.");
            }
            case 1: {
                return this.getSectionHeaderView(sectionAndItem.sectionKey, view, viewGroup);
            }
            case 2: {
                return this.getGraphObjectView((T)sectionAndItem.graphObject, view, viewGroup);
            }
            case 3: {
                assert this.cursor.areMoreObjectsAvailable() && this.dataNeededListener != null;
                this.dataNeededListener.onDataNeeded();
                return this.getActivityCircleView(view, viewGroup);
            }
        }
    }
    
    public int getViewTypeCount() {
        return 3;
    }
    
    public boolean hasStableIds() {
        return true;
    }
    
    public boolean isEmpty() {
        return this.sectionKeys.size() == 0;
    }
    
    public boolean isEnabled(final int n) {
        return this.getSectionAndItem(n).getType() == GraphObjectAdapter$SectionAndItem$Type.GRAPH_OBJECT;
    }
    
    boolean isGraphObjectSelected(final String s) {
        return false;
    }
    
    protected void populateGraphObjectView(final View view, final T t) {
        final String idOfGraphObject = this.getIdOfGraphObject(t);
        view.setTag((Object)idOfGraphObject);
        final CharSequence titleOfGraphObject = this.getTitleOfGraphObject(t);
        final TextView textView = (TextView)view.findViewById(R$id.com_facebook_picker_title);
        if (textView != null) {
            textView.setText(titleOfGraphObject, TextView$BufferType.SPANNABLE);
        }
        final CharSequence subTitleOfGraphObject = this.getSubTitleOfGraphObject(t);
        final TextView textView2 = (TextView)view.findViewById(R$id.picker_subtitle);
        if (textView2 != null) {
            if (subTitleOfGraphObject != null) {
                textView2.setText(subTitleOfGraphObject, TextView$BufferType.SPANNABLE);
                textView2.setVisibility(0);
            }
            else {
                textView2.setVisibility(8);
            }
        }
        if (this.getShowCheckbox()) {
            this.updateCheckboxState((CheckBox)view.findViewById(R$id.com_facebook_picker_checkbox), this.isGraphObjectSelected(idOfGraphObject));
        }
        if (this.getShowPicture()) {
            final URL pictureUrlOfGraphObject = this.getPictureUrlOfGraphObject(t);
            if (pictureUrlOfGraphObject != null) {
                final ImageView imageView = (ImageView)view.findViewById(R$id.com_facebook_picker_image);
                if (!this.prefetchedPictureCache.containsKey(idOfGraphObject)) {
                    this.downloadProfilePicture(idOfGraphObject, pictureUrlOfGraphObject, imageView);
                    return;
                }
                final ImageResponse imageResponse = this.prefetchedPictureCache.get(idOfGraphObject);
                imageView.setImageBitmap(imageResponse.getBitmap());
                imageView.setTag((Object)imageResponse.getRequest().getImageUrl());
            }
        }
    }
    
    public void prioritizeViewRange(int i, final int n, int j) {
        if (n >= i) {
            for (int k = n; k >= 0; --k) {
                final GraphObjectAdapter$SectionAndItem<T> sectionAndItem = this.getSectionAndItem(k);
                if (sectionAndItem.graphObject != null) {
                    final ImageRequest imageRequest = this.pendingRequests.get(this.getIdOfGraphObject((T)sectionAndItem.graphObject));
                    if (imageRequest != null) {
                        ImageDownloader.prioritizeRequest(imageRequest);
                    }
                }
            }
            final int max = Math.max(0, i - j);
            final int min = Math.min(n + j, this.getCount() - 1);
            final ArrayList<GraphObject> list = (ArrayList<GraphObject>)new ArrayList<T>();
            GraphObjectAdapter$SectionAndItem<T> sectionAndItem2;
            for (j = max; j < i; ++j) {
                sectionAndItem2 = this.getSectionAndItem(j);
                if (sectionAndItem2.graphObject != null) {
                    list.add(sectionAndItem2.graphObject);
                }
            }
            GraphObjectAdapter$SectionAndItem<T> sectionAndItem3;
            for (i = n + 1; i <= min; ++i) {
                sectionAndItem3 = this.getSectionAndItem(i);
                if (sectionAndItem3.graphObject != null) {
                    list.add(sectionAndItem3.graphObject);
                }
            }
            for (final GraphObject graphObject : list) {
                final URL pictureUrlOfGraphObject = this.getPictureUrlOfGraphObject((T)graphObject);
                final String idOfGraphObject = this.getIdOfGraphObject((T)graphObject);
                final boolean remove = this.prefetchedProfilePictureIds.remove(idOfGraphObject);
                this.prefetchedProfilePictureIds.add(idOfGraphObject);
                if (!remove) {
                    this.downloadProfilePicture(idOfGraphObject, pictureUrlOfGraphObject, null);
                }
            }
        }
    }
    
    public void rebuildAndNotify() {
        this.rebuildSections();
        this.notifyDataSetChanged();
    }
    
    public void setDataNeededListener(final GraphObjectAdapter$DataNeededListener dataNeededListener) {
        this.dataNeededListener = dataNeededListener;
    }
    
    void setFilter(final GraphObjectAdapter$Filter<T> filter) {
        this.filter = filter;
    }
    
    public void setGroupByField(final String groupByField) {
        this.groupByField = groupByField;
    }
    
    public void setOnErrorListener(final GraphObjectAdapter$OnErrorListener onErrorListener) {
        this.onErrorListener = onErrorListener;
    }
    
    public void setShowCheckbox(final boolean showCheckbox) {
        this.showCheckbox = showCheckbox;
    }
    
    public void setShowPicture(final boolean showPicture) {
        this.showPicture = showPicture;
    }
    
    public void setSortFields(final List<String> sortFields) {
        this.sortFields = sortFields;
    }
    
    void updateCheckboxState(final CheckBox checkBox, final boolean b) {
    }
}
