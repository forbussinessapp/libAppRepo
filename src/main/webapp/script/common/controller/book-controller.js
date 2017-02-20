'use strict';

angular.module('ngdemo.controllers', []).controller('bookCtrl', function ($scope, bookFactory, $timeout) {

    const header_creation = 'Book Registration Form';
    const header_updating = 'Book Updating Form';
    const header_deletion = 'Book Deletion Form';

    var self = this;
    self.book={id:null,title:'',author:{firstName:'',lastName:''}};
    self.books=[];
    $scope.myForm = {};

    fetchAllBooks();

    /**
     * @desc calls service fetchAllBooks method to gets all books in database
     * @return book list containing all book entity data in database or error messages.
     */
    function fetchAllBooks(){
        bookFactory.fetchAllBooks()
            .then(
                function(data) {
                    console.log(data.object);
                    $scope.books = data.object;
                    $scope.errorMode= false;
                },
                function(errResponse){
                    console.error('Error while fetching Books ' + errResponse.message);
                    $scope.errMsg = errResponse.message;
                    $scope.errorMode = true;
                }
            );
    }

    /**
     * @desc calls service createBook method to create a book
     * @param book book containing book data to be created
     * @return succes or error messages
     */
    $scope.createBook = function (book){
        //$scope.book = book;
        bookFactory.createBook( book)
            .then(
                function(response){
                    $scope.handleResponse(response);
                },function(errResponse){
                    $scope.handleErrResponse(errResponse);
                }

            );
    }

    /**
     * @desc calls service updateBook method to update a book
     * @param book book containing book data to be updated
     * @return succes or error messages
     */
    $scope.updateBook = function (book){
        bookFactory.updateBook(book.id, book)
            .then(
                function(response){
                    $scope.handleResponse(response);
                },function(errResponse){
                    $scope.handleErrResponse(errResponse);
                }

            );
    }

    /**
     * @desc calls service deleteBook method to delete a book
     * @param book book containing book data to be deleted
     * @return succes or error messages
     */
    $scope.deleteBook = function (book){
        bookFactory.deleteBook(book.id, book)
            .then(
                function(response){
                    $scope.handleResponse(response);
                },function(errResponse){
                    $scope.handleErrResponse(errResponse);
                }

            );
    }

    /**
     * @desc show messages to the user depends on server response. Status uses to spesify message type.
     * status 0 = success
     * status 1 = warning
     * status 2 = error (logical errors)
     * Message stays in screen 3 seconds
     * @param response includes status and message returned from server.
     */
    $scope.handleResponse = function(response){
        if(response.status === 0){
            $scope.info = response.message;
            $scope.infoMode = true;
            $timeout(function () {$scope.infoMode= false;}, 3000);
        }else if(response.status === 1) {
            $scope.warning = response.message;
            $scope.warningMode = true;
            $timeout(function () {$scope.warningMode= false;}, 3000);
        }else{
            $scope.errMessage = response.message;
            $scope.errMode = true;
            $timeout(function () {$scope.errMode= false;}, 3000);
        }
    }

    /**
     * @desc show exception messages to the user
     * @param errResponse includes status and message returned from server.
     */
    $scope.handleErrResponse = function(errResponse){
        $scope.errMessage = errResponse.message;
        $scope.errMode = true;
        $timeout(function () {$scope.errMode= false;}, 3000);
    }

    /**
     * @desc call back function using for refreshing book list called from directive when dialog is closed
     */
    $scope.callbackFetch = function(){
        $scope.book = null;
        fetchAllBooks();
    }

    /**
     * @desc opens modal-dialog and sets scope variable for create and delete a book
     * @param book used to differentiate the modal usage for creating and deletion
     */
    $scope.openModal = function (book) {
        //Creation Mode is on
        if(book === null | book == undefined){
            $scope.modelHeader = header_creation;
        }else{
            $scope.modelHeader = header_deletion;
            $scope.book = book;
            $scope.delete = true;
        }
        $scope.modalPanel = true;
        $scope.capchaMode = true;
        $scope.generateCap();
        $scope.editMode = false;
        $scope.deleteMode = false;
        $scope.captchaMode = true;
        $scope.captchaModeOff = false;
        $scope.createMode = false;
    };

    /**
     * @desc opens modal-dialog and sets scope variable for updating a book
     * @param book book to be updated
     */
    $scope.editModal = function (book) {
        $scope.book = {};
        self.book = {};
        $scope.book = book;

        $scope.modalPanel = true;
        $scope.modelHeader = header_updating;
        $scope.capchaMode = false;
        $scope.captchaModeOff = true;

        $scope.editMode = true;
        $scope.deleteMode = false;
        $scope.createMode = false;
    }

    /**
     * @desc generates captcha and validates input value itself.
     * When captcha is on the screen loading-directive unable to catch ajax request. For this reason captcha represented
     * first.
     */
    $scope.generateCap = function () {
        if ($scope.captcha == null) {
            var captcha = new CAPTCHA({
                selector: '#captcha',
                width: 300,
                height: 150,
                onSuccess: function () {
                    //just changes scope variable in controller
                    $scope.capchaIsDone = true;
                    toastr.info("Okay, you are not a robot. Now you can continue>");
                }
            });
            $scope.captcha = captcha;
        }
        $scope.captcha.generate();
    };

    /**
     * @desc  After captcha successfully submitted user have to click continue button. Because captcha-onSuccess method
     * can not change binding attributes.
     */
    $scope.continue = function(){
        //captcha is ok and continue is clicked
        if($scope.capchaIsDone){
            //Creation Mode is on
            if($scope.book === null | $scope.book == undefined) {
                $scope.book = {};
                $scope.createMode = true;
            }else{
                $scope.deleteMode = true;
            }
            $scope.captchaMode = false;
            $scope.captchaModeOff = true;

        }
        //captcha is not okay but continue button is clicked
        else{
            toastr.error("Please provide the string into input field and click button \"Done!\" first:");
        }
    }

    /**
     * @desc  resets form
     */
    $scope.reset = function(){
        $scope.book={id:null,title:'',author:{firstName:'',lastName:''}};
        //$scope.book={};
    }

});
