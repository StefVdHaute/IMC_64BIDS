package round1.expiry;

public class TimeValue {
    public double calculateTime(int days, int hours, int minutes) {
        return ((double) days + ((double) hours + (double) minutes / 60) / 24) / 365;
    }
}
