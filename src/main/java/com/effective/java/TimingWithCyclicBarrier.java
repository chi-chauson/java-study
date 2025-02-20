package com.effective.java;

import java.util.concurrent.*;

public class TimingWithCyclicBarrier {

    /**
     * Times the concurrent execution of the given action using a CyclicBarrier.
     *
     * @param executor    the Executor that will run the tasks
     * @param concurrency the number of concurrent tasks (threads) to run
     * @param action      the work each thread should perform
     * @return the elapsed time (in nanoseconds) from "start gun" to completion
     */
    public static long time(Executor executor, int concurrency, Runnable action)
            throws InterruptedException, BrokenBarrierException {

        // +1 for the main (timer) thread, plus 'concurrency' worker threads
        CyclicBarrier barrier = new CyclicBarrier(concurrency + 1);

        // Schedule the worker tasks
        for (int i = 0; i < concurrency; i++) {
            executor.execute(() -> {
                try {
                    // 1) Wait for the main thread to "start the race"
                    barrier.await();
                    // 2) Perform the action
                    action.run();
                    // 3) Wait until the main thread collects finishing times
                    barrier.await();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (BrokenBarrierException e) {
                    // Barrier was broken because of an error or interruption
                    throw new RuntimeException(e);
                }
            });
        }

        // --- MAIN THREAD ---

        // Phase 1: Main thread arrives at the barrier for the "start gun"
        barrier.await();
        long startNanos = System.nanoTime();

        // Phase 2: Main thread arrives again after workers finish
        barrier.await();
        long endNanos = System.nanoTime();

        return endNanos - startNanos;
    }

    // Simple demo
    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newFixedThreadPool(4);
        long nanos = time(exec, 4, () -> {
            // Simulate some work
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        exec.shutdown();
        System.out.println("Elapsed time (ms): " + nanos / 1_000_000.0);
    }
}
