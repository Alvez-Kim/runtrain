package pac.testcase.utils;

import java.util.Arrays;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.lang.StringUtils;

public class HanSpellHelper {
	public static String getEname(String name)
			throws BadHanyuPinyinOutputFormatCombination {
		HanyuPinyinOutputFormat pyFormat = new HanyuPinyinOutputFormat();
		pyFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		pyFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		pyFormat.setVCharType(HanyuPinyinVCharType.WITH_V);

		StringBuilder result = new StringBuilder();
		for (char zi: name.toCharArray()) {
			result.append(Arrays.toString(PinyinHelper.toHanyuPinyinStringArray(zi)));
		}
		
		return result.toString();
	}

	public static String getUpEname(String name)
			throws BadHanyuPinyinOutputFormatCombination {
		char[] strs = name.toCharArray();
		String newname;

		if (strs.length == 2) {
			newname = toUpCase(getEname("" + strs[0])) + " "
					+ toUpCase(getEname("" + strs[1]));
		} else if (strs.length == 3) {
			newname = toUpCase(getEname("" + strs[0])) + " "
					+ toUpCase(getEname("" + strs[1] + strs[2]));
		} else if (strs.length == 4) {
			newname = toUpCase(getEname("" + strs[0] + strs[1])) + " "
					+ toUpCase(getEname("" + strs[2] + strs[3]));
		} else {
			newname = toUpCase(getEname(name));
		}

		return newname;
	}

	private static String toUpCase(String str) {
        return (str.substring(0, 1)).toUpperCase() + str.substring(1, str.length());
	}

    /**
     * 汉字转换位汉语拼音首字母，英文字符不变
     * @param chines 汉字
     * @return 拼音
     */
    public static String converterToFirstSpell(String chines){
        String pinyinName = "";
        char[] nameChar = chines.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (char aNameChar : nameChar) {
            if (aNameChar > 128) {
                try {
                    System.out.println("+++" + aNameChar);
                    pinyinName += PinyinHelper.toHanyuPinyinStringArray(aNameChar, defaultFormat)[0].charAt(0);
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pinyinName += aNameChar;
            }
        }
        return pinyinName;
    }

    /**
     * 汉字转换位汉语拼音，英文字符不变
     * @param chines 汉字
     * @return 拼音
     */
    public static String converterToSpell(String chines){
        String pinyinName = "";
        char[] nameChar = chines.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (char aNameChar : nameChar) {
            if (aNameChar > 128) {
                try {
                    String[] strings = PinyinHelper.toHanyuPinyinStringArray(aNameChar, defaultFormat);

                    //due to each method of PinyinHelper returns null , i hove to check
                    pinyinName += strings == null ? StringUtils.EMPTY : strings[0];
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pinyinName += aNameChar;
            }
        }
        return pinyinName;
    }

    public static void main(String[] args)
			throws BadHanyuPinyinOutputFormatCombination {
		System.out.println(Arrays.toString(PinyinHelper
				.toGwoyeuRomatzyhStringArray('金')));
		
		System.out.println(getUpEname("金大侠"));
        System.out.println(Arrays.toString(PinyinHelper.toGwoyeuRomatzyhStringArray('김')));

        System.out.println(converterToSpell("김大侠"));

	}
}