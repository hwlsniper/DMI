package com.models.cloud.web.controller.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.models.cloud.constants.SmsConstant;
import com.models.cloud.gw.protocolfactory.impl.SetPaymentPassImpl2;
import com.models.cloud.util.ConvertUtils;
import com.models.cloud.web.controller.DoWebPageService;

/**
 * 
 * @Description: 重置支付密码（未绑卡）
 * @ClassName: ResetPaymentPassWebImpl 
 * @author: zhengping.hu
 * @date: 2016年5月19日 下午4:40:58
 */
@Service("resetPaymentPassWebImpl")
public class ResetPaymentPassWebImpl implements DoWebPageService{
	
	@Resource(name="setPaymentPassImpl2")
	private SetPaymentPassImpl2 setPaymentPassImpl2;
	
	@Override
	public Map<String, Object> returnData(Map<String, Object> map,Model model, HttpServletRequest request) {
		Map<String, Object> returnMap = new HashMap<String,Object>();
		returnMap.put("resultCode", "0");
		returnMap.put("resultDesc", "操作成功");
		return returnMap;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> returnDataByMap(Map<String, Object> receiveMap, HttpServletRequest request) {
		if(request.getSession().getAttribute(SmsConstant.SESSION_USER_KEY)!=null){
			Map<String,Object> sessionMap = (Map<String, Object>) request.getSession().getAttribute(SmsConstant.SESSION_USER_KEY);
			receiveMap.put("userId", sessionMap.get("userId"));
			receiveMap.put("interfaceCode","setPayPassword2");
		}
		Map<String,Object> returnMap =  setPaymentPassImpl2.doService(receiveMap);
		return ConvertUtils.getMappingHintMessage("setPayPassword2", returnMap);
	}

}
