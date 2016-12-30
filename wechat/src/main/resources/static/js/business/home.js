layoutApp.controller('contentController', function($scope, $http, $window) {
	$http({
		method : "GET",
		url : "/ajax/user"
	}).success(function(response) {
		$scope.editing = true;
		$scope.user = response;
		$scope.editing = $scope.user.nickName == null;
		$scope.newNickName = $scope.user.nickName;
		$scope.newCellphone = $scope.user.cellphone;
	}).error(function(response) {
	});

	$scope.bind = function() {
		$http({
			method : "PUT",
			url : "/ajax/user",
			data : {
				nickName : $scope.newNickName,
				cellphone : $scope.newCellphone
			}
		}).success(function(response) {
			$scope.message = "更新成功";
			$scope.user.nickName = $scope.newNickName;
			$scope.user.cellphone = $scope.newCellphone;

			$scope.editing = false;
		}).error(function(response) {
			$scope.message = "更新失败";
		});
	}

});