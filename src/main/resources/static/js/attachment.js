$("#import").on("change", function(){

    var path = $(this).val();

    var formTag = $("#import-form")[0];
    var formData = new FormData(formTag);
    formData.append("file", $(this)[0].files[0]);


   $.ajax({
    url: "/fileUpload",
    data:formData,
    type:"post",
    cache: false,
    contentType: false,
    processData: false,
    method: 'POST',
    type: 'POST',
   success: function() {

    },
    error: function(jqxhr, textStatus, errThrown){
        console.log(textStatus);
        console.log(errThrown);
    }

});
    PopUpHide();
});

 function PopUpShowImageFormat() {
    $("#popup-container-image-format").show();
    $("#header").css("-webkit-filter", "blur(10px)");
    $("#header").css("pointer-events", "none");
    $("#show-btn").css("pointer-events", "none");
    $("#main-wrapper").css("pointer-events", "none");
    $("#show-btn").css("-webkit-filter", "blur(10px)");
    $("#main-wrapper").css("-webkit-filter", "blur(10px)");
}

  function PopUpHide() {
    window.location.href = $("#tab").text();
    $("#header").css("-webkit-filter", "blur(0px)");
    $("#header").css("pointer-events", "auto");
    $("#show-btn").css("pointer-events", "auto");
    $("#main-wrapper").css("pointer-events", "auto");
    $("#show-btn").css("-webkit-filter", "blur(0px)");
    $("#main-wrapper").css("-webkit-filter", "blur(0px)");
}

 function getAttachment(id) {
    $.ajax({
        url: '/attachment'
        type: "GET"
        success: function(result) {
 },
        error: function(jqXHR, textStatus, errorThrown) {
            // Code to handle error response
        }
    });
}
 function getAttachment() {
     $.ajax({
        url: '/attachments',
        type: 'GET',
        success: function(response) {
            // Update the attachment list with the new data
            $('#attachments').html(response);
        },
        error: function(xhr, textStatus, errorThrown) {
            console.log('Error:', errorThrown);
        }
    });
    }