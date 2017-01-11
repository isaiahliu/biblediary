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