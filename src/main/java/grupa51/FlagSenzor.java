package grupa51;

public class FlagSenzor {
    private boolean carDetected;

    public FlagSenzor(boolean carDetected) {
        this.carDetected = carDetected;
    }
    //getter

    public boolean getStatus(){
        return carDetected;
    }
    //setter
    public void setStatus(boolean carDetected){
        this.carDetected=carDetected;
    }


}
