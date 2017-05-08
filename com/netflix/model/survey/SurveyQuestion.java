// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.survey;

import java.util.Iterator;
import com.google.gson.JsonObject;
import java.util.Map;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.falkor.Falkor;
import com.google.gson.JsonElement;
import com.netflix.falkor.BranchNodeUtils;
import com.fasterxml.jackson.core.JsonParser;
import android.os.Parcel;
import com.google.gson.annotations.SerializedName;
import android.os.Parcelable$Creator;
import com.netflix.mediaclient.servicemgr.interface_.JsonPopulator;
import com.netflix.mediaclient.servicemgr.interface_.JsonMerger;
import android.os.Parcelable;

public class SurveyQuestion implements Parcelable, JsonMerger, JsonPopulator
{
    public static final Parcelable$Creator CREATOR;
    private static final String TAG = "SurveyQuestion";
    @SerializedName("positiveChoice")
    String agree;
    @SerializedName("questionBody")
    String body;
    @SerializedName("negativeChoice")
    String disagree;
    @SerializedName("type")
    String header;
    @SerializedName("id")
    String id;
    @SerializedName("skipLabel")
    String skip;
    @SerializedName("questionHeader")
    String title;
    @SerializedName("surveyType")
    String type;
    
    static {
        CREATOR = (Parcelable$Creator)new SurveyQuestion$1();
    }
    
    protected SurveyQuestion() {
    }
    
    public SurveyQuestion(final Parcel parcel) {
        this.id = parcel.readString();
        this.type = parcel.readString();
        this.header = parcel.readString();
        this.title = parcel.readString();
        this.body = parcel.readString();
        this.agree = parcel.readString();
        this.disagree = parcel.readString();
        this.skip = parcel.readString();
    }
    
    public SurveyQuestion(final JsonParser jsonParser) {
        BranchNodeUtils.merge(this, jsonParser, jsonParser.getCurrentToken(), false, 10);
    }
    
    public SurveyQuestion(final JsonElement jsonElement) {
        this.populate(jsonElement);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public String getAgreementLabel() {
        return this.agree;
    }
    
    public String getBody() {
        return this.body;
    }
    
    public String getDisagreementLabel() {
        return this.disagree;
    }
    
    public String getHeader() {
        return this.header;
    }
    
    public String getId() {
        return this.id;
    }
    
    public String getSkipLabel() {
        return this.skip;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public String getType() {
        return this.type;
    }
    
    public void populate(final JsonElement jsonElement) {
        final JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("SurveyQuestion", "Populating with: " + asJsonObject);
        }
        for (final Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
            final JsonElement jsonElement2 = entry.getValue();
            final String s = entry.getKey();
            int n = 0;
            Label_0170: {
                switch (s.hashCode()) {
                    case 3355: {
                        if (s.equals("id")) {
                            n = 0;
                            break Label_0170;
                        }
                        break;
                    }
                    case -673944140: {
                        if (s.equals("surveyType")) {
                            n = 1;
                            break Label_0170;
                        }
                        break;
                    }
                    case -1221270899: {
                        if (s.equals("header")) {
                            n = 2;
                            break Label_0170;
                        }
                        break;
                    }
                    case 1746732563: {
                        if (s.equals("questionHeader")) {
                            n = 3;
                            break Label_0170;
                        }
                        break;
                    }
                    case -172652888: {
                        if (s.equals("questionBody")) {
                            n = 4;
                            break Label_0170;
                        }
                        break;
                    }
                    case 2091762938: {
                        if (s.equals("positiveChoice")) {
                            n = 5;
                            break Label_0170;
                        }
                        break;
                    }
                    case 1683659318: {
                        if (s.equals("negativeChoice")) {
                            n = 6;
                            break Label_0170;
                        }
                        break;
                    }
                    case 2076524725: {
                        if (s.equals("skipLabel")) {
                            n = 7;
                            break Label_0170;
                        }
                        break;
                    }
                }
                n = -1;
            }
            switch (n) {
                default: {
                    continue;
                }
                case 0: {
                    this.id = jsonElement2.getAsString();
                    continue;
                }
                case 1: {
                    this.type = jsonElement2.getAsString();
                    continue;
                }
                case 2: {
                    this.header = jsonElement2.getAsString();
                    continue;
                }
                case 3: {
                    this.title = jsonElement2.getAsString();
                    continue;
                }
                case 4: {
                    this.body = jsonElement2.getAsString();
                    continue;
                }
                case 5: {
                    this.agree = jsonElement2.getAsString();
                    continue;
                }
                case 6: {
                    this.disagree = jsonElement2.getAsString();
                    continue;
                }
                case 7: {
                    this.skip = jsonElement2.getAsString();
                    continue;
                }
            }
        }
    }
    
    public boolean set(final String s, final JsonParser jsonParser) {
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("SurveyQuestion", "Populating with: " + jsonParser);
        }
        switch (s) {
            default: {
                return false;
            }
            case "id": {
                this.id = jsonParser.getValueAsString();
                break;
            }
            case "surveyType": {
                this.type = jsonParser.getValueAsString();
                break;
            }
            case "header": {
                this.header = jsonParser.getValueAsString();
                break;
            }
            case "questionHeader": {
                this.title = jsonParser.getValueAsString();
                break;
            }
            case "questionBody": {
                this.body = jsonParser.getValueAsString();
                break;
            }
            case "positiveChoice": {
                this.agree = jsonParser.getValueAsString();
                break;
            }
            case "negativeChoice": {
                this.disagree = jsonParser.getValueAsString();
                break;
            }
            case "skipLabel": {
                this.skip = jsonParser.getValueAsString();
                break;
            }
        }
        return true;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeString(this.id);
        parcel.writeString(this.type);
        parcel.writeString(this.header);
        parcel.writeString(this.title);
        parcel.writeString(this.body);
        parcel.writeString(this.agree);
        parcel.writeString(this.disagree);
        parcel.writeString(this.skip);
    }
}
