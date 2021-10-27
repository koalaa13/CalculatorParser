package visitor;

import exception.DivisionByZeroException;
import exception.OverflowException;

public class Helper {
    public static void checkMultiply(int x, int y) throws OverflowException {
        if (x < 0 && y < 0 && x < Integer.MAX_VALUE / y) {
            throw new OverflowException("Overflow when multiplying");
        }
        if (x < 0 && y > 0 && x < Integer.MIN_VALUE / y) {
            throw new OverflowException("Overflow when multiplying");
        }
        if (x > 0 && y < 0 && y < Integer.MIN_VALUE / x) {
            throw new OverflowException("Overflow when multiplying");
        }
        if (x > 0 && y > 0 && x > Integer.MAX_VALUE / y) {
            throw new OverflowException("Overflow when multiplying");
        }
    }

    public static void checkAdd(int x, int y) throws OverflowException {
        if (y < 0) {
            if (x < Integer.MIN_VALUE - y) {
                throw new OverflowException("Overflow when adding");
            }
        } else {
            if (x > Integer.MAX_VALUE - y) {
                throw new OverflowException("Overflow when adding");
            }
        }
    }

    public static void checkDivide(int x, int y) throws OverflowException, DivisionByZeroException {
        if (y == 0) {
            throw new DivisionByZeroException();
        }
        if (x == Integer.MIN_VALUE && y == -1) {
            throw new OverflowException("Overflow when dividing");
        }
    }

    public static void checkSubtract(int x, int y) throws OverflowException {
        if (y < 0) {
            if (x > Integer.MAX_VALUE + y) {
                throw new OverflowException("Overflow when subtracting");
            }
        } else {
            if (x < Integer.MIN_VALUE + y) {
                throw new OverflowException("Overflow when subtracting");
            }
        }
    }
}
