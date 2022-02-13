package com.positive.oauth2.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 正能量导师
 * @version 1.0
 * @description
 *
 * balloon
 *
 * @date 13/2/2022 下午10:19
 */
public class Balloon {

    static Map<Character, Integer> param = new HashMap<>();

    public static int maxNumberOfBalloons(String text) {
        Map<Character, Integer> map = new HashMap<>();
        ArrayList<Character> temp = new ArrayList<>();
        temp.add('a');
        temp.add('b');
        temp.add('n');
        temp.add('l');
        temp.add('o');
        char[] cList = text.toCharArray();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (temp.contains(c)){
                map.put(c,map.getOrDefault(c,0)+1);
            }
        }
        int result = text.length();
        for (Character c:temp){
            if (c=='l' || c=='o'){
                result = Math.min(result,map.getOrDefault(c,0)/2);
            }else{
                result = Math.min(result,map.getOrDefault(c,0));
            }
        }
        return result;
    }

    public static void keyGudge(Character i,Character target){
        if (i==target){
            if (param.containsKey(target)){
                param.put(target,param.get(target)+1);
            }else {
                param.put(target,1);
            }
        }
    }
}
