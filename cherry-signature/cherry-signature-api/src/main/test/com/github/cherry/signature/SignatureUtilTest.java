package com.github.cherry.signature;

import java.util.TreeMap;

import org.junit.Test;

import com.github.cherry.signature.utils.SignatureUtils;

public class SignatureUtilTest {
    @Test
    public void testBuildData() {
        TreeMap<String, String> treeMap1 = new TreeMap<String, String>();
        treeMap1.put("2", "1");
        treeMap1.put("b", "1");
        treeMap1.put("1", "1");
        treeMap1.put("a", "1");
        String value = SignatureUtils.buildData(treeMap1);
        System.out.println(value);
    }
    
    @Test
    public void testBuildParamterMap() {
        Account account = new Account();
        account.setAccountIgnoreField("accountIgnoreField");
        account.setBalance(1000.00);
        account.setId("10001");
        account.setDd(11.0);
        
        User user = new User();
        user.setAccount(account);
        String[] location = new String[]{"jianye","nanjin","jiangsu","zhongguo"};
        user.setLocation(location);
        user.setPassword("123456");
        user.setUserName("张三");
        
        String sign = SignatureUtils.buildData(SignatureUtils.buildParamterMap(user));
        System.out.println(sign);
    }
}
