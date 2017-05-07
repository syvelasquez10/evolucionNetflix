// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import com.facebook.model.GraphObjectList;
import com.facebook.model.GraphObject$Factory;
import com.facebook.model.GraphObject;
import com.facebook.FacebookGraphObjectException;
import com.facebook.model.OpenGraphObject;
import android.graphics.Bitmap;
import java.util.Collection;
import java.io.File;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import java.util.Iterator;
import org.json.JSONException;
import com.facebook.FacebookException;
import org.json.JSONObject;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import android.app.Activity;
import com.facebook.model.OpenGraphAction;

abstract class FacebookDialog$OpenGraphDialogBuilderBase<CONCRETE extends FacebookDialog$OpenGraphDialogBuilderBase<?>> extends FacebookDialog$Builder<CONCRETE>
{
    private OpenGraphAction action;
    private String actionType;
    private boolean dataErrorsFatal;
    private String previewPropertyName;
    
    public FacebookDialog$OpenGraphDialogBuilderBase(final Activity activity, final OpenGraphAction action, final String previewPropertyName) {
        super(activity);
        Validate.notNull(action, "action");
        Validate.notNullOrEmpty(action.getType(), "action.getType()");
        Validate.notNullOrEmpty(previewPropertyName, "previewPropertyName");
        if (action.getProperty(previewPropertyName) == null) {
            throw new IllegalArgumentException("A property named \"" + previewPropertyName + "\" was not found on the action.  The name of " + "the preview property must match the name of an action property.");
        }
        this.action = action;
        this.actionType = action.getType();
        this.previewPropertyName = previewPropertyName;
    }
    
    public FacebookDialog$OpenGraphDialogBuilderBase(final Activity activity, final OpenGraphAction action, final String actionType, final String previewPropertyName) {
        super(activity);
        Validate.notNull(action, "action");
        Validate.notNullOrEmpty(actionType, "actionType");
        Validate.notNullOrEmpty(previewPropertyName, "previewPropertyName");
        if (action.getProperty(previewPropertyName) == null) {
            throw new IllegalArgumentException("A property named \"" + previewPropertyName + "\" was not found on the action.  The name of " + "the preview property must match the name of an action property.");
        }
        final String type = action.getType();
        if (!Utility.isNullOrEmpty(type) && !type.equals(actionType)) {
            throw new IllegalArgumentException("'actionType' must match the type of 'action' if it is specified. Consider using OpenGraphDialogBuilderBase(Activity activity, OpenGraphAction action, String previewPropertyName) instead.");
        }
        this.action = action;
        this.actionType = actionType;
        this.previewPropertyName = previewPropertyName;
    }
    
    private JSONObject flattenChildrenOfGraphObject(JSONObject jsonObject) {
        try {
            jsonObject = new JSONObject(jsonObject.toString());
            final Iterator keys = jsonObject.keys();
            while (keys.hasNext()) {
                final String s = keys.next();
                if (!s.equalsIgnoreCase("image")) {
                    jsonObject.put(s, this.flattenObject(jsonObject.get(s)));
                }
            }
        }
        catch (JSONException ex) {
            throw new FacebookException((Throwable)ex);
        }
        return jsonObject;
    }
    
    private Object flattenObject(final Object o) {
        Object o2;
        if (o == null) {
            o2 = null;
        }
        else if (o instanceof JSONObject) {
            final JSONObject jsonObject = (JSONObject)o;
            o2 = o;
            if (!jsonObject.optBoolean("fbsdk:create_object")) {
                if (jsonObject.has("id")) {
                    return jsonObject.getString("id");
                }
                o2 = o;
                if (jsonObject.has("url")) {
                    return jsonObject.getString("url");
                }
            }
        }
        else {
            o2 = o;
            if (o instanceof JSONArray) {
                final JSONArray jsonArray = (JSONArray)o;
                final JSONArray jsonArray2 = new JSONArray();
                for (int length = jsonArray.length(), i = 0; i < length; ++i) {
                    jsonArray2.put(this.flattenObject(jsonArray.get(i)));
                }
                return jsonArray2;
            }
        }
        return o2;
    }
    
    private void updateActionAttachmentUrls(final List<String> list, final boolean b) {
        List<JSONObject> image = this.action.getImage();
        if (image == null) {
            image = new ArrayList<JSONObject>(list.size());
        }
        for (final String s : list) {
            final JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("url", (Object)s);
                if (b) {
                    jsonObject.put("user_generated", true);
                }
                image.add(jsonObject);
                continue;
            }
            catch (JSONException ex) {
                throw new FacebookException("Unable to attach images", (Throwable)ex);
            }
            break;
        }
        this.action.setImage(image);
    }
    
    @Override
    protected Bundle getMethodArguments() {
        final Bundle bundle = new Bundle();
        this.putExtra(bundle, "PREVIEW_PROPERTY_NAME", this.previewPropertyName);
        this.putExtra(bundle, "ACTION_TYPE", this.actionType);
        bundle.putBoolean("DATA_FAILURES_FATAL", this.dataErrorsFatal);
        this.putExtra(bundle, "ACTION", this.flattenChildrenOfGraphObject(this.action.getInnerJSONObject()).toString());
        return bundle;
    }
    
    @Override
    protected Bundle setBundleExtras(final Bundle bundle) {
        this.putExtra(bundle, "com.facebook.platform.extra.PREVIEW_PROPERTY_NAME", this.previewPropertyName);
        this.putExtra(bundle, "com.facebook.platform.extra.ACTION_TYPE", this.actionType);
        bundle.putBoolean("com.facebook.platform.extra.DATA_FAILURES_FATAL", this.dataErrorsFatal);
        this.putExtra(bundle, "com.facebook.platform.extra.ACTION", this.flattenChildrenOfGraphObject(this.action.getInnerJSONObject()).toString());
        return bundle;
    }
    
    public CONCRETE setDataErrorsFatal(final boolean dataErrorsFatal) {
        this.dataErrorsFatal = dataErrorsFatal;
        return (CONCRETE)this;
    }
    
    public CONCRETE setImageAttachmentFilesForAction(final List<File> list) {
        return this.setImageAttachmentFilesForAction(list, false);
    }
    
    public CONCRETE setImageAttachmentFilesForAction(final List<File> list, final boolean b) {
        Validate.containsNoNulls((Collection<Object>)list, "bitmapFiles");
        if (this.action == null) {
            throw new FacebookException("Can not set attachments prior to setting action.");
        }
        this.updateActionAttachmentUrls(this.addImageAttachmentFiles(list), b);
        return (CONCRETE)this;
    }
    
    public CONCRETE setImageAttachmentFilesForObject(final String s, final List<File> list) {
        return this.setImageAttachmentFilesForObject(s, list, false);
    }
    
    public CONCRETE setImageAttachmentFilesForObject(final String s, final List<File> list, final boolean b) {
        Validate.notNull(s, "objectProperty");
        Validate.containsNoNulls((Collection<Object>)list, "bitmapFiles");
        if (this.action == null) {
            throw new FacebookException("Can not set attachments prior to setting action.");
        }
        this.updateObjectAttachmentUrls(s, this.addImageAttachmentFiles(list), b);
        return (CONCRETE)this;
    }
    
    public CONCRETE setImageAttachmentsForAction(final List<Bitmap> list) {
        return this.setImageAttachmentsForAction(list, false);
    }
    
    public CONCRETE setImageAttachmentsForAction(final List<Bitmap> list, final boolean b) {
        Validate.containsNoNulls((Collection<Object>)list, "bitmaps");
        if (this.action == null) {
            throw new FacebookException("Can not set attachments prior to setting action.");
        }
        this.updateActionAttachmentUrls(this.addImageAttachments(list), b);
        return (CONCRETE)this;
    }
    
    public CONCRETE setImageAttachmentsForObject(final String s, final List<Bitmap> list) {
        return this.setImageAttachmentsForObject(s, list, false);
    }
    
    public CONCRETE setImageAttachmentsForObject(final String s, final List<Bitmap> list, final boolean b) {
        Validate.notNull(s, "objectProperty");
        Validate.containsNoNulls((Collection<Object>)list, "bitmaps");
        if (this.action == null) {
            throw new FacebookException("Can not set attachments prior to setting action.");
        }
        this.updateObjectAttachmentUrls(s, this.addImageAttachments(list), b);
        return (CONCRETE)this;
    }
    
    void updateObjectAttachmentUrls(final String s, final List<String> list, final boolean b) {
        OpenGraphObject openGraphObject;
        try {
            openGraphObject = this.action.getPropertyAs(s, OpenGraphObject.class);
            if (openGraphObject == null) {
                throw new IllegalArgumentException("Action does not contain a property '" + s + "'");
            }
        }
        catch (FacebookGraphObjectException ex) {
            throw new IllegalArgumentException("Property '" + s + "' is not a graph object");
        }
        if (!openGraphObject.getCreateObject()) {
            throw new IllegalArgumentException("The Open Graph object in '" + s + "' is not marked for creation");
        }
        GraphObjectList<GraphObject> image = openGraphObject.getImage();
        if (image == null) {
            image = GraphObject$Factory.createList(GraphObject.class);
        }
        for (final String s2 : list) {
            final GraphObject create = GraphObject$Factory.create();
            create.setProperty("url", s2);
            if (b) {
                create.setProperty("user_generated", true);
            }
            image.add(create);
        }
        openGraphObject.setImage(image);
    }
}
