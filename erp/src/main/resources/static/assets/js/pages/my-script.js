"use strict";

const wrapper_div = document.querySelector('#kt_wrapper');
const header_div = document.querySelector('#kt_header');
const aside_div = document.querySelector('#kt_aside');
const content_div = document.querySelector('#kt_content');
aside_div.after(wrapper_div);
header_div.after(content_div);
