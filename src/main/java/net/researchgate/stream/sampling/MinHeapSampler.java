package net.researchgate.stream.sampling;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @author Abhilash Krishnan
 * @since 07/04/17.
 *
 * Random Sampling implementation using MinHeap.
 */
public class MinHeapSampler extends Sampler{

    /**
     * Heap array
     */
    private int mH[];
    /**
     * Pointer to track the position of heap array
     */
    private int position;
    /**
     * Random generator
     */
    final Random rand = new SecureRandom();
    /**
     * Counter used in the generation of random value
     */
    int c = 0;

    public MinHeapSampler(int size) {
        super(size);
        mH = new int[size + 1];
        position = 0;
    }

    /**
     * Create and initialize heap with dummy values
     *
     */
    public void createHeap() {

        for (int i=0; i < size; i++) {
            insert(i);
        }
    }

    /**
     * Insert value into heap array
     * @param x The value to be inserted
     */
    public void insert(int x) {
        if (position == 0) {
            mH[position + 1] = x;
            position = 2;
        } else {
            mH[position++] = x;
            bubbleUp();
        }
    }

    /**
     * Bubble up the value after insertion into heap
     */
    public void bubbleUp() {
        int pos = position - 1;

        while(pos > 0 && mH[pos/2] > mH[pos]) {
            int y = mH[pos];
            mH[pos] = mH[pos/2];
            mH[pos/2] = y;
            pos = pos/2;
        }
    }

    /**
     * Sink down the value after deletion from heap
     * @param k The index of the min value - always 1
     */
    public void sinkDown(int k) {
        int smallest = k;

        if(2*k < position && mH[smallest] > mH[2*k]) {
            smallest = 2*k;
        }

        if(2*k+1 < position && mH[smallest] > mH[2*k+1]) {
            smallest = 2*k+1;
        }

        if(smallest != k) {
            swap(k,smallest);
            sinkDown(smallest);
        }
    }

    /**
     * Extract min value from heap
     * @return The min value
     */
    public int extractMin() {
        int min = mH[1];
        mH[1] = mH[position - 1];
        mH[position - 1] = 0;
        position--;
        sinkDown(1);
        return min;
    }

    /**
     * Swap the values
     * @param a
     * @param b
     */
    public void swap (int a, int b) {
        int temp = mH[a];
        mH[a] = mH[b];
        mH[b] = temp;
    }

    /**
     * Do the random sampling by traversing the stream array. If the generated random index is
     * less than the sample size the current element in the iteration is inserted into the heap
     * after extracting the min value from heap.
     * @param stream The character stream
     */
    public void sample(int stream[]) {
        for (int s : stream) {
            int r = (int) (rand.nextDouble() * (size + (c++) + 1));

            if (r < size) {
                extractMin();
                insert(s);
            }
        }
    }

    /**
     * Display the heap
     */
    public void display() {
        System.out.print("Random Sample: ");
        for (int h : mH) {
            System.out.print((char)h);
        }
        System.out.println("");
    }
}
