(function () {

    var app = {

        initialize: function () {
            this.setListeners();
        },

        setListeners: function () {
            $(".delete-item").on("click", app.handlerDeleteItem);
        },

        handlerDeleteItem : function(){
            return confirm("Are your sure to delete ?");
        }
    };
    app.initialize();

}())