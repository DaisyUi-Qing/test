package com.daisy.demo.asyn;


import com.daisy.demo.entity.User;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @author dxm
 */
public class GPerson {

    private final static int MAX_AGE = 100;

    private Set<String> idCardSets = new HashSet<>();

    private final static Random RANDOM = new Random();

    private static final String[] FAMILY_NAMES = {"赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈", "褚", "卫", "蒋", "沈", "韩", "杨", "朱", "秦", "尤", "许", "何", "吕", "施", "张", "孔", "曹", "严", "华", "金", "魏", "陶", "姜", "戚", "谢", "邹", "喻", "柏", "水", "窦", "章", "云", "苏", "潘", "葛", "奚", "范", "彭", "郎", "鲁", "韦", "昌", "马", "苗", "凤", "花", "方", "俞", "任", "袁", "柳", "酆", "鲍", "史", "唐", "费", "廉", "岑", "薛", "雷", "贺", "倪", "汤", "滕", "殷", "罗", "毕", "郝", "邬", "安", "常", "乐", "于", "时", "傅", "皮", "卞", "齐", "康", "伍", "余", "元", "卜", "顾", "孟", "平", "黄", "和", "穆", "萧", "尹", "欧阳", "慕容"};
    private static final String[] COMMON_CHARS = {"峰", "宇", "霞", "婷", "飞", "静", "杰", "磊", "娜", "涛", "欣", "晶", "华", "宁", "芳", "建", "文", "博", "东", "锋", "瑞", "慧", "健", "明", "洋", "洁", "航", "雪", "峰", "丽", "伟", "倩", "辉", "莉", "毅", "雅", "宏", "佳", "强", "颖", "勇", "娟", "鹏", "秀", "俊", "琳", "刚", "梅", "德", "萍", "波", "晨", "媛", "鑫", "敏", "亮", "梦", "蕾", "鑫", "明"};

    public synchronized User getPerson(){
        User user = new User();
        user.setName(generateName());
        user.setAge(generateAge());
        user.setIdCard(generateIdCard());
        return user;
    }

    private String generateIdCard() {
        String idCard;
            do {
                // 生成随机身份证号码
                StringBuilder sb = new StringBuilder();
                // 生成前 17 位数字
                for (int i = 0; i < 17; i++) {
                    int digit = RANDOM.nextInt(10);
                    sb.append(digit);
                }
                // 生成最后一位校验码
                String prefix = sb.toString();
                int sum = 0;
                for (int i = 0; i < prefix.length(); i++) {
                    int weight = (int) Math.pow(2, 17 - i) % 11;
                    int digit = Character.digit(prefix.charAt(i), 10);
                    sum += weight * digit;
                }
                int remainder = sum % 11;
                String lastDigit;
                if (remainder == 0) {
                    lastDigit = "1";
                } else if (remainder == 1) {
                    lastDigit = "0";
                } else if (remainder == 2) {
                    lastDigit = "X";
                } else {
                    lastDigit = Integer.toString(12 - remainder);
                }
                idCard = prefix + lastDigit;
            } while (idCardSets.contains(idCard));
            idCardSets.add(idCard);
            return idCard;
    }

    private Integer generateAge() {
        return RANDOM.nextInt(MAX_AGE);
    }

    private String generateName() {
        String familyName = FAMILY_NAMES[RANDOM.nextInt(FAMILY_NAMES.length)];
        String commonChar = COMMON_CHARS[RANDOM.nextInt(COMMON_CHARS.length)];
        String commonChar2 = COMMON_CHARS[RANDOM.nextInt(COMMON_CHARS.length)];
        return familyName + commonChar + commonChar2;
    }


}
