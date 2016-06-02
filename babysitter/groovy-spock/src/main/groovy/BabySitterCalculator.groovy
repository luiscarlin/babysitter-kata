class BabySitterCalculator {

    public void setTime(int time) {
        if (time < 17 || time > 24) {
            throw new GroovyRuntimeException("Invalid Start Time")
        }
    }
}
