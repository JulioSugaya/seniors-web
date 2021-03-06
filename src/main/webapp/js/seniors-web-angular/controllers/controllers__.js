'use strict';

/* Controllers */
var app = angular.module('seniorsApp');

app.controller('ConsultaMedicaListCtrl', function($scope, $rootScope, ConsultaMedicasFactory, ConsultaMedicaFactory, $location, $dialogs) {

    // callback for ng-click 'editConsultaMedica':
    $scope.editConsultaMedica = function(consultaMedicaId) {
        $location.path('/consultaMedica/detail/' + consultaMedicaId);
    };

    // callback for ng-click 'deleteConsultaMedica':
    $scope.deleteConsultaMedica = function(consultaMedicaId) {

        var dlg = $dialogs.confirm('Aviso', 'Deseja realmente excluir esta Consulta ?');
        dlg.result.then(
            //confirm   
            function(btn) {
                ConsultaMedicaFactory.remove({id : consultaMedicaId   }).$promise.then(
                    //successCallback
                    function(data, status, headers, config) {
                        $scope.consultaMedicas = ConsultaMedicaFactory.query({id : parseFloat($rootScope.selectedSeniorId)});
                    },
                    //errorCallback
                    function(data, status, headers, config) {
                        $rootScope.error = "Erro ao remover ConsultaMedica !";
                    }
                );
            }, 
            //cancel    
            function(btn) {});
    };

    // callback for ng-click 'createConsultaMedica':
    $scope.createNewConsultaMedica = function() {
        $location.path('/consultaMedica/new');
    };

    $scope.consultaMedicas = ConsultaMedicaFactory.query({id : parseFloat($rootScope.selectedSeniorId)});
});

app.controller('ConsultaMedicaDetailCtrl', function($scope, $rootScope, $routeParams, ConsultaMedicaFactory, ConsultaMedicasFactory, $location) {


    // callback for ng-click 'updateConsultaMedica':
    $scope.updateConsultaMedica = function() {
        if($scope.update_consultaMedica.id != undefined){
                ConsultaMedicaFactory.update($scope.update_consultaMedica).$promise.then(
                        // success
                        function(data, status, headers, config) {
                            $location.path('/consultaMedica/list');
                         
                        },
                        // error
                        function(data, status, headers, config) {
                            $rootScope.error = "Erro ao atualizar a ConsultaMedica";
                            console.log("erro");
                            console.log(data);
                });
        }else{
        		 $scope.update_consultaMedica.id_cuidador = parseFloat($rootScope.$id)
                 $scope.update_consultaMedica.id_usuario = parseFloat($rootScope.selectedSeniorId)
                 ConsultaMedicasFactory.create($scope.update_consultaMedica).$promise.then(
                        // success
                        function(data, status, headers, config) {
                            $location.path('/consultaMedica/list');
                        },
                        // error
                        function(data, status, headers, config) {
                            $rootScope.error = "ConsultaMedica já existe";
                });
        }
    
    };

    // callback for ng-click 'cancel':
    $scope.cancel = function() {
        $location.path('/consultaMedica/list');
    };

    $scope.update_consultaMedica = ConsultaMedicaFactory.show({id : $routeParams.id});
    
    //  document.getElementById("spanPrioridade1" + $scope.update_consultaMedica.prioridade).addClass('glyphicon-ok');;
    //DataPicker
     $scope.today = function() {
            $scope.dt = new Date();
          };
          $scope.today();

          $scope.clear = function () {
            $scope.dt = null;
          };

          // Disable weekend selection
          $scope.disabled = function(date, mode) {
            return"";
           // ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
          };

          $scope.toggleMin = function() {
            $scope.minDate = $scope.minDate ? null : new Date();
          };
          $scope.toggleMin();
          
          $scope.open = function($event) {
            $event.preventDefault();
            $event.stopPropagation();

            $scope.opened = true;
          };

          $scope.dateOptions = {
            formatYear: 'yyyy',
            startingDay: 0,
            showWeeks:'false'
          };

          $scope.formats = ['dd - MMMM - yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate','fullDate','dd/MM/yyyy'];
          $scope.format = $scope.formats[5];
        //Fim DataPicker
          
          //timerpick
          $scope.mytime = new Date();

          $scope.hstep = 1;
          $scope.mstep = 15;

          $scope.options = {
            hstep: [1, 2, 3],
            mstep: [1, 5, 10, 15, 25, 30]
          };

          $scope.ismeridian = true;
          $scope.toggleMode = function() {
            $scope.ismeridian = ! $scope.ismeridian;
          };

          $scope.update = function() {
            var d = new Date();
            d.setHours( 1 );
            d.setMinutes( 0 );
            $scope.mytime = d;
          };

          $scope.changed = function () {
           // $log.log('Time changed to: ' + $scope.mytime);
          };

          $scope.clear = function() {
            $scope.mytime = null;
          };
          $scope.update();
          //fim timerpicker
          
        //CheckModel
          $scope.singleModel = 1;
          
          $scope.checkModel = {
              0: false,
              1: false,
              2: false,
              3: false,
              4: false,
              5: false,
              6: false
            };
          
          $scope.loadDias  = function alteraCheckDias() {
           if($scope.update_consultaMedica.diasSemana != undefined){
            var dias = $scope.update_consultaMedica.diasSemana;
            for(var i = 0; i<=6; i++) {
             $scope.checkModel[i] = false;
            }
            var res = dias.split(",");
            for(var i = 0; i<=6; i++) {
             if($.inArray(String(i), res) != -1) {
              $scope.checkModel[i] = true;
             } else {
              $scope.checkModel[i] = false;
             }
            } 
           } else {console.log("erro"); }
           
           
          }
});


app.controller('AtividadeListCtrl', function($scope, $rootScope, AtividadesFactory, AtividadeFactory, $location, $dialogs) {

    // callback for ng-click 'editAtividade':
    $scope.editAtividade = function(atividadeId) {
        $location.path('/atividade/detail/' + atividadeId);
    };

    // callback for ng-click 'deleteAtividade':
    $scope.deleteAtividade = function(atividadeId) {

        var dlg = $dialogs.confirm('Aviso', 'Deseja realmente excluir esta Atividade ?');
        dlg.result.then(
            //confirm   
            function(btn) {
                AtividadeFactory.remove({id : atividadeId   }).$promise.then(
                    //successCallback
                    function(data, status, headers, config) {
                    	$scope.atividades = AtividadeFactory.query({id : parseFloat($rootScope.selectedSeniorId)});
                    },
                    //errorCallback
                    function(data, status, headers, config) {
                        $rootScope.error = "Erro ao remover Atividade !";
                    }
                );
            }, 
            //cancel    
            function(btn) {});
    };

    // callback for ng-click 'createAtividade':
    $scope.createNewAtividade = function() {
        $location.path('/atividade/new');
    };

    $scope.atividades = AtividadeFactory.query({id : parseFloat($rootScope.selectedSeniorId)});
});

app.controller('AtividadeDetailCtrl', function($scope, $rootScope, $routeParams, AtividadeFactory, AtividadesFactory, $location) {


	// callback for ng-click 'updateAtividade':
	$scope.updateAtividade = function() {
	     $scope.update_atividade.diasSemana = "";
	    if($scope.update_atividade.id != undefined){
	            AtividadeFactory.update($scope.update_atividade).$promise.then(
	                    // success
	                    function(data, status, headers, config) {
	                        $location.path('/atividade/list');
	                     
	                    },
	                    // error
	                    function(data, status, headers, config) {
	                        $rootScope.error = "Erro ao atualizar a Atividade";
	                        console.log("erro");
	                        console.log(data);
	            });
	            
	            
	    }else{
	    		 $scope.update_atividade.id_cuidador = parseFloat($rootScope.$id)
	    		 $scope.update_atividade.id_usuario = parseFloat($rootScope.selectedSeniorId)
	             AtividadesFactory.create($scope.update_atividade).$promise.then(
	                    // success
	                    function(data, status, headers, config) {
	                        $location.path('/atividade/list');
	                    },
	                    // error
	                    function(data, status, headers, config) {
	                        $rootScope.error = "Atividade já existe";
	            });
	    }
	
	};

    // callback for ng-click 'cancel':
    $scope.cancel = function() {
        $location.path('/atividade/list');
    };

    $scope.update_atividade = AtividadeFactory.show({id : $routeParams.id});
    
    //  document.getElementById("spanPrioridade1" + $scope.update_atividade.prioridade).addClass('glyphicon-ok');;
    //DataPicker
     $scope.today = function() {
            $scope.dt = new Date();
          };
          $scope.today();

          $scope.clear = function () {
            $scope.dt = null;
          };

          // Disable weekend selection
          $scope.disabled = function(date, mode) {
            return"";
           // ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
          };

          $scope.toggleMin = function() {
            $scope.minDate = $scope.minDate ? null : new Date();
          };
          $scope.toggleMin();
          
          $scope.open = function($event) {
            $event.preventDefault();
            $event.stopPropagation();

            $scope.opened = true;
          };

          $scope.dateOptions = {
            formatYear: 'yyyy',
            startingDay: 0,
            showWeeks:'false'
          };

          $scope.formats = ['dd - MMMM - yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate','fullDate','dd/MM/yyyy'];
          $scope.format = $scope.formats[5];
        //Fim DataPicker
          
          //timerpick
          $scope.mytime = new Date();

          $scope.hstep = 1;
          $scope.mstep = 15;

          $scope.options = {
            hstep: [1, 2, 3],
            mstep: [1, 5, 10, 15, 25, 30]
          };

          $scope.ismeridian = true;
          $scope.toggleMode = function() {
            $scope.ismeridian = ! $scope.ismeridian;
          };

          $scope.update = function() {
            var d = new Date();
            d.setHours( 1 );
            d.setMinutes( 0 );
            $scope.mytime = d;
          };

          $scope.changed = function () {
           // $log.log('Time changed to: ' + $scope.mytime);
          };

          $scope.clear = function() {
            $scope.mytime = null;
          };
          $scope.update();
          //fim timerpicker
          
        //CheckModel
          $scope.singleModel = 1;
          
          $scope.checkModel = {
              0: false,
              1: false,
              2: false,
              3: false,
              4: false,
              5: false,
              6: false
            };
          
          $scope.loadDias  = function alteraCheckDias() {
           if($scope.update_atividade.diasSemana != undefined){
            var dias = $scope.update_atividade.diasSemana;
            for(var i = 0; i<=6; i++) {
             $scope.checkModel[i] = false;
            }
            var res = dias.split(",");
            for(var i = 0; i<=6; i++) {
             if($.inArray(String(i), res) != -1) {
              $scope.checkModel[i] = true;
             } else {
              $scope.checkModel[i] = false;
             }
            } 
           } else {console.log("erro"); }
           
           
          }
});

app.controller('ContatoListCtrl', function($scope, $rootScope, ContatosFactory, ContatoFactory, $location, $dialogs) {

    // callback for ng-click 'editContato':
    $scope.editContato = function(contatoId) {
        $location.path('/contato/detail/' + contatoId);
    };

    // callback for ng-click 'deleteAtividade':
    $scope.deleteContato = function(contatoId) {

        var dlg = $dialogs.confirm('Aviso', 'Deseja realmente excluir este Contato ?');
        dlg.result.then(
            //confirm   
            function(btn) {
                ContatoFactory.remove({id : contatoId   }).$promise.then(
                    //successCallback
                    function(data, status, headers, config) {
                    	 $scope.contatos = ContatoFactory.query({id : parseFloat($rootScope.selectedSeniorId)});
                    },
                    //errorCallback
                    function(data, status, headers, config) {
                        $rootScope.error = "Erro ao remover contato !";
                    }
                );
            }, 
            //cancel    
            function(btn) {});
    };

    // callback for ng-click 'createAtividade':
    $scope.createNewContato = function() {
        $location.path('/contato/new');
    };

    $scope.contatos = ContatoFactory.query({id : parseFloat($rootScope.selectedSeniorId)});
});

app.controller('ContatoDetailCtrl', function($scope, $rootScope, $routeParams, ContatoFactory,ContatosFactory, $location) {


	// callback for ng-click 'updateContato':
	$scope.updateContato = function() {
		$scope.update_contato.id_usuario = $rootScope.selectedSeniorId;
	    if($scope.update_contato.id != undefined){
	            ContatoFactory.update($scope.update_contato).$promise.then(
	                    // success
	                    function(data, status, headers, config) {
	                        $location.path('/contato/list');
	                     
	                    },
	                    // error
	                    function(data, status, headers, config) {
	                        $rootScope.error = "Erro ao atualizar o contato";
	                        console.log("erro");
	                        console.log(data);
	            });
	    }else{
	    		 $scope.update_contato.id_usuario = parseFloat($rootScope.selectedSeniorId)
	             ContatosFactory.create($scope.update_contato).$promise.then(
	                    // success
	                    function(data, status, headers, config) {
	                        $location.path('/contato/list');
	                    },
	                    // error
	                    function(data, status, headers, config) {
	                        $rootScope.error = "Contato já existe";
	            });
	    }
	
	};

    // callback for ng-click 'cancel':
    $scope.cancel = function() {
        $location.path('/contato/list');
    };

    $scope.update_contato = ContatoFactory.show({id : $routeParams.id});
    
});

app.controller('MedicacaoListCtrl', function($scope, $rootScope, MedicacoesFactory, MedicacaoFactory, $location, $dialogs) {

	// callback for ng-click 'editMedicacao':
	$scope.editMedicacao = function(medicacaoId) {
		$location.path('/medicacao/detail/' + medicacaoId);
	};

	// callback for ng-click 'deleteMedicacao':
	$scope.deleteMedicacao = function(medicacaoId) {

		var dlg = $dialogs.confirm('Aviso', 'Deseja realmente excluir este Medicamento ?');
		dlg.result.then(
			//confirm	
			function(btn) {
				MedicacaoFactory.remove({id : medicacaoId	}).$promise.then(
					//successCallback
					function(data, status, headers, config) {
						$scope.meds = MedicacaoFactory.query({id : parseFloat($rootScope.selectedSeniorId)	});
					},
					//errorCallback
					function(data, status, headers, config) {
						$rootScope.error = "Erro ao remover Medicamento !";
					}
				);
			}, 
			//cancel	
			function(btn) {});
	};

	// callback for ng-click 'createMedicacao':
	$scope.createNewMedicacao = function() {
		$location.path('/medicacao/new');
	};

	$scope.meds = MedicacaoFactory.query({id : parseFloat($rootScope.selectedSeniorId)	});
});

app.controller('MedicacaoDetailCtrl', function($scope, $rootScope, $routeParams, MedicacaoFactory, MedicacoesFactory, SeniorFactory, $location, $dialogs) {

	// callback for ng-click 'updateMedicacao':
	$scope.updateMedicacao = function() {
		$scope.update_medicacao.dias = "";
		if($scope.update_medicacao.id != undefined){
			for(var i = 0; i<=6; i++) {
				  if($scope.checkModel[i]) {
					  if(i!=6) {
						  $scope.update_medicacao.dias += String(i) + ",";
					  }else {
						  $scope.update_medicacao.dias += String(i);
					  }
				  }
			  }
			console.log($scope.update_medicacao.dias);
			//$scope.update_medicacao.dias = $scope.dias;
				MedicacaoFactory.update($scope.update_medicacao).$promise.then(
						// success
						
						function(data, status, headers, config) {
							$location.path('/medicacao/list');
							//console.log($scope.update_medicacao);
							//console.log($scope);
						},
						// error
						function(data, status, headers, config) {
							$rootScope.error = "Erro ao atualizar o Medicamento";
				});
		}else{
				 $scope.update_medicacao.id_cuidador = parseFloat($rootScope.$id)
				 $scope.update_medicacao.id_usuario = parseFloat($rootScope.selectedSeniorId)
				 MedicacoesFactory.create($scope.update_medicacao).$promise.then(
						// success
						function(data, status, headers, config) {
							$location.path('/medicacao/list');
							//var dlg = $dialogs.confirm('Aviso', 'Deseja realmente excluir este Medicamento ?');
						},
						// error
						function(data, status, headers, config) {
							$rootScope.error = "Medicamento já existe";
				});
		}

	};

	// callback for ng-click 'cancel':
	$scope.cancel = function() {
		$location.path('/medicacao/list');
	};

	$scope.update_medicacao = MedicacaoFactory.show({
		id : $routeParams.id
	});
	
	//DataPicker
	 $scope.today = function() {
		    $scope.dt = new Date();
		  };
		  $scope.today();

		  $scope.clear = function () {
		    $scope.dt = null;
		  };

		  // Disable weekend selection
		  $scope.disabled = function(date, mode) {
		    return"";
		   // ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
		  };

		  $scope.toggleMin = function() {
		    $scope.minDate = $scope.minDate ? null : new Date();
		  };
		  $scope.toggleMin();
		  
		  $scope.open = function($event) {
		    $event.preventDefault();
		    $event.stopPropagation();

		    $scope.opened = true;
		  };

		  $scope.dateOptions = {
		    formatYear: 'yyyy',
		    startingDay: 0,
		    showWeeks:'false'
		  };

		  $scope.formats = ['dd - MMMM - yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate','fullDate'];
		  $scope.format = $scope.formats[2];
		//Fim DataPicker
		  
		 		  
		//**************  Paginacao
		  $scope.totalItems = 64;
		  $scope.currentPage = 1;

		  $scope.setPage = function (pageNo) {
		    $scope.currentPage = pageNo;
		  };

		  $scope.pageChanged = function() {
		    $log.log('Page changed to: ' + $scope.currentPage);
		  };

		  $scope.maxSize = 1;
		  $scope.bigTotalItems = 175;
		  $scope.bigCurrentPage = 1;
		//**************  Paginacao
		  
	    //CheckModel
		  $scope.singleModel = 1;
		  
		  $scope.checkModel = {
				    0: false,
				    1: false,
				    2: false,
				    3: false,
				    4: false,
				    5: false,
				    6: false
				  };
		  
		  $scope.loadDias  = function alteraCheckDias() {
			  if($scope.update_medicacao.dias != undefined){
				  var dias = $scope.update_medicacao.dias;
				  for(var i = 0; i<=6; i++) {
					  $scope.checkModel[i] = false;
				  }
				  var res = dias.split(",");
				  for(var i = 0; i<=6; i++) {
					  if($.inArray(String(i), res) != -1) {
						  $scope.checkModel[i] = true;
					  } else {
						  $scope.checkModel[i] = false;
					  }
				  } 
			  } //else {console.log("erro"); }
			  
			  
		  }

});

app.controller('SeniorListCtrl', function($scope, $rootScope, UsersFactory, SeniorFactory, $location, $dialogs) {

	// callback for ng-click 'editUser':
	$scope.selectSenior = function(id,name) {
		$rootScope.selectedSeniorId = id;
		$rootScope.selectedSeniorName = name;
		$location.path('/');
	};

	$scope.editUser = function(userId) {
		$location.path('/user/detailsenior/' + userId);
	};

	// callback for ng-click 'deleteUser':
	$scope.deleteUser = function(userId) {

		var dlg = $dialogs.confirm('Aviso', 'Deseja realmente excluir este Usuário ?');
		dlg.result.then(
			//confirm	
			function(btn) {
				UserFactory.remove({id : userId	}).$promise.then(
					//successCallback
					function(data, status, headers, config) {
						$scope.users = SeniorFactory.querycuidador({id : parseFloat($rootScope.$id)});
					},
					//errorCallback
					function(data, status, headers, config) {
						$rootScope.error = "Erro ao remover usuário !";
					}
				);
			}, 
			//cancel	
			function(btn) {});
	};

	//if($scope.hasRole('cuidador')){
		$scope.users = SeniorFactory.querycuidador({id : parseFloat($rootScope.$id)});
	//}else{
	//	$scope.users = UsersFactory.query();
	//}

});

app.controller('SeniorDetailCtrl', function($scope, $rootScope, $routeParams, SeniorFactory, SeniorsFactory, $location, $dialogs) {
	
	$scope.users = SeniorsFactory.queryseniordisp();

	// callback for ng-click 'updateUser':
	$scope.updateUser = function() {

		if ($scope.update_user.authorities !== 'undefined') {
			delete $scope.update_user.authorities;
		}
		if($scope.update_user.id != undefined){
			SeniorFactory.update($scope.update_user).$promise.then(
						// success
						function(data, status, headers, config) {
							$location.path('/user/listsenior');
						},
						// error
						function(data, status, headers, config) {
							$rootScope.error = "Erro ao atualizar o usuário";
				});
		}else{
				SeniorsFactory.create($scope.update_user).$promise.then(
						// success
						function(data, status, headers, config) {
							$location.path('/user/listsenior');
						},
						// error
						function(data, status, headers, config) {
							$rootScope.error = "usuário já existe";
				});
		}

	};

	// callback for ng-click 'cancel':
	$scope.cancel = function() {
		$location.path('/user/listsenior');
	};

	$scope.update_user = SeniorFactory.show({
		id : $routeParams.id
	});	
	
});


app.controller('SeniorVinculoCtrl', function($scope, $rootScope, $routeParams, VinculoFactory, VinculosFactory, $location, $dialogs) {
	
	$scope.addSenior = function() {

		VinculoFactory.verificasenior({id : $scope.phone, id2 : $scope.chave }).$promise.then(
			//successCallback
			function(data, status, headers, config) {

				var dlg = $dialogs.confirm('Aviso', 'Deseja vincular o usuário ' + data.nomeCompleto + '?');
				dlg.result.then(
					//confirm	
					function(btn) {
						VinculoFactory.addsenior({id : parseFloat($rootScope.$id), id2 : data.id	}).$promise.then(
							//successCallback
							function(data, status, headers, config) {
								$location.path('/user/listsenior');
							},
							//errorCallback
							function(data, status, headers, config) {
								$rootScope.error = "Erro ao remover usuário !";
							}
						);
					}, 
					//cancel	
					function(btn) {});
			},
			//errorCallback
			function(data, status, headers, config) {
				$rootScope.error = "Usuário inexistente!";
			}
		);
	};	

	// callback for ng-click 'cancel':
	$scope.cancel = function() {
		$location.path('/user/listsenior');
	};

});

app.controller('UserListCtrl', function($scope, $rootScope, UsersFactory, UserFactory, $location, $dialogs) {

	// callback for ng-click 'editUser':
	$scope.editUser = function(userId) {
		$location.path('/user/detail/' + userId);
	};

	// callback for ng-click 'deleteUser':
	$scope.deleteUser = function(userId) {

		var dlg = $dialogs.confirm('Aviso', 'Deseja realmente excluir este Usuário ?');
		dlg.result.then(
			//confirm	
			function(btn) {
				UserFactory.remove({id : userId	}).$promise.then(
					//successCallback
					function(data, status, headers, config) {
						$scope.users = UsersFactory.query();
					},
					//errorCallback
					function(data, status, headers, config) {
						$rootScope.error = "Erro ao remover usuário !";
					}
				);
			}, 
			//cancel	
			function(btn) {});
	};

	// callback for ng-click 'createUser':
	$scope.createNewUser = function() {
		$location.path('/user/new');
	};

//	if($scope.hasRole('cuidador')){
//		$scope.users = UserFactory.show({id : parseFloat($rootScope.$id)});
//	}else{
		$scope.users = UsersFactory.query();
//	}
});

app.controller('UserDetailCtrl', function($scope, $rootScope, $routeParams, UserFactory, UsersFactory, $location) {

	// callback for ng-click 'updateUser':
	$scope.updateUser = function() {

		if ($scope.update_user.authorities !== 'undefined') {
			delete $scope.update_user.authorities;
		}
		if($scope.update_user.id != undefined){
				UserFactory.update($scope.update_user).$promise.then(
						// success
						function(data, status, headers, config) {
							$location.path('/user/list');
						},
						// error
						function(data, status, headers, config) {
							$rootScope.error = "Erro ao atualizar o usuário";
				});
		}else{
				 UsersFactory.create($scope.update_user).$promise.then(
						// success
						function(data, status, headers, config) {
							$location.path('/user/list');
						},
						// error
						function(data, status, headers, config) {
							$rootScope.error = "usuário já existe";
				});
		}

	};

	// callback for ng-click 'cancel':
	$scope.cancel = function() {
		$location.path('/user/list');
	};

	$scope.update_user = UserFactory.get({
		id : $routeParams.id
	});
	

});

//Criado controle para adicionar novo usuario
app.controller('NewUserCtrl', function($scope, $rootScope, $routeParams, UserFactory, UsersFactory, $location,  $dialogs) {

	// callback for ng-click 'updateUser':
	$scope.papel = true;

	$scope.updateUser = function() {
		if ($scope.update_user.authorities !== 'undefined') {
			delete $scope.update_user.authorities;
		}
		if($scope.update_user.id != undefined){
				UserFactory.update($scope.update_user).$promise.then(
						// success
						function(data, status, headers, config) {
							var dlg = $dialogs.confirm('Parabéns', 'Seu usúario foi criado com sucesso');
							$location.path('/user/list');
						},
						// error
						function(data, status, headers, config) {
							$rootScope.error = "Erro ao atualizar o usuário";
				});
		}else{
				 UsersFactory.create($scope.update_user).$promise.then(
						// success
						 
						function(data, status, headers, config) {
							var dlg = $dialogs.confirm('Parabéns', 'Seu usúario foi criado com sucesso');
							$location.path('/login');
						},
						// error
						function(data, status, headers, config) {
							$rootScope.error = "usuário já existe";
				});
		}

	};
	
	// callback for ng-click 'cancel':
	$scope.cancel = function() {
		$location.path('/user/list');
	};

});
app.controller('UserCreationCtrl', function($scope, $rootScope, UsersFactory, $location) {

	// callback for ng-click 'createNewUser':
	$scope.createNewUser = function() {

		UsersFactory.create($scope.new_user).$promise.then(
		// success
		function(data, status, headers, config) {
			$location.path('/user/list');
		},
		// error
		function(data, status, headers, config) {
			$rootScope.error = "usuário já existe";
		});
	};
});

app.controller('LoginController', function($scope, $rootScope, $location, $cookieStore,	UserLoginService) {
	
	$scope.rememberMe = false;
	$scope.login = function() {
		UserLoginService.authenticate($.param({
			username : $scope.username,
			password : $scope.password
		})).$promise.then(
			//success	
			function(authenticationResult) {
				var authToken = authenticationResult.token;
				$rootScope.authToken = authToken;
				if ($scope.rememberMe) {
					$cookieStore.put('authToken',authToken);
				}

				UserLoginService.get(function(user) {
					$rootScope.user = user;
					$location.path("/");
				});
			},
			//errorCallback
			function(data, status, headers, config) {
				if (data.status !== 'undefined'
						&& data.status == 401) {
					$rootScope.error = "Erro na autenticação. Favor verificar o login ou a senha!";
				}
			});
	};
	$scope.newUser = function() {
		$location.path('/user/newUser');
	}
});
