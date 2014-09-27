package pac.testcase.basic.generic;

import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.util.automaton.CharacterRunAutomaton;

import java.util.Arrays;

public class CharCalc {
    public static void main(String[] args) {
        String s1 = "87987989898";
        String s2 = "1231231239";

        char b = 1+48;
        int a = b-48;
        System.out.println(a);
        System.out.println(add(s1,s2));
    }

    public static String add(String s1, String s2) {
        char[] chars = s1.toCharArray();
        char[] chars1 = s2.toCharArray();
        if(s1.length()>s2.length()){
            char[] tmp = new char[chars.length];
            Arrays.fill(tmp,'0');
            for (int i = s1.length()-s2.length(),j=0; i < chars.length; i++,j++) {
                tmp[i] = chars1[j];
            }
            chars1 = tmp;
        }else{
            char[] tmp = new char[chars1.length];
            Arrays.fill(tmp,'0');
            for (int i = s2.length()-s1.length(),j=0; i < chars1.length; i++,j++) {
                tmp[i] = chars[j];
            }
            chars = tmp;
        }

        char[] result = new char[s1.length()>s2.length()?s1.length()+2:s2.length()+2];
        for (int i = 1; i < result.length-1; i++) {
            char[] tmp = String.valueOf(chars[chars.length-i]-48+(chars1[chars1.length-i]-48)).toCharArray();
            result[result.length-i] = tmp[tmp.length-1];

            if(i>1 && chars[chars.length-i+1]-48+(chars1[chars1.length-i+1]-48)>9){
                tmp = String.valueOf(result[result.length-i]-48+1).toCharArray();
                result[result.length-i] = tmp[tmp.length-1];
            }
        }
        return new String(result);
    }

}
