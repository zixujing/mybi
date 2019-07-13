/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 ‭‭‭‭‭‭‭‭‭‭‭‭[smallbun] www.smallbun.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.smallbun.fast.manage.notify.service;

import org.smallbun.fast.manage.notify.entity.SysNotifyEntity;
import org.smallbun.fast.manage.notify.vo.SysNotifyVO;
import org.smallbun.framework.base.BaseService;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

/**
 * 通知通告 服务类
 *
 * @author SanLi
 * Created by 2689170096@qq.com on 2019/2/14 19:23
 */
public interface SysNotifyService extends BaseService<SysNotifyEntity> {
	/**
	 * model
	 *
	 * @param request
	 * @return
	 */
	SysNotifyVO model(HttpServletRequest request);

	/**
	 * 保存或更新
	 *
	 * @param vo
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	boolean saveOrUpdate(SysNotifyVO vo);

	/**
	 * 根据id获取一条记录
	 *
	 * @param id
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	SysNotifyVO getById(Serializable id);

	/**
	 * 根据用户id获取未阅读的通知公告
	 * @param userId
	 * @return
	 */
	List<SysNotifyEntity> findNotifyOnUnreadByUserId(Long userId);
}
