import java.util.*;

class CircularBuffer {
    private int[] buffer;
    private int size;
    private int head;
    private int tail;
    private int count;

    public CircularBuffer(int size) {
        this.size = size;
        buffer = new int[size];
        head = 0;
        tail = 0;
        count = 0;
    }

    public void insert(int value) {
        buffer[tail] = value;
        tail = (tail + 1) % size;
        if (count == size) {
            head = (head + 1) % size;
        } else {
            count++;
        }
    }

    public int[] getBuffer() {
        int[] result = new int[count];
        for (int i = 0; i < count; i++) {
            result[i] = buffer[(head + i) % size];
        }
        return result;
    }
}

class CircularBufferDemo {
    public static void main(String[] args) {
        CircularBuffer buffer = new CircularBuffer(3);
        buffer.insert(1);
        buffer.insert(2);
        buffer.insert(3);
        System.out.println("Buffer: " + Arrays.toString(buffer.getBuffer()));
        buffer.insert(4);
        System.out.println("Buffer after overflow: " + Arrays.toString(buffer.getBuffer()));
    }
}