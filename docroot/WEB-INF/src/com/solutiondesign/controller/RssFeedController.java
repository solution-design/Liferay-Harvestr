package com.solutiondesign.controller;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.solutiondesign.service.FeedService;

@RequestMapping("VIEW")
public class RssFeedController {
	@Autowired 
	FeedService feedService;
	
    void handleActionRequest(ActionRequest request, ActionResponse response) {
    	
    }

	
	@RenderMapping
	public String render() {
		return "view";
	}
	
	public String render(
			PortletConfig portletConfig, RenderRequest renderRequest,
			RenderResponse renderResponse)
		throws Exception {

		String cmd = ParamUtil.getString(renderRequest, Constants.CMD);

		if (cmd.equals(Constants.ADD)) {
			return "/edit_field.jsp";
		}
		else {
			return "/configuration.jsp";
		}
	}
	
	@RenderMapping (params="view=edit") 
	public String handleEdit (Model model) {
		model.addAttribute("feeds",feedService.myFeeds());
		return "edit";
	}

	//http://localhost:8080/web/guest/welcome?p_p_id=86&p_p_lifecycle=0&p_p_state=pop_up&p_p_col_id=column-2
	//&p_p_col_count=2&_86_struts_action=%2Fportlet_configuration%2Fedit_permissions&_86_redirect=%2F
	//&_86_returnToFullPageURL=%2F&_86_portletResource=RSS_WAR_RSSportlet&
	//_86_resourcePrimKey=10574_LAYOUT_RSS_WAR_RSSportlet&_86_&yui_patched_v3_11_0_1_1392746227752_1217=1392746240216
	
	@ActionMapping("portlet_configuration")
	public void config() {
		
	}
	
    @ActionMapping ("delete")
    public void deleteFeed(int id) {

    }
}