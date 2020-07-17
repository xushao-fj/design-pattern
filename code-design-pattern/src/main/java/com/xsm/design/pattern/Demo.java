package com.xsm.design.pattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xsm
 * @date 2020/07/14
 * @description
 */
public class Demo {

    public static void main(String[] args) {

        List<String> list = Arrays.asList("1001-2020-3260-1245", "1001-2020-3260-4512", "1001-2020-3260-2365-1523-1542");
        List<List<Long>> idList = new ArrayList<List<Long>>(list.size());
        for (String permission : list) {
            idList.add(getDeptIds(permission));
        }
        Long directpid = null;
        List<Long> ids = idList.get(0);
        for (Long id : ids) {
            boolean contain = true;
            for (List<Long> tempIds : idList) {
                if (!tempIds.contains(id)) {
                    contain = false;
                    break;
                }
            }
            if (contain) {
                directpid =  id;
                break;
            }
        }
        System.out.println(directpid);
    }

    private static List<Long> getDeptIds(String deptIdChain) {
        List<Long> deptIds =  new ArrayList<Long>();
        String[] strDeptIds = deptIdChain.split("-");
        for (int i = strDeptIds.length - 1; i >= 0; i--) {
            deptIds.add(Long.parseLong(strDeptIds[i]));
        }
        return deptIds;
    }

}
