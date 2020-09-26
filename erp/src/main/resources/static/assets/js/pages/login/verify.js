"use strict";


jQuery(document).ready(function() {
    $('#verify_form_submit').on('click', function (e) {
        e.preventDefault();
        document.getElementById("verify_form").action = "verify";
		document.getElementById("verify_form").submit();
    });
});