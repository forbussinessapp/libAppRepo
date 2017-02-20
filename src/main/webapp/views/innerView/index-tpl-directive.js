/**
 * Shows modal dialog defining in template.
 * @return {angular.Directive} 'E' - only matches element name modal-dialog definition object.
 */

'use strict';

directives.directive('modalDialog', [ function() {
    return {
        restrict: 'E',
        scope: {
            show: '=',
            callbackFn: '&'
        },
        replace: true,
        transclude: true,
        link: function (scope, element, attrs) {

            scope.dialogStyle = {};
            if (attrs.width)
                scope.dialogStyle.width = attrs.width;
            if (attrs.height)
                scope.dialogStyle.height = attrs.height;

            /*Used for sending message to controller*/
            scope.hideModal = function () {
                scope.show = false;
                scope.callbackFn()
            };
        },
        template: "<div class='ng-modal' ng-show='show'><div class='ng-modal-overlay' ng-click='hideModal()'></div><div class='ng-modal-dialog' ng-style='dialogStyle'><div class='ng-modal-close' ng-click='hideModal()'>X</div><div class='ng-modal-dialog-content' ng-transclude></div></div></div>"
    };
}]);
