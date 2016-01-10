package ru.qupol.MonopolyHelper.Utils;

/**
 * Created by pavel on 10.01.16.
 */
public class ViewUtils {
    public static String MoneyStringFormat(int moneyCount) {
        String moneyCountString = String.valueOf(moneyCount);
        int moneyCountLength = moneyCountString.length();
        boolean needToParse = moneyCountLength > 3;
        StringBuilder sb = new StringBuilder();
        while (needToParse) {
            if (moneyCountString.substring(moneyCountLength - 3).equals("000")) {
                sb.append("K");
                moneyCountString = moneyCountString.substring(0, moneyCountLength - 3);
                moneyCountLength = moneyCountString.length();
                needToParse = moneyCountLength > 3;
            } else {
                needToParse = false;
            }
        }
        sb.insert(0, moneyCountString);
        return sb.toString();
    }
}
