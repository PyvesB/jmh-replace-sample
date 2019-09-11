package io.github.pyvesb.jhm_replace_sample;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

@Warmup(iterations = 10)
@Measurement(iterations = 20)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class ReplaceBenchmark {

    private static final String ALPHA_NUMERIC_CHARS = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final int INPUT_LENGTH = 64;

    private String inputString;

    @Setup
    public void setup() {
        inputString = new Random().ints(INPUT_LENGTH, 0, ALPHA_NUMERIC_CHARS.length())
                .mapToObj(ALPHA_NUMERIC_CHARS::charAt)
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    @Benchmark
    public void replaceAll(Blackhole blackhole) {
        blackhole.consume(inputString.replaceAll("a", "b"));
    }

    @Benchmark
    public void replaceCharSequence(Blackhole blackhole) {
        blackhole.consume(inputString.replace("a", "b"));
    }

    @Benchmark
    public void replaceChar(Blackhole blackhole) {
        blackhole.consume(inputString.replace('a', 'b'));

    }

}
