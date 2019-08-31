'use strict';

/* Services */

/*
 * http://docs.angularjs.org/api/ngResource.$resource
 * 
 * Default ngResources are defined as
 * 
 * 'get': {method:'GET'}, 'save': {method:'POST'}, 'query': {method:'GET',
 * isArray:true}, 'remove': {method:'DELETE'}, 'delete': {method:'DELETE'}
 */
var serverAddress = 'api';
var services = angular.module('seniorsApp');

// RELATORIOS
services.factory('RelatorioFactory', function ($resource) {
return $resource(serverAddress+'/relatorio/:tipo/:userid/:datainicio/:datafinal', {}, {
	  consulta: { method: 'GET', params: {tipo: '@tipo',userid:'@userid',datainicio: '@datainicio',datafinal: '@datafinal'} }
});
});

//VINCULOS
services.factory('VinculoFactory', function ($resource) {
return $resource(serverAddress+'/vinculo/:id/:id2', {}, {
    addsenior: { method: 'PUT', params: {id: '@id',id2: '@id2'} },
	  verificasenior: { method: 'GET', params: {id: '@id',id2: '@id2'} }
});
});

services.factory('VinculosFactory', function ($resource) {
  return $resource(serverAddress+'/vinculo', {}, {
      create: { method: 'POST' }
  });
});

//SENIORS
services.factory('SeniorFactory', function ($resource) {
  return $resource(serverAddress+'/seniors/:id', {}, {
      //addsenior: { method: 'POST' },
      verificasenior: { method: 'POST' },
      show: { method: 'GET' },
      querycuidador : { method : 'GET', isArray : true }
  });
});

services.factory('SeniorsFactory', function ($resource) {
  return $resource(serverAddress+'/seniors', {}, {
  	queryseniordisp: { method: 'GET', isArray: true },
      create: { method: 'POST' }
  });
});

//USERS
services.factory('UsersFactory', function ($resource) {
    return $resource(serverAddress+'/users', {}, {
        query: { method: 'GET', isArray: true },
        create: { method: 'POST' }
    });
});

services.factory('UserFactory', function ($resource) {
    return $resource(serverAddress+'/users/:id', {}, {
        get: { method: 'GET' },
        update: { method: 'PUT', params: {id: '@id'} },
        remove: { method: 'DELETE', params: {id: '@id'} }
    });
});

// MEDICACAO
services.factory('MedicacoesFactory', function($resource) {
	return $resource(serverAddress + '/medicacao', {}, {
		create : {
			method : 'POST'
		}
	});
});

services.factory('MedicacaoFactory', function($resource) {
	return $resource(serverAddress + '/medicacao/:id', {}, {
		query : {
			method : 'POST',
			isArray : true,
			params : {
				id : '@id'
			}
		},
		show : {
			method : 'GET',
			params : {
				id : '@id'
			}
		},
		update : {
			method : 'PUT',
			params : {
				id : '@id'
			}
		},
		remove : {
			method : 'DELETE',
			params : {
				id : '@id'
			}
		}
	});
});

// ATIVIDADE
services.factory('AtividadesFactory', function($resource) {
	return $resource(serverAddress + '/atividade', {}, {
		create : {
			method : 'POST'
		}
	});
});

services.factory('AtividadeFactory', function($resource) {
	return $resource(serverAddress + '/atividade/:id', {}, {
		query : {
			method : 'POST',
			isArray : true,
			params : {
				id : '@id'
			}
		},
		show : {
			method : 'GET'
		},
		update : {
			method : 'PUT',
			params : {
				id : '@id'
			}
		},
		remove : {
			method : 'DELETE',
			params : {
				id : '@id'
			}
		}
	});
});

//CONTATO
services.factory('ContatosFactory', function($resource) {
	return $resource(serverAddress + '/contato', {}, {
		create : {
			method : 'POST'
		}
	});
});

services.factory('ContatoFactory', function($resource) {
	return $resource(serverAddress + '/contato/:id', {}, {
		query : {
			method : 'POST',
			isArray : true,
			params : {
				id : '@id'
			}
		},
		show : {
			method : 'GET'
		},
		update : {
			method : 'PUT',
			params : {
				id : '@id'
			}
		},
		remove : {
			method : 'DELETE',
			params : {
				id : '@id'
			}
		}
	});
});

//CONSULTA MEDICA
services.factory('ConsultaMedicasFactory', function($resource) {
 return $resource(serverAddress + '/consultaMedica', {}, {
     //queryRole : {method : 'GET', isArray : true},
     create : {method : 'POST'}
 });
});

services.factory('ConsultaMedicaFactory', function($resource) {
 return $resource(serverAddress + '/consultaMedica/:id', {}, {
     query : {method : 'POST', isArray : true, params : {id : '@id'} },
     show : {method : 'GET'},
     update : {method : 'PUT',params : {id : '@id'}},
     remove : {method : 'DELETE',params : {id : '@id'}}
 });
});

// Login service
services.factory('UserLoginService', function($resource) {
	return $resource(serverAddress + '/user/:action', {}, {
		authenticate : {
			method : 'POST',
			params : {
				'action' : 'authenticate'
			},
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		},
		logged : {
			method : 'POST',
			params : {
				'action' : 'logged'
			}
		},
		logout : {
			method : 'POST',
			params : {
				'action' : 'logout'
			}
		},
	});
});


//USERS
services.factory('LocalsFactory', function ($resource) {
  return $resource(serverAddress+'/local', {}, {
      create: { method: 'POST' }
  });
});

services.factory('LocalFactory', function ($resource) {
  return $resource(serverAddress+'/local/:id', {}, {
      query: { method: 'GET', isArray : true, params : {id : '@id'} },
      update: { method: 'PUT', params: {id: '@id'} }
  });
});

