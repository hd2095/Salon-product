<jsp:include page="layout/side-nav.jsp" />
<jsp:include page="layout/header.jsp" />
<!--begin::Content-->
<div class="content d-flex flex-column flex-column-fluid"
	id="kt_content">
	<!--begin::Subheader-->
	<div class="subheader py-2 py-lg-4 subheader-solid" id="kt_subheader">
		<div
			class="container-fluid d-flex align-items-center justify-content-between flex-wrap flex-sm-nowrap">
			<!--begin::Info-->
			<div class="d-flex align-items-center flex-wrap mr-2">
				<!--begin::Page Title-->
				<h5 class="text-dark font-weight-bold mt-2 mb-2 mr-5">Marketing</h5>
				<!--end::Page Title-->
				<!--begin::Actions-->
				<div
					class="subheader-separator subheader-separator-ver mt-2 mb-2 mr-4 bg-gray-200"></div>
			</div>
			<div class="d-flex align-items-center">
				<button type="button"
					class="btn btn-light-warning font-weight-bolder btn-sm"
					data-toggle="modal" data-target="#newAppointmentModal">Add
					New</button>
				<!--end::Actions-->
			</div>
			<!--end::Info-->			
		</div>
	</div>
	<!--end::Subheader-->
</div>
<!--end::Content-->
<!-- Modal-->
<div class="modal fade" id="newAppointmentModal" data-backdrop="static"
	tabindex="-1" role="dialog" aria-labelledby="staticBackdrop"
	aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="newAppointmentModalLabel">New
					Appointment</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<i aria-hidden="true" class="ki ki-close"></i>
				</button>
			</div>
			<div class="modal-body">
				<div class="card card-custom card-sticky" id="kt_page_sticky_card">
					<div class="card-header">
						<div class="card-title">
							<h3 class="card-label">
								Sticky Form Actions <i class="mr-2"></i> <small class="">try
									to scroll the page</small>
							</h3>
						</div>
						<div class="card-toolbar">
							<a href="#" class="btn btn-light-primary font-weight-bolder mr-2">
								<i class="ki ki-long-arrow-back icon-sm"></i> Back
							</a>
							<div class="btn-group">
								<button type="button" class="btn btn-primary font-weight-bolder">
									<i class="ki ki-check icon-sm"></i> Save Form
								</button>
								<button type="button"
									class="btn btn-primary dropdown-toggle dropdown-toggle-split"
									data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false"></button>
								<div class="dropdown-menu dropdown-menu-sm dropdown-menu-right">
									<ul class="nav nav-hover flex-column">
										<li class="nav-item"><a href="#" class="nav-link"> <i
												class="nav-icon flaticon2-reload"></i> <span
												class="nav-text">Save & continue</span>
										</a></li>
										<li class="nav-item"><a href="#" class="nav-link"> <i
												class="nav-icon flaticon2-add-1"></i> <span class="nav-text">Save
													& add new</span>
										</a></li>
										<li class="nav-item"><a href="#" class="nav-link"> <i
												class="nav-icon flaticon2-power"></i> <span class="nav-text">Save
													& exit</span>
										</a></li>
									</ul>
								</div>
							</div>
						</div>
					</div>
					<div class="card-body">
						<!--begin::Form-->
						<form class="form" id="kt_form">
							<div class="row">
								<div class="col-xl-2"></div>
								<div class="col-xl-8">
									<div class="my-5">
										<h3 class=" text-dark font-weight-bold mb-10">Customer
											Info:</h3>
										<div class="form-group row">
											<label class="col-3">First Name</label>
											<div class="col-9">
												<input class="form-control form-control-solid" type="text"
													value="Nick" />
											</div>
										</div>
										<div class="form-group row">
											<label class="col-3">Last Name</label>
											<div class="col-9">
												<input class="form-control form-control-solid" type="text"
													value="Watson" />
											</div>
										</div>
										<div class="form-group row">
											<label class="col-3">Company Name</label>
											<div class="col-9">
												<input class="form-control form-control-solid" type="text"
													value="Loop Inc." /> <span class="form-text text-muted">If
													you want your invoices addressed to a company. Leave blank
													to use your full name.</span>
											</div>
										</div>
										<div class="form-group row">
											<label class="col-3">Contact Phone</label>
											<div class="col-9">
												<div class="input-group input-group-solid">
													<div class="input-group-prepend">
														<span class="input-group-text"><i
															class="la la-phone"></i></span>
													</div>
													<input type="text" class="form-control form-control-solid"
														value="+45678967456" placeholder="Phone" />
												</div>
												<span class="form-text text-muted">We'll never share
													your email with anyone else.</span>
											</div>
										</div>
										<div class="form-group row">
											<label class="col-3">Email Address</label>
											<div class="col-9">
												<div class="input-group input-group-solid">
													<div class="input-group-prepend">
														<span class="input-group-text"><i class="la la-at"></i></span>
													</div>
													<input type="text" class="form-control form-control-solid"
														value="nick.watson@loop.com" placeholder="Email" />
												</div>
											</div>
										</div>
										<div class="form-group row">
											<label class="col-3">Company Site</label>
											<div class="col-9">
												<div class="input-group input-group-solid">
													<input type="text" class="form-control form-control-solid"
														placeholder="Username" value="loop" />
													<div class="input-group-append">
														<span class="input-group-text">.com</span>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="separator separator-dashed my-10"></div>
									<div class="my-5">
										<h3 class=" text-dark font-weight-bold mb-10">Address
											Details:</h3>
										<div class="form-group row">
											<label class="col-3">Country</label>
											<div class="col-9">
												<select class="form-control form-control-solid">
													<option value="AF">Afghanistan</option>
													<option value="AX">Åland Islands</option>
													<option value="AL">Albania</option>
													<option value="DZ">Algeria</option>
													<option value="AS">American Samoa</option>
													<option value="AD">Andorra</option>
													<option value="AO">Angola</option>
													<option value="AI">Anguilla</option>
													<option value="AQ">Antarctica</option>
													<option value="AG">Antigua and Barbuda</option>
													<option value="AR">Argentina</option>
													<option value="AM">Armenia</option>
													<option value="AW">Aruba</option>
													<option value="AU">Australia</option>
													<option value="AT">Austria</option>
													<option value="AZ">Azerbaijan</option>
													<option value="BS">Bahamas</option>
													<option value="BH">Bahrain</option>
													<option value="BD">Bangladesh</option>
													<option value="BB">Barbados</option>
													<option value="BY">Belarus</option>
													<option value="BE">Belgium</option>
													<option value="BZ">Belize</option>
													<option value="BJ">Benin</option>
													<option value="BM">Bermuda</option>
													<option value="BT">Bhutan</option>
													<option value="BO">Bolivia, Plurinational State of</option>
													<option value="BQ">Bonaire, Sint Eustatius and
														Saba</option>
													<option value="BA">Bosnia and Herzegovina</option>
													<option value="BW">Botswana</option>
													<option value="BV">Bouvet Island</option>
													<option value="BR">Brazil</option>
													<option value="IO">British Indian Ocean Territory</option>
													<option value="BN">Brunei Darussalam</option>
													<option value="BG">Bulgaria</option>
													<option value="BF">Burkina Faso</option>
													<option value="BI">Burundi</option>
													<option value="KH">Cambodia</option>
													<option value="CM">Cameroon</option>
													<option value="CA">Canada</option>
													<option value="CV">Cape Verde</option>
													<option value="KY">Cayman Islands</option>
													<option value="CF">Central African Republic</option>
													<option value="TD">Chad</option>
													<option value="CL">Chile</option>
													<option value="CN">China</option>
													<option value="CX">Christmas Island</option>
													<option value="CC">Cocos (Keeling) Islands</option>
													<option value="CO">Colombia</option>
													<option value="KM">Comoros</option>
													<option value="CG">Congo</option>
													<option value="CD">Congo, the Democratic Republic
														of the</option>
													<option value="CK">Cook Islands</option>
													<option value="CR">Costa Rica</option>
													<option value="CI">Côte d'Ivoire</option>
													<option value="HR">Croatia</option>
													<option value="CU">Cuba</option>
													<option value="CW">Curaçao</option>
													<option value="CY">Cyprus</option>
													<option value="CZ">Czech Republic</option>
													<option value="DK">Denmark</option>
													<option value="DJ">Djibouti</option>
													<option value="DM">Dominica</option>
													<option value="DO">Dominican Republic</option>
													<option value="EC">Ecuador</option>
													<option value="EG">Egypt</option>
													<option value="SV">El Salvador</option>
													<option value="GQ">Equatorial Guinea</option>
													<option value="ER">Eritrea</option>
													<option value="EE">Estonia</option>
													<option value="ET">Ethiopia</option>
													<option value="FK">Falkland Islands (Malvinas)</option>
													<option value="FO">Faroe Islands</option>
													<option value="FJ">Fiji</option>
													<option value="FI">Finland</option>
													<option value="FR">France</option>
													<option value="GF">French Guiana</option>
													<option value="PF">French Polynesia</option>
													<option value="TF">French Southern Territories</option>
													<option value="GA">Gabon</option>
													<option value="GM">Gambia</option>
													<option value="GE">Georgia</option>
													<option value="DE">Germany</option>
													<option value="GH">Ghana</option>
													<option value="GI">Gibraltar</option>
													<option value="GR">Greece</option>
													<option value="GL">Greenland</option>
													<option value="GD">Grenada</option>
													<option value="GP">Guadeloupe</option>
													<option value="GU">Guam</option>
													<option value="GT">Guatemala</option>
													<option value="GG">Guernsey</option>
													<option value="GN">Guinea</option>
													<option value="GW">Guinea-Bissau</option>
													<option value="GY">Guyana</option>
													<option value="HT">Haiti</option>
													<option value="HM">Heard Island and McDonald
														Islands</option>
													<option value="VA">Holy See (Vatican City State)</option>
													<option value="HN">Honduras</option>
													<option value="HK">Hong Kong</option>
													<option value="HU">Hungary</option>
													<option value="IS">Iceland</option>
													<option value="IN">India</option>
													<option value="ID">Indonesia</option>
													<option value="IR">Iran, Islamic Republic of</option>
													<option value="IQ">Iraq</option>
													<option value="IE">Ireland</option>
													<option value="IM">Isle of Man</option>
													<option value="IL">Israel</option>
													<option value="IT">Italy</option>
													<option value="JM">Jamaica</option>
													<option value="JP">Japan</option>
													<option value="JE">Jersey</option>
													<option value="JO">Jordan</option>
													<option value="KZ">Kazakhstan</option>
													<option value="KE">Kenya</option>
													<option value="KI">Kiribati</option>
													<option value="KP">Korea, Democratic People's
														Republic of</option>
													<option value="KR">Korea, Republic of</option>
													<option value="KW">Kuwait</option>
													<option value="KG">Kyrgyzstan</option>
													<option value="LA">Lao People's Democratic
														Republic</option>
													<option value="LV">Latvia</option>
													<option value="LB">Lebanon</option>
													<option value="LS">Lesotho</option>
													<option value="LR">Liberia</option>
													<option value="LY">Libya</option>
													<option value="LI">Liechtenstein</option>
													<option value="LT">Lithuania</option>
													<option value="LU">Luxembourg</option>
													<option value="MO">Macao</option>
													<option value="MK">Macedonia, the former Yugoslav
														Republic of</option>
													<option value="MG">Madagascar</option>
													<option value="MW">Malawi</option>
													<option value="MY">Malaysia</option>
													<option value="MV">Maldives</option>
													<option value="ML">Mali</option>
													<option value="MT">Malta</option>
													<option value="MH">Marshall Islands</option>
													<option value="MQ">Martinique</option>
													<option value="MR">Mauritania</option>
													<option value="MU">Mauritius</option>
													<option value="YT">Mayotte</option>
													<option value="MX">Mexico</option>
													<option value="FM">Micronesia, Federated States of</option>
													<option value="MD">Moldova, Republic of</option>
													<option value="MC">Monaco</option>
													<option value="MN">Mongolia</option>
													<option value="ME">Montenegro</option>
													<option value="MS">Montserrat</option>
													<option value="MA">Morocco</option>
													<option value="MZ">Mozambique</option>
													<option value="MM">Myanmar</option>
													<option value="NA">Namibia</option>
													<option value="NR">Nauru</option>
													<option value="NP">Nepal</option>
													<option value="NL">Netherlands</option>
													<option value="NC">New Caledonia</option>
													<option value="NZ">New Zealand</option>
													<option value="NI">Nicaragua</option>
													<option value="NE">Niger</option>
													<option value="NG">Nigeria</option>
													<option value="NU">Niue</option>
													<option value="NF">Norfolk Island</option>
													<option value="MP">Northern Mariana Islands</option>
													<option value="NO">Norway</option>
													<option value="OM">Oman</option>
													<option value="PK">Pakistan</option>
													<option value="PW">Palau</option>
													<option value="PS">Palestinian Territory, Occupied</option>
													<option value="PA">Panama</option>
													<option value="PG">Papua New Guinea</option>
													<option value="PY">Paraguay</option>
													<option value="PE">Peru</option>
													<option value="PH">Philippines</option>
													<option value="PN">Pitcairn</option>
													<option value="PL">Poland</option>
													<option value="PT">Portugal</option>
													<option value="PR">Puerto Rico</option>
													<option value="QA">Qatar</option>
													<option value="RE">Réunion</option>
													<option value="RO">Romania</option>
													<option value="RU">Russian Federation</option>
													<option value="RW">Rwanda</option>
													<option value="BL">Saint Barthélemy</option>
													<option value="SH">Saint Helena, Ascension and
														Tristan da Cunha</option>
													<option value="KN">Saint Kitts and Nevis</option>
													<option value="LC">Saint Lucia</option>
													<option value="MF">Saint Martin (French part)</option>
													<option value="PM">Saint Pierre and Miquelon</option>
													<option value="VC">Saint Vincent and the
														Grenadines</option>
													<option value="WS">Samoa</option>
													<option value="SM">San Marino</option>
													<option value="ST">Sao Tome and Principe</option>
													<option value="SA">Saudi Arabia</option>
													<option value="SN">Senegal</option>
													<option value="RS">Serbia</option>
													<option value="SC">Seychelles</option>
													<option value="SL">Sierra Leone</option>
													<option value="SG">Singapore</option>
													<option value="SX">Sint Maarten (Dutch part)</option>
													<option value="SK">Slovakia</option>
													<option value="SI">Slovenia</option>
													<option value="SB">Solomon Islands</option>
													<option value="SO">Somalia</option>
													<option value="ZA">South Africa</option>
													<option value="GS">South Georgia and the South
														Sandwich Islands</option>
													<option value="SS">South Sudan</option>
													<option value="ES">Spain</option>
													<option value="LK">Sri Lanka</option>
													<option value="SD">Sudan</option>
													<option value="SR">Suriname</option>
													<option value="SJ">Svalbard and Jan Mayen</option>
													<option value="SZ">Swaziland</option>
													<option value="SE">Sweden</option>
													<option value="CH">Switzerland</option>
													<option value="SY">Syrian Arab Republic</option>
													<option value="TW">Taiwan, Province of China</option>
													<option value="TJ">Tajikistan</option>
													<option value="TZ">Tanzania, United Republic of</option>
													<option value="TH">Thailand</option>
													<option value="TL">Timor-Leste</option>
													<option value="TG">Togo</option>
													<option value="TK">Tokelau</option>
													<option value="TO">Tonga</option>
													<option value="TT">Trinidad and Tobago</option>
													<option value="TN">Tunisia</option>
													<option value="TR">Turkey</option>
													<option value="TM">Turkmenistan</option>
													<option value="TC">Turks and Caicos Islands</option>
													<option value="TV">Tuvalu</option>
													<option value="UG">Uganda</option>
													<option value="UA">Ukraine</option>
													<option value="AE">United Arab Emirates</option>
													<option value="GB">United Kingdom</option>
													<option value="US" selected>United States</option>
													<option value="UM">United States Minor Outlying
														Islands</option>
													<option value="UY">Uruguay</option>
													<option value="UZ">Uzbekistan</option>
													<option value="VU">Vanuatu</option>
													<option value="VE">Venezuela, Bolivarian Republic
														of</option>
													<option value="VN">Viet Nam</option>
													<option value="VG">Virgin Islands, British</option>
													<option value="VI">Virgin Islands, U.S.</option>
													<option value="WF">Wallis and Futuna</option>
													<option value="EH">Western Sahara</option>
													<option value="YE">Yemen</option>
													<option value="ZM">Zambia</option>
													<option value="ZW">Zimbabwe</option>
												</select>
											</div>
										</div>
										<div class="form-group row">
											<label class="col-3">Address Line 1</label>
											<div class="col-9">
												<input class="form-control form-control-solid" type="text"
													value="District 6 1352 W. Olive Ave" />
											</div>
										</div>
										<div class="form-group row">
											<label class="col-3">Address Line 2</label>
											<div class="col-9">
												<input class="form-control form-control-solid" type="text"
													value="Fresno 559-488-4020" />
											</div>
										</div>
										<div class="form-group row">
											<label class="col-3">City</label>
											<div class="col-9">
												<input class="form-control form-control-solid" type="text"
													value="Polo Alto" />
											</div>
										</div>
										<div class="form-group row">
											<label class="col-3">State / Province / Region</label>
											<div class="col-9">
												<input class="form-control form-control-solid" type="text"
													value="Los Angeles" />
											</div>
										</div>
										<div class="form-group row">
											<label class="col-3">Zip / Postal Code</label>
											<div class="col-9">
												<input class="form-control form-control-solid" type="text"
													value="780456" />
											</div>
										</div>
										<div class="form-group row">
											<label class="col-3"></label>
											<div class="col-9">
												<div class="checkbox-single">
													<label class="checkbox"> <input type="checkbox" />
														Use as shipping address. <span></span>
													</label>
												</div>
											</div>
										</div>
									</div>
									<div class="separator separator-dashed my-10"></div>
									<div class="my-52">
										<h3 class=" text-dark font-weight-bold mb-10">Security:</h3>
										<div class="form-group row">
											<label class="col-3">Login verification</label>
											<div class="col-9">
												<button type="button"
													class="btn btn-light-primary font-weight-bold btn-sm">Setup
													login verification</button>
												<div class="form-text text-muted mt-3">
													After you log in, you will be asked for additional
													information to confirm your identity and protect your
													account from being compromised. <a href="#">Learn more</a>.
												</div>
											</div>
										</div>
										<div class="form-group row">
											<label class="col-3">Password reset verification</label>
											<div class="col-9">
												<div class="checkbox-single">
													<label class="checkbox mb-2"> <input
														type="checkbox" /> Require personal information to reset
														your password. <span></span>
													</label>
												</div>
												<div class="form-text text-muted">
													For extra security, this requires you to confirm your email
													or phone number when you reset your password. <a href="#"
														class="font-weight-bold">Learn more</a>.
												</div>
											</div>
										</div>
										<div class="form-group row mt-10">
											<label class="col-3"></label>
											<div class="col-9">
												<button type="button"
													class="btn btn-light-danger font-weight-bold btn-sm">Deactivate
													your account ?</button>
											</div>
										</div>
									</div>
								</div>
								<div class="col-xl-2"></div>
							</div>
						</form>
						<!--end::Form-->
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-light-primary font-weight-bold"
					data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary font-weight-bold">Save
					changes</button>
			</div>
		</div>
	</div>
</div>
<!--End Modal-->
<script src="http://localhost:8081/assets/js/pages/my-script.js"></script>