<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
  
  	<c:set var="project" value="/dahae/"/>
	<c:set var="folder" value="/dahae/cart/"/>
	<c:set var="upload" value="/dahae/upload/"/>
   
	<c:set var="page_cart" value = "장 바 구 니"/>
	
	<c:set var="msg_putIn" value ="장바구니에 상품이 정상적으로 담겼습니다"/>
	<c:set var="msg_list_x" value="장바구니에 담긴 상품이 없습니다"/>
	
	<c:set var="str_goods" value = "상 &nbsp;&nbsp;&nbsp;품"/>
	<c:set var="str_count" value = "수 량"/>
	<c:set var="str_goods_price" value = "상품 금액"/>
	<c:set var="str_total_price" value = "상품 총금액"/>
	<c:set var="str_delete" value = "삭 제"/>
	<c:set var="str_total" value = "총 금액"/>
	
	<c:set var="btn_go_cart" value = "장바구니로 갈래요"/>
	<c:set var="btn_con" value="쇼핑 계속할래요"/>
	<c:set var="btn_buy" value="구매하기"/>
	<c:set var="btn_delete_all" value="전체삭제하기"/>