/* ============================================================
 * Form Elements
 * This file applies various jQuery plugins to form elements
 * For DEMO purposes only. Extract what you need.
 * ============================================================ */
(function ($) {

	'use strict';

	var getBaseURL = function () {
		var url = document.URL;
		return url.substr(0, url.lastIndexOf('/'));
	}

	$(document).ready(function () {

		//Indeterminate checkbox
		$("#checkboxIndeterminate").prop('indeterminate', true)

		// Multiselect - Select2 plug-in
		$("#multi").val(["Jim", "Lucy"]).select2();

		// Date Pickers
		$('#datepicker-range, #datepicker-component, #datepicker-component2').datepicker();

		$('#datepicker-embeded').datepicker({
			daysOfWeekDisabled: "0,1"
		});


		//Typehead Sample Code

		// Basic Sample using Bloodhound
		// constructs the suggestion engine

		var countries = new Bloodhound({
			datumTokenizer: Bloodhound.tokenizers.whitespace,
			queryTokenizer: Bloodhound.tokenizers.whitespace,
			prefetch: 'http://pages.revox.io/json/countries-list.json'
		});

		var bestPictures = new Bloodhound({
			datumTokenizer: Bloodhound.tokenizers.obj.whitespace('value'),
			queryTokenizer: Bloodhound.tokenizers.whitespace,
			prefetch: 'http://pages.revox.io/json/drop-countries.json',
			remote: {
				url: 'http://pages.revox.io/json/drop-countries.json',
				wildcard: '%QUERY'
			}
		});
		// passing in `null` for the `options` arguments will result in the default
		// options being used
		$('.sample-typehead').typeahead(null, {
			name: 'countries',
			source: countries
		});

		// Custom Template
		$('#custom-templates .typeahead').typeahead(null, {
			name: 'best-pictures',
			display: 'value',
			source: bestPictures,
			templates: {
				empty: [
					'<div class="empty-message">',
					'unable to find any Best Picture winners that match the current query',
					'</div>'
				].join('\n'),
				suggestion: Handlebars.compile('<div>{{value}}– {{year}}</div>')
			}
		});

		$('#daterangepicker').daterangepicker({
			timePicker: true,
			timePickerIncrement: 30,
			format: 'MM/DD/YYYY h:mm A'
		}, function (start, end, label) {
			console.log(start.toISOString(), end.toISOString(), label);
		});

		/* Time picker
		 * https://github.com/m3wolf/bootstrap3-timepicker
		 */
		$('#timepicker').timepicker().on('show.timepicker', function (e) {
			var widget = $('.bootstrap-timepicker-widget');
			widget.find('.glyphicon-chevron-up').removeClass().addClass('pg-arrow_maximize');
			widget.find('.glyphicon-chevron-down').removeClass().addClass('pg-arrow_minimize');
		});



		// disabling dates
		var nowTemp = new Date();
		var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp.getDate(), 0, 0, 0, 0);

		// Input mask - Input helper
		$(function ($) {
			$("#date").mask("99/99/9999");
			$("#phone").mask("(999) 999-9999");
			$("#tin").mask("99-9999999");
			$("#ssn").mask("999-99-9999");
		});
		// Autonumeric plug-in - automatic addition of dollar signs,etc controlled by tag attributes
		$('.autonumeric').autoNumeric('init');

		// Drag n Drop up-loader
		$("div#myId").dropzone({
			url: "/file/post"
		});
		// Single instance of tag inputs - can be initiated with simply using data-role="tagsinput" attribute in any input field
		$('.custom-tag-input').tagsinput({
		});

		// Editor
		// https://quilljs.com/docs/quickstart/
		var toolbarOptions = [
			['bold', 'italic', 'underline', 'strike'],        // toggled buttons
			['blockquote', 'code-block'],

			[{ 'header': 1 }, { 'header': 2 }],               // custom button values
			[{ 'list': 'ordered' }, { 'list': 'bullet' }],
			[{ 'script': 'sub' }, { 'script': 'super' }],      // superscript/subscript
			[{ 'indent': '-1' }, { 'indent': '+1' }],          // outdent/indent
			[{ 'direction': 'rtl' }],                         // text direction

			[{ 'size': ['small', false, 'large', 'huge'] }],  // custom dropdown
			[{ 'header': [1, 2, 3, 4, 5, 6, false] }],

			[{ 'color': [] }, { 'background': [] }],          // dropdown with defaults from theme
			[{ 'font': [] }],
			[{ 'align': [] }],

			['clean']                                         // remove formatting button
		];
		var quill = new Quill('#quill', {
			modules: {
				toolbar: toolbarOptions
			},
			placeholder: 'Type here...',
			theme: 'snow'
		});
		//Avoid Quick Search Toggle
		quill.on('selection-change', function (range, oldRange, source) {
			if (range === null && oldRange !== null) {
				$('body').removeClass('overlay-disabled');
			} else if (range !== null && oldRange === null) {
				$('body').addClass('overlay-disabled');
			}
		});

	});

})(window.jQuery);