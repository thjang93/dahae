<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
  
<c:set var = "project" value = "/dahae/"/>
<c:set var = "folder" value = "/dahae/order/"/>
<c:set var = "upload" value = "/dahae/upload/"/>

<c:set var = "msg_order_complete0" value = "주문 및 결제가 완료 되었습니다."/>
<c:set var = "msg_order_complete1" value = "주문이 완료 되었습니다."/>

<c:set var = "page_order" value = "주문 페이지"/>

<c:set var = "str_goods_name" value = "상품명"/>
<c:set var = "str_goods_img" value = "상품이미지"/>
<c:set var = "str_goods_info" value = "상품정보"/>
<c:set var = "str_goods_count" value = "수량"/>
<c:set var = "str_goods_price" value = "가격"/>
<c:set var = "str_sendto" value = "받는분"/>
<c:set var = "str_name" value = "이름"/>
<c:set var = "str_tel" value = "연락처"/>
<c:set var = "str_addr" value = "주소"/>
<c:set var = "str_zipcode" value = "우편번호"/>
<c:set var = "str_delivery_msg" value = "배송시 요청사항"/>
<c:set var = "str_total_price" value = "총 결제 금액"/>
<c:set var = "str_pay_method" value = "결제 수단"/>
<c:set var = "str_track" value = "배송조회"/>
<c:set var = "str_delivery_addr" value = "배송지"/>

<c:set var = "btn_order" value = "주문하기"/>
<c:set var = "btn_order_cancel" value = "주문취소"/>
<c:set var = "btn_list" value = "목록"/>