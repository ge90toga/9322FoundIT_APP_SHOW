foundITApp.controller('seekerListCtrl', function ($scope, seekerService, toaster, $location, $timeout) {
    $scope.init = function () {
        console.log('seekerListCtrl ctrl');
        $scope.data = {
            jobList: [],
            selectionList: ['Full Time', 'PartTime', 'Casual'],
            search: {
                title: '',
                type: ''
            }
        };
        $scope.data.search.type = $scope.data.selectionList[0];
        $scope.loadJobList();
    };

    $scope.loadJobList = function () {
        seekerService.getJobList().then(function success(jobList) {
            $scope.data.jobList = jobList;
        }, function error(err) {
            toaster.pop('error', 'Loading list error', '');
            console.log('loading error');
        })
    };


    $scope.init();
});