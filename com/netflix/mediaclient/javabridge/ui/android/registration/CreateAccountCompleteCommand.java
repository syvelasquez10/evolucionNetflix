// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.ui.android.registration;

import com.netflix.mediaclient.event.nrdp.BaseNccpEvent;
import org.json.JSONException;
import com.netflix.mediaclient.Log;
import org.json.JSONObject;
import com.netflix.mediaclient.javabridge.ui.BaseCommandCompletedEvent;

public class CreateAccountCompleteCommand extends BaseCommandCompletedEvent
{
    private static final String KEY = "key";
    public static final String NAME = "createdAccount";
    public static final String OBJECT = "nrdp.registration";
    private static final String RESULT = "result";
    private static final String TAG = "nf_reg";
    private boolean created;
    private int key;
    
    public CreateAccountCompleteCommand(final JSONObject jsonObject) throws JSONException {
        super("createdAccount", jsonObject);
        this.created = false;
        this.key = -1;
        Log.d("nf_reg", "CreateAccountCompleteCommand constructor");
        this.localPopulate(jsonObject);
    }
    
    public int getKey() {
        return this.key;
    }
    
    @Override
    public String getObject() {
        return "nrdp.registration";
    }
    
    public boolean isCreatedSuccess() {
        Log.d("nf_reg", "populated: " + this.result + " created:" + this.created + " key" + this.key);
        return this.created;
    }
    
    protected void localPopulate(final JSONObject jsonObject) throws JSONException {
        final String string = BaseNccpEvent.getString(jsonObject, "result", "ERROR");
        this.created = string.equals("COMPLETE");
        if (this.created) {
            this.key = BaseNccpEvent.getInt(jsonObject, "key", -1);
        }
        Log.d("nf_reg", "populated: " + string + " created:" + this.created + " key" + this.key);
    }
}
