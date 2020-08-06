package com.konglingzhan.manager.vo;

import java.util.HashMap;
import java.util.Map;

public class CodeMsg {

    //成功
    public static final int CODE_SUCCESS=0;
    //失败
    public static final int CODE_FAIL=-1;
    // 未登录
    public static final int CODE_NO_LOGIN=-1;
    // 角色已存在
    public static final int CODE_ROLE_EXIST = 1001;
    //非法的请求参数
    public static final int WRONGFUL_ARG=10001;
    //调用服务失败
    public static final int SERVICE_CALL_FAILED=10002;
    //暂无数据
    public static final int NO_DATA=10003;
    //未处理的异常
    public static final int NICHT_VERARBEITET=10004;
    //数据已经存在，请重新插入
    public static final int  DATA_REPEAT=10005;
    //DopCommonService服务异常
    public static final int  DOP_COMMON_SERVICE_EXCEPTION=10006;
    //ActivityService服务异常
    public static final int ACTIVITY_SERVICE_EXCEPTION=10007;
    //YybKhService服务异常
    public static final int YYBKH_SERVICE_EXCEPTION=10008;
    //CrhService服务异常
    public static final int CRH_SERVICE_EXCEPTION=10009;
    //SidiService服务异常
    public static final int SIDI_SERVICE_EXCEPTION=10010;
    //CommonSimulateService服务异常
    public static final int COMMON_SIMULATE_SERVICE_EXCEPTION=10011;
    //活动已经下线
    public static final int  ACTIVITY_OFF_LINE=100012;
    //活动已经结束报名
    public static final int  ACTIVITY_ENTER_END=100013;
    //活动已经结束
    public static final int  ACTIVITY_END=100014;
    //名称重复，就请重新插入
    public static final int  NAME_REPETITON=100015;
    //未参加该比赛
    public static final int  NO_JOIN_MATCH = 100016;
    //用户未登陆
    public static final int  USER_NOT_LOGIN = 100017;

    public static final int  JOIN_MATCH = 100018;
    //请不要重复提交
    public static final int  NO_REPEAT_COMMIT = 100019;
    //牛人汇服务异常
    public static final int  NRH_SERVICE_EXCEPTION = 100019;
    //活动不存在
    public static final int  ACTIVITY_NOT_EXIST =100020;
    //用户不存在
    public static final int  USER_NOT_EXIST = 100021;
    //包含敏感词
    public static final int  SENSWORD_EXIST = 100022;
    //活动名称存在
    public static final int  ACTIVITY_NAME_EXIST = 100023;
    //指标不存在
    public static final int  RULE_NOT_EXIST = 100024;
    //CrmService服务异常
    public static final int  CRM_SERVICE_EXCEPTION = 100025;
    //榜单不存在
    public static final int  TOTAL_BOARD_SET_NOT_EXIST = 100026;
    //订阅不能超过最大订阅数据
    public static final int   NO_OUT_SUC_NUMBER = 100027;
    //用户已经被邀请
    public static final int   USER_INVITE_EXIST   = 100028;
    //FundTransactionService服务异常
    public static final int   FUND_TRANSACTION_EXCEPTION   = 100029;
    //FundBindingService服务异常
    public static final int   FUND_BINDING_EXCEPTION   = 100030;
    //不符合报名条件
    public static final int   NO_ENTRY_ACTIVITY   = 100031;
    //FundPkService服务异常
    public static final int   FUND_PK_EXCEPTION   = 100032;
    //活动未开始报名
    public static final int  ACTIVITY_NOT_ENTER_BEGIN=100033;
    //PublicFundSimulateService服务异常
    public static final int PUBLIC_FUND_SIMULATE_SERVICE_EXCEPTION = 100034;
    //Workflow服务异常
    public static final int WORKFLOW_SERVICE_EXCEPTION = 100035;
    //NrhManageService服务异常
    public static final int NRH_MANAGE_SERVICE_EXCEPTION = 100036;
    //奖励设置已经被使用
    public static final int AWARD_SET_IS_USED = 100037;
    //已经被邀请，请勿重复提交
    public static final int INVITE_INFO_EXIST = 100038;




    private static Map<Integer,String> map=new HashMap<>();

    static {
        map.put(CODE_SUCCESS,"成功");
        map.put(CODE_FAIL,"失败");
        map.put(CODE_ROLE_EXIST,"角色已存在");
        map.put(WRONGFUL_ARG,"非法的请求参数");
        map.put(SERVICE_CALL_FAILED,"调用服务失败");
        map.put(NO_DATA,"暂无数据");
        map.put(NICHT_VERARBEITET,"未处理的异常");
        map.put(DATA_REPEAT,"数据已经存在，请重新插入");
        map.put(DOP_COMMON_SERVICE_EXCEPTION,"DopCommonService服务异常");
        map.put(ACTIVITY_SERVICE_EXCEPTION,"ActivityService服务异常");
        map.put(YYBKH_SERVICE_EXCEPTION,"YybKhService服务异常");
        map.put(SIDI_SERVICE_EXCEPTION,"SidiService服务异常");
        map.put(CRH_SERVICE_EXCEPTION,"CrhService服务异常");
        map.put(COMMON_SIMULATE_SERVICE_EXCEPTION,"CommonSimulateService服务异常");
        map.put(ACTIVITY_OFF_LINE,"活动已经下线");
        map.put(ACTIVITY_ENTER_END,"活动已经结束报名");
        map.put(ACTIVITY_END,"活动已经结束");
        map.put(NAME_REPETITON,"名称重复，就请重新插入");
        map.put(NO_JOIN_MATCH,"未参加该比赛");
        map.put(USER_NOT_LOGIN,"用户未登录");
        map.put(JOIN_MATCH,"已参加该比赛");
        map.put(NO_REPEAT_COMMIT,"请不要重复提交");
        map.put(NRH_SERVICE_EXCEPTION,"NrhService服务异常");
        map.put(ACTIVITY_NOT_EXIST,"该活动不存在");
        map.put(USER_NOT_EXIST,"用户不存在");
        map.put(SENSWORD_EXIST,"包含敏感词");
        map.put(ACTIVITY_NAME_EXIST,"活动名称存在");
        map.put(RULE_NOT_EXIST,"指标不存在");
        map.put(CRM_SERVICE_EXCEPTION,"CrmService服务异常");
        map.put(TOTAL_BOARD_SET_NOT_EXIST,"榜单不存在");
        map.put(NO_OUT_SUC_NUMBER,"订阅不能超过最大订阅数据");
        map.put(USER_INVITE_EXIST,"用户已经被邀请");
        map.put(FUND_TRANSACTION_EXCEPTION,"FundTransactionService服务异常");
        map.put(FUND_BINDING_EXCEPTION,"FundBindingService服务异常");
        map.put(NO_ENTRY_ACTIVITY,"不符合报名条件");
        map.put(FUND_PK_EXCEPTION,"FundPkService服务异常");
        map.put(ACTIVITY_NOT_ENTER_BEGIN,"活动未开始报名");
        map.put(PUBLIC_FUND_SIMULATE_SERVICE_EXCEPTION,"PublicFundSimulateService服务异常");
        map.put(WORKFLOW_SERVICE_EXCEPTION,"WorkflowService服务异常");
        map.put(NRH_MANAGE_SERVICE_EXCEPTION,"NrhManageService服务异常");
        map.put(AWARD_SET_IS_USED,"奖励设置已经被使用");
        map.put(INVITE_INFO_EXIST,"已经被邀请，请勿重复提交");
    }

    public static String  getMessage(Integer key){
        return map.get(key);
    }
}