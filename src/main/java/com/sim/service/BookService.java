package com.sim.service;

import com.sim.model.CommonModel;
import com.sim.utils.ResponseObj;

/**
 * Created with IntelliJ IDEA.
 * User: huangyj
 * Date: 2019/7/25
 */
public interface BookService {

    <E extends CommonModel> ResponseObj process(E req);
}
