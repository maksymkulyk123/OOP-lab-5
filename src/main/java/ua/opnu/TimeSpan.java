package ua.opnu;

public class TimeSpan {
    private int hours;
    private int minutes;

    public TimeSpan() {
        this(0, 0);
    }

    public TimeSpan(int minutes) {
        this(minutes / 60, minutes % 60);
    }

    public TimeSpan(int hours, int minutes) {
        checkTime(hours, minutes);
        this.hours = hours;
        this.minutes = minutes;
    }

    public TimeSpan(TimeSpan span) {
        this(span.getHours(), span.getMinutes());
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void add(int hours, int minutes) {
        checkTime(hours, minutes);
        int sumMinutes = this.minutes + minutes;
        int sumHours = Math.addExact(Math.addExact(this.hours, hours), sumMinutes / 60);
        this.hours = sumHours;
        this.minutes = sumMinutes % 60;
    }

    public void add(int minutes) {
        if (minutes < 0) {
            throw new IllegalArgumentException("Кількість хвилин не може бути від'ємною");
        }
        add(minutes / 60, minutes % 60);
    }

    public void add(TimeSpan span) {
        add(span.getHours(), span.getMinutes());
    }

    public void subtract(int hours, int minutes) {
        checkTime(hours, minutes);
        if (hours > this.hours || (hours == this.hours && minutes > this.minutes)) {
            return;
        }

        this.hours -= hours;
        this.minutes -= minutes;
        if (this.minutes < 0) {
            this.hours--;
            this.minutes += 60;
        }
    }

    public void subtract(int minutes) {
        if (minutes < 0) {
            throw new IllegalArgumentException("Кількість хвилин не може бути від'ємною");
        }
        subtract(minutes / 60, minutes % 60);
    }

    public void subtract(TimeSpan span) {
        subtract(span.getHours(), span.getMinutes());
    }

    public double getTotalHours() {
        return hours + minutes / 60.0;
    }

    public int getTotalMinutes() {
        return Math.addExact(Math.multiplyExact(hours, 60), minutes);
    }

    public void scale(int factor) {
        if (factor <= 0) {
            throw new IllegalArgumentException("Множник повинен бути більшим за нуль");
        }
        long scaledMinutes = (long) minutes * factor;
        long scaledHours = (long) hours * factor + scaledMinutes / 60;
        if (scaledHours > Integer.MAX_VALUE) {
            throw new ArithmeticException("Завеликий часовий інтервал");
        }
        hours = (int) scaledHours;
        minutes = (int) (scaledMinutes % 60);
    }

    private static void checkTime(int hours, int minutes) {
        if (hours < 0 || minutes < 0 || minutes > 59) {
            throw new IllegalArgumentException("Години мають бути невід'ємними, хвилини — від 0 до 59");
        }
    }

    @Override
    public String toString() {
        return hours + " год " + minutes + " хв";
    }
}
