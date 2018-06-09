package com.hd.api.constant;

public class UserInfoConst {

	/**
	 * 用户等级
	 * 
	 * @author hoda
	 *
	 */
	public enum LevelEnum {
		/**
		 * 系统管理员
		 */
		SYSTEM("A"),

		/**
		 * 集团或企业管理员
		 */
		MANAGER("B"),

		/**
		 * 一般操作员
		 */
		OPERATOR("C");

		private String attr;

		LevelEnum(String attr) {
			this.attr = attr;
		};

		@Override
		public String toString() {
			return attr;
		}

		public static LevelEnum fromString(String text) {
			for (LevelEnum vEnum : values()) {
				if (vEnum.attr.equalsIgnoreCase(text)) {
					return vEnum;
				}
			}
			return null;
		}
	}
}
