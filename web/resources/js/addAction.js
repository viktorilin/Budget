(function () {

    var app = {


        errorMsg: {
            'empty': 'Fill in this field',
            'description': 'Description must between 2 and 255',
            'amount': 'Amount must be numeric'
        },

        categories: null,
        subcategories: null,
        form: null,
        kache: new Object(),

        initialize: function () {
            this.categories = $("#categories");
            this.subcategories = $("#subcategories");
            this.form = $("#addActionForm");
            this.setListeners();
        },

        setListeners: function () {
            this.categories.on("change", app.handlerChangeCate);
            this.form.on("submit", app.validate);
        },

        validate: function () {
            var inputs = $(this).find('input');
            var selects = $(this).find('select');
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

            $.each(selects, function (index, val) {
                var input = $(val);
                if (!input.val() || input.val() == 0) {
                    isValid = false;
                    app.showMessage(input, 'empty');
                }
                else {
                    app.destroyMessage(input);
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

        handlerChangeCate: function () {
            if (this.value == 0) {
                $(subcategories).html("");
                $(subcategories).attr("disabled", "disabled");
            }
            else {
                if (app.kache["cat" + this.value]) {
                    app.updateSubcategories(app.kache["cat" + this.value]);
                }
                else {
                    $.get("/category/" + this.value + "/subcategories", function (data) {
                        console.log("request");
                        app.kache["cat" + $(categories).val()] = data;
                        app.updateSubcategories(data);
                    });
                }
            }
            app.destroyMessage($(this));
        },

        updateSubcategories: function (data) {
            $(subcategories).removeAttr("disabled");
            var str = "";
            for (key in data) {
                str += "<option value='" + key + "'>" + data[key] + "</option>";
            }
            $(subcategories).html(str);
            app.destroyMessage($(subcategories));
        }

    };
    app.initialize();

}())