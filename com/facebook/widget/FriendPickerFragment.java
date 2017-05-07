// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.app.Activity;
import java.util.List;
import com.facebook.FacebookException;
import com.facebook.model.GraphObject;
import android.content.Context;
import android.text.TextUtils;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import com.facebook.Request;
import com.facebook.Session;
import java.util.Set;
import com.facebook.android.R;
import android.os.Bundle;
import com.facebook.model.GraphUser;

public class FriendPickerFragment extends PickerFragment<GraphUser>
{
    private static final String ID = "id";
    public static final String MULTI_SELECT_BUNDLE_KEY = "com.facebook.widget.FriendPickerFragment.MultiSelect";
    private static final String NAME = "name";
    public static final String USER_ID_BUNDLE_KEY = "com.facebook.widget.FriendPickerFragment.UserId";
    private boolean multiSelect;
    private String userId;
    
    public FriendPickerFragment() {
        this(null);
    }
    
    public FriendPickerFragment(final Bundle friendPickerSettingsFromBundle) {
        super(GraphUser.class, R.layout.com_facebook_friendpickerfragment, friendPickerSettingsFromBundle);
        this.multiSelect = true;
        this.setFriendPickerSettingsFromBundle(friendPickerSettingsFromBundle);
    }
    
    private Request createRequest(final String s, final Set<String> set, final Session session) {
        final Request graphPathRequest = Request.newGraphPathRequest(session, s + "/friends", null);
        final HashSet<Object> set2 = new HashSet<Object>(set);
        set2.addAll(Arrays.asList("id", "name"));
        final String pictureFieldSpecifier = this.adapter.getPictureFieldSpecifier();
        if (pictureFieldSpecifier != null) {
            set2.add(pictureFieldSpecifier);
        }
        final Bundle parameters = graphPathRequest.getParameters();
        parameters.putString("fields", TextUtils.join((CharSequence)",", (Iterable)set2));
        graphPathRequest.setParameters(parameters);
        return graphPathRequest;
    }
    
    private void setFriendPickerSettingsFromBundle(final Bundle bundle) {
        if (bundle != null) {
            if (bundle.containsKey("com.facebook.widget.FriendPickerFragment.UserId")) {
                this.setUserId(bundle.getString("com.facebook.widget.FriendPickerFragment.UserId"));
            }
            this.setMultiSelect(bundle.getBoolean("com.facebook.widget.FriendPickerFragment.MultiSelect", this.multiSelect));
        }
    }
    
    @Override
    PickerFragmentAdapter<GraphUser> createAdapter() {
        final PickerFragmentAdapter<GraphUser> pickerFragmentAdapter = new PickerFragmentAdapter<GraphUser>(this.getActivity()) {
            @Override
            protected int getDefaultPicture() {
                return R.drawable.com_facebook_profile_default_icon;
            }
            
            @Override
            protected int getGraphObjectRowLayoutId(final GraphUser graphUser) {
                return R.layout.com_facebook_picker_list_row;
            }
        };
        pickerFragmentAdapter.setShowCheckbox(true);
        pickerFragmentAdapter.setShowPicture(this.getShowPictures());
        pickerFragmentAdapter.setSortFields(Arrays.asList("name"));
        pickerFragmentAdapter.setGroupByField("name");
        return pickerFragmentAdapter;
    }
    
    @Override
    LoadingStrategy createLoadingStrategy() {
        return new ImmediateLoadingStrategy();
    }
    
    @Override
    SelectionStrategy createSelectionStrategy() {
        if (this.multiSelect) {
            return new MultiSelectionStrategy(this);
        }
        return new SingleSelectionStrategy(this);
    }
    
    @Override
    String getDefaultTitleText() {
        return this.getString(R.string.com_facebook_choose_friends);
    }
    
    public boolean getMultiSelect() {
        return this.multiSelect;
    }
    
    @Override
    Request getRequestForLoadData(final Session session) {
        if (this.adapter == null) {
            throw new FacebookException("Can't issue requests until Fragment has been created.");
        }
        String userId;
        if (this.userId != null) {
            userId = this.userId;
        }
        else {
            userId = "me";
        }
        return this.createRequest(userId, this.extraFields, session);
    }
    
    public List<GraphUser> getSelection() {
        return this.getSelectedGraphObjects();
    }
    
    public String getUserId() {
        return this.userId;
    }
    
    @Override
    public void onInflate(final Activity activity, final AttributeSet set, final Bundle bundle) {
        super.onInflate(activity, set, bundle);
        final TypedArray obtainStyledAttributes = activity.obtainStyledAttributes(set, R.styleable.com_facebook_friend_picker_fragment);
        this.setMultiSelect(obtainStyledAttributes.getBoolean(0, this.multiSelect));
        obtainStyledAttributes.recycle();
    }
    
    @Override
    void saveSettingsToBundle(final Bundle bundle) {
        super.saveSettingsToBundle(bundle);
        bundle.putString("com.facebook.widget.FriendPickerFragment.UserId", this.userId);
        bundle.putBoolean("com.facebook.widget.FriendPickerFragment.MultiSelect", this.multiSelect);
    }
    
    public void setMultiSelect(final boolean multiSelect) {
        if (this.multiSelect != multiSelect) {
            this.multiSelect = multiSelect;
            this.setSelectionStrategy(this.createSelectionStrategy());
        }
    }
    
    @Override
    public void setSettingsFromBundle(final Bundle bundle) {
        super.setSettingsFromBundle(bundle);
        this.setFriendPickerSettingsFromBundle(bundle);
    }
    
    public void setUserId(final String userId) {
        this.userId = userId;
    }
    
    private class ImmediateLoadingStrategy extends LoadingStrategy
    {
        private void followNextLink() {
            FriendPickerFragment.this.displayActivityCircle();
            this.loader.followNextLink();
        }
        
        @Override
        protected void onLoadFinished(final GraphObjectPagingLoader<GraphUser> graphObjectPagingLoader, final SimpleGraphObjectCursor<GraphUser> simpleGraphObjectCursor) {
            super.onLoadFinished((GraphObjectPagingLoader<T>)graphObjectPagingLoader, (SimpleGraphObjectCursor<T>)simpleGraphObjectCursor);
            if (simpleGraphObjectCursor != null && !graphObjectPagingLoader.isLoading()) {
                if (simpleGraphObjectCursor.areMoreObjectsAvailable()) {
                    this.followNextLink();
                    return;
                }
                FriendPickerFragment.this.hideActivityCircle();
                if (simpleGraphObjectCursor.isFromCache()) {
                    long n;
                    if (simpleGraphObjectCursor.getCount() == 0) {
                        n = 2000L;
                    }
                    else {
                        n = 0L;
                    }
                    graphObjectPagingLoader.refreshOriginalRequest(n);
                }
            }
        }
    }
}
