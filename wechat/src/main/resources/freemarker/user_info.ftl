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