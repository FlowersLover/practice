package ru.ssau.tk._KEPA_._practice_.functions;

import java.awt.*;
import java.util.Iterator;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction {
    @Override
    public Iterator<Point> iterator() {
        return null;
    }

    protected class Node {
        double x;
        double y;
        Node next;
        Node prev;
    }
    private Node head;
    private Node last;
    private void addNode(double x, double y){
        Node newNode = new Node();
        newNode.x = x;
        newNode.y = y;
        if (head == null) {
            head = newNode;
            newNode.prev =newNode;
            newNode.next = newNode;
        } else {
            last=head.prev;
            newNode.prev =last;
            newNode.next = head;
            head.prev = newNode;
            last.next = newNode;
        }
        last = newNode;
        count++;
    }
    private Node getNode(int index) {
        Node indexNode;
        if (index > (count / 2)) {
            indexNode = head.prev;
            for (int i = count - 1; i > 0; i--) {
                if (i == index) {
                    return indexNode;
                } else {
                    indexNode = indexNode.prev;
                }
            }
        }
        else {
            indexNode = head;
            for (int i = 0; i < count; i++) {
                if (index == i) {
                    return indexNode;
                } else {
                    indexNode = indexNode.next;
                }
            }
        }
        return null;
    }

    public void LinkedListTabulatedFunction(double[] xValues ,double[] yValues){
        this.count = xValues.length;
        for (int i = 0; i < count; i++) {
            addNode(xValues[i], yValues[i]);
        }
    }
    public void LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count){
        this.count = count;
        double step = (xTo - xFrom) / (count - 1);
        double first = xFrom;
        for (int i = 0; i < count; i++) {
            double temp = source.apply(first);
            this.addNode(first, temp);
            first += step;
        }
    }
    public double leftBound() {
        return head.x;
    }

    public double rightBound() {
        return last.x;
    }
    @Override
    public int getCount() {
        return count;
    }

    @Override
    public double getX(int index) {
        return getNode(index).x;
    }

    @Override
    public double getY(int index) {
        return getNode(index).y;
    }

    @Override
    public void setY(int index, double valueY) {
        getNode(index).y = valueY;
    }

    @Override
    public int indexOfX(double x) {
        Node indexNode = head;
        for (int i = 0; i < count; i++) {
            if (indexNode.x == x) {
                return i;
            }
            indexNode = indexNode.next;
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        Node indexNode = head;
        for (int i = 0; i < count; i++) {
            if (indexNode.y == y) {
                return i;
            }
            indexNode = indexNode.next;
        }
        return -1;
    }

    @Override
    protected int floorIndexOfX(double x) {
        Node indexNode = head;
        for (int i = 0; i < count; i++) {
            if (indexNode.x < x) {
                indexNode = indexNode.next;
            } else {
                if (i == 0) {
                    return 0;
                }
                return i - 1;
            }
        }
        return getCount();
    }

    @Override
    protected double extrapolateLeft(double x) {
        if (head.x == head.prev.x) {
            return head.y;
        }
        return interpolate(x, head.x, head.next.x, head.y, head.next.y);
    }

    @Override
    protected double extrapolateRight(double x) {
        if (head.x == head.prev.x) {
            //noinspection SuspiciousNameCombination
            return head.y;
        }
        return interpolate(x, head.prev.prev.x, head.prev.x, head.prev.prev.y, head.prev.y);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        if (head.x == head.prev.x) {
            return head.y;
        }
        Node leftNode = getNode(floorIndex);
        Node rightNode = leftNode.next;
        return interpolate(x, leftNode.x, rightNode.x, leftNode.y, rightNode.y);
    }
}

