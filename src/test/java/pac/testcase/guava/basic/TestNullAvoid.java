package pac.testcase.guava.basic;

import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Strings;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Alvez on 14-9-29.
 */
public class TestNullAvoid {
    public static void main(String[] args) {
        testBasic();
//        testString();
    }

    static void testBasic(){

        //if integer == null return 1
        Integer integer = null;
        System.out.println(Objects.firstNonNull(integer, 1));


        //if second optional is absent return 20
        integer = 60;
        Optional<Integer> optional = Optional.absent();

        integer= Optional.fromNullable(integer).or(20);
        //integer = optional.or(Optional.fromNullable(integer)).or(20);

        System.out.println(integer);
    }

    static void testString(){

        //output null
        System.out.println("If input string is empty, emptyToNull returns:::\t"+Strings.emptyToNull(StringUtils.EMPTY));

        //output empty string
        System.out.println("If input string is null, nullToEmpty returns:::\t"+Strings.nullToEmpty(null));

        //false
        System.out.println("check string is null or empty:::\t\t\t"+Strings.isNullOrEmpty("   "));


        System.out.println("\n--------more about com.google.common.base.Strings--------\n");


        //output "Alvez Ki"
        System.out.println("common prefix of 'Alvez Kim' & 'Alvez King.':::\t\t" + Strings.commonPrefix("Alvez Kim", "Alvez King."));

        //output "e Alvez!"
        System.out.println("common suffix of 'We love Alvez!' & 'be Alvez!':::\t" + Strings.commonSuffix("We love Alvez!", "be Alvez!"));


        //2333333333
        System.out.println("attach '3' to end of string '2' till maxlength equal to 10:::\t\t"+Strings.padEnd("2",10,'3'));

        //0000000907
        System.out.println("attach '0' to start of string '0907' till maxlength equal to 10:::\t"+Strings.padStart("0907",10,'0'));

        //yoyoyoyo
        System.out.println("repeat 'yo' 4 times:::\t" + Strings.repeat("yo", 4));

    }
}
