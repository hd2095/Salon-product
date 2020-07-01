<jsp:include page="../global-imports/all-global-imports.jsp" />
<form class="form">	
<ul class="nav nav-tabs nav-tabs-line mb-5">
    <li class="nav-item">
        <a class="nav-link active" data-toggle="tab" href="#kt_tab_pane_1">
            <!-- <span class="nav-icon"><i class="flaticon2-chat-1"></i></span> -->
            <span class="nav-text">Appointment Details</span>
        </a>
    </li>
    <li class="nav-item">
        <a class="nav-link" data-toggle="tab" href="#kt_tab_pane_2">
            <!-- <span class="nav-icon"><i class="flaticon2-pie-chart-4"></i></span> -->
            <span class="nav-text">Client And Staff Details</span>
        </a>
    </li>
</ul>
<div class="tab-content mt-5" id="myTabContent">
    <div class="tab-pane fade show active" id="kt_tab_pane_1" role="tabpanel" aria-labelledby="kt_tab_pane_2">
    	<jsp:include page="../tabContent/appointment-tab-1.jsp" />
    </div>
    <div class="tab-pane fade" id="kt_tab_pane_2" role="tabpanel" aria-labelledby="kt_tab_pane_2">
    	<jsp:include page="../tabContent/appointment-tab-2.jsp" />
    </div>
</div>
</form>
<script src="assets/js/pages/datePicker.js"></script>