package com.xzy.vr.common.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 流程引擎key值
 */
public enum TakingDrugsEnum {

	BDZ("苯二氮类镇静催眠药",0),

	NON_BZD("非苯二氮类镇静催眠药",1),

	MELATONIN("褪黑素受体激动剂",2),

	ANTIDEPRESSANT("抗抑郁药",3),

	ANTIALLERGIC("抗过敏药",4),

	ANTIPSYCHOTIC("抗精神病药",5),

	NEW_TYPE("新型药物",6),

	OTHER("其他",7),
			;

	/** 描述 */
	private String desc;
	/**值*/
	private int value;


	private TakingDrugsEnum(String desc, int value) {
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
		TakingDrugsEnum[] ary = TakingDrugsEnum.values();
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
		TakingDrugsEnum[] ary = TakingDrugsEnum.values();
		List list = new ArrayList();
		for (int i = 0; i < ary.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("desc", ary[i].getDesc());
			map.put("value", ary[i].getValue());
			list.add(map);
		}
		return list;
	}
	
    public static TakingDrugsEnum getEnum(String name) {
        TakingDrugsEnum[] arry = TakingDrugsEnum.values();
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
		TakingDrugsEnum[] enums = TakingDrugsEnum.values();
		StringBuilder jsonStr = new StringBuilder("[");
		for (TakingDrugsEnum senum : enums) {
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
