package com.samabcde;

import static com.samabcde.Eternity2Setup.BORDER_COLOR_CODE;

public record Color(char code) {
    public boolean isBorder() {
        return code == BORDER_COLOR_CODE;
    }

    public boolean isInterior() {
        return !isBorder();
    }

    @Override
    public String toString() {
        return String.valueOf(code);
    }
}
