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


$("#edit").click(function () {
    toEdit();
});

$("#view").click(function () {
    toView();
});

$(document).ready(function () {
    toEdit();
});
