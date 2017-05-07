// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import com.facebook.model.GraphMultiResult;
import android.util.Pair;
import java.util.HashMap;
import java.io.OutputStream;
import org.json.JSONArray;
import org.json.JSONObject;
import com.facebook.model.GraphObjectList;
import java.util.Map;
import java.text.SimpleDateFormat;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.ArrayList;
import com.facebook.model.GraphUser;
import com.facebook.model.OpenGraphObject$Factory;
import com.facebook.model.OpenGraphObject;
import com.facebook.model.OpenGraphAction;
import com.facebook.internal.AttributionIdentifiers;
import android.content.Context;
import java.util.Date;
import android.os.ParcelFileDescriptor;
import java.util.regex.Matcher;
import java.io.File;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Handler;
import java.util.HashSet;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Collection;
import com.facebook.internal.Validate;
import java.util.Locale;
import java.net.HttpURLConnection;
import java.util.Iterator;
import android.net.Uri$Builder;
import android.util.Log;
import com.facebook.internal.Utility;
import com.facebook.internal.Logger;
import java.net.URL;
import com.facebook.internal.ServerProtocol;
import android.os.Bundle;
import com.facebook.model.GraphObject;
import java.util.regex.Pattern;
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
