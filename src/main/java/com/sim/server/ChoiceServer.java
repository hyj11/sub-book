package com.sim.server;

import com.sim.enums.OperaMethod;
import com.sim.enums.WebsiteChannel;
import com.sim.exception.ServiceException;
import com.sim.fectory.ChoiceServerFectory;
import com.sim.model.CommonModel;
import com.sim.service.BookService;
import com.sim.utils.ResponseObj;

/**
 * @author huangyujiao on 2018/4/3.
 * 提供外部调用方法，通过此方法进入工厂方法
 * @version 1.0
 */
public abstract class ChoiceServer {

    public static <E extends CommonModel> ResponseObj process(E req, WebsiteChannel websiteChannel, OperaMethod operaMethod) {
        ResponseObj obj = new ResponseObj();
        if (req == null || websiteChannel == null || operaMethod == null) {
            throw new ServiceException("必要参数为空");
        }
        BookService bookService = ChoiceServerFectory.getBookOperate(websiteChannel, operaMethod);
        if (null == bookService) {
            return ResponseObj.failed(ResponseObj.CODE_FAILED, "请求方法不存在");
        }
        obj = bookService.process(req);
        return obj;

    }
}
