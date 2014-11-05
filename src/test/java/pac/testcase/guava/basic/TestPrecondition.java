package pac.testcase.guava.basic;

import com.google.common.base.Preconditions;

/**
 * Created by Alvez on 14-9-29.
 */
public class TestPrecondition {

    public static void main(String[] args) {
        testPrecondition();
    }

    /**
     * <p>there is 5 checks provided:
     * arguments, element index, position index, state ,not null.
     * and each of these methods has 3 styles
     */
    static void testPrecondition() {
        try{
            Preconditions.checkArgument(false, "error occurred: %s", 10);
        }catch (IllegalArgumentException t){
            t.printStackTrace();
        }

        try{
            Preconditions.checkElementIndex(10,6,"index:10 length:6 error occurred!!!");
        }catch (IndexOutOfBoundsException t){
            t.printStackTrace();
        }

        try{
            Preconditions.checkPositionIndex(10,6,"index:10 length:6 error occurred!!!");
        }catch (IndexOutOfBoundsException t){
            t.printStackTrace();
        }

        try{
            Preconditions.checkState(false,"state incorrect %s",false);
        }catch (IllegalStateException t){
            t.printStackTrace();
        }

        try{
            Preconditions.checkNotNull(null,"this reference could not be null %s",System.currentTimeMillis());
        }catch (NullPointerException n){
            n.printStackTrace();
        }

    }
}
