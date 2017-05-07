// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import android.os.Bundle;
import com.facebook.Session;
import android.content.Context;

public class WebDialog$FeedDialogBuilder extends WebDialog$BuilderBase<WebDialog$FeedDialogBuilder>
{
    private static final String CAPTION_PARAM = "caption";
    private static final String DESCRIPTION_PARAM = "description";
    private static final String FEED_DIALOG = "feed";
    private static final String FROM_PARAM = "from";
    private static final String LINK_PARAM = "link";
    private static final String NAME_PARAM = "name";
    private static final String PICTURE_PARAM = "picture";
    private static final String SOURCE_PARAM = "source";
    private static final String TO_PARAM = "to";
    
    public WebDialog$FeedDialogBuilder(final Context context) {
        super(context, "feed");
    }
    
    public WebDialog$FeedDialogBuilder(final Context context, final Session session) {
        super(context, session, "feed", null);
    }
    
    public WebDialog$FeedDialogBuilder(final Context context, final Session session, final Bundle bundle) {
        super(context, session, "feed", bundle);
    }
    
    public WebDialog$FeedDialogBuilder(final Context context, final String s, final Bundle bundle) {
        super(context, s, "feed", bundle);
    }
    
    public WebDialog$FeedDialogBuilder setCaption(final String s) {
        this.getParameters().putString("caption", s);
        return this;
    }
    
    public WebDialog$FeedDialogBuilder setDescription(final String s) {
        this.getParameters().putString("description", s);
        return this;
    }
    
    public WebDialog$FeedDialogBuilder setFrom(final String s) {
        this.getParameters().putString("from", s);
        return this;
    }
    
    public WebDialog$FeedDialogBuilder setLink(final String s) {
        this.getParameters().putString("link", s);
        return this;
    }
    
    public WebDialog$FeedDialogBuilder setName(final String s) {
        this.getParameters().putString("name", s);
        return this;
    }
    
    public WebDialog$FeedDialogBuilder setPicture(final String s) {
        this.getParameters().putString("picture", s);
        return this;
    }
    
    public WebDialog$FeedDialogBuilder setSource(final String s) {
        this.getParameters().putString("source", s);
        return this;
    }
    
    public WebDialog$FeedDialogBuilder setTo(final String s) {
        this.getParameters().putString("to", s);
        return this;
    }
}
