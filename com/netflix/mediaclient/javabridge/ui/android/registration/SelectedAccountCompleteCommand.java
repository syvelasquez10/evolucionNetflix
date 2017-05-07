// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.ui.android.registration;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.event.nrdp.BaseNccpEvent;
import org.json.JSONException;
import org.json.JSONObject;
import com.netflix.mediaclient.javabridge.ui.BaseCommandCompletedEvent;

public class SelectedAccountCompleteCommand extends BaseCommandCompletedEvent
{
    public static final String NAME = "selectedAccount";
    public static final String OBJECT = "nrdp.registration";
    private static final String RESULT = "result";
    private static final String TAG = "nf_reg";
    private boolean selected;
    
    public SelectedAccountCompleteCommand(final JSONObject jsonObject) throws JSONException {
        super("selectedAccount", jsonObject);
        this.selected = false;
        this.localPopulate(jsonObject);
    }
    
    @Override
    public String getObject() {
        return "nrdp.registration";
    }
    
    public boolean isSelectedSuccess() {
        return this.selected;
    }
    
    protected void localPopulate(final JSONObject jsonObject) throws JSONException {
        this.selected = BaseNccpEvent.getString(jsonObject, "result", "ERROR").equals("COMPLETE");
        Log.d("nf_reg", "populated: selected" + this.selected);
    }
}
