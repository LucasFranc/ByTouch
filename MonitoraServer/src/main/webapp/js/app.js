(function () {
    var app = angular.module('store', ['ui.bootstrap']);

    app.controller('StoreController', function ($scope, $modal, $http) {
        this.products = gems;
        $scope.editProduct = function (selectedProduct) {
            var modalInstance = $modal.open({
                templateUrl: 'models/modalPageEditProduct.html',
                //controller : PopupCtrl,
                controller: function ($scope, $modalInstance, product) {
                    $scope.product = product;
                    $scope.ok = function () {
                        cadastrar = true;
                        var req = {
                            method: 'post',
                            url: 'http://localhost:8084/MonitoraServer/ServletMonitora?opcao=atualizar',
                            data: $scope.product
                        };
                        $http(req).then(function () {

                        });
                        $modalInstance.close($scope.product);
                    };
                    $scope.cancel = function () {
                        cadastrar = false;
                        $modalInstance.dismiss('cancel');
                    };
                },
                resolve: {
                    product: function () {
                        return selectedProduct;
                    }
                }
            });
        };

        $scope.addProduct = function () {
            $modalInstance = $modal.open({
                templateUrl: 'models/modalPageAddProduct.html',
                //controller : PopupCtrl,
                controller: function ($scope, $modalInstance, $http) {
                    $scope.ok = function () {
                        cadastrar = true;
                        $modalInstance.close();
                    };

                    $scope.cancel = function () {
                        cadastrar = false;
                        $modalInstance.dismiss('cancel');
                    };
                }
            });
        };

        $scope.getProduct = function () {
            $scope.newProduct = [];
            $http.get('http://localhost:8084/MonitoraServer/Produtos').
                    success(function (data, status, headers, config) {
                        var teste = null;
                        gems.length = 0;
                        angular.forEach(data, function (value, key) {
                            angular.forEach(value, function (value1, key1) {
                                teste = null;
                                teste = value;
                            });
                            //gems.push(teste);
                            $scope.newProduct.push(teste);
                        });
                        gems = $scope.newProduct;
                    }).
                    error(function (data, status, headers, config) {
                        console.log(data + " error");
                    });
        };

        $scope.deleteProduct = function (deleteProduct, index) {
            $scope.product = deleteProduct;
            var req = {
                method: 'post',
                url: 'http://localhost:8084/MonitoraServer/ServletMonitora?opcao=deletar',
                data: $scope.product
            };
            $http(req).then(function () {
                //var indexItem = gems.indexOf(index);
                gems.splice(index, 1);
            });
        };

        // at the bottom of your controller
        var init = function () {
            // check if there is query in url
            // and fire search in case its value is not empty
            $http.get('http://localhost:8084/MonitoraServer/Produtos').
                    success(function (data, status, headers, config) {
                        var teste = null;
                        angular.forEach(data, function (value, key) {
                            angular.forEach(value, function (value1, key1) {
                                teste = null;
                                teste = value;
                            });
                            gems.push(teste);
                        });
                        console.log(gems);
                    }).
                    error(function (data, status, headers, config) {
                        console.log(data + " error");
                    });
        };
        // and fire it after definition
        init();
    });

    app.controller('PanelController', function () {
        this.tab = 1;
        this.selectTab = function (setTab) {
            this.tab = setTab;
        };
        this.isSelected = function (checkTab) {
            return this.tab === checkTab;
        };
    });


    app.controller("ReviewController", function () {
        this.review = {};

        this.addReview = function (product) {
            product.reviews.push(this.review);
            this.review = {};
        };
    });

    app.directive('productPanels', function () {
        return{
            restrict: 'E',
            templateUrl: 'models/product-panels.html',
            controller: function () {
                this.tab = 1;
                this.selectTab = function (setTab) {
                    this.tab = setTab;
                };
                this.isSelected = function (checkTab) {
                    return this.tab === checkTab;
                };
            },
            controllerAs: 'panels'
        };
    });


    app.controller("GemController", function ($scope, $http) {
        this.gems = {};

        this.addGem = function () {
            if (cadastrar == true) {
                produto1 = this.gems;
                var req = {
                    method: 'post',
                    url: 'http://localhost:8084/MonitoraServer/ServletMonitora?opcao=cadastrar',
                    data: this.gems
                };
                $http(req).then(function () {
                    //gems.push(produto1);
                    $http.get('http://localhost:8084/MonitoraServer/Produtos').
                            success(function (data, status, headers, config) {
                                var teste = null;
                                var cont = 0;
                                gems.length = 0;
                                angular.forEach(data, function (value, key) {
                                    angular.forEach(value, function (value1, key1) {
                                        teste = null;
                                        teste = value;
                                    });
                                    gems.push(teste);
                                });
                                console.log(gems);
                            }).
                            error(function (data, status, headers, config) {
                                console.log(data + " error");
                            });
                    this.gems = {};
                });

            } else {
                console.log("cadastrar == false");
            }
        };


    });

    app.controller("ProductUpdateController", function ($scope) {
        this.update = function (updatedProduct) {
            var product = updatedProduct;

            /*product.$update(function(){  //update product on DB
             
             });*/
        };
    });

    app.directive('productsList', function () {
        return{
            restrict: 'E',
            templateUrl: 'models/productsListView.html'
        };
    });

    app.directive('sideMenu', function () {
        return{
            restrict: 'E',
            templateUrl: 'models/sideMenu.html'
        };
    });

    /*app.controller('modalController',function($scope,$modal) {
     $scope.showPopup = function(selectedProduct) {
     var modalInstance = $modal.open({
     templateUrl : 'modal-Page.html',
     //controller : PopupCtrl,
     controller: function($scope,$modalInstance,product) {
     $scope.product = product;
     },
     resolve: {
     product: function () {
     return selectedProduct;
     }
     }
     })
     };
     
     $scope.addProduct = function() {
     $modal.open({
     templateUrl : 'modal-Page.html',
     controller : PopupCtrl
     })
     };
     
     $scope.editProduct = function(selectedProduct) {
     var modalInstanceEditProduct = $modal.open({
     templateUrl : 'modalPageEditProduct.html',
     //controller : PopupCtrl,
     controller: function($scope,$modalInstanceEditProduct,product) {
     $scope.product = product;
     },
     resolve: {
     product: function () {
     return selectedProduct;
     }
     }
     })
     };
     });
     
     var PopupCtrl = function($scope,$modalInstance,$http,items) {
     $scope.items = items;
     this.products = {
     item: $scope.items[0]
     };
     
     $scope.ok = function() {
     $modalInstance.close();	
     };
     
     $scope.cancel = function() {
     $modalInstance.dismiss('cancel');
     };
     this.editItem = function(product){
     this.selectedGem.name = product.name;
     this.selectedGem.price = product.price;
     //this.productName = product.name;
     
     };
     };*/

    /*$scope.items = items;
     $scope.selected = {
     item: $scope.items[0]
     };
     $scope.size = size;
     
     $scope.ok = function () {
     $modalInstance.close($scope.selected.item);
     };
     
     $scope.cancel = function () {
     $modalInstance.dismiss('cancel');
     };
     };*/

    var produto1 = {};
    var cadastrar = false;
    var gems = [];
    /*var gems = [{
     name: 'Guitarra Les Paul Strinberg',
     price: 1977,
     description: 'Descrição da guitarra',
     image: 'http://www.playtech.com.br/Imagens/produtos/14/321914/321914_Ampliada.jpg'
     }];*/
    /*var gems = [
     {
     name: 'Guitarra Les Paul Strinberg',
     price: 1977,
     description: 'Descrição da guitarra',
     canPurchase: false,
     images: [
     {
     full: 'http://www.playtech.com.br/Imagens/produtos/14/321914/321914_Ampliada.jpg',
     thumb: 'http://www.playtech.com.br/Imagens/produtos/14/321914/321914_Ampliada.jpg'
     }],
     reviews: [
     {
     stars: 5,
     body: "Review da guitarra1",
     author: "joe8@thomas.com"
     },
     {
     stars: 5,
     body: "Review da guitarra1",
     author: "joe8@thomas.com"
     },
     {
     stars: 5,
     body: "Review da guitarra1",
     author: "joe8@thomas.com"
     },
     {
     stars: 5,
     body: "Review da guitarra1",
     author: "joe8@thomas.com"
     },
     {
     stars: 5,
     body: "Review da guitarra4",
     author: "joe0@thomas.com"
     },
     {
     stars: 5,
     body: "Review da guitarra0",
     author: "joe4@thomas.com"
     },
     {
     stars: 5,
     body: "Review da guitarra8",
     author: "joe1@thomas.com"
     }]
     
     },
     {
     name: 'Monitor',
     price: 1994.0,
     description: 'Descrição do monitor',
     canPurchase: true,
     images: [
     {
     full: 'http://www.bestbuy.ca/multimedia/Products/500x500/102/10247/10247401.jpg',
     thumb: 'http://www.bestbuy.ca/multimedia/Products/500x500/102/10247/10247401.jpg'
     }],
     reviews: [
     {
     stars: 5,
     body: "Review do monitor",
     author: "Green@thomas.com"
     },
     {
     stars: 5,
     body: "Review do monitor2",
     author: "Red@thomas.com"
     }]
     },
     {
     name: 'Poltrona',
     price: 350,
     description: 'Descrição da poltrona',
     canPurchase: false,
     images: [
     {
     full: 'http://www.cadeirasepoltronas.art.br/ecommerce_site/arquivos5194/arquivos/1306086724_1.jpg',
     thumb: 'http://www.cadeirasepoltronas.art.br/ecommerce_site/arquivos5194/arquivos/1306086724_1.jpg'
     }
     ],
     reviews: [
     {
     stars: 5,
     body: "Quanto o frete pro cep: 000000-000",
     author: "thomas@joe.com"
     },
     {
     stars: 5,
     body: "Quanto o frete pro cep: 111111-111",
     author: "ze@joe.com"
     }]
     }
     ];*/
})();