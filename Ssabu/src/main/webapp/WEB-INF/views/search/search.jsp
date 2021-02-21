<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	String ctx = getServletContext().getContextPath();
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../top.jsp"/>
<head>
    <meta charset="UTF-8">
    <title>Untitled Document</title>
	<meta name="Author" content=""/>

	
	<meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- Popper JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    
    <link rel="stylesheet" type="text/css" href="<%=ctx %>/css/search.css">
</head>
<body>
<div class="container">
    <br>
    <h4>인물 검색 결과</h4><span id="tagSRnum1">[검색결과 ${mListCount } 건]</span>
    <hr>
    <div>
        <table class="table">
            <thead>
                <tr>
                    <td width="10%"></td>
                    <td width="30%">이메일</td>
                    <td width="15%">성별</td>
                    <td width="45%">최신글</td>
                </tr>
            </thead>
            <tbody>
               <c:if test="${mList eq null }">
                   <a href="#">검색 결과가 없습니다.</a>
               </c:if>
               <c:if test="${mList ne null }">
               	<c:forEach items="${mList}" var="m">
	                <tr>
	                    <td>
	                    	<c:if test="${m.mImage ne null }">
	                    	<a href="mainboard?mIdx=${m.mIdx}">
	                        	<img src="<%=ctx %>/images/${m.mImage}" id="profileImg">
	                        </a>
	                        </c:if>
	                        <c:if test="${m.mImage eq null }">
	                        <a href="mainboard?mIdx=${m.mIdx}">
	                        	<img src="<%=ctx %>/images/noprofile.jpg" id="profileImg">
	                        	</a>
	                        </c:if>
	                    </td>
	                    <td>
	                        <span>${m.email}</span>
	                    </td>
	                    <td>
	                        <span>${m.sex }</span>
	                    </td>
	                    <td>
	                    	<c:if test="${m.newPostSubject ne null }">
	                        	<span><a href="showpost?pIdx=${m.pIdx }">${m.newPostSubject }</a></span>
	                        </c:if>
	                        <c:if test="${m.newPostSubject eq null }">
	                        	<span>최근 작성한 게시물이 없습니다.</span>
	                        </c:if>
	                    </td>
	                </tr>
	                </c:forEach>
                </c:if>
                <tr>
                    <td colspan="4" style="text-align: right"><a href="">검색결과 더보기</a></td>
                </tr>
            </tbody>
        </table>
    </div>
    <h4>태그 검색 결과 </h4><span id="tagSRnum2">[검색결과 ${pListCount } 건]</span>
    <hr>
    <div>
        <table class="table">
            <thead>
                <tr>
                    <td width="30%">글번호</td>
                    <td width="70%">글제목</td>
                </tr>
            </thead>
            <tbody>
               <c:if test="${pList eq null }">
                   <a href="#">검색 결과가 없습니다.</a>
               </c:if>
               <c:if test="${pList ne null }">
	               <c:forEach items="#{pList }" var="p">
	                <tr style="cursor:pointer;" onClick="location.href='showpost?pIdx=${p.PIdx}'">
	                    <td >
	                        <span>${p.PIdx}</span>
	                    </td>
	                    <td>
	                        <span>
	                        	${p.subject }
	                        </span>
	                    </td>
	                </tr>
	                <tr>
	                    <td colspan="2" style="text-align: right"><a href="">검색결과 더보기</a></td>
	                </tr>
	                </c:forEach>
                </c:if>
            </tbody>
        </table>
    </div>
</div>


</body>
</html>
