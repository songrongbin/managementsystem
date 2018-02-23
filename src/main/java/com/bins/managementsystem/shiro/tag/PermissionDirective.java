package com.bins.managementsystem.shiro.tag;

import java.io.IOException;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 后台管理员权限许可
 * 
 * @author andybin
 */
public class PermissionDirective implements TemplateDirectiveModel {

    /***
     * 权限验证
     */
    @SuppressWarnings("unchecked")
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        /*String url = DirectiveUtils.getString(Constants.PARAM_URL, params);
        Subject subject = SecurityUtils.getSubject();
        boolean pass = subject.isPermitted(url);
        if (pass) {
            body.render(env.getOut());
        }*/
    }
}
