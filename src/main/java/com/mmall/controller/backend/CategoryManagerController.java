package com.mmall.controller.backend;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.ICategoryService;
import com.mmall.service.IUserService;
import com.mmall.util.CookieUtil;
import com.mmall.util.JsonUtil;
import com.mmall.util.RedisShardedPoolUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/manager/category")
public class CategoryManagerController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private ICategoryService iCategoryService;

    @RequestMapping("add_category.do")
    @ResponseBody
    public ServerResponse addCategory(HttpServletRequest httpServletRequest, String categoryName,
                                      @RequestParam(value = "parentId",defaultValue = "0") int parentId){

        /*//将原本的从session中获取用户信息转换为从cookie中获取
        String loginToken = CookieUtil.readLoginToken(httpServletRequest);//获取cookie中的值，其值在redis中表现为key
        if (StringUtils.isEmpty(loginToken)){
            return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户的信息");
        }
        String userJsonStr = RedisShardedPoolUtil.get(loginToken);//利用上面拿到的redis中的key取出其对应的值
        User user = JsonUtil.string2Obj(userJsonStr,User.class);


        //校验是否为管理员
        if (iUserService.checkAdminRole(user).isSuccess()){
            //是管理员
            return iCategoryService.addCategory(categoryName,parentId);
        }else {
            return ServerResponse.createBySuccessMessage("无权限操作，请更换管理员权限");
        }*/
        return iCategoryService.addCategory(categoryName,parentId);
    }

    @RequestMapping("set_category_name.do")
    @ResponseBody
    public ServerResponse setCategoryName(HttpServletRequest httpServletRequest, Integer categoryId,String categoryName){
        /*//将原本的从session中获取用户信息转换为从cookie中获取
        String loginToken = CookieUtil.readLoginToken(httpServletRequest);//获取cookie中的值，其值在redis中表现为key
        if (StringUtils.isEmpty(loginToken)){
            return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户的信息");
        }
        String userJsonStr = RedisShardedPoolUtil.get(loginToken);//利用上面拿到的redis中的key取出其对应的值
        User user = JsonUtil.string2Obj(userJsonStr,User.class);
        //校验是否为管理员
        if (iUserService.checkAdminRole(user).isSuccess()){
            //更新categoryName
            return iCategoryService.updateCategoryName(categoryId,categoryName);

        }else {
            return ServerResponse.createBySuccessMessage("无权限操作，请更换管理员权限");
        }*/
        return iCategoryService.updateCategoryName(categoryId,categoryName);

    }

    //平级子节点
    @RequestMapping("get_category.do")
    @ResponseBody
    public ServerResponse getChildrenParallelCategory(HttpServletRequest httpServletRequest, @RequestParam(value = "categoryId",defaultValue = "0")Integer categoryId){
        /*//将原本的从session中获取用户信息转换为从cookie中获取
        String loginToken = CookieUtil.readLoginToken(httpServletRequest);//获取cookie中的值，其值在redis中表现为key
        if (StringUtils.isEmpty(loginToken)){
            return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户的信息");
        }
        String userJsonStr = RedisShardedPoolUtil.get(loginToken);//利用上面拿到的redis中的key取出其对应的值
        User user = JsonUtil.string2Obj(userJsonStr,User.class);
        //校验是否为管理员
        if (iUserService.checkAdminRole(user).isSuccess()){
            //查询子节点的category信息，并且不递归，保持平级
            return iCategoryService.getChildrenCategory(categoryId);

        }else {
            return ServerResponse.createBySuccessMessage("无权限操作，请更换管理员权限");
        }*/
        return iCategoryService.getChildrenCategory(categoryId);

    }

    //递归子节点
    @RequestMapping("get_deep_category.do")
    @ResponseBody
    public ServerResponse getCategoryAndDeepChildCategory(HttpServletRequest httpServletRequest, @RequestParam(value = "categoryId",defaultValue = "0")Integer categoryId){
        /*//将原本的从session中获取用户信息转换为从cookie中获取
        String loginToken = CookieUtil.readLoginToken(httpServletRequest);//获取cookie中的值，其值在redis中表现为key
        if (StringUtils.isEmpty(loginToken)){
            return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户的信息");
        }
        String userJsonStr = RedisShardedPoolUtil.get(loginToken);//利用上面拿到的redis中的key取出其对应的值
        User user = JsonUtil.string2Obj(userJsonStr,User.class);
        //校验是否为管理员
        if (iUserService.checkAdminRole(user).isSuccess()){
            //查询当前节点的id和递归子节点的id
            return iCategoryService.selectCategoryAndChildrenById(categoryId);

        }else {
            return ServerResponse.createBySuccessMessage("无权限操作，请更换管理员权限");
        }*/
        return iCategoryService.selectCategoryAndChildrenById(categoryId);

    }


}
