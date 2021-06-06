$(document).ready(function() {
    $("#languageSelector").change(function () {
        let selectedOption = $('#languageSelector').val();
        if (selectedOption != ''){
            window.location.replace('?lang=' + selectedOption);
        }
        console.log("Buvo paselectinta");
    });
});