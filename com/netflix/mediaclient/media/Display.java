// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media;

public class Display
{
    private int height;
    private int width;
    private int x;
    private int y;
    
    public Display(final int width, final int height, final int x, final int y) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }
    
    public static boolean isDisplayUpdated(final Display display, final int n, final int n2, final int n3, final int n4) {
        return display == null || n2 != display.height || n3 != display.x || n4 != display.y || n != display.width;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null) {
                return false;
            }
            if (!(o instanceof Display)) {
                return false;
            }
            final Display display = (Display)o;
            if (this.height != display.height) {
                return false;
            }
            if (this.x != display.x) {
                return false;
            }
            if (this.y != display.y) {
                return false;
            }
            if (this.width != display.width) {
                return false;
            }
        }
        return true;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    @Override
    public int hashCode() {
        return (((this.height + 31) * 31 + this.x) * 31 + this.y) * 31 + this.width;
    }
    
    @Override
    public String toString() {
        return "Display [height=" + this.height + ", width=" + this.width + ", x=" + this.x + ", y=" + this.y + "]";
    }
}
