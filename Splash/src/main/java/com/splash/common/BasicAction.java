package com.splash.common;

@FunctionalInterface
public interface BasicAction<R> {

    /**
     * Applies this function to the given argument.
     *
     * @param t the function argument
     * @return the function result
     */
    R apply();
}
