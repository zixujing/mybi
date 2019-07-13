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

package org.smallbun.fast.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.smallbun.fast.common.DataPermissionSqlParser;
import org.smallbun.fast.common.handler.BaseMetaObjectHandler;
import org.smallbun.fast.common.interceptor.PrepareInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;
import java.util.List;

/**
 * 配置mybatis plus
 * @author SanLi
 */
@EnableTransactionManagement
@Configuration
@MapperScan(value = "org.smallbun.fast.**.dao")
public class MybatisPlusConfig {
	/**
	 * SQL执行效率插件 设置 dev test 环境开启
	 * @return {@link PerformanceInterceptor}
	 */
	@Bean
	@Profile({"dev", "test"})
	public PerformanceInterceptor performanceInterceptor() {
		return new PerformanceInterceptor();
	}


	/**
	 * 配置数据权限过滤带有逻辑删除
	 * @return {@link LogicSqlInjector}
	 */
	@Bean
	public ISqlInjector dataScopeSqlInjector() {
		return new LogicSqlInjector();
	}

	/**
	 * 分页插件分页插件
	 * @return {@link PaginationInterceptor}
	 */
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		PaginationInterceptor interceptor = new PaginationInterceptor();
		List<ISqlParser> sqlParserList = new ArrayList<>();
		//数据权限解析
		sqlParserList.add(new DataPermissionSqlParser());
		interceptor.setSqlParserList(sqlParserList);

		return interceptor;
	}


	/**
	 * 配置 MetaObjectHandler
	 * @return {@link MetaObjectHandler}
	 */
	@Bean
	public MetaObjectHandler metaObjectHandler() {
		return new BaseMetaObjectHandler();
	}

	/**
	 * 数据权限过滤
	 * @return
	 */
	@Bean
	public PrepareInterceptor prepareInterceptor() {
		return new PrepareInterceptor();
	}
}
