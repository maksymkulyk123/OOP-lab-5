package ua.opnu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeSpanTest {
    @Test
    void constructors() {
        TimeSpan empty = new TimeSpan();
        assertEquals(0, empty.getTotalMinutes());

        TimeSpan fromMinutes = new TimeSpan(150);
        assertEquals(2, fromMinutes.getHours());
        assertEquals(30, fromMinutes.getMinutes());

        TimeSpan original = new TimeSpan(2, 30);
        TimeSpan copy = new TimeSpan(original);
        original.add(10);
        assertEquals(150, copy.getTotalMinutes());
        assertEquals(160, original.getTotalMinutes());
    }

    @Test
    void addOverloads() {
        TimeSpan span = new TimeSpan(2, 15);
        span.add(1, 45);
        assertEquals(4, span.getHours());
        assertEquals(0, span.getMinutes());
        span.add(50);
        assertEquals(290, span.getTotalMinutes());
        span.add(new TimeSpan(1, 20));
        assertEquals(6, span.getHours());
        assertEquals(10, span.getMinutes());
    }

    @Test
    void subtractOverloads() {
        TimeSpan span = new TimeSpan(3, 10);
        span.subtract(50);
        assertEquals(2, span.getHours());
        assertEquals(20, span.getMinutes());
        span.subtract(1, 30);
        assertEquals(50, span.getTotalMinutes());
        span.subtract(new TimeSpan(20));
        assertEquals(30, span.getTotalMinutes());
        span.subtract(new TimeSpan(30));
        assertEquals(0, span.getTotalMinutes());
    }

    @Test
    void cannotSubtractMoreThanAvailable() {
        TimeSpan span = new TimeSpan(1, 20);
        span.subtract(81);
        span.subtract(1, 21);
        span.subtract(new TimeSpan(2, 0));
        assertEquals(80, span.getTotalMinutes());
    }

    @Test
    void totalDurationAndScale() {
        assertEquals(9.75, new TimeSpan(9, 45).getTotalHours(), 0.000001);
        TimeSpan span = new TimeSpan(1, 45);
        span.scale(2);
        assertEquals(3, span.getHours());
        assertEquals(30, span.getMinutes());
        assertEquals(210, span.getTotalMinutes());
        span.scale(1);
        assertEquals(210, span.getTotalMinutes());
        TimeSpan empty = new TimeSpan();
        empty.scale(5);
        assertEquals(0, empty.getTotalMinutes());
    }

    @Test
    void invalidArgumentsDoNotChangeState() {
        assertThrows(IllegalArgumentException.class, () -> new TimeSpan(-1));
        assertThrows(IllegalArgumentException.class, () -> new TimeSpan(-60));
        assertThrows(IllegalArgumentException.class, () -> new TimeSpan(-1, 0));
        assertThrows(IllegalArgumentException.class, () -> new TimeSpan(0, -1));
        assertThrows(IllegalArgumentException.class, () -> new TimeSpan(0, 60));

        TimeSpan span = new TimeSpan(1, 20);
        assertThrows(IllegalArgumentException.class, () -> span.add(-1));
        assertThrows(IllegalArgumentException.class, () -> span.add(0, 60));
        assertThrows(IllegalArgumentException.class, () -> span.subtract(-1));
        assertThrows(IllegalArgumentException.class, () -> span.subtract(-1, 0));
        assertThrows(IllegalArgumentException.class, () -> span.scale(0));
        assertThrows(IllegalArgumentException.class, () -> span.scale(-2));
        assertEquals(80, span.getTotalMinutes());
    }

    @Test
    void operationsOnTheSameObject() {
        TimeSpan span = new TimeSpan(1, 40);
        span.add(span);
        assertEquals(200, span.getTotalMinutes());
        span.subtract(span);
        assertEquals(0, span.getTotalMinutes());
    }

    @Test
    void overflowDoesNotMakeDurationNegative() {
        TimeSpan span = new TimeSpan(Integer.MAX_VALUE, 59);
        assertThrows(ArithmeticException.class, () -> span.add(1));
        assertThrows(ArithmeticException.class, () -> span.scale(2));
        assertThrows(ArithmeticException.class, span::getTotalMinutes);
        assertEquals(Integer.MAX_VALUE, span.getHours());
        assertEquals(59, span.getMinutes());
        assertEquals(Integer.MAX_VALUE, new TimeSpan(Integer.MAX_VALUE).getTotalMinutes());
    }
}
