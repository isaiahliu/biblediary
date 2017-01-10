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