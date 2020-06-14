package com.xzy.vr.common.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 流程引擎key值
 */
public enum InsomniaTimeEnum {

	UNDER_THREE_MONTH("3个月以下",0),

	THREE_SIX_MONTH("3个月~6个月",1),

	SIX_TWELVE_MONTH("6个月~12个月",2),

	MORE_ONE_YEAR("1年以上",3),

	MORE_THREE_YEAR("3年以上",4),

	OTHER("其他",-1);

	/** 描述 */
	private String desc;
	/**值*/
	private int value;


	private InsomniaTimeEnum(String desc, int value) {
		this.desc = desc;
		this.value = value;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public static Map<String, Map<String, Object>> toMap() {
		InsomniaTimeEnum[] ary = InsomniaTimeEnum.values();
		Map<String, Map<String, Object>> enumMap = new HashMap<String, Map<String, Object>>();
		for (int num = 0; num < ary.length; num++) {
			Map<String, Object> map = new HashMap<String, Object>();
			String key = ary[num].name();
			map.put("desc", ary[num].getDesc());
			map.put("value", ary[num].getValue());
			enumMap.put(key, map);
		}
		return enumMap;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List toList() {
		InsomniaTimeEnum[] ary = InsomniaTimeEnum.values();
		List list = new ArrayList();
		for (int i = 0; i < ary.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("desc", ary[i].getDesc());
			map.put("value", ary[i].getValue());
			list.add(map);
		}
		return list;
	}
	
    public static InsomniaTimeEnum getEnum(String name) {
        InsomniaTimeEnum[] arry = InsomniaTimeEnum.values();
        for (int i = 0; i < arry.length; i++) {
            if (arry[i].name().equalsIgnoreCase(name)) {
                return arry[i];
            }
        }
        return null;
    }

	/**
	 * 取枚举的json字符串
	 * 
	 * @return
	 */
	public static String getJsonStr() {
		InsomniaTimeEnum[] enums = InsomniaTimeEnum.values();
		StringBuilder jsonStr = new StringBuilder("[");
		for (InsomniaTimeEnum senum : enums) {
			if (!"[".equals(jsonStr.toString())) {
				jsonStr.append(",");
			}
			jsonStr.append("{id:'").append(senum).append("',value:'").append(senum.getValue()).append("',desc:'")
			.append(senum.getDesc()).append("'}");
		}
		jsonStr.append("]");
		return jsonStr.toString();
	}

}
