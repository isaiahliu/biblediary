layoutApp.controller('contentController', function($scope, $http, $window, $timeout) {
	$http({
		method : "GET",
		url : "/ajax/church"
	}).success(function(response) {
		$scope.churches = response.data;

	}).error(function(response) {
		$scope.churches = [ {
			id : 1,
			name : "sanyi"
		} ];
	});

	$http({
		method : "GET",
		url : "/ajax/user?rsexp=church"
	}).success(function(response) {
		$scope.user = response;
		$scope.changeEditingStatus($scope.user.nickName == null);
	}).error(function(response) {
		$scope.user = {
			nickName : "1",
			cellphone : "123",
			timeZone : {
				code : "a",
				message : "fewfwef"
			}
		};
	});

	$http({
		method : "GET",
		url : "/ajax/lookup/TZONE"
	}).success(function(response) {
		$scope.timeZones = response.data;
	}).error(function(response) {
		$scope.timeZones = [ {
			code : "a",
			message : "aaa"
		}, {
			code : "b",
			message : "vbb"
		}, {
			code : "E8",
			message : "dong8"
		}, ];
	});

	$scope.bind = function() {
		$http({
			method : "PUT",
			url : "/ajax/user",
			data : {
				nickName : $scope.newNickName,
				cellphone : $scope.newCellphone,
				church : {
					id : $scope.newChurch.id
				},
				timeZone : {
					code : $scope.newTimeZone.code
				}
			}
		}).success(function(response) {
			$scope.user.nickName = $scope.newNickName;
			$scope.user.cellphone = $scope.newCellphone;
			$scope.user.church = $scope.newChurch;
			$scope.user.timeZone = $scope.newTimeZone;

			$scope.showMessage("更新成功");
			$scope.editing = false;
		}).error(function(response) {
			$scope.showMessage("更新失败");
			$scope.editing = false;
		});
	};

	$scope.changeEditingStatus = function(flag) {
		if (flag) {
			$scope.newNickName = $scope.user.nickName;
			$scope.newCellphone = $scope.user.cellphone;
			for (var index = 0; index < $scope.churches.length; index++) {
				$scope.newChurch = $scope.churches[index];

				if ($scope.user.church) {
					if ($scope.user.church.id == $scope.newChurch.id) {
						break;
					}
				}
			}

			$scope.newTimeZone = null;
			for (var index = 0; index < $scope.timeZones.length; index++) {
				if ($scope.newTimeZone == null && $scope.timeZones[index].code == "E8") {
					$scope.newTimeZone = $scope.timeZones[index];
				}

				if ($scope.user.timeZone) {
					if ($scope.user.timeZone.code == $scope.timeZones[index].code) {
						$scope.newTimeZone = $scope.timeZones[index];
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
		}, 3000);
	};
});