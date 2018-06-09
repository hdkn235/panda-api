package com.hd.api.thirdly.impl;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.ITemplate;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.NotyPopLoadTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.gexin.rp.sdk.template.style.Style0;
import com.hd.api.thirdly.AppPushManager;
import com.hd.api.utils.common.CommonUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AppPushManagerImpl implements AppPushManager {

    private static Logger logger = LoggerFactory.getLogger(AppPushManager.class);

    @Value("#{api.push_appId}")
    private String PUSH_APP_ID;

    @Value("#{api.push_appKey}")
    private String PUSH_APP_KEY;

    @Value("#{api.push_masterSecret}")
    private String PUSH_MASTER_SECRET;

    @Value("#{api.push_host}")
    private String PUSH_HOST;

    @Override
    public boolean pushToSingle(String cid, ITemplate template) {
        IGtPush push = new IGtPush(PUSH_HOST, PUSH_APP_KEY, PUSH_MASTER_SECRET);
        SingleMessage message = new SingleMessage();
        message.setOffline(true);
        // 离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(24 * 3600 * 1000);
        message.setData(template);
        // 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
        message.setPushNetWorkType(0);
        Target target = new Target();
        target.setAppId(PUSH_APP_ID);
        target.setClientId(cid);
        // 别名推送方式
        // String Alias = "";
        // target.setAlias(Alias);
        IPushResult ret = null;
        try {
            ret = push.pushMessageToSingle(message, target);
        } catch (RequestException e) {
            logger.error(CommonUtil.getStackTraceStr(e));
            ret = push.pushMessageToSingle(message, target, e.getRequestId());
        }

        return isSuccess(ret);
    }

    /**
     * 推送是否成功
     *
     * @param ret
     * @return
     */
    private static boolean isSuccess(IPushResult ret) {
        if (ret != null && ret.getResponse() != null) {
            if (ret.getResponse().get("result").equals("ok")) {
                logger.debug(ret.toString());
                return true;
            } else {
                logger.error(ret.getResponse().toString());
            }
        } else {
            logger.error("推送服务器相应异常");
        }

        return false;
    }

    @Override
    public boolean pushToList(List<String> cids, ITemplate template) {
        // 配置返回每个用户返回用户状态，可选
        System.setProperty("gexin.rp.sdk.pushlist.needDetails", "true");
        IGtPush push = new IGtPush(PUSH_HOST, PUSH_APP_KEY, PUSH_MASTER_SECRET);
        // 通知透传模板
        ListMessage message = new ListMessage();
        message.setData(template);
        // 设置消息离线，并设置离线时间
        message.setOffline(true);
        // 离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(24 * 1000 * 3600);
        // 配置推送目标
        List<Target> targets = new ArrayList<Target>();
        for (String cid : cids) {
            Target target = new Target();
            target.setAppId(PUSH_APP_ID);
            target.setClientId(cid);
            // target.setAlias(alias);
            targets.add(target);
        }
        // taskId用于在推送时去查找对应的message
        String taskId = push.getContentId(message);
        IPushResult ret = push.pushMessageToList(taskId, targets);

        return isSuccess(ret);
    }

    @Override
    public boolean pushToApp(String groupName, ITemplate template) {

        IGtPush push = new IGtPush(PUSH_HOST, PUSH_APP_KEY, PUSH_MASTER_SECRET);

        // 定义"AppMessage"类型消息对象
        AppMessage message = new AppMessage();
        // 设置消息内容模板
        message.setData(template);
        // 是否支持离线发送
        message.setOffline(true);
        // 离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(24 * 1000 * 3600);
        // 发送的目标App列表
        List<String> appIdList = new ArrayList<String>();
        appIdList.add(PUSH_APP_ID);
        message.setAppIdList(appIdList);

        // // 推送给App的目标用户需要满足的条件
        // AppConditions cdt = new AppConditions();
        // // 手机类型
        // cdt.addCondition(AppConditions.PHONE_TYPE, phoneTypeList);
        // // 省份
        // cdt.addCondition(AppConditions.REGION, provinceList);
        // // 自定义tag
        // cdt.addCondition(AppConditions.TAG, tagList);
        // message.setConditions(cdt);

        IPushResult ret = null;
        if (StringUtils.isEmpty(groupName)) {
            ret = push.pushMessageToApp(message);
        } else {
            ret = push.pushMessageToApp(message, groupName);
        }

        return isSuccess(ret);
    }

    @Override
    public NotificationTemplate getNotificationTemplate(String title, String text, String content) {
        NotificationTemplate template = new NotificationTemplate();
        // 设置APPID与APPKEY
        template.setAppId(PUSH_APP_ID);
        template.setAppkey(PUSH_APP_KEY);
        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
        template.setTransmissionType(1);
        // 设置透传内容
        template.setTransmissionContent(content);
        // 设置定时展示时间
        // template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");

        Style0 style = new Style0();
        // 设置通知栏标题与内容
        style.setTitle(title);
        style.setText(text);
        // 配置通知栏图标
        style.setLogo("icon.png");
        // 配置通知栏网络图标
        style.setLogoUrl("");
        // 设置通知是否响铃，震动，或者可清除
        style.setRing(true);
        style.setVibrate(true);
        style.setClearable(true);
        template.setStyle(style);

        return template;
    }

    @Override
    public LinkTemplate getLinkTemplate(String title, String text, String url) {
        LinkTemplate template = new LinkTemplate();
        // 设置APPID与APPKEY
        template.setAppId(PUSH_APP_ID);
        template.setAppkey(PUSH_APP_KEY);
        // 设置通知栏标题与内容
        template.setTitle(title);
        template.setText(text);
        // 配置通知栏图标
        template.setLogo("icon.png");
        // 配置通知栏网络图标，填写图标URL地址
        template.setLogoUrl("");
        // 设置通知是否响铃，震动，或者可清除
        template.setIsRing(true);
        template.setIsVibrate(true);
        template.setIsClearable(true);
        // 设置打开的网址地址
        template.setUrl(url);
        return template;
    }

    @Override
    public TransmissionTemplate getTransmissionTemplate(String content) {
        TransmissionTemplate template = new TransmissionTemplate();
        template.setAppId(PUSH_APP_ID);
        template.setAppkey(PUSH_APP_KEY);
        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
        template.setTransmissionType(2);
        // 需要透传内容
        template.setTransmissionContent(content);
        // 设置定时展示时间
        // template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");
        return template;
    }

    @Override
    public NotyPopLoadTemplate getNotyPopLoadTemplate(String notyTitle, String notyContent, String popTitle,
                                                      String popContent, String loadTitle, String loadUrl) {
        NotyPopLoadTemplate template = new NotyPopLoadTemplate();
        // 设置APPID与APPKEY
        template.setAppId(PUSH_APP_ID);
        template.setAppkey(PUSH_APP_KEY);
        // 设置通知栏标题与内容
        template.setNotyTitle(notyTitle);
        template.setNotyContent(notyContent);
        // 配置通知栏图标
        template.setNotyIcon("icon.png");
        // 设置通知是否响铃，震动，或者可清除
        template.setBelled(true);
        template.setVibrationed(true);
        template.setCleared(true);
        // 设置弹框标题与内容
        template.setPopTitle(popTitle);
        template.setPopContent(popContent);
        // 设置弹框显示的图片
        template.setPopImage("");
        template.setPopButton1("下载");
        template.setPopButton2("取消");
        // 设置下载标题
        template.setLoadTitle(loadTitle);
        template.setLoadIcon("file://icon.png");
        // 设置下载地址
        template.setLoadUrl(loadUrl);
        // 设置定时展示时间
        // template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");
        return template;
    }

}
