package BusinessLogic;

public class Weapon {
	private int thickness;
    private int polygonPoints;
    private int laneAmount;
    private byte laneAmountByteRepresentation;
    private byte thicknessByteRepresentacion;
    private byte polygonPointsByteRepresentacion;
    private int[] color; //RGB

    public Weapon(byte pThicknessByteRepresentacion, byte pPolygonPointsByteRepresentacion, 
            byte pLaneAmountByteRepresentation, int[] pColor) {
        this.thicknessByteRepresentacion = pThicknessByteRepresentacion;
        this.polygonPointsByteRepresentacion = pPolygonPointsByteRepresentacion;
        this.color = pColor;
        this.laneAmountByteRepresentation = pLaneAmountByteRepresentation;

        setLaneAmount();
        setThickness();
        setPolygonPoints();
    }

    public int getThickness() {
        return thickness;
    }

    public void setThickness() {
        this.thickness = ((this.thicknessByteRepresentacion & (0xff))/17)+1; //17 is rank (255/17 = 15)
        if (this.thickness > 15)
            this.thickness = 15;
    }

    public int getPolygonPoints() {
        return polygonPoints;
    }

    public void setPolygonPoints() {
        this.polygonPoints = ((this.polygonPointsByteRepresentacion & (0xff))/85)+3; //85 is rank (255/85 = 15)
        /*
         * The three next are for the possibility of exceed the maximum 5 polygon points
         * that's why they have a low rank of 10.
        */
        if ((this.polygonPointsByteRepresentacion & (0xff)) >= 245)
            polygonPoints += 3;
        else if ((this.polygonPointsByteRepresentacion & (0xff)) >= 234)
            polygonPoints += 2;
        else if ((this.polygonPointsByteRepresentacion & (0xff)) >= 223)
            polygonPoints += 1;
    }

    public int getLaneAmount() {
        return laneAmount;
    }

    public void setLaneAmount() {
        this.laneAmount = ((this.laneAmountByteRepresentation & (0xff))/85)+1; // 85 is rank (255/85 = 3)
        if (this.thickness > 3)
            this.thickness = 3;
    }

    public int[] getColor() {
        return color;
    }

    public void setColor(int[] color) {
        this.color = color;
    }

    public byte getLaneAmountByteRepresentation() {
        return laneAmountByteRepresentation;
    }

    public void setLaneAmountByteRepresentation(byte laneAmountByteRepresentation) {
        this.laneAmountByteRepresentation = laneAmountByteRepresentation;
    }

    public byte getThicknessByteRepresentacion() {
        return thicknessByteRepresentacion;
    }

    public void setThicknessByteRepresentacion(byte thicknessByteRepresentacion) {
        this.thicknessByteRepresentacion = thicknessByteRepresentacion;
    }

    public byte getPolygonPointsByteRepresentacion() {
        return polygonPointsByteRepresentacion;
    }

    public void setPolygonPointsByteRepresentacion(byte polygonPointsByteRepresentacion) {
        this.polygonPointsByteRepresentacion = polygonPointsByteRepresentacion;
    }
  
    @Override
    public String toString(){
        return ("\nThickness: "+thickness+
                "\nPolygonPoints: "+polygonPoints+
                "\nLaneAmount: "+laneAmount+
                "\nColorR: "+color[0]+
                "\nColorG: "+color[1]+
                "\nColorB: "+color[2]);
    }
}
