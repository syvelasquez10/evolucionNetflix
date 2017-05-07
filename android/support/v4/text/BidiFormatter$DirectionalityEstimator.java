// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.text;

class BidiFormatter$DirectionalityEstimator
{
    private static final byte[] DIR_TYPE_CACHE;
    private static final int DIR_TYPE_CACHE_SIZE = 1792;
    private int charIndex;
    private final boolean isHtml;
    private char lastChar;
    private final int length;
    private final String text;
    
    static {
        DIR_TYPE_CACHE = new byte[1792];
        for (int i = 0; i < 1792; ++i) {
            BidiFormatter$DirectionalityEstimator.DIR_TYPE_CACHE[i] = Character.getDirectionality(i);
        }
    }
    
    BidiFormatter$DirectionalityEstimator(final String text, final boolean isHtml) {
        this.text = text;
        this.isHtml = isHtml;
        this.length = text.length();
    }
    
    private static byte getCachedDirectionality(final char c) {
        if (c < '\u0700') {
            return BidiFormatter$DirectionalityEstimator.DIR_TYPE_CACHE[c];
        }
        return Character.getDirectionality(c);
    }
    
    private byte skipEntityBackward() {
        final int charIndex = this.charIndex;
        while (this.charIndex > 0) {
            final String text = this.text;
            final int charIndex2 = this.charIndex - 1;
            this.charIndex = charIndex2;
            this.lastChar = text.charAt(charIndex2);
            if (this.lastChar == '&') {
                return 12;
            }
            if (this.lastChar == ';') {
                break;
            }
        }
        this.charIndex = charIndex;
        this.lastChar = ';';
        return 13;
    }
    
    private byte skipEntityForward() {
        while (this.charIndex < this.length && (this.lastChar = this.text.charAt(this.charIndex++)) != ';') {}
        return 12;
    }
    
    private byte skipTagBackward() {
        final int charIndex = this.charIndex;
        while (this.charIndex > 0) {
            final String text = this.text;
            final int charIndex2 = this.charIndex - 1;
            this.charIndex = charIndex2;
            this.lastChar = text.charAt(charIndex2);
            if (this.lastChar == '<') {
                return 12;
            }
            if (this.lastChar == '>') {
                break;
            }
            if (this.lastChar != '\"' && this.lastChar != '\'') {
                continue;
            }
            final char lastChar = this.lastChar;
            while (this.charIndex > 0) {
                final String text2 = this.text;
                final int charIndex3 = this.charIndex - 1;
                this.charIndex = charIndex3;
                if ((this.lastChar = text2.charAt(charIndex3)) != lastChar) {
                    continue;
                }
                break;
            }
        }
        this.charIndex = charIndex;
        this.lastChar = '>';
        return 13;
    }
    
    private byte skipTagForward() {
        final int charIndex = this.charIndex;
        while (this.charIndex < this.length) {
            this.lastChar = this.text.charAt(this.charIndex++);
            if (this.lastChar == '>') {
                return 12;
            }
            if (this.lastChar != '\"' && this.lastChar != '\'') {
                continue;
            }
            final char lastChar = this.lastChar;
            while (this.charIndex < this.length && (this.lastChar = this.text.charAt(this.charIndex++)) != lastChar) {}
        }
        this.charIndex = charIndex;
        this.lastChar = '<';
        return 13;
    }
    
    byte dirTypeBackward() {
        this.lastChar = this.text.charAt(this.charIndex - 1);
        byte b;
        if (Character.isLowSurrogate(this.lastChar)) {
            final int codePointBefore = Character.codePointBefore(this.text, this.charIndex);
            this.charIndex -= Character.charCount(codePointBefore);
            b = Character.getDirectionality(codePointBefore);
        }
        else {
            --this.charIndex;
            b = getCachedDirectionality(this.lastChar);
            if (this.isHtml) {
                if (this.lastChar == '>') {
                    return this.skipTagBackward();
                }
                b = b;
                if (this.lastChar == ';') {
                    return this.skipEntityBackward();
                }
            }
        }
        return b;
    }
    
    byte dirTypeForward() {
        this.lastChar = this.text.charAt(this.charIndex);
        byte b;
        if (Character.isHighSurrogate(this.lastChar)) {
            final int codePoint = Character.codePointAt(this.text, this.charIndex);
            this.charIndex += Character.charCount(codePoint);
            b = Character.getDirectionality(codePoint);
        }
        else {
            ++this.charIndex;
            b = getCachedDirectionality(this.lastChar);
            if (this.isHtml) {
                if (this.lastChar == '<') {
                    return this.skipTagForward();
                }
                b = b;
                if (this.lastChar == '&') {
                    return this.skipEntityForward();
                }
            }
        }
        return b;
    }
    
    int getEntryDir() {
        this.charIndex = 0;
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        while (this.charIndex < this.length && n == 0) {
            switch (this.dirTypeForward()) {
                case 18: {
                    --n3;
                    n2 = 0;
                    continue;
                }
                case 16:
                case 17: {
                    ++n3;
                    n2 = 1;
                    continue;
                }
                case 14:
                case 15: {
                    ++n3;
                    n2 = -1;
                }
                case 9: {
                    continue;
                }
                default: {
                    n = n3;
                    continue;
                }
                case 0: {
                    if (n3 == 0) {
                        return -1;
                    }
                    n = n3;
                    continue;
                }
                case 1:
                case 2: {
                    if (n3 == 0) {
                        return 1;
                    }
                    n = n3;
                    continue;
                }
            }
        }
        if (n == 0) {
            return 0;
        }
        if (n2 != 0) {
            return n2;
        }
        while (this.charIndex > 0) {
            switch (this.dirTypeBackward()) {
                default: {
                    continue;
                }
                case 14:
                case 15: {
                    if (n != n3) {
                        --n3;
                        continue;
                    }
                    return -1;
                }
                case 16:
                case 17: {
                    if (n == n3) {
                        return 1;
                    }
                    --n3;
                    continue;
                }
                case 18: {
                    ++n3;
                    continue;
                }
            }
        }
        return 0;
    }
    
    int getExitDir() {
        final boolean b = false;
        this.charIndex = this.length;
        int n = 0;
        int n2 = 0;
        int n3 = 0;
    Label_0135:
        while (true) {
            n3 = (b ? 1 : 0);
            if (this.charIndex <= 0) {
                break;
            }
            switch (this.dirTypeBackward()) {
                case 18: {
                    ++n2;
                }
                case 9: {
                    continue;
                }
                default: {
                    if (n == 0) {
                        n = n2;
                        continue;
                    }
                    continue;
                }
                case 0: {
                    if (n2 == 0) {
                        n3 = -1;
                        break Label_0135;
                    }
                    if (n == 0) {
                        n = n2;
                        continue;
                    }
                    continue;
                }
                case 14:
                case 15: {
                    if (n == n2) {
                        return -1;
                    }
                    --n2;
                    continue;
                }
                case 1:
                case 2: {
                    if (n2 == 0) {
                        return 1;
                    }
                    if (n == 0) {
                        n = n2;
                        continue;
                    }
                    continue;
                }
                case 16:
                case 17: {
                    if (n == n2) {
                        return 1;
                    }
                    --n2;
                    continue;
                }
            }
        }
        return n3;
    }
}
