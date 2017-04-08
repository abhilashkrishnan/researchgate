package net.researchgate.stream.sampling;

import java.security.SecureRandom;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * @author Abhilash Krishnan
 * @since 07/04/17
 * An implementation of Reservoir Sampling Algorithm using Java 8 Collector implementation
 */

public class ReservoirSampler<T> extends Sampler implements Collector<T, List<T>, List<T>>{
    /**
     * Random Generator
     */
    final Random rand = new SecureRandom();
    /**
     * Counter used in the generation of random value
     */
    int c = 0;

    public ReservoirSampler(int size) {
        super(size);
    }

    /**
     * Populate the existing accumulator with random samples using Reservoir Sampling algorithm designed
     * by Knuth in 1997.
     * @param in The existing accumulator object
     * @param s The element to be added to the existing accumulator
     */
    private void populate(List<T> in, T s) {
        if (in.size() < size) {
            in.add(s);
        } else {
           int replaceIndex = (int) (rand.nextDouble() * (size + (c++) + 1));
           if(replaceIndex < size) {
               in.set(replaceIndex, s);
           }
        }
    }

    @Override
    public Supplier<List<T>> supplier() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return this::populate;
    }

    @Override
    public BinaryOperator<List<T>> combiner() {
        return (left, right) -> {
            left.addAll(right);
            return left;
        };
    }

    @Override
    public Set<Characteristics> characteristics() {
        return EnumSet.of(Characteristics.UNORDERED, Characteristics.IDENTITY_FINISH);
    }

    @Override
    public Function<List<T>, List<T>> finisher() {
        return (i) -> i;
    }
}

