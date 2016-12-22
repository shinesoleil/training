function toView() {
    $(".title").each(function () {
        $(this).children().remove();
        $(this).append("<h1 class=''>" + $(this).attr("data-content") + "</h1>");
    });

    $(".text").each(function () {
        $(this).children().remove();
        $(this).append("<p class=''>" + $(this).attr("data-content") + "</p>");
    });
}

function toEdit() {

    $(".title").each(function () {
        $(this).children().remove();
        $(this).append(
            "<input type='text' class='form-control title' value='" + $(this).attr("data-content") + "'> "
        );
    });

    $(".text").each(function () {
        $(this).children().remove();
        $(this).append("<textarea class='form-control text'>" + $(this).attr("data-content") +
            "</textarea>");
    });

    $("input, textarea").change(function () {
        var newText = $(this).val();
        $(this).parent().attr("data-content", newText);
    });

    $("input, textarea").focus(function () {
        console.log($(this))
    });
}

function addField() {
    var model = "<ul class = 'row'><li class='col-xs-4'> " +
        "<ul class='paper'> " +
        "<li class='title' data-content='please add title'></li> " +
        "<li class='text' data-content='<b>please add text</b>'></li> " +
        "</ul> " +
        "</li></ul>";

    $("form.text-form").append(model);
    toView();
}

$("#edit").click(function () {
    toEdit();
});

$("#view").click(function () {
    toView();
});

$("#add").click(function () {
    addField();
});

$(document).ready(function () {
    toEdit();



});
