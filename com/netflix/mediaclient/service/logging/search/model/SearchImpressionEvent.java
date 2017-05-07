// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.search.model;

import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.client.model.EventType;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.service.logging.client.model.DiscreteEvent;

@SuppressLint({ "DefaultLocale" })
public class SearchImpressionEvent extends DiscreteEvent
{
    private static final String CATEGORY = "uiView";
    private static final String NAME = "impression";
    private String[] childIds;
    private int from;
    private String searchReferenceId;
    private int to;
    private IClientLogging.ModalView view;
    
    public SearchImpressionEvent(final String s, final int n, final int n2, final String[] array, final IClientLogging.ModalView modalView, final IClientLogging.ModalView modalView2) {
        this.setupData(s, n, n2, array, modalView, modalView2);
        this.setupAttributes(modalView);
    }
    
    private String idsToString() {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.childIds.length; ++i) {
            sb.append('\'').append(this.childIds[i]).append('\'');
            if (i == this.childIds.length - 1) {
                break;
            }
            sb.append(',');
        }
        return sb.toString();
    }
    
    private void setupAttributes(final IClientLogging.ModalView modalView) {
        this.modalView = modalView;
        this.type = EventType.event;
        this.category = "uiView";
        this.name = this.getName();
        this.name = "impression";
    }
    
    private void setupData(final String searchReferenceId, final int from, final int to, final String[] childIds, final IClientLogging.ModalView modalView, final IClientLogging.ModalView view) {
        this.modalView = modalView;
        this.childIds = childIds;
        this.searchReferenceId = searchReferenceId;
        this.from = from;
        this.view = view;
        this.to = to;
    }
    
    @Override
    protected JSONObject getData() throws JSONException {
        JSONObject data;
        if ((data = super.getData()) == null) {
            data = new JSONObject();
        }
        String s;
        if (this.childIds != null && this.childIds.length > 0) {
            s = String.format("['search', 'lists', '%s',{'from': %d, 'to': %d,'ids':[%s]}]", this.searchReferenceId, this.from, this.to, this.idsToString());
        }
        else {
            s = String.format("['search', 'lists', '%s',{'from': %d, 'to': %d}]", this.searchReferenceId, this.from, this.to);
        }
        data.put("path", (Object)new JSONArray(s));
        if (this.view != null) {
            data.put("view", (Object)this.view.name());
        }
        return data;
    }
}
