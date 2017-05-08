// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.survey;

import android.os.Parcel;
import android.os.Parcelable$Creator;

final class SurveyQuestion$1 implements Parcelable$Creator
{
    public SurveyQuestion createFromParcel(final Parcel parcel) {
        return new SurveyQuestion(parcel);
    }
    
    public SurveyQuestion[] newArray(final int n) {
        return new SurveyQuestion[n];
    }
}
