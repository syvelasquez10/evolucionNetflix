// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import com.netflix.mediaclient.ui.common.PlayContext;
import android.content.Context;
import com.netflix.mediaclient.util.LogUtils;
import android.view.View;
import android.view.View$OnClickListener;
import java.util.HashMap;
import android.widget.Toast;
import android.widget.TextView;
import com.netflix.mediaclient.ui.details.DetailsActivity;
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
    
    private void addVideoToMyList(final String s, final int n) {
        this.serviceMan.addToQueue(s, n, new LoggingManagerCallback("AddToMyListWrapper"));
    }
    
    private void removeVideoFromMyList(final String s) {
        this.serviceMan.removeFromQueue(s, new LoggingManagerCallback("AddToMyListWrapper"));
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
    
    public TextViewWrapper createAddToMyListWrapper(final DetailsActivity detailsActivity, final TextView textView) {
        return new TextViewWrapper(detailsActivity, textView);
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
    
    public void updateToError(final int n, final String s, final boolean b) {
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
                Toast.makeText(this.serviceMan.getContext(), 2131296573, 1).show();
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
        private final DetailsActivity activity;
        private final TextView textView;
        
        public TextViewWrapper(final DetailsActivity activity, final TextView textView) {
            this.activity = activity;
            this.textView = textView;
        }
        
        @Override
        public void update(final AddToListState addToListState) {
            switch (addToListState) {
                case IN_LIST: {
                    this.textView.setContentDescription((CharSequence)this.activity.getString(2131296341));
                    this.textView.setCompoundDrawablesWithIntrinsicBounds(2130837822, 0, 0, 0);
                    this.textView.setEnabled(true);
                    this.textView.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                        public void onClick(final View view) {
                            LogUtils.reportRemoveFromQueueActionStarted((Context)TextViewWrapper.this.activity, null, TextViewWrapper.this.activity.getUiScreen());
                            AddToMyListWrapper.this.removeVideoFromMyList(TextViewWrapper.this.activity.getVideoId());
                        }
                    });
                    break;
                }
                case NOT_IN_LIST: {
                    this.textView.setContentDescription((CharSequence)this.activity.getString(2131296340));
                    this.textView.setCompoundDrawablesWithIntrinsicBounds(2130837598, 0, 0, 0);
                    this.textView.setEnabled(true);
                    this.textView.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                        public void onClick(final View view) {
                            int trackId = -1;
                            final PlayContext playContext = TextViewWrapper.this.activity.getPlayContext();
                            if (playContext != null) {
                                trackId = playContext.getTrackId();
                            }
                            LogUtils.reportAddToQueueActionStarted((Context)TextViewWrapper.this.activity, null, TextViewWrapper.this.activity.getUiScreen());
                            AddToMyListWrapper.this.addVideoToMyList(TextViewWrapper.this.activity.getVideoId(), trackId);
                        }
                    });
                    break;
                }
                case LOADING: {
                    this.textView.setEnabled(false);
                    break;
                }
            }
            if (AddToMyListWrapper.this.serviceMan.isCurrentProfileIQEnabled()) {
                this.textView.setVisibility(0);
                return;
            }
            this.textView.setVisibility(8);
        }
    }
}
