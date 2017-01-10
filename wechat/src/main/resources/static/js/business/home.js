layoutApp.controller('contentController', function($scope, $http, $window, $timeout) {
	$scope.inputUser = {};
	$scope.page = "USER_INFO";
	$scope.joiningPlan = null;
	$scope.subNavBar = "";

	$http({
		method : "GET",
		url : "/ajax/church"
	}).success(function(response) {
		$scope.churches = response.data;
	}).error(function(response) {
	});

	$http({
		method : "GET",
		url : "/ajax/user?rsexp=plans"
	}).success(function(response) {
		$scope.user = response;
		$scope.changeEditingStatus($scope.user.nickName == "" || $scope.user.nickName == null);

		$http({
			method : "GET",
			url : "/ajax/plan"
		}).success(function(response) {
			for (var i = 0; i < response.data.length; i++) {
				response.data[i].joined = false;
				for (var j = 0; j < $scope.user.plans.length; j++) {
					if (response.data[i].id == $scope.user.plans[j].id) {
						response.data[i].joined = true;
						break;
					}
				}
			}

			$scope.plans = response.data;
		}).error(function(response) {
		});
	}).error(function(response) {
	});

	$http({
		method : "GET",
		url : "/ajax/lookup/TZONE"
	}).success(function(response) {
		$scope.timeZones = response.data;
	}).error(function(response) {
	});

	$scope.bind = function() {
		$http({
			method : "PUT",
			url : "/ajax/user",
			data : {
				nickName : $scope.inputUser.nickName,
				cellphone : $scope.inputUser.cellphone,
				timeZone : {
					code : $scope.inputUser.timeZone.code
				}
			}
		}).success(function(response) {
			$scope.user.nickName = $scope.inputUser.nickName;
			$scope.user.cellphone = $scope.inputUser.cellphone;
			$scope.user.timeZone = $scope.inputUser.timeZone;

			$scope.showMessage("更新成功");
			$scope.editing = false;
		}).error(function(response) {
			$scope.showMessage("更新失败");
			$scope.editing = false;
		});
	};

	$scope.changeEditingStatus = function(flag) {
		if (flag) {
			$scope.inputUser.nickName = $scope.user.nickName;
			$scope.inputUser.cellphone = $scope.user.cellphone;

			$scope.inputUser.timeZone = null;
			for (var index = 0; index < $scope.timeZones.length; index++) {
				if ($scope.inputUser.timeZone == null && $scope.timeZones[index].code == "E8") {
					$scope.inputUser.timeZone = $scope.timeZones[index];
				}

				if ($scope.user.timeZone) {
					if ($scope.user.timeZone.code == $scope.timeZones[index].code) {
						$scope.inputUser.timeZone = $scope.timeZones[index];
						break;
					}
				}
			}
		}

		$scope.editing = flag;
	};

	$scope.showMessage = function(message) {
		$scope.message = message;

		$timeout(function() {
			$scope.message = "";
		}, 2000);
	};

	$scope.join = function(plan) {
		$scope.agree = false;
		$scope.joiningPlan = plan;
	};

	$scope.agreeAndJoin = function() {
		$http({
			method : "POST",
			url : "/ajax/plan/join/" + $scope.joiningPlan.id
		}).success(function(response) {
			$scope.joiningPlan.joined = true;
			$scope.joiningPlan = null;
			$scope.showMessage("加入成功!");
		}).error(function(response) {
		});
	};

	$scope.showSubNavBar = function(subNavBar) {
		if ($scope.subNavBar == subNavBar) {
			$scope.subNavBar = "";
		} else {
			$scope.subNavBar = subNavBar;
		}
	}
});