package com.sim.fectory;

import com.sim.enums.OperaMethod;
import com.sim.enums.WebsiteChannel;
import com.sim.service.BookService;
import com.sim.service.impl.GetBookServiceImpl;

/**
 * Created with IntelliJ IDEA.
 * User: huangyj
 * 工厂方法，根据传入的渠道和方法判断调用那个方法
 * Date: 2019/7/24
 */
public class ChoiceServerFectory {

    /**
     * 根据下载渠道和下载方法
     *
     * @param websiteChannel 下载渠道
     * @param operaMethod  下载方法
     * @return
     */
    public static BookService getBookOperate(WebsiteChannel websiteChannel, OperaMethod operaMethod) {
        if (websiteChannel.equals(websiteChannel.BOOKBAO)) {
            if (operaMethod.equals(operaMethod.DOWN_BOOK)) {
                return new GetBookServiceImpl();
            }
        }
        return null;
    }
}
