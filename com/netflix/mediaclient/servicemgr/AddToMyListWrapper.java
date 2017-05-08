// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import java.util.HashMap;
import android.widget.Toast;
import com.netflix.mediaclient.android.app.Status;
import android.widget.TextView;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.Log;

public class AddToMyListWrapper
{
    private static final String TAG = "AddToMyListWrapper";
    private final AddToMyListWrapper$AddToListDataHash dataHash;
    final ServiceManager serviceMan;
    
    public AddToMyListWrapper(final ServiceManager serviceMan) {
        this.dataHash = new AddToMyListWrapper$AddToListDataHash((AddToMyListWrapper$1)null);
        this.serviceMan = serviceMan;
    }
    
    private void update(final String s, final AddToListData$AddToListState stateAndNotifyListeners) {
        final AddToListData addToListData = ((HashMap<K, AddToListData>)this.dataHash).get(s);
        if (addToListData == null) {
            Log.v("AddToMyListWrapper", "No listeners for video: " + s);
            return;
        }
        if (Log.isLoggable()) {
            Log.v("AddToMyListWrapper", "Updating state for video: " + s + " to: " + stateAndNotifyListeners);
        }
        addToListData.setStateAndNotifyListeners(stateAndNotifyListeners);
    }
    
    void addVideoToMyList(final String s, final VideoType videoType, final int n, final String s2) {
        this.serviceMan.getBrowse().addToQueue(s, videoType, n, BrowseExperience.shouldLoadKubrickLeavesInDetails(), s2, new LoggingManagerCallback("AddToMyListWrapper"));
    }
    
    public TextViewWrapper createAddToMyListWrapper(final NetflixActivity netflixActivity, final TextView textView, final TextView textView2, final String s, final VideoType videoType, final int n, final boolean b) {
        if (BrowseExperience.isKubrick()) {
            return new KubrickTextViewWrapper(this, netflixActivity, textView, textView2, s, videoType, n, b);
        }
        return new TextViewWrapper(this, netflixActivity, textView, s, videoType, n, b);
    }
    
    public TextViewWrapper createAddToMyListWrapper(final NetflixActivity netflixActivity, final TextView textView, final String s, final VideoType videoType, final int n) {
        return new KubrickTextViewWrapper(this, netflixActivity, textView, null, s, videoType, n, false);
    }
    
    public TextViewWrapper createAddToMyListWrapper(final NetflixActivity netflixActivity, final TextView textView, final String s, final VideoType videoType, final int n, final boolean b) {
        return new TextViewWrapper(this, netflixActivity, textView, s, videoType, n, b);
    }
    
    void register(final String s, final AddToListData$StateListener addToListData$StateListener) {
        final AddToListData addToListData = ((HashMap<K, AddToListData>)this.dataHash).get(s);
        AddToListData addToListData3;
        if (addToListData == null) {
            Log.v("AddToMyListWrapper", "Creating new state data for video: " + s);
            final AddToListData addToListData2 = new AddToListData(addToListData$StateListener);
            this.dataHash.put(s, addToListData2);
            addToListData3 = addToListData2;
        }
        else {
            addToListData.addListener(addToListData$StateListener);
            Log.v("AddToMyListWrapper", "Found state data for video: " + s + ", state: " + addToListData.getState());
            addToListData3 = addToListData;
        }
        addToListData$StateListener.update(addToListData3.getState());
    }
    
    void removeVideoFromMyList(final String s, final VideoType videoType, final String s2) {
        this.serviceMan.getBrowse().removeFromQueue(s, videoType, s2, new LoggingManagerCallback("AddToMyListWrapper"));
    }
    
    void unregister(final String s, final AddToListData$StateListener addToListData$StateListener) {
        final AddToListData addToListData = ((HashMap<K, AddToListData>)this.dataHash).get(s);
        if (addToListData == null) {
            Log.w("AddToMyListWrapper", "Unexpected case - can't find listener for video: " + s);
            return;
        }
        if (Log.isLoggable()) {
            Log.v("AddToMyListWrapper", "Removing listener for video: " + s + ", listener: " + addToListData$StateListener);
        }
        addToListData.removeListener(addToListData$StateListener);
    }
    
    public void updateState(final String s, final boolean b) {
        AddToListData$AddToListState addToListData$AddToListState;
        if (b) {
            addToListData$AddToListState = AddToListData$AddToListState.IN_LIST;
        }
        else {
            addToListData$AddToListState = AddToListData$AddToListState.NOT_IN_LIST;
        }
        this.update(s, addToListData$AddToListState);
    }
    
    public void updateToError(final Status status, final String s, final boolean b, final boolean b2) {
        final AddToListData addToListData = ((HashMap<K, AddToListData>)this.dataHash).get(s);
        if (addToListData == null) {
            Log.v("AddToMyListWrapper", "Could not revert state for video: " + s);
        }
        else {
            if (Log.isLoggable()) {
                Log.v("AddToMyListWrapper", "Reverting state for video: " + s);
            }
            addToListData.revertState();
            if (b2) {
                int n;
                if (b) {
                    n = 2131231070;
                }
                else {
                    n = 2131231073;
                }
                Toast.makeText(this.serviceMan.getContext(), n, 1).show();
            }
        }
    }
    
    public void updateToLoading(final String s) {
        this.update(s, AddToListData$AddToListState.LOADING);
    }
}
