// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx.events;

import java.util.HashMap;
import java.util.Map;

public final class MdxEventHandlerFactory
{
    private Map<String, MdxEventHandler> handlers;
    
    public MdxEventHandlerFactory() {
        (this.handlers = new HashMap<String, MdxEventHandler>()).put("com.netflix.mediaclient.intent.action.MDXUPDATE_AUDIOSUB", new UpdateLanguageHandler());
        this.handlers.put("com.netflix.mediaclient.intent.action.MDXUPDATE_CAPABILITY", new UpdateCapabilityHandler());
        this.handlers.put("com.netflix.mediaclient.intent.action.MDXUPDATE_STATE", new UpdateStateHandler());
        this.handlers.put("com.netflix.mediaclient.intent.action.MDXUPDATE_DIALOGCANCEL", new DialogCancelHandler());
        this.handlers.put("com.netflix.mediaclient.intent.action.MDXUPDATE_DIALOGSHOW", new DialogShowHandler());
        this.handlers.put("com.netflix.mediaclient.intent.action.MDXUPDATE_ERROR", new ErrorHandler());
        this.handlers.put("com.netflix.mediaclient.intent.action.MDXUPDATE_MOVIEMETADATA_AVAILABLE", new UpdateVideoMetadataAvailableHandler());
        this.handlers.put("com.netflix.mediaclient.intent.action.MDXUPDATE_MOVIEMETADA", new UpdateVideoMetadataHandler());
        this.handlers.put("com.netflix.mediaclient.intent.action.MDXUPDATE_NOTREADY", new NotReadyHandler());
        this.handlers.put("com.netflix.mediaclient.intent.action.MDXUPDATE_READY", new ReadyHandler());
        this.handlers.put("com.netflix.mediaclient.intent.action.MDXUPDATE_TARGETLIST", new UpdateTargetListHandler());
    }
    
    public MdxEventHandler getHandler(final String s) {
        return this.handlers.get(s);
    }
}
