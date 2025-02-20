package com.effective.java;

import java.util.concurrent.*;

public class TimingWithPhaser {

    /**
     * Times the concurrent execution of the given action using a Phaser.
     *
     * @param executor    the Executor that will run the tasks
     * @param concurrency the number of concurrent tasks (threads) to run
     * @param action      the work each thread should perform
     * @return the elapsed time (in nanoseconds) from "start gun" to completion
     */
    public static long time(Executor executor, int concurrency, Runnable action) throws InterruptedException {

        // +1 for the main (timer) thread
        Phaser phaser = new Phaser(concurrency + 1);

        // Schedule the worker tasks
        for (int i = 0; i < concurrency; i++) {
            executor.execute(() -> {
                // Phase 0: Arrive and await "start"
                phaser.arriveAndAwaitAdvance();

                // Do the actual work
                action.run();

                // Phase 1: Arrive and await "done"
                phaser.arriveAndAwaitAdvance();
            });
        }

        // --- MAIN THREAD ---
        // Phase 0: Main thread arrives ("start gun")
        phaser.arriveAndAwaitAdvance();
        long startNanos = System.nanoTime();

        // Phase 1: Main thread arrives when tasks have completed
        phaser.arriveAndAwaitAdvance();
        long endNanos = System.nanoTime();

        return endNanos - startNanos;
    }

    // Simple demo
    public static void main(String[] args) throws InterruptedException {
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
