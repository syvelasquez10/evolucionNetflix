// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.branches;

public class Video$Bookmark
{
    private int bookmarkPosition;
    private long lastModified;
    
    public void deepCopy(final Video$Bookmark video$Bookmark) {
        this.bookmarkPosition = video$Bookmark.bookmarkPosition;
        this.lastModified = video$Bookmark.lastModified;
    }
    
    public int getBookmarkPosition() {
        return this.bookmarkPosition;
    }
    
    public long getLastModified() {
        return this.lastModified;
    }
    
    public void setBookmarkPosition(final int bookmarkPosition) {
        this.bookmarkPosition = bookmarkPosition;
    }
    
    public void setLastModified(final long lastModified) {
        this.lastModified = lastModified;
    }
    
    @Override
    public String toString() {
        return "Bookmark [bookmarkPosition=" + this.bookmarkPosition + ", lastModified=" + this.lastModified + "]";
    }
}
