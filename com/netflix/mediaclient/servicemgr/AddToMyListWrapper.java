// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

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
    private final AddToMyListWrapper$AddToListDataHash dataHash;
    private final ServiceManager serviceMan;
    
    public AddToMyListWrapper(final ServiceManager serviceMan) {
        this.dataHash = new AddToMyListWrapper$AddToListDataHash((AddToMyListWrapper$1)null);
        this.serviceMan = serviceMan;
    }
    
    private void addVideoToMyList(final String s, final int n, final String s2) {
        this.serviceMan.getBrowse().addToQueue(s, n, s2, new LoggingManagerCallback("AddToMyListWrapper"));
    }
    
    private void removeVideoFromMyList(final String s, final String s2) {
        this.serviceMan.getBrowse().removeFromQueue(s, s2, new LoggingManagerCallback("AddToMyListWrapper"));
    }
    
    private void update(final String s, final AddToListData$AddToListState stateAndNotify) {
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
    
    public AddToMyListWrapper$TextViewWrapper createAddToMyListWrapper(final NetflixActivity netflixActivity, final TextView textView, final String s, final int n, final boolean b) {
        return new AddToMyListWrapper$TextViewWrapper(this, netflixActivity, textView, s, n, b);
    }
    
    public void register(final String s, final AddToListData$StateListener addToListData$StateListener) {
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
    
    public void unregister(final String s, final AddToListData$StateListener addToListData$StateListener) {
        final AddToListData addToListData = ((HashMap<K, AddToListData>)this.dataHash).get(s);
        if (addToListData == null) {
            Log.w("AddToMyListWrapper", "Unexpected case - can't find listener for video: " + s);
            return;
        }
        if (Log.isLoggable("AddToMyListWrapper", 2)) {
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
                Toast.makeText((Context)this.serviceMan.getActivity(), 2131493169, 1).show();
            }
        }
    }
    
    public void updateToLoading(final String s) {
        this.update(s, AddToListData$AddToListState.LOADING);
    }
}
