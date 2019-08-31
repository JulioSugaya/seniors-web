var App = angular.module('seniorsApp', [ 'ngRoute', 'ngResource', 'ngCookies', 'flow', 'ui.bootstrap', 'dialogs' ]);

App.config([ '$routeProvider', '$locationProvider', '$httpProvider', function($routeProvider, $locationProvider, $httpProvider) {
    
		    //  RELATORIOS
		    $routeProvider.when('/relatorio/consulta', {
		      templateUrl : 'partials/relatorio/consulta.html',
		      controller : 'RelatorioCtrl'
		    });
		    
		    //  CONSULTA MEDICA CRUD
		    $routeProvider.when('/consultaMedica/list', {
		      templateUrl : 'partials/consultaMedica/list-consultaMedica.html',
		      controller : 'ConsultaMedicaListCtrl'
		    });
		    
		    $routeProvider.when('/consultaMedica/detail/:id', {
		      templateUrl : 'partials/consultaMedica/detail-consultaMedica.html',
		      controller : 'ConsultaMedicaDetailCtrl'
		    });
		    
		    $routeProvider.when('/consultaMedica/new', {
		      templateUrl : 'partials/consultaMedica/detail-consultaMedica.html',
		      controller : 'ConsultaMedicaDetailCtrl'
		    });
    
			//  ATIVIDADE CRUD
			$routeProvider.when('/atividade/list', {
				templateUrl : 'partials/atividade/list-atividade.html',
				controller : 'AtividadeListCtrl'
			});
			
			$routeProvider.when('/atividade/detail/:id', {
				templateUrl : 'partials/atividade/detail-atividade.html',
				controller : 'AtividadeDetailCtrl'
			});
			
			$routeProvider.when('/atividade/new', {
				templateUrl : 'partials/atividade/detail-atividade.html',
				controller : 'AtividadeDetailCtrl'
			});
		    
			//  CONTATO CRUD
			$routeProvider.when('/contato/list', {
				templateUrl : 'partials/contato/list-contato.html',
				controller : 'ContatoListCtrl'
			});
			
			$routeProvider.when('/contato/detail/:id', {
				templateUrl : 'partials/contato/detail-contato.html',
				controller : 'ContatoDetailCtrl'
			});
			
			$routeProvider.when('/contato/new', {
				templateUrl : 'partials/contato/detail-contato.html',
				controller : 'ContatoDetailCtrl'
			});
			
			//  Medicacao CRUD
			$routeProvider.when('/medicacao/list', {
				templateUrl : 'partials/medicacao/list-medicacao.html',
				controller : 'MedicacaoListCtrl'
			});
			
			$routeProvider.when('/medicacao/detail/:id', {
				templateUrl : 'partials/medicacao/detail-medicacao.html',
				controller : 'MedicacaoDetailCtrl'
			});
	
			$routeProvider.when('/medicacao/new', {
				templateUrl : 'partials/medicacao/detail-medicacao.html',
				controller : 'MedicacaoDetailCtrl'
			});

			//  localizacao
			$routeProvider.when('/localizacao/mapa', {
				templateUrl : 'partials/localizacao/mapa.html',
				controller : 'LocalizacaoCtrl'
			});
			
			$routeProvider.when('/user/listsenior', {
				templateUrl : 'partials/vinculo/list-vinculo.html',
				controller : 'SeniorListCtrl'
			});
			
			$routeProvider.when('/user/addsenior', {
				templateUrl : 'partials/vinculo/add-senior.html',
				controller : 'SeniorVinculoCtrl'
			});
			
			$routeProvider.when('/user/detailsenior/:id', {
				templateUrl : 'partials/user/detail-user.html',
				controller : 'SeniorDetailCtrl'
			});

			//  Users CRUD
			$routeProvider.when('/user/new', {
				templateUrl : 'partials/user/detail-user.html',
				controller : 'UserDetailCtrl'
			});
			
			$routeProvider.when('/user/list', {
				templateUrl : 'partials/user/list-user.html',
				controller : 'UserListCtrl'
			});
			
			$routeProvider.when('/user/detail/:id', {
				templateUrl : 'partials/user/detail-user.html',
				controller : 'UserDetailCtrl'
			});
			
			$routeProvider.when('/login', {
				templateUrl : 'partials/login.html',
				controller : 'LoginController'
			});
			
			$routeProvider.when('/user/newUser', {
				templateUrl : 'partials/user/new-user-inicial.html',
				controller : 'NewUserCtrl'
			});
			
			$routeProvider.otherwise({
				templateUrl: 'partials/index.html',
			});

			$locationProvider.hashPrefix('!');
			
			/* Register error provider that shows message on failed requests or redirects to login page on
			 * unauthenticated requests */
		    $httpProvider.interceptors.push(function ($q, $rootScope, $location) {
			        return {
			        	'responseError': function(rejection) {
			        		var status = rejection.status;
			        		var config = rejection.config;
			        		var method = config.method;
			        		var url = config.url;
			        		
			        		if (status == 401) {
			        			$rootScope.error = method + " em " + url + " failed with status " + status;
			        			$location.path( "/login" );
			        		} else {
			        			switch(rejection.data) {
				        		    case "WS02":
				        		    	$rootScope.error = "Este usu�rio j� existe!";
				        		        break;
				        		    default:
				        		    	$rootScope.error = method + " em " + url + " failed with status " + status;
				        		};
			        		}
			        		return $q.reject(rejection);
			        	}
			        };
			    }
		    );
		    
		    /* Registers auth token interceptor, auth token is either passed by header or by query parameter
		     * as soon as there is an authenticated user */
		    $httpProvider.interceptors.push(function ($q, $rootScope, $location) {
		        return {
		        	'request': function(config) {
		        		//api is the intercept filter described on web app 
		        		var isRestCall = config.url.indexOf('api') == 0;
		        		if (isRestCall && angular.isDefined($rootScope.authToken)) {
		        			var authToken = $rootScope.authToken;
		        			if (AppConfig.useAuthTokenHeader) {
		        				config.headers['X-Auth-Token'] = authToken;
		        			} else {
		        				config.url = config.url + "?token=" + authToken;
		        			}
		        		}
		        		return config || $q.when(config);
		        	}
		        };
		    }
	    );
	}]
);

App.run(function($rootScope, $location, $cookieStore, UserLoginService) {
	
	/* Reset error when a new view is loaded */
	$rootScope.$on('$viewContentLoaded', function() {
		delete $rootScope.error;
		//Alterado para quando criar um novo (inicial) usuario não redirecione 
		if($location.$$path != "/login" && !$rootScope.authToken && $location.$$path != "/user/newUser") {
			$location.path("/login");
		}

	});
	
	$rootScope.hasRole = function(role) {
		
		if ($rootScope.user === undefined) {
			return false;
		}
		
		if ($rootScope.user.roles[role] === undefined) {
			return false;
		}
		
		return $rootScope.user.roles[role];
	};
	
	$rootScope.logout = function() {
		delete $rootScope.user;
		delete $rootScope.authToken;
		UserLoginService.logout();
		$cookieStore.remove('authToken');
		$location.path("/login");
	};
	

	$rootScope.seniorOff = function() {
		$rootScope.selectedSeniorId = null;
		$rootScope.selectedSeniorName = null;
		$location.path("/");
	};

	
	 /* Try getting valid user from cookie or go to login page */
	var originalPath = $location.path();
	$location.path("/login");
	var authToken = $cookieStore.get('authToken');
	if (authToken !== undefined) {
		$rootScope.authToken = authToken;
		UserLoginService.get(function(user) {
			$rootScope.user = user;
			$location.path(originalPath);
		});
	}
	
	$rootScope.initialized = true;
});

// App Constants
App.value("Constants",{
	SUCCESS 					: "WS00",
	USER_NOT_FOUND 				: "WS01",
	USER_ALREADY_EXISTS   		: "WS02",
	PASSWORD_INCORRECT  		: "WS03",
	SOME_EMPTY_FIELD 			: "WS04",
	SOME_WRONG_INFO 			: "WS05",
	EMAIL_OR_CPF_INCORRECT	    : "WS06",
	OFFER_WITH_ZERO_POINTS	    : "WS07",
	MAX_UPLOAD_SIZE			 	: "2MB"
});