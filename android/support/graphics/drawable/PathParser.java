// 
// Decompiled by Procyon v0.5.30
// 

package android.support.graphics.drawable;

import java.util.ArrayList;

class PathParser
{
    private static void addNode(final ArrayList<PathParser$PathDataNode> list, final char c, final float[] array) {
        list.add(new PathParser$PathDataNode(c, array));
    }
    
    static float[] copyOfRange(final float[] array, final int n, int n2) {
        if (n > n2) {
            throw new IllegalArgumentException();
        }
        final int length = array.length;
        if (n < 0 || n > length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        n2 -= n;
        final int min = Math.min(n2, length - n);
        final float[] array2 = new float[n2];
        System.arraycopy(array, n, array2, 0, min);
        return array2;
    }
    
    public static PathParser$PathDataNode[] createNodesFromPathData(final String s) {
        if (s == null) {
            return null;
        }
        final ArrayList<PathParser$PathDataNode> list = new ArrayList<PathParser$PathDataNode>();
        int i = 1;
        int n = 0;
        while (i < s.length()) {
            final int nextStart = nextStart(s, i);
            final String trim = s.substring(n, nextStart).trim();
            if (trim.length() > 0) {
                addNode(list, trim.charAt(0), getFloats(trim));
            }
            final int n2 = nextStart + 1;
            n = nextStart;
            i = n2;
        }
        if (i - n == 1 && n < s.length()) {
            addNode(list, s.charAt(n), new float[0]);
        }
        return list.toArray(new PathParser$PathDataNode[list.size()]);
    }
    
    public static PathParser$PathDataNode[] deepCopyNodes(final PathParser$PathDataNode[] array) {
        if (array == null) {
            return null;
        }
        final PathParser$PathDataNode[] array2 = new PathParser$PathDataNode[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = new PathParser$PathDataNode(array[i]);
        }
        return array2;
    }
    
    private static void extract(final String s, final int n, final PathParser$ExtractFloatResult pathParser$ExtractFloatResult) {
        pathParser$ExtractFloatResult.mEndWithNegOrDot = false;
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        int i;
        for (i = n; i < s.length(); ++i) {
            Label_0090: {
                switch (s.charAt(i)) {
                    case ' ':
                    case ',': {
                        n2 = 0;
                        n4 = 1;
                        break Label_0090;
                    }
                    case '-': {
                        if (i != n && n2 == 0) {
                            pathParser$ExtractFloatResult.mEndWithNegOrDot = true;
                            n2 = 0;
                            n4 = 1;
                            break Label_0090;
                        }
                        break;
                    }
                    case '.': {
                        if (n3 == 0) {
                            n2 = 0;
                            n3 = 1;
                            break Label_0090;
                        }
                        pathParser$ExtractFloatResult.mEndWithNegOrDot = true;
                        n2 = 0;
                        n4 = 1;
                        break Label_0090;
                    }
                    case 'E':
                    case 'e': {
                        n2 = 1;
                        break Label_0090;
                    }
                }
                n2 = 0;
            }
            if (n4 != 0) {
                break;
            }
        }
        pathParser$ExtractFloatResult.mEndPosition = i;
    }
    
    private static float[] getFloats(final String s) {
        boolean b;
        if (s.charAt(0) == 'z') {
            b = true;
        }
        else {
            b = false;
        }
        boolean b2;
        if (s.charAt(0) == 'Z') {
            b2 = true;
        }
        else {
            b2 = false;
        }
        if (b | b2) {
            return new float[0];
        }
        while (true) {
        Label_0116_Outer:
            while (true) {
                int mEndPosition = 0;
            Label_0180:
                while (true) {
                    Label_0177: {
                        try {
                            final float[] array = new float[s.length()];
                            final PathParser$ExtractFloatResult pathParser$ExtractFloatResult = new PathParser$ExtractFloatResult();
                            final int length = s.length();
                            int n = 0;
                            int i = 1;
                            while (i < length) {
                                extract(s, i, pathParser$ExtractFloatResult);
                                mEndPosition = pathParser$ExtractFloatResult.mEndPosition;
                                if (i >= mEndPosition) {
                                    break Label_0177;
                                }
                                final int n2 = n + 1;
                                array[n] = Float.parseFloat(s.substring(i, mEndPosition));
                                n = n2;
                                if (!pathParser$ExtractFloatResult.mEndWithNegOrDot) {
                                    break Label_0180;
                                }
                                i = mEndPosition;
                            }
                            return copyOfRange(array, 0, n);
                        }
                        catch (NumberFormatException ex) {
                            throw new RuntimeException("error in parsing \"" + s + "\"", ex);
                        }
                    }
                    continue;
                }
                int i = mEndPosition + 1;
                continue Label_0116_Outer;
            }
        }
    }
    
    private static int nextStart(final String s, int i) {
        while (i < s.length()) {
            final char char1 = s.charAt(i);
            if (((char1 - 'A') * (char1 - 'Z') <= '\0' || (char1 - 'a') * (char1 - 'z') <= '\0') && char1 != 'e' && char1 != 'E') {
                break;
            }
            ++i;
        }
        return i;
    }
}
