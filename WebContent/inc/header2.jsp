<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%
request.setCharacterEncoding("UTF-8");
%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
    
<div class="header-area ">
            <div id="sticky-header" class="main-header-area">
                <div class="container-fluid">
                    <div class="header_bottom_border">
                        <div class="row align-items-center">
                            <div class="col-xl-2 col-lg-2">
                                <div class="logo">
                                    <a href="index.jsp">
                                        <img src="../img/logo.png" alt="">
                                    </a>
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6">
                                <div class="main-menu  d-none d-lg-block">
                                    <nav>
                                        <ul id="navigation">
                                            <li><a class="active" href="index.jsp">home</a></li>
                                            <li><a href="about.jsp">About</a></li>
                                            <li><a class="" href="travel_destination.jsp">Destination</a></l/li>
                                            <li><a href="#">pages <i class="ti-angle-down"></i></a>
                                                <ul class="submenu">
                                                        <li><a href="destination_details.jsp">Destinations details</a></li>
                                                        <li><a href="elements.jsp">elements</a></li>
                                                </ul>
                                            </li>
                                            <li><a href="#">blog <i class="ti-angle-down"></i></a>
                                                <ul class="submenu">
                                                    <li><a href="blog.jsp">blog</a></li>
                                                    <li><a href="single-blog.jsp">single-blog</a></li>
                                                </ul>
                                            </li>
                                            <li><a href="contact.jsp">Contact</a></li>
                                        </ul>
                                    </nav>
                                </div>
                            </div>
                            <div class="col-xl-4 col-lg-4 d-none d-lg-block">
                                <div class="social_wrap d-flex align-items-center justify-content-end">
        <!-- login check start -->
		<c:if test="${id ==null }">
			<div id="Toplogin">
	 			<a href="${contextPath }/login.jsp">Login</a> | 
	 			<a href="${contextPath }/Join.jsp">Join</a>
	 		</div>
		</c:if>
		<c:if test="${id !=null }">
			<div id="Toplogin">
	 			<a href="${contextPath }/user/myPage.do">${id }님</a> | 
	 			<a href="${contextPath }/user/logout.do">Logout</a>
	 		</div>
		</c:if>
		<!-- login check end -->
                                    <div class="number">
                                        <p> <i class="fa fa-phone"></i> 10(256)-928 256</p>
                                    </div>
                                    <div class="social_links d-none d-xl-block">
                                        <ul>
                                            <li><a href="#"> <i class="fa fa-instagram"></i> </a></li>
                                            <li><a href="#"> <i class="fa fa-linkedin"></i> </a></li>
                                            <li><a href="#"> <i class="fa fa-facebook"></i> </a></li>
                                            <li><a href="#"> <i class="fa fa-google-plus"></i> </a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div class="seach_icon">
                                <a data-toggle="modal" data-target="#exampleModalCenter" href="#">
                                    <i class="fa fa-search"></i>
                                </a>
                            </div>
                            <div class="col-12">
                                <div class="mobile_menu d-block d-lg-none"></div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>