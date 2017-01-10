<#include "/base/layout.ftl"/> <@layout "USER_CENTER" "home">

<div style="position: fixed; width: 100%; text-align: center; top: 0;">
	<div style="width: 100%; text-align: left;" class="ng-hide" ng-show="user">
		<div class="navItem">
			<a href="javascript:void(0)" ng-click="showSubNavBar('USER_INFO')"><span><@button "USER_INFO"/></span></a>
		</div>
		<div class="navItem ng-hide" ng-show="user.nickName!=null">
			<a href="javascript:void(0)" ng-click="showSubNavBar('PLAN');page='PLAN';joiningPlan=null;"><span><@button "PLAN"/></span></a>
		</div>
		<div class="navItem ng-hide" ng-if="user.admin.code=='Y'">
			<a href="javascript:void(0)" ng-click="showSubNavBar('ADMIN');page='ADMIN';"><span><@button "ADMIN"/></span></a>
		</div>
	</div>

	<div class="subNavBar ng-hide" style="padding-left: 0;" ng-show="subNavBar=='USER_INFO'">
		<div class="subNavItem">
			<a href="javascript:void(0)" ng-click="showSubNavBar('USER_INFO');page='USER_INFO';"><span><@button "USER_DETAILS"/></span></a>
		</div>
		<div class="subNavItem">
			<a href="javascript:void(0)" ng-click="showSubNavBar('USER_INFO');page='CHURCH';"><span><@button "CHURCH"/></span></a>
		</div>
	</div>

	<div style="width: 100%; text-align: center;" class="ng-hide" ng-show="message">
		<span>{{message}}</span>
	</div>
</div>

<div class="home ng-hide" ng-show="user"><#include "/plan.ftl"/> <#include "/admin.ftl"/> <#include "/church.ftl"/> <#include
	"/user_info.ftl"/></div>
<div class="home ng-hide" style="padding-top: 30%;" ng-hide="user">
	<span><@info "FETCHING_USER_INFO"/></span>
</div>

</@layout>
