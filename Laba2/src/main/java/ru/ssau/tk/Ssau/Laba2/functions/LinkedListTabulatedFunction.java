package ru.ssau.tk.Ssau.Laba2.functions;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction {
    private Node head;

    protected static class Node {
        Node next;
        Node prev;
        double x;
        double y;
    }

    public void addNode(double x, double y) {
        Node newNode = new Node();
        if (head == null) {
            head = newNode;
            newNode.x = x;
            newNode.y = y;
            newNode.prev = newNode;
            newNode.next = newNode;
        } else {
            Node last = head.prev;
            head.prev = newNode;
            last.next = newNode;
            newNode.prev = last;
            newNode.next = head;
            newNode.x = x;
            newNode.y = y;
        }
        count += 1;
    }

    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        for (int i = 0; i < xValues.length; i++) {
            this.addNode(xValues[i], yValues[i]);
        }
    }

    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        double[] xValues = new double[count];
        xValues[0] = xFrom;
        final double step = (xTo - xFrom) / (count - 1);
        this.addNode(xValues[0], source.apply(xValues[0]));
        for (int i = 1; i < count; i++) {
            xValues[i] = xValues[i - 1] + step;
            this.addNode(xValues[i], source.apply(xValues[i]));
        }
    }

    public double apply(double x) {
        if (x < leftBound()) {
            return extrapolateLeft(x);
        }
        if (x > rightBound()) {
            return extrapolateRight(x);
        }
        if (indexOfX(x) != -1) {
            return getY(indexOfX(x));
        }
        return interpolate(x, indexOfX(floorNodeOfX(x).x));

    }

    public int getCount() {
        return count;
    }

    private Node getNode(int index) {
        Node first;
        if (index > (count / 2)) {
            first = head.prev;
            for (int i = count - 1; i > 0; i--) {
                if (i == index) {
                    return first;
                }
                first = first.prev;
            }
        }
        first = head;
        for (int i = 0; i < count; i++) {
            if (i == index) {
                return first;
            }
            first = first.next;
        }
        return null;
    }

    protected Node floorNodeOfX(double x) {
        Node indexNode = head;
        for (int i = 0; i < count; i++) {
            if (indexNode.x < x) {
                indexNode = indexNode.next;
            } else {
                if (i == 0) {
                    return indexNode;
                }
                return getNode(i);
            }
        }
        return getNode(getCount());
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
            //noinspection SuspiciousNameCombination
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
        if (getCount() == 1) {
            return head.y;
        }
        Node left = getNode(floorIndex);
        Node right = left.next;
        return interpolate(x, left.x, right.x, left.y, right.y);
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
        Node first;
        first = head;
        for (int i = 0; i < count; i++) {
            if (first.x == x) {
                return i;
            }
            first = first.next;
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        Node buff;
        buff = head;
        for (int i = 0; i < count; i++) {
            if (buff.y == y) {
                return i;
            }
            buff = buff.next;
        }
        return -1;
    }

    @Override
    public double leftBound() {
        return head.x;
    }

    @Override
    public double rightBound() {
        return head.prev.x;
    }
}
