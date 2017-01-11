<#include "/base/layout.ftl"/> <@layout "USER_CENTER" "home">

<div style="position: fixed; width: 100%; text-align: center; top: 0;">
	<#include "/navigator.ftl"/>

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
