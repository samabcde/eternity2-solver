package com.samabcde;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class ColorTest {
    @Test
    public void WhenCodeIs_a_ThenIsBorder() {
        assertTrue(new Color('a').isBorder());
        assertFalse(new Color('a').isInterior());
    }

    @Test
    public void WhenCodeIsNot_a_ThenIsNotBorder() {
        assertFalse(new Color('b').isBorder());
        assertTrue(new Color('b').isInterior());
    }
}