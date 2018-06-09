package com.hd.api.constant;

/**
 * 系统消息常量
 * 
 * @author hoda
 *
 */
public class SystemMsgConst {

	/**
	 * 默认消息
	 */
	public final static String TITLE_DEFAULT = "消息";


	/**
	 * 系统发出
	 */
	public final static String SYSTEM_USER = "system";

	/**
	 * 消息状态枚举
	 */
	public enum MsgStatusEnum {

		/**
		 * 未发送
		 */
		UN_SEND(0),

		/**
		 * 已发送
		 */
		SEND(1),

		/**
		 * 已读
		 */
		READ(2),

		/**
		 * 删除
		 */
		DELETE(-1);

		MsgStatusEnum(Integer code) {
			this.code = code;
		}

		private Integer code;

		@Override
		public String toString() {
			return code.toString();
		}

		public int toInt() {
			return code;
		}
	}

	/**
	 * 通知类型枚举
	 */
	public enum MsgTypeEnum {

		/**
		 * 记录
		 */
		RECORD("A"),

		/**
		 * 好友请求
		 */
		FRIEND_REQUEST("B"),

		/**
		 * 好友分享
		 */
		FRIEND_SHARE("C"),

		/**
		 * 话题回复
		 */
		TOPIC_REPLY("D");

		MsgTypeEnum(String code) {
			this.code = code;
		}

		private String code;

		@Override
		public String toString() {
			return code;
		}

		public static MsgTypeEnum fromString(String text) {
			for (MsgTypeEnum vEnum : values()) {
				if (vEnum.code.equalsIgnoreCase(text)) {
					return vEnum;
				}
			}
			return null;
		}
	}

	/**
	 * 消息分类枚举
	 */
	public enum MsgCategoryEnum {

		/**
		 * 运行建议
		 */
		SUGGESTION("A"),

		/**
		 * 故障消息
		 */
		FAILURE("B"),

		/**
		 * 维保消息
		 */
		MAINTENANCE("C"),

		/**
		 * 好友消息
		 */
		FRIEND("D"),

		/**
		 * 值班消息
		 */
		DUTY("E"),

		/**
		 * 报警消息
		 */
		ALARM("F"),

		/**
		 * 工单消息
		 */
		REPAIR("G");

		MsgCategoryEnum(String code) {
			this.code = code;
		}

		private String code;

		@Override
		public String toString() {
			return code.toString();
		}

		public static MsgCategoryEnum fromString(String text) {
			for (MsgCategoryEnum vEnum : values()) {
				if (vEnum.code.equalsIgnoreCase(text)) {
					return vEnum;
				}
			}
			return null;
		}
	}

}
