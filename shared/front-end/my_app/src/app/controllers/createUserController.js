module.exports = ['$scope', '$http', 'authService', '$mdToast', '$httpParamSerializerJQLike', '$state', function ($scope, $http, authService, $mdToast, $httpParamSerializerJQLike, $state) {
    function toast(msg) {
        $mdToast.show(
            $mdToast.simple()
                .textContent(msg)
                .position('bottom right')
                .hideDelay(3000)
        );
    }





    $scope.postCreateUser = function(username,email,password,phNumber,name,address,type){
        return $http.post(BACKEND_URL+'/api/users/',{
            "type": type,
            "name": name,
            "address": address,
            "phoneNumber": phNumber,
            "username": username,
            "password": password,
            "email":email
        },{
            headers:{
                "authorization": authService.getAuth()
            }
        });
    }

    $scope.createUser=function(username,email,password,phNumber,name,address,type)
    {
        $scope.postCreateUser(username,email,password,phNumber,name,address,type).then(function successCallback(response) {
    }, function errorCallback(response) {
            if(response.status==401)
            {
                alert('Error : Invalid Email Address : Email Address Already Exists or Invalid Password : Password should be 8 to 15 characters in length and must have uppercase characters and special characters');
            }
            if(response.status==500)
            {
                alert('Error : Unauthorized');
            }
            else {
                if (response.status != 200) {
                    alert('Error : Unauthorized');
                }
            }

    });


    }

    $scope.postcreateAccount=function(userID,amount,accountType){

        {
            return $http.post(BACKEND_URL+'/api/accounts/',{
                "userId": userID,
                "amount": amount,
                "accountType": accountType
            },{
                headers:{
                    "authorization": authService.getAuth()
                }
            });
        }

    }

    $scope.createAccount=function(userID,amount,accountType){
        $scope.postcreateAccount(userID,amount,accountType);
    }




}];

