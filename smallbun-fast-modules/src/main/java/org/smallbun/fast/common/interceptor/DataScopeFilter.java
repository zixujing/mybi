package org.smallbun.fast.common.interceptor;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.smallbun.fast.manage.role.entity.SysRoleEntity;
import org.smallbun.fast.manage.user.entity.SysUserEntity;
import org.smallbun.fast.manage.user.util.UserUtil;

import java.util.List;
import java.util.Objects;

import static org.smallbun.framework.permission.constant.DataScopeConstant.*;
import static org.smallbun.framework.permission.constant.DataScopeConstant.DATA_SCOPE_ALL;
import static org.smallbun.framework.toolkit.StringUtil.SPLIT_DEFAULT;

/**
 *
 * @author SanLi [隔壁object港哥][https://www.leshalv.net]
 * Created by 2689170096@qq.com on  2019-03-01 17:35
 */
@Slf4j
public class DataScopeFilter {
	/**
	 * 当前用户 数据范围过滤 机构表别名 org 用户表别名 creator
	 *
	 * @return 标准连接条件对象
	 */
	public static StringBuilder dataScopeFilterSql(String orgAlias, String userAlias) {
		return dataScopeFilter(String.valueOf(Objects.requireNonNull(UserUtil.getLoginUser()).getSysUser().getId()),
				orgAlias, userAlias);
	}

	/**
	 * 数据范围过滤
	 *
	 * @param userId    当前用户对象，通过“entity.getCurrentUser()”获取
	 * @param orgAlias  机构表别名，多个用“,”逗号隔开。
	 * @param userAlias 用户表别名，多个用“,”逗号隔开，传递空，忽略此参数
	 * @return 标准连接条件对象
	 */
	public static StringBuilder dataScopeFilterSql(String userId, String orgAlias, String userAlias) {
		return dataScopeFilter(userId, orgAlias, userAlias);
	}

	/**
	 * 当前用户 数据范围过滤 机构表别名 org 用户表别名 creator
	 *
	 * @return 标准连接条件对象
	 */
	public static StringBuilder dataScopeFilter() {
		return dataScopeFilter(String.valueOf(Objects.requireNonNull(UserUtil.getLoginUser()).getSysUser().getId()),
				"org", "creator");
	}


	/**
	 * 数据范围过滤
	 *
	 * @param userId    当前用户对象，通过“entity.getCurrentUser()”获取
	 * @param orgAlias  机构表别名，多个用“,”逗号隔开。
	 * @param userAlias 用户表别名，多个用“,”逗号隔开，传递空，忽略此参数
	 * @return 标准连接条件对象
	 */
	private static StringBuilder dataScopeFilter(String userId, String orgAlias, String userAlias) {
		// 进行权限过滤，多个角色权限范围之间为或者关系。
		List<String> dataScope = Lists.newArrayList();
		//数据范围描述
		List<String> dataScopeDescribe = Lists.newArrayList();
		//角色名称
		List<String> rolesName = Lists.newArrayList();
		//权限SQL语句
		StringBuilder sqlString = new StringBuilder();
		//获取当前用户
		SysUserEntity user = Objects.requireNonNull(UserUtil.getLoginUser()).getSysUser();
		// 超级管理员，跳过权限过滤
		if (!isAdmin(userId)) {
			boolean isDataScopeAll = false;
			//获取用户角色集合
			for (SysRoleEntity r : user.getRoleList()) {
				for (String oa : StringUtils.split(orgAlias, SPLIT_DEFAULT)) {
					if (!dataScope.contains(String.valueOf(r.getDataScope())) && StringUtils.isNotBlank(oa)) {
						//全部数据
						if (DATA_SCOPE_ALL.getNumber().equals(r.getDataScope())) {
							dataScopeDescribe.add(DATA_SCOPE_ALL.getDescribe());
							isDataScopeAll = true;
						}
						//所在公司及以下数据
						if (DATA_SCOPE_COMPANY_AND_CHILD.getNumber().equals(r.getDataScope())) {
							dataScopeDescribe.add(DATA_SCOPE_COMPANY_AND_CHILD.getDescribe());
							dataScopeCompanyAndChild(sqlString, user, oa);
						}
						//所在公司数据
						if (DATA_SCOPE_COMPANY.getNumber().equals(r.getDataScope())) {
							dataScopeDescribe.add(DATA_SCOPE_COMPANY.getDescribe());
							dataScopeCompany(sqlString, user, oa);
						}
						//所在部门及以下数据
						if (DATA_SCOPE_ORG_AND_CHILD.getNumber().equals(r.getDataScope())) {
							dataScopeDescribe.add(DATA_SCOPE_ORG_AND_CHILD.getDescribe());
							dataScopeOrgAndChild(sqlString, user, oa);
						}
						//所在部门数据
						if (DATA_SCOPE_ORG.getNumber().equals(r.getDataScope())) {
							dataScopeDescribe.add(DATA_SCOPE_ORG.getDescribe());
							dataScopeOrg(sqlString, user, oa);
						}
						//按明细设置
						if (DATA_SCOPE_CUSTOM.getNumber().equals(r.getDataScope())) {
							dataScopeDescribe.add(DATA_SCOPE_CUSTOM.getDescribe());
							//从缓存获取
							//dataScopeCustomCache(sqlString, r, oa);
							//使用SQL关联查询
							dataScopeCustom(sqlString, r, oa);
						}
						//添加当前用户权限
						dataScope.add(r.getDataScope());
						//添加角色名称
						rolesName.add(r.getRoleName());
					}
				}
			}
			sqlString = notIsDataScopeAll(orgAlias, userAlias, dataScope, dataScopeDescribe, sqlString, user,
					isDataScopeAll);
		}
		//如果SQL语句不为空
		if (StringUtils.isNotBlank(sqlString.toString())) {
			sqlString.append(" AND (").append(sqlString.substring(4)).append(")");
		}
		logOutput(dataScope, dataScopeDescribe, rolesName, sqlString, user);
		return sqlString;
	}

	private static StringBuilder notIsDataScopeAll(String orgAlias, String userAlias, List<String> dataScope,
			List<String> dataScopeDescribe, StringBuilder sqlString, SysUserEntity user, boolean isDataScopeAll) {
		// 如果没有全部数据权限，并设置了用户别名，则当前权限为本人；如果未设置别名，当前无权限为已植入权限
		if (!isDataScopeAll) {
			dataScopeDescribe.add(DATA_SCOPE_SELF.getDescribe());
			//本人权限
			if (StringUtils.isNotBlank(userAlias)) {
				for (String ua : StringUtils.split(userAlias, SPLIT_DEFAULT)) {
					sqlString.append(" OR ").append(ua).append(".id = '").append(user.getId()).append("'");
				}
			} else {
				for (String oa : StringUtils.split(orgAlias, SPLIT_DEFAULT)) {
					/*sqlString.append(" OR ").append(oa).append(".id  = ").append(user.getOrg().getId());*/
					sqlString.append(" OR ").append(oa).append(".id IS NULL");
				}
			}
		}
		// 如果包含全部权限，则去掉之前添加的所有条件，并跳出循环。
		else {
			dataScope.clear();
			sqlString = new StringBuilder();
		}
		return sqlString;
	}

	private static void logOutput(List<String> dataScope, List<String> dataScopeDescribe, List<String> rolesName,
			StringBuilder sqlString, SysUserEntity user) {
		if (!isAdmin(String.valueOf(user.getId()))) {
			log.info("用户名称：{}", user.getUsername());
			log.info("拥有角色：{}", rolesName);
			log.info("数据范围：{}", dataScopeDescribe);
			if (!dataScope.contains(String.valueOf(DATA_SCOPE_ALL))) {
				log.info("过滤语句：{}", sqlString.toString());
			}
		} else {
			log.info("超级管理员拥有全部权限");
		}

	}

	private static void dataScopeCustom(StringBuilder sqlString, SysRoleEntity r, String oa) {
		sqlString.append(" OR EXISTS (SELECT 1 FROM sys_role_org WHERE role_id = '").append(r.getId()).append("'");
		sqlString.append(" AND org_id = ").append(oa).append(".id)");
	}

	private static void dataScopeCustomCache(StringBuilder sqlString, SysRoleEntity r, String oa) {
		//第一种方案，从缓存中取数据范围
		String orgIds = StringUtils.join(r.getOrgList(), "','");
		if (StringUtils.isNotEmpty(orgIds)) {
			sqlString.append(" OR ").append(oa).append(".id IN ('").append(orgIds).append("')");
		}
	}

	private static void dataScopeOrg(StringBuilder sqlString, SysUserEntity user, String oa) {
		sqlString.append(" OR ").append(oa).append(".id = '").append(user.getOrg().getId()).append("'");
	}

	private static void dataScopeOrgAndChild(StringBuilder sqlString, SysUserEntity user, String oa) {
		sqlString.append(" OR ").append(oa).append(".id = '").append(user.getOrg().getId()).append("'");
		sqlString.append(" OR ").append(oa).append(".parent_ids LIKE '").append(user.getOrg().getParentIds())
				.append(user.getOrg().getId()).append(",%'");
	}

	private static void dataScopeCompany(StringBuilder sqlString, SysUserEntity user, String oa) {
		sqlString.append(" OR ").append(oa).append(".id = '").append(user.getCompany().getId()).append("'");
		// 包括本公司下的部门 （type=1:公司；type=2：部门）
		sqlString.append(" OR (").append(oa).append(".parent_id = '").append(user.getCompany().getId()).append("' AND ")
				.append(oa).append(".type = '2')");
	}

	private static void dataScopeCompanyAndChild(StringBuilder sqlString, SysUserEntity user, String oa) {
		sqlString.append(" OR ").append(oa).append(".id = '").append(user.getCompany().getId()).append("'");
		sqlString.append(" OR ").append(oa).append(".parent_ids LIKE '").append(user.getCompany().getParentIds())
				.append(user.getCompany().getId()).append(",%'");
	}

	/**
	 * admin id default 1
	 */
	private final static String ADMIN_ID = "1";

	/**
	 * 是否是Admin
	 * @param userId 用户ID
	 * @return 是 否
	 */
	private static boolean isAdmin(String userId) {
		return ADMIN_ID.equals(userId);
	}
}
