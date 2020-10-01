package ru.ssau.tk._KEPA_._practice_.functions;

import java.util.Arrays;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction implements TabulatedFunction {
    private int count;
    private double[] xValues;
    private double[] yValues;

    public void AbstractTabulatedFunction(double[] xValues,double[] yValues){
        count=xValues.length;
        this.xValues=Arrays.copyOf(xValues,count);
        this.yValues=Arrays.copyOf(yValues,count);
    }
    public void AbstractTabulatedFunction(MathFunction source, double xFrom, double xTo, int count){
        this.count = count;
        xValues = new double[count];
        yValues = new double[count];
        xValues[0]=xFrom;
        xValues[count] = xTo;
        for (int i = 1; i < count; i++) {
            xValues[i] = i * (xTo - xFrom) / (count-1);
        }
        for (int i = 0; i < count; i++) {
            yValues[i] = source.apply(xValues[i]);
        }

    }
    @Override
    public int getCount(){
         return count;
    }
    @Override
    public double getX(int index){
        return xValues[index];
    }
    @Override
    public double getY(int index){
        return yValues[index];
    }
    @Override
    public void setY(int index, double value){
        yValues[index] = value;
    }
    @Override
    public double leftBound(){
        return xValues[0];
    }
    @Override
    public double rightBound(){
        return xValues[count];
    }
    protected double interpolate(double x, int floorIndex) {
        return yValues[floorIndex] + (yValues[floorIndex + 1] - yValues[floorIndex]) * (x - xValues[floorIndex]) / (xValues[floorIndex + 1] - xValues[floorIndex]);
    }
    @Override
    public double indexOfX(double x){
        for (int i=0; i<count; i++ ) {
            if (x == xValues[i]) {
                return i;
            }
        }
            return -1;
    }
    @Override
    public int indexOfY(double y ){
        for (int i=0; i<count; i++ ){
            if (y == yValues[i]) {
                return i;
            }
        }
        return -1;
    }

    protected int floorIndexOfX(double x){
        for (int i=0; i<count; i++ ) {
            if (x == xValues[i]) {
                return i;
            }
        }
            int k = 0;
            int j = 0;
            while (x > xValues[k]) {
                j = k;
                k++;
            }
            return j;


    }
    protected double extrapolateLeft(double x){
        return yValues[0]+(yValues[1]-yValues[0])*(x-xValues[0])/(xValues[1]-xValues[0]);
    }

    protected double extrapolateRight(double x) {
        return yValues[count - 1] + (yValues[count] - yValues[count - 1]) * (x - xValues[count - 1]) / (xValues[count] - xValues[count - 1]);
    }
}
