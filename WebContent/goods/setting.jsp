<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	
	<c:set var="project" value = "/dahae/"/>
	<c:set var="folder" value="/dahae/goods/"/>
	<c:set var="upload" value="/dahae/upload/"/>
   
	<c:set var="page_listM" value = "상 품 목 록"/>
	<c:set var="page_write" value = "상 품 등 록"/>
	<c:set var="page_content" value = "상 품 보 기"/>
	<c:set var="page_modify" value ="상 품 수 정"/>
	<c:set var="page_delete" value = "상 품 삭 제"/>
	
	<c:set var="msg_list_x" value ="등록된 상품이 없습니다."/>
    <c:set var="msg_delete_check" value ="이 게시글을 삭제하시겠습니까?"/>
    <c:set var="msg_modify_check" value ="이 게시글을 수정하시겠습니까?"/>
	<c:set var="msg_modify" value = "수정할 정보를 입력하세요"/>
	<c:set var="msg_searchlist_x" value = "검색 결과가 없습니다"/>
	
	<c:set var="str_count" value = "전체글"/>
	<c:set var="str_num" value="글번호"/>
	<c:set var="str_write" value = "상품등록"/>
	<c:set var="str_admin" value = "상품관리"/>
	<c:set var="str_list" value = "상품목록"/>
	<c:set var="str_goods_name" value="상품명"/>
	<c:set var="str_category_name" value="카테고리명"/>
	<c:set var="str_goods_info" value="상품간략설명"/>
	<c:set var="str_image" value="상품이미지"/>
	<c:set var="str_price" value="판매액"/>
	<c:set var="str_sell_count" value="판매량"/>
	<c:set var="str_reg_date" value = "등록일"/>
	<c:set var="str_specimg1" value="상품상세이미지1"/>
	<c:set var="str_specimg2" value="상품상세이미지2"/>
	<c:set var="str_specimg3" value="상품상세이미지3"/>
	
	<c:set var="btn_write" value = "등록"/>
	<c:set var="btn_cancel" value = "취소"/>
	<c:set var="btn_list" value = "목록"/>
	<c:set var="btn_modify" value = "상품수정"/>
	<c:set var="btn_delete" value = "상품삭제"/>
	<c:set var="btn_reply" value = "답글쓰기"/>
	<c:set var="btn_prelist" value = "목록보기"/>
	<c:set var="btn_mod" value = "수정"/>
	<c:set var="btn_mod_cancel" value="수정취소"/>
	<c:set var="btn_del" value = "삭제"/>
	<c:set var="btn_del_cancel" value = "삭제취소"/>
	<c:set var = "str_cart" value = "장바구니"/>
	<c:set var = "str_order" value = "주문하기"/>