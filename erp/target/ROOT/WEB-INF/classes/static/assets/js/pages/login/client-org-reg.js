"use strict";


jQuery(document).ready(function() {
    $('#org_form_submit').on('click', function (e) {
        e.preventDefault();
        document.getElementById("org_form").action = "createOrganization";
		document.getElementById("org_form").submit();
    });
});