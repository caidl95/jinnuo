package xyz.zhuoxuan.jinnuo.util;

import org.apache.commons.lang3.StringUtils;

public class ArrToNullUtil {

    /**
     *
     * @param arr 数组
     * @return true 为 这个数组是空的 false 则为不是空
     */
    public static boolean arrIsNull(String [] arr){
        boolean [] isNollArr = new boolean [arr.length];
        boolean isNoll = true;
        for (int i=0;i<arr.length; i++){
            String str = arr [i];
            if (StringUtils.equals("",str)){
                isNollArr[i] = true;
            }else {
                isNollArr[i] = false;
            }
        }
        for (int i = 0; i < isNollArr.length; i++) {
            if (!isNollArr[i]){
                isNoll = false;
            }
        }
        return isNoll;
    }


    private static String[] remove(String[] arr, String str) {
        String[] tmp = new String[arr.length - 1];
        int idx = 0;
        boolean hasRemove = false;
        for (int i = 0; i < arr.length; i++) {
            if (!hasRemove && arr[i].equals(str)) {
                hasRemove = true;
                continue;
            }
            tmp[idx++] = arr[i];
        }
        return tmp;
    }
}
