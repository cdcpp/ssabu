<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>

<title>글쓰기</title>

<script>
	window.dataLayer = window.dataLayer || [];
	function gtag() {
		dataLayer.push(arguments);
	}
	gtag('js', new Date());
	gtag('config', 'UA-64284762-12');

	function gtagFn(actionName, categoryName) {
		gtag('event', actionName, {
			'event_category' : categoryName
		});
	}
</script>

</head>
<body>
	<c:import url="/top" />
	<div id="cyWrap" class="container" style="margin-top: 20px;">
		<div id="editorWrap">
			<div class="topWrap ce-action-bar; row" style="text-align: center">
				<p>글쓰기</p>
			</div>
			<div id="postData">
				<!-- 글제목 -->
				<div class="postTitle ce-input-title">
					<input id="title" class="formTitle" type="text" maxlength="70"
						placeholder="소중한 일상을 기록해보세요"
						style="height: 150px; width: 70%; text-align: center;"> <input
						type="hidden" id="homeTid" value="54663785" /> <input
						type="hidden" id="homeNickName" value="박민철" /> <input
						type="hidden" id="postId" value="" /> <input type="hidden"
						id="UserTid" value="54663785" />
				</div>
				<!-- //글제목 -->
				<div></div>

				<!--S:글본문 영역-->
				<div class="ce-input-view sortable">



					<!--S:더미박스-->
					<script id="tpl-body-ce-ing-box" type="text/x-handlebars-template">
                    <div>불러오는 중......</div>
                </script>
					<!--E:더미박스-->


					<!--S:텍스트박스-->
					<script id="tpl-body-ce-text-box" type="text/x-handlebars-template">
                    <p>{{nl2br value}}</p>
                </script>
					<!--E:텍스트박스-->

					<!--S:이미지박스-->
					<script id="tpl-body-ce-image-box"
						type="text/x-handlebars-template">
                    <figure><img src="{{imageThumb value}}" alt class="ui-sortable-handle"></figure>
                </script>
					<!--S:이미지박스-->

					<!-- 미디어상자 -->
					<script id="tpl-body-ce-media-box"
						type="text/x-handlebars-template">
                    <div class="media">
                        <dl>
                            <dt class="title"><strong>{{title}}</strong></dt>
                            {{#if description}}
                                <p>{{description}}</p>
                            {{/if}}
                            </dt>
                            {{#if image}}
                            <dd class="thum"><img src="{{image}}" alt style="width:90px;height:67px"/></dd>
                            {{/if}}
                            <dd class="type"><a href="{{url}}" target="_blank">{{#if site_name}}{{site_name}}{{else}}{{url}}{{/if}}</a></dd>
                        </dl>
                    </div>
                </script>
					<!-- //미디어상자 -->

					<!-- 파일상자 -->
					<script id="tpl-body-ce-file-box" type="text/x-handlebars-template">
                    <ul>
                        <li><a href="{{url}}">{{name}} ({{humanBytes size}})</a></li>
                    </ul>
                </script>
					<!-- //파일상자 -->

					<!-- 액티콘상자 -->
					<script id="tpl-body-ce-acticon-box"
						type="text/x-handlebars-template">
                    <figure class="acticon-in" style="display: block;">
                        <img src="{{value}}" alt="{{title}}" title="{{title}}" class="ui-sortable-handle" style="top: 0px;">
                    </figure>
                </script>
					<!-- //액티콘상자 -->


					<!--S:툴바-->
					<br>
					<div class="toolbar ce-toolbar" style="top: 134px;">
						
						<br>
						<div class="btnWrap">
							<span title="사진추가" class="ce-toolbar-btn fa-camera"
								data-action="image"> <label>::사진 추가:: <input
									name="files[]" id="imageupload"
									onclick="gtagFn('photo', 'WriteBtn');" type="file" multiple=""></label></span>
						</div>
						<br>
						<div class="btnWrap">
							<label>::동영상/사이트추가::</label>
							<button title="동영상/사이트추가" class="ce-toolbar-btn fa-paperclip"
								onclick="gtagFn('url', 'WriteBtn');" type="button"
								data-action="media">동영상/사이트 추가</button>
						</div>
						<br>
						<div class="btnWrap">
							<span title="파일추가" class="ce-toolbar-btn fa-file-archive"
								data-action="file"> <label>::파일 추가:: <input
									name="files[]" id="fileupload"
									onclick="gtagFn('file', 'WriteBtn');" type="file" multiple=""></label></span>
						</div>
						<br>
						<div class="btnWrap">
							<label>::이모티콘 추가::</label>
							<button name="acticonIcon" title="액티콘추가"
								class="ce-toolbar-btn fa-acticon" id="acticonIcon1550209963980"
								onclick="gtagFn('acticon', 'WriteBtn');" type="button"
								data-action="acticon">이모티콘 추가</button>
						</div>
					</div>
					<br>
			
				</div>
				<!--E:글본문 영역-->

				<!-- 등록정보 -->
				<div class="postInfo">
					<form class="opt-write">
						<input type="hidden" id="published_date" value="" /> <input
							type="hidden" id="auth" value="4" /> <input type="hidden"
							id="comment_auth" value="4" /> <input type="hidden"
							id="search_yn" value="1" /> <input type="hidden"
							id="post_status" value="" />
						<fieldset>
							<legend>등록정보</legend>
							<ul class="dropdown-menu">
								<li class="auth"><span class="name">게시판 종류</span> <select
									name="upCode" onchange="selectDCate(this.value)">
										<option value="">::게시판 1::</option>
								</select></li>
								<br>
								<li class="auth"><span class="name">공개권한</span> <select
									name="upCode" onchange="selectDCate(this.value)">
										<option value="">::전체공개::</option>
										<option value="">::일촌공개::</option>
										<option value="">::비공개::</option>
								</select></li>
								<br>
								<li class="tags"><span class="name">태그</span> <input
									type="text" id="tags" value="" class="inputTag"
									placeholder="공백 또는 ,로 구분. 문자/숫자/_만 허용" maxlength="42" />
									<div class="tagList tag-area">
										<!--span class="ce-tag"><em class="label">태그1</em> <i class="ce-tag-delete">삭제</i></span-->
									</div></li>
								<br>
								<li class="comment"><span class="name">댓글쓰기</span> <select
									name="upCode" onchange="selectDCate(this.value)">
										<option value="">::모든 사용자에게 허용::</option>
										<option value="">::로그인 사용자에게 허용::</option>
										<option value="">::비허용::</option>
								</select></li>
								<br>
								<li class="search"><span class="name">검색</span> <select
									name="upCode" onchange="selectDCate(this.value)">
										<option value="">::허용::</option>
										<option value="">::비허용::</option>
								</select></li>
								<!-- 
							<li class="export"><span class="name">내보내기</span>
								<label class="ce-export-btn ce-naver" for="export_naver">
                                    <input id="export_naver" name="export_naver" value="1000" type="checkbox"><span>네이버</span>
                                </label>
								<label class="ce-export-btn ce-facebook" for="export_facebook">
                                    <input id="export_facebook" name="export_facebook" value="3000" type="checkbox"><span>페이스북</span>
                                </label>
								<label class="ce-export-btn ce-twitter" for="export_twitter">
                                    <input id="export_twitter" name="export_twitter" value="2000" type="checkbox"><span>트위터</span>
                                </label>
								<label class="ce-export-btn ce-kakaoStory" for="export_kakaostory">
                                    <input id="export_kakaostory" name="export_kakaostory" value="4000" type="checkbox"><span>카카오스토리</span>
                                </label>
							</li>
							 -->
							</ul>
						</fieldset>
					</form>
				</div>
				<!-- //등록정보 -->
				<div class="b_acticon_wrap">
					<div id="acticonLayerArea1111" class="acticonWrap"></div>
				</div>

			</div>
			<br>
			<div class="postBtn ce-action-bar">
				<!--button type="button" data-action="preview" class="ce-post-btn preview" data-dismiss="modal">미리보기</button-->
				<button type="button" data-action="save"
					class="ce-post-btn tempSave" data-dismiss="modal">임시저장</button>
				<!-- onclick="gtagFn('save', 'WriteBtn');" -->
				<button type="button" data-action="publish"
					class="ce-post-btn confirm" data-dismiss="modal">저장</button>
				<!-- onclick="gtagFn('confirm2', 'WriteBtn');" -->
				<button type="button" data-action="cancel"
					class="ce-post-btn ccanccel">취소</button>
			</div>


			<div class="modal-wrap"></div>

			<!-- 쓰기창 -->
			<script id="tpl-ce-input-modal" type="text/x-handlebars-template">
            <!-- dimmed -->
            <div id="dimmed"></div>
            <!-- //dimmed -->
            <div id="{{id}}" class="ce-input-box">
                <div class="ce-btn-text-input postTop">
                    <h3 class="{{name}}-title">{{title}}</h3>
                    <button class="ce-post-btn close ce-btn-cancel" type="button">닫기</button>

                </div>
                <div class="postData {{name}}-body">
                    {{{body}}}
                </div>
                <div class="postBottom">
                    {{#if submit-button}}
                    <button class="ce-post-btn save ce-btn-submit" type="button" data-action="{{data-action}}" data-edit="{{box-id}}">확인</button>
                    {{/if}}
                </div>
            </div>
        </script>
			<!-- //쓰기창 -->

		</div>
	</div>

	<script id="tpl-ce-alert-box" type="text/x-handlebars-template">
<div id="ce-alert-modal" class="modal fade ce-alert-box">
    <div class="modal-dialog">
        <div class="modal-content">

            <div class="modal-header">
                <button type="button" class="close ce-btn-cancel" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title ce-alert-box-title">{{title}}</h4>
            </div>

            <div class="modal-body ce-alert-box-body">
                {{nl2br message}}
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default ce-btn-cancel" data-dismiss="modal">닫기</button>
            </div>

        </div>
    </div>
</div>
</script>

	
<script type="text/javascript" src="/common/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="/common/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="/common/js/jquery.easing.1.3.js"></script>

<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
<script type="text/javascript" src="/common/js/jquery-ui.min.js"></script>
<script src="//cdn.jsdelivr.net/jquery.mcustomscrollbar/3.0.6/jquery.mCustomScrollbar.concat.min.js"></script>
<script type="text/javascript" src="/common/js/jquery.mCustomScrollbar.concat.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.3/handlebars.min.js"></script>
<script type="text/javascript" src="/common/js/handlebars-latest.js"></script>
<script type="text/javascript" src="/common/js/neo.common.js"></script>
<script type="text/javascript" src="/common/js/neo.common.js?v=20160222"></script>
<script type="text/javascript" src="/common/js/neo.thumb.js"></script>
s:file upload
<script src="https://cdnjs.cloudflare.com/ajax/libs/blueimp-file-upload/9.5.7/jquery.iframe-transport.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/blueimp-file-upload/9.5.7/jquery.fileupload.min.js"></script>
<script type="text/javascript" src="/common/js/jquery.iframe-transport.min.js"></script>
<script type="text/javascript" src="/common/js/jquery.fileupload.min.js"></script>
e:file upload
<script type="text/javascript" src="/common/js/md5.min.js"></script>
<script type="text/javascript" src="/common/js/cy.acticon.js"></script>
<script type="text/javascript" src="/common/js/cyco-editor_new.js?v=20160223"></script>
<!-- 
<[if lt IE 10]>
	<script type="text/javascript" src="/common/js/jquery.placeholder.js"></script>
	<script type="text/javascript">$(function() {$('input, textarea').placeholder()})</script>
<![endif]>

 -->	
 <script>
		var postBoolean = false;
	</script>


	<script type="text/javascript">
		jQuery(function($) {

			(function($) {
				'use strict';

				$(document)
						.ready(
								function() {

									localStorage.removeItem('page');
									localStorage.removeItem('write');
									localStorage.removeItem('cancel');

									//뒤로가기 버튼 감지 event
									if (window.history
											&& window.history.pushState) {
										$(window)
												.on(
														'popstate',
														function() {
															var hashLocation = location.hash;
															var hashSplit = hashLocation
																	.split("#!/");
															var hashName = hashSplit[1];
															if (hashName !== '') {
																var hash = window.location.hash;
																if (hash === '') {
																	//alert('back button was pressed.');
																	localStorage
																			.setItem(
																					'page',
																					3000);
																}
															}
														});
										window.history.pushState('forward',
												null, 'boardWrite');
									}
									localStorage.setItem('page', 2000);

									cyHome.typeSelectFunction(); // selectmenu ui
									if ($(window).height() < 725) { // window height가 기준치보다 작을때 툴바위치 조정
										$(".toolbar").css("top", 134);
									}

									//글 쓰기 일 경우에만
									//alert(postBoolean);
									if (!postBoolean) {
										//뒤로가기 버튼 감지 event
										if (window.history
												&& window.history.pushState) {
											$(window)
													.on(
															'popstate',
															function() {
																var hashLocation = location.hash;
																var hashSplit = hashLocation
																		.split("#!/");
																var hashName = hashSplit[1];
																if (hashName !== '') {
																	var hash = window.location.hash;
																	if (hash === '') {
																		//alert('back button was pressed.');
																		localStorage
																				.setItem(
																						'page',
																						3000);
																	}
																}
															});
											//window.history.pushState('forward', null, './#forward');
										}
										localStorage.setItem('page', 2000);
									}
									localStorage.setItem('write', 100);
								});

				$(window).load(function() {
					cyEditorBundle();

				});
			})($);

			var editor = new CycoEditor('.editorWrap', {
				'editor_id' : Date.now(),
			});

			//글수정
			if (postBoolean) {
				editor.edit("", "");
			} else {
				editor.initTitle();
			}
		});
	</script>
	<style>
.btn_bigclose img {
	width: 20px;
}
</style>
</body>
