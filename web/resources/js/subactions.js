(function () {

    var app = {

        errorMsg: {
            'empty': 'Fill in this field',
            'description': 'Description must between 2 and 255',
            'amount': 'Amount must be numeric'
        },

        categories: null,
        subcategories: null,
        kache: new Object(),

        initialize: function () {
            this.setListeners();
        },

        setListeners: function () {
            $(".delete-item").on("click", app.handlerDeleteItem);
            $("#dropDownForm").on("show.bs.dropdown", app.handlerShowDropdown);
            $("#dropDownForm").on("hide.bs.dropdown", app.handlerHIdeDropdown);
            $("#form-add-subaction").on('submit', app.submitForm)
        },

        submitForm: function (e) {
            e.preventDefault();
            var form = $(this);
            if(!app.validateForm(form)) return;
            var str = form.serialize();
            var link = window.location.protocol + "//" + window.location.host + "/subactions/submit";
            $("#butnSubmit").button('loading');
            

            $.ajax({
                url:  link,
                type: "POST",
                data: str
            })
                .fail(function() {
                    console.log("HErovo");
                })
                .always(function(msg){
                    $("#butnSubmit").button('reset');
                })
                .done(function(msg){
                    try{
                        if(msg["status"]){
                            app.addSubaction(msg["id"]);
                        }
                    }
                    catch(e){
                        console.log("Fail");
                        console.log(e);
                    }
                });

        },

        resetForm: function(){
            var form = $("#form-add-subaction");
            var inputs = form.find('input');
            $.each(inputs, function (index, val) {
                var input = $(val);
                if(input.attr('type') == 'hidden') return;
                app.destroyMessage(input);
            });
            form[0].reset();
        },

        validateForm: function(form){
            var inputs = form.find('input');
            var isValid = true;

            $.each(inputs, function (index, val) {
                var pattern = null;
                var input = $(val);
                var res = true;
                if(input.attr('type') == 'hidden') return;
                switch (input.attr('name')) {
                    case 'description':
                        res = (input.val().length > 2 && input.val().length < 255);
                        break;
                    case 'amount':
                        res = !isNaN(parseFloat(input.val()));
                        break;
                }

                if(res){
                    app.destroyMessage(input);
                }
                else{
                    isValid = false;
                    app.showMessage(input, input.attr('name'));
                }

            });
            return isValid;
        },

        removeError: function () {
            app.destroyMessage($(this));
        },

        showMessage: function (elem, error) {
            var formGroup = elem.closest('.form-group');
            formGroup.addClass('has-error');
            var withErrors = formGroup.find('.help-block');
            withErrors.html(app.errorMsg[error]);
        },

        destroyMessage: function (elem) {
            var formGroup = elem.closest('.form-group');
            if (formGroup.hasClass('has-error')) {
                formGroup.removeClass('has-error');
                var withErrors = formGroup.find('.help-block');
                withErrors.html('');
            }
        },

        addSubaction : function(id){

            var item = "<tr>";
                item += "<td>"+$("#form-add-subaction input[name='description']").val()+ "</td>";
                item += "<td class='text-center'>"+$("#form-add-subaction input[name='amount']").val()+ "</td>";
                item += "<td class='text-center'> <a href='/action/" + $("#form-add-subaction input[name='action_id']").val();
                item += "/subaction/"+id+"/delete' class='btn-primary btn btn-xs delete-item'>x</a></td>";
                item +="</tr>";
            $(item).insertAfter("#table-header").on('click', app.handlerDeleteItem);
            $("#dLabel").dropdown('toggle');
        },

        handlerDeleteItem : function(){
            return confirm("Are your sure to delete ?");
        },

        handlerShowDropdown: function () {
            $("#dLabel").fadeOut(100);
        },

        handlerHIdeDropdown: function () {
            $("#dLabel").fadeIn(100);
            app.resetForm();
        }

    };
    app.initialize();

}())