// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import java.util.Currency;
import java.math.BigDecimal;
import java.util.List;
import com.facebook.internal.Logger;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.facebook.internal.Validate;
import android.content.Context;
import com.facebook.model.GraphObject;
import com.facebook.internal.Utility;
import com.facebook.model.GraphObject$Factory;
import android.os.Bundle;

class InsightsLogger$1 implements Runnable
{
    final /* synthetic */ InsightsLogger this$0;
    final /* synthetic */ String val$eventName;
    final /* synthetic */ Bundle val$parameters;
    final /* synthetic */ double val$valueToSum;
    
    InsightsLogger$1(final InsightsLogger this$0, final String val$eventName, final double val$valueToSum, final Bundle val$parameters) {
        this.this$0 = this$0;
        this.val$eventName = val$eventName;
        this.val$valueToSum = val$valueToSum;
        this.val$parameters = val$parameters;
    }
    
    @Override
    public void run() {
        final String access$000 = buildJSONForEvent(this.val$eventName, this.val$valueToSum, this.val$parameters);
        if (access$000 != null) {
            final GraphObject create = GraphObject$Factory.create();
            create.setProperty("event", "CUSTOM_APP_EVENTS");
            create.setProperty("custom_events", access$000);
            if (Utility.queryAppAttributionSupportAndWait(this.this$0.applicationId)) {
                final String attributionId = Settings.getAttributionId(this.this$0.context.getContentResolver());
                if (attributionId != null) {
                    create.setProperty("attribution", attributionId);
                }
            }
            final String format = String.format("%s/activities", this.this$0.applicationId);
            try {
                final Response executeAndWait = Request.newPostRequest(this.this$0.sessionToLogTo(), format, create, null).executeAndWait();
                if (executeAndWait.getError() != null && executeAndWait.getError().getErrorCode() != -1) {
                    notifyDeveloperError(String.format("Error publishing Insights event '%s'\n  Response: %s\n  Error: %s", access$000, executeAndWait.toString(), executeAndWait.getError().toString()));
                }
            }
            catch (Exception ex) {
                Utility.logd("Insights-exception: ", ex);
            }
        }
    }
}
