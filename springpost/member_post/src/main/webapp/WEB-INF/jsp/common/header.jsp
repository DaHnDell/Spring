<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<header class="container-fluid">
       <div class="container clearfix p-2">
           <a href="${cp}index" class="float-start"><img src="${cp}images/biglogo.png" alt="LOGO" class="img-fluid border-warning" width="250"></a>
           <h1 class="text-center fw-bold p-3">TJ UI BOARD DEV LAYOUT</h1>
       </div>
</header>
<div class="border border-warning">	
	<nav class="navbar bg-dark navbar-expand-sm">
	    <ul class="navbar-nav container justify-content-start">
	        <li class="mx-3 nav-item"><a class="nav-link text-warning" href="${cp}index">Main</a></li>
	        <li class="mx-3 nav-item"><a class="nav-link text-warning" href="${cp}">My</a></li>
	        <li class="mx-3 nav-item"><a class="nav-link text-warning" href="${cp}post/list?category=1">Notice</a></li>
	        <li class="mx-3 nav-item dropdown">
	            <a class="nav-link dropdown-toggle text-warning" href="#" role="button" data-bs-toggle="dropdown">Board</a>
	            <ul class="dropdown-menu">
	                <li class="dropdown-item"><a href="${cp}post/list">Free</a></li>
	                <li class="dropdown-item"><a href="${cp}post/list?category=3">Libraries</a></li>
	                <li class="dropdown-item"><a href="${cp}post/list?category=4">Gallery</a></li>
	            </ul>
	        </li>
	    </ul>
	</nav>
</div>