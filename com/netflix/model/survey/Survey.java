// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.survey;

import com.fasterxml.jackson.core.JsonParser;
import java.util.Iterator;
import com.google.gson.JsonObject;
import java.util.Map;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.falkor.Falkor;
import com.google.gson.JsonElement;
import java.util.List;
import android.os.Parcel;
import java.util.ArrayList;
import android.os.Parcelable$Creator;
import com.netflix.mediaclient.servicemgr.interface_.JsonPopulator;
import com.netflix.mediaclient.servicemgr.interface_.JsonMerger;
import android.os.Parcelable;

public class Survey implements Parcelable, JsonMerger, JsonPopulator
{
    public static final Parcelable$Creator CREATOR;
    static final String TAG = "Survey";
    ArrayList<SurveyQuestion> questions;
    
    static {
        CREATOR = (Parcelable$Creator)new Survey$1();
    }
    
    private Survey() {
        this.questions = new ArrayList<SurveyQuestion>(0);
    }
    
    public Survey(final Parcel parcel) {
        parcel.readTypedList((List)(this.questions = new ArrayList<SurveyQuestion>(0)), SurveyQuestion.CREATOR);
    }
    
    public Survey(final JsonElement jsonElement) {
        this.questions = new ArrayList<SurveyQuestion>(0);
        this.populate(jsonElement);
    }
    
    public static Survey createTestSurvey() {
        final Survey survey = new Survey();
        final SurveyQuestion surveyQuestion = new SurveyQuestion();
        surveyQuestion.id = "CA";
        surveyQuestion.header = "Question 1 of 1";
        surveyQuestion.title = "How much do you agree with this?";
        surveyQuestion.body = "Netflix is amazeballs awesome sauce!";
        surveyQuestion.agree = "Kesinlikle Katilmiyorum";
        surveyQuestion.disagree = "Kesinlikle Katilmiyorum";
        surveyQuestion.skip = "SKIP";
        survey.questions.add(surveyQuestion);
        return survey;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public SurveyQuestion getFirstQuestion() {
        if (this.questions.size() > 0) {
            return this.questions.get(0);
        }
        return null;
    }
    
    public SurveyQuestion getQuestion(final int n) {
        return this.questions.get(n);
    }
    
    public int getQuestionTotal() {
        return this.questions.size();
    }
    
    public boolean isEmpty() {
        return this.getFirstQuestion() == null || this.getFirstQuestion().getId() == null;
    }
    
    public void populate(final JsonElement jsonElement) {
        final JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("Survey", "Populating with: " + asJsonObject);
        }
        final Iterator<Map.Entry<String, JsonElement>> iterator = asJsonObject.entrySet().iterator();
        while (iterator.hasNext()) {
            this.questions.add(new SurveyQuestion(((Map.Entry<String, JsonElement>)iterator.next()).getValue()));
        }
    }
    
    public boolean set(final String s, final JsonParser jsonParser) {
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("Survey", "Populating with: " + jsonParser);
        }
        this.questions.add(new SurveyQuestion(jsonParser));
        return false;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeTypedList((List)this.questions);
    }
}
