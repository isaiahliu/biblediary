<#include "/base/layout.ftl"/>

<@layout "USER_CENTER" "home">

<div style="position: fixed; width: 100%; text-align: center; top: 0;">
	<div style="width: 100%; text-align: left;" class="ng-hide" ng-show="user">
		<div class="navItem">
			<a href="javascript:void(0)" ng-click="page='USER_INFO'"><span><@button "USER_INFO"/></span></a>
		</div>
		<div class="navItem ng-hide" ng-show="user.nickName!=null">
			<a href="javascript:void(0)" ng-click="page='PLAN';joiningPlan=null;"><span><@button "PLAN"/></span></a>
		</div>
		<div class="navItem ng-hide" ng-if="user.admin.code=='Y'">
			<a href="javascript:void(0)" ng-click="page='ADMIN'"><span><@button "ADMIN"/></span></a>
		</div>
	</div>

	<div style="width: 100%; text-align: center;" class="ng-hide" ng-show="message">
		<span>{{message}}</span>
	</div>
</div>

<div class="home ng-hide" ng-show="user">
	<div class="ng-hide" ng-show="page=='USER_INFO'">
		<div class="title">
			<span><@info "USER_INFO"/></span>
		</div>
		<div style="margin: 10px 0; height: 35px;">
			<span class="key" style=""><@label "NAME"/></span><input style="border-color: transparent;" ng-hide="editing" class="value"
				ng-model="user.nickName" readonly="readonly"
			/><input ng-show="editing" class="value" ng-model="inputUser.nickName" />
		</div>
		<div style="margin: 10px 0; height: 35px;">
			<span class="key"><@label "CELLPHONE"/></span><input style="border-color: transparent;" ng-hide="editing" class="value"
				ng-model="user.cellphone" readonly="readonly"
			/><input ng-show="editing" class="value" ng-model="inputUser.cellphone" />
		</div>
		<div style="margin: 10px 0; height: 35px;">
			<span class="key"><@label "CHURCH"/></span><span ng-hide="editing" class="value">{{user.church.name}}</span><select ng-show="editing"
				ng-model="inputUser.church" ng-options="church.name for church in churches" class="value"
			></select>
		</div>
		<div style="margin: 10px 0; height: 35px;">
			<span class="key"><@label "TIME_ZONE"/></span><span ng-hide="editing" class="value">{{user.timeZone.message}}</span><select
				ng-show="editing" ng-model="inputUser.timeZone" ng-options="timeZone.message for timeZone in timeZones" class="value"
			></select>
		</div>
		<div>
			<button ng-click="changeEditingStatus(true)" ng-hide="editing">
				<span style="font-size: 18px;"><@button "EDIT"/></span>
			</button>
			<button ng-click="bind()" ng-show="editing">
				<span style="font-size: 18px;"><@button "BIND"/></span>
			</button>
			<button ng-click="changeEditingStatus(false)" ng-show="editing&&user.nickName!=null">
				<span style="font-size: 18px;"><@button "CANCEL"/></span>
			</button>
		</div>
	</div>
	<div ng-show="page=='PLAN'">
		<div ng-hide="joiningPlan">
			<div class="title">
				<span><@info "PLAN"/></span>
			</div>

			<div ng-repeat="plan in plans" style="padding: 10px; border-bottom: 1px solid #dddddd;">
				<span>{{plan.name.message}} </span><a ng-hide="plan.joined" href="javascript:void(0)" ng-click="join(plan)"><span><@button
						"JOIN"/></span></a><span ng-show="plan.joined"><@label "JOINED"/></span>
			</div>
		</div>
		<div ng-show="joiningPlan" style="text-align: center;">
			<div class="title">
				<span><@info "JOIN_PLAN"/></span>
			</div>
			<div style="height: 150px; width: 100%; margin-bottom: 20px;">
				<textarea style="height: 150px; width: 90%; overflow: scroll; font-size: 16px;" readonly="readonly"><@info "MY_WORDS"/></textarea>
			</div>
			<div>
				<input style="height: 25px;" type="checkbox" ng-model="agree"> <span><@info "I_HAVE_READ_AND_AGREE"/></span>
			</div>
			<div>
				<button ng-show="agree" ng-click="agreeAndJoin()">
					<span><@button "JOIN"/></span>
				</button>
			</div>
		</div>
	</div>
</div>

<div class="home ng-hide" style="padding-top: 30%;" ng-hide="user">
	<span><@info "FETCHING_USER_INFO"/></span>
</div>

</@layout>
