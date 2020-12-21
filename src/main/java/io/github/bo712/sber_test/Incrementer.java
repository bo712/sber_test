package io.github.bo712.sber_test;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Class implements interface {@code IIncrementor}
 *
 * @author Konstantin Babakov
 * @version 1.0
 */
@Component
public class Incrementer implements IIncrementor {

    /**
     * Field containing current value
     */
    private AtomicInteger currentNum = new AtomicInteger(0);

    /**
     * Field containing maximum value. Can be from 0 to 2,147,483,647
     */
    private AtomicInteger maxCurrentNum = new AtomicInteger(Integer.MAX_VALUE);

    /**
     * Method returns current number
     *
     * @return value of {@link Incrementer#currentNum}
     */
    @Override
    public synchronized int getNumber() {
        return currentNum.get();
    }

    /**
     * Method incrementing {@link Incrementer#currentNum}
     * If after calling the method{@link Incrementer#incrementNumber()} {@link Incrementer#currentNum}
     * reaches {@link Incrementer#maxCurrentNum}, the current number becomes zero.
     */
    @Override
    public synchronized void incrementNumber() {
        if (currentNum.get() == maxCurrentNum.get()) {
            currentNum = new AtomicInteger(0);
        } else {
            currentNum.incrementAndGet();
        }
    }

    /**
     * Sets the maximum value {@link Incrementer#maxCurrentNum} of the current number.
     * When method {@link Incrementer#incrementNumber()} called and current number reaches
     * {@link Incrementer#maxCurrentNum}, {@link Incrementer#currentNum} reset to zero,
     * i.e. {@link Incrementer#getNumber()} starts returning zero again.
     * If after changing of {@link Incrementer#maxCurrentNum}, current number begins to exceed
     * {@link Incrementer#maxCurrentNum}, then the {@link Incrementer#currentNum} must be reset to zero.
     * You cannot be allowed to set a number here less than zero.
     *
     * @param maximumValue - new value for {@link Incrementer#maxCurrentNum}
     */
    @Override
    public synchronized void setMaximumValue(int maximumValue) {
        if (maximumValue < 0) {
            System.out.println("Can't set number less than zero");
        } else if (currentNum.get() > maximumValue) {
            currentNum = new AtomicInteger(0);
            maxCurrentNum = new AtomicInteger(maximumValue);
        } else {
            maxCurrentNum = new AtomicInteger(maximumValue);
        }
    }

    public void setCurrentNum(int currentNum) {
        this.currentNum = new AtomicInteger(currentNum);
    }

    public AtomicInteger getMaxCurrentNum() {
        return maxCurrentNum;
    }
}
