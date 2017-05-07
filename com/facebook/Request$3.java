// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import com.facebook.model.GraphMultiResult;
import java.io.BufferedOutputStream;
import android.text.TextUtils;
import android.util.Pair;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import com.facebook.model.GraphObjectList;
import java.util.Map;
import java.text.SimpleDateFormat;
import android.os.Parcelable;
import java.util.Locale;
import java.util.Date;
import android.os.ParcelFileDescriptor;
import java.io.File;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Handler;
import java.util.HashSet;
import java.net.URLConnection;
import com.facebook.internal.Utility;
import java.util.Arrays;
import java.util.Collection;
import com.facebook.internal.Validate;
import java.net.HttpURLConnection;
import java.util.Iterator;
import android.net.Uri$Builder;
import com.facebook.internal.Logger;
import java.net.URL;
import android.os.Bundle;
import com.facebook.model.GraphObject;
import java.util.List;
import com.facebook.model.GraphPlace;

final class Request$3 implements Request$Callback
{
    final /* synthetic */ Request$GraphPlaceListCallback val$callback;
    
    Request$3(final Request$GraphPlaceListCallback val$callback) {
        this.val$callback = val$callback;
    }
    
    @Override
    public void onCompleted(final Response response) {
        if (this.val$callback != null) {
            this.val$callback.onCompleted(typedListFromResponse(response, (Class<GraphObject>)GraphPlace.class), response);
        }
    }
}
