package BusinessLogic;

public class PathObject {
	 private double axisX;
    private double axis;

    public PathObject(double axisX, double axis) {
        this.axisX = axisX;
        this.axis = axis;
    }

    public double getAxisX() {
        return axisX;
    }

    public void setAxisX(double axisX) {
        this.axisX = axisX;
    }

    public double getAxis() {
        return axis;
    }

    public void setAxis(double axis) {
        this.axis = axis;
    }
}
