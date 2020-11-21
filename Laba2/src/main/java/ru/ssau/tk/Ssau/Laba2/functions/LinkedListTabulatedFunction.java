package ru.ssau.tk.Ssau.Laba2.functions;

import ru.ssau.tk.Ssau.Laba2.exceptions.InterpolationException;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction implements Insertable, Removable, Serializable {

    private static final long serialVersionUID = 4099715325863317331L;
    private Node head;

    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        if (xValues.length < 2) {
            throw new IllegalArgumentException("Length less than 2 points");
        }
        checkLengthIsTheSame(xValues, yValues);
        checkSorted(xValues);
        for (int i = 0; i < xValues.length; i++) {
            this.addNode(xValues[i], yValues[i]);
        }
    }

    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (count < 2) {
            throw new IllegalArgumentException("Length less than 2 points");
        }
        if ((xFrom >= xTo) || (xFrom < 0) | (xTo < 0)) {
            throw new IllegalArgumentException("Incorrect parameter values");
        }
        double[] xValues = new double[count];
        xValues[0] = xFrom;
        final double step = (xTo - xFrom) / (count - 1);
        this.addNode(xValues[0], source.apply(xValues[0]));
        for (int i = 1; i < count; i++) {
            xValues[i] = xValues[i - 1] + step;
            this.addNode(xValues[i], source.apply(xValues[i]));
        }
    }

    @Override
    public void insert(double x, double y) {
        if (count == 0) {
            addNode(x, y);
        } else {
            if (indexOfX(x) != -1) {
                setY(indexOfX(x), y);
            } else {
                Node newNode = new Node();
                if (x < leftBound() || x > rightBound()) {
                    newNode.next = head;
                    newNode.prev = head.prev;
                    newNode.x = x;
                    newNode.y = y;
                    if (x < leftBound()) {
                        head = newNode;
                    } else {
                        head.prev = newNode;
                    }
                } else {
                    Node previous = getNode(floorIndexOfX(x));
                    newNode.next = previous.next;
                    newNode.prev = previous;
                    newNode.x = x;
                    newNode.y = y;
                    previous.next = newNode;
                }
                count++;
            }
        }
    }

    @Override
    public void remove(int index) {
        if (count > 2) {
            Node deletedNode = getNode(index);
            if (index == 0) {
                head = deletedNode.next;
                head.prev = deletedNode.prev;
            }
            deletedNode.prev.next = deletedNode.next;
            deletedNode.next.prev = deletedNode.prev;
            count--;
        } else {
            throw new IllegalArgumentException("Length less than 2 points");
        }
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
        checkIndex(index);
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
        throw new UnsupportedOperationException("");
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
        if (x < leftBound()) {
            throw new IllegalArgumentException("X is less than the left border");
        }
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
        return interpolate(x, head.x, head.next.x, head.y, head.next.y);
    }

    @Override
    protected double extrapolateRight(double x) {
        return interpolate(x, head.prev.prev.x, head.prev.x, head.prev.prev.y, head.prev.y);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        Node left = getNode(floorIndex);
        Node right = left.next;
        if (x < head.x || x > head.prev.x) {
            throw new InterpolationException("X is out of bounds of interpolation");
        }
        return interpolate(x, left.x, right.x, left.y, right.y);
    }

    @Override
    public double getX(int index) {
        checkIndex(index);
        return getNode(index).x;
    }

    @Override
    public double getY(int index) {
        checkIndex(index);
        return getNode(index).y;
    }

    @Override
    public void setY(int index, double valueY) {
        checkIndex(index);
        getNode(index).y = valueY;
    }

    @Override
    public int indexOfX(double x) {
        Node first = head;
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

    private void checkIndex(int index) {
        if (index < 0 || index > count - 1) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
    }

    protected static class Node implements Serializable {
        private static final long serialVersionUID = 1465232404893441735L;
        public Node next;
        public Node prev;
        public double x;
        public double y;
    }

    @Override
    public Iterator<Point> iterator() {
        return new Iterator<Point>() {
            Node node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public Point next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Point point = new Point(node.x, node.y);
                if (node == head.prev) {
                    node = null;
                } else {
                    node = node.next;
                }
                return point;
            }
        };
    }
}
