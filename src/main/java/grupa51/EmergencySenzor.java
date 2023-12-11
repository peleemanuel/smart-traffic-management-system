package grupa51;

public class EmergencySenzor {
    private boolean emergency = false; // Initial state is not in emergency

    public boolean getStatus() {
        return emergency;
    }

    public void setStatus(boolean status) {
        emergency = status;
    }
}

