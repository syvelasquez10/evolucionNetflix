// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import com.netflix.mediaclient.ui.details.DetailsActivity;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import android.view.View;
import android.view.View$OnClickListener;
import java.util.HashMap;
import android.content.Context;
import android.widget.Toast;
import com.netflix.mediaclient.android.app.Status;
import android.widget.TextView;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.Log;

public class AddToMyListWrapper
{
    private static final String TAG = "AddToMyListWrapper";
    private final AddToListDataHash dataHash;
    private final ServiceManager serviceMan;
    
    public AddToMyListWrapper(final ServiceManager serviceMan) {
        this.dataHash = new AddToListDataHash();
        this.serviceMan = serviceMan;
    }
    
    private void addVideoToMyList(final String s, final int n, final String s2) {
        this.serviceMan.getBrowse().addToQueue(s, n, s2, new LoggingManagerCallback("AddToMyListWrapper"));
    }
    
    private void removeVideoFromMyList(final String s, final String s2) {
        this.serviceMan.getBrowse().removeFromQueue(s, s2, new LoggingManagerCallback("AddToMyListWrapper"));
    }
    
    private void update(final String s, final AddToListData.AddToListState stateAndNotify) {
        final AddToListData addToListData = ((HashMap<K, AddToListData>)this.dataHash).get(s);
        if (addToListData == null) {
            Log.v("AddToMyListWrapper", "No listeners for video: " + s);
            return;
        }
        if (Log.isLoggable("AddToMyListWrapper", 2)) {
            Log.v("AddToMyListWrapper", "Updating state for video: " + s + " to: " + stateAndNotify);
        }
        addToListData.setStateAndNotify(stateAndNotify);
    }
    
    public TextViewWrapper createAddToMyListWrapper(final NetflixActivity netflixActivity, final TextView textView, final String s, final int n, final boolean b) {
        return new TextViewWrapper(netflixActivity, textView, s, n, b);
    }
    
    public void register(final String s, final AddToListData.StateListener stateListener) {
        final AddToListData addToListData = ((HashMap<K, AddToListData>)this.dataHash).get(s);
        AddToListData addToListData3;
        if (addToListData == null) {
            Log.v("AddToMyListWrapper", "Creating new state data for video: " + s);
            final AddToListData addToListData2 = new AddToListData(stateListener);
            this.dataHash.put(s, addToListData2);
            addToListData3 = addToListData2;
        }
        else {
            addToListData.addListener(stateListener);
            Log.v("AddToMyListWrapper", "Found state data for video: " + s + ", state: " + addToListData.getState());
            addToListData3 = addToListData;
        }
        stateListener.update(addToListData3.getState());
    }
    
    public void unregister(final String s, final AddToListData.StateListener stateListener) {
        final AddToListData addToListData = ((HashMap<K, AddToListData>)this.dataHash).get(s);
        if (addToListData == null) {
            Log.w("AddToMyListWrapper", "Unexpected case - can't find listener for video: " + s);
            return;
        }
        if (Log.isLoggable("AddToMyListWrapper", 2)) {
            Log.v("AddToMyListWrapper", "Removing listener for video: " + s + ", listener: " + stateListener);
        }
        addToListData.removeListener(stateListener);
    }
    
    public void updateState(final String s, final boolean b) {
        AddToListData.AddToListState addToListState;
        if (b) {
            addToListState = AddToListData.AddToListState.IN_LIST;
        }
        else {
            addToListState = AddToListData.AddToListState.NOT_IN_LIST;
        }
        this.update(s, addToListState);
    }
    
    public void updateToError(final Status status, final String s, final boolean b) {
        final AddToListData addToListData = ((HashMap<K, AddToListData>)this.dataHash).get(s);
        if (addToListData == null) {
            Log.v("AddToMyListWrapper", "Could not revert state for video: " + s);
        }
        else {
            if (Log.isLoggable("AddToMyListWrapper", 2)) {
                Log.v("AddToMyListWrapper", "Reverting state for video: " + s);
            }
            addToListData.revertState();
            if (b) {
                Toast.makeText((Context)this.serviceMan.getActivity(), 2131493205, 1).show();
            }
        }
    }
    
    public void updateToLoading(final String s) {
        this.update(s, AddToListData.AddToListState.LOADING);
    }
    
    private static class AddToListDataHash extends HashMap<String, AddToListData>
    {
    }
    
    private class TextViewWrapper implements StateListener
    {
        private final NetflixActivity activity;
        private final boolean keepVisibilityState;
        private final TextView textView;
        private final int trackId;
        private final String videoId;
        
        public TextViewWrapper(final NetflixActivity activity, final TextView textView, final String videoId, final int trackId, final boolean keepVisibilityState) {
            this.activity = activity;
            this.textView = textView;
            this.videoId = videoId;
            this.trackId = trackId;
            this.keepVisibilityState = keepVisibilityState;
        }
        
        @Override
        public void update(final AddToListState addToListState) {
            switch (addToListState) {
                case IN_LIST: {
                    this.textView.setContentDescription((CharSequence)this.activity.getString(2131492977));
                    this.textView.setText((CharSequence)this.activity.getString(2131492974, new Object[] { "\u2212" }));
                    this.textView.setEnabled(true);
                    this.textView.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                        public void onClick(final View view) {
                            UserActionLogUtils.reportRemoveFromQueueActionStarted((Context)TextViewWrapper.this.activity, null, TextViewWrapper.this.activity.getUiScreen());
                            String actionToken = null;
                            if (TextViewWrapper.this.activity instanceof DetailsActivity) {
                                actionToken = ((DetailsActivity)TextViewWrapper.this.activity).getActionToken();
                            }
                            AddToMyListWrapper.this.removeVideoFromMyList(TextViewWrapper.this.videoId, actionToken);
                        }
                    });
                    break;
                }
                case NOT_IN_LIST: {
                    this.textView.setContentDescription((CharSequence)this.activity.getString(2131492976));
                    this.textView.setText((CharSequence)this.activity.getString(2131492974, new Object[] { "+" }));
                    this.textView.setEnabled(true);
                    this.textView.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                        public void onClick(final View view) {
                            UserActionLogUtils.reportAddToQueueActionStarted((Context)TextViewWrapper.this.activity, null, TextViewWrapper.this.activity.getUiScreen());
                            String actionToken = null;
                            if (TextViewWrapper.this.activity instanceof DetailsActivity) {
                                actionToken = ((DetailsActivity)TextViewWrapper.this.activity).getActionToken();
                            }
                            AddToMyListWrapper.this.addVideoToMyList(TextViewWrapper.this.videoId, TextViewWrapper.this.trackId, actionToken);
                        }
                    });
                    break;
                }
                case LOADING: {
                    this.textView.setEnabled(false);
                    break;
                }
            }
            if (!this.keepVisibilityState) {
                if (!AddToMyListWrapper.this.serviceMan.isCurrentProfileIQEnabled()) {
                    this.textView.setVisibility(8);
                    return;
                }
                this.textView.setVisibility(0);
            }
        }
    }
}
