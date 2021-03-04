(function($) {
    'use strict';

    var emailList = $('[data-email="list"]');
    var emailOpened = $('[data-email="opened"]');

    $('#mark-email').click(function() {
        $('.item .form-check').toggle();
    });

    // Load list of emails
    emailList.length && $.ajax({
        dataType: "json",
        url: "http://pages.revox.io/json/emails.json",
        success: function(data) {


            $.each(data.emails, function(i) {
                var obj = data.emails[i];
                var group = obj.group;
                var list = obj.list;

                var listViewGroupCont = $('<div/>', {
                    "class": "list-view-group-container"
                });
                listViewGroupCont.append('<div class="list-view-group-header"><span>' + group + '</span></div>');
                var ul = $('<ul/>', {
                    "class": "no-padding"
                });

                $.each(list, function(j) {
                    var $this = list[j];
                    var id = $this.id;
                    var dp = $this.dp;
                    var dpRetina = $this.dpRetina;
                    var to = $this.to.join();
                    var subject = $this.subject;
                    var body = $this.body.replace(/<(?:.|\n)*?>/gm, '');
                    var time = $this.time;
                    var li = '<li class="item padding-10 p-l-15" data-email-id="' + id + '"> \
                                <div class="thumbnail-wrapper d32 circular flex-shrink-0"> \
                                    <img width="40" height="40" alt="" data-src-retina="' + dpRetina + '" data-src="' + dp + '" src="' + dpRetina + '"> \
                                </div> \
                                <div class="form-check d-flex align-items-baseline no-margin p-l-5"> \
                                    <input type="checkbox" value="1" id="emailcheckbox-' + i + "-" + j + '"> \
                                    <label for="emailcheckbox-' + i + "-" + j + '"></label> \
                                </div> \
                                <div class="inline"> \
									<p class="recipients no-margin">' + to + '</p> \
									<div class="datetime">' + time + '</div> \
                                    <p class="subject no-margin">' + subject + '</p> \
                                    <p class="body no-margin"> \
                                     ' + body + ' \
                                    </p> \
                                </div> \
                                <div class="clearfix"></div> \
                            </li>';
                    ul.append(li);
                });

                listViewGroupCont.append(ul);
                emailList.append(listViewGroupCont);
            });
            emailList.ioslist();

        }
    });

    $('body').on('click', '.item .checkbox', function(e) {
        e.stopPropagation();
    });

    $('body').on('click', '.item', function(e) {
        e.stopPropagation();

        var id = $(this).attr('data-email-id');
        var email = null;
        var thumbnailWrapper = $(this).find('.thumbnail-wrapper');
        $.ajax({
            dataType: "json",
            url: "http://pages.revox.io/json/emails.json",
            success: function(data) {
                $.each(data.emails, function(i) {
                    var obj = data.emails[i];
                    var list = obj.list;
                    $.each(list, function(j) {
                        if (list[j].id == id) {
                            email = list[j];

                            return;
                        }
                    });

                    if (email != null) return;
                });

                emailOpened.find('.sender .name').text(email.from);
                emailOpened.find('.fromto .datetime').text(email.datetime);
                emailOpened.find('.subject').text(email.subject);
                emailOpened.find('.email-content-body').html(email.body);

								var thumbnailClasses = thumbnailWrapper.attr('class').replace('d32', 'd48');
                emailOpened.find('.thumbnail-wrapper').html(thumbnailWrapper.html()).attr('class', thumbnailClasses);

                $('.no-result').hide();
                $('.actions-dropdown').toggle();
                $('.actions, .email-content-wrapper').show();
                if ($.Pages.isVisibleSm() || $.Pages.isVisibleXs()) {
                    $('.split-list').toggleClass('slideLeft');
                }

                $(".email-content-wrapper").scrollTop(0);

                // Initialize email action menu 
                $('.menuclipper').menuclipper({
                    bufferWidth: 50
				});
            }
        });

        $('.item').removeClass('active');
        $(this).addClass('active');

    });

    // Toggle email sidebar on mobile view
    $('.toggle-secondary-sidebar').click(function(e) {
        e.stopPropagation();
        $('.secondary-sidebar').toggle();
    });

    $('.split-list-toggle').click(function() {
        $('.split-list').toggleClass('slideLeft');
    });

    $('.secondary-sidebar').click(function(e) {
        e.stopPropagation();
    })

    $(window).resize(function() {

        if ($(window).width() <= 1024) {
            $('.secondary-sidebar').hide();

        } else {
            $('.split-list').length && $('.split-list').removeClass('slideLeft');
            $('.secondary-sidebar').show();
        }
    });
	if($("#quill-compose").length > 0){
		// Compose
		var quillCompose = new Quill('#quill-compose', {
			modules: {
				toolbar: "#emailToolbar"
			},
			placeholder: 'Type here...',
			theme: 'snow'
		});

		// Avoid Quick Search Toggle
		quillCompose.on('selection-change', function(range, oldRange, source) {
			if (range === null && oldRange !== null) {
				$('body').removeClass('overlay-disabled');
			} else if (range !== null && oldRange === null){
				$('body').addClass('overlay-disabled');
			}	
		});
	}

	if($("#quill-reply").length > 0){
		var toolbarOptions = [
			['bold', 'italic', 'underline', 'strike'],        // toggled buttons
			[{ 'header': 1 }, { 'header': 2 }],               // custom button values
			[{ 'list': 'ordered'}, { 'list': 'bullet' }],
			[{ 'indent': '-1'}, { 'indent': '+1' }],          // outdent/indent
			[{ 'size': ['small', false, 'large', 'huge'] }],  // custom dropdown
			[{ 'align': [] }],
			['clean']                                         // remove formatting button
		];
		// Reply
		var quillReply = new Quill('#quill-reply', {
			modules: {
				toolbar: toolbarOptions
			},
			placeholder: 'Type here...',
			theme: 'snow'
		});

		// Avoid Quick Search Toggle
		quillReply.on('selection-change', function(range, oldRange, source) {
			if (range === null && oldRange !== null) {
				$('body').removeClass('overlay-disabled');
			} else if (range !== null && oldRange === null){
				$('body').addClass('overlay-disabled');
			}	
		});
	}
})(window.jQuery);