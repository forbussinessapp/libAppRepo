'use strict';

services.factory("bookFactory", ['$http', '$q', '$log', function($http, $q,  $log){

    const rest_service_url = 'app/rest/books/';
    var factory = {
        fetchAllBooks:fetchAllBooks,
        createBook:createBook,
        updateBook:updateBook,
        deleteBook:deleteBook
    };
    return factory;

    /**
     * @desc calls request server to get all books in database
     * @return book list containing all book entity data in database or error messages.
     */
    function fetchAllBooks() {
        var deferred = $q.defer();
        $http.get(rest_service_url)
            .then(
                function (response) {
                    $log.info(
                        'bookFactory->fetchAllBooks() http status: ' + response.status
                        + ' generic status: ' + response.data.status
                        + ' response: ' + response.data.object.length + ' data found: '
                        + ' message: ' + response.data.message
                    );
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    $log.error('bookFactory->fetchAllBooks() error message: ' + errResponse.data.message
                        + ' error code: ' + errResponse.data.errorCode);
                    deferred.reject(errResponse.data);
                }
            );
        return deferred.promise;
    }

    /**
     * @desc request server to create a book
     * @param book book containing book data to be created
     * @return succes or error messages
     */
    function createBook(book) {
        var deferred = $q.defer();
        $http.post(rest_service_url, book)
            .then(
                function (response) {
                    $log.info(
                        'bookFactory->createBook() http status: ' + response.status
                        + ' generic status: ' + response.data.status
                        + ' title: ' + response.data.object.title
                        + ' message: ' + response.data.message
                    );
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    $log.error('bookFactory->createBook() error message: ' + errResponse.data.message
                        + ' error code: ' + errResponse.data.errorCode);
                    deferred.reject(errResponse.data);
                }
            );
        return deferred.promise;
    }

    /**
     * @desc request server to update a book
     * @param book book containing book data to be updated
     * @return succes or error messages
     */
    function updateBook(id, book) {
        var deferred = $q.defer();
        $http.put(rest_service_url + id, book)
            .then(
                function (response) {
                    $log.info(
                        'bookFactory->updateBook() http status: ' + response.status
                        + ' generic status: ' + response.data.status
                        + ' title: ' + response.data.object.title
                        + ' message: ' + response.data.message
                    );
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    $log.error('bookFactory->updateBook() error message: ' + errResponse.data.message
                        + ' error code: ' + errResponse.data.errorCode);
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    /**
     * @desc request server to delete a book
     * Because of $httpProvider.defaults.headers.delete = { 'Content-Type' : 'application/json;charset=UTF-8' }; can not
     * set content-type in the app.js, header content-type is set in here
     * @param book book containing book data to be deleted
     * @return succes or error messages
     */
    function deleteBook(id,book) {
        var deferred = $q.defer();
        $http({
            method: 'DELETE',
            url: 'app/rest/books/' + id,
            data: book,
            headers: {
                'Content-type': 'application/json;charset=utf-8'
            }
        })
            .then(
                function (response) {
                    $log.info(
                        'bookFactory->deleteBook() http status: ' + response.status
                        + ' generic status: ' + response.data.status
                        + ' title: ' + response.data.object.title
                        + ' message: ' + response.data.message
                    );
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    $log.error('bookFactory->deleteBook() error message: ' + errResponse.data.message
                        + ' error code: ' + errResponse.data.errorCode);
                    deferred.reject(errResponse.data);
                }
            );
        return deferred.promise;
    }
}]);
